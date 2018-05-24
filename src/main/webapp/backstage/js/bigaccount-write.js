$(function() {
	
	doValidForm($('#bigBuyerForm'))
	doFocusForm($('#bigBuyerForm'))
	var proId = window.location.search;
	if (proId) {
		var id = proId.substr(4);
		
		$('#id').val(id)
		
		ajaxSelect(id)
		
	}
	
	
	
	
	
	
})



function saveBigBuyer(){
	
	
	if(!saveVerification($('#bigBuyerForm'))){
		return false;
	}
	
	
	
	var formdate = $('#bigBuyerForm').serialize()
	
	
	$('#saveButton').css({'background-color':'#ddd'}).attr('disabled',true)
	
	$.ajax({
				url : "/bigBuyerComment/updateBigBuyer.do",
				type : "post",
				traditional : true,
				data : formdate,
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

   return false;
}












function ajaxSelect(id) {
	var currpage;
	var id = id;

	$.ajax({
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
						var bigBuyerList = result.data.bigBuyerList;
						var listSize = result.data.listSize
						var totalPrice = result.data.totalPrice
						
						

						if (bigBuyerList) {
                         var bigBuyerComment = bigBuyerList[0];
                         
                         var requirement = bigBuyerComment.requirement
                         var isComment = bigBuyerComment.isComment
                         var comment = bigBuyerComment.comment
                         if(requirement){
                        	 requirements = requirement.split(',')
                        	 if(requirements&&requirements.length>0){
                        		 for(var j=0;j<requirements.length;j++){
                        			 $('#bigBuyerForm').find('input[name=requirement]').eq(j).val(requirements[j])
                        		 }
                        	 }
                         }
                         
						 $('#bigBuyerForm').find('input[name=buyerId]').val(bigBuyerComment.buyerId)
                         $('#bigBuyerForm').find('input[name=title]').val(bigBuyerComment.title)
                         $('#bigBuyerForm').find('textarea[name=buyerInfo]').val(bigBuyerComment.buyerInfo)
                         $('#bigBuyerForm').find('textarea[name=products]').val(bigBuyerComment.products)
                         $('#special').val(bigBuyerComment.special)
                         
                         
                         
                         $('.rfqNum').text(listSize?listSize:0)
                         $('#totalPrice').text(totalPrice?totalPrice:0)
                         if(isComment==1){
                            $('#isComment').prop('checked',true)
                            showText($('#isComment'))
                            $('#comment').val(comment)
       
                         }
                         
                         
                         
				}
			}
		}
	})
}



function showText(obj) {

	if($(obj).is(':checked')){
	$('#comment').next().removeClass('ff4').text('')
	$('#comment').attr('require','').show();
	
	doValidForm($('#commentV'))
	doFocusForm($('#commentV'))
	
	
	}else{
		$('#comment').next().removeClass('ff4').text('')
		$('#comment').val('')
		$('#comment').removeAttr('require').hide();
		
	}
}


