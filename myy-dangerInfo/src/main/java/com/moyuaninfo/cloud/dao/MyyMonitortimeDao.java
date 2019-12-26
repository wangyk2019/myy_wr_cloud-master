package com.moyuaninfo.cloud.dao;

import com.moyuaninfo.cloud.pojo.MyyMonitortime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MyyMonitortimeDao extends JpaRepository<MyyMonitortime,Long> {

    MyyMonitortime findById(long id);

    List<MyyMonitortime> findAllByDayidOrderByTime(long dayid);

    void deleteAllByDayid(long dayid);

}
