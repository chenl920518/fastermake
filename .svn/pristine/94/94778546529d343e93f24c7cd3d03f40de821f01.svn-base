package com.cbt.service;

import java.util.List;
import java.util.Map;

import com.cbt.entity.ProductLibrary;
import com.cbt.entity.ProductLibraryMessage;


public interface ProductLibraryService {
	
	

	
	

   
   /**
    * 根据条件查询产品
    * @return
    */
   List<ProductLibrary> queryProductGroupByCondition(Map map);
   
   
   ProductLibrary  selectByPrimaryKey(int id);
   
   
   void insertMessageToFactory(ProductLibraryMessage pro);
   
   
   
   int  totalOrder(Map map);
   
   int insertNewProduct(ProductLibrary productLibrary);
   
   int deleteByPrimaryKey(int id);
   
   
   List<ProductLibrary> queryProductGroupFactoryId(String factoryId,String process);
   
   /*
    * 分页专用
    * 
    */
   List<ProductLibrary> queryProductGroupFactoryIdAndPage(String factoryId,Integer start,Integer pageSize);
   
   
   
   int  selectCountByFactoryId(String factoryId,String process);
   
   int updateProductMessage(ProductLibrary productLibrary);
   
   
    //收藏
    int insertProductPreference( String factoryId,String productId,String createDate);
 	
 	//取消收藏
 	int deleteProductPreference(String factoryId,String productId);
 	
 	
    int selectProductPreference(String factoryId,String productId);
 	
   
    /*
     * 
     * return : 0  没有产品
     * 
     * return ：1  有产品
     */
  
    int selectCountByFIdAndPName(String factoryId,String productName);
   
    
   

   
   
}