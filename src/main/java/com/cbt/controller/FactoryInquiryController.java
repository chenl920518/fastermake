package com.cbt.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cbt.entity.FactoryInfo;
import com.cbt.entity.FactoryUser;
import com.cbt.entity.NoteMessage;
import com.cbt.entity.Qualification;
import com.cbt.entity.QuoteBigProducts;
import com.cbt.entity.QuoteBigProductsTab;
import com.cbt.entity.QuoteInfo;
import com.cbt.entity.QuoteMessage;
import com.cbt.entity.QuoteProduct;
import com.cbt.entity.QuoteWeeklyReport;
import com.cbt.entity.SupplierQuoteInfo;
import com.cbt.entity.SupplierQuoteProduct;
import com.cbt.entity.SysUser;
import com.cbt.enums.FollowStatusSelfEnum;
import com.cbt.enums.MessageTypeEnum;
import com.cbt.enums.OrderStatusEnum;
import com.cbt.enums.OrderStatusSelfEnum;
import com.cbt.enums.QuoteOrderStatusEnum;
import com.cbt.enums.StateEnum;
import com.cbt.service.EquipmentService;
import com.cbt.service.FactoryInfoService;
import com.cbt.service.FactoryUserService;
import com.cbt.service.QualificationService;
import com.cbt.service.QuoteBigProductsService;
import com.cbt.service.QuoteBigProductsTabService;
import com.cbt.service.QuoteInfoService;
import com.cbt.service.QuoteMessageService;
import com.cbt.service.QuoteProductService;
import com.cbt.service.QuoteWeeklyReportService;
import com.cbt.service.SupplierQuoteInfoService;
import com.cbt.service.SupplierQuoteProductService;
import com.cbt.service.UserOrderService;
import com.cbt.util.Base64Encode;
import com.cbt.util.DateFormat;
import com.cbt.util.GetServerPathUtil;
import com.cbt.util.Md5Util;
import com.cbt.util.OperationFileUtil;
import com.cbt.util.UploadAndDownloadPathUtil;
import com.cbt.util.WebCookie;

/**
 * Edit 修改查询有意向报价工厂（去除当前登录公司）
 * @ClassName FactoryInfoService 
 * @Description
 * @author polo
 * @date 2018年4月8日 下午1:22:21
 */

@RequestMapping("/factoryInquiry")
@Controller
public class FactoryInquiryController {
	  @Autowired
	  private FactoryInfoService factoryInfoService;  
	  @Autowired
	  private QuoteProductService quoteProductService;  
	  @Autowired
	  private QuoteInfoService quoteInfoService;  
	  @Autowired
	  private QuoteMessageService quoteMessageService;  
	  @Autowired
	  private SupplierQuoteInfoService supplierQuoteInfoService;  
	  @Autowired
	  private SupplierQuoteProductService supplierQuoteProductService;  
	  @Autowired
	  private EquipmentService equipmentService;  
	  @Autowired
	  private QualificationService qualificationService;
	  @Autowired
	  private QuoteBigProductsService quoteBigProductsService;
	  @Autowired
	  private QuoteBigProductsTabService quoteBigProductsTabService;
	  @Autowired
	  private QuoteWeeklyReportService quoteWeeklyReportService;
	  @Autowired
	  private FactoryUserService factoryUserService;
	  @Autowired
	  private UserOrderService userOrderService;
     
	  private Logger logger = LoggerFactory.getLogger(this.getClass());
	  
	  /**
	   * 查询询盘列表
	   * @param request
	   * @param response
	   * @return
	   */
	  @ResponseBody
      @RequestMapping("/queryFactoryInquiryList.do")
      public JsonResult<Map<String,Object>> queryFactoryInquiryList(HttpServletRequest request,HttpServletResponse response){
    	 
    	  try {
			  String factoryId = WebCookie.getFactoryId(request);
			  if(StringUtils.isBlank(factoryId)){
				  return  new JsonResult<Map<String,Object>>(2,"未获取到登录信息");
			  }else{
				  Integer permission = WebCookie.getPermission(request);
				  Integer factoryUserId = WebCookie.getFactoryUserId(request);
				  String process = request.getParameter("process");
				  //获取中、英文查询
//				  if(StringUtils.isNotBlank(process)){
//					  ProcessZhAndEnEnum enEnum = ProcessZhAndEnEnum.getEnum(process);
//					  if(enEnum != null){
//						  process = enEnum.getStr()+enEnum.getValue();
//					  }
//				  }
				  
				  String product = request.getParameter("product");
				  Integer orderStatus = null;
				  if(StringUtils.isNotBlank(request.getParameter("orderStatus"))){
					  orderStatus = Integer.parseInt(request.getParameter("orderStatus"));
				  }
				  List<QuoteProduct> inquiryOrders = new ArrayList<QuoteProduct>();			
				  if(permission == 1){
					  inquiryOrders = quoteProductService.queryByFactoryIdGroupByOrderIdAdmin(process, product, factoryId,orderStatus); 
				  }else{					  
					  inquiryOrders = quoteProductService.queryByFactoryIdGroupByOrderId(process, product, factoryId, orderStatus, factoryUserId);
				  }
				  
				  List<List<SupplierQuoteInfo>> supplierQuoteInfos = new ArrayList<List<SupplierQuoteInfo>>();
				  for (QuoteProduct quoteProduct : inquiryOrders) {
					  List<SupplierQuoteInfo> supplierQuoteInfo = supplierQuoteInfoService.queryByOrderId(quoteProduct.getOrderId());
					  supplierQuoteInfos.add(supplierQuoteInfo);
				  }
				  
				  
				  Map<String,Object> map = new HashMap<String, Object>();
				  map.put("inquiryOrders", inquiryOrders);				  			  
				  map.put("supplierQuoteInfos", supplierQuoteInfos);				  			  
				  return new JsonResult<Map<String,Object>>(map);
			  }	  
			} catch (NumberFormatException e) {
				  logger.error("==========factoryInquiry   queryFactoryInquiryList=========",e);
		    	  return  new JsonResult<Map<String,Object>>(1,"查询失败");
			} catch (Exception e) {
				  logger.error("==========factoryInquiry   queryFactoryInquiryList=========",e);
			      return  new JsonResult<Map<String,Object>>(1,"查询失败");
			} 

      }
	  
	  
	
	  
	  /**
	   * 修改询盘信息
	   * @param request
	   * @param response
	   * @return
	   */
	  @ResponseBody
      @RequestMapping("/updateFactoryInquiry.do")
      public JsonResult<String> updateFactoryInquiry(HttpServletRequest request,HttpServletResponse response){
		  
		try {
			  String factoryId = WebCookie.getFactoryId(request);
			  //获取后台登录session
			  HttpSession session = request.getSession();
			  SysUser sysUser = (SysUser) session.getAttribute("_SESSION_USER");
			  
			  if(StringUtils.isBlank(factoryId) && sysUser == null){
				  return new JsonResult<String>(2,"未获取到登录信息");
			  }else{
				  QuoteInfo quoteInfo = new QuoteInfo();
				  if(StringUtils.isNotBlank(factoryId)){
					  quoteInfo = quoteInfoService.queryByOrderIdAndFactoryId(Integer.parseInt(request.getParameter("orderId")), factoryId);  
				  }
				  
				  //如果是快制造内部人员、可修改所有订单
				  if(sysUser != null){
					  quoteInfo = quoteInfoService.queryByOrderId(Integer.parseInt(request.getParameter("orderId")));
				  }
				  if(quoteInfo == null || "".equals(quoteInfo)){
					  return new JsonResult<String>(1,"没有权限操作");
				  }
				  if(StringUtils.isNotBlank(request.getParameter("drawingPath"))){
					  quoteInfo.setDrawingPath(request.getParameter("drawingPath"));  
				  }
				  if(StringUtils.isNotBlank(request.getParameter("quoteEndDate"))){
					  quoteInfo.setQuoteEndDate(request.getParameter("quoteEndDate"));  
				  }
				  if(StringUtils.isNotBlank(request.getParameter("quoteTitle"))){
					  quoteInfo.setQuoteTitle(request.getParameter("quoteTitle"));  
				  }
				  if(StringUtils.isNotBlank(request.getParameter("productKeywords"))){
					  quoteInfo.setProductKeywords(request.getParameter("productKeywords"));  
				  }
				  if(StringUtils.isNotBlank(request.getParameter("equipmentKeywords"))){
					  quoteInfo.setEquipmentKeywords(request.getParameter("equipmentKeywords"));  
				  }				  
				  if(StringUtils.isNotBlank(request.getParameter("mainProcess"))){
					  quoteInfo.setMainProcess(request.getParameter("mainProcess"));  
				  }				  
				  if(StringUtils.isNotBlank(request.getParameter("inviteStatus"))){
					  quoteInfo.setQuoteWay(Integer.parseInt(request.getParameter("inviteStatus")));  
				  }
				  if(StringUtils.isNotBlank(request.getParameter("inviteFactory"))){
					  quoteInfo.setInviteFactory(request.getParameter("inviteFactory"));  
				  }
				  if(StringUtils.isNotBlank(request.getParameter("quoteLocation"))){
					  quoteInfo.setQuoteLocation(Integer.parseInt(request.getParameter("quoteLocation")));  
				  }
				  if(StringUtils.isNotBlank(request.getParameter("staffNumber"))){
					  quoteInfo.setStaffNumber(request.getParameter("staffNumber"));  
				  }
				  if(StringUtils.isNotBlank(request.getParameter("inviteFactoryName"))){
					  quoteInfo.setInviteFactoryName(request.getParameter("inviteFactoryName"));  
				  }
				  if(StringUtils.isNotBlank(request.getParameter("inviteFactory"))){
					  quoteInfo.setInviteFactory(request.getParameter("inviteFactory"));  
				  }
				  if(StringUtils.isNotBlank(request.getParameter("qualification"))){
					  quoteInfo.setQualification(request.getParameter("qualification"));  
				  }
				  if(StringUtils.isNotBlank(request.getParameter("quoteFreightTerm"))){
					  quoteInfo.setQuoteFreightTerm(request.getParameter("quoteFreightTerm"));  
				  }
				  if(StringUtils.isNotBlank(request.getParameter("city"))){
					  quoteInfo.setCity(request.getParameter("city"));  
				  }
				  if(StringUtils.isNotBlank(request.getParameter("quotePurpose"))){
					  quoteInfo.setQuotePurpose(Integer.parseInt(request.getParameter("quotePurpose")));  
				  }
//				  if(StringUtils.isNotBlank(request.getParameter("quoteRemark"))){
					  quoteInfo.setQuoteRemark(request.getParameter("quoteRemark"));  
//				  }
				  if(StringUtils.isNotBlank(request.getParameter("drawingName"))){
					  quoteInfo.setDrawingName(request.getParameter("drawingName")); 					  			 		  
				  }
				  if(StringUtils.isNotBlank(request.getParameter("quoteTitle"))){
					  quoteInfo.setQuoteTitle(request.getParameter("quoteTitle"));  
				  }
				  String productList = request.getParameter("productList");
				  
				  
				  //修改询盘信息时如果当前询盘是被拒绝的状态，更改为待发布
				  if(quoteInfo.getOrderStatus() == OrderStatusEnum.NOPASS.getCode()){
					  quoteInfo.setOrderStatus(OrderStatusEnum.DRAFT.getCode());
				  }
				  quoteInfo.setUpdateTime(DateFormat.format());
                  quoteInfoService.updateByPrimaryKey(quoteInfo, productList,request.getParameter("drawingName"));
				  return new JsonResult<String>(0,"更新成功");
			  		  		  		  
			  }
		} catch (NumberFormatException e) {
			logger.error("==========factoryInquiry   updateFactoryInquiry=========",e);
			return new JsonResult<String>(1,"未获取到信息");
		} catch (Exception e) {
			logger.error("==========factoryInquiry   updateFactoryInquiry=========",e);
			return new JsonResult<String>(1,"更新失败");
		}	  
	  }  
	  
	  
	  
	 
	  
	  /**
	 	 * 修改缩略图
	 	 * @param request
	 	 * @param response
	 	 * @return
	 	 */
	    @ResponseBody
	 	@RequestMapping("/updateCompressImg.do")
	 	public JsonResult<String> updateCompressImg(HttpServletRequest request,HttpServletResponse response){
	 		
	 	 try {
	 		 
	 		  String factoryId = WebCookie.getFactoryId(request);
			  //获取后台登录session
			  HttpSession session = request.getSession();
			  SysUser sysUser = (SysUser) session.getAttribute("_SESSION_USER");
			  
			  logger.warn("获取到工厂id"+factoryId);
			  if(StringUtils.isBlank(factoryId) && sysUser == null){
				  return new JsonResult<String>(2,"未获取到登录信息");
			  }else{
				     String productId = request.getParameter("productId");
			 		 if(StringUtils.isBlank(productId)){
			 			return new JsonResult<String>(1,"产品id为空");	
			 		 }
			 		logger.warn("获取到productId"+productId);
			 		QuoteProduct quoteProduct = quoteProductService.selectByPrimaryKey(Integer.parseInt(productId));
			 		 //如果非此用户操作订单，不允许
			 		 if(!quoteProduct.getCustomerId().equals(factoryId) && sysUser == null){
			 			 return new JsonResult<String>(3,"无操作权限"); 
			 		 }
			 		 
			 		 String path = UploadAndDownloadPathUtil.getDrawingFile();	 		
					 File file = new File(path);
					 if  (!file.exists()  && !file .isDirectory())      
					 {         
						 file .mkdir();     
					 }  	    
			 		Map<String, String> multiFileUpload = OperationFileUtil.multiFileUpload_changename(request, path);
			 		
			 		String staticPath = "";
			 		String photoName = request.getParameter("photoName");
			 		logger.warn("获取到Map"+multiFileUpload);
			 		logger.warn("获取到photoName"+  photoName);
			 		if(multiFileUpload != null && multiFileUpload.size() != 0){
							   String pic = multiFileUpload.get(photoName); 
							   logger.warn("获取到PIC"+pic);
//							   String photoCompressName = "";
//						        if(pic != null && !"".equals(pic)){
//						        	photoCompressName = OperationFileUtil.changeFilenameZip(pic);
//						        }		
						        //String photoPath = path + pic;
			                    // String photoPathCompress = path + photoCompressName;
								//ImageCompressUtil.saveMinPhoto(photoPath, photoPathCompress, 500, 1d);		
							
								staticPath = UploadAndDownloadPathUtil.getDrawingFileStatic() + pic;
								logger.warn("获取到staticPath"+staticPath);
							quoteProduct.setDrawingPathCompress(staticPath);									
							quoteProductService.updateByPrimaryKeySelective(quoteProduct);	
					 		return new JsonResult<String>(0,"success",staticPath);
			 		}else{
			 			 logger.warn("更新图片失败:"+ DateFormat.format() +quoteProduct);
				 		 return new JsonResult<String>(1,"未获取到上传数据");
			 		}
			  }		 

		 	} catch (IllegalStateException e) {
		 		logger.error("==========factoryInquiry   updateCompressImg=========",e);
		 		return new JsonResult<String>(1,e.getMessage());	
		 	} catch (IOException e) {
		 		logger.error("==========factoryInquiry   updateCompressImg=========",e);
		 		return new JsonResult<String>(1,e.getMessage());	
		 	} catch (Exception e) {
		 		logger.error("==========factoryInquiry   updateCompressImg=========",e);
				return new JsonResult<String>(1,e.getMessage());	
			} 	
		 	}
	  
	  
	  
	  
	  
	  
	    /**
	 	 * 修改上传视频
	 	 * @param request
	 	 * @param response
	 	 * @return
	 	 */
	    @ResponseBody
	 	@RequestMapping("/updateVideo.do")
	 	public JsonResult<String> updateVideo(HttpServletRequest request,HttpServletResponse response){
	 		
	 	 try {
	 		 
	 		  String factoryId = WebCookie.getFactoryId(request);
			  //获取后台登录session
			  HttpSession session = request.getSession();
			  SysUser sysUser = (SysUser) session.getAttribute("_SESSION_USER");
			  
			  if(StringUtils.isBlank(factoryId) && sysUser == null){
				  return new JsonResult<String>(2,"未获取到登录信息");
			  }else{
				     String productId = request.getParameter("productId");
			 		 if(StringUtils.isBlank(productId)){
			 			return new JsonResult<String>(1,"产品id为空");	
			 		 }
			 		 QuoteProduct quoteProduct = quoteProductService.selectByPrimaryKey(Integer.parseInt(productId));			 		 
			 		 //如果非此用户操作订单，不允许
			 		 if(!factoryId.equals(quoteProduct.getCustomerId()) && sysUser == null){
			 			 return new JsonResult<String>(3,"无操作权限"); 
			 		 }
			 		 
			 		 String path = UploadAndDownloadPathUtil.getDrawingFile();	 		
					 File file = new File(path);
					 if  (!file.exists()  && !file .isDirectory())      
					 {         
						 file .mkdir();     
					 }  	    
					 
			 		Map<String, String> multiFileUpload = OperationFileUtil.multiFileUpload_changename(request, path);
			 		if(!(multiFileUpload == null || multiFileUpload.size() == 0)){
			 			 Set<String> keySet = multiFileUpload.keySet();
						   for (String key : keySet) {	
							      String pic = multiFileUpload.get(key); 								   
//							      String removeExtension = FilenameUtils.removeExtension(pic);
//                                  String name = removeExtension+".mp4";
//						          String ffmpegPath1 = "D:\\ffmpeg\\bin\\";
//						          String ffmpegPath1 = "/monchickey/ffmpeg/bin/";
//						          if (!ConvertVideo.checkfile(path + pic)) {
//						              System.out.println(path + pic + " is not file");
//						              return new JsonResult<String>(4,"未获取到文件");
//						          }
//						          if (ConvertVideo.process(path + pic, ffmpegPath1, path, name)) {
						              quoteProduct.setVideoPath(UploadAndDownloadPathUtil.getDrawingFileStatic() + pic);
						              quoteProductService.updateByPrimaryKeySelective(quoteProduct);
//						          }							   
						 	   return new JsonResult<String>(0,"success",UploadAndDownloadPathUtil.getDrawingFileStatic() + pic);	
						   }
			 		} 
			 		   return new JsonResult<String>(0,"success");	
			  }
		 

		 	} catch (IllegalStateException e) {
		 		logger.error("==========factoryInquiry   updateVideo=========",e);
		 		return new JsonResult<String>(1,"failed");	
		 	} catch (IOException e) {
		 		logger.error("==========factoryInquiry   updateVideo=========",e);
		 		return new JsonResult<String>(1,"failed");	
		 	} catch (Exception e) {
		 		logger.error("==========factoryInquiry   updateVideo=========",e);
				return new JsonResult<String>(1,"failed");	
			} 
		 			
		 	} 
	  
	  
	  /**
	   * 
	   * @Title queryCountByFactoryId 
	   * @Description
	   * @param request
	   * @param response
	   * @return
	   * @return JsonResult<Map<String,Object>>
	   */
	  @ResponseBody
      @RequestMapping("/queryCountByFactoryId.do")
      public JsonResult<Map<String,Object>> queryCountByFactoryId(HttpServletRequest request,HttpServletResponse response){
    	 
    	  try {
			  String factoryId = WebCookie.getFactoryId(request);
			  if(StringUtils.isBlank(factoryId)){
				  return  new JsonResult<Map<String,Object>>(2,"未获取到登录信息");
			  }else{
				  Integer permission = WebCookie.getPermission(request);
				  Integer factoryUserId = WebCookie.getFactoryUserId(request);
				  //采购商查询
				  //草稿询盘
				  int buyerDraftOrder = 0;
			        if(permission == 1){
			        	buyerDraftOrder = quoteProductService.totalByFactoryIdAdmin(null, null, factoryId, OrderStatusEnum.DRAFT.getCode());
			        }else{
			        	buyerDraftOrder = quoteProductService.totalByFactoryId(null, null, factoryId, OrderStatusEnum.DRAFT.getCode(), factoryUserId);
			        }
						 
				  //正常询盘				 
				  int buyerNormalOrder = 0;
			        if(permission == 1){
			        	buyerNormalOrder = quoteProductService.totalByFactoryIdAdmin(null, null, factoryId, OrderStatusEnum.NORMAL_ORDER.getCode());
			        }else{
			        	buyerNormalOrder = quoteProductService.totalByFactoryId(null, null, factoryId, OrderStatusEnum.NORMAL_ORDER.getCode(), factoryUserId);
			        }
				  
				  //已结束询盘
				  int buyerFinishOrder = 0;
				       if(permission == 1){
				    	    buyerFinishOrder = quoteProductService.totalByFactoryIdAdmin(null, null, factoryId, OrderStatusEnum.CONFIRM.getCode());
				        }else{
				        	buyerFinishOrder = quoteProductService.totalByFactoryId(null, null, factoryId, OrderStatusEnum.CONFIRM.getCode(), factoryUserId);
				        }
				  //已过期询盘
				  int buyerExpireOrder = 0;
					   if(permission == 1){
						    buyerExpireOrder = quoteProductService.totalByFactoryIdAdmin(null, null, factoryId, OrderStatusEnum.EXPIRE.getCode());
				        }else{
				        	buyerExpireOrder = quoteProductService.totalByFactoryId(null, null, factoryId, OrderStatusEnum.EXPIRE.getCode(), factoryUserId);
				       }
				  
				  //已取消询盘
				  int buyerCancelOrder = 0;
					   if(permission == 1){
						   buyerCancelOrder = quoteProductService.totalByFactoryIdAdmin(null, null, factoryId, OrderStatusEnum.CANCEL.getCode());
				        }else{
				        	buyerCancelOrder = quoteProductService.totalByFactoryId(null, null, factoryId, OrderStatusEnum.CANCEL.getCode(), factoryUserId);
				       }
				  
				  //授盘中询盘
				  int buyerDecisionOrder = 0;
					   if(permission == 1){
						   buyerDecisionOrder = quoteProductService.totalByFactoryIdAdmin(null, null, factoryId, OrderStatusEnum.DECISION.getCode());
				        }else{
				           buyerDecisionOrder = quoteProductService.totalByFactoryId(null, null, factoryId, OrderStatusEnum.DECISION.getCode(), factoryUserId);
				       }
				  
				  //处理中询盘
				  int buyerProcessOrder = 0;
					   if(permission == 1){
						   buyerProcessOrder = quoteProductService.totalByFactoryIdAdmin(null, null, factoryId, OrderStatusEnum.PROCESS.getCode());
				        }else{
				        	buyerProcessOrder = quoteProductService.totalByFactoryId(null, null, factoryId, OrderStatusEnum.PROCESS.getCode(), factoryUserId);
				       }
				  
				  
				  //审核未通过询盘
				  int buyerNoPassOrder = 0;
					   if(permission == 1){
						   buyerNoPassOrder = quoteProductService.totalByFactoryIdAdmin(null, null, factoryId, OrderStatusEnum.NOPASS.getCode());
				        }else{
				           buyerNoPassOrder = quoteProductService.totalByFactoryId(null, null, factoryId, OrderStatusEnum.NOPASS.getCode(), factoryUserId);
				       }
				  
				  
				  
				  
				  Map<String,Object> map = new HashMap<String, Object>();			  
				  map.put("buyerDraftOrder", buyerDraftOrder);				  
				  map.put("buyerNormalOrder", buyerNormalOrder);				  
				  map.put("buyerFinishOrder", buyerFinishOrder);				  
				  map.put("buyerExpireOrder", buyerExpireOrder);				  		  
				  map.put("buyerCancelOrder", buyerCancelOrder);				  
				  map.put("buyerDecisionOrder", buyerDecisionOrder);				  
				  map.put("buyerProcessOrder", buyerProcessOrder);	
				  map.put("buyerNoPassOrder", buyerNoPassOrder);	
				  
				  
				  
				  return  new JsonResult<Map<String,Object>>(map);
			  }
			} catch (NumberFormatException e) {
				logger.error("==========factoryInquiry   queryCountByFactoryId=========",e);
		    	  return  new JsonResult<Map<String,Object>>(1,"查询失败");
			} catch (Exception e) {
				logger.error("==========factoryInquiry   queryCountByFactoryId=========",e);
				  return  new JsonResult<Map<String,Object>>(1,"查询失败");
			} 

      }
	  
	  
	  
	  /**
	   * 查询采购商发布的询盘详情、工厂报价数据
	   * @Title queryInquiryDetail 
	   * @Description 
	   * @param request
	   * @param response
	   * @return
	   * @return JsonResult<Map<String,Object>>
	   */
	  @ResponseBody
      @RequestMapping("/queryInquiryDetail.do")
      public JsonResult<Map<String,Object>> queryInquiryDetail(HttpServletRequest request,HttpServletResponse response){		  
		  	try {
				  String orderId = request.getParameter("orderId");
				  if(StringUtils.isBlank(orderId)){
					return new JsonResult<Map<String,Object>>(1,"未获取到询盘号");
				  }
				  String customerId = WebCookie.getFactoryId(request);
				  //获取后台登录session
				  HttpSession session = request.getSession();
				  SysUser sysUser = (SysUser) session.getAttribute("_SESSION_USER");
				  
				  //后台也可以修改询盘信息
				  if(StringUtils.isBlank(customerId) && sysUser == null){
				      return new JsonResult<Map<String,Object>>(2,"未登录");
				  }
				  Integer isVip = WebCookie.getVip(request);
                  String factoryId = request.getParameter("factoryId");
				  
                  //如果不是后台管理员，也不是该采购商工厂管理员，判断是否有查询该询盘权限
                  if(sysUser == null){
    				  Integer permission = WebCookie.getPermission(request);
    				  Integer factoryUserId = WebCookie.getFactoryUserId(request);
    				  if(permission != 1){
    					  int count = userOrderService.selectByFactoryUserIdAndOrderId(factoryUserId, Integer.parseInt(orderId));
    					  if(count == 0){
    						  return new JsonResult<Map<String,Object>>(3,"无权限查看");
    					  }
    				  }   				  
                  }

                  
				  QuoteInfo quoteInfo = quoteInfoService.queryByOrderId(Integer.parseInt(orderId));				
				  List<QuoteProduct> products = quoteProductService.queryByOrderId(Integer.parseInt(orderId));	
				  
				  //查询历史报价大货数据
				  QuoteBigProducts quoteBigProducts = quoteBigProductsService.selectBySupplierId(factoryId, Integer.parseInt(orderId));
				  List<QuoteBigProductsTab> quoteBigProductsTab = new ArrayList<QuoteBigProductsTab>();
				  if(!(quoteBigProducts == null || "".equals(quoteBigProducts))){
					  quoteBigProductsTab = quoteBigProductsTabService.selectByProductId(quoteBigProducts.getId());  
				  }					  
		  
				  
				  //查询符合工艺条件的工厂添加在邀请报价工厂筛选内
				  List<Map<String, Object>> list = factoryInfoService.queryByMainProcess(quoteInfo.getMainProcess());
				  
	              //查询所有工厂报价
				  SupplierQuoteInfo info = new SupplierQuoteInfo();
				  List<SupplierQuoteProduct> supplierQuoteProducts = new ArrayList<SupplierQuoteProduct>();
				  List<List<SupplierQuoteProduct>> supplierQuoteProductss = new ArrayList<List<SupplierQuoteProduct>>();
				  List<SupplierQuoteInfo> supplierQuoteInfos = supplierQuoteInfoService.queryByOrderId(Integer.parseInt(orderId));	              
				  for (SupplierQuoteInfo supplierQuoteInfo : supplierQuoteInfos) {
					  String qualificationNames = "";
					  List<Qualification> qualifications = qualificationService.queryByFactory(supplierQuoteInfo.getFactoryId());
					  for (Qualification qualification : qualifications) {
						  qualificationNames +=qualification.getQualificationName() + "<br>";
					  }
					  supplierQuoteInfo.setQualificationNames(qualificationNames);
					  
					  //获取报价工厂数据
					  if(StringUtils.isNotBlank(factoryId)){
						  if(factoryId.equals(supplierQuoteInfo.getFactoryId())){
							  info = supplierQuoteInfo;
							   supplierQuoteProducts = supplierQuoteProductService.queryBySupplierQuoteId(supplierQuoteInfo.getId());
						  } 
					  }
					  
					  //查询所有工厂报价数据
					  List<SupplierQuoteProduct> supplierQuoteProduct = supplierQuoteProductService.queryBySupplierQuoteId(supplierQuoteInfo.getId());
					  supplierQuoteProductss.add(supplierQuoteProduct);					  
				  }
				  
				  
				  //查询有意向的工厂 （发送过消息未报价）
				  List<FactoryInfo> factorys = factoryInfoService.queryByOrderIdAndQuoteMessage(Integer.parseInt(orderId),quoteInfo.getCustomerId());
				  for (FactoryInfo factoryInfo2 : factorys) {
					  String qualificationNames = "";
					  List<Qualification> qualifications = qualificationService.queryByFactory(factoryInfo2.getFactoryId());
					  for (Qualification qualification : qualifications) {
						  qualificationNames +=qualification.getQualificationName() + "<br>";
					  }
					  factoryInfo2.setQualificationNames(qualificationNames);
				  }
				  
				  
				  
				  //用于大货生产中查询工厂名
				  String factoryName = "";
				  String email = WebCookie.getLoginEmail(request);
				  FactoryInfo factoryInfo = factoryInfoService.selectFactoryInfo(customerId);
				  FactoryUser factoryUser = new FactoryUser();
				  if(factoryInfo != null && "".equals(factoryInfo)){
	                  factoryUser = factoryUserService.selectByLoginEmail(email);				  
					  factoryName = factoryInfo.getFactoryName();
				  }
				  
				  Map<String, Object> map = new HashMap<String, Object>();
				  map.put("quoteInfo", quoteInfo);
				  map.put("products", products);			
				  map.put("isVip", isVip);		
				  map.put("supplierQuoteInfos", supplierQuoteInfos);
				  map.put("factoryName", factoryName);
				  map.put("quoteBigProducts", quoteBigProducts);
				  map.put("quoteBigProductsTab", quoteBigProductsTab);
				  map.put("supplierQuoteInfo", info);
				  map.put("supplierQuoteProducts", supplierQuoteProducts);
				  map.put("supplierQuoteProductss", supplierQuoteProductss);
				  map.put("sysUser", sysUser);
				  map.put("loginId", customerId);
				  map.put("factoryUser", factoryUser);
				  map.put("factorys", factorys);
				  map.put("factoryList", list);
				  
				  return new JsonResult<Map<String,Object>>(map);
			} catch (Exception e) {
				logger.error("==========factoryInquiry   queryInquiryDetail=========",e);
				return new JsonResult<Map<String,Object>>(1,"查询失败");
			}
		  	
	  }
	  
	  
	  
	  
	  /**
	   * 查询采购商发布的询盘详情、工厂报价数据
	   * @Title queryInquiryDetail 
	   * @Description
	   * @param request
	   * @param response
	   * @return
	   * @return JsonResult<Map<String,Object>>
	   */
	  @ResponseBody
      @RequestMapping("/queryInquiryDetailSelf.do")
      public JsonResult<Map<String,Object>> queryInquiryDetailSelf(HttpServletRequest request,HttpServletResponse response){		  
		  	try {
		  	      String loginEmail = "";
				  String realName = "";
				  String userName = "";
		          String pwd = "";
				  String orderId = request.getParameter("orderId").trim();
				  if(StringUtils.isBlank(orderId)){
					return new JsonResult<Map<String,Object>>(1,"未获取到询盘号");
				  }
				  String factoryId = request.getParameter("factoryId");
				  if(StringUtils.isBlank(factoryId)){
						return new JsonResult<Map<String,Object>>(2,"未登录");
				  }
				  realName = request.getParameter("realName");
				  if(StringUtils.isBlank(realName)){
					  return new JsonResult<Map<String,Object>>(2,"未登录");
				  }
				  FactoryInfo factoryInfo = factoryInfoService.selectFactoryInfo(factoryId.trim());
				  FactoryUser factoryUser = factoryUserService.selectByRealName(realName);
				  if(factoryUser == null){
					  return new JsonResult<Map<String,Object>>(2,"请先注册");
				  }
				  //保存cookie token
	    		  String now = String.valueOf(System.currentTimeMillis());
	    		  String token = Md5Util.encoder(now);
	    		  Cookie cookie = new Cookie("token",now+"|"+token);
	    		  cookie.setPath("/");
	    		  cookie.setMaxAge(60*60*24*365);
	    		  response.addCookie(cookie);
	    		  
	    		  pwd = factoryUser.getPwd();
	    		  loginEmail = factoryUser.getEmail();
	    		  Integer state = null;
	    		  String province = "";
	    		  province = factoryInfo.getState();
	    		  if(StringUtils.isNotBlank(factoryInfo.getState())){
	    			  state =  StateEnum.getCode(factoryInfo.getState()).getCode();	 
	        		  Cookie pro2 = new Cookie("FSM_S",state.toString());           
	        		  pro2.setPath("/");
	        		  pro2.setMaxAge(60*60*24*365);
	        		  response.addCookie(pro2); 		 
	    		  }  		    
	    		  userName = factoryUser.getUsername();
	  			  //存放登录数据到Cookie   
	    		  //添加客户类型（0：供应商加采购商  1：供应商  2：采购商）    2017/11/22   polo
	    		  String str1 = 1 +"&"+1 +"&"+pwd+"&"+factoryUser.getPermission()+"&"+factoryUser.getId();
	    		  String str2 = factoryInfo.getIsVip() +"&"+province + "&"+ factoryInfo.getFactoryType();  		 
	    		  Cookie userCookie = new Cookie("factoryInfo",Base64Encode.getBase64(str1));      
	    		  userCookie.setPath("/");
	    		  userCookie.setMaxAge(60*60*24*365);
	    		  response.addCookie(userCookie);    		  
	    		  Cookie userCookie2 = new Cookie("F_INFO",Base64Encode.getBase64(str2));      
	    		  userCookie2.setPath("/");
	    		  userCookie2.setMaxAge(60*60*24*365);
	    		  response.addCookie(userCookie2);    
	    		  //存放客户id
	    		  Cookie userCookie3 = new Cookie("F_ID",Base64Encode.getBase64(factoryId));      
	    		  userCookie3.setPath("/");
	    		  userCookie3.setMaxAge(60*60*24*365);
	    		  response.addCookie(userCookie3);    	
	    		  //存放客户Email
	    		  Cookie userCookie4 = new Cookie("F_M",Base64Encode.getBase64(loginEmail));      
	    		  userCookie4.setPath("/");
	    		  userCookie4.setMaxAge(60*60*24*365);
	    		  response.addCookie(userCookie4); 
	    		  
	    		  
	    		  //客户登录信息存放到session
	    		  HttpSession session = request.getSession();
	    		  session.setAttribute("factoryInfo",Base64Encode.getBase64(str1));
	    		  session.setMaxInactiveInterval(60*60*24*365);
	    		  HttpSession session2 = request.getSession();
	    		  session2.setAttribute("F_INFO",Base64Encode.getBase64(str2));
	    		  session2.setMaxInactiveInterval(60*60*24*365);
	    		  
	    		  HttpSession session3 = request.getSession();
	    		  session3.setAttribute("F_ID",Base64Encode.getBase64(factoryId));
	    		  session3.setMaxInactiveInterval(60*60*24*365);
	    		  HttpSession session4 = request.getSession();
	    		  session4.setAttribute("F_M",Base64Encode.getBase64(loginEmail));
	    		  session4.setMaxInactiveInterval(60*60*24*365);
	    		  
	    		  //客户公司名存放到session
	    		  session.setAttribute("factoryName",factoryInfo.getFactoryName());
	    		  session.setMaxInactiveInterval(60*60*24*365);
	    		  
	    		  //客户名
	    		  Cookie userCookie1 = new Cookie("userName",Base64Encode.getBase64(userName));      
	    		  userCookie1.setPath("/");
	    		  userCookie1.setMaxAge(60*60*24*365);
	    		  response.addCookie(userCookie1);
				  
				 

				  QuoteInfo quoteInfo = quoteInfoService.queryByOrderId(Integer.parseInt(orderId));				 		
				  List<QuoteProduct> products = quoteProductService.queryByOrderId(Integer.parseInt(orderId));	
				  List<List<SupplierQuoteProduct>> supplierQuoteProductss = new ArrayList<List<SupplierQuoteProduct>>();
				  
				  //查询符合工艺条件的工厂添加在邀请报价工厂筛选内
				  List<Map<String, Object>> list = factoryInfoService.queryByMainProcess(quoteInfo.getMainProcess());
				  
	
	              //查询所有工厂报价
				  List<SupplierQuoteInfo> supplierQuoteInfos = supplierQuoteInfoService.queryByOrderId(Integer.parseInt(orderId));	              
				  for (SupplierQuoteInfo supplierQuoteInfo : supplierQuoteInfos) {
					  String qualificationNames = "";
					  List<Qualification> qualifications = qualificationService.queryByFactory(supplierQuoteInfo.getFactoryId());
					  for (Qualification qualification : qualifications) {
						  qualificationNames +=qualification.getQualificationName() + "<br>";
					  }
					  supplierQuoteInfo.setQualificationNames(qualificationNames);
					  
					  
					  //查询所有工厂报价数据
					  List<SupplierQuoteProduct> supplierQuoteProduct = supplierQuoteProductService.queryBySupplierQuoteId(supplierQuoteInfo.getId());
					  supplierQuoteProductss.add(supplierQuoteProduct);					  
					  
				  }
				  
				  //查询有意向的工厂 （发送过消息未报价）
				  List<FactoryInfo> factorys = factoryInfoService.queryByOrderIdAndQuoteMessage(Integer.parseInt(orderId),quoteInfo.getCustomerId());
				  for (FactoryInfo factoryInfo2 : factorys) {
					  String qualificationNames = "";
					  List<Qualification> qualifications = qualificationService.queryByFactory(factoryInfo2.getFactoryId());
					  for (Qualification qualification : qualifications) {
						  qualificationNames +=qualification.getQualificationName() + "<br>";
					  }
					  factoryInfo2.setQualificationNames(qualificationNames);
				  }
				  
				  
				  
				  Map<String, Object> map = new HashMap<String, Object>();
				  map.put("quoteInfo", quoteInfo);
				  map.put("products", products);					
				  map.put("supplierQuoteInfos", supplierQuoteInfos);
				  map.put("supplierQuoteProductss", supplierQuoteProductss);
				  map.put("factoryUser", factoryUser);
				  map.put("factorys", factorys);
				  map.put("factoryList", list);
				  
				  return new JsonResult<Map<String,Object>>(map);
			} catch (Exception e) {
				logger.error("==========factoryInquiry   queryInquiryDetailSelf=========",e);
				return new JsonResult<Map<String,Object>>(1,"查询失败");
			}
		  	
	  }
	  
	  
	  
	  
	  /**
	   * 更新供应商报价状态
	   * @Title updateOrderStatus 
	   * @Description
	   * @param request
	   * @param response
	   * @return
	   * @return JsonResult<String>
	   */
	  @ResponseBody
      @RequestMapping("/updateOrderStatus.do")
      public JsonResult<String> updateOrderStatus(HttpServletRequest request,HttpServletResponse response){
		  try {
			  String orderId = request.getParameter("orderId");
			  if(StringUtils.isBlank(orderId)){
				return new JsonResult<String>(1,"未获取到询盘号");
			  }
			  String factoryId = WebCookie.getFactoryId(request);
			  if(StringUtils.isBlank(factoryId)){
					return new JsonResult<String>(2,"未登录");
			  }
			  String quoteStatus = request.getParameter("quoteStatus");
			  String refuseReasons = null;
			  if(StringUtils.isNotBlank(request.getParameter("refuseReasons"))){
				  refuseReasons = request.getParameter("refuseReasons");
			  }
			  List<SupplierQuoteInfo> supplierQuoteInfos = supplierQuoteInfoService.queryByOrderId(Integer.parseInt(orderId));	
			   for (SupplierQuoteInfo supplierQuoteInfo : supplierQuoteInfos) {
				  supplierQuoteInfo.setQuoteStatus(Integer.parseInt(quoteStatus));
				  supplierQuoteInfo.setOperationTime(DateFormat.format());
				  supplierQuoteInfo.setRefuseReasons(refuseReasons);
		    	}
			  supplierQuoteInfoService.updateOrderStatus(supplierQuoteInfos);
			  return new JsonResult<String>(0,"更新成功");
			  
		} catch (Exception e) {
			logger.error("==========factoryInquiry   updateOrderStatus=========",e);
			return new JsonResult<String>(1,"更新失败");
		}
	  }
	  
	  
	  /**
	   * 查询工厂报价详情信息 、留言信息
	   * @Title queryQuoteDetail 
	   * @Description
	   * @param request
	   * @param response
	   * @return
	   * @return JsonResult<Map<String,Object>>
	   */
	  @ResponseBody
      @RequestMapping("/queryQuoteDetailByCustomerId.do")
      public JsonResult<Map<String,Object>> queryQuoteDetail(HttpServletRequest request,HttpServletResponse response){
		  		    
		  try {
				 String customerId = WebCookie.getFactoryId(request);
				  if(StringUtils.isBlank(customerId)){
					  return  new JsonResult<Map<String,Object>>(2,"未获取到登录信息");
				  }else{
					  String factoryId = request.getParameter("factoryId");
					  Integer orderId = null;
					  if(StringUtils.isNotBlank(request.getParameter("orderId"))){
						 orderId = Integer.parseInt(request.getParameter("orderId"));
					  }
					  QuoteInfo quoteInfo = quoteInfoService.queryByOrderId(orderId);
					  SupplierQuoteInfo supplierQuoteInfo = supplierQuoteInfoService.queryQuoteDetailByFactoryId(orderId, factoryId, customerId);
					  //查询历史报价
					  List<List<SupplierQuoteProduct>> supplierQuoteProducts = new ArrayList<List<SupplierQuoteProduct>>();
					  List<SupplierQuoteProduct> supplierQuoteProduct = supplierQuoteProductService.selectQuoteGroupByQuoteId(factoryId, orderId);
                      for (SupplierQuoteProduct supplierQuoteProduct2 : supplierQuoteProduct) {
                    	  List<SupplierQuoteProduct> list = supplierQuoteProductService.selectQuoteList(supplierQuoteProduct2.getSupplierQuoteId(), factoryId, orderId);
                    	  supplierQuoteProducts.add(list);
					  } 
                      //查询沟通消息
                      List<QuoteMessage> quoteMessages = quoteMessageService.queryMessageByFactoryId(factoryId,orderId);
                      List<QuoteProduct> products = quoteProductService.queryByOrderId(orderId);
                      //查看工厂信息
                      FactoryInfo factoryInfo = factoryInfoService.selectFactoryInfo(factoryId);
                      
                      Map<String,Object> map = new HashMap<String, Object>();
                      map.put("quoteInfo", quoteInfo);
                      map.put("supplierQuoteInfo", supplierQuoteInfo);
                      map.put("supplierQuoteProducts", supplierQuoteProducts);
                      map.put("supplierQuoteProduct", supplierQuoteProduct);
                      map.put("products", products);
                      map.put("quoteMessages", quoteMessages);
                      map.put("factoryInfo", factoryInfo);
                      
					  return new JsonResult<Map<String,Object>>(map);
				  }
			  } catch (Exception e) {
				logger.error("==========factoryInquiry   queryQuoteDetail=========",e);
				return new JsonResult<Map<String,Object>>(1,"查询失败");
			  }
		  
	  }
	  
	  
	  /**
	   * 添加询盘消息(采购商)
	   * @param request
	   * @param response
	   * @return
	   */
	  @ResponseBody
      @RequestMapping("/addQuoteMessage.do")
      public JsonResult<List<QuoteMessage>> addQuoteMessage(HttpServletRequest request,HttpServletResponse response){	
		  
		  try {
			  String customerId = WebCookie.getFactoryId(request);
			  Integer factoryUserId = WebCookie.getFactoryUserId(request);
			  if(StringUtils.isBlank(customerId)){
				  return new JsonResult<List<QuoteMessage>>(2,"未获取到登录信息");
			  }else{
				  Integer orderId = Integer.parseInt(request.getParameter("orderId"));
				  String message = request.getParameter("message");
				  String filePath = request.getParameter("filePath");
				  String factoryId = request.getParameter("factoryId");
				  String fileName = request.getParameter("fileName");
				  QuoteMessage quoteMessage = new QuoteMessage();
				  quoteMessage.setFactoryId(customerId);
				  quoteMessage.setMessageDetails(message);
				  quoteMessage.setOrderId(orderId);
				  quoteMessage.setFilePath(filePath);
				  quoteMessage.setReplyFactoryId(factoryId);
				  quoteMessage.setFileName(fileName);
				  quoteMessage.setSendTime(DateFormat.format());
				  quoteMessage.setFactoryUserId(factoryUserId);
                  QuoteInfo quoteInfo = quoteInfoService.queryByOrderId(orderId);
                //保存到提醒消息表
            	  NoteMessage note = new NoteMessage();  
            	  
            	  SupplierQuoteInfo supplierQuoteInfo = supplierQuoteInfoService.queryByOrderIdAndFactoryId(orderId, factoryId);  
                  if(customerId.equals(quoteInfo.getCustomerId())){
                	 quoteMessage.setReplyStatus(0);  
                	 
               	     String name = WebCookie.getFactoryName(request);
               	     if(StringUtils.isBlank(name)){
               	    	 name = WebCookie.getUserName(request);
               	     }
                	 note.setReceiverId(factoryId);
               		 note.setSendId(customerId);
               		 note.setMessageTitle(orderId+"询盘有新的消息");
               		 note.setMessageDetails("采购商 "+name+"发送了新的回复消息");
               		 note.setOrderId(orderId);
               		 note.setMessageType(MessageTypeEnum.QUOTE_MESSAGE.getCode());
               		 if(supplierQuoteInfo == null || "".equals(supplierQuoteInfo)){
               			note.setUrl(GetServerPathUtil.getServerPath()+"/zh/detail.html?orderId="+orderId);
               		 }else{
                   		note.setUrl(GetServerPathUtil.getServerPath()+"/zh/offer_detail.html?orderId="+orderId);  
               		 }
               		 note.setCreateTime(DateFormat.format());
                  }else{
                	  quoteMessage.setReplyStatus(1); 
                	  
                	  note.setReceiverId(factoryId);
            		  note.setSendId(customerId);
            		  note.setOrderId(orderId);
            		  note.setMessageTitle(orderId+"询盘有新的供应商消息");
            		  note.setMessageDetails("供应商 "+WebCookie.getFactoryName(request)+"发送了新的回复消息");
            		  note.setMessageType(MessageTypeEnum.QUOTE_MESSAGE.getCode());
            		  if(supplierQuoteInfo == null || "".equals(supplierQuoteInfo)){
                          note.setUrl("");  
            		  }else{
            			  note.setUrl(GetServerPathUtil.getServerPath()+"/zh/purchase_supplier.html?factoryId="+factoryId+"&orderId="+orderId);   
            		  }
            		  note.setCreateTime(DateFormat.format());
                	  
                  }
              
                  quoteMessageService.insert(quoteMessage,note);
				  List<QuoteMessage> quoteMessages = quoteMessageService.queryMessageByFactoryId(factoryId,orderId);
				  return new JsonResult<List<QuoteMessage>>(quoteMessages);
			    }
			} catch (Exception e) {
				logger.error("==========factoryInquiry   addQuoteMessage=========",e);
				return new JsonResult<List<QuoteMessage>>(1,"查询失败");
			}
		  
	  }
	  
	  
	  /**
	   * 
	   * @Title updateInquirySelf 
	   * @Description
	   * @param request
	   * @param response
	   * @return
	   * @return JsonResult<String>
	   */
	  @ResponseBody
      @RequestMapping("/updateInquirySelf.do")
	  public JsonResult<String> updateInquirySelf(HttpServletRequest request,HttpServletResponse response){
		  try {
			  String customerId = WebCookie.getFactoryId(request);
			  if(StringUtils.isBlank(customerId)){
				  return new JsonResult<String>(2,"未获取到登录信息");
			  }else{
				  Integer orderId = Integer.parseInt(request.getParameter("orderId"));
				  QuoteInfo quoteInfo = quoteInfoService.queryByOrderId(orderId);
				  if(StringUtils.isNotBlank(request.getParameter("inquiryStatus"))){
					  quoteInfo.setInquiryStatus(Integer.parseInt(request.getParameter("inquiryStatus")));
					  quoteInfo.setUpdateTime(DateFormat.format());
					  
					  //订单状态处理
					  if(OrderStatusSelfEnum.CANCEL.getCode() == Integer.parseInt(request.getParameter("inquiryStatus"))){
						  quoteInfo.setOrderStatus(OrderStatusEnum.CONFIRM.getCode()); 
						  List<SupplierQuoteInfo> supplierQuoteInfos = supplierQuoteInfoService.queryByOrderId(orderId);
						  for (SupplierQuoteInfo supplierQuoteInfo : supplierQuoteInfos) {
							  supplierQuoteInfo.setQuoteStatus(QuoteOrderStatusEnum.REFUSE_ORDER.getCode());
							  supplierQuoteInfo.setRefuseReasons("客户不能给出合理理由");
							  supplierQuoteInfo.setOperationTime(DateFormat.format());
						  }
					  }
					  if(OrderStatusSelfEnum.DECISION.getCode() == Integer.parseInt(request.getParameter("inquiryStatus"))){
						  quoteInfo.setOrderStatus(OrderStatusEnum.CONFIRM.getCode()); 
					  }
					  if(OrderStatusSelfEnum.FOLLOW.getCode() == Integer.parseInt(request.getParameter("inquiryStatus"))){
						  quoteInfo.setOrderStatus(OrderStatusEnum.CONFIRM.getCode()); 
					  }
					  if(OrderStatusSelfEnum.REFUSE.getCode() == Integer.parseInt(request.getParameter("inquiryStatus"))){
						  quoteInfo.setOrderStatus(OrderStatusEnum.CONFIRM.getCode()); 
						  List<SupplierQuoteInfo> supplierQuoteInfos = supplierQuoteInfoService.queryByOrderId(orderId);
						  for (SupplierQuoteInfo supplierQuoteInfo : supplierQuoteInfos) {
							  supplierQuoteInfo.setQuoteStatus(QuoteOrderStatusEnum.REFUSE_ORDER.getCode());
							  supplierQuoteInfo.setRefuseReasons("客户现有的供应商价格更便宜");
							  supplierQuoteInfo.setOperationTime(DateFormat.format());
						  }
					  }
			
				  }
				  if(StringUtils.isNotBlank(request.getParameter("followStatus"))){
					  quoteInfo.setFollowStatus(Integer.parseInt(request.getParameter("followStatus")));
					  quoteInfo.setUpdateTime(DateFormat.format());
					  //如果未拿到订单和订单丢失则项目状态改变
//					  if(FollowStatusSelfEnum.NO_ORDER.getCode() == Integer.parseInt(request.getParameter("followStatus"))){
//						  quoteInfo.setOrderStatus(OrderStatusEnum.CANCEL.getCode()); 
//					  }
					  if(FollowStatusSelfEnum.LOSE_ORDER.getCode() == Integer.parseInt(request.getParameter("followStatus"))){
						  quoteInfo.setOrderStatus(OrderStatusEnum.CANCEL.getCode()); 
					  }
				  }
				  quoteInfoService.updateByPrimaryKey(quoteInfo);
				  return new JsonResult<String>(0,"更新成功");
			  }			  
			} catch (Exception e) {
				logger.error("==========factoryInquiry   updateInquirySelf=========",e);
				return new JsonResult<String>(1,"更新失败");
			}
	  }
	  
	  
	  
	  
	  
	  
	  
	  /**
	   * 增加大货价格、需求
	   * @Title addBigProduct 
	   * @Description
	   * @param request
	   * @param response
	   * @return
	   * @return JsonResult<String>
	   */
	  @ResponseBody
      @RequestMapping("/addBigProduct.do")
	  public JsonResult<String> addBigProduct(HttpServletRequest request,HttpServletResponse response){
		   
		  try {
			String customerId = WebCookie.getFactoryId(request);
			  if(StringUtils.isBlank(customerId)){
				  return new JsonResult<String>(2,"未获取到登录信息");
			  }else{	
				  Integer orderId = Integer.parseInt(request.getParameter("orderId"));
				  String factoryId = request.getParameter("factoryId");
				  QuoteBigProducts quoteBigProducts = new QuoteBigProducts();
				  String paymentTerm = request.getParameter("paymentTerm");
				  String purchaserRequirement = request.getParameter("purchaserRequirement");
				  String priceList = request.getParameter("priceList");
				  quoteBigProducts.setPaymentTerm(paymentTerm); 
				  quoteBigProducts.setCustomerId(customerId);
				  quoteBigProducts.setOrderId(orderId);
				  quoteBigProducts.setCreateTime(DateFormat.format());
				  quoteBigProducts.setFactoryId(factoryId);
				  quoteBigProducts.setIsActive(1);
				  //初始未接受状态
				  quoteBigProducts.setIsSupplierAccept(0);
				  quoteBigProducts.setPurchaserRequirement(purchaserRequirement);
				  
				  quoteBigProductsService.insertSelective(quoteBigProducts, priceList);
				  return new JsonResult<String>(0,"保存成功");
			  }
		} catch (NumberFormatException e) {
			logger.error("==========factoryInquiry   addBigProduct=========",e);
			return new JsonResult<String>(1,"保存失败");
		} catch (Exception e) {
			logger.error("==========factoryInquiry   addBigProduct=========",e);
			return new JsonResult<String>(1,"保存失败");
		}
	  
	  }
	  
	  
	  
	  /**
	   * 查看产品详情
	   * @Title queryBigProductDetails 
	   * @Description 查看产品详情
	   * @param request
	   * @param response
	   * @return
	   * @return JsonResult<Map<String,Object>>
	   */
	  @ResponseBody
	  @RequestMapping("/queryBigProductDetails.do")
      public JsonResult<Map<String, Object>> queryBigProductDetails(HttpServletRequest request,HttpServletResponse response){
		  
		      try {
				  String customerId = WebCookie.getFactoryId(request);
				  if(StringUtils.isBlank(customerId)){
					  return new JsonResult<Map<String, Object>>(2,"未获取到登录信息");
				  }else{
					  Integer orderId = Integer.parseInt(request.getParameter("orderId"));
					  String factoryId = request.getParameter("factoryId");
					  QuoteInfo quoteInfo = quoteInfoService.queryByOrderId(orderId);
					  QuoteBigProducts quoteBigProducts = quoteBigProductsService.selectBySupplierId(factoryId, orderId);
					  List<QuoteBigProductsTab> quoteBigProductsTab = new ArrayList<QuoteBigProductsTab>();
					  if(!(quoteBigProducts == null || "".equals(quoteBigProducts))){
						  quoteBigProductsTab = quoteBigProductsTabService.selectByProductId(quoteBigProducts.getId());  
					  }
					 
					  
			           //查询所有报价工厂
					  List<SupplierQuoteInfo> supplierQuoteInfos = supplierQuoteInfoService.queryByOrderId(orderId);
					   //查询周报
					  List<QuoteWeeklyReport> quoteWeeklyReports = quoteWeeklyReportService.queryByOrderId(orderId);
					  
					  //当前报价工厂
					  SupplierQuoteInfo supplierQuoteInfo = supplierQuoteInfoService.queryByOrderIdAndFactoryId(orderId, factoryId);
					  
					  
				      Map<String, Object> map = new HashMap<String, Object>();		
				      map.put("quoteInfo", quoteInfo);
				      map.put("quoteBigProducts", quoteBigProducts);
				      map.put("quoteBigProductsTab", quoteBigProductsTab);
				      map.put("supplierQuoteInfos", supplierQuoteInfos);
				      map.put("supplierQuoteInfo", supplierQuoteInfo);
				      map.put("quoteWeeklyReports", quoteWeeklyReports);
					  return new JsonResult<Map<String, Object>>(map);  
				  }
			} catch (NumberFormatException e) {
				logger.error("==========factoryInquiry   queryBigProductDetails=========",e);
				return new JsonResult<Map<String, Object>>(1,"查询失败");
			} catch (Exception e) {
				logger.error("==========factoryInquiry   queryBigProductDetails=========",e);
				return new JsonResult<Map<String, Object>>(1,"查询失败");
			}
	  }
	  
	  
	 
	  
	  /**
	   * 更新报价状态    并且拒绝其他工厂
	   * @Title updateQuote
	   * @Description 
	   * @param request
	   * @param response
	   * @return
	   * @return JsonResult<Map<String,Object>>
	   */
	  @ResponseBody
	  @RequestMapping("/updateQuote.do")
      public JsonResult<String> updateQuote(HttpServletRequest request,HttpServletResponse response){
		  	
		  try {
			String customerId = WebCookie.getFactoryId(request);
			  if(StringUtils.isBlank(customerId)){
				  return new JsonResult<String>(2,"未获取到登录信息");
			  }else{
				  Integer orderId = Integer.parseInt(request.getParameter("orderId"));
				  String refuseReasons = request.getParameter("refuseReasons");
				  supplierQuoteInfoService.updateQuote(orderId, refuseReasons);
				  return new JsonResult<String>(0,"保存成功");
			  }
		} catch (NumberFormatException e) {
			logger.error("==========factoryInquiry   updateQuote=========",e);
			return new JsonResult<String>(1,"提交失败");
		} catch (Exception e) {
			logger.error("==========factoryInquiry   updateQuote=========",e);
			return new JsonResult<String>(1,"提交失败");
		}		  
	  }
	  
	  
	  
	  /**
	   * 更新大货生产状态  （主要用于是否付款）
	   * @Title updateBigProduct
	   * @Description 
	   * @param request
	   * @param response
	   * @return
	   * @return JsonResult<String>
	   */
	  @ResponseBody
	  @RequestMapping("/updateBigProduct.do")
      public JsonResult<String> updateBigProduct(HttpServletRequest request,HttpServletResponse response){
		  	
		  try {
			  String customerId = WebCookie.getFactoryId(request);
			  if(StringUtils.isBlank(customerId)){
				  return new JsonResult<String>(2,"未获取到登录信息");
			  }else{
				  Integer orderId = Integer.parseInt(request.getParameter("orderId"));
				  String factoryId = request.getParameter("factoryId");
				  Integer status = Integer.parseInt(request.getParameter("status"));
				  QuoteBigProducts quoteBigProducts = quoteBigProductsService.selectBySupplierId(factoryId, orderId);
				  quoteBigProducts.setIsSupplierAccept(status);
				  quoteBigProducts.setUpdateTime(DateFormat.format());
				  quoteBigProductsService.updateByPrimaryKeySelective(request,quoteBigProducts);
				  return new JsonResult<String>(0,"提交成功");
			  }
		} catch (NumberFormatException e) {
			logger.error("==========factoryInquiry   updateBigProduct=========",e);
			return new JsonResult<String>(1,"提交失败");
		} catch (Exception e) {
			logger.error("==========factoryInquiry   updateBigProduct=========",e);
			return new JsonResult<String>(1,"提交失败");
		}		  
	  }
	  
	  
	  
	  
	  
	  /**
	   * 查询每周周报图片
	   * @Title queryWeeklyPhotos 
	   * @Description
	   * @param request
	   * @param response
	   * @return
	   * @return JsonResult<String>
	   */
	  @ResponseBody
	  @RequestMapping("/queryWeeklyPhotos.do")
      public JsonResult<Map<String,Object>> queryWeeklyPhotos(HttpServletRequest request,HttpServletResponse response){
		  
		  try {
			String customerId = WebCookie.getFactoryId(request);
			  if(StringUtils.isBlank(customerId)){
				  return new JsonResult<Map<String,Object>>(2,"未获取到登录信息");
			  }else{
				  Integer orderId = Integer.parseInt(request.getParameter("orderId"));
				  String uploadDate = request.getParameter("uploadDate");
				  List<QuoteWeeklyReport> photos = quoteWeeklyReportService.queryByOrderIdAndDate(orderId, uploadDate);
				  Map<String,Object> map = new HashMap<String, Object>();
				  map.put("photos", photos);
				  return new JsonResult<Map<String,Object>>(map);
			  }
			} catch (NumberFormatException e) {
				logger.error("==========factoryInquiry   queryWeeklyPhotos=========",e);
				return new JsonResult<Map<String,Object>>(1,"未获取到询盘号");
			} catch (Exception e) {
				logger.error("==========factoryInquiry   queryWeeklyPhotos=========",e);
				return new JsonResult<Map<String,Object>>(1,"获取失败");
			}
	  }
	  
	  
	  /**
	   * 查询每张周报的备注
	   * @Title queryRemark 
	   * @Description
	   * @param request
	   * @param response
	   * @return
	   * @return JsonResult<String>
	   */
	  @ResponseBody
	  @RequestMapping("/queryRemark.do")
      public JsonResult<Map<String,Object>> queryRemark(HttpServletRequest request,HttpServletResponse response){
		  
		  try {
		    String customerId = WebCookie.getFactoryId(request);
			  if(StringUtils.isBlank(customerId)){
				  return new JsonResult<Map<String,Object>>(2,"未获取到登录信息");
			  }else{
				  Integer id = Integer.parseInt(request.getParameter("id"));
				  QuoteWeeklyReport quoteWeeklyReport = quoteWeeklyReportService.selectByPrimaryKey(id);
				  Map<String,Object> map = new HashMap<String, Object>();
				  map.put("remark", quoteWeeklyReport.getRemark());
				  map.put("uploadDate", quoteWeeklyReport.getUploadDate());
				  return new JsonResult<Map<String,Object>>(map);
			  }
			} catch (NumberFormatException e) {
				logger.error("==========factoryInquiry   queryRemark=========",e);
				return new JsonResult<Map<String,Object>>(1,"未获取到询盘号");
			} catch (Exception e) {
				logger.error("==========factoryInquiry   queryRemark=========",e);
				return new JsonResult<Map<String,Object>>(1,"获取失败");
			}
	  }
	  
	  
	  
	  
	  /**
	   * 
	   * @Title updateQuote
	   * @Description 更新第三方检测
	   * @param request
	   * @param response
	   * @return
	   * @return JsonResult<Map<String,Object>>
	   */
	  @ResponseBody
	  @RequestMapping("/updateQuoteInspection.do")
      public JsonResult<String> updateQuoteInspection(HttpServletRequest request,HttpServletResponse response){
		  
		  try {
			String customerId = WebCookie.getFactoryId(request);
			  if(StringUtils.isBlank(customerId)){
				  return new JsonResult<String>(2,"未获取到登录信息");
			  }else{
				  Integer orderId = Integer.parseInt(request.getParameter("orderId"));
				  QuoteInfo quoteInfo = quoteInfoService.queryByOrderIdAndFactoryId(orderId, customerId);
				  Integer inspectionStatus = Integer.parseInt(request.getParameter("inspectionStatus"));
				  if(quoteInfo != null){
					  quoteInfo.setInspectionStatus(inspectionStatus);
					  quoteInfo.setInspectionUploadTime(DateFormat.format());
					  quoteInfoService.updateByPrimaryKey(quoteInfo);
				  }
				  return new JsonResult<String>(0,"更新成功");
			  }
		} catch (NumberFormatException e) {
			logger.error("==========factoryInquiry   updateQuoteInspection=========",e);
			return new JsonResult<String>(1,"未获取到询盘号");
		} catch (Exception e) {
			logger.error("==========factoryInquiry   updateQuoteInspection=========",e);
			return new JsonResult<String>(1,"更新失败");
		}
	  }
	  
}
