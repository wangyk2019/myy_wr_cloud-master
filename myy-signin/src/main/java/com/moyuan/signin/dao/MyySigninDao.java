package com.moyuan.signin.dao;

import com.moyuan.signin.pojo.MyySignin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MyySigninDao extends JpaRepository<MyySignin, Long> {

    @Query(value = "select c.* from myy_signin c,myy_signinpoint t where c.pointid = t.id and c.state =  ?6 " +
            "and c.signintime between ?2 and ?3 " +
            "and t.parent = ?1 order by signintime desc limit ?4,?5", nativeQuery = true)
    List<MyySignin> findByAreaidAndSignintimeOrderBySignintime(Long areaid, String startdate, String enddate, Integer pagestart, Integer pageend, String state);

    @Query(value = "select c.* from myy_signin c,myy_signinpoint t where c.pointid = t.id and c.state =  ?3 " +
            "and c.userid in ?2 and t.parent = ?1 order by signintime desc " ,
//            " limit ?3,?4",
            nativeQuery = true)
    List<MyySignin> findByAreaidAndUseridsOrderBySignintime(Long areaid, List<Long> userids, String state);

    @Query(value = "select * from myy_signin where signintime >= ?1 and signintime <= ?2 and state = ?5 " +
            "order by signintime desc limit ?2,?3", nativeQuery = true)
    List<MyySignin> findBySigntimeOrderBySignintime(Date starttime, Date endtime, Integer pagestart, Integer pageend, String state);

    @Query(value = "select * from myy_signin where state = ?3 order by signintime desc limit ?1,?2", nativeQuery = true)
    List<MyySignin> findAllOrderBySignintime(Integer pagestart, Integer pageend, String state);

    List<MyySignin> findAllByPlanidAndUseridAndState(Long planid, Long userid, String state);

    Optional<MyySignin> findByPlanidAndPointidAndState(Long planid, Long pointid, String state);

//    @Query(value = "select count(c.userid) as cou from myy_signin c,myy_signinpoint t where c.pointid = t.id " +
//            "and c.state = '1' and (?1 != 0,t.parent = ?1,1=1)  " +
//            "and (?2 != 0,c.userid = ?2,1=1) group by c.userid ", nativeQuery = true)
//    int findCountOfAll(Long areaid, Long userid, String startdate, String enddate);

    @Query(value = "select count(c.state) from myy_signin c GROUP BY c.state", nativeQuery = true)
    Object countAll();
}
