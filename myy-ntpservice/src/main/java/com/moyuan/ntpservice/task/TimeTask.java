package com.moyuan.ntpservice.task;

import com.alibaba.fastjson.JSONObject;
import com.moyuan.ntpservice.feignclient.BaseServerClient;
import com.moyuan.ntpservice.service.JsonResult;
import com.moyuan.ntpservice.service.MyConstants;
import com.moyuan.ntpservice.service.MyyDistrict;
import com.moyuan.ntpservice.service.NTPTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@Component
public class TimeTask {
    @Autowired
    BaseServerClient baseServerClient;

    @Scheduled(fixedDelay = 30*60*1000)
    public void updateTime(){

        Date ntptime = NTPTime.getDateTime();

        JSONObject taskobj = new JSONObject();

        JsonResult<List<MyyDistrict>> jst = baseServerClient.findAllDistricts();
        if (jst.getCode() == 0) {
            List<MyyDistrict> districts = jst.getData();
            MyyDistrict district = null;
            for (int i = 0; i < districts.size(); i++) {
                district = districts.get(i);
                taskobj.put("client", "org-" + district.getId());
                taskobj.put("task", "ntptime");
                taskobj.put("msgtype", "request1");
                taskobj.put("ntptime", ntptime);

                taskobj.put("api", "/hik/ntpForCloud");
                taskobj.put("apiclient", "localhik");

                RestTemplate restTemplate = new RestTemplate();
                JSONObject jsrt = restTemplate.postForObject(MyConstants.NETTY_SEND, taskobj, JSONObject.class);
                if (jsrt.getIntValue("code") == 0) {
                    System.out.println("NTP发送成功");
                } else {
                    System.out.println("NTP发送失败");
                }

            }
        }

    }
}
