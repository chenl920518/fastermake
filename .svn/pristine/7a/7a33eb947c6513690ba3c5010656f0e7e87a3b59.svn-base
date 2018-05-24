package com.cbt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cbt.entity.CollectTab;

public interface CollectTabMapper {
	
	
    int deleteByPrimaryKey(Integer id);

    int insert(CollectTab record);

    int insertSelective(CollectTab record);

    CollectTab selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CollectTab record);

    int updateByPrimaryKey(CollectTab record);
    
    
  	/**
  	 * 批量收藏
  	 */	
   	void insertBatch(List<Object> list);
   	
   	
   	/**
   	 * 查询收藏订单
   	 * @param factoryId
   	 * @return
   	 */
   	List<CollectTab> queryCollects(String factoryId);
   	/**
   	 * 根据工厂和询盘号查询是否收藏
   	 * @param factoryId
   	 * @return
   	 */
   	int queryByOrderIdAndFactoryId(@Param("factoryId")String factoryId,@Param("orderId")Integer orderId);
   	
   	
   	/**
   	 * 删除收藏
   	 * @param factoryId
   	 * @return
   	 */
   	int deleteByOrderIdAndFactoryId(@Param("factoryId")String factoryId,@Param("orderId")Integer orderId);
}