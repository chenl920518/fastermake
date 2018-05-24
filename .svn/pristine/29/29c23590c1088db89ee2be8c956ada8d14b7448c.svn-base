$(function() {
	
	
	$('.firstNext').css({'background-color':'#006dcc'}).removeAttr('disabled');
	
	doValidForm($('.quickly_release_div'));
	
	
	var factoryInfo = window.location.search;
	
	if (factoryInfo != null && factoryInfo != '') {
      var factoryId = factoryInfo.substr(11);
      $('.quickly_form').find('input[name=libraryFactoryId]').val(factoryId)
	}
	
	 $("body").click(function(e){
		   
		  
		   if(e.target.id.indexOf("mainCategory") == -1){
   	          $("#treeviewForRfq").hide();
   	          
   	        var mainCategoryText= $('#mainCategoryText').text()
   			if(!mainCategoryText||mainCategoryText.trim()==''){
   				$("#mainCategoryAuto").prop('checked',true)
   			}
   	       }        
	   })
	   
	   
	   getMainProcess();
	 
	$('.page1').find("input[name=confidentialityAgreement]").each(function(){
		if($(this).val()==0){
			$(this).prop('checked',true)
		}
		
	})
	
	$('.page1').find("input[name=quotePurpose]").each(function(){
		if($(this).val()==0){
			$(this).prop('checked',true)
		}
		
	})
	$('.page1').find("input[name=mainCategory]").each(function(){
		if($(this).val()==0){
			$(this).prop('checked',true)
		}
		
	})
	
	$('.upclass').change(function(){
		
		show_drawingName(this)
		
	})
	
	
	ajaxSelect()
	
	
//	sum($('.partNum'))
	
	

	$(".firstNext").click(function() {
        
		
		$('.quickly_release_div').find('.ff4').removeClass('ff4').text('');
		
		$('.quickly_release_div').find("input[require]:visible").blur();

//		$('.quickly_release_div').find("select[require]:visible").blur();
//		
//		$('.quickly_release_div').find("select[requireOrtext]:visible").blur();
//		
//		$('.quickly_release_div').find("textarea[require]:visible").blur();
//		
//		$('.quickly_release_div').find("textarea[requireOrtext]:visible").blur();
//		
//		$('.quickly_release_div').find("input[invitationRequireNum]:visible").blur();
//		
//		$('.quickly_release_div').find("input[requiredate]:visible").blur();
		
		
		var flag = false;
		


		
		 $('.quickly_release_div').find('input[name="confidentialityAgreement"]').each(function(){
				
				if($(this).is(':checked')&&$(this).val()==2){
					 var currForm = $(this).parents('form')
					if(currForm.find('input[name="filePath"]').val().trim()==''){
						$('#confidentialityQuicklyFile').next().addClass('ff4').text('please select a file')
						flag = false;
					
					}
					
				}
			})	
			
		if($('.quickly_release_div').find('input[name="drawingName"]').val().trim()==''){
			$('#drawSpan').addClass('ff4').text('please select a file')
			flag = false;
		}
			

		if ($('.ff4').length > 0) {
			return false;
		}
		
		$('.firstNext').css({'background-color':'#ddd'}).attr("disabled",true);
	
		//$('.weui_mask_new').show();
		
		
		
		var data = {};
		
		$('.quickly_form').find('input[type=hidden],textarea[name="quoteTitle"]').each(function() {
	
				data[$(this).attr('name')] = $(this).val()
			
			
		})
		
		$('.quickly_form').find('input[type=radio]').each(function() {
		if ($(this).prop('checked')) {
				data[$(this).attr('name')] = $(this).val()
				
				
			}
		})
		
		
		
		

		var products = [];
		var len = $('.addProducts').length;

		$('.addProducts:lt(' + len + ')').each(function() {
			var pro = {};
			$(this).find('input[type=text],textarea').each(function() {
				if ($(this).val()) {
					if(pro[$(this).attr('name')]){						
						pro[$(this).attr('name')] +=','+ $(this).val()
					}else{
						pro[$(this).attr('name')] = $(this).val();
					}										
				}
			})
			
			
			products.push(pro)
		})

		data.products = products;

		var obj = JSON.stringify(data);// 对象转成字符串

		$.ajax({
			url : "/quoteInfo/sendQuoteToConsole.do",
			type : "post",
			async: false,
			data : {
				'param' : obj
			},// 参数
			datatype : "json",
			success : function(data) {
				$('.firstNext').css({'background-color':'#006dcc'}).removeAttr('disabled');
				if(data.state==0){
				//	$('.weui_mask_new').hide();
					var url = data.data;
					//根据cookie判断不同语言页面
					var lan = getCookie("language");
					if(lan == 'en'){
						url = url.replace('zh','en');
					}
					window.location = url;					
					
				}

			}

		})
		
	
	})
	
	
	
	
	
	
	
	
	
	
	
	
})


function ajaxSelect(){
	

	$.ajax({
		url : "/quoteInfo/selectDetail.do",
		type : "post",
		async: false,
		success : function(data) {
			if(data.state==0){
	           var quoteInfo = data.data.quoteInfo
	           var products =''
	           if(quoteInfo){
	        	   products = quoteInfo.products
	        	   
	        	   $('#quoteTitle').val(quoteInfo.quoteTitle)
	        	   
	        	   var mainCategory = quoteInfo.mainCategory
	        	   var quotePurpose = quoteInfo.quotePurpose
	        	   var confidentialityAgreement = quoteInfo.confidentialityAgreement
	        	   var filePath = quoteInfo.filePath
	        	   var deliveryDate = quoteInfo.deliveryDate
	        	   var drawingPath = quoteInfo.drawingPath
	        	   var orderId = quoteInfo.orderId
	        	   
     	   
	        	   $('.quickly_form').find('input[name=orderId]').val(orderId)
	        	   
	        	   
	        	   
	        	   //初始选择工艺
	        	   if(mainCategory){
	        		   if(mainCategory==0){
	        			   $('#mainCategoryAuto').prop('checked',true)
	        		   }else if(mainCategory==1){
	        			   $('#mainCategory').prop('checked',true)
	        			   
	        			   if(quoteInfo.mainProcess){
	        				   
		        			   $('#mainProcess').val(quoteInfo.mainProcess)
		        			   
		        			   var mainProcess = quoteInfo.mainProcess.replace('>',',')
		        			   
		        			   if(mainProcess.indexOf('>')>0){
		        				   $('#mainCategoryText').html("The process you choose is:<em style='color:#f64431;'>"+mainProcess+"</em>")
		        			   }else{
		        				   $('#mainCategoryText').html("The process you choose is:<em style='color:#f64431;'>"+mainProcess+'>'+"</em>")
		        			   }
		        			  
	        			   }
	        			   
	        			   
	        			 
	        		   }
	        		   
	        	   }
	        	   
	        	   
	        	   //询盘目的选中
	        	   if(quotePurpose){
	        		   
	        		   $('.quickly_form').find('input[name=quotePurpose]').each(function(){
	        			 
	        			   if($(this).val()==quotePurpose){
	        				   $(this).prop('checked',true)
	        				   
	        			   }
	        			   
	        		   })
	        		   
	        	   }
	        	   
	        	   
	        	  //资格认证 
                 if(confidentialityAgreement){
                	  $('.quickly_form').find('input[name=confidentialityAgreement]').each(function(){
 	        			 
	        			   if($(this).val()==confidentialityAgreement){
	        				   $(this).prop('checked',true)
	        				   
	        			   }
	        			   
	        		   })
	        		   if(confidentialityAgreement==2){
        				   if(filePath){
        					   $('.quickly_form').find('input[name=filePath]').val(filePath)
        					   $('.quickly_form').find('input[name=confidentialityFileName]').val(quoteInfo.confidentialityFileName)
        					   $('#confidentialityQuicklyFile').show()
        					   $('#UploadfileName').prepend(quoteInfo.confidentialityFileName).show()
        					   
        				   }
        				    
        			   }
                	 
                	 
                	 
                 }
                 //交货日期
//                 if(deliveryDate){
//                	 
//                	 $('#deliveryDate').val(deliveryDate)
//                 }
                 
	        	 //图纸 
	        	 if(drawingPath){
	        		 $('#drawingPath').val(drawingPath)
	        		 $('#drawingName').val(quoteInfo.drawingName)
	        		 $('#showdrawfile').prepend(quoteInfo.drawingName).show()
 
	        	 }  
	        	 
	        	 if(products&&products.length>0){
	        		 var addProducts = ''
	        		 
	        		 for(var j=0;j<products.length;j++){
	        			 
	        			 var product = products[j]
	        			
        				 
	        			 if(j==0){
	        				 productGrid($('#firstProduct'),product)
	        			 }
	        			 
	        		 }
	        		 
	        		 
	        	 }
	        	   
	           }
			}
	
		}
	
	
	
	
})
	}

/*
 * 
 *sure
 * 
 */


function showQuicklyUploadEnter(obj) {

	if($(obj).val()==2&&$(obj).is(':checked')){
	$('#confidentialityQuicklyFile').next().removeClass('ff4').text('')
	$('#confidentialityQuicklyFile').show();
	$('#UploadfileName').hide()
	
	}else{
		$('#confidentialityQuicklyFile').next().removeClass('ff4').text('')
		deleteQuicklyfile($('#UploadfileName').find('em'));
		$('#confidentialityQuicklyFile').hide();
	}
}

/*
 * sure
 */

function show_drawingName(obj) {
	

	 
	$(obj).parent().next().removeClass('ff4').text('')
	 var fileUploadForm = $(obj).parents('form');
	 
	 fileUploadForm.find('input[name="fileName"]').val('')
	 
	 /*
	  * 清除文件名样式
	  */
//	 $(obj).next().next().val('');
//	 $(obj).next().next().next().val('');
//	 $(obj).next().html('<i class="iconfont posiabso" onclick="deleteQuicklyfile(this)">&#xe683;</i>').hide();
	 
	 
     if(!getFilesize(obj)){
		
    	 easyDialog.open({
				container : {
					header : 'Prompt message',
					content : 'Less than 30M file'
				},
				overlay : false,
				autoClose : 1000
			});
    	 
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
				
				$(obj).next().next().val(drawingName);
				$(obj).next().next().next().val(result.data);
				
				$(obj).next().html('<em class="iconem"  onclick="deleteQuicklyfile(this)"></em>').prepend(drawingName).show();
				
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

/*
 * sure
 */
function deleteQuicklyfile(obj) {

	

	var temp = $(obj).parent();
	var path = temp.next().next().val()
	
	if(!path){
		return;
	}
	
	
	temp.html('<em class="iconem"  onclick="deleteQuicklyfile(this)"></em>').hide();
	
	
	
	$.ajax({
		url : "/upload/deleteFile.do",
		type : "post",
		data : {
			'filePath' : path
		},
		datatype : "json",
		success : function(data) {
			
			temp.parents('form').find('input[name="fileName"]').val("");
			temp.next().val('')
			temp.next().next().val('')
		}

	})

}
/*
 * sure
 */
	
function deleteProduct(obj) {
	var addPart = $('.addProduct').remove()
	$(obj).parent().remove();
	sum($('.partNum'))
	
	if ($('.partNum').length < 11) {
		var tempform = $('.ntxtbtn').prev().find('form');
		tempform.append(addPart)
		$('.addProduct').show()
	}
	return false;

}

/*
 * 
 * sure
 * 
 */

function addProduct() {

	
	var addHtml = $('#tempAdd .addProducts');
	addHtml.clone().insertBefore($('.ntxtbtn'));
	
	var tempdiv = $('.ntxtbtn').prev();
	
	var tempform = tempdiv.find('form')
	
	var addproduct =  $('.addProduct').remove()
	
	tempform.append(addproduct)
	
	doValidForm(tempdiv)
	doFocusForm(tempdiv)
	
	
	if ($('.partNum').length == 11) {
		$('.addProduct').hide()
	}

	
	

	sum($('.partNum'))
		
	
	$('.form_date').datetimepicker({
        language:  'en',
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
/*
 * sure
 * 
 */

function uploadProduct(obj) {

	$(obj).next()[0].click();
	return false;

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





var mainProcessList = [
                       {
                           name: "Injection molding"
                       },
                       {
                           name: "Vacuum forming"
                       },
                       {
                           name: "Rotational molding"
                       },
                       {
                           name: "Blow molding"
                       },
                       {
                           name: "Plastic extrusion"
                       },
                       {
                           name: "Others(Plastic)"
                       },
                       {
                           name: "Stainless steel casting (silicon glue, water glass)"
                       },
                       {
                           name: "Die casting (aluminum, zinc, magnesium, copper, etc.)"
                       },
                       {
                           name: "Sand casting (steel, iron, copper, aluminum, etc.)"
                       },
                       {
                           name: "Gravity casting (aluminum, copper, etc.)"
                       },
                       {
                           name: "Others(casting)"
                       },
                       {
                           name: "Forging",
                           secondList: [
                               "Hot forging",
                               "Cold forging"
                           ]
                       },
                       {
                           name: "Raw material procurement"
                       },
                       {
                           name: "Sheet metal machining (cutting, bending, welding, assembly)"
                       },
                       {
                           name: "Stamping and stretching"
                       },
                       {
                           name: "Spinning"
                       },
                       {
                           name: "Aluminum extrusion"
                       },
                       {
                           name: "Machining",
                           secondList: [
                               "CNC machining center (3-axes or more)",
                               "High-speed lathe machine",
                               "High-speed milling machine",
                               "Ordinary lathe & millilng machine",
                               "Others"
                           ]
                       },
                       {
                           name: "Prompt Goods"
                       },
                       {
                           name: "Other purchases"
                       }
                   ];

function getMainProcess() {

	var mainProcess = $("#select_main_process");
	mainProcess.html('');
	$("#select_second").html('');
	for (var i = 0; i < mainProcessList.length; i++) {

		var selectli = '<li onclick=getSecond(this) value=' + i + '>'
				+ mainProcessList[i].name + '<span></span></li>';
		mainProcess.append(selectli);
	}
	

}

function getSecond(obj) {

	$(obj).css({
		"background-color" : "#006dcc",
		"color" : "#fff"
	}).siblings().css({
		"background-color" : "#fff",
		"color" : "#333"
	})

	var second = $("#select_second");


	second.empty();
	
	secondList = mainProcessList[$(obj).val()].secondList;

	if(secondList){
	for (var i = 0; i < secondList.length; i++) {
		var selectli = '<li onclick=getValues(this) leaf=' + $(obj).val()
				+ ' value=' + i + '>' + secondList[i]
				+ '<span></span></li>';
		second.append(selectli);

	}}

	$('#mainCategoryText').html("The process you choose is:<em style='color:#f64431;'>"+mainProcessList[$(obj).val()].name + '>'+"</em>");
	
	$('#mainProcess').val(mainProcessList[$(obj).val()].name)

}

function getValues(obj) {
	$(obj).css({
		"background-color" : "#006dcc",
		"color" : "#fff"
	}).siblings().css({
		"background-color" : "#fff",
		"color" : "#333"
	})

	var first = $(obj).attr('leaf');


	$('#mainCategoryText')
			.html(	"The process you choose is:<em style='color:#f64431;'>"+mainProcessList[first].name + '>'
					+ mainProcessList[first].secondList[$(obj).val()]+"</em>");

	$('#mainProcess').val(mainProcessList[first].name + ','
			+ mainProcessList[first].secondList[$(obj).val()])

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

/*
 * sure
 *
 */

function cleanData(){
	$("#treeviewForRfq").hide();
	$('#mainCategoryText').text(null);
	$('#mainProcess').val(null)
	
}

/*
 * 
 * sure
 */

function addQuantityList(obj){
	
	
	var currForm = $(obj).parents('form')
	
	var number = currForm.find('input[name=quantityList]').length
	
	if(number>1){
		$(obj).hide()
	}
    
	var addHtml=
	     '<div class="form-group">'
		+'<label for="" class="col-sm-2 control-label pl28">'
		+'</label>'
		+'<div class="col-sm-10 colsm10 colsm102 posirela"  >'
		
		if(number==1){
			addHtml+= '<span class="number posiabso">2st quantity</span>'  
		}
		
		else if(number==2){
		addHtml+= '<span class="number posiabso">3st quantity</span>'  
	    }
	
		
		
	addHtml+='<input type="text" class="form-control quantityList" name="quantityList" '	
			+'place="0" placeholder="1" field="quantity" invitationRequireNum>'		
		+'<select class="form-control" name="quantityUnit">'	
		+'<option>Pieces</option>'		
		+'<option>Ton</option>'		
		+'</select>'
		+'<span class="deleteQuantityList s01" onclick="deleteQuantityList(this)" style="magin-left:610px">- Delete</span>  <span></span></div>'
		+'</div>'
		+'</div> '  
	
	
	
	
	
	
	
		currForm.find('.materials').before(addHtml)
	
	
	
	doValidForm(currForm);

	doFocusForm(currForm);
	
	
	
}

/*
 * sure
 */

function deleteQuantityList(obj){
	
	
    var currForm = $(obj).parents('form')
	
	var number = currForm.find('input[name=quantityList]').length
	
	if(number==3){
		currForm.find('.addQuantityList').show()
	}
	
	
	$(obj).parent().parent().remove();
	
	
}


//已填入的价格、数量、单位自动赋值
function productGrid(obj,product){
	
	 var quantityList = product.quantityList
	 var quantityUnit = product.quantityUnit
	 
	 $(obj).find('input[name=productName]').val(product.productName)
//	 $(obj).find('input[name=annualQuantity]').val(product.annualQuantity) 
	 
	  $(obj).find('input[name=weight]').val(product.weight) 
	   $(obj).find('textarea[name=materials]').val(product.materials) 
	 
	 $(obj).find('textarea[name=productRemark]').val(product.productRemark)
	 $(obj).find('textarea[name=targetPrice]').val(product.targetPrice) 
	 
	 
	 if(quantityList){
		 $(obj).find('textarea[name=quantityList]').val(quantityList);
		 $(obj).find('input[name=quantityUnit]').val(quantityUnit);

	 }
	
}

function showTextadd(obj){
	
	if($(obj).is(':checked')){
		$(obj).parent().prev().removeAttr('name').val('').removeAttr('requireOrtext')
		$('#equipmentWarn').removeClass('ff4').text('')
		$(obj).parent().next().attr('name','equipmentKeywords').attr('requireOrtext','').show()
		doValidForm($('#equipmentDiv'))
		doFocusForm($('#equipmentDiv'))
		
	}else{
		$(obj).parent().next().removeAttr('name').hide()
	}
	
}


function hideTextadd(obj){
	$(obj).attr('name','equipmentKeywords').attr('requireOrtext')
	$(obj).next().children().prop('checked',false)
	$(obj).next().next().removeAttr('name').removeAttr('requireOrtext').hide()
}	

