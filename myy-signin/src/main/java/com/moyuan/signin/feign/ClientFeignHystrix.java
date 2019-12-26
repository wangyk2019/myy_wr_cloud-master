package com.moyuan.signin.feign;

import com.moyuan.signin.pojo.JsonResult;
import com.moyuan.signin.pojo.MyyUser;
import com.moyuan.signin.vo.FindAllUserByParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientFeignHystrix implements BaseServerClient {
    //打印日志
    private static final Logger print = LoggerFactory.getLogger(ClientFeignHystrix.class);


    @Override
    public JsonResult<MyyUser> findUser(long id) {
        return new JsonResult<>(1, "基础配置服务出现故障。。");
    }

    @Override
    public JsonResult<List<MyyUser>> findUsersByParams(FindAllUserByParams findAllUserByParams) {
        return new JsonResult<>(1, "基础配置服务出现故障。。");
    }


}