package com.cbt.controller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.DigestException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cbt.cache.RedisUtil;
import com.cbt.entity.FactoryInfo;
import com.cbt.entity.FactoryUser;
import com.cbt.entity.QuoteInfo;
import com.cbt.model.DataTemplate;
import com.cbt.model.Item;
import com.cbt.model.MessageTemplate;
import com.cbt.model.SingleAccessToken;
import com.cbt.model.SingleJsapi;
import com.cbt.model.TextMessage;
import com.cbt.model.ValueAndColor;
import com.cbt.model.WechatQRCode;
import com.cbt.service.FactoryInfoService;
import com.cbt.service.FactoryUserService;
import com.cbt.service.QuoteInfoService;
import com.cbt.util.Client;
import com.cbt.util.DateFormat;
import com.cbt.util.GetServerPathUtil;
import com.cbt.util.JsonUtil;
import com.cbt.util.MessageUtil;
import com.cbt.util.SendHttpsRequestUtil;
import com.cbt.util.SignatureUtil;
import com.cbt.util.UploadAndDownloadPathUtil;
import com.cbt.util.WebCookie;
import com.cbt.util.WriteProp;
import com.cbt.wximpl.Wechatimpl;
import com.google.gson.JsonSyntaxException;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 实现类控制
 */

@Controller
@RequestMapping("wimpl")
public class WechatImplController {

	// logo路径
	public static final String LOGO = UploadAndDownloadPathUtil.getQuoteFile() + "qr"+File.separator+"logo.png";
	// 创建二维码  
	public static final String CREATE_TICKET_PATH = "https://api.weixin.qq.com/cgi-bin/qrcode/create";  
	// 通过ticket换取二维码  
	public static final String SHOW_QRCODE_PATH = "https://mp.weixin.qq.com/cgi-bin/showqrcode"; 
	// 永久二维码  
	public static final String QR_LIMIT_SCENE = "QR_LIMIT_SCENE";//0  
	// 永久二维码(字符串)  
	public static final String QR_LIMIT_STR_SCENE = "QR_LIMIT_STR_SCENE";//1  
	
	  //获取域名
	  private static final String SERVER_PATH = GetServerPathUtil.getServerPath();
	
	
    private static int width = 140;              //二维码宽度  
    private static int height = 140;             //二维码高度  
    private static int onColor = 0xFF000000;     //前景色  
    private static int offColor = 0xFFFFFFFF;    //背景色  
    private static int margin = 1;               //白边大小，取值范围0~4  
    private static ErrorCorrectionLevel level = ErrorCorrectionLevel.L;  //二维码容错率  
	
    
    @Autowired
    private QuoteInfoService quoteInfoService;
    @Autowired
    private FactoryInfoService factoryInfoService;
    @Autowired
    private FactoryUserService factoryUserService;
    
    
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("menu")
	public String menu(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("menu");
		HttpSession session = req.getSession();
		session.setAttribute("name", "list");		
		
		return null;
	}

	@RequestMapping("cemenu")
	public String createMenu(HttpServletRequest req, HttpServletResponse resp) {
		// String name = req.getSession().getAttribute("name").toString();
		// System.out.println(name);
		Wechatimpl wechatimpl = new Wechatimpl();
		wechatimpl.createmenu();
		return null;
	}

	@RequestMapping("autosend")
	public String autosend(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("autosend");
		HttpSession session = req.getSession();
		session.setAttribute("name", "zhangsan");
		// Wechatimpl.getautosend();
		return null;
	}

	// 处理微信post过来的数据
	@RequestMapping("/saxmessage.do")
	public String saxmessage(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		resp.setContentType("text/json;charset=utf-8");
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = resp.getWriter();
		String result = "";
		String echostr = req.getParameter("echostr");// 获取接入微信接口凭证
		if (StringUtils.isNotEmpty(echostr) && echostr.length() > 1) {
			result = echostr;
			resp.getWriter().print(result);
		} else {
			// 消息转换
			Map<String, String> map = MessageUtil.xmlToMap(req);
			String fromUserName = map.get("FromUserName");
			String toUserName = map.get("ToUserName");
			String createTime = map.get("CreateTime");
			String msgType = MessageUtil.domethod(map.get("MsgType"));
			String content = map.get("Content");
			String event = map.get("Event");
			String eventKey = map.get("EventKey");
			String ArticleCount = map.get("ArticleCount");
			String Articles = map.get("Articles");
			String Title = map.get("Title");
			String Description = map.get("Description");
			String PicUrl = map.get("PicUrl");
			String Url = map.get("Url");
			// 微信处理流程
			TextMessage text = new TextMessage();
			text.setCreateTime(createTime);
			text.setToUserName(fromUserName);
			text.setFromUserName(toUserName);
			// 文本消息、图片消息、语音消息、视频消息、音乐消息、图文消息
			if ("text".equals(msgType) || "image".equals(msgType)
					|| "voice".equals(msgType) || "video".equals(msgType)
					|| "music".equals(msgType) || "news".equals(msgType)) {
				 text.setMsgType("news");
				 Item item = new Item();
				 item.setDescription("点击查看询盘"); //图文消息的描述
				 item.setPicUrl(SERVER_PATH+"/static_img/fastermake/images/wechatLogo.jpg"); //图文消息图片地址
				 item.setTitle("欢迎使用快制造"); //图文消息标题
				 item.setUrl(SERVER_PATH); //图文url链接
				 
				 Item item1 = new Item();
				 item1.setDescription("工厂接单就去 www.kuaizhizao.com 最专业的制造业订单交易平台，每天更新几十条订单，让接单不再是难事！ 都是制造业的工厂、公司，多交朋友，才能接到订单！ 网上接单，就选择快制造"); //图文消息的描述
				 item1.setPicUrl(SERVER_PATH+"/static_img/fastermake/images/wechat_img.png"); //图文消息图片地址
				 item1.setTitle("制造业订单哪里找，就上快制造"); //图文消息标题
				 item1.setUrl("https://mp.weixin.qq.com/s?__biz=MzU2MTQ3MTA5MQ==&mid=100000014&idx=1&sn=c3704713b720d1d940f0dbf0fa413fc8&scene=19#wechat_redirect"); //图文url链接
				 
				 List<Item> list=new ArrayList<Item>();
				 list.add(item);  //这里发送的是单图文，如果需要发送多图文则在这里list中加入多个Article即可！
				 list.add(item1);
				 text.setArticleCount(list.size());
				 text.setArticles(list);
				 result = MessageUtil.textMessageToXml(text);
				 result = result.replace("com.cbt.model.Item", "item");
				
//				text.setMsgType("text");
//				text.setContent("人工服务暂未开启，请进行其他操作！");
//				result = MessageUtil.textMessageToXml(text);
				resp.getWriter().print(result);
				logger.info("关注后推送的消息："+result);
//				 out.print(result);
			} else if ("event".equals(msgType)) {
				if ("subscribe".equals(event)) {					
					 text.setMsgType("news");
					 Item item = new Item();
					 item.setDescription("点击查看询盘"); //图文消息的描述
					 item.setPicUrl(SERVER_PATH+"/static_img/fastermake/images/wechatLogo.jpg"); //图文消息图片地址
					 item.setTitle("欢迎使用快制造"); //图文消息标题
					 item.setUrl(SERVER_PATH); //图文url链接
					 
					 Item item1 = new Item();
					 item1.setDescription("工厂接单就去 www.kuaizhizao.com 最专业的制造业订单交易平台，每天更新几十条订单，让接单不再是难事！ 都是制造业的工厂、公司，多交朋友，才能接到订单！ 网上接单，就选择快制造"); //图文消息的描述
					 item1.setPicUrl(SERVER_PATH+"/static_img/fastermake/images/wechat_img.png"); //图文消息图片地址
					 item1.setTitle("制造业订单哪里找，就上快制造"); //图文消息标题
					 item1.setUrl("https://mp.weixin.qq.com/s?__biz=MzU2MTQ3MTA5MQ==&mid=100000014&idx=1&sn=c3704713b720d1d940f0dbf0fa413fc8&scene=19#wechat_redirect"); //图文url链接
					 
					 List<Item> list=new ArrayList<Item>();
					 list.add(item);  //这里发送的是单图文，如果需要发送多图文则在这里list中加入多个Article即可！
					 list.add(item1);
					 text.setArticleCount(list.size());
					 text.setArticles(list);
					 result = MessageUtil.textMessageToXml(text);
					 result = result.replace("com.cbt.model.Item", "item");
					 logger.info("关注后推送的消息："+result);
					 out.print(result);
				}
			}
			out.close();
		}
		return null;
	}

	@RequestMapping("/signature.do")
	public String signature(String pageUrl, HttpServletRequest req,
			HttpServletResponse resp) {

		/* String ticket = SignatureUtil.getJsapi_ticket(); */
		// logger.debug("进入获取signature方法");
		String ticket = SingleJsapi.getInstance().getJsapiToken()
				.getAccess_token();
		// Log.debug("获取ticket"+ticket);
		String noncestr = SignatureUtil.createNonceStr();
		// Log.debug("获取noncestr"+noncestr);
		int timestamp = SignatureUtil.getTimestamp();
		// Log.debug("获取timestamp"+timestamp);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("jsapi_ticket", ticket);
		map.put("noncestr", noncestr);
		map.put("timestamp", timestamp);
		map.put("url", pageUrl);

		String signature = "";
		try {
			signature = SignatureUtil.SHA1(map);
		} catch (DigestException e) {
			e.printStackTrace();
		}
		map.remove("url");// 封装json对象时删除，页面不需要的参数不封装
		map.remove("jsapi_ticket");// 封装json对象时删除，页面不需要的参数不封装
		map.put("signature", signature);
		map.put("appid", WriteProp.get("appid"));
		JSONObject json = JSONObject.fromObject(map);
		try {
			resp.getWriter().print(json);
			resp.getWriter().close();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 报价成功消息推送
	 * 
	 * @param pageUrl
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping("quote_note")
	public String quoteNote(String orderId, HttpServletRequest req,
			HttpServletResponse resp) {

		try {
			String openid = WebCookie.getOpenid(req);
			if(StringUtils.isBlank(openid)){
				String loginEmail = WebCookie.getLoginEmail(req);
				FactoryUser factoryUser = factoryUserService.selectByLoginEmail(loginEmail);
				openid = factoryUser.getOpenid();
			}
			if(StringUtils.isNotBlank(openid) && StringUtils.isNotBlank(orderId)){
				
				QuoteInfo quoteInfo = quoteInfoService.queryByOrderId(Integer.parseInt(orderId));
				
				String access_token = SingleAccessToken.getInstance().getAccessToken().getToken();

				String message_push_url = WriteProp.get("message_push_url").replace("ACCESS_TOKEN", access_token);
				
				
				MessageTemplate template = new MessageTemplate();
				template.setTouser(openid);
				template.setTemplate_id("q8W_iQwKVXJzJTy697TreymDNdbQxqQwn1phdcLX2EM");
				template.setUrl(SERVER_PATH + "/m-zh/detail.html?orderId="+quoteInfo.getOrderId());

				DataTemplate data = new DataTemplate();
				Map<Object,Object> map = new HashMap<Object, Object>();
				
				ValueAndColor first = new ValueAndColor();
				first.setValue(quoteInfo.getOrderId()+"您已经成功报价");
				first.setColor("#173177");

				ValueAndColor keyword1 = new ValueAndColor();
				keyword1.setValue(quoteInfo.getQuoteTitle());
				keyword1.setColor("#173177");
				map.put("keyword1", keyword1);

				ValueAndColor keyword2 = new ValueAndColor();
				keyword2.setValue(quoteInfo.getFactoryName());
				keyword2.setColor("#173177");
				map.put("keyword2", keyword2);
				
				ValueAndColor keyword3 = new ValueAndColor();
				keyword3.setValue(DateFormat.currentDate());
				keyword3.setColor("#173177");
				map.put("keyword3", keyword3);

				ValueAndColor remark = new ValueAndColor();
				remark.setValue("请登录快制造微信端或网站PC端查看");
				remark.setColor("#173177");

				System.out.println(map);
				
				data.setFirst(first);
				data.setMap(map);
				data.setRemark(remark);

				template.setData(data);
			  
				JSONObject json = JSONObject.fromObject(template);
			    
				//去除json数据中 "map":{}
				String str = json.toString();
				str = Wechatimpl.changeToFormat(str);
				
				JSONObject j = SendHttpsRequestUtil.sendPostRequest(message_push_url,str);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}	
		return null;
	}
	
	
	
	
	/**
	 * 回复消息推送
	 * 
	 * @param pageUrl
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping("message_note")
	public String messageNote(String factoryId,String orderId, String messageDetail, HttpServletRequest req,HttpServletResponse resp) {

		try {
            String openid = null; 
            if(StringUtils.isNotBlank(factoryId)){
            	FactoryInfo factoryInfo = factoryInfoService.selectFactoryInfo(factoryId);
            	openid = factoryInfo.getOpenid();
            	
    			if(StringUtils.isNotBlank(openid) && StringUtils.isNotBlank(orderId)){
    				
    				QuoteInfo quoteInfo = quoteInfoService.queryByOrderId(Integer.parseInt(orderId));
    				
    				String access_token = SingleAccessToken.getInstance().getAccessToken().getToken();

    				String message_push_url = WriteProp.get("message_push_url").replace("ACCESS_TOKEN", access_token);
    				
    				MessageTemplate template = new MessageTemplate();
    				template.setTouser(openid);
    				template.setTemplate_id("Bt1fW4-XZeWeX6HsrStxlCJLiDWL6FRVAKS7x1hAM1g");
    				template.setUrl(SERVER_PATH  + "/m-zh/detail.html?orderId="+quoteInfo.getOrderId());

    				DataTemplate data = new DataTemplate();
    				Map<Object,Object> map = new HashMap<Object, Object>();
    				
    				ValueAndColor first = new ValueAndColor();
    				first.setValue(quoteInfo.getOrderId()+"您有新的回复消息");
    				first.setColor("#173177");

    				ValueAndColor keyword1 = new ValueAndColor();
    				keyword1.setValue(DateFormat.format());
    				keyword1.setColor("#173177");
    				map.put("keyword1", keyword1);

    				ValueAndColor keyword2 = new ValueAndColor();
    				keyword2.setValue(messageDetail);
    				keyword2.setColor("#173177");
    				map.put("keyword2", keyword2);

    				ValueAndColor remark = new ValueAndColor();
    				remark.setValue("请登录快制造微信端或网站PC端查看");
    				remark.setColor("#173177");

    				data.setFirst(first);
    				data.setMap(map);
    				data.setRemark(remark);

    				template.setData(data);
    			  
    				JSONObject json = JSONObject.fromObject(template);
    				//去除json数据中 "map":{}
    				String str = json.toString();
    				str = Wechatimpl.changeToFormat(str);
    				
    				JSONObject j = SendHttpsRequestUtil.sendPostRequest(message_push_url,str);
    			}
            }			

		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 通过消息中心发送的消息
	 * 订单号为空时只显示消息内容
	 * @param pageUrl
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping("message_reply")
	public String messageReply(String receiverId,String orderId, String messageDetails, HttpServletRequest req,HttpServletResponse resp) {

		try {
            String openid = null; 
            if(StringUtils.isNotBlank(receiverId)){
            	FactoryInfo factoryInfo = factoryInfoService.selectFactoryInfo(receiverId);
            	openid = factoryInfo.getOpenid();            	
    			if(StringUtils.isNotBlank(openid)){
    				Integer pid = null;
    				if(StringUtils.isNotBlank(orderId)){
    					QuoteInfo quoteInfo = quoteInfoService.queryByOrderId(Integer.parseInt(orderId));
    					pid = quoteInfo.getOrderId();
    				}
    				
    				
    				String access_token = SingleAccessToken.getInstance().getAccessToken().getToken();

    				String message_push_url = WriteProp.get("message_push_url").replace("ACCESS_TOKEN", access_token);
    				
    				MessageTemplate template = new MessageTemplate();
    				template.setTouser(openid);
    				template.setTemplate_id("Bt1fW4-XZeWeX6HsrStxlCJLiDWL6FRVAKS7x1hAM1g");
    				if(pid == null){
    					template.setUrl(SERVER_PATH + "/m-zh/quote_list.html");
    				}else{
        				template.setUrl(SERVER_PATH + "/m-zh/detail.html?orderId="+pid);
    				}


    				DataTemplate data = new DataTemplate();
    				Map<Object,Object> map = new HashMap<Object, Object>();
    				
    				ValueAndColor first = new ValueAndColor();
    				if(pid == null){
    					first.setValue("您有新的回复消息");
    				}else{
    					first.setValue(pid+"您有新的回复消息");
    				}
    				
    				first.setColor("#173177");

    				ValueAndColor keyword1 = new ValueAndColor();
    				keyword1.setValue(DateFormat.format());
    				keyword1.setColor("#173177");
    				map.put("keyword1", keyword1);

    				ValueAndColor keyword2 = new ValueAndColor();
    				keyword2.setValue(messageDetails);
    				keyword2.setColor("#173177");
    				map.put("keyword2", keyword2);

    				ValueAndColor remark = new ValueAndColor();
    				remark.setValue("请登录快制造微信端或网站PC端查看");
    				remark.setColor("#173177");

    				data.setFirst(first);
    				data.setMap(map);
    				data.setRemark(remark);

    				template.setData(data);
    			  
    				JSONObject json = JSONObject.fromObject(template);
    				//去除json数据中 "map":{}
    				String str = json.toString();
    				str = Wechatimpl.changeToFormat(str);
    				
    				JSONObject j = SendHttpsRequestUtil.sendPostRequest(message_push_url,str);
    			}
            }			

		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	/** 
	 * 创建永久二维码(字符串) 
	 *  
	 * @param accessToken 
	 * @param sceneStr 
	 *            场景str 
	 * @return 
	 */  
	public static String createForeverStrTicket(String accessToken, String sceneStr) {  
	    TreeMap<String, String> params = new TreeMap<>();  
	    params.put("access_token", accessToken);  
	    // output data  
	    Map<String, String> intMap = new HashMap<>();  
	    intMap.put("scene_str", sceneStr);  
	    Map<String, Map<String, String>> mapMap = new HashMap<>();  
	    mapMap.put("scene", intMap);  
	    Map<String, Object> paramsMap = new HashMap<>();  
	    paramsMap.put("action_name", QR_LIMIT_STR_SCENE);  
	    paramsMap.put("action_info", mapMap);  
//	    paramsMap.put("access_token", accessToken);
	    String data = JsonUtil.map2json(paramsMap);  
	    
	    data = Client.sendPost(CREATE_TICKET_PATH + "?access_token="+accessToken, data);
 
	    WechatQRCode wechatQRCode = null;  
	    try {  
	        wechatQRCode = JsonUtil.jsonToObject(data, WechatQRCode.class);  
	        System.out.println(wechatQRCode);
	    } catch (JsonSyntaxException e) {  
	        e.printStackTrace();  
	    }  
	    return wechatQRCode == null ? null : wechatQRCode.getTicket();  
	}  
	
	
	
	/**
     * 链接url下载图片
     * String URL
     * 
    **/        
    private static void downloadPicture(String urlList) {
        URL url = null; 
        try {
            url = new URL(urlList);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());

          //本地地址及名称
            String imageName =  "E:/"+UUID.randomUUID()+".jpg";

            FileOutputStream fileOutputStream = new FileOutputStream(new File(imageName));
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length;

            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            byte[] context=output.toByteArray();
            fileOutputStream.write(output.toByteArray());
            dataInputStream.close();
            fileOutputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("下载成功");
    }
	
    /**
     * 获取二维码 
     * String tickefile
     * 
     * String   
     * */
    public static String toticke(String ticket){

          //发送 get 请求 换取二维码 (ticke)        
        String tickefile=Client.sendGet(SHOW_QRCODE_PATH, "ticket="+ticket);
      //  System.out.println("filepath=="+url3+"?"+"ticket="+ticke);    
        return SHOW_QRCODE_PATH+"?"+"ticket="+ticket;
    }  
	
    
    
    /**
     * 生成报价的二维码
     * @Title placeQrOrder 
     * @Description
     * @param resp
     * @param url
     * @return void
     */
    @RequestMapping("qr-code")
    public void placeQrOrder(HttpServletResponse resp,String url) {
        try {
			resp.setHeader("Cache-Control", "no-store");
			resp.setHeader("Pragma", "no-cache");
			resp.setDateHeader("Expires", 0);
			resp.setContentType("image/png");

			Map<EncodeHintType, Object> hints = new HashMap<>();
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hints.put(EncodeHintType.MARGIN, 1);
			
			BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, 140, 140, hints);
			
//			bitMatrix = deleteWhite(bitMatrix);//删除白边

			
			
			MatrixToImageWriter.writeToStream(bitMatrix, "png", resp.getOutputStream());

		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    
    /**
     * 生成带logo的二维码
     * @Title placeQrOrderLogo 
     * @Description
     * @param resp
     * @param url
     * @return void
     */
    @ResponseBody
    @RequestMapping("qr-code-logo")
    public String placeQrOrderLogo(HttpServletResponse resp,@Param("url")String url,@Param("orderId")String orderId) {
    	
    	try {
			String imgPath = UploadAndDownloadPathUtil.getQuoteFile() + "qr";   	
			generateQRImage(url, LOGO, imgPath, orderId+".png", "png");
			return imgPath + orderId + ".png";
		} catch (Exception e) {
			e.printStackTrace();
			return "NO";
		}
    	
    }
    
    
    
    
    /** 
     * 生成带logo的二维码图片  
     * @param txt          //二维码内容 
     * @param logoPath     //logo绝对物理路径 
     * @param imgPath      //二维码保存绝对物理路径 
     * @param imgName      //二维码文件名称 
     * @param suffix       //图片后缀名 
     * @throws Exception 
     */  
    public static void generateQRImage(String txt, String logoPath, String imgPath, String imgName, String suffix) throws Exception{  
     
        File filePath = new File(imgPath);  
        if(!filePath.exists()){  
            filePath.mkdirs();  
        }  
          
        imgPath = imgPath + File.separator + imgName;
          
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();     
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");      
        hints.put(EncodeHintType.ERROR_CORRECTION, level);    
        hints.put(EncodeHintType.MARGIN, margin);  //设置白边  
        BitMatrix bitMatrix = new MultiFormatWriter().encode(txt, BarcodeFormat.QR_CODE, width, height, hints);    
        File qrcodeFile = new File(imgPath);      
        writeToFile(bitMatrix, suffix, qrcodeFile, logoPath);      
    }    
        
    /**  
     *   
     * @param matrix 二维码矩阵相关  
     * @param format 二维码图片格式  
     * @param file 二维码图片文件  
     * @param logoPath logo路径  
     * @throws IOException  
     */    
    public static void writeToFile(BitMatrix matrix,String format,File file,String logoPath) throws IOException {    
        BufferedImage image = toBufferedImage(matrix);    
        Graphics2D gs = image.createGraphics();    
           
        int ratioWidth = image.getWidth()*2/10;  
        int ratioHeight = image.getHeight()*2/10;  
        //载入logo    
        Image img = ImageIO.read(new File(logoPath));   
        int logoWidth = img.getWidth(null)>ratioWidth?ratioWidth:img.getWidth(null);  
        int logoHeight = img.getHeight(null)>ratioHeight?ratioHeight:img.getHeight(null);  
          
        int x = (image.getWidth() - logoWidth) / 2;   
        int y = (image.getHeight() - logoHeight) / 2;  
          
        gs.drawImage(img, x, y, logoWidth, logoHeight, null);   
        gs.setColor(Color.black);  
        gs.setBackground(Color.WHITE);  
        gs.dispose();    
        img.flush();    
        if(!ImageIO.write(image, format, file)){    
            throw new IOException("Could not write an image of format " + format + " to " + file);      
        }    
    }    
        
    public static BufferedImage toBufferedImage(BitMatrix matrix){    
        int width = matrix.getWidth();    
        int height = matrix.getHeight();    
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);    
            
        for(int x=0;x<width;x++){    
            for(int y=0;y<height;y++){    
                image.setRGB(x, y, matrix.get(x, y) ? onColor : offColor);    
            }    
        }    
        return image;       
    }         
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * 删除二维码白边
     * */
    private static BitMatrix deleteWhite(BitMatrix matrix) {
        int[] rec = matrix.getEnclosingRectangle();
        int resWidth = rec[2] + 1;
        int resHeight = rec[3] + 1;

        BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
        resMatrix.clear();
        for (int i = 0; i < resWidth; i++) {
            for (int j = 0; j < resHeight; j++) {
                if (matrix.get(i + rec[0], j + rec[1]))
                    resMatrix.set(i, j);
            }
        }
        return resMatrix;
    }
    
    
    
    
    
	
	public static void main(String[] args) {
		String access_token = SingleAccessToken.getInstance().getAccessToken().getToken();
//		String ticket = createForeverStrTicket(access_token,"https://kuaizhizao.cn/zh/detail.html?orderId=28058");
//		String url = toticke(ticket);
//		downloadPicture(url);

	}
}
