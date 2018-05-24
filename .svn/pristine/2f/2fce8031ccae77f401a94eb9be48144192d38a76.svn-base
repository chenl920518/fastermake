package com.cbt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbt.dao.FactoryEvaluateMapper;
import com.cbt.dao.FactoryInfoMapper;
import com.cbt.entity.FactoryEvaluate;
import com.cbt.entity.FactoryEvaluateWithBLOBs;
import com.cbt.service.FactoryEvaluateService;

@Service
public class FactoryEvaluateServiceImpl implements FactoryEvaluateService {

	@Autowired
	private FactoryEvaluateMapper factoryEvaluateMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return factoryEvaluateMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(FactoryEvaluateWithBLOBs record) {

		return factoryEvaluateMapper.insert(record);
	}

	@Override
	public int insertSelective(FactoryEvaluateWithBLOBs record) {

		return factoryEvaluateMapper.insertSelective(record);
	}

	@Override
	public FactoryEvaluateWithBLOBs selectByPrimaryKey(Integer id) {

		return factoryEvaluateMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(FactoryEvaluateWithBLOBs record) {

		return factoryEvaluateMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(FactoryEvaluateWithBLOBs record) {

		return factoryEvaluateMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(FactoryEvaluate record) {

		return factoryEvaluateMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<FactoryEvaluateWithBLOBs> selectByFactoryId(String factoryId,String viewId) {

		return factoryEvaluateMapper.selectByFactoryId(factoryId,viewId);
	}

	@Override
	public int selectCountByFactoryId(String factoryId) {
		return factoryEvaluateMapper.selectCountByFactoryId(factoryId);
	}

	@Override
	public int selectEvaluatePreference(String factoryEvaluateId, String viewId) {
		// TODO Auto-generated method stub
		return factoryEvaluateMapper.selectEvaluatePreference(factoryEvaluateId, viewId);
	}

	@Override
	public int insertEvaluatePreference(String factoryEvaluateId, String viewId) {
		// TODO Auto-generated method stub
		return factoryEvaluateMapper.insertEvaluatePreference(factoryEvaluateId, viewId);
	}

	@Override
	public int deleteEvaluatePreference(String factoryEvaluateId, String viewId) {
		// TODO Auto-generated method stub
		return factoryEvaluateMapper.deleteEvaluatePreference(factoryEvaluateId, viewId);
	}

	@Override
	public int selectCountEvaluateById(String factoryEvaluateId) {
		// TODO Auto-generated method stub
		return factoryEvaluateMapper.selectCountEvaluateById(factoryEvaluateId);
	}
	
	
	

}
