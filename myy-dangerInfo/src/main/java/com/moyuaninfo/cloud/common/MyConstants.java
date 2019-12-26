package com.moyuaninfo.cloud.common;


public class MyConstants {

    /***
     * 播放流的网络协议
     */
    public static final String[] NETWORK_PROTOCOL = {"RTMP", "FLV", "HLS", "RTSP"};
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
    public static String PLAY_NETWORK_PROTOCOL = "RTMP";
    /***
     * 推送流的网络协议
     */
    public static String PUSH_NETWORK_PROTOCOL = "RTMP";
    /***
     * 云存储ID
     */
    public static String COS_SECRETID = null;
    /***
     * 云存储key
     */
    public static String COS_SECRETKEY = null;
    /***
     * 存储区域
     */
    public static String REGION = null;
    /***
     * 存储桶
     */
    public static String BUCKET = null;
    /***
     * 服务器所在高校代码
     */
    public static Long colleageid = 0l;
    /***
     * 云端服务器websocket
     */
    public static String NETTYHOST = null;

    public static int NETTYPORT = 0;

    public static String WEB_SOCKET_URL = "ws://localhost:10086/cloudsocket";
    public static String WEBSOCKET_STR = "cloudsocket";
    public static String UPGRADE_STR = "upgrade";
    public static int OK_CODE = 200;

    public static String HTTP_CODEC = "http-codec";
    public static String AGGREGATOR = "aggregator";
    public static String HTTP_CHUNKED = "http-chunked";
    public static String HANDLER = "handler";
    public static int MAX_CONTENT_LENGTH = 65536;

    public static String EASYPUSHERSTART = "/api/v1/stream/start?url=";

    public static String EASYPUSHERSHUT = "/api/v1/stream/stop?id=";

    public static String EASYPUSHERLIST = "/api/v1/pushers";

    public static String EASYHOST = null;

    public static String PHOST = null;

    public static String PPUSHERSTART = "/pusher/pushStreamSoEasy";

    public static String PPUSHERSHUT = "/pusher/shutPusherSoEasy";

    public static String TESTCAMERA = "/pusher/testCamera";

    public static String GOHOST = null;

    public static String PUSHERTYPE = null;

    public static String DEEPFRAMERESULT = null;

    public static String OS = null;

    public static int FILEUPLOADPOOLSIZE = 0;

    public static int RECORDTIMELENGTH = 5;

    public static String RECORDFOLD = null;

    public static String FIREWARN = null;
    public static String MOVEWARN = null;
    public static String SHAPEWARN = null;
    public static String STAYWARN = null;
    public static String COVERWARN = null;
}
