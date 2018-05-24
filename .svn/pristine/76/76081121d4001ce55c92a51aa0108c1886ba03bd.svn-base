package com.cbt.dao;

import java.util.List;

import com.cbt.entity.Qualification;

public interface QualificationMapper {
	
	
    int deleteByPrimaryKey(Integer id);

    int insert(Qualification record);

    int insertSelective(Qualification record);

    Qualification selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Qualification record);

    int updateByPrimaryKey(Qualification record);
    
    
    
    /**
     * 查询资格认证
     * @param factoryId
     * @return
     */
    List<Qualification> queryByFactory(String factoryId);
}