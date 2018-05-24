$(function(){

	$.post("/inquiry/queryCountList.do",
			function(result){
		      if(result.state == 0){
		    	  var invitationOrder = result.data.invitationOrder;
		    	  var quoteOrder = result.data.normalOrder;
		    	  var collectCount = result.data.collectCount;
		    	  var quoteFinishOrder = result.data.quoteFinishOrder;
		    	  var refusedOrder = result.data.refusedOrder;

		    	  if(invitationOrder>0){
		    		  $('#quote_ul').find('li').eq(1).find('em').text((invitationOrder == 0 ? '' : "("+invitationOrder+")"));  
		    	  }		    	  
		    	  if(quoteOrder>0){
		    		  $('#quote_ul').find('li').eq(2).find('em').text("("+quoteOrder+")");  
		    	  }		    	  
		    	  if(collectCount>0){
		    		  $('#quote_ul').find('li').eq(5).find('em').text("("+collectCount+")");  
		    	  }		    	  
		    	  if(quoteFinishOrder>0){
		    		  $('#quote_ul').find('li').eq(4).find('em').text("("+quoteFinishOrder+")");  
		    	  }		    	  
		    	  if(refusedOrder>0){
		    		  $('#quote_ul').find('li').eq(3).find('em').text("("+refusedOrder+")");  
		    	  }		    	  
		      }  
		  })  	  
 })	


