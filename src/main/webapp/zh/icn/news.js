var factoryId = getCookie("F_ID");
if(factoryId != null && factoryId != ''){
	var base = new Base64();
	factoryId = base.decode(factoryId);
}


$(function() {

	var temp = window.location.search;

	
	if (temp != null && temp != '') {

		var status = temp.substr(8);

	    
		lightButton(status)
		
		var url = location.href;
		if(url.indexOf('?status=')>0){
			newUrl = url.substring(0, url.indexOf('?status='))
			window.history.pushState({}, 0, newUrl);
		}
		ajaxSelect(1)

	}

	ajaxSelect(1)

	$('#chooseStatus li').click(function() {
		$('#selectKey').val('')
		showPage1()
		var index = $("#chooseStatus li").index(this);

		$('#status').val(index)

		var url = location.href;
		if( url.indexOf('#!fenye')>0){
			 newUrl = url.substring(0, url.indexOf('#!fenye'))
			 window.history.pushState({}, 0, newUrl);
		}
		ajaxSelect(1)

	})
		
})

function selectByKey() {

	var temp = $('#selectKey').val()
	if (!temp) {
		return false;
	}

	var url = location.href;
	if(url.indexOf('#!fenye')>0){
		newUrl = url.substring(0, url.indexOf('#!fenye'))
		window.history.pushState({}, 0, newUrl);
	}


	ajaxSelect(1)

}

function ajaxSelect(currpage) {

	var selectKey = '';

	selectKey = $('#selectKey').val()

	var status = $('#status').val();

	$
			.ajax({
				url : "/noteMessage/queryList.do",
				type : "post",
				traditional : true,
				data : {
					'currpage' : currpage,
					'status' : status,
					'selectKey' : selectKey
				},
				datatype : "json",
				success : function(result) {

					if (result.state == 0) {

						$('#messageTable').html('')
						$('#biuuu_link').html('')

						var noteMessageList = result.data.noteMessageList;
						var factoryId = result.data.factoryId

						if (noteMessageList && noteMessageList.length > 0) {

							var tempHtml = ''
							for (var i = 0; i < noteMessageList.length; i++) {
								var noteMessage = noteMessageList[i]
								var sendId = noteMessage.sendId
								var receiverId = noteMessage.receiverId
								var receiverInfo = noteMessage.receiverInfo
								var sendInfo = noteMessage.sendInfo

								
								//判断是否有checkbox，系统消息和已发送消息没有checkbox
								var checkbox = '';
								var td = '';
								var type = '';
								if (receiverId && receiverId != factoryId && receiverInfo) {								
									td = '<td><span class="w140">'
											+ (receiverInfo.factoryName ? receiverInfo.factoryName
													: "") + '</span></td>';
									type = "（已发送）";
								}else if (sendId && sendId != factoryId && sendInfo) {
									checkbox = '<input type="checkbox"/>';
									td = '<td><span class="w140">'
												+ (sendInfo.factoryName ? sendInfo.factoryName
														: "") + '</span></td>';
									type = "（已收件）";
								}else{
									checkbox = '<input type="checkbox"/>';
									td = '<td><span class="w140">系统消息</span></td>';
									type = "（已收件）";
								}
								
								if (noteMessage.isRead == 0
										&& noteMessage.receiverId == factoryId) {
									tempHtml += '<tr class="wd" receiver_id="'
											+ receiverId
											+ '" id="'
											+ noteMessage.id
											+ '" dialogueid="'
											+ (noteMessage.dialogueId ? noteMessage.dialogueId
													: "")
											+ '">' 
											+ '<td>'+checkbox+'</td>'  
											+ '<td><i class="iconfont color_006dcc">&#xe61b;</i>'+type+'</td>'  
								} else {
									tempHtml += '<tr id="'
											+ noteMessage.id
											+ '" receiver_id="'
											+ receiverId
											+ '" dialogueid="'
											+ (noteMessage.dialogueId ? noteMessage.dialogueId
													: "")
											+ '">'
											+ ' <td>'+checkbox+'</td>' 
											+ ' <td><i class="iconfont">&#xe60f;</i>'+type+'</td>'
								}

	
								tempHtml += td;
								tempHtml += '<td><span class="w380">'
										+ (noteMessage.messageTitle ? noteMessage.messageTitle
												: "")
										+ '</span></td>'
										+ '<td><em>'
										+ (noteMessage.createTime ? (noteMessage.createTime
												.substr(0, 10))
												: "") + '</em></td></tr>'
							}
							$('#messageTable').html(tempHtml)

						}

						if (result.data.totalOrder) {

							var totalOrder = result.data.totalOrder;

							var totalPage = Math.ceil(totalOrder / 8);

							laypage({
								cont : 'biuuu_link',
								pages : totalPage,
								skin : '#006DCC', // 皮肤
								last : totalPage,
								groups : 5,
								prev : '<', // 若不显示，设置false即可
								next : '>',// 若不显示，设置false即可
								curr : location.hash.replace('#!fenye=', ''), // 获取hash值为fenye的当前页
								hash : 'fenye', // 自定义hash值
								jump : function(obj, first) {
									if (!first) {

										ajaxSelect(obj.curr);
									}
								}
							})

						}

						$('#messageTable tr td').not("td:first-child").click(
								function() {
                                    
									
									
									$('.page1').hide()

									$(this).parents('tr').removeClass('wd').find('i')
											.removeClass('color_006dcc').html(
													'&#xe60f;')

									var dialogueId = $(this).parents('tr').attr('dialogueid')
									var id = $(this).parents('tr').attr('id')
									var receiverId = $(this).parents('tr').attr('receiver_id')
									ajaxSelectdialogue(dialogueId, id,
											receiverId)
									$('.page2').show()

								})

//						addcss()

							//选中个数决定全选框是否选中
//							$('.news table input').on('click',function(){
//								var allLendth = $('.news table tbody tr').length;
//								var checkedLength = $('.news table input:checked').length;
//								if(checkedLength == allLendth){
//									$('.news .all_select input').prop('checked',true);
//								}else{
//									$('.news .all_select input').prop('checked',false);
//								}
//							})		

							
							//判断如果select是否全选状态
							var This = $('.all_select').find("input[type='checkbox']");
							selectAll(This);
					}
				}
			})
}

function chooseStatus() {

	var url = location.href;
	if(url.indexOf('#!fenye')>0){
		newUrl = url.substring(0, url.indexOf('#!fenye'))
		window.history.pushState({}, 0, newUrl);
	}

	
	lightButton()
	$('#selectKey').val('')
	ajaxSelect(1)

}

function ajaxSelectdialogue(dialogueId, id, receiverId) {

	if (!dialogueId) {
		return false;
	}

	$
			.ajax({
				url : "/noteMessage/selectDetail.do",
				type : "post",
				traditional : true,
				data : {
					'dialogueId' : dialogueId,
					'id' : id,
					'receiverId' : receiverId
				},
				datatype : "json",
				success : function(result) {

					if (result.state == 0) {
						$('#messageBody').html('')
						var noteMessageList = result.data.noteMessageList;
						if (noteMessageList && noteMessageList.length > 0) {
							for (var i = 0; i < noteMessageList.length; i++) {
								var noteMessage = noteMessageList[i]
								var messageTitle = noteMessage.messageTitle
								var createTime = noteMessage.createTime
								var messageDetails = noteMessage.messageDetails
								var url = noteMessage.url
								var fileName = noteMessage.fileName
								var filePath = noteMessage.filePath
								var receiverInfo = noteMessage.receiverInfo
								var sendId = noteMessage.sendId
								var receiverId = noteMessage.receiverId
								var sendInfo = noteMessage.sendInfo

								var receiverName = receiverInfo.username
								if(sendInfo){
									var sendName = sendInfo.username
								}else{
									sendName = "系统消息"
								}
							

								if (i == 0) {

									if (sendId && sendId == factoryId) {
										$('#replyToName').text(
												receiverName ? receiverName
														: "")
										$('#reply_form').find(
												'input[name=receiverId]').val(
												noteMessage.receiverId)
										$('#reply_form').find(
												'input[name=sendId]').val(
												noteMessage.sendId)

									}
									if (receiverId
											&& receiverId == factoryId) {
										$('#replyToName').text(
												sendName ? sendName : "")

										$('#reply_form').find(
												'input[name=receiverId]').val(
												noteMessage.sendId)
										$('#reply_form').find(
												'input[name=sendId]').val(
												noteMessage.receiverId)	  
									}
									$('#reply_form').find(
											'input[name=dialogueId]').val(
											noteMessage.dialogueId)
									$('#reply_form').find(
												'input[name=orderId]').val(
												noteMessage.orderId)
											

									$('#reply_form')
											.find('input[name=messageTitle]')
											.val(
													"Re:"
															+ (noteMessage.messageTitle ? noteMessage.messageTitle
																	: ""))

								}

								var showTime = '';

								if (createTime) {

									showTime = '时 间：' + createTime.substr(0, 4)
											+ '年' + createTime.substr(5, 2)
											+ '月' + createTime.substr(8, 2)
											+ '日' +

											createTime.substr(11, 2) + ':'
											+ createTime.substr(14, 2)
									// + '('
									// + (week ? week : "") + ')'

								}

								var addMessage = $('#tempDivId .tempDiv')
										.clone()

								addMessage.find('.messageTitle').text(
										messageTitle ? messageTitle : "")
								addMessage
										.find('.sendName')
										.text(
												sendInfo ? (sendInfo.username ? sendInfo.username
														: "")
														: sendName)
								addMessage
										.find('.receiverName')
										.text(
												receiverInfo ? (receiverInfo.username ? receiverInfo.username
														: "")
														: "")

								addMessage.find('.createTime').text(showTime)

								addMessage.find('.messageDetails').html(
										messageDetails)

								if (fileName && filePath) {

									addMessage
											.find('.messageFile')
											.html(
													'附件:<a href="javaScript:void(0);" dataid='
															+ noteMessage.id
															+ ' onclick="download_file(this)">'
															+ fileName + '</a>')
								}

								if (url) {
									if(factoryId != sendId || sendId == '0'){
										var detailurl = '<a href ="'
												+ url
												+ '" target="_blank" style="cursor: pointer;">详情请点击</a>'
	
										addMessage.find('.messageUrl').html(
												detailurl)											
									}		
								}

								if (i > 0) {
									$('#messageBody')
											.append(
													'<div class="original">------------ 原始信息 ------------</div>')
								}
								$('#messageBody').append(addMessage.show())

							}
						}
//					   addcss()
					}else if(result.state == 2){
			    		 //如果还未登录，跳转登录页
			    		 window.location = "/zh/login.html";
			        }     

				}
			})

}

var today = new Array('星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六');

var flag = false;

function getWeek(index) {

	return today[index]

}

function showPage1() {
	$('#totalDiv').removeClass('message_reply')
	$('.page1').show()
	$('.page2').hide()
	$('.page3').hide()
//	addcss()
}

function showPage3() {
	$('.page1').hide()

	var messageBody = $('#messageBody').html()
	$('#replayMessageBody').html(messageBody)
	$('.page2').hide()
	$('#totalDiv').addClass('message_reply')

	$('.page3').show()
//    	addcss()
	flag = true
}

function showPage2() {
	$('.page1').hide()
	$('#totalDiv').removeClass('message_reply')
	$('.page2').show()
	$('.page3').hide()
//	addcss()
}

function appendZero(s) {
	return ("00" + s).substr((s + "").length);
}



function addcss() {
	/* body 高度控制底部位置 */
//	var h1 = $(document.body).height();
	var h1 = $('#totalDiv').height()
	var h2 = window.screen.availHeight;
	if (h1 < h2) {
		$('#footer').addClass('footer1')
	} else {
		$('#footer').removeClass('footer1');
	}
	
	/* 根据邮件状态和颜色，控制本行字体颜色 */
	$('tbody .wd').find('span,em').css({
		'color' : '#000'
	});
	/* 隔行换色效果 */
	$('table tbody tr:even').css({
		'background-color' : '#f9f9f9'
	})
}

function replyMessage() {
	$('#detailIsNull').removeClass('ff4').text('')

	var tempValue = $('#reply_form').find('textarea[name=messageDetails]')
			.val()

	if (!tempValue) {
		$('#detailIsNull').addClass('ff4').text('请填下邮件内容')
		return false;
	}

	if (!flag) {
		return false;
	}
	flag = false;

	var formdata = $('#reply_form').serialize()

	$.ajax({
		url : "/noteMessage/replyMessage.do",
		type : "post",
		traditional : true,
		data : formdata,
		datatype : "json",
		success : function(result) {
			if (result.state == 0) {

				easyDialog.open({
					container : {
						header : '请注意',
						content : result.message
					},
					callback : function() {
						window.location = result.data;
					},
					overlay : false,
					autoClose : 1500
				});
				
				
		       //回复消息时发送微信提醒
    			 $.ajax({
    					url : "/wimpl/message_reply.do",
    					type : "post",
    					traditional : true,
    					data : formdata,
    					datatype : "json",
    					success : function(result) {
    						
    					}
    			 })


			}else if(result.state == 2){
	    		 //如果还未登录，跳转登录页
	    		 window.location = "/zh/login.html";
	        }else {

				easyDialog.open({
					container : {
						header : '请注意',
						content : result.message
					},
					overlay : false,
					autoClose : 1500
				});

			}
		}
	})

}
/*
 * 检测文件大小
 */
function getFilesize(file) {

	fileSize = file.files[0].size / 1024;
	if (fileSize > 10240) {
		return false;
	} else {
		return true;
	}

}

function show_drawingName(obj) {

	var fileUploadForm = $(obj).parents('form');

	fileUploadForm.find('input[name="fileName"]').val('')

	if (!getFilesize(obj)) {

		easyDialog.open({
			container : {
				header : '请注意',
				content : '请上传小于10M的文件'
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

	fileUploadForm
			.ajaxSubmit({
				type : "post",
				url : "/upload/uploadFileAndChangeName.do",
				dataType : "text",
				async : false,
				success : function(str) {
					var result = eval('(' + str + ')');
					if (result.state == 0) {

						fileUploadForm.find('input[name="filePath"]').val(
								result.data)

						$(obj)
								.parent()
								.next()
								.next()
								.html(
										'<em onclick="deletefile(this)"><i class="iconfont">&#xe643;</i>')
								.prepend(drawingName).show();

					} else {
						easyDialog.open({
							container : {
								header : '请注意',
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
							header : '请注意',
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

function download_file(obj) {
	var dataid = $(obj).attr('dataid')
	if (dataid) {

		window.location = "/download/messageFiledownload.do?id="
				+ dataid;
	}
}


function uploadProduct(obj) {

	$(obj).next()[0].click();
	return false;

}

function lightButton(status){
	if(status){
		$('#status').val(status)
	}

	var status = $('#status').val()

	var tabcon = $('#chooseStatus').find('li').eq(status)

	tabcon.children('span').slideDown();
	tabcon.siblings().children('span').hide();
	tabcon.children('a').css({
		'color' : '#006dcc'
	});
	tabcon.siblings().children('a').css({
		'color' : '#333'
	})

}



// 全选和反选
function selectAll(obj){
	var isChecked = $(obj).prop('checked');
	if(isChecked){
		$('.news table input').prop('checked',true);
	}else{
		$('.news table input').prop('checked',false);
	}
}


//选择已读、未读，更新消息状态
function select_read_status(obj){	
	
	 var isRead = $(obj).val();
	 if(isRead == '' || isRead == undefined){
		 return false;
	 }	
	//获取要更新消息的ids
	 var ids = [];		 
	 if($('.all_select').find('input[type="checkbox"]').is(":checked")){
		 ids = [];
	 }else{
		 $('#messageTable').find('input[type="checkbox"]').each(function(){
			 if($(this).is(":checked")){
				 var id = $(this).parents('tr').attr('id');
				 if(!isNaN(id)){
					ids.push(id);
				 }
			 }
		 })
	 }	 
	 var jsonArray = JSON.stringify(ids);	 
	 
     $.post("/noteMessage/updateIsReadBatch.do",
					{"jsonArray" : jsonArray,
    	             "isRead" : isRead
					},
					 function(result){
					      if(result.state == 0){
					    	  window.location.reload();
					      }
					})
}


