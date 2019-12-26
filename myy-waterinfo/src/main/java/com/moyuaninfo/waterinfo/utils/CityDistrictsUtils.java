package com.moyuaninfo.waterinfo.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.SecureRandom;
import java.util.Date;


/**
 * 通过https方式请求数据
 * */



public class CityDistrictsUtils {
    /*  * 处理https GET/POST请求  * 请求地址、参数  * */
    public static String httpsRequest(String requestUrl){
        StringBuffer buffer=null;
        try{
            //创建SSLContext
            SSLContext sslContext=SSLContext.getInstance("SSL");
            TrustManager[] tm={new MyX509TrustManager()};
            //初始化
            sslContext.init(null, tm, new SecureRandom());;
            //获取SSLSocketFactory对象
            SSLSocketFactory ssf=sslContext.getSocketFactory();
            URL url=new URL(requestUrl);
            HttpsURLConnection conn=(HttpsURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            //设置当前实例使用的SSLSoctetFactory
            conn.setSSLSocketFactory(ssf);
            conn.connect();
            //读取服务器端返回的内容
            InputStream is=conn.getInputStream();
            InputStreamReader isr=new InputStreamReader(is,"utf-8");
            BufferedReader br=new BufferedReader(isr);
            buffer=new StringBuffer();
            String line=null;
            while((line=br.readLine())!=null){
                buffer.append(line);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }

    public static void main(String[] args) throws IOException {
        //获取天气数据url
        String requestUrl = "https://restapi.amap.com/v3/weather/weatherInfo?city=110101&key=5d4d1362daa1f6ce55f36a8ec860ce1f";
        //通过url发送https请求，返回天气数据
        String aa = httpsRequest(requestUrl);
        System.out.println(aa);
        JSONObject jsonObject = JSONObject.parseObject(aa);
        System.out.println(jsonObject);
        //获取流量数据请求
        //当前unix时间戳
        //String timeUnix = Long.toString(new Date().getTime());
        //获取Signature参数

        //Signature = HexEncode(HMAC_SHA256(SecretSigning, StringToSign));

        //String requestUrl = "https://live.tencentcloudapi.com/?Action=DescribeBillBandwidthAndFluxList" +
        //        "&Version=2018-08-01"+
        //        "&Timestamp="+timeUnix+
        //        "&Nonce=10086"+
        //        "&SecretId=AKIDCNJ4cUTXhcK5rZQJxRgj2iCVnz2R6cT4"+
        //        "&signature=e1d6f5f3ea4f54bbb336c021490165ea490fa650b0e9f6daf978f2f651293c6b"+
        //        "&StartTime=2019-12-01 00:00:00" +
        //        "&EndTime=2019-12-14 00:10:00";

    }

}

