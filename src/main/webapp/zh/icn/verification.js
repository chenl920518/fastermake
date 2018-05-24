function doValidForm(ob) {
	  
	    var flag = false;
	    $(ob).find('.ff4').text('');
	    
		$(ob).find("input[require]:visible").blur(function(){
			
			$(this).next().removeClass('ff4').css({
				"display" : "none"
			});
			
			var temp = $(this).val();
			var field =  $(this).attr('field');
			
			if (temp.trim() != '') {
				$(this).addClass('success');
				flag = true;
     			
			} else {
				if($(this).attr('place')){
				$(this).attr('placeholder',$(this).attr('place'));
			}
				$(this).next().addClass('ff4').text(field+'不能为空').fadeIn();		
				flag = false;
			}
		
			
		})
		
		
		$(ob).find("textarea[require]:visible").blur(function(){
			
			$(this).next().removeClass('ff4').css({
				"display" : "none"
			});
			
			var temp = $(this).val();
			var field =  $(this).attr('field');
			
			if (temp.trim() != '') {
				$(this).addClass('success');
				flag = true;
     			
			} else {
				if($(this).attr('place')){
				$(this).attr('placeholder',$(this).attr('place'));
			}
				$(this).next().addClass('ff4').text(field+'不能为空').fadeIn();		
				flag = false;
			}
		
			
		})
		
		
		$(ob).find("textarea[requireOrtext]:visible").blur(function(){
			
			$('#equipmentWarn').removeClass('ff4').css({
				"display" : "none"
			});
			
			var temp = $(this).val();
			var field =  $(this).attr('field');
			
			if (temp.trim() != '') {
				$(this).addClass('success');
				flag = true;
     			
			} else {
				
				$('#equipmentWarn').addClass('ff4').text(field+'不能为空').fadeIn();		
				flag = false;
			}
		
			
		})
		
		
		
		$(ob).find("input[requiredate]:visible").blur(function(){
			
			
			var obj = $(this);
			 $(".datetimepicker-days").click(function(){
				 
				  obj.parent().next().css({
						"display" : "none"
					}).removeClass('ff4');
				  
				 return ;
			 })
			
			
			$(this).parent().next().css({
				"display" : "none"
			}).removeClass('ff4');
			
			var temp = $(this).val();
			var field =  $(this).attr('field');
			 var a = /^(\d{4})-(\d{2})-(\d{2})$/
			
				 
		    if (!temp||temp.trim()==''){
				if($(this).attr('place')){
				$(this).attr('placeholder',$(this).attr('place'));
			}
				$(this).parent().next().addClass('ff4').text(field+'不能为空').show();		
				flag = false;
			}else if(!a.test(temp)){
				if($(this).attr('place')){
					$(this).attr('placeholder',$(this).attr('place'));
				}
					$(this).parent().next().addClass('ff4').text(field+'不符合日期格式').show();		
					flag = false;
			}else{
				$(this).addClass('success');
				flag = true;
				
			}
		    
		    
		    
		
		})
		
	
		
		
		$(ob).find("select[require]:visible").blur(function(){
			
			$(this).next().removeClass('ff4').css({
				"display" : "none"
			});
			
			var temp = $(this).val();
			var field =  $(this).attr('field');
			
			if (temp.trim() != '') {
				$(this).addClass('success');
				flag = true;
     			
			} else {
				$(this).next().addClass('ff4').text(field+'必选').fadeIn();		
				flag = false;
			}
			return false;
			
		})
		
		
		
		$(ob).find("select[requireOrtext]:visible").blur(function(){
			
			$("#equipmentWarn").removeClass('ff4').css({
				"display" : "none"
			});
			
			var temp = $(this).val();
			var field =  $(this).attr('field');
			
			if (temp.trim() != '') {
				$(this).addClass('success');
				flag = true;
     			
			} else if(!$(this).next().children().prop('checked')){
             $("#equipmentWarn").addClass('ff4').text(field+'必选').fadeIn();		
				flag = false;
				
			 }
				
			return false;
			
		})
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		$(ob).find("input[invitationRequireNum ]:visible").blur(function(){
			
			$(this).parent().children().last().removeClass('ff4-2').css({
				"display" : "none"
			});
			
			var temp = $(this).val();
			var field =  $(this).attr('field');
			
			if (temp == '') {
				flag = false;
				$(this).parent().children().last().addClass('ff4-2').text(field+"不能为空").fadeIn();
				return false;

			}

			else if (!$.isNumeric(temp)) {
				$(this).parent().children().last().addClass('ff4-2').text(field+"必须为数字").fadeIn();
				flag = false;
				return false;
			}

			$(this).addClass('success');
			flag = true;
			return false;
		})
		
		
	   $(ob).find("input[quicklyRequireNum ]:visible").blur(function(){
			
			$(this).parent().parent().children().last().removeClass('ff4').css({
				"display" : "none"
			});
			
			var temp = $(this).val();
			var field =  $(this).attr('field');
			
			if (temp == '') {
				flag = false;
				$(this).parent().parent().children().last().addClass('ff4').text(field+"不能为空").fadeIn();
				return false;

			}

			else if (!$.isNumeric(temp)) {
				$(this).parent().parent().children().last().addClass('ff4').text(field+"必须为数字").fadeIn();
				flag = false;
				return false;
			}

			$(this).addClass('success');
			flag = true;
			return false;
		})
		
		
		
		
		
		
		
		
		
		
		
		
	return flag;
	
}

function doFocusForm(ob) {
	
	
	$(ob).find("input[require]:visible ").focus(function() {
	
		$(this).next().removeClass('ff4').css({
			"display" : "none"
		});
		$(this).attr('placeholder','');
	})
	
	$(ob).find("textarea[require]:visible ").focus(function() {
	
		$(this).next().removeClass('ff4').css({
			"display" : "none"
		});
		$(this).attr('placeholder','');
	})
	
	$(ob).find("input[requiredate]:visible").focus(function() {
		
			 
		$(this).parent().next().removeClass('ff4').css({
			"display" : "none"
		});
		$(this).attr('placeholder','');
	})
	
	
	
	
	$(ob).find("select[require]:visible").focus(function(){
		$(this).next().css({
			"display" : "none"
		});
	
		
		
	})
	
	
	$(ob).find("select[requireOrtext]:visible").focus(function(){
		$('#equipmentWarn').css({
			"display" : "none"
		});
	
	})
	
	
	
	$(ob).find("textarea[requireOrtext]:visible").focus(function(){
		$('#equipmentWarn').css({
			"display" : "none"
		});
	
	})
	
	
	  
	$(ob).find("input[invitationRequireNum]:visible").focus(function() {
	
		$(this).parent().children().last().css({
			"display" : "none"
		});
		$(this).attr('placeholder','');
	})
   
	
	
	
	
	
	$(ob).find("input[quicklyRequireNum]:visible").focus(function() {
	
		$(this).parent().parent().children().last().css({
			"display" : "none"
		});
		$(this).attr('placeholder','');
	})
	
}

function saveVerification(obj){
     
	var flag = true;
	
	$(obj).find('.ff4').removeClass('ff4').text('')
	
	$(obj).find("input[require]:visible").blur();

	$(obj).find("select[require]:visible").blur();
	
	$(obj).find("select[requireOrtext]:visible").blur();
	
	$(obj).find("textarea[require]:visible").blur();
	
	$(obj).find("textarea[requireOrtext]:visible").blur();
	
	$(obj).find("input[invitationRequireNum]:visible").blur();
	
	$(obj).find("input[requiredate]:visible").blur();
	
	
	if ($(obj).find('.ff4').length > 0) {
		flag=false;
	}
	
	return flag;
	
}


function sum(obj){
	
	var partNum = obj;

	var len = partNum.length-1;
	
	
	for(var i=0;i<len;i++){
		var j = i+1;
		partNum.eq(i).parent().html('<i class="partNum"></i>零件信息('+j+'/'+len+')');
	}
	
	
}


function getFilesize(file) { 

    fileSize = file.files[0].size / 1024;
    if (fileSize > 30720) {
    	return false;
    }else{
    	return true;
    }  

}

// 方法 增添dayNumber天（整形），date：如果没传就使用今天（日期型）

function addDay(dayNumber, date) {
    date = date ? date : new Date();
    var ms = dayNumber * (1000 * 60 * 60 * 24)
    var newDate = new Date(date.getTime() + ms);
    return newDate;
}
 



