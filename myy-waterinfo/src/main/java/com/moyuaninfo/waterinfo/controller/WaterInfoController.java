package com.moyuaninfo.waterinfo.controller;

import com.moyuaninfo.waterinfo.common.Global;
import com.moyuaninfo.waterinfo.model.JsonResult;
import com.moyuaninfo.waterinfo.service.WaterInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @ClassName WaterInfoController
 * @Description 水质参数信息
 * @Author zhaoGq
 * @Date 2019/12/2 14:36
 * @Version 1.0
 **/
@Api(value = "水质参数信息",tags="WaterInfoController")
@RestController
public class WaterInfoController {

    private static Logger logger = LoggerFactory.getLogger(WaterInfoController.class);

    @Autowired
    private WaterInfoService waterInfoService;

    /*
     * @Description 添加设备检测的水质信息
     * @Author zhaoGq
     * @Date 2019/12/13 17:47
     * @Param
     * @param request
     * @param session
     * @Return com.moyuaninfo.waterinfo.model.JsonResult<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    @ApiOperation(value = "添加设备检测的水质信息", notes = "添加设备检测的水质信息")
    @RequestMapping(value = "/addDeviceCheckWaterInfo", method = RequestMethod.POST)
    public JsonResult<Map<String,Object>> addDeviceCheckWaterInfo() {
        JsonResult<Map<String, Object>> jsrt = null;
        try {
            waterInfoService.saveWaterInfo();
            jsrt = new JsonResult<>(0,"ok");
        } catch (Exception e) {
            jsrt = new JsonResult<>(2, e.getClass().getName() + ":" + e.getMessage());
            logger.error("addDeviceitem:" + e.getMessage());
        }
        return jsrt;
    }

    /*
     * @Description 查询水质参数类型列表
     * @Author zhaoGq
     * @Date 2019/12/2 15:06
     * @Param
     * @param request
     * @param session
     * @Return com.moyuaninfo.waterinfo.model.JsonResult<java.util.List<java.lang.String>>
     **/
    @ApiOperation(value = "查询水质参数类型列表", notes = "查询水质参数类型列表")
    @RequestMapping(value = "/getWaterQualityTypeList", method = RequestMethod.POST)
    public JsonResult<List<String>> getWaterQualityTypeList() {
        JsonResult<List<String>> jsrt = null;
        try {
            List<String> waterQualityTypeList = Arrays.asList(Global.WATER_QUALITY_TYPE);
            jsrt = new JsonResult<List<String>>(0,"ok",waterQualityTypeList);
        } catch (Exception e) {
            jsrt = new JsonResult<>(2, e.getClass().getName() + ":" + e.getMessage());
            logger.error("getWaterQualityTypeList:" + e.getMessage());
        }
        return jsrt;
    }

    /*
     * @Description 查询各个时间段各个水质参数列表
     * @Author zhaoGq
     * @Date 2019/12/2 15:14
     * @Param
     * @param request
     * @param session
     * @Return com.moyuaninfo.waterinfo.model.JsonResult<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    @ApiOperation(value = "查询各个时间段各个水质参数列表", notes = "查询各个时间段各个水质参数列表")
    @RequestMapping(value = "/getWaterQualityInfoListByTime", method = RequestMethod.POST)
    public JsonResult<Map<String, Object>> getWaterQualityInfoListByTime() {
        JsonResult<Map<String, Object>> jsrt = null;
        try {
            Map<String, Object> waterQualityTypeList = waterInfoService.getWaterQualityInfoListByTime();
            jsrt = new JsonResult<Map<String, Object>>(0,"ok",waterQualityTypeList);
        } catch (Exception e) {
            jsrt = new JsonResult<>(2, e.getClass().getName() + ":" + e.getMessage());
            logger.error("getWaterQualityInfoListByTime:" + e.getMessage());
        }
        return jsrt;
    }

    /*
     * @Description 查询当前各个水质参数列表
     * @Author zhaoGq
     * @Date 2019/12/2 16:47
     * @Param
     * @param request
     * @param session
     * @Return com.moyuaninfo.waterinfo.model.JsonResult<java.util.List<java.util.Map<java.lang.String,java.lang.Object>>>
     **/
    @ApiOperation(value = "查询当前各个水质参数列表", notes = "查询当前各个水质参数列表")
    @RequestMapping(value = "/getWaterQualityInfoListByNewest", method = RequestMethod.POST)
    public JsonResult<List<Map<String, Object>>> getWaterQualityInfoListByNewest() {
        JsonResult<List<Map<String, Object>>> jsrt = null;
        try {
            List<Map<String, Object>> waterQualityTypeList = waterInfoService.getWaterQualityInfoListByNewest();
            jsrt = new JsonResult<List<Map<String, Object>>>(0,"ok",waterQualityTypeList);
        } catch (Exception e) {
            jsrt = new JsonResult<>(2, e.getClass().getName() + ":" + e.getMessage());
            logger.error("getWaterQualityInfoListByNewest:" + e.getMessage());
        }
        return jsrt;
    }


}
