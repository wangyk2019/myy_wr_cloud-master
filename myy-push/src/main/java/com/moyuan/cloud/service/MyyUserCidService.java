package com.moyuan.cloud.service;

import com.moyuan.cloud.dao.MyyPushLogsDao;
import com.moyuan.cloud.dao.MyyUserCidDao;
import com.moyuan.cloud.pojo.MyyPushmsglogs;
import com.moyuan.cloud.pojo.MyyUserClientid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyyUserCidService {
    @Autowired
    MyyUserCidDao myyUserCidDao;
    @Autowired
    MyyPushLogsDao myyPushLogsDao;

    @Cacheable(value = "findAllByDistrictid",key = "#p0")
    public List<MyyUserClientid> findAllByDistrictid(long id){
        return myyUserCidDao.findAllByDistrictid(id);
    }

    @Cacheable(value = "findByUserid",key = "#p0")
    public MyyUserClientid findByUserid(long id){
        return myyUserCidDao.findByUserid(id);
    }

    public MyyPushmsglogs save_push(MyyPushmsglogs myyPushmsglogs){
        return myyPushLogsDao.save(myyPushmsglogs);
    }

}
