package com.moyuan.signin.service;

import com.moyuan.signin.dao.MyySigninPointDao;
import com.moyuan.signin.pojo.MyySigninPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MyySigninPointService {

    @Autowired
    private MyySigninPointDao MyySigninPointDao;

    @Transactional
    public boolean saveOrUpdate(MyySigninPoint area) {
        MyySigninPoint s = MyySigninPointDao.save(area);
        if (s != null) {
            return true;
        } else {
            return false;
        }
    }

    public MyySigninPoint findById(Long id) {
        Optional<MyySigninPoint> opt = MyySigninPointDao.findById(id);
        return opt.isPresent() ? opt.get() : null;
    }

    public List<MyySigninPoint> findByParentArea(Long area) {
        List<MyySigninPoint> list = MyySigninPointDao.findAllByParent(area);
        if (list != null && list.size() > 0) {
            return list;
        } else {
            return null;
        }
    }
}
