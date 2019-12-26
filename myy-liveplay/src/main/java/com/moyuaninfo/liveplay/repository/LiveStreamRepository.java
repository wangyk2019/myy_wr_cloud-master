package com.moyuaninfo.liveplay.repository;

import com.moyuaninfo.liveplay.entity.Livestream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface LiveStreamRepository extends JpaRepository<Livestream, Long> {

    @Query(value = "SELECT t.* from myy_livestream t ,myy_camera c where t.cameraid = c.id and c.areaid = :areaid and t.status = :status ", nativeQuery = true)
    List<Livestream> findByAreaAndStatus(@Param("area") Long areaid, @Param("status") String status);

    List<Livestream> findByStreamname(String streamname);

    List<Livestream> findByStreamnameAndStatusOrderByStreamname(String streamname, String status);

    @Transactional
    @Modifying
    @Query(value = "update myy_livestream set status = :status where pusherid = :pusherid ", nativeQuery = true)
    void updateStatusByPusherid(@Param("status") String status, @Param("pusherid") String pusherid);

    @Query(value = "SELECT t.* from myy_livestream t where t.cameraid = :cameraid ORDER BY t.creattime desc LIMIT 1", nativeQuery = true)
    Livestream findByCameraid(@Param("cameraid") Long cameraid);
}
