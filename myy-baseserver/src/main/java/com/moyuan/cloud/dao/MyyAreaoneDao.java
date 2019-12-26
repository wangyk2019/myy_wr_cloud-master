package com.moyuan.cloud.dao;

import com.moyuan.cloud.pojo.MyyAreaone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MyyAreaoneDao extends JpaRepository<MyyAreaone,Long> {

    MyyAreaone findById(long id);

    MyyAreaone findAllByDistrictidAndNameAndState(long districtid, String name,String state);

    MyyAreaone findAllByNameAndState(String name,String state);

    List<MyyAreaone> findAllByDistrictidAndState(long id, String state);

    @Query("update MyyAreaone set state=?1 where id=?2")
    @Modifying
    void updateStateById(String state,long id);

}
