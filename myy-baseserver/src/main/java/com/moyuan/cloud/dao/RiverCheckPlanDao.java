package com.moyuan.cloud.dao;

import com.moyuan.cloud.pojo.MyyRiverCheckplan;
import com.moyuan.cloud.pojo.MyyRiverChecktask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @ClassName RiverCheckMapper
 * @Description TODO
 * @Author zhaoGq
 * @Date 2019/12/2 17:11
 * @Version 1.0
 **/
public interface RiverCheckPlanDao extends JpaRepository<MyyRiverCheckplan,Long> {

    /*
     * @Description //TODO
     * @Author zhaoGq
     * @Date 2019/12/2 17:33
     * @Param
     * @param myyRiverCheckplan
     * @Return MyyRiverCheckplan
     **/
    MyyRiverCheckplan save(MyyRiverCheckplan myyRiverCheckplan);

    /*
     * @Description //TODO
     * @Author zhaoGq
     * @Date 2019/12/3 9:41
     * @Param
     * @param myyRiverCheckplan
     * @Return
     **/
    void delete(MyyRiverCheckplan myyRiverCheckplan);

    /*
     * @Description //TODO
     * @Author zhaoGq
     * @Date 2019/12/3 10:07
     * @Param
     * @param areaId
     * @param districtId
     * @Return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    @Query(value = "SELECT CASE DATE_FORMAT(CURDATE(),'%w') WHEN 0 \n" +
            "THEN\n" +
            "CONCAT(\n" +
            "'本周('\n" +
            ",DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 6 DAY),'%m-%d')\n" +
            ",'~'\n" +
            ",DATE_FORMAT(NOW(),'%m-%d')\n" +
            ",')'\n" +
            ")\n" +
            "ELSE\n" +
            "CONCAT(\n" +
            "'本周('\n" +
            ",DATE_FORMAT(SUBDATE(CURDATE(),DATE_FORMAT(CURDATE(),'%w')-1),'%m-%d')\n" +
            ",'~'\n" +
            ",DATE_FORMAT(SUBDATE(CURDATE(),DATE_FORMAT(CURDATE(),'%w')-7),'%m-%d')\n" +
            ",')'\n" +
            ")\n" +
            "END\n" +
            "AS currentWeek\n" +
            ",(CASE DATE_FORMAT(myy_river_checkplan.check_time,'%w') WHEN 1 THEN '星期一'\n" +
            "WHEN 2 THEN '星期二'\n" +
            "WHEN 3 THEN '星期三'\n" +
            "WHEN 4 THEN '星期四'\n" +
            "WHEN 5 THEN '星期五'\n" +
            "WHEN 6 THEN '星期六'\n" +
            "WHEN 0 THEN '星期日'\n" +
            "ELSE '' END) AS weekValue\n" +
            ",GROUP_CONCAT(myy_river_checkplan.id) AS checkPlanId\n" +
            ",GROUP_CONCAT(myy_user.username) AS userName\n" +
            ",GROUP_CONCAT(myy_user.phonenumber) AS phonenumber\n" +
            "FROM myy_river_checkplan\n" +
            "LEFT JOIN myy_user\n" +
            "ON myy_river_checkplan.user_id = myy_user.id\n" +
            "AND myy_user.state = '1'\n" +
            "WHERE myy_river_checkplan.state = '1' AND \n" +
            "CASE DATE_FORMAT(myy_river_checkplan.check_time,'%w') \n" +
            "WHEN 0 THEN YEARWEEK(DATE_FORMAT(myy_river_checkplan.check_time,'%Y-%m-%d')) - 1 \n" +
            "ELSE YEARWEEK(DATE_FORMAT(myy_river_checkplan.check_time,'%Y-%m-%d')) \n" +
            "END = \n" +
            "(SELECT CASE DATE_FORMAT(NOW(),'%w') WHEN 0 THEN YEARWEEK(NOW()) - 1\n" +
            "ELSE YEARWEEK(NOW()) END )\n" +
            "AND IF(?1 IS NOT NULL,myy_river_checkplan.area_id=?1,1=1) AND IF(?2 IS NOT NULL,myy_river_checkplan.district_id=?2,1=1)\n" +
            "GROUP BY weekValue\n" +
            "ORDER BY DATE_FORMAT(myy_river_checkplan.check_time,'%w')",nativeQuery = true)
    List<Map<String, Object>> getRiverCheckWeekPlanList(Integer areaId, Integer districtId);


}
