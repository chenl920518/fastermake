package com.cbt.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cbt.entity.FactoryInfo;
import com.cbt.entity.QuoteInfo;
import com.cbt.entity.QuoteProduct;
import com.cbt.service.FactoryInfoService;
import com.cbt.service.QuoteInfoService;
import com.cbt.service.QuoteProductService;
import com.cbt.util.DateFormat;
import com.cbt.util.WebCookie;

/**
 * 采购商询盘
 * 
 * @author jason
 *
 */
@Controller
@RequestMapping("/quoteInfo")
public class QuoteInfoController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private QuoteInfoService quoteService;

	@Autowired
	private FactoryInfoService factoryInfoService;

	@Autowired
	private QuoteProductService quoteProductService;

//	/**
//	 * 查询询盘列表（首页上显示）
//	 * 
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping("/queryList.do")
//	public JsonResult<Map<String, Object>> getList(HttpServletRequest request,
//			HttpServletResponse response) {
//
//		try {
//
//			String str = request.getParameter("page");
//			String orderStatus = request.getParameter("orderStatus");
//			String pageSizeString = request.getParameter("pageSize");
//
//			Integer pageSize = 0;
//			Integer page = 1;
//
//			if (StringUtils.isBlank(str)) {
//				page = 1;
//			} else {
//				page = Integer.parseInt(str);
//			}
//
//			if (StringUtils.isBlank(orderStatus)) {
//				orderStatus = "1";
//			}
//			if (StringUtils.isBlank(pageSizeString)) {
//				pageSize = 20;
//			} else {
//				pageSize = Integer.parseInt(pageSizeString);
//			}
//
//			Integer start = pageSize * (page - 1);
//
//			List<QuoteInfo> quoteList = quoteService.queryAllInquiryOrder(
//					start, pageSize, Integer.parseInt(orderStatus));
//
//			int totalOrder = quoteService.totalNormalOrder(Integer
//					.parseInt(orderStatus));
//
//			Map<String, Object> map = new HashMap<String, Object>();
//
//			map.put("quotelist", quoteList);
//			map.put("totalOrder", totalOrder);
//
//			return new JsonResult<Map<String, Object>>(0, "success", map);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new JsonResult<Map<String, Object>>(1, "查询失败");
//		}
//
//	}

	/**
	 * 采购商发送询盘 保存方法
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/sendQuoteToConsole.do")
	public JsonResult<String> sendMessageToFactory(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			// if (StringUtils.isBlank(buyerId)) {
			// response.sendRedirect("http://192.168.1.54:8099/fastermake/login.html");
			// return new JsonResult<String>(1, "未获取到登录信息");
			// } else {

			String url = "";
			String customerId = WebCookie.getFactoryId(request);
			String jsonString = request.getParameter("param");
//			System.out.println(jsonString);

			ObjectMapper mapper = new ObjectMapper();
			QuoteInfo quoteInfo = mapper.readValue(jsonString, QuoteInfo.class);

			quoteInfo.setCreateTime(DateFormat.format());

			quoteInfo.setOrderStatus(0);

			if (quoteInfo.getIsequipmentList() == null) {
				quoteInfo.setIsequipmentList(0);
			}

			if (customerId != null && customerId.trim() != "") {
				quoteInfo.setCustomerId(customerId);
			}

			if (quoteInfo.getOrderId() != null) {
				quoteService.updateAll(quoteInfo);
			} else {
				Integer orderId = quoteService.insertAll(quoteInfo);

				if (orderId != null) {
					Cookie orderIdCookie = new Cookie("orderId",
							orderId.toString());
					orderIdCookie.setPath("/");
					orderIdCookie.setMaxAge(60 * 60 * 24 * 365);
					response.addCookie(orderIdCookie);

				}
			}

			//判断页面语言选择
			String language = WebCookie.getLanguage(request);
			if("en".equals(language)){
				url = "/en/improve_info.html";
			}else{
				url = "/zh/improve_info.html";
			}

			return new JsonResult<String>(0, "保存成功", url);

		} catch (Exception e) {
//			e.printStackTrace();
			logger.error("=========save error sendQuoteToConsole=========", e);
			return new JsonResult<String>(1, "保存失败");
		}

	}

	/**
	 * 更新主询盘
	 * 
	 * @param request
	 * @param response
	 * @return
	 */

	@ResponseBody
	@RequestMapping("/updateQuoteInfo.do")
	public JsonResult<String> updateQuoteInfo(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String url = "/zh/confirm_info.html";
			String customerId = WebCookie.getFactoryId(request);
			String orderId = WebCookie.cookie(request, "orderId");

			if (StringUtils.isBlank(orderId)) {
				return new JsonResult<String>(2, "请开始创建询盘",
						"/zh/quickly_release.html");
			}

			String jsonString = request.getParameter("param");
//			System.out.println(jsonString);

			ObjectMapper mapper = new ObjectMapper();
			QuoteInfo quoteInfo = mapper.readValue(jsonString, QuoteInfo.class);

			quoteInfo.setOrderId(Integer.parseInt(orderId));

			if (customerId != null && customerId.trim() != "") {
				quoteInfo.setCustomerId(customerId);

			} else {
				Cookie cookie = new Cookie("quoteDetailUrl", url);
				cookie.setPath("/");
				cookie.setMaxAge(60 * 60 * 24 * 365);
				response.addCookie(cookie);
				
				
				//判断页面语言选择
				String language = WebCookie.getLanguage(request);
				if("en".equals(language)){
					url = "/en/quickly_logo_resigner.html";
				}else{
					url = "/zh/quickly_logo_resigner.html";
				}
			}

			quoteService.updateByPrimaryKey(quoteInfo);

			return new JsonResult<String>(0, "更新成功", url);

		} catch (Exception e) {
			logger.error("=========save error updateQuoteInfo=========", e);
			return new JsonResult<String>(1, "保存失败");
		}

	}

	/**
	 * 更新询盘零件信息(修改零件数据)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */

	@ResponseBody
	@RequestMapping("/updateQuoteProduct.do")
	public JsonResult<String> updateQuoteProduct(HttpServletRequest request,
			HttpServletResponse response) {

		try {

			//判断页面语言选择
			String language = WebCookie.getLanguage(request);
			String url = "/zh/inspect_public.html";
			if("en".equals(language)){
				url = "/en/inspect_public.html";
			}
			String customerId = WebCookie.getFactoryId(request);
			String orderId = WebCookie.cookie(request, "orderId");

			String jsonString = request.getParameter("param");
			if (StringUtils.isBlank(jsonString)) {
				return new JsonResult<String>(1, "数据不能为空");
			}
			
			if (StringUtils.isBlank(orderId)) {				
				if("en".equals(language)){
					return new JsonResult<String>(2, "请开始创建询盘",
							"/en/quickly_release.html");
				}else{
					return new JsonResult<String>(2, "请开始创建询盘",
							"/zh/quickly_release.html");
				}
				
			}

			JSONArray array = JSONArray.fromObject(jsonString);
//			System.out.println(jsonString);
			List<QuoteProduct> list = JSONArray.toList(array,new QuoteProduct(), new JsonConfig());

			quoteProductService.updateQuoteProductGroupbyOrderId(list);
			QuoteInfo quoteInfo = new QuoteInfo();
			quoteInfo.setCustomerId(customerId);
			quoteInfo.setOrderId(Integer.parseInt(orderId));
			quoteService.updateByPrimaryKey(quoteInfo);

			Cookie urlCookie = new Cookie("orderId", "");
			urlCookie.setPath("/");
			urlCookie.setMaxAge(0);
			response.addCookie(urlCookie);

			return new JsonResult<String>(0, "更新成功", url);

		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<String>(1, "保存失败");
		}

	}

	@ResponseBody
	@RequestMapping("/selectDetail.do")
	public JsonResult<Map<String, Object>> selectDetail(
			HttpServletRequest request, HttpServletResponse response) {
		try {
			String orderId = WebCookie.cookie(request, "orderId");

			String buyerId = WebCookie.getFactoryId(request);

			if (StringUtils.isBlank(orderId)) {
				return new JsonResult<Map<String, Object>>(1, "null");
			}
			QuoteInfo quoteInfo = quoteService.selectDetailByOrderId(
					Integer.parseInt(orderId), null);

			Map<String, Object> map = new HashMap<String, Object>();

			if (StringUtils.isNotBlank(buyerId)) {
				FactoryInfo factoryInfo = factoryInfoService
						.selectFactoryInfo(buyerId);
				map.put("factoryInfo", factoryInfo);
				Cookie urlCookie = new Cookie("quoteDetailUrl", "");
				urlCookie.setPath("/");
				urlCookie.setMaxAge(0);
				response.addCookie(urlCookie);
			}

			String libraryFactoryId = quoteInfo.getLibraryFactoryId();
			if (StringUtils.isNotBlank(libraryFactoryId)) {
				FactoryInfo factoryInfo = factoryInfoService
						.selectFactoryInfo(libraryFactoryId);
				String libraryFactoryName = factoryInfo.getFactoryName();
				map.put("libraryFactoryName", libraryFactoryName);
			}

			map.put("quoteInfo", quoteInfo);

			return new JsonResult<Map<String, Object>>(0, "success", map);

		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Map<String, Object>>(1, "查询失败");
		}

	}

	@ResponseBody
	@RequestMapping("/selectFactoryList.do")
	public JsonResult<List> selectFactoryList(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String buyerId = WebCookie.getFactoryId(request);
			// if (StringUtils.isBlank(buyerId)) {
			// return new JsonResult<List>(1, "未获取到登录信息");
			// }

			List ls = new ArrayList();

			ls = factoryInfoService.selectFactoryListByBuyerId(buyerId);

			return new JsonResult<List>(0, "success", ls);

		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<List>(1, "查询失败");
		}

	}

}
