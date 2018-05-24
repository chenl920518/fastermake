//package com.cbt;
//
//import java.util.Date;
//import java.util.List;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import com.cbt.entity.Equipment;
//import com.cbt.service.EquipmentService;
//@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
//@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
///**
// * 
// * @author chenlun
// *
// */
//public class EquipmentControllerTest {
//	
//	@Autowired
//	private EquipmentService equipmentService;
//	
//	@Test
//	public void addEquipment() {
//	  Equipment equipment=new Equipment();
//	  equipment.setFactoryId("f2017092614");
//	  equipment.setEquipmentName("电风扇");
//	  equipment.setEquipmentModel("大功率");
//	  equipment.setNumber(Integer.parseInt("20"));
//	  equipment.setParameter("生产使用");
//	  equipment.setType("0");
//	  equipment.setCreateDate(new Date());
//	  int result=0;
//	  try {
//		    equipmentService.addEquipment(equipment);
//			result = equipmentService.selectByEquipmentName("电风扇", "f2017092614");
//			Assert.assertEquals("添加接口有问题", 1, result);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally{
//			 Assert.assertEquals("添加接口有问题", 1, result);
//		}
//	
//	}
//	
//	@Test
//	public void selectFactoryEquipment(){
//		List<Equipment> equipmentList=null;
//		try {
//			String factoryId="f2017092614";
//			equipmentList = equipmentService.selectFactoryEquipment(factoryId);
//			Assert.assertFalse("查询方法有问题", equipmentList.isEmpty());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally{
//			Assert.assertFalse("查询方法有问题", equipmentList.isEmpty());
//		}
//		
//	}
//	
//	@Test 
//	public void updateEquipment(){
//		  Equipment equipment=new Equipment();
//		  equipment.setId(5);
//		  equipment.setFactoryId("f2017092614");
//		  equipment.setEquipmentName("打印机");
//		  equipment.setEquipmentModel("大功率");
//		  equipment.setNumber(Integer.parseInt("20"));
//		  equipment.setParameter("生产使用");
//		  equipment.setType("0");
//		  equipment.setCreateDate(new Date());
//		  Equipment result=null;
//		  try {
//			  equipmentService.updateEquipment(equipment);
//			  result=equipmentService.selectEquipmentById(5);
//			  Assert.assertEquals("更新接口有问题", "打印机", result.getEquipmentName());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally{
//			Assert.assertEquals("更新接口有问题", "打印机", result.getEquipmentName());
//		}
//	}
//	
//	@Test 
//	public void delEquipment(){
//		Equipment result=new Equipment();
//		try {
//			equipmentService.delEquipment(4);
//			result=equipmentService.selectEquipmentById(4);
//			Assert.assertNull(result);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally{
//			Assert.assertNull(result);
//		} 
//	}
//}
