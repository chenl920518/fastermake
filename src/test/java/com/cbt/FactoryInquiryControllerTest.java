//package com.cbt;
//import javax.annotation.Resource;
//import javax.servlet.http.Cookie;
//import net.sf.json.JSONObject;
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
//import com.cbt.controller.FactoryInquiryController;
//import com.cbt.controller.QuoteInfoController;
//import com.cbt.entity.FactoryInfo;
//import com.cbt.entity.QuoteProduct;
//import com.cbt.service.FactoryInfoService;
//import com.cbt.service.QuoteProductService;
//import com.cbt.util.Base64Encode;
//
//@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
//@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})   
//public class FactoryInquiryControllerTest {
//	  @Autowired
//	  private FactoryInquiryController controller;
//	 
//	  @Resource
//	  FactoryInfoService factoryInfoService;
//	  
//	  private MockMvc mockMvc;
//
//	  private Cookie userCookie;
//	
//	  private Cookie orderIdCookie;
//	  
//		@Before
//		public void setup() {
//			mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
//
//			String loginEmail = "545731790@qq.com";
//			String pwd = "1234567";
//			FactoryInfo factoryInfo = factoryInfoService.login(loginEmail, pwd);
//
//			String factoryId = factoryInfo.getFactoryId();
//			pwd = factoryInfo.getPwd();
//			Integer state = null;
//			String province = "";
//			province = factoryInfo.getState();
//
//			String str = factoryId + "&" + loginEmail + "&" + pwd + "&"
//					+ factoryInfo.getIsVip() + "&" + province + "&"
//					+ factoryInfo.getFactoryType();
//			 userCookie = new Cookie("factoryInfo",Base64Encode.getBase64(str));
//			 userCookie.setPath("/");
//			 userCookie.setMaxAge(60*60*24*365);
//			
//			orderIdCookie =  new Cookie("orderId","100079");
//			orderIdCookie.setPath("/");
//			orderIdCookie.setMaxAge(60*60*24*365);
//
//		}  
//	    @Test
//		public void queryProductList() throws Exception{
//	    	ResultActions resultActions = null;
//	    	if (userCookie != null) {
//				resultActions = this.mockMvc.perform(MockMvcRequestBuilders
//						.post("/factoryInquiry/queryFactoryInquiryList.do")
//						.param("process", "")
//						.param("product", "")
//						.param("orderStatus", "1")
//						.cookie(userCookie)
//						);
//		          MvcResult mvcResult = resultActions.andReturn();
//		          String result = mvcResult.getResponse().getContentAsString();
//		          JSONObject jsonObject=JSONObject.fromObject(result);
//		          System.out.println("=====我的询盘产品:" + jsonObject);
//			}	    	
//
//	    }
//	    @Test
//	    public void updateFactoryInquiry() throws Exception{
//	    	ResultActions resultActions = null;
//	    	if (userCookie != null) {
//	    		resultActions = this.mockMvc.perform(MockMvcRequestBuilders
//	    				.post("/factoryInquiry/updateFactoryInquiry.do")
//	    				.param("orderId", "100006")
//	    				.param("quoteEndDate", "2017-12-30")
//	    				.param("quoteTitle", "test")
//	    				.param("mainProcess", "机加工,高速车床")
//	    				.param("inviteStatus", "0")
//	    				.param("quoteLocation", "0")
//	    				.param("staffNumber", "500+")
//	    				.param("qualification", "ISO 9001,ISO 9004")
//	    				.param("quoteFreightTerm", "运送到目的地价")
//	    				.param("city", "上海")
//	    				.param("quotePurpose", "0")
//	    				.param("quoteRemark", "测试一下")
//	    				.cookie(userCookie)
//	    				);
//	    		MvcResult mvcResult = resultActions.andReturn();
//	    		String result = mvcResult.getResponse().getContentAsString();
//	    		JSONObject jsonObject=JSONObject.fromObject(result);
//	    		System.out.println("=====我的询盘产品:" + jsonObject);
//	    	}	    	
//	    	
//	    }
//	    
//	    
//	    
//	    
//	    
//	    
//	    
//	    
//	    
//	    /**
//	     * 
//	     * @Title queryWeeklyPhotos 
//	     * @Description 测试查看周报图片
//	     * @throws Exception
//	     * @return void
//	     */
//	    @Test
//	    public void queryWeeklyPhotos() throws Exception{
//	    	ResultActions resultActions = null;
//	    	if (userCookie != null) {
//	    		resultActions = this.mockMvc.perform(MockMvcRequestBuilders
//	    				.post("/factoryInquiry/queryWeeklyPhotos.do")
//	    				.param("orderId", "100006")
//	    				.param("uploadDate", "2017-12-30")
//	    				.cookie(userCookie)
//	    				);
//	    		MvcResult mvcResult = resultActions.andReturn();
//	    		String result = mvcResult.getResponse().getContentAsString();
//	    		JSONObject jsonObject=JSONObject.fromObject(result);
//	    		System.out.println("=====我的询盘产品:" + jsonObject);
//	    	}	    	
//	    	
//	    }
//	    
//	    
//	    
//	    
//	    
//}
