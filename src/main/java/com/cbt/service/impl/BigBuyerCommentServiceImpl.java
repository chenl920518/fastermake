package com.cbt.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbt.dao.BigBuyerCommentMapper;
import com.cbt.dao.ProductLibraryMapper;
import com.cbt.dao.QuoteProductMapper;
import com.cbt.entity.BigBuyerComment;
import com.cbt.entity.ProductLibrary;
import com.cbt.entity.ProductLibraryMessage;
import com.cbt.entity.QuoteInfo;
import com.cbt.entity.QuoteProduct;
import com.cbt.service.BigBuyerCommentService;
import com.cbt.service.ProductLibraryService;
import com.cbt.service.QuoteProductService;


@Service
public class BigBuyerCommentServiceImpl implements BigBuyerCommentService {
	
	@Autowired
	private  BigBuyerCommentMapper bigBuyerCommentMapper;

	@Override
	public List<BigBuyerComment> selectOrderByCondition(Integer start, Integer pageSize) {
		
		return bigBuyerCommentMapper.selectOrderByCondition(null, null, start, pageSize);
	}

	@Override
	public int totalOrder() {
		return bigBuyerCommentMapper.totalOrder(null);
	}

	@Override
	public BigBuyerComment selectByPrimaryKey(Integer id) {

		return bigBuyerCommentMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		
		return bigBuyerCommentMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateSelective(BigBuyerComment record) {
		
		return bigBuyerCommentMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int insert(BigBuyerComment record) {
		
		return bigBuyerCommentMapper.insert(record);
	}
	
	
	
	
	

	
}
