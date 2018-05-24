package com.cbt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbt.dao.UserOrderMapper;
import com.cbt.entity.UserOrder;
import com.cbt.service.UserOrderService;

@Service 
public class UserOrderServiceImpl implements UserOrderService {

	@Autowired
	private UserOrderMapper userOrderMapper;
	
	@Override
	public int selectByFactoryUserIdAndOrderId(Integer factoryUserId,
			Integer orderId) {
		return userOrderMapper.selectByFactoryUserIdAndOrderId(factoryUserId, orderId);
	}

	@Override
	public List<UserOrder> selectByFactoryUserId(Integer factoryUserId) {
		return userOrderMapper.selectByFactoryUserId(factoryUserId);
	}

}
