package com.cbt.dao;

import org.apache.ibatis.annotations.Param;

import com.cbt.entity.QuoteBigProducts;

public interface QuoteBigProductsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QuoteBigProducts record);

    int insertSelective(QuoteBigProducts record);

    QuoteBigProducts selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QuoteBigProducts record);

    int updateByPrimaryKey(QuoteBigProducts record);
       
    /**
     * 根据采购商名、询盘号查询
     * @Title selectByOrderIdKey 
     * @Description TODO
     * @param factoryId
     * @param orderId
     * @return
     * @return QuoteBigProducts
     */
    QuoteBigProducts selectByOrderId(@Param("customerId")String customerId,@Param("orderId")Integer orderId);
    
    
    
    /**
     * 根据供应商id查询
     * @Title selectBySupplierId 
     * @Description TODO
     * @param factoryId
     * @return
     * @return QuoteBigProducts
     */
    QuoteBigProducts selectBySupplierId(@Param("factoryId")String factoryId,@Param("orderId")Integer orderId);
    
    
    /**
     * 根据询盘号将给供应商的大货is_active 字段更新为0
     * @Title updateIsActive 
     * @Description TODO
     * @param factoryId
     * @param orderId
     * @return void
     */
    void updateIsActive(@Param("factoryId")String factoryId,@Param("orderId")Integer orderId);
}