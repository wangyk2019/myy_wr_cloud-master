package com.moyuan.cloud.service;

import com.moyuan.cloud.dao.MyyAreaoneDao;
import com.moyuan.cloud.dao.MyyAreabottomDao;
import com.moyuan.cloud.dao.MyyAreatwoDao;
import com.moyuan.cloud.dao.MyyPositionDao;
import com.moyuan.cloud.pojo.MyyAreaone;
import com.moyuan.cloud.pojo.MyyAreabottom;
import com.moyuan.cloud.pojo.MyyAreatwo;
import com.moyuan.cloud.pojo.MyyPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MyyAreaService {
    @Autowired
    MyyAreaoneDao myyAreaoneDao;
    @Autowired
    MyyAreatwoDao myyAreatwoDao;
    @Autowired
    MyyAreabottomDao myyAreabottomDao;
    @Autowired
    MyyPositionDao myyPositionDao;

    @Caching(put = {@CachePut(value = "myyAreaone",key = "#result.id")},
            evict = {@CacheEvict(value = "myyAreaones",key = "#result.districtid")})
    @Transactional
    public MyyAreaone save_one(MyyAreaone myyAreaone){
        MyyAreaone mu = myyAreaoneDao.save(myyAreaone);
        return mu;
    }

    @Cacheable(value = "myyAreaone",key = "#p0")
    public MyyAreaone findById_one(long id){
        MyyAreaone myyAreaone = myyAreaoneDao.findById(id);
        return myyAreaone;
    }

    @Cacheable(value = "myyAreaones",key = "#p0")
    public List<MyyAreaone> findoneAllByDistr(long id){
        List<MyyAreaone> myyAreaone = myyAreaoneDao.findAllByDistrictidAndState(id,"1");
        return myyAreaone;
    }

    public MyyAreaone findAllByDistrictidAndNameAndState(long distrctid, String name){
        MyyAreaone myyAreaone = myyAreaoneDao.findAllByDistrictidAndNameAndState(distrctid, name,"1");
        return myyAreaone;
    }

    @Caching(put = {@CachePut(value = "myyAreaone",key = "#p0")},
            evict = {@CacheEvict(value = "myyAreaones",allEntries = true)})
    @Transactional
    public void updateStateById(long id){
        myyAreaoneDao.updateStateById("0",id);
    }


    ///////////////////////////////////myyAreatwo//////////////////////////////////////////////////////
    @Caching(put = {@CachePut(value = "myyAreatwo",key = "#result.id")},
            evict = {@CacheEvict(value = "myyAreatwos",allEntries = true),
                    @CacheEvict(value = "myyAreatwosOrgidAndOnename",allEntries = true)})
    @Transactional
    public MyyAreatwo save_two(MyyAreatwo myyAreatwo){
        MyyAreatwo o = myyAreatwoDao.save(myyAreatwo);
        return o;
    }
    @Cacheable(value = "myyAreatwo",key = "#p0")
    public MyyAreatwo findById_reach(long id){
        MyyAreatwo o = myyAreatwoDao.findById(id);
        return o;
    }
    @Cacheable(value = "myyAreatwos",key = "#p0")
    public List<MyyAreatwo> findTwosByOneid(long id){
        List<MyyAreatwo> o = myyAreatwoDao.findAllByAreaoneidAndState(id,"1");
        return o;
    }
    @Cacheable(value = "findAllByAreaoneidAndNameAndState",key = "#p0 + #p1")
    public MyyAreatwo findAllByAreaoneidAndNameAndState(long areaoneid,String name){
        MyyAreatwo o = myyAreatwoDao.findAllByAreaoneidAndNameAndState(areaoneid,name,"1");
        return o;
    }
    @Caching(put = {@CachePut(value = "myyAreatwo",key = "#p0")},
            evict = {@CacheEvict(value = "myyAreatwos",allEntries = true),
                    @CacheEvict(value = "findAllByAreaoneidAndNameAndState",allEntries = true),
                    @CacheEvict(value = "myyAreatwosOrgidAndOnename",allEntries = true)})
    @Transactional
    public void updateStateById_reach(long id){
        myyAreatwoDao.updateStateById("0",id);
    }

    @Cacheable(value = "myyAreatwosOrgidAndOnename",key = "#p0 + #p1")
    public List<MyyAreatwo> myyAreatwosOrgidAndOnename(long id,String name){
        List<MyyAreatwo> o = myyAreatwoDao.findAllByOrgidAndOnename(id,name);
        return o;
    }


/////////////////////////////////////////////myyAreabottom////////////////////////////////////////////////////
    @Caching(put = {@CachePut(value = "myyAreabottom",key = "#result.id"),
                    @CachePut(value = "findByAreatwoidAndNameAndState",key = "#result.areatwoid + #result.name")},
            evict = {@CacheEvict(value = "myyAreabottoms",key = "#result.areatwoid")})
    @Transactional
    public MyyAreabottom save_bottom(MyyAreabottom myyAreabottom){
        MyyAreabottom o = myyAreabottomDao.save(myyAreabottom);
        return o;
    }
    @Cacheable(value = "myyAreabottom",key = "#p0")
    public MyyAreabottom findById_bank(long id){
        MyyAreabottom o = myyAreabottomDao.findById(id);
        return o;
    }
    @Cacheable(value = "myyAreabottoms",key = "#p0")
    public List<MyyAreabottom> findBottomsByTwoid(long id){
        List<MyyAreabottom> o = myyAreabottomDao.findAllByAreatwoidAndState(id,"1");
        return o;
    }
    @Cacheable(value = "findByAreatwoidAndNameAndState",key = "#p0 + #p1")
    public MyyAreabottom findByAreatwoidAndNameAndState(long areatwoid,String name){
        MyyAreabottom o = myyAreabottomDao.findByAreatwoidAndNameAndState(areatwoid,name,"1");
        return o;
    }
    @Caching(put = {@CachePut(value = "myyAreabottom",key = "#p0")},
            evict = {@CacheEvict(value = "myyAreabottoms",allEntries = true),
                    @CacheEvict(value = "findByAreatwoidAndNameAndState",allEntries = true)})
    @Transactional
    public void updateStateById_bank(long id){
        myyAreabottomDao.updateStateById("0",id);
    }

//    @Cacheable(value = "myyAreabottoms",key = "#p0")
    public List myyAreabottoms(long orgid, long oneid, long twoid, long threeid){
        List o = myyAreabottomDao.myyAreabottoms(orgid,oneid,twoid,threeid);
        return o;
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<MyyAreabottom> myyAreabottomsOrgidAndBottomname(long orgid,String bottomname){
        List<MyyAreabottom> o = myyAreabottomDao.myyAreabottomsOrgidAndBottomname(orgid,bottomname);
        return o;
    }

    public List<MyyAreabottom> myyAreabottomsOrgid(long orgid){
        List<MyyAreabottom> o = myyAreabottomDao.myyAreabottomsOrgid(orgid);
        return o;
    }

    public List<MyyAreabottom> myyAreabottomsOrgidAndManager(long id){
        List<MyyAreabottom> o = myyAreabottomDao.myyAreabottomsOrgidAndManager(id);
        return o;
    }


    /////////////////////////////////////////////////////////////////////////////////////
    public List<MyyPosition> findPositions(long areaid){
        List<MyyPosition> objs = myyPositionDao.findAllByAreaid(areaid);
        return objs;
    }



}
