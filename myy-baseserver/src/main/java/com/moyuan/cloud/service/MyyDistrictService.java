package com.moyuan.cloud.service;

import com.moyuan.cloud.dao.MyyDistrictDao;
import com.moyuan.cloud.dao.MyyOrgWarnDao;
import com.moyuan.cloud.dao.MyyWarntypeDao;
import com.moyuan.cloud.pojo.MyyDistrict;
import com.moyuan.cloud.pojo.MyyOrgwarntype;
import com.moyuan.cloud.pojo.MyyWarntype;
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
public class MyyDistrictService {
    @Autowired
    MyyDistrictDao myyDistrictDao;
    @Autowired
    MyyOrgWarnDao myyOrgWarnDao;
    @Autowired
    MyyWarntypeDao myyWarntypeDao;

    @Caching(put = {@CachePut(value = "myyDistrict",key = "#result.id"),
                @CachePut(value = "myyDistrict_findByName",key = "#result.name")},
            evict = {@CacheEvict(value = "findDistrictsByParams",allEntries = true),
                    @CacheEvict(value = "findDistrictsAll",allEntries = true)})
    public MyyDistrict save(MyyDistrict myyDistrict){
        MyyDistrict mu = myyDistrictDao.save(myyDistrict);
        return mu;
    }

    @Cacheable(value = "myyDistrict",key = "#p0")
    public MyyDistrict findById(long id){
        MyyDistrict myyDistrict = myyDistrictDao.findById(id);
        return myyDistrict;
    }

    @Cacheable(value = "myyDistrict_findByName",key = "#p0")
    public MyyDistrict findByName(String name){
        MyyDistrict myyDistrict = myyDistrictDao.findAllByNameAndState(name,"1");
        return myyDistrict;
    }

    @Cacheable(value = "findDistrictsByParams")
    public List<MyyDistrict> findDistrictsByParams(String name){
        List<MyyDistrict> myyDistrict = myyDistrictDao.findAllByNameContainsAndStateOrderByCreattime(name,"1");
        return myyDistrict;
    }

    @Cacheable(value = "findDistrictsAll")
    public List<MyyDistrict> findDistrictsAll(){
        List<MyyDistrict> myyDistrict = myyDistrictDao.findAllByState("1");
        return myyDistrict;
    }

    @Caching(put = {@CachePut(value = "myyDistrict",key = "#p0")},
            evict = {@CacheEvict(value = "findDistrictsByParams",allEntries = true),
                    @CacheEvict(value = "myyDistrict_findByName",allEntries = true),
                    @CacheEvict(value = "findDistrictsAll",allEntries = true)})
    @Transactional
    public void updateStateById(long id){
        myyDistrictDao.updateStateById("0",id);
    }


    //////////////////////////////MyyOrgwarntype/////////////////////////////////////
    @Transactional
    public void saveOrgWarn(MyyOrgwarntype myyOrgwarntype){
        myyOrgWarnDao.save(myyOrgwarntype);
    }

    public List<MyyOrgwarntype> findWarns(long orgid,int belongs){
        List<MyyOrgwarntype> myyDistrict = myyOrgWarnDao.findAllByOrgidAndBelongsOrderByActiveDescSortAsc(orgid,belongs);
        return myyDistrict;
    }

    public MyyOrgwarntype findWarns_OrgidAndWarnid(long orgid,long warnid){
        MyyOrgwarntype myyOrgwarntype = myyOrgWarnDao.findByOrgidAndWarntypeid(orgid,warnid);
        return myyOrgwarntype;
    }

    @Transactional
    public void updateOrgwarntypeSort(long orgid,long warntypeid,int sort,int belongs){
        myyOrgWarnDao.updateOrgwarntypeSort(orgid,warntypeid,sort,belongs);
    }


    /////////////////////////////////MyyWarntype////////////////////////////////////////////////
    public MyyWarntype findByWarntypeid(long warnid){
        MyyWarntype myyWarntype = myyWarntypeDao.findByWarntypeid(warnid);
        return myyWarntype;
    }
    public List<MyyWarntype> findWarnByWarnen(List<Long> ids){
        List<MyyWarntype> myyWarntypes = myyWarntypeDao.findAllByWarntypeidNotIn(ids);
        return myyWarntypes;
    }
    public MyyWarntype findWarntypeByWarnen(String warnen){
        MyyWarntype myyWarntypes = myyWarntypeDao.findByWarnen(warnen);
        return myyWarntypes;
    }
    public List<MyyWarntype> findWarntypeAll(){
        List<MyyWarntype> myyWarntypes = myyWarntypeDao.findAll();
        return myyWarntypes;
    }
}
