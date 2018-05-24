//package com.cbt;
//
//import java.util.List;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import com.cbt.entity.Qualification;
//import com.cbt.service.QualificationService;
//@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
//@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
//
//public class QualificationControllerTest {
//    
//	@Autowired
//	private QualificationService qualificationService;
//	
//	@Test
//	public void addQuality() {
//		 Qualification qualification=new Qualification();
//	     qualification.setFactoryId("f201711501");
//	     qualification.setQualificationName("ISO-90009");
//	     qualification.setFileUrl("/images/zg.png");
//	     qualification.setValidityTime("2017-10-11");
//	     List<Qualification> qualitytList=null;
//	     try {
//			 qualificationService.addQualification(qualification);
//			 qualitytList = qualificationService.queryByFactory("f201711501");
//			 Assert.assertFalse("添加方法有问题", qualitytList.isEmpty());
//		 } catch (Exception e) {
//			e.printStackTrace();
//		 }finally {
//			Assert.assertFalse("添加方法有问题", qualitytList.isEmpty());
//		 }
//		
//	}
//	
//	public void selectQualification(){
//		String factoryId="f201711501";
//		List<Qualification> qualitytList=null;
//		try {
//			qualitytList=qualificationService.queryByFactory(factoryId);
//			Assert.assertFalse("查询方法有问题", qualitytList.isEmpty());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally{
//			Assert.assertFalse("查询方法有问题", qualitytList.isEmpty());
//		}
//	}
//	
//	public void updateQualification(){
//		 Qualification qualification=new Qualification();
//		 qualification.setId(1);
//	     qualification.setQualificationName("CE Mark(Europe)");
//	     qualification.setFileUrl("/images/zg.png");
//	     qualification.setValidityTime("2017-10-11");
//	     qualificationService.updateQualificatio(qualification);
//	     Qualification searQualition=null;
//	     try {
//			  searQualition=qualificationService.selectQualificationById(1);
//			  Assert.assertEquals("更新接口有问题", "CE Mark(Europe)", searQualition.getQualificationName());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally{
//			 Assert.assertEquals("更新接口有问题", "CE Mark(Europe)", searQualition.getQualificationName());
//		}
//	}
//	
//	@Test 
//	public void delEquipment(){
//		Qualification result=new Qualification();
//		try {
//			qualificationService.delQualification(1);
//			result=qualificationService.selectQualificationById(1);
//			Assert.assertNull(result);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally{
//			Assert.assertNull(result);
//		} 
//	}
//
//}
