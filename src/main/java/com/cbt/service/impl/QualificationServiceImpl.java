package com.cbt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbt.dao.QualificationMapper;
import com.cbt.entity.Qualification;
import com.cbt.service.QualificationService;


@Service
public class QualificationServiceImpl implements QualificationService {

	@Autowired
	private QualificationMapper qualificationMapper;
	
	@Override
	public List<Qualification> queryByFactory(String factoryId) {
		return qualificationMapper.queryByFactory(factoryId);
	}

	@Override
	public void addQualification(Qualification qualification) {
		qualificationMapper.insertSelective(qualification);
	}

	@Override
	public void updateQualificatio(Qualification qualification) {
		qualificationMapper.updateByPrimaryKeySelective(qualification);
	}

	@Override
	public void delQualification(Integer id) {
		qualificationMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Qualification selectQualificationById(Integer id) {
		return qualificationMapper.selectByPrimaryKey(id);
	}

}
