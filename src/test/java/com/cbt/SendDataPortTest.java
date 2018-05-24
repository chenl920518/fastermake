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
//import com.cbt.controller.SendDataPort;
//@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
//@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})   
//public class SendDataPortTest {
//	@Autowired
//    private SendDataPort sendDataPort;
//    private MockMvc mockMvc;
//
//    @Before
//    public void setup(){
//        mockMvc = MockMvcBuilders.standaloneSetup(sendDataPort).build();
//    }
//  
//    /**
//     * 测试同步工厂到内部报价接口
//     */
//    @Test
//    public void testSyncFactoryInfo()throws Exception{
//  	    ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post("/sendPort/syncFactoryInfo.do")
//  	    .param("factoryId", "f2017111352"));
//        MvcResult mvcResult = resultActions.andReturn();
//        String result = mvcResult.getResponse().getContentAsString();
//        System.out.println("=====客户端获得反馈数据:" + result);
//        Assert.assertEquals(0,result);	
//    }
//    
//    
//
//
//
//}
