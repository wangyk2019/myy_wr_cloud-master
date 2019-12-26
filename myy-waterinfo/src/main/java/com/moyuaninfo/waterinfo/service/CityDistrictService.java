package com.moyuaninfo.waterinfo.service;

import com.moyuaninfo.waterinfo.model.MyyCityDistrict;

import java.util.List;

public interface CityDistrictService {
    //全部区域信息查询
    List<MyyCityDistrict> selectCityDistrictAll();
}
