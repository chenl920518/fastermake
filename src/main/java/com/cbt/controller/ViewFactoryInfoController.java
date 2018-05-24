package com.cbt.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cbt.entity.Equipment;
import com.cbt.entity.FactoryEvaluateWithBLOBs;
import com.cbt.entity.FactoryInfo;
import com.cbt.entity.NoteMessage;
import com.cbt.entity.ProductLibrary;
import com.cbt.entity.Qualification;
import com.cbt.enums.MessageTypeEnum;
import com.cbt.service.EquipmentService;
import com.cbt.service.FactoryEvaluateService;
import com.cbt.service.FactoryInfoService;
import com.cbt.service.NoteMessageService;
import com.cbt.service.ProductLibraryService;
import com.cbt.service.QualificationService;
import com.cbt.service.QuoteInfoService;
import com.cbt.service.QuoteProductService;
import com.cbt.util.DateFormat;
import com.cbt.util.WebCookie;


@Controller
@RequestMapping("/viewFactoryInfo")
public class ViewFactoryInfoController {

	@Autowired
	private FactoryInfoService factoryInfoService;

	@Autowired
	private EquipmentService equipmentService;

	@Autowired
	private QualificationService qualificationService;

	@Autowired
	private QuoteProductService quoteProductService;

	@Autowired
	private QuoteInfoService quoteInfoService;

	@Autowired
	private FactoryEvaluateService factoryEvaluateService;
	
	@Autowired
	private ProductLibraryService productLibraryService;
	
	@Autowired
	private NoteMessageService noteService;
	
	
	
	
	
	
	/**
	 * 查询产品列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/queryList.do")
	public JsonResult<Map<String, Object>> queryList(HttpServletRequest request,
			HttpServletResponse response) {

		try {

			String str = request.getParameter("page");

			Integer pageSize = 5;
		
			Integer page = 1;
			if (StringUtils.isBlank(str)) {
				page = 1;
			} else {
				page = Integer.parseInt(str);
			}
			Integer start = pageSize * (page - 1);


			String[] materialsGroup = request.getParameterValues("materialsGroup");
			String[] processGroup = request.getParameterValues("processGroup");
			String[] regionGroup = request.getParameterValues("regionGroup");
			
			String[] qualificationGroup = request.getParameterValues("qualificationGroup");
			String[] staffNumberGroup = request.getParameterValues("staffNumberGroup");
			String[] factoryAcreageGroup = request.getParameterValues("factoryAcreageGroup");
			
			

			Map map1 = new HashMap();

			map1.put("materialsGroup", materialsGroup);
			map1.put("processGroup", processGroup);
			map1.put("stateGroup", regionGroup);
			
			map1.put("qualificationGroup", qualificationGroup);
			map1.put("staffNumberGroup", staffNumberGroup);
			map1.put("factoryAcreageGroup", factoryAcreageGroup);
			
			map1.put("start", start);
			map1.put("pageSize", pageSize);

			List<FactoryInfo> fclist = factoryInfoService.selectByCondition(map1);
			int totalOrder = factoryInfoService.totalOrder(map1);

			Map<String, Object> map = new HashMap<String, Object>();

			map.put("factoryList", fclist);
			map.put("totalOrder", totalOrder);

			return new JsonResult<Map<String, Object>>(0, "success", map);

		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Map<String, Object>>(1, "查询失败");
		}

	}

	
	
	/**
	 * 查询企业的详细信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/selectFactoryDetailsInfo.do")
	public JsonResult<Map<String, Object>> selectFactoryDetailsInfo(
			HttpServletRequest request, HttpServletResponse response) {
		String factoryId = request.getParameter("factoryId");

		String buyerId = WebCookie.getFactoryId(request);

		Cookie urlCookie = new Cookie("quoteDetailUrl", "");
		urlCookie.setPath("/");
		urlCookie.setMaxAge(0);
		response.addCookie(urlCookie);

		int isCollect = 0;
		try {
			if (StringUtils.isBlank(factoryId)) {
				return new JsonResult<Map<String, Object>>(1,
						"factoryId 的值不能为空");
			} else {
				// 1.查询企业基本信息
				FactoryInfo factoryInfo = factoryInfoService
						.selectFactoryInfo(factoryId);
				factoryInfo = factoryInfoService.selectFactoryInfo(factoryId);
				String[] mainProcess;// 处理工艺强项
				if (StringUtils.isNotBlank(factoryInfo.getMainProcess())) {
					mainProcess = factoryInfo.getMainProcess().split(",");
					factoryInfo.setMainProcessS(mainProcess);
				}
				// 2.查询设备清单
				List<Equipment> equipmentList = equipmentService
						.selectFactoryEquipment(factoryId);// 处理设备清单
				factoryInfo.setEquipmentList(equipmentList);

				// 3.查询资格认证
				List<Qualification> qualificationList = new ArrayList<Qualification>();
				qualificationList = qualificationService
						.queryByFactory(factoryId);
				factoryInfo.setQualificationList(qualificationList);
				// 4.处理合作企业
				String[] cooperativeEnterprise;
				if (StringUtils.isNotBlank(factoryInfo
						.getCooperativeEnterprise())) {
					cooperativeEnterprise = factoryInfo
							.getCooperativeEnterprise().split(",");
					factoryInfo
							.setCooperativeEnterpriseS(cooperativeEnterprise);
				}
				// 查看是否收藏（add by sang)

				if (StringUtils.isNotBlank(buyerId)) {
					isCollect = factoryInfoService.selectFactoryPreference(
							factoryId, buyerId);
				}

				// 查看评价（add by sang)

				List<FactoryEvaluateWithBLOBs> factoryEvaluateList = factoryEvaluateService
						.selectByFactoryId(factoryId,buyerId);

				int count = factoryEvaluateService
						.selectCountByFactoryId(factoryId);

				Map<String, Object> map = new HashMap<String, Object>();

				// if(factoryInfo!=null&&factoryInfo.getQualificationList()!=null){
				// for(int i=0;i<factoryInfo.getQualificationList().size();i++){
				// Qualification qf = factoryInfo.getQualificationList().get(i);
				// if(qf.getFileUrl()!=null){
				// qf.setFileUrl(UploadAndDownloadPathUtil.getQualificationFile()+factoryId+File.separator+qf.getFileUrl());
				//
				// }
				//
				// }
				//
				// }
				if(!factoryId.equals(buyerId)){
					factoryInfo.setClickCounts(factoryInfo.getClickCounts()+1);
					factoryInfoService.updateByPrimaryKeySelective(factoryInfo);
				}

				map.put("factoryInfo", factoryInfo);
				map.put("isCollect", isCollect);
				map.put("factoryEvaluateList", factoryEvaluateList);
				map.put("evaluateCount", count);

				return new JsonResult<Map<String, Object>>(0, "查询成功", map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Map<String, Object>>(1, "查询失败");
		}
	}

	
	/**
	 * 查询工业大众点评产品列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/queryProductList.do")
	public JsonResult<Map<String, Object>> getList(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			String factoryId = request.getParameter("factoryId");
			if(factoryId==null||factoryId.trim()==""){
				
				return new JsonResult<Map<String, Object>>(1,"工厂Id不能为空");
			}
			
			String str = request.getParameter("page");
            String pageS = request.getParameter("pageSize");
            Integer pageSize = 0;
            if(StringUtils.isBlank(pageS)){
            	pageSize = 20;
            }else{
            	pageSize = Integer.parseInt(pageS) ;
            }
            
			
			
			
			Integer page = 1;
			if (StringUtils.isBlank(str)) {
				page = 1;
			} else {
				page = Integer.parseInt(str);
			}
			Integer start = pageSize * (page - 1);

			Map<String, Object> map = null;
		
			List<ProductLibrary> pllist = productLibraryService.queryProductGroupFactoryIdAndPage(factoryId, start, pageSize);
			
			int totalOrder = productLibraryService.selectCountByFactoryId(factoryId, null);

			map= new HashMap<String, Object>();

			map.put("productList", pllist);
			map.put("totalOrder", totalOrder);

			return new JsonResult<Map<String, Object>>(0, "success", map);

			}
		 catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Map<String, Object>>(1, "查询失败");
		}

	}
	
	
	
	
	/**
	 * 单个添加取消收藏
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addOrCancelCollect.do")
	public JsonResult<String> addOrCancelCollect(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			String factoryId = request.getParameter("factoryId");
			
			String factoryName = request.getParameter("factoryName");

			if (StringUtils.isBlank(factoryId)) {
				return new JsonResult<String>(1, "factoryId 的值不能为空");
			}

			String buyerId = WebCookie.getFactoryId(request);

			if (StringUtils.isBlank(buyerId)) {
				String url = "/zh/view_factory_info.html?id="
						+ factoryId;
				Cookie cookie = new Cookie("quoteDetailUrl", url);
				cookie.setPath("/");
				cookie.setMaxAge(60 * 60 * 24 * 365);
				response.addCookie(cookie);
				return new JsonResult<String>(2, "请先登录",
						"/zh/login.html");
			}

			int result = 0;

			int isCollect = factoryInfoService.selectFactoryPreference(
					factoryId, buyerId);
			if (isCollect == 0) {

				factoryInfoService.insertFactoryPreference(factoryId, buyerId,factoryName,
						DateFormat.format());

				result = 1;
				return new JsonResult<String>(0, "收藏成功", result + "");
			} else {
				factoryInfoService.deleteFactoryPreference(factoryId, buyerId);
				result = 0;
				return new JsonResult<String>(0, "取消收藏成功", result + "");
			}

			

		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<String>(1, "保存失败");
		}

	}
	/**
	 * 发表评论
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/publishEvaluate.do")
	public JsonResult<Map<String, Object>> addOrCancelCollects(HttpServletRequest request,
			HttpServletResponse response) {
		
		try {
			
			String factoryId = request.getParameter("factoryId");
			String evaluateInfo = request.getParameter("evaluateInfo");
			String viewId =  WebCookie.getFactoryId(request);
			
			FactoryEvaluateWithBLOBs fb = new FactoryEvaluateWithBLOBs();
			fb.setFactoryId(factoryId);
			fb.setEvaluateInfo(evaluateInfo);
            fb.setPublishId(viewId);
            fb.setCreateTime(DateFormat.format());
			factoryEvaluateService.insertSelective(fb);
			
			
			// 查看评价（add by sang)

			List<FactoryEvaluateWithBLOBs> factoryEvaluateList = factoryEvaluateService
					.selectByFactoryId(factoryId,viewId);

			int count = factoryEvaluateService
					.selectCountByFactoryId(factoryId);
			
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("factoryEvaluateList", factoryEvaluateList);
			map.put("evaluateCount", count);
			
			return new JsonResult<Map<String, Object>>(0, "查询成功", map);
			
			
			
			
		}catch(Exception e){
			e.printStackTrace();
			return new JsonResult<Map<String, Object>>(1, "保存失败");
		}
		
	}
		
		
		
		/**
		 * 单个添加取消点赞
		 * 
		 * @param request
		 * @param response
		 * @return
		 */
		@ResponseBody
		@RequestMapping("/thumbUp.do")
		public JsonResult<Map<String,Integer>> thumbUp(HttpServletRequest request,
				HttpServletResponse response) {

			try {
				String dataid = request.getParameter("dataid");

				if (StringUtils.isBlank(dataid)) {
					return new JsonResult<Map<String,Integer>>(1, "dataid 的值不能为空");
				}

				String viewId = WebCookie.getFactoryId(request);


				int result = 0;

				int isCollect = factoryEvaluateService.selectEvaluatePreference(dataid, viewId);
				
				if (isCollect == 0) {
					factoryEvaluateService.insertEvaluatePreference(dataid, viewId);
					result = 1;
				}else{
					factoryEvaluateService.deleteEvaluatePreference(dataid, viewId);
					result = 0;
					
				}
				
				int count = factoryEvaluateService.selectCountEvaluateById(dataid);
				
				Map<String,Integer> map = new HashMap<String,Integer>();
				
				map.put("isPrefer", result);
				map.put("preferCount", count);
				
				return new JsonResult<Map<String,Integer>>(0, "操作成功",map);

			} catch (Exception e) {
				e.printStackTrace();
				return new JsonResult<Map<String,Integer>>(1, "保存失败");
			}

		}
		
      
		/**
		 * 发送消息给工厂
		 * 
		 * @param request
		 * @param response
		 * @return
		 */
		@ResponseBody
		@RequestMapping("/sendMessageToFactory.do")
		public JsonResult<String> sendMessageToFactory(HttpServletRequest request,
				HttpServletResponse response) {

			try {
				
				String factoryId = request.getParameter("factoryId");

				if (StringUtils.isBlank(factoryId)) {
					return new JsonResult<String>(1, "factoryId 的值不能为空");
				}
                
				
				
				String buyerId = WebCookie.getFactoryId(request);
				
				String messageTitle = request.getParameter("messageTitle");
				
				String messageInfo = request.getParameter("messageInfo");

//				if (StringUtils.isBlank(buyerId)) {
//					String url = "/zh/view_factory_info.html?id="
//							+ factoryId;
//					Cookie cookie = new Cookie("quoteDetailUrl", url);
//					cookie.setPath("/");
//					cookie.setMaxAge(60 * 60 * 24 * 365);
//					response.addCookie(cookie);
//					return new JsonResult<String>(2, "请先登录",
//							"/zh/login.html");
//				}

				NoteMessage    vm = new NoteMessage();
				vm.setReceiverId(factoryId);
				vm.setSendId(buyerId);
				vm.setMessageTitle(messageTitle);
				vm.setMessageDetails(messageInfo);
				vm.setMessageType(MessageTypeEnum.FACTORYCOMMENT.getCode());
				vm.setCreateTime(DateFormat.format());
				
				noteService.insertNewDialogue(vm);
				return new JsonResult<String>(0, "发送成功");

			} catch (Exception e) {
				e.printStackTrace();
				return new JsonResult<String>(1, "保存失败");
			}

		}
		
		
		
		
		
		
	
		
	

}
