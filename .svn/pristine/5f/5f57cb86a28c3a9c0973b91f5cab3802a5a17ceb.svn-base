package com.cbt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cbt.service.FactoryInfoService;
import com.cbt.service.FactoryUserService;
import com.cbt.util.GetServerPathUtil;
import com.cbt.util.SendHttpsRequestUtil;
import com.cbt.util.WriteProp;

@RequestMapping("/wechat")
@Controller
public class WechatLogin {
	  @Autowired
	  private FactoryInfoService factoryInfoService;    
	  @Autowired
	  private FactoryUserService factoryUserService;  
	  
	  //获取域名
	  private static final String SERVER_PATH = GetServerPathUtil.getServerPath();
	  
	  private static String appid = WriteProp.get("appid");
	  private static String secret = WriteProp.get("secret");
	  
	  private Logger logger = LoggerFactory.getLogger(this.getClass());
	    /**
		 * 用户授权，获取用户的公开信息，保存至session
		 * 
		 * @param request
		 * @param response
		 * @return
		 */
		@RequestMapping("authorization")
		public String authorization(HttpServletRequest request, HttpServletResponse response) {

			/* ===============第一步：根据授权确认操作得到CODE==================== */
			String code = request.getParameter("code");
			if (null == code || "".equals(code)) {
				logger.error("authorization : The user authorization failure!");
			}
            	
			
			
			//根据code获取用户openid保存session
		   if(StringUtils.isNotBlank(code)){
			    logger.error("获取code:"+code);
				JSONObject json = SendHttpsRequestUtil.sendGetRequest(WriteProp.get("pageAuthorization_access_token_url")
						.replace("APPID", appid).replace("SECRET", secret).replace("CODE", code));
				String openid = json.getString("openid");
				if(StringUtils.isNotBlank(openid)){
					 //客户登录信息存放到session
		    		  HttpSession session_openid = request.getSession();
		    		  session_openid.setAttribute("openid",openid);
		    		  session_openid.setMaxInactiveInterval(60*60*24*365);			    		  
				}
		    }
		   
			//获取跳转的页面
			if(StringUtils.isNotBlank(request.getParameter("go"))){
				String go = request.getParameter("go");
				switch (go) {
					case "1":
						return "redirect:"+SERVER_PATH+"/m-zh/login.html";
					case "2":
						return "redirect:"+SERVER_PATH+"/m-zh/register.html";
					case "3":
						return "redirect:"+SERVER_PATH+"/m-zh/quote_list.html";
					case "4":
						return "redirect:"+SERVER_PATH+"/m-zh/quote_list.html?quoteStatus=1";
					default:
						return "redirect:"+SERVER_PATH+"/m-zh/login.html";
				}
			}else{
				return "redirect:"+SERVER_PATH+"/m-zh/login.html";
			}
		}
}
