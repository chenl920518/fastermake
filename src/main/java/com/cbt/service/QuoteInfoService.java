package com.cbt.service;
import java.text.ParseException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cbt.entity.NoteMessage;
import com.cbt.entity.QuoteInfo;
import com.cbt.entity.SupplierQuoteInfo;

public interface QuoteInfoService {

	/**
	 * 根据id删除
	 * 
	 * @param id
	 * @return
	 */
	public int deleteByPrimaryKey(Integer id);

	/**
	 * 插入询盘
	 * 
	 * @param record
	 * @return
	 */
	public int insert(QuoteInfo record);

	/**
	 * 插入询盘&零件
	 * 
	 * @param void
	 * @return
	 */

	public int insertAll(QuoteInfo quoteInfo);
        /**
	 * 更新询盘&零件
	 * 
	 * 
	 */
	public int updateAll(QuoteInfo quoteInfo);

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	public QuoteInfo selectByPrimaryKey(Integer id);

	/**
	 * 更新数据
	 * 
	 * @param record
	 * @return
	 */
	public int updateByPrimaryKey(QuoteInfo record);
	/**
	 * 更新数据(发送提示给采购商)
	 * 
	 * @param record
	 * @return
	 */
	public int updateByPrimaryKey(QuoteInfo record,NoteMessage note);
	
	
	/**
	 * 更新数据(更新询盘和零件信息  2017/11/24)
	 * 
	 * @param record
	 * @return
	 */
	public void updateByPrimaryKey(QuoteInfo record,String productList,String drawingName)throws ParseException;
	
	/**
	 * 更新数据(更新询盘和供应商报价信息  2017/12/26)
	 * 
	 * @param record
	 * @return
	 */
	public void updateByPrimaryKey(QuoteInfo record,List<SupplierQuoteInfo> list);

	/**
	 * 根据询盘状态查询
	 * 
	 * @return
	 */
	public List<QuoteInfo> queryAllInquiryOrder(Integer start,
			 Integer pageSize,
			Integer orderStatus);

	/**
	 * 根据询盘状态查询询盘数
	 * 
	 * @return
	 */
	public int totalNormalOrder(@Param("orderStatus") Integer orderStatus);

	/**
	 * 根据询盘号查询
	 * 
	 * @param orderId
	 * @return
	 */
	public QuoteInfo queryByOrderId(Integer orderId);
	
	
    
    /**
     * 根据询盘号和客户ID查询
     * @param orderId
     * @return
     */
    public QuoteInfo queryByOrderIdAndFactoryId(Integer orderId,String factoryId);
    
     /*
     * 查出询盘和订单详情
     */
    public QuoteInfo selectDetailByOrderId(Integer orderId,String buyerId);

    
    /**
     * 根据询盘状态查询
     * @return
     */
    public List<QuoteInfo> queryAll(Integer orderStatus);
    
    
    /**
     * 根据询盘状态查询
     * @return
     */
    public List<QuoteInfo> selectDetailListByFactoryId(Integer orderStatus,String customerId);
    
    
    
    /**
     * 计算一周报价价格
     * @Title calEstimatedPrice 
     * @Description 
     * @param orderId
     * @return
     * @return double
     */
    public double calEstimatedPrice();
    

    /**
     * 更新报价（当选择下单工厂后处理）
     * @Title updateQuote 
     * @Description 
     * @param projectId
     * @param factoryId
     * @param factoryName
     * @param totalAmount
     * @return
     * @return int
     */
    int updateQuote(String projectId,String factoryId,String factoryName,String totalAmount)throws ParseException;
    
    
    /**
     * 更新询盘跟进状态（从内部报价或者NBMail进行同步）
     * @Title updateQuote 
     * @Description
     * @param projectId
     * @param followStatus
     * @return
     * @return int
     */
    int updateQuote(String projectId,String followStatus);
    
    
    /**
     * 多种状态查询
     * @Title queryOrderFactory 
     * @Description
     * @return
     * @return List<?>
     */
    <T>List<?> queryOrderFactoryList(Integer...item);
    
    
    
    
    /**
     * 内部报价修改报价助理同步数据
     * @Title updateQuoteAssistant 
     * @Description 
     * @param projectId
     * @param assistant
     * @return
     * @return int
     */
    int updateQuoteAssistant(String projectId,String assistant);
    
    
}