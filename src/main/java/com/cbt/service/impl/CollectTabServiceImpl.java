package com.cbt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbt.dao.CollectTabMapper;
import com.cbt.entity.CollectTab;
import com.cbt.service.CollectTabService;


@Service
public class CollectTabServiceImpl implements CollectTabService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private CollectTabMapper collectTabMapper;
	
	
	@Override
	public void insertBatch(List<Object> list) {
		collectTabMapper.insertBatch(list);		
	}


	@Override
	public List<CollectTab> queryCollects(String factoryId) {
		return collectTabMapper.queryCollects(factoryId);
	}


	@Override
	public int queryByOrderIdAndFactoryId(String factoryId, Integer orderId) {
		return collectTabMapper.queryByOrderIdAndFactoryId(factoryId, orderId);
	}


	@Override
	public int insert(CollectTab record) {
		return collectTabMapper.insert(record);
	}


	@Override
	public int updateByPrimaryKeySelective(CollectTab record) {
		return collectTabMapper.updateByPrimaryKeySelective(record);
	}


	@Override
	public int deleteByOrderIdAndFactoryId(String factoryId, Integer orderId) {
		return collectTabMapper.deleteByOrderIdAndFactoryId(factoryId, orderId);
	}

}
