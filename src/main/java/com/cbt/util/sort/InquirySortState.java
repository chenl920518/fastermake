package com.cbt.util.sort;

import java.util.Comparator;

import com.cbt.controller.AccountController;
import com.cbt.entity.QuoteProduct;


public class InquirySortState implements Comparator<QuoteProduct>{

     
	     
	     //重写list比较方法（地区排序）
	     @Override
	     public int compare(QuoteProduct o1, QuoteProduct o2) {
	    	   	 
	    	 Integer state = AccountController.getState();
	    	 if(o1==null){
	    		 return -1;
	    	 }
	    	 if(o2==null){
	    		 return 1;
	    	 }
	    	 if(state != null){	    	
				if(o1.getState() == state && o2.getState() != state){
					return -1;
				}
				if(o1.getState() != state && o2.getState() == state){
					return 1;
				}								
	    	 }
	    	 return 0;
	     }

		
		
    }
    
