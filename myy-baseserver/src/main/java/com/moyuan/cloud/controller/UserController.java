package com.moyuan.cloud.controller;

import com.moyuan.cloud.VO.*;
import com.moyuan.cloud.feignClient.CameraClient;
import com.moyuan.cloud.feignClient.DangerClient;
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
@Api(value = "人员信息",tags = "人员信息")
@RequestMapping("/user")
public class UserController {
    @Autowired
    MyyUserService myyUserService;
    @Autowired
    MyyAreaService myyAreaService;
    @Autowired
    MyyWarnPushService myyWarnPushService;

    @Autowired
    CameraClient cameraClient;
    @Autowired
    DangerClient dangerClient;

    @ApiOperation(value = "添加修改人员信息")
    @PostMapping(value = "/add")
    public JsonResult save(@RequestBody MyyUser myyUser){
        JsonResult jr = null;
        try {
            myyUserService.save(myyUser);
            jr=  new JsonResult(0);
        }catch (Exception e){
            jr=  new JsonResult(1,e.getMessage().toString());
        }
        return jr;
    }

    @ApiOperation(value = "删除人员信息",notes = "",httpMethod = "POST")
    @PostMapping(value = "/detele")
    public JsonResult<MyyUser> delete(@RequestParam(value = "id") long id){
        JsonResult<MyyUser> jsonResult = null;
        try{
            myyUserService.updateStateById(id);
            jsonResult = new JsonResult<>(0);
        }catch (Exception e){
            jsonResult = new JsonResult<>(1,e.getMessage());
        }
        return jsonResult;
    }

    @ApiOperation(value = "查询人员信息",notes = "id",httpMethod = "POST")
    @PostMapping(value = "/findUser")
    public JsonResult<MyyUser> findUser(@RequestParam(value = "id") long id){
        JsonResult<MyyUser> jsonResult = null;
        try {
            MyyUser myyUserList = myyUserService.findById(id);
            jsonResult = new JsonResult<>(0,myyUserList);
        }catch (Exception e){
            jsonResult = new JsonResult<>(1,e.getMessage());
        }
        return jsonResult;
    }

    @ApiOperation(value = "组织orgId查询人员信息")
    @PostMapping(value = "/findUsers_orgid")
    public JsonResult<List<MyyUser>> findUsersByDis(@RequestParam(value = "orgId") long orgId){
        JsonResult<List<MyyUser>> jsonResult = null;
        try {
            List<MyyUser> myyUserList = myyUserService.findByOrgid(orgId);
            jsonResult = new JsonResult<>(0,myyUserList);
        }catch (Exception e){
            jsonResult = new JsonResult<>(1,e.getMessage());
        }
        return jsonResult;
    }

//    @ApiOperation(value = "查询人员信息",notes = "多参数",httpMethod = "POST")
//    @PostMapping(value = "/findUsersByParams")
//    public JsonResult<List<MyyUser>> findUsersByParams(@RequestBody FindAllUserByParams findAllUserByParams){
//        JsonResult<List<MyyUser>> jsonResult = null;
//        try {
//            if (findAllUserByParams.getPhonenumber() == null){
//                findAllUserByParams.setPhonenumber("");
//            }
//            if (findAllUserByParams.getUsername() == null){
//                findAllUserByParams.setUsername("");
//            }
//
////            ArrayList<Long> longs = new ArrayList<>();
////            if (findAllUserByParams.getArea_id() == null){
////                findAllUserByParams.setArea_id(longs);
////            }else{
////                longs = findAllUserByParams.getArea_id();
////            }
////            longs.add(0L);
//            List<MyyUser> myyUserList = myyUserService.findUsersByParams(findAllUserByParams.getId(),findAllUserByParams.getAreaid(),
//                    findAllUserByParams.getDistrict_id(),findAllUserByParams.getPosition_id(),findAllUserByParams.getPhonenumber(),findAllUserByParams.getUsername());
//            if(myyUserList.size()==0){
//                jsonResult = new JsonResult<>(0,JsonResult.NoDataMSG,myyUserList);
//            }else {
//                jsonResult = new JsonResult<>(0,myyUserList);
//            }
//        }catch (Exception e){
//            jsonResult = new JsonResult<>(1,e.getMessage());
//        }
//        return jsonResult;
//    }

    ///////////////////////////////////////////////////////////////////////////////////
    @ApiOperation(
            value="用户设置信息",
            notes="获取用户基本信息",
            produces = "application/json;charset=UTF-8")
    @PostMapping(value = "/info")
    public JsonResult<SettingsPageOut> userInfo(@RequestBody UserInfoIn userInfoIn){
        JsonResult<SettingsPageOut> jr =null;
        MyyUser userJsonResult = myyUserService.findByPhone(userInfoIn.getCellphone());

        if (userJsonResult != null){
            List<MyyAreatwo>  listF = myyAreaService.myyAreatwosOrgidAndOnename(userInfoIn.getOrgId(),"");//实验楼

            List<MyyAreabottom> listM = myyAreaService.myyAreabottomsOrgidAndManager(userInfoIn.getOrgId());//管理员

            JsonResult<List<MyyCamera>> listC = cameraClient.findAllByDistrict(userInfoIn.getOrgId());//摄像头

//            JsonResult<List> listP = dangerClient.findPhoneByColleage(userInfoIn.getOrgId());//预警电话
//
//            JsonResult<List> listI = dangerClient.findMesByColleage(userInfoIn.getOrgId());//预警信息

            SettingsPageOut settingsPageOut = new SettingsPageOut();
            settingsPageOut.setAdminNum((long)listM.size());
            settingsPageOut.setCamNum((long)listC.getData().size());
            settingsPageOut.setFloorNum((long)listF.size());
            settingsPageOut.setWarnMsgNum(1L);
            settingsPageOut.setWarnPhNum(2L);
            settingsPageOut.setName(userJsonResult.getUsername() );
            jr = new JsonResult<>(0,settingsPageOut);
        }else{
            jr = new JsonResult<>(1,"手机号不存在");
        }

        return jr;
    }


    /////////////////////////////区域人员//////////////////////////////////////////////
    @ApiOperation(value = "区域三级添加人员")
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public JsonResult addUserToLaboratory(@RequestBody UserbaseAndAreaVO userbaseAndAreaVO){
        JsonResult jsrt = null;
        try {
            if(userbaseAndAreaVO.getCellphone() != null && userbaseAndAreaVO.getOrgId() !=0
                    && (userbaseAndAreaVO.getAreabottomid() != 0 || userbaseAndAreaVO.getAreabottomName() != null)){
                long areaid = 0;
                if (userbaseAndAreaVO.getAreabottomid() != 0){
                    areaid = userbaseAndAreaVO.getAreabottomid();
                }else{
                    List<MyyAreabottom> areabottoms = myyAreaService.myyAreabottomsOrgidAndBottomname(userbaseAndAreaVO.getOrgId(),userbaseAndAreaVO.getAreabottomName());
                    if (areabottoms.size()==0){
                        return new JsonResult(1,"区域3级输入有误！");
                    }
                    areaid = areabottoms.get(0).getId();
                }
                MyyAreabottom areabottom = myyAreaService.findById_bank(areaid);
                if (areabottom != null){
                    MyyUser userJsonResult = myyUserService.findByPhone(userbaseAndAreaVO.getCellphone());
                    if (userJsonResult == null){//没人
                        if (userbaseAndAreaVO.isAdmin()) {//管理员
                            List<MyyAreabottomUser> myyAreabottomUsers = myyUserService.findAllByAreabottomidAndManager(areaid);
                            if (myyAreabottomUsers.size()==0) {
                                MyyUser user = new MyyUser();
                                user.setDistrictid(userbaseAndAreaVO.getOrgId());
                                user.setPhonenumber(userbaseAndAreaVO.getCellphone());
                                user.setUsername(userbaseAndAreaVO.getUsername());
                                user.setPassword("123456");
                                user.setRole("user");
                                user.setValid("0");
                                user = myyUserService.save(user);

                                MyyAreabottomUser myyAreabottomUser = new MyyAreabottomUser();
                                myyAreabottomUser.setManager("1");
                                myyAreabottomUser.setUserid(user.getId());
                                myyAreabottomUser.setAreabottomid(areaid);
                                myyAreabottomUser.setUsername(userbaseAndAreaVO.getUsername());
                                myyUserService.save_areauser(myyAreabottomUser);

                                JsonResult<List<MyyCamera>> myyCameras = cameraClient.findAllByAreabottomid(areaid);
                                if (myyCameras.getCode() == 0 && myyCameras.getData() != null){
                                    for (MyyCamera camera : myyCameras.getData()){
                                        MyyWarnpushcfg myyWarnpushcfg = new MyyWarnpushcfg();
                                        myyWarnpushcfg.setAreauserid(myyWarnpushcfg.getId());
                                        myyWarnpushcfg.setCameraid(camera.getId());
                                        myyWarnPushService.save(myyWarnpushcfg);
                                    }
                                }

                                jsrt=  new JsonResult(0,"ok");
                            }else {
                                jsrt=  new JsonResult(1,"该区域下已有管理员！");
                            }
                        }else {
                            MyyUser user = new MyyUser();
                            user.setDistrictid(userbaseAndAreaVO.getOrgId());
                            user.setPhonenumber(userbaseAndAreaVO.getCellphone());
                            user.setUsername(userbaseAndAreaVO.getUsername());
                            user.setPassword("123456");
                            user.setRole("user");
                            user.setValid("0");
                            user = myyUserService.save(user);

                            MyyAreabottomUser myyAreabottomUser = new MyyAreabottomUser();
                            myyAreabottomUser.setManager("0");
                            myyAreabottomUser.setUserid(user.getId());
                            myyAreabottomUser.setAreabottomid(areaid);
                            myyAreabottomUser.setUsername(userbaseAndAreaVO.getUsername());
                            myyUserService.save_areauser(myyAreabottomUser);

                            JsonResult<List<MyyCamera>> myyCameras = cameraClient.findAllByAreabottomid(areaid);
                            if (myyCameras.getCode() == 0 && myyCameras.getData() != null){
                                for (MyyCamera camera : myyCameras.getData()){
                                    MyyWarnpushcfg myyWarnpushcfg = new MyyWarnpushcfg();
                                    myyWarnpushcfg.setAreauserid(myyWarnpushcfg.getId());
                                    myyWarnpushcfg.setCameraid(camera.getId());
                                    myyWarnPushService.save(myyWarnpushcfg);
                                }
                            }
                            jsrt=  new JsonResult(0,"ok");
                        }

                    }else if (userJsonResult != null){//有人
                        MyyAreabottomUser userarea = myyUserService.findAllByAreabottomidAndUserid(areaid,userJsonResult.getId());
                        if (userarea != null){
                            jsrt=  new JsonResult(1,"该人员已存在！");
                        }else {
                            if (userbaseAndAreaVO.isAdmin()) {
                                List<MyyAreabottomUser> myyAreabottomUsers = myyUserService.findAllByAreabottomidAndManager(areaid);
                                if (myyAreabottomUsers.size() == 0) {
                                    MyyAreabottomUser myyAreabottomUser = new MyyAreabottomUser();
                                    myyAreabottomUser.setManager("1");
                                    myyAreabottomUser.setUserid(userJsonResult.getId());
                                    myyAreabottomUser.setAreabottomid(areaid);
                                    myyAreabottomUser.setUsername(userbaseAndAreaVO.getUsername());
                                    myyUserService.save_areauser(myyAreabottomUser);

                                    jsrt=  new JsonResult(0,"ok");
                                }else {
                                    jsrt=  new JsonResult(0,"该区域下已有管理员！");
                                }
                            }else {
                                MyyAreabottomUser myyAreabottomUser = new MyyAreabottomUser();
                                myyAreabottomUser.setManager("0");
                                myyAreabottomUser.setUserid(userJsonResult.getId());
                                myyAreabottomUser.setAreabottomid(areaid);
                                myyAreabottomUser.setUsername(userbaseAndAreaVO.getUsername());
                                myyUserService.save_areauser(myyAreabottomUser);

                                jsrt=  new JsonResult(0,"ok");
                            }
                        }
                    }else{
                        jsrt=  new JsonResult(1,"人员查询失败！");
                    }
                }
            }else{
                jsrt=  new JsonResult(1,"输入数据不全！");
            }
        }catch (Exception e){
            jsrt=  new JsonResult(1,e.getMessage().toString());
        }
        return jsrt;
    }
    @ApiOperation(value = "修改区域人员的信息")
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public JsonResult userupdate(@RequestBody UserUpdateIn userUpdateIn){
        JsonResult jsrt = null;
        try{
            MyyUser user = myyUserService.findById(userUpdateIn.getUserId());
            if (user !=null){
                user.setPhonenumber(userUpdateIn.getCellphone());
                myyUserService.save(user);

                MyyAreabottomUser userarea = myyUserService.findAllByAreabottomidAndUserid(userUpdateIn.getRoomId(),userUpdateIn.getUserId());
                if (userUpdateIn.isAdmin()){
                    userarea.setManager("1");
                }else{
                    userarea.setManager("0");
                }
                userarea.setUsername(userUpdateIn.getUsername());
                myyUserService.save_areauser(userarea);
                jsrt=  new JsonResult(0,"操作成功！");
            }else {
                jsrt=  new JsonResult(1,"人员没有找到！");
            }
        }catch (Exception e){
            jsrt=  new JsonResult(1,e.getMessage()+"");
        }
        return jsrt;
    }
    @ApiOperation(value = "区域人员删除", notes = "关联删除推送信息、人员信息")
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public JsonResult delUserToLaboratory(@RequestBody AreabottomidAndUserid areaidAndUserid){
        JsonResult jsrt = null;
        try{
            MyyAreabottomUser myyAreabottomUser = myyUserService.findAllByAreabottomidAndUserid(areaidAndUserid.getAreabottomId(),areaidAndUserid.getUserId());

            myyUserService.updateStateById(areaidAndUserid.getUserId());

            myyWarnPushService.deleteAllByAreauserid(myyAreabottomUser.getId());

            myyUserService.deleteAllByAreabottomidAndUserid(areaidAndUserid.getAreabottomId(),areaidAndUserid.getUserId());

            jsrt = new JsonResult(0);
        }catch (Exception e){
            jsrt = new JsonResult(1,e.getMessage()+"");
        }
        return jsrt;
    }

    @ApiOperation(value = "管理人员设置筛选")
    @RequestMapping(value = "/findAreasUsers", method = RequestMethod.POST)
    public JsonResult<List<ManagerSetOut>> findAreasUsers(@RequestBody FindAllAreaIn findAllAreaIn){
        JsonResult<List<ManagerSetOut>> jsrt = null;
        try{
            List<ManagerSetOut> managerSetOuts = new ArrayList<>();

            List areabottoms = myyAreaService.myyAreabottoms(findAllAreaIn.getOrgId(),findAllAreaIn.getOneId(),findAllAreaIn.getTwoId(),findAllAreaIn.getThreeId());
            for (Object ab : areabottoms){
                ManagerSetOut managerSetOut = new ManagerSetOut();
                managerSetOut.setAreabottomId(Long.parseLong(String.valueOf(Array.get(ab, 1))));
                managerSetOut.setAddr(String.valueOf(Array.get(ab, 0)));

                List<UserMsg> userMsgs = new ArrayList<>();
                List<MyyAreabottomUser> abulist = myyUserService.findUserBybomttom(Long.parseLong(String.valueOf(Array.get(ab, 1))));
                for (MyyAreabottomUser abu : abulist){
                    MyyUser myyUser = myyUserService.findById(abu.getUserid());

                    UserMsg userMsg = new UserMsg();
                    userMsg.setUserid(abu.getUserid());
                    userMsg.setUsername(abu.getUsername());
                    userMsg.setPhonenumber(myyUser.getPhonenumber());
                    userMsg.setAdmin(abu.getManager());
                    userMsgs.add(userMsg);
                }
                managerSetOut.setUserMsg(userMsgs);

                managerSetOuts.add(managerSetOut);
            }
            jsrt = new JsonResult(0,managerSetOuts);
        }catch (Exception e){
            jsrt = new JsonResult(1,e.getMessage()+"");
        }
        return jsrt;
    }

    @ApiOperation(value = "根据二级id，人员姓名查询人员")
    @RequestMapping(value = "/findUsersByTwoidAndUsername", method = RequestMethod.POST)
    public JsonResult<List<NameAndIdOut>> findUsersByTwoidAndUsername(@RequestBody FindAllIn findAllIn){
        JsonResult<List<NameAndIdOut>> jsrt = null;

        List<NameAndIdOut> nameAndIdOuts = new ArrayList<>();
        try{
            List<MyyUser> users = myyUserService.findUsersByTwoidAndUsername(findAllIn.getAreaId(),findAllIn.getName());
            for (MyyUser u : users){
                NameAndIdOut nameAndIdOut = new NameAndIdOut();
                nameAndIdOut.setId(u.getId());
                nameAndIdOut.setName(u.getUsername());

                nameAndIdOuts.add(nameAndIdOut);
            }
            jsrt = new JsonResult(0,nameAndIdOuts);
        }catch (Exception e){
            jsrt = new JsonResult(1,e.getMessage()+"");
        }
        return jsrt;
    }

}
