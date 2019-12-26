package com.moyuan.cloud.feignClient;

import com.alibaba.fastjson.JSONObject;
import com.moyuan.cloud.VO.FindCameraByParams;
import com.moyuan.cloud.VO.PushParamIn;
import com.moyuan.cloud.pojo.JsonResult;
import com.moyuan.cloud.pojo.MyyCamera;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientFeignHystrix implements PushClient,DangerClient,CloudNettyClient,CameraClient{
    //打印日志
    private static final Logger print = LoggerFactory.getLogger(ClientFeignHystrix.class);

    @Override
    public JsonResult pushMsgCode(PushParamIn pushParamIn) {
        return new JsonResult<>(1,"推送服务出现故障。。");
    }

    @Override
    public JsonResult deleteByBU(long areabottomid, long userid) {
        return new JsonResult<>(1,"险情服务出现故障。。");
    }

    @Override
    public JsonResult<String> nettySend(JSONObject args) {
        return new JsonResult<>(1,"netty服务出现故障。。");
    }

    @Override
    public JsonResult<List<MyyCamera>> findByParams(FindCameraByParams findCameraByParams) {
        return new JsonResult<>(1,"摄像头服务出现故障。。");
    }

    @Override
    public JsonResult<List<MyyCamera>> findAllByDistrict(long id) {
        return new JsonResult<>(1,"摄像头服务出现故障。。");
    }

    @Override
    public JsonResult<List<MyyCamera>> findAllByAreabottomid(long id) {
        return new JsonResult<>(1,"摄像头服务出现故障。。");
    }
}