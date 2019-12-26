package com.moyuaninfo.suggest.service;

import java.util.Map;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author zhaoGq
 * @Date 2019/12/24 20:56
 * @Version 1.0
 **/
public interface UserService {

    /*
     * @Description //TODO
     * @Author zhaoGq
     * @Date 2019/12/24 21:01
     * @Param
     * @param userId
     * @Return java.util.Map<java.lang.String,java.lang.Object>
     **/
    Map<String, Object> getUserInfoByUserId(Long userId);
    
}
