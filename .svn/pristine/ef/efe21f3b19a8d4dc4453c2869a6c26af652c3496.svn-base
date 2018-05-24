$(function(){
	
	$.post("/backstage/queryAll.do",	
			 function(result){
			      if(result.state == 0){
			    	  var quoteInfos = result.data;
			    	  $('#tbody').empty();
			    	  for(var i=0;i<quoteInfos.length;i++){
			    		  var tr = '<tr>'+
										'<td><a href="inquiry-release.html?orderId='+quoteInfos[i].orderId+'">'+quoteInfos[i].orderId+'</td>'+
										'<td>'+quoteInfos[i].customerId+'</td>'+
										'<td>'+quoteInfos[i].publishDate+'</td>'+		
										'<td>'+(quoteInfos[i].orderStatus == 0 ? '待审核' : (quoteInfos[i].orderStatus == 1 ? '正常询盘' : (quoteInfos[i].orderStatus == 2 ? '已结束' : (quoteInfos[i].orderStatus == 3 ? '已取消' : (quoteInfos[i].orderStatus == 4 ? '已过期' : (quoteInfos[i].orderStatus == 5 ? '决策中' : (quoteInfos[i].orderStatus == 6 ? '生产中' : (quoteInfos[i].orderStatus == 7 ? '审核未通过' : ''))) )))))+'</td>'+		
										'<td>'+((quoteInfos[i].orderStatus == 0 || quoteInfos[i].hasNewItem == 1)  ? '是' : '否')+'</td>'+
								   '</tr>';
			    		  $('#tbody').prepend(tr);
			    	  }
			    	  $('.table-sort').dataTable({"order": [[ 0, "desc" ]]});
			      }else if(result.state == 2){
			    		 //如果还未登录，跳转登录页
			    		 window.location = "/backstage/login.html";
			      }    
	      })	
})