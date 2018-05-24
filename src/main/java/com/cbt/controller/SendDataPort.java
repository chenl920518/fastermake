package com.cbt.controller;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cbt.entity.FactoryInfo;
import com.cbt.entity.SupplierQuoteInfo;
import com.cbt.entity.SupplierQuoteProduct;
import com.cbt.service.FactoryInfoService;
import com.cbt.service.SupplierQuoteInfoService;
import com.cbt.service.SupplierQuoteProductService;
import com.cbt.util.Client;
import com.cbt.util.GetServerPathUtil;
import com.cbt.util.JsonUtil;

/**
 * 提供给其他系统的数据同步接口
 * @author polo
 * 2017年11月8日
 */
@Controller
@RequestMapping("/sendPort")
public class SendDataPort {
	

      private Logger logger = LoggerFactory.getLogger(this.getClass());	

      private int count = 0;
      private int count1 = 0;

	  @Autowired
	  private SupplierQuoteInfoService supplierQuoteInfoService;     
	  @Autowired
	  private SupplierQuoteProductService supplierQuoteProductService;
	  @Autowired
	  private FactoryInfoService factoryInfoService;
	  
	  /**
	   * 发送报价单消息到后台报价系统
	   * @author polo
	   * 2017年11月8日
	   *
	   */
	  @ResponseBody
	  @RequestMapping("/sendQuoteInfo.do")	 
	  public String sendQuoteInfo(HttpServletRequest request,HttpServletResponse response){
		    
		  try {	  
		       String quoteInfoId = request.getParameter("quoteInfoId");
		       if(StringUtils.isBlank(quoteInfoId)){
		    	   logger.info("《《《《报价为空》》》》"); 
		    	   return "NO";
		       }else{
		    	   Integer id = Integer.parseInt(quoteInfoId);
		    	   SupplierQuoteInfo quoteInfo = supplierQuoteInfoService.selectByPrimaryKey(id);
		    	   List<SupplierQuoteProduct> supplierQuoteProducts = supplierQuoteProductService.queryBySupplierQuoteId(id);
		    	      Map<String,Object> map = new HashMap<String, Object>();
		              map.put("csgOrderId",quoteInfo.getCsgOrderId() );
		              map.put("factoryId",quoteInfo.getFactoryId() );
		              map.put("factoryName",URLEncoder.encode(quoteInfo.getFactoryName(),"utf-8"));
		              map.put("supplierQuoteProducts", supplierQuoteProducts);
		              String json = JsonUtil.map2json(map);
//		              System.out.println(json);
		              if(StringUtils.isNotBlank(quoteInfo.getCsgOrderId())){
		                Client.sendData(GetServerPathUtil.getInnerServerPath()+"/New-Quotation/helpServlet?action=quotationInformation&className=RapidManufacturingEnquiryServlet", json);   
//		            	  Client.sendData("http://192.168.1.91:8080/New-Quotation/helpServlet?action=quotationInformation&className=RapidManufacturingEnquiryServlet", json);   
		              }
		             
		       }
	           
		       return "YES"; 
		  
		       } catch (Exception e) {
			            logger.error("《《《《发送消息失败》》》》",e);
			                count = count+1;
			                if(count < 2){
			                	sendQuoteInfo(request,response);
			                }
			            return "NO";
			   }
			   
	  }
	  
  

	  /**
	   * 同步工厂信息到内部报价
	   * @author polo
	   * 2017年12月20日
	   *
	   */
	  @ResponseBody
	  @RequestMapping("/syncFactoryInfo.do")	 
	  public String syncFactoryInfo(HttpServletRequest request,HttpServletResponse response){
		    
		  try {	  
		       String factoryId = request.getParameter("factoryId");
		       if(StringUtils.isBlank(factoryId)){
		    	   logger.info("工厂ID为空"); 
		    	   return "NO";
		       }else{

		    	      FactoryInfo factoryInfo = factoryInfoService.selectFactoryInfo(factoryId);
		    	      Map<String,Object> map = new HashMap<String, Object>();
		              map.put("factoryId",factoryInfo.getFactoryId());
		              map.put("factoryName",URLEncoder.encode(factoryInfo.getFactoryName(),"UTF-8"));
		              map.put("email",URLEncoder.encode(factoryInfo.getEmail(),"UTF-8"));
		              map.put("tel", factoryInfo.getTel());
		              String json = JsonUtil.map2json(map);
//		              System.out.println(json);
		              Client.sendData(GetServerPathUtil.getInnerServerPath()+"/New-Quotation/helpServlet?action=addFactoryInformation1&className=FactoryServlet", json);   		             
		       }	           
		       return "YES"; 		  
		       } catch (Exception e) {
			            logger.error("《《《《发送消息失败》》》》",e);
			                count1 = count1+1;
			                if(count1 < 2){
			                	sendQuoteInfo(request,response);
			                }
			            return "NO";
			   }
			   
	  }
	  
	  
	  
	  
	  
	  
	  
}
