
var orderStatus = null;
$(function(){
    queryAll();
	
	$('#customer_type').find('li').mouseover(function(){
		$(this).addClass('active');
	})
	$('#customer_type').find('li').mouseout(function(){
		$(this).removeClass('active');
	})
	
	$('#customer_type').find('li').click(function(){
		$('#select_type').val($(this).text());
		queryAll();
		$('#select_type').val("");
	})
	
	
	  /* 根据询盘状态  判断侧边栏效果 */
   	if(orderStatus == 0){		
	    $('.section_in li').hover(function(){
	        $(this).children('span').show();
	        $(this).siblings().not('.section_in li:eq(1)').children('span').hide();
	        $(this).children('a').css({'color':'#006dcc'});
	        $(this).siblings().not('.section_in li:eq(1)').children('a').css({'color':'#333'});
	        $('.section_in li:eq(1)').children('span').show();
		    $('.section_in li:eq(1)').children('a').css({'color':'#006dcc'});
	    });
	    $('.section_in li').mouseout(function(){
	    	 $('.section_in li').not('.section_in li:eq(1)').children('span').hide();
	    	 $('.section_in li').not('.section_in li:eq(1)').children('a').css({'color':'#333'});
	    })
	    /*侧边栏默认效果*/
	    $('.section_in li:eq(1)').children('span').show();
	    $('.section_in li:eq(1)').children('a').css({'color':'#006dcc'});	
	}else if(orderStatus == 1){
		$('.section_in li').hover(function(){
	        $(this).children('span').show();
	        $(this).siblings().not('.section_in li:eq(2)').children('span').hide();
	        $(this).children('a').css({'color':'#006dcc'});
	        $(this).siblings().not('.section_in li:eq(2)').children('a').css({'color':'#333'});
		    $('.section_in li:eq(2)').children('span').show();
		    $('.section_in li:eq(2)').children('a').css({'color':'#006dcc'});	
	    });
	    $('.section_in li').mouseout(function(){
	    	 $('.section_in li').not('.section_in li:eq(2)').children('span').hide();
	    	 $('.section_in li').not('.section_in li:eq(2)').children('a').css({'color':'#333'});
	    })
	    /*侧边栏默认效果*/
	    $('.section_in li:eq(2)').children('span').show();
	    $('.section_in li:eq(2)').children('a').css({'color':'#006dcc'});	
	}else if(orderStatus == 2){
		$('.section_in li').hover(function(){
	        $(this).children('span').show();
	        $(this).siblings().not('.section_in li:eq(5)').children('span').hide();
	        $(this).children('a').css({'color':'#006dcc'});
	        $(this).siblings().not('.section_in li:eq(5)').children('a').css({'color':'#333'});
		    $('.section_in li:eq(5)').children('span').show();
		    $('.section_in li:eq(5)') .children('a').css({'color':'#006dcc'});	
	    });
	    $('.section_in li').mouseout(function(){
	    	 $('.section_in li').not('.section_in li:eq(5)').children('span').hide();
	    	 $('.section_in li').not('.section_in li:eq(5)').children('a').css({'color':'#333'});
	    })
	    /*侧边栏默认效果*/
	    $('.section_in li:eq(5)').children('span').show();
	    $('.section_in li:eq(5)') .children('a').css({'color':'#006dcc'});	
	}else if(orderStatus == 3){
		$('.section_in li').hover(function(){
	        $(this).children('span').show();
	        $(this).siblings().not('.section_in li:eq(7)').children('span').hide();
	        $(this).children('a').css({'color':'#006dcc'});
	        $(this).siblings().not('.section_in li:eq(7)').children('a').css({'color':'#333'});
	        $('.section_in li:eq(7)').children('span').show();
		    $('.section_in li:eq(7)') .children('a').css({'color':'#006dcc'});
	    });
	    $('.section_in li').mouseout(function(){
	    	 $('.section_in li').not('.section_in li:eq(7)').children('span').hide();
	    	 $('.section_in li').not('.section_in li:eq(7)').children('a').css({'color':'#333'});
	    })
	    /*侧边栏默认效果*/
	    $('.section_in li:eq(7)').children('span').show();
	    $('.section_in li:eq(7)') .children('a').css({'color':'#006dcc'});	
	}else if(orderStatus == 4){
		$('.section_in li').hover(function(){
	        $(this).children('span').show();
	        $(this).siblings().not('.section_in li:eq(6)').children('span').hide();
	        $(this).children('a').css({'color':'#006dcc'});
	        $(this).siblings().not('.section_in li:eq(6)').children('a').css({'color':'#333'});
	        $('.section_in li:eq(6)').children('span').show();
		    $('.section_in li:eq(6)') .children('a').css({'color':'#006dcc'});	
	    });
	    $('.section_in li').mouseout(function(){
	    	 $('.section_in li').not('.section_in li:eq(6)').children('span').hide();
	    	 $('.section_in li').not('.section_in li:eq(6)').children('a').css({'color':'#333'});
	    })
	    /*侧边栏默认效果*/
	    $('.section_in li:eq(6)').children('span').show();
	    $('.section_in li:eq(6)') .children('a').css({'color':'#006dcc'});	
	}else if(orderStatus == 5){
		$('.section_in li').hover(function(){
	        $(this).children('span').show();
	        $(this).siblings().not('.section_in li:eq(3)').children('span').hide();
	        $(this).children('a').css({'color':'#006dcc'});
	        $(this).siblings().not('.section_in li:eq(3)').children('a').css({'color':'#333'});
	        $('.section_in li:eq(3)').children('span').show();
		    $('.section_in li:eq(3)') .children('a').css({'color':'#006dcc'});	
	    });
	    $('.section_in li').mouseout(function(){
	    	 $('.section_in li').not('.section_in li:eq(3)').children('span').hide();
	    	 $('.section_in li').not('.section_in li:eq(3)').children('a').css({'color':'#333'});
	    })
	    /*侧边栏默认效果*/
	    $('.section_in li:eq(3)').children('span').show();
	    $('.section_in li:eq(3)') .children('a').css({'color':'#006dcc'});	
	}else if(orderStatus == 6){
		$('.section_in li').hover(function(){
	        $(this).children('span').show();
	        $(this).siblings().not('.section_in li:eq(4)').children('span').hide();
	        $(this).children('a').css({'color':'#006dcc'});
	        $(this).siblings().not('.section_in li:eq(4)').children('a').css({'color':'#333'});
	        $('.section_in li:eq(4)').children('span').show();
		    $('.section_in li:eq(4)') .children('a').css({'color':'#006dcc'});	
	    });
	    $('.section_in li').mouseout(function(){
	    	 $('.section_in li').not('.section_in li:eq(4)').children('span').hide();
	    	 $('.section_in li').not('.section_in li:eq(4)').children('a').css({'color':'#333'});
	    })
	    /*侧边栏默认效果*/
	    $('.section_in li:eq(4)').children('span').show();
	    $('.section_in li:eq(4)') .children('a').css({'color':'#006dcc'});	
	}else if(orderStatus == 7){
		$('.section_in li').hover(function(){
	        $(this).children('span').show();
	        $(this).siblings().not('.section_in li:eq(8)').children('span').hide();
	        $(this).children('a').css({'color':'#006dcc'});
	        $(this).siblings().not('.section_in li:eq(8)').children('a').css({'color':'#333'});
	        $('.section_in li:eq(8)').children('span').show();
		    $('.section_in li:eq(8)') .children('a').css({'color':'#006dcc'});	
	    });
	    $('.section_in li').mouseout(function(){
	    	 $('.section_in li').not('.section_in li:eq(8)').children('span').hide();
	    	 $('.section_in li').not('.section_in li:eq(8)').children('a').css({'color':'#333'});
	    })
	    /*侧边栏默认效果*/
	    $('.section_in li:eq(8)').children('span').show();
	    $('.section_in li:eq(8)') .children('a').css({'color':'#006dcc'});	
	}else{
		$('.section_in li').hover(function(){
	        $(this).children('span').show();
	        $(this).siblings().not('.section_in li:eq(0)').children('span').hide();
	        $(this).children('a').css({'color':'#006dcc'});
	        $(this).siblings().not('.section_in li:eq(0)').children('a').css({'color':'#333'});
	        $('.section_in li:eq(0)').children('span').show();
		    $('.section_in li:eq(0)') .children('a').css({'color':'#006dcc'});	
	    });
	    $('.section_in li').mouseout(function(){
	    	 $('.section_in li').not('.section_in li:eq(0)').children('span').hide();
	    	 $('.section_in li').not('.section_in li:eq(0)').children('a').css({'color':'#333'});
	    })
	    /*侧边栏默认效果*/
	    $('.section_in li:eq(0)').children('span').show();
	    $('.section_in li:eq(0)') .children('a').css({'color':'#006dcc'});	
	}
})


//查询所有正常询盘
 function queryAll(){
	
	var process = $('#select_process').find('option:selected').text();
	if(process == undefined || process.trim() == "工艺"){
	   process = "";
	}
	var product = $('#product_name').val();
	if(product == undefined){
		product  == "";
	}


	var str = window.location.search;
	if(str != null && str != ''){
		orderStatus = str.substr(1);
		orderStatus = orderStatus.split("&")[0].split("=")[1];
	}

	
	$.post("/factoryInquiry/queryFactoryInquiryList.do",
			{
	         "orderStatus":orderStatus,
		     "process":process.trim(),
		     "product" : product
			 },
			function(result){
		      if(result.state == 0){
		    	  var inquiryOrders = result.data.inquiryOrders;		    	  
		    	  var supplierQuoteInfos = result.data.supplierQuoteInfos;		    	  
		    	  
		    	  var tl = inquiryOrders.length;
		    	  $('#tbody').empty();
		    	  var d_class;
		    	  for(var i=0;i<tl;i++){
                      if(inquiryOrders[i].orderStatus == 0){
                    	  d_class = "em6";
                      }else if(inquiryOrders[i].orderStatus == 1){
                    	  d_class = "em2";
                      }else if(inquiryOrders[i].orderStatus == 2){
                    	  d_class = "em1";
                      }else if(inquiryOrders[i].orderStatus == 3){
                    	  d_class = "em4";
                      }else if(inquiryOrders[i].orderStatus == 4){
                    	  d_class = "em5";
                      }else if(inquiryOrders[i].orderStatus == 5){
                    	  d_class = "em7";
                      }else if(inquiryOrders[i].orderStatus == 6){
                    	  d_class = "em3";
                      }else if(inquiryOrders[i].orderStatus == 7){
		    		      d_class = "em8";
		    	      }
		    		  
                      //获取报价工厂列表
                     var supplierQuoteInfo = supplierQuoteInfos[i];
//                     var options = '';
                     var p = '';
                     var sel;
                     for(var j=0;j<supplierQuoteInfo.length;j++){
//                    	 options += '<option>'+supplierQuoteInfo[j].factoryName+'</option>';
                    	 if(supplierQuoteInfo[j].factoryName != null){
                        	 p +='<p>'+(j+1)+'、'+supplierQuoteInfo[j].factoryName+'</p>'; 
                    	 }
                     }
//                     if(options == ''){
//                    	 sel = '<select class="form-control" style="display:none;">'+options+'</select>'; 
//                     }else{
//                    	 sel = '<select class="form-control">'+options+'</select>';  
//                     }

                      
		    		  var tr =   '<tr>'+
				                      '<td>'+
				                      '<a href="/zh/purchase_detail.html?orderId='+inquiryOrders[i].orderId+'" class="imgs">'+
				                          '<img src="'+((inquiryOrders[i].drawingPathCompress == null || inquiryOrders[i].drawingPathCompress == '') ? '../images/pic2.png' : inquiryOrders[i].drawingPathCompress)+'" alt=""/>'+
				                      '</a>'+
				                  '</td>'+
				                  '<td><a href="/zh/purchase_detail.html?orderId='+inquiryOrders[i].orderId+'" class="w100 color_006dcc fAM">'+inquiryOrders[i].orderId+(inquiryOrders[i].csgOrderId == null ? '' : '('+inquiryOrders[i].csgOrderId+')')+'</a></td>'+
				                  '<td><span class="w100">'+(inquiryOrders[i].quoteTitle == null ? inquiryOrders[i].productName : inquiryOrders[i].quoteTitle)+'</span></td>'+
				                  '<td><span class="w100">'+(inquiryOrders[i].mainProcess == null ? '' : inquiryOrders[i].mainProcess)+'</span></td>'+
				                  '<td><span class="fAM">'+(inquiryOrders[i].publishDate == null ? '' :(new Date(inquiryOrders[i].publishDate.replace(/-/g,"/").split(".")[0])).Format("yyyy-MM-dd"))+'</span></td>'+
				                  '<td>'+
				                      '<em class="'+d_class+'"></em>'+
				                      '<div class="zs_fac">'+p+'</div>'+
//				                      '<span><i></i></span>'+
				                  '</td>'+
				              '</tr>';
		    		  
		    		  $('#tbody').append(tr);
		    	  }
		    	  
		    	
//		    	    compare();
		    	   
		  	        /* 表格隔行换色效果*/
		    	    $('table tr:odd').css({
		    	        'background-color':'#f9f9f9'
		    	    })
		    	  
		      }else if(result.state == 2){
		    		 //如果还未登录，跳转登录页
		    		 window.location = "/zh/login.html";
		      }    
		  })	
		  

  
} 


 //根据工艺筛选
 function queryByProcess(obj){
    queryAll();	
 }
 //根据工艺筛选
 function queryByKey(obj){
    queryAll();	
 }


 
 //查询订单详情
 function queryDetails(orderId){
    window.location = "/zh/offer_detail.html?orderId="+orderId;
 }
 
 
 
 function compare(){
		/*侧边栏长度控制效果*/
		var h1 = $(document.body).height()+230;
		var h2 = window.screen.availHeight  ;
		if(h1 < h2){
			$('#footer').addClass('footer1');
		}else{
			$('#footer').removeClass('footer1');
		}
} 
 
 

