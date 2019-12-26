package com.moyuan.cloud.dao;

import com.moyuan.cloud.pojo.MyyAreabottom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MyyAreabottomDao extends JpaRepository<MyyAreabottom,Long> {

    MyyAreabottom findById(long id);

    MyyAreabottom findByAreatwoidAndNameAndState(long id,String name,String state);

    List<MyyAreabottom> findAllByAreatwoidAndState(long id, String state);

    @Query("update MyyAreabottom set state=?1 where id=?2")
    @Modifying
    void updateStateById(String state, long id);

    @Query(value = "select a.* from myy_areabottom a,myy_areatwo b,myy_areaone c " +
            "where a.areatwoid=b.id and b.areaoneid=c.id " +
            "and a.state='1' and b.state='1' and c.state='1' " +
            "and c.districtid=?1 and a.name=?2 ",nativeQuery = true)
    List<MyyAreabottom> myyAreabottomsOrgidAndBottomname(long id, String bottomname);

    @Query(value = "select a.* from myy_areabottom a,myy_areatwo b,myy_areaone c " +
            "where a.areatwoid=b.id and b.areaoneid=c.id " +
            "and a.state='1' and b.state='1' and c.state='1' " +
            "and c.districtid=?1 ",nativeQuery = true)
    List<MyyAreabottom> myyAreabottomsOrgid(long id);

    @Query(value = "select a.manager from myy_areabottom a,myy_areatwo b,myy_areaone c " +
            "where a.areatwoid=b.id and b.areaoneid=c.id " +
            "and a.state='1' and b.state='1' and c.state='1' " +
            "and c.districtid=?1 and a.manager !=0 group by a.manager ",nativeQuery = true)
    List<MyyAreabottom> myyAreabottomsOrgidAndManager(long id);

    @Query(value = "select CONCAT_WS('_',c.name,b.name,a.name) as addr,a.id " +
            "from myy_areabottom a,myy_areatwo b,myy_areaone c " +
            "where a.areatwoid=b.id and b.areaoneid=c.id " +
            "and a.state='1' and b.state='1' and c.state='1' " +
            "and if(?1 != 0,c.district_id=?1,1=1) and if(?2 != 0,c.id=?2,1=1) " +
            "and if(?3 != 0,b.id=?3,1=1) and if(?4 != 0,a.id=?4,1=1)",nativeQuery = true)
    List myyAreabottoms(long orgid, long oneid, long twoid, long threeid);

}
