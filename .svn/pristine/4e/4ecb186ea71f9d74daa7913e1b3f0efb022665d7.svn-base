package com.cbt.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbt.dao.ProductLibraryMapper;
import com.cbt.dao.QuoteProductMapper;
import com.cbt.entity.ProductLibrary;
import com.cbt.entity.ProductLibraryMessage;
import com.cbt.entity.QuoteInfo;
import com.cbt.entity.QuoteProduct;
import com.cbt.service.ProductLibraryService;
import com.cbt.service.QuoteProductService;


@Service
public class ProductLibraryServiceImpl implements ProductLibraryService {
	
	@Autowired
	private ProductLibraryMapper productLibraryMapper;

	@Override
	public List<ProductLibrary> queryProductGroupByCondition(Map map) {
		// TODO Auto-generated method stub
		return productLibraryMapper.selectByCondition(map);
	}

	@Override
	public void insertMessageToFactory(ProductLibraryMessage pro) {
		productLibraryMapper.insertMessageToFactory(pro);
	}
    
	@Override
	public int totalOrder(Map map) {
		
		return productLibraryMapper.totalOrder(map);
		
	}

	@Override
	public int insertNewProduct(ProductLibrary productLibrary) {
	
		return productLibraryMapper.insertNewProduct( productLibrary);
	}

	@Override
	public List<ProductLibrary> queryProductGroupFactoryId(String factoryId,
			String process) {
		return productLibraryMapper.selectByFactoryId(factoryId, process);
	}

	@Override
	public int deleteByPrimaryKey(int id) {
		// TODO Auto-generated method stub
		return productLibraryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateProductMessage(ProductLibrary productLibrary) {
		// TODO Auto-generated method stub
		return productLibraryMapper.updateByPrimaryKeySelective(productLibrary);
	}

	@Override
	public ProductLibrary selectByPrimaryKey(int id) {
		
		return productLibraryMapper.selectByPrimaryKey(id);
	}

	@Override
	public int selectCountByFactoryId(String factoryId, String process) {
		
		return productLibraryMapper.selectCountByFactoryId(factoryId, process);
	}

	@Override
	public int insertProductPreference(String factoryId, String productId,
			String createDate) {
		// TODO Auto-generated method stub
		return productLibraryMapper.insertProductPreference(factoryId, productId, createDate);
	}

	@Override
	public int deleteProductPreference(String factoryId, String productId) {
		// TODO Auto-generated method stub
		return productLibraryMapper.deleteProductPreference(factoryId, productId);
	}

	@Override
	public int selectProductPreference(String factoryId, String productId) {
		// TODO Auto-generated method stub
		return productLibraryMapper.selectProductPreference(factoryId, productId);
	}

	@Override
	public int selectCountByFIdAndPName(String factoryId, String productName) {
		int i = 0;
		int result = 0;
		i = productLibraryMapper.selectCountByFIdAndPName(factoryId, productName);
		if(i>0){
			result = 1;
		}else{
			result = 0;
		}
		return result;
	}

	@Override
	public List<ProductLibrary> queryProductGroupFactoryIdAndPage(
			String factoryId, Integer start, Integer pageSize) {
		return productLibraryMapper.selectByFactoryIdAndPage(factoryId, start, pageSize);
	}

	
	

	

	
	
	

	

}
