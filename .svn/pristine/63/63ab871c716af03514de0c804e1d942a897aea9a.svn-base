//package com.cbt;
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
//import com.cbt.controller.ProductLibraryController;
//import com.cbt.entity.FactoryInfo;
//import com.cbt.service.FactoryInfoService;
//import com.cbt.util.Base64Encode;
//
//
//import net.sf.json.JSONObject;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//// 表示继承了SpringJUnit4ClassRunner类
//@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
//public class ProductLibraryControllerTest {
//	@Autowired
//	private ProductLibraryController controller;
//	
//	
//
//	private MockMvc mockMvc;
//
//	private Cookie userCookie;
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
//		userCookie = new Cookie("factoryInfo", Base64Encode.getBase64(str));
//		userCookie.setPath("/");
//		userCookie.setMaxAge(60 * 60 * 24 * 365);
//
//	}
//
//	/*
//	 * 
//	 * 查询列表
//	 */
//	
////	@Test
//	public void selectPublicList() throws Exception {
//
//		ResultActions resultActions = null;
//
//		MockHttpServletRequestBuilder build = MockMvcRequestBuilders
//				.post("/productLibrary/queryList.do");
//
//		if (userCookie != null) {
//			build.cookie(userCookie);
//
//		}
//
//		String str = "1";
//
//		build.param("page", "1").param("materialsGroup", "青铜", "不锈钢");
//
//		resultActions = this.mockMvc.perform(build);
//
//		MvcResult mvcResult = resultActions.andReturn();
//
//		String result = mvcResult.getResponse().getContentAsString();
//		JSONObject jsonObject = JSONObject.fromObject(result);
//		System.out.println("=====客户端获得反馈数据:" + result);
//		Assert.assertEquals(0, jsonObject.get("state"));
//
//	}
//	
//	/**
//	 * 采购商发送消息给供应商
//	 * 
//	 */
////	@Test
//	public void sendMessageToFactory() throws Exception {
//		
//		ResultActions resultActions = null;
//
//		MockHttpServletRequestBuilder build = MockMvcRequestBuilders
//				.post("/productLibrary/sendMessageToFactory.do");
//
//		if (userCookie != null) {
//			build.cookie(userCookie);
//
//		}
//
//		build.param("factoryId", "143454").param("planBuyCount", "21312")
//		.param("planBuyCountUnit", "件").param("planBuyPrice", "1212").param("planInfo", "大家好才是真的好");
//
//		resultActions = this.mockMvc.perform(build);
//
//		MvcResult mvcResult = resultActions.andReturn();
//
//		String result = mvcResult.getResponse().getContentAsString();
//		JSONObject jsonObject = JSONObject.fromObject(result);
//		System.out.println("=====客户端获得反馈数据:" + result);
//		Assert.assertEquals(0, jsonObject.get("state"));
//
//	}
//	
//	
//	/**
//	 * 收藏商品
//	 * 
//	 */
//	@Test
//	public void checkPreference() throws Exception {
//		
//		ResultActions resultActions = null;
//
//		MockHttpServletRequestBuilder build = MockMvcRequestBuilders
//				.post("/productLibrary/checkPreference.do");
//
//		if (userCookie != null) {
//			build.cookie(userCookie);
//
//		}
//				
//		build.param("productId", "321").param("type", "1");
//
//		resultActions = this.mockMvc.perform(build);
//
//		MvcResult mvcResult = resultActions.andReturn();
//
//		String result = mvcResult.getResponse().getContentAsString();
//		JSONObject jsonObject = JSONObject.fromObject(result);
//		System.out.println("=====客户端获得反馈数据:" + result);
//		Assert.assertEquals(0, jsonObject.get("state"));
//		
//	}
//	
//
//}
