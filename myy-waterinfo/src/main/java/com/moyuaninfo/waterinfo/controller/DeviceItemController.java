package com.moyuaninfo.waterinfo.controller;

import com.moyuaninfo.waterinfo.model.JsonResult;
import com.moyuaninfo.waterinfo.model.MyyDeviceData;
import com.moyuaninfo.waterinfo.model.MyyDeviceItem;
import com.moyuaninfo.waterinfo.model.MyyDeviceList;
import com.moyuaninfo.waterinfo.service.DeviceItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;


@Api(value = "设备信息",tags="DeviceItemController")
@RestController
//@CrossOrigin
//@RequestMapping("/api/deviceItem")
public class DeviceItemController {

    @Autowired
    private DeviceItemService deviceItemService;


    @ApiOperation(value = "查询水质设备信息", notes = "查询水质设备信息")
    @RequestMapping(value = "/selectDeviceItemByAreaoneId", method = RequestMethod.POST)
    public JsonResult<Map<String ,List<MyyDeviceItem>>>selectDeviceItemByAreaoneId(int areaoneId) {
        //初始化返回参数
        JsonResult jr = null;

        try {
            //创建Data数据填充Map
            Map<String ,List<MyyDeviceItem>> MyyDeviceItemMap = new HashMap<>();
            //通过名字区分机器查询
            List<MyyDeviceItem> MyyDeviceItemList1 = deviceItemService.selectDeviceItemByAreaoneId(areaoneId,"总磷");
            List<MyyDeviceItem> MyyDeviceItemList2 = deviceItemService.selectDeviceItemByAreaoneId(areaoneId,"ph");
            List<MyyDeviceItem> MyyDeviceItemList3 = deviceItemService.selectDeviceItemByAreaoneId(areaoneId,"浊度");
            List<MyyDeviceItem> MyyDeviceItemList4 = deviceItemService.selectDeviceItemByAreaoneId(areaoneId,"溶氧");
            List<MyyDeviceItem> MyyDeviceItemList5 = deviceItemService.selectDeviceItemByAreaoneId(areaoneId,"氨氮");
            List<MyyDeviceItem> MyyDeviceItemList6 = deviceItemService.selectDeviceItemByAreaoneId(areaoneId,"温度");
            List<MyyDeviceItem> MyyDeviceItemList7 = deviceItemService.selectDeviceItemByAreaoneId(areaoneId,"高锰酸盐");
            //数据填充
            MyyDeviceItemMap.put("TP",MyyDeviceItemList1);
            MyyDeviceItemMap.put("PH",MyyDeviceItemList2);
            MyyDeviceItemMap.put("Transp",MyyDeviceItemList3);
            MyyDeviceItemMap.put("DO",MyyDeviceItemList4);
                MyyDeviceItemMap.put("NH3-N",MyyDeviceItemList5);
            MyyDeviceItemMap.put("℃",MyyDeviceItemList6);
            MyyDeviceItemMap.put("CODmn",MyyDeviceItemList7);

            //数据封装
            jr = new JsonResult(0,MyyDeviceItemMap);

        }catch (Exception e){
            jr = new JsonResult(1,e.getMessage().toString());
        }

        return jr;
    }
}
