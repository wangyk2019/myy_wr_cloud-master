package com.moyuan.cloud.system.feignClient;

import com.moyuan.cloud.security.entity.JsonResult;
import com.moyuan.cloud.system.VO.PushParamIn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ClientFeignHystrix implements PushClient {
    //打印日志
    private static final Logger print = LoggerFactory.getLogger(ClientFeignHystrix.class);

    @Override
    public JsonResult pushMsgCode(PushParamIn pushParamIn) {
        return new JsonResult<>(1, "推送服务出现故障。。");
    }
}