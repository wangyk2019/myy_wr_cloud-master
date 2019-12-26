package com.moyuaninfo.cloud.feignClient;
import com.alibaba.fastjson.JSONObject;
import com.moyuaninfo.cloud.VO.*;
import com.moyuaninfo.cloud.pojo.*;
import com.moyuaninfo.cloud.common.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.List;

@Component
public class ClientFeignHystrix implements BaseServerClient,CameraClient,PushClient,CloudNettyClient,LivePlayClient {
    //打印日志
    private static final Logger print = LoggerFactory.getLogger(ClientFeignHystrix.class);

    @Override
    public JsonResult<MyyAreaone> findAreaone(long id) {
        return new JsonResult<>(1,"基础配置服务出现故障。。");
    }

    @Override
    public JsonResult<List<MyyAreaone>> findAreaonesParam(@Valid FindAllAreaByParams findAllAreaByParams) {
        return new JsonResult<>(1,"基础配置服务出现故障。。");
    }

    @Override
    public JsonResult<List<MyyAreaone>> findAreaones(FindAllAreaIn findAllAreaIn) {
        return new JsonResult<>(1,"findAreaones调用失败。。");
    }

    @Override
    public JsonResult<MyyAreatwo> findAreatwo(long id) {
        return new JsonResult<>(1,"基础配置服务出现故障。。");
    }

    @Override
    public JsonResult<MyyAreabottom> findAreabottom(long id) {
        return new JsonResult<>(1,"基础配置服务出现故障。。");
    }


    @Override
    public JsonResult<MyyUser> findUser(long id) {
        return new JsonResult<>(1,"基础配置服务出现故障。。");
    }

    @Override
    public JsonResult<MyyDistrict> findDistrict(long id) {
        return new JsonResult<>(1,"基础配置服务出现故障。。");
    }

    @Override
    public JsonResult<MyyWarntype> findWarntypeByWarnen(FindAllIn findAllIn) {
        return new JsonResult<>(1,"基础配置服务出现故障。。");
    }

    @Override
    public JsonResult<List<WarninfoOut>> findWarnsByOrgid(FindAllIn findAllIn) {
        return new JsonResult<>(1,"基础配置服务出现故障。。");
    }

    @Override
    public JsonResult<MyyWarntype> findWarnById(FindAllIn findAllIn) {
        return new JsonResult<>(1,"findWarnById查询失败。。");
    }

    @Override
    public JsonResult<List<MyyAreabottom>> myyAreabottomsOrgid(long orgId) {
        return new JsonResult<>(1,"myyAreabottomsOrgid查询失败。。");
    }

    @Override
    public JsonResult<MyyCamera> findById(long id) {
        return new JsonResult<>(1,"摄像机服务出现故障。。");
    }

    @Override
    public JsonResult<List<MyyCamera>> findAllByDistrict(long id) {
        return new JsonResult<>(1,"摄像机服务出现故障。。");
    }

    @Override
    public JsonResult<List<MyyCamera>> findAllByCameraAndStatus(long colleageid, String status) {
        return new JsonResult<>(1,"摄像机服务出现故障。。");
    }

    @Override
    public JsonResult pushMsg(PushParamIn paramIn) {
        return new JsonResult<>(1,"推送服务出现故障。。");
    }

    @Override
    public JsonResult pushPhone(PushParamIn pushParamIn) {
        return new JsonResult<>(1,"推送服务出现故障。。");
    }

    @Override
    public JsonResult pushToAppsByUserid(PushParamIn pushParamIn) {
        return new JsonResult<>(1,"推送服务出现故障。。");
    }

    @Override
    public JsonResult<String> nettySend(JSONObject args) {
        return new JsonResult<>(1,"推送服务出现故障。。");
    }

    @Override
    public JsonResult<String> wsplay(CameraVO cameravo) {
        return new JsonResult<>(1,"直播服务出现故障。。");
    }
}