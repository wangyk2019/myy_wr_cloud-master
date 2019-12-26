package com.moyuaninfo.waterinfo.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * 生成随机数util
 * */
public class GitNumberUtil {
    public static Integer getRandomNumber(int number){
        //生成0-size之间的随机数（不包含size）
        Random rand = new Random();
        if(number<=1){
            return 0;
        }else{
            int i = rand.nextInt(number);
            return i;
        }

    }

    public static Date getNineDate(){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 9);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        Date dateTime = c.getTime();
        return dateTime;
    }

    public static Date getZeroDate(){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 10);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        Date dateTime = c.getTime();
        return dateTime;
    }

    public static Date getSubtractDay(Date date,int n){
        long time = date.getTime();
        //一天代表的毫秒数
        int oneDayTime = (24*60*60*1000)*n;
        time -= oneDayTime;
        Date dateTime = new Date(time);
        return dateTime;
    }
    //public static void main(String[] args) {
    //    Calendar c = Calendar.getInstance();
    //    c.set(Calendar.HOUR_OF_DAY, 9);
    //    c.set(Calendar.MINUTE, 0);
    //    c.set(Calendar.SECOND, 0);
    //    Long startTimeL = c.getTime().getTime();
    //
    //    //一天代表的毫秒数
    //    int oneDayTime = 24*60*60*1000;
    //    startTimeL -= oneDayTime;
    //    Date date = new Date(startTimeL);
    //    System.out.println(date);
    //}
}
