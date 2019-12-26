package com.moyuaninfo.cloud;

import java.util.HashMap;

public class MyConstants {

	/***
	 * 推流地址
	 */
	public static String PUSH_DEMAIN = null;
	/***
	 * 播放地址
	 */
	public static String PLAY_DEMAIN = null;
	/***
	 * 鉴权KEY
	 */
	public static String API_KEY_PLAY = null;
	/***
	 * 视频宽度
	 */
	public static int VIDEO_WIDTH = 480;
	/***
	 * 视频高度
	 */
	public static int VIDEO_HEIGHT = 272;
	/***
	 * 视频转码封装格式，如果是推送到rtmp就必须是flv封装格式
	 */
	public static String FORMATTYPE = "flv";
	/***
	 * 码率
	 */
	public static double FRAMERATE = 25;
	/***
	 * 播放流的网络协议
	 */
	public static final String[] NETWORK_PROTOCOL = {"RTMP","FLV","HLS","RTSP"};
	/***
	 * 播放流的网络协议
	 */
	public static String PLAY_NETWORK_PROTOCOL = "HLS";
	/***
	 * 推送流的网络协议
	 */
	public static String PUSH_NETWORK_PROTOCOL = "RTMP";
	/***
	 * 播放流的appname
	 */
	public static String APPNAME = "live";

	public static String WEB_SOCKET_URL = "ws://localhost:10086/CloudScoket";
	public static String WEBSOCKET_STR = "CloudScoket";
	public static String UPGRADE_STR = "Upgrade";
	public static int OK_CODE = 200;

	public static String HTTP_CODEC = "http-codec";
	public static String AGGREGATOR = "aggregator";
	public static String HTTP_CHUNKED = "http-chunked";
	public static String HANDLER = "handler";
	public static int MAX_CONTENT_LENGTH = 65536;

	public static HashMap<String, HashMap<String, String>> WARNCONFIG = new HashMap<String, HashMap<String, String>>();

	public static String DETECTONE = null;

	/**
	 * 摄像头服务问题
	 */
	public static String CAMAddr = "http://127.0.0.1:8039";
	/**
	 * 直播服务地址
	 */
	public static String LIVEURL = "http://127.0.0.1:8072";
	/**
	 * 险情服务地址
	 */
	public static String WARNURL = "http://127.0.0.1:8039";

	public static String BUCKETURL = null;

	public static  HashMap<String,String> clientsmap = new HashMap<String, String>();
}
