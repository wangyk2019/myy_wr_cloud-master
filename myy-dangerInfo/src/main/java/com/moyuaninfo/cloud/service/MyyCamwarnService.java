package com.moyuaninfo.cloud.service;

import com.moyuaninfo.cloud.dao.MyyCamwarnconfigDao;
import com.moyuaninfo.cloud.dao.MyyMonitordayDao;
import com.moyuaninfo.cloud.dao.MyyMonitortimeDao;
import com.moyuaninfo.cloud.pojo.MyyCamwarnconfig;
import com.moyuaninfo.cloud.pojo.MyyMonitorday;
import com.moyuaninfo.cloud.pojo.MyyMonitortime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyyCamwarnService {
    @Autowired
    MyyCamwarnconfigDao myyCamwarnconfigDao;
    @Autowired
    MyyMonitordayDao myyMonitordayDao;
    @Autowired
    MyyMonitortimeDao myyMonitortimeDao;

    @Caching(put = {@CachePut(value = "myyCamwarnconfig",key = "#result.id"),
            @CachePut(value = "findAllByCameraidAndWarntype",key = "#result.cameraid + #result.warntypeid")},
            evict = {@CacheEvict(value = "myyCamwarnconfig_findByCameraid",key = "#result.cameraid")})
    @Transactional
    public MyyCamwarnconfig save(MyyCamwarnconfig myyCamwarnconfig){
        MyyCamwarnconfig mu = myyCamwarnconfigDao.save(myyCamwarnconfig);
        return mu;
    }

    @Caching(evict = {@CacheEvict(value = "myyCamwarnconfig_findByCameraid",key = "#result.cameraid"),
                    @CacheEvict(value = "findAllByCameraidAndWarntype",allEntries = true)})
    @Transactional
    public List<MyyCamwarnconfig> saveAll(List<MyyCamwarnconfig> myyCamwarnconfigs){
        List<MyyCamwarnconfig> mu = myyCamwarnconfigDao.saveAll(myyCamwarnconfigs);
        return mu;
    }

//    @Cacheable(value = "findAllByCameraidAndWarntype",key = "#p0 + #p1")
//    public MyyCamwarnconfig findAllByCameraidAndWarntype(long cameraid,long warntype){
//        MyyCamwarnconfig myyArea = myyCamwarnconfigDao.findAllByCameraidAndWarntypeidAndState(cameraid,warntype,"1");
//        return myyArea;
//    }

    @Cacheable(value = "myyCamwarnconfig",key = "#p0")
    public MyyCamwarnconfig findById(long id){
        MyyCamwarnconfig myyArea = myyCamwarnconfigDao.findById(id);
        return myyArea;
    }

    @Cacheable(value = "myyCamwarnconfig_findByCameraid",key = "#p0")
    public List<MyyCamwarnconfig> findByCameraid(long id){
        List<MyyCamwarnconfig> myyArea = myyCamwarnconfigDao.findByCameraidAndStateAndOnoff(id,"1","1");
        return myyArea;
    }

//    @Caching(evict = {@CacheEvict(value = "myyCamwarnconfig_findByCameraids",allEntries = true),
//            @CacheEvict(value = "myyCamwarnconfig",allEntries = true)})
//    @Transactional
//    public void delete_camwarn(long cameraid){
//        myyCamwarnconfigDao.deleteAllByCameraid(cameraid);
//    }

    @Caching(put = {@CachePut(value = "myyCamwarnconfig",key = "#p0")},
            evict = {@CacheEvict(value = "myyCamwarnconfig_findByCameraid",allEntries = true),
            @CacheEvict(value = "findAllByCameraidAndWarntype",allEntries = true)})
    @Transactional
    public void updateById(long id){
        myyCamwarnconfigDao.updateStateById("0",id);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////

    @Caching(put = {@CachePut(value = "myyMonitorday",key = "#result.id"),
            @CachePut(value = "myyMonitorday_findByCameraidAndDay",key = "#result.camwarnid + #result.day")},
            evict = {@CacheEvict(value = "myyMonitorday_findByCameraid",allEntries = true)})
    @Transactional
    public MyyMonitorday save_md(MyyMonitorday myyMonitorday){
        MyyMonitorday mu = myyMonitordayDao.save(myyMonitorday);
        return mu;
    }

    @Caching(evict = {@CacheEvict(value = "myyMonitorday_findByCameraid",allEntries = true),
                    @CacheEvict(value = "myyMonitorday_findByCameraidAndDay",allEntries = true)})
    @Transactional
    public List<MyyMonitorday> saveAll_md(List<MyyMonitorday> myyMonitordays){
        List<MyyMonitorday> list = myyMonitordayDao.saveAll(myyMonitordays);
        return list;
    }

    @Cacheable(value = "myyMonitorday",key = "#p0")
    public MyyMonitorday findById_md(long id){
        MyyMonitorday myyArea = myyMonitordayDao.findById(id);
        return myyArea;
    }

    @Caching(put = {@CachePut(value = "myyMonitorday",key = "#id")},
            evict = {@CacheEvict(value = "myyMonitorday_findByCameraid",allEntries = true)})
    @Transactional
    public void delete_md(long id){
        myyMonitordayDao.deleteById(id);
    }

    @Caching(evict = {@CacheEvict(value = "myyMonitorday",allEntries = true),
                    @CacheEvict(value = "myyMonitorday_findByCameraid",key = "#p0"),
            @CacheEvict(value = "myyMonitorday_findByCameraidAndDay",allEntries = true)})
    @Transactional
    public void delete_mds(long id){
        myyMonitordayDao.deleteAllByCamwarnid(id);
    }

//    @Cacheable(value = "myyMonitorday_findByCameraid",key = "#p0")
//    public MyyMonitorday findByCameraid_md(long id,String day){
//        MyyMonitorday myyArea = myyMonitordayDao.findAllByParams(id,day);
//        return myyArea;
//    }

    @Cacheable(value = "myyMonitorday_findByCameraid",key = "#p0")
    public List<MyyMonitorday> findByCamwarnid_md(long camid){
        List<MyyMonitorday> myyArea = myyMonitordayDao.findByCamwarnidOrderByDay(camid);
        return myyArea;
    }

    @Cacheable(value = "myyMonitorday_findByCameraidAndDay",key = "#p0")
    public MyyMonitorday findByCamwarnidAndDay_md(long camid,String day){
        MyyMonitorday myyArea = myyMonitordayDao.findByCamwarnidAndDay(camid,day);
        return myyArea;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////

    @Caching(put = {@CachePut(value = "myyMonitortime",key = "#result.id")},
            evict = {@CacheEvict(value = "myyMonitortime_findByDayid",allEntries = true)})
    @Transactional
    public MyyMonitortime save_mt(MyyMonitortime myyMonitortime){
        MyyMonitortime mu = myyMonitortimeDao.save(myyMonitortime);
        return mu;
    }

    @CacheEvict(value = "myyMonitortime_findByDayid",allEntries = true)
    @Transactional
    public List<MyyMonitortime> saveAll_mt(List<MyyMonitortime> myyMonitortimes){
        List<MyyMonitortime> list = myyMonitortimeDao.saveAll(myyMonitortimes);
        return list;
    }

    @Cacheable(value = "myyMonitortime",key = "#p0")
    public MyyMonitortime findById_mt(long id){
        MyyMonitortime myyArea = myyMonitortimeDao.findById(id);
        return myyArea;
    }

    @Caching(evict = {@CacheEvict(value = "myyMonitortime",key = "#id"),
                    @CacheEvict(value = "myyMonitortime_findByDayid",allEntries = true)})
    public void delete_mt(long id){
        myyMonitortimeDao.deleteById(id);
    }

    @Caching(evict = {@CacheEvict(value = "myyMonitortime",allEntries = true),
            @CacheEvict(value = "myyMonitortime_findByDayid",key = "#p0")})
    @Transactional
    public void delete_mts(long dayid){
        myyMonitortimeDao.deleteAllByDayid(dayid);
    }

    @Cacheable(value = "myyMonitortime_findByDayid",key = "#p0")
    public List<MyyMonitortime> findByDayid_mt(long dayid){
        List<MyyMonitortime> myyArea = myyMonitortimeDao.findAllByDayidOrderByTime(dayid);
        return myyArea;
    }
}
