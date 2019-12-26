package com.moyuaninfo.cloud.controller;

import com.alibaba.fastjson.JSONObject;
import com.moyuaninfo.cloud.VO.*;
import com.moyuaninfo.cloud.common.JsonResult;
import com.moyuaninfo.cloud.feignClient.BaseServerClient;
import com.moyuaninfo.cloud.feignClient.CameraClient;
import com.moyuaninfo.cloud.feignClient.CloudNettyClient;
import com.moyuaninfo.cloud.pojo.*;
import com.moyuaninfo.cloud.service.MyyCamwarnService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "摄像头险情配置接口",tags = "摄像头险情配置接口")
@RequestMapping("/camWarn")
public class CamWarnController {
    @Autowired
    MyyCamwarnService myyCamwarnService;

    @Autowired
    BaseServerClient baseServerClient;

    @Autowired
    CameraClient cameraClient;
    @Autowired
    CloudNettyClient cloudNettyClient;

    @ApiOperation(value = "添加修改摄像头险情信息",notes = "",httpMethod = "POST")
    @PostMapping(value = "/addCamWarn")
    public JsonResult<MyyCamwarnconfig> save(@RequestBody MyyCamwarnconfig myyCamwarnconfig){
        JsonResult jr = null;

        try {
            myyCamwarnconfig = myyCamwarnService.save(myyCamwarnconfig);

            if (myyCamwarnconfig.getOnoff().equals("0")){
                deleteOne(myyCamwarnconfig.getId());
            }

            JsonResult<MyyCamera> cameraJsonResult = cameraClient.findById(myyCamwarnconfig.getCameraid());
            if (cameraJsonResult.getCode() == 0 && cameraJsonResult.getData() != null) {
                JsonResult<MyyAreabottom> areabottom = baseServerClient.findAreabottom(cameraJsonResult.getData().getAreabottomid());
                JsonResult<MyyAreatwo> areatwo = baseServerClient.findAreatwo(areabottom.getData().getAreatwoid());
                JsonResult<MyyAreaone> areaone = baseServerClient.findAreaone(areatwo.getData().getAreaoneid());
                delayCheck(areaone.getData().getDistrictid());
            }
            jr = new JsonResult<>(0);
        }catch (Exception e){
            jr=  new JsonResult(1,e.getMessage().toString());
        }
        return jr;
    }

    @ApiOperation(value = "添加修改摄像头后的险情配置信息",notes = "添加修改摄像头后默认生成",httpMethod = "POST")
    @PostMapping(value = "/addCamWarn_cam")
    public JsonResult addCamWarn_cam(@RequestParam(value = "orgid") long orgid,@RequestParam(value = "cameraid") long cameraid){
        JsonResult jr = null;
        try {
            JsonResult<MyyDistrict> myyDistrictJsonResult = baseServerClient.findDistrict(orgid);

//            if (myyDistrictJsonResult.getCode() ==0 && myyDistrictJsonResult.getData() != null){
//                List<MyyCamwarnconfig> myyCamwarnconfigs = new ArrayList<>();
//
//                    MyyCamwarnconfig myyCamwarnconfig = myyCamwarnService.findAllByCameraidAndWarntype(cameraid,key);
//                    if (myyCamwarnconfig == null){
//                        myyCamwarnconfig = new MyyCamwarnconfig();
//                        myyCamwarnconfig.setCameraid(cameraid);
//                        myyCamwarnconfig.setWarntype(key);
//                    }
//                    myyCamwarnconfig.setOnoff(integ);
//
//                    myyCamwarnconfigs.add(myyCamwarnconfig);
//                myyCamwarnService.saveAll(myyCamwarnconfigs);
//
//            }
            delayCheck(orgid);
            jr = new JsonResult(0);
        }catch (Exception e){
            jr=  new JsonResult(1,e.getMessage().toString());
        }
        return jr;
    }

//    @ApiOperation(value = "组织添加后调用",notes = "更新险情开关信息",httpMethod = "POST")
//    @PostMapping(value = "/addCamWarns_distr")
//    public JsonResult addCamWarns_distr(@RequestParam(value = "orgid") long orgid){
//        JsonResult jr = null;
//        LinkedHashMap stringList = new LinkedHashMap();
//        try {
//            JsonResult<MyyDistrict> myyDistrictJsonResult = baseServerClient.findDistrict(orgid);
//            JsonResult<List<MyyCamera>> myyCameraJsonResult = cameraClient.findAllByDistrict(orgid);
//            if (myyDistrictJsonResult.getCode() ==0 && myyDistrictJsonResult.getData() != null){
//                Iterator iter = stringList.entrySet().iterator();
//                while(iter.hasNext()) {
//                    Map.Entry entry = (Map.Entry)iter.next();
//                    // 获取key
//                    String key = entry.getKey().toString();
//                    // 获取value
//                    String integ = entry.getValue().toString();
//
//                    for (MyyCamera myyCamera : myyCameraJsonResult.getData()){
//                        MyyCamwarnconfig myyCamwarnconfig = myyCamwarnService.findAllByCameraidAndWarntype(myyCamera.getId(),key);
//                        if (myyCamwarnconfig == null){
//                            myyCamwarnconfig = new MyyCamwarnconfig();
//                            myyCamwarnconfig.setCameraid(myyCamera.getId());
//                            myyCamwarnconfig.setWarntype(key);
//                        }
//                        myyCamwarnconfig.setOnoff(integ);
//                        myyCamwarnconfig = myyCamwarnService.save(myyCamwarnconfig);
//
//                        if (integ.equals("0")){
//                            deleteOne(myyCamwarnconfig.getId());
//                        }
//                    }
//
//                }
//
//            }
//            delayCheck(orgid);
//            jr = new JsonResult(0);
//
//        }catch (Exception e){
//            jr=  new JsonResult(1,e.getMessage().toString());
//        }
//        return jr;
//    }

    @ApiOperation(value = "删除摄像头险情信息",notes = "id",httpMethod = "POST")
    @RequestMapping(value = "/deteleCamWarn")
    public JsonResult<MyyCamwarnconfig> deleteOne(@RequestParam(value = "id") long id){
        JsonResult<MyyCamwarnconfig> jsonResult = null;
        try{
            ArrayList<Long> arrayList = new ArrayList<>();
            arrayList.add(id);
            List<MyyMonitorday> objList2 = myyCamwarnService.findByCamwarnid_md(id);
            for (MyyMonitorday b :objList2){
                myyCamwarnService.delete_mts(b.getId());
            }
            myyCamwarnService.delete_mds(id);

            myyCamwarnService.updateById(id);

            MyyCamwarnconfig myyCamwarnconfig = myyCamwarnService.findById(id);

            JsonResult<MyyCamera> cameraJsonResult = cameraClient.findById(myyCamwarnconfig.getCameraid());
            if (cameraJsonResult.getCode() == 0 && cameraJsonResult.getData() != null) {
                JsonResult<MyyAreabottom> areabottom = baseServerClient.findAreabottom(cameraJsonResult.getData().getAreabottomid());
                JsonResult<MyyAreatwo> areatwo = baseServerClient.findAreatwo(areabottom.getData().getAreatwoid());
                JsonResult<MyyAreaone> areaone = baseServerClient.findAreaone(areatwo.getData().getAreaoneid());
                delayCheck(areaone.getData().getDistrictid());
            }

            jsonResult = new JsonResult<>(0,"操作成功！");
        }catch (Exception e){
            jsonResult = new JsonResult<>(1,e.getMessage());
        }
        return jsonResult;
    }

    @ApiOperation(value = "删除摄像头险情信息",notes = "摄像头id",httpMethod = "POST")
    @RequestMapping(value = "/deteleCamWarns")
    public JsonResult<MyyCamwarnconfig> deleteAll(@RequestParam(value = "id") long id){
        JsonResult<MyyCamwarnconfig> jsonResult = null;
        try{
            ArrayList<Long> arrayList = new ArrayList<>();
            arrayList.add(id);
            List<MyyCamwarnconfig> objList1 = myyCamwarnService.findByCameraid(id);
            for (MyyCamwarnconfig a :objList1){
                List<MyyMonitorday> objList2 = myyCamwarnService.findByCamwarnid_md(a.getId());
                for (MyyMonitorday b :objList2){
                    myyCamwarnService.delete_mts(b.getId());
                }
                myyCamwarnService.delete_mds(a.getId());

                myyCamwarnService.updateById(a.getId());
            }
//            myyCamwarnService.delete_camwarn(id);

            JsonResult<MyyCamera> cameraJsonResult = cameraClient.findById(id);
            if (cameraJsonResult.getCode() == 0 && cameraJsonResult.getData() != null) {
                JsonResult<MyyAreabottom> areabottom = baseServerClient.findAreabottom(cameraJsonResult.getData().getAreabottomid());
                JsonResult<MyyAreatwo> areatwo = baseServerClient.findAreatwo(areabottom.getData().getAreatwoid());
                JsonResult<MyyAreaone> areaone = baseServerClient.findAreaone(areatwo.getData().getAreaoneid());
                delayCheck(areaone.getData().getDistrictid());
            }
            jsonResult = new JsonResult<>(0);
        }catch (Exception e){
            jsonResult = new JsonResult<>(1,e.getMessage());
        }
        return jsonResult;
    }

    @ApiOperation(value = "查询摄像头险情信息",notes = "摄像头id",httpMethod = "POST")
    @RequestMapping(value = "/findCamWarns")
    public JsonResult<List<MyyCamwarnconfig>> findAreaonesByParams(@RequestParam(value = "cameraid") long cameraid){
        JsonResult<List<MyyCamwarnconfig>> jsonResult = null;
        try {

            List<MyyCamwarnconfig> objList = myyCamwarnService.findByCameraid(cameraid);
            if(objList == null){
                jsonResult = new JsonResult<>(0,JsonResult.NoDataMSG,objList);
            }else {
                jsonResult = new JsonResult<>(0,objList);
            }
        }catch (Exception e){
            jsonResult = new JsonResult<>(1,e.getMessage());
        }
        return jsonResult;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @ApiOperation(value = "添加监控时间天信息",notes = "",httpMethod = "POST")
    @RequestMapping(value = "/addMoniDay")
    public JsonResult<MyyMonitorday> addMoniDay(@RequestBody MyyMonitordayIn myyMonitordayIn){
        JsonResult jr = null;
        try {

            List<MyyMonitorday> myyMonitordays = new ArrayList<>();
            for (int i=0;i<myyMonitordayIn.getDays().size();i++){
                MyyMonitorday myyMonitorday = new MyyMonitorday();
                myyMonitorday.setCamwarnid(myyMonitordayIn.getCamwarnid());
                myyMonitorday.setDay(myyMonitordayIn.getDays().get(i).toString());
//                myyCamwarnService.save_md(myyMonitorday);
                myyMonitordays.add(myyMonitorday);
            }
            myyCamwarnService.saveAll_md(myyMonitordays);
            MyyCamwarnconfig myyCamwarnconfig = myyCamwarnService.findById(myyMonitordayIn.getCamwarnid());
            JsonResult<MyyCamera> cameraJsonResult = cameraClient.findById(myyCamwarnconfig.getCameraid());
            if (cameraJsonResult.getCode() == 0 && cameraJsonResult.getData() != null) {
                JsonResult<MyyAreabottom> areabottom = baseServerClient.findAreabottom(cameraJsonResult.getData().getAreabottomid());
                JsonResult<MyyAreatwo> areatwo = baseServerClient.findAreatwo(areabottom.getData().getAreatwoid());
                JsonResult<MyyAreaone> areaone = baseServerClient.findAreaone(areatwo.getData().getAreaoneid());
                delayCheck(areaone.getData().getDistrictid());
            }
            jr = new JsonResult<>(0);
        }catch (Exception e){
            jr=  new JsonResult(1,e.getMessage().toString());
        }
        return jr;
    }

    @ApiOperation(value = "删除监控时间天信息",notes = "id",httpMethod = "POST")
    @RequestMapping(value = "/deteleMoniDay")
    public JsonResult<MyyMonitorday> deteleMoniDay(@RequestParam(value = "id") long id){
        JsonResult<MyyMonitorday> jsonResult = null;
        try{
            MyyMonitorday myyMonitorday = myyCamwarnService.findById_md(id);
            MyyCamwarnconfig myyCamwarnconfig = myyCamwarnService.findById(myyMonitorday.getCamwarnid());

            myyCamwarnService.delete_mts(id);
            myyCamwarnService.delete_md(id);

            JsonResult<MyyCamera> cameraJsonResult = cameraClient.findById(myyCamwarnconfig.getCameraid());
            if (cameraJsonResult.getCode() == 0 && cameraJsonResult.getData() != null) {
                JsonResult<MyyAreabottom> areabottom = baseServerClient.findAreabottom(cameraJsonResult.getData().getAreabottomid());
                JsonResult<MyyAreatwo> areatwo = baseServerClient.findAreatwo(areabottom.getData().getAreatwoid());
                JsonResult<MyyAreaone> areaone = baseServerClient.findAreaone(areatwo.getData().getAreaoneid());
                delayCheck(areaone.getData().getDistrictid());
            }

            jsonResult = new JsonResult<>(0,"操作成功！");
        }catch (Exception e){
            jsonResult = new JsonResult<>(1,e.getMessage());
        }
        return jsonResult;
    }

    @ApiOperation(value = "删除监控时间天信息",notes = "camwarnid",httpMethod = "POST")
    @RequestMapping(value = "/deteleMoniDays")
    public JsonResult<MyyMonitorday> deteleMoniDays(@RequestParam(value = "id") long id){
        JsonResult<MyyMonitorday> jsonResult = null;
        try{
            List<MyyMonitorday> objList2 = myyCamwarnService.findByCamwarnid_md(id);
            for (MyyMonitorday b :objList2){
                myyCamwarnService.delete_mts(b.getId());
            }
            myyCamwarnService.delete_mds(id);

            MyyCamwarnconfig myyCamwarnconfig = myyCamwarnService.findById(id);

            JsonResult<MyyCamera> cameraJsonResult = cameraClient.findById(myyCamwarnconfig.getCameraid());
            if (cameraJsonResult.getCode() == 0 && cameraJsonResult.getData() != null) {
                JsonResult<MyyAreabottom> areabottom = baseServerClient.findAreabottom(cameraJsonResult.getData().getAreabottomid());
                JsonResult<MyyAreatwo> areatwo = baseServerClient.findAreatwo(areabottom.getData().getAreatwoid());
                JsonResult<MyyAreaone> areaone = baseServerClient.findAreaone(areatwo.getData().getAreaoneid());
                delayCheck(areaone.getData().getDistrictid());
            }
            jsonResult = new JsonResult<>(0,"操作成功！");
        }catch (Exception e){
            jsonResult = new JsonResult<>(1,e.getMessage());
        }
        return jsonResult;
    }

    @ApiOperation(value = "查询监控时间天信息",notes = "id",httpMethod = "POST")
    @RequestMapping(value = "/findMoniDay")
    public JsonResult<MyyMonitorday> findById_md(@RequestParam(value = "id") long id){
        JsonResult<MyyMonitorday> jsonResult = null;
        try {
            MyyMonitorday obj = myyCamwarnService.findById_md(id);
            jsonResult = new JsonResult<>(0,obj);
        }catch (Exception e){
            jsonResult = new JsonResult<>(1,e.getMessage());
        }
        return jsonResult;
    }

    @ApiOperation(value = "查询监控时间天信息",notes = "险情id",httpMethod = "POST")
    @RequestMapping(value = "/findMoniDays")
    public JsonResult<List<MyyMonitorday>> findMoniDays_md(@RequestParam(value = "险情id") long id){
        JsonResult<List<MyyMonitorday>> jsonResult = null;
        try {
            List<MyyMonitorday> objList = myyCamwarnService.findByCamwarnid_md(id);
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

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @ApiOperation(value = "添加监控时间段信息",notes = "",httpMethod = "POST")
    @RequestMapping(value = "/addMoniTime")
    public JsonResult<MyyMonitortime> addMoniTime(@RequestBody MyyMonitortimeIn myyMonitortimeIn){
        JsonResult jr = null;

        try {
            MyyMonitorday myyMonitorday = myyCamwarnService.findById_md(myyMonitortimeIn.getDayid());

            List<MyyMonitortime> myyMonitortimes = new ArrayList<>();
            for (int i=0;i<myyMonitortimeIn.getTimes().size();i++){
                MyyMonitortime myyMonitortime = new MyyMonitortime();
                myyMonitortime.setDayid(myyMonitortimeIn.getDayid());
                myyMonitortime.setTime(myyMonitortimeIn.getTimes().get(i).toString());
//                myyCamwarnService.save_mt(myyMonitortime);
                myyMonitortimes.add(myyMonitortime);
            }
            myyCamwarnService.saveAll_mt(myyMonitortimes);

            MyyCamwarnconfig myyCamwarnconfig = myyCamwarnService.findById(myyMonitorday.getCamwarnid());

            JsonResult<MyyCamera> cameraJsonResult = cameraClient.findById(myyCamwarnconfig.getCameraid());
            if (cameraJsonResult.getCode() == 0 && cameraJsonResult.getData() != null) {
                JsonResult<MyyAreabottom> areabottom = baseServerClient.findAreabottom(cameraJsonResult.getData().getAreabottomid());
                JsonResult<MyyAreatwo> areatwo = baseServerClient.findAreatwo(areabottom.getData().getAreatwoid());
                JsonResult<MyyAreaone> areaone = baseServerClient.findAreaone(areatwo.getData().getAreaoneid());
                delayCheck(areaone.getData().getDistrictid());
            }
            jr = new JsonResult<>(0);
        }catch (Exception e){
            jr=  new JsonResult(1,e.getMessage().toString());
        }
        return jr;
    }

    @ApiOperation(value = "删除监控时间段信息",notes = "id",httpMethod = "POST")
    @RequestMapping(value = "/deteleMoniTime")
    public JsonResult deteleMoniTime(@RequestParam(value = "id") long id){
        JsonResult jsonResult = null;
        try{
            MyyMonitortime myyMonitortime = myyCamwarnService.findById_mt(id);
            myyCamwarnService.delete_mt(id);

            MyyMonitorday myyMonitorday = myyCamwarnService.findById_md(myyMonitortime.getDayid());

            MyyCamwarnconfig myyCamwarnconfig = myyCamwarnService.findById(myyMonitorday.getCamwarnid());

            JsonResult<MyyCamera> cameraJsonResult = cameraClient.findById(myyCamwarnconfig.getCameraid());
            if (cameraJsonResult.getCode() == 0 && cameraJsonResult.getData() != null) {
                JsonResult<MyyAreabottom> areabottom = baseServerClient.findAreabottom(cameraJsonResult.getData().getAreabottomid());
                JsonResult<MyyAreatwo> areatwo = baseServerClient.findAreatwo(areabottom.getData().getAreatwoid());
                JsonResult<MyyAreaone> areaone = baseServerClient.findAreaone(areatwo.getData().getAreaoneid());
                delayCheck(areaone.getData().getDistrictid());
            }

            jsonResult = new JsonResult(0,"操作成功！");
        }catch (Exception e){
            jsonResult = new JsonResult(1,e.getMessage());
        }
        return jsonResult;
    }
    @ApiOperation(value = "删除监控时间段信息",notes = "dayid",httpMethod = "POST")
    @RequestMapping(value = "/deteleMoniTimes")
    public JsonResult deteleMoniTimes(@RequestParam(value = "dayid") long dayid){
        JsonResult jsonResult = null;
        try{
            myyCamwarnService.delete_mts(dayid);

            MyyMonitorday myyMonitorday = myyCamwarnService.findById_md(dayid);

            MyyCamwarnconfig myyCamwarnconfig = myyCamwarnService.findById(myyMonitorday.getCamwarnid());

            JsonResult<MyyCamera> cameraJsonResult = cameraClient.findById(myyCamwarnconfig.getCameraid());
            if (cameraJsonResult.getCode() == 0 && cameraJsonResult.getData() != null) {
                JsonResult<MyyAreabottom> areabottom = baseServerClient.findAreabottom(cameraJsonResult.getData().getAreabottomid());
                JsonResult<MyyAreatwo> areatwo = baseServerClient.findAreatwo(areabottom.getData().getAreatwoid());
                JsonResult<MyyAreaone> areaone = baseServerClient.findAreaone(areatwo.getData().getAreaoneid());
                delayCheck(areaone.getData().getDistrictid());
            }
            jsonResult = new JsonResult(0,"操作成功！");
        }catch (Exception e){
            jsonResult = new JsonResult(1,e.getMessage());
        }
        return jsonResult;
    }

    @ApiOperation(value = "查询监控时间段信息",notes = "id",httpMethod = "POST")
    @RequestMapping(value = "/findMoniTime")
    public JsonResult<MyyMonitortime> findById_mt(@RequestParam(value = "id") long id){
        JsonResult<MyyMonitortime> jsonResult = null;
        try {
            MyyMonitortime obj = myyCamwarnService.findById_mt(id);
            jsonResult = new JsonResult<>(0,obj);
        }catch (Exception e){
            jsonResult = new JsonResult<>(1,e.getMessage());
        }
        return jsonResult;
    }

    @ApiOperation(value = "查询监控时间段信息",notes = "dayid",httpMethod = "POST")
    @RequestMapping(value = "/findMoniTimes")
    public JsonResult<List<MyyMonitortime>> findMoniDays_mt(@RequestParam(value = "id") long id){
        JsonResult<List<MyyMonitortime>> jsonResult = null;
        try {
            List<MyyMonitortime> objList = myyCamwarnService.findByDayid_mt(id);
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

    @ApiOperation(value = "快速复制",notes = "",httpMethod = "POST")
    @RequestMapping(value = "/addN")
    public JsonResult addN(@RequestBody AddNIn addNIn){
        JsonResult jsonResult = null;
        try {
            MyyMonitorday myyMonitorday1 = myyCamwarnService.findById_md(addNIn.getDayid());
            for (int i=0;i<addNIn.getDays().size();i++){
                MyyMonitorday myyMonitorday2 = myyCamwarnService.findByCamwarnidAndDay_md(myyMonitorday1.getCamwarnid(),addNIn.getDays().get(i).toString());
                if (myyMonitorday2 != null){
                    deteleMoniDay(myyMonitorday2.getId());
                }

                MyyMonitorday myyMonitorday = new MyyMonitorday();
                myyMonitorday.setCamwarnid(myyMonitorday1.getCamwarnid());
                myyMonitorday.setDay(addNIn.getDays().get(i).toString());
                myyMonitorday = myyCamwarnService.save_md(myyMonitorday);

                List<MyyMonitortime> myyMonitortimes = new ArrayList<>();
                List<MyyMonitortime> objList = myyCamwarnService.findByDayid_mt(myyMonitorday1.getId());
                for (int j=0;j<objList.size();j++){
                    MyyMonitortime myyMonitortime = new MyyMonitortime();
                    myyMonitortime.setDayid(myyMonitorday.getId());
                    myyMonitortime.setTime(objList.get(j).getTime());
//                    myyCamwarnService.save_mt(myyMonitortime);
                    myyMonitortimes.add(myyMonitortime);
                }
                myyCamwarnService.saveAll_mt(myyMonitortimes);
            }


            MyyCamwarnconfig myyCamwarnconfig = myyCamwarnService.findById(myyMonitorday1.getCamwarnid());

            JsonResult<MyyCamera> cameraJsonResult = cameraClient.findById(myyCamwarnconfig.getCameraid());
            if (cameraJsonResult.getCode() == 0 && cameraJsonResult.getData() != null) {
                JsonResult<MyyAreabottom> areabottom = baseServerClient.findAreabottom(cameraJsonResult.getData().getAreabottomid());
                JsonResult<MyyAreatwo> areatwo = baseServerClient.findAreatwo(areabottom.getData().getAreatwoid());
                JsonResult<MyyAreaone> areaone = baseServerClient.findAreaone(areatwo.getData().getAreaoneid());
                delayCheck(areaone.getData().getDistrictid());
            }
            jsonResult = new JsonResult<>(0);
        }catch (Exception e){
            jsonResult = new JsonResult<>(1,e.getMessage());
        }
        return jsonResult;
    }


    public JsonResult delayCheck(long orgid) {
        JSONObject map1 = new JSONObject();

        try{
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


                List<MyyCamwarnconfig> myyCamwarnconfigs = myyCamwarnService.findByCameraid(camera.getId());

                HashMap<String, Object> detection = new HashMap<>();
                for (MyyCamwarnconfig myyCamwarnconfig : myyCamwarnconfigs){
                    HashMap<String, ArrayList<String>> week = new HashMap<>();
                    List<MyyMonitorday> dateList = myyCamwarnService.findByCamwarnid_md(myyCamwarnconfig.getId());
                    if (dateList.size() > 0) {
                        for (int j = 0; j < dateList.size(); j++) {
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

                            JsonResult<MyyWarntype> myyWarntypeJsonResult = baseServerClient.findWarnById(new FindAllIn(myyCamwarnconfig.getWarntypeid()));
                            detection.put(myyWarntypeJsonResult.getData().getWarnen(), warn);
//                            detection.put(myyCamwarnconfig.getWarntype(), warn);
                        }
                    }
                }

                map.put("detection", detection);
                if (tmap.get(String.valueOf(camera.getAreabottomid())) != null) {
                    room = tmap.get(String.valueOf(camera.getAreabottomid()));
                } else {
                    room = new HashMap<>();
                }
                room.put(camera.getIp()+":"+camera.getChannel(), map);
                tmap.put(String.valueOf(camera.getAreabottomid()), room);

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

            FindAllAreaIn findAllAreaIn = new FindAllAreaIn();
            findAllAreaIn.setOrgId(orgid);
            JsonResult<List<MyyAreaone>> alllist = baseServerClient.findAreaones(findAllAreaIn);
            roomlist.clear();
            for (int i = 0; i < alllist.getData().size(); i++) {
                MyyAreaone lab = alllist.getData().get(i);
                roomlist.add(String.valueOf(lab.getId()));
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

}
