$(function() {

	ajaxSelect()
	
	
    $('#editQuoteButton').click(function(){
    	
    	window.location = "/en/quickly_release.html";
    	
    })
    
    $('#saveButton').click(function(){
    	
    	$('.infos').find('.ff4').removeClass('ff4').text('')
    	
    	$('.infos').find("input[requireNum]:visible").blur();
    	
    	if ($('.ff4').length > 0) {
			return false;
		}
    	
    	
    	
    	$('#saveButton').css({'background-color':'#ddd'}).attr("disabled",true);
    	
    	
    	var products = [];
    	
    	$('.confirm_info').find('.infos').each(function(){
    	
    		var pro = {};
			$(this).find('input[type=text],input[type=hidden],textarea').each(function() {
				
				pro[$(this).attr('name')] = $(this).val();
					
			})
			products.push(pro)
    	})
    	
    	var obj = JSON.stringify(products);
    	
    	$.ajax({
			url : "/quoteInfo/updateQuoteProduct.do",
			type : "post",
			async: false,
			data : {
				'param' : obj
			},// 参数
			datatype : "json",
			success : function(data) {
				$('#saveButton').css({'background-color':'#006dcc'}).removeAttr('disabled');
				if(data.state==0){
					
					var url = data.data;
					window.location = url;
				}else if(data.state==2){
					
					easyDialog.open({
						container : {
							header : '  Prompt message',
							content :  data.message
						},
						callback :function(){
							
							
							//根据cookie判断不同语言退出页面
							var lan = getCookie("language");
							var url = data.data;
							if(lan == 'en'){
								url = url.replace('zh','en');
							}else{
								url = url.replace('en','zh');
							}							
							window.location= url;
							
						},
						overlay : false,
						autoClose : 1500
					});
					
					
				}
    	
    	
    	
			}
    	
    	
    })
    	
    	
    	
    	
    	
    	
    	
    	
    })
	
	
})

function ajaxSelect() {

	$.ajax({
		url : "/quoteInfo/selectDetail.do",
		type : "post",
		async : false,
		success : function(data) {
			if (data.state == 0) {

				var quoteInfo = data.data.quoteInfo
				var factoryInfo = data.data.factoryInfo
				
				
				
				if(quoteInfo){
					var quoteLocation = quoteInfo.quoteLocation
					var orderId = quoteInfo.orderId
					var quotePurpose = quoteInfo.quotePurpose
					var quoteWay = quoteInfo.quoteWay
					var inviteFactoryName = quoteInfo.inviteFactoryName
					var confidentialityAgreement = quoteInfo.confidentialityAgreement
					var quoteFreightTerm = quoteInfo.quoteFreightTerm
					var drawingName = quoteInfo.drawingName
					var drawingPath = quoteInfo.drawingPath
					var products = quoteInfo.products
					
					$('#orderId').text('OrderId#'+(orderId?orderId:""))
					
					if(quoteLocation==0){
						$('#quoteLocationInfo').text('Unlimited')
					}
					if(quoteLocation==1){
						$('#quoteLocationInfo').text('JiangZheHu')
					}
					if(quoteLocation==2){
						$('#quoteLocationInfo').text('ShenZhenGuangDongFuJian')
					}
					
					if(quotePurpose==0){
						
						$('#quotePurposeInfo').text('Real RFQ')
					}
					if(quotePurpose==1){
						
						$('#quotePurposeInfo').text('Establish a long-term partnership with suppliers')
					}
					if(quoteWay==0){
						
						$('#quoteWayInfo').text(' Release your enquiry on the market to allow more factories to see')
					}
                    if(quoteWay==1){
						$('#quoteWayInfo').text('Offer only to directional suppliers')
						$('#inviteFactoryNameInfo').text(inviteFactoryName?inviteFactoryName:"-")
					
					}
					
                    if(confidentialityAgreement==0){
                    	$('#confidentialityAgreementInfo').text('None')
                    	
                    }
                    if(confidentialityAgreement==1){
                    	$('#confidentialityAgreementInfo').text('Kuaizhizao NDA')
                    }
                    if(confidentialityAgreement==2){
                    	$('#confidentialityAgreementInfo').text('Upload your own NDA')
                    }
                   
                    
					$('.quoteInfo td').each(function(){
					  var temp = quoteInfo[$(this).attr('id')]
					  if(temp){
						  $(this).text(temp)
					  }
					})
					
					$('#quoteFreightTerm').text(quoteFreightTerm?quoteFreightTerm:"-")
					
					$('#drawingName').text(drawingName?drawingName:"-")
					
					$('#drawingName').parent().attr('dataid',(orderId?orderId:""))
					
					
					if($('#mainProcess').text()==null||$('#mainProcess').text()==""){
						$('#mainProcess').text("Get suggestions from Kuaizhizao's engineers")
					}
					
				}
				
				
//				if(products&&products.length>0){
//					for(var k=0;k<products.length;k++){
//						var quoteProduct = products[k]
//						
//						var quantityList = quoteProduct.quantityList
//						
//						var quantitys = quantityList.split(',')
//						
//						
//						
//						addProduct()
//						
//						var tempDiv = $('.btns').prev()
//						
//						tempDiv.find('input[name=id]').val(quoteProduct.id?quoteProduct.id:"")
//						tempDiv.find('textarea[name=productRemark]').val(quoteProduct.productRemark?quoteProduct.productRemark:"")
//						tempDiv.find('input[name=targetPrice]').val(quoteProduct.targetPrice?quoteProduct.targetPrice:"")
//						
//						var tempTable ='<table class="table tab2">' 
//							+'<thead><tr>'
//							+'<th>Part Name</th>'
//							+'<th>Order One</th>'
//							+'<th>Order Two</th>'
//						    +'<th>Order Three</th>'
//							+'<th>Annual Usage Estimate</th>'
//							+'<th>Material</th>'
//							+'<th>Weight(kg)</th>'
//							+'</tr></thead>'
//							+'<tbody><tr>'
//							+'<td>'+(quoteProduct.productName?quoteProduct.productName:"")+'</td>'
//							
//							+'<td>'+(quantitys[0]?quantitys[0]:"")+'</td>'
//							
//							if(quantitys.length>1){
//								tempTable+='<td>'+(quantitys[1]?quantitys[1]:"-")+'</td>'
//							}else{
//								tempTable+='<td>-</td>'
//							}
//							  if(quantitys.length>2){
//								  tempTable+='<td>'+(quantitys[2]?quantitys[2]:"-")+'</td>'
//							  }else{
//								  tempTable+='<td>-</td>'
//								}
//							  
//							  
//							  
//							tempTable+='<td>'+(quoteProduct.annualQuantity?quoteProduct.annualQuantity:"-")+'</td>'
//							
//							tempTable+='<td>'+(quoteProduct.materials?quoteProduct.materials:"-")+'</td>'
//							tempTable+='<td>'+(quoteProduct.weight?quoteProduct.weight:"-")+'</td>'
//							
//							+'</tr></tbody> </table>'
//							tempDiv.find('.remarks').before(tempTable)
//						
//					       }
//						
//				}
				
				
				
				
				if(factoryInfo){
					var factoryName = factoryInfo.factoryName

					$('#factoryName').text(factoryName?factoryName:"-")
					
					$('.factoryInfo td').each(function(){
					  var temp = factoryInfo[$(this).attr('id')]
					  if(temp){
						  $(this).text(temp)
					  }
					  	
					})
				}
				
				doValidForm($('.infos'))
				doFocusForm($('.infos'))	
				
			}
		}
	})
}


function addProduct(){
	var addHtml = $('#tempAdd .infos');
	
	addHtml.clone().insertBefore($('.btns'));
	
}



function download_file(obj){
    var dataid = $(obj).attr('dataid')
    if(dataid){
    	
    	window.location = "/download/drawingfiledownload.do?id="+dataid;
    }
    

}


function doValidForm(ob) {
	
	$(ob).find("input[requireNum ]:visible").blur(function(){
		
		$(this).parent().next().removeClass('ff4').css({
			"display" : "none"
		});
		
		var temp = $(this).val();
		if(temp != ''){

		 if (!$.isNumeric(temp)) {
			 $(this).parent().next().addClass('ff4').text("Please enter the number").fadeIn();
			return false;
		}
		}

		
		$(this).addClass('success');
	
		return false;
	})
	
}


