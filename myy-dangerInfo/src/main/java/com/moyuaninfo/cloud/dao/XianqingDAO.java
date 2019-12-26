package com.moyuaninfo.cloud.dao;

import com.moyuaninfo.cloud.pojo.MyyDangerInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface XianqingDAO extends JpaRepository<MyyDangerInfo,Long>, JpaSpecificationExecutor<MyyDangerInfo> {

	MyyDangerInfo findById(long id);

	List<MyyDangerInfo> findAllByDistrictidAndIpAndWarntypeAndPicpath(long orgid, String ip, String warntype, String picpath);

	@Query(value="select * from myy_dangerinfo d where d.districtid= ?1 and d.result='1' order by d.time desc limit 3 " +
			"",nativeQuery = true)
	List<MyyDangerInfo> findByDistAndNum3(long colleage);

	List<MyyDangerInfo> findAllByDistrictidAndWarntypeAndResultOrderByTime(long district, String warnType,String result);

	List<MyyDangerInfo> findAllByDistrictidAndNameContainsAndResultOrderByTime(long district, String name,String result);

	long countAllByDistrictidAndWarntypeAndResultAndIscheck(long district, String warntype,String result,int ischeck);

	long countAllByDistrictidAndNameAndResultAndIscheck(long district, String name,String result,int ischeck);

	long countAllByDistrictidAndWarntypeAndResult(long district, String warntype,String result);

	@Query(value="select * from myy_dangerinfo a,myy_areaone b,myy_areatwo c,myy_areabottom d " +
			"where a.areabottomid=d.id and d.areatwoid=c.id and c.areaoneid=b.id " +
			"and a.result='1'" +
			" and if(?1 !=0,a.districtid=?1,1=1) and if(?2 !=0,b.id= ?2,1=1) and if(?3 !=0,c.id= ?3,1=1)" +
			" and if(?4 !=0,d.id= ?4,1=1) and if(?5 !='',a.warntype= ?5,1=1)" +
			" and if(?6 !='',a.time >= ?6,1=1) and if(?7 !='',a.time <= ?7,1=1) " +
			" and if(?8 !=0,a.cameraid= ?8,1=1) ",nativeQuery = true)
	List<MyyDangerInfo> findAllByColleageandAndFloor1AndLabidAndTypeaAndTime(int colleage, long floor1, long floor2, long labid, String type, String begintime, String endtime, long cameraid);

	@Query("update MyyDangerInfo set ischeck=1 where districtid=?1 and warntype=?2")
	@Modifying
	void updateIscheckByDistrictidAndWarntype(long districtid,String name);

	@Query(value="select * from myy_dangerinfo d where d.districtid= ?1 and d.result='1' order by d.time desc limit 10 " +
			"",nativeQuery = true)
	List<MyyDangerInfo> findByDistAndNum10(long colleage);

	@Query(value="select * from myy_dangerinfo d where d.result='1'" +
			" and if(?1 !=0,d.districtid=?1,1=1) and if(?2 !='',d.warntype= ?2,1=1)" +
			" and if(?3 !='',d.time >= ?3,1=1) and if(?4 !='',d.time <= ?4,1=1) ",nativeQuery = true)
	List<MyyDangerInfo> findAllByOrgidAndWarntypeAndTime(long colleage, String warntype, String begintime, String endtime);

	@Query(value="select * from myy_dangerinfo d where d.result='1'" +
			" and if(?1 !=0,d.districtid=?1,1=1) " +
			" and if(?2 !='',d.time >= ?2,1=1) and if(?3 !='',d.time <= ?3,1=1) ",nativeQuery = true)
	List<MyyDangerInfo> findAllByOrgidAndTime(long colleage, String begintime, String endtime);

}