package com.moyuan.cloud.dao;

import com.moyuan.cloud.pojo.MyyAreabottomUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface MyyAreabottomUserDao extends CrudRepository<MyyAreabottomUser, Long> {

	Optional<MyyAreabottomUser> findById(Long id);

	List<MyyAreabottomUser> findAllByAreabottomid(long areabottomid);

    List<MyyAreabottomUser> findAllByAreabottomidAndManager(long areabottomid,String manager);

    MyyAreabottomUser findByAreabottomidAndAndUserid(long areabottomid,long userid);

    void deleteAllByAreabottomidAndUserid(long areabottomid,long userid);

//    @Query(value = "select CONCAT_WS('_',d.name,c.name,b.name) as addr,e.name,e.phonenumber,a.manager,a.areabottomid,a.userid,a.id " +
//            "from myy_areabottomuser a,myy_areabottom b,myy_areatwo c,myy_areaone d,myy_user e " +
//            "where a.areaid=b.id and b.areatwoid=c.id and c.areaoneid=d.id and a.userid=e.id" +
//            "and b.state='1' and c.state='1' and d.state='1' " +
//            "and if(?1 != 0,d.district_id=?1,1=1) and if(?2 != 0,d.id=?2,1=1) " +
//            "and if(?3 != 0,c.id=?3,1=1) and if(?4 != 0,b.id=?4,1=1) and if(?5 != 0,a.userid=?5,1=1)",nativeQuery = true)
    @Query(value = "select a.* " +
            "from myy_areabottomuser a,myy_areabottom b,myy_areatwo c,myy_areaone d,myy_user e " +
            "where a.areaid=b.id and b.areatwoid=c.id and c.areaoneid=d.id and a.userid=e.id" +
            "and b.state='1' and c.state='1' and d.state='1' " +
            "and if(?1 != 0,d.district_id=?1,1=1) and if(?2 != 0,d.id=?2,1=1) " +
            "and if(?3 != 0,c.id=?3,1=1) and if(?4 != 0,b.id=?4,1=1) and if(?5 != 0,a.userid=?5,1=1)",nativeQuery = true)
    List<MyyAreabottomUser> findAllByParams(long orgid,long areaone,long areatwo,long area3,long userid);
}
