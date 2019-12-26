package com.moyuan.cloud.dao;

import com.moyuan.cloud.pojo.MyyUserClientid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface MyyUserCidDao extends JpaRepository<MyyUserClientid,Long> {
    MyyUserClientid findById(long id);

    MyyUserClientid findByUserid(long id);

    List<MyyUserClientid> findAllByDistrictid(long districtid);

}
