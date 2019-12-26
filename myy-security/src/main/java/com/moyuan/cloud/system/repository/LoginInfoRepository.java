package com.moyuan.cloud.system.repository;

import com.moyuan.cloud.system.entity.MyyLoginLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginInfoRepository extends JpaRepository<MyyLoginLog,Long> {
}
