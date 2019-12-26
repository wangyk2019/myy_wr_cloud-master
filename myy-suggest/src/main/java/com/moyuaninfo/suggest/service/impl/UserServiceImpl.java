package com.moyuaninfo.suggest.service.impl;

import com.moyuaninfo.suggest.dao.SuggestMapper;
import com.moyuaninfo.suggest.dao.UserMapper;
import com.moyuaninfo.suggest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author zhaoGq
 * @Date 2019/12/24 20:56
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /*
     * @Description //TODO
     * @Author zhaoGq
     * @Date 2019/12/24 21:03
     * @Param
     * @param userId
     * @Return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @Override
    public Map<String, Object> getUserInfoByUserId(Long userId) {
        return userMapper.getUserInfoByUserId(userId);
    }
}
