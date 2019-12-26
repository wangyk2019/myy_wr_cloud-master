package com.moyuaninfo.cloud.feignClient;

import com.alibaba.fastjson.JSONObject;
import com.moyuaninfo.cloud.common.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="myy-cloudnetty",fallback = ClientFeignHystrix.class)
public interface CloudNettyClient {

    @RequestMapping(value = "/cloudNetty/sendMsg", method = RequestMethod.POST)
    public JsonResult<String> nettySend(@RequestBody JSONObject args);
}
