package com.moyuan.cloud.dao;

import com.moyuan.cloud.pojo.MyyWarnpushcfg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface MyyWarnPushDao extends JpaRepository<MyyWarnpushcfg,Long> {

    MyyWarnpushcfg findById(long id);

    List<MyyWarnpushcfg> findAllByAreauseridAndStateOrderByAreauserid(long id, String state);

    @Query(value = "select * from myy_warnpushcfg where state='1' and if(length ('?1') > 4,areabottomid in ?1,1=1) and if(?2!=0,userid=?2,1=1)" +
            " order by userid",nativeQuery = true)
    List<MyyWarnpushcfg> findAllByAreabottomidInAndUseridAndStateOrderByUserid(ArrayList id, long user, String state);

    @Query("update MyyWarnpushcfg set state=?1 where id=?2")
    @Modifying
    void updateStateById(String state, long id);

    @Query("update MyyWarnpushcfg set state=?1 where areabottomid=?2 and userid=?3")
    @Modifying
    void updateStateByBU(String state, long areabottomid, long userid);

//    @Query(value = "select CONCAT_WS('_',c.name,b.name,a.name) as addr,d.id,d.userid,d.pushtype,d.warntype " +
//            "from myy_warnpushcfg d,myy_areabottomuser a " +
//            "where a.id=d.areauserid " +
//            "and a.state='1' " +
//            "and if(?1 != 0,a.id in ?1,1=1) and if(?2 != 0,d.cameraid=?2,1=1) " +
//            "and if(?3 != 0,a.userid=?3,1=1) ",nativeQuery = true)
    List<MyyWarnpushcfg> findAllByAreauseridAndCameraidAndPushtypeOrderByAreauserid(long areauserid, long cameraid,String pushtype);

    void deleteAllByAreauserid(long areauserid);
}
