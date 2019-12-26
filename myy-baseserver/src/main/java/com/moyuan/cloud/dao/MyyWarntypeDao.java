package com.moyuan.cloud.dao;

import com.moyuan.cloud.pojo.MyyNotice;
import com.moyuan.cloud.pojo.MyyWarntype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MyyWarntypeDao extends JpaRepository<MyyWarntype,Long> {

    MyyWarntype findByWarntypeid(long id);

    MyyWarntype findByWarnen(String warnen);

    List<MyyWarntype> findAllByWarntypeidNotIn(List<Long> ids);
}
