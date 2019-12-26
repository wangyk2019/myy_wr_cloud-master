package com.moyuaninfo.waterinfo.service.impl;

import com.moyuaninfo.waterinfo.common.ContinueReadDev;
import com.moyuaninfo.waterinfo.common.Global;
import com.moyuaninfo.waterinfo.dao.WaterInfoMapper;
import com.moyuaninfo.waterinfo.service.WaterInfoService;
import com.moyuaninfo.waterinfo.utils.CRC16;
import com.moyuaninfo.waterinfo.utils.DateUtils;
import com.moyuaninfo.waterinfo.utils.HexStrToBytes;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName WaterInfoServiceImpl
 * @Description TODO
 * @Author zhaoGq
 * @Date 2019/12/2 14:43
 * @Version 1.0
 **/
@Service
public class WaterInfoServiceImpl implements WaterInfoService {

    @Autowired
    private WaterInfoMapper waterInfoMapper;

    @Autowired
    private HexStrToBytes hexStrToBytes;

    @Autowired
    private CRC16 crc16;

    @Autowired
    private ContinueReadDev continueReadDev;

    /*
     * @Description 引用db_waterinfo库中的表结构(王工设计)
     * @Author zhaoGq
     * @Date 2019/12/12 10:38
     * @Param
     * @Return void
     **/
//    @Override
    // @Scheduled(cron = "0 0 9,19 * * ?") // 每天的：AM:9，PM:19
    // @Scheduled(cron = "0 0 */1 * * ?") // 每隔1小时请求一次
//    public void saveWaterInfo() {
//        List<Map<String, Object>> list = new ArrayList<>();
//        for (String requestCommand : Global.WATER_QUALITY_TYPE_REQUEST) {
//            String requestMessage = getRequestMessage(requestCommand);
//            try {
//                Thread.sleep(Global.REQUEST_RESPONSE_TIME_DIFFERENCE);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            Map<String, Object> result = getWaterInfoResult(requestMessage);
//            list.add(result);
//            try {
//                Thread.sleep(Global.REQUEST_RESPONSE_TIME_DIFFERENCE);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        waterInfoMapper.saveWaterInfo(list);
//    }

    /*
     * @Description 引用db_river库中的表结构(尹工设计)
     * @Author zhaoGq
     * @Date 2019/12/18 8:53
     * @Param
     * @Return void
     **/
    @Override
    // @Scheduled(cron = "0 0 9,19 * * ?") // 每天的：AM:9，PM:19
    // @Scheduled(cron = "0 0 */1 * * ?") // 每隔1小时请求一次
     @Scheduled(cron = "0 44 16 * * ?") // 10:50触发
    public void saveWaterInfo() {
        List<Map<String, Object>> list = new ArrayList<>();
        // 通过区域河道id查询所有的设备列表(比如：马溪下01、02号设备等)
        List<Map<String, Object>> deviceList = waterInfoMapper.getDeviceListByAreaoneId(Global.AREAONE_ID);
        for (Map<String, Object> device : deviceList) {
            Integer deviceListId = Integer.valueOf(device.get("deviceListId").toString());
            // 查询所有的设备列表(比如：马溪下01号设备下：PH、氨氮等设备)
            List<Map<String, Object>> deviceItemList = waterInfoMapper.getDeviceItemListByAllot(deviceListId);
            Map<String, Object> mapAllot = new HashMap<>();
            mapAllot.put("deviceListId", deviceListId);
            for (Map<String, Object> deviceItemInfo : deviceItemList) {
                String requestCommand = deviceItemInfo.get("comm").toString();
                String requestMessage = getRequestMessage(requestCommand);
//                try {
//                    Thread.sleep(Global.REQUEST_RESPONSE_TIME_DIFFERENCE);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                // 接收响应,获取水质信息结果
                String responseMessage = continueReadDev.getResponseMessage();
                String formatResult = "0";
                if (StringUtils.isNotBlank(responseMessage)) {
                    formatResult = getFormatResult(responseMessage).toString();
                }
                String detectObject = deviceItemInfo.get("detectObject").toString();
                mapAllot.put("effective", 1);
                mapAllot.put("createtime", DateUtils.getNow());
                mapAllot.put("updatetime", DateUtils.getNow());
                mapAllot = getWaterCategoryInfoResult(mapAllot, detectObject, formatResult);
//                try {
//                    Thread.sleep(Global.REQUEST_RESPONSE_TIME_DIFFERENCE);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
            list.add(mapAllot);
        }
        waterInfoMapper.saveWaterInfo(list);
    }

    /*
     * @Description //TODO
     * @Author zhaoGq
     * @Date 2019/12/12 13:53
     * @Param
     * @param param
     * @Return byte[]
     **/
    private String getRequestMessage (String param) {
//        String paramStr = hexStrToBytes.toHexString(param);
        // 计算crc16校验码
        String crc16Result = crc16.getCRC(param);
        // 请求信息字符
        String requestStr = param + crc16Result;
        byte[] byteResult = hexStrToBytes.hexStrToBinaryStr(requestStr);
        // 发送请求
        continueReadDev.sendRequestMessage(byteResult);
        return param;
    }

    /*
     * @Description //TODO
     * @Author zhaoGq
     * @Date 2019/12/13 17:59
     * @Param
     * @param param
     * @Return java.lang.Float
     **/
    private Float getFormatResult (String param) {
        String formatParam = param.replaceAll("\\s*", "");
        if (formatParam.length() > 6) {
//            String hexStr = formatParam.substring(6, formatParam.length());
            String hexStr = formatParam.substring(6, 14);
            Float checkResult = Float.intBitsToFloat(Integer.valueOf(hexStr, 16));
            return checkResult;
        }else {
            Float checkResult = Float.intBitsToFloat(Integer.valueOf("0", 16));
            return checkResult;
        }
    }

    /*
     * @Description //TODO
     * @Author zhaoGq
     * @Date 2019/12/14 16:14
     * @Param
     * @param requestMessageStr
     * @Return java.util.Map<java.lang.String,java.lang.Object>
     **/
    private Map<String, Object> getWaterInfoResult (String requestMessageStr) {
        // 通过设备地址获取设备信息
        String deviceAddr = requestMessageStr.substring(0, 2) + requestMessageStr.substring(6, 8);
        Map<String, Object> waterInfo = waterInfoMapper.getDeviceInfoByDeviceAddr(deviceAddr);
        // 接收响应
        String responseMessage = continueReadDev.getResponseMessage();
        Float formatResult = getFormatResult(responseMessage);
        waterInfo.put("formatResult", formatResult);
        waterInfo.put("state", "1");
        waterInfo.put("createtime", DateUtils.getNow());
        waterInfo.put("updatetime", DateUtils.getNow());
        return waterInfo;
    }

    /*
     * @Description //TODO
     * @Author zhaoGq
     * @Date 2019/12/18 14:59
     * @Param
     * @param mapParam
     * @param detectObject
     * @param formatResult
     * @Return java.util.Map<java.lang.String,java.lang.Object>
     **/
    private Map<String, Object> getWaterCategoryInfoResult(Map<String, Object> mapParam, String detectObject, String formatResult) {
        switch (detectObject) {
            case Global.NUMBERS_PH:
                mapParam.put("digitalPHvalue", formatResult);
                return mapParam;
            case Global.NUMBERS_TEMPERATURE:
                mapParam.put("temperatureValue", formatResult);
                return mapParam;
            case Global.NUMBERS_DIMNESS:
                mapParam.put("digitalTurbidityValue", formatResult);
                return mapParam;
            case Global.NUMBERS_DISSOLVED_OXYGEN:
                mapParam.put("dissolvedOxygenValue", formatResult);
                return mapParam;
            case Global.NUMBERS_AMMONIA_NITROGEN:
                mapParam.put("ammonianTrogenValue", formatResult);
                return mapParam;
            case Global.NUMBERS_TOTAL_PHOSPHORUS:
                mapParam.put("totalPhosphorusValue", formatResult);
                return mapParam;
            case Global.NUMBERS_PERMANGANATE:
                mapParam.put("permanganateValue", formatResult);
                return mapParam;
            default:
                return mapParam;
        }
    }

    /*
     * @Description //TODO
     * @Author zhaoGq
     * @Date 2019/12/2 15:18
     * @Param
     * @Return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @Override
    public Map<String, Object> getWaterQualityInfoListByTime() {
        List<Map<String, Object>> waterQualityInfoList = waterInfoMapper.getWaterQualityInfoListByTime();
        Map<String, Object> mapResult = new HashMap<>();
        List<Map<String, Object>> currentDayList = new ArrayList<>();
        List<Map<String, Object>> oneDayBeforeList = new ArrayList<>();
        List<Map<String, Object>> twoDayBeforeList = new ArrayList<>();
        List<Map<String, Object>> threeDayBeforeList = new ArrayList<>();
        List<Map<String, Object>> oneWeekBeforeList = new ArrayList<>();
        for (Map<String, Object> map : waterQualityInfoList) {
            String timeType = map.get("timeType").toString();
            switch(timeType){
                case "currentDay":
                    currentDayList.add(map);
                    break;
                case "oneDayBefore":
                    oneDayBeforeList.add(map);
                    break;
                case "twoDayBefore":
                    twoDayBeforeList.add(map);
                    break;
                case "threeDayBefore":
                    threeDayBeforeList.add(map);
                    break;
                case "oneWeekBefore":
                    oneWeekBeforeList.add(map);
                    break;
                default:
                    break;
            }
        }
        mapResult.put("currentDay",currentDayList);
        mapResult.put("oneDayBefore",oneDayBeforeList);
        mapResult.put("twoDayBefore",twoDayBeforeList);
        mapResult.put("threeDayBefore",threeDayBeforeList);
        mapResult.put("oneWeekBefore",oneWeekBeforeList);
        return mapResult;
    }

    /*
     * @Description //TODO
     * @Author zhaoGq
     * @Date 2019/12/2 16:50
     * @Param
     * @Return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    @Override
    public List<Map<String, Object>> getWaterQualityInfoListByNewest() {
        return waterInfoMapper.getWaterQualityInfoListByNewest();
    }


}
