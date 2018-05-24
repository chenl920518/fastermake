$(function() {

	var permission;
	var factoryInfo = getCookie("factoryInfo");
	if (factoryInfo != null && factoryInfo != '' && factoryInfo != undefined) {    		
		var base = new Base64();
		factoryInfo = base.decode(factoryInfo);
		var strs = [];
		strs = factoryInfo.split("&");
		permission = strs[3];
		if(permission !=1){
			$('.li3').prev().hide();
		}
	}
	
	
	$('#addButton').click(function() {
		window.location = "/zh/product_create.html";
	})

	$('.imgadd a').click(function() {
		window.location = "/zh/product_create.html";
	})
	ajaxSelect();
	$('.product_condition span a').click(function() {
		ajaxSelect($(this).text())
	})

})
function ajaxSelect(obj) {
	var process = obj;
	$('.proShow .addHtml ').remove()
	$
			.ajax({
				url : "/productManage/queryList.do",
				type : "post",
				traditional : true,
				data : {
					'process' : process
				},
				datatype : "json",
				success : function(result) {

					if (result.state == 0) {
						
						$('#factoryName').text(result.data.factoryInfo.factoryName);
						for (var i = 0; i < result.data.productList.length; i++) {
							var temp = result.data.productList[i];
							var process = []
							if(temp.process){
								 process = temp.process.split(';');
							}
							
							
							var number = result.data.number;
							
							$('#number').text('共'+number+'个');

							var addHtml = '<div class="col-xs-3 addHtml" >'
									+ '<div class="thumbnail">'
									+ '<div class="imgs">' + '<img src="'
									+ temp.compressDrawingPath
									+ '" alt="" class="img-responsive imgsize">'
									+ '</div>' + '<div class="caption">'
									+ '<p>' + '<span>产品名</span> <em>'
									
								if(temp.productName.length>5)
									addHtml+=  temp.productName.substr(0,5) +'...'+'</em></p><p>'
								else{
										addHtml+=  temp.productName + '</em></p><p>'	
									}
									
									
							if(process.length>0){
								addHtml+= '<span>工艺</span><em>'+process[0]+'</em></p><p>'
								
							}	
									
								
							if (temp.minquote != null
									&& temp.minquote.trim() != ''
									&& temp.maxquote != null
									&& temp.maxquote.trim() != '') {
								addHtml += '<span>价格</span> <i>'
										+ temp.minquote + '- ' + temp.maxquote+'人民币'
										+ '</i>';
							} else if (temp.minquote != null) {
								addHtml += '<span>价格</span> <i>'
										+ temp.minquote +'人民币</i>';
							} else if (temp.maxquote != null) {
								addHtml += '<span>价格</span> <i>'
										+ temp.maxquote + '人民币</i>';

							}

							addHtml += '</p>'
									+ '</div>'
									+ '<div class="bj">'
									+ '<div class="bj_l pull-left">'
//									+ '<i></i> 1000'
									+ '</div>'
									+ '<div class="bj_r pull-right">'
									+ '<span  onclick="deletePro(this)"  picId='
									+ temp.id
									+ '>删除</span> <a href="/zh/product_create.html?id='
									+ temp.id + '">编辑</a>'
									+ '</div></div></div></div>';

							$('#addPro').before(addHtml);
						}
						
//						addcss()
						
					  }else if(result.state == 1&&result.data.url!=null){
						window.location = result.data.url;
				
					}
				}
			})
}

 function addcss(){
	
	 /* body 高度控制底部位置 */
  var h1 = $(document.body).height();
  var h2 = window.screen.availHeight  ;
  if(h1 < h2){
      $('#footer').addClass('footer1')
  }else{
      $('#footer').removeClass('footer1');
  }; 
	
}

function deletePro(obj) {
	var picId = $(obj).attr('picId');
	// alert(picId)
	$.ajax({
		url : "/productManage/deleteProduct.do",
		type : "post",
		traditional : true,
		data : {
			'picId' : picId
		},
		datatype : "json",
		success : function(result) {
			if (result.state == 0) {

//				$(obj).parents('.addHtml').remove();
				
				window.location = "/zh/product_totalpage.html";
				
				
			}
		}
	})

	return false;

}





