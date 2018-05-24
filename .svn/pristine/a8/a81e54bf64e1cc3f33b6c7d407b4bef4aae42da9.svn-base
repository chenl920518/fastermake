package com.cbt.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbt.dao.UserDao;
import com.cbt.entity.User;
import com.cbt.exception.NameOrPasswordException;
import com.cbt.service.UserService;
@Service
public class UserServiceImpl implements UserService {
   
	@Autowired
	private UserDao userDao;
	

   
	public User login(String loginEmail, String pwd) throws NameOrPasswordException {
		//入口参数检查
		if(loginEmail==null || loginEmail.trim().isEmpty()){
			throw new NameOrPasswordException("邮箱不能为空");
		}
		if(pwd==null || pwd.trim().isEmpty()){
			throw new NameOrPasswordException("密码不能为空");
		}
		//从业务层查询用户信息
		User user = userDao.queryByLoginMail(loginEmail);
		if(user==null){
			throw new NameOrPasswordException("邮箱未注册，请先注册");
		}
		if(user.getNickname().equals(pwd)){
			return user;                    //登录成功
		} else{
			throw new NameOrPasswordException("账号或者密码错误");
		}	
		
		
	}

	
	@Override
	public User queryByUserId(String userid) {
		User user = userDao.queryByUserId(userid);
		return user;
	}

	@Override
	public User queryByLoginMail(String loginEmail) {
		
		return userDao.queryByLoginMail(loginEmail);
	}



	@Override
	public Integer queryMaxId() {
		return userDao.queryMaxId();
	}

	@Override
	public void updateCustomerInfo(User user) {
		userDao.updateCustomerInfo(user);
		
	}

	
	
}
