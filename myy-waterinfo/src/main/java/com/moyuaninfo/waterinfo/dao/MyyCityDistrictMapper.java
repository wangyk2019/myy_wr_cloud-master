package com.moyuaninfo.waterinfo.dao;

import com.moyuaninfo.waterinfo.model.MyyCityDistrict;
import com.moyuaninfo.waterinfo.model.MyyCityDistrictExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MyyCityDistrictMapper {
    int countByExample(MyyCityDistrictExample example);

    int deleteByExample(MyyCityDistrictExample example);

    int deleteByPrimaryKey(Integer districtid);

    int insert(MyyCityDistrict record);

    int insertSelective(MyyCityDistrict record);

    List<MyyCityDistrict> selectByExample(MyyCityDistrictExample example);

    MyyCityDistrict selectByPrimaryKey(Integer districtid);

    int updateByExampleSelective(@Param("record") MyyCityDistrict record, @Param("example") MyyCityDistrictExample example);

    int updateByExample(@Param("record") MyyCityDistrict record, @Param("example") MyyCityDistrictExample example);

    int updateByPrimaryKeySelective(MyyCityDistrict record);

    int updateByPrimaryKey(MyyCityDistrict record);
}