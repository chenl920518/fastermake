$(function() {

	
	
	var factory = window.location.search;

	if (factory != null&& factory != '') {
		var factoryId = factory.substr(11);
		$('#inviteFactory').html('<option value="'+factoryId+'">已选择一个供应商</option>')
		$('#inviteFactory').val(factoryId);
		$('#inviteFactory').prop('disabled','disabled');

	}
	
	
	
	
	
	
	doValidForm($('.product_invitation'));

	doFocusForm($('.product_invitation'));
	
	
	$('.product_invitation').find('input[name=quoteWay]').each(function(){
		
		if($(this).val()==1){
			$(this).prop('checked',true)
		}
		
	})
		
	
	$('.inps').click(function(){
		
		$(this).find('input').prop('checked',true)
		
	})
	
	$('.pics').click(function(){
		$(this).prev().find('input').prop('checked',true)
	})
	
	
	$('#bottom').find('input[name=quoteLocation]').each(function(){
		
		if($(this).val()==0){
			$(this).prop('checked',true)
		}
		
	})
	
	
	
	var datetime = addDay(15,null)
	var datetime2 = addDay(30,null)
	
	var dateString = datetime.toLocaleString()
	var dateString2 = datetime2.toLocaleString()
	
	var quoteEndDate = datetime.getFullYear()+'-'+appendZero(datetime.getMonth()+1)+'-'+appendZero(datetime.getDate())
	var deliveryDate = datetime2.getFullYear()+'-'+appendZero(datetime2.getMonth()+1)+'-'+appendZero(datetime2.getDate())
	
	
//    var quoteEndDate = dateString.substr(0,4)+'-'+dateString.substr(5,2)+'-'+dateString.substr(8,2);
//
//	var deliveryDate = dateString2.substr(0,4)+'-'+appendZero(dateString2.substr(5,2))+'-'+appendZero(dateString2.substr(8,2);
	
	
	$('#quoteEndDate').val(quoteEndDate)
	$('#deliveryDate').val(deliveryDate)
	
	
	
	   $("body").click(function(e){
		   
		   $('.product_invitation').find('input[name=quoteWay][value=1]').each(function(){
			   
			   if($(this).prop('checked')){
				   
//			        if($('#mainFactoryForeach > div').length>0){
//						$('#factoryList').show()
//					}else{
//						alert('邀请报价工厂列表为空')
//                        $('.product_invitation').find('input[name=quoteWay][value=0]').prop('checked',true)
//					}
			        
			        $('#factoryList').show()
			        
			   }else{
					$('#factoryList').hide()
				
				}
			
				
			})
			
			  if(e.target.id.indexOf("mainFactory") == -1){
				   
			      if(e.target.className=='ckbx'){
			    	  $("#mainFactoryForeach").show();
			      }
				  else{
					  $("#mainFactoryForeach").hide(); 
				  }
		   }
		   
		   
		   if(e.target.id.indexOf("qualificationName") == -1){
			 
			      if(e.target.name=='qualificationck'){
			    	  $("#qualificationForeach").show();
			      }
				  else{
					  $("#qualificationForeach").hide(); 
				  }
		   }
		   
		   
		   
			
			
			 
		   
			
	   })
	   
	   
       ajaxFactoryList()          
       
	   ajaxSelect()
 
	
	
		
	
	

	$("#saveButton").click(function() {

		$('.product_invitation').find('.ff4').removeClass('ff4').text('')
		$('.product_invitation').find("input[require]:visible").blur();

		$('.product_invitation').find("select[require]:visible").blur();
		
		$('.product_invitation').find("input[invitationRequireNum]:visible").blur();
		
		$('.product_invitation').find("input[requiredate]:visible").blur();
		
		checkDelivery()
		var flag = false;
		/*
		 * 
		
		if($('#factoryList').is(':visible')){
			if($('#mainFactory').val()==''){
				$('#factoryListV').addClass('ff4').text('邀请报价工厂不能为空')
			}
			
		}
		 */
		
		if ($('.ff4').length > 0) {
			return false;
		}
         
		$('#saveButton').css({'background-color':'#ddd'}).attr('disabled',true)
		
	//	$('.weui_mask_new').show();
		
		
		
		var data = {};
		
		
		
		$('.product_invitation').find('input[type=text],input[type=hidden],select,textarea').each(function() {
			
				data[$(this).attr('name')] = $(this).val()
			
			
		})
		
		
		
		
		$('.product_invitation').find('input[type=radio]').each(function() {
		if ($(this).prop('checked')) {
				data[$(this).attr('name')] = $(this).val()
				
				
			}
		})
		
	
		var obj = JSON.stringify(data);// 对象转成字符串

		$.ajax({
			url : "/quoteInfo/updateQuoteInfo.do",
			type : "post",
			async: false,
			data : {
				'param' : obj
			},// 参数
			datatype : "json",
			success : function(data) {
				$('#saveButton').css({'background-color':'#006dcc'}).removeAttr('disabled');
				if(data.state==0){
					//$('.weui_mask_new').hide();
					var url = data.data;
					
					window.location = url;
					
					
				}else if(data.state==2){
					
					easyDialog.open({
						container : {
							header : '  Prompt message',
							content :  data.message
						},
						callback :function(){
							window.location= data.data;
						},
						overlay : false,
						autoClose : 1500
					});
					
					
				}

			}

		})
		
		
	})
	
	

})

function deleteProduct(obj) {

	$(obj).parent().remove();
	sum($('.partNum'))

	if ($('.partNum').length < 21) {

		$('.addProduct').show();

	}

	return false;

}

function addProduct() {

	
	var addHtml = $('#tempAdd .addProducts');
	addHtml.clone().insertBefore($('#bottom'));
	
	var tempdiv = $('#bottom').prev();
	
	

	
	
	doValidForm(tempdiv)
	doFocusForm(tempdiv)
	
	
	
	
	
	
	

	if ($('.partNum').length == 21) {
		$('.addProduct').hide()
	}

	

	sum($('.partNum'))
		
	$('.form_date').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 4,
        forceParse: 0
    });	
  	
	 
	

	return false;

}

function uploadProduct(obj) {

	$(obj).next()[0].click();
	return false;

}

function show_drawingName(obj) {
	  
	/*
	 * 清除提醒（10M）
	 */
    $(obj).next().text('');
    /*
     * 清除文件名样式
     */
    $(obj).parent().next().hide().html('')
    
  
    var fileUploadForm = $(obj).parents('form');
    
    fileUploadForm.find('input[name="fileName"]').val('')
    fileUploadForm.find('input[name="filePath"]').val('')
    
    
    if(!getFilesize(obj)){
		$(obj).next().text('文件上传最大10M');
		return false;
	}
    
	  

	var path = $(obj).val();

	sppath = path.split("\\");

	var drawingName = sppath[sppath.length - 1];

	if (drawingName == null || drawingName == '' || drawingName == undefined) {
		return false;
	} else {

		autTime();
		$('#show_upload_dialog').show();

	}

	var fileUploadForm = $(obj).parents('form');
	
    
	
	// 先上传后获取上传文件路径

	fileUploadForm.find('input[name="fileName"]').val(drawingName)

	fileUploadForm.ajaxSubmit({
		type : "post",
		url : "/upload/uploadFileAndChangeName.do",
		dataType : "text",
		async :false,
		success : function(str) {
			var result = eval('(' + str + ')');
			if (result.state == 0) {
				
				fileUploadForm.find('input[name="filePath"]').val(result.data);
				
				$(obj).parent().next().show().text(drawingName).append('<span  onclick="deletefile(this)"><em class="iconfont">&#xe643;</em></span>');
				} else {
				easyDialog.open({
					container : {
						header : '  Prompt message',
						content : '  Upload failed '
					},
					overlay : false,
					autoClose : 1000
				});
				$('#show_upload_dialog').hide();
				

			}
		},
		error : function() {
			easyDialog.open({
				container : {
					header : '  Prompt message',
					content : '  Upload failed '
				},
				overlay : false,
				autoClose : 1000
			});
			$('#show_upload_dialog').hide();
		}
	});

}








function deletefile(obj) {

	

	var path = $(obj).parents('form').find('input[name="filePath"]').val();
	
	var temp = $(obj).parent()
	$(obj).parent().html('').hide();
	
	
	
	

	$.ajax({
		url : "/upload/deleteFile.do",
		type : "post",
		data : {
			'filePath' : path
		},
		datatype : "json",
		success : function(data) {
			
		
			temp.parents('form').find('input[name="fileName"]').val("");
			temp.parents('form').find('input[name="filePath"]').val("");

		}

	})

}

function showUploadEnter(obj) {

	if($(obj).val()==2&&$(obj).is(':checked')){
	$('#confidentialityFile').show();
	}else{
		$('#confidentialityFile').hide().find('span').text('');
		$(obj).parents('form').find('input[name="fileName"]').val('')
		$(obj).parents('form').find('input[name="filePath"]').val('')
		$(obj).parent().parent().children(':last').text('').hide()
		
		
	}
	

}




function showTree(obj){
	var obj = $("#treeviewForRfq");
	if(obj.css("display")=="none"){
		obj.show();
		getMainProcess()
	}else{
		obj.hide();
		var mainCategoryText= $('#mainCategoryText').text()
		if(!mainCategoryText||mainCategoryText.trim()==''){
			$("#mainCategoryAuto").prop('checked',true)
		}
		
		
	}
	$("#treeviewForRfq").click(function(event) {
		return false;
	});
}


function cleanData(){
	$('#mainCategoryText').text(null);
	$('#mainCategoryH').val(null)
	
}



function addQuantityList(obj){
	
	var addHtml= '<div class="form-group">'+
	
	'<label for="" class="col-sm-2 control-label"><span></span></label>'+
	
	
		'<div class="col-sm-10 colsm10 colsm102 ff5div">'+
			'<input type="text" class="form-control" name="quantityList"'+
				'place="0" placeholder="0" field="报价数量" invitationRequireNum>'+
			'<select class="form-control" name="quantityUnit">'+
				'<option>件</option>'+
				'<option>吨</option>'+
			'</select> <span class="s0 deleteQuantityList" onclick="deleteQuantityList(this)" style="magin-left:600px">- 删除报价数量</span>  <span class="ff5" ></span></div>';
		
	
	$(obj).parent().parent().after(addHtml)
	
	doValidForm($('.product_invitation'));

	doFocusForm($('.product_invitation'));
	
	
	
}

function deleteQuantityList(obj){
	
	$(obj).parent().parent().remove();
	
	
}


function ajaxFactoryList(){
	
	
	$.ajax({
		url : "/quoteInfo/selectFactoryList.do",
		type : "get",
		async: false,
		success : function(result) {
			if(result.state==0){
	         if(result.data&&result.data.length>0){
	        	 
	        	 var addFactory = ''
	        	 for(var i=0;i<result.data.length;i++){
	        		 var temp = result.data[i]
	        		 
	        		 addFactory+='<div class="checkbox">'
	        			       +'<label>'
	        			       +'<input type="checkbox" onclick="checkValue()" class="ckbx" name="inviteFactory" value="'+(temp.factory_id?temp.factory_id:0)+'">'+(temp.factory_name?temp.factory_name:0)+''
	        			       +' </label>'
	        		           +'</div>'
				        
	        		 
	        	 }
	        	 $('#mainFactoryForeach').append(addFactory)
	        	 
	        	 
	        	 
	        	 
	         }
			
			}
	
		}
	
	
	
	
})
	
	
	
	
	
}

function checkValue(){
	
	var mainFactory = ''
	var inviteFactory = ''
	
	$('#mainFactory').val('')
	$('#inviteFactory').val('')
	
	$('#mainFactoryForeach').find('input[type="checkbox"]').each(function(){

		if($(this).prop('checked')){
			if(mainFactory==''&&inviteFactory==''){
				mainFactory = $(this).parent().text()
				inviteFactory = $(this).val()
			}
			
		else{
			mainFactory += ','+$(this).parent().text()
			inviteFactory += ','+$(this).val()
		}
		}
	})
	
	$('#mainFactory').val(mainFactory)
	$('#inviteFactory').val(inviteFactory)
	
}


function checkqcValue(){

	    var name = ''
		$('#qualificationName').val('')

		
		$('#qualificationForeach').find('input[type="checkbox"]').each(function(){

			if($(this).prop('checked')){
				if(name==''){
					name = $(this).val()
					
				}
				
			else{
				name += ','+$(this).val()
			
			}
			}
		})
		
		$('#qualificationName').val(name)

}


function showCity(obj){
	
	if($(obj).val()=='运送到目的地价'){
		$('#deliveryAddress').show()
		
		doValidForm($('#deliveryAddress'));
		doFocusForm($('#deliveryAddress'));
		
		
	}else{
		$('#deliveryAddress').hide()
		$('#city').val('')
	}
	
	
}

	
function checkDelivery(){
	if($('#quoteEndDate').val()!=''&&$('#deliveryDate').val()!=''){
		if($('#quoteEndDate').val()>$('#deliveryDate').val()){
			$('#deliveryDate').parent().next().addClass('ff4').text('交货日期应该大于报价截止时间').show()
			
		}
	}
}	

function ajaxSelect(){
	

	$.ajax({
		url : "/quoteInfo/selectDetail.do",
		type : "post",
		async: false,
		success : function(data) {
			if(data.state==0){
	           var quoteInfo = data.data.quoteInfo
	           var libraryFactoryName = data.data.libraryFactoryName
	           var libraryFactoryId = quoteInfo.libraryFactoryId
	           
	          
	        	   
	           var quoteWay = quoteInfo.quoteWay
	           var inviteFactory = quoteInfo.inviteFactory
	           var quoteEndDate = quoteInfo.quoteEndDate
	           var deliveryDate =quoteInfo.deliveryDate
	           var quoteFreightTerm =quoteInfo.quoteFreightTerm
	           var quoteRemark =quoteInfo.quoteRemark
	           var quoteLocation =quoteInfo.quoteLocation
	           var staffNumber =quoteInfo.staffNumber
	           var qualification =quoteInfo.qualification
	           var city = quoteInfo.city
	          
	           
	           
	           
	           
	           
        		   $('.moreInfo').find('input[name=quoteWay]').each(function(){
        			   if($(this).val()==quoteWay){
        				   $(this).prop('checked',true)
        				   
        			   }
        			  if(quoteWay==0){
        				  $('#factoryList').hide()
        			  }
        			   
        			   
        		   })
        		   
        	   
	           
	           if(libraryFactoryId){
	        	   
	        	   var libraryFactoryList = '<div class="checkbox">'
				       +'<label>'
				       +'<input type="checkbox" onclick="checkValue()" class="ckbx" checked name="inviteFactory" value="'+(libraryFactoryId?libraryFactoryId:0)+'">'+(libraryFactoryName?libraryFactoryName:"")+''
				       +' </label>'
			           +'</div>';
		           $('#mainFactoryForeach').append(libraryFactoryList);
		           
	        	   
	           }
	         
	           
	           
	           
	           
	           
	           if(inviteFactory){
	        	   $('#inviteFactory').val(inviteFactory)
	        	   
	        	   var inviteFactorys = inviteFactory.split(',')
	        	   
	        	   if(inviteFactorys&&inviteFactorys.length>0){
	        		   for(var i=0;i<inviteFactorys.length;i++){
	        			   $('#mainFactoryForeach').find('input[name=inviteFactory]').each(function(){
	        				 
	        				   if($(this).val()==inviteFactorys[i]){
	        					   $(this).prop('checked',true)
	        				   }
	        				    
	        			   })
	        			
	        		   }
	        		  
	        	   }
	        	   
	           }
	           checkValue()
	           if(quoteEndDate){
	        	   $('#quoteEndDate').val(quoteEndDate)
	           }
	           if(deliveryDate){
	        	   $('#deliveryDate').val(deliveryDate)
	           }
	           
	           $('#quoteFreightTerm').val(quoteFreightTerm)
	           if(city){
	        	  
	        	   $('#deliveryAddress').show()
	        	  
	        	    $('#city').val(city)
	           }
	           
	           $('#quoteRemark').val(quoteRemark)
	           
	           $('#bottom').find('input[name=quoteLocation]').each(function(){
	        	   if($(this).val()==quoteLocation){
    				   $(this).prop('checked',true)
    				   
    			   }
	 
	           })
	           $('#staffNumber').val(staffNumber)
	           
	           if(qualification){
	   
	        	   qualifications = qualification.split(',')
	        	   if(qualifications&&qualifications.length>0){
	        		   for(var j=0;j<qualifications.length;j++){
	        			   
	        			   $('#qualificationForeach').find('input[type=checkbox]').each(function(){
	        				   if($(this).val()==qualifications[j]){
	        					   $(this).prop('checked',true)
	        				   }
	        				    
	        			   })
	        			  
	        		   }
	        		   checkqcValue()
	        	   }
	        	   
	        	  
	        	   
	           }
	           
	           
	           
	           
			}
		}
	})
}

function appendZero(s)
{return ("00"+ s).substr((s+"").length);}

	