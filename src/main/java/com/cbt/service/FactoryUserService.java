package com.cbt.service;

import com.cbt.entity.FactoryUser;

public interface FactoryUserService {
    /**
     * 根据邮箱去查询
     * @Title selectByLoginEmail 
     * @Description 
     * @param loginEmail
     * @return
     * @return FactoryUser
     */
   public FactoryUser selectByLoginEmail(String loginEmail);
   
   
   /**
    * 更新用户信息
    * @Title updateByPrimaryKeySelective 
    * @Description TODO
    * @param record
    * @return
    * @return int
    */
   public int updateByPrimaryKeySelective(FactoryUser record);
   
   
   /**
    * 根据名称查询
    * @Title selectByRealName 
    * @Description 
    * @param realName
    * @return
    * @return FactoryUser
    */
  public FactoryUser selectByRealName(String realName);
}
