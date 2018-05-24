
var orderId = null;
var factoryId = null;
var str = window.location.search;
if(str != null && str != ''){
	str = str.substr(1);
	factoryId = str.split("&")[0].split("=")[1];
	orderId = str.split("&")[1].split("=")[1];
	$('#orderId').val(orderId);
}

$(function(){
	

	$.post("/factoryInquiry/queryQuoteDetailByCustomerId.do",
			{
		      "orderId":orderId,
		      "factoryId":factoryId
			},
			 function(result){
			      if(result.state == 0){
			    	 //获取沟通消息
			    	 var quoteMessages = result.data.quoteMessages; 
			    	 //获取报价信息
			    	 var supplierQuoteInfo = result.data.supplierQuoteInfo; 
			    	 var supplierQuoteProducts = result.data.supplierQuoteProducts; 		
			    	 var quoteInfo = result.data.quoteInfo;
			    	 //报价工厂信息
			    	 var factoryInfo = result.data.factoryInfo;
			    	 
			    	 //询盘条件
			    	 $('#quoteId').text(quoteInfo.orderId);
			    	 if(quoteInfo.quoteTitle == null || quoteInfo.quoteTitle == ''){
			    		 $('#quote_title').hide();
			    		 $('#quote_title').prev().hide();
			    	 }else{
				    	 $('#quote_title').text(quoteInfo.quoteTitle);
			    	 }
			    	 
			    	 
			    	 //未发布询盘不显示工厂报价详情
			    	 if(quoteInfo.orderStatus == 0){
			    		 $('.panel4').hide();
			    		 $('#quoteId').next().css({"color":"#ff5900"}).text('待发布');
			    	 }else if(quoteInfo.orderStatus == 1){
			    		 $('#quoteId').next().css({"color":"#FF973B"}).text('可报价'); 
			    	 }else if(quoteInfo.orderStatus == 2){
			    		 $('#quoteId').next().css({"color":"#4C88FF"}).text('已完成'); 
			    	 }else if(quoteInfo.orderStatus == 3){
			    		 $('#quoteId').next().css({"color":"#999999"}).text('已取消'); 
			    	 }else if(quoteInfo.orderStatus == 4){
			    		 $('#quoteId').next().css({"color":"#CCCCCC"}).text('已过期'); 
			    	 }else if(quoteInfo.orderStatus == 5){
			    		 $('#quoteId').next().css({"color":"#ff655d"}).text('授盘中'); 
			    	 }else if(quoteInfo.orderStatus == 6){
			    		 $('#quoteId').next().css({"color":"#6DCE59"}).text('生产中'); 
			    	 }else if(quoteInfo.orderStatus == 7){
			    		 $('.panel4').hide();
			    		 $('#quoteId').next().css({"color":"#ED1C24"}).text('未通过'); 
			    	 }
			    	 
			    	 
			         //显示公司优势
					 if(factoryInfo != null && factoryInfo != '' && factoryInfo != undefined){
					    	$('#factory_advantage').find('p:first').find('span').text((factoryInfo.technologicalAdvantage == null ? '-' : factoryInfo.technologicalAdvantage));
					    	$('#factory_advantage').find('p:eq(1)').find('span').text((factoryInfo.dominantMaterialModel == null ? '-' : factoryInfo.dominantMaterialModel));
					    	$('#factory_advantage').find('p:eq(2)').find('span').text((factoryInfo.dominantMaterialSize == 0 ? '大' : (factoryInfo.dominantMaterialSize == 1 ? '中' : (factoryInfo.dominantMaterialSize == 2 ? '小' : '-'))));
					    	$('#factory_advantage').find('p:eq(3)').find('span').text((factoryInfo.mainProcess == null ? '-' : factoryInfo.mainProcess));
					    	$('#factory_advantage').find('p:eq(4)').find('span').text((factoryInfo.siteSize == 0 ? '能达到世界500强公司的验厂标准或者汽配行业Tier1的验厂标准' : (factoryInfo.siteSize == 1 ? '能通过正常的验厂' :(factoryInfo.siteSize == 2 ? '暂不允许验厂' : '-'))));
					  }
			    	 
			    	 
			    	 if(supplierQuoteInfo != null){
				    	 $('#factory_name').text(supplierQuoteInfo.factoryName);
				    	 $('#quote_date').text(getDate(supplierQuoteInfo.quoteDate,supplierQuoteInfo.validityDays));
	//			    	 $('#validity_days').text((supplierQuoteInfo.validityDays == null ? '' : supplierQuoteInfo.validityDays+"（天）"));
				    	 $('#payment_term').text(supplierQuoteInfo.paymentTerm);
//				    	 $('#payment_remark').text((supplierQuoteInfo.paymentRemark == null ? '-' : supplierQuoteInfo.paymentRemark));
				    	 $('#quote_reasons').text((supplierQuoteInfo.paymentRemark == null ? '-' : supplierQuoteInfo.paymentRemark));
			             if(supplierQuoteInfo.attachmentPath != null && supplierQuoteInfo.attachmentPath != '' && supplierQuoteInfo.attachmentPath != undefined){
			            	 var fileName = supplierQuoteInfo.attachmentPath.substr(supplierQuoteInfo.attachmentPath.lastIndexOf('\/')+1);  
		    				 var gen = fileName.substr(fileName.lastIndexOf('.')+1);
		    				 var split = fileName.split("&");
		    				 fileName = split[0] + "." + gen;
		    				 $('#file_attachment').find('span:first').text(supplierQuoteInfo.attachmentName);
		    				 var base = new Base64();
		    	             var path = base.encode(supplierQuoteInfo.attachmentPath);
		    				 $('#file_attachment').attr('href','/download/quote-file-download.do?id='+supplierQuoteInfo.id+'');
			             }else{
			            	 $('#file_attachment').hide();
			             }
			    	 }
			    	 
			    	 
			    	 
			    	 $('#message').empty();
			    	 //询盘消息
			    	 if(quoteMessages.length != 0){		
			    		 
			    		 $('#message').css({"height":"325px"});
			    		 
			    		 for(var i=0;i<quoteMessages.length;i++){	
			    			 
			    			 var file_div = '';
			    			 if(quoteMessages[i].fileName != null && quoteMessages[i].fileName != '' && quoteMessages[i].fileName != undefined && quoteMessages[i].filePath != null && quoteMessages[i].filePath != '' && quoteMessages[i].filePath != undefined){
//			    				 var fileName = quoteMessages[i].filePath.substr(quoteMessages[i].filePath.lastIndexOf('\/')+1);  
//			    				 var gen = fileName.substr(fileName.lastIndexOf('.')+1);
//			    				 var split = fileName.split("&");
//			    				 fileName = split[0] + "." + gen;
			    				 file_div = '<div class="file-download">附件:<a style="text-decoration: underline;" onclick="download_file(\''+quoteMessages[i].id+'\')">'+quoteMessages[i].fileName+'</a></div>';	 
			    			 }		
			    			 
			    			 if(quoteMessages[i].replyStatus == 0){
			    				 var message_div =  '<div class="d d2">'+
			    				                         '<div class="imgs pull-left">'+
								                         '<img src="'+(quoteMessages[i].photo != null ?  quoteMessages[i].photo : (quoteMessages[i].factoryLogo == null || quoteMessages[i].factoryLogo == '' ? '../images/defaultLogo.png' : "/static_img/factory_logo/"+quoteMessages[i].factoryId +'\/'+ quoteMessages[i].factoryLogo+""))+'" alt="" class="pull-left img-responsive"/></div>'+
								                         '<div class="c pull-left">'+
								                             '<div class="arrs">'+
								                                 '<div class="arr1"></div>'+
								                                 '<div class="arr1 arr2"></div>'+
								                             '</div>'+
								                             '<div class="t1 clearfix">'+
								                                 '<span class="pull-left">'+(quoteMessages[i].realName != null ? quoteMessages[i].realName : quoteMessages[i].userName)+'</span>'+
								                                 '<em class="pull-right">'+(new Date(quoteMessages[i].sendTime.replace(/-/g,"/").split(".")[0])).Format("yyyy-MM-dd hh:mm:ss")+'</em>'+
								                             '</div>'+
								                             '<div class="t2">'+quoteMessages[i].messageDetails+'</div>'+file_div+	
								                        ' </div>'+
								                     '</div>'; 			    				 
			    
			    				 $('#message').append(message_div);
			    			 }
			    			 if(quoteMessages[i].replyStatus == 1){
			    				 var message_div = '<div class="d d1">'+
			    				                                   '<div class="imgs pull-right">'+
	                                                               '<img src="'+(quoteMessages[i].photo != null ?  quoteMessages[i].photo : (quoteMessages[i].factoryLogo == null || quoteMessages[i].factoryLogo == '' ? '../images/defaultLogo.png' : "/static_img/factory_logo/"+quoteMessages[i].factoryId +'\/'+ quoteMessages[i].factoryLogo+""))+'" alt="" class="pull-left"/></div>'+
							                                 '<div class="c pull-right">'+
							                                 '<div class="arrs">'+
							                                     '<div class="arr1"></div>'+
							                                     '<div class="arr1 arr2"></div>'+
							                                 '</div>'+
							                                 '<div class="t1 clearfix">'+
							                                     '<span class="pull-left">'+(quoteMessages[i].realName != null ? quoteMessages[i].realName : quoteMessages[i].userName)+'</span>'+
							                                     '<em class="pull-right">'+(new Date(quoteMessages[i].sendTime.replace(/-/g,"/").split(".")[0])).Format("yyyy-MM-dd hh:mm:ss")+'</em>'+
							                                 '</div>'+
							                                 '<div class="t2">'+quoteMessages[i].messageDetails+'</div>'+file_div+	 			
							                             '</div>'+					                                   
							                         '</div>';
			    		
			    				 $('#message').append(message_div);
			    			 }
			    			
			    		 }	
			    		 $('#message').scrollTop( $('#message').height());
			    	 }else{
			    		 $('#message').css({"height":"0"});
			    	 }
			    	 
			    	 
			    	 
			    	 
			    	 
			    	 //报价列表				    	 
			    	var supplierQuoteProduct = result.data.supplierQuoteProduct;
			    	var products = result.data.products;
			    	var qty; 
			    	var qtyList;
			    	
			    	$('#current_quote').empty(); 
			    	$('#history_quote').empty(); 
			    	for(var k=0;k<supplierQuoteProduct.length;k++){
			    					    		
			    		if(k == 0){
				    		var tables = '';
				    		var tables2 = '';	
				    		for(var n=0;n<supplierQuoteProducts[k].length;n++){			    			
				    			
					    		qty = supplierQuoteProducts[k][n].quantityList;
					    		qtyList = qty.split(",");
				    			
			                    var table = '<table class="table table-bordered pull-left table2">'+
				                    '<thead>'+
				                    '<tr>'+
				                        '<th style="color:#006dcc;">'+products[n].productName+'</th>'+
				                        '<th>订量一</th>'+(supplierQuoteProducts[k][n].quoteUnitPrice2 == 0 ? '' : '<th>订量二</th>')+(supplierQuoteProducts[k][n].quoteUnitPrice3 == 0 ? '' : '<th>订量三</th>')+
				                    '</thead>'+
				                    '<tbody>'+
				                    '<tr class="f9">'+
				                        '<td>目标报价数量</td>'+
				                        '<td>'+(qtyList.length > 0 ? qtyList[0] : '')+'</td>'+(qtyList.length > 1 ? '<td>'+qtyList[1]+'</td>' : '')+(qtyList.length > 2 ? '<td>'+qtyList[2]+'</td>' : '')+
				                    '</tr>'+
				                    '<tr >'+
				                        '<td>单价 (元)</td>'+
				                        '<td>'+(supplierQuoteProducts[k][n].quoteUnitPrice1)+'</td>'+(supplierQuoteProducts[k][n].quoteUnitPrice2 == 0 ? '' : '<td>'+supplierQuoteProducts[k][n].quoteUnitPrice2+'</td>')+(supplierQuoteProducts[k][n].quoteUnitPrice3 == 0 ? '' : '<td>'+supplierQuoteProducts[k][n].quoteUnitPrice3+'</td>')+		       
				                    '</tr>'+
				                    '<tr class="f9">'+
				                        '<td>模具费 (元)</td>'+
				                        '<td>'+(supplierQuoteProducts[k][n].quoteMoldPrice1)+'</td>'+(supplierQuoteProducts[k][n].quoteUnitPrice2 == 0 ? '' : '<td>'+supplierQuoteProducts[k][n].quoteMoldPrice2+'</td>')+(supplierQuoteProducts[k][n].quoteUnitPrice3 == 0 ? '' : '<td>'+supplierQuoteProducts[k][n].quoteMoldPrice3+'</td>')+		       
				                    '</tr>'+
				                    '<tr class="f9">'+
				                    '<td>产品备注</td>'+
				                    '<td>'+(supplierQuoteProduct[k].productRemark == null ? '' : supplierQuoteProduct[k].productRemark)+'</td>'+		       
				                    '</tr>'+
				                    '<tr class="f9">'+
				                    '<td>总计 (元)</td>'+
				                    '<td>'+Number(qtyList[0]*supplierQuoteProducts[k][n].quoteUnitPrice1 + supplierQuoteProducts[k][n].quoteMoldPrice1).toFixed(2)+'</td>'+(supplierQuoteProducts[k][n].quoteUnitPrice2 == 0 ? '' : '<td>'+Number(Number(qtyList[1]*supplierQuoteProducts[k][n].quoteUnitPrice2) + Number(supplierQuoteProducts[k][n].quoteMoldPrice2)).toFixed(2)+'</td>')+(supplierQuoteProducts[k][n].quoteUnitPrice3 == 0 ? '' : '<td>'+Number(Number(qtyList[2]*supplierQuoteProducts[k][n].quoteUnitPrice3) + Number(supplierQuoteProducts[k][n].quoteMoldPrice3)).toFixed(2)+'</td>')+		       
				                    '</tr>'+
				                    '</tbody>'+
				                '</table>';	
			                    tables +=table;
				    		}
				    		var currentQuote = '<p class="p1 clearfix"><em></em><span>日期：'+new Date(supplierQuoteProduct[k].createTime.replace(/-/g,"/").split(".")[0]).Format("yyyy-MM-dd hh:mm:ss")+' </span><em></em></p><div>'+tables+"</div>";
			    	        $('#current_quote').append(currentQuote);
			    		}else{
			    			
				    		for(var n=0;n<supplierQuoteProducts[k].length;n++){		
				    			
					    		qty = supplierQuoteProducts[k][n].quantityList;
					    		qtyList = qty.split(",");
				    			
			                    var table = '<table class="table table-bordered pull-left table2 dis">'+
				                    '<thead>'+
				                    '<tr>'+
				                        '<th style="color:#006dcc;">'+products[n].productName+'</th>'+
				                        '<th>订量一</th>'+(supplierQuoteProducts[k][n].quoteUnitPrice2 == 0 ? '' : '<th>订量二</th>')+(supplierQuoteProducts[k][n].quoteUnitPrice3 == 0 ? '' : '<th>订量三</th>')+
				                    '</thead>'+
				                    '<tbody>'+
				                    '<tr class="f9">'+
				                        '<td>目标报价数量</td>'+
				                        '<td>'+(qtyList.length > 0 ? qtyList[0] : '')+'</td>'+(qtyList.length > 1 ? '<td>'+qtyList[1]+'</td>' : '')+(qtyList.length > 2 ? '<td>'+qtyList[2]+'</td>' : '')+
				                    '</tr>'+
				                    '<tr >'+
				                        '<td>单价 (元)</td>'+
				                        '<td>'+(supplierQuoteProducts[k][n].quoteUnitPrice1)+'</td>'+(supplierQuoteProducts[k][n].quoteUnitPrice2 == 0 ? '' : '<td>'+supplierQuoteProducts[k][n].quoteUnitPrice2+'</td>')+(supplierQuoteProducts[k][n].quoteUnitPrice3 == 0 ? '' : '<td>'+supplierQuoteProducts[k][n].quoteUnitPrice3+'</td>')+		       
				                    '</tr>'+
				                    '<tr class="f9">'+
				                        '<td>模具费 (元)</td>'+
				                        '<td>'+(supplierQuoteProducts[k][n].quoteMoldPrice1)+'</td>'+(supplierQuoteProducts[k][n].quoteUnitPrice2 == 0 ? '' : '<td>'+supplierQuoteProducts[k][n].quoteMoldPrice2+'</td>')+(supplierQuoteProducts[k][n].quoteUnitPrice3 == 0 ? '' : '<td>'+supplierQuoteProducts[k][n].quoteMoldPrice3+'</td>')+		       
				                    '</tr>'+
				                    '<tr class="f9">'+
				                    '<td>产品备注</td>'+
				                    '<td>'+(supplierQuoteProduct[k].productRemark == null ? '' : supplierQuoteProduct[k].productRemark)+'</td>'+		       
				                    '</tr>'+
				                    '<tr class="f9">'+
				                    '<td>总计 (元)</td>'+
				                    '<td>'+Number(qtyList[0]*supplierQuoteProducts[k][n].quoteUnitPrice1 + supplierQuoteProducts[k][n].quoteMoldPrice1).toFixed(2)+'</td>'+(supplierQuoteProducts[k][n].quoteUnitPrice2 == 0 ? '' : '<td>'+(Number(qtyList[1]*supplierQuoteProducts[k][n].quoteUnitPrice2) + Number(supplierQuoteProducts[k][n].quoteMoldPrice2))+'</td>')+(supplierQuoteProducts[k][n].quoteUnitPrice3 == 0 ? '' : '<td>'+(Number(qtyList[2]*supplierQuoteProducts[k][n].quoteUnitPrice3) + Number(supplierQuoteProducts[k][n].quoteMoldPrice3))+'</td>')+		       
				                    '</tr>'+
				                    '</tbody>'+
				                '</table>';	
			                    tables2 +=table;
				    		}
				    		var historyQuote = '<p class="p1 clearfix"><i onclick="history(this)"></i><em></em><span>日期：'+new Date(supplierQuoteProduct[k].createTime.replace(/-/g,"/").split(".")[0]).Format("yyyy-MM-dd hh:mm:ss")+' </span><em></em></p><div>'+tables2+"</div>";
			    	        $('#history_quote').append(historyQuote);
			    		}
             	
			    	} 	
			    	
			    	
			         	//当只有一个报价时，历史报价控制不显示
				    	if(supplierQuoteProduct.length == 1){
				    		$('#history_quote').parents('.panel4').hide();
				    	}
			    	
			    	
			    }else if(result.state == 2){
		    		 //如果还未登录，跳转登录页
		    		 window.location = "/zh/login.html";
		        }   			      			 
	      })
			 
  
})


     //控制历史报价显示隐藏
  function history(obj){
	  var $this = $(obj).parent().next('div').find('table');
      if($this.hasClass('dis')){
      	$this.removeClass('dis');
              $this.show();
          $(obj).css({
              'background':'url(../images/red.png) no-repeat'
          })
      }else{
      	$this.addClass('dis');
          $this.hide();
          $(obj).css({
              'background':'url(../images/green.png) no-repeat'
          })
      }
  }


//查看历史报价
function show_history(){
	   $('.history').find('.p1').each(function(i){
		   if(i>0){
			   $(this).next().addClass('dis');
			   $(this).next().hide();
			   $(this).find('i').css({
	                'background':'url(../images/green.png) no-repeat'
	            })
		   }else{
			   $(this).next().removeClass('dis');
			   $(this).find('i').css({
	                'background':'url(../images/red.png) no-repeat'
	            })
		   }
	   })
}






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





//发送询盘消息
function send_message(obj){
	
	 var message = $('#message_details').val();
	 var filePath = $('#filePath').val();
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
	 
	 $.post("/factoryInquiry/addQuoteMessage.do",	
			  {
		     "orderId":$('#orderId').val(),
		     "message":message,
		     "factoryId" : factoryId,
		     "filePath":filePath,
		     "fileName" : $('#fileName').val()  
			 },
			 function(result){
				 
	 $(obj).css({'background-color':'#006dcc'}).removeAttr('disabled');   	
		       if(result.state == 0){
		    	  var quoteMessages = result.data;

		    		 $('#quote_message').find('.d').remove();
			    	 //询盘消息
			    	 if(quoteMessages.length != 0){		
			    		 
			    		 $('#message').css({"height":"325px"});
			    		 for(var i=0;i<quoteMessages.length;i++){	
			    			 var file_div = '';
			    			 if(quoteMessages[i].fileName != null && quoteMessages[i].fileName != '' && quoteMessages[i].fileName != undefined && quoteMessages[i].filePath != null && quoteMessages[i].filePath != '' && quoteMessages[i].filePath != undefined){
//			    				 var fileName = quoteMessages[i].filePath.substr(quoteMessages[i].filePath.lastIndexOf('\/')+1); 
//			    				 var gen = fileName.substr(fileName.lastIndexOf('.')+1);
//			    				 var split = fileName.split("&");
//			    				 fileName = split[0] + "." + gen;
			    				 file_div = '<div class="file-download">附件:<a style="text-decoration: underline;" onclick="download_file(\''+quoteMessages[i].id+'\')">'+quoteMessages[i].fileName+'</a></div>';
			    				 
			    			 }		
			    			 
			    			 
			    			 if(quoteMessages[i].replyStatus == 0){
			    				 var message_div =  '<div class="d d2">'+
								                         '<div class="imgs pull-left">'+
								                         '<img src="'+(quoteMessages[i].photo != null ?  quoteMessages[i].photo : (quoteMessages[i].factoryLogo == null || quoteMessages[i].factoryLogo == '' ? '../images/defaultLogo.png' : "/static_img/factory_logo/"+quoteMessages[i].factoryId +'\/'+ quoteMessages[i].factoryLogo+""))+'" alt="" class="pull-left img-responsive"/></div>'+
								                         '<div class="c pull-left">'+
								                             '<div class="arrs">'+
								                                 '<div class="arr1"></div>'+
								                                 '<div class="arr1 arr2"></div>'+
								                             '</div>'+
								                             '<div class="t1 clearfix">'+
								                                 '<span class="pull-left">'+(quoteMessages[i].realName != null ? quoteMessages[i].realName : quoteMessages[i].userName)+'</span>'+
								                                 '<em class="pull-right">'+(new Date(quoteMessages[i].sendTime.replace(/-/g,"/").split(".")[0])).Format("yyyy-MM-dd hh:mm:ss")+'</em>'+
								                             '</div>'+
								                             '<div class="t2">'+quoteMessages[i].messageDetails+'</div>'+file_div+	
								                        ' </div>'+
								                     '</div>'; 			    				 

			    				 $('#message').append(message_div);
			    			 }
			    			 if(quoteMessages[i].replyStatus == 1){
			    				 var message_div = '<div class="d d1">'+
							                                 '<div class="c pull-left">'+
							                                 '<div class="arrs">'+
							                                     '<div class="arr1"></div>'+
							                                     '<div class="arr1 arr2"></div>'+
							                                 '</div>'+
							                                 '<div class="t1 clearfix">'+
							                                     '<span class="pull-left">'+(quoteMessages[i].realName != null ? quoteMessages[i].realName : quoteMessages[i].userName)+'</span>'+
							                                     '<em class="pull-right">'+(new Date(quoteMessages[i].sendTime.replace(/-/g,"/").split(".")[0])).Format("yyyy-MM-dd hh:mm:ss")+'</em>'+
							                                 '</div>'+
							                                 '<div class="t2">'+quoteMessages[i].messageDetails+'</div>'+file_div+	 			
							                             '</div><div class="imgs pull-right">'+
							                             '<img src="'+(quoteMessages[i].photo != null ?  quoteMessages[i].photo : (quoteMessages[i].factoryLogo == null || quoteMessages[i].factoryLogo == '' ? '../images/defaultLogo.png' : "/static_img/factory_logo/"+quoteMessages[i].factoryId +'\/'+ quoteMessages[i].factoryLogo+""))+'" alt="" class="pull-left"/></div>'+					                                   
							                         '</div>';
											
								 $('#message').append(message_div);
			    			 }
			    			
			    		 }	
			    		 
			    		 $('#message').scrollTop( $('#message').height());
			    		 
			    		 
			    		 
			    		 //回复消息时发送微信提醒
				    	 $.post("/wimpl/message_note.do",	
						    		 {
				    		         "orderId":$('#orderId').val(),
				    			     "messageDetail":message,
				    			     "factoryId" : factoryId
						    	     },
								    function(result){
						    	     	 
						       })
			    		 
			    		 
			    	 }else{
			    		 $('#message').css({"height":"0"});
			    	 }
		    	  
			    	    $('#filePath').val('');
				    	$('#fileName').val('');
				    	$('#message_details').val('');
		       }else if(result.state == 2){
		    		 //如果还未登录，跳转登录页
		    		 window.location = "/zh/login.html";
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
	          		     content : '  上传失败 '
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
//下载附件
//function download_attachment(path){
//	window.location = "/download/quote-file-download.do?id="+id;
//}



//时间格式化
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




//功能:实现获取日期
//
function getDate(date,days){ 
//	   var array =  date.split("-");
//	   var dt = new Date(array[0], array[1], array[2]);	   
	   if(days>=1){now=new Date(date+86400000*days);}   
	   var yyyy=now.getFullYear(),mm=(now.getMonth()+1).toString(),dd=now.getDate().toString();   
	   if(mm.length==1){mm='0'+mm;} if(dd.length==1){dd='0'+dd;}
	   return (yyyy+'-'+mm+'-'+dd);        
	  }

