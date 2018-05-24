


$(function(){

    queryAll(1);


})

 var currentPage = 1;

//查询所有正常询盘
 function queryAll(page){
	
	var process = $('#select_process').find('option:selected').text();
	if(process == undefined || process.trim() == "工艺"){
	   process = "";
	}
	var product = $('#product_name').val();
	if(product == undefined){
		product  == "";
	}

	
	$.post("/inquiry/queryFinishQuoteList.do",
			{
		     "process":process.trim(),
		     "product" : product,
		     "page" : page
			 },
			function(result){
		      if(result.state == 0){
		    	  var inquiryOrders = result.data.inquiryOrders;
		    	  var totalOrder = result.data.totalOrder;
		    	  var accepts = result.data.accepts;
		    	  var totalPage = Math.ceil(totalOrder/18);
		    	  
		      	  if(totalOrder>0){
		    		  $('.cc0').find('em').text("("+totalOrder+")");  
		    	  }		
		    	  
		    	  
		    	  var tl = inquiryOrders.length;
		    	  $('#tbody1').empty();
		    	  $('#tbody2').empty();
		    	  for(var i=0;i<tl;i++){ 
		    		  
		    		  //报价状态
		    		  var quoteState = inquiryOrders[i].quoteState;
		    		  var em = '';
		    		  if(quoteState == 5){
		    			 em = '<em class="em1"></em>';
		    		  }else if(quoteState == 2){
		    			 em = '<em class="em7"></em>';
		    		  }else if(quoteState == 3){
		    			 em = '<em class="em3"></em>';
		    		  }    		  		    		  
		    		  
		    		  
		    		  var a = '<a href="/zh/offer_detail.html?orderId='+inquiryOrders[i].orderId+'" class="amt10" title="'+(inquiryOrders[i].quoteTitle == null ? inquiryOrders[i].productName : inquiryOrders[i].quoteTitle)+'" target="_blank">'+(inquiryOrders[i].quoteTitle == null ? inquiryOrders[i].productName : inquiryOrders[i].quoteTitle)+'</a>';
		    		  //是否选择大货
		    		  if(accepts[i] == 0){
		    			 a = '<a href="/zh/supplier_big_goods.html?orderId='+inquiryOrders[i].orderId+'" class="amt10" title="'+(inquiryOrders[i].quoteTitle == null ? inquiryOrders[i].productName : inquiryOrders[i].quoteTitle)+'" target="_blank">'+(inquiryOrders[i].quoteTitle == null ? inquiryOrders[i].productName : inquiryOrders[i].quoteTitle)+'</a>';
		    		  }else if(accepts[i] == 1 || accepts[i] == 2 || accepts[i] == 3 || accepts[i] == 4){
		    			 a = '<a href="/zh/offer_detail.html?orderId='+inquiryOrders[i].orderId+'" class="amt10" title="'+(inquiryOrders[i].quoteTitle == null ? inquiryOrders[i].productName : inquiryOrders[i].quoteTitle)+'" target="_blank">'+(inquiryOrders[i].quoteTitle == null ? inquiryOrders[i].productName : inquiryOrders[i].quoteTitle)+'</a>'; 
		    		  }	    		  
		    		  var tr =   '<tr>'+
				                      '<td class="d1">'+
				                      '<img src="../images/zg.png" alt="" class="img1"/>'+
				                      '<div class="imgs2">'+
				                      '<img src="'+((inquiryOrders[i].drawingPathCompress == null || inquiryOrders[i].drawingPathCompress == '') ? '../images/pic2.png' : inquiryOrders[i].drawingPathCompress)+'" alt="" class="img2"/><br/>'+
				                      '</div>'+a+				                      
				                  '</td>'+
				                  '<td class="d2">'+
				                      '<span>'+(inquiryOrders[i].mainProcess == null ? '' : inquiryOrders[i].mainProcess)+'</span><br/>'+
				                      '<span>'+(inquiryOrders[i].annualQuantity == null ? '' : inquiryOrders[i].annualQuantity)+'</span>'+
				                  '</td>'+
				                  '<td class="d3">'+(new Date(inquiryOrders[i].publishDate.replace(/-/g,"/").split(".")[0])).Format("yyyy-MM-dd")+'</td>'+
				                  '<td>'+(inquiryOrders[i].state == 1 ? '江浙沪' : (inquiryOrders[i].state == 2 ? '深圳、广东、福建' : '不限'))+em+'</td>'+
				                 '</tr>';
		    		  
		    		  
		    		  
		    		  
		    		  
		    		  if(i % 2 == 0){
		    			  $('#tbody1').append(tr);
		    		  }else if(i % 2 == 1){
		    			  $('#tbody2').append(tr); 
		    		  }
    		
		    	  }
		    	  
		    	  $('#page_ul').empty();
		    	  var li = "";
		    	  if(totalPage == 0 || totalPage == 1){
		    		  li =    '<li class="li0"><a href="#" class="c-hide">&lt;&lt;</a></li><li class="active"><a href="#">1</a></li>'+
				    			  '<li class="li0">'+
		                          '<a class="c-hide">&gt;&gt;</a>'+
		                          '</li>';		    		  		    		  
		    	  }else{
		    		  var li_s = "";
		    		  for(var k=0;k<totalPage;k++){
		    			  if(k == totalPage - 2){
		    				  li_s += "<li class='active'><a href='#' onclick='queryByPage("+(k+1)+")'>"+(k+1)+"</a></li>";  
		    			  }else{
		    				  li_s += "<li><a href='#' onclick='queryByPage("+(k+1)+")'>"+(k+1)+"</a></li>"; 
		    			  }		    			     
		    		  }
		    		  
		    		 
		    		  //第一页 前面的不能点击
		    		 if(page == 1){
			    		 li =  '<li class="li0" ><a class="c-hide">&lt;&lt;</a></li>'+li_s+
			    			    '<li class="li0">'+
		                        '<a href="#" class="c-show" onclick="queryByPage('+(currentPage+1)+')">&gt;&gt;</a>'+
		                        '</li>';	
		    		  }else if(page == totalPage){
		    			  li =  '<li class="li0"><a href="#" onclick="queryByPage('+(currentPage-1)+')" class="c-show">&lt;&lt;</a></li>'+li_s+
			    			    '<li class="li0">'+
		                        '<a class="c-hide">&gt;&gt;</a>'+
		                        '</li>';	 
		    		  }else{
		    			  li =  '<li class="li0"><a href="#" class="c-show" onclick="queryByPage('+(currentPage-1)+')" class="c-show">&lt;&lt;</a></li>'+li_s+
			    			    '<li class="li0">'+
		                        '<a href="#" class="c-show" onclick="queryByPage('+(currentPage+1)+')">&gt;&gt;</a>'+
		                        '</li>';	 
		    		  } 

		    	  }
		    	  $('#page_ul').append(li);
		    	  $('#page_ul').find('li').eq(page).addClass('active').siblings().removeClass('active');
//		    	  compare();
		      }else if(result.state == 2){
		    		 //如果还未登录，跳转登录页
		    		 window.location = "/zh/login.html";
		      }    
		  })	
		  
		  
} 


 //根据工艺筛选
 function queryByProcess(obj){
    queryAll(1);	
 }
 //根据工艺筛选
 function queryByKey(obj){
    queryAll(1);	
 }
 
 function queryByPage(page){
	 if(page == currentPage){
		 return false;
	 }else{
		 currentPage = page;		 
		 queryAll(page);	 
	 }
 }


 //根据选择的checkbox添加收藏
 function collect_inquiry(){
	var orderIds = '';
	$('#total_div').find('tbody').find('input:checkbox').each(function(){		
		if($(this).is(':checked')){
			var orderId = $(this).next().val();
			orderIds += orderId + ",";
		}	
	})
	
	if(orderIds == ''){
		return false;
	}
	
	$.post("/inquiry/addCollect.do",
			{
		     "orderIds":orderIds
			 },
			function(result){				 
				if(result.state == 0){
					easyDialog.open({
						  container : {
						    content : '收藏成功'
						  },
			    		  overlay : false,
			    		  autoClose : 1000
						});
				}else{
					easyDialog.open({
						  container : {
						    content : '收藏失败'
						  },
			    		  overlay : false,
			    		  autoClose : 1000
						});
				} 
		    })
 }
 
 
 //查询订单详情
 function queryDetails(orderId){
    window.location = "/zh/offer_detail.html?orderId="+orderId;
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
 
 

