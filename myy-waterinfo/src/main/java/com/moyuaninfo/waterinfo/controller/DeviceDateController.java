package com.moyuaninfo.waterinfo.controller;


import com.moyuaninfo.waterinfo.model.JsonResult;
import com.moyuaninfo.waterinfo.model.MyyDeviceData;
import com.moyuaninfo.waterinfo.service.DeviceDateService;
import com.moyuaninfo.waterinfo.utils.DateUtils;
import com.moyuaninfo.waterinfo.utils.GitNumberUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

@Api(value = "水质参数信息显示",tags="DeviceDateController")
@RestController
//@CrossOrigin
//@RequestMapping("/api/deviceDate")
public class DeviceDateController {

    @Autowired
    private DeviceDateService deviceDateService;

//参数标准栏接口（标准参数以及当前参数）selectDeviceDataNew
    /**
     * 1.通过区域id查询区域下所有设备分配id
     * 2.通过设备分配id查询先关的参数信息（当天最新）
     * 3.返回相关数据给前端
     * 参数：
     *      value ：0.66  参数具体值
     *      unit ：mg/L 单位
     *      comment ：溶解氧  说明
     * */
    @ApiOperation(value = "查询当前时间水质信息参数", notes = "查询当前时间水质信息参数")
    @RequestMapping(value = "/selectDeviceDataNew", method = RequestMethod.POST)
    public JsonResult<MyyDeviceData> selectDeviceDataNew(int areaoneId) {
        //数据封装
        JsonResult jr = null;


        try {
            //通过区域id查询当前水质参数
            MyyDeviceData myyDeviceData = deviceDateService.selectDeviceDataNew(areaoneId);

            jr = new JsonResult(0,myyDeviceData);
        }catch (Exception e){
            jr = new JsonResult(1,e.getMessage().toString());
        }


        return jr;
    }


    @ApiOperation(value = "查询当前时间水质信息参数", notes = "查询当前时间水质信息参数")
    @RequestMapping(value = "/selectDeviceDataNewO", method = RequestMethod.POST)
    public JsonResult<Map<String,MyyDeviceData>> selectDeviceDataNewO(int areaoneId) {
        //数据封装
        JsonResult jr = null;

        Map<String,MyyDeviceData> deviceDataMap = new HashMap<>();
        try {
            //通过区域id查询当前水质参数
            MyyDeviceData myyDeviceData = deviceDateService.selectDeviceDataNew(areaoneId);
            MyyDeviceData myyDeviceDataN = new MyyDeviceData();
            //标准参数值
            myyDeviceDataN.setDigitalPhValue("6");
            myyDeviceDataN.setTemperatureValue("12");
            myyDeviceDataN.setDigitalTurbidityValue("1");
            myyDeviceDataN.setDissolvedOxygenValue("40");
            myyDeviceDataN.setAmmoniaNTrogenValue("4");
            myyDeviceDataN.setTotalPhosphorusValue("0.3");
            myyDeviceDataN.setPermanganateValue("15");
            //数据封装
            deviceDataMap.put("standard",myyDeviceDataN);
            deviceDataMap.put("today",myyDeviceData);
            jr = new JsonResult(0,deviceDataMap);
        }catch (Exception e){
            jr = new JsonResult(1,e.getMessage().toString());
        }


        return jr;
    }


    //参数指标栏接口（今天，一天前，两天前，三天前，一周前;8-9点）selectDeviceDataByTime
    /**
     * 1.通过区域id查询区域下所有设备id
     * 2.通过设备id查询先关的参数信息（1.当天 2.一天前 3.两天前 4.三天前 5.一周前）
     * 3.返回相关数据给前端
     *  参数：
     *          value ：0.66  （参数具体值）
     *          unit ：mg/L   （单位 ）
     *          comment ：溶解氧  （说明）
     *          timeString ：今天   （时间）
     * */
    @ApiOperation(value = "查询参数指标栏接口", notes = "查询参数指标栏接口")
    @RequestMapping(value = "/selectDeviceDataByTime", method = RequestMethod.POST)
    public JsonResult<List<MyyDeviceData>> selectDeviceDataByTime(int areaoneId) {
        System.out.println("222222222222222222");
        //初始化返回参数
        JsonResult jr = null;

        //创建回显的List<MyyDeviceData>
        List<MyyDeviceData> selectMyyDeviceDataList = new ArrayList<>();

        try {

            //初始化空时间参数
            Date startTime = GitNumberUtil.getNineDate();//今天上午9点
            Date endTime = GitNumberUtil.getZeroDate();//今天上午10点

            //通过方法获取随机数据
            //MyyDeviceData myyDeviceDataNow = this.selectDateDataRandom(areaoneId, startTime, endTime);//今天
            //selectMyyDeviceDataList.add(myyDeviceDataNow);//存数据
            //昨天时间
            Date startTimeO = GitNumberUtil.getSubtractDay(startTime,1);
            Date endTimeO = GitNumberUtil.getSubtractDay(endTime,1);
            //通过方法获取随机数据
            MyyDeviceData myyDeviceDatayesterday = this.selectDateDataRandom(areaoneId, startTimeO, endTimeO);
            selectMyyDeviceDataList.add(myyDeviceDatayesterday);

            //前两天
            Date startTimeT = GitNumberUtil.getSubtractDay(startTime,2);
            Date endTimeT = GitNumberUtil.getSubtractDay(endTime,2);
            //通过方法获取随机数据
            MyyDeviceData myyDeviceDatayesterday2 = this.selectDateDataRandom(areaoneId, startTimeT, endTimeT);
            selectMyyDeviceDataList.add(myyDeviceDatayesterday2);

            //前3天
            Date startTimeTR = GitNumberUtil.getSubtractDay(startTime,3);
            Date endTimeTR = GitNumberUtil.getSubtractDay(endTime,3);
            //通过方法获取随机数据
            MyyDeviceData myyDeviceDatayesterday3 = this.selectDateDataRandom(areaoneId, startTimeTR, endTimeTR);//昨天
            selectMyyDeviceDataList.add(myyDeviceDatayesterday3);

            //前四天
            Date startTimeF = GitNumberUtil.getSubtractDay(startTime,4);
            Date endTimeF = GitNumberUtil.getSubtractDay(endTime,4);
            //通过方法获取随机数据
            MyyDeviceData myyDeviceDatayesterday4 = this.selectDateDataRandom(areaoneId, startTimeF, endTimeF);//昨天
            selectMyyDeviceDataList.add(myyDeviceDatayesterday4);

            //前5天
            Date startTimeFI = GitNumberUtil.getSubtractDay(startTime,5);
            Date endTimeFI = GitNumberUtil.getSubtractDay(endTime,5);
            //通过方法获取随机数据
            MyyDeviceData myyDeviceDatayesterday5 = this.selectDateDataRandom(areaoneId, startTimeFI, endTimeFI);//昨天
            selectMyyDeviceDataList.add(myyDeviceDatayesterday5);

            //数据封装
            jr = new JsonResult(0,selectMyyDeviceDataList);
        }catch (Exception e){
            jr = new JsonResult(1,e.getMessage().toString());
        }
        //返回
        return jr;
    }


    public MyyDeviceData selectDateDataRandom(int areaoneId,Date startTime,Date endTime){
        System.out.println("111111111111111111111");
        //查询方法
        List<MyyDeviceData> myyDeviceDataList = deviceDateService.selectDeviceDataByTime(areaoneId, startTime, endTime);
        //获取结果数据条数
        int size = myyDeviceDataList.size();
        //生成0-size之间的随机数（不包含size）
        Integer number = GitNumberUtil.getRandomNumber(size);
        //获取随机数据
        MyyDeviceData myyDeviceData = myyDeviceDataList.get(0);
        return  myyDeviceData;
    }

}
