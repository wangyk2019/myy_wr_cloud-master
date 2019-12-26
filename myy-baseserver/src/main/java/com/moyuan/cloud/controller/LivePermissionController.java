package com.moyuan.cloud.controller;

import com.moyuan.cloud.VO.FindAllIn;
import com.moyuan.cloud.pojo.JsonResult;
import com.moyuan.cloud.pojo.MyyDistrict;
import com.moyuan.cloud.pojo.MyyUser;
import com.moyuan.cloud.service.MyyDistrictService;
import com.moyuan.cloud.service.MyyUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "直播权限",tags = "直播权限")
@RequestMapping("/livePermiss")
public class LivePermissionController {
    @Autowired
    MyyUserService myyUserService;
    @Autowired
    MyyDistrictService myyDistrictService;

    /**
     * 直播权限筛选查询
     * param orgId
     * param name
     * @return
     */
    @ApiOperation(value = "根据组织orgId,username查询人员信息")
    @PostMapping(value = "/findUsers")
    public JsonResult<List<MyyUser>> findUsers(@RequestBody FindAllIn findAllIn){
        JsonResult<List<MyyUser>> jsonResult = null;
        try {
            List<MyyUser> myyUserList = myyUserService.findUsersByOrgAndUsername(findAllIn.getOrgId(),findAllIn.getName());
            jsonResult = new JsonResult<>(0,myyUserList);
        }catch (Exception e){
            jsonResult = new JsonResult<>(1,e.getMessage());
        }
        return jsonResult;
    }

    /**
     * 直播权限设置
     * param userid
     * param livepower
     * @return
     */
    @ApiOperation(value = "根据userid,livepower更新信息")
    @PostMapping(value = "/liveConfig")
    public JsonResult liveConfig(@RequestBody MyyUser myyUser){
        JsonResult jsonResult = null;
        try {
            String livepow = myyUser.getLivepower();
            myyUser = myyUserService.findById(myyUser.getId());
            if (myyUser == null){
                jsonResult = new JsonResult(1,"未找到人员信息");
            }else {
                //人员开启权限
                if ("1".equals(livepow)){
                    //查询组织下已开启权限的数量
                    long num = myyUserService.livepowerNum(myyUser.getDistrictid());

                    //查询组织设置的权限数量
                    MyyDistrict myyDistrict = myyDistrictService.findById(myyUser.getDistrictid());
                    if (num<myyDistrict.getLivepowernum()){
                        //已开启权限的数量 小于 设置的权限数量
                        myyUserService.updateLivepowerById(myyUser.getId(),livepow);
                        jsonResult = new JsonResult(0);
                    }else {
                        //已开启权限的数量 不小于 设置的权限数量
                        jsonResult = new JsonResult(1,"人员超额！");
                    }
                }else {
                    //人员关闭权限
                    myyUserService.updateLivepowerById(myyUser.getId(),livepow);
                    jsonResult = new JsonResult(0);
                }
            }
        }catch (Exception e){
            jsonResult = new JsonResult(1,e.getMessage()+"");
        }
        return jsonResult;
    }
}
