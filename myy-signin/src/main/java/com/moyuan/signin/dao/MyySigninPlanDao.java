package com.moyuan.signin.dao;

import com.moyuan.signin.pojo.MyySigninPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MyySigninPlanDao extends JpaRepository<MyySigninPlan, Long> {

    List<MyySigninPlan> findAllByDateAndAreaid(Date date, Long areaid);

    @Query(value = "select * from myy_signinplan where state = '1' and (userida = ?2 or useridb = ?2) " +
            "and date = ?1 ", nativeQuery = true)
    MyySigninPlan findByDateAndUserid(String date, Long userid);

    @Query(value = "select t.* from myy_signinplan t where state = '1' and t.date between ?1 and ?2 " +
            "and t.areaid = ?3 order by t.date asc ", nativeQuery = true)
    List<MyySigninPlan> findAllBetweenDate(String begindate, String enddate, Long areaid);

    Optional<MyySigninPlan> findById(Long id);

}
