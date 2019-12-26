package com.moyuan.cloud.dao;

import com.moyuan.cloud.pojo.MyyPushmsglogs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyyPushLogsDao extends JpaRepository<MyyPushmsglogs,Long> {
    MyyPushmsglogs findById(long id);

    List<MyyPushmsglogs> findAllByTypeOrderByCreattime(String type);

}
