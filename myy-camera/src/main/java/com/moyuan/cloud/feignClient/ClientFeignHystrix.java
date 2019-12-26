package com.moyuan.cloud.feignClient;

import com.alibaba.fastjson.JSONObject;
import com.moyuan.cloud.pojo.JsonResult;
import com.moyuan.cloud.pojo.MyyAreabottom;
import com.moyuan.cloud.pojo.MyyAreaone;
import com.moyuan.cloud.pojo.MyyAreatwo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ClientFeignHystrix implements DangerClient,BaseServerClient,CloudNettyClient {
    //打印日志
    private static final Logger print = LoggerFactory.getLogger(ClientFeignHystrix.class);


    @Override
    public JsonResult delayCheck(long orgid) {
        return new JsonResult<>(1,"险情服务出现故障。。");
    }

    @Override
    public JsonResult addCamWarn_cam(long orgid, long cameraid) {
        return new JsonResult<>(1,"险情服务出现故障。。");
    }

    @Override
    public JsonResult<String> nettySend(JSONObject args) {
        return new JsonResult<>(1,"险情服务出现故障。。");
    }

    @Override
    public JsonResult<MyyAreabottom> findAreabottom(long id) {
        return new JsonResult<>(1,"基础服务出现故障。。");
    }

    @Override
    public JsonResult<MyyAreatwo> findAreatwo(long id) {
        return new JsonResult<>(1,"基础服务出现故障。。");
    }

    @Override
    public JsonResult<MyyAreaone> findAreaone(long id) {
        return new JsonResult<>(1,"基础服务出现故障。。");
    }
}