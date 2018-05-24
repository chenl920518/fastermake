package com.cbt.dao;

import java.util.List;

import com.cbt.entity.User;

public interface UserDao {
  
	/**
	 * 批量插入用户
	 */	
	void insertUser(List<Object> list);
   
	/**
	 * 根据客户名查询
	 * @param userid
	 * @return
	 */	  
	User queryByUserId(String userid);
	/**
	 * 根据登录邮箱查询
	 * @param loginEmail
	 * @return
	 */	  
	User queryByLoginMail(String loginEmail);
	
	
	/**
	 * 插入用户
	 * @param user
	 */
	void insert(User user);
	
	

	/**
	 * 查询id最大值
	 * @return
	 */
	Integer queryMaxId();
	
	
	/**
	 * 更新客户信息
	 * @param user
	 */
	void updateCustomerInfo(User user);
}
