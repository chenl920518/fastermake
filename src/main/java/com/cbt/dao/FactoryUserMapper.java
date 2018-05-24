package com.cbt.dao;

import com.cbt.entity.FactoryUser;

public interface FactoryUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FactoryUser record);

    int insertSelective(FactoryUser record);

    FactoryUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FactoryUser record);

    int updateByPrimaryKey(FactoryUser record);
        
    /**
     * 根据邮箱去查询
     * @Title selectByLoginEmail 
     * @Description 
     * @param loginEmail
     * @return
     * @return FactoryUser
     */
    FactoryUser selectByLoginEmail(String loginEmail);
    /**
     * 根据名称查询
     * @Title selectByRealName 
     * @Description 
     * @param realName
     * @return
     * @return FactoryUser
     */
    FactoryUser selectByRealName(String realName);
    
}