package com.cbt.service;

import java.util.List;

import com.cbt.entity.QuoteBigProductsTab;

public interface QuoteBigProductsTabService {

	
	public int insertSelective(QuoteBigProductsTab record);

	public QuoteBigProductsTab selectByPrimaryKey(Integer id);

	public int updateByPrimaryKeySelective(QuoteBigProductsTab record);
    
    
    
    /**
     * 根据询盘号查询
     * @Title queryByOrderId 
     * @Description TODO
     * @param orderId
     * @return
     * @return List<QuoteBigProductsTab>
     */
    public List<QuoteBigProductsTab> selectByProductId(Integer bigProductId);
    
    
    
    /**
     * 根据报价产品表id查询
     * @Title selectByProductId 
     * @Description TODO
     * @param productId
     * @return
     * @return List<QuoteBigProductsTab>
     */
    public QuoteBigProductsTab selectByQuoteProductId(Integer productId);
}
