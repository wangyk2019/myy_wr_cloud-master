package com.moyuaninfo.cloud.dao;

import com.moyuaninfo.cloud.pojo.MyyCamwarnconfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface MyyCamwarnconfigDao extends JpaRepository<MyyCamwarnconfig,Long> {

    MyyCamwarnconfig findById(long id);

    List<MyyCamwarnconfig> findByCameraidAndStateAndOnoff(long cameraid,String state,String onoff);

    @Query("update MyyCamwarnconfig set state=?1 where id=?2")
    @Modifying
    void updateStateById(String state,long id);

}
