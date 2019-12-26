package com.moyuan.cloud.service;

import com.moyuan.cloud.dao.MyyCameraDao;
import com.moyuan.cloud.pojo.MyyCamera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyyCameraService {
    @Autowired
    MyyCameraDao myyCameraDao;


    @Caching(put = {@CachePut(value = "water_camera",key = "#result.id")},
            evict = {@CacheEvict(value = "cam_findAllByParams",allEntries = true),
                    @CacheEvict(value = "cam_findAllByDistrict",allEntries = true),
                    @CacheEvict(value = "cam_findAllByAreabottomid",allEntries = true)})
    public MyyCamera saveCa(MyyCamera myyCamera){
        return myyCameraDao.save(myyCamera);
    }

    @Caching(put = {@CachePut(value = "water_camera",key = "#p0")},
            evict = {@CacheEvict(value = "cam_findAllByParams",allEntries = true),
                    @CacheEvict(value = "cam_findAllByDistrict",allEntries = true),
                    @CacheEvict(value = "cam_findAllByAreabottomid",allEntries = true)})
    @Transactional
    public void updateStateById(long id){
        myyCameraDao.updateStateById("0",id);
    }

    @Cacheable(value = "water_camera",key = "#p0")
    public MyyCamera findById(long id){
        return myyCameraDao.findById(id);
    }

    @Cacheable(value = "cam_findAllByParams")
    public List<MyyCamera> findAllByParams(ArrayList<Long> areaid, String status,String ip,String link){
        return myyCameraDao.findAllByAreaidInAndStatusAndIpAndLink(areaid,status,ip,link);
    }

    @Cacheable(value = "cam_findAllByDistrict")
    public List<MyyCamera> findAllByDistrict(long id){
        return myyCameraDao.findAllByDistrict(id);
    }

    @Cacheable(value = "cam_findAllByAreabottomid")
    public List<MyyCamera> findAllByAreabottomid(long id){
        return myyCameraDao.findAllByAreabottomidAndState(id,"1");
    }




    ///////////////////////////////////////////////////////////////////
//    @Cacheable(cacheNames = {"findAllByLaboratoryAndStatus"})
    public List<MyyCamera> findAllByCameraAndStatus(long colleageid, String status) {
        return myyCameraDao.findAllByCameraAndStatus(colleageid, status);
    }

    public List findAllByOrgid(int colleageid) {
        return myyCameraDao.findAllByOrgid(colleageid);
    }

    public List findAllByAreaoneid(long areaoneid) {
        return myyCameraDao.findAllByAreaoneid(areaoneid);
    }

}
