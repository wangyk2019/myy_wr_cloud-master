package com.moyuaninfo.waterinfo.controller;



import com.moyuaninfo.waterinfo.model.JsonResult;
import com.moyuaninfo.waterinfo.model.MyySuggest;
import com.moyuaninfo.waterinfo.service.SuggestService;
import com.moyuaninfo.waterinfo.utils.GitNumberUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//web端展示问题咨询图表
@Api(value = "web问题咨询查询",tags="SelectSuggest")
@RestController
//@CrossOrigin
public class SelectSuggestController {

    @Autowired
    private SuggestService suggestService;
    /**
     * 1.今日所有问题
     * 2.今日之前所有未处理问题
     * 3.今日之前所有已处理问题
     */
    @ApiOperation(value = "问题咨询饼图", notes = "问题咨询饼图")
    @RequestMapping(value = "/selectSuggestByTimeAndStatus", method = RequestMethod.POST)
    public JsonResult<Map<String,Integer>> selectSuggestByTimeAndStatus(MyySuggest myySuggest){
        //初始化返回值
        JsonResult jsrt = null;
        try {
            //初始化时间参数
            Date startsDate = null;
            Date endDate = null;
            Map<String,Integer> stringIntegerMap = new HashMap<>();
            //查询今日所有问题咨询个数
            Integer today = suggestService.countSuggest(myySuggest, startsDate, endDate);
            stringIntegerMap.put("today",today);
            //获取今日时间
            startsDate = GitNumberUtil.getNewDate();
            endDate = GitNumberUtil.getOldDate();
            //查询今日之前时间已完成
            Integer beforeProcessing = suggestService.countSuggest(myySuggest, startsDate, endDate);
            stringIntegerMap.put("beforeProcessing",beforeProcessing);
            //查询今日之前时间未完成
            Integer beforeProcessingNot = suggestService.countSuggest(myySuggest, startsDate, endDate);
            stringIntegerMap.put("beforeProcessingNot",beforeProcessingNot);
            jsrt = new JsonResult(0,stringIntegerMap);
        }catch (Exception e){
            jsrt = new JsonResult(1,e.getMessage().toString());
        }
        return jsrt;
    }

}
