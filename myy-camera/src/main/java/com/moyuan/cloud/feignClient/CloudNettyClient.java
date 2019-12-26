package com.moyuan.cloud.feignClient;

import com.alibaba.fastjson.JSONObject;
import com.moyuan.cloud.pojo.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="myy-cloudnetty",fallback = ClientFeignHystrix.class)
public interface CloudNettyClient {

    @RequestMapping(value = "/cloudNetty/sendMsg", method = RequestMethod.POST)
    public JsonResult<String> nettySend(@RequestBody JSONObject args);
}
