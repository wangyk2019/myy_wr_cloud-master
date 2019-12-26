package com.moyuan.cloud.system.repository;

import com.moyuan.cloud.system.entity.MyyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface MyyUserDao extends JpaRepository<MyyUser, Long> {
    MyyUser findById(long id);

    MyyUser findByPhonenumber(String phonenumber);

    @Query(value = "select * from myy_user u where u.state='1' and if(?1 != 0,u.id=?1,1=1) and if(length ('?2') > 4,u.area_id in ?2,1=1) and if(?3 != 0,u.district_id=?3,1=1) " +
            "and if(?4 != 0,u.position_id=?4,1=1) and if(?5 != '',u.phonenumber=?5,1=1) and if(?6 != '',u.username=?6,1=1) order by u.creattime desc,u.district_id ", nativeQuery = true)
    List<MyyUser> findAllByParams(long id, ArrayList area_id, long district_id, long position_id, String phonenum, String username);

    Optional<MyyUser> findByPhonenumberAndPassword(String cellphone, String password);
}
