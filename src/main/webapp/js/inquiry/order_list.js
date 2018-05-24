
var url = "";
$(function(){
		
	var userStrs = getCookie("factoryInfo");
	if (userStrs != null && userStrs != '' && userStrs != undefined) {
        $('#join').hide();
        $('#join1').hide();
 	    url = "/inquiry/queryAllListByStatus.do";
 	    $('#select_process').parent().show();
	}else{
		$('#page_ul').hide();
		$('#page_ul1').hide();
		url = "/inquiry/queryByMainProcess.do";
		$('#select_process').parent().hide();
	}
	
	queryOrder(1,1);
})

 var currentPage = 1;
//查询正常询盘
 function queryOrder(status,page){
	
	var process = $('#select_process').find('option:selected').text();
	if(process == undefined || process.trim() == "工艺"){
	   process = "";
	}
	 
	 
	$.post( url,
			{
		     "status" : status,
		     "page" : page,
		     "process" : process 
			 },
			function(result){
		      if(result.state == 0){
		    	  var inquiryOrders = result.data.inquiryOrders;
		    	  var totalOrder = result.data.totalOrder;
		    	  var totalPage = Math.ceil(totalOrder/20);
		    	  
		    	  if(inquiryOrders != null && inquiryOrders != '' && inquiryOrders != undefined){
		    		  var tl = inquiryOrders.length;
			    	  $('#tbody1').empty();
			    	  $('#tbody2').empty();
			    	  $('#tbody3').empty();
			    	  $('#tbody4').empty();
			    	  $('.logBefore_new_inquiry').hide();			    	  
			    	  for(var i=0;i<tl;i++){
		    		     
			      	   	//获取国家对标国旗
			 	     	 var country = inquiryOrders[i].country;	
			 	    	 var flagSrc=getFlag(country); 	
			    		  
			    		  var tr =   '<tr>'+
					                      '<td class="d1">'+
					                      '<img src="'+flagSrc+'" alt="" class="img1"/>'+
					                      '<div class="imgs2">'+
					                      '<img style="margin:0;" src="'+((inquiryOrders[i].drawingPathCompress == null ||  inquiryOrders[i].drawingPathCompress ==  '')? '../images/pic2.png' : inquiryOrders[i].drawingPathCompress)+'" alt="" class="img2"/><br/></div>'+
					                      '<a href="/zh/detail.html?orderId='+inquiryOrders[i].orderId+'" class="amt10" title="'+(inquiryOrders[i].quoteTitle == null ? inquiryOrders[i].productName : inquiryOrders[i].quoteTitle)+'" target="_blank">'+(inquiryOrders[i].quoteTitle == null ? inquiryOrders[i].productName : inquiryOrders[i].quoteTitle)+'</a>'+
					                  '</td>'+
					                  '<td class="d2">'+
					                      '<span>'+(inquiryOrders[i].mainProcess == null ? '' : inquiryOrders[i].mainProcess)+'</span><br/>'+
					                      '<span>'+(inquiryOrders[i].annualQuantity == null ? '' : inquiryOrders[i].annualQuantity)+'</span>'+
					                  '</td>'+
					                  '<td class="d3">'+(inquiryOrders[i].publishDate == null ? '' : (new Date(inquiryOrders[i].publishDate.replace(/-/g,"/").split(".")[0])).Format("yyyy-MM-dd"))+'</td>'+
					                  '<td>'+(inquiryOrders[i].state == 1 ? '江浙沪' : (inquiryOrders[i].state == 2 ? '深圳、广东、福建' : '不限'))+'</td>'+
					                 '</tr>';
			    		  
				    		  if(status == 1){
					    		  if(i % 2 == 0){
					    			  $('#tbody1').append(tr);
					    		  }else if(i % 2 == 1){
					    			  $('#tbody2').append(tr); 
					    		  }   
				    		  }else if(status == 2){
				    			  if(i % 2 == 0){
					    			  $('#tbody3').append(tr);
					    		  }else if(i % 2 == 1){
					    			  $('#tbody4').append(tr); 
					    		  }    
				    		  }
				    		  
				    		  $('#page_ul').empty();
				    		  $('#page_ul1').empty();
				    		  
				    		  
				    		  
					    	  var li = "";
					    	  if(totalPage == 0 || totalPage == 1){
					    		  li =    '<li class="li0"><a href="#" class="c-hide">&lt;&lt;</a></li><li class="active"><a href="#">1</a></li>'+
							    			  '<li class="li0">'+
					                          '<a href="#" class="c-hide">&gt;&gt;</a>'+
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
					    	  if(status == 1){
						    	  $('#page_ul').append(li);
						    	  $('#page_ul').find('li').eq(page).addClass('active').siblings().removeClass('active');  
					    	  }else if(status == 2){
					    		  $('#page_ul1').append(li);
						    	  $('#page_ul1').find('li').eq(page).addClass('active').siblings().removeClass('active');  
					    	  } 
			    	      }		 
			    	  
			    	     $('.con_right_bottom ').show();
			    	     
			    	      /*隔行换色效果*/
					      $('.con_right_bottom table #tbody1 tr:even').css({
					          'background-color':'#f9f9f9'
					      })
					      /*隔行换色效果*/
					      $('.con_right_bottom table #tbody2 tr:even').css({
					    	  'background-color':'#f9f9f9'
					      })
			    	 
		    	  }else{
		    		  $('.con_right_bottom ').hide();
		    		  $('.con_right_bottom ').next().hide();
		    		  var injectionOrders = result.data.injectionOrders;      //塑料
		    		  var metalOrders = result.data.metalOrders;              //铸造
		    		  var machiningOrders = result.data.machiningOrders;      //加工
		    		  var castingOrders = result.data.castingOrders;          //锻造
		    		  var machiningCastingOrders = result.data.machiningCastingOrders;      //机加工
		    		  var otherOrders = result.data.otherOrders;              //其他工艺
		    		  
		    		  $('.logBefore_new_inquiry').empty();
		    		  
		    		  //添加塑料
		    		  if(injectionOrders != null && injectionOrders != '' && injectionOrders != undefined){
		    			  
		    			  var trs_odd = "";
		    			  var trs_even = "";
		    			  for(var i=0;i<injectionOrders.length;i++){
		    				  
				      	   	//获取国家对标国旗
				 	     	 var country = injectionOrders[i].country;	
				 	    	 var flagSrc=getFlag(country); 	
		    				  
		    				  
		    				  var tr = '<tr>'+
				        					'<td class="d1">'+	                    						
			        						'<div class="imgs">'+
			        							'<img src="'+flagSrc+'" alt="" class="img1">'+
			        							'<img src="'+((injectionOrders[i].drawingPathCompress == null ||  injectionOrders[i].drawingPathCompress ==  '')? '../images/pic2.png' : injectionOrders[i].drawingPathCompress)+'" alt="" class="img2">'+
			        						'</div>'+
			        						'<a href="/zh/detail.html?orderId='+injectionOrders[i].orderId+'" class="amt10" title="'+(injectionOrders[i].quoteTitle == null ? injectionOrders[i].productName : injectionOrders[i].quoteTitle)+'" target="_blank">'+(injectionOrders[i].quoteTitle == null ? injectionOrders[i].productName : injectionOrders[i].quoteTitle)+'</a>'+
			        					'</td>'+
			        					'<td class="d2"><span>'+injectionOrders[i].mainProcess+'</span><br><em>'+(injectionOrders[i].annualQuantity == null ? '' : injectionOrders[i].annualQuantity)+'</em></td>'+
			        					'<td class="d3">'+(injectionOrders[i].publishDate == null ? '' : (new Date(injectionOrders[i].publishDate.replace(/-/g,"/").split(".")[0])).Format("yyyy-MM-dd"))+'</td>'+
			        					'<td>'+(injectionOrders[i].state == 1 ? '江浙沪' : (injectionOrders[i].state == 2 ? '深圳、广东、福建' : '不限'))+'</td>'+
			        					'<td><a href="/zh/detail.html?orderId='+injectionOrders[i].orderId+'" class="bj">报价</a></td>'+
			        				'</tr>';		
		    				  
		    				   
		    				   if(i % 2 == 0){
		    					   trs_odd +=tr;		    					   
					    	   }else if(i % 2 == 1){
					    		   trs_even +=tr;
					    	   }    
		    				   
		    			   }
		    			  
		    			  
		    			  //奇数、偶数分别放在不同的table
		    			  var table_odd = "";
		    			  var table_even = "";
		    			  if(trs_odd != ""){
		    				  table_odd =  '<table class="table1 pull-left">'+
								        			'<thead>'+
								        				'<tr>'+
								        					'<th class="th1"></th>'+
								        					'<th class="th2">询盘信息</th>'+
								        					'<th class="th2">询价日期</th>'+
								        					'<th class="th3">地域</th>'+
								        					'<th class="th3"></th>'+
								        				'</tr>'+
								        			'</thead>'+
								        			'<tbody>'+trs_odd+							        				        				
								        			'</tbody>'+
								        		'</table>';
		    			  }
		    			  if(trs_even != ""){
		    				  table_even =  
								    			'<table class="table1 table2 pull-left">'+
								        			'<thead>'+
								        				'<tr>'+
								        					'<th class="th1"></th>'+
								        					'<th class="th2">询盘信息</th>'+
								        					'<th class="th2">询价日期</th>'+
								        					'<th class="th3">地域</th>'+
								        					'<th class="th3"></th>'+
								        				'</tr>'+
								        			'</thead>'+
								        			'<tbody>'+trs_even+ 							        				        				
								        			'</tbody>'+
								        		'</table>';
		    			  }
		    			  
		    			  
		    			  var  table =  '<div class="inquiry_0 clearfix">'+
							          			'<div class="title">'+
							    				'<span><i class="iconfont">&#xe648;</i></span><em>塑料</em>'+
							    			'</div>'+table_odd+ table_even +							    			
							    		'</div>';
		    			  
		    			  $('.logBefore_new_inquiry').append(table);
		    		  }
		    		
						    		  
		    		  //添加铸造
		    		  if(metalOrders != null && metalOrders != '' && metalOrders != undefined){
		    			  
		    			  var trs_odd1 = "";
		    			  var trs_even1 = "";
		    			  for(var i=0;i<metalOrders.length;i++){
		    				  
				      	   	//获取国家对标国旗
				 	     	 var country = metalOrders[i].country;	
				 	    	 var flagSrc=getFlag(country); 	
		    				  
		    				  
		    				  var tr = '<tr>'+
				        					'<td class="d1">'+	                    						
			        						'<div class="imgs">'+
			        							'<img src="'+flagSrc+'" alt="" class="img1">'+
			        							'<img src="'+((metalOrders[i].drawingPathCompress == null ||  metalOrders[i].drawingPathCompress ==  '')? '../images/pic2.png' : metalOrders[i].drawingPathCompress)+'" alt="" class="img2">'+
			        						'</div>'+
			        						'<a href="/zh/detail.html?orderId='+metalOrders[i].orderId+'" class="amt10" title="'+(metalOrders[i].quoteTitle == null ? metalOrders[i].productName : metalOrders[i].quoteTitle)+'" target="_blank">'+(metalOrders[i].quoteTitle == null ? metalOrders[i].productName : metalOrders[i].quoteTitle)+'</a>'+
			        					'</td>'+
			        					'<td class="d2"><span>'+metalOrders[i].mainProcess+'</span><br><em>'+(metalOrders[i].annualQuantity == null ? '' : metalOrders[i].annualQuantity)+'</em></td>'+
			        					'<td class="d3">'+(metalOrders[i].publishDate == null ? '' : (new Date(metalOrders[i].publishDate.replace(/-/g,"/").split(".")[0])).Format("yyyy-MM-dd"))+'</td>'+
			        					'<td>'+(metalOrders[i].state == 1 ? '江浙沪' : (metalOrders[i].state == 2 ? '深圳、广东、福建' : '不限'))+'</td>'+
			        					'<td><a href="/zh/detail.html?orderId='+metalOrders[i].orderId+'" class="bj">报价</a></td>'+
			        				'</tr>';		
		    				  
		    				   if(i % 2 == 0){
		    					   trs_odd1 +=tr;		    					   
					    	   }else if(i % 2 == 1){
					    		   trs_even1 +=tr;
					    	   }    
		    			   }
		    			  
		    			  
		    			  //奇数、偶数分别放在不同的table
		    			  var table_odd1 = "";
		    			  var table_even1 = "";
		    			  if(trs_odd1 != ""){
		    				  table_odd1 =  '<table class="table1 pull-left">'+
								        			'<thead>'+
								        				'<tr>'+
								        					'<th class="th1"></th>'+
								        					'<th class="th2">询盘信息</th>'+
								        					'<th class="th2">询价日期</th>'+
								        					'<th class="th3">地域</th>'+
								        					'<th class="th3"></th>'+
								        				'</tr>'+
								        			'</thead>'+
								        			'<tbody>'+trs_odd1+							        				        				
								        			'</tbody>'+
								        		'</table>';
		    			  }
		    			  if(trs_even1 != ""){
		    				  table_even1 =  
								    			'<table class="table1 table2 pull-left">'+
								        			'<thead>'+
								        				'<tr>'+
								        					'<th class="th1"></th>'+
								        					'<th class="th2">询盘信息</th>'+
								        					'<th class="th2">询价日期</th>'+
								        					'<th class="th3">地域</th>'+
								        					'<th class="th3"></th>'+
								        				'</tr>'+
								        			'</thead>'+
								        			'<tbody>'+trs_even1+ 							        				        				
								        			'</tbody>'+
								        		'</table>';
		    			  }
		    			  
		    			  
		    			  
		    			  
		    			  var  table =  '<div class="inquiry_0 clearfix">'+
							          			'<div class="title">'+
							    				'<span><i class="iconfont">&#xe648;</i></span><em>铸造</em>'+
							    			'</div>'+table_odd1 + table_even1 +
							    		'</div>';
		    			  
		    			  $('.logBefore_new_inquiry').append(table);
		    		  } 
		    		  
		    		  
		    		  //添加加工
		    		  if(machiningOrders != null && machiningOrders != '' && machiningOrders != undefined){
		    			  
		    			  var trs_odd2 = "";
		    			  var trs_even2 = "";
		    			  for(var i=0;i<machiningOrders.length;i++){
		    				  
				      	   	//获取国家对标国旗
				 	     	 var country = machiningOrders[i].country;	
				 	    	 var flagSrc=getFlag(country); 	
		    				  
		    				  
		    				  var tr = '<tr>'+
				        					'<td class="d1">'+	                    						
			        						'<div class="imgs">'+
			        							'<img src="'+flagSrc+'" alt="" class="img1">'+
			        							'<img src="'+((machiningOrders[i].drawingPathCompress == null ||  machiningOrders[i].drawingPathCompress ==  '')? '../images/pic2.png' : machiningOrders[i].drawingPathCompress)+'" alt="" class="img2">'+
			        						'</div>'+
			        						'<a href="/zh/detail.html?orderId='+machiningOrders[i].orderId+'" class="amt10" title="'+(machiningOrders[i].quoteTitle == null ? machiningOrders[i].productName : machiningOrders[i].quoteTitle)+'" target="_blank">'+(machiningOrders[i].quoteTitle == null ? machiningOrders[i].productName : machiningOrders[i].quoteTitle)+'</a>'+
			        					'</td>'+
			        					'<td class="d2"><span>'+machiningOrders[i].mainProcess+'</span><br><em>'+(machiningOrders[i].annualQuantity == null ? '' : machiningOrders[i].annualQuantity)+'</em></td>'+
			        					'<td class="d3">'+(machiningOrders[i].publishDate == null ? '' : ( new Date(machiningOrders[i].publishDate.replace(/-/g,"/").split(".")[0])).Format("yyyy-MM-dd"))+'</td>'+
			        					'<td>'+(machiningOrders[i].state == 1 ? '江浙沪' : (machiningOrders[i].state == 2 ? '深圳、广东、福建' : '不限'))+'</td>'+
			        					'<td><a href="/zh/detail.html?orderId='+machiningOrders[i].orderId+'" class="bj">报价</a></td>'+
			        				'</tr>';		
		    				  
		    				   if(i % 2 == 0){
		    					   trs_odd2 +=tr;		    					   
					    	   }else if(i % 2 == 1){
					    		   trs_even2 +=tr;
					    	   }    
		    			   }
		    			  
		    			  
		    			  //奇数、偶数分别放在不同的table
		    			  var table_odd2 = "";
		    			  var table_even2 = "";
		    			  if(trs_odd2 != ""){
		    				  table_odd2 =  '<table class="table1 pull-left">'+
								        			'<thead>'+
								        				'<tr>'+
								        					'<th class="th1"></th>'+
								        					'<th class="th2">询盘信息</th>'+
								        					'<th class="th2">询价日期</th>'+
								        					'<th class="th3">地域</th>'+
								        					'<th class="th3"></th>'+
								        				'</tr>'+
								        			'</thead>'+
								        			'<tbody>'+trs_odd2+							        				        				
								        			'</tbody>'+
								        		'</table>';
		    			  }
		    			  if(trs_even2 != ""){
		    				  table_even2 =  
								    			'<table class="table1 table2 pull-left">'+
								        			'<thead>'+
								        				'<tr>'+
								        					'<th class="th1"></th>'+
								        					'<th class="th2">询盘信息</th>'+
								        					'<th class="th2">询价日期</th>'+
								        					'<th class="th3">地域</th>'+
								        					'<th class="th3"></th>'+
								        				'</tr>'+
								        			'</thead>'+
								        			'<tbody>'+trs_even2+ 							        				        				
								        			'</tbody>'+
								        		'</table>';
		    			  }
		    			  
		    			  
		    			  
		    			  var  table =  '<div class="inquiry_0 clearfix">'+
							          			'<div class="title">'+
							    				'<span><i class="iconfont">&#xe648;</i></span><em>加工</em>'+
							    			'</div>'+ table_odd2 + table_even2 +
							    		'</div>';
		    			  
		    			  $('.logBefore_new_inquiry').append(table);
		    		  } 
		    		  
		    		  //添加机加工
		    		  if(machiningCastingOrders != null && machiningCastingOrders != '' && machiningCastingOrders != undefined){
		    			  
		    			  var trs_odd2 = "";
		    			  var trs_even2 = "";
		    			  for(var i=0;i<machiningCastingOrders.length;i++){
		    				  
				      	   	//获取国家对标国旗
				 	     	 var country = machiningCastingOrders[i].country;	
				 	    	 var flagSrc=getFlag(country); 			    				  
		    				  
		    				  
		    				  var tr = '<tr>'+
		    				  '<td class="d1">'+	                    						
		    				  '<div class="imgs">'+
		    				  '<img src="'+flagSrc+'" alt="" class="img1">'+
		    				  '<img src="'+((machiningCastingOrders[i].drawingPathCompress == null ||  machiningCastingOrders[i].drawingPathCompress ==  '')? '../images/pic2.png' : machiningCastingOrders[i].drawingPathCompress)+'" alt="" class="img2">'+
		    				  '</div>'+
		    				  '<a href="/zh/detail.html?orderId='+machiningCastingOrders[i].orderId+'" class="amt10" title="'+(machiningCastingOrders[i].quoteTitle == null ? machiningCastingOrders[i].productName : machiningCastingOrders[i].quoteTitle)+'" target="_blank">'+(machiningCastingOrders[i].quoteTitle == null ? machiningCastingOrders[i].productName : machiningCastingOrders[i].quoteTitle)+'</a>'+
		    				  '</td>'+
		    				  '<td class="d2"><span>'+machiningCastingOrders[i].mainProcess+'</span><br><em>'+(machiningCastingOrders[i].annualQuantity == null ? '' : machiningCastingOrders[i].annualQuantity)+'</em></td>'+
		    				  '<td class="d3">'+(machiningCastingOrders[i].publishDate == null ? '' : ( new Date(machiningCastingOrders[i].publishDate.replace(/-/g,"/").split(".")[0])).Format("yyyy-MM-dd"))+'</td>'+
		    				  '<td>'+(machiningCastingOrders[i].state == 1 ? '江浙沪' : (machiningCastingOrders[i].state == 2 ? '深圳、广东、福建' : '不限'))+'</td>'+
		    				  '<td><a href="/zh/detail.html?orderId='+machiningCastingOrders[i].orderId+'" class="bj">报价</a></td>'+
		    				  '</tr>';		
		    				  
		    				  if(i % 2 == 0){
		    					  trs_odd2 +=tr;		    					   
		    				  }else if(i % 2 == 1){
		    					  trs_even2 +=tr;
		    				  }    
		    			  }
		    			  
		    			  
		    			  //奇数、偶数分别放在不同的table
		    			  var table_odd2 = "";
		    			  var table_even2 = "";
		    			  if(trs_odd2 != ""){
		    				  table_odd2 =  '<table class="table1 pull-left">'+
		    				  '<thead>'+
		    				  '<tr>'+
		    				  '<th class="th1"></th>'+
		    				  '<th class="th2">询盘信息</th>'+
		    				  '<th class="th2">询价日期</th>'+
		    				  '<th class="th3">地域</th>'+
		    				  '<th class="th3"></th>'+
		    				  '</tr>'+
		    				  '</thead>'+
		    				  '<tbody>'+trs_odd2+							        				        				
		    				  '</tbody>'+
		    				  '</table>';
		    			  }
		    			  if(trs_even2 != ""){
		    				  table_even2 =  
		    					  '<table class="table1 table2 pull-left">'+
		    					  '<thead>'+
		    					  '<tr>'+
		    					  '<th class="th1"></th>'+
		    					  '<th class="th2">询盘信息</th>'+
		    					  '<th class="th2">询价日期</th>'+
		    					  '<th class="th3">地域</th>'+
		    					  '<th class="th3"></th>'+
		    					  '</tr>'+
		    					  '</thead>'+
		    					  '<tbody>'+trs_even2+ 							        				        				
		    					  '</tbody>'+
		    					  '</table>';
		    			  }
		    			  
		    			  
		    			  
		    			  var  table =  '<div class="inquiry_0 clearfix">'+
		    			  '<div class="title">'+
		    			  '<span><i class="iconfont">&#xe648;</i></span><em>机加工</em>'+
		    			  '</div>'+ table_odd2 + table_even2 +
		    			  '</div>';
		    			  
		    			  $('.logBefore_new_inquiry').append(table);
		    		  } 
		    		  
		    		  //添加锻造
		    		  if(castingOrders != null && castingOrders != '' && castingOrders != undefined){
		    			  
		    			  var trs_odd3 = "";
		    			  var trs_even3 = "";
		    			  for(var i=0;i<castingOrders.length;i++){
		    				  
				      	   	//获取国家对标国旗
				 	     	 var country = castingOrders[i].country;	
				 	    	 var flagSrc=getFlag(country); 		
		    				  
		    				  
		    				  var tr = '<tr>'+
				        					'<td class="d1">'+	                    						
			        						'<div class="imgs">'+
			        							'<img src="'+flagSrc+'" alt="" class="img1">'+
			        							'<img src="'+((castingOrders[i].drawingPathCompress == null ||  castingOrders[i].drawingPathCompress ==  '')? '../images/pic2.png' : castingOrders[i].drawingPathCompress)+'" alt="" class="img2">'+
			        						'</div>'+
			        						'<a href="/zh/detail.html?orderId='+castingOrders[i].orderId+'" class="amt10" title="'+(castingOrders[i].quoteTitle == null ? castingOrders[i].productName : castingOrders[i].quoteTitle)+'" target="_blank">'+(castingOrders[i].quoteTitle == null ? castingOrders[i].productName : castingOrders[i].quoteTitle)+'</a>'+
			        					'</td>'+
			        					'<td class="d2"><span>'+castingOrders[i].mainProcess+'</span><br><em>'+(castingOrders[i].annualQuantity == null ? '' : castingOrders[i].annualQuantity)+'</em></td>'+
			        					'<td class="d3">'+(castingOrders[i].publishDate == null ? '' : (new Date(castingOrders[i].publishDate.replace(/-/g,"/").split(".")[0])).Format("yyyy-MM-dd"))+'</td>'+
			        					'<td>'+(castingOrders[i].state == 1 ? '江浙沪' : (castingOrders[i].state == 2 ? '深圳、广东、福建' : '不限'))+'</td>'+
			        					'<td><a href="/zh/detail.html?orderId='+castingOrders[i].orderId+'" class="bj">报价</a></td>'+
			        				'</tr>';		
		    				  
		    				   if(i % 2 == 0){
		    					   trs_odd3 +=tr;		    					   
					    	   }else if(i % 2 == 1){
					    		   trs_even3 +=tr;
					    	   }    
		    			   }
		    			  
		    			  
		    			  
		    			  //奇数、偶数分别放在不同的table
		    			  var table_odd3 = "";
		    			  var table_even3 = "";
		    			  if(trs_odd3 != ""){
		    				  table_odd3 =  '<table class="table1 pull-left">'+
								        			'<thead>'+
								        				'<tr>'+
								        					'<th class="th1"></th>'+
								        					'<th class="th2">询盘信息</th>'+
								        					'<th class="th2">询价日期</th>'+
								        					'<th class="th3">地域</th>'+
								        					'<th class="th3"></th>'+
								        				'</tr>'+
								        			'</thead>'+
								        			'<tbody>'+trs_odd3+							        				        				
								        			'</tbody>'+
								        		'</table>';
		    			  }
		    			  if(trs_even3 != ""){
		    				  table_even3 =  '<table class="table1 table2 pull-left">'+
								        			'<thead>'+
								        				'<tr>'+
								        					'<th class="th1"></th>'+
								        					'<th class="th2">询盘信息</th>'+
								        					'<th class="th2">询价日期</th>'+
								        					'<th class="th3">地域</th>'+
								        					'<th class="th3"></th>'+
								        				'</tr>'+
								        			'</thead>'+
								        			'<tbody>'+trs_even3+ 							        				        				
								        			'</tbody>'+
								        		'</table>';
		    			  }
		    			  
		    			  
		    			  
		    			  var  table =  '<div class="inquiry_0 clearfix">'+
							          			'<div class="title">'+
							    				'<span><i class="iconfont">&#xe648;</i></span><em>锻造</em>'+
							    			'</div>'+ table_odd3 + table_even3 +							    		
							    		'</div>';
		    			  
		    			  $('.logBefore_new_inquiry').append(table);
		    		  } 
		    		  
		    		  //添加其他
		    		  if(otherOrders != null && otherOrders != '' && otherOrders != undefined){
		    			  
		    			  var trs_odd4 = "";
		    			  var trs_even4 = "";
		    			  for(var i=0;i<otherOrders.length;i++){
		    				  
		    				  
				      	   	//获取国家对标国旗
				 	     	 var country = otherOrders[i].country;	
				 	    	 var flagSrc=getFlag(country); 	
		    				  
		    				  var tr = '<tr>'+
		    				  '<td class="d1">'+	                    						
		    				  '<div class="imgs">'+
		    				  '<img src="'+flagSrc+'" alt="" class="img1">'+
		    				  '<img src="'+((otherOrders[i].drawingPathCompress == null ||  otherOrders[i].drawingPathCompress ==  '')? '../images/pic2.png' : otherOrders[i].drawingPathCompress)+'" alt="" class="img2">'+
		    				  '</div>'+
		    				  '<a href="/zh/detail.html?orderId='+otherOrders[i].orderId+'" class="amt10" title="'+(otherOrders[i].quoteTitle == null ? otherOrders[i].productName : otherOrders[i].quoteTitle)+'" target="_blank">'+(otherOrders[i].quoteTitle == null ? otherOrders[i].productName : otherOrders[i].quoteTitle)+'</a>'+
		    				  '</td>'+
		    				  '<td class="d2"><span>'+otherOrders[i].mainProcess+'</span><br><em>'+(otherOrders[i].annualQuantity == null ? '' : otherOrders[i].annualQuantity)+'</em></td>'+
		    				  '<td class="d3">'+(otherOrders[i].publishDate == null ? '':(new Date(otherOrders[i].publishDate.replace(/-/g,"/").split(".")[0])).Format("yyyy-MM-dd"))+'</td>'+
		    				  '<td>'+(otherOrders[i].state == 1 ? '江浙沪' : (otherOrders[i].state == 2 ? '深圳、广东、福建' : '不限'))+'</td>'+
		    				  '<td><a href="/zh/detail.html?orderId='+otherOrders[i].orderId+'" class="bj">报价</a></td>'+
		    				  '</tr>';		
		    				  
		    				   if(i % 2 == 0){
		    					   trs_odd4 +=tr;		    					   
					    	   }else if(i % 2 == 1){
					    		   trs_even4 +=tr;
					    	   }    
		    			  }
		    			  
		    			  
		    			  //奇数、偶数分别放在不同的table
		    			  var table_odd4 = "";
		    			  var table_even4 = "";
		    			  if(trs_odd4 != ""){
		    				  table_odd4 =  '<table class="table1 pull-left">'+
								        			'<thead>'+
								        				'<tr>'+
								        					'<th class="th1"></th>'+
								        					'<th class="th2">询盘信息</th>'+
								        					'<th class="th2">询价日期</th>'+
								        					'<th class="th3">地域</th>'+
								        					'<th class="th3"></th>'+
								        				'</tr>'+
								        			'</thead>'+
								        			'<tbody>'+trs_odd4+							        				        				
								        			'</tbody>'+
								        		'</table>';
		    			  }
		    			  if(trs_even4 != ""){
		    				  table_even4 =  
								    			'<table class="table1 table2 pull-left">'+
								        			'<thead>'+
								        				'<tr>'+
								        					'<th class="th1"></th>'+
								        					'<th class="th2">询盘信息</th>'+
								        					'<th class="th2">询价日期</th>'+
								        					'<th class="th3">地域</th>'+
								        					'<th class="th3"></th>'+
								        				'</tr>'+
								        			'</thead>'+
								        			'<tbody>'+trs_even4+ 							        				        				
								        			'</tbody>'+
								        		'</table>';
		    			  }
		    			  
		    			  
		    			  
		    			  var  table =  '<div class="inquiry_0 clearfix">'+
						    			  '<div class="title">'+
						    			  '<span><i class="iconfont">&#xe648;</i></span><em>其他</em>'+
						    			  '</div>'+ table_odd4 + table_even4 +
						    			'</div>';
		    			  
		    			  $('.logBefore_new_inquiry').append(table);
		    		  } 
		    		  
		    	  }	
		    	  
		    	 
			      /*隔行换色效果*/
			      $('.news_detail table tbody tr:even').css({
			          'background-color':'#f9f9f9'
			      })
		      }  
		      

//		      compare();
		  })	
} 

//查询已结束询盘
function query_finish(status,page){
	
	var process = $('#select_process').find('option:selected').text();
	if(process == undefined || process.trim() == "工艺"){
	   process = "";
	}

	$.post("/inquiry/queryAllListByStatus.do",
			{
		     "status" : status,
		     "page" : page,
		     "process" : process 
			 },
			function(result){
		      if(result.state == 0){
		    	  var inquiryOrders = result.data.inquiryOrders;
		    	  var totalOrder = result.data.totalOrder;
		    	  var totalPage = Math.ceil(totalOrder/20);
		    	  
		    	  var tl = inquiryOrders.length;
		    	  $('#tbody3').empty();
		    	  $('#tbody4').empty();
		    	  for(var i=0;i<tl;i++){	    
	    		  
		      	   	//获取国家对标国旗
		 	     	 var country = inquiryOrders[i].country;	
		 	    	 var flagSrc = getFlag(country); 	
		    		  
		    		  var tr =   '<tr>'+
				                      '<td class="d1">'+
				                      '<img src="'+flagSrc+'" alt="" class="img1"/>'+
				                      '<div class="imgs2">'+
				                      '<img style="margin:0;" src="'+((inquiryOrders[i].drawingPathCompress == null ||  inquiryOrders[i].drawingPathCompress ==  '')? '../images/pic2.png' : inquiryOrders[i].drawingPathCompress)+'" alt="" class="img2"/><br/></div>'+
				                      '<a href="/zh/detail.html?orderId='+inquiryOrders[i].orderId+'" class="amt10" title="'+(inquiryOrders[i].quoteTitle == null ? inquiryOrders[i].productName : inquiryOrders[i].quoteTitle)+'" target="_blank">'+(inquiryOrders[i].quoteTitle == null ? inquiryOrders[i].productName : inquiryOrders[i].quoteTitle)+'</a>'+
				                  '</td>'+
				                  '<td class="d2">'+
				                      '<span>'+(inquiryOrders[i].mainProcess == null ? '' : inquiryOrders[i].mainProcess)+'</span><br/>'+
				                      '<span>'+(inquiryOrders[i].annualQuantity == null ? '' : inquiryOrders[i].annualQuantity)+'</span>'+
				                  '</td>'+
				                  '<td class="d3">'+(inquiryOrders[i].publishDate == null ? '' : (new Date(inquiryOrders[i].publishDate.replace(/-/g,"/").split(".")[0])).Format("yyyy-MM-dd"))+'</td>'+
				                  '<td>'+(inquiryOrders[i].state == 1 ? '江浙沪' : (inquiryOrders[i].state == 2 ? '深圳、广东、福建' : '不限'))+'</td>'+
				                 '</tr>';
		    		  
		    		  
		    		  if(i % 2 == 0){
		    			  $('#tbody3').append(tr);
		    		  }else if(i % 2 == 1){
		    			  $('#tbody4').append(tr); 
		    		  }   
		    		  
		    		  
		    		  
		    		  
		    		  $('#page_ul').empty();
			    	  var li = "";
			    	  if(totalPage == 0 || totalPage == 1){
			    		  li =    '<li class="li0"><a href="#" class="c-hide">&lt;&lt;</a></li><li class="active"><a href="#">1</a></li>'+
					    			  '<li class="li0">'+
			                          '<a href="#" class="c-hide">&gt;&gt;</a>'+
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
				    		 li =  '<li class="li0" ><a href="#" class="c-hide">&lt;&lt;</a></li>'+li_s+
				    			    '<li class="li0">'+
			                        '<a href="#" class="c-show" onclick="queryByPage('+(currentPage+1)+')">&gt;&gt;</a>'+
			                        '</li>';	
			    		  }else if(page == totalPage){
			    			  li =  '<li class="li0"><a href="#" onclick="queryByPage('+(currentPage-1)+')" class="c-show">&lt;&lt;</a></li>'+li_s+
				    			    '<li class="li0">'+
			                        '<a href="#" class="c-hide">&gt;&gt;</a>'+
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
		    	  }	
		    	  $('.con_right_bottom ').show();
		    	  
		    	  

	    	    /*隔行换色效果*/
	    	    $('.con_right_bottom table #tbody3 tr:even').css({
	    	        'background-color':'#f9f9f9'
	    	    })
	    	    $('.con_right_bottom table #tbody4 tr:even').css({
	    	    	'background-color':'#f9f9f9'
	    	    })
		    	  
//		    	  compare();
		      }  
		  })	
} 



 //根据工艺筛选
 function queryByProcess(obj){
	 if($('#myTab').find('li').eq(0).attr('class') == 'active'){
		 queryOrder(1,1);
	 }else if($('#myTab').find('li').eq(1).attr('class') == 'active'){
		 queryOrder(2,1);
	 }
	 	
 }

 
 function queryByPage(page){
	 if(page == currentPage){
		 return false;
	 }else{
		 currentPage = page;		 
		 queryOrder(1,page);	 
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
    top.location = "/zh/detail.html?orderId="+orderId;
 }
 
 function compare(){
	 

		/*侧边栏长度控制效果*/
		var h1 = $(document.body).height();
		var h2 = window.screen.availHeight ;
		if(h1 < h2){
			$('#footer').addClass('footer1');
		}else{
			$('#footer').removeClass('footer1');
		}
}
 
 
 
 

