//package com.cbt;
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
//import com.cbt.controller.AccountController;
//
//import net.sf.json.JSONObject;
//@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
//@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})   
//public class AccountControllerTest {
//	@Autowired
//    private AccountController accountController;
//    private MockMvc mockMvc;
//
//    @Before
//    public void setup(){
//        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
//    }
//  
//    /** 
//     * 测试工厂登录
//     */  
//    @Test  
//    public void testShowInquiry() throws Exception {  
//    	  ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post("/account/showInquiry.do")
//    	  .param("loginEmail", "panda@163.com")
//    	  .param("pwd", "123456"));
//          MvcResult mvcResult = resultActions.andReturn();
//          String result = mvcResult.getResponse().getContentAsString();
//          JSONObject jsonObject=JSONObject.fromObject(result);
//          System.out.println("=====客户端获得反馈数据:" + result);
//          Assert.assertEquals(0, jsonObject.get("state"));
//    }
//    /**
//     * 测试工厂注册
//     */
//    @Test
//    public void testSupplierRegister()throws Exception{
//  	    ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post("/account/supplier_register.do")
//  	    .param("factoryName", "上海凯融")
//  	    .param("email", "demo@163.com")
//  	    .param("pwd", "123456")
//  	    .param("mainProcess", ""));
//        MvcResult mvcResult = resultActions.andReturn();
//        String result = mvcResult.getResponse().getContentAsString();
//        JSONObject jsonObject=JSONObject.fromObject(result);
//        System.out.println("=====客户端获得反馈数据:" + result);
//        Assert.assertEquals(0, jsonObject.get("state"));	
//    }
//    
//    /**
//     * 测试采购商注册
//     */
//    @Test
//    public void testBuyerRegister()throws Exception{
//  	    ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post("/account/buyer_register.do")
//  	    .param("factoryName", "上海凯融信息") 
//  	    .param("email", "demo@163.com")
//  	    .param("pwd", "123456")
//  	    .param("cellphone", "13688886666")
//  	    .param("tel", "13588886666")
//  	    .param("country", "中国")
//  	    .param("state", "上海市")
//  	    .param("factoryType", "2"));
//        MvcResult mvcResult = resultActions.andReturn();
//        String result = mvcResult.getResponse().getContentAsString();
//        JSONObject jsonObject=JSONObject.fromObject(result);
//        System.out.println("=====客户端获得反馈数据:" + result);
//        Assert.assertEquals(0, jsonObject.get("state"));	
//    }
//    
//    /**
//     * 测试忘记密码
//     */
//    @Test
//    public void testRecoverPwd()throws Exception{
//  	    ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post("/account/recoverPwd.do")
//  	    .param("email", "panda@163.com")
//  	    );
//        MvcResult mvcResult = resultActions.andReturn();
//        String result = mvcResult.getResponse().getContentAsString();
//        JSONObject jsonObject=JSONObject.fromObject(result);
//        System.out.println("=====客户端获得反馈数据:" + result);
//        Assert.assertEquals(0, jsonObject.get("state"));	
//    }
//    
//    
//    /**
//     * 测试同步工厂到内部报价接口
//     */
//    @Test
//    public void testSyncFactoryInfo()throws Exception{
//  	    ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post("/account/supplier_register.do")
//  	    .param("factoryName", "上海凯融")
//  	    .param("email", "demo@163.com")
//  	    .param("pwd", "123456")
//  	    .param("mainProcess", ""));
//        MvcResult mvcResult = resultActions.andReturn();
//        String result = mvcResult.getResponse().getContentAsString();
//        JSONObject jsonObject=JSONObject.fromObject(result);
//        System.out.println("=====客户端获得反馈数据:" + result);
//        Assert.assertEquals(0, jsonObject.get("state"));	
//    }
//    
//
//
//}
