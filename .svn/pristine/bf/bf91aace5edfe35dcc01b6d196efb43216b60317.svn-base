// -------------获取url参数
var orderId = obj["orderId"];
//var project = obj["project"];
//var orderInfo = getCookie("orderInfo");
//var imgSrc = getCookie("imgSrc");

var appId = "";
var timestamp = 0;
var nonceStr = "";
var signature = "";
$.ajax({
		async : false,
		type : "GET",// 请求方式
		url : "/wimpl/signature.do",// 地址，就是action请求路径
		data : {
			'pageUrl':window.location.href.split('#')[0]
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
				//window.location.href = "/m-zh/error.html";
			}, 0);
			
		}
})




wx.config({

    debug: false, // true开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。

    appId: appId, // 公众号的唯一标识

    timestamp: timestamp, // 时间戳

    nonceStr: nonceStr, // 随机串

    signature: signature,// 签名

    jsApiList: ['onMenuShareAppMessage','onMenuShareTimeline','hideMenuItems','showOptionMenu','showMenuItems'] // 需要使用的JS接口列表

});

wx.ready(function(){

	//不隐藏菜单
	wx.showOptionMenu();
	
	//隐藏分享到QQ、QQ空间，微博和脸书功能
	wx.hideMenuItems({

	    menuList: ['menuItem:share:qq','menuItem:share:QZone','menuItem:share:weiboApp','menuItem:share:facebook']

	});
	//开放分享给朋友、分享到朋友圈功能
	wx.showMenuItems({

	    menuList: ['menuItem:share:appMessage','menuItem:share:timeline']

	});
	//分享给朋友
	wx.onMenuShareAppMessage({

		    title: '询盘信息', // 分享标题

		    desc: orderInfo, // 分享描述

		    link : website + '/m-zh/detail.html?orderId='+orderId, // 分享链接

		    imgUrl: website + '/images/default_logo.png', // 分享图标

		    type: 'link', // 分享类型,music、video或link，不填默认为link

		   /* dataUrl: '',*/ // 如果type是music或video，则要提供数据链接，默认为空

		    success: function () { 
		        // 用户确认分享后执行的回调函数
		    	
		    	setTimeout(function(){
		    		window.location.href = "/m-zh/shared.html";
				}, 0);
		    	
		    },

		    /*cancel: function () {
		        // 用户取消分享后执行的回调函数

		    }*/

		});
	
	//分享到朋友圈
	wx.onMenuShareTimeline({
		
		title: '快制造发布了的新的询盘，快来参与报价吧！', // 分享标题
		
		desc: orderInfo, // 分享描述
		
		link : website + '/m-zh/detail.html?orderId='+orderId,
		
		imgUrl : website + imgSrc,
		
		success : function(){
			// 用户确认分享后执行的回调函数
			
			setTimeout(function(){
				window.location.href = "/m-zh/shared.html";
			}, 0);
		},
		
		/*cancel : function(){
			// 用户取消分享后执行的回调函数
			
		}*/
	});
})