package com.moyuan.cloud.dao;

import com.moyuan.cloud.pojo.MyyRiverChecktask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
 * @ClassName RiverCheckMapper
 * @Description TODO
 * @Author zhaoGq
 * @Date 2019/12/2 17:11
 * @Version 1.0
 **/
public interface RiverCheckTaskDao extends JpaRepository<MyyRiverChecktask,Long> {

    /*
     * @Description //TODO
     * @Author zhaoGq
     * @Date 2019/12/3 11:22
     * @Param
     * @param myyRiverChecktask
     * @Return int
     **/
    MyyRiverChecktask save(MyyRiverChecktask myyRiverChecktask);

    /*
     * @Description //TODO
     * @Author zhaoGq
     * @Date 2019/12/3 13:37
     * @Param
     * @param districtId
     * @param areaId
     * @param areaSite
     * @param areaDetail
     * @Return java.util.List
     **/
    @Query(value = "SELECT myy_user.username AS userName\n" +
            ",myy_river_checktask.phonenumber\n" +
            ",CONCAT(myy_river_checktask.area_site,myy_river_checktask.area_detail) AS checkSite\n" +
            ",myy_river_checktask.creattime AS checkTime\n" +
            "FROM myy_river_checktask\n" +
            "LEFT JOIN myy_user\n" +
            "ON myy_river_checktask.create_user = myy_user.id\n" +
            "AND myy_user.state = '1'\n" +
            "WHERE myy_river_checktask.creattime\n" +
            "BETWEEN \n" +
            "CASE DATE_FORMAT(CURDATE(),'%w') WHEN 0 \n" +
            "THEN DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 6 DAY),'%Y-%m-%d')\n" +
            "ELSE DATE_FORMAT(SUBDATE(CURDATE(),DATE_FORMAT(CURDATE(),'%w')-1),'%Y-%m-%d') \n" +
            "END\n" +
            "AND \n" +
            "CASE DATE_FORMAT(CURDATE(),'%w') WHEN 0 \n" +
            "THEN DATE_FORMAT(SUBDATE(CURDATE(),DATE_FORMAT(CURDATE(),'%w')-1),'%Y-%m-%d')\n" +
            "ELSE DATE_FORMAT(SUBDATE(CURDATE(),DATE_FORMAT(CURDATE(),'%w')-8),'%Y-%m-%d')\n" +
            "END\n" +
            "AND IF(?1 IS NOT NULL,myy_river_checktask.district_id=?1,1=1)\n" +
            "AND IF(?2 IS NOT NULL,myy_river_checktask.area_id=?2,1=1)\n" +
            "AND IF(?3 IS NOT NULL,myy_river_checktask.area_site=?3,1=1)\n" +
            "AND IF(?4 IS NOT NULL,myy_river_checktask.area_detail=?4,1=1)\n" +
            "AND IF(?5 IS NOT NULL,myy_river_checktask.creattime BETWEEN ?6 AND ?7,1=1)\n" +
            "ORDER BY myy_river_checktask.creattime DESC",nativeQuery = true)
    List<Map<String, Object>> getRiverCheckWeekTaskList(Integer districtId, Integer areaId
            , String areaSite, String areaDetail, String validTime, String beginTime, String endTime);


}
