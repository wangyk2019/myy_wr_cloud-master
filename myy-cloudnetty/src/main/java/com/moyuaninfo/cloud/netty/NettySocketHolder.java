package com.moyuaninfo.cloud.netty;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NettySocketHolder {
    private static final Map<String, MyHttpHandler> HTTPMAP = new ConcurrentHashMap<>(16);
    public static void putHTTP(String id, MyHttpHandler socketChannel) {
        HTTPMAP.put(id, socketChannel);
    }
    public static MyHttpHandler getHTTP(String id) {
        return HTTPMAP.get(id);
    }
    public static Map<String, MyHttpHandler> getMAP() {
        return HTTPMAP;
    }
    public static void removeHTTP(MyHttpHandler nioSocketChannel) {
        HTTPMAP.entrySet().stream().filter(entry -> entry.getValue() == nioSocketChannel).forEach(entry -> HTTPMAP.remove(entry.getKey()));
    }

//    public static HashMap<String, JSONObject> sendmap = new HashMap<String,JSONObject>();
//    public static HashMap<String, JSONObject> receivemap = new HashMap<String,JSONObject>();
}
