package com.moyuan.cloud.dao;

import com.moyuan.cloud.pojo.MyyCamera;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface MyyCameraDao extends JpaRepository<MyyCamera,Long> {
    MyyCamera findById(long id);

    @Query(value = "select * from myy_camera l where l.state='1' and if(length ('?1') > 4,l.areabottomid in ?1,1=1) and if(?2 !='',l.status= ?2,1=1) " +
            "and if(?3 !='',l.ip = ?3,1=1) and if(?4 !='',l.link= ?4,1=1) order by l.creattime desc ",nativeQuery = true)
    List<MyyCamera> findAllByAreaidInAndStatusAndIpAndLink(ArrayList<Long> areaid, String status,String ip,String link);

    @Query(value = "select a.* " +
            "from myy_camera a,myy_areaone b,myy_areabottom c,myy_areatwo d " +
            "where a.areabottomid=c.id and c.areatwoid=d.id and d.areaoneid=b.id " +
            "and a.state='1' and c.state='1' and d.state='1' and b.state='1' " +
            "and b.districtid=?1 ",nativeQuery = true)
    List<MyyCamera> findAllByDistrict(long id);

    @Query("update MyyCamera set state=?1 where id=?2")
    @Modifying
    void updateStateById(String state,long id);

    List<MyyCamera> findAllByAreabottomidAndState(long areabottomid,String state);

    ///////////////////////////////////////////////////////////////////////////
    @Query(nativeQuery = true,value="SELECT t.* from myy_camera t, myy_areabottom l,myy_areatwo a,myy_areaone b " +
            "where l.id = t.areabottomid and l.areatwoid=a.id and a.areaoneid=b.id " +
            "and b.districtid = ?1 and t.status=?2 ")
    List<MyyCamera> findAllByCameraAndStatus(long colleageid,String status);


    @Query(value = "select CONCAT_WS(':',a.ip,a.channel) as camname,a.id as camid,d.name as areatwoname," +
            "c.name as areabottomname,a.longitude,a.latitude," +
            "(select count(1) from myy_dangerinfo where cameraid=a.id and result='1') as dangernum " +
            "from myy_camera a,myy_areaone b,myy_areabottom c,myy_areatwo d " +
            "where a.areabottomid=c.id and c.areatwoid=d.id and d.areaoneid=b.id " +
            "and a.state='1' and c.state='1' and d.state='1' and b.state='1' " +
            "and b.districtid=?1 ",nativeQuery = true)
    List findAllByOrgid(int colleageid);

    @Query(value = "select CONCAT_WS(':',a.ip,a.channel) as camname,a.id as camid,d.name as areatwoname," +
            "c.name as areabottomname,a.longitude,a.latitude," +
            "(select count(1) from myy_dangerinfo where cameraid=a.id and result='1') as dangernum " +
            "from myy_camera a,myy_areabottom c,myy_areatwo d " +
            "where a.areabottomid=c.id and c.areatwoid=d.id " +
            "and a.state='1' and c.state='1' and d.state='1' " +
            "and d.areaoneid=:areaoneid ",nativeQuery = true)
    List findAllByAreaoneid(long areaoneid);



}
