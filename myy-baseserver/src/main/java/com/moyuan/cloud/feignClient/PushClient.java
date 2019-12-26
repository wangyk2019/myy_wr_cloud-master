package com.moyuan.cloud.feignClient;

import com.moyuan.cloud.VO.PushParamIn;
import com.moyuan.cloud.pojo.JsonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="myy-push",fallback = ClientFeignHystrix.class)
public interface PushClient {

    @ApiOperation(value="短信验证码", notes="手机号，短信模板参数")
    @RequestMapping(value="/msgcode", method= RequestMethod.POST)
    public JsonResult pushMsgCode(@RequestBody PushParamIn pushParamIn);
}
