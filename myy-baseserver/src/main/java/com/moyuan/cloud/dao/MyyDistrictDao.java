package com.moyuan.cloud.dao;

import com.moyuan.cloud.pojo.MyyDistrict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MyyDistrictDao extends JpaRepository<MyyDistrict,Long> {
    MyyDistrict findById(long id);

    List<MyyDistrict> findAllByState(String state);

    List<MyyDistrict> findAllByNameContainsAndStateOrderByCreattime(String name,String state);

    MyyDistrict findAllByNameAndState(String name,String state);

    @Query("update MyyDistrict set state=?1 where id=?2")
    @Modifying//需要执行一个更新操作
    void updateStateById(String state,long id);
}
