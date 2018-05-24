package com.cbt.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbt.dao.SysUserMapper;
import com.cbt.entity.SysUser;
import com.cbt.service.SysUserService;

@Service
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserMapper sysUserMapper;

	@Override
	public SysUser selectByName(String loginName, String password) {
		
		return sysUserMapper.selectByNameAndPas(loginName, password);
	}

}
