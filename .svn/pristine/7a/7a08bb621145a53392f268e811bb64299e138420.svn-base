//package com.cbt;
//import javax.annotation.Resource;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import net.sf.json.JSONObject;
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
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import com.cbt.controller.FactoryInquiryController;
//import com.cbt.controller.InquiryController;
//import com.cbt.controller.QuoteInfoController;
//import com.cbt.entity.FactoryInfo;
//import com.cbt.entity.QuoteProduct;
//import com.cbt.service.FactoryInfoService;
//import com.cbt.service.QuoteProductService;
//import com.cbt.util.Base64Encode;
//
//@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
//@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})   
//public class InquiryControllerTest1 {
//	  @Autowired
//	  private InquiryController controller;
//	 
//	  @Resource
//	  FactoryInfoService factoryInfoService;
//	  
//	  private MockMvc mockMvc;
//
//	  private Cookie userCookie;
//	  
//		@Before
//		public void setup() {
//			mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
//
//			String loginEmail = "panda@163.com";
//			String pwd = "123456";
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
//		}  
//		@Test
//		public void testQueryInquiryList() throws Exception {
//			 ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders
//					 .post("/inquiry/queryInquiryList.do")
//			    	  .param("page", "1")
//			    	  .param("process", "")
//			    	  .param("product", "")
//			    	  .param("pageSize", "10")
//			    	  .param("bigBuyerId", "")
//			    	  .cookie(userCookie));
//			          MvcResult mvcResult = resultActions.andReturn();
//			          String result = mvcResult.getResponse().getContentAsString();
//			          JSONObject jsonObject=JSONObject.fromObject(result);
//			          System.out.println("=====客户端获得反馈数据:" + result);
//			          Assert.assertEquals(0, jsonObject.get("state"));
//		}
//		@Test
//		public void testQueryAllList() throws Exception {
//			ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders
//					.post("/inquiry/queryAllListByStatus.do")
//					.param("page", "1")
//					.param("status", "1")
//					.cookie(userCookie));
//			MvcResult mvcResult = resultActions.andReturn();
//			String result = mvcResult.getResponse().getContentAsString();
//			JSONObject jsonObject=JSONObject.fromObject(result);
//			System.out.println("=====客户端获得反馈数据:" + result);
//			Assert.assertEquals(0, jsonObject.get("state"));
//		}
//		@Test
//		public void testQueryInvitation() throws Exception {
//			ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders
//					.post("/inquiry/queryInvitation.do")
//					.param("process", "")
//					.param("product", "")
//					.cookie(userCookie));
//			MvcResult mvcResult = resultActions.andReturn();
//			String result = mvcResult.getResponse().getContentAsString();
//			JSONObject jsonObject=JSONObject.fromObject(result);
//			System.out.println("=====客户端获得反馈数据:" + result);
//			Assert.assertEquals(0, jsonObject.get("state"));
//		}
//		@Test
//		public void testQueryByMainProcess() throws Exception {
//			ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders
//					.post("/inquiry/queryByMainProcess.do")
//					.cookie(userCookie));
//			MvcResult mvcResult = resultActions.andReturn();
//			String result = mvcResult.getResponse().getContentAsString();
//			JSONObject jsonObject=JSONObject.fromObject(result);
//			System.out.println("=====客户端获得反馈数据:" + result);
//			Assert.assertEquals(0, jsonObject.get("data"));
//		}
//		
//		
//		/**
//		 * 首页根据状态查询询盘
//		 * @Title testQueryAllListByStatus 
//		 * @Description
//		 * @throws Exception
//		 * @return void
//		 */
//		@Test
//		public void testQueryAllListByStatus() throws Exception {
//			ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders
//					.post("/inquiry/queryAllListByStatus.do")
//					.param("status", "1")
//					.cookie(userCookie));
//			MvcResult mvcResult = resultActions.andReturn();
//			String result = mvcResult.getResponse().getContentAsString();
//			JSONObject jsonObject=JSONObject.fromObject(result);
//			System.out.println("=====客户端获得反馈数据:" + jsonObject);
//		}
//
//		
//		
//		/**
//		 * 查询收藏
//		 * @Title testQueryCollectList 
//		 * @Description
//		 * @throws Exception
//		 * @return void
//		 */
//		@Test
//		public void testQueryCollectList() throws Exception {
//			ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders
//					.post("/inquiry/queryCollectList.do")
//					.param("process", "")
//					.param("product", "")
//					.cookie(userCookie));
//			MvcResult mvcResult = resultActions.andReturn();
//			String result = mvcResult.getResponse().getContentAsString();
//			JSONObject jsonObject=JSONObject.fromObject(result);
//			System.out.println("=====客户端获得反馈数据:" + jsonObject);
//		}
//
//		
//		
//		/**
//		 * 添加收藏
//		 * @Title testAddCollect 
//		 * @Description
//		 * @throws Exception
//		 * @return void
//		 */
//		@Test
//		public void testAddCollect() throws Exception {
//			ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders
//					.post("/inquiry/addOrCancelCollect.do")
//					.param("orderId", "100099")
//					.cookie(userCookie));
//			MvcResult mvcResult = resultActions.andReturn();
//			String result = mvcResult.getResponse().getContentAsString();
//			JSONObject jsonObject=JSONObject.fromObject(result);
//			System.out.println("=====客户端获得反馈数据:" + jsonObject);
//		}
//		
//		
//		/**
//		 * 查询订单详情
//		 * @Title testQueryInquiryDetails 
//		 * @Description 
//		 * @throws Exception
//		 * @return void
//		 */
//		@Test
//		public void testQueryInquiryDetails() throws Exception {
//			ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders
//					.post("/inquiry/queryInquiryDetails.do")
//					.param("orderId", "100099")
//					.cookie(userCookie));
//			MvcResult mvcResult = resultActions.andReturn();
//			String result = mvcResult.getResponse().getContentAsString();
//			JSONObject jsonObject=JSONObject.fromObject(result);
//			System.out.println("=====客户端获得反馈数据:" + jsonObject);
//		}
//		
//		/**
//		 * 添加沟通消息
//		 * @Title testAddQuoteMessage 
//		 * @Description 
//		 * @throws Exception
//		 * @return void
//		 */
//		@Test
//		public void testAddQuoteMessage() throws Exception {
//			ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders
//					.post("/inquiry/addQuoteMessage.do")
//					.param("orderId", "100099")
//					.param("message", "这是一个测试方法")
//					.cookie(userCookie));
//			MvcResult mvcResult = resultActions.andReturn();
//			String result = mvcResult.getResponse().getContentAsString();
//			JSONObject jsonObject=JSONObject.fromObject(result);
//			System.out.println("=====客户端获得反馈数据:" + jsonObject);
//		}
//		
//		
//		/**
//		 * 供应商添加报价
//		 * @Title testAddSupplierQuote 
//		 * @Description 
//		 * @throws Exception
//		 * @return void
//		 */
//		@Test
//		public void testAddSupplierQuote() throws Exception {
//			
//			String productList = "10&200";
//			String productIds = "471";
//			ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders
//					.post("/inquiry/addSupplierQuote.do")
//					.param("orderId", "100099")
//					.param("paymentTerm", "30%预付，其余出货后付清")
//					.param("quoteRemark", "这是一个测试")
//					.param("quoteReasons", "这是一个测试")
//					.param("quoteStatus", "1")
//					.param("validityDays", "30")
//					.param("productList", productList)
//					.param("productIds", productIds)
//					.cookie(userCookie));
//			MvcResult mvcResult = resultActions.andReturn();
//			String result = mvcResult.getResponse().getContentAsString();
//			JSONObject jsonObject=JSONObject.fromObject(result);
//			System.out.println("=====客户端获得反馈数据:" + jsonObject);
//		}
//		
//		
//		/**
//		 * 查询报价详情
//		 * @Title testQuerySupplierQuoteDetails 
//		 * @Description 
//		 * @throws Exception
//		 * @return void
//		 */
//		@Test
//		public void testQuerySupplierQuoteDetails() throws Exception {
//			ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders
//					.post("/inquiry/querySupplierQuoteDetails.do")
//					.param("orderId", "100099")
//					.cookie(userCookie));
//			MvcResult mvcResult = resultActions.andReturn();
//			String result = mvcResult.getResponse().getContentAsString();
//			JSONObject jsonObject=JSONObject.fromObject(result);
//			System.out.println("=====客户端获得反馈数据:" + jsonObject);
//		}
//		
//		/**
//		 * 查询大货详情
//		 * @Title testQuerySupplierQuoteDetails 
//		 * @Description 
//		 * @throws Exception
//		 * @return void
//		 */
//		@Test
//		public void testQueryBigProductDetails() throws Exception {
//			ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders
//					.post("/inquiry/queryBigProductDetails.do")
//					.param("orderId", "100099")
//					.cookie(userCookie));
//			MvcResult mvcResult = resultActions.andReturn();
//			String result = mvcResult.getResponse().getContentAsString();
//			JSONObject jsonObject=JSONObject.fromObject(result);
//			System.out.println("=====客户端获得反馈数据:" + jsonObject);
//		}
//		
//		
//		/**
//		 * 更新大货状态
//		 * @Title testUpdateBigProduct 
//		 * @Description 
//		 * @throws Exception
//		 * @return void
//		 */
//		@Test
//		public void testUpdateBigProduct() throws Exception {
//			ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders
//					.post("/inquiry/updateBigProduct.do")
//					.param("orderId", "100091")
//					.param("isSupplierAccept", "1")
//					.param("supplierRemark", "")
//					.cookie(userCookie));
//			MvcResult mvcResult = resultActions.andReturn();
//			String result = mvcResult.getResponse().getContentAsString();
//			JSONObject jsonObject=JSONObject.fromObject(result);
//			System.out.println("=====客户端获得反馈数据:" + jsonObject);
//		}
//		
//
//		
//		
//}
