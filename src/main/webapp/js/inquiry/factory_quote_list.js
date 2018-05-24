


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
		product  == "";factory_quote_list.html
	}

	var quoteStatus = null;
	var str = window.location.search;
	if(str != null && str != ''){
		quoteStatus = str.substr(1);
		quoteStatus = quoteStatus.split("&")[0].split("=")[1];
	}
    //判断当前报价状态，当为status为2时，显示为授盘给我
	if(quoteStatus == 2 || quoteStatus == 3 || quoteStatus == 5){
		$('#quote_ul').find('li').removeClass('li1');
		$('#quote_ul').find('li').find('a').removeClass('cc0').css({"color":"#333"});
		$('#quote_ul').find('li').find('span').hide();
		$('#quote_ul').find('li').eq(4).find('a').addClass('cc0').css({"color":"#006dcc"});
		$('#quote_ul').find('li').eq(4).find('span').show();
		$('#quote_ul').find('li').eq(4).addClass('li1');
	}else if(quoteStatus == 7){
		$('#quote_ul').find('li').removeClass('li1');
		$('#quote_ul').find('li').find('a').removeClass('cc0').css({"color":"#333"});
		$('#quote_ul').find('li').find('span').hide();
		$('#quote_ul').find('li').eq(3).find('a').addClass('cc0').css({"color":"#006dcc"});
		$('#quote_ul').find('li').eq(3).find('span').show();
		$('#quote_ul').find('li').eq(3).addClass('li1');		
	}
	
	$.post("/inquiry/queryQuoteList.do",
			{
	         "status":quoteStatus,
		     "process":process.trim(),
		     "product" : product,
		     "page" : page
			 },
			function(result){
		      if(result.state == 0){
		    	  var inquiryOrders = result.data.inquiryOrders;
		    	  var totalOrder = result.data.totalOrder;
		    	  var totalPage = Math.ceil(totalOrder/18);
		    	  
		      	  if(totalOrder>0){
		    		  $('.cc0').find('em').text("("+totalOrder+")");  
		    	  }		
		    	  
		    	  
		      	  
		      	  //添加询盘信息
		    	  var tl = inquiryOrders.length;
		    	  $('#tbody1').empty();
		    	  $('#tbody2').empty();
		    	  for(var i=0;i<tl;i++){		    		  
		    		  
		    		  var tr =   '<tr>'+
				                      '<td class="d1">'+
				                      '<img src="../images/zg.png" alt="" class="img1"/>'+
				                      '<div class="imgs2">'+
				                      '<img src="'+((inquiryOrders[i].drawingPathCompress == null || inquiryOrders[i].drawingPathCompress == '') ? '../images/pic2.png' : inquiryOrders[i].drawingPathCompress)+'" alt="" class="img2"/><br/>'+
				                      '</div>'+
				                      '<a href="/zh/offer_detail.html?orderId='+inquiryOrders[i].orderId+'" class="amt10" title="'+(inquiryOrders[i].quoteTitle == null ? inquiryOrders[i].productName : inquiryOrders[i].quoteTitle)+'" target="_blank">'+(inquiryOrders[i].quoteTitle == null ? inquiryOrders[i].productName : inquiryOrders[i].quoteTitle)+'</a>'+
				                  '</td>'+
				                  '<td class="d2">'+
				                      '<span>'+(inquiryOrders[i].mainProcess == null ? '' : inquiryOrders[i].mainProcess)+'</span><br/>'+
				                      '<span>'+(inquiryOrders[i].annualQuantity == null ? '' : inquiryOrders[i].annualQuantity)+'</span>'+
				                  '</td>'+
				                  '<td class="d3">'+(new Date(inquiryOrders[i].publishDate.replace(/-/g,"/").split(".")[0])).Format("yyyy-MM-dd")+'</td>'+
				                  '<td>'+(inquiryOrders[i].state == 1 ? '江浙沪' : (inquiryOrders[i].state == 2 ? '深圳、广东、福建' : '不限'))+'</td>'+
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
		var h1 = $(document.body).height() ;
		var h2 = window.screen.availHeight  ;
		if(h1 < h2){
			$('#footer').addClass('footer1');
		}else{
			$('#footer').removeClass('footer1');
		}
} 
 
 

