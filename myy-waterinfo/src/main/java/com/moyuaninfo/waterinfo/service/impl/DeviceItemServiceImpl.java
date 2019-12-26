package com.moyuaninfo.waterinfo.service.impl;

import com.moyuaninfo.waterinfo.dao.DeviceItemMapper;
import com.moyuaninfo.waterinfo.dao.MyyDeviceDataMapper;
import com.moyuaninfo.waterinfo.dao.MyyDeviceItemMapper;
import com.moyuaninfo.waterinfo.dao.MyyDeviceListMapper;
import com.moyuaninfo.waterinfo.model.*;
import com.moyuaninfo.waterinfo.service.DeviceItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DeviceItemServiceImpl implements DeviceItemService {

    @Autowired
    private MyyDeviceItemMapper myyDeviceItemMapper;
    @Autowired
    private MyyDeviceListMapper myyDeviceListMapper;
    /**
     * 1.通过区域id查询区域设备分配表
     * 2.通过设备分配表查询设备信息
     * */
    @Override
    public List<MyyDeviceItem> selectDeviceItemByAreaoneId(int areaoneId,String name) {
        //初始化返回参数
        List<MyyDeviceItem> myyDeviceItemList = new ArrayList<>();
        //初始化查询条件
        MyyDeviceListExample myyDListExample = new MyyDeviceListExample();
        MyyDeviceListExample.Criteria criteria = myyDListExample.createCriteria();
        criteria.andAreaoneIdEqualTo(areaoneId);
        //查询得到区域分配表
        List<MyyDeviceList> myyDeviceLists = myyDeviceListMapper.selectByExample(myyDListExample);
        //判断区域分配表不为空
        if (myyDeviceLists.size()>0){
            //初始化查询条件
            MyyDeviceItemExample myyDeviceItemExample = new MyyDeviceItemExample();
            MyyDeviceItemExample.Criteria ItemCriteria = myyDeviceItemExample.createCriteria();
            ItemCriteria.andDeviceNameEqualTo(name);
            //通过分配表id查询设备信息
            for (MyyDeviceList myyDeviceList:myyDeviceLists) {

                //为查询条件赋值
                ItemCriteria.andDeviceListIdEqualTo(myyDeviceList.getDeviceListId());
                List<MyyDeviceItem> myyDeviceItemListO = myyDeviceItemMapper.selectByExample(myyDeviceItemExample);
                myyDeviceItemList.addAll(myyDeviceItemListO);
            }
            return myyDeviceItemList;
        }else {
            //区域下没有分配监测机器
            return myyDeviceItemList;
        }
    }
}
