package com.moyuaninfo.waterinfo.common;

import com.moyuaninfo.waterinfo.utils.CRC16;
import com.moyuaninfo.waterinfo.utils.HexStrToBytes;
import gnu.io.*;
import org.springframework.boot.web.server.PortInUseException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.TooManyListenersException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ContinueReadDev
 * @Description TODO
 * @Author zhaoGq
 * @Date 2019/12/11 16:30
 * @Version 1.0
 **/
@Component
public class ContinueReadDev extends Thread implements SerialPortEventListener {

    // 监听器,独立开辟一个线程监听串口数据
    static CommPortIdentifier portId; // 串口通信管理类
    static Enumeration<?> portList; // 有效连接上的端口的枚举
    InputStream inputStream; // 从串口来的输入流
    static OutputStream outputStream;// 向串口输出的流
    static SerialPort serialPort; // 串口的引用
    // 堵塞队列用来存放读到的数据
    private static BlockingQueue<String> msgQueue = new LinkedBlockingQueue<String>();

    volatile static boolean isRunning = true;

    @Override
    /**
     * SerialPort EventListene 的方法,持续监听端口上是否有数据流
     */
    public void serialEvent(SerialPortEvent event) {

        switch (event.getEventType()) {
            case SerialPortEvent.BI:
            case SerialPortEvent.OE:
            case SerialPortEvent.FE:
            case SerialPortEvent.PE:
            case SerialPortEvent.CD:
            case SerialPortEvent.CTS:
            case SerialPortEvent.DSR:
            case SerialPortEvent.RI:
            case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
                break;
            // 当有可用数据时读取数据
            case SerialPortEvent.DATA_AVAILABLE:
                byte[] readBuffer = new byte[20];
                try {
                    int numBytes = -1;
                    while (inputStream.available() > 0) {
                        numBytes = inputStream.read(readBuffer);

                        if (numBytes > 0) {
                            boolean add = msgQueue.add(new String(readBuffer));
                            // 重新构造缓冲对象，否则有可能会影响接下来接收的数据
                            readBuffer = new byte[20];
                        } else {
                             msgQueue.add("没有读到数据");
                        }
                    }
                } catch (IOException e) {

                }
                break;
        }
    }

    /**
     *
     * 通过程序打开串口，设置监听器以及相关的参数
     *
     * @return 返回1 表示端口打开成功，返回 0表示端口打开失败
     */
    public int startComPort() {
        // 通过串口通信管理类获得当前连接上的串口列表
        portList = CommPortIdentifier.getPortIdentifiers();

        while (portList.hasMoreElements()) {

            // 获取相应串口对象
            portId = (CommPortIdentifier) portList.nextElement();

            System.out.println("设备类型：--->" + portId.getPortType());
            System.out.println("设备名称：---->" + portId.getName());
            // 判断端口类型是否为串口
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                // 判断如果COM3串口存在，就打开该串口
                if (portId.getName().equals("COM3")) {
                    try {
                        // 打开串口名字为COM3(名字任意),延迟为2毫秒
                        serialPort = (SerialPort) portId.open("COM_3", 2000);

                    } catch (PortInUseException e) {
                        e.printStackTrace();
                        return 0;
                    } catch (gnu.io.PortInUseException e) {
                        e.printStackTrace();
                    }
                    // 设置当前串口的输入输出流
                    try {
                        inputStream = serialPort.getInputStream();
                        outputStream = serialPort.getOutputStream();
                    } catch (IOException e) {
                        e.printStackTrace();
                        return 0;
                    }
                    // 给当前串口添加一个监听器
                    try {
                        serialPort.addEventListener(this);
                    } catch (TooManyListenersException e) {
                        e.printStackTrace();
                        return 0;
                    }
                    // 设置监听器生效，即：当有数据时通知
                    serialPort.notifyOnDataAvailable(true);

                    // 设置串口的一些读写参数
                    try {
                        // 比特率、数据位、停止位、奇偶校验位
                        serialPort.setSerialPortParams(9600,
                                SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
                                SerialPort.PARITY_NONE);
                    } catch (UnsupportedCommOperationException e) {
                        e.printStackTrace();
                        return 0;
                    }
                    return 1;
                }
            }
        }
        return 0;
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                System.out.println("--------------任务处理线程运行了--------------");
                // 如果堵塞队列中存在数据就将其输出
                if (msgQueue.size() > 0) {
                    // 从队列中获取值，如果队列中没有值，线程会一直阻塞，直到队列中有值，并且该方法取得了该值。
                    // System.out.println(">>>>>接收到的消息：" + msgQueue.take());
                    // msgQueue.take();

                    // 在给定的时间里，从队列中获取值，时间到了直接调用普通的poll方法，为null则直接返回null。
                    String poll = msgQueue.poll(Global.REQUEST_RESPONSE_TIME_DIFFERENCE, TimeUnit.MILLISECONDS);
                    System.out.println("任务运行，接收到的消息：" + poll);
                }
                try {
                    // 每次间隔
                    Thread.sleep(Global.REQUEST_TIME_DIFFERENCE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("线程任务结束。。。。。。");
    }

    /*
     * @Description 发送报文请求
     * @Author zhaoGq
     * @Date 2019/12/12 15:35
     * @Param
     * @param param
     * @Return void
     **/
    public static void sendRequestMessage (byte[] param) {
        ContinueReadDev cRead = new ContinueReadDev();
        int i = cRead.startComPort();
        if (i == 1) {
            try {
                outputStream.write(param, 0, param.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            return;
        }
    }

    /*
     * @Description 接收响应信息
     * @Author zhaoGq
     * @Date 2019/12/13 14:36
     * @Param
     * @Return java.lang.String
     **/
    public static String getResponseMessage() {
        ContinueReadDev cRead = new ContinueReadDev();
        String take = "";
        // 启动线程来处理收到的数据
        cRead.start();
        try {
            // 从队列中获取值，如果队列中没有值，线程会一直阻塞，直到队列中有值，并且该方法取得了该值。
            // take = msgQueue.take();
            // System.out.println("接收响应：" + take);

            // 在给定的时间里，从队列中获取值，时间到了直接调用普通的poll方法，为null则直接返回null。
            take = msgQueue.poll(Global.REQUEST_RESPONSE_TIME_DIFFERENCE, TimeUnit.SECONDS);
            // 每次请求设备后，先关闭串口
            serialPort.close();
            isRunning = false;
            return take;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        serialPort.close();
        isRunning = false;
        return take;
    }

//    public static void main(String[] args) {
//        // 发送请求报文
////        byte[] byteResult = {11,03,00,00,00,02};
////        sendRequestMessage(byteResult);
//        CRC16 crc16 = new CRC16();
//        HexStrToBytes hexStrToBytes = new HexStrToBytes();
//        List<String> list = new ArrayList<>();
//        list.add("060300000002");
//        list.add("060300040002");
//        list.add("070300000002");
//        for (String param : list) {
//            // 计算crc16校验码
//            String crc16Result = crc16.getCRC(param);
//            // 请求信息字符
//            String requestStr = param + crc16Result;
//            byte[] byteResult = hexStrToBytes.hexStrToBinaryStr(requestStr);
//            // 发送请求
//            sendRequestMessage(byteResult);
//
//            // 接收响应信息
//            String result = getResponseMessage();
//            System.out.println("接收到的响应：" + result);
//        }
//    }



}

