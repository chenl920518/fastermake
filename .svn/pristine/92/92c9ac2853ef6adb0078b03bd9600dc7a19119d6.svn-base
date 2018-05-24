package com.cbt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cbt.dao.FactoryUserMapper;
import com.cbt.dao.QuoteInfoMapper;
import com.cbt.dao.QuoteProductMapper;
import com.cbt.dao.UserOrderMapper;
import com.cbt.entity.FactoryUser;
import com.cbt.entity.QuoteInfo;
import com.cbt.entity.QuoteProduct;
import com.cbt.entity.UserOrder;
import com.cbt.enums.OrderStatusEnum;
import com.cbt.service.QuoteProductService;


@Service
public class QuoteProductServiceImpl implements QuoteProductService {

	@Autowired
	private QuoteProductMapper quoteProductMapper;
	@Autowired
	private QuoteInfoMapper quoteInfoMapper;
	@Autowired
	private FactoryUserMapper factoryUserMapper;
	@Autowired
	private UserOrderMapper userOrderMapper;
	
	private static final Integer NORMAL = 10;
	private static final Integer PRICE_ASSISTANT = 20;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return quoteProductMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(QuoteProduct record) {
		return quoteProductMapper.insert(record);
	}

	@Override
	public QuoteProduct selectByPrimaryKey(Integer id) {
		return quoteProductMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(QuoteProduct record) {
		return quoteProductMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<QuoteProduct> queryByOrderId(Integer orderId) {
		return quoteProductMapper.queryByOrderId(orderId);
	}

	@Override
	public List<QuoteProduct> queryProductGroupByOrderId(Integer start,
			Integer pageSize, Integer orderStatus, String process,
			String product,Integer customerType,String factoryId,String bigBuyerId) {
		return quoteProductMapper.queryProductGroupByOrderId(start, pageSize, orderStatus, process, product,customerType,factoryId,bigBuyerId);
	}

	@Override
	public int totalOrder(Integer orderStatus, String process, String product,Integer customerType,String factoryId,String buyerId) {
		return quoteProductMapper.totalOrder(orderStatus, process, product,customerType,factoryId,buyerId);
	}

	@Override
	public List<QuoteProduct> queryByFactoryGroupByOrderId(Integer start,Integer pageSize, String process, 
			String product, String factoryId,Integer quoteStatus) {
		return quoteProductMapper.queryByFactoryGroupByOrderId(start, pageSize, process, product, factoryId,quoteStatus);
	}

	@Override
	public int totalQuoteOrder(String process, String product, String factoryId,Integer quoteStatus) {		
		return quoteProductMapper.totalQuoteOrder(process, product, factoryId,quoteStatus);
	}

	@Override
	public List<QuoteProduct> queryByCollectOrderId(Integer start,
			Integer pageSize, String process, String product, String factoryId) {
		return quoteProductMapper.queryByCollectOrderId(start, pageSize, process, product, factoryId);
	}

	@Override
	public int totalCollectCount(String process, String product,
			String factoryId) {		
		return quoteProductMapper.totalCollectCount(process, product, factoryId);
	}

	
	@Transactional
	@Override
	public void insert(QuoteInfo record, List<QuoteProduct> list) {
		
		//过滤重复的询盘	
		//当已经存在则不执行保存
		int orderCount = quoteInfoMapper.queryCountByCgsOrderId(record.getCsgOrderId(), OrderStatusEnum.NORMAL_ORDER.getCode());
		if(orderCount == 0){
			quoteInfoMapper.insert(record);
			if(list != null && list.size() != 0){
				for (QuoteProduct quoteProduct : list) {
					quoteProduct.setOrderId(record.getOrderId());			
				}
				quoteProductMapper.insertList(list);
			}
			
			//保存报价员、询盘关系
			if(StringUtils.isNotBlank(record.getQuoter())){
				FactoryUser factoryUser = factoryUserMapper.selectByRealName(record.getQuoter());
				if(factoryUser != null){
					UserOrder userOrder = new UserOrder();
					userOrder.setCgsOrderId(record.getCsgOrderId());
					userOrder.setOrderId(record.getOrderId());
					userOrder.setFactoryUserId(factoryUser.getId());
					int count = userOrderMapper.selectByFactoryUserIdAndOrderId(factoryUser.getId(), record.getOrderId());
					if(count == 0){
						userOrderMapper.insert(userOrder);
					}
				}
			}
			//保存销售、询盘关系
			if(StringUtils.isNotBlank(record.getInitialSales())){
				FactoryUser factoryUser = factoryUserMapper.selectByRealName(record.getInitialSales());
				if(factoryUser != null){
					UserOrder userOrder = new UserOrder();
					userOrder.setCgsOrderId(record.getCsgOrderId());
					userOrder.setOrderId(record.getOrderId());
					userOrder.setFactoryUserId(factoryUser.getId());
					int count  = userOrderMapper.selectByFactoryUserIdAndOrderId(factoryUser.getId(), record.getOrderId());
					if(count == 0){
						userOrderMapper.insert(userOrder);
					}
				}
			}
			//保存报价助理
			if(StringUtils.isNotBlank(record.getPriceAssistant())){
				FactoryUser factoryUser = factoryUserMapper.selectByRealName(record.getPriceAssistant());
				if(factoryUser != null){
					UserOrder userOrder = new UserOrder();
					userOrder.setCgsOrderId(record.getCsgOrderId());
					userOrder.setOrderId(record.getOrderId());
					userOrder.setFactoryUserId(factoryUser.getId());						
					int count = userOrderMapper.selectByFactoryUserIdAndOrderId(factoryUser.getId(), record.getOrderId());
					if(count == 0){
						userOrderMapper.insert(userOrder);
					}
				}
			}
		}
		
		

		
	}

	@Override
	public QuoteProduct queryByCgsQuotationId(Integer cgsQuotationId) {
		return quoteProductMapper.queryByCgsQuotationId(cgsQuotationId);
	}

	@Override
	public List<QuoteProduct> queryAllProductGroupByOrderId(
			Integer orderStatus, String factoryId) {
		return quoteProductMapper.queryAllProductGroupByOrderId(orderStatus, factoryId);
	}

	@Override
	public List<QuoteProduct> queryByFactoryIdGroupByOrderIdAdmin(String process,
			String product, String factoryId,Integer orderStatus) {
		return quoteProductMapper.queryByFactoryIdGroupByOrderIdAdmin(process, product, factoryId,orderStatus);
	}

	@Override
	public int totalByFactoryIdAdmin(String process, String product,
			String factoryId, Integer orderStatus) {
		return quoteProductMapper.totalByFactoryIdAdmin(process, product, factoryId, orderStatus);
	}

	@Override
	public int deleteByOrderId(Integer orderId) {
		
		return quoteProductMapper.deleteByOrderId(orderId);
	}

	@Override
	public int updateQuoteProductGroupbyOrderId(List<QuoteProduct> list) {
		
		try{
			if(list!=null&&list.size()>0){
				for(QuoteProduct qp :list){
					if(qp.getTargetPrice()==null){
						qp.setTargetPrice(0.0);
					}
					quoteProductMapper.updateByPrimaryKeySelective(qp);
					
				}
				
			}
			return 1;
		}catch(Exception e){
			throw new RuntimeException("更新失败");
		}
		
	}

	@Override
	public List<QuoteProduct> queryFinishByFactoryGroupByOrderId(Integer start,
			Integer pageSize, String process, String product, String factoryId) {
		return quoteProductMapper.queryFinishByFactoryGroupByOrderId(start, pageSize, process, product, factoryId);
	}

	@Override
	public int totalFinishQuoteOrder(String process, String product,
			String factoryId) {
		return quoteProductMapper.totalFinishQuoteOrder(process, product, factoryId);
	}

	@Override
	public List<QuoteProduct> queryInvitationOrder(Integer orderStatus, String process,
			String product, String factoryId) {
		return quoteProductMapper.queryInvitationOrder(orderStatus, process, product, factoryId);
	}

	@Override
	public List<QuoteProduct> queryProductByMainProcess(Integer start,
			Integer pageSize, Integer orderStatus, List<String> processes,
			String factoryId) {
		return quoteProductMapper.queryProductByMainProcess(start, pageSize, orderStatus, processes, factoryId);
	}

	@Override
	public List<QuoteProduct> queryByFactoryIdGroupByOrderId(String process,
			String product, String factoryId, Integer orderStatus,
			Integer factoryUserId) {
		return quoteProductMapper.queryByFactoryIdGroupByOrderId(process, product, factoryId, orderStatus, factoryUserId);
	}

	@Override
	public int totalByFactoryId(String process, String product,
			String factoryId, Integer orderStatus, Integer factoryUserId) {
		return quoteProductMapper.totalByFactoryId(process, product, factoryId, orderStatus, factoryUserId);
	}

}
