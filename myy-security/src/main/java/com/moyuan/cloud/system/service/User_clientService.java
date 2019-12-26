package com.moyuan.cloud.system.service;

import com.moyuan.cloud.system.entity.MyyUserClientid;
import com.moyuan.cloud.system.repository.User_ClientidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class User_clientService {

    @Autowired
    private User_ClientidRepository repository;

    @Transactional(rollbackOn = Exception.class)
    public void save(MyyUserClientid entity) {
        repository.save(entity);
    }

    public MyyUserClientid findById(Long id) {
        Optional<MyyUserClientid> opt = repository.findById(id);
        return opt.isPresent() ? opt.get() : null;
    }

    public MyyUserClientid findByUserid(long userid) {
        Optional<MyyUserClientid> opt = repository.findByUserid(userid);
        return opt.isPresent() ? opt.get() : null;
    }

    public List<MyyUserClientid> findClientidsByUserids(List<String> userids) {
        List<MyyUserClientid> opt = repository.findAllByUseridIn(userids);
        return opt;
    }

    public List<MyyUserClientid> findClientidAll(long orgid) {
        List<MyyUserClientid> opt = repository.findAllByDistrictid(orgid);
        return opt;
    }


}
