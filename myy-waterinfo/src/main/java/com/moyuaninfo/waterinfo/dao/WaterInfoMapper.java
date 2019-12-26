package com.moyuaninfo.waterinfo.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @ClassName WaterInfoMapper
 * @Description TODO
 * @Author zhaoGq
 * @Date 2019/12/2 14:44
 * @Version 1.0
 **/
@Repository("waterInfoMapper")
public interface WaterInfoMapper {

    /*
     * @Description //TODO
     * @Author zhaoGq
     * @Date 2019/12/14 8:58
     * @Param
     * @param deviceAddr
     * @Return java.util.Map<java.lang.String,java.lang.Object>
     **/
    Map<String, Object> getDeviceInfoByDeviceAddr(@Param("deviceAddr") String deviceAddr);

    /*
     * @Description //TODO
     * @Author zhaoGq
     * @Date 2019/12/18 17:19
     * @Param
     * @param areaoneId
     * @Return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    List<Map<String, Object>> getDeviceListByAreaoneId(Integer areaoneId);

    /*
     * @Description //TODO
     * @Author zhaoGq
     * @Date 2019/12/18 9:07
     * @Param
     * @param deviceListId
     * @Return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    List<Map<String, Object>> getDeviceItemListByAllot(Integer deviceListId);

    /*
     * @Description //TODO
     * @Author zhaoGq
     * @Date 2019/12/14 10:20
     * @Param
     * @param list
     * @Return int
     **/
    int saveWaterInfo(@Param("list") List<Map<String, Object>> list);

    /*
     * @Description //TODO
     * @Author zhaoGq
     * @Date 2019/12/2 15:20
     * @Param
     * @Return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    List<Map<String, Object>> getWaterQualityInfoListByTime();

    /*
     * @Description //TODO
     * @Author zhaoGq
     * @Date 2019/12/2 16:51
     * @Param
     * @Return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    List<Map<String, Object>> getWaterQualityInfoListByNewest();


}
