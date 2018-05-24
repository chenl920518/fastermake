package com.cbt.service;

import com.cbt.entity.BuyerInfo;
import com.cbt.exception.NameOrPasswordException;

public interface BuyerInfoService {
	   /**
	    * 登录方法
	    * userid  用户id
	    * password 密码
	    * 登录成功时候返回用户的信息
	    * NameOrPasswordException
	    *   用户名或密码错误
	    *   用户名或密码为空
	    */
	public BuyerInfo login(String loginEmail,String pwd) throws NameOrPasswordException;
	
	
	
	
	 /**
	  * 查询当前时间最大登录ID
	  */
	 public int selectMaxId();
	 
	 
	 /**
	  * 插入供应商信息
	  * @param record
	  * @return
	  */
	 public int insertSelective(BuyerInfo record);
	 
	 
	 /**
	  * 更新供应商信息
	  * @param record
	  * @return
	  */
	public int updateByPrimaryKeySelective(BuyerInfo record);
	
	
	
	/**
	 * 根据登录邮箱查询
	 * @param loginEmail
	 * @return
	 */
	public BuyerInfo selectByLoginEmail(String loginEmail);
}
