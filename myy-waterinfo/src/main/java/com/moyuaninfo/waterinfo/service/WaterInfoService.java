package com.moyuaninfo.waterinfo.service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName WaterInfoService
 * @Description TODO
 * @Author zhaoGq
 * @Date 2019/12/2 14:40
 * @Version 1.0
 **/
public interface WaterInfoService {

    /*
     * @Description //TODO
     * @Author zhaoGq
     * @Date 2019/12/12 10:37
     * @Param
     * @Return
     **/
    void saveWaterInfo();

    /*
     * @Description //TODO
     * @Author zhaoGq
     * @Date 2019/12/2 15:17
     * @Param
     * @Return java.util.Map<java.lang.String,java.lang.Object>
     **/
    Map<String, Object> getWaterQualityInfoListByTime();

    /*
     * @Description //TODO
     * @Author zhaoGq
     * @Date 2019/12/2 16:49
     * @Param
     * @Return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    List<Map<String, Object>> getWaterQualityInfoListByNewest();
}
