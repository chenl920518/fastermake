$(function() {

	ajaxQuoteList();

	ajaxBigBuyer();

	// 判断供应商是否登录，登录后显示完善资料和匹配询盘
	var userInfo = getCookie("factoryInfo");
	if (userInfo == null || userInfo == '') {
		$('#s_div')
				.append(
						'<a href="/en/login.html" class="active">SIGN IN</a><a href="/en/register_en.html">REGISTER</a>');
	} else {
		$('#s_div')
				.append(
						'<a href="/en/quote_list.html" class="active">Inquiry</a><a href="/en/edit_enterprise_archives.html">Edit Archives</a>');

	}
	
	
	
		/*视频播放控制*/
    $('.home .bf_botton').click(function(){
     
      
    })
    
    
    
    $('.home .close_button').click(function(){
      $(this).parent().hide();
    })

})

function ajaxQuoteList() {

	$
			.ajax({
				url : "/inquiry/queryInquiryListToIndex.do",
				type : "post",
				traditional : true,
				data : {
					'page' : 1,
					'pageSize' : 8

				},
				datatype : "json",
				success : function(result) {

					if (result.state == 0) {
                   
						if (result.data.inquiryOrders) {
							for (var i = 0; i < result.data.inquiryOrders.length; i++) {
								var quoteProduct = result.data.inquiryOrders[i];
								//获取国家国旗
						     	 var country = quoteProduct.country;	
						    	 var flagSrc=getFlag(country);
								
								
								var newQuote = '<li>'
										+ '<div class="top">'

										+ '<img src="'+flagSrc+'" alt=""/>'

//										+ '<a href="/inquiry/details.do?orderId='
//										+ quoteProduct.orderId
//										+ '">'
//										+ quoteProduct.orderId
//										+ '</a>'
										+ '<span>'
										+ (quoteProduct.mainProcess == null ? ''
												: quoteProduct.mainProcess)
										+ '</span>'
										+ '<em class="xpdate">'+(quoteProduct.publishDate == null ? '' : (new Date(quoteProduct.publishDate.replace(/-/g,"/").split(".")[0])).Format("yyyy-MM-dd"))+'</em>'
										+ '</div>'
										+ '<div class="center">'
										+ '<div class="div_video">'
										+ '	<video src="'+(quoteProduct.videoPath == null ? '' : quoteProduct.videoPath)+'" autoplay="" controls="" loop=""></video>'
										+ '	<div class="close_button" onclick="closeVideo(this)"><span class="iconfont">&#xe643;</span></div>'
										+ '</div>'
										+ '<img src="'
										+ ((quoteProduct.drawingPathCompress == null || quoteProduct.drawingPathCompress == '') ? '/images/pic2.png'
												: quoteProduct.drawingPathCompress)
										+ '"  onclick="queryDetail('+quoteProduct.orderId+')" alt=""/>'
										+ (quoteProduct.videoPath == null || quoteProduct.videoPath == '' ? '' : '<div class="bf_botton" onclick="playVideo(this)"><span class="iconfont">&#xe626;</span><a title="点击观看3D"></a></div>')
										+ ' </div>'
										
										
										+ '<div class="bottom">'
										+ '<div class="bot-left pull-left">'
										+ '<span>'+(quoteProduct.quoteTitle == null ? "" : quoteProduct.quoteTitle)+ '</span>'
										+ '<span>'+(quoteProduct.materials == null ? "" : quoteProduct.materials) + '</span>';
                                  
								if (quoteProduct.quantityList) {
									var quantityList = quoteProduct.quantityList
											.split(',');
									var quantityUnit = quoteProduct.quantityUnit
											.split(',');
									// for (var j = 0; j < quantityList.length;
									// j++) {

									newQuote += '<em>' + quantityList[0] + ' '
											+ quantityUnit[0] + '</em><br/>';

								}

								newQuote += '</div>'
									    + '<div class="bot-right pull-right">'
										+ '<a href="/inquiry/details.do?orderId='
										+ quoteProduct.orderId
										+ '" class="">Quote</a>'
										+ '<div>'
										+ '<i>Quote Factory:</i>'
										+ '<em><b>'+quoteProduct.currentNumber+'</b>/3</em>'
										+ '</div>'
										+ '</div>' 
										+ '</li>';

								$('#newQuote').append(newQuote)
							}
						}
						
						
						//获取一周询盘总价和日期
						var backWeekDate=getBackWeekDate();
						$('#estimatedDate').text("("+backWeekDate+")");
						$('#estimatedPrice').text("$"+Number((result.data.totalEstimatedPrice == 0 ? 630000 : result.data.totalEstimatedPrice)/6.3).toFixed(0));
					}
				
				}
			})
}

function ajaxBigBuyer() {

	$
			.ajax({
				url : "/bigBuyerComment/queryList.do",
				type : "post",
				traditional : true,
				data : {
					'currpage' : 1,
					'pageSize' : 8
				},
				datatype : "json",
				success : function(result) {
					if (result.state == 0) {

						var bigBuyerList = result.data.bigBuyerList;

						var userInfo = result.data.userInfo

						if (userInfo != null &&userInfo.factoryLogo != null) {

							$('#factoryLogo').attr(
									'src',
									(userInfo.factoryLogo ?('/static_img/factory_logo/'+ userInfo.factoryId + '/' +
									userInfo.factoryLogo) : ""))

						}

						if (bigBuyerList) {
							for (var i = 0; i < bigBuyerList.length; i++) {

								var bigBuyerComment = bigBuyerList[i];

								var newBigBuyer = '<li>'

										+ '<div class="top">'

										+ '<img src="/fastermak1.png" alt=""/>'
										+ '<span style="cursor:pointer"  src="/fastermaktail.html?id='
										+ bigBuyerComment.id
										+ '" onclick="jumpUrl(this)")">接受报名</span>'
										+ '</div>' + '<div class="center">'

								if (bigBuyerComment.factoryInfo
										&& bigBuyerComment.factoryInfo.factoryLogo) {
									newBigBuyer += '<img src="/static_img/factory_logo/'
											+ bigBuyerComment.buyerId
											+ '/'
											+ bigBuyerComment.factoryInfo.factoryLogo
											+ '" alt=""/>'
								} else {
									newBigBuyer += '<img src="/fastermakypurchase1.png" alt=""/>'
								}

								newBigBuyer += '</div>'
										+ '<div class="bottom">' + '<h4>'
										+ bigBuyerComment.title + '</h4>'

								if (bigBuyerComment.products) {
									newBigBuyer += '<p>采购产品:'
									var products = bigBuyerComment.products
											.split("，");
									for (var j = 0; j < products.length; j++) {
										newBigBuyer += ' <em> ' + products[j]
												+ '</em>'
									}
								}

								newBigBuyer += '</p></div>'

								+ '<div class="li-hover">'
								if (bigBuyerComment.requirement) {
									newBigBuyer += '<div class="words">'
											+ '<p>采购商对于供应商的要求</p>'
									var requirements = bigBuyerComment.requirement
											.split(",");
									for (var k = 0; k < requirements.length; k++) {
										var indexK = k + 1;
										if (k == requirements.length - 1) {
											newBigBuyer += '<span>' + indexK
													+ '、' + requirements[k]
													+ '。 </span><br/>';
										} else {
											newBigBuyer += '<span>' + indexK
													+ '、' + requirements[k]
													+ '； </span><br/>';

										}
									}

								}

								newBigBuyer += ' </div>'
										+ '<a href="/fastermaktail.html?id='
										+ bigBuyerComment.id + '" target="_blank">查看详情</a>'
										+ '</div>' + '</li>';

								$('#newBigBuyer').append(newBigBuyer)

								$('.buyer-purchase li').hover(
										function() {
											$(this).children('.li-hover')
													.addClass('active')
													.siblings().children(
															'.li-hover')
													.removeClass('active');
										},
										function() {
											$(this).children('.li-hover')
													.removeClass('active');
										})

							}

						}

					}
				}
			})
}

//查询详情
function queryDetail(orderId){
	window.location = "/inquiry/details.do?orderId="+orderId;
}


function jumpUrl(obj) {

	if ($(obj).attr('src')) {

		window.location = $(obj).attr('src');

	}
}



/*视频播放控制*/
function playVideo(obj){
	
	$(obj).siblings('.div_video').show();
    var div_h = $('.home .div_video').height();
	var video_h =  $(obj).parent().find('video').height();
    var m_t = (div_h - video_h)/2;
    $(obj).parent().find('video').css({'margin-top':m_t +'px'});	  
	$('#newQuote').find('.div_video').hide().removeAttr('autoplay');
	$(obj).prev().prev().show().find('video').attr('autoplay',true);
	
 	var $video = $(obj).prev().prev().show().find('video');
 	$video.get(0).play();
}
function closeVideo(obj){
	$(obj).parent().hide().find('video').removeAttr('autoplay');
 	var $video = $(obj).parent().find('video');
 	$video.get(0).pause();
}






Date.prototype.Format = function (fmt) { //author: meizz
	var o = {
	"M+": this.getMonth() + 1, //月份
	"d+": this.getDate(), //日
	"h+": this.getHours(), //小时
	"m+": this.getMinutes(), //分
	"s+": this.getSeconds(), //秒
	"q+": Math.floor((this.getMonth() + 3) / 3), //季度
	"S": this.getMilliseconds() //毫秒
	};
	if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for (var k in o)
	if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
	}


//获取系统前一周的时间
function getBackWeekDate(){
	var nowdate = new Date();
	var oneweekdate = new Date(nowdate-7*24*3600*1000);
	var y = oneweekdate.getFullYear();
	var m = oneweekdate.getMonth()+1;
	var d = oneweekdate.getDate();
	if(m.toString().length == 1){
	    m="0"+m;
	}
	if(d.toString().length == 1){
		d="0"+d;
	}
	var formatwdate = y+'-'+m+'-'+d;
	return formatwdate;
}




