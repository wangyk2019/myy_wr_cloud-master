package com.moyuan.cloud.controller;

import com.moyuan.cloud.VO.FindAllAreaIn;
import com.moyuan.cloud.VO.WarnPushcfgSetOut;
import com.moyuan.cloud.pojo.*;
import com.moyuan.cloud.service.MyyAreaService;
import com.moyuan.cloud.service.MyyUserService;
import com.moyuan.cloud.service.MyyWarnPushService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@RestController
@Api(value = "人员推送配置接口",tags = "人员推送配置接口")
@RequestMapping("/warnPush")
public class WarnPushcfgController {
    @Autowired
    MyyWarnPushService myyWarnPushService;
    @Autowired
    MyyAreaService myyAreaService;
    @Autowired
    MyyUserService myyUserService;

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

//    @ApiOperation(value = "删除人员推送设置信息")
//    @PostMapping(value = "/deleteByBU")
//    public JsonResult deleteByBU(@RequestParam(value = "areabottomid") long areabottomid,@RequestParam(value = "userid") long userid){
//        JsonResult jsonResult = null;
//        try{
//            myyWarnPushService.updateStateByBU(areabottomid,userid);
//            jsonResult = new JsonResult(0,"操作成功！");
//        }catch (Exception e){
//            jsonResult = new JsonResult(1,e.getMessage());
//        }
//        return jsonResult;
//    }

//    @ApiOperation(value = "查询人员推送设置信息",notes = "区域三级id")
//    @PostMapping(value = "/findWarnPushcfg")
//    public JsonResult<List<MyyWarnpushcfg>> findAreasByParam(@RequestBody FindAllCamWarnByParams findAllCamWarnByParams){
//        JsonResult<List<MyyWarnpushcfg>> jsonResult = null;
//        try {
//            List<MyyWarnpushcfg> objList = myyWarnPushService.findByAreauserid(findAllCamWarnByParams.getThreeid());
//            if(objList.size()==0){
//                jsonResult = new JsonResult<>(0,JsonResult.NoDataMSG,objList);
//            }else {
//                jsonResult = new JsonResult<>(0,objList);
//            }
//        }catch (Exception e){
//            jsonResult = new JsonResult<>(1,e.getMessage());
//        }
//        return jsonResult;
//    }

    @ApiOperation(value = "消息推送设置筛选")
    @PostMapping(value = "/findWarnPushcfgs")
    public JsonResult<List<WarnPushcfgSetOut>> findWarnPushcfgs(@RequestBody FindAllAreaIn findAllAreaIn){
        JsonResult<List<WarnPushcfgSetOut>> jsonResult = null;
        List<WarnPushcfgSetOut> warnPushcfgSetOuts = new ArrayList<>();
        try {
            List<MyyAreabottomUser> areabottomusers = myyUserService.findAllByParams(findAllAreaIn.getOrgId(),findAllAreaIn.getOneId(),
                    findAllAreaIn.getTwoId(),findAllAreaIn.getThreeId(),findAllAreaIn.getUserId());
            for (MyyAreabottomUser abu : areabottomusers){
                MyyAreabottom three = myyAreaService.findById_bank(abu.getAreabottomid());
                MyyAreatwo two = myyAreaService.findById_reach(three.getAreatwoid());
                MyyAreaone one = myyAreaService.findById_one(two.getAreaoneid());
//                MyyUser user = myyUserService.findById(abu.getUserid());

                WarnPushcfgSetOut warnPushcfgSetOut = new WarnPushcfgSetOut();
                warnPushcfgSetOut.setAreauserid(abu.getId());
                warnPushcfgSetOut.setAddr(one.getName()+""+two.getAreaoneid()+""+three.getName());
                warnPushcfgSetOut.setUsername(abu.getUsername());

                List<MyyWarnpushcfg> myyWarnpushcfgs_phone = myyWarnPushService.myyWarnpushcfgs(abu.getId(),findAllAreaIn.getCamId(),"phone");
                List<String> phonestrings = new ArrayList<>();
                for (MyyWarnpushcfg myyWarnpushcfg :myyWarnpushcfgs_phone){
                    phonestrings.add(myyWarnpushcfg.getWarntype());
                }
                warnPushcfgSetOut.setPhonewarn(phonestrings);

                List<MyyWarnpushcfg> myyWarnpushcfgs_msg = myyWarnPushService.myyWarnpushcfgs(abu.getId(),findAllAreaIn.getCamId(),"msg");
                List<String> msgstrings = new ArrayList<>();
                for (MyyWarnpushcfg myyWarnpushcfg :myyWarnpushcfgs_msg){
                    msgstrings.add(myyWarnpushcfg.getWarntype());
                }
                warnPushcfgSetOut.setMsgwarn(msgstrings);

                warnPushcfgSetOuts.add(warnPushcfgSetOut);
            }

            jsonResult = new JsonResult<>(0,warnPushcfgSetOuts);
        }catch (Exception e){
            jsonResult = new JsonResult<>(1,e.getMessage());
        }
        return jsonResult;
    }
    

}
