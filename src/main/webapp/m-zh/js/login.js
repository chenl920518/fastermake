     //自动填入用户、密码
        $(function(){     	
        	
        	//获取用户密码
        	var userStrs = getCookie("factoryInfo");
        	var loginEmail = getCookie("F_M");
        	var pwd = '';
        	if(userStrs != null && userStrs != '' && userStrs != undefined){
            	var str = [];
        		var base = new Base64();
            	userStrs=base.decode(userStrs);
            	loginEmail = base.decode(loginEmail);
            	str = userStrs.split("&");
            	pwd=str[2];
        	}

        	
        	if(!(loginEmail == null || loginEmail == '' || pwd == null || pwd == '')){
        		$('#loginEmail').val(loginEmail);
        	    $('#u_pwd').val(pwd);	     		
        	}
           //键盘enter事件
           $(document).keydown(function(event){         	   
	           if(event.keyCode==13){ 
	            login(); 
	           }           
           });

        
             var flag=false;
	       	 var e_judgment = false;
	       	 var p_judgment = false;
	       	 var dp_judgment = false;
	       	 var com_judgment = false;
	       	 var user_judgment = false;
	       	 
	       	 
	       	 
	       	 
	       	 
	         /*公司中文名限制*/
	     	$('#company_name').blur(function(){
	             var company_name = $(this).val();
	     		var myname = /^[\u4e00-\u9fa5]{1,}$/;		
	     		if(company_name == ''){
	     			$(this).siblings('span').html('公司名称不能为空');
	     			com_judgment = false;
	     			return false;
	     		}else if(!company_name.match(myname)){			
	     			$(this).siblings('span').html('请输入中文字符');
	     			com_judgment = false;
	     			return false;
	     		}else{
	     			$(this).siblings('span').html('');
	     			com_judgment = true;
	     		}
	     	});
	         /*姓名验证*/
	         $('#user_name').blur(function(){
	             var user_name = $(this).val();
	             if(user_name == ""){
	                 $(this).siblings('span').html('用户名不能为空');
	                 user_judgment = false; 
	                 return false;
	             }else{
	                 $(this).siblings('span').html('');
	                 user_judgment = true; 
	             }

	         });
	         /*电子邮件限制*/
	         $('#email').blur(function(){
	             var email = $(this).val();
	             var myemail = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
	             if(email == ""){
	                 $(this).siblings('span').html('邮箱不能为空');
	                 e_judgment = false;
	           		 return false;
	             }else if(!email.match(myemail)){
	                 $(this).siblings('span').html('请输入正确的邮箱格式');
	                 e_judgment = false;
	           		 return false;
	             }else{
	                 $(this).siblings('span').html('');
	             }
	             
//	        	 $.post("/account/verifyfEmail.do",
//     	            	  { 
//       		               "email" : email
//     	            	  },
//     	        	   function(result){
//     	        	     if(result.state == 0){
//	     	                e_judgment = true;
//     	        	     }else{
//     	        	    	 $('#email').next().html("邮箱已存在");
//	     			    	 e_judgment = false;
//     	        	     }
//     	        	  }); 

	        	 $.ajax({
	        		   type: "POST",
	        		   url: "/account/verifyfEmail.do",
	        		   data: {"email" : email},
	        		   async:false, 
	        		   success: function(result){
	        			   if(result.state == 0){
		     	                e_judgment = true;
	     	        	     }else{
	     	        	    	 $('#email').next().html("邮箱已存在");
		     			    	 e_judgment = false;
	     	        	    }
	        		   }
	        		});
	        	 
	             
	         });
	         
	         
	         /*密码验证*/
	         $('#pwd').blur(function(){
	             var pwd = $(this).val();
	             var mypwd = /^[\x21-\x7E]{6,15}$/;
	             if(pwd == ""){
	                 $(this).siblings('span').html('密码不能为空');
	                 p_judgment = false;
	       			 return false;
	             }else if(!pwd.match(mypwd)){
	                 $(this).siblings('span').html('请输入6到15个字符');
	                 p_judgment = false;
	       			 return false;
	             }else{
	                 $(this).siblings('span').html('');
	                 p_judgment = true;
	             }
	         });
	     	
	         /*确认密码验证*/
	         $('#pwd1').blur(function(){
	             var pwd1 = $(this).val(),
	                 mypwd1 = $('#pwd').val();
	             if(pwd1 == ""){
	                 $(this).siblings('span').html('确认密码不能为空');
	      			 dp_judgment = false;
	       		     return false;
	             }else if(pwd1 !== mypwd1){
	                 $(this).siblings('span').html('两次密码输入不一致');
	      			 dp_judgment = false;
	       			 return false;
	             }else{
	                 $(this).siblings('span').html('');
	                 dp_judgment = true;
	             }
	         });
	       	 
	       	 
	       	 
	       	 
	       	 //光标切入事件，移除提醒
	       	 $('#pwd').focus(function(){
	       		 $(this).siblings('span').html('');
	       	 })   
	       	 $('#pwd1').focus(function(){
	       		 $(this).siblings('span').html('');
	       	 }) 
             $('#email').focus(function(){
            	 $(this).siblings('span').html('')
             }) 

             $('#company_name').focus(function(){
            	 $(this).siblings('span').html('') 
             }) 
             
             $('#user_name').focus(function(){
            	 $(this).siblings('span').html('') 
             }) 
	       	 
	       	 
	       	 
                /**
		         *供应商注册
		         */
	             $('#sub_btn').click(function(){
//	                 var rightList=$("#rightList option");
//	                 if(rightList.length==0){
//	                     $("#rightList").focus();
//	                     $("#selectOne").show();
//	                     return "";
//	                 }
//	                 var processList = '';
//	                 for(var y = 0 ; y < rightList.length; y++){
//	                     $(rightList[y]).prop("selected","selected");
//	                     var process = $('#rightList').find('option').eq(y).text();
//	                     if(y == 0){
//	                       processList = process;	
//	                     }else{
//	                       processList += ","+process;
//	                     }          
//	                 }
//	                 $('#mainProcess').val(processList);
	                 var processFlag = false;
	            	 //工艺必选
	                 $('#select_process').find('option').each(function(){
	                	 if($(this).is(":checked")){
	                		 processFlag = true;
	                	 }
	                 })
	            	 if(!processFlag){
	            		 easyDialog.open({
	  			    		  container : {
	  			    		    content : '请选择工艺'
	  			    		  },
	  			    		  overlay : false,
	  			    		  autoClose : 1000   			    		  
	  			    	});
	            		 return false;
	            	 }
	            	 
	    	       	 $('#company_name').blur();
	    	         $('#pwd').blur(); 
	          	     $('#email').blur();
	          	     $('#pwd1').blur();
	          	     $('#user_name').blur();
	                 
	    	       	 if(!(e_judgment && p_judgment && dp_judgment && com_judgment && user_judgment)){
	    	       		return false; 
	    	       	 }
	    	       	
	    	       	var factoryType = 0;   //供应商和采购商
	    	       	 
	    	       	$('#sub_btn').css({'background-color':'#ddd'}).attr("disabled",true);
	    	       	 
	    	       	$.ajax({
	    				type : "post",
	    				datatype : "json",
	    				url : "/account/supplier_register.do",
	    				data : {
	    				  "factoryName":$('#company_name').val(),
	    				  "userName":$('#user_name').val(),
	    				  "pwd":$('#pwd').val(),
	  					  "email":$('#email').val(),
//	  					  "mainProcess":$('#mainProcess').val(),
	  					  "factoryType" : factoryType
	    				},
	    				success : function(data) {
	    				  	$('#sub_btn').css({'background-color':'#006dcc'}).removeAttr('disabled');
	    					if(data.state==0){
	    						window.location = "/m-zh/welcome.html";
	    						
	    					   //同步工厂到内部报价
	   				    	   $.post("/sendPort/syncFactoryInfo.do",	
	   						    		 {
	   				    		          factoryId:data.data
	   						    	     },
	   								    function(result){
	   						    	     	 
	   						       })
	    					}
	    					
	    				},
	    				error : function() {
	    					$('#sub_btn').css({'background-color':'#006dcc'}).removeAttr('disabled');
	    					easyDialog.open({
	  			    		  container : {
	  			    		    header : 'Prompt message',
	  			    		    content : '注册失败'
	  			    		  },
	  			    		  overlay : false,
	  			    		  autoClose : 1000   			    		  
	  			    		});
	    				}
	    	       	})	
	             }) 
	             
	             
	             
	             
	             
	             
	             
	            //采购商注册             
	           	//验证公司名是否为空
	       	$('#buyer_companyName').blur(function(){     
		       		$('#buyer_companyName').next().css({"display":"none"});
		       		 var companyName = $('#buyer_companyName').val();
		       		 if(!(companyName.trim() == '')){
		       			$('#buyer_companyName').addClass('success');
		       			com_judgment = true; 
		       		 }else{
		       			$('#buyer_companyName').next().addClass('error').text("请输入中文名公司名称").fadeIn();
		       			com_judgment = false; 
		       		 }		       		 
		       		 return false;
		       	 });
	       	//验证公司名是否为空
	       	$('#buyer_name').blur(function(){     
	       		$('#buyer_name').next().css({"display":"none"});
	       		var userName = $('#buyer_name').val();
	       		if(!(userName.trim() == '')){
	       		    $('#buyer_name').addClass('success');
	       			user_judgment = true; 
	       		}else{
	       			$('#buyer_name').next().addClass('error').text("名称不能为空").fadeIn();
	       			user_judgment = false; 
	       		}		       		 
	       		return false;
	       	});
	       	 
	       	 //验证密码是否合理
	       	 $('#buyer_password').blur(function(){     
	       		$('#buyer_password').next().css({"display":"none"});
	       		 var pwd = $('#buyer_password').val();
	       		 var psdReg = /^[0-9a-zA-Z_]{6,15}$/;//密码正则
	       			if (!psdReg.test(pwd)) {
	       				$('#buyer_password').next().addClass('error').text("6-15位数字或者字母").fadeIn();
	       			    p_judgment = false;
	       				return false;
	       			} 
	       		 $('#buyer_password').addClass('success');
	       	     p_judgment = true;
	       		 return false;
	       	 });
	       	 
	       	 
	       	 //验证两次密码是否相同
	       	  $('#rePassword').blur(function(){     
		        var pwd = $('#buyer_password').val();
		       	var pwd1 = $('#rePassword').val();  	       		  
	       		$('#rePassword').next().css({"display":"none"});      			       		
	       		if (pwd != pwd1) {
	       			    $('#rePassword').next().addClass('error').text("两次密码不一致").fadeIn();
	       			    dp_judgment = false;
	       				return false;
	       			} 
	       		
	       		var psdReg = /^[0-9a-zA-Z_]{6,15}$/;//密码正则
   			if (!psdReg.test(pwd1)) {
   				$('#rePassword').next().addClass('error').text("6-15位数字或者字母").fadeIn();
   			    p_judgment = false;
   				return false;
   			} 	 
	       		
	       		 $('#rePassword').addClass('success');
	       		 dp_judgment = true;
	       		 return false;
	       	 });
	       	 
	       	 
	       	 
	       	 
	       	 //光标切入事件，移除提醒
	       	 $('#buyer_password').focus(function(){
	       		 $('#buyer_password').next().removeClass('error');
	       		 $('#buyer_password').next().css({"display":"none"});
	       	 })   
	       	 $('#rePassword').focus(function(){
	       		 $('#rePassword').next().removeClass('error');
	       		 $('#rePassword').next().css({"display":"none"});
	       	 })   
	       	  	
	            /**
	             *光标切入事件
	             */
	             $('#buyer_email').focus(function(){
	            	 $('#buyer_email').next().css({"display":"none"}); 
	             }) 

	             $('#buyer_companyName').focus(function(){
	            	 $('#buyer_companyName').next().css({"display":"none"}); 
	             }) 
	             
	             $('#buyer_name').focus(function(){
	            	 $('#buyer_name').next().css({"display":"none"}); 
	             }) 
	             
	           
	           /**
	            *验证邮箱是否合法
	            */
	            $('#buyer_email').blur(function(){
	             $('#buyer_email').next().css({"display":"none"});
	           	 var email = $('#buyer_email').val(); 
	           	 if(email == null || email == ''){
	           		 $('#buyer_email').next().addClass('error'); 
	           		 $('#buyer_email').next().text("邮箱不能为空").fadeIn();
	           		 e_judgment = false;
	           		 return false;
	           	 }
	   	       	   //验证邮箱是否合法
	   	    	   var szReg=/^(\w-*\.*)+@(\w-?)+(\.\w{2,})+/;     	    	   
	   	    	   if(!(email == null || email.length==0)){
	   	    		   var bChk = szReg.test(email);
	   	   			
	   	   			if(!bChk){
	   	   			    $('#buyer_email').next().addClass('error'); 
	           		    $('#buyer_email').next().text("邮箱格式不正确").fadeIn();
	   	   				e_judgment = false;
	   	   				return false;
	   	   			}
	   	   		   }         	 	           	 
	   	    	   
	           	 $.post("/account/verifyfEmail.do",
      	            	  { 
        		      "email" : email
      	            	  },
      	        	   function(result){
      	        	     if(result.state == 0){
      	        			$('#buyer_email').next().css({"display":"none"});
	     					$('#buyer_email').addClass('success');
	     	                e_judgment = true;
      	        	     }else{
	      	      	    	 $('#buyer_email').next().addClass('error');
	     			    	 $('#buyer_email').next().text("邮箱已存在").fadeIn();
	     			    	 e_judgment = false;
      	        	     }
      	        	  }); 
	             })         
	             
	             
	             
	             
	             
	             
	             
	             
	             
	             
	             
	             
	             
	             
	             
	             
	             
	             
	             
	             
	             
	             
	             
	             
	             
	             
	             
	             /**
		            *采购商注册
		            */
	             $('#sub_btn1').click(function(){	              	                 
	                 
	    	       	 $('#buyer_companyName').blur();
	    	         $('#buyer_password').blur(); 
	          	     $('#buyer_email').blur();
	          	     $('#rePassword').blur();
	          	     $('#buyer_name').blur();
	                 
	    	       	 if(!(e_judgment && p_judgment && dp_judgment && com_judgment && user_judgment)){
	    	       		return false; 
	    	       	 }	    	       	
	    	       	var factoryType = 2;   //采购商
	    	       	 
	    	       	$('#sub_btn1').css({'background-color':'#ddd'}).attr("disabled",true);
	    	       	 
	    	       	$.ajax({
	    				type : "post",
	    				datatype : "json",
	    				url : "/fastermake/account/supplier_register.do",
	    				data : {
	    				  "factoryName":$('#buyer_companyName').val(),
	    				  "userName":$('#buyer_name').val(),
	    				  "pwd":$('#buyer_password').val(),
	  					  "email":$('#buyer_email').val(),
	  					  "state" : $('#select_province').find("option:selected").text(),
	  					  "country" : $('#select_country').find("option:selected").text(),
	  					  "city" : $('#select_city').find("option:selected").text(),
//	  					  "tel" : $('#buyer_tel').val(),
	  					  "tel" : $('#buyer_phone').val(),
	  					  "factoryType" : factoryType 
	    				},
	    				success : function(data) {
	    				  	$('#sub_btn1').css({'background-color':'#006dcc'}).removeAttr('disabled');
	    					if(data.state==0){
	    						window.location = "/fastermake/zh/welcome.html"
	    					}
	    					
	    				},
	    				error : function() {
	    					$('#sub_btn1').css({'background-color':'#006dcc'}).removeAttr('disabled');
	    					easyDialog.open({
	  			    		  container : {
	  			    		    header : 'Prompt message',
	  			    		    content : '注册失败'
	  			    		  },
	  			    		  overlay : false,
	  			    		  autoClose : 1000   			    		  
	  			    		});
	    				}
	    	       	})	
	             }) 
	             	             
        })
        
      
        
        
        //获取更改的国家
   		function changeCountry(){
   	        var t = document.getElementById("country"); 
   		    var country = t.options[t.selectedIndex].value;
   		    return country;
   		}
   		//获取更改的省/州
   		function changeState(){
   	        var t = document.getElementById("state"); 
   		    var state = t.options[t.selectedIndex].value;
   		    $('#hidden_state').val(state);
   		    $('#saveAddress').css({backgroundColor:"#006dcc"});
   		    if(state == '-1'){
    		  $('#saveAddress').css({backgroundColor:"#888"});
 	        }
   		    return state;
   		}
        
        
        
        
        
        
              
        
           //跳转主页
           function login(){
        		//检查表单数据的正确性
        		//将表单数据发送到服务器
        		//利用Callback处理返回结果
        		//如果成功就跳转到主页
        		//如果失败显示错误消息
        		var loginEmail = $('#loginEmail').val();
        		var pwd = $('#u_pwd').val();
                
        	    if(loginEmail == null || loginEmail == '' || loginEmail == undefined){
        	    	easyDialog.open({
			    		  container : {
			    		    header : 'Prompt message',
			    		    content : '邮箱不能为空'
			    		  },
			    		  overlay : false,
			    		  autoClose : 1000   			    		  
			    		});
        	    	return false;
        	    }
        	    if(pwd == null || pwd == '' || pwd == undefined){
        	    	easyDialog.open({
			    		  container : {
			    		    header : 'Prompt message',
			    		    content : '密码不能为空'
			    		  },
			    		  overlay : false,
			    		  autoClose : 1000   			    		  
			    		});
        	    	return false;
        	    }
        		$('#login').css({'background-color':'#ddd'}).attr("disabled",true);       		
        		$.ajax({
    				type : "post",
    				datatype : "json",
    				url : "/account/showInquiryWechat.do",
    				data : {
    				  "loginEmail":loginEmail,
  					  "pwd":pwd
    				},
    				success : function(data) {
    					$('#login').css({'background-color':'#006dcc'}).removeAttr('disabled');
    					if(data.state==0){
    						window.location = data.data;
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
    					$('#login').css({'background-color':'#006dcc'}).removeAttr('disabled');
    					easyDialog.open({
  			    		  container : {
  			    		    header : 'Prompt message',
  			    		    content : result.message
  			    		  },
  			    		  overlay : false,
  			    		  autoClose : 1000   			    		  
  			    		});
    				}
    			});
            }
         
      //找回密码弹窗   
      function recover_pwd(){
    	  $('#recover_pwd_div').show(); 
    	  var email = $('#loginEmail').val(); 
      	  $('#login_email').val(email);
      }   
      
      function dispose(){
    	  $('#recover_pwd_div').hide();
      }
      
      //找回密码
      function recover(){
    	var email = $('#login_email').val();
    	
	   if(email == null || email == ""){
		   
		   easyDialog.open({
	    		  container : {
	    		    header : 'Prompt message',
	    		    content : ' * 邮箱不能为空'
	    		  },
	    		  autoClose : 2000
	    		});
			return false; 
	   }
		 //验证邮箱是否合法
 	   var szReg=/^(\w-*\.*)+@(\w-?)+(\.\w{2,})+/;     	    	   
 	   if(!(email == null || email.length==0)){
 		   var bChk = szReg.test(email);
			
			if(!bChk){
				easyDialog.open({
		    		  container : {
		    		    header : 'Prompt message',
		    		    content : ' * 邮箱格式不正确'
		    		  },
		    		  autoClose : 1000
		    		});
				return false;
			}
		   }  
    	
    	$('#send_mail_div').show(); 
      }   