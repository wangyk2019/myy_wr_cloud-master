package com.moyuaninfo.suggest.dao;

import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @ClassName UserMapper
 * @Description TODO
 * @Author zhaoGq
 * @Date 2019/12/24 20:56
 * @Version 1.0
 **/
@Repository("userMapper")
public interface UserMapper {

    /*
     * @Description //TODO
     * @Author zhaoGq
     * @Date 2019/12/24 21:03
     * @Param
     * @param userId
     * @Return java.util.Map<java.lang.String,java.lang.Object>
     **/
    Map<String, Object> getUserInfoByUserId(Long userId);


}
