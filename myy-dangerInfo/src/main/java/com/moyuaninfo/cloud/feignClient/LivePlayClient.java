package com.moyuaninfo.cloud.feignClient;

import com.moyuaninfo.cloud.VO.CameraVO;
import com.moyuaninfo.cloud.common.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//摄像机服务
@FeignClient(value = "MYY-LIVEPLAY", fallback = ClientFeignHystrix.class)
public interface LivePlayClient {

    @RequestMapping(value = "/liveplay/wsplay", method = RequestMethod.POST)
    public JsonResult<String> wsplay(@RequestBody CameraVO cameravo);
}
