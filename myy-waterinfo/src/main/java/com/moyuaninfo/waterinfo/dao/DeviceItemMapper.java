package com.moyuaninfo.waterinfo.dao;

import com.moyuaninfo.waterinfo.model.MyyDeviceItem;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @ClassName DeviceItemMapper
 * @Description TODO
 * @Author zhaoGq
 * @Date 2019/12/2 10:06
 * @Version 1.0
 **/
@Repository("deviceItemMapper")
public interface DeviceItemMapper {

    /*
     * @Description //TODO
     * @Author zhaoGq
     * @Date 2019/12/2 13:09
     * @Param myyDeviceItem
     * @Return
     **/
    int addDeviceItem(MyyDeviceItem myyDeviceItem);

    /*
     * @Description //TODO
     * @Author zhaoGq
     * @Date 2019/12/2 13:31
     * @Param
     * @param myyDeviceItem
     * @Return int
     **/
    int updateDeviceItemById(MyyDeviceItem myyDeviceItem);

    /*
     * @Description //TODO
     * @Author zhaoGq
     * @Date 2019/12/2 13:41
     * @Param
     * @param id
     * @Return int
     **/
    int deleteDeviceItemById(Integer id);

    /*
     * @Description //TODO
     * @Author zhaoGq
     * @Date 2019/12/2 14:03
     * @Param
     * @param mapParam
     * @Return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    List<Map<String, Object>> getDeviceItemListByDistrictAndRiver(Map<String, Object> mapParam);

}
