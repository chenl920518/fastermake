package com.cbt.service.impl;

import java.math.BigDecimal;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cbt.dao.NoteMessageMapper;
import com.cbt.dao.QuoteBigProductsMapper;
import com.cbt.dao.QuoteBigProductsTabMapper;
import com.cbt.dao.QuoteInfoMapper;
import com.cbt.dao.SupplierQuoteInfoMapper;
import com.cbt.entity.DialogueIds;
import com.cbt.entity.NoteMessage;
import com.cbt.entity.QuoteBigProducts;
import com.cbt.entity.QuoteBigProductsTab;
import com.cbt.entity.QuoteInfo;
import com.cbt.entity.SupplierQuoteInfo;
import com.cbt.enums.BigProductStatusEnum;
import com.cbt.enums.MessageTypeEnum;
import com.cbt.enums.OrderStatusEnum;
import com.cbt.enums.QuoteOrderStatusEnum;
import com.cbt.service.QuoteBigProductsService;
import com.cbt.util.DateFormat;
import com.cbt.util.GetServerPathUtil;
import com.cbt.util.WebCookie;

@Service
public class QuoteBigProductsServiceImpl implements QuoteBigProductsService {
     
	@Autowired
	private QuoteBigProductsMapper quoteBigProductsMapper;
	@Autowired
	private QuoteBigProductsTabMapper quoteBigProductsTabMapper;
	@Autowired
	private QuoteInfoMapper quoteInfoMapper;
	@Autowired
	private SupplierQuoteInfoMapper supplierQuoteInfoMapper;
	@Autowired
	private NoteMessageMapper noteMessageMapper;
	
	@Override
	public int insertSelective(QuoteBigProducts record) {
		return quoteBigProductsMapper.insertSelective(record);
	}

	@Override
	public QuoteBigProducts selectByPrimaryKey(Integer id) {
		return quoteBigProductsMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(HttpServletRequest request,QuoteBigProducts record) {
		//保存到提醒消息表
		String factoryId = WebCookie.getFactoryId(request);
	    NoteMessage note = new NoteMessage(); 
	    
		QuoteInfo quoteInfo = quoteInfoMapper.queryByOrderIdAndFactoryId(record.getOrderId(), record.getCustomerId());
		if(record.getIsSupplierAccept() == BigProductStatusEnum.CONFIRM.getCode()){
			SupplierQuoteInfo supplierQuoteInfo = supplierQuoteInfoMapper.queryByOrderIdAndFactoryId(record.getOrderId(), record.getFactoryId());
			supplierQuoteInfo.setQuoteStatus(QuoteOrderStatusEnum.PROCESS_ORDER.getCode());
			supplierQuoteInfoMapper.updateByPrimaryKeySelective(supplierQuoteInfo);
			
			//当付款状态时，显示付款时间
			record.setPaymentConfirmTime(DateFormat.format());
			

			quoteInfo.setOrderStatus(OrderStatusEnum.PROCESS.getCode());
			quoteInfoMapper.updateByPrimaryKeySelective(quoteInfo);
			
		    note.setReceiverId(record.getFactoryId());
		    note.setSendId("");
		    note.setOrderId(record.getOrderId());
		    note.setMessageTitle(record.getOrderId()+"询盘有新的大货消息");
		    note.setMessageDetails(record.getOrderId()+"询盘大货付款快制造平台已确认到账，稍后会与工厂联系，开启大货生产。");
		    note.setMessageType(MessageTypeEnum.BIG_PRODUCT_MESSAGE.getCode());
		    note.setUrl(GetServerPathUtil.getServerPath()+"/zh/supplier_big_goods.html?orderId="+record.getOrderId());   
		    note.setCreateTime(DateFormat.format());		 	
		}else if(record.getIsSupplierAccept() == BigProductStatusEnum.REFUSE.getCode()){
			record.setSupplierReplyTime(DateFormat.format());
		}else if(record.getIsSupplierAccept() == BigProductStatusEnum.ACCEPT.getCode()){
			record.setSupplierReplyTime(DateFormat.format());
		}else if(record.getIsSupplierAccept() == BigProductStatusEnum.NEED_CONFIRM.getCode()){
			record.setPaymentNotConfirmTime(DateFormat.format());
		}
	       
	       //当是采购商修改大货的时候，通知供应商
		   if(factoryId.equals(record.getCustomerId())){
			   note.setReceiverId(record.getFactoryId());
			   note.setSendId(factoryId);  
			   note.setOrderId(record.getOrderId());
			   note.setMessageTitle(record.getOrderId()+"询盘有新的大货消息");
			   note.setMessageDetails(record.getOrderId()+"询盘大货有更新消息");
			   note.setMessageType(MessageTypeEnum.BIG_PRODUCT_MESSAGE.getCode());
			   note.setUrl(GetServerPathUtil.getServerPath()+"/zh/supplier_big_goods.html?orderId="+record.getOrderId());   
			   note.setCreateTime(DateFormat.format());
		   }else if(factoryId.equals(record.getFactoryId())){
			   note.setReceiverId(record.getCustomerId());
			   note.setSendId(factoryId);  
			   note.setOrderId(record.getOrderId());
			   note.setMessageTitle(record.getOrderId()+(quoteInfo.getCsgOrderId() == null ? "" : "("+quoteInfo.getCsgOrderId()+")")+"询盘有新的大货消息");
			   note.setMessageDetails(record.getOrderId()+"询盘大货有更新消息");
			   note.setMessageType(MessageTypeEnum.BIG_PRODUCT_MESSAGE.getCode());
			   note.setUrl(GetServerPathUtil.getServerPath()+"/zh/purchase_big_goods_generation.html?factoryId="+record.getFactoryId()+"&orderId="+record.getOrderId());   
			   note.setCreateTime(DateFormat.format());
		   }
		   
		   
	     
		   if(note != null){
			   DialogueIds did = new DialogueIds();
			   noteMessageMapper.returnDialogueId(did);
			   int dialogueId = did.getId();
			   note.setDialogueId(dialogueId);
			   noteMessageMapper.insertSelective(note);  
		   }

		
		return quoteBigProductsMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public QuoteBigProducts selectByOrderId(String factoryId, Integer orderId) {
		return quoteBigProductsMapper.selectByOrderId(factoryId, orderId);
	}

	
	@Transactional
	@Override
	public void insertSelective(QuoteBigProducts record, String priceList) {
		 quoteBigProductsMapper.updateIsActive(record.getFactoryId(), record.getOrderId());
		 quoteBigProductsMapper.insertSelective(record);
		 
		 
		  //保存到提醒消息表
   	      NoteMessage note = new NoteMessage();    
		  note.setReceiverId(record.getFactoryId());
		  note.setSendId(record.getCustomerId());
		  note.setOrderId(record.getOrderId());
		  note.setMessageTitle(record.getOrderId()+"询盘有新的大货消息");
		  note.setMessageDetails(record.getOrderId()+"询盘有新的大货消息");
		  note.setMessageType(MessageTypeEnum.BIG_PRODUCT_MESSAGE.getCode());
		  note.setUrl(GetServerPathUtil.getServerPath()+"/zh/supplier_big_goods.html?orderId="+record.getOrderId());   
		  note.setCreateTime(DateFormat.format());
		  DialogueIds did = new DialogueIds();
		  noteMessageMapper.returnDialogueId(did);
		  int dialogueId = did.getId();
		  note.setDialogueId(dialogueId);
		  noteMessageMapper.insertSelective(note);
		  
		  
		 if (priceList.endsWith("~")) {
				if(priceList.length()>1){
					priceList = priceList.substring(0, priceList.length() - 1);
					String[] split = priceList.split("~");
					Double totalAmount = 0.0;
					for(int i = 0;i<split.length;i++){
						        Double amount = 0.0;
						        String[] split1 = split[i].split("&",-1);
						        QuoteBigProductsTab  quoteBigProductsTab = new QuoteBigProductsTab();
						        quoteBigProductsTab.setBigProductId(record.getId());
						        quoteBigProductsTab.setOrderId(record.getOrderId());
								if(StringUtils.isNotBlank(split1[0])){
									quoteBigProductsTab.setProductId(Integer.parseInt(split1[0].trim()));
								}	
								Integer qty = 0;
								if(StringUtils.isNotBlank(split1[1])){
									qty = Integer.parseInt(split1[1].trim());
									quoteBigProductsTab.setQuantity(qty);
								}	
								Double unitPrice = 0.0;
								if(StringUtils.isNotBlank(split1[2])){
									unitPrice = Double.parseDouble(split1[2].trim());
									quoteBigProductsTab.setUnitPrice(unitPrice);
								}	
								Double moldPrice = 0.0;
								if(StringUtils.isNotBlank(split1[3])){
									moldPrice = Double.parseDouble(split1[3].trim());
									quoteBigProductsTab.setMoldPrice(moldPrice);
								}	
								if(StringUtils.isNotBlank(split1[4])){
									quoteBigProductsTab.setProductRemark(split1[4]);
								}	
								amount = qty * unitPrice + moldPrice;
								totalAmount +=amount;							
						  if(quoteBigProductsTab != null && !"".equals(quoteBigProductsTab)){
							  quoteBigProductsTabMapper.insertSelective(quoteBigProductsTab);
						  }
					 }	
					//更新总金额
					totalAmount = new BigDecimal(totalAmount).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
					if(!(record == null || "".equals(record))){
						record.setTotalAmount(totalAmount);
						quoteBigProductsMapper.updateByPrimaryKeySelective(record);
					}
				}		
		 }
		 
		 //更新报价状态为授盘中
		 QuoteInfo quoteInfo = quoteInfoMapper.queryByOrderId(record.getOrderId());
		 quoteInfo.setOrderStatus(OrderStatusEnum.DECISION.getCode());
		 quoteInfoMapper.updateByPrimaryKeySelective(quoteInfo);
		 
		 //对应报价更新状态
		 SupplierQuoteInfo supplierQuoteInfo = supplierQuoteInfoMapper.queryByOrderIdAndFactoryId(record.getOrderId(), record.getFactoryId());
		 supplierQuoteInfo.setIsProcess(1);
		 supplierQuoteInfo.setQuoteStatus(QuoteOrderStatusEnum.CONFIRM_ORDER.getCode());
		 supplierQuoteInfoMapper.updateByPrimaryKeySelective(supplierQuoteInfo);
	}

	@Override
	public QuoteBigProducts selectBySupplierId(String factoryId, Integer orderId) {
		return quoteBigProductsMapper.selectBySupplierId(factoryId, orderId);
	}



}
