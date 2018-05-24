package com.cbt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cbt.entity.QuoteBigProductsTab;

public interface QuoteBigProductsTabMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QuoteBigProductsTab record);

    int insertSelective(QuoteBigProductsTab record);

    QuoteBigProductsTab selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QuoteBigProductsTab record);

    int updateByPrimaryKey(QuoteBigProductsTab record);
    
    
    
    /**
     * 根据询盘号查询
     * @Title queryByOrderId 
     * @Description TODO
     * @param orderId
     * @return
     * @return List<QuoteBigProductsTab>
     */
    List<QuoteBigProductsTab> selectByProductId(@Param("bigProductId")Integer bigProductId);
    
    
    /**
     * 根据报价产品表id查询
     * @Title selectByProductId 
     * @Description TODO
     * @param productId
     * @return
     * @return List<QuoteBigProductsTab>
     */
    QuoteBigProductsTab selectByQuoteProductId(@Param("productId")Integer productId);
    
}