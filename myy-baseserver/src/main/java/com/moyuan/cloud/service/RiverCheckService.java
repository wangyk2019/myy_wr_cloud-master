package com.moyuan.cloud.service;

import com.moyuan.cloud.dao.RiverCheckPlanDao;
import com.moyuan.cloud.dao.RiverCheckTaskDao;
import com.moyuan.cloud.pojo.MyyRiverCheckplan;
import com.moyuan.cloud.pojo.MyyRiverChecktask;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName RiverCheckServiceImpl
 * @Description TODO
 * @Author zhaoGq
 * @Date 2019/12/2 17:10
 * @Version 1.0
 **/
@Service
public class RiverCheckService {

    @Autowired
    private RiverCheckPlanDao riverCheckPlanDao;

    @Autowired
    private RiverCheckTaskDao riverCheckTaskDao;

    /*
     * @Description //TODO
     * @Author zhaoGq
     * @Date 2019/12/2 17:25
     * @Param
     * @param myyRiverCheckplan
     * @Return int
     **/
    public MyyRiverCheckplan addRiverCheckPlan(MyyRiverCheckplan myyRiverCheckplan) {
        myyRiverCheckplan.setUpdateUser(myyRiverCheckplan.getCreateUser());
        Timestamp ts = new Timestamp(new Date().getTime());
        if (myyRiverCheckplan.getId() == null) {
            myyRiverCheckplan.setCreattime(ts);
        }
        myyRiverCheckplan.setUpdatetime(ts);
        myyRiverCheckplan.setState("1");
        return riverCheckPlanDao.save(myyRiverCheckplan);
    }

    /*
     * @Description //TODO
     * @Author zhaoGq
     * @Date 2019/12/3 9:41
     * @Param
     * @param myyRiverCheckplan
     * @Return
     **/
    @Transactional
    public void deleteRiverCheckPlan(MyyRiverCheckplan myyRiverCheckplan) {
        riverCheckPlanDao.delete(myyRiverCheckplan);
    }

    /*
     * @Description //TODO
     * @Author zhaoGq
     * @Date 2019/12/3 10:06
     * @Param
     * @param areaId
     * @param districtId
     * @Return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    public List<Map<String, Object>> getRiverCheckWeekPlanList(Integer areaId, Integer districtId) {
        return riverCheckPlanDao.getRiverCheckWeekPlanList(areaId,districtId);
    }

    /*
     * @Description //TODO
     * @Author zhaoGq
     * @Date 2019/12/3 11:21
     * @Param
     * @param myyRiverChecktask
     * @Return int
     **/
    public MyyRiverChecktask addRiverCheckTask(MyyRiverChecktask myyRiverChecktask) {
        myyRiverChecktask.setUpdateUser(myyRiverChecktask.getCreateUser());
        Timestamp ts = new Timestamp(new Date().getTime());
        myyRiverChecktask.setCreattime(ts);
        myyRiverChecktask.setUpdatetime(ts);
        myyRiverChecktask.setState("1");
        return riverCheckTaskDao.save(myyRiverChecktask);
    }

    /*
     * @Description //TODO
     * @Author zhaoGq
     * @Date 2019/12/3 13:34
     * @Param
     * @param districtId
     * @param areaId
     * @param areaSite
     * @param areaDetail
     * @param beginTime
     * @param endTime
     * @Return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    public List<Map<String, Object>> getRiverCheckWeekTaskList(Integer districtId, Integer areaId
            , String areaSite, String areaDetail, String beginTime, String endTime) {
        String validTime = "validTime";
        if (StringUtils.isBlank(beginTime) || StringUtils.isBlank(endTime)) {
            validTime = null;
        }
        return riverCheckTaskDao.getRiverCheckWeekTaskList(districtId, areaId
                , areaSite, areaDetail, validTime, beginTime, endTime);
    }


}

