package com.moyuaninfo.liveplay;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Random;

public class Tools {

	/****
	 * 组装rtsp地址：
	 * rtsp://admin:admin123456@10.192.44.101:554/h264/ch/1
	 * 解释下
	 * admin:设备登录账号 
	 * admin123456:设备登录密码 
	 * 10.192.44.101:设备的内网IP地址 
	 * 554:默认端口
	 * h264:是视频流编码格式 
	 * ch1:通道号
	 ****/
	public static String getRtspUrl(String username, String password, String ip, String port, String channel,
									String stream) {
		String rtspurl = "rtsp://" + username + ":" + password + "@" + ip;
		if (port != null && !"".equals(port)) {
			rtspurl += ":" + port;
		}
		if (stream != null && !"".equals(stream)) {
			rtspurl += "/" + stream;
		} else {
			rtspurl += "/Streaming";
		}
		if (channel != null && channel.contains("ch1")) {
			rtspurl += "/ch1/sub/av_stream";
		}else if (channel != null && !"".equals(channel)) {
			rtspurl += "/Channels/" + channel;
		} else {
			rtspurl += "/Channels/1";
		}
		return rtspurl;
	}
	/****
	 * 拆解rtsp地址：
	 * rtsp://   admin  :  admin123456  @  10.192.44.101  :  554  /  h264  /  ch1  
	 * 数据保存在map中，根据key获取
	 * key{username,password,ip,port,streamname,channel}
	 */
	public static HashMap<String,String> decodeRtspUrl(String rtspurl) {
		HashMap<String,String> urlmap = new HashMap<String,String>();
		String[] temp = rtspurl.split("@");
		String[] temp1 = temp[0].split(":");
		urlmap.put("username", temp1[1].substring(2));
		urlmap.put("password", temp1[2]);
		String[] temp2 = temp[1].split("/");
		temp1=null;temp1=temp2[0].split(":");
		urlmap.put("ip", temp1[0]);
		urlmap.put("port", temp1[1]);
		urlmap.put("streamname", temp2[1]);
		urlmap.put("channel", temp2[2]);
		return urlmap;
	}
	
	
	private static final char[] DIGITS_LOWER =
        {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

	  /*
	  * KEY+ streamName + txTime
	  */
	public static String getSafeUrl(String key, String streamName, long txTime) {
	        String input = new StringBuilder().
	                          append(key).
	                          append(streamName).
	                          append(Long.toHexString(txTime).toUpperCase()).toString();
	
	        String txSecret = null;
	        try {
	              MessageDigest messageDigest = MessageDigest.getInstance("MD5");
				  txSecret  = byteArrayToHexString(
				              messageDigest.digest(input.getBytes(StandardCharsets.UTF_8)));
				} catch (NoSuchAlgorithmException e) {
				      e.printStackTrace();
				}

        return txSecret == null ? "" :
	              new StringBuilder().
	              append("txSecret=").
	              append(txSecret).
	              append("&").
	              append("txTime=").
	                          append(Long.toHexString(txTime).toUpperCase()).
	                          toString();
	}
	
	private static String byteArrayToHexString(byte[] data) {
		char[] out = new char[data.length << 1];

		for (int i = 0, j = 0; i < data.length; i++) {
			  out[j++] = DIGITS_LOWER[(0xF0 & data[i]) >>> 4];
			  out[j++] = DIGITS_LOWER[0x0F & data[i]];
		}
		return new String(out);
	}

	public static String getRandomString(int length) {
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(62);
			sb.append(str.charAt(number));
		}
		return sb.toString();
	}

	/****
	 * 无文件则创建，有文件则增量写
	 * @param fileName
	 * @param content
	 */
	public static void writefile(String fileName, String content) {
		try {
			// 打开一个随机访问文件流，按读写方式
			RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
			// 文件长度，字节数
			long fileLength = randomFile.length();
			// 将写文件指针移到文件尾。
			randomFile.seek(fileLength);
			randomFile.write((content + "\r\n").getBytes());
			randomFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
