package com.cbt.service;

import java.io.Serializable;
import java.util.List;

import com.cbt.entity.CollectTab;

public interface CollectTabService extends Serializable {

	
	
	
	
  /**
   * 单条插入	
   * @param record
   * @return
   */
  public int insert(CollectTab record);
	
	
  /**
   * 更新
   * @param record
   * @return
   */
  public int updateByPrimaryKeySelective(CollectTab record);
	
	
	
    
  	/**
  	 * 批量收藏
  	 */	
   public void insertBatch(List<Object> list);
   
   
  	/**
  	 * 查询收藏订单
  	 * @param factoryId
  	 * @return
  	 */
   public List<CollectTab> queryCollects(String factoryId);
   
   
  	/**
  	 * 根据工厂和询盘号查询是否收藏
  	 * @param factoryId
  	 * @return
  	 */
  	public int queryByOrderIdAndFactoryId(String factoryId,Integer orderId);
  	/**
  	 * 删除收藏
  	 * @param factoryId
  	 * @return
  	 */
  	public int deleteByOrderIdAndFactoryId(String factoryId,Integer orderId);
}
