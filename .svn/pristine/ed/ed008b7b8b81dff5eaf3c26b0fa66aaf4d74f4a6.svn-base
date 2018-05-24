var orderId;
var factoryId;
$(function(){
	var str = window.location.search;
	if(str != null && str != ''){
		str = str.substr(1);
		orderId = str.split("&")[1].split("=")[1];
		factoryId = str.split("&")[0].split("=")[1];
	}
	$.post("/factoryInquiry/queryBigProductDetails.do",	
			{"orderId":orderId,
		     "factoryId":factoryId},
			 function(result){
			      if(result.state == 0){
			    	 var quoteInfo = result.data.quoteInfo; 
			    	 var quoteBigProducts = result.data.quoteBigProducts; 
			    	 var quoteBigProductsTab = result.data.quoteBigProductsTab; 		    	 
			    	 var supplierQuoteInfos = result.data.supplierQuoteInfos; 		    	 
			    	 var supplierQuoteInfo = result.data.supplierQuoteInfo; 		    	 
			    	 
			    	 //询盘条件
			    	 $('#quoteId').text(quoteInfo.orderId);
			    	//未发布询盘不显示工厂报价详情
			    	 if(quoteInfo.orderStatus == 0){
			    		 $('.panel4').hide();
			    		 $('#quoteId').next().text('待发布');
			    	 }else if(quoteInfo.orderStatus == 1){
			    		 $('#quoteId').next().text('可报价'); 
			    	 }else if(quoteInfo.orderStatus == 2){
			    		 $('#quoteId').next().text('已结束'); 
			    	 }else if(quoteInfo.orderStatus == 3){
			    		 $('#quoteId').next().text('已取消'); 
			    	 }else if(quoteInfo.orderStatus == 4){
			    		 $('#quoteId').next().text('已过期'); 
			    	 }else if(quoteInfo.orderStatus == 5){
			    		 $('#quoteId').next().text('授盘中'); 
			    	 }else if(quoteInfo.orderStatus == 6){
			    		 $('#quoteId').next().text('生产中'); 
			    	 }else if(quoteInfo.orderStatus == 7){
			    		 $('.panel4').hide();
			    		 $('#quoteId').next().text('未通过'); 
			    	 }
			    	 
			    	 
			    	 
			    	 if(quoteInfo.quoteTitle == null || quoteInfo.quoteTitle == ''){
			    		 $('#quote_title').hide();
			    		 $('#quote_title').prev().hide();
			    	 }else{
				    	 $('#quote_title').text(quoteInfo.quoteTitle);
			    	 }	    	
			    	 
			    	 $('#big_product_id').val(quoteBigProducts.id);
			    	 $('#payment_term').text(quoteBigProducts.paymentTerm);
			    	 $('#requirement').text((quoteBigProducts.purchaserRequirement == null ? '' : quoteBigProducts.purchaserRequirement));
			    	
			    	 //根据大货状态判断显示
			    	 if(quoteBigProducts.isSupplierAccept == 0){
			    		 $('#panel3').find('span:first').text(new Date(quoteBigProducts.createTime.replace(/-/g,"/").split(".")[0]).Format("yyyy-MM-dd hh:mm:ss"));
			    		 $('#panel1').show();				    		 
			    	 }
			    	 if(quoteBigProducts.isSupplierAccept == 1){
			    		 $('#panel3').show();
			    		 $('#panel3').find('b:first').text(quoteBigProducts.factoryName);
			    		 $('#panel3').find('span:first').text(new Date(quoteBigProducts.supplierReplyTime.replace(/-/g,"/").split(".")[0]).Format("yyyy-MM-dd hh:mm:ss"));
			    		 
			    		 var amount = 0.0;
			    		 var totalAmount = quoteBigProducts.totalAmount;
			    		 var paymentTerm = quoteBigProducts.paymentTerm;
			    		 if(paymentTerm == "30%预付，其余出货后付清"){
			    			 amount = Number(totalAmount*0.3).toFixed(2); 
			    		 }else if(paymentTerm == "50%预付，其余出货后付清"){
			    			 amount = Number(totalAmount*0.5).toFixed(2); 
			    		 }else if(paymentTerm == "出货后30天付款"){
			    			 amount = Number(totalAmount*0).toFixed(2); 
			    		 }else if(paymentTerm == "同意供应商定义的支付方式"){
			    			  var payment = supplierQuoteInfo.paymentTerm;
			    			  if(payment == "30%预付，其余出货后付清"){
					    			 amount = Number(totalAmount*0.3).toFixed(2); 
					    	  }else if(payment == "50%预付，其余出货后付清"){
					    			 amount = Number(totalAmount*0.5).toFixed(2); 
					    	  }else if(payment == "出货后30天付款"){
					    			 amount = Number(totalAmount*0).toFixed(2); 
					    	  }else{
					    		      var split = paymentTerm.split("%");
					    			  var num= split.replace(/[^0-9]/ig,"");
					    			  amount = Number(totalAmount * num / 100).toFixed(2);   
					    	  }
			    		 }else{
			    			 var split = paymentTerm.split("%");
			    			 var num= split.replace(/[^0-9]/ig,"");
			    			 amount = Number(totalAmount * num / 100).toFixed(2); 
			    		 }
			    		 $('#panel3').find('b:eq(1)').text(amount);
			    	 }
			    	 if(quoteBigProducts.isSupplierAccept == 2){
			    		 $('#panel2').show();
			    		 $('#panel2').find('b:first').text(quoteBigProducts.factoryName);
			    		 $('#panel2').find('p:first').text(quoteBigProducts.supplierRemark);
			    		 $('#panel2').find('span:first').text(new Date(quoteBigProducts.supplierReplyTime.replace(/-/g,"/").split(".")[0]).Format("yyyy-MM-dd hh:mm:ss"));
			    		 $('#panel2').find('a:last').attr("href","/zh/purchase_big_goods.html?factoryId="+factoryId+"&orderId="+orderId+"");
			    	 }
			    	 if(quoteBigProducts.isSupplierAccept == 3){
			    		 $('#panel4').find('span:first').text(new Date(quoteBigProducts.paymentNotConfirmTime.replace(/-/g,"/").split(".")[0]).Format("yyyy-MM-dd hh:mm:ss"));
			    		 $('#panel4').show();
			    	 }
			    	 if(quoteBigProducts.isSupplierAccept == 4){
			    		 $('#panel5').find('span:first').text(new Date(quoteBigProducts.paymentConfirmTime.replace(/-/g,"/").split(".")[0]).Format("yyyy-MM-dd hh:mm:ss"));
			    		 $('#panel5').show();
			    	 }
			    	 
			    	 
			    	 
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
					                            '<td colspan="7">'+(quoteBigProductsTab[i].productRemark == null ? '无' : quoteBigProductsTab[i].productRemark)+				                              
					                            '</td>'+
					                        '</tr>';
							   
		
							 
							  $('#product_tbody').append(tr1);   
							  $('#product_tbody').append(tr2);   							 
							  
                       }
				        if(quoteBigProductsTab.length == 1){
					          em_click($('.panel3 tbody em'));	
				        }

				        
				        //其他报价工厂
				        $('#refuse_ul').empty();
				        $('#refuse_div').empty();
				        for(var j=0;j<supplierQuoteInfos.length;j++){
				        	
				        	if(supplierQuoteInfos[j].factoryId != factoryId){
				        		
				        	  if(supplierQuoteInfos[j].quoteStatus == 7){
				        		 // $('#refuse_ul').parents('.panel4').hide();
				        		 // $('.btns').hide();				        		  
				        		  var div =  '<div class="panel panel2 panel4 panel5">'+
								              	(j == 0 ? '<div class="panel-heading posirela">拒绝其他工厂的理由 <span>*</span></div>' : '')+
								              	'<div class="panel-body">'+
								              		'<div class="companys">'+
								              			'<p>'+supplierQuoteInfos[j].factoryName+'</p>'+
								  	                    '<div class="supplement clearfix">'+
								  	                        '<em>原因</em>'+
								  	                        '<span>'+supplierQuoteInfos[j].refuseReasons+'</span>'+
								  	                    '</div>'+
								              		'</div>'+
								              	'</div>'+
								              '</div>';		
				        		  $('#refuse_div').append(div);
				        		  
				        	  }else{
				        		  //$('#refuse_ul').parents('.panel4').show();
				        		  var li =  '<li>'+
								                     '<div class="li_L posirela pull-left">'+
							                            '<input class="factory-id" type="hidden" value="'+supplierQuoteInfos[j].factoryId+'">'+
							                            '<p>'+supplierQuoteInfos[j].factoryName+'</p>'+
							                            '<div class="form-horizontal">'+
							                                '<div class="form-group">'+
							                                    '<label for="" class="col-sm-2 control-label">原因：</label>'+
							                                    '<div class="col-sm-10 w400 posirela">'+
							                                        '<span class="selectarror selspan"></span>'+
							                                        '<input class="form-control w400 no_confirm" onclick="select_reason(this,event)" type="text" readonly>'+
							                                        '<div class="dx w400 zgrzdx" id="qualificationForeach">'+
							                                            '<div class="checkbox">'+
							                                                '<label>'+
							                                                    '<input type="checkbox"  onchange="checkReason(this)" name="qualificationck" value="价格偏贵">价格偏贵'+
							                                                '</label>'+
							                                            '</div>'+
							                                            '<div class="checkbox">'+
							                                                '<label>'+
							                                                    '<input type="checkbox" onchange="checkReason(this)" name="qualificationck" value="地方远">地方远'+
							                                                '</label>'+
							                                            '</div>'+
							                                            '<div class="checkbox">'+
							                                                '<label>'+
							                                                    '<input type="checkbox"  onchange="checkReason(this)" name="qualificationck" value="资质不够">资质不够'+
							                                                '</label>'+
							                                            '</div>'+
							                                            '<div class="checkbox">'+
							                                                '<label>'+
							                                                    '<input type="checkbox" onchange="checkReason(this)"  name="qualificationck" value="反应速度慢">反应速度慢'+
							                                                '</label>'+
							                                            '</div>'+
							                                            '<div class="checkbox">'+
							                                                '<label>'+
							                                                    '<input type="checkbox"  onchange="checkReason(this)" name="qualificationck" value="其他">其他'+
							                                                '</label>'+
							                                            '</div>'+
							                                        '</div>'+
							                                    '</div>'+
							                                '</div>'+
							                                '<div class="form-group">'+
							                                    '<label for="" class="col-sm-2 control-label">补充说明：</label>'+
							                                    '<div class="col-sm-10 w400">'+
							                                        '<textarea class="form-control w400 z-text"></textarea>'+
							                                    '</div>'+
							                                '</div>'+
							                            '</div>'+
							                        '</div>'+
							                    '</li>';
							        	
							        	  $('#refuse_ul').append(li);
				        	    }					        								     
				        	}
				        	
				        }
				       
				        
				        if($('#refuse_ul').find('li').length == 0){
				        	$('#refuse_ul').parents('.panel4').hide();
				        	$('.btns').hide();
				        }
				        
				       //处理高度显示
//				        compare();
				        
				        /* panel4 里面的 li 隔行换色*/
				        $('.supplier_big_goods_generation .panel4 li:even').css({
				            'background-color':'#f9f9f9'
				        })
			      }else if(result.state == 2){
			    		 //如果还未登录，跳转登录页
			    		 window.location = "/zh/login.html";
			      }   			      			 
	      })
	
			    
	      
})


//选择拒绝理由
function select_reason(obj,e){	
	  $(obj).parent().find('.zgrzdx').toggle();
	  $(document).on("click", function(){
	    	$(".zgrzdx").hide();
	  });
	  $(".zgrzdx").on("click", function(e){
		    e.stopPropagation();
	  });
	  e.stopPropagation();
}


//选择取消资格认证事件
function checkReason(obj){
	var reasons = '';
	$(obj).parents('.zgrzdx').find('input[type="checkbox"]').each(function(){
		if($(this).is(':checked')){
			reasons +=$(this).val() + ",";
		}
	})   
	if(reasons != null && reasons != '' && reasons != undefined){
		reasons = reasons.substring(0,reasons.length-1);
	}
	$(obj).parents('.zgrzdx').prev().val(reasons);
}


//function show_reason(obj){
//	e.stopPropagation();
//}


//产品展开、收放
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




var flag = true;
//拒绝其他工厂
function refuse_factory(obj){
	
    var refuseReasons = "";
	$('#refuse_ul').find('li').each(function(){
		var reasons = $(this).find('.no_confirm').val() ;
		if($(this).find('.z-text').val() != null && $(this).find('.z-text').val() != ''
			&& $(this).find('.z-text').val() != undefined){
			reasons = reasons + "," +  $(this).find('.z-text').val();
		    }
		if(reasons == null || reasons == '' || reasons == undefined){
			easyDialog.open({
      		   container : {
          		     header : ' 提示信息',
          		     content : ' 拒绝理由不能为空'
      		   },
					  overlay : false,
					  autoClose : 1000
      		 });
			flag = false;
			return false;
		}else{
			flag = true;
		}
		var re = /&/g;
		reasons = reasons.replace(re,"");
		reasons = $(this).find('.factory-id').val() + "&" +reasons;				
		var re1 = /~/g;
		reasons = reasons.replace(re1,"");
		refuseReasons += reasons + "~";
	})	
	
	if(!flag){
		return false;
	}
	
	$(obj).css({'background-color':'#ddd'}).attr("disabled",true);
	$.post("/factoryInquiry/updateQuote.do",	
			{
		     "refuseReasons" : refuseReasons,  
		     "orderId" : orderId  
			},
			 function(result){
				
				 $(obj).css({'background-color':'#006dcc'}).removeAttr('disabled');		
		
				if(result.state == 0){
					easyDialog.open({
		         		   container : {
		             		     header : ' 提示信息',
		             		     content : ' 提交成功 '
		         		   },
							  overlay : false,
							  autoClose : 1000
		         		 });   	  
					 setTimeout("location.reload()",1000);	  
				}else if(result.state == 2){
		    		 //如果还未登录，跳转登录页
		    		 window.location = "/zh/login.html";
		        }   
		 }
	 )	
	
}


//更新大货生产状态
function update_big_product(obj,status){
	if($('.panel4').css("display") != "none"){
		easyDialog.open({
  		   container : {
      		     header : ' 提示信息',
      		     content : ' 请先拒绝其他工厂！'
  		   },
				  overlay : false,
				  autoClose : 1000
  		 });   
		return false;
	}
	
	
	$(obj).css({'color':'#ddd'}).attr("disabled",true);
	$.post("/factoryInquiry/updateBigProduct.do",	
			{
		     "status" : status,  
		     "orderId" : orderId,
		     "factoryId" : factoryId
			},
			 function(result){
				
				 $(obj).css({'color':'#006dcc'}).removeAttr('disabled');		
		
				if(result.state == 0){
					easyDialog.open({
		         		   container : {
		             		     header : ' 提示信息',
		             		     content : ' 提交成功，等待平台审核。'
		         		   },
							  overlay : false,
							  autoClose : 1000
		         		 });   
					 setTimeout("location.reload()",1000);	  
				}else if(result.state == 2){
		    		 //如果还未登录，跳转登录页
		    		 window.location = "/zh/login.html";
		        }   
			}
	 )	
}



function compare(){
	/*侧边栏长度控制效果*/
	var h1 = $(document.body).height() +230 ;
	var h2 = window.screen.availHeight ;
	if(h1 < h2){
		$('#footer').addClass('footer1');
	}else{
		$('#footer').removeClass('footer1');
	}
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


