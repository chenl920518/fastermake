var orderId;
var str = window.location.search;
if(str != null && str != ''){
	str = str.substr(1);
	orderId = str.split("&")[0].split("=")[1];
}
$(function(){

	$.post("/inquiry/queryBigProductDetails.do",
			{"orderId" : orderId},
			 function(result){
			      if(result.state == 0){
			    	 var quoteInfo = result.data.quoteInfo; 
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
			    	 
			    	 $('#big_product_id').val(quoteBigProducts.id);
			    	 $('#payment_term').text(quoteBigProducts.paymentTerm);
			    	 $('#requirement').text((quoteBigProducts.purchaserRequirement == null ? '' : quoteBigProducts.purchaserRequirement));
			    	 //产品列表
			    	 $('#product_tbody').empty();			    	 
			    	 		    		   
			        for(var i=0;i<quoteBigProductsTab.length;i++){			    			   								   							   
							
							 var tr1 =  '<tr>'+
			                            '<td>'+
			                                '<em onclick="em_click(this)"></em>'+
			                                '<div class="imgs">'+
			                                '<img src="'+((quoteBigProductsTab[i].drawingPathCompress == null || quoteBigProductsTab[i].drawingPathCompress == '') ? '../images/pic2.png' : quoteBigProductsTab[i].drawingPathCompress)+'" alt=""/>'+ 
			                                '</div>'+			                              
			                            '</td>'+
			                            '<td> <div class="ws"><span>'+quoteBigProductsTab[i].productName+'</span></div></td>'+
			                            '<td><div class="ws"><span>'+(quoteBigProductsTab[i].process == null ? quoteInfo.mainProcess : quoteBigProductsTab[i].process)+'</span></div></td>'+
			                            '<td><div class="ws"><span>'+(quoteBigProductsTab[i].materials == null ? '' : quoteBigProductsTab[i].materials)+'</span></div></td>'+
			                            '<td><div class="ws"><span>'+(quoteBigProductsTab[i].weight == null ? '' : quoteBigProductsTab[i].weight)+'</span></div></td>'+
			                            '<td><div class="ws"><span>'+quoteBigProductsTab[i].quantity+'</span></div></td>'+
			                            '<td><div class="ws"><span>'+quoteBigProductsTab[i].unitPrice+'</span></div></td>'+
			                            '<td><div class="ws"><span>'+quoteBigProductsTab[i].moldPrice+'</span></div></td>'+
			                        '</tr>';
							 
							   var tr2 =  '<tr class="trcol currdis">'+
					                            '<td class="ljms">注释：</td>'+
					                            '<td colspan="7">'+quoteBigProductsTab[i].productRemark+				                              
					                            '</td>'+
					                        '</tr>';
							 
							  $('#product_tbody').append(tr1);   
							  $('#product_tbody').append(tr2);   							 
							  
                       }
				        if(quoteBigProductsTab.length == 1){
					          em_click($('.panel3 tbody em'));	
				        }

			      }else if(result.state == 2){
			    		 //如果还未登录，跳转登录页
			    		 window.location = "/zh/login.html";
			      }    					      			 
	      })
	
	      
})


function em_click(obj){
    var $this = $(obj).parent().parent().next('.trcol');
    if($this.hasClass('currdis')){
        $this.removeClass('currdis');
        $this.show();
        $(obj).css({
            'background':'url(../images/red.png) no-repeat'
        })
    }else{
        $this.addClass('currdis');
        $this.hide();
        $(obj).css({
            'background':'url(../images/green.png) no-repeat'
        })
    }
}





//修改产品信息
function update_quote(obj,status){
	
	if(status == 2){
			if($('#reply_content').val() == null || $('#reply_content').val() == "" || $('#reply_content').val() == undefined ){
				easyDialog.open({
	      		   container : {
	          		     header : ' 提示信息',
	          		     content : ' 拒绝消息不能为空 '
	      		   },
						  overlay : false,
						  autoClose : 1000
	      		 });   
				return false;
			}
	}
	$(obj).css({'background-color':'#ddd'}).attr("disabled",true);
	$.post("/inquiry/updateBigProduct.do",	
			{
		     "orderId":orderId,
		     "supplierRemark" : $('#reply_content').val(),
		     "bigProductId":$('#big_product_id').val(),
		     "isSupplierAccept": status  
			},
			 function(result){
			   if(status == 1){
				 $(obj).css({'background-color':'#5bb75b'}).removeAttr('disabled');	
			   }else{
				 $(obj).css({'background-color':'#fff'}).removeAttr('disabled');	  
			   }
		
			   if(result.state == 0){
				    if(status == 1){
				    	window.location = "/zh/accept.html?orderId="+orderId;	
				    }else if(status == 2){
						window.location = "/zh/refuse.html?orderId="+orderId;	
				    }

			   }else if(result.state == 2){
		    		 //如果还未登录，跳转登录页
		    		 window.location = "/zh/login.html";
		       }    		
				
			}
		)	
	
}



function compare(){
	/*侧边栏长度控制效果*/
	var h1 = $(document.body).height()+230 ;
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


