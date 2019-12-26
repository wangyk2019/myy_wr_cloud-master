package com.moyuan.signin.dao;

import com.moyuan.signin.pojo.MyySigninPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MyySigninPointDao extends JpaRepository<MyySigninPoint,Long> {

    Optional<MyySigninPoint> findById(Long id);

    List<MyySigninPoint> findAllByParent(Long parent);
}
