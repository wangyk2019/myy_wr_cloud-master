package com.moyuaninfo.cloud.feignClient;

import com.moyuaninfo.cloud.common.JsonResult;
import com.moyuaninfo.cloud.pojo.MyyCamera;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="myy-camera",fallback = ClientFeignHystrix.class)
public interface CameraClient {
    @ApiOperation(value = "查询摄像头信息",notes = "跟id查",httpMethod = "POST")
    @PostMapping(value = "/findById")
    public JsonResult<MyyCamera> findById(@RequestParam(value = "id") long id);

    @ApiOperation(value = "查询摄像头信息",notes = "组织id",httpMethod = "POST")
    @PostMapping(value = "/findAllByDistrict")
    public JsonResult<List<MyyCamera>> findAllByDistrict(@RequestParam(value = "id") long id);

    @ApiOperation(value = "查找好坏摄像头", notes = "高校ID")
    @RequestMapping(value = "/findAllByCameraAndStatus", method = RequestMethod.POST)
    public JsonResult<List<MyyCamera>> findAllByCameraAndStatus(@RequestParam(value = "colleageid") long colleageid,
                                                                    @RequestParam(value = "status") String status);
}
