package com.cbt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbt.dao.SupplierQuoteProductMapper;
import com.cbt.entity.SupplierQuoteProduct;
import com.cbt.service.SupplierQuoteProductService;


@Service
public class SupplierQuoteProductServiceImpl implements
		SupplierQuoteProductService {

	@Autowired
	private SupplierQuoteProductMapper supplierQuoteProductMapper;
	
	@Override
	public List<SupplierQuoteProduct> queryBySupplierQuoteId(Integer supplierQuoteId) {
		return supplierQuoteProductMapper.queryBySupplierQuoteId(supplierQuoteId);
	}

	@Override
	public List<SupplierQuoteProduct> selectQuoteGroupByQuoteId(
			String factoryId, Integer orderId) {
		return supplierQuoteProductMapper.selectQuoteGroupByQuoteId(factoryId, orderId);
	}

	@Override
	public List<SupplierQuoteProduct> selectQuoteList(Integer supplierQuoteId,
			String factoryId, Integer orderId) {
		return supplierQuoteProductMapper.selectQuoteList(supplierQuoteId, factoryId, orderId);
	}

}
