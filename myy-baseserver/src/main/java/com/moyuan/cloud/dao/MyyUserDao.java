package com.moyuan.cloud.dao;

import com.moyuan.cloud.pojo.MyyUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface MyyUserDao extends JpaRepository<MyyUser,Long> {
    MyyUser findById(long id);

    List<MyyUser> findAllByDistrictidAndStateOrderById(long id,String state);


    MyyUser findByPhonenumber(String phonenumber);

    @Query(value = "select * from myy_user u where u.state='1' and if(?1 != 0,u.id=?1,1=1) and if(?2 != 0,u.area_id = ?2,1=1) and if(?3 != 0,u.districtid=?3,1=1) " +
            "and if(?4 != 0,u.position_id=?4,1=1) and if(?5 != '',u.phonenumber=?5,1=1) and if(?6 != '',u.username like %?6%,1=1) order by u.creattime desc,u.districtid ",nativeQuery = true)
    List<MyyUser> findAllByParams(long userid, long district_id, String phonenum, String username);

    Page<MyyUser> findAllByDistrictidAndUsernameAndState(long orgid, String username, String state, Pageable pageable);

    List<MyyUser> findAllByDistrictidAndUsernameContainingAndState(long orgid, String username, String state);

    Optional<MyyUser> findByPhonenumberAndPassword(String cellphone, String password);

    @Query("update MyyUser set state=?1 where id=?2")
    @Modifying
    void updateStateById(String state,long id);

    @Query("update MyyUser set livepower=?2 where id=?1")
    @Modifying
    void updateLivepowerById(long id, String livepower);

    @Query(value = "select * from myy_user u where u.state='1' and if(?2 != '',u.username like %?2%,1=1) " +
            "and u.id in (select userid from myy_areabottomuser where areabottomid " +
                        "in(select id from myy_areabottom where areatwoid=?1)) " +
            "order by u.creattime desc,u.districtid ",nativeQuery = true)
    List<MyyUser> findUsersByTwoidAndUsername(long id, String username);

    long countAllByDistrictidAndStateAndLivepower(long orgid, String state, String livepower);

}
