$(function(){
	selectFactoryBusinessBoard(1);
})

function selectFactoryBusinessBoard(page){
	$.post("/factoryInfo/selectFactoryBusinessBoard.do",
	 {
		"page" : page
	 },
	function(result){
	  if(result.state == 0){
		    var factoryInfo=result.data.factoryInfo;
		    var inquiryOrders = result.data.inquiryOrders;
		    var productNames = result.data.productNames;
		    var totalOrder = result.data.totalOrder;
		    var totalPage = Math.ceil(totalOrder/18);
		    
		    $('#click_count').text(factoryInfo.clickCounts);
		    var tl = inquiryOrders.length;
		    $('#tbody1').empty();
		    $('#tbody2').empty();
		    for(var i=0;i<tl;i++){
    		  var products = productNames[i];
    		  var country = inquiryOrders[i].country;
    		  var productName = '';
    		  for(var j=0;j<products.length;j++){
    			  productName += "<span style='color:#797979;'>"+products[j]+"</span><br/>"
    		  }
    		  var tr = '<tr>'+
		                      '<td class="d1">'+
		                      '<img src="../images/zg.png" alt="" class="img1"/>'+
		                      '<div class="imgs2">'+
		                      '<img style="margin:0;" src="'+((inquiryOrders[i].drawingPathCompress == null ||  inquiryOrders[i].drawingPathCompress ==  '') ? '../images/pic2.png' : inquiryOrders[i].drawingPathCompress)+'" alt="" class="img2"/><br/></div>'+
		                      '<a href="###" class="amt10" onclick="queryDetails('+inquiryOrders[i].orderId+')">'+products[0]+'</a>'+
		                  '</td>'+
		                  '<td class="d2">'+
		                      '<span>'+(inquiryOrders[i].mainProcess == null ? '' : inquiryOrders[i].mainProcess)+'</span><br/>'+
		                      '<span>'+(inquiryOrders[i].annualQuantity == null ? '' : inquiryOrders[i].annualQuantity)+'</span>'+
		                  '</td>'+
		                  '<td class="d3">'+(new Date(inquiryOrders[i].publishDate.replace(/-/g,"/").split(".")[0])).Format("yyyy-MM-dd")+'</td>'+
		                  '<td>'+(inquiryOrders[i].state == 1 ? '江浙沪' : (inquiryOrders[i].state == 2 ? '深圳、广东、福建' : '不限'))+'</td>'+
		                 '</tr>';
    		  if(i % 2 == 0){
    			  $('#tbody1').append(tr);
    		  }else if(i % 2 == 1){
    			  $('#tbody2').append(tr); 
    		  }
		    }
		    
		    $("#userName").text(factoryInfo.username);
		    $("#factoryName").text(factoryInfo.factoryName);
		    $("#tel").text((factoryInfo.tel == null ? '' : factoryInfo.tel));
		    $("#factoryLogo").attr('src',""+(factoryInfo.factoryLogo == null || factoryInfo.factoryLogo == '' ? '../images/defaultLogo.png' : "/static_img/factory_logo/"+factoryInfo.factoryId+'/'+factoryInfo.factoryLogo)+""); 
		 }  
	})		
}

//查询订单详情
function queryDetails(orderId){
   window.location = "/zh/detail.html?orderId="+orderId;
}

		
		