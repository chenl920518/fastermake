//package com.cbt;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import com.cbt.entity.FactoryInfo;
//import com.cbt.service.FactoryInfoService;
//@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
//@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
///**
// * 
// * @author chenlun
// *
// */
//public class FactoryInfoControllerTest {
//	
//	@Autowired
//	private FactoryInfoService factoryInfoService;
//	
//	@Test
//	public void updateFactoryInfo(){
//	 FactoryInfo factoryInfo=new FactoryInfo();
//	 factoryInfo.setFactoryId("f2017092614");
//	 factoryInfo.setFactoryName("上海凯融信息科技有限公司");
//	 factoryInfo.setTel("13588886666");
//	 factoryInfo.setCity("普陀区");
//	 factoryInfo.setState("上海市");
//	 factoryInfo.setFactoryAcreage("500");
//	 factoryInfo.setStaffNumber("100");
//	 factoryInfo.setWebsite("www.importx.com");
//	 factoryInfo.setAliWebsite("www.importx.com/ali");
//	 factoryInfo.setDetailsAddress("上海市云岭东路609");
//	 factoryInfo.setDistrict("普陀区");
//	 factoryInfo.setType("0");
//	 factoryInfo.setFactoryValue("5000");
//	 factoryInfo.setEstablishmentYear("2000");
//	 factoryInfo.setFactoryLicense("");
//	 factoryInfo.setFactoryGate("");
//	 factoryInfo.setUsername("Panda");
//     factoryInfo.setPosition("主管");
//     factoryInfo.setTel("13566668888");
//     factoryInfo.setFax("13655558888");
//     factoryInfo.setMobile("13188889999");
//     factoryInfo.setTechnologicalAdvantage("钣金,镀金,冲压");
//	 factoryInfo.setDominantMaterialModel("ISO-10100");
//	 factoryInfo.setDominantMaterialSize("大");
//	 factoryInfo.setMainProcess("钣金，切割，镀金");
//	 factoryInfo.setAcceptCondition("0");
//	 factoryInfo.setAcceptMoney("20万");
//	 factoryInfo.setCooperativeEnterprise("飞利浦,德邦,亚马逊");
//	 FactoryInfo result=new FactoryInfo();
//	 try {
//		 factoryInfoService.updateByPrimaryKeySelective(factoryInfo);
//		 result=factoryInfoService.selectFactoryInfo("f2017092614");
//		 Assert.assertEquals("更新接口有问题", "上海凯融信息科技有限公司", result.getFactoryName());
//	 } catch (Exception e) {
//		e.printStackTrace();
//	 }finally{
//		 Assert.assertEquals("更新接口有问题", "上海凯融信息科技有限公司", result.getFactoryName());
//	 }
//	}
//	
//	public void selectFactoryInfo(){
//		String factoryId="f201711501";
//		FactoryInfo factoryInfo=new FactoryInfo();
//		try {
//			factoryInfo=factoryInfoService.selectFactoryInfo(factoryId);
//			Assert.assertNull(factoryInfo);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally{
//			Assert.assertNull(factoryInfo);
//		}
//	}
//}
