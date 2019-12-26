package com.moyuan.cloud;

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

	public static String NETTY_SEND = "http://127.0.0.1:2222/cloudNetty/sendMsg";


	public static HashMap<String, HashMap<String, String>> WARNCONFIG = new HashMap<String, HashMap<String, String>>();

	public static String DETECTONE = null;

	public static String BUCKETURL = null;
}
