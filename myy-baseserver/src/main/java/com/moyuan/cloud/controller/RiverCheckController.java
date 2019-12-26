package com.moyuan.cloud.controller;

import com.moyuan.cloud.pojo.JsonResult;
import com.moyuan.cloud.pojo.MyyRiverCheckplan;
import com.moyuan.cloud.pojo.MyyRiverChecktask;
import com.moyuan.cloud.service.RiverCheckService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @ClassName RiverCheckController
 * @Description TODO
 * @Author zhaoGq
 * @Date 2019/12/2 17:07
 * @Version 1.0
 **/
@Api(value = "RiverCheckController",tags="河道检查信息")
@RestController
//@CrossOrigin
@RequestMapping(value = "/riverCheck")
public class RiverCheckController {

    private static Logger logger = LoggerFactory.getLogger(RiverCheckController.class);

    @Autowired
    private RiverCheckService riverCheckService;

    /*
     * @Description //TODO
     * @Author zhaoGq
     * @Date 2019/12/2 17:24
     * @Param
     * @param request
     * @param session
     * @param myyRiverCheckplan
     * @Return com.moyuaninfo.waterinfo.model.JsonResult<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    @ApiOperation(value = "新增巡查计划", notes = "新增巡查计划")
    @RequestMapping(value = "/addRiverCheckPlan", method = RequestMethod.POST)
    public JsonResult<Map<String,Object>> addRiverCheckPlan(HttpServletRequest request, HttpSession session
            , @RequestBody @Valid MyyRiverCheckplan myyRiverCheckplan) {
        JsonResult<Map<String, Object>> jsrt = null;
        try {
            MyyRiverCheckplan addResult = riverCheckService.addRiverCheckPlan(myyRiverCheckplan);
            if (addResult != null) {
                jsrt = new JsonResult<>(0,"ok");
            }else {
                jsrt = new JsonResult<>(1,"error");
            }
        } catch (Exception e) {
            jsrt = new JsonResult<>(2, e.getClass().getName() + ":" + e.getMessage());
            logger.error("addRiverCheckPlan:" + e.getMessage());
        }
        return jsrt;
    }

    /*
     * @Description //TODO
     * @Author zhaoGq
     * @Date 2019/12/3 9:40
     * @Param
     * @param request
     * @param session
     * @param id
     * @Return com.moyuan.cloud.pojo.JsonResult<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    @ApiOperation(value = "删除巡查计划", notes = "删除巡查计划")
    @RequestMapping(value = "/deleteRiverCheckPlan", method = RequestMethod.POST)
    public JsonResult<Map<String,Object>> deleteRiverCheckPlan(HttpServletRequest request, HttpSession session
            , @ApiParam(value="巡查计划id",required=true) @RequestParam Integer id) {
        JsonResult<Map<String, Object>> jsrt = null;
        try {
            MyyRiverCheckplan myyRiverCheckplan = new MyyRiverCheckplan();
            myyRiverCheckplan.setId(Long.valueOf(id));
            riverCheckService.deleteRiverCheckPlan(myyRiverCheckplan);
            jsrt = new JsonResult<>(0,"ok");
        } catch (Exception e) {
            jsrt = new JsonResult<>(2, e.getClass().getName() + ":" + e.getMessage());
            logger.error("deleteRiverCheckPlan:" + e.getMessage());
        }
        return jsrt;
    }

    /*
     * @Description //TODO
     * @Author zhaoGq
     * @Date 2019/12/3 10:05
     * @Param
     * @param request
     * @param session
     * @param areaId
     * @param districtId
     * @Return com.moyuan.cloud.pojo.JsonResult<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    @ApiOperation(value = "查询本周巡查计划", notes = "查询本周巡查计划")
    @RequestMapping(value = "/getRiverCheckWeekPlanList", method = RequestMethod.POST)
    public JsonResult<List<Map<String,Object>>> getRiverCheckWeekPlanList(HttpServletRequest request, HttpSession session
            , @ApiParam("河道id") @RequestParam(value="areaId",required=false) Integer areaId
            , @ApiParam(value="组织id",required=true) @RequestParam Integer districtId) {
        JsonResult<List<Map<String, Object>>> jsrt = null;
        try {
            List<Map<String, Object>> riverCheckWeekPlanList = riverCheckService.getRiverCheckWeekPlanList(areaId, districtId);
            jsrt = new JsonResult<List<Map<String, Object>>>(0,"ok",riverCheckWeekPlanList);
        } catch (Exception e) {
            jsrt = new JsonResult<>(2, e.getClass().getName() + ":" + e.getMessage());
            logger.error("getRiverCheckWeekPlanList:" + e.getMessage());
        }
        return jsrt;
    }

    /*
     * @Description //TODO
     * @Author zhaoGq
     * @Date 2019/12/3 11:21
     * @Param
     * @param request
     * @param session
     * @param myyRiverChecktask
     * @Return com.moyuan.cloud.pojo.JsonResult<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    @ApiOperation(value = "新增巡查任务", notes = "新增巡查任务")
    @RequestMapping(value = "/addRiverCheckTask", method = RequestMethod.POST)
    public JsonResult<Map<String,Object>> addRiverCheckTask(HttpServletRequest request, HttpSession session
            , @RequestBody @Valid MyyRiverChecktask myyRiverChecktask) {
        JsonResult<Map<String, Object>> jsrt = null;
        try {
            MyyRiverChecktask addResult = riverCheckService.addRiverCheckTask(myyRiverChecktask);
            if (addResult != null) {
                jsrt = new JsonResult<>(0,"ok");
            }else {
                jsrt = new JsonResult<>(1,"error");
            }
        } catch (Exception e) {
            jsrt = new JsonResult<>(2, e.getClass().getName() + ":" + e.getMessage());
            logger.error("addRiverCheckTask:" + e.getMessage());
        }
        return jsrt;
    }

    /*
     * @Description //TODO
     * @Author zhaoGq
     * @Date 2019/12/3 13:34
     * @Param
     * @param request
     * @param session
     * @param districtId
     * @param areaId
     * @param areaSite
     * @param areaDetail
     * @param beginTime
     * @param endTime
     * @Return com.moyuan.cloud.pojo.JsonResult<java.util.List<java.util.Map<java.lang.String,java.lang.Object>>>
     **/
    @ApiOperation(value = "查询本周巡查任务", notes = "查询本周巡查任务")
    @RequestMapping(value = "/getRiverCheckWeekTaskList", method = RequestMethod.POST)
    public JsonResult<List<Map<String,Object>>> getRiverCheckWeekTaskList(HttpServletRequest request, HttpSession session
            , @ApiParam("组织id") @RequestParam(value="districtId",required=false) Integer districtId
            , @ApiParam("河道id") @RequestParam(value="areaId",required=false) Integer areaId
            , @ApiParam("河道位置") @RequestParam(value="areaSite",required=false) String areaSite
            , @ApiParam("河岸信息") @RequestParam(value="areaDetail",required=false) String areaDetail
            , @ApiParam("开始时间") @RequestParam(value="beginTime",required=false) String beginTime
            , @ApiParam("结束时间") @RequestParam(value="endTime",required=false) String endTime) {
        JsonResult<List<Map<String, Object>>> jsrt = null;
        try {
            List<Map<String, Object>> riverCheckWeekTaskList = riverCheckService.getRiverCheckWeekTaskList(districtId, areaId
            ,areaSite,areaDetail,beginTime,endTime);
            jsrt = new JsonResult<List<Map<String, Object>>>(0,"ok",riverCheckWeekTaskList);
        } catch (Exception e) {
            jsrt = new JsonResult<>(2, e.getClass().getName() + ":" + e.getMessage());
            logger.error("getRiverCheckWeekTaskList:" + e.getMessage());
        }
        return jsrt;
    }



}





