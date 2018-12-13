package org.util.tools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期时间处理工具
 * 
 * @author renjx
 * 
 */
public class DateTimeUtil {

	/**
	 * 取UTC时间 for wxcs
	 * 
	 * @return
	 */
	public static String getUTCDate() {
		// 1、取得本地时间：
		java.util.Calendar cal = java.util.Calendar.getInstance();
		// 2、取得时间偏移量：
		int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET);
		// 3、取得夏令时差：
		int dstOffset = cal.get(java.util.Calendar.DST_OFFSET);
		// 4、从本地时间里扣除这些差量，即可以取得UTC时间：
		cal.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		String dt = sdf.format(cal.getTime());
		return dt;
	}

	
	/**
	 * 某日期上添加x天
	 * 
	 * @param date
	 * @param intDate
	 * @return
	 */
	public static Date addDay(Date date, int intDate) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, intDate);
		return calendar.getTime();
	}

	/**
	 * 某日期上添加x月
	 * 
	 * @param date
	 * @param intDate
	 * @return
	 */
	public static Date addMonth(Date date, int intMonth) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, intMonth);
		return calendar.getTime();
	}

	/**
	 * 某日期上添加x月
	 * 
	 * @param date
	 * @param intDate
	 * @return
	 */
	public static Date addMonth(int intMonth) {
		Date date = new Date();
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, intMonth);
		return calendar.getTime();
	}

	/**
	 * 某日期上添加x时间段
	 * 
	 * @param date
	 * @param iType
	 *            如Calendar.DAY_OF_MONTH
	 * @param iValue
	 * @return
	 */
	public static Date add(Date date, int iType, int iValue) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(iType, iValue);
		return calendar.getTime();
	}

	/**
	 * 取系统当前日期时间
	 * 
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dt = sdf.format(new Date());
		return dt;
	}

	/**
	 * 取系统当前日期时间
	 * 
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getMiniDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
		String dt = sdf.format(new Date());
		return dt;
	}

	/**
	 * 取系统当前日期时间
	 * 
	 * @param format
	 *            格式(如：yyyy-MM-dd HH:mm:ss)
	 * @return
	 */
	public static String getDateTime(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String dt = sdf.format(new Date());
		return dt;
	}

	/**
	 * 将指定日期以短格式显示
	 * 
	 * @param date
	 * @return
	 */
	public static String shortFmt(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		return sdf.format(date);
	}

	/**
	 * 将指定日期以以指定格式返回
	 * 
	 * @param date
	 * @return
	 */	
	public static String dateConvtoFmt(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 将日期字符串(yyyy-MM-dd HH:mm:ss或yyyy-MM-dd)转换成日期
	 * 
	 * @param date
	 * @return
	 */		
	public static Date parse(String dtvalue) {
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			if (dtvalue != null && dtvalue.length() <= 10) {
				sdf = new SimpleDateFormat("yyyy-MM-dd");
			}
			date = sdf.parse(dtvalue);
		} catch (ParseException ex) {
		}
		return date;
	}

	/**
	 * 将日期字符串转换成日期
	 * 
	 * @param dtValue
	 *            日期字符串
	 * @param dtFormat
	 *            日期字符串的格式
	 * @return
	 */	
	public static Date parse(String dtValue, String dtFormat) {
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat(dtFormat);
		try {
			date = sdf.parse(dtValue);
		} catch (ParseException ex) {
		}
		return date;
	}

	/**
	 * 判断当前时间是否在时间dtValue之前
	 * 
	 * @param dtValue
	 * @return
	 */
	public static boolean isBefore(String dtValue) {
		try {
			Date currDate = new Date();
			DateFormat df = DateFormat.getDateTimeInstance();
			return currDate.before(df.parse(dtValue));
		} catch (ParseException e) {
			//System.out.print("[SYS] " + e.getMessage());
			return false;
		}
	}

	/**
	 * 取昨天的日期
	 */
	public static String getYesterday() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String yesterday = new SimpleDateFormat("yyyyMMdd").format(cal
				.getTime());
		return yesterday;
	}

	/**
	 * 取昨天的日期
	 */
	public static String getYesterday2() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		return yesterday;
	}


	// 日期相差天数
	public static long getQuot(String time1, String time2) {
		long quot = 0;
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

		try {
			Date date1 = ft.parse(time1);
			Date date2 = ft.parse(time2);
			quot = date1.getTime() - date2.getTime();
			quot = quot / 1000 / 60 / 60 / 24;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return quot;
	}

	// 某日期几天后的日期
	public static String addDate(Date d, long day) throws ParseException {

		long time = d.getTime();
		day = day * 24 * 60 * 60 * 1000;
		time += day;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

		return sf.format(new Date(time));

	}
	
	// 获得当前年份
	public static int getCurrentYear() {
		return Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date()));
	}

	// 获得当前月份
	public static int getCurrentMonth() {
		return Integer.parseInt(new SimpleDateFormat("MM").format(new Date()));
	}
	
	public static void main(String a[]) {
		// Long vv = getQuot("20091111", "20091113");
		//System.out.println(getCurrentMonth());
	}

	// 获取某年某月的天数
	public static String getDay_Month(int year, int month) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month - 1); // 月份从0开始算
		int dateOfMonth = c.getActualMaximum(Calendar.DATE);
		return year + "-" + (month < 10 ? "0" + month : month) + "-"
				+ (dateOfMonth < 10 ? "0" + dateOfMonth : dateOfMonth);
	}

	/**
	 * 获得指定日期的后一天
	 * 
	 * @param specifiedDay
	 * @return
	 */
	public static String getSpecifiedDayAfter(String specifiedDay) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + 1);

		String dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		return dayAfter;
	}

	/**
	 * 获得指定日期的月份的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfMonth(Date date){
		Calendar c = Calendar.getInstance();    
        c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, 1);
		Date dt = c.getTime();
		return dt;
	}

	// 两时间差
	public static String timeDifference(Date dt1,Date dt2){
		String str = "";
		if(dt1!=null&&dt2!=null){
			long milliseconds=dt2.getTime()-dt1.getTime();
			str = (milliseconds / (1000 * 60)) + "Minutes " + (milliseconds % (1000 * 60)) / 1000 + "Seconds " + milliseconds % 1000 + "Milliseconds";
		} else {
			str = "比较的两时间不能为null";
		}
		return str;
	}

	// 两时间差 :dt1,dt2均为毫秒数
	public static String timeDifference2(long dt1, long dt2) {
		long milliseconds = dt2 - dt1;
		return (milliseconds / (1000 * 60)) + "Minutes " + (milliseconds % (1000 * 60)) / 1000 + "Seconds " + milliseconds % 1000 + "Milliseconds";
	}

	// 两时间相差天数
	public static long getTwoDay(Date begin_date, Date end_date) {
		long day = 0;
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String sdate = format.format(Calendar.getInstance().getTime());
			if (begin_date == null) {
				begin_date = format.parse(sdate);
			}
			if (end_date == null) {
				end_date = format.parse(sdate);
			}
			day = (end_date.getTime() - begin_date.getTime()) / (24 * 60 * 60 * 1000);
		} catch (Exception e) {
			return -1;
		}
		return day;
	}

	// 两时间相差分钟数 只用于技师计算
	public static int getToMin(Date begin_date, Date end_date) {
		int time = 0;
		try {
			time = (int) ((end_date.getTime() - begin_date.getTime()) / (60 * 1000));
			if (time<0) {
				time = 0;
			}
		} catch (Exception e) {
			return 0;
		}
		return time;
	}
}
