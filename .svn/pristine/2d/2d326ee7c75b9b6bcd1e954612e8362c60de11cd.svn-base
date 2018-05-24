package com.cbt.wximpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cbt.entity.User;
import com.cbt.model.AccessToken;
import com.cbt.model.JsapiToken;
import com.cbt.model.SingleAccessToken;
import com.cbt.util.JsonUtil;
import com.cbt.util.SendHttpsRequestUtil;
import com.cbt.util.WriteProp;

public class Wechatimpl {
	//获取access_token接口调用凭证
	/*public static String access_token = SingleAccessToken.getInstance()
			.getAccessToken().getToken();*/
	public static final String token = "cerongwxapp10020";
	public static Long mils = 5000 + System.currentTimeMillis();
	public static String timestamp = timestamp(mils);
	public Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 创建菜单
	public void createmenu() {
		String access_token = SingleAccessToken.getInstance().getAccessToken().getToken();
//		System.out.println(access_token);
		String createmenu_url = WriteProp.get("createmenu_url");
		try {
			String buttonstr = WriteProp.get("button");
//			System.out.println(buttonstr);
			String s1 = JSONObject.fromObject(buttonstr).toString();
			System.err.println("创建菜单中================"+s1+"================================");
			JSONObject s = SendHttpsRequestUtil.sendPostRequest(
					createmenu_url.replace("ACCESS_TOKEN", access_token),
					buttonstr);
//			System.out.println(s);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("com.cerong.util.wximpl.createmenu  happend Exception"
					+ e);
		}

	}

	// 获取自动回复规则
	public static JSONObject getautosend() {
		String access_token = SingleAccessToken.getInstance().getAccessToken().getToken();
		String url = WriteProp.get("url_autosend");
		JSONObject s = SendHttpsRequestUtil.sendGetRequest(url.replace("ACCESS_TOKEN",
				access_token));
//		System.out.println(s);
		return s;
	}

	// 获取access_token
	public static AccessToken getAccessToken(String appid, String appsecret) {
		String access_token_url = WriteProp.get("access_token_url");
		AccessToken accessToken = null;
		JSONObject json = SendHttpsRequestUtil.sendGetRequest(access_token_url.replace("APPID",
				appid).replace("SECRET", appsecret));
		if (json != null) {
			String access_token = json.getString("access_token");
			Integer expiresIn = json.getInt("expires_in");
			accessToken = new AccessToken();
			accessToken.setToken(access_token);
			accessToken.setExpiresIn(expiresIn);
		}
		return accessToken;
	}
	
	/**JS-SDK
	 * 获取jsapi_access_token
	 * @return
	 */
	public static JsapiToken getJsapiToken() {
		
		String access_token = SingleAccessToken.getInstance().getAccessToken()
				.getToken();

		JSONObject json = SendHttpsRequestUtil.sendPostRequest(WriteProp.get(
				"jsapi_ticket_url").replace("ACCESS_TOKEN", access_token),"");
		JsapiToken jsapiToken = new JsapiToken();
		jsapiToken.setAccess_token(json.getString("ticket"));
		jsapiToken.setExpires_in(json.getString("expires_in"));
		return jsapiToken;
	}

	/**
	 * 获取本公众号的关注者openid列表
	 * @return
	 */
	public static JSONObject getAllpenid() {
		String access_token = SingleAccessToken.getInstance().getAccessToken().getToken();
		String url = WriteProp.get("url_openid");
		JSONObject result = SendHttpsRequestUtil.sendGetRequest(url.replace("ACCESS_TOKEN",
				access_token));
		return result;
	}
	
	/**
	 * 在网页进行授权时，通过返回的code得到openid
	 * @param code
	 * @return
	 */
	public static String getOpenidByCode(String code){
		String appid = WriteProp.get("appid");
		String secret = WriteProp.get("secret");
		JSONObject result = SendHttpsRequestUtil.sendGetRequest(WriteProp.get("pageAuthorization_access_token_url").replace("APPID", appid).replace("SECRET", secret)
				.replace("CODE", code));
		if(null != result)
			return result.getString("openid");
		return null;
	}

	// 获取详细信息并保存到列表
	public static User getUserinfo(String openid) throws UnsupportedEncodingException {
		String access_token = SingleAccessToken.getInstance().getAccessToken().getToken();
		String url = WriteProp.get("userinfo");
		JSONObject s = SendHttpsRequestUtil.sendGetRequest(url.replace("ACCESS_TOKEN",
				access_token).replace("OPENID", openid));
//		System.out.println(s);
		String nickname = s.getString("nickname");
		String encodeNickname = URLEncoder.encode(nickname,"utf-8");//昵称进行unicode编码，解决昵称附带表情无法保存问题
		int sex = s.getInt("sex");
		String city = s.getString("city");
		String country = s.getString("country");
		String province = s.getString("province");
		String headimgurl = s.getString("headimgurl");
		long subscribe_time = s.getLong("subscribe_time");
		String language = s.getString("language");
		String remark = s.getString("remark");
		String groupid = s.getString("groupid");
		String tagid_list = s.getString("tagid_list");
		User user = new User();
		user.setCity(city);
		user.setCountry(country);
		user.setGroupid(groupid);
		user.setHeadimgurl(headimgurl);
		user.setLanguage(language);
		user.setNickname(encodeNickname);
		user.setOpenid(openid);
		user.setProvince(province);
		user.setRemark(remark);
		user.setSex("" + sex);
		// 转换为yyyy-mm-dd hh:mm:ss形式
		Timestamp scurrtest = new Timestamp(subscribe_time * 1000);
		user.setSubscribe_time(scurrtest.toString());
		user.setTagid_list(tagid_list);
		return user;
	}

	// 解析消息返回json字符串
	public static JSONObject hdmessage(HttpServletRequest req) {
		int len = req.getContentLength();
//		System.out.println("数据流长度:" + len);
		JSONObject json = new JSONObject();
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
			json = (JSONObject) new XMLSerializer().read(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}

	// 解析json字符串返回xml消息给服务器
	public static JSONObject hdmessagetoxml(HttpServletRequest req) {
		int len = req.getContentLength();
//		System.out.println("数据流长度:" + len);
		JSONObject json = new JSONObject();
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
			json = (JSONObject) new XMLSerializer().read(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}

	// 获取时间戳
	public static String timestamp(final Long millis) {
		Long l = System.currentTimeMillis();
		if (millis < l) {
			mils = millis + 5000;
			return mils.toString().substring(0, 10);
		}
		return millis.toString().substring(0, 10);
	}

	public static String sig(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		PrintWriter writer = resp.getWriter();
		writer.println("GET " + req.getRequestURL() + " "
				+ req.getQueryString());
		Map<String, String[]> params = req.getParameterMap();
		String queryString = "";
		for (String key : params.keySet()) {
			String[] values = params.get(key);
			for (int i = 0; i < values.length; i++) {
				String value = values[i];
				queryString += key + "=" + value + "&";
			}
		}
		// 去掉最后一个空格
		queryString = queryString.substring(0, queryString.length() - 1);
		JSONObject json = (JSONObject) new XMLSerializer().read(queryString);
//		System.out.println(json);
		return queryString;
	}

	public static String checkurl(HttpServletRequest req,
			HttpServletResponse resp) {
		// writer.println("GET " + req.getRequestURL() + " "
		// + req.getQueryString());
		Map<String, String[]> params = req.getParameterMap();
		Map<String, String> map = new HashMap<String, String>();
		for (String key : params.keySet()) {
			String[] values = params.get(key);
			for (int i = 0; i < values.length; i++) {
				String value = values[i];
				map.put(key, value);
			}
		}
		JSONObject obj = JSONObject.fromObject(JsonUtil.map2json(map));
		String echostr = obj.getString("echostr");
		return echostr;
	}

	// sha1加密验证url有效性
	public static boolean getsig(String sig, String timestamp, String nonce) {
		StringBuffer b = new StringBuffer();
		String[] test = { token, timestamp, timestamp };
		Arrays.sort(test);
		for (int i = 0; i < test.length; i++) {
			b.append(test[i]);
		}
//		System.out.println(b);
		byte[] t = DigestUtils.sha(b.toString());
		StringBuffer sb = new StringBuffer();
		for (byte b1 : t) {
			sb.append(b1);
		}
		if (sig.equals(sb.toString())) {
			return true;
		} else {
			return false;
		}
	}

	
	
	public static void main(String[] args) {
		Wechatimpl wechatimpl = new Wechatimpl();
		wechatimpl.createmenu();
	}
	
	
	
	
	
	
	//消息模板中去除不正确消息格式
	public static String changeToFormat(String str){				
		//去除json数据中 "map":{}
		String obj = str.split("\"map\":\\{")[1];
		String replace = obj.replaceFirst("}}", "}"); 
		str = str.replace("\"map\":{", "");
		str = str.replace(obj, replace);
		return str;
	}
	
	
	
	
	
	
	
	
	
}
