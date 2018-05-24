package com.cbt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cbt.entity.UserOrder;

public interface UserOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserOrder record);

    int insertSelective(UserOrder record);

    UserOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserOrder record);

    int updateByPrimaryKey(UserOrder record);
    
    /**
     * 根据用户id和询盘号查询
     * @Title selectByFactoryUserIdAndOrderId 
     * @Description
     * @param factoryUserId
     * @param orderId
     * @return
     * @return int
     */
    int selectByFactoryUserIdAndOrderId(@Param("factoryUserId")Integer factoryUserId,@Param("orderId")Integer orderId);
    
    
    /**
     * 查询询盘列表
     * @Title selectByFactoryUserId 
     * @Description TODO
     * @param factoryUserId
     * @return
     * @return List<UserOrder>
     */
    List<UserOrder> selectByFactoryUserId(@Param("factoryUserId")Integer factoryUserId);
    
    
    /**
     * 根据项目号查询报价助理
     * @Title selectAssistantByCgsOrderId 
     * @Description
     * @param cgsOrderId
     * @return
     * @return UserOrder
     */
    UserOrder selectAssistantByCgsOrderId(@Param("cgsOrderId")String cgsOrderId);
    
}