package com.cbt.controller;

import java.io.IOException;
import java.util.ArrayList;
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

import com.cbt.entity.PreferenceSet;
import com.cbt.service.PreferenceSetService;
import com.cbt.util.WebCookie;

/**
 * 偏好设置
 * @author chenlun
 *
 */
@RequestMapping("/preferenceSet")
@Controller
public class PreferenceSetController {

	@Autowired
	private PreferenceSetService preferenceSetService;
	
	@RequestMapping("/selectPreferenceSet.do")
	@ResponseBody
	public JsonResult<Map<String,Object>> selectPreferenceSetList(HttpServletRequest request,HttpServletResponse response){
		 String factoryId = WebCookie.getFactoryId(request);
		 try {
			if(StringUtils.isBlank(factoryId)){
			  response.sendRedirect("http://192.168.1.54:8099/login.html");
			  return new JsonResult<Map<String,Object>>(1,"未获取到登录信息");
			 }else{
				  Map<String, Object> map = new HashMap<String, Object>();
				  List<PreferenceSet> preferenceSetList=new ArrayList<PreferenceSet>();
				  preferenceSetList=preferenceSetService.selectPreferenceSetList(factoryId);
				  map.put("preferenceSetList", preferenceSetList);
				  return new JsonResult<Map<String, Object>>(map);
			 }
		} catch (IOException e) {
			 e.printStackTrace();
			 return new JsonResult<Map<String,Object>>(1,"查询失败");
		}
	}
	
	@RequestMapping("/updatePreferenceSet.do")
	@ResponseBody
	public JsonResult<String> updatePreferenceSet(HttpServletRequest request,HttpServletResponse response){
		String factoryId = WebCookie.getFactoryId(request);
		 try {
			if(StringUtils.isBlank(factoryId)){
			  response.sendRedirect("http://192.168.1.54:8099/login.html");
			  return new JsonResult<String>(1,"更新失败");
			 }else{
			   String language=request.getParameter("language");
			   String currency=request.getParameter("currency");
			   String awardDisc=request.getParameter("currency");
			   String invitedTender=request.getParameter("currency");
			   String news=request.getParameter("news");
			   String updateRecord=request.getParameter("updateRecord");
			   String secrecyApplyYes=request.getParameter("secrecyApplyYes");
			   String updateSchedule=request.getParameter("updateSchedule");
			   String receiveEvaluation=request.getParameter("updateSchedule");
			   String secrecyApplyNo=request.getParameter("secrecyApplyNo");
			   String inquiryWarn=request.getParameter("inquiryWarn");
			   PreferenceSet preferenceSet=new PreferenceSet();
			   preferenceSet.setFactoryId(factoryId);
			   preferenceSet.setLanguage(language);
			   preferenceSet.setCurrency(currency);
			   preferenceSet.setAwardDisc(awardDisc);
			   preferenceSet.setInvitedTender(invitedTender);
			   preferenceSet.setNews(news);
			   preferenceSet.setUpdateRecord(updateRecord);
			   preferenceSet.setSecrecyApplyYes(secrecyApplyYes);
			   preferenceSet.setUpdateSchedule(updateSchedule);
			   preferenceSet.setReceiveEvaluation(receiveEvaluation);
			   preferenceSet.setSecrecyApplyNo(secrecyApplyNo);
			   preferenceSet.setInquiryWarn(inquiryWarn);
			   preferenceSetService.updatePreferenceSet(preferenceSet);
			   return new JsonResult<String>(0,"更新成功");
			 }
		} catch (IOException e) {
			 e.printStackTrace();
			 return new JsonResult<String>(1,"更新失败");
		}

	}
}
