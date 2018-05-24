package com.cbt.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cbt.entity.ProductLibrary;
import com.cbt.entity.ProductLibraryMessage;

public interface ProductLibraryMapper {

	ProductLibrary selectByPrimaryKey(Integer id);

	List<ProductLibrary> selectByCondition(Map map);

	void insertMessageToFactory(ProductLibraryMessage pro);

	int totalOrder(Map map);
	
	int insertNewProduct( ProductLibrary productLibrary);
	
	int deleteByPrimaryKey(Integer id);
	
	int selectCountByFactoryId(@Param(value = "factoryId") String factoryId,@Param(value = "process")String process);
	
	List<ProductLibrary> selectByFactoryId(@Param(value = "factoryId") String factoryId,@Param(value = "process")String process );
	
	List<ProductLibrary> selectByFactoryIdAndPage(@Param(value = "factoryId") String factoryId,@Param("start")Integer start,@Param("pageSize")Integer pageSize);

	int updateByPrimaryKeySelective(ProductLibrary productLibrary);
	
	//收藏
	int insertProductPreference( @Param(value = "factoryId") String factoryId,@Param(value = "productId")String productId,@Param(value = "createDate")String createDate);
	
	//取消收藏
	int deleteProductPreference(@Param(value = "factoryId") String factoryId,@Param(value = "productId")String productId);
	
	
	int selectProductPreference(@Param(value = "factoryId") String factoryId,@Param(value = "productId")String productId);
	
	
	int selectCountByFIdAndPName(@Param(value = "factoryId") String factoryId, @Param(value = "productName")String productName);
	
	
	

}