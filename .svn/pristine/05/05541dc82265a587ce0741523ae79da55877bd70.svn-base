package com.cbt.wximpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

public class MethedCase {
	public static final String event1= "subscribe";//关注事件
	public static final String event2= "unsubscribe";//取关事件
	public static final String event3= "SCAN";//
	public static final String event4= "LOCATION";//
	public static final String event5= "CLICK";//菜单点击事件
	public static final String event6= "VIEW";//
	public static final String event7= "text";//
	public static final String event8= "image";//
	public static final String event9= "voice";//
	public static final String event10= "video";//
	public static final String event11="music";//
	public static final String event12="music";//
	public static final String event13="music";//
	public static final String event14="news";//
	public static final String msgtype1="text";//
	public static final String msgtype2="image";//
	public static final String msgtype3="video";//
	public static final String msgtype4="localhost";//
	public static final String msgtype5="link";//

	public static JSONObject hdmessage(HttpServletRequest req) {
		int len = req.getContentLength();
//		System.out.println("数据流长度:" + len);
		JSONObject json=new JSONObject();
		// 获取HTTP请求的输入流
		try {
			InputStream is = req.getInputStream();
			// 已HTTP请求输入流建立一个BufferedReader对象
			BufferedReader br = new BufferedReader(new InputStreamReader(is,
					"UTF-8"));
			// BufferedReader br = request.getReader();
			// 读取HTTP请求内容
			String buffer = null;
			StringBuffer sb = new StringBuffer();

			while ((buffer = br.readLine()) != null) {
				// 在页面中显示读取到的请求参数
				sb.append(buffer);
			}
			json= (JSONObject) new XMLSerializer().read(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}
}
