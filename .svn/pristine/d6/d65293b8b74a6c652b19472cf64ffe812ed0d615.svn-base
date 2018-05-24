$(function() {

	ajaxSelect()
	
	
    $('#editQuoteButton').click(function(){
    	
    	window.location = "/zh/quickly_release.html";
    	
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
					
					$('#orderId').text('询盘编号#'+(orderId?orderId:""))
					
					if(quoteLocation==0){
						$('#quoteLocationInfo').text('不限')
					}
					if(quoteLocation==1){
						$('#quoteLocationInfo').text('江浙沪')
					}
					if(quoteLocation==2){
						$('#quoteLocationInfo').text('深圳广东福建')
					}
					
					if(quotePurpose==0){
						
						$('#quotePurposeInfo').text('实际订单')
					}
					if(quotePurpose==1){
						
						$('#quotePurposeInfo').text('暂时不急的询盘，主要想建立些长期供应商关系')
					}
					if(quoteWay==0){
						
						$('#quoteWayInfo').text('发布你的询盘在市场上，让更多工厂可以看到')
					}
                    if(quoteWay==1){
						$('#quoteWayInfo').text('只给定向供应商报价')
						$('#inviteFactoryNameInfo').text(inviteFactoryName?inviteFactoryName:"-")
					
					}
					
                    if(confidentialityAgreement==0){
                    	$('#confidentialityAgreementInfo').text('无保密协议')
                    	
                    }
                    if(confidentialityAgreement==1){
                    	$('#confidentialityAgreementInfo').text('快制造保密协议')
                    }
                    if(confidentialityAgreement==2){
                    	$('#confidentialityAgreementInfo').text('自己上传保密协议')
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
						$('#mainProcess').text('请平台工程师建议')
					}
					
				}
				
				
				if(products&&products.length>0){
					for(var k=0;k<products.length;k++){
						var quoteProduct = products[k]
						
						var quantityList = quoteProduct.quantityList
						
						var quantitys = quantityList.split(',')
						
						
						
						addProduct()
						
						var tempDiv = $('.btns').prev()
						
						tempDiv.find('input[name=id]').val(quoteProduct.id?quoteProduct.id:"")
						tempDiv.find('textarea[name=productRemark]').val(quoteProduct.productRemark?quoteProduct.productRemark:"")
						tempDiv.find('input[name=targetPrice]').val(quoteProduct.targetPrice?quoteProduct.targetPrice:"")
						
						var tempTable ='<table class="table tab2">' 
							+'<thead><tr>'
							+'<th>零件名称</th>'
							+'<th>第一报价数量</th>'
							+'<th>第二报价数量</th>'
						    +'<th>第三报价数量</th>'
							+'<th>年用量预估</th>'
							+'<th>材料</th>'
							+'<th>重量(kg)</th>'
							+'</tr></thead>'
							+'<tbody><tr>'
							+'<td>'+(quoteProduct.productName?quoteProduct.productName:"")+'</td>'
							
							+'<td>'+(quantitys[0]?quantitys[0]:"")+'</td>'
							
							if(quantitys.length>1){
								tempTable+='<td>'+(quantitys[1]?quantitys[1]:"-")+'</td>'
							}else{
								tempTable+='<td>-</td>'
							}
							  if(quantitys.length>2){
								  tempTable+='<td>'+(quantitys[2]?quantitys[2]:"-")+'</td>'
							  }else{
								  tempTable+='<td>-</td>'
								}
							  
							  
							  
							tempTable+='<td>'+(quoteProduct.annualQuantity?quoteProduct.annualQuantity:"-")+'</td>'
							
							tempTable+='<td>'+(quoteProduct.materials?quoteProduct.materials:"-")+'</td>'
							tempTable+='<td>'+(quoteProduct.weight?quoteProduct.weight:"-")+'</td>'
							
							+'</tr></tbody> </table>'
							tempDiv.find('.remarks').before(tempTable)
						
					       }
						
				}
				
				
				
				
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
			 $(this).parent().next().addClass('ff4').text("请输入数字").fadeIn();
			return false;
		}
		}

		
		$(this).addClass('success');
	
		return false;
	})
	
}


function doFocusForm(ob) {
	
	$(ob).find("input[requireNum]:visible").focus(function() {
		
		$(this).parent().next().css({
			"display" : "none"
		});
		$(this).attr('placeholder','');
	})
   	

}