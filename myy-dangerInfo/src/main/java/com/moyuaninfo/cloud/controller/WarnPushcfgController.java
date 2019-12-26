package com.moyuaninfo.cloud.controller;

import com.moyuaninfo.cloud.VO.FindAllCamWarnByParams;
import com.moyuaninfo.cloud.VO.WarnPushcfgSetOut;
import com.moyuaninfo.cloud.common.JsonResult;
import com.moyuaninfo.cloud.pojo.MyyWarnpushcfg;
import com.moyuaninfo.cloud.service.MyyWarnPushService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "人员推送配置接口",tags = "人员推送配置接口")
@RequestMapping("/warnPush")
public class WarnPushcfgController {
    @Autowired
    MyyWarnPushService myyWarnPushService;

    @ApiOperation(value = "添加人员推送设置信息")
    @PostMapping(value = "/addWarnPushcfg")
    public JsonResult<MyyWarnpushcfg> save(@RequestBody MyyWarnpushcfg myyWarnpushcfg){
        JsonResult jr = null;
        try {
            myyWarnPushService.save(myyWarnpushcfg);
            jr = new JsonResult<>(0);
        }catch (Exception e){
            jr=  new JsonResult(1,e.getMessage().toString());
        }
        return jr;
    }

    @ApiOperation(value = "删除人员推送设置信息")
    @PostMapping(value = "/deteleWarnPushcfg")
    public JsonResult delete(@RequestParam(value = "id") long id){
        JsonResult jsonResult = null;
        try{
            myyWarnPushService.updateStateById(id);
            jsonResult = new JsonResult(0,"操作成功！");
        }catch (Exception e){
            jsonResult = new JsonResult(1,e.getMessage());
        }
        return jsonResult;
    }

    @ApiOperation(value = "删除人员推送设置信息")
    @PostMapping(value = "/deleteByBU")
    public JsonResult deleteByBU(@RequestParam(value = "areabottomid") long areabottomid,@RequestParam(value = "userid") long userid){
        JsonResult jsonResult = null;
        try{
            myyWarnPushService.updateStateByBU(areabottomid,userid);
            jsonResult = new JsonResult(0,"操作成功！");
        }catch (Exception e){
            jsonResult = new JsonResult(1,e.getMessage());
        }
        return jsonResult;
    }

    @ApiOperation(value = "查询人员推送设置信息",notes = "区域三级id")
    @PostMapping(value = "/findWarnPushcfg")
    public JsonResult<List<MyyWarnpushcfg>> findAreasByParam(@RequestBody FindAllCamWarnByParams findAllCamWarnByParams){
        JsonResult<List<MyyWarnpushcfg>> jsonResult = null;
        try {
            List<MyyWarnpushcfg> objList = myyWarnPushService.findByareabottomid(findAllCamWarnByParams.getThreeid());
            if(objList.size()==0){
                jsonResult = new JsonResult<>(0,JsonResult.NoDataMSG,objList);
            }else {
                jsonResult = new JsonResult<>(0,objList);
            }
        }catch (Exception e){
            jsonResult = new JsonResult<>(1,e.getMessage());
        }
        return jsonResult;
    }

    @ApiOperation(value = "消息推送设置筛选")
    @PostMapping(value = "/findWarnPushcfgs")
    public JsonResult<List<WarnPushcfgSetOut>> findWarnPushcfgs(@RequestBody FindAllCamWarnByParams findAllCamWarnByParams){
        JsonResult<List<WarnPushcfgSetOut>> jsonResult = null;
        try {
            List objList = myyWarnPushService.myyWarnpushcfgs(findAllCamWarnByParams.getOrgid(),findAllCamWarnByParams.getOneid(),
                    findAllCamWarnByParams.getTwoid(),findAllCamWarnByParams.getThreeid(),findAllCamWarnByParams.getCameraid(),
                    findAllCamWarnByParams.getUserid());
            if(objList.size()==0){
                jsonResult = new JsonResult<>(0,JsonResult.NoDataMSG,objList);
            }else {
                List<WarnPushcfgSetOut> warnPushcfgSetOuts = new ArrayList<>();
                WarnPushcfgSetOut warnPushcfgSetOut = new WarnPushcfgSetOut();
                for (Object ab : objList) {

                    warnPushcfgSetOut.setAddr(String.valueOf(Array.get(ab, 0)));
                    warnPushcfgSetOut.setId(Long.parseLong(String.valueOf(Array.get(ab, 0))));
                    warnPushcfgSetOut.setUsername(String.valueOf(Array.get(ab, 0)));
                }
                jsonResult = new JsonResult<>(0,warnPushcfgSetOuts);
            }
        }catch (Exception e){
            jsonResult = new JsonResult<>(1,e.getMessage());
        }
        return jsonResult;
    }
    

}
