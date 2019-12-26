package com.moyuan.cloud.dao;

import com.moyuan.cloud.pojo.MyyAreaone;
import com.moyuan.cloud.pojo.MyyAreatwo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MyyAreatwoDao extends JpaRepository<MyyAreatwo,Long> {

    MyyAreatwo findById(long id);

    List<MyyAreatwo> findAllByAreaoneidAndState(long id, String state);

    @Query("update MyyAreatwo set state=?1 where id=?2")
    @Modifying
    void updateStateById(String state, long id);

    @Query(value = "select * from myy_areatwo a,myy_areaone b " +
            "where a.areaoneid=b.id " +
            "and a.state='1' and b.state='1' " +
            "and b.districtid=?1 and b.name=?2 ",nativeQuery = true)
    List<MyyAreatwo> findAllByOrgidAndOnename(long id, String name);

    MyyAreatwo findAllByAreaoneidAndNameAndState(long area_id, String name,String state);
}
