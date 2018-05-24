package com.cbt.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cbt.entity.SysUser;
import com.cbt.service.SysUserService;

@RequestMapping("/sysUser")
@Controller
public class SysUserController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SysUserService service;

	@RequestMapping("/login.do")
	@ResponseBody
	public JsonResult<String> login(HttpSession session,
			HttpServletRequest request) {

		String result = "";
		try {

			String loginName = request.getParameter("loginName");
			String password = request.getParameter("password");

			if (StringUtils.isBlank(loginName) || StringUtils.isBlank(password)) {
				return new JsonResult<String>(1, "账户名密码不能为空");
			}
			SysUser sysUser = service.selectByName(loginName, password);
			if (sysUser == null) {
				return new JsonResult<String>(1, "账户名密码错误");
			}
			session.setAttribute("_SESSION_USER", sysUser);
			result = "index.html";
			return new JsonResult<String>(0, "登录成功", result);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new JsonResult<String>(1, "登录失败");

	}

	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {

		if (session != null) {
			session.removeAttribute("_SESSION_USER");
			session.invalidate();
		}
		return "redirect:/backstage/login.html";

	}

	@RequestMapping("/checkSession.do")
	@ResponseBody
	public JsonResult<String> checkSession(HttpSession session) {

		String result = "";
		SysUser sysUser = (SysUser) session.getAttribute("_SESSION_USER");
		if (sysUser == null) {
			return new JsonResult<String>(1, "没有登录");
		}

		String loginName = sysUser.getLoginName();

		return new JsonResult<String>(0, "已登录", loginName);

	}

}
