package com.cbt.util;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

public class WebCookie {
	public static String cookie(HttpServletRequest request, String cookiev) {
		Cookie[] cookie = request.getCookies();
		if (cookie != null) {
			for (Cookie cookie2 : cookie) {
				if (cookie2.getName().equals(cookiev)) {
					return cookie2.getValue();
				}
			}
		}
		return null;
	}

	public static String getSessionValue(HttpServletRequest request,
			String cookiev) {
		HttpSession session = request.getSession();
		Object val = session.getAttribute(cookiev);
		if (val == null || "".equals(val)) {
			return null;
		}
		return val.toString();
	}

	/**
	 * 根据名字获取cookie
	 * 
	 * @param request
	 * @param name
	 *            cookie名字
	 * @return
	 */
	public static Cookie getCookieByName(HttpServletRequest request, String name) {
		Map<String, Cookie> cookieMap = ReadCookieMap(request);
		if (cookieMap.containsKey(name)) {
			Cookie cookie = cookieMap.get(name);
			return cookie;
		} else {
			return null;
		}
	}

	/**
	 *  * 将cookie封装到Map里面  * @param request  * @return  
	 */
	private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}

	/**
	 * 判断用户是否登录
	 */
	public static String[] getUser(HttpServletRequest request) {
		String user = WebCookie.getSessionValue(request, "factoryInfo");
		if (user == null || "".equals(user)) {
			user = WebCookie.cookie(request, "factoryInfo");
		}
		String[] userInfo = null;
		if (user != null && !"".equals(user)) {
			try {
				user = Base64Encode.getFromBase64(user);
				userInfo = user.split("&");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return userInfo;
	}

	// 从cookies中获取userid
	public static String getFactoryId(HttpServletRequest request) {

		String userid = WebCookie.getSessionValue(request, "F_ID");
		if (userid == null || "".equals(userid)) {
			userid = WebCookie.cookie(request, "F_ID");
			userid = Base64Encode.getFromBase64(userid);
		}else{
			userid = Base64Encode.getFromBase64(userid);
		}
		return userid;
	}
	
	// 从cookies中获取username
	public static String getUserName(HttpServletRequest request) {
		
		String userName  = WebCookie.cookie(request, "userName");
		if (userName != null && !"".equals(userName)) {
			try {
				userName = Base64Encode.getFromBase64(userName);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}		
		return userName;
	}
	// 从session中获取公司名
	public static String getFactoryName(HttpServletRequest request) {
		
		String factoryName  = WebCookie.getSessionValue(request, "factoryName");	
		return factoryName;
	}

	// 从cookies中获取loginEmail
	public static String getLoginEmail(HttpServletRequest request) {

		String loginEmail  = WebCookie.getSessionValue(request, "F_M");
		if (loginEmail == null || "".equals(loginEmail)) {
			loginEmail = WebCookie.cookie(request, "F_M");
			loginEmail = Base64Encode.getFromBase64(loginEmail);
		}else{
			loginEmail = Base64Encode.getFromBase64(loginEmail);
		}
		return loginEmail;
	}

	// 从cookies中获取用户密码的信息
	public static String getPwd(HttpServletRequest request) {

		String pwd = "";
		String userStr = WebCookie.getSessionValue(request, "factoryInfo");
		if (userStr == null || "".equals(userStr)) {
			userStr = WebCookie.cookie(request, "factoryInfo");
		}
		if (!("".equals(userStr) || userStr == null)) {
			try {
				userStr = Base64Encode.getFromBase64(userStr);
				String[] userInfo = userStr.split("&");
				pwd = userInfo[2];
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return pwd;
	}
	
	// 从cookies中获取用户权限的信息
	public static Integer getPermission(HttpServletRequest request) {
		
		Integer permission = null;
		String userStr = WebCookie.getSessionValue(request, "factoryInfo");
		if (userStr == null || "".equals(userStr)) {
			userStr = WebCookie.cookie(request, "factoryInfo");
		}
		if (!("".equals(userStr) || userStr == null)) {
			try {
				userStr = Base64Encode.getFromBase64(userStr);
				String[] userInfo = userStr.split("&");
				if(StringUtils.isNotBlank(userInfo[3])){
					permission = Integer.parseInt(userInfo[3]);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}		
		return permission;
	}
	
	
	// 从cookies中获取用户Id的信息
	public static Integer getFactoryUserId(HttpServletRequest request) {
		
		Integer factoryUserId = null;
		String userStr = WebCookie.getSessionValue(request, "factoryInfo");
		if (userStr == null || "".equals(userStr)) {
			userStr = WebCookie.cookie(request, "factoryInfo");
		}
		if (!("".equals(userStr) || userStr == null)) {
			try {
				userStr = Base64Encode.getFromBase64(userStr);
				String[] userInfo = userStr.split("&");
				if(StringUtils.isNotBlank(userInfo[4])){
					factoryUserId = Integer.parseInt(userInfo[4]);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}		
		return factoryUserId;
	}

	// 从cookies中获取用户是否是VIP
	public static Integer getVip(HttpServletRequest request) {

		Integer vip = null;
		String userStr = WebCookie.getSessionValue(request, "F_INFO");
		if (userStr == null || "".equals(userStr)) {
			userStr = WebCookie.cookie(request, "F_INFO");
		}
		if (!("".equals(userStr) || userStr == null)) {
			try {
				userStr = Base64Encode.getFromBase64(userStr);
				String[] userInfo = userStr.split("&");
				if(StringUtils.isNotBlank(userInfo[0])){
					vip = Integer.parseInt(userInfo[0]);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return vip;
	}

	// 从cookies中获取用户语言
	public static String getLanguage(HttpServletRequest request) {
		
		String language = WebCookie.cookie(request, "language");	
		return language;
	}
	
	
	// 从cookies中获取客户类型
	public static Integer getFactoryType(HttpServletRequest request) {
		
		Integer type = null;
		String userStr = WebCookie.getSessionValue(request, "F_INFO");
		if (userStr == null || "".equals(userStr)) {
			userStr = WebCookie.cookie(request, "F_INFO");
		}
		if (!("".equals(userStr) || userStr == null)) {
			try {
				userStr = Base64Encode.getFromBase64(userStr);
				String[] userInfo = userStr.split("&");
				if(StringUtils.isNotBlank(userInfo[2])){
					type = Integer.parseInt(userInfo[2]);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return type;
	}
	
	
	
	
	
	
	
	// 从cookies中获取工厂所在地
	public static String getStateName(HttpServletRequest request) {

		String state = null;
		String userStr = WebCookie.getSessionValue(request, "F_INFO");
		if (userStr == null || "".equals(userStr)) {
			userStr = WebCookie.cookie(request, "F_INFO");
		}
		if (!("".equals(userStr) || userStr == null)) {
			try {
				userStr = Base64Encode.getFromBase64(userStr);
				String[] userInfo = userStr.split("&");
				state = userInfo[1];
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return state;
	}

	// 从cookies中获取process
	public static String getProcess(HttpServletRequest request) {

		String str = WebCookie.cookie(request, "FSM_P");

		if (!("".equals(str) || str == null)) {
			try {
				str = URLDecoder.decode(str, "utf-8");
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return str;
	}

	// 从cookies中获取地区（0：所有地区 1：江浙沪 2：广州、深圳、福建）
	public static Integer getState(HttpServletRequest request) {
		Integer state = null;
		String str = WebCookie.cookie(request, "FSM_S");

		if (!("".equals(str) || str == null)) {
			state = Integer.parseInt(str);
		}
		return state;
	}

	// 从cookies中获取认证信息
	public static String getQualification(HttpServletRequest request) {
		String str = WebCookie.cookie(request, "FSM_Q");

		if (!("".equals(str) || str == null)) {
			try {
				str = URLDecoder.decode(str, "utf-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return str;
	}

	// 从cookies中获取询盘id
	public static String getQuoteOrderId(HttpServletRequest request) {
		String str = WebCookie.cookie(request, "orderId");
		return str;
	}

	// 从session中获取访问链接
	public static Object getPayUrl(HttpServletRequest request) {

		HttpSession session = request.getSession();
		Object url = session.getAttribute("payUrl");
		return url;
	}
	// 从session中获取历史访问链接
	public static String getHistroyUrl(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		String url = null;
		if(session.getAttribute("historyUrl") != null && !"".equals(session.getAttribute("historyUrl"))){
			url = session.getAttribute("historyUrl").toString();
		}
		return url;
	}

	
	
	// 从session中获取openid
	public static String getOpenid(HttpServletRequest request) {

		HttpSession session = request.getSession();
		String openid = null;
		if(session.getAttribute("openid") != null && !"".equals(session.getAttribute("openid"))){
			openid = session.getAttribute("openid").toString();
		}
		return openid;
	}
	
}
