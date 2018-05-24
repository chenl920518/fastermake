package com.cbt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbt.dao.QuoteBigProductsTabMapper;
import com.cbt.entity.QuoteBigProductsTab;
import com.cbt.service.QuoteBigProductsTabService;

@Service
public class QuoteBigProductsTabServiceImpl implements QuoteBigProductsTabService {

    @Autowired 
    private QuoteBigProductsTabMapper quoteBigProductsTabMapper;
	
	@Override
	public int insertSelective(QuoteBigProductsTab record) {
		return quoteBigProductsTabMapper.insertSelective(record);
	}

	@Override
	public QuoteBigProductsTab selectByPrimaryKey(Integer id) {
		return quoteBigProductsTabMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(QuoteBigProductsTab record) {
		return quoteBigProductsTabMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<QuoteBigProductsTab> selectByProductId(Integer bigProductId) {
		return quoteBigProductsTabMapper.selectByProductId(bigProductId);
	}

	@Override
	public QuoteBigProductsTab selectByQuoteProductId(Integer productId) {
		return quoteBigProductsTabMapper.selectByQuoteProductId(productId);
	}

}
