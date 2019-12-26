package com.moyuaninfo.liveplay.task;

import com.alibaba.fastjson.JSONObject;
import com.moyuaninfo.liveplay.MyConstants;
import com.moyuaninfo.liveplay.entity.Camera;
import com.moyuaninfo.liveplay.entity.JsonResult;
import com.moyuaninfo.liveplay.services.CameraService;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.live.v20180801.LiveClient;
import com.tencentcloudapi.live.v20180801.models.DayStreamPlayInfo;
import com.tencentcloudapi.live.v20180801.models.DescribeStreamPlayInfoListRequest;
import com.tencentcloudapi.live.v20180801.models.DescribeStreamPlayInfoListResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Slf4j
@Component
public class PushershutTask {

    @Autowired
    private CameraService cameraservice;

    @Scheduled(initialDelay = 10000, fixedDelay = 300000)
    public void shut() {

        try {

            Credential cred = new Credential(MyConstants.COS_SECRETID, MyConstants.COS_SECRETKEY);

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("live.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            LiveClient client = new LiveClient(cred, "", clientProfile);

            List<Camera> cameralist = cameraservice.findAll();
            Camera camera = null;
            Calendar now = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (int i = 0; i < cameralist.size(); i++) {
                camera = cameralist.get(i);

                now = Calendar.getInstance();
                now.add(Calendar.MINUTE, -3);
                String starttime = sdf.format(now.getTime());
                now = Calendar.getInstance();
                String endtime = sdf.format(now.getTime());

                String params = "{\"PlayDomain\":\"" + MyConstants.PLAY_DEMAIN + "\",\"StreamName\":\"" + camera.getStreamname() + "\",\"StartTime\":\"" + starttime + "\",\"EndTime\":\"" + endtime + "\"}";
                DescribeStreamPlayInfoListRequest req = DescribeStreamPlayInfoListRequest.fromJsonString(params, DescribeStreamPlayInfoListRequest.class);

                DescribeStreamPlayInfoListResponse resp = client.DescribeStreamPlayInfoList(req);

//                System.out.println(DescribeStreamPlayInfoListRequest.toJsonString(resp));

                int online = 0;
                for (int j = 0; j < resp.getDataInfoList().length; j++) {
                    DayStreamPlayInfo datainfo = resp.getDataInfoList()[j];
                    if (datainfo.getOnline() > 0) {
                        online = datainfo.getOnline();
                    }
                }
                if (online <= 0 && camera.getLinkstatus().equals("1")) {
                    JSONObject taskobj = new JSONObject();
                    taskobj.put("client", "org-" + camera.getAreaid());
                    taskobj.put("task", "shutpusher");
                    taskobj.put("msgtype", "request1");
                    taskobj.put("streamname", camera.getStreamname());
                    taskobj.put("cameraid", camera.getId());

                    taskobj.put("api", "/stream/shutPusher");
                    taskobj.put("apiclient", "localservice");

                    RestTemplate restTemplate = new RestTemplate();
                    JsonResult<String> jsrt = restTemplate.postForObject(MyConstants.NETTY_SEND, taskobj, JsonResult.class);
                    if (jsrt.getCode() == 0) {
                        System.out.println("关闭直播发送成功");
                    } else {
                        System.out.println("关闭直播发送失败");
                    }
                }
            }

        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }
}
