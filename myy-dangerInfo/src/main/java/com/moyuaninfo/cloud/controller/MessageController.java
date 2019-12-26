package com.moyuaninfo.cloud.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.moyuaninfo.cloud.VO.*;
import com.moyuaninfo.cloud.common.JsonResult;
import com.moyuaninfo.cloud.feignClient.BaseServerClient;
import com.moyuaninfo.cloud.feignClient.CameraClient;
import com.moyuaninfo.cloud.pojo.MyyAreaone;
import com.moyuaninfo.cloud.pojo.MyyCamera;
import com.moyuaninfo.cloud.pojo.MyyDangerInfo;
import com.moyuaninfo.cloud.service.XianqingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(value = "信息中心controller")
@RequestMapping(value = "/message")
//@CrossOrigin
public class MessageController {

    @Autowired
    XianqingService xianqingService;

    @Autowired
    CameraClient cameraClient;
    @Autowired
    BaseServerClient baseServerClient;

    @ApiOperation(
            value="历史消息列表",
            notes="历史消息列表",
            produces = "application/json;charset=UTF-8")
    @PostMapping(value = "/history/list")
    public JsonResult<MessageHistoryListOut> messageHistoryList(@RequestBody OrgIdIn orgIdIn){
        MessageHistoryListOut messageHistoryListOut = new MessageHistoryListOut();

        try{
            List<MyyDangerInfo> myyDangerInfos = xianqingService.findByDistAndWarnname(orgIdIn.getOrgId(),orgIdIn.getEventType());
            //楼
            JsonResult<List<MyyAreaone>> myyareas= baseServerClient.findAreaonesParam(new FindAllAreaByParams(orgIdIn.getOrgId()));
            List<BuildData> jsonArray = new ArrayList<>();
            if (myyareas.getCode() ==0 && myyareas.getData() != null){
                for (MyyAreaone a : myyareas.getData()){
                    BuildData buildData = new BuildData();
                    buildData.setName(a.getName());
                    jsonArray.add(buildData);
                }
            }
            messageHistoryListOut.setBuilddata(jsonArray);

            List<WarnMsg> warnMsgList = new ArrayList<>();
            for (MyyDangerInfo a : myyDangerInfos) {
                JsonResult<MyyCamera> lab = cameraClient.findById(a.getCameraid());
                WarnMsg warnMsg = new WarnMsg();
                warnMsg.setId(a.getId());
                warnMsg.setEventType(a.getName());
                warnMsg.setAddr(lab.getData().getLocation());
                warnMsg.setImg(a.getPicpath());
                warnMsg.setCtime(a.getTime());

                warnMsgList.add(warnMsg);
            }
            messageHistoryListOut.setHistoryData(warnMsgList);

            return new JsonResult<>(0,messageHistoryListOut);
        }catch (Exception e){
            return new JsonResult<>(1,e.getMessage()+"");
        }
    }



}
