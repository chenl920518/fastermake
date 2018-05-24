package com.cbt.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cbt.util.Md5Util;


/**
 * 验证用户是否登录
 * @author polo
 *
 */
public class AccessFilter implements Filter{

	public void destroy() {
		
		
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;	
	     //除了拦截login.html  其他的都拦截
		 StringBuffer url = request.getRequestURL();
		 String path = url.toString();
//		 HttpSession session = request.getSession();
//		 session.setAttribute("viewURL", path);	
		 
		 
		 if(path.endsWith(".html") || path.endsWith(".do")){
	
			 if(path.endsWith(".html")){
				 chain.doFilter(req, res);
				 return;
			 }
			 if(path.endsWith("register_zh.html")){
				 chain.doFilter(req, res);
				 return;
			 }
			 if(path.endsWith("queryMailsByUserId.do")){
				 chain.doFilter(req, res);
				 return;
			 }
			 if(path.endsWith("viewQuotationById.do")){
				 chain.doFilter(req, res);
				 return;
			 }
			 if(path.endsWith("progressStatus.do")){
				 chain.doFilter(req, res);
				 return;
			 }
			 //放过/account/*.do
			 if(path.indexOf("/account/")>0 || path.indexOf("/port/")>0){
				 chain.doFilter(req, res);
				 return;
			 }  
   
			 processAccessControl(request,response,chain);
			 return;
		 }
		 chain.doFilter(req, res);
		 
	}



	/**
	 * 处理访问控制
	 * @throws IOException 
	 * @throws ServletException 
	 * 
	 */
	private void processAccessControl(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		
	
		
		//检查Cookie中是否有Token 没有就去登录
		Cookie[] cookies = request.getCookies();
		Cookie token = null;
		if(cookies != null){
		for(Cookie cookie :cookies){
			if(cookie.getName().equals("token")){
				token = cookie;
				break;
			}			
		}
	  }
		 StringBuffer url = request.getRequestURL();
		 String urlPath = url.toString();
		 HttpSession session = request.getSession();
		 session.setAttribute("viewURL", urlPath);	
		
		
		if(token == null){
			//如果没有找到，就重定向到登录界面
			String path = request.getContextPath();
			String login = path+"/login.html";
			response.sendRedirect(login);
			return;
		}
		//处理token是否合格
		String value = token.getValue();
		String[] data = value.split("\\|");
		String time = data[0];
		String md5 = data[1];
		if(md5.equals(Md5Util.encoder(time))){
			chain.doFilter(request, response);
			return;
		}
		//如果token验证不合理，去登录页面
		String path = request.getContextPath();
		String login = path+"/login.html";
		response.sendRedirect(login);
		return;
		
	}

	public void init(FilterConfig arg0) throws ServletException {
	
		
	}
   
}
