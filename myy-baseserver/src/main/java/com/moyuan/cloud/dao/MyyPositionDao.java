package com.moyuan.cloud.dao;

import com.moyuan.cloud.pojo.MyyNotice;
import com.moyuan.cloud.pojo.MyyPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MyyPositionDao extends JpaRepository<MyyPosition,Long> {

    MyyPosition findById(long id);

    List<MyyPosition> findAllByAreaid(long areaid);

}
