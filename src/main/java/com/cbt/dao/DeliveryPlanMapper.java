package com.cbt.dao;

import com.cbt.entity.DeliveryPlan;

public interface DeliveryPlanMapper {
	
	
    int deleteByPrimaryKey(Integer id);

    int insert(DeliveryPlan record);

    int insertSelective(DeliveryPlan record);

    DeliveryPlan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DeliveryPlan record);

    int updateByPrimaryKey(DeliveryPlan record);
}