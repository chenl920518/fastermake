package com.cbt.translate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cbt.util.JsonUtil;
import com.cbt.util.ReadProp;

public class TransApi {
    private static final String TRANS_API_HOST = "http://api.fanyi.baidu.com/api/trans/vip/translate";

    private static String appid = ReadProp.get("APP_ID", "baiduAppconf.properties");
    private static String securityKey = ReadProp.get("SECURITY_KEY", "baiduAppconf.properties");;

//    public TransApi(String appid, String securityKey) {
//        this.appid = appid;
//        this.securityKey = securityKey;
//    }

    public static String getTransResult(String query, String from, String to) {
        Map<String, String> params = buildParams(query, from, to);
        String str = HttpGet.get(TRANS_API_HOST, params);
	    Map<Object,Object> map = JsonUtil.jsonToObject(str, Map.class);
	    if(map.get("trans_result") != null && !"".equals(map.get("trans_result"))){
		    List<Object> list = (List<Object>)map.get("trans_result");
		    Map<Object,Object> map1 = JsonUtil.jsonToObject(list.get(0).toString(), Map.class);
	        return (map1.get("dst") == null ? "" : map1.get("dst").toString());
	    }else{
	    	return "";
	    }  
    }

    private static Map<String, String> buildParams(String query, String from, String to) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("q", query);
        params.put("from", from);
        params.put("to", to);

        params.put("appid", appid);

        // 随机数
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("salt", salt);

        // 签名
        String src = appid + query + salt + securityKey; // 加密前的原文
        params.put("sign", MD5.md5(src));

        return params;
    }

}
