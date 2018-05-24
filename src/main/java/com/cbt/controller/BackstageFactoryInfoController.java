package com.cbt.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cbt.entity.FactoryInfo;
import com.cbt.entity.NoteMessage;
import com.cbt.entity.QuoteBigProducts;
import com.cbt.entity.QuoteInfo;
import com.cbt.entity.QuoteProduct;
import com.cbt.entity.SysUser;
import com.cbt.enums.MessageTypeEnum;
import com.cbt.enums.OrderStatusEnum;
import com.cbt.service.FactoryInfoService;
import com.cbt.service.QuoteBigProductsService;
import com.cbt.service.QuoteInfoService;
import com.cbt.service.QuoteProductService;
import com.cbt.service.SupplierQuoteInfoService;
import com.cbt.util.DateFormat;
import com.cbt.util.GetServerPathUtil;
import com.cbt.util.OperationFileUtil;
import com.cbt.util.UploadAndDownloadPathUtil;


/**
 * 后台用户管理
 * @ClassName BackstageFactoryInfoController 
 * @Description
 * @author zj
 * @date 2017年11月25日 下午3:00:50
 */
@Controller
@RequestMapping("/backstage")
public class BackstageFactoryInfoController {
	@Autowired
	private FactoryInfoService factoryInfoService;
	
	@Autowired
	private QuoteProductService quoteProductService;
	
	@Autowired
	private QuoteInfoService quoteInfoService;
	
	@Autowired
	private SupplierQuoteInfoService supplierQuoteInfoService;
	
	@Autowired
	private QuoteBigProductsService quoteBigProductsService;
	
	
	/**
	 * 查询后台用户
	 * @Title queryFactoryList 
	 * @Description
	 * @param request
	 * @param response
	 * @return
	 * @return JsonResult<Map<String,Object>>
	 */
	@RequestMapping("/queryFactoryList.do")
	@ResponseBody
	public JsonResult<List<FactoryInfo>> queryFactoryList(HttpServletRequest request,HttpServletResponse response){		
		List<FactoryInfo> factoryList = factoryInfoService.queryFactoryList();
		return new JsonResult<List<FactoryInfo>>(factoryList);		
	}
	
	
	@RequestMapping("/queryAll.do")
	@ResponseBody
	public JsonResult<List<QuoteInfo>> queryAll(HttpServletRequest request,HttpServletResponse response){	
		
    	//获取后台登录session
		HttpSession session = request.getSession();
    	SysUser sysUser = (SysUser) session.getAttribute("_SESSION_USER");
    	if(sysUser == null){
    		return new JsonResult<List<QuoteInfo>>(2,"未登录管理账号");	
    	}
		List<QuoteInfo> quoteInfos = quoteInfoService.queryAll(null);
		for (QuoteInfo quoteInfo : quoteInfos) {
			QuoteBigProducts quoteBigProducts = quoteBigProductsService.selectByOrderId(quoteInfo.getCustomerId(), quoteInfo.getOrderId());
			if(!(quoteBigProducts == null || "".equals(quoteBigProducts))){
				if(quoteBigProducts.getIsSupplierAccept() == 3){
					quoteInfo.setHasNewItem(1);
				}
			}
		}
		return new JsonResult<List<QuoteInfo>>(quoteInfos);		
	}
	
	
	
	/**
	 * 查询工厂详情
	 * @Title queryFactoryList 
	 * @Description
	 * @param request
	 * @param response
	 * @return
	 * @return JsonResult<List<FactoryInfo>>
	 */
	@ResponseBody
	@RequestMapping("/queryFactoryById.do")
	public JsonResult<Map<String,Object>> queryFactoryById(HttpServletRequest request,HttpServletResponse response){
		//获取后台登录session
		HttpSession session = request.getSession();
    	SysUser sysUser = (SysUser) session.getAttribute("_SESSION_USER");
    	if(sysUser == null){
    		return new JsonResult<Map<String,Object>>(2,"未登录管理账号");	
    	}
		String factoryId = request.getParameter("factoryId");
		FactoryInfo factoryInfo = factoryInfoService.selectFactoryInfo(factoryId);
		List<QuoteProduct> quoteProduct = quoteProductService.queryByFactoryIdGroupByOrderIdAdmin(null, null, factoryId, OrderStatusEnum.CONFIRM.getCode());
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("factoryInfo", factoryInfo);
		map.put("quoteProduct", quoteProduct);
		return new JsonResult<Map<String,Object>>(map);
	}
	
	/**
	 * 设置封号、解封、VIP、取消VIP方法
	 * @Title queryFactoryById 
	 * @Description
	 * @param request
	 * @param response
	 * @return
	 * @return JsonResult<FactoryInfo>
	 */
	@ResponseBody
	@RequestMapping("/updateFactory.do")
	public JsonResult<String> updateFactory(HttpServletRequest request,HttpServletResponse response){
		try {
			
			//获取后台登录session
			HttpSession session = request.getSession();
	    	SysUser sysUser = (SysUser) session.getAttribute("_SESSION_USER");
	    	if(sysUser == null){
	    		return new JsonResult<String>(2,"未登录管理账号");	
	    	}
			String factoryId = request.getParameter("factoryId");
			FactoryInfo factoryInfo = factoryInfoService.selectFactoryInfo(factoryId);
			if(factoryInfo == null || "".equals(factoryInfo)){
				return new JsonResult<String>(1,"未获取到客户信息");
			}
			if(StringUtils.isNotBlank(request.getParameter("isVip"))){
				Integer isVip = Integer.parseInt(request.getParameter("isVip"));

				//如果是设置封号，录入封号时间
			    if(isVip == 105){
			    	factoryInfo.setCloseTime(DateFormat.format());
			    }else if(isVip == 101){
			    	int days = Integer.parseInt(request.getParameter("days"));
			    	if(factoryInfo.getIsVip() == 101 && factoryInfo.getVipExpiresTime() != null){
			    		factoryInfo.setVipExpiresTime(DateFormat.addDays(factoryInfo.getVipExpiresTime(), days));	
			    	}else{
			    		factoryInfo.setVipExpiresTime(DateFormat.addDays(DateFormat.format(),days));
			    	}
			    }else{
			    	if(factoryInfo.getIsVip() == 101){
			        	factoryInfo.setVipExpiresTime(DateFormat.format());	
			    	}
			    }           
				factoryInfo.setIsVip(isVip);
			}
			if(StringUtils.isNotBlank(request.getParameter("systemRemark"))){
				factoryInfo.setSystemRemark(request.getParameter("systemRemark"));
			}
			if(StringUtils.isNotBlank(request.getParameter("factoryStatus"))){
				factoryInfo.setFactoryStatus(Integer.parseInt(request.getParameter("factoryStatus")));
				if(StringUtils.isNotBlank(request.getParameter("inspectionNote"))){
				 factoryInfo.setInspectionNote(request.getParameter("inspectionNote"));	
				}
			}
			
			factoryInfoService.updateByPrimaryKeySelective(factoryInfo);
			return new JsonResult<String>(0,"更新成功",factoryInfo.getVipExpiresTime());		
		} catch (NumberFormatException e) {			
			e.printStackTrace();
			return new JsonResult<String>(1,"未获取到客户信息");
		} catch (ParseException e) {
			e.printStackTrace();
			return new JsonResult<String>(1,"格式转换错误");
		}
	}
	
	
	
	/**
 	 * 上传验厂文档
 	 * @param request
 	 * @param response
 	 * @return
 	 */
    @ResponseBody
 	@RequestMapping("/uploadInspectionDocument.do")
 	public JsonResult<String> uploadAttachmentAndChangeName(HttpServletRequest request,HttpServletResponse response){
 		
 	 try {
 		 
		//获取后台登录session
		HttpSession session = request.getSession();
    	SysUser sysUser = (SysUser) session.getAttribute("_SESSION_USER");
    	if(sysUser == null){
    		return new JsonResult<String>(2,"未登录管理账号");	
    	}
		 String factoryId = request.getParameter("factoryId");
		 FactoryInfo factoryInfo = factoryInfoService.selectFactoryInfo(factoryId);
		 if(factoryInfo == null || "".equals(factoryInfo)){
			return new JsonResult<String>(1,"未获取到客户信息");
		 }
 		 String drawingName = request.getParameter("selectName");
 		 String path = UploadAndDownloadPathUtil.getFactoryDocument() +  factoryId + File.separator;
		 File file = new File(path);
		 if  (!file.exists()  && !file .isDirectory())      
		 {         
			 file .mkdir();     
		 }  	    	  
 		Map<String, String> multiFileUpload = OperationFileUtil.multiFileUpload1(request, path);
 		String filePath = "";
 		if(!(multiFileUpload == null || multiFileUpload.size() == 0)){
 			filePath = multiFileUpload.get(drawingName);
 		} 
 		factoryInfo.setInspectionDocumentPath(filePath);
 		factoryInfoService.updateByPrimaryKeySelective(factoryInfo);
 		return new JsonResult<String>(0,"success");	
	 	} catch (IllegalStateException e) {
	 		e.printStackTrace();
	 		return new JsonResult<String>(1,"failed");	
	 	} catch (IOException e) {
	 		e.printStackTrace();
	 		return new JsonResult<String>(1,"failed");	
	 	} 	 			
	 	}

	
    /**
     * 根据询盘号查询订单
     * @Title queryInquiryByOrderId 
     * @Description
     * @param request
     * @param response
     * @return
     * @return JsonResult<List<QuoteInfo>>
     */
    @RequestMapping("/queryInquiryByOrderId.do")
	@ResponseBody
	public JsonResult<Map<String,Object>> queryInquiryByOrderId(HttpServletRequest request,HttpServletResponse response){		
      
    	String orderId = request.getParameter("orderId");
    	if(StringUtils.isBlank(orderId)){
    		return new JsonResult<Map<String,Object>>(1,"未获取到询盘号");	
    	}
    	//获取后台登录session
		HttpSession session = request.getSession();
    	SysUser sysUser = (SysUser) session.getAttribute("_SESSION_USER");
    	if(sysUser == null){
    		return new JsonResult<Map<String,Object>>(2,"未登录管理账号");	
    	}
    	Map<String,Object> map = new HashMap<String, Object>();
    	QuoteInfo quoteInfo = quoteInfoService.queryByOrderId(Integer.parseInt(orderId));   
    	QuoteBigProducts quoteBigProducts = quoteBigProductsService.selectByOrderId(quoteInfo.getCustomerId(), Integer.parseInt(orderId));
    	map.put("quoteInfo", quoteInfo);
    	map.put("quoteBigProducts", quoteBigProducts);
		return new JsonResult<Map<String,Object>>(map);		
	}
	
	
    
    /**
     * 根据询盘号更新
     * @Title updateQuote 
     * @Description
     * @param request
     * @param response
     * @return
     * @return JsonResult<String>
     */
    @RequestMapping("/updateQuote.do")
	@ResponseBody
	public JsonResult<String> updateQuote(HttpServletRequest request,HttpServletResponse response){	
    	
		//获取后台登录session
		HttpSession session = request.getSession();
    	SysUser sysUser = (SysUser) session.getAttribute("_SESSION_USER");
    	if(sysUser == null){
    		return new JsonResult<String>(2,"未登录管理账号");	
    	}
    	String orderId = request.getParameter("orderId");
    	if(StringUtils.isBlank(orderId)){
    		return new JsonResult<String>(1,"未获取到询盘号");	
    	}
    	QuoteInfo quoteInfo = quoteInfoService.queryByOrderId(Integer.parseInt(orderId));    	
    	if(quoteInfo.getOrderStatus() == 0 && quoteInfo.getMainCategory() == 0){
    		quoteInfo.setMainProcess(request.getParameter("mainProcess"));
    	}
    	if(StringUtils.isNotBlank(request.getParameter("orderStatus"))){
    		quoteInfo.setOrderStatus(Integer.parseInt(request.getParameter("orderStatus")));
    		if("7".equals(request.getParameter("orderStatus"))){
        		quoteInfo.setNoPassReasons(request.getParameter("noPassReasons"));	
    		}
    		quoteInfo.setPublishDate(DateFormat.format());
    	}
    	
    	  NoteMessage note = new NoteMessage();    
 		  note.setReceiverId(quoteInfo.getCustomerId());
 		  note.setSendId("");
 		  if(quoteInfo.getOrderStatus() == OrderStatusEnum.NOPASS.getCode()){
 			  note.setMessageTitle(quoteInfo.getOrderId()+"询盘未通过系统审核");
 	 		  note.setMessageDetails(quoteInfo.getOrderId()+"询盘未通过系统审核，请按要求修改。");  
 		  }else{
 			  note.setMessageTitle(quoteInfo.getOrderId()+"询盘已通过系统审核");
	 		  note.setMessageDetails(quoteInfo.getOrderId()+"询盘已通过系统审核");  
 		  }
 		  note.setMessageType(MessageTypeEnum.QUOTE_INFO.getCode());
 		  note.setOrderId(Integer.parseInt(orderId));
 		  note.setUrl(GetServerPathUtil.getServerPath()+"/zh/purchase_detail.html?orderId="+quoteInfo.getOrderId());   
 		  note.setCreateTime(DateFormat.format());

    	
    	quoteInfoService.updateByPrimaryKey(quoteInfo,note);
    	return new JsonResult<String>(0,"更新成功");	
    }
    
    
    
    
}
