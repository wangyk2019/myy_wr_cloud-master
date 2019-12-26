package com.moyuaninfo.waterinfo.service;

import com.moyuaninfo.waterinfo.model.MyyDeviceData;

import java.util.Date;
import java.util.List;

/**
 * 水质信息显示接口
 * */
public interface DeviceDateService {
    //显示当前水域实时水质信息参数（最新一条）
    MyyDeviceData selectDeviceDataNew(int areaoneId);

    //显示（一段时间）水质信息
    List<MyyDeviceData> selectDeviceDataByTime(int areaoneId, Date startTime, Date endTime);
}
