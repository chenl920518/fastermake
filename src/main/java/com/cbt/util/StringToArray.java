package com.cbt.util;

public class StringToArray {

	/*
	 * 将字符串转换为int数组
	 */
	public static Integer[] parse(String str) {  
		 int length = str.length();  
		 Integer[] result = new Integer[length];  
		 // 依次取得字符串中的每一个字符，并将其转化为数字，放进integer数组中  
		 for (int i = 0; i < length; i++) {  
		 char c = str.charAt(i);  
		 result[i] = Character.getNumericValue(c);  
		 }  
		 return result;  
		}  
	
	
	/*
	 * 将string数组转换为int数组
	 */	
	public static Integer[] parseStr(String[] str){
		
      Integer[] num = new Integer[str.length];
	   for(int i = 0;i<str.length;i++){
		   num[i] = Integer.parseInt(str[i]);
	   }
	      return num;
	}	

}