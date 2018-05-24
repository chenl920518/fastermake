package com.cbt.service;

import java.util.List;

import com.cbt.entity.BigBuyerComment;


/*
 * add by jason
 * 
 * 大买家专区
 */


public interface BigBuyerCommentService {

   /*
    * 根据条件进行筛选
    * 
    * condition：排序条件（默认截止时间）
    * 
    * isComment: 是否点评 （默认为0）
    * 
    * 
    */
    List<BigBuyerComment> selectOrderByCondition( Integer start,Integer pageSize);
	
    
	int totalOrder();
	
	
	BigBuyerComment selectByPrimaryKey(Integer id);
	
	
	/*
	 * 删除数据
	 */
	int deleteByPrimaryKey(Integer id);
   
	
	/*
	 * 更新数据
	 */
	int updateSelective(BigBuyerComment record);
	
	
	/*
	 * 插入新数据
	 */
	int insert(BigBuyerComment record);
   
   
}