package com.moyuan.signin;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Tools {

    public static String getTimeString(String timepattern) {
        Calendar cald = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(timepattern);
        return sdf.format(cald.getTime());
    }

    public static String getTimeStringByDate(Date date, String timepattern) {
        Calendar cald = Calendar.getInstance();
        cald.setTime(date);
        SimpleDateFormat sdf = new SimpleDateFormat(timepattern);
        return sdf.format(cald.getTime());
    }

    public static Date getDateByTimeString(String timestring, String timepattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(timepattern);
        return sdf.parse(timestring);
    }

    private static final double EARTH_RADIUS = 6378137;//赤道半径

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    public static double GetDistance(double lon1, double lat1, double lon2, double lat2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lon1) - rad(lon2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        return s;//单位米
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

    /**
     * 判断当前日期是星期几<br>
     * <br>
     * @param calendar 修要判断的时间<br>
     * @return dayForWeek 判断结果<br>
     * @Exception 发生异常<br>
     */
    public static int getDayForWeek(Calendar calendar){
        int dayForWeek = 0;
        if(calendar.get(Calendar.DAY_OF_WEEK) == 1){
            dayForWeek = 7;
        }else{
            dayForWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayForWeek;
    }


    /**
     * 获取星期
     *
     * @return
     */
    public static String getDayOfWeek(Calendar calendar) {
        String Dw = new String();
        int i = calendar.get(Calendar.DAY_OF_WEEK);
        switch (i) {
            case 1:
                Dw = "星期日";
                break;
            case 2:
                Dw = "星期一";
                break;
            case 3:
                Dw = "星期二";
                break;
            case 4:
                Dw = "星期三";
                break;
            case 5:
                Dw = "星期四";
                break;
            case 6:
                Dw = "星期五";
                break;
            case 7:
                Dw = "星期六";
                break;
        }

        return Dw;
    }

    /**
     * 获取指定日期所在周的周一
     *
     * @param today 日期
     * @param weeks 间隔周数  -1 前一周  -2 前二周   0 当前周 1 下一周 2 下二周
     */
    public static Calendar getFirstDayOfWeek(Calendar today, int weeks) {
        Calendar calendar = getStartDate(today);
        if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
            calendar.add(Calendar.DAY_OF_MONTH, -1);
        }
        calendar.add(Calendar.DATE, 7*weeks);
        calendar.add(Calendar.DATE, calendar.getFirstDayOfWeek() - calendar.get(Calendar.DAY_OF_WEEK) + 1);
        return calendar;
    }

    /**
     * 获取指定日期所在周的周日
     *
     * @param today 日期
     * @param weeks 间隔周数  -1 前一周  -2 前二周   0 当前周 1 下一周 2 下二周
     */
    public static Calendar getLastDayOfWeek(Calendar today, int weeks) {
        Calendar calendar = getEndDate(today);
        // 如果是周日直接返回
        if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
            return calendar;
        }
//        System.out.println(today.get(Calendar.DAY_OF_WEEK));
        calendar.add(Calendar.DATE, 7*weeks);
        calendar.add(Calendar.DATE, 7 - calendar.get(Calendar.DAY_OF_WEEK) + 1);
        return calendar;
    }

    /**
     * 获得当天的起始时间
     *
     * @return
     */
    public static Calendar getStartDate(Calendar today) {
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);
        return today;
    }

    /**
     * 获取当天截止时间
     *
     * @return
     */
    public static Calendar getEndDate(Calendar endToday) {
        endToday.set(Calendar.HOUR_OF_DAY, 23);
        endToday.set(Calendar.MINUTE, 59);
        endToday.set(Calendar.SECOND, 59);
        endToday.set(Calendar.MILLISECOND, 59);
        return endToday;
    }

    /**
     * 获得当月起始时间
     *
     * @return
     */
    public static Calendar getFirstDayOfMounth(Calendar today) {
        Calendar calendar = getStartDate(today);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return calendar;
    }

    /**
     * 获得当月结束时间
     *
     * @return
     */
    public static Calendar getLastDayOfMounth(Calendar endToday) {
        Calendar endMounth = getEndDate(endToday);
        endMounth.set(Calendar.DAY_OF_MONTH, endMounth.getActualMaximum(endMounth.DAY_OF_MONTH));
        return endMounth;
    }

    /**
     * 获取当前季度 起始时间
     *
     * @return
     */
    public static Calendar getFirstDayOfQuarter(Calendar today) {
        Calendar calendar = getStartDate(today);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        try {
            if (currentMonth >= 1 && currentMonth <= 3)
                calendar.set(Calendar.MONTH, 0);
            else if (currentMonth >= 4 && currentMonth <= 6)
                calendar.set(Calendar.MONTH, 3);
            else if (currentMonth >= 7 && currentMonth <= 9)
                calendar.set(Calendar.MONTH, 4);
            else if (currentMonth >= 10 && currentMonth <= 12)
                calendar.set(Calendar.MONTH, 9);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return calendar;
    }

    /**
     * 获取当季的结束时间
     */
    public static Calendar getLastDayOfQuarter(Calendar today) {
        Calendar calendar = getEndDate(today);
        int currentMonth = today.get(Calendar.MONTH) + 1;
        try {
            if (currentMonth >= 1 && currentMonth <= 3) {
                calendar.set(Calendar.MONTH, 2);
                calendar.set(Calendar.DATE, 31);
            } else if (currentMonth >= 4 && currentMonth <= 6) {
                calendar.set(Calendar.MONTH, 5);
                calendar.set(Calendar.DATE, 30);
            } else if (currentMonth >= 7 && currentMonth <= 9) {
                calendar.set(Calendar.MONTH, 8);
                calendar.set(Calendar.DATE, 30);
            } else if (currentMonth >= 10 && currentMonth <= 12) {
                calendar.set(Calendar.MONTH, 11);
                calendar.set(Calendar.DATE, 31);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return calendar;
    }

}
