package com.moyuan.cloud.service;

import com.moyuan.cloud.dao.MyyWarnPushDao;
import com.moyuan.cloud.pojo.MyyWarnpushcfg;
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
public class MyyWarnPushService {
    @Autowired
    MyyWarnPushDao myyWarnPushDao;

    @Caching(put = {@CachePut(value = "myyWarnPushcfg",key = "#result.id"),
                    @CachePut(value = "myyWarnPushcfg_areauserid",key = "#result.areauserid")},
            evict = {@CacheEvict(value = "myyWarnPushcfg_areauserid",allEntries = true),
                    @CacheEvict(value = "myyWarnPushcfg_areabottomidAndUserid",allEntries = true)})
    public MyyWarnpushcfg save(MyyWarnpushcfg myyWarnpushcfg){
        MyyWarnpushcfg obj = myyWarnPushDao.save(myyWarnpushcfg);
        return obj;
    }

    @Cacheable(value = "myyWarnPushcfg",key = "#p0")
    public MyyWarnpushcfg findById(long id){
        MyyWarnpushcfg obj = myyWarnPushDao.findById(id);
        return obj;
    }

    @Cacheable(value = "myyWarnPushcfg_areauserid",key = "#p0")
    public List<MyyWarnpushcfg> findByAreauserid(long id){
        List<MyyWarnpushcfg> myyArea = myyWarnPushDao.findAllByAreauseridAndStateOrderByAreauserid(id,"1");
        return myyArea;
    }

    @Caching(put = {@CachePut(value = "myyWarnPushcfg",key = "#p0")},
            evict = {@CacheEvict(value = "myyWarnPushcfg_areabottomid",allEntries = true),
                    @CacheEvict(value = "myyWarnPushcfg_areabottomidAndUserid",allEntries = true)})
    @Transactional
    public void updateStateById(long id){
        myyWarnPushDao.updateStateById("0",id);
    }

    @Caching(put = {@CachePut(value = "myyWarnPushcfg",key = "#p0")},
            evict = {@CacheEvict(value = "myyWarnPushcfg_areabottomid",allEntries = true),
                    @CacheEvict(value = "myyWarnPushcfg_areabottomidAndUserid",allEntries = true)})
    @Transactional
    public void deleteAllByAreauserid(long areauserid){
        myyWarnPushDao.deleteAllByAreauserid(areauserid);
    }

//    @Cacheable(value = "myyWarnPushcfg_areabottomidAndwarntype")
//    public List<MyyWarnpushcfg> findByAreabottomid(long id){
//        List<MyyWarnpushcfg> myyArea = myyWarnPushDao.findAllByAreabottomid(id);
//        return myyArea;
//    }

//    @Cacheable(value = "myyWarnPushcfg_areabottomidAndUserid")
//    public List<MyyWarnpushcfg> findByAreabottomidAndUserid(ArrayList areabottomid,long userid){
//        List<MyyWarnpushcfg> warnpushcfgs = myyWarnPushDao.findAllByAreabottomidInAndUseridAndStateOrderByUserid(areabottomid,userid,"1");
//        return warnpushcfgs;
//    }


    public List<MyyWarnpushcfg> myyWarnpushcfgs(long areauserid, long cameraid,String pushtype){
        List<MyyWarnpushcfg> warnpushcfgs = myyWarnPushDao.findAllByAreauseridAndCameraidAndPushtypeOrderByAreauserid(areauserid,cameraid,pushtype);
        return warnpushcfgs;
    }
}
