$(function() {
	ajaxSelect()
	
	
})

function ajaxSelect(id) {
	var currpage;
	var id = id;

	$
			.ajax({
				url : "/bigBuyerComment/queryList.do",
				type : "post",
				traditional : true,
				data : {
					'currpage' : currpage,
					'id' : id,
					'pageSize' : 1000
				},
				datatype : "json",
				success : function(result) {
					if (result.state == 0) {
						$('#bigList').html('')
						var bigBuyerList = result.data.bigBuyerList;

						if (bigBuyerList) {

							var addHtml = '';

							for (var i = 0; i < bigBuyerList.length; i++) {
								var bigBuyerComment = bigBuyerList[i]

								addHtml += '<tr>'
										+ '<td><input type="text" value="'
										+ (bigBuyerComment.title ? bigBuyerComment.title
												: "")
										+ '" class="input-text radius"  readonly="readonly"/> </td>'
										+ '<td><input type="text" value="'
										+ (bigBuyerComment.buyerId ? bigBuyerComment.buyerId
												: "")
										+ '"  class="input-text radius" readonly="readonly" /></td>'
										+ '<td><a href="bigaccount-write.html?id='+(bigBuyerComment.id ? bigBuyerComment.id
												: "")+'"  class="btn btn-primary radius">修改</a></td>'
										+ '<td><a href="javascript:void(0);" onclick="deleteDetail('+bigBuyerComment.id+')"  class="btn btn-danger radius">删除</a></td>'
										+ '</tr>'

							}
							
							
							$('#bigList').append(addHtml)
						}
					}
				}

			})
}

function deleteDetail(id){
	
	if(confirm("是否确认删除？")){
	
	$.ajax({
		url : "/bigBuyerComment/updateBigBuyer.do",
		type : "post",
		traditional : true,
		data : {
			id:id,
			oper:'del'
			
		},
		
		datatype : "json",
		success : function(result) {
			if (result.state == 0) {
				
				easyDialog.open({
					container : {
						header : '  Prompt message',
						content :  result.message
					},
					callback :function(){
						window.location= result.data;
					},
					overlay : false,
					autoClose : 500
				});
			}
	
}
	})
	
	}
	
	
	
}