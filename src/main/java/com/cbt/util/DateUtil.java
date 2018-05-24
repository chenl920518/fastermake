package com.cbt.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	/**
	* 字符串转换成日期
	* @param str
	* @return date
	*/
	public static Date StrToDate(String str) {  
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	   Date date = null;
	   try {
	    date = format.parse(str);
	   } catch (Exception e) {
	    e.printStackTrace();
	   }
	   return date;
    }
	/**
	 * 根据当前时间得到前两周的时间
	 * @return
	 */
	public static String getTwoWeeksDate(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        //过去两周
        c.setTime(new Date());
        c.add(Calendar.DATE, - 14);
        Date d = c.getTime();
        String date = format.format(d);
		return date;
	}
	
	/**
	 * 根据时间计算和当前时间差值
	 * @return
	 * @throws ParseException 
	 */
	public static Boolean calExpires(String date) throws ParseException{
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd"); 
	    Date date1=simpleFormat.parse(date);    		
		long endDate = date1.getTime();  
		long now = new Date().getTime();  
		int days = (int) ((now - endDate)/(1000 * 60 * 60 * 24));  
		return days>0?true:false;
	}
	
	
	public static void main(String xp[]){
		System.out.print(getTwoWeeksDate());
	}
}
