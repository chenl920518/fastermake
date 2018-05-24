package com.cbt.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cbt.entity.FactoryInfo;

public interface FactoryInfoMapper {
	
	
    int deleteByPrimaryKey(Integer id);

    int insert(FactoryInfo record);

    int insertSelective(FactoryInfo record);

    FactoryInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FactoryInfo record);

    int updateByPrimaryKeyWithBLOBs(FactoryInfo record);
    
    List<FactoryInfo> selectByCondition(Map map);
    
    int totalOrder(Map map);
    
    
    /**
     * 根据登录邮箱查询
     * @param loginEmail
     * @return
     */
    FactoryInfo selectByLoginEmail(@Param("email")String loginEmail);
    
    
    /**
     * 查询当前最大ID
     */
    int selectMaxId();
    /**
     * 查询工厂基本信息
     * @param factoryId
     * @return
     */
	FactoryInfo selectFactoryInfo(String factoryId);
	
	
	
	FactoryInfo selectFactoryInfoAndEquipment(String factoryId);
	
	
	//收藏
	int insertFactoryPreference( @Param(value = "factoryId") String factoryId,@Param(value = "buyerId")String buyerId,@Param(value = "factoryName")String factoryName,@Param(value = "createDate")String createDate);
		
	//取消收藏
	int deleteFactoryPreference(@Param(value = "factoryId") String factoryId,@Param(value = "buyerId")String buyerId);
		
		
	int selectFactoryPreference(@Param(value = "factoryId") String factoryId,@Param(value = "buyerId")String buyerId);
	
	List selectFactoryListByBuyerId(@Param(value = "buyerId")String buyerId);
	
	/**
	 * 查询所有用户信息
	 * @Title queryFactoryList 
	 * @Description 
	 * @return
	 * @return List<FactoryInfo>
	 */
	List<FactoryInfo> queryFactoryList();
    /**
     * 删除工厂运行视频
     * @param factoryInfo
     */
	void delProductionVideo(FactoryInfo factoryInfo);
	
	
	
	/**
	 * 查询给询盘发送过消息未报价的工厂
	 * @Title queryByOrderIdAndQuoteMessage 
	 * @Description 
	 * @param orderId
	 * @return
	 * @return List<FactoryInfo>
	 */
	List<FactoryInfo> queryByOrderIdAndQuoteMessage(@Param("orderId")Integer orderId,@Param("factoryId")String factoryId);
	
    
	/**
	 * 根据工艺查询符合的工厂
	 * @Title queryByMainProcess 
	 * @Description 
	 * @param process
	 * @return
	 * @return List<Map<String,Object>>
	 */
	List<Map<String,Object>> queryByMainProcess(@Param("process")String process);
}