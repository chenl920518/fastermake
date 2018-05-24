package com.cbt.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cbt.entity.Equipment;
import com.cbt.entity.FactoryInfo;
import com.cbt.service.EquipmentService;
import com.cbt.service.FactoryInfoService;
import com.cbt.util.OperationFileUtil;
import com.cbt.util.UploadAndDownloadPathUtil;
import com.cbt.util.WebCookie;

/**
 * 添加企业设备清单
 * @author chenlun
 *
 */
@Controller
@RequestMapping("/equipment")
public class EquipmentController {

	@Autowired
	private FactoryInfoService factoryInfoService;
	
	@Autowired 
	private EquipmentService equipmentService;
	/**
	 * 添加企业的设备清单
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addEquipment.do")
	@ResponseBody
	public JsonResult<String> addEquipment(HttpServletRequest request,HttpServletResponse response){
		  String factoryId = WebCookie.getFactoryId(request);
		  try {
			  if(StringUtils.isBlank(factoryId)){
				  return new JsonResult<String>(2,"未获取到登录信息");
			  }else{
				  String equipmentName=request.getParameter("equipmentName");
				  String equipmentModel=request.getParameter("equipmentModel");
				  String number=request.getParameter("number");
				  String parameter=request.getParameter("parameter");
				  String type=request.getParameter("type");
				  String id=request.getParameter("id");
				 
				  Equipment equipment=new Equipment();
				  
				  equipment.setFactoryId(factoryId);
				  equipment.setEquipmentName(equipmentName);
				  equipment.setEquipmentModel(equipmentModel);
				  equipment.setNumber(Integer.parseInt(number));
				  equipment.setParameter(parameter);
				  equipment.setType(type);
				  equipment.setCreateDate(new Date());
				  if(StringUtils.isNotBlank(id)){
						Integer  idS=Integer.parseInt(id);
						equipment.setId(idS);
						equipmentService.updateEquipment(equipment);
				  }else{
					  equipmentService.addEquipment(equipment);
				  }
				
				  return new JsonResult<String>(0,"操作成功", equipment.getId().toString());
			  }
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<String>(1,"操作失败");
		}
	}
	
	/**
	 * 查询企业设备清单
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/selectFactoryEquipment.do")
	@ResponseBody
	public JsonResult<Map<String,Object>> selectFactoryEquipment(HttpServletRequest request,HttpServletResponse response){
	   try {
	      String factoryId = WebCookie.getFactoryId(request);
		  if(StringUtils.isBlank(factoryId)){
			  return new JsonResult<Map<String,Object>>(2,"未获取到登录信息");
		  }else{
			  List<Equipment> equipmentList=equipmentService.selectFactoryEquipment(factoryId);
			  Map<String, Object> map = new HashMap<String, Object>();
			  map.put("equipmentList", equipmentList);
			  return new JsonResult<Map<String, Object>>(map);
		  }
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Map<String,Object>>(1,"查询失败");
		}
	}
	
	/**
	 * 删除企业的设备清单
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/delEquipment.do")
	@ResponseBody
	public JsonResult<String> delEquipment(HttpServletRequest request,HttpServletResponse response){
		  String factoryId = WebCookie.getFactoryId(request);
		  try {
			  if(StringUtils.isBlank(factoryId)){
				  return new JsonResult<String>(2,"未获取到登录信息");
			  }else{
				  Integer id=Integer.parseInt(request.getParameter("id"));
				  equipmentService.delEquipment(id);
				  return new JsonResult<String>(0,"删除成功");
			  }
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<String>(1,"删除失败");
		}
	}
	
	/**
	 * 编辑企业的设备清单
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/updateEquipment.do")
	@ResponseBody
	public JsonResult<String> updateEquipment(HttpServletRequest request,HttpServletResponse response){
		  String factoryId = WebCookie.getFactoryId(request);
		  try {
			  if(StringUtils.isBlank(factoryId)){
				  return new JsonResult<String>(2,"未获取到登录信息");
			  }else{
				  Integer id=Integer.parseInt(request.getParameter("id"));
				  String equipmentName=request.getParameter("equipmentName");
				  String equipmentModel=request.getParameter("equipmentModel");
				  String number=request.getParameter("number");
				  String parameter=request.getParameter("parameter");
				  String type=request.getParameter("type");
				  
				  Equipment equipment=new Equipment();
				  equipment.setId(id);
				  equipment.setEquipmentName(equipmentName);
				  equipment.setEquipmentModel(equipmentModel);
				  equipment.setNumber(Integer.parseInt(number));
				  equipment.setParameter(parameter);
				  equipment.setType(type);
				  
				  equipmentService.updateEquipment(equipment);
				  return new JsonResult<String>(0,"编辑成功");
			  }
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<String>(1,"编辑失败");
		}
	}
	
	
	

	/**
	 * 上传设备excel表
	 * @Title updateFactory 
	 * @Description TODO
	 * @param request
	 * @param response
	 * @return
	 * @return JsonResult<String>
	 */
	@RequestMapping("/updateFactoryEquipment.do")
	@ResponseBody
	public JsonResult<String> updateFactoryEquipment(HttpServletRequest request,HttpServletResponse response){	
		  String factoryId = WebCookie.getFactoryId(request);
		  try {
			  if(StringUtils.isBlank(factoryId)){
				  return new JsonResult<String>(2,"未获取到登录信息");
			  }else{
				  
				     String equipmentName = request.getParameter("equipmentName");
				     String path = UploadAndDownloadPathUtil.getFactoryEquipment() +  factoryId + File.separator;			 		 
					 File file = new File(path);
					 if  (!file.exists()  && !file .isDirectory())      
					 {         
						 file .mkdir();     
					 }  	    
				    //根据文件名获取上传文件的位置  数据库保存原始文件名称
			 		Map<String, String> multiFileUpload = OperationFileUtil.multiFileUpload(request, path);
			 		String fileName = "";
			 		if(!(multiFileUpload == null || multiFileUpload.size() == 0)){
			 			fileName = multiFileUpload.get(equipmentName);
			 			fileName = path + fileName;
			 		} 	
			 		FactoryInfo factoryInfo = factoryInfoService.selectFactoryInfo(factoryId);
			 		factoryInfo.setFactoryEquipmentDocument(fileName);
			 		factoryInfoService.updateByPrimaryKeySelective(factoryInfo);
				    return new JsonResult<String>(0,"更新成功",factoryInfo.getId().toString());
			  }
		} catch (IOException e) {
			e.printStackTrace();
			 return new JsonResult<String>(1,"更新失败");
		}
 } 
	
	/**
	 * 删除设备清单
	 * @Title updateFactory 
	 * @Description TODO
	 * @param request
	 * @param response
	 * @return
	 * @return JsonResult<String>
	 */
	@RequestMapping("/delFactoryEquipment.do")
	@ResponseBody
	public JsonResult<String> delFactoryEquipment(HttpServletRequest request,HttpServletResponse response){	
		  String factoryId = WebCookie.getFactoryId(request);
		  try {
			  if(StringUtils.isBlank(factoryId)){
				  return new JsonResult<String>(2,"未获取到登录信息");
			  }else{
                    Integer id  = Integer.parseInt(request.getParameter("id"));	
			 		FactoryInfo factoryInfo = factoryInfoService.selectByPrimaryKey(id);
			 		factoryInfo.setFactoryEquipmentDocument("");
			 		factoryInfoService.updateByPrimaryKeySelective(factoryInfo);
				    return new JsonResult<String>(0,"更新成功",factoryInfo.getId().toString());
			  }
		} catch (Exception e) {
			e.printStackTrace();
			 return new JsonResult<String>(1,"更新失败");
		}
 } 
	
}
