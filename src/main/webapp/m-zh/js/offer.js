var orderId;
var str = window.location.search;
if(str != null && str != ''){
	str = str.substr(1);
	orderId = str.split("&")[0].split("=")[1];
}


//总报价工厂数
var total;
//是否属于更新报价
var isUpdateQuote = false;

$(function(){
		
	
	$.post("/inquiry/queryInquiryDetails.do",	
			{"orderId" : orderId},
			 function(result){
			      if(result.state == 0){
			    	 var quoteInfo = result.data.quoteInfo; 
			    	 var products = result.data.products; 
//			    	 var isVip = result.data.isVip;
			    	 
				    //历史报价记录
				    var supplierQuoteInfo = result.data.supplierQuoteInfo;
				    var supplierQuoteProducts = result.data.supplierQuoteProducts;
			    	var supplierQuoteProduct = result.data.supplierQuoteProduct;
			    	 			    	 
	    
			        $('#quoteId').text(quoteInfo.orderId); 	 
			   
			   
				 //判断是否是会员
//		    	 if(isVip == 101){			    		 
//
//		    	 }else{
//		    		var hour = DateDiff(quoteInfo.publishDate,new Date());
//		    	   	if(hour > 48){
//		    		   $('#limit_quote').find('span').text('');
//			           $('#limit_quote').find('em').text('');
//			    	   $('.to-quote').css({"cursor":"pointer"}).removeAttr("disabled");				    	   	  
//		    	   	}else{
//		    	   	  hour = Number(48 - hour).toFixed(0);
//		    	   	  if(hour == 0){
//		    	   		  hour = 1;
//		    	   	  }
//		    	   	  $('#add_quote').css({"cursor":"not-allowed","color":"#ccc"}).removeAttr('onclick');
//		    	      $('.supplier_offer').find('button:last').css({"cursor":"not-allowed","color":"#ccc"}).removeAttr('onclick');
//		    	   	}
//		    	 }
		    	 
			   		   
			  
			   
//			   $('#quote_select').empty(); 	 
			   if(products.length > 0){	
			    $('#qty').text(products.length); 
				   
			    for(var i=0;i<products.length;i++){				    	
			    	var qty = products[i].quantityList;
	    			var list = qty.split(",");
                                                    
                    var product =    ' <div class="row clearfix m_l10 m_r10">'+
										'<h4 class="pull-left">零件信息(<span>'+(i+1)+'</span>/<em>'+products.length+'</em>)</h4>'+
										'<div class="lj1 pull-left">'+
											'<div class="imgs" onclick="showBig_pic(this)"><img src="'+(products[i].drawingPathCompress == null ? '../images/pic2.png' : products[i].drawingPathCompress)+'" alt=""></div>'+
										'</div>'+
										'<table class="info pull-right">'+
											'<tr><td>零件名称</td><td><span>'+products[i].productName+'</span></td></tr>'+
											'<tr><td>年用量预估</td><td><span>'+(products[i].annualQuantity == null ? '' : products[i].annualQuantity)+'</span></td></tr>'+						
											'<tr><td>材质</td><td><span>'+(products[i].materials == null ? '' : products[i].materials)+'</span></td></tr>'+
											'<tr><td>单位重量</td><td><span>'+(products[i].weight == null ? '' : products[i].weight)+'</span></td></tr>'+
//											'<tr><td>单位</td><td>'+(products[i].weight == null ? '' : products[i].weight)+'</td></tr>'+
											'<tr><td>注释</td><td><span>'+(products[i].productRemark == null ? '' : products[i].productRemark)+'</span></td></tr>'+
										'</table>'+
										'<table class="table2 pull-left">'+
											'<thead>'+
												'<tr>'+
													'<th></th>'+
													'<th>定量一</th>'+
													(list.length > 1 ? '<th>定量二</th>' : '')+
													(list.length > 2 ? '<th>定量三</th>' : '')+
												'</tr>'+
											'</thead>'+
											'<tbody>'+
										    '<input type="hidden" value="'+products[i].id+'">'+
												'<tr>'+
													'<td>报价数量</td>'+
													'<td>'+(list.length > 0 ? list[0] : '')+'</td>'+
													(list.length > 1 ? '<td>'+(list.length > 1 ? list[1] : '')+'</td>' : '')+
													(list.length > 2 ? '<td>'+(list.length > 2 ? list[2] : '')+'</td>' : '')+
												'</tr>'+
												'<tr>'+
													'<td>单价(元)</td>'+
													'<td><input type="text" value="'+((supplierQuoteProducts != '' && supplierQuoteProducts != null) ? (supplierQuoteProducts[0][i].quoteUnitPrice1) : '')+'" oninput="calculator(this)" onkeyup="keyUp(this)" onKeyPress="keyPress(this)" onblur="onBlur(this)"></td>'+
													(list.length > 1 ? '<td><input type="text" value="'+((supplierQuoteProducts != '' && supplierQuoteProducts != null) ? (supplierQuoteProducts[0][i].quoteUnitPrice2) : '')+'" oninput="calculator(this)" onkeyup="keyUp(this)" onKeyPress="keyPress(this)" onblur="onBlur(this)"></td>' : '') +
													(list.length > 2 ? '<td><input type="text" value="'+((supplierQuoteProducts != '' && supplierQuoteProducts != null) ? (supplierQuoteProducts[0][i].quoteUnitPrice3) : '')+'" oninput="calculator(this)" onkeyup="keyUp(this)" onKeyPress="keyPress(this)" onblur="onBlur(this)"></td>' : '') +
												'</tr>'+
												'<tr>'+
													'<td>模具费(元)</td>'+
													'<td><input type="text" value="'+(supplierQuoteProducts != '' && supplierQuoteProducts != null ? (supplierQuoteProducts[0][i].quoteMoldPrice1) : '')+'" oninput="mold_calculator(this)" onkeyup="keyUp(this)" onKeyPress="keyPress(this)" onblur="onBlur(this)"></td>'+
													(list.length > 1 ? '<td><input type="text" value="'+(supplierQuoteProducts != '' && supplierQuoteProducts != null ? (supplierQuoteProducts[0][i].quoteMoldPrice2) : '')+'" oninput="mold_calculator(this)" onkeyup="keyUp(this)" onKeyPress="keyPress(this)" onblur="onBlur(this)"></td>' : '') +
													(list.length > 2 ? '<td><input type="text" value="'+(supplierQuoteProducts != '' && supplierQuoteProducts != null ? (supplierQuoteProducts[0][i].quoteMoldPrice3) : '')+'" oninput="mold_calculator(this)" onkeyup="keyUp(this)" onKeyPress="keyPress(this)" onblur="onBlur(this)"></td>' : '') +
												'</tr>'+
												'<tr>'+
													'<td>总计(元)</td>'+
													'<td><span>'+(supplierQuoteProducts != '' && supplierQuoteProducts != null ? Number(Number(supplierQuoteProducts[0][i].quoteUnitPrice1 * list[0]) + Number(supplierQuoteProducts[0][i].quoteMoldPrice1)).toFixed(0) : '')+'</span></td>'+
													(list.length > 1 ? '<td><span>'+(supplierQuoteProducts != '' && supplierQuoteProducts != null ? Number(Number(supplierQuoteProducts[0][i].quoteUnitPrice2 * list[0]) + Number(supplierQuoteProducts[0][i].quoteMoldPrice2)).toFixed(0) : '')+'</span></td>' : '')+
													(list.length > 2 ? '<td><span>'+(supplierQuoteProducts != '' && supplierQuoteProducts != null ? Number(Number(supplierQuoteProducts[0][i].quoteUnitPrice3 * list[0]) + Number(supplierQuoteProducts[0][i].quoteMoldPrice3)).toFixed(0) : '')+'</span></td>' : '')+
												'</tr>'+
											'</tbody>'+
										'</table>'+
									'</div>';
                    
                    
                    
                    
                    $('#quote_select').before(product); 
			      }     
			                              
                       
			   }    
			   
		    	
		    	//历史报价
		    	if(supplierQuoteInfo != null && supplierQuoteInfo != '' && supplierQuoteInfo != undefined){
		    		
		    		//更新是否属于更新报价状态
		    		isUpdateQuote = true;
		    		
		    		$('#title_ul').find('li:first').find('a').attr('href','/fastermake/zh/offer_detail.html?orderId='+orderId+'');
		    		
		    		var validityDays = supplierQuoteInfo.validityDays;
		    		var paymentTerm = supplierQuoteInfo.paymentTerm;
		    		var paymentRemark = supplierQuoteInfo.paymentRemark;
		    		var quoteRemark = supplierQuoteInfo.quoteRemark;
		    		var quoteReasons = supplierQuoteInfo.quoteReasons;

		    		 $("#validityDays").find("option[value="+validityDays+"]").prop("selected","selected");
		             $('#payment_select').val(paymentTerm);
		             
		               $('#select_sel option').each(function(){
			    			if($(this).val() == paymentTerm ){
			    				$(this).attr("selected","selected");
			    			}else{
			    				$("#payment_select").removeAttr("readonly");
			    				$("#payment_select").show();
			    				$("#payment_select").focus();
			    			} 
			    		 })
		    		 $('#quote_remark').val((quoteRemark == null ? '' : quoteRemark));
		    		 $('#quoteReasons').val((quoteReasons == null ? '' : quoteReasons));
		    	}		    	
		    	
		    	 
		    	$('.history').empty(); 
		    	for(var k=0;k<supplierQuoteProduct.length;k++){
		    		
		    		
		    		var tables = '';
		    		for(var n=0;n<supplierQuoteProducts[k].length;n++){
		    			
		    		   var qty = supplierQuoteProducts[k][n].quantityList;
			    	   var qtyList = qty.split(",");
		    			
	                   var table = '<table class="table table-bordered pull-left table2">'+
		                    '<thead>'+
		                    '<tr>'+
		                        '<th style="color:#006dcc;width: 350px;border-bottom:0 none;">'+products[n].productName+'</th>'+
		                        '<th style="border-bottom:0 none;">订量一</th>'+(supplierQuoteProducts[k][n].quoteUnitPrice2 == 0 ? '' : '<th style="border-bottom:0 none;">订量二</th>')+(supplierQuoteProducts[k][n].quoteUnitPrice3 == 0 ? '' : '<th style="border-bottom:0 none;">订量三</th>')+
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
		                        '<td>模具费</td>'+
		                        '<td>'+(supplierQuoteProducts[k][n].quoteMoldPrice1)+'</td>'+(supplierQuoteProducts[k][n].quoteUnitPrice2 == 0 ? '' : '<td>'+supplierQuoteProducts[k][n].quoteMoldPrice2+'</td>')+(supplierQuoteProducts[k][n].quoteUnitPrice3 == 0 ? '' : '<td>'+supplierQuoteProducts[k][n].quoteMoldPrice3+'</td>')+		       
		                    '</tr>'+
		                    '<tr class="f9">'+
		                    '<td>总计 (元)</td>'+
		                    '<td>'+(qtyList[0]*supplierQuoteProducts[k][n].quoteUnitPrice1 + supplierQuoteProducts[k][n].quoteMoldPrice1)+'</td>'+(supplierQuoteProducts[k][n].quoteUnitPrice2 == 0 ? '' : '<td>'+(Number(qtyList[1]*supplierQuoteProducts[k][n].quoteUnitPrice2) + Number(supplierQuoteProducts[k][n].quoteMoldPrice2))+'</td>')+(supplierQuoteProducts[k][n].quoteUnitPrice3 == 0 ? '' : '<td>'+(Number(qtyList[2]*supplierQuoteProducts[k][n].quoteUnitPrice3) + Number(supplierQuoteProducts[k][n].quoteMoldPrice3))+'</td>')+		       
		                    '</tr>'+
		                    '</tbody>'+
		                '</table>';	
	                    tables +=table;
		    		}
		    		
			    	var historyQuote = '<p class="p1 clearfix"><i onclick="history(this)"></i><em></em><span>日期：'+new Date(supplierQuoteProduct[k].createTime.replace(/-/g,"/").split(".")[0]).Format("yyyy-MM-dd hh:mm:ss")+' </span><em></em></p><div>'+tables+"</div>";
			    	
					$('.history').append(historyQuote);                	
		    	} 				
                       
			}
	          
	})
	
	
	
	
	    //支付条款改变   
//		$('#payment_select').change(function(){
//			var payment = $(this).find("option:selected").text(); 
//			if(payment == "其他"){
//				$('.textarea1').show();
//			}else{
//				$('.textarea1').hide();
//			}
//			
//		})
		
			
		
		  $('.inquiry_detail .section nav li a').hover(function(){
        $(this).addClass('active')
        .parent().siblings('li').children('a').removeClass('active');
    })


    /* 更多消息下拉 */
    $('.header_top_in .xxs').click(function(){
        $(this).children('.xx').css({
            "border-color":"#dfdfdf",
            "border-bottom-color":"transparent"
        })
        $(this).children('.xxlist').toggle();
        $(this).children('.xxlist').show();
    })

    $('.header_top_in .xxs').hover(function(){
        $(this).children('.xx').css({
            'border-color':'#dfdfdf',
            'border-bottom-color':'transparent'
        })
    },function(){
        $(this).children('.xx').css({
            'border-color':'transparent',
        })
    })

    /*头部向下箭头点击变成向上箭头*/
    $('.header_top_in .iconf').click(function(){
        $(this).css({
            'background':'url(../images/xs.png)'
        });




        if($('.xxlist').hasClass('dis')){
            $(this).css({
                'background':'url(../images/xx.png)'
            });
        }else{
            $(this).css({
                'background':'url(../images/xs.png)'
            });
        }
    })
	
    
  
    
   //图片点击放大 
//    $('.offer .lj1 .imgs').on('click',function(){
//	var str = $(this).find('img').attr('src');
//	$('.offer .big_pic img').attr('src',str);
//    })



//	$('.offer .big_pic span').on('click',function(){
//		$('.big_pic').hide();
//	})
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
//$("#big_pic").click(function(event) {
//    event.stopPropagation(); 
//});


//图片点击放大 
//function bigImg(obj){
//	var str = $(obj).find('img').attr('src');
//	$('.offer .big_pic img').attr('src',str);
//	$('.offer .big_pic').show();
//}
function closeBigImg(){
	$('.big_pic').hide();
}



     //控制历史报价显示隐藏
  function history(obj){
	  var $this = $(obj).parent().next('div');
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




	    //提交报价
		function submit_quote(status,obj){
			
		  //如果不是更新报价，则进行工厂人数验证    
	      if(!isUpdateQuote){	
				//查询当前报价数量
				verifyTotal();
				if(total == 5 || total > 5){
				    easyDialog.open({
						  container : {
							header : '  提示信息',
						    content : '当前报价人数已满'
						  },
			    		  overlay : false,
			    		  autoClose : 1000
						});
					return false;
				}
	      }	
		    var flag = true;
		    var productList = '';
		    var productIds = '';
		    $('#product_list').find('.table2').find('tbody').each(function(){
		    	 var products = '';
		    	 This = $(this);
		    	 This.find('tr').eq(1).find('input').each(function(i){
				    	var price = $(this).val();
				    	var moldPrice = This.find('tr').eq(2).find('input').eq(i).val();
				    	var totalAmount = This.find('tr').eq(3).find('span').eq(i).text();
				    	if(price == null || price == '' || price == undefined){
				    		 easyDialog.open({
								  container : {
									header : '  提示信息',
								    content : '价格不能为空'
								  },
					    		  overlay : false,
					    		  autoClose : 1000
								});
				    		flag = false;
				    		return false;
				    	}
				    	products += price + "&" + moldPrice + "&" + totalAmount + "&"; 
				  })
				  products = products.substring(0, products.length-1);
				  productList += products + ",";
				  var productId = This.find('input:first').val();
				  productIds +=productId + ',';				 
		    })
		    productIds = productIds.substring(0, productIds.length-1);
		    var validityDays = $('#validityDays').val();
		    if(validityDays == null || validityDays == ''){
		    	 easyDialog.open({
					  container : {
						header : '  提示信息',
					    content : '请选择报价有效期'
					  },
		    		  overlay : false,
		    		  autoClose : 1000
					});
		    	 return false;
		    }
		    
		    if(!flag){
		    	return;
		    }
		    
		    
		    //报价优势
//		    if($('#quoteReasons').val() == '' || $('#quoteReasons').val() == null || $('#quoteReasons').val() == undefined){
//		    	easyDialog.open({
//					  container : {
//						header : '  提示信息',
//					    content : '请输入工厂优势'
//					  },
//		    		  overlay : false,
//		    		  autoClose : 1000
//					});
//		    	 return false;
//		    }
		    
			$(obj).css({'background-color':'#ddd'}).attr("disabled",true);
		    $.post("/inquiry/addSupplierQuote.do",	
		    		{
		    	     "orderId" : orderId,
		    	     "paymentTerm": $("#payment_select").val(),
		    	     "quoteRemark": $('#quote_remark').val(),
		    	     "attachmentPath": $('#filePath').val(),
		    	     "quoteReasons" : $('#quoteReasons').val(),
		    	     "paymentRemark" : $('#paymentRemark').val(),
		    	     "quoteStatus" : status,
		    	     "productList" : productList,
		    	     "productIds" : productIds,
		    	     "validityDays" : validityDays,
		    	     "fileName" : $('#fileName').val() 
		    	     },
				   function(result){		    	    	 
		    	    $(obj).css({'background-color':'#006dcc'}).removeAttr('disabled');   	 
				     if(result.state == 0){	
				    					    	 
				    	var quoteInfoId = result.data.quoteInfoId;
	
				    	 //同步到内部报价
				    	 $.post("/sendPort/sendQuoteInfo.do",	
						    		 {
				    		          quoteInfoId:quoteInfoId
						    	     },
								    function(result){
						    	     	 
						       })
						       
						       
						  //发送微信通知
				    	 $.post("/wimpl/quote_note.do",	
						    		 {
				    		           "orderId" : orderId				    		          
						    	     },
								    function(result){
						    	     	 
						       })      
						       
						       
						 easyDialog.open({
							  container : {
							    header : '  提示信息',
							    content : '报价成功'
							  },
				    		  overlay : false,
				    		  autoClose : 1000
							});
				    	 setTimeout(function(){show()},1000);
						       
				    					    
				     }else if(result.state == 2){
			    		 //如果还未登录，跳转登录页
			    		 window.location = "/m-zh/login.html";
			         }else if(result.state == 3){
				    	 easyDialog.open({
							  container : {
							    header : '  提示信息',
							    content : 'vip已过期,请充值'
							  },
				    		  overlay : false,
				    		  autoClose : 1000
							});  
			    	 }else{
					    	 easyDialog.open({
								  container : {
								    header : '  提示信息',
								    content : '保存失败'
								  },
					    		  overlay : false,
					    		  autoClose : 1000
								}); 
				     }				    	 
		    })		      
	    }

		
		
//验证报价数量		
function verifyTotal(){
	$.ajax({  
         type : "post",  
         url : "/inquiry/queryQuoteAmount.do", 
         data: {"orderId" : orderId},
         async : false,  
         success : function(result){  
            total = result.data;
         }  
    }); 
}		
		
		
		
		
		
function show(){
	 window.location =  "/m-zh/detail.html?orderId="+orderId;
}

//文件上传
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
		url: "/upload/attachmentAndChangeName.do",
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
         		     content : ' 上传失败 '
     		   },
				  overlay : false,
				  autoClose : 1000
     		 });   
     		$('#show_upload_dialog').hide();
		}       	     	
 	}); 	 		    
}


//计算总价
function calculator(obj){	 
	 var index = $(obj).parent('td').index();
	 var unitPrice = $(obj).val();
	 var qty = $(obj).parents('tbody').find('tr:first').find('td').eq(index).text();
	 var moldPrice = $(obj).parents('tr').next().find('td').eq(index).find('input').val();
	 if(moldPrice == null || moldPrice == '' || moldPrice == undefined){
		moldPrice = 0;
	 }
	 var totalPrice = Number(unitPrice*qty)+ Number(moldPrice);
	 totalPrice = Number(totalPrice).toFixed(0);
	 if(isNaN(totalPrice)){ 
		return false;
     }
	 $(obj).parents('tbody').find('tr:last').find('td').eq(index).find('span').text(totalPrice);
}
//计算总价
function mold_calculator(obj){	 
	var index = $(obj).parent('td').index();
	var moldPrice = $(obj).val();
	var qty = $(obj).parents('tbody').find('tr:first').find('td').eq(index).text();
	var unitPrice = $(obj).parents('tr').prev().find('td').eq(index).find('input').val();
	if(unitPrice == null || unitPrice == '' || unitPrice == undefined){
		unitPrice = 0;
	}
	var totalPrice = Number(unitPrice*qty)+ Number(moldPrice);
	totalPrice = Number(totalPrice).toFixed(0);
	 if(isNaN(totalPrice)){ 
			return false;
	 }
	$(obj).parents('tbody').find('tr:last').find('td').eq(index).find('span').text(totalPrice);
}


function show_detail(){
	window.location = "/m-zh/detail.html?orderId="+orderId;
}

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



function keyPress(ob) {
	 if (!ob.value.match(/^[\+\-]?\d*?\.?\d*?$/)) ob.value = ob.t_value; else ob.t_value = ob.value; if (ob.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/)) ob.o_value = ob.value;
	}
function keyUp(ob) {
	 if (!ob.value.match(/^[\+\-]?\d*?\.?\d*?$/)) ob.value = ob.t_value; else ob.t_value = ob.value; if (ob.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/)) ob.o_value = ob.value;
	        }
function onBlur(ob) {
	if(!ob.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))ob.value=ob.o_value;else{if(ob.value.match(/^\.\d+$/))ob.value=0+ob.value;if(ob.value.match(/^\.$/))ob.value=0;ob.o_value=ob.value};
	}




