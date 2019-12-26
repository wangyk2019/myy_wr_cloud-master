package com.moyuan.cloud.service;

import com.moyuan.cloud.PageHelper;
import com.moyuan.cloud.dao.MyyAreabottomUserDao;
import com.moyuan.cloud.dao.MyyUserDao;
import com.moyuan.cloud.pojo.MyyAreabottomUser;
import com.moyuan.cloud.pojo.MyyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MyyUserService {
    @Autowired
    MyyUserDao myyUserDao;
    @Autowired
    MyyAreabottomUserDao myyAreabottomUserDao;

    @Caching(put = {@CachePut(value = "myyUser",key = "#result.id"),
            @CachePut(value = "findByPhone",key = "#result.phonenumber"),
            @CachePut(value = "findOneByCellphoneAndPassword",key = "#result.phonenumber +#result.password")},
        evict = {@CacheEvict(value = "findUsersByParams",allEntries = true),
                @CacheEvict(value = "myyUsers",allEntries = true)})
    public MyyUser save(MyyUser myyUser){
        MyyUser mu = myyUserDao.save(myyUser);
        return mu;
    }

    @Caching(put = {@CachePut(value = "myyUser",key = "#p0")},
            evict = {@CacheEvict(value = "findUsersByParams",allEntries = true),
                    @CacheEvict(value = "findOneByCellphoneAndPassword",allEntries = true),
                    @CacheEvict(value = "findUsersByParams",allEntries = true),
                    @CacheEvict(value = "myyUsers",allEntries = true)})
    @Transactional
    public void updateStateById(long id){
        myyUserDao.updateStateById("0",id);
    }

    @Transactional
    public void updateLivepowerById(long id, String livepower){
        myyUserDao.updateLivepowerById(id, livepower);
    }

    @Cacheable(value = "findByPhone",key = "#p0")
    public MyyUser findByPhone(String phoneNum){
        MyyUser myyUser = myyUserDao.findByPhonenumber(phoneNum);
        return myyUser;
    }

    @Cacheable(value = "myyUser",key = "#p0")
    public MyyUser findById(long id){
        MyyUser myyUser = myyUserDao.findById(id);
        return myyUser;
    }

    @Cacheable(value = "myyUsers",key = "#p0")
    public List<MyyUser> findByOrgid(long id){
        List<MyyUser> myyUsers = myyUserDao.findAllByDistrictidAndStateOrderById(id,"1");
        return myyUsers;
    }

    @Cacheable(value = "findUsersByParams")
    public List<MyyUser> findUsersByParams(long userid, long district_id, String phonenum, String username){
        List<MyyUser> myyUser = myyUserDao.findAllByParams(userid, district_id, phonenum, username);
        return myyUser;
    }

    public Page<MyyUser> findUsersByOrgAndUsername_page(long district_id, String username){
        Sort sort = new Sort(Sort.Direction.DESC,"id"); //创建时间降序排序
        Pageable pageable = PageRequest.of(0,10, sort);
        Page<MyyUser> myyUser = myyUserDao.findAllByDistrictidAndUsernameAndState(district_id, username, "1",pageable);
        return myyUser;
    }
    public List<MyyUser> findUsersByOrgAndUsername(long district_id, String username){
        List<MyyUser> myyUser = myyUserDao.findAllByDistrictidAndUsernameContainingAndState(district_id, username, "1");
        return myyUser;
    }

    @Cacheable(value = "findOneByCellphoneAndPassword",key = "#p0 + #p1")
    public MyyUser findOneByCellphoneAndPassword(String cellphone,String password) {
//		Optional<User> opu = userrepository.findOneByCellphoneAndPassword(cellphone, Tools.md5(password));
        Optional<MyyUser> opu = myyUserDao.findByPhonenumberAndPassword(cellphone,password);
        return opu.isPresent()?opu.get():null;
    }

    public long livepowerNum(long orgid){
        long num = myyUserDao.countAllByDistrictidAndStateAndLivepower(orgid,"1","1");
        return num;
    }


    //////////////////////////////////////////myyAreabottomUser//////////////////////////////////////////////////////
    public MyyAreabottomUser save_areauser(MyyAreabottomUser myyAreabottomUser){
        MyyAreabottomUser obj = myyAreabottomUserDao.save(myyAreabottomUser);
        return obj;
    }

    public List<MyyAreabottomUser> findUserBybomttom(long areabottomid){
        List<MyyAreabottomUser> objs = myyAreabottomUserDao.findAllByAreabottomid(areabottomid);
        return objs;
    }

    public List<MyyAreabottomUser> findAllByAreabottomidAndManager(long areabottomid){
        List<MyyAreabottomUser> objs = myyAreabottomUserDao.findAllByAreabottomidAndManager(areabottomid,"1");
        return objs;
    }

    public MyyAreabottomUser findAllByAreabottomidAndUserid(long areabottomid,long userid){
        MyyAreabottomUser obj = myyAreabottomUserDao.findByAreabottomidAndAndUserid(areabottomid,userid);
        return obj;
    }

    public void deleteAllByAreabottomidAndUserid(long areabottomid,long userid){
        myyAreabottomUserDao.deleteAllByAreabottomidAndUserid(areabottomid,userid);
    }

    public List<MyyAreabottomUser> findAllByParams(long orgid,long areaone,long areatwo,long area3,long userid) {
        return myyAreabottomUserDao.findAllByParams(orgid,areaone,areatwo,area3,userid);
    }

    public List<MyyUser> findUsersByTwoidAndUsername(long areatwo,String username) {
        return myyUserDao.findUsersByTwoidAndUsername(areatwo,username);
    }

}
