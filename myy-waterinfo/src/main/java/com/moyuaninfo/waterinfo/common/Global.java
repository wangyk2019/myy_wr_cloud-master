package com.moyuaninfo.waterinfo.common;

/**
 * @ClassName Global
 * @Description 公共属性
 * @Author zhaoGq
 * @Date 2019/12/2 14:49
 * @Version 1.0
 **/
public class Global {

    // 区域河道id（比如：马溪、锦溪），根据db_river库中myy_device_list表中areaone_id字段来定，目前暂定。
    public static final Integer AREAONE_ID = 67;

    // 水质参数类型
    public static final String[] WATER_QUALITY_TYPE = {"数字式ph","温度","数字式浊度","数字式溶氧","氨氮","总磷","高锰酸盐"};

    // 水质参数类型请求报文
    public static final String[] WATER_QUALITY_TYPE_REQUEST = {"060300000002","060300040002","070300000002","080300060002","090300000002","0a0300000002","0b0300000002"};

    // 请求设备与设备响应的时间差（秒）
    public static final Long REQUEST_RESPONSE_TIME_DIFFERENCE = 1*10L;

    // 请求间隔时间（毫秒）
    public static final Long REQUEST_TIME_DIFFERENCE = 5*60*1000L;

    // 水质参数类型
    public static final String NUMBERS_PH = "数字式ph";
    public static final String NUMBERS_TEMPERATURE = "温度";
    public static final String NUMBERS_DIMNESS = "数字式浊度";
    public static final String NUMBERS_DISSOLVED_OXYGEN = "数字式溶氧";
    public static final String NUMBERS_AMMONIA_NITROGEN = "氨氮";
    public static final String NUMBERS_TOTAL_PHOSPHORUS = "总磷";
    public static final String NUMBERS_PERMANGANATE = "高锰酸盐";

//    // 数字式ph(modbus请求报文)
//    public static final byte[] NUMBERS_PH = {06,03,00,00,00,02};
//
//    // 温度(modbus请求报文)
//    public static final byte[] NUMBERS_TEMPERATURE = {06,03,00,04,00,02};
//
//    // 数字式浊度(modbus请求报文)
//    public static final byte[] NUMBERS_DIMNESS = {07,03,00,00,00,02};
//
//    // 数字式溶氧(modbus请求报文)
//    public static final byte[] NUMBERS_DISSOLVED_OXYGEN = {8,03,00,06,00,02};
//
//    // 氨氮(modbus请求报文)
//    public static final byte[] NUMBERS_AMMONIA_NITROGEN = {9,03,00,00,00,02};
//
//    // 总磷(modbus请求报文)
//    public static final byte[] NUMBERS_TOTAL_PHOSPHORUS = {10,03,00,00,00,02};
//
//    // 高锰酸盐(modbus请求报文)
//    public static final byte[] NUMBERS_PERMANGANATE = {11,03,00,00,00,02};


}
