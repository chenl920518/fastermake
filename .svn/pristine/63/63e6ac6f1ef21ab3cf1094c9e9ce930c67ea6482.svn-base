package com.cbt.util.sort;

import java.util.Comparator;

import org.apache.commons.lang.StringUtils;

import com.cbt.controller.AccountController;
import com.cbt.entity.QuoteProduct;
import com.cbt.enums.ProcessZhAndEnEnum;


public class InquirySortProcess implements Comparator<QuoteProduct>{

     
	     
	     //重写list比较方法（增加工艺排序）
	     @Override
	     public int compare(QuoteProduct o1, QuoteProduct o2) {
	    	   	 
	    	 String str = AccountController.getProcess();
	    	 if(o1==null){
	    		 return -1;
	    	 }
	    	 if(o2==null){
	    		 return 1;
	    	 }
    	 
	    	 Boolean flag1 = false;
	    	 Boolean flag2 = false;
	    	 if(StringUtils.isNotBlank(str)){
	    		 String[] split = str.split(",");
	    		 String process = null;    //原工艺
	    		 String process1 = null;   //英文工艺
	    		 for (int i = 0; i < split.length; i++) {
		    			process=split[i];  
					
					    ProcessZhAndEnEnum enEnum = ProcessZhAndEnEnum.getEnum(process);
					    if(enEnum != null){
					    	process = enEnum.getStr();
					    	process1 = enEnum.getValue();
							if(process.equals(o1.getMainProcess()) || process1.equals(o1.getMainProcess())){
								flag1 = true;
							}
							if(process.equals(o2.getMainProcess()) || process1.equals(o2.getMainProcess())){
								flag2 = true;
							}
					    }else{
							if(process.equals(o1.getMainProcess())){
								flag1 = true;
							}
							
							if(process.equals(o2.getMainProcess())){
								flag2 = true;
							}
					    }
					
				 }
	    	 }
	    	 
	    	 if(flag1 == true && flag2 == false){
	    		 return -1;
	    	 }
	    	 if (flag1 == false && flag2 == true){
	    		 return 1;
	    	 }
	    	 return 0;
	     }

		
		
    }
    
