package com.cbt.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 获取当前时间
 * 
 * @since 2013-12-03
 */
public class DateFormat {

	/**
	 * 
	 * @param source
	 * @return
	 */
	public static String format()
	{
		long time = System.currentTimeMillis();
	  	Date date = new Date(time);    	
	  	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  	String currentTime = sdf.format(date);
		
		
		return currentTime;
	}
	public static String formatDate1(String date) throws ParseException
	{
//		long time = System.currentTimeMillis();
//		Date date = new Date(time);    	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date time = sdf.parse(date); 
		String currentTime = sdf.format(time);
		
		
		return currentTime;
	}


	public static String formatDate2(String date) throws ParseException
	{
//		long time = System.currentTimeMillis();
//		Date date = new Date(time);    	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = sdf.parse(date); 
		String currentTime = sdf.format(time);
		
		
		return currentTime;
	}
	public static String currentDate() throws ParseException
	{
	    long time = System.currentTimeMillis();
	    Date date = new Date(time);    	
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    String currentTime = sdf.format(date);		
		return currentTime;
	}
	
	public static String weekLaterDate() throws ParseException
	{
		   Calendar calendar = Calendar.getInstance();  
	       calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 7);  
	       Date today = calendar.getTime();  
	       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
	       String result = format.format(today);  
	       return result;
	}
	
	public static Date formatToDate(Date date) throws ParseException
	{ 	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(date);
		return sdf.parse(str);
	}
	
	
	public static String addDays(String dateTime,int days) throws ParseException
	{ 	
			
         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          Date  currdate = format.parse(dateTime);
//          System.out.println("现在的日期是：" + currdate);
          Calendar ca = Calendar.getInstance();
          ca.setTime(currdate);
          ca.add(Calendar.DATE, days);// num为增加的天数，可以改变的
          currdate = ca.getTime();
          String enddate = format.format(currdate);
//          System.out.println("增加天数以后的日期：" + enddate);
          return enddate;
	}
	
	
	
	
	public static int calHours(String date) throws ParseException
	{ 	

     SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     String currentDate = simpleFormat.format(new Date());  
     String publishDate = simpleFormat.format(date);  
     long from = simpleFormat.parse(currentDate).getTime();  
     long to = simpleFormat.parse(publishDate).getTime();  
     int hours = (int) ((from - to)/(1000 * 60 * 60));  
     return hours;
	}
	
}

