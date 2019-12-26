package com.moyuaninfo.cloud.feignClient;

import com.moyuaninfo.cloud.VO.PushParamIn;
import com.moyuaninfo.cloud.common.JsonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="myy-push",fallback = ClientFeignHystrix.class)
public interface PushClient {
    @ApiOperation(value="短信", notes="手机号，短信模板参数")
    @RequestMapping(value="/msg", method= RequestMethod.POST)
    public JsonResult pushMsg(@RequestBody PushParamIn paramIn);

    @ApiOperation(value="语音消息", notes="手机号，语音模板参数")
    @RequestMapping(value="/phone", method= RequestMethod.POST)
    public JsonResult pushPhone(@RequestBody PushParamIn pushParamIn);

    @ApiOperation(value="app推送", notes="根据组织id")
    @RequestMapping(value="/pushToAppsByUserid", method= RequestMethod.POST)
    public JsonResult pushToAppsByUserid(@RequestBody PushParamIn pushParamIn);
}
