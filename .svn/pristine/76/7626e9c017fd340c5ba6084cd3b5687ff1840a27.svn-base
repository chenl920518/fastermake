package com.cbt.util;

import org.apache.commons.lang.StringUtils;

public class GetCityUtil {


/**
 * 用于判断直辖市是县还是地区（用于同步修改企业档案信息）
 * @Title getCity 
 * @Description
 * @param state
 * @param district
 * @return
 * @return String
 */
	    public static String getState(String district){
	    	
	    	String city = null;
	    	if(StringUtils.isNotBlank(district)){	    		
	    		String str = district.substring(district.length()-1, district.length());
	    		if("县".equals(str)){
	    			city = "县";
	    		}else{
	    			city = "市辖区";
	    		}
	    	}	    	
			return city;
	    }

}
