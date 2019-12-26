package com.moyuan.cloud.system.service;

import com.moyuan.cloud.system.entity.MyyLoginLog;
import com.moyuan.cloud.system.repository.LoginInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyyLoginLogService {

    @Autowired
    private LoginInfoRepository repository;

    public boolean saveLoginLog(MyyLoginLog log){
        MyyLoginLog r = repository.save(log);
        if (r != null) {
            return true;
        }else
            return false;
    }

    public MyyLoginLog findById(Long id){
        Optional<MyyLoginLog> opt = repository.findById(id);
        return opt.isPresent()?opt.get():null;
    }
}
