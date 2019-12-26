package com.moyuaninfo.waterinfo.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/*
 * @Description
 * @Author zhaoGq
 * @Date 2019/12/02 10:08
 * @Param
 * @param null
 * @Return 
 **/
public class DateUtils {

    private static Log log = LogFactory.getLog(DateUtils.class);
    
    public static String YEAR_PATTERN = "yyyy";
    
    public static String MONTH_PATTERN = "yyyy-MM";
    
    /**
     * yyyy-MM-dd
     */
    public static String DATE_PATTERN = "yyyy-MM-dd";
    
    /**
     * yyyy年MM月dd日
     */
    public static String DATECN_PATTERN = "yyyy年MM月dd日";
    
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    
    /**
     * yyyy-MM-dd HH:mm:ss.SSS
     */
    public static String DDATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
    
    /**
     * yyyyMMddHHmmss
     */
    public static String DATETIME_NUMBER_PATTERN = "yyyyMMddHHmmss";
    
    /**
     * Fri Aug 17 2012 00:00:00 GMT+0800 (中国标准时间)  格式转换
     */
    public static String STANDARD_DATETIME_PATTERN = "EEE MMM dd yyyy HH:mm:ss z";
    
    //private static String timePattern = "HH:mm";
    
    /**
     * 获取年份
     */
    public static String getYear() {
    	DateFormat sdf = new SimpleDateFormat(YEAR_PATTERN);
        return sdf.format(new Date());
    }
    
    /**
     * 获取年份
     */
    public static int getYear(Date date) {
    	DateFormat sdf = new SimpleDateFormat(YEAR_PATTERN);
        return Integer.valueOf(sdf.format(date)) ;
    }
    
    /**
     * 获取年份
     */
    public static String getYearStr(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(YEAR_PATTERN);
        return sdf.format(date);
    }
    
    /**
     * 获取年月
     */
    public static String getMonth(Date date) {
    	DateFormat sdf = new SimpleDateFormat(MONTH_PATTERN);
        return sdf.format(date);
    }
    
    /**
     * get system date
     * 
     * yyyy-MM-dd
     * @return 
     */
    public static String getSystemDate() {
        DateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        return sdf.format(new Date());
    }
    
    /**
     *  get system date.
     *  Can set interval get every day value.
     * @param intervalDays
     * @return yyyy/mm/dd
     */
    public static String getSystemDate(int intervalDays) {
        Date sysDate = convert(convert(new Date()));
        DateTime dateTime = new DateTime(sysDate.getTime());
        DateTime date = dateTime.plusDays(intervalDays);
        return format(date.toDate(), "yyyy-MM-dd");
    }
    
    /**
     * get system datetime
     * @return yyyy/mm/dd hh:mm:ss
     */
    public static String getSystemDateTime() {
        DateFormat sdf = new SimpleDateFormat(DATETIME_PATTERN);
        return sdf.format(new Date());
    }
    
    /**
     * convert Date String to Date object
     * pattern :  yyyy-MM-dd
     * @param date String format date
     * @return Date format date
     */
    public static Date convert(String date) {
        DateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        Date dateFormat = null;
        try {
            dateFormat = sdf.parse(date);
        } catch(ParseException pe) {
            log.error(pe);
        }
        return dateFormat;
    }
    
    public static Date convert(String date, String pattern) {
        DateFormat sdf = new SimpleDateFormat(pattern);
        Date dateFormat = null;
        try {
            dateFormat = sdf.parse(date);
        } catch(ParseException pe) {
            log.error(pe);
        }
        return dateFormat;
    }
    
    public static Date convertDateTime(String date) {
        DateFormat sdf = new SimpleDateFormat(DATETIME_PATTERN);
        Date dateFormat = null;
        try {
            dateFormat = sdf.parse(date);
        } catch(ParseException pe) {
            log.error(pe);
        }
        return dateFormat;
    }

    /**
     * 按 格式 yyyy-MM-dd 进行 类型转换
     * @param date
     * @return
     */
    public static Date convertDate(String date) {
        DateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        Date dateFormat = null;
        try {
            dateFormat = sdf.parse(date);
        } catch(ParseException pe) {
            log.error(pe);
        }
        return dateFormat;
    }
    /**
     * 按 格式 yyyy-MM-dd hh-mm 进行 类型转换
     * @param date
     * @return
     */
    public static Date convertDateYMDHM(String date) {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date dateFormat = null;
        try {
            dateFormat = sdf.parse(date);
        } catch(ParseException pe) {
            log.error(pe);
        }
        return dateFormat;
    }
    /**
     * 
     * @param date Date format date  'yyyy-MM-dd'
     * @return Date format date
     */
    public static String convert(Date date) {
        if(date == null) {
            return "";
        }
        DateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        String dateFormat = sdf.format(date);
        return dateFormat;
    }
    
    /**
     * 
     * @param date
     * @param pattern
     * @return
     */
    public static String convert(Date date, String pattern) {
        if(date == null) {
            return "";
        }
        DateFormat sdf = new SimpleDateFormat(pattern);
        String dateFormat = sdf.format(date);
        return dateFormat;
    }
    
    public static String convertFull(Date date) {
        if(date == null) {
            return "";
        }
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateFormat = sdf.format(date);
        return dateFormat;
    }
    
    /* 将
     * Fri Aug 17 2012 00:00:00 GMT+0800 (中国标准时间)
     * 转换为：yyyy-MM-dd 的格式
     */
    public static String convertStandardDateYMD(String dateStr) {
    	if(StringUtils.isBlank(dateStr)) {
            return "";
        }
        String dateStrChinaRep = dateStr.replace("GMT", "").replaceAll("\\(.*\\)", "");
        SimpleDateFormat sdf = new SimpleDateFormat(STANDARD_DATETIME_PATTERN, Locale.ENGLISH);
        Date date = null;
        String dateFormat = "";
        try {
            date = sdf.parse(dateStrChinaRep);
            dateFormat = DateUtils.convert(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateFormat;
	}
    
    /* 将
     * Fri Aug 17 2012 00:00:00 GMT+0800 (中国标准时间)
     * 转换为：yyyy 的格式
     */
    public static String convertStandardDateY(String dateStr) {
        if(StringUtils.isBlank(dateStr)) {
            return "";
        }
        String dateStrChinaRep = dateStr.replace("GMT", "").replaceAll("\\(.*\\)", "");
        SimpleDateFormat sdf = new SimpleDateFormat(STANDARD_DATETIME_PATTERN, Locale.ENGLISH);
        Date date = null;
        String dateFormat = "";
        try {
            date = sdf.parse(dateStrChinaRep);
            dateFormat = DateUtils.getYearStr(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateFormat;
    }
    
    public static String convertMonth(Date date) {
    	if(date == null) {
            return "";
        }
        DateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String dateFormat = sdf.format(date);
        return dateFormat;
    }
    
    public static String convert(Timestamp date) {
        if(date == null) {
            return "";
        }
        DateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        String dateFormat = sdf.format(date);
        return dateFormat;
    }

    public static Date getBeforeHour(Date currentDate, int intervalHours) {
    	DateTime dateTime = new DateTime(currentDate.getTime());
		DateTime date = dateTime.minusHours(intervalHours);
		return date.toDate();
    }
    
    public static Date getAfterHour(Date currentDate, int intervalHours) {
    	DateTime dateTime = new DateTime(currentDate.getTime());
		DateTime date = dateTime.plusHours(intervalHours);
		return date.toDate();
    }
    
    public static Date getAfterDate(Date currentDate, int intervalDays) {
    	DateTime dateTime = new DateTime(currentDate.getTime());
		DateTime date = dateTime.plusDays(intervalDays);
		return date.toDate();
	}
    public static Date getBeforeDate(Date currentDate, int intervalDays) {
		DateTime dateTime = new DateTime(currentDate.getTime());
		DateTime date = dateTime.minusDays(intervalDays);
		return date.toDate();
	}
    
    public static Date getAfterMonth(Date currentDate, int intervalMonths){
    	DateTime dateTime = new DateTime(currentDate.getTime());
		DateTime date = dateTime.plusMonths(intervalMonths);
		return date.toDate();
    }
    
    /**
     * 通过字符串 '2011-05' , 计算相应间隔的月份，如 intervalMonths = 1
     * 则 return '2011-06'
     * 
     * @param month yyyy-MM
     * @param intervalMonths
     * @return monthTo yyyyy-MM
     */
    public static String getAfterMonthForMonth(String month, int intervalMonths) {
    	Date date = DateUtils.convert(month+"-01");
		Date mTo = DateUtils.getAfterMonth(date, intervalMonths);
		String monthTo = DateUtils.format(mTo, "yyyy-MM");
		return monthTo;
    }
    
    public static Date getBeforeMonth(Date currentDate, int intervalMonths) {
		DateTime dateTime = new DateTime(currentDate.getTime());
		DateTime date = dateTime.minusMonths(intervalMonths);
		return date.toDate();
	}
    
    public static String getDate(String date, int intervalDays) {
    	DateTime dateTime = new DateTime(convert(date));
    	DateTime resDate = dateTime.plusDays(intervalDays);
		return convert(resDate.toDate());
	}
    
    /**
     * 
    * 获取一周后的时间
    * @return yyyy-MM-dd HH:mm:ss
    * @throws
    * @other: (注意事项)
    *
    * @author: zhaoGq
    * @date:   2019-07-23 17:21:42
     */
    public static String getAfterWeek() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_PATTERN);
        calendar.set(Calendar.WEEK_OF_YEAR,calendar.get(Calendar.WEEK_OF_YEAR) + 1);  
        return sdf.format(calendar.getTime());
    }

    /**
     * date1 > date2?
     * @param date1
     * @param date2
     * @return
     */
    public static boolean greaterThan(Date date1, String date2) {
        Date date = convert(date2);
        return greaterThan(date1, date);
    }
    
    /**
     *  date1 > date2?
     *  
     * @param date1
     * @param date2
     * @return
     */
    public static boolean greaterThan(Date date1, Date date2) {
        if (date1.getTime() > date2.getTime()) {
            return true;
        }
        return false;
    }
    
    /**
     * date1 >= date2?
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static boolean greaterEqualThan(Date date1, String date2) {
        Date date = convert(date2);
        return greaterEqualThan(date1, date);
    }
    
    /**
     * date1 >= date2?
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static boolean greaterEqualThan(Date date1, Date date2) {
        if (date1.getTime() >= date2.getTime()) {
            return true;
        }
        return false;
    }
    
    /**
     * date1 <= date2?
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static boolean lessEqualThan(Date date1, Date date2) {
    	 if (date1.getTime() <= date2.getTime()) {
             return true;
         }
         return false;
    }
    
    /**
     * date1 < date2?
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static boolean lessThan(Date date1, Date date2) {
   	 if (date1.getTime() < date2.getTime()) {
            return true;
        }
        return false;
    }
    
    /**
     * 
    * 两个字符串类型日期比大小：date1 < date2
    * 2019-07-24 20:03:47 < 2019-07-24 20:03:48
    * @param date1
    * @param date2
    * @return
    * @throws
    * @other: (注意事项)
    *
    * @author: zhaoGq
    * @date:   2019-07-25 15:42:21
     */
    public static boolean greaterThanString(String date1, String date2) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_PATTERN);
        try {
            if(sdf.parse(date1).getTime() < sdf.parse(date2).getTime()) {
                return true;
            }else{
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * 等于比较
     * @param date1
     * @param date2
     * @return
     */
    public static boolean equals(Date date1, String date2) {
        Date date = convert(date2);
        return equals(date1, date);
    }
    public static boolean equals(Date date1, Date date2) {
        if (date1.getTime() == date2.getTime()) {
            return true;
        }
        return false;
    }
    
    /**
     * 获得当前小时
     * @return
     */
    public static int getCurrentHour() {
		DateTime date = new DateTime(new Date());
		return date.getHourOfDay();
	}
    
    /**
	 * 计算时间间隔 
	 * @param startDate
	 * @param endDate
	 * @return 毫秒级 millisecond level
	 */
	public static long getIntervalTime(Date startDate, Date endDate) {
		long intervalTime = endDate.getTime() - startDate.getTime();
		return intervalTime;
	}
	
	/**
	 * 计算时间间隔 （天）
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static long getInterval(Date startDate, Date endDate) {
		long intervalTime = endDate.getTime() - startDate.getTime();
		
		return intervalTime/1000/60/60/24;
	}
    
	/**
	 * 计算时间间隔 （分）
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static long getIntervalMinute(Date startDate, Date endDate) {
		long intervalTime = endDate.getTime() - startDate.getTime();
		
		return intervalTime/1000/60;
	}
	
	public static String getIntervalStr(Date startDate, Date endDate) {
		long intervalTime = endDate.getTime() - startDate.getTime();
		
		long s = intervalTime/1000;
		long m = s/60;
		long h = m/60;
		long d = h/24;
		
		String str = "";
		if(m%60 > 0) {
			str = m%60 + "分";
		}
		
		if(h%24 > 0) {
			str = h%24 + "小时" + str;
		}
		
		if(d > 0 ) {
			str = d + "天" + str;
		}
		
		return str;
	}
	
	/**
	 * @see
	 * （天）
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static long getInterval(String startDate, String endDate) {
		return getInterval(convert(startDate), convert(endDate));
	}
	
    /**
     *  format date by pattern 
     *      (yyyy-MM-dd) or (yyyy-MM-dd hh:mm:ss) or (yyyy/MM/dd hh:mm:ss)
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        DateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
    
    public static String format(String date, String pattern) {
        return format(convertDateTime(date), pattern);
    }
    
    /**
     * 
     * @param date 格式： yyyy-MM-dd
     * @param pattern
     * @return
     */
    public static String formatDate(String date, String pattern) {
    	return format(convertDate(date), pattern);
    }
    
    /**
     * get now datetime
     * @return datetime by format yyyy-MM-dd HH:mm:ss
     */
    public static String getNow(){
    	return DateUtils.format(new Date(), DATETIME_PATTERN);
    }
    
    /**
     * 返回当前系统时间
     * 格式 yyyy-MM-dd HH:mm:ss.SSS
     * @return String
     */
    public static String getDNow(){
    	return DateUtils.format(new Date(), DDATETIME_PATTERN);
    }
    
    /**
     * 返回当前系统时间作为编号
     * 格式 yyyyMMddHHmmss
     * @return String
     */
    public static String getNowNumber(){
        return DateUtils.format(new Date(), DATETIME_NUMBER_PATTERN);
    }
    
    /**
     * 获取当前日期
     * yyyy-MM-dd
     * @return
     */
    public static String getNowDate() {
    	return DateUtils.format(new Date(), DATE_PATTERN);
    }
    
    /**
     * 根据pattern 格式，返回当前系统时间
     * @param pattern
     * @return String
     */
    public static String getNow(String pattern){
    	return DateUtils.format(new Date(), pattern);
    }
    
    /**
     * 返回当前系统时间
     * 格式 yyyy-MM-dd
     * @return Date
     */
    public static Date getNowForDate(){
    	return convert(getNow());
    }
    
    /**
     * 返回当前系统时间
     * 格式 yyyy-MM-dd HH:mm:ss
     * @return Date
     */
    public static Date getNowForDateTime() {
    	return convertDateTime(getNow());
    }
    
    /**
     * 当前月的第一天    
     * @param
     * @return
     */
    public static String getBeginDateOfMonth() {
    	SimpleDateFormat   df   =   new   SimpleDateFormat("yyyy-MM-dd");       
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();    
        gc.set(Calendar.DAY_OF_MONTH, 1);    
        String day_first = df.format(gc.getTime());  
        return day_first;
    }
    
    /**
     * 获取 月末 日期
     * @param date
     * @return
     */
    public static String getEndDateOfMonth(String date) {
    	Date from = DateUtils.convert(date);
    	Date end = DateUtils.getAfterMonth(from, 1);
    	end = DateUtils.getBeforeDate(end, 1);
    	return DateUtils.convert(end);
    }
    
    /**
     * 获取 月数量
     * @param dateFrom
     * @param dateTo
     * @return
     */
	public static int getMonthCnt(String dateFrom, String dateTo) throws Exception {
    	if(DateUtils.convert(dateFrom).compareTo(DateUtils.convert(dateTo))>0  ){
    		throw new Exception("请输入正确的时间顺序");
    	}
    	
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(DateUtils.convert(dateTo));
    	int iYearTo = calendar.get(Calendar.YEAR) ;
    	int iMonthTo = calendar.get(Calendar.MONTH) ;
    	calendar.setTime(DateUtils.convert(dateFrom));
    	int iMonthFrom = calendar.get(Calendar.MONTH) ;
    	int iyearFrom =  calendar.get(Calendar.YEAR) ;
    	int monthCnt = (iYearTo-iyearFrom)*12+(iMonthTo - iMonthFrom)+1;
    	return monthCnt;
	}
	
	/**
	 * true - 交叉
	 * false - 无交叉
	 * @param from1
	 * @param end1
	 * @param from2
	 * @param end2
	 * @return
	 */
	public static boolean getCross(String from1, String end1, String from2, String end2) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		try { 
			if( !sdf.parse(from1).after(sdf.parse(end2))
					&& !sdf.parse(end1).before(sdf.parse(from2))){ 
				return true;
			} 
		} catch (Exception e) { }
		return false;
	}
	/** 
     *获取前/后几天的日期
     */  
    public static String getDate(Date date,long i){  
         SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");     
         return df.format(new Date(date.getTime() + i * 24 * 60 * 60 * 1000));  
    }  
    
    /**
     * 
    * 将英文星期转为中文信息
    * @param
    * @throws Exception
    * @throws
    * @other: (注意事项)
    *
    * @author: root
    * @date:   2018年5月30日 下午4:59:22
     */
    
    public static String convertENGWeekToCHIWeek(String weekDate){
        if (weekDate.equals("Monday")) {
            weekDate= "周一";
        }else if(weekDate.equals("Tuesday")){
            weekDate= "周二";
        }else if(weekDate.equals("Wednesday")){
            weekDate= "周三";
        }else if(weekDate.equals("Thursday")){
            weekDate= "周四";
        }else if(weekDate.equals("Friday")){
            weekDate= "周五";
        }else if(weekDate.equals("Saturday ")){
            weekDate= "周六";
        }else if(weekDate.equals("Sunday")){
            weekDate= "周日";
        }else {
            weekDate= "";
        }
       return weekDate;
    }
    
    public static void main(String[] args) throws Exception{

//    	for(int i=0;i<1000;i++){
//    		System.out.println( i + "-" + System.currentTimeMillis());
//    	}
    	
        //System.out.println(DateUtil.getInterval(convertStamp("2006-8-1 7:30:35"), convertStamp("2006-8-2 8:40:40"),"H"));
       // System.out.println(DateUtil.getInterval("2006-11-2 9:21:34", "2006-11-4 12:29:21", "M"));
        //System.out.println(DateUtil.getIntervalTime(DateUtil.convertStamp("2006-11-2 9:21:34"), DateUtil.convertStamp("2006-11-4 12:29:21")));
        //System.out.println(DateUtil.format("2007-1-1 07:30:00", "yyyyMMdd hhmmss"));
        //Date date = DateUtil.convertDateTime("2007-3-1");
    	//int date = DateUtil.getDayNight("2007-2-1 14:00:00");
        //System.out.print(DateUtil.getCurrentHour());
//        System.out.print(DateUtil.getNow("yyyyMM"));
//    	 System.out.print(DateUtil.getNow("yyyyMMDD").substring(2));
//        System.out.println(DateUtil.getInterval(convertDateTime("2010-01-09 00:50:00"), convertDateTime("2010-01-10 01:00:00")));
    	 
//    	System.out.println(DateUtil.getInterval("2010-10-1", "2010-9-30"));
    	
//    	Date date = DateUtil.getBeforeMonth(new Date(), 2);
    	 
    	//System.out.println(getMonthCnt("2011-08-01","2011-09-01") );
//    	String str = DateUtil.getIntervalStr(convertDateTime("2012-06-05 12:00:00"), convertDateTime("2012-06-06 13:10:00"));
//    	System.out.println(str);
    	
//    	boolean b = DateUtil.getCross("2013-01-02", "2013-01-31", "2013-02-01", "2013-02-03");
    	
//		Date fromDate = DateUtils.getBeforeDate(new Date(), 7);
//		Date endDate = DateUtils.getBeforeDate(new Date(), 1);
//		
//		String from = DateUtils.convert(fromDate, DateUtils.DATE_PATTERN);
//		String end = DateUtils.convert(endDate, DateUtils.DATE_PATTERN);
    	System.out.println(getDate(new Date(),0));
    }
	public static String dateCompareMin(List<String> listTime)
	{   
	   Date dateMin=DateUtils.convertDateYMDHM(listTime.get(0));
		for(String time:listTime){
			Date date=DateUtils.convertDateYMDHM(time);
			if(date.before(dateMin)){
                 dateMin=date;
			}
		}
		return dateMin.toString();
	}
	public static String dateCompareMax(List<String> listTime)
	{   
	   Date dateMax=DateUtils.convertDateYMDHM(listTime.get(0));
		for(String time:listTime){
			Date date=DateUtils.convertDateYMDHM(time);
			if(dateMax.before(date)){
				 dateMax=date;
			}
		}
		return dateMax.toString();
	}
	
	public static String dateCompletionZero(String dateString) {
//		固定格式
//		String examBeganTime = "2019-1-2 00:00";
//		String examBeganTime = "2019-01-02 00:00";
		if (StringUtils.isNotBlank(dateString)) {
			if(!"undefined".equals(dateString)) {
				String[] dateTimeSplit = dateString.split(" ");
				String yearMonthDay = dateTimeSplit[0];
				String time = dateTimeSplit[1];
				String[] yearMonthDaySplit = yearMonthDay.split("-");
				String year = yearMonthDaySplit[0];
				String month = yearMonthDaySplit[1];
				String day = yearMonthDaySplit[2];
				if (month.length() < 2) {
					month = "0" + month;
				}
				if (day.length() < 2) {
					day = "0" + day;
				}
				dateString = year + "-" + month + "-" + day + " " + time;
			}else {
				return "";
			}
			return dateString;
		}else {
			return "";
		}
	}
	
	/**
	 * 
	* 获取年-月-日
	* @param dateTime
	* @return
	* @throws ParseException
	* @throws
	* @other: (注意事项)
	*
	* @author: pyy
	* @date:   2019年5月13日 下午3:37:36
	 */
	 public static String dateTimeToDateShort(String dateTime) throws ParseException {
        //string 转date
        SimpleDateFormat format = new SimpleDateFormat(DATETIME_PATTERN); 
        Date paseDate = format.parse(dateTime); 
        //转换格式
        SimpleDateFormat formatShort = new SimpleDateFormat("yyyy-MM-dd"); 
        String dateShort =formatShort.format(paseDate) ;
        return dateShort; 
	 }


	    /**
	     * 
	    * 获取今天是星期几，英文的
	    * @param dateTime
	    * @return
	    * @throws ParseException
	    * @throws
	    * @other: (注意事项)
	    *
	    * @author: pyy
	    * @date:   2019年5月13日 下午3:27:21
	     */
	    public static String dateTimeToDateWeek(String dateTime) throws ParseException {
	        //string 转date
	        SimpleDateFormat format = new SimpleDateFormat(DATETIME_PATTERN); 
	        Date paseDate = format.parse(dateTime); 
	        //转换格式
	        SimpleDateFormat formatWeek = new SimpleDateFormat("EEEE",Locale.ENGLISH); 
	        String dateWeek =formatWeek.format(paseDate) ;
	        return dateWeek;
	    }


	    /**
	     * 
	    * 获取时间格式yyyyMMdd
	    * @param dateTime
	    * @return
	    * @throws ParseException
	    * @throws
	    * @other: (注意事项)
	    *
	    * @author: pyy
	    * @date:   2019年5月13日 下午3:30:37
	     */
	    public static String dateTimeToDateGroup(String dateTime) throws ParseException {
	        //string 转date
	        SimpleDateFormat format = new SimpleDateFormat(DATETIME_PATTERN); 
	        Date paseDate = format.parse(dateTime); 
	        //转换格式
	        SimpleDateFormat formatDateGroup = new SimpleDateFormat("yyyyMMdd"); 
	        String dateGroup =formatDateGroup.format(paseDate) ;
	        return dateGroup;
	    }


	    /**
	     * 
	    * 获取时间格式HH:mm:ss
	    * @param dateTime
	    * @return
	    * @throws ParseException
	    * @throws
	    * @other: (注意事项)
	    *
	    * @author: pyy
	    * @date:   2019年5月13日 下午3:31:05
	     */
	    public static String dateTimeToTime(String dateTime) throws ParseException {
	        //string 转time
	        SimpleDateFormat format = new SimpleDateFormat(DATETIME_PATTERN); 
	        Date paseDate = format.parse(dateTime); 
	        //转换格式
	        SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss"); 
	        String time =formatTime.format(paseDate) ;
	        return time;
	    }
	    
	    /**
	     * 
	    * 获取时间格式月/日
	    * @param dateTime
	    * @return
	    * @throws ParseException
	    * @throws
	    * @other: (注意事项)
	    *
	    * @author: pyy
	    * @date:   2019年5月13日 下午3:31:38
	     */
	    public static String dateTimeToMonthDay(String dateTime) throws ParseException {
	        //string 转月日
	        SimpleDateFormat format = new SimpleDateFormat(DATETIME_PATTERN); 
	        Date paseDate = format.parse(dateTime); 
	        //转换格式
	        SimpleDateFormat formatTime = new SimpleDateFormat("MM/dd"); 
	        String monthDay =formatTime.format(paseDate) ;
	        return monthDay;
	    }
	
}
