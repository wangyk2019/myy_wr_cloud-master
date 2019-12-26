package com.moyuan.myyzuul.feign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.List;

@Component
public class Fallback implements SecurityClient {
    //打印日志
    private static final Logger print = LoggerFactory.getLogger(Fallback.class);

    @Override
    public boolean auth(String token, String uri) {
        return false;
    }

    @Override
    public boolean getToken(String token) {
        return false;
    }
}