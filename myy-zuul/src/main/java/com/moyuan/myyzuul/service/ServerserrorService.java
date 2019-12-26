package com.moyuan.myyzuul.service;

import com.moyuan.myyzuul.dao.ServersErrorDao;
import com.moyuan.myyzuul.pojo.ServersError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerserrorService {

    @Autowired
    ServersErrorDao serversErrorDao;

    public void addObj(ServersError notice) {
        serversErrorDao.save(notice);
    }


}
