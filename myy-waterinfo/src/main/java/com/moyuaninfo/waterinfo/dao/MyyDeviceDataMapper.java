package com.moyuaninfo.waterinfo.dao;

import com.moyuaninfo.waterinfo.model.MyyDeviceData;
import com.moyuaninfo.waterinfo.model.MyyDeviceDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MyyDeviceDataMapper {
    int countByExample(MyyDeviceDataExample example);

    int deleteByExample(MyyDeviceDataExample example);

    int deleteByPrimaryKey(Integer deviceDataId);

    int insert(MyyDeviceData record);

    int insertSelective(MyyDeviceData record);

    List<MyyDeviceData> selectByExample(MyyDeviceDataExample example);

    MyyDeviceData selectByPrimaryKey(Integer deviceDataId);

    int updateByExampleSelective(@Param("record") MyyDeviceData record, @Param("example") MyyDeviceDataExample example);

    int updateByExample(@Param("record") MyyDeviceData record, @Param("example") MyyDeviceDataExample example);

    int updateByPrimaryKeySelective(MyyDeviceData record);

    int updateByPrimaryKey(MyyDeviceData record);

    MyyDeviceData selectBydeviceListIdAndNewTime(Integer deviceListId);
}