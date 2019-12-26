package com.moyuaninfo.liveplay.controller;

import com.alibaba.fastjson.JSONObject;
import com.moyuaninfo.liveplay.MyConstants;
import com.moyuaninfo.liveplay.Tools;
import com.moyuaninfo.liveplay.entity.Camera;
import com.moyuaninfo.liveplay.entity.JsonResult;
import com.moyuaninfo.liveplay.entity.Livelog;
import com.moyuaninfo.liveplay.entity.Livestream;
import com.moyuaninfo.liveplay.services.CameraService;
import com.moyuaninfo.liveplay.services.LiveStreamService;
import com.moyuaninfo.liveplay.services.LivelogService;
import com.moyuaninfo.liveplay.vo.CameraVO;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.live.v20180801.LiveClient;
import com.tencentcloudapi.live.v20180801.models.DescribeLiveStreamStateRequest;
import com.tencentcloudapi.live.v20180801.models.DescribeLiveStreamStateResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Calendar;


@Api(value = "直播管理操作接口", tags = "直播管理操作接口")
@RestController
public class LiveplayController {

    public static int playerRemain = 5;

    @Autowired
    private CameraService cameraservice;
    @Autowired
    private LiveStreamService livestreamservice;
    @Autowired
    private LivelogService livelogService;


    /***
     * 获取推流地址
     * @param streamname  视频流别名
     * @param appname  app名称，默认live
     * @param key 鉴权key
     * @param txTime 过期时间
     * @return 播放地址（RTMP）
                rtmp://play.moyuantech.com/live/StreamName

                播放地址（FLV）
                http://play.moyuantech.com/live/StreamName.flv

                播放地址（HLS）
                http://play.moyuantech.com/live/StreamName.m3u8
     */
    private String getPlayUrl(String streamname, String appname, String key, long txTime, String model) {
        String playUrl = MyConstants.PLAY_DEMAIN;
        switch (MyConstants.PLAY_NETWORK_PROTOCOL) {
            case "RTMP":
                playUrl = "rtmp://" + playUrl + "/" + appname + "/" + streamname;
                break;
            case "FLV":
                playUrl = "http://" + playUrl + "/" + appname + "/" + streamname + ".flv";
                break;
            case "HLS":
                playUrl = "http://" + playUrl + "/" + appname + "/" + streamname + ".m3u8";
                break;
        }
        if (model != null && model.equalsIgnoreCase("long")) {
            playUrl += "?" + Tools.getSafeUrl(key, streamname, txTime);
        }
        return playUrl;
    }

    @ApiOperation(value = "开启摄像头直播推流", notes = "开启摄像头直播推流，需要高校ID和摄像头IP和摄像头通道channel，以及用户userID")
//	@ApiImplicitParams({ @ApiImplicitParam(name = "cameravo", value = "摄像头vo对象", required = true, dataType = "CameraVO") })
    @RequestMapping(value = "/liveplay/wsplay", method = RequestMethod.POST)
    public JsonResult<String> wsplay(@RequestBody CameraVO cameravo) {
        JsonResult<String> jsrt = null;
        if (playerRemain <= 0) {
            return new JsonResult<String>(0, "在线观看人数超过限额，请稍后再看", null);
        }
        String streamurl = null;
        Camera camera = null;
        try {
//            camera = cameraservice.findByAreaAndIpAndChannel(cameravo.getAreaid(), cameravo.getCamIp(), cameravo.getChannel());
            camera = cameraservice.findOne(cameravo.getId());
            Calendar cald = Calendar.getInstance();
            cald.add(Calendar.DATE, 1);
            streamurl = getPlayUrl(camera.getStreamname(), MyConstants.APPNAME, MyConstants.API_KEY_PLAY, cald.getTime().getTime(), "short");
            jsrt = new JsonResult<String>(0, "ok", streamurl);

            JSONObject taskobj = new JSONObject();
            taskobj.put("client", "org-" + cameravo.getDistrictid());
            taskobj.put("task", "pushstream");
            taskobj.put("msgtype", "request1");
            taskobj.put("rtsp", camera.getLink());
            taskobj.put("streamname", camera.getStreamname());
            taskobj.put("cameraid", camera.getId());
            taskobj.put("userid", cameravo.getUserid());
            taskobj.put("playstreamurl",streamurl);

            taskobj.put("api", "/stream/newPusher");
            taskobj.put("apiclient", "localservice");

            RestTemplate restTemplate = new RestTemplate();
            JsonResult<String> jsrt2 = restTemplate.postForObject(MyConstants.NETTY_SEND, taskobj, JsonResult.class);
            if (jsrt2.getCode() == 0) {
                System.out.println("开启直播发送成功");
                jsrt2.setMsg("开启直播发送成功");
            } else {
                System.out.println("开启直播发送失败");
                jsrt2.setMsg("开启直播发送失败");
            }

        } catch (Exception e) {
            jsrt = new JsonResult<String>(1, e.getClass().getName() + ":" + e.getMessage(), "");
        }

        //查询直播流是否起效
        if (camera != null && jsrt.getCode() == 0) {
            DescribeLiveStreamStateResponse resp = checkOnlineUser(camera.getStreamname());
            if (resp == null || !"active".equalsIgnoreCase(resp.getStreamState())) {
                jsrt = new JsonResult<String>(1, "直播未开启", "");
            }
        }

        if (jsrt.getCode() == 0) {
            playerRemain--;
        }
        return jsrt;
    }

    /***
     * 通过腾讯云SDK查询在线观看人数
     * @param streamname 推流名
     * @return
     */
    public DescribeLiveStreamStateResponse checkOnlineUser(String streamname){
        Credential cred = new Credential(MyConstants.COS_SECRETID, MyConstants.COS_SECRETKEY);
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("live.tencentcloudapi.com");
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);
        LiveClient client = new LiveClient(cred, "", clientProfile);
        DescribeLiveStreamStateResponse resp = null;
        DescribeLiveStreamStateRequest req = new DescribeLiveStreamStateRequest();
        req.setAppName(MyConstants.APPNAME);
        req.setDomainName(MyConstants.PLAY_DEMAIN);
        req.setStreamName(streamname);
        int coun = 0;
        while (coun < 15 && (resp == null || !"active".equalsIgnoreCase(resp.getStreamState()))) {
            // 通过client对象调用想要访问的接口，需要传入请求对象
            try {
                resp = client.DescribeLiveStreamState(req);
                Thread.sleep(200);
            } catch (TencentCloudSDKException | InterruptedException ex) {
                ex.printStackTrace();
            }
            coun++;
        }
        return resp;
    }

    @ApiOperation(value = "开启推流结果", notes = "推流回传结果")
//	@ApiImplicitParams({ @ApiImplicitParam(name = "args", value = "摄像头推流回传结果", required = true, dataType = "String") })
    @RequestMapping(value = "/liveplay/wsplayResult", method = RequestMethod.POST)
    public String wsplayResult(@RequestBody String args) {
        JSONObject msgobj = JSONObject.parseObject(args);
        Camera camera = null;
        if (msgobj.getString("result") != null) {
            camera = cameraservice.findOne(msgobj.getLong("cameraid"));
            if ("pushing".equalsIgnoreCase(msgobj.getString("result"))) {
                camera.setLinkstatus("1");
            }
            cameraservice.saveCamera(camera);

            livestreamservice.saveLiveStream(new Livestream(camera.getId(), camera.getIp(), camera.getName(), camera.getStreamname(), "1"));
            Livestream livestream = livestreamservice.findByCameraid(camera.getId());
            //保存看直播记录
			livelogService.saveLivelog(new Livelog(msgobj.getLong("userid")!=null?msgobj.getLong("userid"):0
                    ,livestream.getId(),msgobj.getString("playstreamurl")));
        }
        System.out.println("收到消息：" + args);
        return "Ok";
    }

    @ApiOperation(value = "关闭推流", notes = "关闭推流，需要实验室ID和摄像头IP")
//	@ApiImplicitParams({ @ApiImplicitParam(name = "cameravo", value = "摄像头vo对象", required = true, dataType = "CameraVO") })
    @RequestMapping(value = "/liveplay/shutpusher", method = RequestMethod.POST)
    public JsonResult<String> shutpusher(@RequestBody CameraVO cameravo) {
        JsonResult<String> jsrt = null;
        try {
            Camera camera = cameraservice.findByAreaAndIpAndChannel(cameravo.getAreaid(), cameravo.getCamIp(), cameravo.getChannel());
            JSONObject taskobj = new JSONObject();
            taskobj.put("client", "org-" + cameravo.getAreaid());
            taskobj.put("task", "shutpusher");
            taskobj.put("msgtype", "request1");
            taskobj.put("streamname", camera.getStreamname());
            taskobj.put("cameraid", camera.getId());

            taskobj.put("api", "/stream/shutPusher");
            taskobj.put("apiclient", "localservice");

            RestTemplate restTemplate = new RestTemplate();
            jsrt = restTemplate.postForObject(MyConstants.NETTY_SEND, taskobj, JsonResult.class);
            System.out.println(taskobj);
            if (jsrt.getCode() == 0) {
                System.out.println("关闭直播发送成功");
                jsrt.setMsg("关闭直播发送成功");
            } else {
                System.out.println("关闭直播发送失败");
                jsrt.setMsg("关闭直播发送失败");
            }
        } catch (Exception e) {
            jsrt = new JsonResult<String>(2, e.getClass().getName() + ":" + e.getMessage(), "");
        }
        return jsrt;
    }

    @ApiOperation(value = "关闭推流结果", notes = "关闭推流结果回传")
//	@ApiImplicitParams({ @ApiImplicitParam(name = "args", value = "回传信息", required = true, dataType = "String") })
    @RequestMapping(value = "/liveplay/shutPusherResult", method = RequestMethod.POST)
    public String shutPusherResult(@RequestBody String args) {
        JSONObject msgobj = JSONObject.parseObject(args);
        System.out.println(args);
        Camera camera = null;
        if (msgobj.getString("result") != null) {
            camera = cameraservice.findOne(msgobj.getLong("cameraid"));
            if ("shut".equalsIgnoreCase(msgobj.getString("result"))) {
                camera.setLinkstatus("2");
            }
            cameraservice.saveCamera(camera);
        }
        System.out.println("收到消息：" + args);
        return "Ok";
    }

//	@ApiOperation(value = "参数请求", notes = "参数请求回传")
////	@ApiImplicitParams({ @ApiImplicitParam(name = "args", value = "请求信息", required = true, dataType = "String") })
//	@RequestMapping(value = "/app/initConstants", method = RequestMethod.POST)
//	public String initConstants(@RequestBody String args) {
//		JSONObject msgobj = JSONObject.parseObject(args);
//		List<Constants> constantslist = constantsservice.findAll();
//		if(constantslist!=null && constantslist.size()>0){
//			msgobj.put("constantslist",constantslist);
//		}
//		RestTemplate restTemplate = new RestTemplate();
//		JsonResult<String> jsrt = restTemplate.postForObject(MyConstants.NETTY_SEND, msgobj, JsonResult.class);
//		if (jsrt.getCode()==0){
//			System.out.println("回传初始化参数发送成功");
//		}else{
//			System.out.println("回传初始化参数发送失败");
//		}
////		System.out.println("消息长度："+msgobj.toJSONString().length());
//		System.out.println("收到消息："+args);
//		return "OK";
//	}
}
