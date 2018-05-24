var orderId;
var factoryId;
$(function(){
	var str = window.location.search;
	if(str != null && str != ''){
		str = str.substr(1);
		orderId = str.split("&")[1].split("=")[1];
		factoryId = str.split("&")[0].split("=")[1];
	}
	$.post("/factoryInquiry/queryBigProductDetails.do",	
			{"orderId":orderId,
		     "factoryId":factoryId},
			 function(result){
			      if(result.state == 0){
			    	 var quoteInfo = result.data.quoteInfo; 
			    	 var quoteBigProducts = result.data.quoteBigProducts; 
			    	 var quoteBigProductsTab = result.data.quoteBigProductsTab; 		    	 
			    	 var supplierQuoteInfos = result.data.supplierQuoteInfos; 		    	 
			    	 var quoteWeeklyReports = result.data.quoteWeeklyReports; 		    	 
			    	 
			    	 //询盘条件
			    	 $('#quoteId').text(quoteInfo.orderId);
			    	 
			    	 //未发布询盘不显示工厂报价详情
			    	 if(quoteInfo.orderStatus == 0){
			    		 $('.panel4').hide();
			    		 $('#quoteId').next().text('待发布');
			    	 }else if(quoteInfo.orderStatus == 1){
			    		 $('#quoteId').next().text('可报价'); 
			    	 }else if(quoteInfo.orderStatus == 2){
			    		 $('#quoteId').next().text('已结束'); 
			    	 }else if(quoteInfo.orderStatus == 3){
			    		 $('#quoteId').next().text('已取消'); 
			    	 }else if(quoteInfo.orderStatus == 4){
			    		 $('#quoteId').next().text('已过期'); 
			    	 }else if(quoteInfo.orderStatus == 5){
			    		 $('#quoteId').next().text('授盘中'); 
			    	 }else if(quoteInfo.orderStatus == 6){
			    		 $('#quoteId').next().text('生产中'); 
			    	 }else if(quoteInfo.orderStatus == 7){
			    		 $('.panel4').hide();
			    		 $('#quoteId').next().text('未通过'); 
			    	 }
			    	 
			    	 
			    	 if(quoteInfo.quoteTitle == null || quoteInfo.quoteTitle == ''){
			    		 $('#quote_title').hide();
			    		 $('#quote_title').prev().hide();
			    	 }else{
				    	 $('#quote_title').text(quoteInfo.quoteTitle);
			    	 }	    	
			    	 
			    	 $('#big_product_id').val(quoteBigProducts.id);
			    	 $('#payment_term').text(quoteBigProducts.paymentTerm);
			    	 $('#requirement').text((quoteBigProducts.purchaserRequirement == null ? '' : quoteBigProducts.purchaserRequirement));
			    	
			    	 //根据大货状态判断显示
			    	 if(quoteBigProducts.isSupplierAccept == 3){
			    		 $('#panel4').show();
			    	 }
			    	 if(quoteBigProducts.isSupplierAccept == 4){
			    		 $('#panel5').show();
			    	 }
			    	 
			    	 			    	 
			    	 //产品列表
			    	 $('#product_tbody').empty();
		    	 
			    	 		    		   
			        for(var i=0;i<quoteBigProductsTab.length;i++){			    			   								   							   
							
							 var tr1 =  '<tr>'+
			                            '<td>'+
			                                '<em onclick="em_click(this)"></em>'+
			                                '<div class="imgs">'+
			                                '<img src="'+((quoteBigProductsTab[i].drawingPathCompress == null || quoteBigProductsTab[i].drawingPathCompress == '') ? '../images/pic2.png' : quoteBigProductsTab[i].drawingPathCompress)+'" alt=""/>'+ 
			                                '</div>'+			                              
			                            '</td>'+
			                            '<td> <div class="ws"><span>'+quoteBigProductsTab[i].productName+'</span></div></td>'+
			                            '<td><div class="ws"><span>'+(quoteBigProductsTab[i].process == null ? quoteInfo.mainProcess : quoteBigProductsTab[i].process)+'</span></div></td>'+
			                            '<td><div class="ws"><span>'+(quoteBigProductsTab[i].materials == null ? '' : quoteBigProductsTab[i].materials)+'</span></div></td>'+
			                            '<td><div class="ws"><span>'+(quoteBigProductsTab[i].weight == null ? '' : quoteBigProductsTab[i].weight)+'</span></div></td>'+
			                            '<td><div class="ws"><span>'+quoteBigProductsTab[i].quantity+'</span></div></td>'+
			                            '<td><div class="ws"><span>'+quoteBigProductsTab[i].unitPrice+'</span></div></td>'+
			                            '<td><div class="ws"><span>'+quoteBigProductsTab[i].moldPrice+'</span></div></td>'+
			                        '</tr>';
							 
							   var tr2 =  '<tr class="trcol currdis">'+
					                            '<td class="ljms">注释：</td>'+
					                            '<td colspan="7">'+(quoteBigProductsTab[i].productRemark == null ? '无': quoteBigProductsTab[i].productRemark)+				                              
					                            '</td>'+
					                        '</tr>';
							   
		
							 
							  $('#product_tbody').append(tr1);   
							  $('#product_tbody').append(tr2);   							 
							  
                       }
				        if(quoteBigProductsTab.length == 1){
					          em_click($('.panel3 tbody em'));	
				        }


				        
				        //显示周报
				        $('#progress_ul').empty();
				        $('#material_ul').empty();
				        $('#quanlity_ul').empty();
//				        $('#photo_ul').empty();
				        var tl = quoteWeeklyReports.length;
				        for(var j=0;j<tl;j++){
				        	var li = '<li>'+
			                            '<a onclick="download_report(\''+quoteWeeklyReports[j].id+'\')">'+quoteWeeklyReports[j].fileName+'</a>'+
			                            '<span>'+quoteWeeklyReports[j].uploadDate+'</span>'+
			                         '</li>';
				        	var li1 =  '<li onclick="show_photos('+quoteWeeklyReports[j].orderId+',\''+(quoteWeeklyReports[j].uploadDate)+'\')">'+
			        					'<div class="img_in"><img src="'+quoteWeeklyReports[j].photoPathCompress+'" alt="" /></div>'+
			        					'<p>'+quoteWeeklyReports[j].uploadDate+'</p>'+
			        				  '</li>';
				        	if(quoteWeeklyReports[j].fileType == 1){			
				        		$('#progress_ul').append(li);
				        	}else if(quoteWeeklyReports[j].fileType == 2){
				        		$('#material_ul').append(li);
				        	}else if(quoteWeeklyReports[j].fileType == 3){
				        		$('#quanlity_ul').append(li);
				        	}else{
				        		$('#photo_ul').append(li1);
				        	}				        					        	
				        } 
				        
				        
				        
				        //处理是否要求第三方检测
				        var inspectionStatus = quoteInfo.inspectionStatus;
				        if(inspectionStatus == 0){
				        	$('.btns_1').find('.div_a').attr("onclick","update_quote('1',this)");
				        	$('.btns_1').show();
				        }else if(inspectionStatus == 1){
				        	$('.btns_3').show();
				        }else if(inspectionStatus == 2){
				        	$('.btns_4').show();
				        }else if(inspectionStatus == 3){
				        	$('.btns_5').show();
				        }
				        
				        
				        
				        
				       //处理高度显示
//				        compare();
			      }else if(result.state == 2){
			    		 //如果还未登录，跳转登录页
			    		 window.location = "/zh/login.html";
			      }   			      			 
	      })
	
			    
	      
})









//下载进度报告
function download_report(id){
	window.location = "/download/report-download.do?id="+id;
}


//查看图片
//function show_photos(orderId1,uploadDate){
//	
//	$('.purchase_big_report .tc').show();
//	$.post("/factoryInquiry/queryWeeklyPhotos.do",	
//			{"orderId":orderId1,
//		     "factoryId":factoryId},
//			 function(result){
//			      if(result.state == 0){
//			    	  var photos = result.data.photos;
//			    	  $('.purchase_big_report .tc').find('ul:first').empty();
//			    	  var tl = photos.length;
//			    	  for(var i=0;i<tl;i++){
//			    		  
//			    		  var li =    '<li>'+
//			                          '<a href="###"><img src="'+photos[i].photoPath+'" alt=""/></a>'+
//			                          '</li>';
//			    		  
//			    		  
//			    		  var li1 =   '<li>'+
//			                          '<a href="###"><img src="'+photos[i].photoPathCompress+'" alt=""/></a>'+
//			                          '</li>';
//			    		  
//			    		  $('#upload_date').text(photos[i].uploadDate);
//			    		  $('#remark').text((photos[i].remark == null ? '' : photos[i].remark));
//			    		  $('.purchase_big_report .tc').find('ul:first').append(li);
//			    		  $('.purchase_big_report .tc').find('ul:last').append(li);
//			    	  }
//			      }
//		     })     
//}



//邀请第三方检测
function update_quote(inspectionStatus,obj){	
	
	$(obj).css({'color':'#ddd','border-color':'#ddd'}).attr("disabled",true);
	$.post("/factoryInquiry/updateQuoteInspection.do",	
			{"orderId":orderId,
		     "inspectionStatus" :inspectionStatus
			},
			 function(result){
				
				$(obj).css({'color':'#006dcc','border-color':'#006dcc'}).removeAttr('disabled');	
			      if(result.state == 0){
			    		easyDialog.open({
				      		   container : {
				          		     header : ' 提示信息',
				          		     content : '已提交平台，等待确认'
				      		   },
								  overlay : false,
								  autoClose : 1000
				        });  
			    		$('.btns_1').hide();
			    		$('.btns_3').show();
			    		
			      }else if(result.state == 2){
			    		 //如果还未登录，跳转登录页
			    		 window.location = "/zh/login.html";
			      }   
		     })     
}



//产品展开、收放
function em_click(obj){
    var $this = $(obj).parent().parent().next('.trcol');
    if($this.hasClass('currdis')){
        $this.removeClass('currdis');
        $this.show();
        $(obj).css({
            'background':'url(../images/red.png) no-repeat'
        })
    }else{
        $this.addClass('currdis');
        $this.hide();
        $(obj).css({
            'background':'url(../images/green.png) no-repeat'
        })
    }
}


//点击显示所有图片
function show_photos(orderId,uploadDate){
	
	$.post("/factoryInquiry/queryWeeklyPhotos.do",	
			{"orderId":orderId,
		     "uploadDate" :uploadDate
			},
			 function(result){				
			    if(result.state == 0){
			    	var photos = result.data.photos;
			    	$('#big_photo').empty();
			    	$('#small_photo').empty();
			        if(photos != null && photos != '' && photos != undefined){
			        	$('#total_tl').text(photos.length);			     
		        		$('#upload_date').text(photos[0].uploadDate);
		        		$('#remark').text(photos[0].remark);
			        	for(var i=0;i<photos.length;i++){
			        		$('#big_photo').append('<li><input type="hidden" value="'+photos[i].id+'"><a href="###"><img src="'+photos[i].photoPath+'"/></a></li>');
			        		$('#small_photo').append('<li onclick="small_photo_change(this,'+photos[i].id+')"><a href="###"><img src="'+photos[i].photoPathCompress+'"/></a></li>');
			        	}
			        }	
			        $('.purchase_big_report .tc').show();
			    }else if(result.state == 2){
		    		 //如果还未登录，跳转登录页
		    		 window.location = "/zh/login.html";
		        }   
		 })     
	
}



$(function(){

	 /*小图轮播*/
    var small_key = 0;
    var big_key = 0;
    $('.small_imgs i:last-child').click(function(){
      var photos_tl = $('#small_photo li').length;
      if(photos_tl > 13){
    	  small_key ++;
          if(small_key > 2){
              small_key = 0;
              $('.small_imgs ul').css({'left':'0'})
          }
          var move = small_key * -57;
          $('.small_imgs ul').animate({'left':move + 'px'});
          
          
          //获取图片备注
          var id = $('.big_imgs ul li').eq(small_key).find('input').val();
          getRemark(id);
      }     
    })
    $('.small_imgs i:first-child').click(function(){
    	 var photos_tl = $('#small_photo li').length;
	         if(photos_tl > 13){
	        small_key --;
	        if(small_key < 0){
	            small_key = 0;
	            $('.small_imgs ul').css({'left':'0'})
	        }
	        var move = small_key * -57;
	        $('.small_imgs ul').animate({'left':move + 'px'});
	        
	        
	          //获取图片备注
	          var id = $('.big_imgs ul li').eq(small_key).find('input').val();
	          getRemark(id);
         }
    })
    /* 大图轮播*/
    $('.big_imgs i:last-child').click(function(){
        var photos_tl = $('#small_photo li').length;
        big_key ++;
        if(big_key > photos_tl || big_key == photos_tl){
            big_key = 0;
            $('.big_imgs ul').css({'left':'0'})
        }
        var move = big_key * -600;
        $('.big_imgs ul').animate({'left':move + 'px'});
        /*对应小图效果*/
        $('.small_imgs ul li').eq(big_key).find('a').css({'border':'5px solid #337ab7'});
        $('.small_imgs ul li').eq(big_key).siblings().find('a').css({'border':'5px solid transparent'});
        
        //获取图片备注
        var id = $('.big_imgs ul li').eq(big_key).find('input').val();
        getRemark(id);
          	
    });

    $('.big_imgs i:first-child').click(function(){
        big_key--;
        if(big_key < 0){
            big_key = 0;
            $('.big_imgs ul').css({'left':'0'})
        }
        var move = big_key * -600;
        $('.big_imgs ul').animate({'left':move + 'px'})
        /*对应小图效果*/
        $('.small_imgs ul li').eq(big_key).find('a').css({'border':'5px solid #337ab7'});
        $('.small_imgs ul li').eq(big_key).siblings().find('a').css({'border':'5px solid transparent'});
        
        //获取图片备注
        var id = $('.big_imgs ul li').eq(big_key).find('input').val();
        getRemark(id);
    });
    /*小图点击对应大图事件*/
    $('.small_imgs ul li').click(function(){
        $(this).find('a').css({'border':'5px solid #73b4e0'});
        $(this).siblings().find('a').css({'border':'5px solid transparent'});
        var $index = $(this).index();
        var move1 = ($index)* -600;
        $('.big_imgs ul').animate({'left':move1 + 'px'})
    })
    
    /* 图片快照轮播 */
   var ulkey = 0;
   $('.purchase_big_report  .report-content  .i2').click(function(){
	    var tl = $('#photo_ul li').length;
	   	ulkey++;
	   	if(ulkey > (tl - 3) || ulkey == (tl - 3)){
	   		ulkey = 0;
	   		$('.purchase_big_report .report-content ul').css({'left':'0'});
	   	}
	   	var move = ulkey *-170;
	   	$('.purchase_big_report .report-content ul').animate({'left':move + 'px'});
   });
   
    $('.purchase_big_report  .report-content  i:first-child').click(function(){
	   	ulkey--;
	   	if(ulkey < 0){
	   		ulkey = 0;
	   		$('.purchase_big_report .report-content ul').css({'left':'0'});
	   	}
	   	var move = ulkey *-170;
	   	$('.purchase_big_report .report-content ul').animate({'left':move + 'px'});
   });
	
	
})

//轮播小图点击效果
function small_photo_change(obj,id){
	 $(obj).find('a').css({'border':'5px solid #73b4e0'});
     $(obj).siblings().find('a').css({'border':'5px solid transparent'});
     var $index = $(obj).index();
     var move1 = ($index)* -600;
     $('.big_imgs ul').animate({'left':move1 + 'px'});
     
     
   //获取图片备注
    getRemark(id);
}

//获取图片备注
function getRemark(id){
	$.post("/factoryInquiry/queryRemark.do",	
			{"id":id},
			 function(result){				
			    if(result.state == 0){
			    	var remark = result.data.remark;
			        if(remark != null && remark != undefined){
			        	$('#remark').text(remark);			     
			        }else{
			        	$('#remark').text('');		
			        }	
			  }else if(result.state == 2){
		    		 //如果还未登录，跳转登录页
		    		 window.location = "/zh/login.html";
		     }         
	})   
	
}




function compare(){
	/*侧边栏长度控制效果*/
	var h1 = $(document.body).height()+230 ;
	var h2 = window.screen.availHeight  ;
	if(h1 < h2){
		$('#footer').addClass('footer1');
	}else{
		$('#footer').removeClass('footer1');
	}
}


//时间格式化
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



function keyPress(ob) {
	 if (!ob.value.match(/^[\+\-]?\d*?\.?\d*?$/)) ob.value = ob.t_value; else ob.t_value = ob.value; if (ob.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/)) ob.o_value = ob.value;
	}
function keyUp(ob) {
	 if (!ob.value.match(/^[\+\-]?\d*?\.?\d*?$/)) ob.value = ob.t_value; else ob.t_value = ob.value; if (ob.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/)) ob.o_value = ob.value;
	        }
function onBlur(ob) {
	if(!ob.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))ob.value=ob.o_value;else{if(ob.value.match(/^\.\d+$/))ob.value=0+ob.value;if(ob.value.match(/^\.$/))ob.value=0;ob.o_value=ob.value};
	}


