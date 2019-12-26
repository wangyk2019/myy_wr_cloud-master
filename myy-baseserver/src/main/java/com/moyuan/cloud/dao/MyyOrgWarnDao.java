package com.moyuan.cloud.dao;

import com.moyuan.cloud.pojo.MyyOrgwarntype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MyyOrgWarnDao extends JpaRepository<MyyOrgwarntype,Long> {

    MyyOrgwarntype findById(long id);

    List<MyyOrgwarntype> findAllByOrgidAndBelongsOrderByActiveDescSortAsc(long orgid,int belongs);

    @Query("update MyyOrgwarntype set sort=:sort,belongs=:belongs where orgid=:orgid and warntypeid=:warntypeid ")
    @Modifying
    void updateOrgwarntypeSort(long orgid,long warntypeid,int sort,int belongs);

    MyyOrgwarntype findByOrgidAndWarntypeid(long orgid,long warntypeid);

}
