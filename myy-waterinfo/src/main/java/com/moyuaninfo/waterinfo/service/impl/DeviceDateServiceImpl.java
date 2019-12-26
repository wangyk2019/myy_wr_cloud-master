package com.moyuaninfo.waterinfo.service.impl;

import com.moyuaninfo.waterinfo.dao.MyyDeviceDataMapper;
import com.moyuaninfo.waterinfo.dao.MyyDeviceListMapper;
import com.moyuaninfo.waterinfo.model.MyyDeviceData;
import com.moyuaninfo.waterinfo.model.MyyDeviceDataExample;
import com.moyuaninfo.waterinfo.model.MyyDeviceList;
import com.moyuaninfo.waterinfo.model.MyyDeviceListExample;
import com.moyuaninfo.waterinfo.service.DeviceDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;


@Service
public class DeviceDateServiceImpl implements DeviceDateService {

    @Autowired
    private MyyDeviceDataMapper myyDeviceDataMapper;
    @Autowired
    private MyyDeviceListMapper myyDeviceListMapper;

    //显示当前水域实时水质信息参数（最新一条）
    @Override
    public MyyDeviceData selectDeviceDataNew(int areaoneId) {
        /**
         * 1.通过区域id查询区域设备分配id
         * 2.通过区域设备分配id查询最新一条记录
         * */
        //初始化查询条件
        MyyDeviceListExample myyDListExample = new MyyDeviceListExample();
        MyyDeviceListExample.Criteria criteria = myyDListExample.createCriteria();
        criteria.andAreaoneIdEqualTo(areaoneId);
        //查询得到区域分配表
        List<MyyDeviceList> myyDeviceLists = myyDeviceListMapper.selectByExample(myyDListExample);
        //判断区域分配表不为空
        if (myyDeviceLists.size()>0){
            //获取第一个分配数据的id
            Integer deviceListId = myyDeviceLists.get(0).getDeviceListId();
            //通过分配表id查询最新一条消息
            MyyDeviceData myyDeviceData =  myyDeviceDataMapper.selectBydeviceListIdAndNewTime(deviceListId);
            return myyDeviceData;
        }else {
            //区域下没有分配监测机器
            return null;
        }

    }


    //显示（一段时间）水质信息
    @Override
    public List<MyyDeviceData> selectDeviceDataByTime(int areaoneId, Date startTime, Date endTime) {
        /**
         * 1.通过区域id查询区域设备分配id
         * 2.通过区域设备分配id查询最新一条记录
         * */
        //初始化查询条件
        MyyDeviceListExample myyDeviceListExample = new MyyDeviceListExample();
        MyyDeviceListExample.Criteria criteria = myyDeviceListExample.createCriteria();
        criteria.andAreaoneIdEqualTo(areaoneId);
        //查询得到区域分配表
        List<MyyDeviceList> myyDeviceLists = myyDeviceListMapper.selectByExample(myyDeviceListExample);
        //判断区域分配表不为空
        if (myyDeviceLists.size()>0){
            //获取第一个分配数据的id
            Integer deviceListId = myyDeviceLists.get(0).getDeviceListId();
            //通过分配表id查询时间段数据
            MyyDeviceDataExample myyDeviceDataExample = new MyyDeviceDataExample();
            MyyDeviceDataExample.Criteria dateCriteria = myyDeviceDataExample.createCriteria();
            //初始化条件为分配表id,开始时间，结束时间
            dateCriteria.andDeviceListIdEqualTo(deviceListId);
            System.out.println("======================================");
            System.out.println(startTime.toString());
            System.out.println(endTime.toString());
            System.out.println("======================================");
//            dateCriteria.andUpdatetimeBetween(startTime,endTime);
            //执行查询
            List<MyyDeviceData> myyDeviceDataList = myyDeviceDataMapper.selectByExample(myyDeviceDataExample);
            return myyDeviceDataList;
        }else {
            //区域下没有分配监测机器
            return null;
        }
    }

}
