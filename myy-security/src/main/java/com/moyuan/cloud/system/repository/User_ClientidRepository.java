package com.moyuan.cloud.system.repository;

import com.moyuan.cloud.system.entity.MyyUserClientid;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface User_ClientidRepository extends CrudRepository<MyyUserClientid, Long> {

    Optional<MyyUserClientid> findById(Long id);

    Optional<MyyUserClientid> findByUserid(long userid);

    List<MyyUserClientid> findAllByUseridIn(List<String> userid);

    List<MyyUserClientid> findAllByDistrictid(long orgid);
}
