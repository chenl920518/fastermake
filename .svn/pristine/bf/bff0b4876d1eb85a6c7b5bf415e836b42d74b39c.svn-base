package com.cbt.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.cbt.entity.SupplierQuoteInfo;

public interface SupplierQuoteInfoService extends Serializable {

	/**
	 * 插入报价
	 * @param record
	 * @return
	 */
	public int insert(SupplierQuoteInfo record);
	
	
	/**
	 * 更新报价
	 * @param record
	 * @return
	 */
	public int updateByPrimaryKeySelective(SupplierQuoteInfo record);
	
	
	
	
	public Map<String,Object> insert(SupplierQuoteInfo record,String productList,String productIds,String productRemarks);
	
	
	
	
	
	/**
     * 查询工厂报价
     * @param orderId
     * @param factoryId
     * @return
     */
    public SupplierQuoteInfo queryByOrderIdAndFactoryId(Integer orderId,String factoryId);
    
    
    
    /**
     * 根据ID查询
     * @param id
     * @return
     */
    public SupplierQuoteInfo selectByPrimaryKey(Integer id);
    
    
    
    /**
     * 根据询盘号查询工厂报价
     * @Title queryByOrderId 
     * @Description TODO
     * @param orderId
     * @return
     * @return List<SupplierQuoteInfo>
     */
    public List<SupplierQuoteInfo> queryByOrderId(Integer orderId);
    
    
    /**
     * 批量更新工厂报价状态
     * @Title updateOrderStatus 
     * @Description TODO
     * @param list
     * @return void
     */
    public void updateOrderStatus(List<SupplierQuoteInfo> list);
    
    
    
    /**
     * 根据采购商ID、供应商ID、询盘号查询报价
     * @Title queryQuoteDetailByFactoryId 
     * @Description TODO
     * @param orderId
     * @param factoryId
     * @param customerId
     * @return
     * @return SupplierQuoteInfo
     */
    public SupplierQuoteInfo queryQuoteDetailByFactoryId(Integer orderId,String factoryId,String customerId);
    
    
    
    /**
     * 拒绝工厂理由
     * @Title updateQuote 
     * @Description TODO
     * @param refuseReasons
     * @return void
     */
    public void updateQuote(Integer orderId,String refuseReasons); 
    
    /**
     * 查询订单报价工厂数
     * @return
     */
    public int totalQuoteFactory(Integer orderId); 
}
