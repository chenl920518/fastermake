package com.cbt.service;

import java.util.List;

import com.cbt.entity.SupplierQuoteProduct;

public interface SupplierQuoteProductService {

	
	
    
    /**
     * 根据厂家报价表ID查询
     * @param supplierQuoteId
     * @return
     */
    public List<SupplierQuoteProduct> queryBySupplierQuoteId(Integer supplierQuoteId); 
    
    
    
    
    
    /**
     * 查询
     * @param factoryId
     * @param orderId
     * @return
     */
   public List<SupplierQuoteProduct> selectQuoteGroupByQuoteId(String factoryId,Integer orderId);
    
    /**
     * 根据工厂报价号分组查询
     * @param supplierQuoteId
     * @param factoryId
     * @param orderId
     * @return
     */
   public List<SupplierQuoteProduct> selectQuoteList(Integer supplierQuoteId,String factoryId,Integer orderId);
}
