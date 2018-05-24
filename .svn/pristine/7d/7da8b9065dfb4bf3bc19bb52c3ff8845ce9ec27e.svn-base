
var orderId;
var str = window.location.search;
if(str != null && str != ''){
	str = str.substr(1);
	orderId = str.split("&")[0].split("=")[1];
	$('#orderId').val(orderId);
}	

//判断客户是否登录
var factoryInfo = getCookie("factoryInfo");

$(function(){
	
	
	$.post("/inquiry/queryInquiryDetails.do",
			{"orderId" : orderId},
			 function(result){
			      if(result.state == 0){
			    	 var quoteMessages = result.data.quoteMessages; 
			    	 var quoteInfo = result.data.quoteInfo; 
			    	 var products = result.data.products; 
			    	 var compareProcess = result.data.compareProcess; 
			    	 var compareQualification = result.data.compareQualification; 
			    	 var compareState = result.data.compareState; 
			    	 var compareEquipment = result.data.compareEquipment; 
			    	 var compareProduct = result.data.compareProduct; 
			    	 var isVip = result.data.isVip;
			    	 var isCollect = result.data.isCollect;
			    	 //是否属于被邀请
			    	 var inviteFlag = result.data.inviteFlag;
			    	 
			    	 
			    	 
			    	 //询盘条件
			    	 $('#quoteId').text(quoteInfo.orderId);
			    	 if(quoteInfo.quoteTitle == null || quoteInfo.quoteTitle == ''){
			    		 $('#quote_title').hide();
			    		 $('#quote_title').prev().hide();
			    	 }else{
				    	 $('#quote_title').text(quoteInfo.quoteTitle);
			    	 }

			    	 
			   		 $('#comparison_thead').find('tr').append('<th style="width:105px;">工艺</th>');
		    		 $('#comparison_tbody').find('tr').eq(0).append('<td><img src="'+(compareProcess == 1 ? '../images/xz.png' : '../images/error.png')+'" alt=""></td>');

			    	 if(quoteInfo.quoteLocation != 0){
			    		 $('#comparison_thead').find('tr').append('<th style="width:72px;">地域</th>');
			    		 $('#comparison_tbody').find('tr').eq(0).append('<td><img src="'+(compareState == 1 ? '../images/xz.png' : '../images/error.png')+'" alt=""></td>');		
			    	 }else{
			    		 $('#comparison_thead').find('tr').append('<th style="width:72px;">地域</th>');
			    		 $('#comparison_tbody').find('tr').eq(0).append('<td><img src="../images/xz.png" alt=""></td>');
			    	 }
			    	 if(quoteInfo.qualification != null && quoteInfo.qualification != ''){			   			   		 
			    		 $('#comparison_thead').find('tr').append('<th style="width:120px;">资格认证</th>');
			    		 $('#comparison_tbody').find('tr').eq(0).append('<td><img src="'+(compareQualification == 1 ? '../images/xz.png' : '../images/error.png')+'" alt=""></td>');
			    	 }	   
			    		
			    	 //比较是否有产品
			    	 if(!(quoteInfo.productKeywords == null || quoteInfo.productKeywords == '' || quoteInfo.productKeywords == undefined)){
				    	 $('#comparison_thead').find('tr').append('<th style="width:80px;">现有产品</th>');
			    		 $('#comparison_tbody').find('tr').eq(0).append('<td><img src="'+(compareProduct == 0 ? '../images/error.png' : '../images/xz.png')+'" alt=""></td>'); 
			    	 }
			    	 
		    		 //比较设备
			    	 if(!(quoteInfo.equipmentKeywords == null || quoteInfo.equipmentKeywords == '' || quoteInfo.equipmentKeywords == undefined)){		    		 
			    		 $('#comparison_thead').find('tr').append('<th style="border-right: 1px solid #eee;width:80px;">设备要求</th>');
		    		     $('#comparison_tbody').find('tr').eq(0).append('<td style="border-right: 1px solid #eee;"><img src="'+(compareEquipment == 0 ? '../images/error.png' : '../images/xz.png')+'" alt=""></td>');
			    	 }
		    	
			    	 
			    	 
			    	 $('#country').text(quoteInfo.country);
			    	 $('#update_date').text(quoteInfo.updateTime);
			    	 $('#payment_term').text(quoteInfo.paymentTerm);
			    	 $('#confidentiality_agreement').text((quoteInfo.confidentialityAgreement == 0  ? '无需保密协议': '有保密协议'));
			    	 $('#end_date').text(quoteInfo.quoteEndDate);
//			    	 $('#quote_status').text(quoteInfo.orderStatus == 1 ? '进行中' : (quoteInfo.orderStatus == 2 ? '已结束' : (quoteInfo.orderStatus == 3 ? '已取消': '')));
			    	 $('#publish_date').text((new Date(quoteInfo.publishDate.replace(/-/g,"/").split(".")[0])).Format("yyyy-MM-dd"));
			    	 $('#process').text(quoteInfo.mainProcess);
			    	 if(inviteFlag == true){
			    		 $('#current_number').text(quoteInfo.currentNumber +'(指定工厂报价)');	 
			    	 }else{
			    		 $('#current_number').text((quoteInfo.currentNumber < 5 ? quoteInfo.currentNumber+'/5' : quoteInfo.currentNumber+'/5 (报价工厂数已满)'));
			    		 //当前报价人数大于4个则不能进行继续报价
			    		 if(quoteInfo.currentNumber > 4){
			    		     $('.to-quote').css({"cursor":"not-allowed","color":"#ccc"}).attr("disabled","true").removeAttr('onclick');
				    	     $('#title_ul').find('li').eq(1).find('a').removeAttr('onclick'); 
			    		 }
			    	 }			    	
			    	 $('#delivery_date').text((quoteInfo.deliveryDate == null ? 'N/A' : quoteInfo.deliveryDate));
			    	 
			    	 //产品介绍			    	 
			    	 $('#comparison_tbody').find('tr').eq(1).find('td').append('<h3>询盘描述</h3>');
			    	 $('#comparison_tbody').find('tr').eq(1).find('td').append('<p>主要工艺：'+(quoteInfo.mainProcess == null ? '' : quoteInfo.mainProcess)+"</p>");
			    	 $('#comparison_tbody').find('tr').eq(1).find('td').append('<p>地域：'+(quoteInfo.quoteLocation == 1 ? '江浙沪' : (quoteInfo.quoteLocation == 2 ? '深圳、广东、福建' : '不限'))+"</p>");
			    	 if(quoteInfo.qualification != null && quoteInfo.qualification != '' && quoteInfo.qualification != undefined){
			    		 $('#comparison_tbody').find('tr').eq(1).find('td').append('<p>资格认证：'+quoteInfo.qualification+"</p>"); 
			    	 }
			    	 if(quoteInfo.productKeywords != null && quoteInfo.productKeywords != ''){
			    		 $('#comparison_tbody').find('tr').eq(1).find('td').append('<p>现有产品：'+(quoteInfo.productKeywords == null ? '' : quoteInfo.productKeywords)+"</p>");
			    	 }
			    	 if(quoteInfo.equipmentKeywords != null && quoteInfo.equipmentKeywords != ''){
			    		 $('#comparison_tbody').find('tr').eq(1).find('td').append('<p>设备要求：'+(quoteInfo.equipmentKeywords == null ? '' : quoteInfo.equipmentKeywords)+"</p>");
			    	 }
			    	 
			    	 if(quoteInfo.paymentTerm != null && quoteInfo.paymentTerm != '' && quoteInfo.paymentTerm != undefined){
			    		 $('#comparison_tbody').find('tr').eq(1).find('td').append('<p>付款条款：'+quoteInfo.paymentTerm+"</p>"); 
			    	 }
			    	 if(quoteInfo.quoteRemark != null && quoteInfo.quoteRemark != '' && quoteInfo.quoteRemark != undefined){
			    		 $('#comparison_tbody').find('tr').eq(1).find('td').append('<p>其他要求：'+quoteInfo.quoteRemark+"</p>");	
			    	 }

		
			    	 
			    	 
			    	 
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
			    	 
			    	 
			    	  //判断是否收藏
			    	 if(isCollect == 0){
		    		    $('#collect_order').text("收藏"); 
			    		$('#collect_order').prev().css( "background" ,"url(../images/sc.png)");
			    	 }else{
			    		$('#collect_order').text("已收藏"); 
			    		$('#collect_order').prev().css( "background" ,"url(../images/heart.png)");
			    	 }			    	 
			    	 
			    	 
			    	 
			    	 $('#message').empty();
			    	 //询盘消息
			    	 if(quoteMessages.length != 0){			    		 
			    		 for(var i=0;i<quoteMessages.length;i++){	
			    			 
			    			 var file_div = '';
			    			 if(quoteMessages[i].filePath != null && quoteMessages[i].filePath != '' && quoteMessages[i].filePath != undefined){
			    				 var fileName = quoteMessages[i].filePath.substr(quoteMessages[i].filePath.lastIndexOf('\/')+1);  
			    				 var gen = fileName.substr(fileName.lastIndexOf('.')+1);
			    				 var split = fileName.split("&");
			    				 fileName = split[0] + "." + gen;
			    				 file_div = '<div class="file-download">附件:<a style="text-decoration: underline;" onclick="download_file(\''+quoteMessages[i].id+'\')">'+fileName+'</a></div>';	 
			    			 }		
			    			 
			    			 if(quoteMessages[i].replyStatus == 0){
			    				 var message_div =  '<div class="d d2">'+
			    				                         '<div class="imgs pull-left">'+
								                         '<img src="../images/detal1.png" alt="" class="pull-left"/></div>'+
								                         '<div class="c pull-left">'+
								                             '<div class="arrs">'+
								                                 '<div class="arr1"></div>'+
								                                 '<div class="arr1 arr2"></div>'+
								                             '</div>'+
								                             '<div class="t1 clearfix">'+
								                                 '<span class="pull-left">'+quoteMessages[i].userName+'</span>'+
								                                 '<em class="pull-right">'+(new Date(quoteMessages[i].sendTime.replace(/-/g,"/").split(".")[0])).Format("yyyy-MM-dd hh:mm:ss")+'</em>'+
								                             '</div>'+
								                             '<div class="t2">'+quoteMessages[i].messageDetails+'</div>'+
								                        ' </div>'+
								                     '</div>'; 
			    				 $('#message').append(message_div);
			    			 }
			    			 if(quoteMessages[i].replyStatus == 1){
			    				 var message_div =      '<div class="d d1">'+
							                                 '<div class="c pull-left">'+
							                                 '<div class="arrs">'+
							                                     '<div class="arr1"></div>'+
							                                     '<div class="arr1 arr2"></div>'+
							                                 '</div>'+
							                                 '<div class="t1 clearfix">'+
							                                     '<span class="pull-left">'+quoteMessages[i].userName+'</span>'+
							                                     '<em class="pull-right">'+(new Date(quoteMessages[i].sendTime.replace(/-/g,"/").split(".")[0])).Format("yyyy-MM-dd hh:mm:ss")+'</em>'+
							                                 '</div>'+
							                                 '<div class="t2">'+quoteMessages[i].messageDetails+'</div>	'+file_div+					
							                             '</div><div class="imgs pull-right">'+
							                             '<img src="../images/detail2.png" alt="" class="pull-left"/></div>'+	
							                         '</div>';
			    				 $('#message').append(message_div);
			    			 }
			    			
			    		 }	    		 
			    	 }
			    	 
			    	 
			    	 

			    	 
			    	 
			    	 
			    	 
			    	 
			    	 
			    	 
			    	 
			    	 //产品列表
			    	 $('#product_count').text(products.length);
			    	 $('#product_tbody').empty();
			    	 
			    	 var flag1 = false;    //判断是否有第二报价数
			    	 var flag2 = false;    //判断是否有第三报价数
			    	 
			    	 if(products.length == 1){			    		   
			    		   for(var j=0;j<products.length;j++){			    			   
			    			   var qty = products[j].quantityList;
			    			   var drawingPath = products[j].drawingPath;			    			   
			    			   var list = qty.split(",");							   
							   
						  var tr1 = '<tr><td>'+
				                  				'<em onclick="view_detail(this)"></em>'+
				                  				'<div class="imgs">'+
				                               '<img src="'+((products[j].drawingPathCompress == null || products[j].drawingPathCompress == '') ? '../images/pic2.png' : products[j].drawingPathCompress)+'" alt=""/>'+
				                               '</div>'+
				                               (drawingPath == null ? '' : '<a href="###" class="fj1" onclick="download_drawing('+products[j].id+')">附件</a>')+
				                  			'</td>'+
				                  			'<td> <div class="ws"><span>'+products[j].productName+'</span></div></td>'+
				                  			'<td><div class="ws"><span>'+(products[j].process == null ? quoteInfo.mainProcess : products[j].process)+'</span></div></td>'+
				                  			'<td><div class="ws"><span>'+(products[j].materials == null ? '' : products[j].materials)+'</span></div></td>'+
				                  			'<td><div class="ws"><span>'+(products[j].weight == null ? '' : products[j].weight)+'</span></div></td>'+
				                  			'<td><div class="ws"><span>'+(list.length > 0 ? list[0] : '')+'</span></div></td>'+(list.length > 1 ? '<td><div class="ws"><span>'+(list.length > 1 ? list[1] : '')+'</span></div></td>' : '')+(list.length > 2 ? '<td><div class="ws"><span>'+(list.length > 2 ? list[2] : '')+'</span></div></td>' : '')+
				                  			'<td><div class="ws"><span>'+(products[j].annualQuantity == null ? '' : products[j].annualQuantity)+'</span></div></td>'+
			                  		   '</tr>';
							 var tr2 = '<tr class="trcol currdis">'+
			                    			'<td class="ljms">零件描述：</td>'+
			                       			'<td colspan="8">'+(products[j].productRemark == null ? '无' : products[j].productRemark)+
			                       			'</td>'+                             			
			                       		'</tr>'; 
							   
							  $('#product_tbody').append(tr1);   
							  $('#product_tbody').append(tr2);
							   
							   
								 //判断产品是否存在第二报价 第三报价

							     if(list.length == 2){
								      flag1 = true;
								 }else if(list.length >2){
									  flag1 = true;
									  flag2 = true;
								 }
			    		   }
			    		   $('.lj_det tbody em').click();
			    	 }
			    	 
			    	 if(products.length > 1){
			    		 for(var j=0;j<products.length;j++){			    			   
			    			   var qty = products[j].quantityList;
			    			   var drawingPath = products[j].drawingPath;
			    			   
			    			   var list = qty.split(",");
			    			   var tr1 = '<tr><td>'+
				                 				'<em onclick="view_detail(this)"></em>'+
				                 				'<div class="imgs">'+
				                              '<img src="'+((products[j].drawingPathCompress == null || products[j].drawingPathCompress == '') ? '../images/pic2.png' : products[j].drawingPathCompress)+'" alt=""/>'+
				                              '</div>'
				                              +(products[j].drawingPath == null ? '' : '<a href="###" class="fj1" onclick="download_drawing('+products[j].id+')">附件</a>')+
				                 			'</td>'+
				                 			'<td> <div class="ws"><span>'+products[j].productName+'</span></div></td>'+
				                  			'<td><div class="ws"><span>'+(products[j].process == null ? quoteInfo.mainProcess : products[j].process)+'</span></div></td>'+
				                  			'<td><div class="ws"><span>'+(products[j].materials == null ? '' : products[j].materials)+'</span></div></td>'+
				                  			'<td><div class="ws"><span>'+(products[j].weight == null ? '' : products[j].weight)+'</span></div></td>'+
				                  			'<td><div class="ws"><span>'+(list.length > 0 ? list[0] : '')+'</span></div></td>'+(list.length > 1 ? '<td><div class="ws"><span>'+(list.length > 1 ? list[1] : '')+'</span></div></td>' : '')+(list.length > 2 ? '<td><div class="ws"><span>'+(list.length > 2 ? list[2] : '')+'</span></div></td>' : '')+
				                  			'<td><div class="ws"><span>'+(products[j].annualQuantity == null ? '' : products[j].annualQuantity)+'</span></div></td>'+
				             		   '</tr>';
							 var tr2 = '<tr class="trcol currdis">'+
				               			'<td class="ljms">零件描述：</td>'+
				                  			'<td colspan="8">'+(products[j].productRemark == null ? '无' : products[j].productRemark)+
				                  			'</td>'+                             			
				                  		'</tr>'; 
							   
							  $('#product_tbody').append(tr1);   
							  $('#product_tbody').append(tr2);  
							   
							 //判断产品是否存在第二报价 第三报价

							     if(list.length == 2){
								      flag1 = true;
								 }else if(list.length >2){
									  flag1 = true;
									  flag2 = true;
								 }
			    		   }
			    	 }
			    	 
			    	 
			  	       //判断当前报价数(没有则不显示)
					   if(!flag2){
						   $('#product_thead').find('th').eq(7).remove();   
						   $('.trcol').find('td').eq(1).attr('colspan',7);
					   }
					   if(!flag1){
						   $('#product_thead').find('th').eq(6).remove();  
						   $('.trcol').find('td').eq(1).attr('colspan',6);
					   }

					   
					   //如果还未登录，详情和沟通消息不显示
					   if(factoryInfo == null || factoryInfo == '' || factoryInfo == undefined){
						   $('.quotation ').hide(); 
					   }
					   
						/* 隔行换色*/
						$(".supplier_detail .lj_det tbody tr:not('.trcol'):even").css({
							'background-color':'#f9f9f9'
						})
			    	 
			      }			      			 
	      })
			 
	      
})


//查看产品详情
function view_detail(obj){


    var $this = $(obj).parent().parent().next('.trcol');
    if($this.hasClass('currdis')){
            $this.removeClass('currdis');
        $(obj).css({
            'background':'url(../images/red.png) no-repeat'
        })
    }else{
        $this.addClass('currdis');
        $(obj).css({
            'background':'url(../images/green.png) no-repeat'
        })
    }

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
	
	 var message = $('#message_details').val();
	 var filePath = $('#filePath').val();
	 var fileName = $('#fileName').val();
	 if(message.trim() == null || message.trim() == '' || message.trim() == undefined){
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

		    		 $('#quote_message').find('.d').remove();
			    	 //询盘消息
			    	 if(quoteMessages.length != 0){			    		 
			    		 for(var i=0;i<quoteMessages.length;i++){	
			    			 var file_div = '';
			    			 if(quoteMessages[i].filePath != null && quoteMessages[i].filePath != '' && quoteMessages[i].filePath != undefined){
			    				 var fileName = quoteMessages[i].filePath.substr(quoteMessages[i].filePath.lastIndexOf('\/')+1); 
			    				 var gen = fileName.substr(fileName.lastIndexOf('.')+1);
			    				 var split = fileName.split("&");
			    				 fileName = split[0] + "." + gen;
			    				 file_div = '<div class="file-download">附件:<a style="text-decoration: underline;" onclick="download_file(\''+quoteMessages[i].id+'\')">'+quoteMessages[i].fileName+'</a></div>'; 
			    			 }		
			    			 
			    			 
			    			 if(quoteMessages[i].replyStatus == 0){
			    				 var message_div =     '<div class="d d2">'+
								                         '<img src="../images/detal1.png" alt="" class="pull-left"/>'+
								                         '<div class="c pull-left">'+
								                             '<div class="arrs">'+
								                                 '<div class="arr1"></div>'+
								                                 '<div class="arr1 arr2"></div>'+
								                             '</div>'+
								                             '<div class="t1 clearfix">'+
								                                 '<span class="pull-left">'+quoteMessages[i].userName+'</span>'+
								                                 '<em class="pull-right">'+(new Date(quoteMessages[i].sendTime.replace(/-/g,"/").split(".")[0])).Format("yyyy-MM-dd hh:mm:ss")+'</em>'+
								                             '</div>'+
								                             '<div class="t2">'+quoteMessages[i].messageDetails+'</div>'+file_div+
								                        ' </div>'+
								                     '</div>'; 
			    				 $('#message').append(message_div);
			    			 }
			    			 if(quoteMessages[i].replyStatus == 1){
			    				 var message_div =      '<div class="d d1">'+
							                                 '<div class="c pull-left">'+
							                                 '<div class="arrs">'+
							                                     '<div class="arr1"></div>'+
							                                     '<div class="arr1 arr2"></div>'+
							                                 '</div>'+
							                                 '<div class="t1 clearfix">'+
							                                     '<span class="pull-left">'+quoteMessages[i].userName+'</span>'+
							                                     '<em class="pull-right">'+(new Date(quoteMessages[i].sendTime.replace(/-/g,"/").split(".")[0])).Format("yyyy-MM-dd hh:mm:ss")+'</em>'+
							                                 '</div>'+
							                                 '<div class="t2">'+quoteMessages[i].messageDetails+'</div>	'+file_div+				
							                             '</div>'+
							                             '<img src="../images/detail2.png" alt="" class="pull-left"/>'+
							                         '</div>';
			    				 $('#message').append(message_div);
			    			 }
			    			
			    		 }	    		 
			    	 }
		    	  
			    	$('#filePath').val('');
			    	$('#fileName').val('');
			    	$('#message_details').val('');
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
function download_drawing(id){
	window.location = "/download/drawing-download.do?id="+id;
}

var factoryInfo = getCookie("factoryInfo");
function to_offer(){
   if (factoryInfo == null || factoryInfo == '' || factoryInfo == undefined) {
       window.location = "/zh/login.html";
   }else{
	   window.location = "/zh/offer.html?orderId="+orderId; 
   }	
}




