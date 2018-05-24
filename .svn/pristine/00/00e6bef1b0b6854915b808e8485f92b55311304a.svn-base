


	      
var appId = "";
var timestamp = 0;
var nonceStr = "";
var signature = "";

var shareTitle = '';
var orderInfo = "";
var imgSrc = "";
var orderId;
var str = window.location.search;
if(str != null && str != ''){
	str = str.substr(1);
	orderId = str.split("&")[0].split("=")[1];
	$('#orderId').val(orderId);
}	

//判断客户是否登录
var factoryInfo = getCookie("factoryInfo");
var factoryId = getCookie("F_ID");
if(factoryId != null && factoryId != ''){
	var base = new Base64();
	factoryId = base.decode(factoryId);
}



$(function(){
		
	$.post("/inquiry/queryInquiryDetails.do",
			{"orderId" : orderId},
			 function(result){
			      if(result.state == 0){
			    	 var quoteMessages = result.data.quoteMessages; 
			    	 var quoteInfo = result.data.quoteInfo; 
			    	 var products = result.data.products; 
			    	 var isVip = result.data.isVip;
			    	 var isCollect = result.data.isCollect;
			    	 //是否属于被邀请
			    	 var inviteFlag = result.data.inviteFlag;
			    	 //获取国家国旗
			    	 var country = quoteInfo.country;	
			    	 var flagSrc=getFlag(country);
			 		 $('.detail .row2 img').attr('src',flagSrc);
			    	 
			    	 //询盘条件
			    	 $('#quoteId').text(quoteInfo.orderId);
		    	

			    	 $('#confidentiality_agreement').text((quoteInfo.confidentialityAgreement == 0  ? '无需保密协议': '需要保密协议'));
			    	 $('#end_date').text(((quoteInfo.csgOrderId != null && quoteInfo.csgOrderId != '') ? 'N/A' : quoteInfo.quoteEndDate));
			    	 $('#publish_date').text((new Date(quoteInfo.publishDate.replace(/-/g,"/").split(".")[0])).Format("yyyy-MM-dd"));
			    	 $('#process').text(quoteInfo.mainProcess);
			    	 if(inviteFlag == true){
			    		 $('#current_number').text(quoteInfo.currentNumber +'(指定工厂报价)');	 
			    	 }else{
			    		 $('#current_number').text((quoteInfo.currentNumber < 5 ? quoteInfo.currentNumber+'/5' : quoteInfo.currentNumber+'/5 (报价工厂数已满)'));
			    		 //当前报价人数大于2个则不能进行继续报价
			    		 if(quoteInfo.currentNumber > 4){
			    		     $('.to-quote').css({"cursor":"not-allowed","color":"#ccc"}).attr("disabled","true").removeAttr('onclick');
				    	     $('#title_ul').find('li').eq(1).find('a').removeAttr('onclick'); 
			    		 }
			    		 if(factoryId == quoteInfo.customerId){
			    		     $('.to-quote').css({"cursor":"not-allowed","color":"#ccc"}).attr("disabled","true").removeAttr('onclick');
				    	     $('#title_ul').find('li').eq(1).find('a').removeAttr('onclick'); 
			    			 $('#note').text('(您不能对自己的询盘进行报价)');
			    			 $('#message').hide();
			    			 $('#file_upload_id').hide();
			    		 }
			    	 }			    	
			    	 $('#delivery_date').text((quoteInfo.deliveryDate == null ? 'N/A' : quoteInfo.deliveryDate));
		    	 
			    	 
			    	 //分享内容
			    	 shareTitle = quoteInfo.quoteTitle; 
			    	 var productQty = products[0].quantityList.split(",");
			    	 var unitQty = products[0].quantityUnit.split(",");
			    	 orderInfo = "数量："+productQty[0]+ unitQty[0]+"\r\n工艺："
			    	              +quoteInfo.mainProcess+"\r\n快制造询盘 "+(new Date(quoteInfo.publishDate.replace(/-/g,"/").split(".")[0])).Format("yyyy-MM-dd"); 
			    	 
			    	 //判断是否是会员
//			    	 if(isVip == 101){			    		 
//			    		 $('#limit_quote').find('span').text('');
//			    		 $('#limit_quote').find('em').text('');
//			    		 $('.to-quote').css({"cursor":"pointer"}).removeAttr("disabled");
//			    	 }else{
//
//			    		 $('#limit_quote').show();
//				    		var hour = DateDiff(quoteInfo.publishDate,new Date());
//				    	   	if(hour > 48){
//				    		   $('#limit_quote').find('span').text('');
//					           $('#limit_quote').find('em').text('');
//					    	   $('.to-quote').css({"cursor":"pointer"}).removeAttr("disabled");				    	   	  
//				    	   	}else{
//				    	   	  hour = Number(48 - hour).toFixed(0);
//				    	   	  if(hour == 0){
//				    	   		  hour = 1;
//				    	   	  }
//				    	   	  $('#limit_quote').find('span').text(hour+"小时");	
//				    	      $('.to-quote').css({"cursor":"not-allowed","color":"#ccc"}).attr("disabled","true").removeAttr('onclick');
//				    	      $('#title_ul').find('li').eq(1).find('a').removeAttr('onclick');
//				    	   	}
//			    	  }
			    	 
			    	 
		    	 

			    	 
			    	 
			    	 

			    	 
			    	 
			    	 
			    	 
			    	 
			    	 
			    	 
			    	 
			    	 //产品列表
			    	 $('#product_size').text(products.length);
			    	 $('#product_tbody').empty();
			    	 
			    	 var flag1 = false;    //判断是否有第二报价数
			    	 var flag2 = false;    //判断是否有第三报价数
			    	 		    		   
			           for(var j=0;j<products.length;j++){			    			   
			    		   var qty = products[j].quantityList;
		    			   var qtyUnit = products[j].quantityUnit;
		    			   var drawingPath = quoteInfo.drawingPath;	
		    			   var list = qty.split(",");							   
		    			   var unitList = qtyUnit.split(",");							   
						  	   	
						  //分享图片
		    			   imgSrc = ((products[0].drawingPathCompress == null || products[0].drawingPathCompress == '') ? '../images/pic2.png' : products[0].drawingPathCompress);
		    			   
		    			   
				    	  var div =	'<div class="row row3 m_l10 m_r10">'+
				    				'<div class="bf">'+
				    					'<div class="bf_bg"></div>'+
				    					(products[j].videoPath == null || products[j].videoPath == '' ? '' : 
				    					'<div class="video_close" onclick="closeVideo(this)"><span class="glyphicon glyphicon-remove"></span></div>'+
				    					'<video controls="controls" loop src="'+(products[j].videoPath == null ? '' : products[j].videoPath)+'" autobuffer></video>')+
				    				'</div>'+
				    			'<div class="imgs"><img src="'+((products[j].drawingPathCompress == null || products[j].drawingPathCompress == '') ? '../images/pic2.png' : products[j].drawingPathCompress)+'" alt="">'+
				    			(products[j].videoPath == null || products[j].videoPath == '' ? '' : '<div class="bf_video" onclick="playVideo(this)"><span class="iconfont">&#xe626;</span><a title="点击观看3D"></a></div>')+
				    			'</div>'+
				    			'<ul class="li_detail">'+
				    				'<li><div class="number">(<em>'+(j+1)+'</em>/<i>'+products.length+'</i>)</div><span>'+products[j].productName+'</span></li>'+
				    				'<li><em>基本工艺：</em><span>'+(products[j].process == null ? quoteInfo.mainProcess : products[j].process)+'</span></li>'+
				    				'<li class="clearfix"><em class="mr_10">材料：</em><span>'+(products[j].materials == null ? '-' : products[j].materials)+'</span></li>'+
				    				'<li class="clearfix lis"><em class="em_weight">单位重量（kg）：</em><span>'+(products[j].weight == null ? '-' : products[j].weight)+'</span></li>'+
				    			'</ul>'+
	    			   
//							 '<div class="row row3 m_l10 m_r10">'+
//									   '<div class="bf"><div class="bf_bg"></div>'+  
//										'<div class="video_close" onclick="closeVideo(this)"><span class="glyphicon glyphicon-remove"></span></div>'+  
//											'<video controls="controls" autoplay="" loop="" src="'+(products[j].videoPath == null ? '' : products[j].videoPath)+'"></video>'+ 					
//										'</div>'+ 
//										'<div class="imgs">'+
//											'<img src="'+((products[j].drawingPathCompress == null || products[j].drawingPathCompress == '') ? '../images/pic2.png' : products[j].drawingPathCompress)+'" alt="">'+
//											(products[j].videoPath == null || products[j].videoPath == '' ? '' : '<div class="bf_video" onclick="playVideo(this)"><span class="iconfont">&#xe626;</span><a title="点击观看3D"></a></div>')+
//											'</div>'+				
//										'<ul class="li_detail">'+
//											'<li>'+
//												'<div class="number">(<em>'+(j+1)+'</em>/<i>'+products.length+'</i>)</div>'+
//												'<span>'+products[j].productName+'</span>'+
//											'</li>'+
//											'<li>'+
//												'<em>基本工艺：</em>'+
//												'<span>'+(products[j].process == null ? quoteInfo.mainProcess : products[j].process)+'</span>'+
//											'</li>'+						
//											'<li class="clearfix">'+							
//													'<em>材料：</em>'+
//													'<span>'+(products[j].materials == null ? '-' : products[j].materials)+'</span>'+														
//											'</li>'+
//											'<li class="clearfix lis">'+												
//													'<em class="em_weight">单位重量（kg）：</em>'+
//													'<span>'+(products[j].weight == null ? '-' : products[j].weight)+'</span>'+						
//											'</li>'+		
//										'</ul>'+
										'<table class="table2">	'+					
											'<tbody>'+										 
											(list.length > 0 ? ('<tr>'+
													'<td>订量一</td>'+
													'<td><span>'+(list.length > 0 ? list[0] : '')+'</span>'+(qtyUnit.length > 0 ? qtyUnit[0] : '')+'</td>'+
												'</tr>') : '' )+
										    (list.length > 1 ? ('<tr>'+
													'<td>订量二</td>'+
													'<td><span>'+(list.length > 1 ? list[1] : '')+'</span>'+(qtyUnit.length > 1 ? qtyUnit[1] : qtyUnit[0])+'</td>'+
												'</tr>') : '' )+
											(list.length > 2 ? ('<tr>'+
													'<td>订量三</td>'+
													'<td><span>'+(list.length > 2 ? list[2] : '')+'</span>'+(qtyUnit.length > 2 ? qtyUnit[2] : qtyUnit[0])+'</td>'+
											    '</tr>') : '' )+
											'</tbody>'+
										'</table>'+
										'<div class="row yg">'+
											'<em>年用预估量:</em>'+
											'<span>'+(products[j].annualQuantity == null ? '' : products[j].annualQuantity)+'</span>'+
										'</div>'+
										'<div class="row row0">'+
											'<label for="" class="col-xs-2">零件描述:</label>'+
											'<div class="col-xs-10 div_textarea">'+(products[j].productRemark == null ? '无' : products[j].productRemark)+'</div>'+
										'</div>'+
									'</div>';
						     
							
							     $('#product_list').append(div);						     

			    	 }
			           
					    //历史报价记录
					    var supplierQuoteInfo = result.data.supplierQuoteInfo;
					    var supplierQuoteProducts = result.data.supplierQuoteProducts;
				    	var supplierQuoteProduct = result.data.supplierQuoteProduct; 
				    	$('.history').empty(); 
				    	if(supplierQuoteInfo != null && supplierQuoteInfo!= ''){
				    		
				    		$('.history_title').show();
				    		$('.to-quote').text('更新报价');
				    		
				    		        var tables = '';	    					    		
 							    	for(var k=0;k<supplierQuoteProduct.length;k++){		    				    				
								    		for(var n=0;n<supplierQuoteProducts[k].length;n++){								    		
								    			//只显示最近一次报价数据
										    	if(k==0){
											    		var qty = supplierQuoteProducts[k][n].quantityList;
											    		var qtyList = qty.split(",");
										    			
									                    var table = '<table>'+
										                    '<thead>'+
										                    '<tr>'+
										                        '<th style="color:#006dcc;width:100px;border-bottom:0 none;">'+products[n].productName+'</th>'+
										                        '<th style="border-bottom:0 none;">订量一</th>'+(supplierQuoteProducts[k][n].quoteUnitPrice2 == 0 ? '' : '<th style="border-bottom:0 none;">订量二</th>')+(supplierQuoteProducts[k][n].quoteUnitPrice3 == 0 ? '' : '<th style="border-bottom:0 none;">订量三</th>')+
										                    '</thead>'+
										                    '<tbody>'+
										                    '<tr>'+
										                        '<td>目标报价数量</td>'+
										                        '<td>'+(qtyList.length > 0 ? qtyList[0] : '')+'</td>'+(qtyList.length > 1 ? '<td>'+qtyList[1]+'</td>' : '')+(qtyList.length > 2 ? '<td>'+qtyList[2]+'</td>' : '')+
										                    '</tr>'+
										                    '<tr>'+
										                        '<td>单价 (元)</td>'+
										                        '<td>'+(supplierQuoteProducts[k][n].quoteUnitPrice1)+'</td>'+(supplierQuoteProducts[k][n].quoteUnitPrice2 == 0 ? '' : '<td>'+supplierQuoteProducts[k][n].quoteUnitPrice2+'</td>')+(supplierQuoteProducts[k][n].quoteUnitPrice3 == 0 ? '' : '<td>'+supplierQuoteProducts[k][n].quoteUnitPrice3+'</td>')+		       
										                    '</tr>'+
										                    '<tr>'+
										                        '<td>模具费 (元)</td>'+
										                        '<td>'+(supplierQuoteProducts[k][n].quoteMoldPrice1)+'</td>'+(supplierQuoteProducts[k][n].quoteUnitPrice2 == 0 ? '' : '<td>'+supplierQuoteProducts[k][n].quoteMoldPrice2+'</td>')+(supplierQuoteProducts[k][n].quoteUnitPrice3 == 0 ? '' : '<td>'+supplierQuoteProducts[k][n].quoteMoldPrice3+'</td>')+		       
										                    '</tr>'+
										                    '<tr>'+
										                    '<td>总计 (元)</td>'+
										                    '<td>'+Number((qtyList[0]*supplierQuoteProducts[k][n].quoteUnitPrice1 + supplierQuoteProducts[k][n].quoteMoldPrice1)).toFixed(0)+'</td>'+(supplierQuoteProducts[k][n].quoteUnitPrice2 == 0 ? '' : '<td>'+(Number(qtyList[1]*supplierQuoteProducts[k][n].quoteUnitPrice2) + Number(supplierQuoteProducts[k][n].quoteMoldPrice2))+'</td>')+(supplierQuoteProducts[k][n].quoteUnitPrice3 == 0 ? '' : '<td>'+(Number(qtyList[2]*supplierQuoteProducts[k][n].quoteUnitPrice3) + Number(supplierQuoteProducts[k][n].quoteMoldPrice3))+'</td>')+		       
										                    '</tr>'+
										                    '</tbody>'+
										                '</table>';	
								                    tables +=table;
										    	  }
								    		}							    											    									    	
											                	
								    	} 				
								    	var historyQuote = '<p><div class="down_up"><img class="red" src="image/red.png"><img class="green" src="image/green.png" ></div>日期：<span>'+new Date(supplierQuoteProduct[0].createTime.replace(/-/g,"/").split(".")[0]).Format("yyyy-MM-dd hh:mm:ss")+'</span></p>'+tables+"</div>";							    	
								    	$('.history').append(historyQuote);
								    	//历史报价展开和缩回
								    	$('.detail .history .down_up img').on('click',function(){
								    		if($(this).hasClass('green')){
								    			$('.detail .history .down_up .red').show();
								    			$('.detail .history .down_up .green').hide();
								    			$('.detail .history table').show();
								    		}else{
								    			$('.detail .history .down_up .green').show();
								    			$('.detail .history .down_up .red').hide();		
								    			$('.detail .history table').hide();
								    		}
								    	 })
				    	}
				    				           
				    	
				    	//显示沟通消息内容
				    	 $('#message').empty();
				    	 //询盘消息
				    	 if(quoteMessages.length != 0){			    		 
				    		 for(var i=0;i<quoteMessages.length;i++){	
				    			 
				    			 var file_div = '';
				    			 if(quoteMessages[i].fileName != null && quoteMessages[i].fileName != '' && quoteMessages[i].fileName != undefined){
				    				 file_div = '<span class="dialog_fj">附件:<a onclick="download_file(\''+quoteMessages[i].id+'\')">'+quoteMessages[i].fileName+'</a></span>';
				    			 }		
				    			 
				    			 if(quoteMessages[i].replyStatus == 0){
				    				 var message_div =  '<div class="dialog_0 dialog_a clearfix">'+
										    					'<div class="pull-left wrap col-xs-10 clearfix">'+
										    						'<div class="top"><em class="pull-left">'+(quoteMessages[i].realName != null ? quoteMessages[i].realName : quoteMessages[i].userName)+'</em><em class="pull-right">'+(new Date(quoteMessages[i].sendTime.replace(/-/g,"/").split(".")[0])).Format("yyyy-MM-dd hh:mm:ss")+'</em></div>'+
										    						'<p>'+quoteMessages[i].messageDetails+'</p>'+file_div+
										    					'</div>'+
										    					'<div class="imgs pull-right"><img src="'+(quoteMessages[i].photo != null ?  quoteMessages[i].photo : (quoteMessages[i].factoryLogo == null || quoteMessages[i].factoryLogo == '' ? '../images/defaultLogo.png' : "/static_img/factory_logo/"+quoteMessages[i].factoryId +'\/'+ quoteMessages[i].factoryLogo+""))+'" /></div>'+
										    				'</div>';
					    				 
					    				 $('#message').append(message_div);
				    			 }
				    			 if(quoteMessages[i].replyStatus == 1){
				    				 var message_div =  '<div class="dialog_0 dialog_b clearfix">'+
									    					'<div class="pull-right wrap col-xs-10 clearfix">'+
									    						'<div class="top"><em class="pull-left">'+(quoteMessages[i].realName != null ? quoteMessages[i].realName : quoteMessages[i].userName)+'</em><em class="pull-right">'+(new Date(quoteMessages[i].sendTime.replace(/-/g,"/").split(".")[0])).Format("yyyy-MM-dd hh:mm:ss")+'</em></div>'+
									    						'<p>'+quoteMessages[i].messageDetails+'</p>'+file_div+
									    					'</div>'+
									    					'<div class="imgs pull-left"><img src="'+(quoteMessages[i].photo != null ?  quoteMessages[i].photo : (quoteMessages[i].factoryLogo == null || quoteMessages[i].factoryLogo == '' ? '../images/defaultLogo.png' : "/static_img/factory_logo/"+quoteMessages[i].factoryId +'\/'+ quoteMessages[i].factoryLogo+""))+'" /></div>'+
									    				'</div>';
				    				     $('#message').append(message_div);
				    			 }
				    			
				    		 }	    		 
				    	 }
				    	 $('#message').scrollTop( $('#message').height());
				    	
				    	
	    		 }		 
	      })
			 
	      
	      
	      
	      

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
				//window.location.href = "/fastermake-wechat/m-zh/error.html";
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

		    title: shareTitle, // 分享标题

		    desc: orderInfo, // 分享描述

		    link : website + '/m-zh/detail.html?orderId='+orderId, // 分享链接

		    imgUrl: website + imgSrc, // 分享图标

		    type: 'link', // 分享类型,music、video或link，不填默认为link

		   /* dataUrl: '',*/ // 如果type是music或video，则要提供数据链接，默认为空

		    success: function () { 
		        // 用户确认分享后执行的回调函数
		    	
		    	setTimeout(function(){
		    		easyDialog.open({
						  container : {
						    content : '分享成功'
						  },
			    		  autoClose : 1000
						});
				}, 0);
		    	
		    },

		    /*cancel: function () {
		        // 用户取消分享后执行的回调函数

		    }*/

		});
	
	//分享到朋友圈
	wx.onMenuShareTimeline({
		
		title: shareTitle, // 分享标题
		
		desc: orderInfo, // 分享描述
		
		link : website + '/m-zh/detail.html?orderId='+orderId,
		
		imgUrl : website + imgSrc,
		
		success : function(){
			// 用户确认分享后执行的回调函数
			
			setTimeout(function(){
				easyDialog.open({
					  container : {
					    content : '分享成功'
					  },
		    		  autoClose : 1000
					});
			}, 0);
		},
		
		/*cancel : function(){
			// 用户取消分享后执行的回调函数
			
		}*/
	});
})
})

/* 点击空白处，关闭大图*/
function showBig_pic(obj){	
	var str = $(obj).find('img').attr('src');
	$('.offer .big_pic img').attr('src',str);
	$('.pic_bg').show();
	$('#big_pic').show();
	$('.offer .pic_bg').on('click',function(){
		$('.big_pic').hide();
	})
};
function closeBigImg(){
	$('.big_pic').hide();
}

//打开视频
function playVideo(obj){
//	$('.bf_bg').show();
//	$(obj).parent().prev().show();
//	$('.detail .bf_bg').on('click',function(){
//		$('.detail .bf').hide();
//	})
	
	var $video = $(obj).parent().prev().find('video');
	$video.get(0).play();
}
function closeVideo(obj){
	$(obj).parent().hide();
	
//	var $video = $(obj).next();
//	$video.get(0).pause();
}


var count = 3;
var countdown = null;
//添加取消收藏
function addOrCancelCollect(){
  
  $.post("/inquiry/addOrCancelCollect.do",	
		  {
	     "orderId":orderId	   
		 },
		 function(result){
	       if(result.state == 0){
	    	  var status = result.data;
	    	  if(status == '0'){
	    		  $('#collect_order').text("收藏");	    		 
		    	  $('#collect_order').prev().css( "background" ,"url(../images/sc.png)"); 
		    	  easyDialog.open({
					  container : {
					    content : '已取消收藏'
					  },
		    		  overlay : false,
		    		  autoClose : 1000
					});
	    	  }else if(status == '1'){
	    		  $('#collect_order').text("已收藏"); 
		    	  $('#collect_order').prev().css( "background" ,"url(../images/heart.png)");
		    	  $('.supplier_detail .tc').show();
		    	  countdown = setInterval(timeOut,1000); 	    	 
	    	  }
	       }	  
        })
   
}



/**
 * 弹框倒计时变化
 */ 
 function timeOut(){
	 count--;
     $("#count_down").html(count + "s");
     if(count<1){
         clearInterval(countdown);
         count = 3;
         $("#count_down").html(count + "s");
         $('.supplier_detail .tc').hide();
     }
 };


//发送询盘消息
function send_message(obj){
	 	
	 //如果还未登录，跳转登录页
	 if(factoryInfo == null || factoryInfo == '' || factoryInfo == undefined){
	    $.post("/account/addHistoryUrl.do", 
				   { url: "/m-zh/detail.html?orderId="+orderId},
				   function(data){
				    
			  });
		 window.location = "/m-zh/login.html";
		 return false;
	 }
	
	 var message = $('#message_details').val();
	 var filePath = $('#filePath').val();
	 var fileName = $('#fileName').val();
	 if($.trim(message) == null || $.trim(message) == '' || $.trim(message) == undefined){
			easyDialog.open({
				  container : {
				    content : '消息不能为空！'
				  },
	    		  overlay : false,
	    		  autoClose : 1000
				});
		 return false;
	 }
	
	 $(obj).css({'background-color':'#ddd'}).attr("disabled",true);
	 $.post("/inquiry/addQuoteMessage.do",	
			  {
		     "orderId" : orderId,
		     "message":message,
		     "filePath":filePath,
		     "fileName":fileName 
			 },
			 function(result){
				 
	 $(obj).css({'background-color':'#006dcc'}).removeAttr('disabled');   	
		       if(result.state == 0){
		    	  var quoteMessages = result.data;

			    	 //询盘消息
		    	     $('#message').empty();
		    	     $(obj).next().find('a').html('');
			    	 if(quoteMessages.length != 0){			    		 
			    		 for(var i=0;i<quoteMessages.length;i++){	
			    			 
			    			 var file_div = '';
			    			 if(quoteMessages[i].fileName != null && quoteMessages[i].fileName != '' && quoteMessages[i].fileName != undefined){
			    				 file_div = '<span class="dialog_fj">附件:<a onclick="download_file(\''+quoteMessages[i].id+'\')">'+quoteMessages[i].fileName+'</a></span>';
			    			 }		
			    			 
			    			 if(quoteMessages[i].replyStatus == 0){
			    				 var message_div =  '<div class="dialog_0 dialog_a clearfix">'+
									    					'<div class="pull-left wrap col-xs-10 clearfix">'+
									    						'<div class="top"><em class="pull-left">'+(quoteMessages[i].realName != null ? quoteMessages[i].realName : quoteMessages[i].userName)+'</em><em class="pull-right">'+(new Date(quoteMessages[i].sendTime.replace(/-/g,"/").split(".")[0])).Format("yyyy-MM-dd hh:mm:ss")+'</em></div>'+
									    						'<p>'+quoteMessages[i].messageDetails+'</p>'+file_div+
									    					'</div>'+
									    					'<div class="imgs pull-right"><img src="'+(quoteMessages[i].photo != null ?  quoteMessages[i].photo :(quoteMessages[i].factoryLogo == null || quoteMessages[i].factoryLogo == '' ? '../images/defaultLogo.png' : "/static_img/factory_logo/"+quoteMessages[i].factoryId +'\/'+ quoteMessages[i].factoryLogo+""))+'" /></div>'+
									    				'</div>';
				    				 
				    				 $('#message').append(message_div);
			    			 }
			    			 if(quoteMessages[i].replyStatus == 1){
			    				 var message_div =  '<div class="dialog_0 dialog_b clearfix">'+
								    					'<div class="pull-right wrap col-xs-10 clearfix">'+
								    						'<div class="top"><em class="pull-left">'+(quoteMessages[i].realName != null ? quoteMessages[i].realName : quoteMessages[i].userName)+'</em><em class="pull-right">'+(new Date(quoteMessages[i].sendTime.replace(/-/g,"/").split(".")[0])).Format("yyyy-MM-dd hh:mm:ss")+'</em></div>'+
								    						'<p>'+quoteMessages[i].messageDetails+'</p>'+file_div+
								    					'</div>'+
								    					'<div class="imgs pull-left"><img src="'+(quoteMessages[i].photo != null ?  quoteMessages[i].photo :(quoteMessages[i].factoryLogo == null || quoteMessages[i].factoryLogo == '' ? '../images/defaultLogo.png' : "/static_img/factory_logo/"+quoteMessages[i].factoryId +'\/'+ quoteMessages[i].factoryLogo+""))+'" /></div>'+
								    				'</div>';
			    				     $('#message').append(message_div);
			    			 }
			    			
			    		 }	 
			    		 
			    		 $('#message').scrollTop( $('#message').height());
			    	 }
		    	  
			    	$('#filePath').val('');
			    	$('#fileName').val('');
			    	$('#message_details').val('');
//			    	easyDialog.open({
//			      		   container : {
//			          		     header : '  提示信息',
//			          		     content : ' 发送成功'
//			      		   },
//							  overlay : false,
//							  autoClose : 1000
//			      		 });   
			    	
		       }  
		  })
}


 //上传后返回图纸路径，以逗号隔开
function show_drawingName(obj){
		var path = $(obj).val();
	    sppath = path.split("\\");
	    var drawingName = sppath[sppath.length-1];	  	   
	    if(drawingName == null || drawingName == '' || drawingName == undefined){
	    	return false;
	    }else{
	       $('#fileName').val(drawingName);
	       $(obj).next().next().text(drawingName);
    	   autTime(); 
		   $('#upload_title').children().text("上传进度");
	    }	 		    	
     		  
		  //先上传后获取上传文件路径
		 $("#file_upload_id").ajaxSubmit({    			
			type: "post",
			url: "/upload/uploadAttachmentAndChangeName.do",
	     	dataType: "text",
	     	success: function(str){
	     	var result = eval('(' + str + ')');	
	     	if(result.state == 0){
 	             $('#filePath').val(result.data);  
	     	}else{
       	     	easyDialog.open({
	      		   container : {
	          		     header : '  提示信息',
	          		     content : ' 上传失败 '
	      		   },
					  overlay : false,
					  autoClose : 1000
	      		 });   
       	     	$('#show_upload_dialog').hide();
	     	}	

	     	},
			error: function(){
				 easyDialog.open({
         		   container : {
             		     header : '  提示信息',
             		     content : '  上传失败 '
         		   },
					  overlay : false,
					  autoClose : 1000
         		 });   
	     		$('#show_upload_dialog').hide();
			}       	     	
	 	}); 	 		    

 }


//下载附件
function download_file(id){
	window.location = "/download/message-file-download.do?id="+id;
}
//下载图纸
function download_drawing(orderId){
	window.location = "/download/drawing-download.do?orderId="+orderId;
}

var factoryInfo = getCookie("factoryInfo");
function to_offer(){
   if (factoryInfo == null || factoryInfo == '' || factoryInfo == undefined) {
	   
	   $.post("/account/addHistoryUrl.do", 
			   { url: "/m-zh/offer.html?orderId="+orderId},
			   function(data){
			    
			   });
	   
       window.location = "/m-zh/login.html";
   }else{
	   window.location = "/m-zh/offer.html?orderId="+orderId; 
   }	
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





