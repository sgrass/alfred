package com.alfred.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DateUtil {
	
	private static Log log = LogFactory.getLog(DateUtil.class);
	
	public static final String DATE_SHORT_PATTERN="yyyy-MM-dd HH:mm:ss";
	
	public static final String TIME_SHORT_PATTERN="HH:mm";
	
	public static final String TIME_BETTEW_FMT="MM/dd/yyyy";
	
	/**
	 * 01:00 AM字符串转换为24小时制日期
	 * @param time
	 * @return
	 */
	public static Date getTimeTo24(String time) {
		try {
			if (StringUtils.isBlank(time)) {
				return null;
			}
			SimpleDateFormat parse = new SimpleDateFormat("h:mm a",Locale.US);
			return parse.parse(time);
			
		} catch (ParseException e) {
			log.error("DateUtil.getTimeTo24()-->",e);
			return null;
		}
	}
	
	public static Date getDateParse(String pattern, String date) {
		try {
			if (StringUtils.isBlank(date)) {
				return null;
			}
			DateFormat df = new SimpleDateFormat(pattern);
			return df.parse(date);
		} catch (ParseException e) {
			log.error("DateUtil.getDateParse()-->",e);
			return null;
		}
	}
	
	/**
	 * 获取两个日期之间的天数
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int getDaysBetween(Date startDate, Date endDate) {
		Calendar startCalendar = Calendar.getInstance();
		Calendar endCalendar = Calendar.getInstance();
		startCalendar.setTime(startDate);
		endCalendar.setTime(endDate);
		setTimeToMidnight(startCalendar);
		setTimeToMidnight(endCalendar);

		long startMs = startCalendar.getTimeInMillis();
		long endMs = endCalendar.getTimeInMillis();
		
		long intervalMs = endMs - startMs;
		return millisecondsToDays(intervalMs);
	}
	
	/**
	 * 得到之前的或之后几天的日期
	 * @param curDate
	 * @param day
	 * @return
	 */
	public static Date getDate(Date curDate, Integer day) {
		if (curDate == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(curDate);
		cal.add(Calendar.DAY_OF_YEAR, day);
		return cal.getTime();
	}
	
	
	public static String getDate(Date curDate, Integer day,String fmt) {
		if (curDate == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(curDate);
		cal.add(Calendar.DAY_OF_YEAR, day);
		
		
		SimpleDateFormat formatter = new SimpleDateFormat(fmt);

		return formatter.format(cal.getTime());
	}
	
	
	
	
	
	/**
	 * 毫秒转化为天数
	 * @param intervalMs
	 * @return
	 */
	public static  int millisecondsToDays(long intervalMs) {
		return (int) (intervalMs / (1000 * 86400));
	}
	
	/**
	 * 过滤时分秒
	 * @param calendar
	 */
	public static  void setTimeToMidnight(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
	}
}
