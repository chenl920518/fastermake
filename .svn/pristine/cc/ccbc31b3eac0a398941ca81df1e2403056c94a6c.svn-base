package com.cbt.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import net.sf.json.JSONObject;

public class SendHttpsRequestUtil {

	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url1
	 *            发送请求的URL
	 * @return
	 */
	public static JSONObject sendGetRequest(String url1) {

		try {
//			System.out.println("访问GET请求：" + url1);
			HttpURLConnection httpConn = null;
			BufferedReader in = null;
			try {
				URL url = new URL(url1);
				httpConn = (HttpURLConnection) url.openConnection();

				// 读取响应
				if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
					StringBuffer content = new StringBuffer();
					String tempStr = "";
					in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
					while ((tempStr = in.readLine()) != null) {
						content.append(tempStr);
					}
					return JSONObject.fromObject(content.toString());
				} else {
					throw new Exception("请求出现了问题!");
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				in.close();
				httpConn.disconnect();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static JSONObject sendPostRequest(String url, String param) {

//		System.out.println("访问POST请求:" + url);
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
//			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		JSONObject obj = JSONObject.fromObject(result);
		return obj;
	}

	public static void doPost(String urlPath, String param) {

		try {
			// Send the request
			URL url = new URL(urlPath);
			URLConnection conn = url.openConnection();

			conn.setDoOutput(true);
			OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());

			// write parameters
			writer.write(param);
			writer.flush();

			// Get the response
			StringBuffer answer = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				answer.append(line);
			}
			writer.close();
			reader.close();

			// Output the response
//			System.out.println(answer.toString());

		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
