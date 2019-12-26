package com.moyuaninfo.waterinfo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.moyuaninfo.waterinfo.model.JsonResult;
import com.moyuaninfo.waterinfo.model.MyyCityDistrict;
import com.moyuaninfo.waterinfo.service.CityDistrictService;
import com.moyuaninfo.waterinfo.utils.CityDistrictsUtils;
import com.moyuaninfo.waterinfo.utils.GitNumberUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * @ClassName CityDistrictController
 * @Description TODO
 * @Author 尹智伟
 * @Date 2019/12/12
 * @Version 1.0
 **/

@Api(value = "城市区域信息", tags = "CityDistrictController")
@RestController
//@CrossOrigin
//@RequestMapping("/api/deviceItem")
public class CityDistrictController {

    //服务器运行流量计算
    static Integer fllowNumber = 100234;
    @Autowired
    private CityDistrictService cityDistrictService;

    //全部区域信息查询
    @ApiOperation(value = "区域详细信息查询", notes = "区域详细信息查询")
    @RequestMapping(value = "/selectCityDistrict", method = RequestMethod.POST)
    public JsonResult< List<MyyCityDistrict>> selectCityDistrict() {
        //初始化返回参数
        JsonResult jr = null;

        try {
            //查所有区域基本信息并返回
            List<MyyCityDistrict> myyCityDistrictsList = cityDistrictService.selectCityDistrictAll();
            //封装数据
            jr = new JsonResult(0,myyCityDistrictsList);
        }catch (Exception e){
            //封装数据
            jr = new JsonResult(1,e.getMessage().toString());
        }

        return jr;
    }


    //通过区域行政代码，查询天气信息
    /**
     * 接口名称 :selectWeatherByAdcode
     *  1.通过区行政代码调用CityDistrictsUtils实现天气查询
     *  2.返回参数给前端
     *      参数：
     *      {"status":"1","count":"1","info":"OK","infocode":"10000","
     *      lives":[{"province":"北京","city":"东城区","adcode":"110101",
     *      "weather":"晴","temperature":"9","winddirection":"西","windpower":"≤3","humidity":"19",
     *      "reporttime":"2019-12-13 15:57:32"}]}
     * */
    @ApiOperation(value = "区域天气信息查询", notes = "区域天气信息查询")
    @RequestMapping(value = "/selectWeatherByAdcode", method = RequestMethod.POST)
    public JsonResult<String> selectWeatherByAdcode(int adcode){
        //初始化返回参数
        JsonResult jr = null;

        try {

        //使用区域行政编码拼接url
        String url = "https://restapi.amap.com/v3/weather/weatherInfo?"+"city="+adcode+"&key=5d4d1362daa1f6ce55f36a8ec860ce1f";
        //调用天气util返回数据
        CityDistrictsUtils cityDistrictsUtils = new CityDistrictsUtils();
        String request = cityDistrictsUtils.httpsRequest(url);
        JSONObject jsonObject = JSONObject.parseObject(request);
        //JsonResult<String> jsonResult = new JsonResult<>();
        //jsonResult.setData(request);
        //封装数据
            jr = new JsonResult(0,"天气",jsonObject);
        }catch (Exception e){
            jr = new JsonResult(1,e.getMessage().toString());
        }
        return jr;
    }

    //返回服务器运行时间
    @SneakyThrows
    @ApiOperation(value = "服务器运行时间", notes = "服务器运行时间")
    @RequestMapping(value = "/selectTimeNew", method = RequestMethod.POST)
    public JsonResult<Long> selectTimeNew() {
        //初始化返回参数
        JsonResult jr = null;

        try {
            //初始化时间
            DateFormat dateFormatime = new SimpleDateFormat("YYYY-HH-DD hh:mm:ss");
            Date oldTime = dateFormatime.parse("2019-12-18 01:01:01");
            Date newTime = new Date();
            //获取时间毫秒值
            long timeN = newTime.getTime();
            long timeNu = oldTime.getTime();
            //获取服务器运行时间（秒）
            long number = (timeN - timeNu)/1000;

            //封装数据
            jr = new JsonResult(0,number);
        }catch (Exception e){
            jr = new JsonResult(1,e.getMessage().toString());
        }
        return jr;
    }

    //返回服务器使用流量
    @ApiOperation(value = "服务器运行流量", notes = "服务器运行流量")
    @RequestMapping(value = "/selectFlowNumber", method = RequestMethod.POST)
    public JsonResult<Integer> selectFlowNumber() {
        //初始化返回参数
        JsonResult jr = null;

        try {
           //获取随机数
            Integer randomNumber = GitNumberUtil.getRandomNumber(10000);
            //流量总量加随机数
            fllowNumber += randomNumber;
            jr = new JsonResult(0,fllowNumber);
        }catch (Exception e){
            jr = new JsonResult(1,e.getMessage().toString());
        }
        return jr;
    }
}
