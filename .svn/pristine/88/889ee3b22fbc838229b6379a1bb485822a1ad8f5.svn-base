//package com.cbt;
//import java.io.UnsupportedEncodingException;
//import java.net.URLEncoder;
//import java.util.List;
//
//import javax.annotation.Resource;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import net.sf.json.JSONObject;
//
//import org.apache.commons.lang.StringUtils;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import com.cbt.controller.InquiryController;
//import com.cbt.entity.FactoryInfo;
//import com.cbt.entity.Qualification;
//import com.cbt.service.FactoryInfoService;
//import com.cbt.service.QualificationService;
//import com.cbt.util.Base64Encode;
//
//@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
//@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})   
//public class InquiryControllerTest {
//	
//	@Autowired
//    private InquiryController controller;
//	@Resource
//	FactoryInfoService factoryInfoService;
//	
//	@Resource
//	QualificationService qualificationService;
//	
//    private MockMvc mockMvc;
//
//    private Cookie userCookie;
//    private Cookie q_cookie;
//    private Cookie s_cookie;
//
//    private Cookie orderIdCookie;
//
////    @Before
////	public void setup() throws UnsupportedEncodingException {
////		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
////		String loginEmail = "panda@163.com";
////		String pwd = "123456";
////		FactoryInfo factoryInfo = factoryInfoService.login(loginEmail, pwd);
////
////		String factoryId = factoryInfo.getFactoryId();
////		pwd = factoryInfo.getPwd();
////		String province = "";
////		province = factoryInfo.getState();
////
////		String str = factoryId + "&" + loginEmail + "&" + pwd + "&"
////				+ factoryInfo.getIsVip() + "&" + province + "&"  
////				+ factoryInfo.getFactoryType();
////		 userCookie = new Cookie("factoryInfo",Base64Encode.getBase64(str));
////		 userCookie.setPath("/");
////		 userCookie.setMaxAge(60*60*24*365);
//		
//		 
////		 StringBuffer qs = new StringBuffer();
////         String q = null;
////         List<Qualification> qualification = qualificationService.queryByFactory(factoryId);
////         if(qualification != null && qualification.size() != 0){
////       	  for (Qualification qualification2 : qualification) {
////       		  qs.append(qualification2.getQualificationName()+",");
////			  }
////			  if(qs.length()>1){
////			  q = qs.substring(0, qs.length() -1);
////			  }
////			  
////		  q_cookie = new Cookie("FSM_Q",URLEncoder.encode(q , "UTF-8"));           
////		  q_cookie.setPath("/");
////		  q_cookie.setMaxAge(60*60*24*365);
////         }
//         
////        if(StringUtils.isNotBlank(factoryInfo.getMainProcess())){
////       	  //存放process、地区 、资格认证
////        	  s_cookie = new Cookie("FSM_P",URLEncoder.encode(factoryInfo.getMainProcess(), "UTF-8"));           
////        	  s_cookie.setPath("/");
////        	  s_cookie.setMaxAge(60*60*24*365);
////        }
//		 
//		 
////		orderIdCookie =  new Cookie("orderId","100079");
////		orderIdCookie.setPath("/");
////		orderIdCookie.setMaxAge(60*60*24*365);
//
//		
//		
//		
////	}  
//    
//    
//	@Before
//	public void setup() {
//		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
//
//		String loginEmail = "panda@163.com";
//		String pwd = "123456";
//		FactoryInfo factoryInfo = factoryInfoService.login(loginEmail, pwd);
//
//		String factoryId = factoryInfo.getFactoryId();
//		pwd = factoryInfo.getPwd();
//		Integer state = null;
//		String province = "";
//		province = factoryInfo.getState();
//
//		String str = factoryId + "&" + loginEmail + "&" + pwd + "&"
//				+ factoryInfo.getIsVip() + "&" + province + "&"
//				+ factoryInfo.getFactoryType();
//		 userCookie = new Cookie("factoryInfo",Base64Encode.getBase64(str));
//		 userCookie.setPath("/");
//		 userCookie.setMaxAge(60*60*24*365);
//		
//		orderIdCookie =  new Cookie("orderId","100079");
//		orderIdCookie.setPath("/");
//		orderIdCookie.setMaxAge(60*60*24*365);
//
//	}  
//    
//    
//    
//  
//	@Test
//	public void testQueryInquiryList() throws Exception {
//		 ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders
//				 .post("/inquiry/queryInquiryList.do")
//		    	  .param("page", "1")
//		    	  .param("process", "")
//		    	  .param("product", "")
//		    	  .param("pageSize", "10")
//		    	  .param("bigBuyerId", "")
//		    	  .cookie(userCookie));
//		          MvcResult mvcResult = resultActions.andReturn();
//		          String result = mvcResult.getResponse().getContentAsString();
//		          JSONObject jsonObject=JSONObject.fromObject(result);
//		          System.out.println("=====客户端获得反馈数据:" + result);
//		          Assert.assertEquals(0, jsonObject.get("state"));
//	}
//	
//	
//	
//	    /**
//	     * 添加收藏
//	     * @Title addCollect 
//	     * @Description TODO
//	     * @param request
//	     * @param response
//	     * @throws Exception
//	     * @return void
//	     */
//		@Test
//		public void addOrCancelCollect(HttpServletRequest request,HttpServletResponse response) throws Exception {  
////			 ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders
////					  .post("/inquiry/addOrCancelCollect.do")
////			    	  .param("orderId", "100082")
////			    	  .cookie(userCookie));
////			          MvcResult mvcResult = resultActions.andReturn();
////			          String result = mvcResult.getResponse().getContentAsString();
////			          JSONObject jsonObject=JSONObject.fromObject(result);
////			          System.out.println("=====客户端获得反馈数据:" + result);
//			 Assert.assertEquals(1, 1);
//		}
//
//}
