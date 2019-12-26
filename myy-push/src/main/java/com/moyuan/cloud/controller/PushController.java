package com.moyuan.cloud.controller;

import com.gexin.fastjson.JSONException;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.TtsVoiceSender;
import com.github.qcloudsms.TtsVoiceSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.moyuan.cloud.VO.PushParamIn;
import com.moyuan.cloud.common.JsonResult;
import com.moyuan.cloud.common.PushAppOnline;
import com.moyuan.cloud.pojo.MyyPushmsglogs;
import com.moyuan.cloud.pojo.MyyUserClientid;
import com.moyuan.cloud.service.MyyUserCidService;
import com.moyuan.cloud.service.RedisPool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.*;

@RestController
@Api(value = "推送管理",tags = "推送管理接口")
@RefreshScope //开启更新功能
public class PushController {
    @Autowired
    MyyUserCidService myyUserCidService;

    @Value("${appid}")
    int appid;// 短信应用 SDK AppID 以1400开头

    @Value("${appkey}")
    String appkey;// 短信应用 SDK AppKey

    @Value("${smsSign}")
    String smsSign;// 签名

    @Value("${msgtemplateId}")
    int msgtemplateId;// 短信模板id

    @Value("${phonetemplateId}")
    int phonetemplateId;// 语音模板id

    @Value("${codetemplateId}")
    int codetemplateId;// 验证码模板id

    /**
     * param content(3个值)
     * param phone
     * @return
     */
    @ApiOperation(value="短信", notes="手机号，短信模板参数")
    @RequestMapping(value="/msg", method= RequestMethod.POST)
    public JsonResult pushMsg(@RequestBody PushParamIn pushParamIn){
        JsonResult json = null;

        // 短信模板 ID，需要在短信应用中申请
//        int templateId = 381457; // NOTE: 这里的模板 ID`7839`只是示例，真实的模板 ID 需要在短信控制台中申请（3个参数）
        // 签名
//        String smsSign = "墨远信息"; // NOTE: 签名参数使用的是`签名内容`，而不是`签名ID`。这里的签名"腾讯云"只是示例，真实的签名需要在短信控制台申请
        // 需要发送短信的手机号码
//        ArrayList<String> phoneNumbers = [];

        String emsg ="";
        try {
            ArrayList<String> params = pushParamIn.getContent();
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            Map map = new HashMap();
            SmsSingleSenderResult result = ssender.sendWithParam("86", pushParamIn.getPhone(),
                    msgtemplateId, params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
            map.put(pushParamIn.getPhone(),result.errMsg);
            json = new JsonResult(0,map);
        } catch (HTTPException e) {
            // HTTP 响应码错误
            e.printStackTrace();
            emsg = ""+e;
            json = new JsonResult(1,emsg);
        } catch (JSONException e) {
            // JSON 解析错误
            e.printStackTrace();
            emsg = ""+e;
            json = new JsonResult(1,emsg);
        } catch (IOException e) {
            // 网络 IO 错误
            e.printStackTrace();
            emsg = ""+e;
            json = new JsonResult(1,emsg);
        }

        MyyPushmsglogs myyPushmsglogs = new MyyPushmsglogs();
        myyPushmsglogs.setType("msg");
        myyPushmsglogs.setInfo(pushParamIn.getPhone() + pushParamIn.getContent().toString());
        myyPushmsglogs.setResult(json.toString());
        if (json.getCode()==0){
            myyPushmsglogs.setResult("OK");
        }else {
            myyPushmsglogs.setResult("error"+json.getMessage());
        }
        myyUserCidService.save_push(myyPushmsglogs);
        return json;
    }

    /**
     * param content(3个值)
     * param phone
     * @return
     */
    @ApiOperation(value="语音消息", notes="手机号，语音模板参数")
    @RequestMapping(value="/phone", method= RequestMethod.POST)
    public JsonResult pushPhone(@RequestBody PushParamIn pushParamIn){
        JsonResult json = null;

        // 需要发送短信的手机号码
//        ArrayList<String> phoneNumbers = pushParamIn.getPhones();
        // 短信模板 ID，需要在短信应用中申请
//        int templateId = 381410; // NOTE: 这里的模板 ID`7839`只是示例，真实的模板 ID 需要在短信控制台中申请（2个参数）

        String emsg ="";
        Map map = new HashMap();

        try {
            ArrayList<String> params = pushParamIn.getContent();
            TtsVoiceSender tvsender = new TtsVoiceSender(appid, appkey);
            TtsVoiceSenderResult result = tvsender.send("86", pushParamIn.getPhone(),
                    phonetemplateId, params, 2, "");
            System.out.println(result);
            map.put(pushParamIn.getPhone(),result.errMsg);

            //////因命中下发频率没有发送成功的，延时35s再发一次
            if (result.result == 1013){
                Timer timer = new Timer();// 实例化Timer类
                timer.schedule(new TimerTask() {
                    public void run() {
                        try {
                            TtsVoiceSenderResult result1 = tvsender.send("86", pushParamIn.getPhone(),
                                    phonetemplateId, params, 2, "");
                            System.out.println(result1);
                        }catch (HTTPException e) {
                            // HTTP 响应码错误
                            e.printStackTrace();

                        } catch (JSONException e) {
                            // JSON 解析错误
                            e.printStackTrace();

                        } catch (IOException e) {
                            // 网络 IO 错误
                            e.printStackTrace();
                        }

                    }
                }, 35000);//

            }
            json = new JsonResult(0,map);
        } catch (HTTPException e) {
            // HTTP 响应码错误
            e.printStackTrace();
            emsg = ""+e;
            json = new JsonResult(1,"error:"+emsg);
        } catch (JSONException e) {
            // JSON 解析错误
            e.printStackTrace();
            emsg = ""+e;
            json = new JsonResult(1,"error:"+emsg);
        } catch (IOException e) {
            // 网络 IO 错误
            e.printStackTrace();
            emsg = ""+e;
            json = new JsonResult(1,"error:"+emsg);
        }

        MyyPushmsglogs myyPushmsglogs = new MyyPushmsglogs();
        myyPushmsglogs.setType("phone");
        myyPushmsglogs.setInfo(pushParamIn.getPhone() + pushParamIn.getContent().toString());
        if (json.getCode()==0){
            myyPushmsglogs.setResult("OK");
        }else {
            myyPushmsglogs.setResult("error"+json.getMessage());
        }
        myyUserCidService.save_push(myyPushmsglogs);
        return json;
    }

//    @ApiOperation(value="app推送", notes="根据组织id")
//    @RequestMapping(value="/pushToAppsByDistrict", method= RequestMethod.POST)
//    public JsonResult pushToAppsByDistrict(@RequestBody PushParamIn pushParamIn) throws Exception{
//        PushAppOnline n = new PushAppOnline();
//
//        List<String> cids = new ArrayList<>();
//        List<MyyUserClientid> cidAll = myyUserCidService.findAllByDistrictid(pushParamIn.getDistrictid());
//        for (MyyUserClientid t : cidAll){
//            cids.add(t.getClientid());
//        }
//        String result = n.pushToUserOne(cids, pushParamIn.getApptitle(), pushParamIn.getAppcontent());
//
//        MyyPushmsglogs myyPushmsglogs = new MyyPushmsglogs();
//        myyPushmsglogs.setType("app");
//        myyPushmsglogs.setInfo("districtid:"+pushParamIn.getDistrictid()+","+ pushParamIn.getApptitle()+","+ pushParamIn.getAppcontent());
//        myyPushmsglogs.setResult(result);
//        myyUserCidService.save_push(myyPushmsglogs);
//
//        return new JsonResult(0,result);
//    }

    @ApiOperation(value="app推送", notes="根据用户id")
    @RequestMapping(value="/pushToAppsByUserid", method= RequestMethod.POST)
    public JsonResult pushToAppsByUserid(@RequestBody PushParamIn pushParamIn) throws Exception{
        PushAppOnline n = new PushAppOnline();

        MyyPushmsglogs myyPushmsglogs = new MyyPushmsglogs();
        myyPushmsglogs.setType("app");
        myyPushmsglogs.setInfo("userid:"+pushParamIn.getUserid()+ ",title"+pushParamIn.getApptitle()+",content"+ pushParamIn.getAppcontent());

        List<String> cids = new ArrayList<>();
        MyyUserClientid cidAll = myyUserCidService.findByUserid(pushParamIn.getUserid());
        if (cidAll == null){
            myyPushmsglogs.setResult("人员还没登陆过！");
        }else {
            cids.add(cidAll.getClientid());
            String result = n.pushToUserOne(cids, pushParamIn.getApptitle(), pushParamIn.getAppcontent());
            myyPushmsglogs.setResult(result);
        }

        myyUserCidService.save_push(myyPushmsglogs);

        return new JsonResult(0,myyPushmsglogs.getResult());
    }


    @ApiOperation(value="短信验证码", notes="手机号，短信模板参数")
    @RequestMapping(value="/msgcode", method= RequestMethod.POST)
    public JsonResult pushMsgCode(@RequestBody PushParamIn pushParamIn){
        JsonResult<String> jsrt = null;
        Jedis jedis = RedisPool.getJedis();
        jedis.select(0);

        // 短信模板 ID，需要在短信应用中申请
//        int templateId = 278667; // NOTE: 这里的模板 ID`7839`只是示例，真实的模板 ID 需要在短信控制台中申请
//        // 签名
//        String smsSign = "墨远信息"; // NOTE: 签名参数使用的是`签名内容`，而不是`签名ID`。这里的签名"腾讯云"只是示例，真实的签名需要在短信控制台申请

        String code = String.valueOf(getRandomNumber(9999, 1000));
        ArrayList<String> params = new ArrayList<String>();
        params.add(code);params.add("5");
        try {
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.sendWithParam("86", pushParamIn.getPhone(),
                    codetemplateId,params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信

            if("OK".equals(result.errMsg)) {
                jedis.set(pushParamIn.getPhone()+"smscode", code);
                jedis.expire(pushParamIn.getPhone()+"smscode",300);
                jsrt = new JsonResult<String>(0,"ok", result.errMsg);
            }else {
                jsrt = new JsonResult<String>(1,"error",result.errMsg);
            }
        } catch (HTTPException e) {
            // HTTP 响应码错误
            jsrt = new JsonResult<String>(2,"error",e.getClass().getName() + ":" + e.getMessage());
        } catch (JSONException e) {
            // JSON 解析错误
            jsrt = new JsonResult<String>(2,"error",e.getClass().getName() + ":" + e.getMessage());
        } catch (IOException e) {
            // 网络 IO 错误
            jsrt = new JsonResult<String>(2,"error",e.getClass().getName() + ":" + e.getMessage());
        }

        MyyPushmsglogs myyPushmsglogs = new MyyPushmsglogs();
        myyPushmsglogs.setType("msgCode");
        myyPushmsglogs.setInfo(pushParamIn.getPhone()+code);
        if (jsrt.getCode()==0){
            myyPushmsglogs.setResult("OK");
        }else {
            myyPushmsglogs.setResult("error"+jsrt.getData());
        }

        myyUserCidService.save_push(myyPushmsglogs);

        RedisPool.returnResource(jedis);
        return jsrt;
    }

    @ApiOperation(value="短信验证码（登陆外接口调用）", notes="手机号，短信模板参数")
    @RequestMapping(value="/msgcodes", method= RequestMethod.POST)
    public JsonResult pushMsgCodes(@RequestBody PushParamIn pushParamIn){
        JsonResult<String> jsrt = null;
        Jedis jedis = RedisPool.getJedis();
        jedis.select(0);

        // 短信模板 ID，需要在短信应用中申请
//        int templateId = 278667; // NOTE: 这里的模板 ID`7839`只是示例，真实的模板 ID 需要在短信控制台中申请
//        // 签名
//        String smsSign = "墨远信息"; // NOTE: 签名参数使用的是`签名内容`，而不是`签名ID`。这里的签名"腾讯云"只是示例，真实的签名需要在短信控制台申请

        String code = String.valueOf(getRandomNumber(9999, 1000));
        ArrayList<String> params = new ArrayList<String>();
        params.add(code);params.add("5");
        try {
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.sendWithParam("86", pushParamIn.getPhone(),
                    codetemplateId,params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信

            if("OK".equals(result.errMsg)) {
                jedis.set(pushParamIn.getPhone()+"smscode", code);
                jedis.expire(pushParamIn.getPhone()+"smscode",300);
                jsrt = new JsonResult<String>(0,"ok", result.errMsg);
            }else {
                jsrt = new JsonResult<String>(1,"error",result.errMsg);
            }
        } catch (HTTPException e) {
            // HTTP 响应码错误
            jsrt = new JsonResult<String>(2,"error",e.getClass().getName() + ":" + e.getMessage());
        } catch (JSONException e) {
            // JSON 解析错误
            jsrt = new JsonResult<String>(2,"error",e.getClass().getName() + ":" + e.getMessage());
        } catch (IOException e) {
            // 网络 IO 错误
            jsrt = new JsonResult<String>(2,"error",e.getClass().getName() + ":" + e.getMessage());
        }

        MyyPushmsglogs myyPushmsglogs = new MyyPushmsglogs();
        myyPushmsglogs.setType("msgCode");
        myyPushmsglogs.setInfo(pushParamIn.getPhone()+code);
        if (jsrt.getCode()==0){
            myyPushmsglogs.setResult("OK");
        }else {
            myyPushmsglogs.setResult("error"+jsrt.getData());
        }

        myyUserCidService.save_push(myyPushmsglogs);

        RedisPool.returnResource(jedis);
        return jsrt;
    }

    /***
     * 获取随机验证码
     * @param max 最大数
     * @param min 最小数
     * @return
     */
    private int getRandomNumber(int max,int min) {
        return new Random().nextInt(max)%(max-min+1) + min;
    }


}