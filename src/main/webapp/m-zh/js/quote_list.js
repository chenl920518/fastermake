
var maxPage = 1;
var scrollPage = 1;
$(function(){
	queryAll(1);
	
	  //移动端滚动分页
    $(window).scroll(function(){
    	var scrollTop = $(this).scrollTop();
    	var scrollHeight = $(document).height();
    	var windowHeight = $(this).height();
    	 if (scrollTop + windowHeight == scrollHeight) {
    		 if(scrollPage == maxPage){
    			 return false;
    		 }
    		 scrollPage++;
    		 queryAll(scrollPage); 
    	 }
    })
})

 var currentPage = 1;

//查询所有正常询盘
 function queryAll(page){
	
	var process = $('#select_process').find('option:selected').text();

	var product = $('#product_name').val();
	if(product == undefined){
		product  == "";
	}	
	if(process == undefined || process.trim() == "工艺"){
		   process = "";
	}
		
	
	
	//后台获取数据链接
	var queryUrl = "";
	var quoteStatus = null;
	var str = window.location.search;
	if(str != null && str != ''){
		quoteStatus = str.substr(1);
		quoteStatus = quoteStatus.split("&")[0].split("=")[1];
	}
	
	//根据状态获取链接
	if(quoteStatus == 1){
		queryUrl = "/inquiry/queryQuoteList.do";
	}else{
		queryUrl = "/inquiry/queryInquiryList.do";
	}		
	
	
	$.post(queryUrl,
			{
		     "status":quoteStatus,
		     "process":process.trim(),
		     "product" : product,
		     "customerType" : $('#select_type').val(),
		     "page" : page
			 },
			function(result){
		      if(result.state == 0){
		    	  var inquiryOrders = result.data.inquiryOrders;
		    	  var totalOrder = result.data.totalOrder;
		    	  maxPage = Math.ceil(totalOrder/18);		    	  
		    	  
		    	  var tl = inquiryOrders.length;		    	  
		    	  
		    	  //判断当前筛选是否为 '工艺'
		    	  var selectProcess = $('#select_process').find('option:selected').text();
	    	      if((selectProcess.trim() != '' || product != '') && scrollPage == 1){
	    			  $('#tbody').empty();
	    		  }
		    	  
		    	  for(var i=0;i<tl;i++){
		    		  
		    	  	//获取国家国旗
			     	 var country = inquiryOrders[i].country;	
			    	 var flagSrc = getFlag(country);
	    		  
		    		  
					  var tr =	'<tr onclick="window.location=\'/m-zh/detail.html?orderId='+inquiryOrders[i].orderId+'\'">'+ 
										'<td class="th1">'+
										'<a class="imgs" href="###">'+
											'<img src="'+flagSrc+'" alt="" class="img1"/>'+
											'<img src="'+((inquiryOrders[i].drawingPathCompress == null || inquiryOrders[i].drawingPathCompress == '' ) ? '/images/pic2.png' : inquiryOrders[i].drawingPathCompress)+'" alt="" class="img2"/>'+
										'</a>'+				
									'</td>'+
									'<td class="th2">'+
										'<span class="word">'+(inquiryOrders[i].quoteTitle == null ? inquiryOrders[i].productName : inquiryOrders[i].quoteTitle)+'</span>'+
										'<span class="word">'+(inquiryOrders[i].mainProcess == null ? '' : inquiryOrders[i].mainProcess)+'</span>'+
										'<span class="word">'+(inquiryOrders[i].state == 1 ? '江浙沪' : (inquiryOrders[i].state == 2 ? '深圳、广东、福建' : '不限'))+'</span>'+
										'<span class="word">'+(new Date(inquiryOrders[i].publishDate.replace(/-/g,"/").split(".")[0])).Format("yyyy-MM-dd")+'</span>'+	
										(inquiryOrders[i].quoteState == 1 ? '<button>已报价</button>' : (inquiryOrders[i].messageStatus != null &&  inquiryOrders[i].messageStatus != '' ? '<button>咨询过</button>': ''))+
									'</td>'+									
								'</tr>'; 		    		  		    		  
		    			  $('#tbody').append(tr);
		    			  /* 表格隔行换色效果*/
		    			  	
				    	    $('table tr:nth(18n)').css({
				    	        'background-color':'red'
				    	    }) ;				    	    			    	    
		    	  }	 
		    	  	    	  
		    	  
		      }else if(result.state == 2){
		          //如果还未登录，跳转登录页
		    	  window.location = "/m-zh/login.html";
		      }  
		  })			  		  
} 
 //根据工艺筛选
 function queryByProcess(obj){
	scrollPage = 1;
    queryAll(1);	
 }
 //根据关键词筛选
 function queryByKey(obj){
	 scrollPage = 1;
    queryAll(1);	
 }
 


 
 //查询订单详情
 function queryDetails(orderId){
    top.location = "/zh/detail.html?orderId="+orderId;
 }
 
 
 
 Date.prototype.Format = function (fmt) { //author: meizz
	 var o = {
	 "M+": this.getMonth() + 1, //月份
	 "d+": this.getDate(), //日
	 "h+": this.getHours(), //小时
	 "m+": this.getMinutes(), //分
	 "s+": this.getSeconds(), //秒
	 "q+": Math.floor((this.getMonth() + 3) / 3), //季度
	 "S": this.getMilliseconds() //毫秒
	 };
	 if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	 for (var k in o)
	 if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	 return fmt;
	 }

 
 
 
function share(){
	var appId = "";
	var timestamp = 0;
	var nonceStr = "";
	var signature = "";
	$.ajax({
			async : false,
			type : "GET",// 请求方式
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

			    desc: '快制造发布了的新的询盘，快来参与报价吧！', // 分享描述

			    link : website+'/m-zh/quote_list.html', // 分享链接

			    imgUrl: website+'/images/default_logo.png', // 分享图标

			    type: 'link', // 分享类型,music、video或link，不填默认为link

			   /* dataUrl: '',*/ // 如果type是music或video，则要提供数据链接，默认为空
			    trigger: function (res) {
			        // 不要尝试在trigger中使用ajax异步请求修改本次分享的内容，因为客户端分享操作是一个同步操作，这时候使用ajax的回包会还没有返回
			        alert('用户点击发送给朋友');
			      },
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
			
			link : website+'/m-zh/quote_list.html',
			
			imgUrl : website+'/images/default_logo.png',
			
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
}


