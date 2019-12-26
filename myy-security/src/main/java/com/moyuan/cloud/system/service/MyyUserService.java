package com.moyuan.cloud.system.service;

import com.moyuan.cloud.system.entity.MyyUser;
import com.moyuan.cloud.system.repository.MyyUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MyyUserService {
    @Autowired
    MyyUserDao myyUserDao;

    @Caching(put = {@CachePut(value = "myyUser", key = "#result.id"),
            @CachePut(value = "findByPhone", key = "#result.phonenumber"),
            @CachePut(value = "findOneByCellphoneAndPassword", key = "#result.phonenumber +#result.password")},
            evict = {@CacheEvict(value = "findUsersByParams", allEntries = true)})
    public MyyUser save(MyyUser myyUser) {
        MyyUser mu = myyUserDao.save(myyUser);
        return mu;
    }

    @Cacheable(value = "findByPhone", key = "#p0")
    public MyyUser findByPhone(String phoneNum) {
        MyyUser myyUser = myyUserDao.findByPhonenumber(phoneNum);
        return myyUser;
    }

    @Cacheable(value = "myyUser", key = "#p0")
    public MyyUser findById(long id) {
        MyyUser myyUser = myyUserDao.findById(id);
        return myyUser;
    }

    @Cacheable(value = "findUsersByParams")
    public List<MyyUser> findUsersByParams(long id, ArrayList area_id, long district_id, long position_id, String phonenum, String username) {
        List<MyyUser> myyUser = myyUserDao.findAllByParams(id, area_id, district_id, position_id, phonenum, username);
        return myyUser;
    }

    @Cacheable(value = "findOneByCellphoneAndPassword", key = "#p0 + #p1")
    public MyyUser findOneByCellphoneAndPassword(String cellphone, String password) {
//		Optional<User> opu = userrepository.findOneByCellphoneAndPassword(cellphone, Tools.md5(password));
        Optional<MyyUser> opu = myyUserDao.findByPhonenumberAndPassword(cellphone, password);
        return opu.isPresent() ? opu.get() : null;
    }
}
