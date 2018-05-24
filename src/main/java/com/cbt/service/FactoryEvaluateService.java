package com.cbt.service;

import java.util.List;

import com.cbt.entity.FactoryEvaluate;
import com.cbt.entity.FactoryEvaluateWithBLOBs;


public interface FactoryEvaluateService {
	
	
	    int deleteByPrimaryKey(Integer id);

	    int insert(FactoryEvaluateWithBLOBs record);

	    int insertSelective(FactoryEvaluateWithBLOBs record);

	    FactoryEvaluateWithBLOBs selectByPrimaryKey(Integer id);

	    int updateByPrimaryKeySelective(FactoryEvaluateWithBLOBs record);

	    int updateByPrimaryKeyWithBLOBs(FactoryEvaluateWithBLOBs record);

	    int updateByPrimaryKey(FactoryEvaluate record);
	    
	    
	    /*
	     * 根据工厂id查询评价列表
	     */
	    List<FactoryEvaluateWithBLOBs> selectByFactoryId(String factoryId,String viewId);

	    
	  
	    /*
	     * 根据工厂id查询评价数
	     */
	    int selectCountByFactoryId(String factoryId);
	
	
        /*
         *查看是否点赞
         */
	    int selectEvaluatePreference( String factoryEvaluateId,String viewId);
	    
	    /*
	     * 点赞
	     * 
	     */
	    
	    int insertEvaluatePreference(String factoryEvaluateId,String viewId);

	    /*
	     * 取消点赞
	     */
	    
	    
	    int deleteEvaluatePreference(String factoryEvaluateId,String viewId);
	    
	    
	    /*
	     * 查看点赞数
	     */
	 
	    int selectCountEvaluateById(String factoryEvaluateId);
   
    
   

   
   
}