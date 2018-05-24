package com.cbt.service;

import java.util.List;

import com.cbt.entity.QuoteInfo;
import com.cbt.entity.QuoteProduct;

public interface QuoteProductService {
	
	
	public int deleteByPrimaryKey(Integer id);

	public int insert(QuoteProduct record);


	public QuoteProduct selectByPrimaryKey(Integer id);


	public int updateByPrimaryKeySelective(QuoteProduct record);
	
	
	public int deleteByOrderId(Integer orderId);
	
	
    /**
     * 批量询盘产品
     * @param list
     * @return
     */
    public void insert(QuoteInfo record,List<QuoteProduct> list);
	
	
    /**
     * 根据询盘号查询
     * @return
     */
   public List<QuoteProduct> queryByOrderId(Integer orderId);
   
   
   /**
    * 根据询盘状态查询
    * @return
    */
   public List<QuoteProduct> queryAllProductGroupByOrderId(Integer orderStatus,String factoryId);
   
   
   
   /**
    * 查询邀请报价的询盘（非已报价）
    * @return
    */
   public List<QuoteProduct> queryInvitationOrder(Integer orderStatus,String process,String product,String factoryId);
   
   
   /**
    * 根据询盘状态查询(根据主工艺筛选，主要用于首页更多询盘显示)
    * 2018/1/9  
    * @return
    */
   public List<QuoteProduct> queryProductByMainProcess(Integer start,Integer pageSize,Integer orderStatus,List<String> processes,String factoryId);
   
   
   /**
    * 根据询盘状态查询
    * @return
    */
   public List<QuoteProduct> queryProductGroupByOrderId(Integer start,Integer pageSize,
	   		Integer orderStatus,String process,String product,Integer customerType,String factoryId,String bigBuyerId);
	   
   
   
   /**
    * 查询询盘数
    * @return
    */
   public int totalOrder(Integer orderStatus,String process,String product,Integer customerType,String factoryId,String bigBuyerId);
   
   
   
   
   
   /**
    * 查询工厂报价中的产品 
    * @return
    */
   public List<QuoteProduct> queryByFactoryGroupByOrderId(Integer start,Integer pageSize,String process,String product,
   		String factoryId,Integer quoteStatus);
   
   
   /**
    * 查询工厂报价询盘数
    * @return
    */
   public int totalQuoteOrder(String process,String product,String factoryId,Integer quoteStatus);
   
   
   
   /**
    * 查询工厂已授盘（包含已授盘、已完成、生产中）
    * @return
    */
   public List<QuoteProduct> queryFinishByFactoryGroupByOrderId(Integer start,Integer pageSize,
   		String process,String product,String factoryId);
   
   
   /**
    * 查询工厂已授盘（包含已授盘、已完成、生产中） 数量
    * @return
    */
   public int totalFinishQuoteOrder(String process,String product,String factoryId);
   
   
   
   /**
    * 查询收藏的询盘
    * @return
    */
   public List<QuoteProduct> queryByCollectOrderId(Integer start,Integer pageSize,
   		String process,String product,
   		String factoryId);
   
   
   /**
    * 查询收藏询盘数
    * @return
    */
   public int totalCollectCount(String process,String product,String factoryId);
   
   
   
   
   /**
    * 
    * @return
    */
   public QuoteProduct queryByCgsQuotationId(Integer cgsQuotationId);
   
   
   
   /**
    * 查询采购商询盘(管理员)
    * @param start
    * @param pageSize
    * @param process
    * @param product
    * @param factoryId
    * @return
    */
   List<QuoteProduct> queryByFactoryIdGroupByOrderIdAdmin(String process,String product,String factoryId,Integer orderStatus);
   
   
   
   
   /**
    * 客户询盘数(管理员)
    * @param process
    * @param product
    * @param factoryId
    * @return
    */
   int totalByFactoryIdAdmin(String process,String product,
   		String factoryId,Integer orderStatus);
   
   public int updateQuoteProductGroupbyOrderId(List<QuoteProduct> list);
   
   
   
   
   
   
   /**
    * 查询采购商询盘
    * @param start
    * @param pageSize
    * @param process
    * @param product
    * @param factoryId
    * @return
    */
   public List<QuoteProduct> queryByFactoryIdGroupByOrderId(String process,String product,String factoryId,
		   Integer orderStatus,Integer factoryUserId);
   
   
   
   
   /**
    * 客户询盘数
    * @param process
    * @param product
    * @param factoryId
    * @return
    */
   int totalByFactoryId(String process,String product,
   		String factoryId,Integer orderStatus,Integer factoryUserId);
   
   
   
}