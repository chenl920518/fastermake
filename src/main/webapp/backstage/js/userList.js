$(function(){
	
	

	
	
	
	$.post("/backstage/queryFactoryList.do",	
			 function(result){
			      if(result.state == 0){
			    	  var factoryList = result.data;
			    	  $('#tbody').empty();
			    	  for(var i=0;i<factoryList.length;i++){
			    		  var tr = '<tr>'+
										'<td><a href="user-manage.html?factoryId='+factoryList[i].factoryId+'">'+factoryList[i].factoryId+'</td>'+
										'<td>'+factoryList[i].factoryName+'</td>'+
										'<td>'+(factoryList[i].username == null ? '-' : factoryList[i].username)+'</td>'+
										'<td>'+(factoryList[i].createTime == null ? '-' : factoryList[i].createTime)+'</td>'+
										'<td>'+(factoryList[i].isVip == 101 ? "VIP" : "否")+'</td>'+
										'<td>'+(factoryList[i].vipExpiresTime == null ? '' :factoryList[i].vipExpiresTime)+'</td>'+
										'<td>是</td>'+
								   '</tr>';
			    		  $('#tbody').append(tr);
			    	  }
			    	  $('.table-sort').dataTable({});
			      }else if(result.state == 2){
			    		 //如果还未登录，跳转登录页
			    		 window.location = "/backstage/login.html";
			      }    
	      })
	      
	
})


function checkSession(){
	$.ajax({
		url : "/sysUser/checkSession.do",
		type : "post",
		success : function(data) {
			
			if(data.state==0){
				
				$('#loginName').text(data.data)
				
			}else{
				
				window.location = "/backstage/login.html";
				
			}
			
		}
	})
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}