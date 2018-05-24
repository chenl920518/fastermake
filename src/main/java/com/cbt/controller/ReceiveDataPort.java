package com.cbt.controller;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cbt.entity.QuoteInfo;
import com.cbt.entity.QuoteProduct;
import com.cbt.enums.FollowStatusSelfEnum;
import com.cbt.enums.OrderStatusEnum;
import com.cbt.enums.OrderStatusSelfEnum;
import com.cbt.service.QuoteInfoService;
import com.cbt.service.QuoteProductService;
import com.cbt.util.Base64Encode;
import com.cbt.util.DateFormat;
import com.cbt.util.HttpClientDownloadUtil;
import com.cbt.util.OperationFileUtil;
import com.cbt.util.SerializeUtil;
import com.cbt.util.UploadAndDownloadPathUtil;

/**
 * 获取内部报价数据接口
 * 动态获取数据，并存入数据库
 * @author polo
 *
 */
@Controller
@RequestMapping("/port")
public class ReceiveDataPort {
	
       @Autowired
	   private QuoteProductService quoteProductService;
       @Autowired
       private QuoteInfoService quoteInfoService;
	  
       
     	private Logger logger = LoggerFactory.getLogger(this.getClass());
	  /**
	   * 同步内部报价数据
	   */
	  @ResponseBody
	  @RequestMapping(value ="/getQuotationData.do",method = RequestMethod.POST)	  
	   public String getQuotationData(@RequestParam Map<String,String> map){
	 		  
        try {
        	  if(map != null && !"".equals(map)){
    	          logger.debug("=======获取传输数据======"+map.toString()); 
        	  }else{
        		  logger.debug("=======获取传输数据======"+null); 
        	  }

        	  //将json数据反序列化为map格式
			  String jsonStr = map.get("RapidManufacturing");
			  if(StringUtils.isBlank(jsonStr)){
				  return "Is null";
			  }
			  
			  Map<Object, Object> cl = SerializeUtil.JsonToMap(jsonStr);
				  
			     //创建存放对象的list集合
				 List<QuoteProduct> list1 = new ArrayList<QuoteProduct>();
				 List<Object> list2 = (List<Object>)cl.get("allRapid");  
                 QuoteInfo quoteInfo = new QuoteInfo();               
				 
				 
				  if(!(cl.get("orderId") == null || "".equals(cl.get("orderId")))){
					  quoteInfo.setCsgOrderId((String)cl.get("orderId"));
				  }
				  if(!(cl.get("quoteEndDate") == null || "".equals(cl.get("quoteEndDate")))){
					  quoteInfo.setQuoteEndDate((String)cl.get("quoteEndDate"));
				  }
				  if(!(cl.get("mainProcess") == null || "".equals(cl.get("mainProcess")))){
					  quoteInfo.setMainProcess((String)cl.get("mainProcess"));
				  }
				  if(!(cl.get("inviteFactory") == null || "".equals(cl.get("inviteFactory")))){
					  quoteInfo.setInviteFactory((String)cl.get("inviteFactory"));
					  quoteInfo.setQuoteWay(1);
				  }else{
					  quoteInfo.setQuoteWay(0);
				  }
				  if(!(cl.get("country") == null || "".equals(cl.get("country")))){
					  quoteInfo.setCountry((String)cl.get("country"));
				  }
				  if(!(cl.get("quoteLocation") == null || "".equals(cl.get("quoteLocation")))){
					  quoteInfo.setQuoteLocation(Integer.parseInt(cl.get("quoteLocation").toString()));  
				  }
				  if(!(cl.get("staffNumber") == null || "".equals(cl.get("staffNumber")))){
					  quoteInfo.setStaffNumber((String)cl.get("staffNumber"));
				  }
				  if(!(cl.get("qualification") == null || "".equals(cl.get("qualification")))){
					  quoteInfo.setQualification((String)cl.get("qualification"));
				  }
				  if(!(cl.get("customerId") == null || "".equals(cl.get("customerId")))){
					  quoteInfo.setCustomerId(cl.get("customerId").toString());					  
				  }
				  if(!(cl.get("title") == null || "".equals(cl.get("title")))){
					  quoteInfo.setQuoteTitle(cl.get("title").toString());					  
				  }
				  if(!(cl.get("equipmentKeywords") == null || "".equals(cl.get("equipmentKeywords")))){
					  quoteInfo.setEquipmentKeywords(cl.get("equipmentKeywords").toString());					  
				  }
				  if(!(cl.get("productKeywords") == null || "".equals(cl.get("productKeywords")))){
					  quoteInfo.setProductKeywords(cl.get("productKeywords").toString());					  
				  }
				  if(!(cl.get("factoryName") == null || "".equals(cl.get("factoryName")))){
					  quoteInfo.setInviteFactoryName(cl.get("factoryName").toString());					  
				  }
				  if(!(cl.get("deliveryDate") == null || "".equals(cl.get("deliveryDate")))){
					  quoteInfo.setDeliveryDate((String)cl.get("deliveryDate"));
				  }
				  if(!(cl.get("quoter") == null || "".equals(cl.get("quoter")))){
					  quoteInfo.setQuoter((String)cl.get("quoter"));
				  }
				  if(!(cl.get("salename") == null || "".equals(cl.get("salename")))){
					  quoteInfo.setInitialSales((String)cl.get("salename"));
				  }
				  if(!(cl.get("contactTel") == null || "".equals(cl.get("contactTel")))){
					  quoteInfo.setContactTel((String)cl.get("contactTel"));
				  }
				  if(!(cl.get("quotationAssistant") == null || "".equals(cl.get("quotationAssistant")))){
					  quoteInfo.setPriceAssistant(cl.get("quotationAssistant").toString());
				  }
				  if(!(cl.get("quoteRemark") == null || "".equals(cl.get("quoteRemark")))){
					  quoteInfo.setQuoteDetail(cl.get("quoteRemark").toString());
				  }
				  quoteInfo.setOrderStatus(OrderStatusEnum.NORMAL_ORDER.getCode());
				  quoteInfo.setInquiryStatus(OrderStatusSelfEnum.NORMAL.getCode());
				  quoteInfo.setFollowStatus(FollowStatusSelfEnum.NO_ORDER.getCode());
				  //quoteInfo.setCountry("中国");
				  quoteInfo.setPublishDate(DateFormat.format());
				  quoteInfo.setCreateTime(DateFormat.format());
				  quoteInfo.setQuotePurpose(0);
				  quoteInfo.setQuoteFreightTerm("运送到目的地价");
				  quoteInfo.setCity("上海");
				  
				  
				  for (Object o : list2) {
					  QuoteProduct quoteProduct = new QuoteProduct();
					  String strs = o.toString();
				      //循环list先将底层map转换为json字符串，再反序列化为map
					  Map<Object, Object> cl2 = SerializeUtil.JsonToMap(strs);
					  if(!(cl2.get("productName") == null || "".equals(cl2.get("productName")))){
						  quoteProduct.setProductName((String)cl2.get("productName"));
					  }
					  if(!(cl2.get("allProcess") == null || "".equals(cl2.get("allProcess")))){
						  quoteProduct.setProcess((String)cl2.get("allProcess"));
					  }
					  if(!(cl2.get("materials") == null || "".equals(cl2.get("materials")))){
						  quoteProduct.setMaterials((String)cl2.get("materials"));
					  }
					  if(!(cl2.get("quantityList") == null || "".equals(cl2.get("quantityList")))){
						  quoteProduct.setQuantityList((String)cl2.get("quantityList"));
					  }
					  if(!(cl2.get("targetPrice") == null || "".equals(cl2.get("targetPrice")))){
						  quoteProduct.setTargetPrice(Double.parseDouble(cl2.get("targetPrice").toString()));
					  }
					  if(!(cl2.get("targetMoldPrice") == null || "".equals(cl2.get("targetMoldPrice")))){
						  quoteProduct.setTargetMoldPrice(Double.parseDouble(cl2.get("targetMoldPrice").toString()));
					  }
					  if(!(cl2.get("annualQuantity") == null || "".equals(cl2.get("annualQuantity")))){
						  quoteProduct.setAnnualQuantity(cl2.get("annualQuantity").toString());
					  }
					  if(!(cl2.get("quantityUnit") == null || "".equals(cl2.get("quantityUnit")))){
						  quoteProduct.setQuantityUnit((String)cl2.get("quantityUnit"));
					  }
					  if(!(cl2.get("productRemark") == null || "".equals(cl2.get("productRemark")))){
						  quoteProduct.setProductRemark((String)cl2.get("productRemark"));
					  }		
					  if(!(cl2.get("drawingPath") == null || "".equals(cl2.get("drawingPath")))){
						  String newName = HttpClientDownloadUtil.getImg((String)cl2.get("drawingPath"), (String)cl.get("orderId"));
						  String staticPath = UploadAndDownloadPathUtil.getDrawingFileStatic() + newName;
						  if(StringUtils.isBlank(newName)){
							  staticPath = ""; 
						  }
						  quoteProduct.setDrawingPathCompress(staticPath);
					  }
					  if(!(cl2.get("video") == null || "".equals(cl2.get("video")))){
						  String newName = HttpClientDownloadUtil.getVideo((String)cl2.get("video"));
						  String staticPath = UploadAndDownloadPathUtil.getDrawingFileStatic() + newName;
						  if(StringUtils.isBlank(newName)){
							  staticPath = ""; 
						  }
						  quoteProduct.setVideoPath(staticPath);;
					  }
					  if(!(cl2.get("weight") == null || "".equals(cl2.get("weight")))){
						  Double weight = Double.parseDouble(cl2.get("weight").toString());
						  quoteProduct.setWeight(weight);
					  }
					  if(!(cl2.get("cgsQuotationId") == null || "".equals(cl2.get("cgsQuotationId")))){
						  Integer cgsQuotationId = Integer.parseInt(cl2.get("cgsQuotationId").toString());						
						  quoteProduct.setCgsQuotationId(cgsQuotationId);
					  }
					  quoteProduct.setCustomerId(cl.get("customerId").toString());
					  list1.add(quoteProduct);
				  }
				  quoteProductService.insert(quoteInfo, list1);
				  
				  String orderId = "";
				  if(quoteInfo.getOrderId() == null){
					  orderId = "";
				  }else{
					  orderId = quoteInfo.getOrderId().toString();
				  }
			  return orderId;
			} catch (Exception e) {
				logger.error("=========error=========", e);
				return "NO";
			}
	  }
     	  
	  
	  
	  
	  /**
	   * 获取图纸
	   * @param map
	   * @return
	   */
	   @ResponseBody
	   @RequestMapping(value ="/getQuotationDrawing.do",method = RequestMethod.POST)	  
	   public String getQuotationDrawing(@RequestParam Map<String,String> map){
		  
		   String orderId = map.get("quotationId");
		   if(StringUtils.isBlank(orderId)){
			   logger.info("获取询盘表ID失败");
			   return "NO";
		   }else{
			   String csgOrderId = map.get("orderId");
			   String mainFigure = map.get("mainFigure");
			   try {
				QuoteInfo quoteInfo = quoteInfoService.queryByOrderId(Integer.parseInt(orderId));
				String d_path = HttpClientDownloadUtil.getQuoteImg(mainFigure, csgOrderId , quoteInfo.getOrderId().toString());	
				quoteInfo.setDrawingName(OperationFileUtil.changeFilenameTime(Integer.parseInt(orderId), mainFigure));
				quoteInfo.setDrawingPath(d_path);
				quoteInfoService.updateByPrimaryKey(quoteInfo);
				
				logger.info("获取内部报价图纸成功");
				return "YES";
			} catch (Exception e) {			
				logger.info("获取内部报价图纸失败");
				return "NO";
			}
		  }		   		  		   		   
	 }
	  
	  
	   
	   
	     /**
		   * 当内部报价删除时，将当前询盘更新为取消
		   * @param map
		   * @return
		   */
		   @ResponseBody
		   @RequestMapping(value ="/updateQuoteStatus.do",method = RequestMethod.POST)	  
		   public String updateQuoteStatus(@RequestParam Map<String,String> map){
			  
			   String orderId = map.get("orderId");
			   if(StringUtils.isBlank(orderId)){
				   logger.info("获取询盘表ID失败");
				   return "NO";
			   }else{
				   try {
					orderId = Base64Encode.getFromBase64(orderId);
					QuoteInfo quoteInfo = quoteInfoService.queryByOrderId(Integer.parseInt(orderId));
					quoteInfo.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
					quoteInfo.setInquiryStatus(OrderStatusSelfEnum.CANCEL.getCode());
					quoteInfoService.updateByPrimaryKey(quoteInfo);					
					logger.info(orderId+"状态已取消");
					return "YES";
				} catch (Exception e) {			
					e.printStackTrace();
					logger.info(orderId+"更新询盘状态失败");
					return "NO";
				}
			  }		   		  		   		   
		 }
		  
	   
	   
	  
		   
		   
		  /**
		   *  获取下单工厂数据
		   * @Title getFactoryOrder 
		   * @Description 
		   * @param map
		   * @return
		   * @return int
		   */
		  @ResponseBody
		  @RequestMapping(value ="/getFactoryOrder.do",method = RequestMethod.POST)	  
		   public int getFactoryOrder(@RequestParam Map<String,String> map){
		 		  
	        	  try {
					//将json数据反序列化为map格式
					  String projectId = map.get("projectId");
					  String factoryId = map.get("factoryId");
					  String factoryName = map.get("factoryName");
					  String totalAmount = map.get("totalAmount");
					  int state = quoteInfoService.updateQuote(projectId, factoryId, factoryName, totalAmount);
                      return state;
				} catch (ParseException e) {
					logger.info("====更新询盘状态失败========getFactoryOrder======",e);
					return 0;
				}
						  
		  }
		  
		  
		  /**
		   *  同步内部报价/NBMail询盘跟进数据
		   * @Title getFactoryOrder 
		   * @Description 
		   * @param map
		   * @return
		   * @return int
		   */
		  @ResponseBody
		  @RequestMapping(value ="/updateFollowStatus.do",method = RequestMethod.POST)	  
		   public int updateFollowStatus(@RequestParam Map<String,String> map){
		 		  
	        	  try {
					//将json数据反序列化为map格式
					  String projectId = map.get("projectId");
					  String followStatus = map.get("followStatus");
                      int state = quoteInfoService.updateQuote(projectId, followStatus);
                      return state;
				} catch (Exception e) {
					logger.info("====更新跟进状态失败========updateFollowStatus======",e);
					return 0;
				}
						  
		  }
		  
		  
		  
		  
		  
		  /**
		   *  同步内部报价修改助理数据
		   * @Title getFactoryOrder 
		   * @Description 
		   * @param map
		   * @return
		   * @return int
		   */
		  @ResponseBody
		  @RequestMapping(value ="/updateAssistant.do",method = RequestMethod.POST)	  
		   public int updateAssistant(HttpServletRequest request){
		 		  
	        	  try {
					  //获取项目号和项目助理
					  String projectId = request.getParameter("projectId");
					  String assistant = request.getParameter("assistant");
                      int state = quoteInfoService.updateQuoteAssistant(projectId, assistant);					  
                      return state;
				} catch (Exception e) {
					logger.info("====修改助理数据失败========updateAssistant======",e);
					return 0;
				}
						  
		  }
		  
		  
		  
		  
		   
}
