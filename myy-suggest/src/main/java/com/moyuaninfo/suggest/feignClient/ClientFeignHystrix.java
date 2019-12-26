package com.moyuaninfo.suggest.feignClient;

import com.moyuaninfo.suggest.model.JsonResult;
import com.moyuaninfo.suggest.model.MyyUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ClientFeignHystrix implements UserClient{

    //打印日志
    private static final Logger print = LoggerFactory.getLogger(ClientFeignHystrix.class);

    @Override
    public JsonResult<MyyUser> findUser(long id)  {
        return new JsonResult<>(1,"获取用户信息出现异常。。");
    }
}