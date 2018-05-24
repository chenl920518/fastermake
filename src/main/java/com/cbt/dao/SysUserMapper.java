package com.cbt.dao;

import org.apache.ibatis.annotations.Param;

import com.cbt.entity.SysUser;

public interface SysUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);
    
    SysUser selectByNameAndPas(@Param("loginName")String loginName,@Param("password")String password);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
}