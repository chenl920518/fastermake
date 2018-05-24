$(function() {

	var proId = window.location.search;

//	if (proId != null && proId.trim() != '') {
//		var big_buyer_id = proId.substr(4);
//		ajaxSelect(1,big_buyer_id)
//		
//		return;
//	}

	
	
	ajaxSelect(1)
	
	
	

})

function ajaxSelect(currpage,id) {

	$
			.ajax({
				url : "/bigBuyerComment/queryList.do",
				type : "post",
				traditional : true,
				data : {
					'currpage' : currpage,
					'id' :id
				},
				datatype : "json",
				success : function(result) {

					if (result.state == 0) {

						$('#content-box').html('')
						var bigBuyerList = result.data.bigBuyerList;

						if (bigBuyerList) {
							for (var i = 0; i < bigBuyerList.length; i++) {

								var bigBuyerComment = bigBuyerList[i];
								var addhtml = '	<div class="panel panel-default row box_01">'
										+ '<div class="col-xs-8 col-md-8">'
										+ '<div class="col-xs-12">'
										+ '<div class="col-xs-8">'
										+ '<div class="buyer_box">'
										+ '<a href="###" class="a_img">';

								if (bigBuyerComment.factoryInfo
										&& bigBuyerComment.factoryInfo.factoryLogo) {
									addhtml += '<img src="/static_img/factory_logo/'+ bigBuyerComment.buyerId+'/'	
									
									+bigBuyerComment.factoryInfo.factoryLogo
									
									+'" style="width:100%;height:100%;">'
								} else {
									addhtml += '<img src="../images/web_log.png" style="width:100%;height:100%;">';
								}

								addhtml += '</a>'
										+ '<p class="big-buyer-title">'
										+ '<a class="haizol-gray-link" href="###">'
										+ bigBuyerComment.title + '</a><br/>'
										+ '</p>' + '</div>' + '	</div>'
										+ '	<div class="col-xs-4 comm_hand">'

								if (bigBuyerComment.isComment) {
									var isComment = bigBuyerComment.isComment;
									if (isComment == 1) {
										addhtml += '<span class="span_02">已点评</span>';

									}
								} else {
									addhtml += '<span class="span_02">未点评</span>';
								}

								addhtml += '</div>' + '</div>'
										+ '<div class="col-xs-12 buyer-cont">'
										+ bigBuyerComment.buyerInfo + ' </div>'

								if (bigBuyerComment.comment) {

									addhtml += '<div class="row">'
											+ '<div class="col-xs-12 comment-01">'
											+ '<h5 class="col-xs-12 buyer-comment">采购商点评</h5>'
											+ '<div class="col-xs-12 comment-box">'
											+ bigBuyerComment.comment
											+ '</div></div> </div>'
								}

								addhtml += '</div>'

										+ ' <div class="col-xs-4 col-md-4" style="padding-left:40px;">'

								if (bigBuyerComment.products) {

									addhtml += '<div style="margin-bottom:10px;">'
											+ '<p class="simple-title">采购产品</p>';
									var products = bigBuyerComment.products
											.split("，");

									var productHtml = '';
									for (var j = 0; j < products.length; j++) {

										var index = j + 1;
										if (j == products.length - 1) {
											productHtml += '<p>' + index
													+ '、&nbsp;' + products[j]
													+ '。 </p>';
										} else {
											productHtml += '<p>' + index
													+ '、&nbsp;' + products[j]
													+ '; </p>';
										}
									}
									addhtml += productHtml;

									addhtml += '<p>&nbsp;</p>' + '</div>'

								}

								if (bigBuyerComment.requirement) {
									addhtml += ' <div style="margin:10px 0;">'
											+ '	<p class="simple-title">采购商对于供应商的要求</p>'
									var requirements = bigBuyerComment.requirement
											.split(",");

									var requireHtml = '';
									for (var k = 0; k < requirements.length; k++) {

										var indexK = k + 1;
										if (k == requirements.length - 1) {
											requireHtml += '<p>' + indexK
													+ '、&nbsp;'
													+ requirements[k]
													+ '。 </p>';
										} else {
											requireHtml += '<p>' + indexK
													+ '、&nbsp;'
													+ requirements[k]
													+ '; </p>';
										}
									}
									addhtml += requireHtml;

									addhtml += '</div>'
								}
								
								
								if(bigBuyerComment.special){
									
									addhtml+='<div style="margin:30px 0;">'
										+'<p class="simple-title">采购商特点</p>'
										+'<p>'+bigBuyerComment.special+'</p>'							
										+'</div>'
									
								}
								
								addhtml += '<div class="div_btn">'
										//+ '<a class="btn btn-primary" href="/bigBuyerComment/bigBuyerQuoteList.do?id='+bigBuyerComment.buyerId+'" target="_blank">申请加入</a>'
									    + '<a class="btn btn-primary" href="/zh/buy_detail.html?id='+bigBuyerComment.id+'" target="_blank">申请加入</a>'
										+ '</div></div></div>';

								$('#content-box').append(addhtml);

								if (result.data.totalOrder) {

									var totalOrder = result.data.totalOrder;

									var totalPage = Math.ceil(totalOrder / 5);


									laypage({
										cont : 'biuuu_link',
										pages : totalPage,
										skin : '#006DCC', // 皮肤
										last :  totalPage,
										groups : 5,
										prev : '<', // 若不显示，设置false即可
										next : '>',// 若不显示，设置false即可
										curr : location.hash.replace(
												'#!fenye=', ''), // 获取hash值为fenye的当前页
										hash : 'fenye', // 自定义hash值
										jump : function(obj,first) {
                                            if(!first){
                                            	ajaxSelect(obj.curr);
                                            }
											
										}
									})

								}
//								addcss()

							}
						}

					}
				}
			})
}

function addcss(){
	var h1 = $(document.body).height()  +230 ;
    var h2 = window.screen.availHeight   ;
    if(h1 < h2){
        $('#footer').addClass('footer1')
    }else{
        $('#footer').removeClass('footer1');
    };	
	
}
