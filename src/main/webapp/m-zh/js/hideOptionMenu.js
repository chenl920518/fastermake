var appId = "";
var timestamp = 0;
var nonceStr = "";
var signature = "";
$.ajax({
		async : false,
		type : "POST",// 请求方式
		url : "/wimpl/signature.do",// 地址，就是action请求路径
		data : {
			'pageUrl':window.location.href.split('#')[0],
		},
		dataType : "json",// 数据类型text xml json script jsonp
		success : function(msg) {
			appId = msg.appid;
			timestamp = msg.timestamp;
			nonceStr = msg.noncestr;
			signature = msg.signature;
		},
		error : function() {
			setTimeout(function(){
				window.location.href = "/m-zh/error.html";
			}, 0);
		}
})

wx.config({

    debug: false, // true开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。

    appId: appId, // 公众号的唯一标识

    timestamp: timestamp, // 时间戳

    nonceStr: nonceStr, // 随机串

    signature: signature,// 签名

    jsApiList: ['hideOptionMenu'] // 需要使用的JS接口列表

});

wx.ready(function(){
	//隐藏微信右上角菜单
	wx.hideOptionMenu();
})