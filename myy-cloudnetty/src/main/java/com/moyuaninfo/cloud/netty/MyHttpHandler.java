package com.moyuaninfo.cloud.netty;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.moyuaninfo.cloud.MyConstants;
import com.moyuaninfo.cloud.NettyConfig;
import com.moyuaninfo.cloud.Tools;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import static io.netty.handler.codec.http.HttpMethod.POST;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

@Slf4j
@ChannelHandler.Sharable
public class MyHttpHandler extends SimpleChannelInboundHandler {
    public JSONObject textMsg = new JSONObject();
    public ChannelHandlerContext chc;
    public String responsetype;


//    private PrelimWarnService prelimWarnService;

    public MyHttpHandler() {
    }

//    public MyHttpHandler(PrelimWarnService prelimWarnService) {
//        this.prelimWarnService = prelimWarnService;
//    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (JSON.parse((String) msg) instanceof JSONObject) {
            handStringMessage(ctx, (String) msg);
            responsetype = "String";
        }

    }

    /**
     * 处理客户端与服务端之间的websocket业务
     *
     * @param ctx   ctx
     * @param frame frame
     */
    private void handStringMessage(ChannelHandlerContext ctx, String frame) {
        // 获取客户端向服务端发送的消息
        String msg = frame;
        chc = ctx;
        log.info("netty read msg:" + msg);

        JSONObject rceobj = (JSONObject) JSONObject.parse(msg);
        String task = String.valueOf(rceobj.get("task"));
        if ("register".equalsIgnoreCase(task)) {
            String registerid = rceobj.getString("client");
            NettySocketHolder.putHTTP(registerid, this);
            rceobj.put("result", "access");
            rceobj.put("msgtype", "request0");
            rceobj.put("timestamp", new Date().toString());
            chc.writeAndFlush(Unpooled.copiedBuffer((JSON.toJSONString(rceobj) + "$_").getBytes()));
            log.info("收到客户端" + registerid + "的注册消息: {}", msg);
        }
        else if ("heartbeat".equalsIgnoreCase(task)) {
            String registerid = rceobj.getString("client");
            String beat = rceobj.getString("beat");
            if (beat.contains("OK")) {
                rceobj.put("beatback", "ok");
            }
            rceobj.put("msgtype", "request0");
            rceobj.put("timestamp", new Date().toString());
            chc.writeAndFlush(Unpooled.copiedBuffer((JSON.toJSONString(rceobj) + "$_").getBytes()));
            if (NettySocketHolder.getHTTP(registerid) == null) {
                NettySocketHolder.putHTTP(registerid, this);
            }
            log.info("收到客户端" + registerid + "的心跳消息: {}", msg);
        }
//        else if("config".equalsIgnoreCase(task)){
//            String url = MyConstants.CAMAddr + "/app/camConfig";
//            RestTemplate restTemplate = new RestTemplate();
//            try{
//                String result = restTemplate.postForObject(url, rceobj, String.class);
//                log.info("系统配置信息获取："+result);
//            }
//            catch (Exception e){
//                log.error(e.getMessage());
//            }
//        }
//        else if ("initconstant".equalsIgnoreCase(task)) {
//            String url = MyConstants.LIVEURL + "/app/initConstants";
//            RestTemplate restTemplate = new RestTemplate();
//            try {
//                String result = restTemplate.postForObject(url, rceobj, String.class);
//                log.info("系统信息初始化获取："+result);
//            }
//            catch (Exception e)
//            {
//                log.error(e.getMessage());
//            }
//
//        }
//        else if ("testcamera".equalsIgnoreCase(task)) {
//            RestTemplate restTemplate = new RestTemplate();
//            String url = MyConstants.CAMAddr + "/app/camera/testCameraResult";
//            try{
//                String result = restTemplate.postForObject(url, rceobj, String.class);
//                log.info("摄像机ip测试："+result);
//            }
//            catch (Exception e){
//                log.error(e.getMessage());
//            }
//
//        }
//        else if ("deepdetection".equalsIgnoreCase(task)) {
//            String registerid = rceobj.getString("client");
//            log.info("收到客户端" + registerid + "的消息: {}", msg);
////            String url = MyConstants.WARNURL + "/app/warn/todeep";
//            String url = MyConstants.WARNURL + "/api/danger/addObj1";
//            RestTemplate restTemplate = new RestTemplate();
//            try{
//                String result = restTemplate.postForObject(url, rceobj, String.class);
//                log.info("发送预警信息的回应："+result);
//            }
//            catch (Exception e){
//                log.error(e.getMessage());
//            }
//        }
//        else if ("todeepdetection".equalsIgnoreCase(task)) {
//            String registerid = rceobj.getString("client");
//            log.info("收到客户端" + registerid + "的消息: {}", msg);
//            String url = MyConstants.WARNURL + "/api/danger/addObj11";
//            RestTemplate restTemplate = new RestTemplate();
//            try{
//                String result = restTemplate.postForObject(url, rceobj, String.class);
//                log.info("发送图片上传结果的回应："+result);
//            }
//            catch (Exception e){
//                log.error(e.getMessage());
//            }
//        }
//        // 推流
//        else if ("pushstream".equalsIgnoreCase(task)) {
//            String url = MyConstants.LIVEURL + "/app/camera/wsplayResult";
//            RestTemplate restTemplate = new RestTemplate();
//            try{
//                String result = restTemplate.postForObject(url, rceobj, String.class);
//                log.info("直播推流开启："+result);
//            }
//            catch (Exception e){
//                log.error(e.getMessage());
//            }
//
//        }
//        // 关流
//        else if ("shutpusher".equalsIgnoreCase(task)) {
//            String url = MyConstants.LIVEURL + "/app/camera/shutPusherResult";
//            RestTemplate restTemplate = new RestTemplate();
//            try{
//                String result = restTemplate.postForObject(url, rceobj, String.class);
//                log.info("直播断流："+result);
//            }
//            catch (Exception e){
//                log.error(e.getMessage());
//            }
//
//        }
        else {
            String api = rceobj.getString("api");
            String client = rceobj.getString("apiclient");
            String url = null;
            if (MyConstants.clientsmap.get(client) != null) {
                url = MyConstants.clientsmap.get(client) + api;
            } else {
                url = client + api;
            }
            RestTemplate restTemplate = new RestTemplate();
            try{
                String result = restTemplate.postForObject(url, rceobj, String.class);
                log.info("myy-cloudnetty:"+result);
            }
            catch (Exception e){
                log.error("myy-cloudnetty:"+e.getMessage());
            }
            log.info("myy-cloudnetty收到客户端的消息: {}", msg);
        }
    }

    /**
     * 客户端与服务端创建连接的时候调用
     *
     * @param ctx ctx
     * @throws Exception Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 将channel添加到channel group中
        NettyConfig.GROUP.add(ctx.channel());
        log.info("客户端与服务端连接开启...");
    }

    /**
     * 客户端与服务端断开连接的时候调用
     *
     * @param ctx ctx
     * @throws Exception Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // 从channel group中移除这个channel
        NettyConfig.GROUP.remove(ctx.channel());
        NettySocketHolder.removeHTTP(this);
        log.info("客户端与服务端关闭连接...");
    }

    /**
     * 服务端接收客户端发送过来的数据结束之后调用
     *
     * @param ctx ctx
     * @throws Exception Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // 清空数据
        ctx.flush();
        log.info("flush数据 {}", ctx.name());
    }

    /**
     * 工程出现异常的时候调用
     *
     * @param ctx   ctx
     * @param cause cause
     * @throws Exception Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 打印异常堆栈
        cause.printStackTrace();
        // 主动关闭连接
        ctx.close();
        log.error("WebSocket连接异常");
    }

    public void send(ChannelHandlerContext ctx, JSONObject msg, URI requestUrl, String responsetype) {
        if ("HTTP".equalsIgnoreCase(responsetype)) {
            String msgstr = JSON.toJSONString(msg);
            //配置HttpRequest的请求数据和一些配置信息
            FullHttpRequest request = null;
            request = new DefaultFullHttpRequest(HTTP_1_1, POST, requestUrl.toASCIIString(), Unpooled.wrappedBuffer(msgstr.getBytes(StandardCharsets.UTF_8)));
            request.headers()
                    .set(HttpHeaderNames.CONTENT_TYPE, "text/plain;charset=UTF-8")
                    //开启长连接
                    .set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE)
                    //设置传递请求内容的长度
                    .set(HttpHeaderNames.CONTENT_LENGTH, request.content().readableBytes())
                    .set(MyConstants.UPGRADE_STR, MyConstants.WEBSOCKET_STR)
                    .set("msg", msgstr);
            ctx.writeAndFlush(request);
        } else {
            String msgtype = msg.getString("msgtype");
            String msgid = null;
            if (msgtype!=null && !msgtype.equalsIgnoreCase("request1")) {
                msgid = Tools.getRandomString(6);
                msg.put("msgid", msgid);
                String register = msg.getString("client");
//                NettySocketHolder.sendmap.put(register+"*"+msgid, msg);
            }
            ctx.writeAndFlush(Unpooled.copiedBuffer((JSON.toJSONString(msg) + "$_").getBytes()));
        }
        log.info("服务端发往客户端的消息  msg:" + msg);
    }
}
