package com.cbt.controller;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.Cookie;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.cbt.entity.FactoryInfo;
import com.cbt.entity.FactoryUser;
import com.cbt.entity.LoginLog;
import com.cbt.entity.Qualification;
import com.cbt.enums.StateEnum;
import com.cbt.service.BuyerInfoService;
import com.cbt.service.FactoryInfoService;
import com.cbt.service.FactoryUserService;
import com.cbt.service.LoginLogService;
import com.cbt.service.QualificationService;
import com.cbt.service.UserService;
import com.cbt.util.Base64Encode;
import com.cbt.util.DateFormat;
import com.cbt.util.GetCityUtil;
import com.cbt.util.GetServerPathUtil;
import com.cbt.util.Md5Util;
import com.cbt.util.SecurityHelper;
import com.cbt.util.SendHttpsRequestUtil;
import com.cbt.util.WebCookie;
import com.cbt.util.WriteProp;


/**
 * Edit 修改登录注册时，用户邮箱，密码过长导致保存cookie报错处理
 * 将邮箱，工厂ID
 * @ClassName AccountController 
 * @Description
 * @author polo
 * @date 2018年4月4日 下午3:32:01
 */

@RequestMapping("/account")
@Controller
public class AccountController {

	  @Autowired
      private UserService userService;      
	  @Autowired
	  private FactoryInfoService factoryInfoService;    
	  @Autowired
	  private LoginLogService loginLogService;    
	  @Autowired
	  private BuyerInfoService buyerInfoService;  
	  @Autowired
	  private QualificationService qualificationService;  
	  @Autowired
	  private FactoryUserService factoryUserService;  

	  
	  private Logger logger = LoggerFactory.getLogger(this.getClass());
      
      
      private static final int ISVIP = 100;
      
      private static final int PERMISSION = 1;

      private static FactoryInfo factoryInfo1 = new FactoryInfo();
      
      //供应商工艺和地区
	  private static String process;
	  private static Integer state;

      
      
		 public static String getProcess() {
			return process;
		}

		public static void setProcess(String process) {
			AccountController.process = process;
		}


	    public static Integer getState() {
			return state;
		}

		public static void setState(Integer state) {
			AccountController.state = state;
		}

	/**
	   * 登录
	   * @param request
	   * @param response
	   * @return
	   */
      @RequestMapping("/showInquiry.do")
      @ResponseBody
      public JsonResult<String> login(HttpServletRequest request,HttpServletResponse response){
		  
		  String loginEmail = "";
		  String userName = "";
          String pwd = "";
		  try { 	      
			  
	          /*
	           * 获取userInfo（加密的用户登录邮箱和密码）
	           */   	
    	      if(StringUtils.isBlank(loginEmail)){
    	    	  loginEmail = WebCookie.getLoginEmail(request);
    	      }
    	      if(StringUtils.isBlank(pwd)){
    	    	  pwd = WebCookie.getPwd(request);
    	      }
    	      if(!(request.getParameter("loginEmail") == null || "".equals(request.getParameter("loginEmail")))){
    	    	  loginEmail = request.getParameter("loginEmail");
    	      }
    	      if(!(request.getParameter("pwd") == null || "".equals(request.getParameter("pwd")))){
    	    	  pwd = request.getParameter("pwd");
    	      }
    	      
    	      
    	      FactoryInfo factoryInfo = factoryInfoService.login(loginEmail, pwd);
    	      FactoryUser factoryUser = factoryUserService.selectByLoginEmail(loginEmail);
   	      
    	      
    		  //保存cookie token
    		  String now = String.valueOf(System.currentTimeMillis());
    		  String token = Md5Util.encoder(now);
    		  Cookie cookie = new Cookie("token",now+"|"+token);
    		  cookie.setPath("/");
    		  cookie.setMaxAge(60*60*24*365);
    		  response.addCookie(cookie);
    		  
              String factoryId = factoryInfo.getFactoryId(); 		  
    		  pwd = factoryUser.getPwd();
    		  Integer state = null;
    		  String province = "";
    		  province = factoryInfo.getState();
    		  if(StringUtils.isNotBlank(factoryInfo.getState())){
    			  state =  StateEnum.getCode(factoryInfo.getState()).getCode();	 
        		  Cookie pro2 = new Cookie("FSM_S",state.toString());           
        		  pro2.setPath("/");
        		  pro2.setMaxAge(60*60*24*365);
        		  response.addCookie(pro2); 		 
    		  }  		    
    		  userName = factoryUser.getUsername();
  			  //存放登录数据到Cookie   
    		  //添加客户类型（0：供应商加采购商  1：供应商  2：采购商）    2017/11/22   polo
    		  String str1 = 1 +"&"+ 1 +"&"+pwd+"&"+factoryUser.getPermission()+"&"+factoryUser.getId();
    		  String str2 = factoryInfo.getIsVip() +"&"+province + "&"+ factoryInfo.getFactoryType();  		 
    		  
    		  
    		  Cookie userCookie = new Cookie("factoryInfo",Base64Encode.getBase64(str1));      
    		  userCookie.setPath("/");
    		  userCookie.setMaxAge(60*60*24*365);
    		  response.addCookie(userCookie);  
    		  
    		  //存放客户id
    		  Cookie userCookie3 = new Cookie("F_ID",Base64Encode.getBase64(factoryId));      
    		  userCookie3.setPath("/");
    		  userCookie3.setMaxAge(60*60*24*365);
    		  response.addCookie(userCookie3);    	
    		  //存放客户Email
    		  Cookie userCookie4 = new Cookie("F_M",Base64Encode.getBase64(loginEmail));      
    		  userCookie4.setPath("/");
    		  userCookie4.setMaxAge(60*60*24*365);
    		  response.addCookie(userCookie4);    	
    		  
    		  Cookie userCookie2 = new Cookie("F_INFO",Base64Encode.getBase64(str2));      
    		  userCookie2.setPath("/");
    		  userCookie2.setMaxAge(60*60*24*365);
    		  response.addCookie(userCookie2);    		  
    		  
    		  //客户登录信息存放到session
    		  HttpSession session = request.getSession();
    		  session.setAttribute("factoryInfo",Base64Encode.getBase64(str1));
    		  session.setMaxInactiveInterval(60*60*24*365);
    		  HttpSession session2 = request.getSession();
    		  session2.setAttribute("F_INFO",Base64Encode.getBase64(str2));
    		  session2.setMaxInactiveInterval(60*60*24*365);
    		  HttpSession session3 = request.getSession();
    		  session3.setAttribute("F_ID",Base64Encode.getBase64(factoryId));
    		  session3.setMaxInactiveInterval(60*60*24*365);
    		  HttpSession session4 = request.getSession();
    		  session4.setAttribute("F_M",Base64Encode.getBase64(loginEmail));
    		  session4.setMaxInactiveInterval(60*60*24*365);
    		  
    		  
    		  
    		  //客户公司名存放到session
    		  session.setAttribute("factoryName",factoryInfo.getFactoryName());
    		  session.setMaxInactiveInterval(60*60*24*365);
    		  
    		  
    		  //客户名
    		  Cookie userCookie1 = new Cookie("userName",Base64Encode.getBase64(userName));      
    		  userCookie1.setPath("/");
    		  userCookie1.setMaxAge(60*60*24*365);
    		  response.addCookie(userCookie1);
		  
    		  
    		  AccountController.setProcess(factoryInfo.getMainProcess());  		 
    		  AccountController.setState(state);   
             
    		  
              StringBuffer qs = new StringBuffer();
              String q = null;
              List<Qualification> qualification = qualificationService.queryByFactory(factoryId);
              if(qualification != null && qualification.size() != 0){
            	  for (Qualification qualification2 : qualification) {
            		  qs.append(qualification2.getQualificationName()+",");
				  }
    			  if(qs.length()>1){
    			  q = qs.substring(0, qs.length() -1);
    			  }
    			  
    			  Cookie pro3 = new Cookie("FSM_Q",URLEncoder.encode(q , "UTF-8"));           
        		  pro3.setPath("/");
        		  pro3.setMaxAge(60*60*24*365);
        		  response.addCookie(pro3);
              }
              
             if(StringUtils.isNotBlank(factoryInfo.getMainProcess())){
            	  //存放process、地区 、资格认证
	       		  Cookie pro1 = new Cookie("FSM_P",URLEncoder.encode(factoryInfo.getMainProcess(), "UTF-8"));           
	       		  pro1.setPath("/");
	       		  pro1.setMaxAge(60*60*24*365);
	       		  response.addCookie(pro1); 
             }
             
             //历史打开链接（发送消息、报价）
             String histroyUrl = WebCookie.getHistroyUrl(request);
             if(StringUtils.isNotBlank(histroyUrl)){
            	 return new JsonResult<String>(0,"登录成功",histroyUrl) ; 	
             }
             
             //报价详情页
             String quoteDetailUrl = WebCookie.cookie(request, "quoteDetailUrl");
    		   if(StringUtils.isNotBlank(quoteDetailUrl)){
    			  return new JsonResult<String>(0,"登录成功",quoteDetailUrl) ; 	
    		 }  		   
    		 String language = WebCookie.getLanguage(request);
    		 if("en".equals(language)){
    			 return new JsonResult<String>(0,"登录成功","/en/index.html");    
    		 }else{
    			 return new JsonResult<String>(0,"登录成功","/zh/business_view.html");    
    		 }
    		
    		   
			} catch (Exception e) {		
				    logger.error("=========login===========",e);
					return new JsonResult<String>(1,e.getMessage()) ;
			}finally{
				  try {
					LoginLog loginLog = new LoginLog();
					  loginLog.setLoginEmail(loginEmail);
					  loginLog.setLoginIp(request.getRemoteAddr());
					  loginLog.setLoginStatus(0);
					  loginLog.setLoginTime(DateFormat.format());
					  loginLog.setUsername(userName);
					  loginLogService.insertLoginLog(loginLog);
				} catch (Exception e) {					
					logger.error("=========login(保存登录日志)===========",e);
					LoginLog loginLog = new LoginLog();
					loginLog.setLoginEmail(loginEmail);				
					loginLog.setLoginFailTime(DateFormat.format());
					loginLog.setLoginIp(request.getRemoteAddr());
					loginLog.setLoginStatus(1);
					loginLog.setUsername(userName);
					loginLog.setErrorInfo(e.toString());
					loginLogService.insertLoginLog(loginLog);
				}
			}

		   
      }
      
    
      
      
  	/**
	   * 登录
	   * @param request
	   * @param response
	   * @return
	   */
      @RequestMapping("/showInquiryWechat.do")
      @ResponseBody
      public JsonResult<String> wechat_login(HttpServletRequest request,HttpServletResponse response){
		  
		  String loginEmail = "";
		  String userName = "";
          String pwd = "";
		  try { 	      

	          
	          /*
	           * 获取userInfo（加密的用户登录邮箱和密码）
	           */   	
    	      if(StringUtils.isBlank(loginEmail)){
    	    	  loginEmail = WebCookie.getLoginEmail(request);
    	      }
    	      if(StringUtils.isBlank(pwd)){
    	    	  pwd = WebCookie.getPwd(request);
    	      }
    	      if(!(request.getParameter("loginEmail") == null || "".equals(request.getParameter("loginEmail")))){
    	    	  loginEmail = request.getParameter("loginEmail");
    	      }
    	      if(!(request.getParameter("pwd") == null || "".equals(request.getParameter("pwd")))){
    	    	  pwd = request.getParameter("pwd");
    	      }
    	      
    	      
    	      FactoryInfo factoryInfo = factoryInfoService.login(loginEmail, pwd);
    	      FactoryUser factoryUser = factoryUserService.selectByLoginEmail(loginEmail);
    	      
    	      
    	      
    	      
    	      String openid = WebCookie.getOpenid(request);
			  logger.debug("<<<<<<<<<<<<<<获取openid>>>>>>>>>>>>>>"+ openid);
			  
		        /* ===============使用code换取页面授权接口调用凭证access_token，可得到openid=========
				 * = =======
				 */
				if(StringUtils.isNotBlank(openid)){
					 //保存客户openid
					 //如果已存储则不进行保存操作
		    		  if(StringUtils.isBlank(factoryInfo.getOpenid())){
			    		  factoryUser.setOpenid(openid);
			    		  factoryInfo.setOpenid(openid);
			    		  factoryInfoService.updateByPrimaryKeySelective(factoryInfo,factoryUser);
		    		  }
				}
    	      
    	      

    		  //保存cookie token
    		  String now = String.valueOf(System.currentTimeMillis());
    		  String token = Md5Util.encoder(now);
    		  Cookie cookie = new Cookie("token",now+"|"+token);
    		  cookie.setPath("/");
    		  cookie.setMaxAge(60*60*24*365);
    		  response.addCookie(cookie);
              String factoryId = factoryInfo.getFactoryId(); 		  
    		  pwd = factoryUser.getPwd();
    		  Integer state = null;
    		  String province = "";
    		  province = factoryInfo.getState();
    		  if(StringUtils.isNotBlank(factoryInfo.getState())){
    			  state =  StateEnum.getCode(factoryInfo.getState()).getCode();	 
        		  Cookie pro2 = new Cookie("FSM_S",state.toString());           
        		  pro2.setPath("/");
        		  pro2.setMaxAge(60*60*24*365);
        		  response.addCookie(pro2); 		 
    		  }  		    
    		  userName = factoryUser.getUsername();
  			  //存放登录数据到Cookie   
    		  //添加客户类型（0：供应商加采购商  1：供应商  2：采购商）    2017/11/22   polo
    		  String str1 = 1 +"&"+ 1 +"&"+pwd+"&"+factoryUser.getPermission()+"&"+factoryUser.getId();
    		  String str2 = factoryInfo.getIsVip() +"&"+province + "&"+ factoryInfo.getFactoryType();  		 
    		  
    		  
    		  Cookie userCookie = new Cookie("factoryInfo",Base64Encode.getBase64(str1));      
    		  userCookie.setPath("/");
    		  userCookie.setMaxAge(60*60*24*365);
    		  response.addCookie(userCookie);  
    		  
    		  //存放客户id
    		  Cookie userCookie3 = new Cookie("F_ID",Base64Encode.getBase64(factoryId));      
    		  userCookie3.setPath("/");
    		  userCookie3.setMaxAge(60*60*24*365);
    		  response.addCookie(userCookie3);    	
    		  //存放客户Email
    		  Cookie userCookie4 = new Cookie("F_M",Base64Encode.getBase64(loginEmail));      
    		  userCookie4.setPath("/");
    		  userCookie4.setMaxAge(60*60*24*365);
    		  response.addCookie(userCookie4);    	
    		  
    		  Cookie userCookie2 = new Cookie("F_INFO",Base64Encode.getBase64(str2));      
    		  userCookie2.setPath("/");
    		  userCookie2.setMaxAge(60*60*24*365);
    		  response.addCookie(userCookie2);    		  
    		  
    		  //客户登录信息存放到session
    		  HttpSession session = request.getSession();
    		  session.setAttribute("factoryInfo",Base64Encode.getBase64(str1));
    		  session.setMaxInactiveInterval(60*60*24*365);
    		  HttpSession session2 = request.getSession();
    		  session2.setAttribute("F_INFO",Base64Encode.getBase64(str2));
    		  session2.setMaxInactiveInterval(60*60*24*365);
    		  HttpSession session3 = request.getSession();
    		  session3.setAttribute("F_ID",Base64Encode.getBase64(factoryId));
    		  session3.setMaxInactiveInterval(60*60*24*365);
    		  HttpSession session4 = request.getSession();
    		  session4.setAttribute("F_M",Base64Encode.getBase64(loginEmail));
    		  session4.setMaxInactiveInterval(60*60*24*365);
    		  
    		  
    		  
    		  //客户公司名存放到session
    		  session.setAttribute("factoryName",factoryInfo.getFactoryName());
    		  session.setMaxInactiveInterval(60*60*24*365);
    		  
    		  
    		  //客户名
    		  Cookie userCookie1 = new Cookie("userName",Base64Encode.getBase64(userName));      
    		  userCookie1.setPath("/");
    		  userCookie1.setMaxAge(60*60*24*365);
    		  response.addCookie(userCookie1);
		  
    		  
    		  AccountController.setProcess(factoryInfo.getMainProcess());  		 
    		  AccountController.setState(state);   
             
    		  
              StringBuffer qs = new StringBuffer();
              String q = null;
              List<Qualification> qualification = qualificationService.queryByFactory(factoryId);
              if(qualification != null && qualification.size() != 0){
            	  for (Qualification qualification2 : qualification) {
            		  qs.append(qualification2.getQualificationName()+",");
				  }
    			  if(qs.length()>1){
    			  q = qs.substring(0, qs.length() -1);
    			  }
    			  
    			  Cookie pro3 = new Cookie("FSM_Q",URLEncoder.encode(q , "UTF-8"));           
        		  pro3.setPath("/");
        		  pro3.setMaxAge(60*60*24*365);
        		  response.addCookie(pro3);
              }
              
             if(StringUtils.isNotBlank(factoryInfo.getMainProcess())){
            	  //存放process、地区 、资格认证
	       		  Cookie pro1 = new Cookie("FSM_P",URLEncoder.encode(factoryInfo.getMainProcess(), "UTF-8"));           
	       		  pro1.setPath("/");
	       		  pro1.setMaxAge(60*60*24*365);
	       		  response.addCookie(pro1); 
             }
             
             //历史打开链接（发送消息、报价）
             String histroyUrl = WebCookie.getHistroyUrl(request);
             if(StringUtils.isNotBlank(histroyUrl)){
            	 return new JsonResult<String>(0,"登录成功",histroyUrl) ; 	
             }	   
    		 return new JsonResult<String>(0,"登录成功","/m-zh/quote_list.html");    

    		
    		   
			} catch (Exception e) {		
				    logger.error("=========login===========",e);
					return new JsonResult<String>(1,e.getMessage()) ;
			}finally{
				  try {
					LoginLog loginLog = new LoginLog();
					  loginLog.setLoginEmail(loginEmail);
					  loginLog.setLoginIp(request.getRemoteAddr());
					  loginLog.setLoginStatus(0);
					  loginLog.setLoginTime(DateFormat.format());
					  loginLog.setUsername(userName);
					  loginLogService.insertLoginLog(loginLog);
				} catch (Exception e) {					
					logger.error("=========login(保存登录日志)===========",e);
					LoginLog loginLog = new LoginLog();
					loginLog.setLoginEmail(loginEmail);				
					loginLog.setLoginFailTime(DateFormat.format());
					loginLog.setLoginIp(request.getRemoteAddr());
					loginLog.setLoginStatus(1);
					loginLog.setUsername(userName);
					loginLog.setErrorInfo(e.toString());
					loginLogService.insertLoginLog(loginLog);
				}
			}

		   
      }
      
      
      
      /**
       * 忘记密码
       * @param request
       * @param response
       * @return
       */
      @ResponseBody
      @RequestMapping("/recoverPwd.do")
      public JsonResult<String> recoverPwd(HttpServletRequest request,HttpServletResponse response){
    	  
    	  String result1 = "";
    	  try {
    		  
    		  //获取当前语言
    		  String language = WebCookie.getLanguage(request);
    		  
			  String email = request.getParameter("email");
			  if(email == null || "".equals(email)){
				  throw new RuntimeException("邮箱不能为空");
			  }
		      PrintWriter out = null;			  
		      BufferedReader in = null;
		      
		      String validateCode = DateFormat.format();
		      validateCode = Base64Encode.getBase64(validateCode);
		      FactoryUser factoryUser = factoryUserService.selectByLoginEmail(email);
		      if(factoryUser == null || "".equals(factoryUser)){
		    	  return new JsonResult<String>(1,"邮箱不存在");	 
		      }
		      
		      //如果选择的是英语，发送为英文页面
		      String getPwdUrl = null;
		      if("en".equals(language)){
		    	  getPwdUrl = GetServerPathUtil.getServerPath()+"/en/reset.html?email="+email+"&validateCode="+validateCode;  
		      }else{
			      getPwdUrl = GetServerPathUtil.getServerPath()+"/zh/reset.html?email="+email+"&validateCode="+validateCode;  
		      }
//		      String getPwdUrl = "http://192.168.1.151:8080/fastermake/zh/reset.html?email="+email+"&validateCode="+validateCode;
		      getPwdUrl = URLEncoder.encode(getPwdUrl, "utf-8");
		      try {
		      	
		          URL realUrl = new URL(GetServerPathUtil.getNbmailPath()+"/NBEmail/helpServlet?action=SendEmail1&className=ExternalInterfaceServlet");
//		          URL realUrl = new URL("http://192.168.1.62:8080/NBEmail/helpServlet?action=SendEmail1&className=ExternalInterfaceServlet");
		          // 打开和URL之间的连接
		          URLConnection conn = realUrl.openConnection();
		          // 设置通用的请求属性
		         // conn.setRequestProperty("accept", "*/*");
		          conn.setRequestProperty("connection", "Keep-Alive");
		          conn.setRequestProperty("user-agent",
		                  "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
		          // 发送POST请求必须设置如下两行
		          conn.setDoOutput(true);
		          conn.setDoInput(true);
		          // 获取URLConnection对象对应的输出流
		          out = new PrintWriter(conn.getOutputStream());
		          // 发送请求参数emaillAddress, sm,map,path
		          out.print("&email="+email+"&&title="+URLEncoder.encode("Account update", "utf-8")+"&content="+getPwdUrl);
		          // flush输出流的缓冲
		          out.flush();
		          // 定义BufferedReader输入流来读取URL的响应
		          in = new BufferedReader(
		                  new InputStreamReader(conn.getInputStream()));
		          String line;
		          while ((line = in.readLine()) != null) {
		              result1 += line;
		          }
		          result1 = new String(result1.getBytes("gbk"),"utf-8");
		      } catch (Exception e) {
		    	  logger.error("=========recoverPwd=========",e);
		    	 return new JsonResult<String>(1,e.getMessage());
		      }
			  
		} catch (Exception e) {			
			logger.error("=========recoverPwd=========",e);
			return new JsonResult<String>(1,e.getMessage());			
		}
     	  return new JsonResult<String>(0,result1);
      }

      
      /**
       * 重置密码
       * @param request
       * @param response
       * @return
       */
      @ResponseBody
      @RequestMapping("/resetPwd.do")
      public JsonResult<String> resetPwd(HttpServletRequest request,HttpServletResponse response){
    	  
    	  try {
			  String pwd = request.getParameter("pwd");
			  String email = request.getParameter("email");
			  FactoryUser factoryUser = factoryUserService.selectByLoginEmail(email);
			  factoryUser.setPwd(pwd);
			  factoryUser.setUpdateTime(DateFormat.format());
			  factoryUserService.updateByPrimaryKeySelective(factoryUser);
			  return new JsonResult<String>(0,"修改成功");
		} catch (Exception e) {
			logger.error("======resetPwd=====",e);
			return new JsonResult<String>(1,"修改失败");
		}    	     	
      }
      
      
      /**
       * 切换语言选择
       * @param request
       * @param response
       * @return
       */
      @ResponseBody
      @RequestMapping("/changeLanguage.do")
      public JsonResult<String> changeLanguage(HttpServletRequest request,HttpServletResponse response){
    	  
    	  try {
    		  String language = request.getParameter("language");
              if(StringUtils.isNotBlank(language)){
            	  Cookie cookie = new Cookie("language",language);      
            	  cookie.setPath("/");
            	  cookie.setMaxAge(60*60*24*365);
        		  response.addCookie(cookie);  
              }
    		  return new JsonResult<String>(0,"修改成功");
    	  } catch (Exception e) {
    		  logger.error("=========changeLanguage=========",e);
    		  return new JsonResult<String>(1,"修改失败");
    	  }    	     	
      }
      
      
      /**
       * 获取历史记录
       * @param request
       * @param response
       * @return
       */
      @ResponseBody
      @RequestMapping("/addHistoryUrl.do")
      public JsonResult<String> addHistoryUrl(HttpServletRequest request,HttpServletResponse response){
    	  
    	  try {
    		  String url = request.getParameter("url");
              if(StringUtils.isNotBlank(url)){
                   HttpSession session = request.getSession();
                   session.setAttribute("historyUrl", url);
                   session.setMaxInactiveInterval(30*60);
              }
    		  return new JsonResult<String>(0,"保存成功");
    	  } catch (Exception e) {
    		  e.printStackTrace();
    		  return new JsonResult<String>(1,"保存失败");
    	  }    	     	
      }
      
      
      /**
       * 清空cookie
       * @param request
       * @param response
       * @return
       */
      @ResponseBody
      @RequestMapping("/clearCookie.do")
      public JsonResult<String> clearCookie(HttpServletRequest request,HttpServletResponse response){
    	  
    	  Cookie cookie = new Cookie("factoryInfo", null);  
          cookie.setMaxAge(0);  
          cookie.setPath("/");//删除cookie   
          response.addCookie(cookie);  
          Cookie cookie7 = new Cookie("F_INFO", null);  
          cookie7.setMaxAge(0);  
          cookie7.setPath("/");//删除cookie   
          response.addCookie(cookie7);  
          
          Cookie cookie8 = new Cookie("F_ID", null);  
          cookie8.setMaxAge(0);  
          cookie8.setPath("/");//删除cookie   
          response.addCookie(cookie8);  
          
          Cookie cookie9 = new Cookie("F_M", null);  
          cookie9.setMaxAge(0);  
          cookie9.setPath("/");//删除cookie   
          response.addCookie(cookie9);  
          
          Cookie cookie6 = new Cookie("userName", null);  
          cookie6.setMaxAge(0);  
          cookie6.setPath("/");//删除cookie   
          response.addCookie(cookie6);  
          Cookie cookie1 = new Cookie("FSM_P", null);  
          cookie1.setMaxAge(0);  
          cookie1.setPath("/");//删除cookie      
          response.addCookie(cookie1);  
          Cookie cookie2 = new Cookie("FSM_Q", null);  
          cookie2.setMaxAge(0);  
          cookie2.setPath("/");//删除cookie      
          response.addCookie(cookie2);  
          Cookie cookie3 = new Cookie("FSM_REM", null);  
          cookie3.setMaxAge(0);  
          cookie3.setPath("/zh/");//删除cookie      
          response.addCookie(cookie3);  
          Cookie cookie4 = new Cookie("FSM_S", null);  
          cookie4.setMaxAge(0);  
          cookie4.setPath("/");//删除cookie   
          response.addCookie(cookie4);  
          Cookie cookie5 = new Cookie("token", null);  
          cookie5.setMaxAge(0);  
          cookie5.setPath("/");//删除cookie  
          response.addCookie(cookie5); 
          Cookie urlCookie = new Cookie("orderId","");
		  urlCookie.setPath("/");
		  urlCookie.setMaxAge(0);
		  response.addCookie(urlCookie); 
          
          
          HttpSession session = request.getSession();
          session.invalidate();
          
          return new JsonResult<String>(0,"删除成功");
    	  
      }
      
      
      
      
      /**
       * 工厂注册（采购商、供应商通用）
       * @param request
       * @param response
       * @return
     * @throws Exception 
       */
      @ResponseBody
      @RequestMapping("/supplier_register.do")
      public JsonResult<String> factoryRegister(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	  
    	  try {
		  
    		  
    		  FactoryInfo factoryInfo = new FactoryInfo();
    		  String factoryName = "";
    		  if(!(request.getParameter("factoryName") == null || "".equals(request.getParameter("factoryName")))){
    			  factoryName = request.getParameter("factoryName");
    		  } 		  
    		  String factoryAdminEmail = "";
    		  if(!(request.getParameter("email") == null || "".equals(request.getParameter("email")))){
    			  factoryAdminEmail = request.getParameter("email");
    		  }else{
    			  
    			  String language = WebCookie.getLanguage(request);
    			  if("en".equals(language)){
    				  response.sendRedirect("/en/register_en.html");
    			  }else{
    				  response.sendRedirect("/zh/register_zh.html");
    			  }   			  
    		  }
    		  String userName = "";
    		  if(!(request.getParameter("userName") == null || "".equals(request.getParameter("userName")))){
    			  userName = request.getParameter("userName");
    		  }     		  
    		  if(!(request.getParameter("mobilePhone") == null || "".equals(request.getParameter("mobilePhone")))){
    			  factoryInfo.setMobile(request.getParameter("mobilePhone"));
    		  }     		  
    		  String factoryAdminPwd = "";
    		  if(!(request.getParameter("pwd") == null || "".equals(request.getParameter("pwd")))){
    			  factoryAdminPwd = request.getParameter("pwd");
    		  }     
    		  if(!(request.getParameter("factoryType") == null || "".equals(request.getParameter("factoryType")))){
    			  factoryInfo.setFactoryType(Integer.parseInt(request.getParameter("factoryType")));
    		  }     
    		  if(!(request.getParameter("state") == null || "".equals(request.getParameter("state")))){
    			  factoryInfo.setState(request.getParameter("state"));
    		  }       		  
    		  if(!(request.getParameter("country") == null || "".equals(request.getParameter("country")))){
    			  factoryInfo.setCountry(request.getParameter("country"));
    		  }       		  
    		  if(!(request.getParameter("city") == null || "".equals(request.getParameter("city")))){
    			  factoryInfo.setCity(request.getParameter("city"));
    			  String city = GetCityUtil.getState(request.getParameter("city"));  			  
    			  switch (request.getParameter("state")) {
								case "北京市":	
									factoryInfo.setCity(city);
									factoryInfo.setDistrict(request.getParameter("city"));
									break;				
								case "上海市":	
									factoryInfo.setCity(city);
									factoryInfo.setDistrict(request.getParameter("city"));
									break;				
								case "重庆市":	
									factoryInfo.setCity(city);
									factoryInfo.setDistrict(request.getParameter("city"));
									break;				
								case "天津市":	
									factoryInfo.setCity(city);
									factoryInfo.setDistrict(request.getParameter("city"));
									break;						
								    default:
									break;
				 }
    		  }       		  
    		  if(!(request.getParameter("tel") == null || "".equals(request.getParameter("tel")))){
    			  factoryInfo.setTel(request.getParameter("tel"));
    		  }       		  
    		  String mainProcess = "";
    		  if(!(request.getParameter("mainProcess") == null || "".equals(request.getParameter("mainProcess")))){
    			  mainProcess = request.getParameter("mainProcess");
    		  }     
    		  if(!(request.getParameter("companyIntroduction") == null || "".equals(request.getParameter("companyIntroduction")))){
    			  factoryInfo.setFactoryRemark(request.getParameter("companyIntroduction"));
    		  }     
    		  if(!(request.getParameter("url") == null || "".equals(request.getParameter("url")))){
    			  factoryInfo.setWebsite(request.getParameter("url"));
    		  }     
    		  if(!(request.getParameter("staffNumber") == null || "".equals(request.getParameter("staffNumber")))){
    			  factoryInfo.setStaffNumber(request.getParameter("staffNumber"));
    		  }     
    		  factoryInfo.setFactoryName(factoryName);
    		  factoryInfo.setEmail(factoryAdminEmail);
    		  factoryInfo.setUsername(userName);
    		  factoryInfo.setPwd(factoryAdminPwd);
    		  factoryInfo.setMainProcess(mainProcess);
    		  factoryInfo.setIsVip(ISVIP);
    		  FactoryUser factoryUser = factoryInfoService.insertSelective(factoryInfo);

    		  
    	  	  //存放登录数据到Cookie
    		  //添加客户类型（0：供应商加采购商  1：供应商  2：采购商）    2017/11/22   polo
     		  //String str = factoryInfo.getFactoryId() +"&"+factoryInfo.getEmail() +"&"+factoryInfo.getPwd()+"&"+factoryInfo.getIsVip()+"&"+ factoryInfo.getState()+"&"+ factoryInfo.getFactoryType();  		 
    		  String str1 = 1 +"&"+ 1 +"&"+factoryAdminPwd +"&"+ PERMISSION + "&" + factoryUser.getId();
    		  String str2 = factoryInfo.getIsVip() +"&"+factoryInfo.getState() + "&"+ factoryInfo.getFactoryType();  		 
    		  Cookie userCookie = new Cookie("factoryInfo",Base64Encode.getBase64(str1));      
    		  userCookie.setPath("/");
    		  userCookie.setMaxAge(60*60*24*365);
    		  response.addCookie(userCookie);    		  
    		  Cookie userCookie2 = new Cookie("F_INFO",Base64Encode.getBase64(str2));      
    		  userCookie2.setPath("/");
    		  userCookie2.setMaxAge(60*60*24*365);
    		  response.addCookie(userCookie2);   
    		  //存放客户id
    		  Cookie userCookie3 = new Cookie("F_ID",Base64Encode.getBase64(factoryInfo.getFactoryId()));      
    		  userCookie3.setPath("/");
    		  userCookie3.setMaxAge(60*60*24*365);
    		  response.addCookie(userCookie3);    	
    		  //存放客户Email
    		  Cookie userCookie4 = new Cookie("F_M",Base64Encode.getBase64(factoryAdminEmail));      
    		  userCookie4.setPath("/");
    		  userCookie4.setMaxAge(60*60*24*365);
    		  response.addCookie(userCookie4);    	
    		  
     		  
     		  if(StringUtils.isNotBlank(factoryInfo.getMainProcess())){
           	  //存放process、地区 、资格认证
     			  Cookie pro1 = new Cookie("FSM_P",URLEncoder.encode(factoryInfo.getMainProcess(), "UTF-8"));                
	       		  pro1.setPath("/");
	       		  pro1.setMaxAge(60*60*24*365);
	       		  response.addCookie(pro1); 
              }
     		  
     		  //客户登录信息存放到session
     		  HttpSession session = request.getSession();
     		  session.setAttribute("factoryInfo",Base64Encode.getBase64(str1));
     		  session.setMaxInactiveInterval(60*60*24*365); 
     		  HttpSession session2 = request.getSession();
     		  session2.setAttribute("F_INFO",Base64Encode.getBase64(str2));
     		  session2.setMaxInactiveInterval(60*60*24*365); 
    		  //客户公司名存放到session
    		  session.setAttribute("factoryName",factoryInfo.getFactoryName());
    		  session.setMaxInactiveInterval(60*60*24*365);
     		  
       		  Cookie userCookie1 = new Cookie("userName",Base64Encode.getBase64(userName));       
    		  userCookie1.setPath("/");
    		  userCookie1.setMaxAge(60*60*24*365);
    		  response.addCookie(userCookie1);
     		  

    		  logger.info("注册成功"+DateFormat.format());
     		  return new JsonResult<String>(0,"注册成功",factoryInfo.getFactoryId());
    	  } catch (Exception e) {
  			logger.error("=========factoryRegister=========",e);
  			throw new Exception(e.getMessage());
    	  }   	     	  
    	  
      }
      
 
      
      /**
       * 采购商注册(未使用)
       * @param request
       * @param response
       * @return
     * @throws Exception 
       */
      @ResponseBody
      @RequestMapping("/buyer_register.do")
      public JsonResult<String> buyerRegister(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	  
    	  try {
    		  FactoryInfo factoryInfo = new FactoryInfo();
    		  String factoryName = "";
    		  if(!(request.getParameter("factoryName") == null || "".equals(request.getParameter("factoryName")))){
    			  factoryName = request.getParameter("factoryName");
    		  } 		  
    		  String factoryAdminEmail = "";
    		  if(!(request.getParameter("email") == null || "".equals(request.getParameter("email")))){
    			  factoryAdminEmail = request.getParameter("email");
    		  }else{
    			  response.sendRedirect("/zh/register_zh.html");
    		  }
    		  String userName = "";
    		  if(!(request.getParameter("userName") == null || "".equals(request.getParameter("userName")))){
    			  userName = request.getParameter("userName");
    		  }     		  
    		  if(!(request.getParameter("tel") == null || "".equals(request.getParameter("tel")))){
    			  factoryInfo.setTel(request.getParameter("tel"));
    		  }     		  
    		  String factoryAdminPwd = "";
    		  if(!(request.getParameter("pwd") == null || "".equals(request.getParameter("pwd")))){
    			  factoryAdminPwd = request.getParameter("pwd");
    		  }     
    		  if(!(request.getParameter("state") == null || "".equals(request.getParameter("state")))){
    			  factoryInfo.setState(request.getParameter("state"));
    		  }     
    		  if(!(request.getParameter("city") == null || "".equals(request.getParameter("city")))){
    			  factoryInfo.setCity(request.getParameter("city"));
    		  }  
    		  if(!(request.getParameter("factoryType") == null || "".equals(request.getParameter("factoryType")))){
    			  factoryInfo.setFactoryType(Integer.parseInt(request.getParameter("factoryType")));
    		  }     
    		  if(!(request.getParameter("phone") == null || "".equals(request.getParameter("phone")))){
    			  factoryInfo.setMobile(request.getParameter("phone"));
    		  }     
    		  factoryInfo.setFactoryName(factoryName);
    		  factoryInfo.setEmail(factoryAdminEmail);
    		  factoryInfo.setUsername(userName);
    		  factoryInfo.setPwd(factoryAdminPwd);
    		  factoryInfo.setCreateTime(DateFormat.format());
    		  factoryInfo.setIsVip(ISVIP);
    		  factoryInfoService.insertSelective(factoryInfo);

    		  
    	  		 //存放登录数据到Cookie
      		  String str1 = 1 +"&"+ 1 +"&"+factoryInfo.getPwd();
    		  String str2 = factoryInfo.getIsVip() +"&"+factoryInfo.getState() + "&"+ factoryInfo.getFactoryType();  		 
    		  Cookie userCookie = new Cookie("factoryInfo",Base64Encode.getBase64(str1));      
    		  userCookie.setPath("/");
    		  userCookie.setMaxAge(60*60*24*365);
    		  response.addCookie(userCookie);    		  
    		  Cookie userCookie2 = new Cookie("F_INFO",Base64Encode.getBase64(str2));      
    		  userCookie2.setPath("/");
    		  userCookie2.setMaxAge(60*60*24*365);
    		  response.addCookie(userCookie2); 
    		  
    		  //存放客户id
    		  Cookie userCookie3 = new Cookie("F_ID",Base64Encode.getBase64(factoryInfo.getFactoryId()));      
    		  userCookie3.setPath("/");
    		  userCookie3.setMaxAge(60*60*24*365);
    		  response.addCookie(userCookie3);    	
    		  //存放客户Email
    		  Cookie userCookie4 = new Cookie("F_M",Base64Encode.getBase64(factoryAdminEmail));      
    		  userCookie4.setPath("/");
    		  userCookie4.setMaxAge(60*60*24*365);
    		  response.addCookie(userCookie4);    
     		  
     		  if(StringUtils.isNotBlank(factoryInfo.getMainProcess())){
           	  //存放process、地区 、资格认证
     			  Cookie pro1 = new Cookie("FSM_P",URLEncoder.encode(factoryInfo.getMainProcess(), "UTF-8"));                
	       		  pro1.setPath("/");
	       		  pro1.setMaxAge(60*60*24*365);
	       		  response.addCookie(pro1); 
              }
     		  
     		  //客户登录信息存放到session
     		  HttpSession session = request.getSession();
     		  session.setAttribute("factoryInfo",Base64Encode.getBase64(str1));
     		  session.setMaxInactiveInterval(60*60*24*365); 
     		  HttpSession session2 = request.getSession();
     		  session2.setAttribute("F_INFO",Base64Encode.getBase64(str2));
     		  session2.setMaxInactiveInterval(60*60*24*365); 
    		  //客户公司名存放到session
    		  session.setAttribute("factoryName",factoryInfo.getFactoryName());
    		  session.setMaxInactiveInterval(60*60*24*365);
     		  
       		  Cookie userCookie1 = new Cookie("userName",Base64Encode.getBase64(userName));       
    		  userCookie1.setPath("/");
    		  userCookie1.setMaxAge(60*60*24*365);
    		  response.addCookie(userCookie1);
     		  

    		  logger.info("注册成功"+DateFormat.format());
     		  return new JsonResult<String>(0,"注册成功");
    	  } catch (Exception e) {
    		  logger.error("=========factoryRegister=========",e);
  			throw new Exception(e.getMessage());
    	  }   	     	  
    	  
      }
      
      
      

 
      
      
      /**
       * 验证供应商邮箱是否存在
       * @param request
       * @param response
       * @return
       */
      @ResponseBody
      @RequestMapping("/verifyfEmail.do")
      public JsonResult<String> verifyfEmail(HttpServletRequest request,HttpServletResponse response){
    	  String email = null;    	
    	  if(!(request.getParameter("email") == null || "".equals(request.getParameter("email")))){
    		  email = request.getParameter("email"); 
//    		  FactoryInfo factoryInfo = factoryInfoService.selectByLoginEmail(email);
    		  FactoryUser factoryUser = factoryUserService.selectByLoginEmail(email);
    		  if(!(factoryUser == null || "".equals(factoryUser))){
    			  return new JsonResult<String>(1,"邮箱已存在");
    		  }
    	  }
    	  return new JsonResult<String>(0,"可以使用");
      } 
      

      
      
      /**
       * 工厂注册（factory_id的线程处理）未使用
       * @param factoryInfo
       * @param backUser
       * @throws Exception 
       */
     public void register(FactoryInfo factoryInfo,HttpServletRequest request,HttpServletResponse response) throws Exception{
  		ExecutorService pool = Executors.newSingleThreadExecutor();
           Runnable task1 = new SingelTask();           
  		   pool.execute(task1);		   	   
  		    // 等待已提交的任务全部结束 不再接受新的任务
  		   pool.shutdown(); 
  		   
  		 //存放登录数据到Cookie
 		  String str = factoryInfo.getFactoryId() +"&"+factoryInfo.getEmail() +"&"+factoryInfo.getPwd();  		 
 		  Cookie userCookie = new Cookie("factoryInfo",SecurityHelper.encrypt("factoryInfo", str));           
 		  userCookie.setPath("/");
 		  userCookie.setMaxAge(60*60*24*365);
 		  response.addCookie(userCookie);
 		  
 		  //客户登录信息存放到session
 		  HttpSession session = request.getSession();
 		  session.setAttribute("factoryInfo",SecurityHelper.encrypt("factoryInfo", str));
 		  session.setMaxInactiveInterval(60*60*24*365);
  	   }  	   
  		   
  	  public class SingelTask implements Runnable{ 		  
  		    @Override
  		    public synchronized void run() {
  		     System.out.println(Thread.currentThread().getName() + "正在执行…");
  		     
  		     try {
				Integer id = factoryInfoService.selectMaxId(); 	
				String currentDay = DateFormat.currentDate().replace("-", "");
				   if(id == null || "".equals(id)){
					   id = 1;
				   }else{
					   id = id + 1;
				   }				   
			   factoryInfo1.setFactoryId("f"+currentDay+id);
			   factoryInfoService.updateByPrimaryKeySelective(factoryInfo1);  
				   
			} catch (Exception e) {
				logger.error("====SingelTask========",e);
			}	 		       
  		     System.out.println(Thread.currentThread().getName() + "执行完毕");
  		   }
  		  
  	   }
  	  
  	  
  	  
  	  
  	  
      /**
       * 采购商注册（userid的线程处理）
       * @param factoryInfo
       * @param backUser
       */
      
//     public void register1(User user,ShippingInfo shippingInfo){
//  		ExecutorService pool = Executors.newSingleThreadExecutor();
//           Runnable task1 = new SingelTask1();           
//  		   pool.execute(task1);		   	   
//  		    // 等待已提交的任务全部结束 不再接受新的任务
//  		   pool.shutdown(); 
//  	   }  	   
  		   
//  	  public class SingelTask1 implements Runnable{ 		  
//  		    @Override
//  		    public synchronized void run() {
//  		     System.out.println(Thread.currentThread().getName() + "正在执行…");
//  		     
//  		     try {
//				Integer id = userService.queryMaxId();  		     
//				   if(id == null || "".equals(id)){
//					   id = 1001;
//				   }else{
//					   id = 1001 + id;
//				   }
//				   
//				   user1.setUserid("c"+id);
//				   shippingInfo1.setUserid(("c"+id));
////				   userService.insert(user1,shippingInfo1);
//			} catch (Exception e) {
//				Log.error("=========SingelTask1======= "+e.getMessage());
//				e.printStackTrace();
//			}	
//  		       
//  		     System.out.println(Thread.currentThread().getName() + "执行完毕");
//  		   }
//  		  
//  	   }
  	  

  	  
  	  
  	  
  	  
  	  
  	  
  	  
  	  
}
