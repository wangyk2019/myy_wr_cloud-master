package com.moyuaninfo.liveplay;

import java.util.HashMap;

public class MyConstants {

	/***
	 * 推流地址
	 */
	public static String PUSH_DEMAIN = "wrpush.moyuantech.com";
	/***
	 * 播放地址
	 */
	public static String PLAY_DEMAIN = "wrplay.moyuantech.com";
	/***
	 * 鉴权KEY
	 */
	public static String API_KEY_PLAY = "6e865271d6ec696e9d56975eeb97562d";
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
	public static String PLAY_NETWORK_PROTOCOL = "FLV";
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

	/***
	 * 云存储ID
	 */
	public static String COS_SECRETID = "AKID3tZNK1wcjkeN6ci61uDH7lHbfU8ZajU6";
	/***
	 * 云存储key
	 */
	public static String COS_SECRETKEY = "5vaAWbQ3zkGau7exqXfZGXggaIm0NCpD";
}
