package com.moyuaninfo.liveplay.repository;

import com.moyuaninfo.liveplay.entity.Livelog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LivelogRepository extends JpaRepository<Livelog, Long> {

    Optional<Livelog> findByUseridAndPlaystreamid(long userid, long playstreamid);

}
