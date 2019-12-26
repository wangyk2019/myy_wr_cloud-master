package com.moyuaninfo.cloud.service;

import com.moyuaninfo.cloud.dao.MyyWarnPushDao;
import com.moyuaninfo.cloud.pojo.MyyCamwarnconfig;
import com.moyuaninfo.cloud.pojo.MyyWarnpushcfg;
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

    @Caching(put = {@CachePut(value = "myyWarnPushcfg",key = "#result.id")},
            evict = {@CacheEvict(value = "myyWarnPushcfg_areabottomid",allEntries = true),
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

    @Cacheable(value = "myyWarnPushcfg_areabottomid")
    public List<MyyWarnpushcfg> findByareabottomid(long id){
        List<MyyWarnpushcfg> myyArea = myyWarnPushDao.findAllByAreabottomidAndStateOrderByUserid(id,"1");
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
    public void updateStateByBU(long areabottomid,long userid){
        myyWarnPushDao.updateStateByBU("0",areabottomid,userid);
    }

//    @Cacheable(value = "myyWarnPushcfg_areabottomidAndwarntype")
//    public List<MyyWarnpushcfg> findByAreabottomid(long id){
//        List<MyyWarnpushcfg> myyArea = myyWarnPushDao.findAllByAreabottomid(id);
//        return myyArea;
//    }

    @Cacheable(value = "myyWarnPushcfg_areabottomidAndUserid")
    public List<MyyWarnpushcfg> findByAreabottomidAndUserid(ArrayList areabottomid,long userid){
        List<MyyWarnpushcfg> warnpushcfgs = myyWarnPushDao.findAllByAreabottomidInAndUseridAndStateOrderByUserid(areabottomid,userid,"1");
        return warnpushcfgs;
    }


    public List myyWarnpushcfgs(long orgid, long oneid, long twoid, long threeid, long cameraid, long userid){
        List warnpushcfgs = myyWarnPushDao.myyWarnpushcfgs(orgid,oneid,twoid,threeid,cameraid,userid);
        return warnpushcfgs;
    }
}
