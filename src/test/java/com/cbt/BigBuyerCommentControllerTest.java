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
//import com.cbt.controller.BigBuyerCommentController;
//
//import net.sf.json.JSONObject;
//@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
//@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})   
//public class BigBuyerCommentControllerTest {
//	@Autowired
//    private BigBuyerCommentController bigBuyerCommentController;
//    private MockMvc mockMvc;
//
//    @Before
//    public void setup(){
//        mockMvc = MockMvcBuilders.standaloneSetup(bigBuyerCommentController).build();
//    }
//  
//    /** 
//     * 测试大卖家专区列表
//     */  
//    @Test  
//    public void testShowInquiry() throws Exception {  
//    	  ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post("/bigBuyerComment/queryList.do")
//    	  .param("currpage", "1")
//    	  .param("id", "")
//    	  .param("pageSize", "10"));
//          MvcResult mvcResult = resultActions.andReturn();
//          String result = mvcResult.getResponse().getContentAsString();
//          JSONObject jsonObject=JSONObject.fromObject(result);
//          System.out.println("=====客户端获得反馈数据:" + result);
//          Assert.assertEquals(0, jsonObject.get("state"));
//    }
//    
//}
