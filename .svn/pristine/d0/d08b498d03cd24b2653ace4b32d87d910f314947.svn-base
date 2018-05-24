package com.cbt.controller;

import java.util.ArrayList;
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

import com.cbt.entity.Qualification;
import com.cbt.service.QualificationService;
import com.cbt.util.WebCookie;

/**
 * 资质认证Conrtoller
 * @author chenlun
 *
 */
@RequestMapping("/qualificate")
@Controller
public class QualificationController {
	
	@Autowired
	private QualificationService qualificationService;
	/**
	 * 添加企业资质认证信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addQualification.do")
	@ResponseBody
	public JsonResult<String> addQualification(HttpServletRequest request,HttpServletResponse response){
		 String factoryId = WebCookie.getFactoryId(request);
	     try {
			if(StringUtils.isBlank(factoryId)){
			  return new JsonResult<String>(2,"未获取到登录信息");
			 }else{
				 Qualification qualification=new Qualification();
			     String qualificationName=request.getParameter("qualificationName"); 
			     String validityTime=request.getParameter("validityTime");
			     String fileUrl=request.getParameter("fileUrl");
			     qualification.setFactoryId(factoryId);
			     qualification.setQualificationName(qualificationName);
			     qualification.setFileUrl(fileUrl);
			     qualification.setValidityTime(validityTime);
			     qualification.setCreateDate(new Date());
			     qualificationService.addQualification(qualification);
			     return new JsonResult<String>(0,"添加成功");
			 }
		} catch (Exception e) {
			e.printStackTrace();
			 return new JsonResult<String>(1,"添加失败");
		}	
	}
	/**
	 * 查询企业资质信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/selectQualification.do")
	@ResponseBody
	public JsonResult<Map<String,Object>> selectQualification(HttpServletRequest request,HttpServletResponse response){
		 String factoryId = WebCookie.getFactoryId(request);
		 try {
			if(StringUtils.isBlank(factoryId)){
			  return new JsonResult<Map<String,Object>>(2,"未获取到登录信息");
			 }else{
				  List<Qualification> qualificationList=new ArrayList<Qualification>();
				  qualificationList=qualificationService.queryByFactory(factoryId);
				  Map<String, Object> map = new HashMap<String, Object>();
				  map.put("qualificationList", qualificationList);
				  return new JsonResult<Map<String, Object>>(map);
			 }
		} catch (Exception e) {
			 e.printStackTrace();
			 return new JsonResult<Map<String,Object>>(1,"查询失败");
		}
	}
	
	@RequestMapping("/updateQualification.do")
	@ResponseBody
	public JsonResult<Map<String,Object>> updateQualification(HttpServletRequest request,HttpServletResponse response){
		 String factoryId = WebCookie.getFactoryId(request);
		 try {
			if(StringUtils.isBlank(factoryId)){
			  return new JsonResult<Map<String,Object>>(2,"未获取到登录信息");
			 }else{
				  String id=request.getParameter("id");
				  String fileUrl=request.getParameter("qualificationFile");
			      Qualification qualification=new Qualification();
			      qualification.setFactoryId(factoryId);
			      qualification.setId(Integer.parseInt(id));
			      qualification.setFileUrl(fileUrl);
			      qualificationService.updateQualificatio(qualification);
				  return new JsonResult<Map<String,Object>>(0,"更新成功");
			 }
		} catch (Exception e) {
			 e.printStackTrace();
			 return new JsonResult<Map<String,Object>>(1,"查询失败");
		}
	}
	/**
	 * 删除资质认证信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/delQualification.do")
	@ResponseBody
	public JsonResult<Map<String,Object>> delQualification(HttpServletRequest request,HttpServletResponse response){
		 String factoryId = WebCookie.getFactoryId(request);
		 try {
			if(StringUtils.isBlank(factoryId)){
			  return new JsonResult<Map<String,Object>>(2,"未获取到登录信息");
			 }else{
				  Integer id=Integer.parseInt(request.getParameter("id"));
			      qualificationService.delQualification(id);
				  return new JsonResult<Map<String,Object>>(0,"操作成功");
			 }
		} catch (Exception e) {
			 e.printStackTrace();
			 return new JsonResult<Map<String,Object>>(1,"操作失败");
		}
	}
}
