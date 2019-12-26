package com.moyuan.cloud.common;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.impl.Transparent.Notification;
import com.gexin.rp.sdk.base.notify.Notify;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.dto.GtReq;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.gexin.rp.sdk.template.style.Style6;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 移动端消息推送类
 * @author wyk
 * @date 2019年9月8日
 */
public class PushAppOnline {

    private static String appId = "BXIc0SIiv78IsuiUdXuI4";
    private static String appKey = "u3EtQhtSWw65o59AnsmkJ6";
    private static String masterSecret = "42W7xMsO8M7aiVc2Aoc0Y3";

    private static String url = "http://sdk.open.api.igexin.com/apiex.htm";


    public String pushToUserOne(List<String> cids,String title,String content) throws IOException{
        String pushText="{title:'"+title+"',content:'"+content+"',payload:'test'}";
        TransmissionTemplate template = new TransmissionTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
        template.setTransmissionType(2);
        template.setTransmissionContent(content); //透传内容


        Notify notify = new Notify();
        notify.setTitle(title);
        notify.setContent(content);
//        notify.setPayload("test");
//        notify.setType(GtReq.NotifyInfo.Type._intent);
        // notify.setUrl("https://dev.getui.com/");

//        notify.setIntent("intent:#Intent;action=android.intent.action.huaweipush;launchFlags=0x14000000;" +
//                "package=com.moyuaninfo.fire;component=com.moyuaninfo.fire/com.getui.demo.MainActivity;" +
//                "S.UP-OL-SU=true;" +
//                "S.title="+title+";S.content="+content+";S.payload="+pushText+";end");
        notify.setIntent("intent:#Intent;action=android.intent.action.huaweipush;launchFlags=0x14000000;" +
                "component=com.moyuaninfo.waterway/com.getui.demo.MainActivity;" +
                "S.UP-OL-SU=true;" +
                "S.title="+title+";S.content="+content+";S.payload="+pushText+";end");
        notify.setType(GtReq.NotifyInfo.Type._intent);
        template.set3rdNotifyInfo(notify);//厂商通道

        APNPayload payload = new APNPayload();
        //在已有数字基础上加1显示，设置为-1时，在已有数字上减1显示，设置为数字时，显示指定数字
        payload.setAutoBadge("+1");
        payload.setContentAvailable(0);
        //ios 12.0 以上可以使用 Dictionary 类型的 sound
        payload.setSound("default");
        payload.setCategory("$由客户端定义");
        payload.addCustomMsg("tilte", title);
        payload.addCustomMsg("content", content);

        APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
        alertMsg.setTitle(title);
        alertMsg.setBody(content);
        payload.setAlertMsg(alertMsg);

        //简单模式APNPayload.SimpleMsg
//        payload.setAlertMsg(new APNPayload.SimpleAlertMsg(content));

//        payload.setAlertMsg(new APNPayload.DictionaryAlertMsg().setTitle(title));  //字典模式使用APNPayload.DictionaryAlertMsg
//        payload.setAlertMsg(new APNPayload.DictionaryAlertMsg().setBody(content));

        //设置语音播报类型，int类型，0.不可用 1.播放body 2.播放自定义文本
//        payload.setVoicePlayType(2);
        //设置语音播报内容，String类型，非必须参数，用户自定义播放内容，仅在voicePlayMessage=2时生效
        //注：当"定义类型"=2, "定义内容"为空时则忽略不播放
//        payload.setVoicePlayMessage("定义内容");

        // 添加多媒体资源
//        payload.addMultiMedia(new MultiMedia().setResType(MultiMedia.MediaType.pic)
//                .setResUrl("资源文件地址")
//                .setOnlyWifi(true));
        template.setAPNInfo(payload); //ios消息推送


        IGtPush push = new IGtPush(appKey, masterSecret);
        ListMessage message = new ListMessage();
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(12 * 3600 * 1000);
        message.setData(template);
        // 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
        message.setPushNetWorkType(0);
        List<Target> targets = new ArrayList<Target>();
        for(String cid:cids){
            Target target = new Target();
            target.setAppId(appId);
            target.setClientId(cid);
            targets.add(target);
        }

        //推送前通过该接口申请“ContentID”
        String contentId = push.getContentId(message);
        IPushResult ret = push.pushMessageToList(contentId, targets);
        System.out.println(ret.getResponse().toString());

        String result ="";
        if ("ok".equals(ret.getResponse().get("result"))){
            result = "ok";
        }else{
            result = ret.getResponse().toString();
        }
        return result;
    }

}
