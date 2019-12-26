package com.moyuaninfo.liveplay.task;

import com.alibaba.fastjson.JSONObject;
import com.moyuaninfo.liveplay.MyConstants;
import com.moyuaninfo.liveplay.controller.LiveplayController;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.live.v20180801.LiveClient;
import com.tencentcloudapi.live.v20180801.models.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Slf4j
@Component
public class LimitPlayerTask {

    @Scheduled(initialDelay = 10000, fixedDelay = 180000)
    public void taskDo() {
        try {

            Credential cred = new Credential("AKID3tZNK1wcjkeN6ci61uDH7lHbfU8ZajU6", "5vaAWbQ3zkGau7exqXfZGXggaIm0NCpD");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("live.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            Calendar now = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            now = Calendar.getInstance();
            now.add(Calendar.MINUTE, -4);
            String starttime = sdf.format(now.getTime());
            now = Calendar.getInstance();
            String endtime = sdf.format(now.getTime());

            LiveClient client = new LiveClient(cred, "", clientProfile);

//            String params = "{\"PlayDomain\":\"liveplay.moyuantech.com\",\"StartTime\":\""+starttime+"\",\"EndTime\":\""+endtime+"\",\"PageSize\":\""+50+"\"}";

            JSONObject param = new JSONObject();
            param.put("PlayDomain", MyConstants.PUSH_DEMAIN);
            param.put("PageSize", 50);

            DescribeLiveStreamOnlineListRequest req = DescribeLiveStreamOnlineListRequest.fromJsonString(param.toJSONString(), DescribeLiveStreamOnlineListRequest.class);

            DescribeLiveStreamOnlineListResponse resp = client.DescribeLiveStreamOnlineList(req);

//            System.out.println(DescribeStreamPlayInfoListRequest.toJsonString(resp));
            DescribeStreamPlayInfoListRequest req2 = null;
            DescribeStreamPlayInfoListResponse resp2 = null;


            param.put("StartTime", starttime);
            param.put("EndTime", endtime);
            int onlinecount = 0;
            StreamOnlineInfo[] infos = resp.getOnlineInfo();
            for (int i = 0; i < resp.getTotalNum(); i++) {
                if (MyConstants.PLAY_DEMAIN.equalsIgnoreCase(infos[i].getDomainName())){
                    System.out.println("推流名：" + infos[i].getStreamName());

                    param.remove("StreamName");
                    param.put("StreamName", infos[i].getStreamName());

                    req2 = DescribeStreamPlayInfoListRequest.fromJsonString(param.toJSONString(), DescribeStreamPlayInfoListRequest.class);

                    resp2 = client.DescribeStreamPlayInfoList(req2);

                    for (int j = 0; j < resp2.getDataInfoList().length; j++) {
                        DayStreamPlayInfo datainfo = resp2.getDataInfoList()[j];
                        if (datainfo.getOnline() > 0) {
                            System.out.println("时间：" + datainfo.getTime());
                            System.out.println("在线人数：" + datainfo.getOnline());
                        } else {
                            System.out.println("*****时间：" + datainfo.getTime());
                            System.out.println("*****在线人数：" + datainfo.getOnline());
                        }
                        onlinecount += datainfo.getOnline();
                    }
                }
            }
            System.out.println("总在线人数：" + onlinecount);
            LiveplayController.playerRemain = 5 - onlinecount;
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }
}
