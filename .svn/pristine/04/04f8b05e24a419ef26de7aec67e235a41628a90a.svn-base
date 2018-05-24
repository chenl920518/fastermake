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
//import com.cbt.controller.NoteMessageController;
//import com.cbt.controller.QuoteInfoController;
//import com.cbt.controller.ViewFactoryInfoController;
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
//public class ViewFactoryInfoControllerTest {
//	@Autowired
//	private ViewFactoryInfoController controller;
//
//	private MockMvc mockMvc;
//
//	private Cookie userCookie;
//	
//	private String tempId;
//	
//	private String factoryName;
//
//	@Resource
//	FactoryInfoService factoryInfoService;
//
//	@Before
//	public void setup() {
//		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
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
//		tempId = "f2017111559";
//		factoryName = "宁波市易驰汽车部件有限公司";
//				
//	}
//
//	/*
//	 * 查询列表
//	 */
//
//	@Test
//	public void queryList() throws Exception {
//
//		ResultActions resultActions = null;
//
//		resultActions = this.mockMvc.perform(MockMvcRequestBuilders
//				.post("/viewFactoryInfo/queryList.do").param("page", "1")
//				.param("materialsGroup", "合金钢").param("materialsGroup", "铝"));
//
//		MvcResult mvcResult = resultActions.andReturn();
//
//		String result = mvcResult.getResponse().getContentAsString();
//		JSONObject jsonObject = JSONObject.fromObject(result);
//		System.out.println("=====客户端获得反馈数据:" + result);
//		Assert.assertEquals(0, jsonObject.get("state"));
//	}
//
//	/*
//	 * 查询详情
//	 * 
//	 */
//	
//	@Test
//	public void selectFactoryDetailsInfo() throws Exception {
//
//		ResultActions resultActions = null;
//
//		resultActions = this.mockMvc.perform(MockMvcRequestBuilders
//				.post("/viewFactoryInfo/selectFactoryDetailsInfo.do").param("factoryId", tempId).cookie(userCookie));
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
//	 * 查询工业大众点评产品列表
//	 */
//	@Test
//	public void getList() throws Exception {
//		
//		ResultActions resultActions = null;
//
//		resultActions = this.mockMvc.perform(MockMvcRequestBuilders
//				.post("/viewFactoryInfo/queryProductList.do").param("factoryId", tempId).param("page", "1"));
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
//	 * 单个添加取消收藏
//	 */
//	@Test
//	public void addOrCancelCollect() throws Exception {
//		
//		ResultActions resultActions = null;
//
//		resultActions = this.mockMvc.perform(MockMvcRequestBuilders
//				.post("/viewFactoryInfo/addOrCancelCollect.do").param("factoryId", tempId).param("factoryName", factoryName).cookie(userCookie));
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
//	/*
//	 * 发表评论
//	 * 
//	 */
////	@Test
//	public void addOrCancelCollects() throws Exception {
//		
//		ResultActions resultActions = null;
//
//		resultActions = this.mockMvc.perform(MockMvcRequestBuilders
//				.post("/viewFactoryInfo/publishEvaluate.do").param("factoryId", tempId).param("evaluateInfo", "不错我喜欢").cookie(userCookie));
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
//	
//	/*
//	 * 单个添加取消点赞
//	 */
//	@Test
//    public void thumbUp()  throws Exception {
//		
//		ResultActions resultActions = null;
//
//		resultActions = this.mockMvc.perform(MockMvcRequestBuilders
//				.post("/viewFactoryInfo/thumbUp.do").param("dataid", "24").cookie(userCookie));
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
//	 * 发送消息给工厂
//	 */
//	
//	@Test
//	public void sendMessageToFactory() throws Exception{
//		
//		ResultActions resultActions = null;
//
//		resultActions = this.mockMvc.perform(MockMvcRequestBuilders
//				.post("/viewFactoryInfo/sendMessageToFactory.do").param("factoryId", tempId).param("messageTitle", "你好 我是单元测试").
//				param("messageInfo", "很高兴能给你发信息").cookie(userCookie));
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
//	
//	
//}
