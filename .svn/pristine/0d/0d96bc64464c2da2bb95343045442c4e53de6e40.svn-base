var orderId;
var factoryId;
$(function(){
	var str = window.location.search;
	if(str != null && str != ''){
		str = str.substr(1);
		orderId = str.split("&")[1].split("=")[1];
		factoryId = str.split("&")[0].split("=")[1];
	}	
	$.post("/factoryInquiry/queryInquiryDetail.do",	
			{"orderId" : orderId,
		     "factoryId" : factoryId 
			},
			 function(result){
			      if(result.state == 0){
			    	 var quoteInfo = result.data.quoteInfo; 
			    	 var products = result.data.products; 
			    	 var factoryName = result.data.factoryName;
			    	 var supplierQuoteInfo = result.data.supplierQuoteInfo;
			    	 var supplierQuoteProducts = result.data.supplierQuoteProducts;
			    	 var quoteBigProducts = result.data.quoteBigProducts;
			    	 var quoteBigProductsTab = result.data.quoteBigProductsTab;
			    	 
			    	 
			    	 //询盘条件
			    	 $('#quoteId').text(quoteInfo.orderId);
			    	 if(quoteInfo.quoteTitle == null || quoteInfo.quoteTitle == ''){
			    		 $('#quote_title').hide();
			    		 $('#quote_title').prev().hide();
			    	 }else{
				    	 $('#quote_title').text(quoteInfo.quoteTitle);
			    	 }	    	
			    	 
			    	 //选择的工厂
			    	 $('.posirela:first').append('<span></span>选用工厂：'+factoryName+'');
			    	 
			    	 
			    	 //产品列表
			    	 $('#product_tbody').empty();			    	 
			    	 
			    	 for(var j=0;j<products.length;j++){			    			   								   							   
		    		         
			    		     //供应商给出的价格数据
			    		     var price = '';
			    		     var qty = '';
			    		     var moldPrice = '';
			    		     var qtyList = '';
			    		     var priceList = '';
			    		     var moldList = '';
			    		     if(supplierQuoteProducts != null){
			    		    	 for(var n=0;n<supplierQuoteProducts.length;n++){
			    		    		 if(supplierQuoteProducts[n].quoteProductId == products[j].id){
			    		    			 var qty = products[j].quantityList;	    			   
						    			 var list = qty.split(",");	
						    			 if(list.length > 2){
						    				 price = supplierQuoteProducts[n].quoteUnitPrice3;
						    				 moldPrice = supplierQuoteProducts[n].quoteMoldPrice3;
						    				 qty = list[2];
						    				 
						    				 qtyList =  '<div class="print">'+
			                                         	'<div><em>订量一：</em><span>'+list[0]+'</span></div>'+
			                                         	'<div><em>订量二：</em><span>'+list[1]+'</span></div>'+
			                                         	'<div><em>订量三：</em><span>'+list[2]+'</span></div>'+
			                                            '</div>';
						    				 
						    				 priceList = '<div class="print">'+
				                                         	'<div><em>单价一：</em><span>'+supplierQuoteProducts[n].quoteUnitPrice1+'</span></div>'+
				                                         	'<div><em>单价二：</em><span>'+supplierQuoteProducts[n].quoteUnitPrice2+'</span></div>'+
				                                         	'<div><em>单价三：</em><span>'+supplierQuoteProducts[n].quoteUnitPrice3+'</span></div>'+
				                                         '</div>';
						    				 
						    				 moldList =  '<div class="print">'+
										    				 '<div><em>模具费一：</em><span>'+supplierQuoteProducts[n].quoteMoldPrice1+'</span></div>'+
										    				 '<div><em>模具费二：</em><span>'+supplierQuoteProducts[n].quoteMoldPrice2+'</span></div>'+
										    				 '<div><em>模具费三：</em><span>'+supplierQuoteProducts[n].quoteMoldPrice3+'</span></div>'+
										    			  '</div>';
						    				 
						    			 }else if(list.length == 2){
						    				 price = supplierQuoteProducts[n].quoteUnitPrice2;
						    				 moldPrice = supplierQuoteProducts[n].quoteMoldPrice2;
						    				 qty = list[1];
						    				 
						    				 qtyList =  '<div class="print">'+
			                                         	'<div><em>订量一：</em><span>'+list[0]+'</span></div>'+
			                                         	'<div><em>订量二：</em><span>'+list[1]+'</span></div>'+
			                                            '</div>';
						    				 
						    				 priceList = '<div class="print">'+
				                                         	'<div><em>单价一：</em><span>'+supplierQuoteProducts[n].quoteUnitPrice1+'</span></div>'+
				                                         	'<div><em>单价二：</em><span>'+supplierQuoteProducts[n].quoteUnitPrice2+'</span></div>'+
				                                         '</div>';
			    				 
						    				 moldList =  '<div class="print">'+
										    				 '<div><em>模具费一：</em><span>'+supplierQuoteProducts[n].quoteMoldPrice1+'</span></div>'+
										    				 '<div><em>模具费二：</em><span>'+supplierQuoteProducts[n].quoteMoldPrice2+'</span></div>'+
										    			  '</div>';
						    			 }else{
						    				 price = supplierQuoteProducts[n].quoteUnitPrice1;
						    				 moldPrice = supplierQuoteProducts[n].quoteMoldPrice1;
						    				 qty = list[0];
						    				 
						    				 qtyList =  '<div class="print">'+
			                                         	'<div><em>订量：</em><span>'+list[0]+'</span></div>'+
			                                            '</div>';
						    				 
						    				 priceList = '<div class="print">'+
				                                         	'<div><em>单价：</em><span>'+supplierQuoteProducts[n].quoteUnitPrice1+'</span></div>'+				                                       	
				                                         '</div>';
			    				 
						    				 moldList =  '<div class="print">'+
										    				 '<div><em>模具费：</em><span>'+supplierQuoteProducts[n].quoteMoldPrice1+'</span></div>'+
										    			  '</div>';
						    			 }			    		    			
			    		    		 }
			    		    	 }
			    		     }
			    		 
							 var tr = '<tr>'+
					                    '<td colspan="5">'+
				                             '<ul class="clearfix">'+
				                                 '<li class="w210">'+
				                                     '<div class="imgs">'+
				                                     '<img src="'+((products[j].drawingPathCompress == null || products[j].drawingPathCompress == '') ? '../images/pic2.png' : products[j].drawingPathCompress)+'" alt=""/>'+
				                                     '</div>'+
				                                 '</li>'+
				                                 '<li><span class="w210 w2102">'+products[j].productName+'</span></li>'+
				                                 '<li><span class="w270 w2102">'+(products[j].process == null ? '-' : products[j].process)+'</span></li>'+
				                                 '<li><span class="w245 w2102">'+(products[j].materials == null ? '-' : products[j].materials)+'</span></li>'+
				                                 '<li><span class="w190">'+(products[j].weight == null ? '-' : products[j].weight)+'</span></li>'+
				                             '</ul>'+
				                             '<div class="inputs">'+
				                                 '<div class="form-inline">'+
				                                     '<div class="form-group">'+
				                                         '<input type="hidden" value="'+products[j].id+'">'+
				                                         '<label for="" >数量：</label>'+
				                                         '<input type="text" class="form-control" onKeyPress="return inputNum(event);" value="'+(quoteBigProductsTab.length > 0 ? quoteBigProductsTab[j].quantity : qty)+'"/>'+qtyList+
				                                    ' </div>'+
				                                     '<div class="form-group price">'+
				                                         '<label for="">单价：</label>'+
				                                         '<input type="text" class="form-control" onkeyup="keyUp(this)" onKeyPress="keyPress(this)" onblur="onBlur(this)" value="'+(quoteBigProductsTab.length > 0 ? quoteBigProductsTab[j].unitPrice : price)+'"/>'+priceList+
				                                     '</div>'+
				                                     '<div class="form-group">'+
				                                         '<label for="">模具费：</label>'+
				                                         '<input type="text" class="form-control" onkeyup="keyUp(this)" onKeyPress="keyPress(this)" onblur="onBlur(this)" value="'+(quoteBigProductsTab.length > 0 ? quoteBigProductsTab[j].moldPrice : moldPrice)+'"/>'+moldList+
				                                     '</div>'+
				                                 '</div>'+
				                             '</div>'+
				                             '<div class="form-group">'+
	                                            '<label for="" class="col-sm-1">注释：</label>'+
	                                            '<div class="col-sm-11">'+
	                                                '<textarea class="form-control ">'+(quoteBigProductsTab.length > 0 ? quoteBigProductsTab[j].productRemark : '')+'</textarea>'+
	                                            '</div>'+
	                                         '</div>'+
				                         '</td>'+
				                     '</tr>';
							 
							  $('#product_tbody').append(tr);   
							   
                       }
				   
			    	 
			    	    //大货生成情况历史赋值
			    	    //要求确认
			        	 var requirement;
			        	 var paymentTerm;
				       if(quoteBigProducts != null && quoteBigProducts != ''){
					    	requirement = quoteBigProducts.purchaserRequirement;
					    	paymentTerm = quoteBigProducts.paymentTerm;
				        }

			    	   if(requirement != null && requirement != '' && requirement != undefined){
			    		   var strs = requirement.split(','); 
			    		   for(var k=0;k<strs.length;k++){
			    			   $('#requirement').find('input[type="checkbox"]').each(function(i){
			    				   if($(this).val() == strs[k]){
			    					  $(this).attr("checked","checked");
			    				   }
			    			   })
			    		   }
			    	   }
			    	   
			    	     //付款条件
			    	  $('#payment_term').find('option').each(function(i){
			    		  if($(this).val == paymentTerm){
			    			  $(this).remove();
			    		  }			    		  
			    	  })
			    	  $("#payment").val($('#payment_term').find('option').eq(0).val());
			    	  if(paymentTerm != null && paymentTerm != ''){
				    	  $("#payment").val(paymentTerm);
			    	  }
			    	   
//			    	    compare();	
			    	    /* table1 隔行换色*/
			    	    $('.table1 tbody tr:even').css({
			    	        'background-color':'#f9f9f9'
			    	    })
			      }else if(result.state == 2){
			    		 //如果还未登录，跳转登录页
			    		 window.location = "/zh/login.html";
			      }else if(result.state == 3){
			    	  easyDialog.open({
			      		   container : {
			          		     header : '  提示信息',
			          		     content : ' 无权限查看'
			      		   },
							  overlay : false
			      		 });   
			      }   			   			      			 
	      })
		
})








//修改产品信息
function add_price(obj){
	var purchaserRequirement = '';
	var paymentTerm = $('#payment').val();
	$('#requirement').find("input[type=checkbox]").each(function(){
		if ($(this).is(':checked')) {
		   purchaserRequirement +=$(this).val()+",";
        }
	})
	if(purchaserRequirement != ''){
		purchaserRequirement = purchaserRequirement.substring(0,purchaserRequirement.length-1);
	}
	
	
	var priceList = '';
	//遍历tr
	$('#product_tbody tr').each(function(i){
		var quantityList;
			var productId = $(this).find('td').eq(0).find('input:first').val();
			var quantity = $(this).find('td').eq(0).find('input').eq(1).val();
			var unitPrice = $(this).find('td').eq(0).find('input').eq(2).val();
			var moldPrice = $(this).find('td').eq(0).find('input').eq(3).val();
			var productRemark = $(this).find('td').eq(0).find('textarea').val();
			var re = /~/g;
			productRemark = productRemark.replace(re,"");
			priceList += (productId + "&" + quantity + "&" + unitPrice + "&"  + moldPrice + "&"  + productRemark + "~"); 

	})

	$(obj).css({'background-color':'#ddd'}).attr("disabled",true);
	$.post("/factoryInquiry/addBigProduct.do",	
			{"orderId" : orderId,
		     "factoryId" : factoryId,
		     "purchaserRequirement" : purchaserRequirement,
		     "paymentTerm" : paymentTerm,
		     "priceList" : priceList  
			},
			 function(result){
			  $(obj).css({'background-color':'#006dcc'}).removeAttr('disabled');		
				if(result.state == 0){
					easyDialog.open({
		         		   container : {
		             		     header : ' 提示信息',
		             		     content : ' 保存成功 '
		         		   },
							  overlay : false,
							  autoClose : 1000
		         		 });   
				   setTimeout("window.location='/zh/purchase_big_goods_generation.html?factoryId="+factoryId+"&orderId="+orderId+"'",1000);	  
				}else if(result.state == 2){
		    		 //如果还未登录，跳转登录页
		    		 window.location = "/zh/login.html";
		        }   
			}
		)	
	
}


//支付方式改变事件
function select_payment(obj){
	if($(obj).val() == '（）%预付，其余出货后付清'){
		$('#payment').show();
		$('#payment').attr('readonly',false).val($(obj).val());				
		$('#payment').focus();
	}else{
		$('#payment').hide();
		$('#payment').attr('readonly',true).val($(obj).val());
	}
}




function compare(){
	/*侧边栏长度控制效果*/
	var h1 = $(document.body).height() + 230;
	var h2 = window.screen.availHeight  ;
	if(h1 < h2){
		$('#footer').addClass('footer1');
	}else{
		$('#footer').removeClass('footer1');
	}
}





function keyPress(ob) {
	 if (!ob.value.match(/^[\+\-]?\d*?\.?\d*?$/)) ob.value = ob.t_value; else ob.t_value = ob.value; if (ob.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/)) ob.o_value = ob.value;
	}
function keyUp(ob) {
	 if (!ob.value.match(/^[\+\-]?\d*?\.?\d*?$/)) ob.value = ob.t_value; else ob.t_value = ob.value; if (ob.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/)) ob.o_value = ob.value;
	 if ($(ob).val() == 'undefined'){
		 $(ob).val('');	 
	 }
}
function onBlur(ob) {
	if(!ob.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))ob.value=ob.o_value;else{if(ob.value.match(/^\.\d+$/))ob.value=0+ob.value;if(ob.value.match(/^\.$/))ob.value=0;ob.o_value=ob.value};
	}

//注意：火狐使用event时， 
function inputNum(evt){ 
  //火狐使用evt.which获取键盘按键值，IE使用window.event.keyCode获取键盘按键值
  var ev = evt.which?evt.which:window.event.keyCode;
  if(ev>=48&&ev<=57){
    return true;  
  }else{
    return false;
  } 
} 
