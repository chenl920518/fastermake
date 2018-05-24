//获取内存中信息的js帮助文件
function getCookieVal(cookieName) {
	var search = cookieName + "="
	var returnvalue = "";
	alert("document.cookie.length==" + document.cookie.length);
	if (document.cookie.length > 0) {
		offset = document.cookie.indexOf(search);
		if (offset != -1) {
			offset += search.length;
			end = document.cookie.indexOf(";", offset);
			if (end == -1)
				end = document.cookie.length;
			returnvalue = unescape(document.cookie.substring(offset, end))
		}
	}
	return returnvalue;
}

function getparm() {
	var url = location.search; // 获取url中"?"符后的字串
	var theRequest = new Object();
	if (url.indexOf("?") != -1) {
		var str = url.substr(1);
		strs = str.split("&");
		for (var i = 0; i < strs.length; i++) {
			theRequest[strs[i].split("=")[0]] = (strs[i].split("=")[1]);
		}
	}
	return theRequest;
}
var obj = getparm();

function getinfo(pid, uid) {
	var msgs;
	// 获取用户信息
	$.ajax({
		async : false,
		type : "POST",// 请求方式
		url : "/user/get.do",// 地址，就是action请求路径
		data : {
			"pid" : pid,
			"uid" : uid
		},// 数据类型text xml json script jsonp
		success : function(msg) {
			msgs = msg;
		}
	});
	return msgs;
}

//格式化金额为千分位形式
function fmoney(s, n) {
	n = n > 0 && n <= 20 ? n : 2;
	s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
	var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1];
	t = "";
	for (i = 0; i < l.length; i++) {
		t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
	}
	return t.split("").reverse().join("") + "." + r;
}


//获取当前系统时间并格式化为yyyy-mm-dd形式
function getCurDate(){
	var date = new Date();
	var hour = date.getHours();
	var year = date.getFullYear();//四位数年份
	var month = date.getMonth()+1;//月份，0-11，0代表一月
	var day = date.getDate();//日期，1-31
	if(month >=1 && month <= 9){
		month = "0" + month;
	}
	if(day >=1 && day <= 9){
		day = "0" + day;
	}
	return year + "-" + month + "-" + day;
}

//获取当前系统时间并格式化为yyyy-mm-dd形式
function getCurDateAndHour(){
	var date = new Date();
	var hour = date.getHours();
	var year = date.getFullYear();//四位数年份
	var month = date.getMonth()+1;//月份，0-11，0代表一月
	var day = date.getDate();//日期，1-31
	if(month >=1 && month <= 9){
		month = "0" + month;
	}
	if(day >=1 && day <= 9){
		day = "0" + day;
	}
	return year + "-" + month + "-" + day + " " + hour;
}
//获取当前时间后几天的日期并格式化为yyyy-mm-dd形式
function getDelayDate(days){
	var cur = new Date();
	var hour = cur.getHours();
	var seconds = days * 24 * 60 * 60 * 1000;
	var newSeconds = cur.getTime() + seconds;
	var date = new Date(newSeconds);
	var year = date.getFullYear();//四位数年份
	var month = date.getMonth()+1;//月份，0-11，0代表一月
	var day = date.getDate();//日期，1-31
	if(month >=1 && month <= 9){
		month = "0" + month;
	}
	if(day >=1 && day <= 9){
		day = "0" + day;
	}
	return year + "-" + month + "-" + day + " " + hour + "时";
}

//判断是否直接使用浏览器打开的
function isBrowser() {
    var userAgentInfo = navigator.userAgent;
    //移动端及微信PC客户端
    var Agents = ["Android", "iPhone","SymbianOS", "Windows Phone","iPad","iPod","WindowsWechat"];
    var flag = true;
    for (var v = 0; v < Agents.length; v++) {
        if (userAgentInfo.indexOf(Agents[v]) > 0) {
            flag = false;
            break;
        }
    }
    return flag;
}

//为空或者不是大于零的整数
function isEmpty(value){
	if('' == value){
		return true;
	}
	//不为空判断是否整数
	var regex = /^\d+$/;
	if(regex.test(value)){
		if(Number(value) <= 0){
			return true;
		}else{
			return false;
		}
	}else{
		return true;
	}
}
//为空或者不是大于零的数值
function isEmpty2(value){
	if('' == value){
		return true;
	}
	//不为空判断是数字
	var regex = /^\d+\.?\d*$/;
	if(regex.test(value)){
		if(Number(value) <= 0){
			return true;
		}else{
			return false;
		}
	}else{
		return true;
	}
}

