package com.moyuaninfo.cloud.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.moyuaninfo.cloud.VO.*;
import com.moyuaninfo.cloud.common.JsonResult;
import com.moyuaninfo.cloud.feignClient.BaseServerClient;
import com.moyuaninfo.cloud.feignClient.CameraClient;
import com.moyuaninfo.cloud.feignClient.LivePlayClient;
import com.moyuaninfo.cloud.pojo.*;
import com.moyuaninfo.cloud.service.XianqingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javafx.scene.Camera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(value = "/show")
@Api(value = "险情展示")
//@CrossOrigin
public class ShowController {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");

    //智慧水务驾驶舱显示的险情类型
    private static final String[] warntype = new String[]{"warninglevel","paddle","fishing","garbagefloat"};

    @Autowired
    XianqingService xianqingService;

    @Autowired
    CameraClient cameraClient;
    @Autowired
    BaseServerClient baseServerClient;
    @Autowired
    LivePlayClient livePlayClient;

    /**
     * 驾驶舱展示接口
     * 险情累积量
     * 只需参数orgId
     * @param findAllIn
     * @return
     */
    @ApiOperation(value="险情累积量")
    @PostMapping(value = "/accumulate")
    public JsonResult<WarnReferByTypeOut> accumulate(@RequestBody FindAllIn findAllIn){
        WarnReferByTypeOut warnReferByTypeOut =new WarnReferByTypeOut();
        int code = 0;
        String msg = "成功！";
        try {
            List<WarnType> warnList = new ArrayList<>();
            for(int j=0;j<warntype.length;j++){
                //根据组织id,险情类型，险情结果 查询险情数量
                Long ds = xianqingService.countAllByDistrictidAndWarntypeAndResult(findAllIn.getOrgId(),warntype[j]);

                //封装返回信息
                WarnType warnType = new WarnType();
                warnType.setNum(ds.intValue());
                warnType.setWarnname(findNameByWarnen(warntype[j]));
                warnList.add(warnType);
                warnReferByTypeOut.setDangerList(warnList);
            }
        } catch (Exception e) {
            code = 1;
            msg = "失败！"+e.getClass().getName() + ":" + e.getMessage();
        }
        return new JsonResult<>(code,msg,warnReferByTypeOut);
    }

    /**
     * 根据险情英文查询险情中文
     * @param warnen
     * @return
     */
    public String findNameByWarnen(String warnen){
        JsonResult<MyyWarntype> myyWarntypeJsonResult = baseServerClient.findWarntypeByWarnen(new FindAllIn(warnen));
        if (myyWarntypeJsonResult.getCode() ==0 && myyWarntypeJsonResult.getData()!= null){
            return myyWarntypeJsonResult.getData().getWarnname();
        }else {
            return "空";
        }
    }

    /**
     * 数据驾驶舱
     * 险情累积量(根据组织激活的险情显示最多4个)
     * 只需参数orgId
     * 目前没用到
     * @param findAllIn
     * @return
     */
    @ApiOperation(value="险情累积量(根据组织激活的险情显示最多4个)")
    @PostMapping(value = "/show_accumulate")
    public JsonResult<List<WarnType>> show_accumulate(@RequestBody FindAllIn findAllIn){
        JsonResult<List<WarnType>> jsonResult = null;

        //返回类型
        List<WarnType> warnList = new ArrayList<>();

        try {
            JsonResult<List<WarninfoOut>> warns = baseServerClient.findWarnsByOrgid(findAllIn);
            if (warns.getData()!= null){
                for (WarninfoOut warninfoOut : warns.getData()){
                    Long ds = xianqingService.countAllByDistrictidAndWarntypeAndResult(findAllIn.getOrgId(),warninfoOut.getWarnen());

                    WarnType warnType = new WarnType();
                    warnType.setNum(ds.intValue());
                    warnType.setWarnname(warninfoOut.getWarnname());
                    warnList.add(warnType);
                }
            }
            jsonResult = new JsonResult<>(0,warnList);
        } catch (Exception e) {
            jsonResult = new JsonResult<>(1,e.getMessage()+"");
        }
        return jsonResult;
    }

    /**
     * 数据驾驶舱
     * 最新险情
     * 只需参数orgId
     * @param findAllIn
     * @return
     */
    @ApiOperation(value="最新险情")
    @PostMapping(value = "/newDanger")
    public JsonResult<List<WarnMsg>> newDanger(@RequestBody FindAllIn findAllIn){
        JsonResult<List<WarnMsg>> jsrt = null;
        int code = 0;
        String msg = "成功！";

        List<WarnMsg> warnList =new ArrayList<>();
        try {
            // 查询组织id查出最新的10条险情
            List<MyyDangerInfo> dangers = xianqingService.findAllByColleage_show(findAllIn.getOrgId());
            for (MyyDangerInfo a : dangers){
                //封装返回信息
                WarnMsg warnMsg = new WarnMsg();
                warnMsg.setId(a.getId());
                warnMsg.setEventType(a.getName());
                warnMsg.setCtime(a.getTime());

                JsonResult<MyyCamera> camera = cameraClient.findById(a.getCameraid());
                if(camera.getCode()==0 && camera.getData()!= null){
                    warnMsg.setAddr(camera.getData().getLocation());
                    warnMsg.setCamName(camera.getData().getName());
                }else{
                    warnMsg.setAddr("");
                    warnMsg.setCamName("");
                }
                warnList.add(warnMsg);
            }
        } catch (Exception e) {
            code = 1;
            msg = "失败！"+e.getClass().getName() + ":" + e.getMessage();
        }
        return new JsonResult<>(code,msg,warnList);
    }

    /**
     * 数据驾驶舱
     * 一周险情累积量（查询当前时间的上周险情数据）
     * 只需参数orgId
     * @param findAllIn
     * @return
     */
    @ApiOperation(value="一周险情累积量")
    @PostMapping(value = "/accumulate_week")
    public JsonResult<List<DayWarns>> accumulate_week(@RequestBody FindAllIn findAllIn){
        int code = 0;
        String msg = "成功！";
        List<DayWarns> weekWarnsList = new ArrayList();
        try {
            for (int j=0;j<7;j++){
                //获取时间
                Calendar c = Calendar.getInstance();
                c.set(Calendar.DAY_OF_WEEK,2);
                c.add(Calendar.DATE, j-7);
                SimpleDateFormat startSdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
                SimpleDateFormat endSdf = new SimpleDateFormat("yyyy-MM-dd 23:59:59");

                //返回信息天类
                DayWarns dayWarns = new DayWarns();

                //返回信息列表
                List<WarnType> ls = new ArrayList();
                for (int i=0;i<warntype.length;i++) {
                    //返回信息险情类
                    WarnType warnType = new WarnType();
                    //根据组织id,险情类型，开始时间，结束时间 查询险情列表
                    List<MyyDangerInfo> jsrts = xianqingService.findAllByOrgidAndWarntypeAndTime(findAllIn.getOrgId(),
                            warntype[i], startSdf.format(c.getTime()), endSdf.format(c.getTime()));
                    warnType.setWarnname(findNameByWarnen(warntype[i]));
                    warnType.setNum(jsrts.size());

                    ls.add(warnType);
                }
                dayWarns.setDay(j+1);
                dayWarns.setWarnTypes(ls);

                weekWarnsList.add(dayWarns);
            }
        } catch (Exception e) {
            code = 1;
            msg = "失败！"+e.getClass().getName() + ":" + e.getMessage();
        }
        return new JsonResult<>(code,msg,weekWarnsList);
    }

    /**
     * 数据驾驶舱
     * 四个月险情累积量（查询当前月和前3个月的险情量）
     * 只需参数orgId
     * @param findAllIn
     * @return
     */
    @ApiOperation(value="四个月险情累积量")
    @PostMapping(value = "/accumulate_fourMonth")
    public JsonResult<List<MonthWarns>> accumulate_fourMonth(@RequestBody FindAllIn findAllIn){
        int code = 0;
        String msg = "成功！";
        List<MonthWarns> monthWarnsList = new ArrayList();
        try {
            ///获取上第四个月时间
            Calendar c5 = Calendar.getInstance();
            c5.add(Calendar.MONTH, -4);
            c5.set(c5.get(Calendar.YEAR), c5.get(Calendar.MONTH), c5.getActualMaximum(Calendar.DAY_OF_MONTH), 23, 59, 59);
            SimpleDateFormat startSdf5 = new SimpleDateFormat("yyyy-MM-01 00:00:00");
            SimpleDateFormat endSdf5 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            //获取上第四个月的险情量
            List<MyyDangerInfo> jsrts5 = xianqingService.findAllByOrgidAndTime(findAllIn.getOrgId(),startSdf5.format(c5.getTime()), endSdf5.format(c5.getTime()));
            int lastMonthnum = jsrts5.size();

            for (int j=0;j<4;j++){
                Calendar c = Calendar.getInstance();
                c.add(Calendar.MONTH, j-3);
                c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.getActualMaximum(Calendar.DAY_OF_MONTH), 23, 59, 59);
                SimpleDateFormat startSdf = new SimpleDateFormat("yyyy-MM-01 00:00:00");
                SimpleDateFormat endSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                //返回信息类
                MonthWarns monthWarns = new MonthWarns();
                List<WarnType> ls = new ArrayList();
                int dangerssum = 0;
                for (int i=0;i<warntype.length;i++) {
                    WarnType warnType = new WarnType();
                    //根据组织id,险情类型，开始时间，结束时间 查询险情列表
                    List<MyyDangerInfo> jsrts = xianqingService.findAllByOrgidAndWarntypeAndTime(findAllIn.getOrgId(),
                            warntype[i], startSdf.format(c.getTime()), endSdf.format(c.getTime()));
                    warnType.setWarnname(findNameByWarnen(warntype[i]));
                    warnType.setNum(jsrts.size());

                    ls.add(warnType);

                    dangerssum += jsrts.size();
                }
                monthWarns.setMonth(c.get(Calendar.MONTH)+1);
                monthWarns.setWarnTypes(ls);
                monthWarns.setMonthGrowthrate(String.format("%.1f", (float) (dangerssum-lastMonthnum) / (float) dangerssum * 100)+"%");

                monthWarnsList.add(monthWarns);

                lastMonthnum = dangerssum;
            }
        } catch (Exception e) {
            code = 1;
            msg = "失败！"+e.getClass().getName() + ":" + e.getMessage();
        }
        return new JsonResult<>(code,msg,monthWarnsList);
    }

    /**
     * 数据驾驶舱
     * 直播模块
     * 只需参数orgId
     * @param findAllIn
     * @return
     */
    @ApiOperation(value="摄像头信息查询,无地图直接进入直播")
    @PostMapping(value = "/online_show")
    public JsonResult online_show(@RequestBody FindAllIn findAllIn){
        JsonResult jsrt = new JsonResult(1,"错误...");
        Map map = new HashMap();
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray1 = new JSONArray();
        try {
            //查询摄像头
            JsonResult<List<MyyCamera>> cameras= cameraClient.findAllByDistrict(findAllIn.getOrgId());
            if(cameras.getCode() ==0 && cameras.getData() != null){
                for (MyyCamera c : cameras.getData()){
                    CameraVO cameraVO = new CameraVO();
                    cameraVO.setAreaid(c.getAreabottomid());
                    cameraVO.setCamIp(c.getIp());
                    cameraVO.setId(c.getId());
                    cameraVO.setDistrictid(findAllIn.getOrgId());
                    cameraVO.setChannel(c.getChannel());
                    //获取视频链接
                    JsonResult<String> stringJsonResult = livePlayClient.wsplay(cameraVO);
                    System.out.println(stringJsonResult);
                    if (stringJsonResult.getCode()==0){
                        jsonObject.put("vedioAddr",stringJsonResult.getData());

                        jsonArray1.add(jsonObject);
                        map.put("onlinedata",jsonArray1);
                        jsrt = new JsonResult(0,"", map);
                    }else {
                        jsonObject.put("vedioImg","");
                        jsonArray1.add(jsonObject);
                        map.put("onlinedata",jsonArray1);
                        jsrt = new JsonResult(3,"启动中...", map);
                    }
                    break;
                }
            }else{
                jsonObject.put("vedioAddr","");
                jsonObject.put("vedioImg","");
                jsonArray1.add(jsonObject);
                map.put("onlinedata",jsonArray1);
                jsrt = new JsonResult(2,"获取摄像头信息出错...", map);
            }
        } catch (Exception e) {
            jsonObject.put("vedioAddr","");
            jsonObject.put("vedioImg","");
            jsonArray1.add(jsonObject);
            map.put("onlinedata",jsonArray1);
            jsrt = new JsonResult(2,"连接超时",map);
        }
        return jsrt;
    }

    /**
     * 数据驾驶舱
     * 选择摄像头进入直播
     * 参数camid
     * @param camid
     * @return
     */
    @ApiOperation(value="进入直播")
    @PostMapping(value = "/to_online")
    public JsonResult to_online(@RequestParam(value = "camid") long camid){
        JsonResult jsrt = null;
        try {
            //查询摄像头信息
            JsonResult<MyyCamera> camera= cameraClient.findById(camid);
            JSONObject jsonObject = new JSONObject();
            if(camera.getCode() ==0){
                jsonObject.put("mpAddr",camera.getData().getLocation());
                jsonObject.put("vedioImg","");
                CameraVO cameraVO = new CameraVO();
                cameraVO.setAreaid(camera.getData().getAreabottomid());
                cameraVO.setCamIp(camera.getData().getIp());
                cameraVO.setId(camera.getData().getId());

                //查询组织信息
                JsonResult<MyyAreabottom> bottom = baseServerClient.findAreabottom(camera.getData().getAreabottomid());
                JsonResult<MyyAreatwo> two = baseServerClient.findAreatwo(bottom.getData().getAreatwoid());
                JsonResult<MyyAreaone> one = baseServerClient.findAreaone(two.getData().getAreaoneid());
                cameraVO.setDistrictid(one.getData().getDistrictid());
                cameraVO.setChannel(camera.getData().getChannel());

                //获取直播链接
                JsonResult<String> stringJsonResult = livePlayClient.wsplay(cameraVO);
                if (stringJsonResult.getCode()==0){
                    jsonObject.put("vedioAddr",stringJsonResult.getData());
                    jsrt = new JsonResult(0,"ok", jsonObject);
                }else {
                    jsonObject.put("vedioImg","");
                    jsrt = new JsonResult(3,"直播开启失败，请稍后重试...", jsonObject);
                }
            }else {
                jsrt = new JsonResult(1,"摄像头查询出错！", jsonObject);
            }
        } catch (Exception e) {
            jsrt = new JsonResult(1,e.getClass().getName() + ":" + e.getMessage());
        }
        return jsrt;
    }


}
