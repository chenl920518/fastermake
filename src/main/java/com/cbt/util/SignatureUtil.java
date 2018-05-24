package com.cbt.util;

import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.cbt.model.SingleAccessToken;

import net.sf.json.JSONObject;


/**
 * JS SDK的签名工具类
 * @author Administrator
 *
 */
public class SignatureUtil {

	/**
	 * 生成随机字符串
	 * 
	 * @return
	 */
	public static String createNonceStr() {
		int length = 16;
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";// 26+26+10=62，下标0-61
		String str = "";
		Random rad = new Random();
		for (int i = 0; i < length; i++) {
			int k = rad.nextInt(62);// 0~61的随机数
			str += chars.charAt(k);
		}
		return str;
	}

	/**
	 * 获取jsapi_ticket
	 * 
	 * @return
	 */
	public static String getJsapi_ticket() {

		String access_token = SingleAccessToken.getInstance().getAccessToken()
				.getToken();

		/*JSONObject json = SendHttpsRequestUtil.sendGetRequest(WriteProp.get(
				"jsapi_ticket_url").replace("ACCESS_TOKEN", access_token));*/
		JSONObject json = SendHttpsRequestUtil.sendPostRequest(WriteProp.get(
				"jsapi_ticket_url").replace("ACCESS_TOKEN", access_token),"");

		return json.getString("ticket");
	}

	/**
	 * 获取时间戳
	 * 
	 * @return
	 */
	public static int getTimestamp() {

		return (int)(System.currentTimeMillis() / 1000);

	}


	/** 
     * SHA1 安全加密算法 
     * @param maps 参数key-value map集合 
     * @return 
     * @throws DigestException  
     */  
    public static String SHA1(Map<String,Object> maps) throws DigestException {  
        //获取信息摘要 - 参数字典排序后字符串  
        String decrypt = getOrderByLexicographic(maps);  
        try {  
            //指定sha1算法  
            MessageDigest digest = MessageDigest.getInstance("SHA-1");  
            digest.update(decrypt.getBytes());  
            //获取字节数组  
            byte messageDigest[] = digest.digest();  
            // Create Hex String  
            StringBuffer hexString = new StringBuffer();  
            // 字节数组转换为 十六进制 数  
            for (int i = 0; i < messageDigest.length; i++) {  
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);  
                if (shaHex.length() < 2) {  
                    hexString.append(0);  
                }  
                hexString.append(shaHex);  
            }  
            return hexString.toString().toUpperCase();  
  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
            throw new DigestException("签名错误！");  
        }  
    }  
    
    /** 
     * 获取参数的字典排序 
     * @param maps 参数key-value map集合 
     * @return String 排序后的字符串 
     */  
    private static String getOrderByLexicographic(Map<String,Object> maps){  
        return splitParams(lexicographicOrder(getParamsName(maps)),maps);  
    }  
    
    /** 
     * 获取参数名称 key 
     * @param maps 参数key-value map集合 
     * @return 
     */  
    private static List<String> getParamsName(Map<String,Object> maps){  
        List<String> paramNames = new ArrayList<String>();  
        for(Map.Entry<String,Object> entry : maps.entrySet()){  
            paramNames.add(entry.getKey());  
        }  
        return paramNames;  
    }  
    
    /** 
     * 参数名称按字典排序 
     * @param paramNames 参数名称List集合 
     * @return 排序后的参数名称List集合 
     */  
    private static List<String> lexicographicOrder(List<String> paramNames){  
        Collections.sort(paramNames);  
        return paramNames;  
    } 
    
    /** 
     * 拼接排序好的参数名称和参数值 
     * @param paramNames 排序后的参数名称集合 
     * @param maps 参数key-value map集合 
     * @return String 拼接后的字符串 
     */  
    private static String splitParams(List<String> paramNames,Map<String,Object> maps){  
        StringBuilder paramStr = new StringBuilder();  
        for(String paramName : paramNames){  
            paramStr.append(paramName+"=");  
            for(Map.Entry<String,Object> entry : maps.entrySet()){  
                if(paramName.equals(entry.getKey())){  
                    paramStr.append(String.valueOf(entry.getValue())+"&");  
                }  
            }  
        }
        String str = paramStr.toString();
        return str.substring(0, str.length()-1);  
    }

}
