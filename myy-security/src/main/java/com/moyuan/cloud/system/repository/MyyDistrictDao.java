package com.moyuan.cloud.system.repository;

import com.moyuan.cloud.system.entity.MyyDistrict;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyyDistrictDao extends JpaRepository<MyyDistrict, Long> {
    MyyDistrict findById(long id);


    List findAllByNameContainsAndStateOrderByCreattime(String name, String state);
}
