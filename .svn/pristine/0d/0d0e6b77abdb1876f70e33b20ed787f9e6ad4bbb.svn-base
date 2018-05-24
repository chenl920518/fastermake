package com.cbt.dao;

import org.apache.ibatis.annotations.Param;

import com.cbt.entity.BuyerInfo;

public interface BuyerInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BuyerInfo record);

    int insertSelective(BuyerInfo record);

    BuyerInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BuyerInfo record);

    int updateByPrimaryKey(BuyerInfo record);
    
    
    
    /**
     * 根据登录邮箱查询
     * @param loginEmail
     * @return
     */
    BuyerInfo selectByLoginEmail(@Param("email")String loginEmail);
    
    
    /**
     * 查询当前最大ID
     */
    int selectMaxId();
}