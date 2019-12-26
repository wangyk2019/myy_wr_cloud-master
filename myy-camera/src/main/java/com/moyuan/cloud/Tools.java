package com.moyuan.cloud;

import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Random;

public class Tools {

    private static final char[] DIGITS_LOWER =
            {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /****
     * 组装rtsp地址：
     * rtsp://username:password@ip:port/stream/channel/CID
     * 解释下
     * manufacture:制造商，区别海康，大华等
     * linksource:链接源 IP直连 "IP"  硬盘录像机 "DVR"
     * username:设备登录账号
     * password:设备登录密码
     * ip:设备的内网IP地址，如果是DVR则是DVR的IP
     * port:默认端口
     * stream:是视频流编码格式
     * channel:通道
     * CHID:通道ID  DVR连接时，1为通道，01是主码流，02是子码流  默认用子码流 102
     * 一般stream为streaming,也可以设置为h264流 ，channel为channels，或者是ch1
     ****/
    public static String getRtspUrl(String manufacture, String linksource, String username, String password,
                                    String ip, String port, String stream, String channel, String CHID) {
        String rtspurl = null;
        if (StringUtils.isNotBlank(manufacture) && manufacture.equalsIgnoreCase("hik")) {
            rtspurl = "rtsp://" + username + ":" + password + "@" + ip;
            if (StringUtils.isNotBlank(port)) {
                rtspurl += ":" + port;
            }
            if (StringUtils.isNotBlank(stream)) {
                rtspurl += "/" + stream;
            } else {
                rtspurl += "/Streaming";
            }
            if (StringUtils.isNotBlank(channel) && channel.contains("ch1")) {
                rtspurl += "/ch1/sub/av_stream";
            } else {
                rtspurl += "/Channels/";
                if (StringUtils.isNotBlank(CHID) && StringUtils.isNotBlank(linksource)
                        && linksource.equalsIgnoreCase("IP")) {
                    rtspurl += CHID;
                } else if (StringUtils.isNotBlank(CHID) && StringUtils.isNotBlank(linksource)
                        && linksource.equalsIgnoreCase("DVR")) {
                    rtspurl += CHID + "02";
                } else {
                    rtspurl += "1";
                }
            }
        }

        return rtspurl;
    }

    /****
     * 拆解rtsp地址：
     * rtsp://   admin  :  admin123456  @  10.192.44.101  :  554  /  h264  /  ch1
     * 数据保存在map中，根据key获取
     * key{username,password,ip,port,streamname,channel}
     */
    public static HashMap<String, String> decodeRtspUrl(String rtspurl) {
        HashMap<String, String> urlmap = new HashMap<String, String>();
        String[] temp = rtspurl.split("@");
        String[] temp1 = temp[0].split(":");
        urlmap.put("username", temp1[1].substring(2));
        urlmap.put("password", temp1[2]);
        String[] temp2 = temp[1].split("/");
        temp1 = null;
        temp1 = temp2[0].split(":");
        urlmap.put("ip", temp1[0]);
        urlmap.put("port", temp1[1]);
        urlmap.put("streamname", temp2[1]);
        urlmap.put("channel", temp2[2]);
        return urlmap;
    }

    /*
     * KEY+ streamName + txTime
     */
    public static String getSafeUrl(String key, String streamName, long txTime) {
        String input = new StringBuilder().
                append(key).
                append(streamName).
                append(Long.toHexString(txTime).toUpperCase()).toString();

        String txSecret = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            txSecret = byteArrayToHexString(
                    messageDigest.digest(input.getBytes("UTF-8")));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return txSecret == null ? "" :
                new StringBuilder().
                        append("txSecret=").
                        append(txSecret).
                        append("&").
                        append("txTime=").
                        append(Long.toHexString(txTime).toUpperCase()).
                        toString();
    }

    private static String byteArrayToHexString(byte[] data) {
        char[] out = new char[data.length << 1];

        for (int i = 0, j = 0; i < data.length; i++) {
            out[j++] = DIGITS_LOWER[(0xF0 & data[i]) >>> 4];
            out[j++] = DIGITS_LOWER[0x0F & data[i]];
        }
        return new String(out);
    }

    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /****
     * 无文件则创建，有文件则增量写
     * @param fileName
     * @param content
     */
    public static void writefile(String fileName, String content) {
        try {
            // 打开一个随机访问文件流，按读写方式
            RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            // 将写文件指针移到文件尾。
            randomFile.seek(fileLength);
            randomFile.write((content + "\r\n").getBytes());
            randomFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
