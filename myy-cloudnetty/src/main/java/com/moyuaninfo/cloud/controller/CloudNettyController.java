package com.moyuaninfo.cloud.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.moyuaninfo.cloud.MyConstants;
import com.moyuaninfo.cloud.Tools;
import com.moyuaninfo.cloud.entity.JsonResult;
import com.moyuaninfo.cloud.netty.MyHttpHandler;
import com.moyuaninfo.cloud.netty.NettySocketHolder;
import com.moyuaninfo.cloud.vo.CameraVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Calendar;
import java.util.List;


@Api(value = "netty server",tags="netty服务器操作接口")
@RestController
public class CloudNettyController {

	@ApiOperation(value = "消息发送", notes = "云端发送消息")
	@RequestMapping(value = "/cloudNetty/sendMsg", method = RequestMethod.POST)
	public JsonResult<String> nettySend(
			@RequestBody JSONObject args) {
		JsonResult<String> jsrt = null;
		System.out.println("接受消息：" + JSON.toJSONString(args));
		try {
			String cId = args.getString("client");
			MyHttpHandler hander = NettySocketHolder.getHTTP(cId);
			hander.send(hander.chc,args,null,"String");
//			System.err.println("——————————回传结果—————————— ");
//			JSONObject resultobj = NettySocketHolder.getHTTP(cId).textMsg;
//			System.out.println(args+"************"+resultobj);
//			if(resultobj != null && resultobj.get("result") != null){
//				jsrt = new JsonResult<String>(0,"ok",null);
//			}else{
//				jsrt = new JsonResult<String>(1,"request failure", "failure");
//			}
		} catch (Exception e) {
			jsrt = new JsonResult<String>(2,e.getClass().getName() + ":" + e.getMessage(),"");
		}
		return new JsonResult(0,"");
	}

	@ApiIgnore
	@ApiOperation(value = "回传深度检测结果", notes = "回传深度检测结果")
	@RequestMapping(value = "/cloudNetty/sendBackResult", method = RequestMethod.POST)
	public JsonResult sendBackResult(
			@RequestParam(value = "ip") String ip,
			@RequestParam(value = "result") String result,
			@RequestParam(value = "colleageid") String colleageid,
			@RequestParam(value = "prelimwarnid") int prelimwarnid) {
		JsonResult<String> jsrt = null;
		try {
			JSONObject taskobj = new JSONObject();
			taskobj.put("client","colleage-"+colleageid);
			taskobj.put("task","detectionresult");
			taskobj.put("ip",ip);
			taskobj.put("prelimwarnid",prelimwarnid);
			taskobj.put("result",result);
			taskobj.put("msgtype","request1");
			MyHttpHandler hander = NettySocketHolder.getHTTP("colleage-"+colleageid);
			hander.send(hander.chc,taskobj,null,"String");
			System.err.println("——————————回传深度检测结果—————————— ");
			JSONObject resultobj = NettySocketHolder.getHTTP("colleage-"+colleageid).textMsg;
			System.out.println(taskobj+"************"+resultobj);
			if(resultobj != null && resultobj.get("result") != null){
				jsrt = new JsonResult<String>(0,"ok",null);
			}else{
				jsrt = new JsonResult<String>(1,"request failure", "failure");
			}
		} catch (Exception e) {
			jsrt = new JsonResult<String>(2,e.getClass().getName() + ":" + e.getMessage(),"");
		}
		return null;
	}

	/***
	 * 获取推流地址
	 * @param streamname  视频流别名
	 * @param appname  app名称，默认live
	 * @param key 鉴权key
	 * @param txTime 过期时间
	 * @return  播放地址（RTMP）
				rtmp://play.moyuantech.com/live/StreamName

				播放地址（FLV）
				http://play.moyuantech.com/live/StreamName.flv

				播放地址（HLS）
				http://play.moyuantech.com/live/StreamName.m3u8
	 */
	private String getPlayUrl(String streamname,String appname,String key, long txTime, String model) {
		String playUrl = MyConstants.PLAY_DEMAIN;
		switch (MyConstants.PLAY_NETWORK_PROTOCOL) {
		case "RTMP":
			playUrl = "rtmp://"+playUrl+"/"+appname+"/"+streamname;
			break;
		case "FLV":
			playUrl = "http://"+playUrl+"/"+appname+"/"+streamname+".flv";
			break;
		case "HLS":
			playUrl = "http://"+playUrl+"/"+appname+"/"+streamname+".m3u8";
			break;
		}
		if(model!=null && model.equalsIgnoreCase("long")){
			playUrl +="?"+Tools.getSafeUrl(key, streamname, txTime);
		}
		return playUrl;
	}

	@ApiIgnore
	@ApiOperation(value = "开启摄像头直播推流", notes = "开启摄像头直播推流，需要高校ID和摄像头IP")
	@ApiImplicitParams({ @ApiImplicitParam(name = "cameravo", value = "摄像头vo对象", required = true, dataType = "CameraVO") })
	@RequestMapping(value = "/cloudNetty/startLive", method = RequestMethod.POST)
	public JsonResult<String> startLive(@RequestBody CameraVO cameravo) {
		JsonResult<String> jsrt = null;
		try {
			JSONObject taskobj = new JSONObject();
			taskobj.put("client","colleage-"+cameravo.getColleageid());
			taskobj.put("task","pushstream");
			taskobj.put("msgtype","request1");
			taskobj.put("link",cameravo.getLink());
			taskobj.put("streamname",cameravo.getStreamname());
			taskobj.put("ip",cameravo.getCamIp());
			taskobj.put("camId",cameravo.getId());
			taskobj.put("camName",cameravo.getName());
			MyHttpHandler hander = NettySocketHolder.getHTTP("colleage-"+cameravo.getColleageid());
			hander.send(hander.chc,taskobj,null,"String");

			JSONObject resultobj = NettySocketHolder.getHTTP("colleage-"+cameravo.getColleageid()).textMsg;
			for (int i = 0; i < 4; i++) {
				if(resultobj != null && resultobj.get("result") != null){
					System.out.println("收到直播反馈消息"+resultobj);
					Thread.currentThread().sleep(1000);
					break;
				}else{
					Thread.currentThread().sleep(500);
					resultobj = NettySocketHolder.getHTTP("colleage-"+cameravo.getColleageid()).textMsg;
				}
			}

			if(resultobj != null && resultobj.get("result") != null && resultobj.getString("result").contains("pushing")){
				Calendar cald= Calendar.getInstance();
//                cald.add(Calendar.DATE, 1);
				String streamurl = getPlayUrl(cameravo.getStreamname(), MyConstants.APPNAME, MyConstants.API_KEY_PLAY, cald.getTime().getTime(),"short");
				jsrt = new JsonResult<String>(0,"ok", streamurl);
			}else{
				jsrt = new JsonResult<String>(1,"bad request", "bad request");
			}
			System.out.println("推流回应："+resultobj);
		} catch (Exception e) {
			jsrt = new JsonResult<String>(1,e.getClass().getName() + ":" + e.getMessage(),"");
		}
		return jsrt;
	}

	@ApiIgnore
	@ApiOperation(value = "关闭摄像头直播推流", notes = "关闭推流，需要实验室ID和摄像头IP")
	@ApiImplicitParams({ @ApiImplicitParam(name = "cameravo", value = "摄像头vo对象", required = true, dataType = "CameraVO") })
	@RequestMapping(value = "/cloudNetty/shutLive", method = RequestMethod.POST)
	public JsonResult<String> shutLive(@RequestBody CameraVO cameravo) {
		JsonResult<String> jsrt = null;
		try {
			JSONObject taskobj = new JSONObject();
			taskobj.put("client","colleage-"+cameravo.getColleageid());
			taskobj.put("task","shutpusher");
			taskobj.put("msgtype","request1");
			taskobj.put("param",cameravo.getLink());
			taskobj.put("ip",cameravo.getCamIp());
			MyHttpHandler hander = NettySocketHolder.getHTTP("colleage-"+cameravo.getColleageid());
			hander.send(hander.chc,taskobj,null,"WebSocket");
			Thread.currentThread().sleep(5000);
			JSONObject resultobj = NettySocketHolder.getHTTP("colleage-"+cameravo.getColleageid()).textMsg;
			if(resultobj != null && resultobj.get("result") != null){
				jsrt = new JsonResult<String>(0,"ok", String.valueOf(resultobj.get("result")));
			}else{
				jsrt = new JsonResult<String>(1,"failure", "failure");
			}
		} catch (Exception e) {
			jsrt = new JsonResult<String>(2,e.getClass().getName() + ":" + e.getMessage(),"");
		}
		return jsrt;
	}

	@ApiIgnore
	@ApiOperation(value = "组织信息配置更新", notes = "更新系统配置信息")
	@ApiImplicitParams({ @ApiImplicitParam(name = "args", value = "数据包", required = true, dataType = "String") })
	@RequestMapping(value = "/cloudNetty/sendConfig", method = RequestMethod.POST)
	public JsonResult<String> sendConfig(@RequestParam JSONObject args,@RequestParam long cId) {
		JsonResult<String> jsrt = null;
		try {
//			JSONObject taskobj = new JSONObject();
//			taskobj.put("client","colleage-"+cameravo.getColleageid());
//			taskobj.put("task","shutpusher");
//			taskobj.put("msgtype","request1");
//			taskobj.put("param",cameravo.getLink());
//			taskobj.put("ip",cameravo.getCamIp());
			MyHttpHandler hander = NettySocketHolder.getHTTP("colleage-"+cId);
			hander.send(hander.chc,args,null,"WebSocket");
			Thread.currentThread().sleep(5000);
			JSONObject resultobj = NettySocketHolder.getHTTP("colleage-"+cId).textMsg;
			if(resultobj != null && resultobj.get("result") != null){
				jsrt = new JsonResult<String>(0,"ok", String.valueOf(resultobj.get("result")));
			}else{
				jsrt = new JsonResult<String>(1,"failure", "failure");
			}
		} catch (Exception e) {
			jsrt = new JsonResult<String>(2,e.getClass().getName() + ":" + e.getMessage(),"");
		}
		return jsrt;
	}

}
