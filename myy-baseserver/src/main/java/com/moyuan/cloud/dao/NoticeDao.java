package com.moyuan.cloud.dao;

import com.moyuan.cloud.pojo.MyyNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoticeDao extends JpaRepository<MyyNotice,Long> {

    MyyNotice findById(long id);

    List<MyyNotice> findAllByUsersIn(String[] users);

//    @Query(value = "select * from (select id,name,'0' as type, beigntime from notice union all " +
//            "select l.id,h.floor1 || h.floor2 || h.room || '有'|| l.name as name,'1' as type,l.time as beigntime from  danger l, laboratory h " +
//            "where l.labid=h.id and h.colleage= ?1) nl where 1=1 order by nl.date ",nativeQuery = true)
//    List<Notice> findAllNoticeAndColleageDanger(int colleage);

    List<MyyNotice> findAll();

    @Query("update MyyNotice set state='1' where id=?1 ")
    @Modifying
    void updateOneById(long id);
}
