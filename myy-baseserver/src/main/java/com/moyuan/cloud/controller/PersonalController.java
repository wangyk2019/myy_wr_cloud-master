package com.moyuan.cloud.controller;

import com.alibaba.fastjson.JSONObject;
import com.moyuan.cloud.VO.PushParamIn;
import com.moyuan.cloud.VO.ResetPhIn;
import com.moyuan.cloud.VO.ResetPwdIn;
import com.moyuan.cloud.VO.ResetPwdPhIn;
import com.moyuan.cloud.VO.UserInfoOut;
import com.moyuan.cloud.feignClient.PushClient;
import com.moyuan.cloud.pojo.JsonResult;
import com.moyuan.cloud.pojo.MyyDistrict;
import com.moyuan.cloud.pojo.MyyUser;
import com.moyuan.cloud.service.MyyDistrictService;
import com.moyuan.cloud.service.MyyUserService;
import com.moyuan.cloud.service.RedisPool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/personal")
@Api(value = "个人中心",tags = "个人中心操作")
//@CrossOrigin
public class PersonalController {
    @Autowired
    MyyUserService userSerivces;
    @Autowired
    MyyDistrictService myyDistrictService;

    @Autowired
    PushClient pushClient;

    @ApiOperation(value="用户信息",produces = "application/json;charset=UTF-8")
    @PostMapping(value = "/info")
    public JsonResult<UserInfoOut> info(@RequestHeader("userid") long userid){
        MyyUser userJsonResult = userSerivces.findById(userid);
        UserInfoOut userInfoOut = new UserInfoOut();

        userInfoOut.setUsername(userJsonResult.getUsername());
        userInfoOut.setCellphone(userJsonResult.getPhonenumber());
        userInfoOut.setPassword(userJsonResult.getPassword());
        MyyDistrict jsC = myyDistrictService.findById(userJsonResult.getDistrictid());
        if (jsC != null){
            userInfoOut.setOrgName(jsC.getName());
        }else {
            userInfoOut.setOrgName("");
        }
        userInfoOut.setDepartment("");
        userInfoOut.setCertificates("");
        userInfoOut.setCertificatesNO(userJsonResult.getId_number());
        return new JsonResult(0,userInfoOut);
    }

    @ApiOperation(value="重置手机号",produces = "application/json;charset=UTF-8")
    @PostMapping(value = "/reset/ph")
    public JsonResult resetPh(@RequestBody ResetPhIn resetPhIn, @RequestHeader("userid") long userid){
        JsonResult<MyyUser> jsrt = null;
        resetPhIn.setUserid(userid);
        Jedis jedis = RedisPool.getJedis();
        jedis.select(0);
        try {
            MyyUser userJsonResult = userSerivces.findById(userid);
            if (resetPhIn.getNewph().equals(userJsonResult.getPhonenumber())) {
                if(jedis.get(resetPhIn.getNewph()+"smscode").equals(resetPhIn.getCode())) {
                    userJsonResult.setPhonenumber(resetPhIn.getNewph());
                    userSerivces.save(userJsonResult);
                    jedis.del(userJsonResult.getPhonenumber());
                    jedis.del(resetPhIn.getNewph()+"smscode");
                    jsrt = new JsonResult<>(0,JsonResult.SUCCESSMSG);
                }else {
                    jsrt = new JsonResult<>(1,"验证码错误！");
                }
            }else {
                jsrt = new JsonResult<>(1,"原手机号码与新手机号一致！");
            }
        } catch (Exception e) {
            jsrt = new JsonResult<>(1,e.getClass().getName() + ":" + e.getMessage());
        }
        RedisPool.returnResource(jedis);
        return jsrt;
    }

//    @ApiOperation(value="重置手机号---获取验证码",produces = "application/json;charset=UTF-8")
//    @PostMapping(value = "/reset/ph/code")
//    public JsonResult resetPhCode(@RequestBody ResetPwdPhIn resetPwdIn){
//        JsonResult userJsonResult = null;
//        if ("".equals(resetPwdIn.getNewph())){
//            userJsonResult = new JsonResult(1,"请输入新的手机号码！");
//        }else {
//            try {
//                MyyUser user = userSerivces.findByPhone(resetPwdIn.getNewph());
//                if(user != null) {
//                    return new JsonResult<>(1,"用户已在系统中");
//                }
//                PushParamIn pushParamIn = new PushParamIn();
//                ArrayList<String> al = new ArrayList<>();
//                al.add(resetPwdIn.getNewph());
//                pushParamIn.setPhones(al);
//                JsonResult jsonResult = pushClient.pushMsgCode(pushParamIn);
//                userJsonResult = jsonResult;
//            } catch (Exception e) {
//                userJsonResult = new JsonResult<>(1,"error",e.getClass().getName() + ":" + e.getMessage());
//            }
//        }
//        return userJsonResult;
//    }


    @ApiOperation(value="重置密码（根据验证码）",produces = "application/json;charset=UTF-8")
    @PostMapping(value = "/reset/pwd")
    public JsonResult resetPwd(@RequestBody ResetPwdIn resetPwdIn, @RequestHeader("userid") long userid){
        JsonResult<MyyUser> jsrt = null;
        Jedis jedis = RedisPool.getJedis();
        jedis.select(0);
        try {
            MyyUser userJsonResult = userSerivces.findById(userid);
            if(jedis.get(userJsonResult.getPhonenumber()+"smscode").equals(resetPwdIn.getSmscode())) {
                userJsonResult.setPassword(resetPwdIn.getNewpw());
                userJsonResult = userSerivces.save(userJsonResult);
                if(jedis.exists(userJsonResult.getPhonenumber())){
                    jedis.del(userJsonResult.getPhonenumber()+"smscode");
                    jedis.set(userJsonResult.getPhonenumber(), JSONObject.toJSONString(userJsonResult));
                }
                jsrt = new JsonResult<>(0,"操作成功！", userJsonResult);
            }else {
                jsrt = new JsonResult<>(1,"验证码错误！");
            }
        } catch (Exception e) {
            jsrt = new JsonResult<>(1,"验证码失效！");
        }
        RedisPool.returnResource(jedis);
        return jsrt;
    }

//    @ApiOperation(value="重置手机号和密码",produces = "application/json;charset=UTF-8")
//    @PostMapping(value = "/reset/pwd_ph")
//    public JsonResult resetPwd_ph(@RequestBody ResetPwdPhIn resetPwdIn, @RequestHeader("userid") long userid){
//        JsonResult<MyyUser> userJsonResult1 = null;
//        resetPwdIn.setUserid(userid);
//        MyyUser user = userSerivces.findById(userid);
//
//        try {
//            if (cacheService.get(resetPwdIn.getNewph()+"smscode") != null){
//                if(cacheService.get(resetPwdIn.getNewph()+"smscode").equals(resetPwdIn.getCode())) {
//                    user.setPhonenumber(resetPwdIn.getNewph());
//                    user.setPassword(resetPwdIn.getNewpw());
//                    userSerivces.save(user);
//                    cacheService.del(user.getPhonenumber());
//                    cacheService.del(resetPwdIn.getNewph()+"smscode");
//                    userJsonResult1 = new JsonResult<>(0,"操作成功！");
//                }else {
//                    userJsonResult1 = new JsonResult<>(1,"验证码错误！");
//                }
//            }else{
//                userJsonResult1 = new JsonResult<>(1,"验证码失效，请重新发送！");
//            }
//
//        } catch (Exception e) {
//            userJsonResult1 = new JsonResult<>(1,e.getClass().getName() + ":" + e.getMessage());
//        }
//
//        return userJsonResult1;
//    }
//
//    @ApiOperation(value="重置手机号和密码--获取验证码",produces = "application/json;charset=UTF-8")
//    @PostMapping(value = "/reset/pwd_ph/code")
//    public JsonResult resetPwd_phCode(@RequestBody ResetPwdPhIn resetPwdIn){
//        JsonResult userJsonResult = null;
//        if ("".equals(resetPwdIn.getNewph())){
//            userJsonResult = new JsonResult(1,"请输入新的手机号码！");
//        }else {
//            try {
//                MyyUser user = userSerivces.findByPhone(resetPwdIn.getNewph());
//                if(user != null) {
//                    return new JsonResult<>(1,"用户已在系统中");
//                }
//                PushParamIn pushParamIn = new PushParamIn();
//                ArrayList<String> al = new ArrayList<>();
//                al.add(resetPwdIn.getNewph());
//                pushParamIn.setPhones(al);
//                JsonResult jsonResult = pushClient.pushMsgCode(pushParamIn);
//                userJsonResult = jsonResult;
//            } catch (Exception e) {
//                userJsonResult = new JsonResult<>(2,"error",e.getClass().getName() + ":" + e.getMessage());
//            }
//        }
//
//        return userJsonResult;
//    }

    @ApiOperation(value="重置密码(根据旧密码)",produces = "application/json;charset=UTF-8")
    @PostMapping(value = "/set/pwd")
    public JsonResult setPwd(@RequestBody ResetPwdIn resetPwdIn, @RequestHeader("userid") long userid){
        JsonResult<MyyUser> jsrt = null;
        MyyUser user = null;
        Jedis jedis = RedisPool.getJedis();
        jedis.select(0);
        try {
            user = userSerivces.findById(userid);
            if (resetPwdIn.getOldpw().equals(user.getPassword())){
                user.setPassword(resetPwdIn.getNewpw());
                userSerivces.save(user);
                jedis.set(user.getPhonenumber(), JSONObject.toJSONString(user));
                jsrt = new JsonResult<>(0,"操作成功！");
            }else {
                jsrt = new JsonResult<>(1,"原密码输入错误！");
            }
        } catch (Exception e) {
            jsrt = new JsonResult<>(1,e.getClass().getName() + ":" + e.getMessage());
        }
        RedisPool.returnResource(jedis);
        return jsrt;
    }

}
