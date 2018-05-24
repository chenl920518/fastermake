package com.cbt.service.impl;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cbt.dao.FactoryInfoMapper;
import com.cbt.dao.FactoryUserMapper;
import com.cbt.dao.NoteMessageMapper;
import com.cbt.dao.QuoteInfoMapper;
import com.cbt.dao.QuoteProductMapper;
import com.cbt.dao.SupplierQuoteInfoMapper;
import com.cbt.dao.UserOrderMapper;
import com.cbt.entity.DialogueIds;
import com.cbt.entity.FactoryInfo;
import com.cbt.entity.FactoryUser;
import com.cbt.entity.NoteMessage;
import com.cbt.entity.QuoteInfo;
import com.cbt.entity.QuoteProduct;
import com.cbt.entity.SupplierQuoteInfo;
import com.cbt.entity.UserOrder;
import com.cbt.enums.FollowStatusQuotationEnum;
import com.cbt.enums.MessageTypeEnum;
import com.cbt.enums.OrderStatusEnum;
import com.cbt.enums.QuoteOrderStatusEnum;
import com.cbt.service.QuoteInfoService;
import com.cbt.util.DateFormat;
import com.cbt.util.DateUtil;
import com.cbt.util.GetServerPathUtil;

/**
 * Edit  增加修改图纸时，提醒已报价工厂图纸已更新
 * @ClassName QuoteInfoServiceImpl 
 * @Method updateByPrimaryKey
 * @Description TODO
 * @author zj
 * @date 2018年5月8日 
 */
/**
 * Edit  同步询盘状态，首页根据状态获取完成询盘下单情况
 * @ClassName QuoteInfoServiceImpl 
 * @Method updateQuote,queryOrderFactoryList
 * @Description TODO
 * @author zj
 * @date 2018年4月27日 
 */
/**
 * Edit  判断报价截止时间是否大于当前时间，如果项目状态为已过期，则更新为正常询盘
 * @ClassName QuoteInfoServiceImpl 
 * @Method updateByPrimaryKey
 * @Description TODO
 * @author zj
 * @date 2018年4月4日 下午5:12:52
 */

@Service
public class QuoteInfoServiceImpl implements QuoteInfoService {

	@Autowired
	private QuoteInfoMapper quoteInfoMapper;
	
	@Autowired
	private FactoryInfoMapper factoryInfoMapper;

	@Autowired
	private QuoteProductMapper quoteProductMapper;
	
	@Autowired
	private SupplierQuoteInfoMapper supplierQuoteInfoMapper;
	@Autowired
	private NoteMessageMapper noteMessageMapper;
	@Autowired
	private UserOrderMapper userOrderMapper;
	@Autowired
	private FactoryUserMapper factoryUserMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return quoteInfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(QuoteInfo record) {
		return quoteInfoMapper.insert(record);
	}
    
	@Transactional
	@Override
	public int insertAll(QuoteInfo quoteInfo) {
		
		quoteInfo.setPublishDate(DateFormat.format());
		quoteInfo.setCurrentNumber(0);

		quoteInfoMapper.insertBackOrderId(quoteInfo);

		if (quoteInfo.getOrderId() != null) {
			Integer orderId = quoteInfo.getOrderId();
			List<QuoteProduct> list = quoteInfo.getProducts();

			if (list != null && list.size() > 0) {
				for (QuoteProduct quoteProduct : list) {	
					quoteProduct.setCustomerId(quoteInfo.getCustomerId());
					quoteProduct.setOrderId(orderId);
					if(quoteProduct.getTargetMoldPrice() == null){
						quoteProduct.setTargetMoldPrice(0.0);
					}
					if(quoteProduct.getTargetPrice() == null){
						quoteProduct.setTargetPrice(0.0);;
					}
				}
				quoteProductMapper.insertList(list);
			}
		}
		return quoteInfo.getOrderId();
	}

	
	@Override
	public int updateAll(QuoteInfo quoteInfo) {
		
		Integer orderId = quoteInfo.getOrderId();
		if (orderId == null) {
			throw new  RuntimeException("更新数据，orderId不能为空");
		}
		List<QuoteProduct> list = quoteInfo.getProducts();
	
		quoteProductMapper.deleteByOrderId(orderId);
		
		quoteInfoMapper.updateByPrimaryKeySelective(quoteInfo);
		
		if (list != null && list.size() > 0) {
			for (QuoteProduct quoteProduct : list) {					
				quoteProduct.setOrderId(orderId);
			}
			quoteProductMapper.insertList(list);
		}
		
		return orderId;
		
		
	}
    @Override
	public QuoteInfo selectByPrimaryKey(Integer id) {
		return quoteInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(QuoteInfo record) {
		return quoteInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<QuoteInfo> queryAllInquiryOrder(Integer start,
			Integer pageSize, Integer orderStatus) {
		return quoteInfoMapper.queryAllInquiryOrder(start, pageSize,
				orderStatus);
	}

	@Override
	public int totalNormalOrder(Integer orderStatus) {
		return quoteInfoMapper.totalNormalOrder(orderStatus);
	}

	@Override
	public QuoteInfo queryByOrderId(Integer orderId) {
		return quoteInfoMapper.queryByOrderId(orderId);
	}

	@Override
	public QuoteInfo queryByOrderIdAndFactoryId(Integer orderId,
			String factoryId) {
		return quoteInfoMapper.queryByOrderIdAndFactoryId(orderId, factoryId);
	}

	
	@Transactional
	@Override
	public void updateByPrimaryKey(QuoteInfo record, String productList,String drawingName) throws ParseException {
	 if(StringUtils.isNotBlank(productList)){
		 if (productList.endsWith("~")) {
				if(productList.length()>1){
					productList = productList.substring(0, productList.length() - 1);
					String[] split = productList.split("~");
					for(int i = 0;i<split.length;i++){
				        String[] split1 = split[i].split("&", -1);
				        Integer productId = Integer.parseInt(split1[0]);
				        QuoteProduct quoteProduct = quoteProductMapper.selectByPrimaryKey(productId);
				        if(StringUtils.isNotBlank(split1[1])){
				        	quoteProduct.setProductName(split1[1]);
				        }
				        if(StringUtils.isNotBlank(split1[2])){
				        	quoteProduct.setProcess(split1[2]);
				        }
				        if(StringUtils.isNotBlank(split1[3])){
				        	quoteProduct.setMaterials(split1[3]);
				        }
				        if(StringUtils.isNotBlank(split1[4])){
				        	quoteProduct.setWeight(Double.parseDouble(split1[4]));
				        }
				        if(StringUtils.isNotBlank(split1[5])){
				        	quoteProduct.setQuantityList(split1[5]);
				        }
				        if(StringUtils.isNotBlank(split1[6])){
				        	quoteProduct.setAnnualQuantity(split1[6]);
				        }
				        if(StringUtils.isNotBlank(split1[7])){
				        	quoteProduct.setProductRemark(split1[7]);
				        }		
				        quoteProductMapper.updateByPrimaryKeySelective(quoteProduct);
					}  
				}
		    }
	 }	
		
	    //判断报价截止时间是否大于当前时间，如果项目状态为已过期，则更新为正常询盘
	    String quoteEndDate = record.getQuoteEndDate();
	    if(StringUtils.isNotBlank(quoteEndDate)){
		    Boolean flag = DateUtil.calExpires(quoteEndDate);
		    if(flag && record.getOrderStatus() == OrderStatusEnum.EXPIRE.getCode()){
		    	record.setOrderStatus(OrderStatusEnum.NORMAL_ORDER.getCode());
		    }
	    }
	    quoteInfoMapper.updateByPrimaryKeySelective(record);
	 
	    
	    
		  //更新图纸时，提醒已报价的工厂图纸已更新
	    if(StringUtils.isNotBlank(drawingName)){
	    	
	    	  Integer orderId = record.getOrderId();
	    	  List<SupplierQuoteInfo> supplierQuoteInfo = supplierQuoteInfoMapper.queryByOrderId(orderId);
	    	  NoteMessage note = new NoteMessage();   
	    	  note.setSendId(record.getCustomerId());
			  note.setMessageTitle(orderId+"询盘图纸已更新，请下载查看");
		 	  note.setMessageDetails(orderId+"询盘图纸已更新，请下载查看。");  
			  note.setMessageType(MessageTypeEnum.OTHER_MESSAGE.getCode());
			  note.setOrderId(orderId);
			  note.setUrl(GetServerPathUtil.getServerPath()+"/zh/offer_detail.html?orderId="+orderId);   			  
	    	  for (SupplierQuoteInfo supplierQuoteInfo2 : supplierQuoteInfo) {	
	    		  
		  		  DialogueIds did = new DialogueIds();
				  noteMessageMapper.returnDialogueId(did);
				  int dialogueId = did.getId();
				  note.setDialogueId(dialogueId);
				  note.setReceiverId(supplierQuoteInfo2.getFactoryId());
				  note.setCreateTime(DateFormat.format());
				  noteMessageMapper.insertSelective(note);
			  }	    	
	  	      
	    }
	    
	    
	    
	}

	@Override
	public List<QuoteInfo> queryAll(Integer orderStatus) {
		return quoteInfoMapper.queryAll(orderStatus);
	}
         
	 @Override
	public QuoteInfo selectDetailByOrderId(Integer orderId,String buyerId) {
		return quoteInfoMapper.selectDetailByPrimaryKey(orderId,buyerId);
	}
	
	
		@Override
		public List<QuoteInfo> selectDetailListByFactoryId(Integer orderStatus,
				String customerId) {
			return quoteInfoMapper.selectDetailListByFactoryId(orderStatus, customerId);
		}

		
		@Transactional
		@Override
		public void updateByPrimaryKey(QuoteInfo record,
				List<SupplierQuoteInfo> list) {
			quoteInfoMapper.updateByPrimaryKeySelective(record);
			supplierQuoteInfoMapper.updateOrderStatus(list);
		}

		@Transactional
		@Override
		public int updateByPrimaryKey(QuoteInfo record, NoteMessage note) {			
			DialogueIds did = new DialogueIds();
			noteMessageMapper.returnDialogueId(did);
			int dialogueId = did.getId();
			note.setDialogueId(dialogueId);
			noteMessageMapper.insertSelective(note);		
			return quoteInfoMapper.updateByPrimaryKeySelective(record);
		}

		@Override
		public double calEstimatedPrice() {			
			List<QuoteProduct> quoteProducts = quoteProductMapper.queryProductByWeek();
			Double totalEstimatedPrice = 0.0;
			String quantityList;
			Double targetPrice = 0.0;
			Double targetMoldPrice = 0.0;
			Integer quantity = 0;
			String[] split;
			for (QuoteProduct quoteProduct : quoteProducts) {
				
				if(quoteProduct != null){
					quantityList = quoteProduct.getQuantityList();
					if(StringUtils.isNotBlank(quantityList)){
						split = quantityList.split(",");
						quantity = Integer.parseInt(split[split.length-1]);
					}
					targetPrice = quoteProduct.getTargetPrice();	
					targetMoldPrice = quoteProduct.getTargetMoldPrice();
					if(targetMoldPrice != null){
						totalEstimatedPrice += quantity*targetPrice+targetMoldPrice;
					}else{
						totalEstimatedPrice += quantity*targetPrice;
					}					
				}				
				//System.out.println(totalEstimatedPrice);
			}	
		
			return totalEstimatedPrice;
		}

		
		
		//更新工厂下单金额（内部报价同步）
		@Transactional
		@Override
		public int updateQuote(String projectId, String factoryId,String factoryName, String totalAmount) throws ParseException {
			QuoteInfo quoteInfo = quoteInfoMapper.queryByCgsOrderId(projectId);
			quoteInfo.setConfirmFactoryId(factoryId);
			quoteInfo.setOrderFactoryName(factoryName);
			if(StringUtils.isNotBlank(factoryId)){
				FactoryInfo factoryInfo = factoryInfoMapper.selectFactoryInfo(factoryId);
				if(factoryInfo != null){
					quoteInfo.setOrderFactoryName(factoryInfo.getFactoryName());
				}
				
				//修改报价状态为生产中
				SupplierQuoteInfo supplierQuoteInfo = supplierQuoteInfoMapper.queryByOrderIdAndFactoryId(quoteInfo.getOrderId(), factoryId);
				supplierQuoteInfo.setQuoteStatus(QuoteOrderStatusEnum.PROCESS_ORDER.getCode());
				supplierQuoteInfoMapper.updateByPrimaryKey(supplierQuoteInfo);
			}
			quoteInfo.setTotalAmount(Double.parseDouble(totalAmount));  
			quoteInfo.setOrderEndDate(DateFormat.currentDate());	
			quoteInfo.setOrderStatus(OrderStatusEnum.CONFIRM.getCode());  //更新项目状态为已完成
			int state = quoteInfoMapper.updateByPrimaryKeySelective(quoteInfo);
			return state;
		}

		@Override
		public int updateQuote(String projectId, String followStatus) {

		   int state = 0;
           QuoteInfo quoteInfo = quoteInfoMapper.queryByCgsOrderId(projectId);
           if(StringUtils.isNotBlank(followStatus)){
        	   Integer status = Integer.parseInt(followStatus);
        	   quoteInfo.setFollowStatusQuotation(status);
        	   quoteInfo.setFollowDetail(FollowStatusQuotationEnum.getEnum(status).getValue());
        	   quoteInfo.setFollowTime(DateFormat.format());
        	   
        	   //如果询盘状态为死亡，更新快制造询盘状态
        	   if(status == FollowStatusQuotationEnum.OTHER_DEATH.getCode() || status == FollowStatusQuotationEnum.HIGH_PRICE_DEATH.getCode()){
        		   quoteInfo.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        	   }
        	  //如果询盘状态为生产，更新快制造询盘状态
        	   if(status == FollowStatusQuotationEnum.PRODUCTION.getCode()){
        		   quoteInfo.setOrderStatus(OrderStatusEnum.PROCESS.getCode());
        	   }
        	  	  //如果询盘状态为生产，更新快制造询盘状态
        	   if(status == FollowStatusQuotationEnum.QUOTE_COMPLETE.getCode()){
        		   quoteInfo.setOrderStatus(OrderStatusEnum.CONFIRM.getCode());
        	   }
        	   
        	   state = quoteInfoMapper.updateByPrimaryKeySelective(quoteInfo);
           }			
			return state;
		}

		@Override
		public <T> List<?> queryOrderFactoryList(Integer... item) {
			return quoteInfoMapper.queryOrderFactoryList(item);
		}

		
		@Transactional
		@Override
		public int updateQuoteAssistant(String projectId,String assistant) {
			
			int count = 0;
			//更新报价单中报价助理
			QuoteInfo quoteInfo = quoteInfoMapper.queryByCgsOrderId(projectId);
			if(StringUtils.isNotBlank(assistant)){
				quoteInfo.setPriceAssistant(assistant);
				quoteInfoMapper.updateByPrimaryKeySelective(quoteInfo);
			}
			
			//查看订单、内部人员关联表
			//更新关联关系
			UserOrder userOrder = userOrderMapper.selectAssistantByCgsOrderId(projectId);
			FactoryUser factoryUser = factoryUserMapper.selectByRealName(assistant);
			if(factoryUser != null && userOrder != null){
				userOrder.setFactoryUserId(factoryUser.getId());
				count = userOrderMapper.updateByPrimaryKey(userOrder);
			}else if(factoryUser != null && userOrder == null){
				UserOrder order = new UserOrder(); 
				order.setCgsOrderId(projectId);
				order.setFactoryUserId(factoryUser.getId());
				order.setOrderId(quoteInfo.getOrderId());
				count = userOrderMapper.insert(order);
			}
			
			return count;
		}
	

}
