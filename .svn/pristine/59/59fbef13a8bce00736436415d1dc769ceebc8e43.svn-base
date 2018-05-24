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
//public class NoteMessageControllerTest {
//	@Autowired
//	private NoteMessageController controller;
//
//	private MockMvc mockMvc;
//
//	private Cookie userCookie;
//	
//	private String dialogueId;
//	
//	private String receiverId;
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
//		
//		dialogueId = "37";
//		receiverId ="f2017120574";
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
//				.post("/noteMessage/queryList.do")
//				.param("currpage","1").param("status", "0").cookie(userCookie));
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
//	/*
//	 * 查询详情
//	 */
//	@Test
//	public void selectDetail() throws Exception {
//
//		ResultActions resultActions = null;
//
//		resultActions = this.mockMvc.perform(MockMvcRequestBuilders
//				.post("/noteMessage/selectDetail.do")
//				.param("dialogueId",dialogueId).param("receiverId",receiverId).cookie(userCookie));
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
//	 * 
//	 * 查询新消息
//	 */
//
//	@Test
//	public void getNewMessageCount() throws Exception {
//		
//		ResultActions resultActions = null;
//
//		resultActions = this.mockMvc.perform(MockMvcRequestBuilders
//				.post("/noteMessage/newMessageCount.do").cookie(userCookie));
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
//	 * 回复新消息
//	 * 
//	 */
//	
//	@Test
//	public void  replyMessage()  throws Exception {
//		
//		ResultActions resultActions = null;
//
//		resultActions = this.mockMvc.perform(MockMvcRequestBuilders
//				.post("/noteMessage/replyMessage.do").param("receiverId", "test").param("sendId", "test").param("messageDetails","大家好才是真的好").param("dialogueId", "000").cookie(userCookie));
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
//}
