package com.cx.test;

import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

public class DateTest {

	public static void main(String[] args) throws UnsupportedEncodingException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
//		System.out.println(cal.get(Calendar.DAY_OF_MONTH)-1);
//		cal.set(1970, 0, 01);
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)-1);
//	  cal.set(Calendar.HOUR_OF_DAY,2);
//    cal.set(Calendar.MINUTE,0);
//    cal.set(Calendar.SECOND,0);
    
//		Date date = new Date(-21600000);
//		System.out.println(date);
//		
//    
//    System.out.println(cal.getTimeInMillis());
//    
//    System.out.println(date.compareTo(cal.getTime()));
		
//		System.out.println(sdf.format(cal.getTime()));
		
		System.out.println(""+Runtime.getRuntime().availableProcessors());
		
		System.out.println(String.format("%05d",2));
		
		System.out.println(System.getProperty("java.io.tmpdir"));
		
		
	}

}
