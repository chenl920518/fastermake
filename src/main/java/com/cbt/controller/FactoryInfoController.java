package com.cbt.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cbt.entity.Equipment;
import com.cbt.entity.FactoryInfo;
import com.cbt.entity.FactoryUser;
import com.cbt.entity.Qualification;
import com.cbt.entity.QuoteProduct;
import com.cbt.enums.CustomerTypeEnum;
import com.cbt.enums.OrderStatusEnum;
import com.cbt.service.EquipmentService;
import com.cbt.service.FactoryInfoService;
import com.cbt.service.FactoryUserService;
import com.cbt.service.QualificationService;
import com.cbt.service.QuoteInfoService;
import com.cbt.service.QuoteProductService;
import com.cbt.util.WebCookie;
import com.cbt.util.sort.InquirySortProcess;
import com.cbt.util.sort.InquirySortState;

@Controller
@RequestMapping("/factoryInfo")
public class FactoryInfoController {

	@Autowired
	private FactoryInfoService factoryInfoService;
	
	@Autowired
	private FactoryUserService factoryUserService;

	@Autowired 
	private EquipmentService equipmentService;
	
	@Autowired
	private QualificationService qualificationService;
	
	@Autowired
	private QuoteProductService quoteProductService;  
	@Autowired
	private QuoteInfoService quoteInfoService;  
	/**
	 * 完善企业资料,更新企业资料
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/updateFactoryInfo.do")
	@ResponseBody
	public JsonResult<String> updateFactoryInfo(HttpServletRequest request,HttpServletResponse response){	
		  String factoryId = WebCookie.getFactoryId(request);
		  try {
			  if(StringUtils.isBlank(factoryId)){
				  return new JsonResult<String>(2,"未获取到登录信息");
			  }else{
				 FactoryInfo factoryInfo=factoryInfoService.selectFactoryInfo(factoryId); 
				 //FactoryInfo factoryInfo=new FactoryInfo();
				 String factoryName=request.getParameter("factoryName");
			     String tel=request.getParameter("tel");
			     String mobile=request.getParameter("mobile");
			     String country=request.getParameter("country"); //国家
				 String state=request.getParameter("province"); //省份
				 String city=request.getParameter("city");      //城市
				 String district=request.getParameter("district");//市区
				 String detailsAddress=request.getParameter("detailsAddress");//详细地址
				 String factoryAcreage=request.getParameter("factoryAcreage");//工厂面积
				 String staffNumber=request.getParameter("staffNumber");//人员数量
				 String website=request.getParameter("website");
				 String aliWebsite=request.getParameter("aliWebsite");
				 String type=request.getParameter("type");
				 String factoryValue=request.getParameter("factoryValue");
				 String establishmentYear=request.getParameter("establishmentYear");
				 String factoryInfoText=request.getParameter("factoryInfo");
				 
//				 String factoryLicenseFileS=request.getParameter("factoryLicenseFileS");
//				 String factoryGateFileS=request.getParameter("factoryGateFileS");
				 String factoryLogoFileS=request.getParameter("factoryLogoFileS");
				  //factoryInfo.setFactoryId(factoryId);
				 factoryInfo.setFactoryName(factoryName);
				 factoryInfo.setTel(tel);
				 factoryInfo.setMobile(mobile);
				 factoryInfo.setCountry(country);
				 factoryInfo.setCity(city);
				 factoryInfo.setState(state);
				 factoryInfo.setFactoryAcreage(factoryAcreage);
				 factoryInfo.setStaffNumber(staffNumber);
				 factoryInfo.setWebsite(website);
				 factoryInfo.setAliWebsite(aliWebsite);
				 factoryInfo.setDetailsAddress(detailsAddress);
				 factoryInfo.setDistrict(district);
				 factoryInfo.setType(type);
				 factoryInfo.setFactoryValue(factoryValue);
				 factoryInfo.setFactoryInfo(factoryInfoText);
				 factoryInfo.setEstablishmentYear(establishmentYear);
				 if(StringUtils.isNotBlank("factoryLogoFileS")){
					 factoryInfo.setFactoryLogo(factoryLogoFileS);
				 }
				 factoryInfoService.updateByPrimaryKeySelective(factoryInfo);
				 return new JsonResult<String>(0,"更新成功");
			  }
		} catch (Exception e) {
			e.printStackTrace();
			 return new JsonResult<String>(1,"更新失败");
		}
		
	}
	/**
	 * 查询企业基本信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/selectFactoryInfo.do")
	@ResponseBody
	public JsonResult<Map<String,Object>> selectFactoryInfo(HttpServletRequest request,HttpServletResponse response){
		String factoryId = WebCookie.getFactoryId(request);
		String loginEmail = WebCookie.getLoginEmail(request);
		try {
			if(StringUtils.isBlank(factoryId)){
			   return new JsonResult<Map<String,Object>>(2,"未获取到登录信息");
			}else{
			   FactoryInfo factoryInfo=new FactoryInfo();
			   factoryInfo=factoryInfoService.selectFactoryInfo(factoryId);
			   FactoryUser factoryUser = factoryUserService.selectByLoginEmail(loginEmail);
			   String[] mainProcess;//处理工艺强项
			   if(StringUtils.isNotBlank(factoryInfo.getMainProcess())){
				    mainProcess=factoryInfo.getMainProcess().split(","); 
				    factoryInfo.setMainProcessS(mainProcess);
			   }
			   List<Equipment> equipmentList=equipmentService.selectFactoryEquipment(factoryId);//处理设备清单
			   factoryInfo.setEquipmentList(equipmentList);
			   
			   String[] cooperativeEnterprise;//处理合作企业
			   if(StringUtils.isNotBlank(factoryInfo.getCooperativeEnterprise())){
				   cooperativeEnterprise=factoryInfo.getCooperativeEnterprise().split(","); 
				    factoryInfo.setCooperativeEnterpriseS(cooperativeEnterprise);
			   }
			   Map<String,Object> map=new HashMap<String, Object>();
			   map.put("factoryInfo", factoryInfo);
			   map.put("factoryUser", factoryUser);
			   return new JsonResult<Map<String,Object>>(0,"查询成功",map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Map<String,Object>>(1,"查询失败");
		}	
   	 }
     /**
      * 更新企业账号信息
      * @param request
      * @param response
      * @return
      */
	 @ResponseBody
     @RequestMapping("/updateFactoryAccount.do")
     public JsonResult<String> updateFactoryAccount(HttpServletRequest request,HttpServletResponse response){
		 String factoryId = WebCookie.getFactoryId(request);
		 try {
			if(StringUtils.isBlank(factoryId)){
				return new JsonResult<String>(2,"未获取到登录信息");
			 }else{
				String loginEmail=request.getParameter("loginEmail");
				String curPass=request.getParameter("curPass");
				String newPass=request.getParameter("newPass");
				
				FactoryUser factoryUser = factoryUserService.selectByLoginEmail(loginEmail);
				if(!curPass.equals(factoryUser.getPwd())){
					return new JsonResult<String>(1,"原密码不正确");
				}else{
					factoryUser.setPwd(newPass);
					factoryUserService.updateByPrimaryKeySelective(factoryUser);
				    return new JsonResult<String>(0,"密码修改成功");
				}
			 }
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<String>(1,"密码修改失败");
		}
     }
	 
	 /**
      * 更新企业联系人信息
      * @param request
      * @param response
      * @return
      */
	 @ResponseBody
     @RequestMapping("/updateFactoryContactInfo.do")
     public JsonResult<String> updateFactoryContactInfo(HttpServletRequest request,HttpServletResponse response){
		 String factoryId = WebCookie.getFactoryId(request);
		 try {
			if(StringUtils.isBlank(factoryId)){
				return new JsonResult<String>(2,"未获取到登录信息");
			 }else{
                 FactoryInfo factoryInfo=new FactoryInfo();
                 String position=request.getParameter("position");
                 String userName=request.getParameter("userName");
                 String tel=request.getParameter("tel");
                 String mobile=request.getParameter("mobile");
                 String fax=request.getParameter("fax");
                 factoryInfo.setFactoryId(factoryId);
                 factoryInfo.setUsername(userName);
			     factoryInfo.setPosition(position);
				 factoryInfo.setTel(tel);
				 factoryInfo.setMobile(mobile);
				 factoryInfo.setFax(fax);
				 factoryInfoService.updateByPrimaryKeySelective(factoryInfo);
				 return new JsonResult<String>(0,"更新信息成功");
			 }
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<String>(1,"更新账号信息失败");
		}
     }
	 
	 /**
      * 添加公司的优势信息
      * @param request
      * @param response
      * @return
      */
	 @ResponseBody
     @RequestMapping("/updateFactoryAdvantage.do")
     public JsonResult<String> updateFactoryAdvantage(HttpServletRequest request,HttpServletResponse response){
		 String factoryId = WebCookie.getFactoryId(request);
		 try {
			if(StringUtils.isBlank(factoryId)){
				return new JsonResult<String>(2,"未获取到登录信息");
			 }else{
				 String technologicalAdvantage=request.getParameter("technologicalAdvantage");//公司技术优势
				 String dominantMaterialModel=request.getParameter("dominantMaterialModel");//优势材料型号
				 String dominantMaterialSize=request.getParameter("dominantMaterialSize");//优势材料大小
				 String mainProcess=request.getParameter("mainProcess");//工艺强项
				 String siteSize=request.getParameter("siteSize");
				 FactoryInfo factoryInfo=new FactoryInfo();
				 factoryInfo.setFactoryId(factoryId);
				 factoryInfo.setTechnologicalAdvantage(technologicalAdvantage);
				 factoryInfo.setDominantMaterialModel(dominantMaterialModel);
				 factoryInfo.setDominantMaterialSize(dominantMaterialSize);
				 factoryInfo.setMainProcess(mainProcess);
				 factoryInfo.setSiteSize(siteSize);
				 factoryInfoService.updateByPrimaryKeySelective(factoryInfo);
				 return new JsonResult<String>(0,"操作成功");
			 }
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<String>(1,"操作失败");
		}
     }
	 
	 /**
      * 更新公司的接受询盘条件
      * @param request
      * @param response
      * @return
      */
	 @ResponseBody
     @RequestMapping("/updateAcceptCondition.do")
     public JsonResult<String> updateAcceptCondition(HttpServletRequest request,HttpServletResponse response){
		 String factoryId = WebCookie.getFactoryId(request);
		 try {
			if(StringUtils.isBlank(factoryId)){
				return new JsonResult<String>(2,"未获取到登录信息");
			 }else{
				 String acceptCondition=request.getParameter("acceptCondition");
				 String acceptMoney=request.getParameter("acceptMoney");
				 
				 FactoryInfo factoryInfo=new FactoryInfo();
				 factoryInfo.setFactoryId(factoryId);
				 factoryInfo.setAcceptCondition(acceptCondition);
				 factoryInfo.setAcceptMoney(acceptMoney);
				 factoryInfoService.updateByPrimaryKeySelective(factoryInfo);
				 return new JsonResult<String>(0,"操作成功");
			 }
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<String>(1,"操作失败");
		}
     }
	 
	 /**
      * 添加合作公司信息
      * @param request
      * @param response
      * @return
      */
	 @ResponseBody
     @RequestMapping("/updateFactoryCooperativeEnterprise.do")
     public JsonResult<String> updateFactoryCooperativeEnterprise(HttpServletRequest request,HttpServletResponse response){
		 String factoryId = WebCookie.getFactoryId(request);
		 try {
			if(StringUtils.isBlank(factoryId)){
				return new JsonResult<String>(2,"未获取到登录信息");
			 }else{
				 String cooperativeEnterprise=request.getParameter("cooperativeEnterprise");//合作的公司
				 FactoryInfo factoryInfo=new FactoryInfo();
				 factoryInfo.setFactoryId(factoryId);
				 factoryInfo.setCooperativeEnterprise(cooperativeEnterprise);
				 factoryInfoService.updateByPrimaryKeySelective(factoryInfo);
				 return new JsonResult<String>(0,"操作成功");
			 }
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<String>(1,"操作失败");
		}
     }
	 /**
	  * 查询企业的详细信息
	  * @param request
	  * @param response
	  * @return
	  */
	 @ResponseBody
     @RequestMapping("/selectFactoryDetailsInfo.do")
     public JsonResult<Map<String,Object>> selectFactoryDetailsInfo(HttpServletRequest request,HttpServletResponse response){
		 String factoryId = WebCookie.getFactoryId(request);
		 String loginEmail = WebCookie.getLoginEmail(request);
		 try {
			 if(StringUtils.isBlank(factoryId)){
				return new JsonResult<Map<String,Object>>(2,"未获取你的登陆信息");
			 }else{
				   //1.查询企业基本信息 
				   FactoryInfo factoryInfo=factoryInfoService.selectFactoryInfo(factoryId);
				   FactoryUser factoryUser = factoryUserService.selectByLoginEmail(loginEmail);
				   String[] mainProcess;//处理工艺强项
				   if(StringUtils.isNotBlank(factoryInfo.getMainProcess())){
					    mainProcess=factoryInfo.getMainProcess().split(","); 
					    factoryInfo.setMainProcessS(mainProcess);
				   }
				   //2.查询设备清单
				   List<Equipment> equipmentList=equipmentService.selectFactoryEquipment(factoryId);//处理设备清单
				   factoryInfo.setEquipmentList(equipmentList);
				   
				   //3.查询资格认证
				   List<Qualification> qualificationList=new ArrayList<Qualification>();
				   qualificationList=qualificationService.queryByFactory(factoryId);
				   factoryInfo.setQualificationList(qualificationList);
				   //4.处理合作企业
				   String[] cooperativeEnterprise;
				   if(StringUtils.isNotBlank(factoryInfo.getCooperativeEnterprise())){
					   cooperativeEnterprise=factoryInfo.getCooperativeEnterprise().split(","); 
					    factoryInfo.setCooperativeEnterpriseS(cooperativeEnterprise);
				   }
				   Map<String,Object> map=new HashMap<String, Object>();
				   map.put("factoryInfo", factoryInfo);
				   map.put("factoryUser", factoryUser);
				   return new JsonResult<Map<String,Object>>(0,"查询成功",map);
			 }
		 }catch(Exception e){
			 e.printStackTrace();
			 return new JsonResult<Map<String,Object>>(1,"查询失败");
		 }
	 }
	 
	 
	    /**
		 * 添加合作企业
		 * @param request
		 * @param response
		 * @return
		 */
		@RequestMapping("/addCooperativeEnterprise.do")
		@ResponseBody
		public JsonResult<String> addCooperativeEnterprise(HttpServletRequest request,HttpServletResponse response){	
			  String factoryId = WebCookie.getFactoryId(request);
			  try {
				  if(StringUtils.isBlank(factoryId)){
					  return new JsonResult<String>(2,"未获取到登录信息");
				  }else{
					 FactoryInfo factoryInfo=new FactoryInfo();
				     String data=request.getParameter("data"); 
					 factoryInfo.setFactoryId(factoryId);
					 factoryInfo.setCooperativeEnterprise(data);
					 factoryInfoService.updateByPrimaryKeySelective(factoryInfo);
					 return new JsonResult<String>(0,"更新成功");
				  }
			} catch (Exception e) {
				e.printStackTrace();
				 return new JsonResult<String>(1,"更新失败");
			}
	 } 
		
		
		
		
	  
	 /**
	  * 更新视频和图片
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping("/updateProductionEnvironmentAndVideo.do")
	 @ResponseBody
	 public JsonResult<String> updateProductionEnvironmentAndVideo(HttpServletRequest request,HttpServletResponse response){	
		  String factoryId = WebCookie.getFactoryId(request);
		  try {
			  if(StringUtils.isBlank(factoryId)){
				  return new JsonResult<String>(2,"未获取到登录信息");
			  }else{
				 FactoryInfo factoryInfo=factoryInfoService.selectFactoryInfo(factoryId);
//				 FactoryInfo searFactoryInfo=factoryInfoService.selectFactoryInfo(factoryId); 
//			     String productionEnvironmentS=request.getParameter("productionEnvironmentS"); 
			     String productionVideoS=request.getParameter("productionVideoS");
//				 factoryInfo.setFactoryId(factoryId);
//				 if(StringUtils.isNotBlank(productionEnvironmentS)){
//					 String productionEnvironment=searFactoryInfo.getProductionEnvironment();
//					 String productionEnvironmentStr="";
//					 if(productionEnvironment!=null && productionEnvironment!=""){
//						 productionEnvironmentStr=productionEnvironment+','+productionEnvironmentS;
//					 }else{
//						 productionEnvironmentStr=productionEnvironmentS+',';
//					 }
//					 factoryInfo.setProductionEnvironment(productionEnvironmentStr);
//				 }
				 if(StringUtils.isNotBlank(productionVideoS)){
					 factoryInfo.setProductionVideo(productionVideoS);
				 }
				 factoryInfoService.updateByPrimaryKeySelective(factoryInfo);
				 return new JsonResult<String>(0,"更新成功");
			  }
		} catch (Exception e) {
			e.printStackTrace();
			 return new JsonResult<String>(1,"更新失败");
		}
	} 
	 
	 
	 /**
	  * 查询企业业务看板信息
	  * @param request
	  * @param response
	  * @return
	  */
	 @ResponseBody
     @RequestMapping("/selectFactoryBusinessBoard.do")
     public JsonResult<Map<String,Object>> selectFactoryBusinessBoard(HttpServletRequest request,HttpServletResponse response){
		 String factoryId = WebCookie.getFactoryId(request);
		 try {
			 if(StringUtils.isBlank(factoryId)){
				return new JsonResult<Map<String,Object>>(2,"未获取你的登陆信息");
			 }else{
				//1.查询企业基本信息 
			  FactoryInfo factoryInfo=factoryInfoService.selectFactoryInfo(factoryId);
			  String str = request.getParameter("page");
			  String pageSizeString = request.getParameter("pageSize");
			  String bigBuyerId = request.getParameter("bigBuyerId");
			  Integer pageSize = 0;
			  Integer page = 1;
			  if(StringUtils.isBlank(str)){
				  page = 1;
			  }else{
				  page = Integer.parseInt(str);
			  }
			  if (StringUtils.isBlank(pageSizeString)) {
				pageSize = 18;
			  }else{
				pageSize = Integer.parseInt(pageSizeString);
			  }
			  Integer start = pageSize * (page-1);
			  Integer orderStatus = OrderStatusEnum.NORMAL_ORDER.getCode();
			  String process = request.getParameter("process");
			  String product = request.getParameter("product");
			  String type = request.getParameter("customerType");
			  Integer customerType = null;
			  if(StringUtils.isNotBlank(type)){
				  customerType = CustomerTypeEnum.getEnum(type).getCode();
			  }				  
			  List<QuoteProduct> inquiryOrders = quoteProductService.queryProductGroupByOrderId(start, pageSize, orderStatus, process, product,customerType,factoryId,bigBuyerId);
			  int totalOrder = quoteProductService.totalOrder(orderStatus, process, product, customerType,factoryId,null);
//			  List<List<String>> productNames =  new ArrayList<List<String>>();
			  
			  //匹配工厂工艺进行排序				  
			  Collections.sort(inquiryOrders,new InquirySortState());
			  Collections.sort(inquiryOrders,new InquirySortProcess());
			  
//			  for (QuoteProduct inquiryOrder : inquiryOrders) {
//				  List<QuoteProduct> products = quoteProductService.queryByOrderId(inquiryOrder.getOrderId());
//				  List<String> strs = new ArrayList<String>();
//					  for (QuoteProduct quoteProduct : products) {							  
//						 //产品名处理，当产品名大于6位时，以...代替末尾显示 
//						 String productName = quoteProduct.getProductName();
//						 if(StringUtils.isNotBlank(productName)){
//							 int tl = productName.length();
//							 if(tl > 6){
//								 productName=productName.substring(0,6) + "..."; 
//								 strs.add(productName);
//							 }else{
//								 strs.add(productName);
//							 }
//						 }
//					  }
//					  productNames.add(strs);  
//			  }
			  
			  Map<String,Object> map = new HashMap<String, Object>();
			  map.put("inquiryOrders", inquiryOrders);
//			  map.put("productNames", productNames);				  
			  map.put("totalOrder", totalOrder);
			  map.put("factoryInfo", factoryInfo);
			  return  new JsonResult<Map<String,Object>>(map);
			}
		 }catch(Exception e){
			 e.printStackTrace();
			 return new JsonResult<Map<String,Object>>(1,"查询失败");
		 }
	 }
	 
	 
	 /**
	  * 删除营业执照
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping("/delFactoryLicense.do")
	 @ResponseBody
	 public JsonResult<String> delFactoryLicense(HttpServletRequest request,HttpServletResponse response){	
		  String factoryId = WebCookie.getFactoryId(request);
		  try {
			  if(StringUtils.isBlank(factoryId)){
				  return new JsonResult<String>(2,"未获取到登录信息");
			  }else{
				 FactoryInfo factoryInfo=null;
				 String factoryLicense=request.getParameter("factoryLicense");
				 String factoryGate=request.getParameter("factoryGate");
				 String productionEnvironment=request.getParameter("productionEnvironment");
				 factoryInfo=factoryInfoService.selectFactoryInfo(factoryId);
				 if(StringUtils.isNotBlank(factoryLicense)){//删除营业执照
					 String factoryLicenseS=factoryInfo.getFactoryLicense();
					 factoryInfo.setFactoryLicense(factoryLicenseS.replace(factoryLicense,"").replace(",","")); 
				 }
				 if(StringUtils.isNotBlank(factoryGate)){//删除工厂厂门
					 String factoryGateS=factoryInfo.getFactoryGate();
					 factoryInfo.setFactoryGate(factoryGateS.replace(factoryGate,"").replace(",","")); 
				 }
				 if(StringUtils.isNotBlank(productionEnvironment)){
					 String productionEnvironmentS=factoryInfo.getProductionEnvironment();
					 String[] split2 = productionEnvironmentS.split(",");
					 String[] split = productionEnvironmentS.split(productionEnvironment);
					 
					 if(split2.length > 1){
						 
						 if(split.length > 1){
							 productionEnvironment = productionEnvironment + ",";
						 }else{
							 productionEnvironment = "," + productionEnvironment;
						 }
					 }					 

					 factoryInfo.setProductionEnvironment(productionEnvironmentS.replace(productionEnvironment,"")); 
				 }
				 factoryInfoService.updateByPrimaryKeySelective(factoryInfo);
				 return new JsonResult<String>(0,"删除成功");
			  }
		} catch (Exception e) {
			e.printStackTrace();
			 return new JsonResult<String>(1,"删除失败");
		}
	} 
	/**
	 * 删除生产视频
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/delProductionVideo.do")
	@ResponseBody
	public JsonResult<String> delProductionVideo(HttpServletRequest request,HttpServletResponse response){
		 String factoryId = WebCookie.getFactoryId(request);
		  try {
			  if(StringUtils.isBlank(factoryId)){
				  return new JsonResult<String>(2,"未获取到登录信息");
			  }else{
				 FactoryInfo factoryInfo=new FactoryInfo();
				 factoryInfo.setFactoryId(factoryId);
				 factoryInfo.setProductionVideo("");
				 factoryInfoService.delProductionVideo(factoryInfo);
				 return new JsonResult<String>(0,"删除成功");
			  }
		} catch (Exception e) {
			e.printStackTrace();
			 return new JsonResult<String>(1,"删除失败");
		}
	}
	 
}
