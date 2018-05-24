package com.cbt.service;

import java.util.List;

import com.cbt.entity.UserOrder;

public interface UserOrderService {
    /**
     * 根据用户id和询盘号查询
     * @Title selectByFactoryUserIdAndOrderId 
     * @Description
     * @param factoryUserId
     * @param orderId
     * @return
     * @return int
     */
    public int selectByFactoryUserIdAndOrderId(Integer factoryUserId,Integer orderId);
    
    
    
    
    /**
     * 查询询盘列表
     * @Title selectByFactoryUserId 
     * @Description TODO
     * @param factoryUserId
     * @return
     * @return List<UserOrder>
     */
   public List<UserOrder> selectByFactoryUserId(Integer factoryUserId);
}
