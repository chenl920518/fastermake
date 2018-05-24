package com.cbt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbt.dao.PreferenceSetMapper;
import com.cbt.entity.PreferenceSet;
import com.cbt.service.PreferenceSetService;

@Service
public class PreferenceSetServiceImpl implements PreferenceSetService {
    
	@Autowired
	private PreferenceSetMapper preferenceSetMapper;
    /**
     * 查询偏好设置列表新
     */
	@Override
	public List<PreferenceSet> selectPreferenceSetList(String factoryId) {
		return preferenceSetMapper.selectByPrimaryKey(factoryId);
	}
	@Override
	public void updatePreferenceSet(PreferenceSet preferenceSet) {
		preferenceSetMapper.updateByPrimaryKeySelective(preferenceSet);
	}
	
}
