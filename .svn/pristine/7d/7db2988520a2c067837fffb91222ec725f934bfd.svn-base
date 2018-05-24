package com.cbt.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbt.dao.FactoryUserMapper;
import com.cbt.entity.FactoryUser;
import com.cbt.service.FactoryUserService;

@Service
public class FactoryUserServiceImpl implements FactoryUserService {

	@Autowired
	private FactoryUserMapper factoryUserMapper;
	
	@Override
	public FactoryUser selectByLoginEmail(String loginEmail) {
		return factoryUserMapper.selectByLoginEmail(loginEmail);
	}

	@Override
	public int updateByPrimaryKeySelective(FactoryUser record) {
		return factoryUserMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public FactoryUser selectByRealName(String realName) {
		return factoryUserMapper.selectByRealName(realName);
	}

	
	
	
}
