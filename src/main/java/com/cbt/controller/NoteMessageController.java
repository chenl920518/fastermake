package com.cbt.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cbt.entity.NoteMessage;
import com.cbt.service.NoteMessageService;
import com.cbt.util.DateFormat;
import com.cbt.util.JsonUtil;
import com.cbt.util.WebCookie;

@RequestMapping("/noteMessage")
@Controller
public class NoteMessageController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private NoteMessageService service;

	/**
	 * 查询列表
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/queryList.do")
	public JsonResult<Map<String, Object>> queryList(HttpServletRequest request) {

		Map<String, Object> map = null;
		List<NoteMessage> bbl = null;
		int totalOrder = 0;
		try {
			String str = request.getParameter("currpage");

			String pageSizeString = request.getParameter("pageSize");

			String status = request.getParameter("status");

			String factoryId = WebCookie.getFactoryId(request);
			
			String selectKey = request.getParameter("selectKey");
			
            Integer  messageType = null;
			boolean checkRead = false;
			boolean checkSend = false;

			if (StringUtils.isBlank(factoryId)) {
				return new JsonResult<Map<String, Object>>(1, "请先登录");
			}

			Integer pageSize = 0;
			Integer page = 1;
			if (StringUtils.isBlank(str)) {
				page = 1;
			} else {
				page = Integer.parseInt(str);
			}

			if (StringUtils.isBlank(pageSizeString)) {
				pageSize = 8;
			} else {
				pageSize = Integer.parseInt(pageSizeString);
			}

			Integer start = pageSize * (page - 1);

			if (StringUtils.isNotBlank(status)) {

				switch (status) {

				case "1":
					checkRead = true;
					break;
				case "2":
					checkSend = true;
					break;
				case "3":
				    messageType = 3; 
					break;
				case "4":
					messageType = 6; 
					break;
					
				default:
					break;
				}

			}
			
			Integer permission = WebCookie.getPermission(request);
			Integer factoryUserId = WebCookie.getFactoryUserId(request);
			if(permission == 1){
				bbl = service.queryListAdmin(factoryId, start, pageSize, checkRead,checkSend,selectKey,messageType);
				totalOrder = service.totalOrderAdmin(factoryId, checkRead, checkSend,selectKey,messageType);
			}else{
				bbl = service.queryList(factoryId, start, pageSize, checkRead,checkSend,selectKey,messageType,factoryUserId);
				totalOrder = service.totalOrder(factoryId, checkRead, checkSend,selectKey,messageType,factoryUserId);
			}
			
			

			map = new HashMap<String, Object>();
			map.put("noteMessageList", bbl);
			map.put("totalOrder", totalOrder);
			map.put("factoryId", factoryId);

			return new JsonResult<Map<String, Object>>(0, "success", map);

		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Map<String, Object>>(1, "查询失败");
		}

	}

	/**
	 * 查询详情
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/selectDetail.do")
	public JsonResult<Map<String, Object>> selectDetail(
			HttpServletRequest request) {

		Map<String, Object> map = null;
		List<NoteMessage> bbl = null;
		int totalOrder = 0;
		try {

			String dialogueId = request.getParameter("dialogueId");
//			String id = request.getParameter("id");
			String receiverId = request.getParameter("receiverId");

			String factoryId = WebCookie.getFactoryId(request);

			if (StringUtils.isBlank(factoryId)) {
				return new JsonResult<Map<String, Object>>(2, "请先登录");
			}

			if (StringUtils.isBlank(dialogueId)) {
				return new JsonResult<Map<String, Object>>(1, "dialogueId不能为空");
			}

			bbl = service.queryDetail(Integer.parseInt(dialogueId));

			if (factoryId.equals(receiverId)) {

				NoteMessage nm = new NoteMessage();
				nm.setDialogueId(Integer.parseInt(dialogueId));
				nm.setIsRead(1);
				service.updateMessageData(nm);

			}

			map = new HashMap<String, Object>();
			map.put("noteMessageList", bbl);
			return new JsonResult<Map<String, Object>>(0, "success", map);

		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Map<String, Object>>(1, "查询失败");
		}

	}

	/*
	 * 
	 * 查询新消息
	 */

	@ResponseBody
	@RequestMapping("/newMessageCount.do")
	public JsonResult<Map<String,Integer>> getNewMessageCount(HttpServletRequest request) {

		try {
			Map<String,Integer> map = new HashMap<String,Integer>();
			String factoryId = WebCookie.getFactoryId(request);
			if (StringUtils.isBlank(factoryId)) {
				return new JsonResult<Map<String,Integer>>(2, "请先登录");
			}
			Integer permission = WebCookie.getPermission(request);
			Integer factoryUserId = WebCookie.getFactoryUserId(request);
			Integer unReadsum = 0;
			Integer awardsum = 0;
			Integer updateProcesssum = 0;
			if(permission != null){
				if(permission == 1){
					unReadsum = service.totalOrderAdmin(factoryId, true, false,null,null);
					awardsum = service.totalOrderAdmin(factoryId, true, false,null,3);
					updateProcesssum = service.totalOrderAdmin(factoryId, true, false,null,6);
				}else{
					unReadsum = service.totalOrder(factoryId, true, false,null,null,factoryUserId);
					awardsum = service.totalOrder(factoryId, true, false,null,3,factoryUserId);
					updateProcesssum = service.totalOrder(factoryId, true, false,null,6,factoryUserId);
				}
			}
			
			
			map.put("unRead", unReadsum);
			map.put("award", awardsum);
			map.put("updateProcess", updateProcesssum);
			
			
			
			return new JsonResult<Map<String,Integer>>(0, "success", map);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Map<String,Integer>>(1, "查询新消息失败");
		}

	}
	
	
	/*
	 * 回复新消息
	 * 
	 */
	@ResponseBody
	@RequestMapping("/replyMessage.do")
	public JsonResult<String> replyMessage(HttpServletRequest request,
			HttpServletResponse response) {
		try {
		
			String url = "/zh/news.html";
            NoteMessage nm = new NoteMessage();
            
			Enumeration<String> en = request.getParameterNames();
			
			while (en.hasMoreElements()) {
				String name = en.nextElement();
				String value = request.getParameter(name);
				BeanUtils.setProperty(nm, name, value);

			}
            if(nm.getDialogueId()==null){
            	return new  JsonResult<String>(1,"发送失败");
            }

			nm.setCreateTime(DateFormat.format());
			
			service.insertSelective(nm);
			
			return new  JsonResult<String>(0,"发送成功",url);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return new  JsonResult<String>(1,"发送失败");
			
			
		}
	}
	
	
	
	/**
	 * 批量更新阅读状态
	 * @Title updateIsReadBatch 
	 * @Description
	 * @param request
	 * @param response
	 * @return
	 * @return JsonResult<String>
	 */
	@ResponseBody
	@RequestMapping("/updateIsReadBatch.do")
	public JsonResult<String> updateIsReadBatch(HttpServletRequest request,HttpServletResponse response) {
		
		 try {
			String factoryId = WebCookie.getFactoryId(request);
			 Integer factoryUserId = WebCookie.getFactoryUserId(request);
			 Integer permission = WebCookie.getPermission(request);
			 
			 //获取需要更新的ids
			 String jsonArray = request.getParameter("jsonArray");
			 List<Integer> ids= new ArrayList<Integer>();
			 if(StringUtils.isNotBlank(jsonArray)){
				 ids = JsonUtil.jsonToObjectList(jsonArray, Integer.class);
				 System.out.println(ids);
			 }
			 
			 Integer isRead = null;
			 if(StringUtils.isNotBlank(request.getParameter("isRead"))){
				 isRead = Integer.parseInt(request.getParameter("isRead"));
			 }			 
			 service.updateBatch(ids, permission, factoryId, factoryUserId,isRead);
		     return new JsonResult<String>(0,"更新成功");

		} catch (Exception e) {
			logger.error("=======NoteMessageController updateIsReadBatch======", e);
			return new JsonResult<String>(1,"更新失败");
		}
		
		
	}
	
	

}
