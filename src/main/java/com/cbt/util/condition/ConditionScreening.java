package com.cbt.util.condition;

import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.cbt.util.WebCookie;


public class ConditionScreening{

     	  
         /**
          * 判断供应商工艺是否满足条件
          * @param process
          * @param request
          * @return
          */
	     public static int compareProcess(String process,HttpServletRequest request) {
	    	 
	         String str = WebCookie.getProcess(request);
	         int option = 0;
	         if(StringUtils.isNotBlank(str)){
	        	 String[] split = str.split(",");
	    		 for (int i = 0; i < split.length; i++) { 
	    			 if(StringUtils.isNotBlank(process)){
		    			 if(process.equals(split[i])){
		    				 option = 1;
		    			 } 
	    			 }
		         } 
	         }
	    	 return option;	
	     }	
	     
	     /**
          * 判断供应商所在地区是否满足条件
          * @param process
          * @param request
          * @return
          */
	     public static int compareState(Integer state,HttpServletRequest request) {
	    	 
	    	 Integer str = WebCookie.getState(request);
	    	 int option = 0;
	    	 if(str != null){
	    		if(state == str){
	    			option = 1;
	    		}
	    	 }
	    	 return option;	
	     }	
	     
	     
	     /**
          * 判断供应商资格证书是否满足条件
          * @param process
          * @param request
          * @return
          */
	     public static int compareQualification(String qualification,HttpServletRequest request) {
	    	 
	    	 String str = WebCookie.getQualification(request);
	    	 int option = 0;
	    	 if(StringUtils.isNotBlank(str)){
		    		 if(StringUtils.isNotBlank(qualification)){
	    				 String[] split1 = qualification.split(","); 
	    				 ArrayList<String> arrayList1 = new ArrayList<String>(Arrays.asList(split1));
	    				 String[] split = str.split(",");
	    				 ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(split));
	    				 arrayList1.retainAll(arrayList);
	    				 if(arrayList1.size() > 0){
	    					if(arrayList1.size() == arrayList.size()){
	    						option = 1;
	    					}
	    				 }
	    		     }	 		    		 
	    	 }
	    	 return option;	
	     }	
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
    }
    
