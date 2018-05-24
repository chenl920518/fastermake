package com.cbt.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cbt.dao.LoginLogDao;
import com.cbt.entity.LoginLog;
import com.cbt.service.LoginLogService;

@Service
public class LoginLogServiceImpl implements LoginLogService {
    
	@Autowired
	private LoginLogDao loginLogDao;
	@Override
	public LoginLog queryById(Integer id) {
		
		return loginLogDao.queryById(id);
	}

	@Transactional
	@Override
	public void insertLoginLog(LoginLog loginLog) {
		loginLogDao.insertLoginLog(loginLog);

	}

}
