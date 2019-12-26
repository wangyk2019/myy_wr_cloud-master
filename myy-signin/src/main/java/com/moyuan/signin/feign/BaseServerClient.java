package com.moyuan.signin.feign;

import com.moyuan.signin.pojo.JsonResult;
import com.moyuan.signin.pojo.MyyUser;
import com.moyuan.signin.vo.FindAllUserByParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "MYY-BASESERVER", fallback = ClientFeignHystrix.class)
public interface BaseServerClient {


    @ApiOperation(value = "查询人员信息", notes = "id", httpMethod = "POST")
    @PostMapping(value = "/user/findUser")
    public JsonResult<MyyUser> findUser(@RequestParam(value = "id") long id);

    @ApiOperation(value = "查询人员信息", notes = "多参数", httpMethod = "POST")
    @PostMapping(value = "/user/findUsersByParams")
    public JsonResult<List<MyyUser>> findUsersByParams(@RequestBody FindAllUserByParams findAllUserByParams);

}