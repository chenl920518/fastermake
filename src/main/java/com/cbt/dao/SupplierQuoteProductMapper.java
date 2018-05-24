package com.cbt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cbt.entity.SupplierQuoteProduct;

public interface SupplierQuoteProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SupplierQuoteProduct record);

    int insertSelective(SupplierQuoteProduct record);

    SupplierQuoteProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SupplierQuoteProduct record);

    int updateByPrimaryKey(SupplierQuoteProduct record);
    
    
    
    
    
    /**
     * 批量报价产品报价
     * @param list
     * @return
     */
    int insertList(List<SupplierQuoteProduct> list);
    
    /**
     * 根据厂家报价表ID查询
     * @param supplierQuoteId
     * @return
     */
    List<SupplierQuoteProduct> queryBySupplierQuoteId(Integer supplierQuoteId); 
    
    
    
    /**
     * 查询工厂报价
     * @param factoryId
     * @param orderId
     * @return
     */
    List<SupplierQuoteProduct> selectQuoteGroupByQuoteId(@Param("factoryId")String factoryId,@Param("orderId")Integer orderId);
    
    /**
     * 根据工厂报价号分组查询
     * @param supplierQuoteId
     * @param factoryId
     * @param orderId
     * @return
     */
    List<SupplierQuoteProduct> selectQuoteList(@Param("supplierQuoteId")Integer supplierQuoteId,
    		@Param("factoryId")String factoryId,@Param("orderId")Integer orderId);
    
    
}