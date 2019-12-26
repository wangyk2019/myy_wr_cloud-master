package com.moyuan.cloud.system.service;

import com.moyuan.cloud.system.entity.MyyDistrict;
import com.moyuan.cloud.system.repository.MyyDistrictDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyyDistrictService {
    @Autowired
    MyyDistrictDao myyDistrictDao;

    @Caching(put = {@CachePut(value = "myyDistrict", key = "#result.id")},
            evict = {@CacheEvict(value = "findDistrictsByParams", allEntries = true)})
    public MyyDistrict save(MyyDistrict myyDistrict) {
        MyyDistrict mu = myyDistrictDao.save(myyDistrict);
        return mu;
    }

    @Cacheable(value = "myyDistrict", key = "#p0")
    public MyyDistrict findById(long id) {
        MyyDistrict myyDistrict = myyDistrictDao.findById(id);
        return myyDistrict;
    }

    @Cacheable(value = "findDistrictsByParams")
    public List<MyyDistrict> findDistrictsByParams(String name) {
        List<MyyDistrict> myyDistrict = myyDistrictDao.findAllByNameContainsAndStateOrderByCreattime(name, "1");
        return myyDistrict;
    }
}
