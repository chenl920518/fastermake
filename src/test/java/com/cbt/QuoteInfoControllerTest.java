//package com.cbt;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//
//import javax.annotation.Resource;
//import javax.servlet.http.Cookie;
//
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
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import com.cbt.controller.BigBuyerCommentController;
//import com.cbt.controller.QuoteInfoController;
//import com.cbt.entity.FactoryInfo;
//import com.cbt.service.FactoryInfoService;
//import com.cbt.service.QuoteInfoService;
//import com.cbt.util.Base64Encode;
//import com.cbt.util.Md5Util;
//
//import net.sf.json.JSONObject;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//// 表示继承了SpringJUnit4ClassRunner类
//@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
//public class QuoteInfoControllerTest {
//	@Autowired
//	private QuoteInfoController controller;
//
//	private MockMvc mockMvc;
//
//	private Cookie userCookie;
//	
//	private Cookie orderIdCookie;
//
//	@Resource
//	FactoryInfoService factoryInfoService;
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
//	/**
//	 * 快速发布询盘第一步
//	 * 
//	 */
//
//	@Test
//	public void testFirst() throws Exception {
//		
//		String jsonString = getJsonString("quickly_json.txt");
//
//		ResultActions resultActions = null;
//		if (userCookie != null) {
//			resultActions = this.mockMvc.perform(MockMvcRequestBuilders
//					.post("/quoteInfo/sendQuoteToConsole.do")
//					.param("param", jsonString).cookie(userCookie));
//		} else {
//			resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post(
//					"/quoteInfo/sendQuoteToConsole.do").param("param",
//					jsonString));
//		}
//
//		MvcResult mvcResult = resultActions.andReturn();
//
//		String result = mvcResult.getResponse().getContentAsString();
//		JSONObject jsonObject = JSONObject.fromObject(result);
//		System.out.println("=====客户端获得反馈数据:" + result);
//		Assert.assertEquals(0, jsonObject.get("state"));
//	}
//	
//	
//	/**
//	 * 快速发布询盘第二步
//	 * 
//	 */
//
////	@Test
//	public void testSecond() throws Exception {
//		
//		String jsonString = getJsonString("quickly_second.txt");
//		
//		ResultActions resultActions = null;
//		
//		MockHttpServletRequestBuilder  build= MockMvcRequestBuilders.post("/quoteInfo/updateQuoteInfo.do");
//		
//		if(orderIdCookie!=null){
//			build.cookie(orderIdCookie);
//		}
//		if (userCookie != null) {
//			build.cookie(userCookie);
//			
//		}
//
//	    resultActions = this.mockMvc.perform(build.param("param", jsonString));
//			
//		MvcResult mvcResult = resultActions.andReturn();
//
//		String result = mvcResult.getResponse().getContentAsString();
//		JSONObject jsonObject = JSONObject.fromObject(result);
//		System.out.println("=====客户端获得反馈数据:" + result);
//		Assert.assertEquals(0, jsonObject.get("state"));
//		
//
//	}
//	
//	/**
//	 * 快速发布询盘第四步
//	 * 
//	 */
//	
////	@Test
//	public void confirmTest() throws Exception {
//		
//		String jsonString = getJsonString("quickly_four.txt");
//		
//		ResultActions resultActions = null;
//		
//		MockHttpServletRequestBuilder  build= MockMvcRequestBuilders.post("/quoteInfo/updateQuoteProduct.do");
//	
//		if(orderIdCookie!=null){
//			build.cookie(orderIdCookie);
//		}
//	
//		build.cookie(userCookie);
//			
//		 resultActions = this.mockMvc.perform(build.param("param", jsonString));
//	
//		 MvcResult mvcResult = resultActions.andReturn();
//
//		String result = mvcResult.getResponse().getContentAsString();
//		JSONObject jsonObject = JSONObject.fromObject(result);
//		System.out.println("=====客户端获得反馈数据:" + result);
//		Assert.assertEquals(0, jsonObject.get("state"));
//		
//	}
//	
//	/*
//	 * 
//	 * 查询详情
//	 * 
//	 */
//	
//	//@Test
//	public void selectDetail() throws Exception {
//		
//		
//		
//		ResultActions resultActions = null;
//		
//		MockHttpServletRequestBuilder  build= MockMvcRequestBuilders.post("/quoteInfo/selectDetail.do");
//	
//		if(orderIdCookie!=null){
//			build.cookie(orderIdCookie);
//		}
//		if(userCookie!=null){
//		build.cookie(userCookie);
//		}
//		resultActions = this.mockMvc.perform(build);
//	
//		 MvcResult mvcResult = resultActions.andReturn();
//
//		String result = mvcResult.getResponse().getContentAsString();
//		JSONObject jsonObject = JSONObject.fromObject(result);
//		System.out.println("=====客户端获得反馈数据:" + result);
//		Assert.assertEquals(0, jsonObject.get("state"));
//		
//	}
//	
//	
//	/*
//	 * 查询当前用户收藏的工厂列表
//	 * 
//	 * 
//	 * 
//	 */
////	@Test
//	public void  selectFactoryList() throws Exception {
//		
//       ResultActions resultActions = null;
//		
//		MockHttpServletRequestBuilder  build= MockMvcRequestBuilders.post("/quoteInfo/selectFactoryList.do");
//	
//		
//		if(userCookie!=null){
//		build.cookie(userCookie);
//		}
//		resultActions = this.mockMvc.perform(build);
//	
//		 MvcResult mvcResult = resultActions.andReturn();
//
//		String result = mvcResult.getResponse().getContentAsString();
//		JSONObject jsonObject = JSONObject.fromObject(result);
//		System.out.println("=====客户端获得反馈数据:" + result);
//		Assert.assertEquals(0, jsonObject.get("state"));
//		
//	}
//	
//	
//	
//	
//	
//
//	public String getJsonString(String fileName) {
//
//		String path = QuoteInfoControllerTest.class.getClassLoader()
//				.getResource("").getPath()
//				+ fileName;
//
//		StringBuilder sb = new StringBuilder("");
//		try {
//			FileInputStream is = new FileInputStream(new File(path));
//
//			String line = null;
//			BufferedReader reader = new BufferedReader(
//					new InputStreamReader(is));
//
//			while ((line = reader.readLine()) != null) {
//
//				sb.append(line);
//
//			}
//
//			is.close();
//			reader.close();
//
//
//		} catch (Exception e) {
//			e.printStackTrace();
//	
//		}
//	
//		return sb.toString();
//		
//	}
//	
//	
//
//}
