$(function() {

	var proId = window.location.search;

	if (proId && proId.trim() != '') {
		var picId = proId.substr(4);
		$('#id').val(picId)

		ajaxSelect(picId)
	}

	var pro_judgment = false;
	var enpro_judgment = false;
	var process_judgment = false;
	var metails_judgment = false;
	var minquote_judgment = false;
	var min_aqmount_purchased_judgment = false;
	var industry_judgment = false;
	var piccheck_judgment = false;
	var unit_judgment = false;

	getInductry();
	getMainProcess();

	// 验证产品名是否为空

	$('#productName').blur(function() {
		$('#productName').next().css({
			"display" : "none"
		});
		var productName = $('#productName').val();
		if (productName.trim() != '') {
			$('#productName').addClass('success');
			pro_judgment = true;
		} else {
			$('#productName').next().addClass('ffb').text("产品名称不能为空").fadeIn();
			pro_judgment = false;
		}
		return false;
	})

	// 验证英文产品名是否为空
	$('#enProductName').blur(
			function() {
				$('#enProductName').next().css({
					"display" : "none"
				});
				var enProductName = $('#enProductName').val();
				if (enProductName.trim() != '') {
					$('#enProductName').addClass('success');
					enpro_judgment = true;
				} else {
					$('#enProductName').next().addClass('ffb').text(
							"英文产品名称不能为空").fadeIn();
					enpro_judgment = false;
				}
				return false;
			})

	// 验证加工工艺是否必选
	$('#mainCategory').blur(function() {
		$('#mainCategory').next().css({
			"display" : "none"
		});
		var mainCategory = $('#mainCategory').val();
		if (mainCategory.trim() != '') {
			$('#mainCategory').addClass('success');
			process_judgment = true;

		} else {
			$('#mainCategory').next().addClass('ffb').text("必须填写选项").fadeIn();
			process_judgment = false;
		}

		return false;
	})

	// 验证物料名称是否为空
	$('#materials').blur(function() {
		$('#materials').next().css({
			"display" : "none"
		});
		var materials = $('#materials').val();
		if (materials != ''&&materials!=null) {
			$('#materials').addClass('success');
			metails_judgment = true;
		} else {
			$('#materials').next().addClass('ffb').text("材料名称必选").fadeIn();
			metails_judgment = false;
		}
		return false;
	});

	// 验证价格是否为空/验证价格是否为数字
	$('#minquote').blur(function() {
		$('#minquoteError').css({
			"display" : "none"
		});
		var minquote = $('#minquote').val();

		if (minquote.trim() == '') {
			minquote_judgment = false;
			$('#minquoteError').addClass('ffb').text("价格不能为空").fadeIn();
			return false;

		}

		else if (!$.isNumeric(minquote)) {
			$('#minquoteError').addClass('ffb').text("价格必须为数字").fadeIn();
			minquote_judgment = false;
			return false;
		}

		$('#minquote').addClass('success');
		minquote_judgment = true;
		return false;
	});

	// 验证最低发货量是否为空
	$('#minAqmountPurchased').blur(
			function() {
				$('#minAqmountPurchased').next().css({
					"display" : "none"
				});
				var minAqmountPurchased = $('#minAqmountPurchased').val();
				if (minAqmountPurchased.trim() == '') {
					$('#minAqmountPurchased').next().addClass('ffb').text(
							"最低采购量不能为空").fadeIn();
					min_aqmount_purchased_judgment = false;

					return false;
				} else if (!$.isNumeric(minAqmountPurchased)) {
					$('#minAqmountPurchased').next().addClass('ffb').text(
							"最低采购量必须为数字").fadeIn();
					min_aqmount_purchased_judgment = false;
					return false;
				}

				$('#minAqmountPurchased').addClass('success');
				min_aqmount_purchased_judgment = true;
				return false;

			});

	$('#purchasedUnit').blur(function() {
		$('#purchasedUnit').next().css({
			"display" : "none"
		});
		var purchasedUnit = $('#purchasedUnit').val();
		if (purchasedUnit.trim() != '') {
			$('#purchasedUnit').addClass('success');
			unit_judgment = true;

		} else {
			$('#purchasedUnit').next().addClass('ffb').text("单位必选").fadeIn();
			unit_judgment = false;
		}

		return false;
	})

	// 光标切入事件，移除提醒
	$('#productName').focus(function() {
		$('#productName').next().removeClass('ffb');
		$('#productName').next().css({
			"display" : "none"
		});
	})
	$('#enProductName').focus(function() {
		$('#enProductName').next().removeClass('ffb');
		$('#enProductName').next().css({
			"display" : "none"
		});
	})

	$('#minquote').focus(function() {
		$('#minquoteError').removeClass('ffb');
		$('#minquoteError').css({
			"display" : "none"
		});
	})

	$('#materials').focus(function() {
		$('#materials').next().removeClass('ffb');
		$('#materials').next().css({
			"display" : "none"
		});
	})
	$('#minAqmountPurchased').focus(function() {
		$('#minAqmountPurchased').next().removeClass('ffb');
		$('#minAqmountPurchased').next().css({
			"display" : "none"
		});
	})

	$('#purchasedUnit').focus(function() {
		$('#purchasedUnit').next().removeClass('ffb');
		$('#purchasedUnit').next().css({
			"display" : "none"
		});
	})

	$('#mainCategory').focus(function() {
		$('#mainCategory').next().removeClass('ffb');
		$('#mainCategory').next().css({
			"display" : "none"
		});
	})

	$('#inductry').focus(function() {

		$('#inductry').next().removeClass('ffb');
		$('#inductry').next().css({
			"display" : "none"
		});
	})

	$('#inductry').click(function() {
		$(' .dxtc').toggle();
		return false;
	})

	$("body").click(function(e) {
		if (e.target.id.indexOf("inductry") == -1) {
			$(".dxtc").hide();
		}
	})

	$('.dxtc').click(function() {
		return false;
	})

	$('#saveButton').click(
			function() {

				$('#productName').blur();
				$('#enProductName').blur();
				$('#materials').blur();
				$('#minAqmountPurchased').blur();
				$('#minquote').blur();
				$('#mainCategory').blur();
				$('#picCheck').text('');
				$('#purchasedUnit').blur();
				var inductry = $('#inductry').val();

//				if (inductry.split('>').length > 2) {
//					industry_judgment = true;
//
//				} else {
//					industry_judgment = false;
//					$('#inductry').next().addClass('ffb').text("请选二级以上")
//							.fadeIn();
//				}
				
				if (inductry.split('>').length > 1) {
				industry_judgment = true;

			    } else {
				industry_judgment = false;
				$('#inductry').next().addClass('ffb').text("请选择所属行业")
						.fadeIn();
			    }
				

				if ($('.showDetail').size() < 1) {
					$('#picCheck').text('必须上传图片');
					piccheck_judgment = false;
				} else {
					piccheck_judgment = true;
				}

				if (!(pro_judgment && enpro_judgment && process_judgment
						&& min_aqmount_purchased_judgment && minquote_judgment
						&& industry_judgment && piccheck_judgment
						&& unit_judgment && metails_judgment)) {
					return false;
				}

				$('#create_form').submit();

			})

})

var mainProcessList = [ {
	name : "注塑"
}, {
	name : "吸塑"
}, {
	name : "滚塑"
}, {
	name : "吹塑"
}, {
	name : "挤塑"
}, {
	name : "其他塑料工艺"
}, {
	name : "不锈钢铸造(硅溶胶、水玻璃）"
}, {
	name : "压铸（铝、锌、镁、铜等）"
}, {
	name : "砂铸(钢、铁、铜、铝等)"
}, {
	name : "重力铸造（铝、铜等）"
}, {
	name : "其他铸造"
}, {
	name : "锻造",
	secondList : [ "热锻", "冷锻" ]
}, {
	name : "原料采购粗加工"
}, {
	name : "钣金加工（切割，折弯，焊接，组装）"
}, {
	name : "冲压拉伸"
}, {
	name : "旋压"
}, {
	name : "铝挤压"
}, {
	name : "机加工",
	secondList : [ "3轴以上精密加工中心", "高速车床", "高速车床", "普通车铣钻磨", "其他机加工" ]
}, {
	name : "现成商品采购"
}, {
	name : "其他采购"
} ];

function getMainProcess() {

	var mainProcess = $("#mainCategory");
	mainProcess.html('<option value="">请选择</option><option value=""></option>');
	$("#subCategory").html(
			'<option value="">请选择</option><option value=""></option>');

	for (var i = 0; i < mainProcessList.length; i++) {
		var selectli = '<option  value=' + mainProcessList[i].name + '>'
				+ mainProcessList[i].name + '</option>';
		mainProcess.append(selectli);
	}

}

function initSubCategory() {

	var subCategory = $("#subCategory");

	var mainCategory = $("#mainCategory").val();

	subCategory.html('<option value="">请选择</option><option value=""></option>');

	for (var i = 0; i < mainProcessList.length; i++) {

		if (mainProcessList[i].name == mainCategory
				&& mainProcessList[i].secondList) {

			for (var j = 0; j < mainProcessList[i].secondList.length; j++) {
				var selectli = '<option  value='
						+ mainProcessList[i].secondList[j] + '>'
						+ mainProcessList[i].secondList[j] + '</option>';
				subCategory.append(selectli);
			}

		}
	}
}

function show_drawingName(obj) {

	var path = $(obj).val();
	var index = Number($('#index').val());
	sppath = path.split("\\");
	var drawingName = sppath[sppath.length - 1];
	if (drawingName == null || drawingName == '' || drawingName == undefined) {
		return false;
	} else {
		$('#fileName').val(drawingName);
		autTime();
		$('#show_upload_dialog').show();

	}

	// 先上传后获取上传文件路径
	$("#create_form")
			.ajaxSubmit(
					{
						type : "post",
						url : "/upload/uploadProductPicChangeName.do",
						dataType : "text",
						success : function(str) {

							var result = eval('(' + str + ')');
							if (result.state == 0) {
								$('#picCheck').text('');
								index++;

								$('#index').val(index);

								var url = result.data.split(';');
								$('#filePath' + index).val(url[0]);
								$('#comprocessPath' + index).val(url[1]);

								// $('#temp').attr('src',temp[1]);
								var picShow = '<div class="col-xs-3 showDetail" >'
										+ '<a  href="###"> <img src="'
										+ url[1]
										+ '" alt="" class="img-responsive" datasource="'
										+ url[0]
										+ '" />'
										+ '<div class="sy">'
										// + '<input type="radio"
										// class="pull-left" name="pictureCover"
										// value='
										// + index
										// + ' "/> 设置为封面'
										+ '<span class="glyphicon glyphicon-trash" onclick="deletePic(this)"  ></span>'
										+ '</div>' + '</a></div>';

								$('#addpic').before(picShow);

								$('#addpic').remove();

								var addpicHtml = '<div id="addpic" class="col-xs-3 addpic">'
										+ '<a href="javascript:void(0);" class="lasta text-center"'
										+ 'onclick="uploadFile()"> <img src="../images/products/add.png" alt=""'
										+ 'class="img-responsive" /><br/> <em>上传图片</em>'
										+ '<input id="uploadFile" name="files" type="file"'
										+ 'accept=".png, .jpg, .jpeg,.gif, .bmp" style="display: none"'
										+ 'onchange="show_drawingName(this)" />'
										+ '</a></div>';

								$('#addLocation').before(addpicHtml)

								if ($('.showDetail').size() > 3) {
									$('#addpic').remove();

								}
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

// 上传多个文件时后返回产品路径，以逗号隔开
function show_drawingNames(obj) {

	var uploadFiles = $("#uploadFile")[0].files;

	var fileNames = '';
	for (var i = 0; i < uploadFiles.length; i++) {

		if (i == uploadFiles.length - 1) {
			fileNames += uploadFiles[i].name;
		} else {
			fileNames += uploadFiles[i].name + ';';
		}

	}
	if (fileNames == null || fileNames == '' || fileNames == undefined) {
		return false;

	}
	// else {
	// $('#fileNames').val(fileNames);
	// autTime();
	// $('#show_upload_dialog').show();
	//
	// }

	// 先上传后获取上传文件路径
	$("#create_form").ajaxSubmit({
		type : "post",
		url : "/upload/uploadProductPicAndChangeName.do",
		dataType : "text",
		async : false,
		success : function(str) {
			var result = eval('(' + str + ')');
			if (result.state == 0) {

				var data = result.data.split(';');

				$('#temp').arr('src', data[1]);

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

function uploadFile() {

	$('#uploadFile')[0].click();

}

/*
 * 
 * 三级联动
 * 
 */

// var inductryList = [ {
// name : '汽车用品',
// secondList : [ {
// name : '影音导航',
// thirdList : [ '车载对讲机', '车载免提' ]
// }, {
// name : '汽车内饰',
// thirdList : [ '车挂', '车用指南针' ]
// } ]
// }, {
// name : '宠物与园艺',
// secondList : [ {
// name : '狗狗及用品',
// thirdList : [ '狗粮', '狗窝' ]
// }, {
// name : '宠物环境清洁',
// thirdList : [ '空气清新', '禁区喷剂' ]
// } ]
// }
//
// ];
//
// function getInductry() {
//
// var inductry = $("#select_inductry");
// for (var i = 0; i < inductryList.length; i++) {
//
// var selectli = '<li onclick=getSecond(this) value=' + i + '>'
// + inductryList[i].name + '<span></span></li>';
// inductry.append(selectli);
// }
//
// }
//
// function getSecond(obj) {
//
// $(obj).css({
// "background-color" : "#006dcc",
// "color" : "#fff"
// }).siblings().css({
// "background-color" : "#fff",
// "color" : "#333"
// })
//
// $(obj).children('span').css({
// "background" : "url(../images/products/arr3.png)"
// }).siblings().children('span').css({
// "background" : "url(../images/products/arr2.png)"
// })
// var second = $("#select_second");
// var third = $("#select_third");
//
// second.empty();
// third.empty();
// secondList = inductryList[$(obj).val()].secondList;
//
// for (var i = 0; i < secondList.length; i++) {
// var selectli = '<li onclick=getThird(this) leaf=' + $(obj).val()
// + ' value=' + i + '>' + secondList[i].name
// + '<span></span></li>';
// second.append(selectli);
//
// }
//
// $('#inductry').val(inductryList[$(obj).val()].name + '>');
//
// }
//
// function getThird(obj) {
//
//	
//
// $(obj).css({
// "background-color" : "#006dcc",
// "color" : "#fff"
// }).siblings().css({
// "background-color" : "#fff",
// "color" : "#333"
// })
//
// $(obj).children('span').css({
// "background" : "url(../images/products/arr3.png)"
// }).siblings().children('span').css({
// "background" : "url(../images/products/arr2.png)"
// })
//
// var first = $(obj).attr('leaf');
// var second = $(obj).val();
//
// var third = $("#select_third");
// third.empty();
//
// var thirdList = inductryList[first].secondList[second].thirdList;
// third.length = 1;
//
// $('#inductry').val(
// inductryList[first].name + '>'
// + inductryList[first].secondList[second].name + '>');
//
// var inductry = $('#inductry').val();
// for (var i = 0; i < thirdList.length; i++) {
// var selectli = '<li onclick=getValues(this) first=' + first
// + ' second=' + second + ' >' + thirdList[i]
// + '<span></span></li>';
// third.append(selectli);
// }
//
// }
//
//
// function getValues(obj) {
// $(obj).css({
// "background-color" : "#006dcc",
// "color" : "#fff"
// }).siblings().css({
// "background-color" : "#fff",
// "color" : "#333"
// })
//
// $(obj).children('span').css({
// "background" : "url(../images/products/arr3.png)"
// }).siblings().children('span').css({
// "background" : "url(../images/products/arr2.png)"
// })
//
// var first = $(obj).attr('first');
// var second = $(obj).attr('second');
// var third = $(obj).text();
//
// $('#inductry')
// .val(
// inductryList[first].name + '>'
// + inductryList[first].secondList[second].name + '>'
// + third);
//
//	
//
// }

var inductryList = [ {
	name : '汽车用品',
	secondList : [ {
		name : '影音导航'
//		,thirdList : [ '车载对讲机', '车载免提' ]
	}, {
		name : '汽车内饰'
//		thirdList : [ '车挂', '车用指南针' ]
	} ]
}, {
	name : '宠物与园艺',
	secondList : [ {
		name : '狗狗及用品',
//		,thirdList : [ '狗粮', '狗窝' ]
	}, {
		name : '宠物环境清洁',
//		,thirdList : [ '空气清新', '禁区喷剂' ]
	} ]
}

];

function getInductry() {

	var inductry = $("#select_inductry");
	for (var i = 0; i < inductryList.length; i++) {

		var selectli = '<li onclick=getSecond(this) value=' + i + '>'
				+ inductryList[i].name + '<span></span></li>';
		inductry.append(selectli);
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

	$(obj).children('span').css({
		"background" : "url(../images/products/arr3.png)"
	}).siblings().children('span').css({
		"background" : "url(../images/products/arr2.png)"
	})
	var second = $("#select_third");
	

	second.empty();

	secondList = inductryList[$(obj).val()].secondList;

	for (var i = 0; i < secondList.length; i++) {
		var selectli = '<li onclick=getValues(this) leaf=' + $(obj).val()
				+ ' value=' + i + '>' + secondList[i].name
				+ '</li>';
		second.append(selectli);

	}
	$('#inductry').val(inductryList[$(obj).val()].name + '>');

}

//function getThird(obj) {
//
//	$(obj).css({
//		"background-color" : "#006dcc",
//		"color" : "#fff"
//	}).siblings().css({
//		"background-color" : "#fff",
//		"color" : "#333"
//	})
//
//	$(obj).children('span').css({
//		"background" : "url(../images/products/arr3.png)"
//	}).siblings().children('span').css({
//		"background" : "url(../images/products/arr2.png)"
//	})
//
//	var first = $(obj).attr('leaf');
//	var second = $(obj).val();
//
//	var third = $("#select_third");
//	third.empty();
//
//	var thirdList = inductryList[first].secondList[second].thirdList;
//	third.length = 1;
//
//	$('#inductry').val(
//			inductryList[first].name + '>'
//					+ inductryList[first].secondList[second].name + '>');
//
//	var inductry = $('#inductry').val();
//	for (var i = 0; i < thirdList.length; i++) {
//		var selectli = '<li onclick=getValues(this) first=' + first
//				+ ' second=' + second + ' >' + thirdList[i]
//				+ '<span></span></li>';
//		third.append(selectli);
//	}
//
//}

function getValues(obj) {
	$(obj).css({
		"background-color" : "#006dcc",
		"color" : "#fff"
	}).siblings().css({
		"background-color" : "#fff",
		"color" : "#333"
	})

	$(obj).children('span').css({
		"background" : "url(../images/products/arr3.png)"
	}).siblings().children('span').css({
		"background" : "url(../images/products/arr2.png)"
	})

	var first = $(obj).attr('leaf');
	var second = $(obj).text();
//	var third = $(obj).text();

	$('#inductry')
			.val(inductryList[first].name + '>'
							+ second);

}


$('#productName').blur(function() {
	$('#productName').next().css({
		"display" : "none"
	});
	var productName = $('#productName').val();
	if (productName.trim() != '') {
		$('#productName').addClass('success');
		pro_judgment = true;
	} else {
		$('#productName').next().addClass('ffb').text("产品名称不能为空").fadeIn();
		pro_judgment = false;
	}
	return false;
});

// 删除图片
function deletePic(obj) {

	var addpicHtml = '<div id="addpic" class="col-xs-3 addpic">'
			+ '<a href="javascript:void(0);" class="lasta text-center"'
			+ 'onclick="uploadFile()"> <img src="../images/products/add.png" alt=""'
			+ 'class="img-responsive" /><br/> <em>上传图片</em>'
			+ '<input id="uploadFile" name="files" type="file"'
			+ 'accept=".png, .jpg, .jpeg,.gif, .bmp" style="display: none"'
			+ 'onchange="show_drawingName(this)" />' + '</a></div>';

	if ($('.showDetail').size() == 4) {
		$('#addLocation').before(addpicHtml)
	}

	var url1 = $(obj).parent().prev().attr('datasource');
	var compressUrl1 = $(obj).parent().prev().attr('src');

	$(obj).parent().parent().parent().remove();

	$("input[id^=filePath]").val('');
	$("input[id^=comprocessPath]").val('');

	$('#index').val($('.showDetail').length);

	var picGroup = $('.showDetail')
	if (picGroup) {
		for (var i = 0; i < picGroup.length; i++) {
			var compressUrl = picGroup.eq(i).find('img').attr('src')
			var url = picGroup.eq(i).find('img').attr('datasource');
			var j = i + 1;
			$('#filePath' + j).val(url);
			$('#comprocessPath' + j).val(compressUrl);
		}

	}

	// $.ajax({
	// url : "/upload/deletePic.do",
	// type : "post",
	// data : {
	// 'url' : url1,
	// 'compressUrl' : compressUrl1
	// },
	// datatype : "json",
	// success : function(data) {
	//
	// }
	//
	// })

}

function ajaxSelect(id) {

	$
			.ajax({
				url : "/productManage/selectOneProduct.do",
				type : "post",
				traditional : true,
				data : {
					'picId' : id
				},
				datatype : "json",
				success : function(result) {

					if (result.state == 0) {

						$('#id').val(result.data.id);
						$('#factoryId').val(result.data.factoryId);
						$('#productName').val(result.data.productName)
						$('#enProductName').val(result.data.enProductName)
						$('#minquote').val(result.data.minquote)
						$('#minAqmountPurchased').val(
								result.data.minAqmountPurchased)
						$('#productInfo').val(result.data.productInfo)
						$('#purchasedUnit').val(result.data.purchasedUnit)

						
						if(result.data.materials){
							$('#materials').val(result.data.materials)
						}
						

						var process = []
						if (result.data.process) {
							process = result.data.process.split(';');
						}

						if (process.length > 1) {
							$('#mainCategory').val(process[0]);
							initSubCategory();
							$('#subCategory').val(process[1]);
						} else if (process.length > 0) {
							$('#mainCategory').val(process[0]);
						}
						$('#inductry').val(result.data.inductry);

						var drawingPathGroup = result.data.drawingPathGroup
								.split(';');
						var compressDrawingPathGroup = result.data.compressDrawingPathGroup
								.split(';');

						$('#index').val(drawingPathGroup.length);

						for (var i = 0; i < drawingPathGroup.length; i++) {

							var j = i + 1;
							$('#filePath' + j).val(drawingPathGroup[i]);
							$('#comprocessPath' + j).val(
									compressDrawingPathGroup[i]);
							var picShow = '<div class="col-xs-3 showDetail" >'
									+ '<a href="###"> <img src="'
									+ compressDrawingPathGroup[i]
									+ '" alt="" class="img-responsive" datasource="'
									+ drawingPathGroup[i] + '" />'
									+ '<div class="sy">';

							// if(drawingPathGroup[i]==result.data.drawingPath){
							// picShow+='<input type="radio" class="pull-left"
							// name="pictureCover" value='+ j+ ' checked />
							// 设置为封面'
							// }else{
							// picShow+='<input type="radio" class="pull-left"
							// name="pictureCover" value='+ j+ ' "/> 设置为封面'
							// }
							//						
							picShow += '<span class="glyphicon glyphicon-trash" onclick="deletePic(this)"  ></span>'
									+ '</div>' + '</a></div>';
							$('#addpic').before(picShow);

						}

						if ($('.showDetail').size() > 3) {
							$('#addpic').remove();

						}

					}
				}
			})
}
