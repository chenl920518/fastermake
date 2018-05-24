package com.cbt.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbt.dao.BuyerInfoMapper;
import com.cbt.entity.BuyerInfo;
import com.cbt.exception.NameOrPasswordException;
import com.cbt.service.BuyerInfoService;

@Service
public class BuyerServiceImpl implements BuyerInfoService {

	@Autowired 
	private BuyerInfoMapper buyerInfoMapper;
	
	@Override
	public BuyerInfo login(String loginEmail, String pwd)throws NameOrPasswordException {
		//入口参数检查
		if(loginEmail==null || loginEmail.trim().isEmpty()){
			throw new NameOrPasswordException("Email can not be empty");
		}
		if(pwd==null || pwd.trim().isEmpty()){
			throw new NameOrPasswordException("password can not be empty");
		}
		//从业务层查询用户信息
		BuyerInfo buyerInfo = buyerInfoMapper.selectByLoginEmail(loginEmail);
		if(buyerInfo==null){
			throw new NameOrPasswordException("Not yet registered,please register first");
		}
		if(buyerInfo.getPwd().equals(pwd)){
			return buyerInfo;                    //登录成功
		} else{
			throw new NameOrPasswordException("wrong password");
		}	
	}

	@Override
	public int selectMaxId() {
		return buyerInfoMapper.selectMaxId();
	}

	@Override
	public int insertSelective(BuyerInfo record) {
		return buyerInfoMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(BuyerInfo record) {
		return buyerInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public BuyerInfo selectByLoginEmail(String loginEmail) {
		return buyerInfoMapper.selectByLoginEmail(loginEmail);
	}

}
