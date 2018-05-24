/*
 * 功能:javascript翻译工具
 * 日期:2018/03/24
 * 作者:polo
 */
var lang = "en";//语言类型

function translate(cal){
	//获取接口
	var value = 'https://fanyi-api.baidu.com/api/trans/vip/translate?';
	//获取当前时间
	var date = Date.now();
	//此处拼接接口数据,好转换成jsonp数据格式,实现跨域访问
	var str = '20170605000052254' + cal + date + '63r1c42X7_buc4OrXm1g';
	//使用加密算法计算数据
	var md5 = MD5(str);
	
	var dst = "123";

	$.ajax({
	    url: 'https://fanyi-api.baidu.com/api/trans/vip/translate',
	    type: 'get',
//	    async : false,
	    dataType: 'jsonp',
	    data: {
	        q: cal,
	        appid: '20170605000052254',
	        salt: date,
	        from: "zh",
	        to: "en",
	        sign: md5
	    },
	    success: function (data) {
	        if(data.trans_result == undefined){
	        	dst = "";
	        }else{
	        	dst = data.trans_result[0].dst;
//	        	s = dst;
	        	callbackName(dst);	        	
	        	
	        }
	    } 
	});
}








function translate1(obj){
	var appid = '20170605000052254';
	var key = '63r1c42X7_buc4OrXm1g';
	var salt = (new Date).getTime();
//	var query = 'apple';
	// 多个query可以用\n连接  如 query='apple\norange\nbanana\npear'
	var from = 'zh';
	var to = 'en';
	var str1 = appid + obj + salt +key;
	var sign = MD5(str1);
	$.ajax({
	    url: 'http://api.fanyi.baidu.com/api/trans/vip/translate',
	    type: 'get',
	    dataType: 'jsonp',
	    data: {
	        q: obj,
	        appid: appid,
	        salt: salt,
	        from: from,
	        to: to,
	        sign: sign
	    },
	    success: function (data) {
	    	return data.trans_result[0].dst;
	    } 
	});
}



function translate2(){
		
	var appKey = '您的应用ID';
	var key = '您的应用密钥';//注意：暴露appSecret，有被盗用造成损失的风险
	var salt = (new Date).getTime();
	var query = '你好';
	// 多个query可以用\n连接  如 query='apple\norange\nbanana\npear'
	var from = '';
	var to = 'en';
	var str1 = appKey + query + salt +key;
	var sign = md5(str1);
	$.ajax({
	    url: 'http://openapi.youdao.com/api',
	    type: 'post',
	    dataType: 'jsonp',
	    data: {
	        q: query,
	        appKey: appKey,
	        salt: salt,
	        from: from,
	        to: to,
	        sign: sign
	    },
	    success: function (data) {
	        console.log(data);
	    } 
	});
}




//回调函数定义
function callbackName(str){
	//再次获取输出结果标签
	sts = str;
	return str;
}



//var ss = callbackName("aaa");


//alert(ss);


