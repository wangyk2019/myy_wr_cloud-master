package com.moyuaninfo.waterinfo.service.impl;

import com.moyuaninfo.waterinfo.dao.MyyCityDistrictMapper;
import com.moyuaninfo.waterinfo.model.MyyCityDistrict;
import com.moyuaninfo.waterinfo.model.MyyCityDistrictExample;
import com.moyuaninfo.waterinfo.service.CityDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityDistrictServiceImpl implements CityDistrictService {

    @Autowired
    private MyyCityDistrictMapper cityDistrictMapper;

    //查询区域详细信息
    @Override
    public  List<MyyCityDistrict> selectCityDistrictAll() {
        MyyCityDistrictExample MyyCityDistrictExample = new MyyCityDistrictExample();
        MyyCityDistrictExample.createCriteria();
        List<MyyCityDistrict> myyCityDistrictsList = cityDistrictMapper.selectByExample(MyyCityDistrictExample);
        return myyCityDistrictsList;
    }



}
