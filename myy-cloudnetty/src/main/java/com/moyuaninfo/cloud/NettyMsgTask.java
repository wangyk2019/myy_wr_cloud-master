package com.moyuaninfo.cloud;

import com.alibaba.fastjson.JSONObject;
import com.moyuaninfo.cloud.netty.MyHttpHandler;
import com.moyuaninfo.cloud.netty.NettySocketHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Slf4j
@Component
public class NettyMsgTask {

    private String rtsplink;

    public NettyMsgTask() {
    }

    public NettyMsgTask(String link) {
        this.rtsplink = link;
    }

//    @Scheduled(initialDelay = 10000,fixedDelay = 1000)
    public void sendmsg() throws Exception {

//        if(NettySocketHolder.sendmap.keySet().isEmpty()){
//            return;
//        }
//        Iterator itor = NettySocketHolder.sendmap.keySet().iterator();
//        while (itor.hasNext()){
//            String key = (String) itor.next();
//            String[] keys = key.split("\\*");
//            JSONObject value = NettySocketHolder.sendmap.get(keys[1]);
//            MyHttpHandler handle = NettySocketHolder.getHTTP(keys[0]);
//            handle.send(handle.chc,value,null,"String");
//        }

        return ;
    }
}
