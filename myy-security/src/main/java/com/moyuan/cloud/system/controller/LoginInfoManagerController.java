package com.moyuan.cloud.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.moyuan.cloud.security.constants.SecurityConstants;
import com.moyuan.cloud.security.entity.JsonResult;
import com.moyuan.cloud.security.utils.JwtTokenUtils;
import com.moyuan.cloud.system.VO.*;
import com.moyuan.cloud.system.entity.MyyDistrict;
import com.moyuan.cloud.system.entity.MyyLoginLog;
import com.moyuan.cloud.system.entity.MyyUser;
import com.moyuan.cloud.system.entity.MyyUserClientid;
import com.moyuan.cloud.system.feignClient.PushClient;
import com.moyuan.cloud.system.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@RestController
@Api(value = "人员登陆管理", tags = "人员登陆管理")
//@CrossOrigin
//@RequestMapping("/login")
public class LoginInfoManagerController {

    @Autowired
    MyyUserService myyUserSerivce;
    @Autowired
    User_clientService user_clientService;
    @Autowired
    MyyDistrictService myyDistrictService;
    @Autowired
    MyyLoginLogService myyLoginLogService;

    @Autowired
    PushClient pushClient;

    @ApiOperation(value = "登录", notes = "使用手机号，密码登录", produces = "application/json;charset=UTF-8",
            httpMethod = "POST")
    @PostMapping(value = "/loginP")
    @Transactional
    public JsonResult<LoginPOut> loginP(@RequestBody LoginPIn loginPIn, @RequestHeader(value = "cid", required = false) String cid) {
        MyyUser jsrt = myyUserSerivce.findOneByCellphoneAndPassword(loginPIn.getCellphone(), loginPIn.getPassword());

        JsonResult<LoginPOut> loginPOutJsonResult = null;

        Jedis jedis = RedisPool.getJedis();
        jedis.select(0);
        LoginPOut loginPOut = new LoginPOut();
        if (jsrt != null) {
            if ("1".equals(jsrt.getState())) {
                if (jsrt.getValid().equals("1")) {
                    loginPOut.setUsername(jsrt.getUsername());
                    loginPOut.setCellphone(jsrt.getPhonenumber());
                    if (jsrt.getRole().contains("admin")) {
                        loginPOut.setColleagerole("管理员");
                    } else if (jsrt.getRole().contains("user")) {
                        loginPOut.setColleagerole("普通用户");
                    } else if (jsrt.getRole().contains("people")) {
                        loginPOut.setColleagerole("群众");
                    }
                    loginPOut.setColleageId(jsrt.getDistrict_id());

                    MyyDistrict myyDistrict = myyDistrictService.findById(jsrt.getDistrict_id());
                    loginPOut.setOrgName(myyDistrict.getName());
                    loginPOut.setUserId(jsrt.getId());
                    loginPOut.setAdmin(jsrt.getRole());

                    if (cid != null && !"".equals(cid)) {
                        try {
                            MyyUserClientid t = user_clientService.findByUserid(jsrt.getId());
                            if (t == null) {
                                t = new MyyUserClientid();
                            }
                            t.setUserid(jsrt.getId());
                            t.setAreaid(jsrt.getArea_id());
                            t.setDistrictid(jsrt.getDistrict_id());
                            t.setClientid(cid);
                            user_clientService.save(t);
                            // 创建 Token
                            String token = JwtTokenUtils.createToken(jsrt.getPhonenumber(), jsrt.getRolesString(), true);
                            // Http Response Header 中返回 Token
                            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                            HttpServletResponse response = servletRequestAttributes.getResponse();
                            response.setHeader(SecurityConstants.TOKEN_HEADER, token);
                            loginPOut.setToken(token);

                            loginPOutJsonResult = new JsonResult<>(0, loginPOut);

                            jedis.setex(token, 60 * 60 * 24 * 30, token);
                        } catch (Exception e) {
                            loginPOutJsonResult = new JsonResult<>(1, "cid获取失败！");
                        }

                    } else {
                        // 创建 Token
                        String token = JwtTokenUtils.createToken(jsrt.getPhonenumber(), jsrt.getRolesString(), true);
                        // Http Response Header 中返回 Token
                        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                        HttpServletResponse response = servletRequestAttributes.getResponse();
                        response.setHeader(SecurityConstants.TOKEN_HEADER, token);
                        loginPOut.setToken(token);

                        jedis.setex(token, 60 * 60 * 24 * 30, token);
                    }
                    myyLoginLogService.saveLoginLog(new MyyLoginLog(jsrt.getId(), 1, loginPIn.getPlatform(), loginPIn.getIp()));
                    loginPOutJsonResult = new JsonResult<LoginPOut>(0, "操作成功！", loginPOut);
                } else {
                    loginPOutJsonResult = new JsonResult<>(1, "账号未激活！");
                }
            } else {
                loginPOutJsonResult = new JsonResult<>(1, "人员已移除！");
            }

        } else {
            loginPOutJsonResult = new JsonResult<>(1, "手机号或密码错误！");
        }


        RedisPool.returnResource(jedis);
        return loginPOutJsonResult;
    }

    @ApiOperation(value = "验证码登录", notes = "通过手机号和验证码登录", produces = "application/json;charset=UTF-8",
            httpMethod = "POST")
    @RequestMapping(value = "/loginC")
    @Transactional
    public JsonResult<LoginPOut> loginC(@RequestBody CellphoneAndCodeVO infovo, @RequestHeader(value = "cid", required = false) String cid) {
        JsonResult<LoginPOut> loginPOutJsonResult = null;
        MyyUser user = null;
        Jedis jedis = RedisPool.getJedis();
        jedis.select(0);
        try {
            if (jedis.get(infovo.getCellphone() + "smscode").equals(infovo.getCode())) {
                user = myyUserSerivce.findByPhone(infovo.getCellphone());
                jedis.set(user.getPhonenumber(), JSONObject.toJSONString(user));

                LoginPOut loginPOut = new LoginPOut();
                if (user != null) {
                    if (user.getValid().equals("1")) {
                        loginPOut.setUsername(user.getUsername());
                        loginPOut.setCellphone(user.getPhonenumber());
                        if (user.getRole().contains("admin")) {
                            loginPOut.setColleagerole("管理员");
                        } else if (user.getRole().contains("user")) {
                            loginPOut.setColleagerole("普通用户");
                        } else if (user.getRole().contains("people")) {
                            loginPOut.setColleagerole("群众");
                        }
                        loginPOut.setColleageId(user.getDistrict_id());
                        loginPOut.setUserId(user.getId());
                        loginPOut.setAdmin(user.getRole());

                        if (cid != null && !"null".equals(cid)) {
                            try {
                                MyyUserClientid t = user_clientService.findByUserid(user.getId());
                                if (t == null) {
                                    t = new MyyUserClientid();
                                }
                                t.setUserid(user.getId());
                                t.setAreaid(user.getArea_id());
                                t.setDistrictid(user.getDistrict_id());
                                t.setClientid(cid);
                                user_clientService.save(t);
                                // 创建 Token
                                String token = JwtTokenUtils.createToken(user.getPhonenumber(), user.getRolesString(), true);
                                // Http Response Header 中返回 Token
                                ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                                HttpServletResponse response = servletRequestAttributes.getResponse();
                                response.setHeader(SecurityConstants.TOKEN_HEADER, token);
                                loginPOut.setToken(token);
                                loginPOutJsonResult = new JsonResult<LoginPOut>(0, "操作成功！", loginPOut);

                                myyLoginLogService.saveLoginLog(new MyyLoginLog(user.getId(), 2, infovo.getPlatform(), infovo.getIp()));
                                jedis.setex(token, 60 * 60 * 24 * 30, token);
                            } catch (Exception e) {
                                loginPOutJsonResult = new JsonResult<>(1, "cid获取失败！");
                            }
                        } else {
                            // 创建 Token
                            String token = JwtTokenUtils.createToken(user.getPhonenumber(), user.getRolesString(), true);
                            // Http Response Header 中返回 Token
                            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                            HttpServletResponse response = servletRequestAttributes.getResponse();
                            response.setHeader(SecurityConstants.TOKEN_HEADER, token);

                            jedis.setex(token, 60 * 60 * 24 * 30, token);

                            loginPOutJsonResult = new JsonResult<LoginPOut>(0, "操作成功！", loginPOut);
                        }
                    } else {
                        loginPOutJsonResult = new JsonResult<>(1, "账号未激活！");
                    }

                } else {
                    loginPOutJsonResult = new JsonResult<>(1, "手机号错误！");
                }
            } else {
                loginPOutJsonResult = new JsonResult<>(1, "验证码错误！");
            }
        } catch (Exception e) {
            loginPOutJsonResult = new JsonResult<>(1, e.getClass().getName() + ":" + e.getMessage());
        }
        RedisPool.returnResource(jedis);
        return loginPOutJsonResult;
    }

//    @ApiOperation(value = "验证码登录--获取验证码", notes = "获取验证码",
//            produces = "application/json;charset=UTF-8", httpMethod = "POST")
//    @RequestMapping(value = "/loginC/get")
//    public JsonResult loginCode(@RequestBody CellphoneVO phoneVO) {
//        JsonResult<String> jsrt = null;
//        try {
//            MyyUser user = myyUserSerivce.findByPhone(phoneVO.getCellphone());
//            if (user == null) {
//                return new JsonResult<String>(1, "用户不在系统中", "");
//            }
//            PushParamIn pushParamIn = new PushParamIn();
//            ArrayList<String> al = new ArrayList<>();
//            al.add(phoneVO.getCellphone());
//            pushParamIn.setPhones(al);
//            JsonResult jsonResult = pushClient.pushMsgCode(pushParamIn);
//            jsrt = jsonResult;
//        } catch (Exception e) {
//            jsrt = new JsonResult<>(2, "error", e.getClass().getName() + ":" + e.getMessage());
//        }
//
//        return jsrt;
//    }


    @ApiOperation(value = "注册", notes = "输入基本信息，进行注册",
            produces = "application/json;charset=UTF-8", httpMethod = "POST")
    @RequestMapping(value = "/register")
    @Transactional
    public JsonResult<MyyUser> userRegister(@RequestBody UserRegisterIn userRegisterIn) {
        JsonResult<MyyUser> jsrt = null;
        Jedis jedis = RedisPool.getJedis();
        jedis.select(0);
        try {
            if (jedis.get(userRegisterIn.getCellphone() + "smscode").equals(userRegisterIn.getSmscode())) {
                MyyUser user = myyUserSerivce.findOneByCellphoneAndPassword(userRegisterIn.getCellphone(), userRegisterIn.getPassword());
                if (user == null) {
                    user = new MyyUser();
                    user.setDistrict_id(0);
                    user.setPhonenumber(userRegisterIn.getCellphone());
                    user.setPassword(userRegisterIn.getPassword());
                    user.setUsername(userRegisterIn.getUsername());
                    user.setRole("people");
                    user.setState("1");
                    user.setValid("1");
                    user.setId_number(userRegisterIn.getSmscode());
//                    user.setPosition_id(userRegisterIn.getType());
                    myyUserSerivce.save(user);
                    jsrt = new JsonResult<>(0, "注册成功！");
                } else {
                    if (user.getState().equals("1")) {
                        if (user.getValid().equals("1")) {
                            jsrt = new JsonResult<>(0, "已激活！");
                        } else {
                            user.setValid("1");
                            myyUserSerivce.save(user);
                            jsrt = new JsonResult<>(0, "激活成功！");
                        }

                    } else {
                        jsrt = new JsonResult<>(1, "人员已移除！");
                    }

                }
                jedis.del(userRegisterIn.getCellphone() + "smscode");
            } else {
                jsrt = new JsonResult<>(1, "验证码错误！");
            }


//            setMyyUserColleageRole(user, user);

        } catch (Exception e) {
            jsrt = new JsonResult<>(1, "用户不存在！");
        }

        RedisPool.returnResource(jedis);
        return jsrt;
    }


    @ApiOperation(value = "获取验证码", notes = "获取验证码",
            produces = "application/json;charset=UTF-8", httpMethod = "POST")
    @PostMapping(value = "/loginC/get")
    public JsonResult loginCode(@RequestBody CellphoneVO phoneVO) {
        JsonResult<String> jsrt = null;
        try {
            MyyUser user = myyUserSerivce.findByPhone(phoneVO.getCellphone());
            if (user == null) {
                return new JsonResult<String>(1, "用户不在系统中", "");
            }
            PushParamIn pushParamIn = new PushParamIn();
            pushParamIn.setPhone(phoneVO.getCellphone());
            JsonResult jsonResult = pushClient.pushMsgCode(pushParamIn);
            jsrt = jsonResult;
        } catch (Exception e) {
            jsrt = new JsonResult<>(2, "error", e.getClass().getName() + ":" + e.getMessage());
        }

        return jsrt;
    }

    @ApiOperation(value = "忘记密码", notes = "重新设置密码",
            produces = "application/json;charset=UTF-8", httpMethod = "POST")
    @RequestMapping(value = "/forgot")
    public JsonResult<MyyUser> forgotP(@RequestBody ForgotPIn forgotPIn) {
        JsonResult<MyyUser> jsrt = null;
        try {
            Jedis jedis = RedisPool.getJedis();
            jedis.select(0);
            if (jedis.get(forgotPIn.getCellphone() + "smscode").equals(forgotPIn.getSmscode())) {
//                MyyUser user = JSONObject.parseObject(jedis.get(forgotPIn.getCellphone()), MyyUser.class);
                MyyUser user = myyUserSerivce.findByPhone(forgotPIn.getCellphone());

                user.setPhonenumber(forgotPIn.getCellphone());
                user.setPassword(forgotPIn.getPassword());
                user = myyUserSerivce.save(user);
                if (jedis.exists(forgotPIn.getCellphone())) {
                    jedis.del(forgotPIn.getCellphone() + "smscode");
                    jedis.set(user.getPhonenumber(), JSONObject.toJSONString(user));
                }
                jsrt = new JsonResult<>(0, "操作成功！");
            } else {
                jsrt = new JsonResult<>(1, "验证码不对！");
            }
        } catch (Exception e) {
            jsrt = new JsonResult<>(1, "验证码失效！");
        }

        return jsrt;
    }


}
