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
//
//import com.cbt.controller.BigBuyerCommentController;
//import com.cbt.controller.ProductLibraryController;
//import com.cbt.controller.ProductManageController;
//import com.cbt.controller.QuoteInfoController;
//import com.cbt.entity.FactoryInfo;
//import com.cbt.service.FactoryInfoService;
//import com.cbt.service.QuoteInfoService;
//import com.cbt.util.Base64Encode;
//import com.cbt.util.Md5Util;
//import com.cbt.util.WebCookie;
//
//import net.sf.json.JSONObject;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//// 表示继承了SpringJUnit4ClassRunner类
//@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
//public class ProductManageControllerTest {
//	@Autowired
//	private ProductManageController controller;
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
//	 * 查询登录用户产品列表
//	 */
//	
////	@Test
//	public void queryList() throws Exception {
//
//		ResultActions resultActions = null;
//
//		MockHttpServletRequestBuilder build = MockMvcRequestBuilders.post("/productManage/queryList.do");
//
//		if (userCookie != null) {
//			build.cookie(userCookie);
//		}
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
//	 * 工厂档案产品库 查询一个零件的详细信息
//	 * 
//	 */
//	
////	@Test
//	public void selectOneProduct() throws Exception {
//		
//		ResultActions resultActions = null;
//
//		MockHttpServletRequestBuilder build = MockMvcRequestBuilders.post("/productManage/selectOneProduct.do");
//
//		if (userCookie != null) {
//			build.cookie(userCookie);
//		}
//		
//	
//		build.param("picId", "1");
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
//	
////	@Test
//	public void selectProductDetail() throws Exception {
//		
//		
//		ResultActions resultActions = null;
//
//		MockHttpServletRequestBuilder build = MockMvcRequestBuilders.post("/productManage/selectProductDetail.do");
//
//		if (userCookie != null) {
//			build.cookie(userCookie);
//		}
//		
//	
//		build.param("picId", "1");
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
//	/**
//	 * 更新产品
//	 * 
//	 */
////	@Test
//	public void updateProduct() throws Exception {
//		
//		ResultActions resultActions = null;
//
//		MockHttpServletRequestBuilder build = MockMvcRequestBuilders.post("/productManage/updateProduct.do");
//
//		if (userCookie != null) {
//			build.cookie(userCookie);
//		}
//
//		build.param("factoryId", "f2017092614").param("mainCategory", "铸造").param("id", "2");
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
//	/*
//	 * 
//	 * 删除产品
//	 * 
//	 */
//	@Test
//	public void deleteProduct() throws Exception {
//		
//		ResultActions resultActions = null;
//
//		MockHttpServletRequestBuilder build = MockMvcRequestBuilders.post("/productManage/deleteProduct.do");
//
//		if (userCookie != null) {
//			build.cookie(userCookie);
//		}
//
//		build.param("picId", "2");
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
////	@Test
//	public void createNewProduct() throws Exception {
//		ResultActions resultActions = null;
//
//		MockHttpServletRequestBuilder build = MockMvcRequestBuilders.post("/productManage/createNewProduct.do");
//
//		if (userCookie != null) {
//			build.cookie(userCookie);
//		}
//
//		
//		build.param("mainCategory", "锻造");
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
//	
//	
//	
//	
//	
//}
