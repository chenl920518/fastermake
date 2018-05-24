$(function(){
	selectFactoryDetailsInfo();
});
function selectFactoryDetailsInfo(){
	$.ajax({
		url : "/factoryInfo/selectFactoryDetailsInfo.do",
		type : "post",
		traditional : true,
		data : {
			
		},
		datatype : "json",
		success : function(result) {
			if (result.state == 0) {
				var data=result.data.factoryInfo;
				var factoryUser = result.data.factoryUser;
				$("#factoryName").text(data.factoryName);
				$("#loginEmail").text(factoryUser.email);
				$("#userName").val(factoryUser.username);
				$("#position").val(data.position);
				$("#tel").val(data.tel);
				$("#fax").val(data.fax);
				$("#mobile").val(data.mobile);
				if(factoryUser.permission != 1){
					$('.li3').prev().hide();
				}
				
			}else if(result.state == 2){
	    		 //如果还未登录，跳转登录页
	    		 window.location = "/zh/login.html";
	        }    
		}		
   });
}
function addcss(){
	
	  var h1 = $(document.body).height()  +230 ;
	    var h2 = window.screen.availHeight   ;
	    if(h1 < h2){
	        $('#footer').addClass('footer1')
	    }else{
	        $('#footer').removeClass('footer1');
	    };
	
}

//修改密码验证
function updateUserPassword(){
	 var loginEmail=$("#loginEmail").text();
     var cur_judgment=false;
     var new_judgment=false;
     var con_judgment=false;
	//验证当前密码是否为空
   	$('#curPass').blur(function(){     
   		$('#curPass').next().css({"display":"none"});
   		 var curPass = $('#curPass').val();
   		 if(!(curPass.trim() == '')){
   			$('#curPass').addClass('success');
   			cur_judgment = true; 
   		 }else{
   			$('#curPass').next().addClass('error').text("Please enter current password").fadeIn();
   			cur_judgment = false; 
   		 }		       		 
   		 return false;
    });
    //验证新密码是否为空
   	$('#newPass').blur(function(){     
   		$('#newPass').next().css({"display":"none"});
   		 var newPass = $('#newPass').val();
   		 if(!(newPass.trim() == '')){
   			$('#newPass').addClass('success');
   		    new_judgment = true; 
   		 }else{
   			$('#newPass').next().addClass('error').text("Please enter a new password").fadeIn();
   		    new_judgment = false; 
   		 }		       		 
   		 return false;
    });
   	
   //验证确认密码是否为空
   	$('#conPass').blur(function(){     
   		$('#conPass').next().css({"display":"none"});
   		 var conPass = $('#conPass').val();
   		 if(!(conPass.trim() == '')){
   			$('#conPass').addClass('success');
   			con_judgment = true; 
   		 }else{
   			$('#conPass').next().addClass('error').text("Please confirm your password").fadeIn();
   			con_judgment = false; 
   		 }		       		 
   		 return false;
    });
    var conPass = $('#conPass').val();
    var newPass = $('#newPass').val();
    var curPass=$("#curPass").val();
	if(newPass==conPass){
		$('#submitUpdate').addClass('success');
	}else{
		$('#submitUpdate').parent().prev().find('.error').text("Passwords must match").fadeIn();
		return false;
	}
	
   	$('#curPass').blur(); 
	$('#newPass').blur();
	$('#conPass').blur();
     
   	if(!(cur_judgment && new_judgment && con_judgment)){
   		return false; 
   	}
	$('#submitUpdate').css({'background-color':'#ddd'}).attr("disabled",true);
	$.ajax({
		url : "/factoryInfo/updateFactoryAccount.do",
		type : "post",
		traditional : true,
		data : {
			loginEmail:loginEmail,curPass:curPass,
			newPass:newPass,conPass:conPass
		},
		datatype : "json",
		success : function(result) {
			
		$('#submitUpdate').css({'background-color':'#006dcc'}).removeAttr("disabled");
			if (result.state == 0) {
				//修改成功之后需要清除cookies,重新登录
				$.ajax({
					url : "/account/clearCookie.do",
					type : "post",
					traditional : true,
					data : {
						
					},
					datatype : "json",
					success : function(result) {
						if (result.state == 0) {
							window.location.href="/en/login.html";
						}else{
							easyDialog.open({
					    		  container : {
					    		    header : 'Prompt message',
					    		    content : result.message
					    		  },
					    		  overlay : false,
					    		  autoClose : 1000   			    		  
					    	});	
						}
					}		
			   });
				
			}else if(result.state == 2){
	    		 //如果还未登录，跳转登录页
	    		 window.location = "/en/login.html";
	        }else{
				easyDialog.open({
		    		  container : {
		    		    header : 'Prompt message',
		    		    content : result.message
		    		  },
		    		  overlay : false,
		    		  autoClose : 1000   			    		  
		    		});	
			}
		},
		error : function(result) {
			console.log(result);
			$('#submitUpdate').css({'background-color':'#006dcc'}).removeAttr("disabled");
			easyDialog.open({
	    		  container : {
	    		    header : 'Prompt message',
	    		    content : result.message
	    		  },
	    		  overlay : false,
	    		  autoClose : 1000   			    		  
	    		});
		}
	})
}

//更新联系人信息
function updateFactoryInfo(){
	var userName=$("#userName").val();
    var position=$("#position").val();
    var tel=$("#tel").val();
    var fax=$("#fax").val();
    var mobile=$("#mobile").val();
    $.ajax({
		url : "/factoryInfo/updateFactoryContactInfo.do",
		type : "post",
		traditional : true,
		data : {
			userName:userName,position:position,
			tel:tel,fax:fax,mobile:mobile
		},
		datatype : "json",
		success : function(result) {
			console.log(result);
			if (result.state == 0) {
				easyDialog.open({
		    		  container : {
		    		    header : 'Prompt message',
		    		    content : "Modification success"
		    		  },
		    		  overlay : false,
		    		  autoClose : 1000   			    		  
		    	});	
				selectFactoryDetailsInfo();
			}else if(result.state == 2){
	    		 //如果还未登录，跳转登录页
	    		 window.location = "/zh/login.html";
	        }else{
				easyDialog.open({
		    		  container : {
		    		    header : 'Prompt message',
		    		    content : data.message
		    		  },
		    		  overlay : false,
		    		  autoClose : 1000   			    		  
		    		});	
			}
		},
		error : function(result) {
			easyDialog.open({
	    		  container : {
	    		    header : 'Prompt message',
	    		    content : result.message
	    		  },
	    		  overlay : false,
	    		  autoClose : 1000   			    		  
	    		});
		}
	})
}


