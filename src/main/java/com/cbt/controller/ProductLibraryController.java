package com.cbt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cbt.entity.NoteMessage;
import com.cbt.entity.ProductLibrary;
import com.cbt.enums.MessageTypeEnum;
import com.cbt.service.NoteMessageService;
import com.cbt.service.ProductLibraryService;
import com.cbt.util.DateFormat;
import com.cbt.util.WebCookie;

@RequestMapping("/productLibrary")
@Controller
public class ProductLibraryController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

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
	public JsonResult<Map<String, Object>> getList(HttpServletRequest request,
			HttpServletResponse response) {

		try {

			String str = request.getParameter("page");
			// String pageS = request.getParameter("pageSize");
			Integer pageSize = 20;
			// if(StringUtils.isBlank(pageS)){
			// pageSize = 20;
			// }else{
			// pageSize = Integer.parseInt(pageS) ;
			// }
			//
			// pageSize = 20;

			Integer page = 1;
			if (StringUtils.isBlank(str)) {
				page = 1;
			} else {
				page = Integer.parseInt(str);
			}
			Integer start = pageSize * (page - 1);

			String productName = request.getParameter("productName");

			String[] materialsGroup = request
					.getParameterValues("materialsGroup");
			String[] processGroup = request.getParameterValues("processGroup");
			String[] regionGroup = request.getParameterValues("regionGroup");

			Map<String,Object> map1 = new HashMap<String, Object>();

			map1.put("materialsGroup", materialsGroup);
			map1.put("processGroup", processGroup);
			map1.put("regionGroup", regionGroup);
			map1.put("productName", productName);
			map1.put("start", start);
			map1.put("pageSize", pageSize);

			List<ProductLibrary> pllist = productLibraryService
					.queryProductGroupByCondition(map1);
			int totalOrder = productLibraryService.totalOrder(map1);

			Map<String, Object> map = new HashMap<String, Object>();

			map.put("productList", pllist);
			map.put("totalOrder", totalOrder);

			return new JsonResult<Map<String, Object>>(0, "success", map);

		} catch (Exception e) {
			logger.error("=========query error queryList=========",e);
			e.printStackTrace();
			return new JsonResult<Map<String, Object>>(1, "查询失败");
		}

	}

	/**
	 * 采购商发送消息给供应商
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
			String buyerId = WebCookie.getFactoryId(request);
			
			
			
			

			if (StringUtils.isBlank(buyerId)) {
				String url = "/zh/login.html";
				return new JsonResult<String>(1, "未获取到登录信息", url);
			} else {

				String factoryId = request.getParameter("factoryId");
				String planBuyCount = request.getParameter("planBuyCount");
				String planBuyCountUnit = request.getParameter("planBuyCountUnit");
				String planBuyPrice = request.getParameter("planBuyPrice");
				String planInfo = request.getParameter("planInfo");
				String proId = request.getParameter("proId");
				String proName = request.getParameter("proName");
				
				if (planInfo == null || planInfo.trim() == "") {
					return new JsonResult<String>(1, "内容不能为空");
				}

				String url = "/zh/product_detail.html?id=" + proId;

				StringBuilder messageInfo = new StringBuilder("");
				messageInfo.append("产品名：").append(proName).append("<br/>");
				if (StringUtils.isNotBlank(planBuyCount)) {
					messageInfo.append("计划采购量：").append(planBuyCount)
							.append(planBuyCountUnit).append("<br/>");
				}
				if (StringUtils.isNotBlank(planBuyPrice)) {
					messageInfo.append("计划采购价格：").append(planBuyPrice)
							.append("人民币").append("<br/>");
				}
				messageInfo.append("咨询内容：").append(planInfo);

				NoteMessage note = new NoteMessage();
				note.setReceiverId(factoryId);
				note.setSendId(buyerId);
				note.setMessageTitle("邀请您报价" + proName);
				note.setMessageDetails(messageInfo.toString());
				note.setMessageType(MessageTypeEnum.PRODUCTLIBRARY.getCode());
				note.setUrl(url);
				note.setCreateTime(DateFormat.format());
				noteService.insertNewDialogue(note);
				return new JsonResult<String>(0, "保存成功");
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("=========save error productLibraryMessage=========",e);
			return new JsonResult<String>(1, "保存失败");
		}

	}

	/**
	 * 收藏商品
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkPreference.do")
	public JsonResult<String> checkPreference(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String factoryId = WebCookie.getFactoryId(request);
			String productId = request.getParameter("productId");
			String type = request.getParameter("type");// 0 ：select 1：insert
														// 2：delete
			int result = 0;
			if (factoryId == null || productId == null || type == null) {

				return new JsonResult<String>(1, "不能为空");
			}

			int flag = Integer.parseInt(type);
			switch (flag) {
			case 0:
				result = productLibraryService.selectProductPreference(
						factoryId, productId);
				if (result == 1) {
					return new JsonResult<String>(0, "0");
				}

				break;
			case 1:
				productLibraryService.insertProductPreference(factoryId,
						productId, DateFormat.format());
				return new JsonResult<String>(0, "1");

			case 2:

				productLibraryService.deleteProductPreference(factoryId,
						productId);
				return new JsonResult<String>(0, "2");

			}

			return new JsonResult<String>(1, "没有收藏");

		} catch (Exception e) {
			logger.error("=========save error productLibraryMessage=========",e);
			e.printStackTrace();
			return new JsonResult<String>(1, "保存失败");
		}

	}

}
