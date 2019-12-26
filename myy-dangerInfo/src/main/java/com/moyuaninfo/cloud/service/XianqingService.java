package com.moyuaninfo.cloud.service;

import com.moyuaninfo.cloud.VO.WarnType;
import com.moyuaninfo.cloud.common.JsonResult;
import com.moyuaninfo.cloud.dao.XianqingDAO;
import com.moyuaninfo.cloud.pojo.MyyDangerInfo;
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

import java.util.List;

@Service
public class XianqingService {
	@Autowired
	XianqingDAO objDao;

	@Caching(put = {@CachePut(value = "dangersave",key = "#result.id")},
		evict = {@CacheEvict(value = "findByDist",allEntries=true),
				@CacheEvict(value = "findByDistAndWarntype",allEntries=true),
				@CacheEvict(value = "findByDistAndWarnname",allEntries=true),
				@CacheEvict(value = "countAllByDistrictidAndWarntypeAndResultAndIscheck",allEntries=true)})
	@Transactional
	public MyyDangerInfo addObj(MyyDangerInfo danger) {
		return objDao.save(danger);
	}

	@Cacheable(value = "dangersave",key = "#id")
	public MyyDangerInfo getObj(long id) {
		MyyDangerInfo a = objDao.findById(id);
		return a;
	}

	public List<MyyDangerInfo> findAllByOrgidAndIpAndWarntypeAndFilepathAndPicpath(long orgid, String ip, String warntype, String picpath) {
		List<MyyDangerInfo> r = objDao.findAllByDistrictidAndIpAndWarntypeAndPicpath(orgid,ip,warntype,picpath);
		return r;
	}

	///根据组织、险情类型查
	@Cacheable(value = "findByDistAndWarntype")
	public List<MyyDangerInfo> findByDistAndWarntype(long orgid,String warnType) {
		List<MyyDangerInfo> r = objDao.findAllByDistrictidAndWarntypeAndResultOrderByTime(orgid,warnType,"1");
		return r;
	}

	@Cacheable(value = "findByDistAndWarnname")
	public List<MyyDangerInfo> findByDistAndWarnname(long orgid,String warnType) {
		List<MyyDangerInfo> r = objDao.findAllByDistrictidAndNameContainsAndResultOrderByTime(orgid,warnType,"1");
		return r;
	}

	@Cacheable(value = "countAllByDistrictidAndWarntypeAndResultAndIscheck")
	public Long countAllByDistrictidAndWarntypeAndResultAndIscheck(long orgid,String warnType) {
		Long r = objDao.countAllByDistrictidAndWarntypeAndResultAndIscheck(orgid,warnType,"1",0);
		return r;
	}

	public Long countAllByDistrictidAndNameAndResultAndIscheck(long orgid,String warnname) {
		Long r = objDao.countAllByDistrictidAndNameAndResultAndIscheck(orgid,warnname,"1",0);
		return r;
	}

	@Caching(evict = {@CacheEvict(value = "dangersave",allEntries=true),
					@CacheEvict(value = "findByDist",allEntries=true),
					@CacheEvict(value = "findByDistAndWarntype",allEntries=true),
					@CacheEvict(value = "findByDistAndWarnname",allEntries=true),
					@CacheEvict(value = "countAllByDistrictidAndWarntypeAndResultAndIscheck",allEntries=true)})
	@Transactional
	public void updateStateByDistrictidAndwarntype(long orgid,String warnen){
		objDao.updateIscheckByDistrictidAndWarntype(orgid,warnen);
	}

	//根据组织查询三个
	public List<MyyDangerInfo> findByDistAndNum3(long orgid) {
		List<MyyDangerInfo> r = objDao.findByDistAndNum3(orgid);
		return r;
	}

	public List<MyyDangerInfo> findAllByColleageandAndFloor1AndLabidAndTypeaAndTime_page(int colleage,long floor1,long floor2,long room,long cameraid,String type,String begin,String end) {
		List<MyyDangerInfo> r = objDao.findAllByColleageandAndFloor1AndLabidAndTypeaAndTime(colleage,floor1,floor2,room,type,begin,end,cameraid);
		return r;
	}

	public Long countAllByDistrictidAndWarntypeAndResult(long orgid,String warnType) {
		Long r = objDao.countAllByDistrictidAndWarntypeAndResult(orgid,warnType,"1");
		return r;
	}

	//根据组织查询10个
	public List<MyyDangerInfo> findAllByColleage_show(long orgid) {
		List<MyyDangerInfo> r = objDao.findByDistAndNum10(orgid);
		return r;
	}
	public List<MyyDangerInfo> findAllByOrgidAndWarntypeAndTime(long colleage,String warntype,String begintime,String endtime) {
		List<MyyDangerInfo> r = objDao.findAllByOrgidAndWarntypeAndTime(colleage,warntype,begintime,endtime);
		return r;
	}
	public List<MyyDangerInfo> findAllByOrgidAndTime(long colleage,String begintime,String endtime) {
		List<MyyDangerInfo> r = objDao.findAllByOrgidAndTime(colleage,begintime,endtime);
		return r;
	}

}
