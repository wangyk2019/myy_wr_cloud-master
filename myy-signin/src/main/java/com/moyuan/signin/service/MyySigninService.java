package com.moyuan.signin.service;

import com.moyuan.signin.dao.MyySigninDao;
import com.moyuan.signin.pojo.MyySignin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MyySigninService {

    @Autowired
    private MyySigninDao myySigninDao;

    @Transactional
    public boolean saveOrUpdate(MyySignin signin) {
        MyySignin s = myySigninDao.save(signin);
        if (s != null) {
            return true;
        } else {
            return false;
        }
    }

    public int countOfAll() {
        Object obj = myySigninDao.countAll();
        BigInteger bi = (BigInteger) obj;
        return bi.intValue();
    }


    public List<MyySignin> findByAreaidAndSignintimeOrderBySignintime(Long areaid, String startdate, String enddate, Integer pagenum, Integer pagesize) {
        List<MyySignin> list = myySigninDao.findByAreaidAndSignintimeOrderBySignintime(areaid, startdate, enddate, (pagenum - 1) * pagesize, pagenum * pagesize, "1");
        if (list != null && list.size() > 0) {
            return list;
        } else {
            return null;
        }
    }

    public List<MyySignin> findByAreaidAndUseridsOrderBySignintime(Long areaid, List<Long> userids, Integer pagenum, Integer pagesize) {
        List<MyySignin> list = myySigninDao.findByAreaidAndUseridsOrderBySignintime(areaid, userids, "1");
        if (list != null && list.size() > 0) {
            return list;
        } else {
            return null;
        }
    }

    public List<MyySignin> findByAreaidAndUseridsOrderBySignintimePageable(Long areaid, List<Long> userids, Integer pagenum, Integer pagesize) {
//        //规格定义
//        Specification<MyySignin> specification = new Specification<MyySignin>() {
//
//            @Override
//            public Predicate toPredicate(Root<MyySignin> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//                List<Predicate> predicates = new ArrayList<>(); //所有的断言
//                if (areaid != 0) { //添加断言
//                    Predicate eqAreaid = cb.equal(root.get("areaid").as(Long.class), areaid);
//                    predicates.add(eqAreaid);
//                }
//                if (userids != null) {
//                    CriteriaBuilder.In<Long> inUserid = cb.in(root.get("userid").as(Long.class));
//                    for (Long userid : userids) {
//                        inUserid.value(userid);
//                    }
//                    predicates.add(inUserid);
//                }
//                return cb.and(predicates.toArray(new Predicate[0]));
//            }
//        };
//        //分页信息
//        Pageable pageable = new PageRequest(pagenum - 1, pagesize); //页码：前端从1开始，jpa从0开始，做个转换
//        //查询
//        return myySigninDao.findAll(specification, pageable);
        return null;
    }

    public List<MyySignin> findBySigntimeOrderBySignintime(Date starttime, Date endtime, Integer pagenum, Integer pagesize) {
        List<MyySignin> list = myySigninDao.findBySigntimeOrderBySignintime(starttime, endtime, (pagenum - 1) * pagesize, pagenum * pagesize, "1");
        if (list != null && list.size() > 0) {
            return list;
        } else {
            return null;
        }
    }

    public List<MyySignin> findAllOrderBySignintime(Integer pagenum, Integer pagesize) {
        List<MyySignin> list = myySigninDao.findAllOrderBySignintime((pagenum - 1) * pagesize, pagenum * pagesize, "1");
        if (list != null && list.size() > 0) {
            return list;
        } else {
            return null;
        }
    }

    public List<MyySignin> findAllByPlanidAndUserid(Long planid, Long userid) {
        List<MyySignin> list = myySigninDao.findAllByPlanidAndUseridAndState(planid, userid, "1");
        if (list != null && list.size() > 0) {
            return list;
        } else {
            return null;
        }
    }

    public MyySignin findByPlanidAndPointid(Long planid, Long pointid) {
        Optional<MyySignin> opt = myySigninDao.findByPlanidAndPointidAndState(planid, pointid, "1");
        return opt.isPresent() ? opt.get() : null;
    }

}
