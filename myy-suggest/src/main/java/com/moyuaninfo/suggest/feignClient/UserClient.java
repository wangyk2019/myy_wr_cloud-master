package com.moyuaninfo.suggest.feignClient;

import com.moyuaninfo.suggest.model.JsonResult;
import com.moyuaninfo.suggest.model.MyyUser;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="myy-baseserver",fallback = ClientFeignHystrix.class)
public interface UserClient {

    @ApiOperation(value = "查询用户信息")
    @PostMapping(value = "/user/findUser")
    public JsonResult<MyyUser> findUser(@RequestParam(value = "id") long id);

}
