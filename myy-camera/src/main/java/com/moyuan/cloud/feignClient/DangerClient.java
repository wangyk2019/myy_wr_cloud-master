package com.moyuan.cloud.feignClient;

import com.moyuan.cloud.pojo.JsonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="myy-dangerinfo",fallback = ClientFeignHystrix.class)
public interface DangerClient {
    @RequestMapping(value="/dangerinfo/delayCheck", method= RequestMethod.POST)
    public JsonResult delayCheck(@RequestParam(value = "orgid") long orgid);

    @ApiOperation(value = "添加摄像头险情信息",notes = "添加摄像头后默认生成",httpMethod = "POST")
    @PostMapping(value = "/camWarn/addCamWarn_cam")
    public JsonResult addCamWarn_cam(@RequestParam(value = "orgid") long orgid,@RequestParam(value = "cameraid") long cameraid);

}
