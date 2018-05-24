package com.cbt.service;

import javax.servlet.http.HttpServletRequest;

import com.cbt.entity.QuoteBigProducts;

public interface QuoteBigProductsService {    
	
	
	     public int insertSelective(QuoteBigProducts record);

	     public QuoteBigProducts selectByPrimaryKey(Integer id);

	     public int updateByPrimaryKeySelective(HttpServletRequest request,QuoteBigProducts record);
	     
	     
	     /**
	      * 根据采购商名、询盘号查询
	      * @Title selectByOrderIdKey 
	      * @Description TODO
	      * @param factoryId
	      * @param orderId
	      * @return
	      * @return QuoteBigProducts
	      */
	    public  QuoteBigProducts selectByOrderId(String factoryId,Integer orderId);
	    
	    /**
	     * 插入大货采购商需求数据和价格数据
	     * @Title insertSelective 
	     * @Description TODO
	     * @param record
	     * @param priceList
	     * @return
	     * @return int
	     */
	    public void insertSelective(QuoteBigProducts record,String priceList);
	    
	    
	    
	    /**
	     * 根据供应商id查询
	     * @Title selectBySupplierId 
	     * @Description TODO
	     * @param factoryId
	     * @return
	     * @return QuoteBigProducts
	     */
	    public QuoteBigProducts selectBySupplierId(String factoryId,Integer orderId);
} 
