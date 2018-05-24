$(function() {
	var proId = window.location.search;

	if (proId == null || proId == '') {
		window.location = "/zh/products.html";
		return;

	}

	var picId = proId.substr(4);
	
	$('#prodetail_href').attr('href','/zh/product_detail.html?id='+picId)
	
	ajaxSelect(picId);

	$(".f3").mouseover(function() {
		$(".sup-prod-bg-photo").fadeIn();

		$(".sup-prod-bg-photo img").prop("src", $('.f3').attr('datasource'));

	}).mouseout(function() {
		$(".sup-prod-bg-photo").fadeOut();
	});

	$('#connectFactory').click(function() {

		$('.page1').hide();
		$('#saveButton').css({'background-color':'#006dcc'}).removeAttr('disabled');
		$('.page2').show();
		

		return false;
	})

	// var planBuyCount_judgment = false;
	// var planBuyPrice = false;
	var planInfo_judgment = false;

	$('#planInfo').blur(function() {
		$('#planInfo').next().css({
			"display" : "none"
		});
		var planInfo = $('#planInfo').val();
		if (planInfo.trim() != '') {
			$('#planInfo').addClass('success');
			planInfo_judgment = true;
		} else {
			$('#planInfo').next().addClass('ff4 s0').text("内容不能为空！").fadeIn();
			planInfo_judgment = false;
		}
		return false;
	})

	$('#planInfo').focus(function() {
		$('#planInfo').next().removeClass('ff4 s0');
		$('#planInfo').next().css({
			"display" : "none"
		});
	})
    
	
	ajaxPreference(0);
	
	
	 
	$('#preferencePic').click(function() {

		var picType = $('#preferencePic').attr('class');
		var type = 0;

		if (picType == 'notPic') {
			type = 1;
			 ajaxPreference(type)

		} else if (picType == 'likePic') {
			type = 2;
			ajaxPreference(type)
		}

		return false;

	})

	$('#saveButton').click(function() {

		$('#planInfo').blur();
         
		
		
		if (planInfo_judgment) {
			
			$('#saveButton').css({'background-color':'#ddd'}).attr('disabled',true)
			
			
			
			$.ajax({
				type : 'POST',
				data : $('#sendToFactory').serialize(),
				url : '../productLibrary/sendMessageToFactory.do',
				success : function(result) {

					if (result.state == 0) {
						alert('发送成功');
                        
						$('#planBuyCount').val('')
						$('#planBuyPrice').val('')
						$('#planInfo').val('')
						
						$('.page2').hide();
						$('.page1').show();

					}else if(result.state == 1&&result.data){
						var url = result.data;
						
						window.location = url;
						
					}

				},
				error : function(result) {

				}
			});
		

		}
		return false;
		

	})
	
	$('#createNewQuote').click(function(){
		
		var factoryId = $('#factoryId').val();
	
		if(factoryId){
			window.location = "/zh/quickly_release.html?factoryId="+factoryId;
		}
		
		
	})
	
})

function ajaxSelect(id) {

	$
			.ajax({
				url : "/productManage/selectProductDetail.do",
				type : "post",
				async:false,
				traditional : true,
				data : {
					'picId' : id
				},
				datatype : "json",
				success : function(result) {

					if (result.state == 0) {
                         
						var equipmentList = {};
						var productLibrary = result.data.productLibrary;
						var factoryInfo = result.data.factoryInfo;
						var buyerId = result.data.buyerId;
						
						if(factoryInfo){
						 equipmentList = factoryInfo.equipmentList;
						 var factoryLogo = factoryInfo.factoryLogo
						 var factoryId = factoryInfo.factoryId
						 
						 $('#viewFactory').attr('href','/zh/view_factory_info.html?id='+factoryId+'#!tab=0')
						 
						}
						if(factoryLogo){
							
							$('.factoryLogo').attr('src','/static_img/factory_logo/'+factoryId+'/'+factoryLogo)
						}
						
						$('.productName').text(productLibrary.productName);
						$('.factoryName').text(factoryInfo.factoryName);
						
						
						$('#producta').attr('href','/zh/view_factory_info.html?id='+factoryId+'#!tab=1')

						$('#mainProcess').text(productLibrary.mainProcess?productLibrary.mainProcess:'-');
						$('#materials').text(productLibrary.materials?productLibrary.materials:'-');
						$('#productId').val(productLibrary.id);
						$('#factoryId').val(productLibrary.factoryId);
						$('#formFacoryId').val(productLibrary.factoryId);
						$('#proId').val(productLibrary.id)
						$('#proName').val(productLibrary.productName)
						$('#buyerId').val(result.data.buyerId);
						$('#userName').text('联系人：' + factoryInfo.username);
						$('#productInfo2').text(productLibrary.productInfo);
						$('#planBuyCount').parent().next().find('span').text(
								'最低采购量' + (productLibrary.minAqmountPurchased?productLibrary.minAqmountPurchased:'-')
										+ (productLibrary.purchasedUnit?productLibrary.purchasedUnit:"件"));
						$('#planBuyPrice').parent().next().find('span').text(
								'人民币/ 件（价格范围区间: ' + productLibrary.minquote
										+ '）')
						$('#planBuyCountUnit').val(productLibrary.purchasedUnit?productLibrary.purchasedUnit:"件")

						$('#quote').text(
								'￥' + productLibrary.minquote + '/'
										+ (productLibrary.purchasedUnit?productLibrary.purchasedUnit:"件"))
						$('#minAqmountPurchased').text((productLibrary.minAqmountPurchased?productLibrary.minAqmountPurchased+(productLibrary.purchasedUnit?productLibrary.purchasedUnit:"件"):""));
						$('.f3')
								.attr('src', productLibrary.compressDrawingPath);
						$('#productPic').attr('src',
								productLibrary.compressDrawingPath);

						var compressDrawingPathGroup = productLibrary.compressDrawingPathGroup
								.split(';');

						var drawingPathGroup = productLibrary.drawingPathGroup
								.split(';');

						var productInfo = [];
						if(productLibrary.productInfo){
						productInfo = productLibrary.productInfo
								.split(/[，。]/);
						
						for (var i = 0; i < productInfo.length; i++) {
							if (productInfo[i].trim() != '') {
								var addp = '<p>' + productInfo[i] + '。'
										+ '</p>';

								$('#productInfo').append(addp);

							}

						}
						}
						for (var i = 0; i < compressDrawingPathGroup.length; i++) {
							var addHtml = '<img alt="" class="compressimg" onmouseover="changePic(this)" datasource="'
									+ drawingPathGroup[i]
									+ '"   src="'
									+ compressDrawingPathGroup[i] + '"/>';
							$('.media-object-js').append(addHtml)

						}

						for ( var key in factoryInfo) {
                              
							if(key!='mainProcess'){
								$('#' + key).text(
										factoryInfo[key] ? factoryInfo[key] : '-');
								
							}
							

						}
                        if(equipmentList.length>0){
						for (var j = 0; j < equipmentList.length; j++) {

							var equipmentName = equipmentList[j].equipmentName ? equipmentList[j].equipmentName
									: '-';
							var equipmentModel = equipmentList[j].equipmentModel ? equipmentList[j].equipmentModel
									: '-';
							var number = equipmentList[j].number ? equipmentList[j].number
									: '-';
							var parameter = equipmentList[j].parameter ? equipmentList[j].parameter
									: '-';
							var type = equipmentList[j].type ? equipmentList[j].type
									: '-';

							var appendHtml = '<tr>' + '<td><span class="w140">' + (equipmentName?equipmentName:"")
									+ '</span></td>' + '<td><span class="w140">' + (equipmentModel?equipmentModel:"")
									+ '</span></td>' + '<td><span class="w140 pull-left">' + (number?number:"") + '</td></td>'
//									+ '<td>' + parameter + '</td>'
									+ '<td><span class="w249">'
									+ (type?type:"") + '</span></td>' + '</tr>';

							$('.table3 tbody').append(appendHtml);

						}
                        }

					}
				}
			})
}

function ajaxPreference(type) {

	
	var productId = $('#productId').val();

	if ( productId.trim() != '') {

		$.ajax({
			url : "/productLibrary/checkPreference.do",
			type : "post",
			traditional : true,
			data : {
				'productId' : productId,
				'type' : type

			},
			datatype : "json",
			success : function(result) {

				if (result.state == 0) {
					
					if(type==0){
						 $('#preferencePic').attr('class','likePic');
		             }
					if(type==1){
						$('#preferencePic').attr('class','likePic');
					}
					if(type==2){
						$('#preferencePic').attr('class','notPic');
					}
				}

			}

		})

	}

}

function changePic(obj) {
	var compic = $(obj).attr('src');
	var pic = $(obj).attr('datasource');
	$('.f3').attr('src', compic);
	$('.f3').attr('datasource', pic);
}
