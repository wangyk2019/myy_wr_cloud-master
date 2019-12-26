package com.moyuaninfo.cloud.dao;

import com.moyuaninfo.cloud.pojo.MyyCamwarnconfig;
import com.moyuaninfo.cloud.pojo.MyyMonitorday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface MyyMonitordayDao extends JpaRepository<MyyMonitorday,Long> {

    MyyMonitorday findById(long id);

    @Query(value = "select l.* from myy_monitorday l, myy_camwarnconfig u where l.camwarnid = u.id " +
            "and u.cameraid=?1 and l.day=?2 ",nativeQuery = true)
    MyyMonitorday findAllByParams(long camid, String day);

    List<MyyMonitorday> findByCamwarnidOrderByDay(long cameraid);

    MyyMonitorday findByCamwarnidAndDay(long cameraid,String day);

    void deleteAllByCamwarnid(long camwarnid);

}
