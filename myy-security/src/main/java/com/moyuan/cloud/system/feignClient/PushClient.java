package com.moyuan.cloud.system.feignClient;

import com.moyuan.cloud.security.entity.JsonResult;
import com.moyuan.cloud.system.VO.PushParamIn;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "MYY-PUSH", fallback = ClientFeignHystrix.class)
public interface PushClient {

    @ApiOperation(value = "短信验证码", notes = "手机号，短信模板参数")
    @RequestMapping(value = "/msgcode", method = RequestMethod.POST)
    JsonResult pushMsgCode(@RequestBody PushParamIn pushParamIn);
}
