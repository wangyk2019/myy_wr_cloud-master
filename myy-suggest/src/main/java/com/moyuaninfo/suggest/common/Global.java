package com.moyuaninfo.suggest.common;

/**
 * @ClassName Global
 * @Description TODO
 * @Author zhaoGq
 * @Date 2019/11/1 15:09
 * @Version 1.0
 **/
public class Global {

    // 腾讯云
    public static final String TXCLOUD_SECRETID = "AKID3tZNK1wcjkeN6ci61uDH7lHbfU8ZajU6";
    public static final String TXCLOUD_SECRETKEY = "5vaAWbQ3zkGau7exqXfZGXggaIm0NCpD";
    public static final String TXCLOUD_BUCKET = "ap-shanghai";
    public static final String TXCLOUD_BUCKETNAME = "news-1258601646";
    public static final String TXCLOUD_PATH = "https://news-1258601646.cos.ap-shanghai.myqcloud.com";
    public static final String TXCLOUD_SUGGEST_PREFIX = "suggest";

    // 咨询建议图片路径截取字符约定
    public static String SUGGESTPHOTOPATH_SPLIT_APPOINT = ";";
    // 咨询建议图片路径对应key截取字符约定
    public static String SUGGESTPHOTOPATH_KEY_SPLIT_APPOINT = "myqcloud.com";

    // 用户角色
//    public static int GOVERNMENT_ADMIN = 1;  // 政府管理员
//    public static int GOVERNMENT_PEOPLE = 2;  // 政府人员
//    public static int THE_MASSES = 3;  // 人民群众
    public static String GOVERNMENT_ADMIN = "admin";  // 政府管理员
    public static String GOVERNMENT_PEOPLE = "user";  // 政府人员
    public static String THE_MASSES = "people";  // 人民群众
}
