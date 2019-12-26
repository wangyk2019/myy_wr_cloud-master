package com.moyuaninfo.cloud.controller;

import com.alibaba.fastjson.JSONObject;
import com.moyuaninfo.cloud.VO.FindAllAreaByParams;
import com.moyuaninfo.cloud.VO.FindAllAreaIn;
import com.moyuaninfo.cloud.VO.FindAllIn;
import com.moyuaninfo.cloud.feignClient.CloudNettyClient;
import com.moyuaninfo.cloud.pojo.*;
import com.moyuaninfo.cloud.VO.PushParamIn;
import com.moyuaninfo.cloud.common.JsonResult;
import com.moyuaninfo.cloud.feignClient.BaseServerClient;
import com.moyuaninfo.cloud.feignClient.CameraClient;
import com.moyuaninfo.cloud.feignClient.PushClient;
import com.moyuaninfo.cloud.service.MyyCamwarnService;
import com.moyuaninfo.cloud.service.MyyWarnPushService;
import com.moyuaninfo.cloud.service.XianqingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RestController
@RequestMapping(value = "/dangerinfo")
@Api(value = "险情生成接口",tags = "险情生成接口")
@RefreshScope //开启更新功能
public class XianqingController {

    @Autowired
    XianqingService objService;
    @Autowired
    MyyWarnPushService myyWarnPushService;
    @Autowired
    MyyCamwarnService myyCamwarnService;

    @Autowired
    CameraClient cameraClient;
    @Autowired
    BaseServerClient baseServerClient;
    @Autowired
    PushClient pushClient;
    @Autowired
    CloudNettyClient cloudNettyClient;


    /**
     * 接受险情结果，根据结果推送，并发送结果回应
     * @param dangerid  险情id
     * @param result  检测结果 0：是，1：否
     * @return
     */
    @ApiOperation(value="深度结果回传")
    @PostMapping(value = "/deepResult")
    public JsonResult deepResult(@RequestParam("dangerid") long dangerid, @RequestParam("result") String result) {
        JsonResult jsonResu = null;
        try{
            //根据id获取险情详情
            MyyDangerInfo danger = objService.getObj(dangerid);
            if(danger != null){
                //根据id获取摄像头详情
                JsonResult<MyyCamera> cameraJsonResult = cameraClient.findById(danger.getCameraid());
                if (cameraJsonResult.getCode() == 0 && cameraJsonResult.getData() != null){
                    danger.setResult(result);//更新结果
                    //根据险情类型获取险情类型详情
                    JsonResult<MyyWarntype> myyWarntypeJsonResult = baseServerClient.findWarntypeByWarnen(new FindAllIn(danger.getWarntype()));
                    if (myyWarntypeJsonResult.getCode() ==0 && myyWarntypeJsonResult.getData()!= null){
                        danger.setName(myyWarntypeJsonResult.getData().getWarnname());//更新险情名称
                    }
                    danger = objService.addObj(danger);//更新

                    if ("1".equals(result)){//结果为1的进行消息推送
                        //查询区域3级下的人员推送配置
                        List<MyyWarnpushcfg> myyWarnpushcfgs = myyWarnPushService.findByareabottomid(cameraJsonResult.getData().getAreabottomid());
                        if (myyWarnpushcfgs.size() > 0) {
                            for (int i=0;i<myyWarnpushcfgs.size();i++){
                                //推送信息
                                PushParamIn pushParamIn = new PushParamIn();
                                pushParamIn.setDistrictid(danger.getDistrictid());
                                //根据id获取人员详情
                                JsonResult<MyyUser> userJsonResult = baseServerClient.findUser(myyWarnpushcfgs.get(i).getUserid());
                                if (userJsonResult.getCode()==0 && userJsonResult.getData() != null) {
                                    pushParamIn.setPhone(userJsonResult.getData().getPhonenumber());

                                    //推送设置的险情需与险情结果一致
                                    if (myyWarnpushcfgs.get(i).getWarntypeid() == myyWarntypeJsonResult.getData().getWarntypeid()){
                                        //app推送
                                        if (myyWarnpushcfgs.get(i).getPushtype().contains("app")){
                                            //所需参数
                                            pushParamIn.setApptitle(danger.getName());
                                            pushParamIn.setAppcontent(cameraJsonResult.getData().getLocation()+"，"+danger.getTime()+"检测到"+danger.getName()+"疑似险情！");
                                            pushParamIn.setUserid(userJsonResult.getData().getId());
                                            pushClient.pushToAppsByUserid(pushParamIn);
                                        }
                                        //短信推送
                                        if (myyWarnpushcfgs.get(i).getPushtype().contains("msg")){
                                            //所需参数
                                            ArrayList<String> al = new ArrayList<>();
                                            al.add(danger.getName());
                                            al.add(danger.getTime());
                                            al.add(cameraJsonResult.getData().getLocation());
                                            pushParamIn.setContent(al);
                                            pushClient.pushMsg(pushParamIn);
                                        }
                                        //电话推送
                                        if (myyWarnpushcfgs.get(i).getPushtype().contains("phone")){
                                            //所需参数
                                            ArrayList<String> al = new ArrayList<>();
                                            al.add("正洁环境");
                                            al.add(danger.getName());
                                            al.add(cameraJsonResult.getData().getLocation());
                                            pushParamIn.setContent(al);
                                            pushClient.pushPhone(pushParamIn);
                                        }
                                    }
                                }

                            }
                        }

                    }

                    //发给cloudnetty的回应
                    JSONObject taskobj = new JSONObject();
                    taskobj.put("client", "org-" + danger.getDistrictid());
                    taskobj.put("task", "detectionresult");
                    taskobj.put("msgtype", "request1");
                    taskobj.put("danger", danger);
                    taskobj.put("api","/warn/deepResultBack");
                    taskobj.put("apiclient","localservice");
//                    RestTemplate restTemplate = new RestTemplate();
//                    restTemplate.postForEntity("http://127.0.0.1:2222/cloudNetty/sendMsg", taskobj, JSONObject.class);
                    cloudNettyClient.nettySend(taskobj);

                    jsonResu = new JsonResult<>(0,"ok");

                }else{
                    jsonResu = new JsonResult<>(1,"没有找到摄像头");
                }
            }else{
                jsonResu = new JsonResult<>(1,"没有找到险情信息");
            }
        }catch (Exception e){
            jsonResu = new JsonResult<>(1,e.getMessage());
        }

        return jsonResu;
    }


    @Value("${bucketurl}")
    String BUCKETURL; ///#存储桶获取地址
    @Value("${detectone}")
    String DETECTONE; ///发送给深度检测

    /**
     *根据json字符串转换存到险情表
     * @param msg
     * @return
     */
    @ApiOperation(value="保存初筛数据")
    @PostMapping(value="/addInfo")
    public JsonResult addInfo(@RequestBody String msg) {
        JSONObject rceobj = (JSONObject) JSONObject.parse(msg);
        System.out.println(rceobj);

        try {
            if ("deepdetection".equals(rceobj.getString("task"))){///初筛
                MyyDangerInfo danger = new MyyDangerInfo();
                danger.setDistrictid(rceobj.getLong("orgid"));
                danger.setCameraid(rceobj.getLong("cameraid"));
                danger.setWarntype(rceobj.getString("warntype"));
                danger.setIp(rceobj.getString("ip"));
                danger.setFilepath(BUCKETURL+"/"+rceobj.getString("filepath")+rceobj.getString("record"));
                danger.setPicpath(BUCKETURL+"/"+rceobj.getString("filepath")+rceobj.getString("imgname"));
                danger.setStatus("0");
                danger.setResult("0");
                danger.setIscheck(0);

                ////////////根据filepath的值获取险情发生时间
                int ca=0;
                Matcher matcher= Pattern.compile("_").matcher(rceobj.getString("filepath"));
                if(matcher.find()){
                    ca = matcher.start();
                }else{
                    return new JsonResult<>(1,"时间有问题");
                }
                String time1 = rceobj.getString("filepath").substring(ca-4,ca+6).replace('_','-');
                String time2 = rceobj.getString("filepath").substring(ca+7,ca+15).replace('_',':');
                String time = time1+" "+time2;
                danger.setTime(time);

                objService.addObj(danger);

                return new JsonResult(0,"ok");
            }else{
                return new JsonResult(1,"taskError");
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
            return new JsonResult(1,e.getMessage()+"");
        }
    }

    /**
     *根据json字符串转换
     * 更新是否上传状态，并发给深度检测
     * @param msg
     * @return
     */
    @ApiOperation(value="上传深度")
    @PostMapping(value="/sendToDeep")
    public JsonResult sendToDeep(@RequestBody String msg) {
        JSONObject rceobj = (JSONObject) JSONObject.parse(msg);
        System.out.println(rceobj);
        try {
            if ("todeepdetection".equals(rceobj.getString("task"))){///上传深度任务
                //根据组织id,摄像头ip,险情类型，图片路径查询险情
                List<MyyDangerInfo> dangers = objService.findAllByOrgidAndIpAndWarntypeAndFilepathAndPicpath(rceobj.getLong("orgid"),rceobj.getString("ip"),
                        rceobj.getString("warntype"),BUCKETURL+"/"+rceobj.getString("filepath")+"/"+rceobj.getString("imgname"));
                if (dangers.size()>0){
                    MyyDangerInfo lc = dangers.get(0);
                    if ("0".equals(lc.getStatus())){
                        JSONObject json = new JSONObject();
                        json.put("warnid", lc.getId());
                        json.put("warntype", rceobj.getString("warntype"));
                        json.put("ip", lc.getIp());
                        json.put("filepath", BUCKETURL+"/"+rceobj.getString("filepath")+"/");
                        json.put("record", rceobj.getString("record"));
                        json.put("imgname", rceobj.getString("imgname"));

                        //发给深度
//                        RestTemplate restTemplate = new RestTemplate();
//                        String result = restTemplate.postForObject(DETECTONE, json, String.class);
//                        JsonResult<String> result = cloudNettyClient.nettySend(json);
//                        System.out.println("深度检测的回应："+result);
//                        if(result.getCode() == 0){
                            lc.setStatus("1");
                            objService.addObj(lc);
//                        }
                        deepResult(lc.getId(),"1");

                        return new JsonResult(0,"ok");
                    }else {
                        return new JsonResult(1,"已经发过");
                    }
                }else {
                    return new JsonResult(1,"dangerError");
                }
            }else{
                return new JsonResult(1,"taskError");
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
            return new JsonResult(1,e.getMessage());
        }
    }


    /**
     * 摄像机配置信息
     * 接口调用
     * @param orgid
     * @return
     */
    @ApiOperation(value="上传摄像头配置", notes="",httpMethod = "POST")
    @PostMapping(value="/delayCheck")
    public JsonResult delayCheck(@RequestParam(value = "orgid") long orgid) {
        JSONObject map1 = new JSONObject();
        try{
            //查询组织下的摄像头信息
            JsonResult<List<MyyCamera>> myyCameraJsonResult = cameraClient.findAllByDistrict(orgid);
            if (myyCameraJsonResult.getCode() == 0 && myyCameraJsonResult.getData().size() <= 0) {
                return new JsonResult<>(1,"没有摄像头...");
            }
            List<String> roomlist = new ArrayList<String>();
            HashMap<String, HashMap> tmap = new HashMap<>();
            HashMap<String, HashMap> room = null;

            for (int i = 0; i < myyCameraJsonResult.getData().size(); i++) {
                MyyCamera camera = myyCameraJsonResult.getData().get(i);

                HashMap<String, Object> map = new HashMap();
                map.put("ip", camera.getIp()+":"+camera.getChannel());
                map.put("cameraid", camera.getId());
                map.put("channel", camera.getChannel());
                map.put("rtspport", camera.getRtspport());
                map.put("rtsp", camera.getLink());

                if (tmap.get(String.valueOf(camera.getAreabottomid())) != null) {
                    room = tmap.get(String.valueOf(camera.getAreabottomid()));
                } else {
                    room = new HashMap<>();
                }
                room.put(camera.getIp()+":"+camera.getChannel(), map);
                tmap.put(String.valueOf(camera.getAreabottomid()), room);

                //查询摄像头的险情配置
                List<MyyCamwarnconfig> myyCamwarnconfigs = myyCamwarnService.findByCameraid(camera.getId());

                HashMap<String, Object> detection = new HashMap<>();
                for (MyyCamwarnconfig myyCamwarnconfig : myyCamwarnconfigs){
                    HashMap<String, ArrayList<String>> week = new HashMap<>();
                    //查询摄像头的监控天配置
                    List<MyyMonitorday> dateList = myyCamwarnService.findByCamwarnid_md(myyCamwarnconfig.getId());
                    if (dateList.size() > 0) {
                        for (int j = 0; j < dateList.size(); j++) {
                            //查询摄像头的监控时间段配置
                            List<MyyMonitortime> times = myyCamwarnService.findByDayid_mt(dateList.get(j).getId());
                            ArrayList<String> timee = new ArrayList<>();
                            if (times.size() > 0) {
                                for (int k = 0; k < times.size(); k++) {
                                    timee.add(times.get(k).getTime());
                                }
                            }
                            week.put(String.valueOf(Integer.parseInt(dateList.get(j).getDay()) - 1), timee);

                            HashMap<String, Object> warn = new HashMap<>();
                            warn.put("enable", 1);
                            warn.put("week", week);

                            //查询险情类型信息
                            JsonResult<MyyWarntype> myyWarntypeJsonResult = baseServerClient.findWarnById(new FindAllIn(myyCamwarnconfig.getWarntypeid()));
                            detection.put(myyWarntypeJsonResult.getData().getWarnen(), warn);
//                            detection.put(myyCamwarnconfig.getWarntype(), warn);
                        }
                    }
                }
                map.put("detection", detection);

                map1.put(camera.getIp()+":"+camera.getChannel(), map);////////ip
            }
            Iterator ie = tmap.keySet().iterator();
            Iterator te = null;
            String key2 = null;
            List<String> iplist = null;
            while (ie.hasNext()) {
                String key = (String) ie.next();
                roomlist.add(key);
                te = tmap.get(key).keySet().iterator();
                iplist = new ArrayList<String>();
                while (te.hasNext()) {
                    key2 = (String) te.next();
                    iplist.add(key2);
                }
                map1.put(key, iplist);//////iplist
            }

//            FindAllAreaIn findAllAreaIn = new FindAllAreaIn();
//            findAllAreaIn.setOrgId(orgid);
//            JsonResult<List<MyyAreaone>> alllist = baseServerClient.findAreaones(findAllAreaIn);
//            roomlist.clear();
//            for (int i = 0; i < alllist.getData().size(); i++) {
//                MyyAreaone lab = alllist.getData().get(i);
//                roomlist.add(String.valueOf(lab.getId()));
//            }
            JsonResult<List<MyyAreabottom>> myya = baseServerClient.myyAreabottomsOrgid(orgid);
            roomlist.clear();
            if (myya.getCode()==0 && myya.getData()!=null){
                for (MyyAreabottom myyAreabottom : myya.getData()) {
                    roomlist.add(myyAreabottom.getId().toString());
                }
            }
            map1.put("arealist", roomlist);//////roomlist

            map1.put("client", "org-" + orgid);
            map1.put("task", "config");
            map1.put("msgtype", "request1");

            map1.put("api","/outputCameraConfig");
            map1.put("apiclient","localservice");
            System.out.println(map1);

//            RestTemplate restTemplate = new RestTemplate();
//            restTemplate.postForEntity("http://127.0.0.1:2222/cloudNetty/sendMsg",map1,JSONObject.class);
            cloudNettyClient.nettySend(map1);

            return new JsonResult<>(0,"ok");
        }catch (Exception e){
            return new JsonResult<>(1,e.getMessage());
        }
    }

    /**
     * 摄像机配置信息
     * netty调用
     * @param msg
     * @return
     */
    @ApiOperation(value="netty查询摄像头配置")
    @PostMapping(value="/localCamConfig")
    public JsonResult localCamConfig(@RequestBody String msg) {
        JSONObject rceobj = (JSONObject) JSONObject.parse(msg);
        try{
            JSONObject map1 = new JSONObject();
            JsonResult<List<MyyCamera>> myyCameraJsonResult = cameraClient.findAllByDistrict(rceobj.getLong("orgid"));
            if (myyCameraJsonResult.getCode() == 0 && myyCameraJsonResult.getData().size() <= 0) {
                return new JsonResult<>(1,"没有摄像头...");
            }
            List<String> roomlist = new ArrayList<String>();
            HashMap<String, HashMap> tmap = new HashMap<>();
            HashMap<String, HashMap> room = null;

            for (int i = 0; i < myyCameraJsonResult.getData().size(); i++) {
                MyyCamera camera = myyCameraJsonResult.getData().get(i);

                HashMap<String, Object> map = new HashMap();
                map.put("ip", camera.getIp()+":"+camera.getChannel());
                map.put("cameraid", camera.getId());
                map.put("channel", camera.getChannel());
                map.put("rtspport", camera.getRtspport());
                map.put("rtsp", camera.getLink());

                if (tmap.get(String.valueOf(camera.getAreabottomid())) != null) {
                    room = tmap.get(String.valueOf(camera.getAreabottomid()));
                } else {
                    room = new HashMap<>();
                }
                room.put(camera.getIp()+":"+camera.getChannel(), map);
                tmap.put(String.valueOf(camera.getAreabottomid()), room);

                //查询摄像头的险情配置
                List<MyyCamwarnconfig> myyCamwarnconfigs = myyCamwarnService.findByCameraid(camera.getId());

                HashMap<String, Object> detection = new HashMap<>();
                for (MyyCamwarnconfig myyCamwarnconfig : myyCamwarnconfigs){
                    HashMap<String, ArrayList<String>> week = new HashMap<>();
                    //查询摄像头的监控天配置
                    List<MyyMonitorday> dateList = myyCamwarnService.findByCamwarnid_md(myyCamwarnconfig.getId());
                    if (dateList.size() > 0) {
                        for (int j = 0; j < dateList.size(); j++) {
                            //查询摄像头的监控时间段配置
                            List<MyyMonitortime> times = myyCamwarnService.findByDayid_mt(dateList.get(j).getId());
                            ArrayList<String> timee = new ArrayList<>();
                            if (times.size() > 0) {
                                for (int k = 0; k < times.size(); k++) {
                                    timee.add(times.get(k).getTime());
                                }
                            }
                            week.put(String.valueOf(Integer.parseInt(dateList.get(j).getDay()) - 1), timee);

                            HashMap<String, Object> warn = new HashMap<>();
                            warn.put("enable", 1);
                            warn.put("week", week);

                            //查询险情类型信息
                            JsonResult<MyyWarntype> myyWarntypeJsonResult = baseServerClient.findWarnById(new FindAllIn(myyCamwarnconfig.getWarntypeid()));
                            detection.put(myyWarntypeJsonResult.getData().getWarnen(), warn);
//                            detection.put(myyCamwarnconfig.getWarntype(), warn);
                        }
                    }
                }
                map.put("detection", detection);

                map1.put(camera.getIp()+":"+camera.getChannel(), map);////////ip
            }
            Iterator ie = tmap.keySet().iterator();
            Iterator te = null;
            String key2 = null;
            List<String> iplist = null;
            while (ie.hasNext()) {
                String key = (String) ie.next();
                roomlist.add(key);
                te = tmap.get(key).keySet().iterator();
                iplist = new ArrayList<String>();
                while (te.hasNext()) {
                    key2 = (String) te.next();
                    iplist.add(key2);
                }
                map1.put(key, iplist);//////iplist
            }

//            FindAllAreaIn findAllAreaIn = new FindAllAreaIn();
//            findAllAreaIn.setOrgId(orgid);
//            JsonResult<List<MyyAreaone>> alllist = baseServerClient.findAreaones(findAllAreaIn);
//            roomlist.clear();
//            for (int i = 0; i < alllist.getData().size(); i++) {
//                MyyAreaone lab = alllist.getData().get(i);
//                roomlist.add(String.valueOf(lab.getId()));
//            }
            JsonResult<List<MyyAreabottom>> myya = baseServerClient.myyAreabottomsOrgid(rceobj.getLong("orgid"));
            roomlist.clear();
            if (myya.getCode()==0 && myya.getData()!=null){
                for (MyyAreabottom myyAreabottom : myya.getData()) {
                    roomlist.add(myyAreabottom.getId().toString());
                }
            }
            map1.put("arealist", roomlist);//////roomlist

            map1.put("client", "org-" + rceobj.getLong("orgid"));
            map1.put("task", "config");
            map1.put("msgtype", "request1");

            map1.put("api","/outputCameraConfig");
            map1.put("apiclient","localservice");
            System.out.println(map1);

//            RestTemplate restTemplate = new RestTemplate();
//            restTemplate.postForEntity("http://127.0.0.1:2222/cloudNetty/sendMsg",map1,JSONObject.class);
            cloudNettyClient.nettySend(map1);

            return new JsonResult<>(0,"ok");
        }catch (Exception e){
            return new JsonResult<>(1,e.getMessage());
        }


    }



//    @Scheduled(cron="0 0 0/12 * * ?")
//    public void autoTestCam() {
//        JsonResult<String> jsrt = null;
//        try {
//            ArrayList<Long> arrayList = new ArrayList<>();
//
//            List<MyyCamera> cameras = myyCameraService.findAllByParams(0,arrayList,"","","");
//            for (MyyCamera myyCamera :cameras){
//                JsonResult<MyyArea> myyArea = baseServerClient.findArea(myyCamera.getAreaid());
//
//                JSONObject taskobj = new JSONObject();
//                taskobj.put("client","org-"+myyArea.getData().getDistrictid());
//                taskobj.put("task","testcamera");
//                taskobj.put("api","/stream/testCamera");
//                taskobj.put("apiclient","baseclient");
//                taskobj.put("msgtype","request1");
//                taskobj.put("ip",myyCamera.getIp());
//                taskobj.put("camid",myyCamera.getId());
//                taskobj.put("channel",myyCamera.getChannel());
//
//                //TODO 通过netty发送测试摄像头请求
//                RestTemplate restTemplate = new RestTemplate();
//                jsrt = restTemplate.postForObject(MyConstants.NETTY_SEND, taskobj, JsonResult.class);
//
//                System.out.println(jsrt);
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }


}