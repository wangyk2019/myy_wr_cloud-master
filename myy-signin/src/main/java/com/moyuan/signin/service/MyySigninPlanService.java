package com.moyuan.signin.service;

import com.moyuan.signin.dao.MyySigninPlanDao;
import com.moyuan.signin.pojo.MyySigninPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MyySigninPlanService {

    @Autowired
    private MyySigninPlanDao myySigninPlanDao;

    @Transactional
    public boolean saveOrUpdate(MyySigninPlan plan) {
        MyySigninPlan s = myySigninPlanDao.save(plan);
        if (s != null) {
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public boolean saveAll(List<MyySigninPlan> plans) {
        List<MyySigninPlan> data = new ArrayList<>();
        for (MyySigninPlan plan : plans) {
            if (data.size() == 100) {
                myySigninPlanDao.saveAll(data);
                data.clear();
            }
            data.add(plan);
            if (!data.isEmpty()) {
                myySigninPlanDao.saveAll(data);
            }
        }
        return true;
    }

    public MyySigninPlan findByDateAndUserid(String date, Long userid) {
        return myySigninPlanDao.findByDateAndUserid(date, userid);
    }

    public List<MyySigninPlan> findAllBetweenDate(String begindate, String enddate, Long areaid) {
        List<MyySigninPlan> list = myySigninPlanDao.findAllBetweenDate(begindate, enddate, areaid);
        if (list != null && list.size() > 0) {
            return list;
        } else {
            return null;
        }
    }

    public MyySigninPlan findById(Long id){
        Optional<MyySigninPlan> plan = myySigninPlanDao.findById(id);
        return plan.isPresent()?plan.get():null;
    }

}
