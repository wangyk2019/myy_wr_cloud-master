package com.moyuaninfo.waterinfo.dao;

import com.moyuaninfo.waterinfo.model.MyyDeviceItem;
import com.moyuaninfo.waterinfo.model.MyyDeviceItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MyyDeviceItemMapper {
    int countByExample(MyyDeviceItemExample example);

    int deleteByExample(MyyDeviceItemExample example);

    int deleteByPrimaryKey(Integer deviceItemId);

    int insert(MyyDeviceItem record);

    int insertSelective(MyyDeviceItem record);

    List<MyyDeviceItem> selectByExampleWithBLOBs(MyyDeviceItemExample example);

    List<MyyDeviceItem> selectByExample(MyyDeviceItemExample example);

    MyyDeviceItem selectByPrimaryKey(Integer deviceItemId);

    int updateByExampleSelective(@Param("record") MyyDeviceItem record, @Param("example") MyyDeviceItemExample example);

    int updateByExampleWithBLOBs(@Param("record") MyyDeviceItem record, @Param("example") MyyDeviceItemExample example);

    int updateByExample(@Param("record") MyyDeviceItem record, @Param("example") MyyDeviceItemExample example);

    int updateByPrimaryKeySelective(MyyDeviceItem record);

    int updateByPrimaryKeyWithBLOBs(MyyDeviceItem record);

    int updateByPrimaryKey(MyyDeviceItem record);
}