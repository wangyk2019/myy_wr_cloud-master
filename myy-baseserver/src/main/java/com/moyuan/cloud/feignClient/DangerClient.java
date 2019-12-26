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

    @ApiOperation(value = "删除人员推送设置信息")
    @PostMapping(value = "/warnPush/deleteByBU")
    public JsonResult deleteByBU(@RequestParam(value = "areabottomid") long areabottomid,@RequestParam(value = "userid") long userid);

}
