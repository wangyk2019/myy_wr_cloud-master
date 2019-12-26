package com.moyuaninfo.waterinfo.dao;

import com.moyuaninfo.waterinfo.model.MyyDeviceList;
import com.moyuaninfo.waterinfo.model.MyyDeviceListExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MyyDeviceListMapper {
    int countByExample(MyyDeviceListExample example);

    int deleteByExample(MyyDeviceListExample example);

    int deleteByPrimaryKey(Integer deviceListId);

    int insert(MyyDeviceList record);

    int insertSelective(MyyDeviceList record);

    List<MyyDeviceList> selectByExample(MyyDeviceListExample example);

    MyyDeviceList selectByPrimaryKey(Integer deviceListId);

    int updateByExampleSelective(@Param("record") MyyDeviceList record, @Param("example") MyyDeviceListExample example);

    int updateByExample(@Param("record") MyyDeviceList record, @Param("example") MyyDeviceListExample example);

    int updateByPrimaryKeySelective(MyyDeviceList record);

    int updateByPrimaryKey(MyyDeviceList record);
}