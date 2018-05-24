package com.cbt.service;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cbt.entity.FactoryInfo;
import com.cbt.entity.FactoryUser;
import com.cbt.exception.NameOrPasswordException;


/**
 * Edit 修改查询有意向报价工厂（去除当前登录公司）
 * @ClassName FactoryInfoService 
 * @Description
 * @author polo
 * @date 2018年4月8日 下午1:22:21
 */
public interface FactoryInfoService {
	   /**
	    * 登录方法
	    * userid  用户id
	    * password 密码
	    * 登录成功时候返回用户的信息
	    * NameOrPasswordException
	    *   用户名或密码错误
	    *   用户名或密码为空
	    */
	public FactoryInfo login(String loginEmail,String pwd) throws NameOrPasswordException;
	
	
	
	public FactoryInfo selectByPrimaryKey(Integer id);
	
    /**
     * 查询当前时间最大登录ID
     */
    public int selectMaxId();   
    /**
     * 插入供应商信息
     * @param record
     * @return
     */
    public FactoryUser insertSelective(FactoryInfo record)throws ParseException;
    
    /**
     * 更新供应商信息
     * @param record
     * @return
     */
   public int updateByPrimaryKeySelective(FactoryInfo record);
   
   /**
    * 更新供应商信息
    * @param record
    * @return
    */
  public int updateByPrimaryKeySelective(FactoryInfo record,FactoryUser factoryUser);  
   /**
    * 根据登录邮箱查询
    * @param loginEmail
    * @return
    */
   public FactoryInfo selectByLoginEmail(String loginEmail);
   /**
    * 查询企业(供应商)基本信息
    * @param factoryId
    * @return
    */
   public FactoryInfo selectFactoryInfo(String factoryId);  
   
   /**
    * 查询企业(供应商)信息、设备信息
    * @param factoryId
    * @return
    */
   public FactoryInfo selectFactoryInfoAndEquipment(String factoryId);
   
   
   
   
    //收藏
   public int insertFactoryPreference( String factoryId,String buyerId,String factoryName,String createDate);
 		
 	//取消收藏
   public int deleteFactoryPreference(String factoryId,String buyerId);
 		
   //查看是否收藏
   public int selectFactoryPreference( String factoryId,String buyerId);
 		
   
   public List selectFactoryListByBuyerId(String buyerId);
   
	/**
	 * 查询所有用户信息  并且查看是否有未审核询盘
	 * @Title queryFactoryList 
	 * @Description TODO
	 * @return
	 * @return List<FactoryInfo>
	 */
	public List<FactoryInfo> queryFactoryList();
	
	
	/*
	 * add by jason
	 * 查询工厂信息（产品、资质）
	 * 
	 */
	public List<FactoryInfo> selectByCondition(Map map);
	
	
	public int totalOrder(Map map);
    
	/**
	 * 删除工厂运行视频
	 * @param factoryInfo
	 */
	public void delProductionVideo(FactoryInfo factoryInfo);
	
	
	
	/**
	 * 查询给询盘发送过消息未报价的工厂
	 * @Title queryByOrderIdAndQuoteMessage 
	 * @Description 
	 * @param orderId
	 * @return
	 * @return List<FactoryInfo>
	 */
	public List<FactoryInfo> queryByOrderIdAndQuoteMessage(Integer orderId,String factoryId);
	
	
	
	/**
	 * 根据工艺查询符合的工厂
	 * @Title queryByMainProcess 
	 * @Description 
	 * @param process
	 * @return
	 * @return List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryByMainProcess(String process);
	
}


