package com.moyuan.ntpservice.feignclient;

import com.moyuan.ntpservice.service.JsonResult;
import com.moyuan.ntpservice.service.MyyDistrict;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="MYY-BASESERVER")
public interface BaseServerClient {

    @ApiOperation(value = "查询所有组织信息",httpMethod = "POST")
    @PostMapping(value = "/district/findAllDistricts")
    public JsonResult<List<MyyDistrict>> findAllDistricts();

}
