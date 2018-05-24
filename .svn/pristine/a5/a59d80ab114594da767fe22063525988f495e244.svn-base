$(function() {

	var proId = window.location.search;

	if (proId == null || proId == '') {
		return;
	}

	var id = proId.substr(4);
	
	
	
	ajaxSelect(id) 

   

})

function ajaxSelect(id) {

	$.ajax({
		url : "/bigBuyerComment/queryList.do",
		type : "post",
		traditional : true,
		data : {
			'id' : id
		},
		datatype : "json",
		success : function(result) {

			if (result.state == 0) {

				var bigBuyerList = result.data.bigBuyerList;
				if (bigBuyerList) {
					var bigBuyer = bigBuyerList[0]
					var title = bigBuyer.title;
					var buyerId = bigBuyer.buyerId;
					var products = bigBuyer.products;
					var requirement = bigBuyer.requirement;
					var isComment = bigBuyer.isComment;
					var buyerInfo = bigBuyer.buyerInfo;
					var comment = bigBuyer.comment;
					var factoryInfo = bigBuyer.factoryInfo;
					var special    = bigBuyer.special
					
					$('#big_buyer_id').val(buyerId)
					
					
					 queryAll(1,buyerId);
					

					$('.buyerTitle').text(title ? title : "")
					if (isComment == 0) {
						$('#isComment').text('未点评')

					} else if (isComment == 1) {
						$('#isComment').text('已点评')
						$('#comment').find('.textarea_div').text(comment)
						$('#comment').show()
						
					}

					if (factoryInfo && factoryInfo.factoryLogo) {
						$('#factoryLogo').attr('src',  '/static_img/factory_logo/'+bigBuyer.buyerId+'/'+factoryInfo.factoryLogo)

					}
					$('#buyerInfo').text(buyerInfo ? buyerInfo : "")

					if (products) {
						var addproducts = '<dt>采购产品</dt>'
						var products = products.split("，");
						for (var j = 0; j < products.length; j++) {

							var index = j + 1;
							if (j == products.length - 1) {
								addproducts += '<dd>' + index + '、&nbsp;'
										+ products[j] + '。 </dd>';
							} else {
								addproducts += '<dd>' + index + '、&nbsp;'
										+ products[j] + '; </dd>';
							}

						}
						
						$('#products').html(addproducts)

					}
					
                    if (requirement) {
   					 
						var requireHtml = '<dt>采购商对于供应商的要求</dt>'
						
						var requirements = requirement.split(",");

						
						for (var k = 0; k < requirements.length; k++) {

							var indexK = k + 1;
							if (k == requirements.length - 1) {
								requireHtml += '<dd>' + indexK
										+ '、&nbsp;'
										+ requirements[k]
										+ '。 </dd>';
							} else {
								requireHtml += '<dd>' + indexK
										+ '、&nbsp;'
										+ requirements[k]
										+ '; </dd>';
							}
						}
						
						$('#requirement').html(requireHtml)
					
					}
                    
                    if(special){
                    	
                    	var atml = '<dt>采购商特点</dt><dd>'+special+'</dd>';
                    	
                    	$('#special').html(atml)
                    }
					
				}
				
				

			}
		}
	})

}

var currentPage = 1;

// 查询所有正常询盘
function queryAll(page, big_buyer_id) {


	
	$.post("/inquiry/queryInquiryList.do",
					{
						"page" : page,
						"pageSize" : 8,
						"bigBuyerId" : big_buyer_id
					},
					function(result) {
						if (result.state == 0) {
							var inquiryOrders = result.data.inquiryOrders;
							// var productNames = result.data.productNames;
							var totalOrder = result.data.totalOrder;
							var totalPage = Math.ceil(totalOrder / 8);
							
							

							var tl = inquiryOrders.length;
							$('#tbody1').empty();
							$('#tbody2').empty();
							for (var i = 0; i < tl; i++) {
						  

								var tr =  '<tr><td>'
                                
                                +'<div class="imgs1"><img src="../images/zg.png" alt=""/></div>'
                                +'<div class="imgs2"><img src="'+((inquiryOrders[i].drawingPathCompress)? inquiryOrders[i].drawingPathCompress:'../images/pic2.png')+'" alt=""/></div>'
                                +'<a href="/zh/detail.html?orderId='
								+ inquiryOrders[i].orderId+'" class="name_a" title="'
								+ (inquiryOrders[i].quoteTitle?inquiryOrders[i].quoteTitle:inquiryOrders[i].productName)
					            +'">'
					            
					            
					            + (inquiryOrders[i].quoteTitle?inquiryOrders[i].quoteTitle:inquiryOrders[i].productName)
								+'</a> </td><td><span>'
								+ (inquiryOrders[i].mainProcess?inquiryOrders[i].mainProcess:'')
								+ '</span>'
								+ ' <em>'+ (inquiryOrders[i].annualQuantity?inquiryOrders[i].annualQuantity:"")+'</em></td>'
                                +'<td>'+(new Date(inquiryOrders[i].publishDate.replace(/-/g,"/").split(".")[0])).Format("yyyy-MM-dd")+'</td>'
                                +'<td>'+(inquiryOrders[i].state == 1 ? '江浙沪':(inquiryOrders[i].state == 2 ? '深圳、广东、福建':'不限'))+'</td>'
								+'<td><a href="/zh/detail.html?orderId='+ inquiryOrders[i].orderId+'" class="bj">马上报价</a></td> </tr>'
								
								
								
								
								if (i % 2 == 0) {
									$('#tbody1').append(tr);
								} else if (i % 2 == 1) {
									$('#tbody2').append(tr);
								}

							}
							var buyerId = $('#big_buyer_id').val()
							
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
										queryAll(obj.curr,buyerId);
										
										
									}
									
								}
							})
							
//							compare();
							
							/* table 隔行换色*/
							$('.buy_detail tbody tr:even').css({
								'background' : '#f9f9f9'
							})
						}
					})
}

//// 根据工艺筛选
//function queryByProcess(obj) {
//	var big_buyer_id = $('#big_buyer_id').val()
//	queryAll(1, big_buyer_id);
//}
//// 根据工艺筛选
//function queryByKey(obj) {
//	var big_buyer_id = $('#big_buyer_id').val()
//	queryAll(1, big_buyer_id);
//}

//function queryByPage(page) {
//
//	if (page == currentPage) {
//		return false;
//	} else {
//		currentPage = page;
//		var big_buyer_id = $('#big_buyer_id').val()
//		queryAll(page, big_buyer_id);
//	}
//}

// 根据选择的checkbox添加收藏
function collect_inquiry() {
	var orderIds = '';
	$('#total_div').find('tbody').find('input:checkbox').each(function() {
		if ($(this).is(':checked')) {
			var orderId = $(this).next().val();
			orderIds += orderId + ",";
		}
	})

	if (orderIds == '') {
		return false;
	}

	$.post("/inquiry/addCollect.do", {
		"orderIds" : orderIds
	}, function(result) {
		if (result.state == 0) {
			easyDialog.open({
				container : {
					content : '收藏成功'
				},
				overlay : false,
				autoClose : 1000
			});
		} else {
			easyDialog.open({
				container : {
					content : '收藏失败'
				},
				overlay : false,
				autoClose : 1000
			});
		}
	})
}

// 查询订单详情
function queryDetails(orderId) {
	top.location = "/zh/detail.html?orderId=" + orderId;
}

function compare() {
	/* 侧边栏长度控制效果 */
	var h1 = $(document.body).height();
	var h2 = window.screen.availHeight;
	if (h1 < h2) {
		$('#footer').addClass('footer1');
	} else {
		$('#footer').removeClass('footer1');
	}
}
