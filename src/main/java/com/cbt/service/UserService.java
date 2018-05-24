package com.cbt.service;

import com.cbt.entity.User;
import com.cbt.exception.NameOrPasswordException;

public interface UserService {


	
	
	   /**
	    * 登录方法
	    * userid  用户id
	    * password 密码
	    * 登录成功时候返回用户的信息
	    * NameOrPasswordException
	    *   用户名或密码错误
	    *   用户名或密码为空
	    */
	public User login(String loginEmail,String pwd) throws NameOrPasswordException;
	
	/**
	 * 根据客户名查询
	 * @param userid
	 * @return
	 */	  
	public User queryByUserId(String userid);
	
	/**
	 * 根据登录邮箱查询
	 * @param loginEmail
	 * @return
	 */	  
	public User queryByLoginMail(String loginEmail);
	
	
	

	
	/**
	 * 查询id最大值
	 * @return
	 */
	public Integer queryMaxId();
	
	
	/**
	 * 更新客户信息
	 * @param user
	 */
	void updateCustomerInfo(User user);
	
}
