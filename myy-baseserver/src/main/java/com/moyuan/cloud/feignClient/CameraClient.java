package com.moyuan.cloud.feignClient;

import com.moyuan.cloud.VO.FindCameraByParams;
import com.moyuan.cloud.pojo.JsonResult;
import com.moyuan.cloud.pojo.MyyCamera;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="myy-camera",fallback = ClientFeignHystrix.class)
public interface CameraClient {

    @ApiOperation(value = "查询摄像头信息")
    @PostMapping(value = "/findByParams")
    public JsonResult<List<MyyCamera>> findByParams(@RequestBody FindCameraByParams findCameraByParams);

    @ApiOperation(value = "查询摄像头信息")
    @PostMapping(value = "/findAllByDistrict")
    public JsonResult<List<MyyCamera>> findAllByDistrict(@RequestParam(value = "id") long id);

    @ApiOperation(value = "区域3级id查询摄像头信息")
    @PostMapping(value = "/findAllByAreabottomid")
    public JsonResult<List<MyyCamera>> findAllByAreabottomid(@RequestParam(value = "id") long id);

}
