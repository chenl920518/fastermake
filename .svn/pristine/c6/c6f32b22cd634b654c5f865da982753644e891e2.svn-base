package com.cbt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cbt.entity.QuoteInfo;

public interface QuoteInfoMapper {
	
	
    int deleteByPrimaryKey(Integer id);

    int insert(QuoteInfo record);
    
    int insertBackOrderId(QuoteInfo record);

    int insertSelective(QuoteInfo record);

    QuoteInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QuoteInfo record);

    int updateByPrimaryKeyWithBLOBs(QuoteInfo record);

    int updateByPrimaryKey(QuoteInfo record);
    
    
    
    /**
     * 根据询盘号查询
     * @param orderId
     * @return
     */
    QuoteInfo queryByOrderId(Integer orderId);
    
    /**
     * 根据询盘号和客户ID查询
     * @param orderId
     * @return
     */
    QuoteInfo queryByOrderIdAndFactoryId(@Param("orderId")Integer orderId,@Param("customerId")String customerId);
    
    /**
     * 根据询盘状态查询
     * @return
     */
    List<QuoteInfo> queryAllInquiryOrder(@Param("start")Integer start,@Param("pageSize")Integer pageSize,
    		@Param("orderStatus")Integer orderStatus);
    
    /**
     * 根据询盘状态查询
     * @return
     */
    List<QuoteInfo> queryAll(@Param("orderStatus")Integer orderStatus);
    
    
    /**
     * 查询询盘数
     * @return
     */
    int totalNormalOrder(@Param("orderStatus")Integer orderStatus);
    
    
    /**
     * 根据工厂ID查询已结束询盘
     * @Title queryFinishOrderByFactoryId 
     * @Description TODO
     * @param factoryId
     * @return
     * @return List<QuoteInfo>
     */
    List<QuoteInfo> queryFinishOrderByFactoryId(String factoryId);
    
    
    
    /*
     * 根据orderid 查出所有详情
     * 
     * @return QuoteInfo
     * 
     * add by jason
     */
    
    QuoteInfo  selectDetailByPrimaryKey(@Param("orderId")Integer orderId,@Param("buyerId")String buyerId);
     
    
    
    /*
     * 根据customerId 查出所有详情list
     * 
     * @return List<QuoteInfo>
     * 
     * add by jason
     */
    List<QuoteInfo> selectDetailListByFactoryId(@Param("orderStatus")Integer orderStatus,@Param("customerId")String customerId);
     
    
     
    /**
     * 根据项目号和状态查询
     * @Title queryByCgsOrderId 
     * @Description
     * @param cgsOrderId
     * @param orderStatus
     * @return
     * @return int
     */
    int queryCountByCgsOrderId(@Param("csgOrderId")String cgsOrderId,@Param("orderStatus")Integer orderStatus);
    
    
    
    /**
     * 根据项目号查询最新的询盘
     * @Title queryByCgsOrderId 
     * @Description
     * @param cgsOrderId
     * @return
     * @return QuoteInfo
     */
    QuoteInfo queryByCgsOrderId(@Param("csgOrderId")String cgsOrderId);
    
    
    
    
    /**
     * 多种状态查询
     * @Title queryOrderFactory 
     * @Description
     * @return
     * @return List<?>
     */
    <T>List<?> queryOrderFactoryList(@Param("item")Integer...item);
}