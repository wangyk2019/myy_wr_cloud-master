package com.moyuaninfo.waterinfo.service;

import com.moyuaninfo.waterinfo.model.MyyDeviceItem;
import com.moyuaninfo.waterinfo.model.MyyDeviceList;

import java.util.List;
import java.util.Map;


public interface DeviceItemService {


    List<MyyDeviceItem> selectDeviceItemByAreaoneId(int areaoneId,String name);
}
