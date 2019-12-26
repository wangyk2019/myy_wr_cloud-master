package com.moyuaninfo.liveplay;

import com.alibaba.fastjson.JSONObject;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.live.v20180801.LiveClient;
import com.tencentcloudapi.live.v20180801.models.DayStreamPlayInfo;
import com.tencentcloudapi.live.v20180801.models.DescribeStreamPlayInfoListRequest;
import com.tencentcloudapi.live.v20180801.models.DescribeStreamPlayInfoListResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Calendar;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class LiveplayApplicationTests {

    @Test
    public void contextLoads() {

//        try{
//
//            Credential cred = new Credential("AKID3tZNK1wcjkeN6ci61uDH7lHbfU8ZajU6", "5vaAWbQ3zkGau7exqXfZGXggaIm0NCpD");
//
//            HttpProfile httpProfile = new HttpProfile();
//            httpProfile.setEndpoint("live.tencentcloudapi.com");
//
//            ClientProfile clientProfile = new ClientProfile();
//            clientProfile.setHttpProfile(httpProfile);
//
//            Calendar now = null;
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            now = Calendar.getInstance();
//            now.add(Calendar.MINUTE, -6);
//            String starttime = sdf.format(now.getTime());
//            now = Calendar.getInstance();
//            String endtime = sdf.format(now.getTime());
//
//            LiveClient client = new LiveClient(cred, "", clientProfile);
////"StreamName":"cam202",
//            String params = "{\"PlayDomain\":\"liveplay.moyuantech.com\",\"StreamName\":\"test1\",\"StartTime\":\""+starttime+"\",\"EndTime\":\""+endtime+"\"}";
//            DescribeStreamPlayInfoListRequest req = DescribeStreamPlayInfoListRequest.fromJsonString(params, DescribeStreamPlayInfoListRequest.class);
//
//            DescribeStreamPlayInfoListResponse resp = client.DescribeStreamPlayInfoList(req);
//
//            System.out.println(DescribeStreamPlayInfoListRequest.toJsonString(resp));
//
//            for (int i = 0; i < resp.getDataInfoList().length; i++) {
//                DayStreamPlayInfo datainfo = resp.getDataInfoList()[i];
//                if (datainfo.getOnline()>0){
//                    System.out.println("时间："+datainfo.getTime());
//                    System.out.println("在线人数："+datainfo.getOnline());
//                }else {
//                    System.out.println("*****时间："+datainfo.getTime());
//                    System.out.println("*****在线人数："+datainfo.getOnline());
//                }
//            }
//
//        } catch (TencentCloudSDKException e) {
//            System.out.println(e.toString());
//        }
    }

}
