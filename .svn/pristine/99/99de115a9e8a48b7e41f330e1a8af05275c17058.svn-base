$(function() {

	
	/* 点击分页 */
//	$('.pagination li:first-child').nextUntil('.pagination li:last-child')
//			.click(function() {
//				$(this).addClass('active').siblings().removeClass('active');
//			})

//	init();



//	function init() {
//
//		ajaxSelect(1);
//
//	}
	
	
	ajaxSelect(1);
	
	
	$('#selectButton').click(function() {
		
		var url = location.href;
		
		//不刷新页面修改url
		if(url.indexOf('#!fenye')>0){
		newUrl = url.substring(0,url.indexOf('#!fenye'))
		window.history.pushState({}, 0, newUrl);
		}
		ajaxSelect(1);
		
	})
	


})



function ajaxSelect(page) {
	var processGroup = [];
	var materialsGroup = [];
	var regionGroup = [];

//	$('#productList').empty();
	$('#processGroup span i').each(function() {
		if ($(this).hasClass('iactive')) {
			processGroup.push($(this).parent().text());

		}
	})

	$('#materialsGroup span i').each(function() {
		if ($(this).hasClass('iactive')) {
			materialsGroup.push($(this).parent().text());

		}
	})

	$('#regionGroup span i').each(function() {
		if ($(this).hasClass('iactive')) {
			regionGroup.push($(this).parent().text());
		}
	})

	
			$.ajax({
				url : "/productLibrary/queryList.do",
				type : "post",
				traditional : true,
				data : {
					'processGroup' : processGroup,
					'materialsGroup' : materialsGroup,
					"regionGroup" : regionGroup,
					"page" : page
				},
				datatype : "json",
				success : function(result) {
					var totalOrder = result.data.totalOrder;
					if (result.state == 0) {

						$('#productList').empty();
						
						
						
						
						for (var i = 0; i < result.data.productList.length; i++) {
							var temp = result.data.productList[i];
							var process = [];
							if(temp.process){
								 process =temp.process.split(';');
							}
							
							
							var product_div = '<div class="col-xs-3 he-c">'
									+ '<div class="thumbnail">'
									+ '<div class="imgs">' 
									
									+'<a   target="_blank" href="/zh/product_detail.html?id='
									+ temp.id + '">'
									+'<img src="'
									 + temp.compressDrawingPath
									+ '" alt="..."  class="img-responsive">'
									+ '</a></div>' + '<div class="caption">'
									+ '<p>' + '<span>产品名</span> <em>'
									
									
									if(temp.productName.length>5)
										product_div+=  temp.productName.substr(0,5) +'...'+'</em></p><p>'
									else{
										product_div+=  temp.productName + '</em></p><p>'	
										}
									
		
							product_div+='<span>工艺</span>'
							if(process.length>0){
								product_div+= '<em>'+process[0]+'</em>'
								
							}	
							
							product_div+='</p><p>'
							if (temp.minquote != null
									&& temp.minquote.trim() != ''
									&& temp.maxquote != null
									&& temp.maxquote.trim() != '') {
								product_div += '<span>价格</span> <i>￥['
									    + temp.minquote
										+ '- ' + temp.maxquote + ']/元</i>';
							} else if (temp.minquote != null) {
								product_div += '<span>价格</span> <i>￥['
										  + temp.minquote
										+ ']/元</i>';

							} else if (temp.maxquote != null) {
								product_div += '<span>价格</span> <i>￥['
										 + temp.maxquote
										+ ']/元</i>';

							}
							product_div += '</p>' + '</div>' + '</div>'
									+ '</div> ';

							$('#productList').append(product_div);
							
							
							

						}

//						var pagination = '<div class="col-xs-12">'
//								+ '<div class="pages">'
//								+ '<ul class="pagination" id="page_ul">'
//								+ '</ul></div></div>';
//						$('#productList').append(pagination);
//
//						var li = "";
//						if (totalPage == 0 || totalPage == 1) {
//							li = '  <li><a href="###"><span class="glyphicon glyphicon-menu-left"></span></a></li><li class="active"><a href="#">1</a></li> <li><a href="###"><span class="glyphicon glyphicon-menu-right"></span></a></li>';
//						} else {
//							var li_s = "";
//							for (var k = 0; k < totalPage; k++) {
//								if (k < 5) {
//									li_s += "<li><a href='#' onclick='queryByPage("
//											+ (k + 1)
//											+ ")'>"
//											+ (k + 1)
//											+ "</a></li>";
//								} else {
//									li_s += "<li style='display:none'><a href='#' onclick='queryByPage("
//											+ (k + 1)
//											+ ")'>"
//											+ (k + 1)
//											+ "</a></li>";
//								}
//
//							}
//
//							// 第一页 前面的不能点击
//							if (page == 1) {
//								li = '<li><a href="###"><span class="glyphicon glyphicon-menu-left"></span></a></li>'
//										+ li_s
//										+ '<li>'
//										+ '<a href="#" onclick="queryByPage('
//										+ (currentPage + 1)
//										+ ')"><span class="glyphicon glyphicon-menu-right"></span></a>'
//										+ '</li>';
//							} else if (page == totalPage) {
//								li = '<li><a href="#" onclick="queryByPage('
//										+ (currentPage - 1)
//										+ ')"><span class="glyphicon glyphicon-menu-left"></span></a></li>'
//										+ li_s
//										+ ' <li><a href="###"><span class="glyphicon glyphicon-menu-right"></span></a></li>';
//
//							} else {
//								li = '<li><a href="#" onclick="queryByPage('
//										+ (currentPage - 1)
//										+ ')"><span class="glyphicon glyphicon-menu-left"></span></a></li>'
//										+ li_s
//										+ '<li><a href="#" onclick="queryByPage('
//										+ (currentPage + 1)
//										+ ')"><span class="glyphicon glyphicon-menu-right"></span></a></li>';
//							}
//						}
//
//						$('#page_ul').append(li);
//
//						if (page >= 6) {
//							$('#page_ul li').hide();
//							$('#page_ul li:first').show();
//							$('#page_ul li:last').show();
//
//							for (var j = page - 4; j <= page; j++) {
//								$('#page_ul').find('li').eq(j).show();
//							}
//
//						} else {
//							$('#page_ul li').hide();
//							$('#page_ul li:first').show();
//							$('#page_ul li:last').show();
//
//							for (var j = 1; j <= 5; j++) {
//								$('#page_ul').find('li').eq(j).show();
//							}
//
//						}
//
//						$('#page_ul').find('li').eq(page).addClass('active')
//								.siblings().removeClass('active');
						
						
                         
							
							var totalOrder = result.data.totalOrder;
							
							var totalPage = Math.ceil(totalOrder / 20);
							
							
							laypage({
								cont: 'biuuu_link',
								pages: totalPage,
								skin: '#006DCC', //皮肤
								last:  totalPage,
								groups:5,
							    prev: '<', //若不显示，设置false即可
							    next: '>' ,//若不显示，设置false即可
							    curr: location.hash.replace('#!fenye=', ''), //获取hash值为fenye的当前页
							    hash: 'fenye', //自定义hash值
								jump: function(obj,first) {
									if(!first){
										ajaxSelect(obj.curr);
										
										
									}
									
								}
							})
							
							

							
							
//							addcss()

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




//function choosePage(pages){
//	
//	laypage({
//		cont: 'biuuu_link',
//		pages: pages,
//		skin: '#006DCC', //皮肤
//		last:pages,
//		groups:5,
//	    prev: '<', //若不显示，设置false即可
//	    next: '>' ,//若不显示，设置false即可
//	    curr: location.hash.replace('#!fenye=', ''), //获取hash值为fenye的当前页
//	    hash: 'fenye', //自定义hash值
//		jump: function(obj) {
//			ajaxSelect(obj.curr);
//		}
//	})
//	
//}



//function queryByPage(page) {
//	if (page == currentPage) {
//		return false;
//	} else {
//		currentPage = page;
//		ajaxSelect(page);
//	}
//}
