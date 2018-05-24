package com.cbt.controller;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
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

import com.cbt.entity.BigBuyerComment;
import com.cbt.entity.FactoryInfo;
import com.cbt.entity.QuoteInfo;
import com.cbt.entity.QuoteProduct;
import com.cbt.enums.OrderStatusEnum;
import com.cbt.service.BigBuyerCommentService;
import com.cbt.service.FactoryInfoService;
import com.cbt.service.QuoteInfoService;
import com.cbt.util.DateFormat;
import com.cbt.util.WebCookie;

@RequestMapping("/bigBuyerComment")
@Controller
public class BigBuyerCommentController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BigBuyerCommentService bigBuyerCommentService;

	@Autowired
	private QuoteInfoService quoteInfoService;

	@Autowired
	private FactoryInfoService factoryInfoService;

	/**
	 * 查询大买家专区列表
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/queryList.do")
	public JsonResult<Map<String, Object>> getList(HttpServletRequest request) {

		Map<String, Object> map = null;
		List<BigBuyerComment> bbl = null;
		int totalOrder = 0;
		try {

			String factoryId = WebCookie.getFactoryId(request);

			String str = request.getParameter("currpage");

			String id = request.getParameter("id");
			/*
			 * 如果传入id不为空，只返回一条详情
			 */
			if (StringUtils.isNotBlank(id)) {
				map = new HashMap<String, Object>();

				BigBuyerComment bb = bigBuyerCommentService
						.selectByPrimaryKey(Integer.parseInt(id));

				FactoryInfo factoryInfo = factoryInfoService
						.selectFactoryInfo(bb.getBuyerId());

				if (factoryInfo != null) {
					bb.setFactoryInfo(factoryInfo);
				}

				totalOrder = 1;
				bbl = new ArrayList<BigBuyerComment>();
				bbl.add(bb);
				String buyerId = bb.getBuyerId();

				List<QuoteInfo> quoteList = quoteInfoService
						.selectDetailListByFactoryId(
								OrderStatusEnum.NORMAL_ORDER.getCode(), buyerId);

				if (quoteList != null && quoteList.size() > 0) {
					int listSize = quoteList.size();
					Double totalPrice = 0.0;
					for (int j = 0; j < quoteList.size(); j++) {
						List<QuoteProduct> qpList = quoteList.get(j)
								.getProducts();

						if (qpList != null && qpList.size() > 0) {
							for (int k = 0; k < qpList.size(); k++) {
								QuoteProduct qp = qpList.get(k);
								if (qp.getTargetPrice() != null
										&& qp.getTargetPrice() != 0) {
									String[] qts = qp.getQuantityList().split(
											",");
									int sum = compareMax(qts);
									totalPrice += qp.getTargetPrice() * sum;
								}

							}

						}
					}

					map.put("totalPrice", formatFloatNumber(totalPrice));
					map.put("listSize", listSize);
				}

				map.put("bigBuyerList", bbl);
				map.put("totalOrder", totalOrder);
				return new JsonResult<Map<String, Object>>(0, "success", map);
			}

			String pageSizeString = request.getParameter("pageSize");

			Integer pageSize = 0;
			Integer page = 1;
			if (StringUtils.isBlank(str)) {
				page = 1;
			} else {
				page = Integer.parseInt(str);
			}
			if (StringUtils.isBlank(pageSizeString)) {
				pageSize = 5;
			} else {
				pageSize = Integer.parseInt(pageSizeString);
			}

			Integer start = pageSize * (page - 1);

			bbl = bigBuyerCommentService
					.selectOrderByCondition(start, pageSize);

			totalOrder = bigBuyerCommentService.totalOrder();

			map = new HashMap<String, Object>();

			if (StringUtils.isNotBlank(factoryId)) {

				FactoryInfo userInfo = factoryInfoService
						.selectFactoryInfo(factoryId);
				map.put("userInfo", userInfo);

			}
			map.put("bigBuyerList", bbl);
			map.put("totalOrder", totalOrder);

			return new JsonResult<Map<String, Object>>(0, "success", map);

		} catch (Exception e) {
			logger.error("=========bigBuyerComment getList===========",e);
			return new JsonResult<Map<String, Object>>(1, "查询失败");
		}

	}

	/**
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/bigBuyerQuoteList.do")
	public String details(HttpServletRequest request,
			HttpServletResponse response) {

		String factoryId = WebCookie.getFactoryId(request);

		String url = request.getRequestURI() + "?" + request.getQueryString();

		// System.out.println(url);
		if (StringUtils.isBlank(factoryId)) {

			Cookie cookie = new Cookie("quoteDetailUrl", url);
			cookie.setPath("/");
			cookie.setMaxAge(60 * 60 * 24 * 365);
			response.addCookie(cookie);
			return "redirect:/zh/login.html";

		} else {

			Cookie urlCookie = new Cookie("quoteDetailUrl", "");
			urlCookie.setPath("/");
			urlCookie.setMaxAge(0);
			response.addCookie(urlCookie);

			return "redirect:/zh/big_buyer_quote_list.html?id="
					+ request.getQueryString().substring(3);

		}

	}

	/**
	 * 
	 * 
	 * @param request
	 * @param response
	 * 
	 * 更新数据
	 * @return
	 * 
	 **/

	@ResponseBody
	@RequestMapping("/updateBigBuyer.do")
	public JsonResult<String> doUpdate(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String url = "big-account.html";
			String id = request.getParameter("id");
			String oper = request.getParameter("oper");
			String isComment = request.getParameter("isComment");

			BigBuyerComment big = new BigBuyerComment();

			String[] requirements = request.getParameterValues("requirement");
			StringBuilder requirement = new StringBuilder("");
			if (requirements != null && requirements.length > 0) {
				for (int i = 0; i < requirements.length; i++) {
					if (StringUtils.isNotBlank(requirements[i])) {
						requirement.append(requirements[i]).append(",");
					}
				}
				if (requirement.indexOf(",") > 0) {
					requirement.deleteCharAt(requirement.length() - 1);
				}
			}
			big.setRequirement(requirement.toString());

			boolean isNew = true;

			if (StringUtils.isNotBlank(id)) {
				isNew = false;
				big.setId(Integer.parseInt(id));
			}

			// 如果是删除指令 就删除
			if ("del".equals(oper) && !isNew) {
				bigBuyerCommentService.deleteByPrimaryKey(Integer.parseInt(id));

				return new JsonResult<String>(0, "删除成功", url);

			}

			Enumeration<String> en = request.getParameterNames();

			while (en.hasMoreElements()) {

				String name = en.nextElement();
				String value = request.getParameter(name);
				if (!name.equals("id") && !name.equals("requirement")) {
					BeanUtils.setProperty(big, name, value);
				}
			}

			big.setReleaseDate(DateFormat.format());

			if (isComment == null) {
				big.setIsComment(0);
			}

			if (isNew) {
				bigBuyerCommentService.insert(big);
			} else {
				bigBuyerCommentService.updateSelective(big);
			}

			return new JsonResult<String>(0, "操作成功", url);

		} catch (Exception e) {
			logger.error("=========bigBuyerComment updateBigBuyer===========",e);
			return new JsonResult<String>(1, "操作失败");

		}
	}

	private int compareMax(String[] arr) {

		int max = Integer.parseInt(arr[0]);
		for (int i = 0; i < arr.length; i++) {
			if (Integer.parseInt(arr[i]) > max) {
				max = Integer.parseInt(arr[i]);
			}
		}
		return max;

	}

	private String formatFloatNumber(double value) {
		if (value != 0.00) {
			DecimalFormat df = new DecimalFormat("########.00");
			return df.format(value);
		} else {
			return "0.00";
		}

	}

}
