package com.cbt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cbt.entity.BigBuyerComment;


public interface BigBuyerCommentMapper {
	
	
	List<BigBuyerComment> selectOrderByCondition(@Param(value = "condition") String condition,@Param(value = "isComment") Integer isComment,@Param(value = "start") Integer start,@Param(value = "pageSize") Integer pageSize);
	
	int totalOrder(@Param(value = "isComment") Integer isComment);

	
	
    int deleteByPrimaryKey(Integer id);

    int insert(BigBuyerComment record);

    int insertSelective(BigBuyerComment record);

    BigBuyerComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BigBuyerComment record);


    int updateByPrimaryKey(BigBuyerComment record);
}