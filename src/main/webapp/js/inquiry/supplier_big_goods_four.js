var orderId;
var str = window.location.search;
if(str != null && str != ''){
	str = str.substr(1);
	orderId = str.split("&")[0].split("=")[1];
	$('#file_upload_id1').find('input:first').val(orderId);
	$('#file_upload_id').find('input:first').val(orderId);
	$('#file_upload_id2').find('input:first').val(orderId);
	$('#file_upload_id3').find('input:first').val(orderId);
}
$(function(){

	$.post("/inquiry/queryBigProductDetails.do",
			{"orderId" : orderId},
			 function(result){
			      if(result.state == 0){
			    	 var quoteInfo = result.data.quoteInfo; 
			    	 var quoteBigProducts = result.data.quoteBigProducts; 
			    	 var quoteBigProductsTab = result.data.quoteBigProductsTab; 		    	 	    	 
			    	 var quoteWeeklyReports = result.data.quoteWeeklyReports; 		    	 
			    	 
			    	 //询盘条件
			    	 $('#quoteId').text(quoteInfo.orderId);
			    	 
			    	 //未发布询盘不显示工厂报价详情
			    	 if(quoteInfo.orderStatus == 1){
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
			    	 }
			    	 
			    	 //询盘标题
			    	 if(quoteInfo.quoteTitle == null || quoteInfo.quoteTitle == ''){
			    		 $('#quote_title').hide();
			    		 $('#quote_title').prev().hide();
			    	 }else{
				    	 $('#quote_title').text(quoteInfo.quoteTitle);
			    	 }	    	
			    	 
			    	 if(quoteBigProducts != null && quoteBigProducts != ''){
				    	 $('#payment_term').text(quoteBigProducts.paymentTerm);
				    	 $('#requirement').text((quoteBigProducts.purchaserRequirement == null ? '' : quoteBigProducts.purchaserRequirement));	 
			    	 }

			    	
			    	 //根据大货状态判断显示
			    	 if(quoteBigProducts.isSupplierAccept == 1){
			    		 $('#panel2').show();
			    	 }else if(quoteBigProducts.isSupplierAccept == 2){
			    		 $('#panel1').show();
			    	 }else if(quoteBigProducts.isSupplierAccept == 3){
			    		 $('#panel3').show();
			    	 }else if(quoteBigProducts.isSupplierAccept == 4){
			    		 $('#panel4').show();
			    		 $('.panel_four_2').show();
			    		 $('.panel_four_3').show();
			    	 }
			    	 //显示大货回复内容
			    	 if(quoteBigProducts.supplierRemark == null || quoteBigProducts.supplierRemark == ''){
			    		 $('#supplier_remark').parents('.panel_four_4').hide();
			    	 }else{
				    	 $('#supplier_remark').text(quoteBigProducts.supplierRemark);
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
					                            '<td colspan="7">'+(quoteBigProductsTab[i].productRemark == null ? '无' : quoteBigProductsTab[i].productRemark)+					                              
					                            '</td>'+
					                        '</tr>';
							   
		
							 
							  $('#product_tbody').append(tr1);   
							  $('#product_tbody').append(tr2);   							 
							  
                       }
				        if(quoteBigProductsTab.length == 1){
					          em_click($('.panel3 tbody em'));	
				        }


				        
				        //显示进度报告、图片、质检报告、采购报告
				        $('#progress_div').empty();
				        $('#material_div').empty();
				        $('#quanlity_div').empty();
				        $('#photos_ul').find("li").not(":last").remove();
				        var tl = quoteWeeklyReports.length;
				        for(var j=0;j<tl;j++){
				        	
				        	var div = '<div class="posirela"><p onclick="download_report('+quoteWeeklyReports[j].id+')">'+quoteWeeklyReports[j].fileName+'</p><em>'+(new Date(quoteWeeklyReports[j].uploadDate.replace(/-/g,"/").split(".")[0])).Format("yy/MM/dd")+'</em><i class="iconfont" onclick="del_report('+quoteWeeklyReports[j].id+',this,1)">&#xe61d;</i></div>';				        	
				        	var li = '<li>'+
		                                '<div class="img_in"><img src="'+quoteWeeklyReports[j].photoPathCompress+'" alt=""/></div>'+
		                                '<p>'+quoteWeeklyReports[j].uploadDate+'</p>'+
		                                '<div class="dele">'+                               
		                                    '<i class="iconfont pull-right" onclick="del_report('+quoteWeeklyReports[j].id+',this,0)">&#xe634;</i>'+
		                                    '<i class="iconfont pull-right" onclick="open_update_photo('+quoteWeeklyReports[j].id+',this)">&#xe62d;</i>'+
		                                '</div>'+
		                            '</li>';
				        	
				        	if(quoteWeeklyReports[j].fileType == 1){			
				        		$('#progress_div').append(div);
				        	}else if(quoteWeeklyReports[j].fileType == 2){
				        		$('#material_div').append(div);
				        	}else if(quoteWeeklyReports[j].fileType == 3){
				        		$('#quanlity_div').append(div);
				        	}else if(quoteWeeklyReports[j].fileType == 0){
				        		$('#photos_ul').find("li:last").before(li);
				        	}				        					        	
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


//编辑图片信息
function open_update_photo(id,obj){
	
	var imgPath = $(obj).parent().prev().find('img').attr("src");	
	$.post("/inquiry/queryPhotoById.do",	
			{"id":id},
			 function(result){
			      if(result.state == 0){
			    	  var report = result.data;
			    	  if(report != null && report != ''){
			    		  $('.purchase_big_report .tcbj').find('img').attr("src",report.photoPathCompress);
				    	  $('#remark_edit').val((report.remark == null ? '' : report.remark));  
				    	  $('#file_upload_id1_edit').find('input:first').val(report.id);
				    	  $('#file_upload_id1_edit').find('input').eq(3).val(report.orderId);
				    	  var tl = report.remark.length;
				    	  $('#remark_edit').next().find('i').text((report.remark == null ? '0' : report.remark.length));
				    	  $('.purchase_big_report .tcbj').show();
			    	  }

			      }
		     })	      
	
}
//修改图片信息
function update_photo(obj){
	$(obj).css({'background-color':'#ddd'}).attr("disabled",true);
	$.post("/inquiry/updatePhotoById.do",	
			{"id" : $('#file_upload_id1_edit').find('input:first').val(),
		     "originalName" : $('#file_upload_id1_edit').find('input').eq(1).val(),
		     "newName" : $('#file_upload_id1_edit').find('input').eq(2).val(),
		     "remark" :$('#remark_edit').val() 
			},
			 function(result){
		    	  $(obj).css({'background-color':'#006dcc'}).removeAttr('disabled');
			      if(result.state == 0){
			    	  easyDialog.open({
			      		   container : {
			          		     header : ' 提示信息',
			          		     content : ' 更新成功'
			      		   },
							  overlay : false,
							  autoClose : 1000
			      		 });   
		    		  setTimeout("location.reload()",1000);	  
			      }else if(result.state == 2){
			    		 //如果还未登录，跳转登录页
			    		 window.location = "/zh/login.html";
			       }    
		     })	      	
}



//删除报告
function del_report(id,obj,type){	
	$.post("/inquiry/deleteReport.do",	
			{"id":id},
			 function(result){
			      if(result.state == 0){
		    		easyDialog.open({
			      		   container : {
			          		     header : ' 提示信息',
			          		     content : ' 删除成功'
			      		   },
							  overlay : false,
							  autoClose : 1000
			      		 });   
		    		  if(type == 0){
		    			  $(obj).parents('li').remove(); 
		    		  }else{
				    	  $(obj).parent().remove();     
		    		  }
			      }else if(result.state == 2){
			    		 //如果还未登录，跳转登录页
			    		 window.location = "/zh/login.html";
			       }    
		     })	      
}

//保存图片
function save_photos(obj){	
	$(obj).css({'background-color':'#ddd'}).attr("disabled",true);
	$.post("/inquiry/insertPhotos.do",	
			{
		     "remark":$('#remark').val()		      
			},
			 function(result){
				$(obj).css({'background-color':'#006dcc'}).removeAttr('disabled');	
			      if(result.state == 0){
		    		 easyDialog.open({
			      		   container : {
			          		     header : ' 提示信息',
			          		     content : ' 保存成功'
			      		   },
							  overlay : false,
							  autoClose : 1000
			      		 });   
		    		  setTimeout("location.reload()",1000);	  
			      }else if(result.state == 2){
			    		 //如果还未登录，跳转登录页
			    		 window.location = "/zh/login.html";
			       }    
		     })	      
}


//修改图片获取新图片路径
function upload_photo_edit(obj){	
	var path = $(obj).val();
    sppath = path.split("\\");
    var fileName = sppath[sppath.length-1];	  	   
    if(fileName == null || fileName == '' || fileName == undefined){
    	return false;
    }else{
       $('#file_upload_id1_edit').find('input').eq(1).val(fileName);
	   autTime(); 
	   $('#upload_title').children().text("上传进度");
    }	 		    	
 			  
	  //先上传后获取上传文件路径
	 $("#file_upload_id1_edit").ajaxSubmit({    			
		type: "post",
		url: "/inquiry/uploadPhoto.do",
     	dataType: "text",
     	success: function(str){
     	var result = eval('(' + str + ')');		
     	if(result.state == 0){   
     		var str = result.data;
     		if(str != null && str != ''){
     			$('.purchase_big_report .tcbj').find('img').attr("src",str.filePath);
         		$('#file_upload_id1_edit').find('input').eq(2).val(str.newName);
     		}
     	}else if(result.state == 2){
   		 //如果还未登录，跳转登录页
   		 window.location = "/zh/login.html";
        }    

     	},
		error: function(){
			 easyDialog.open({
     		   container : {
         		     header : '  提示信息',
         		     content : '  上传失败 '
     		   },
				  overlay : false,
				  autoClose : 1000
     		 });   
     		$('#show_upload_dialog').hide();
		}       	     	
 	}); 	 		    
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


//上传周报
function upload_photos(obj){	
	var path = $(obj).val();
    sppath = path.split("\\");
    var fileName = sppath[sppath.length-1];	  	   
    if(fileName == null || fileName == '' || fileName == undefined){
    	return false;
    }else{
	   autTime(); 
	   $('#upload_title').children().text("上传进度");
    }	 		    	
 		
	  
	  //先上传后获取上传文件路径
	 $("#file_upload_id1").ajaxSubmit({    			
		type: "post",
		url: "/inquiry/uploadPhotos.do",
     	dataType: "text",
     	success: function(str){
     	var result = eval('(' + str + ')');		
     	if(result.state == 0){       	
     		
     	}else if(result.state == 2){
   		 //如果还未登录，跳转登录页
   		 window.location = "/zh/login.html";
     	}	

     	},
		error: function(){
			 easyDialog.open({
     		   container : {
         		     header : '  提示信息',
         		     content : '  上传失败 '
     		   },
				  overlay : false,
				  autoClose : 1000
     		 });   
     		$('#show_upload_dialog').hide();
		}       	     	
 	}); 	 		    
}


//上传进度报告
function upload_progress_report(obj){
		var path = $(obj).val();
	    sppath = path.split("\\");
	    var fileName = sppath[sppath.length-1];	  	   
	    if(fileName == null || fileName == '' || fileName == undefined){
	    	return false;
	    }else{
	       $('#fileName').val(fileName);
    	   autTime(); 
		   $('#upload_title').children().text("上传进度");
	    }	 		    	
     		
		  
		  //先上传后获取上传文件路径
		 $("#file_upload_id").ajaxSubmit({    			
			type: "post",
			url: "/inquiry/insertReport.do",
	     	dataType: "text",
	     	success: function(str){
	     	var result = eval('(' + str + ')');		
	     	var report = result.data.report;
	     	if(result.state == 0){  
	     		var div = '<div class="posirela"><p>'+report.fileName+'</p><em>'+(new Date(report.uploadDate.replace(/-/g,"/").split(".")[0])).Format("yy/MM/dd")+'</em><i class="iconfont" onclick="del_report('+report.id+',this,1)">&#xe61d;</i></div>'; 		     		
	     		$('#progress_div').prepend(div);
	     		
	     	}else if(result.state == 2){
	    		 //如果还未登录，跳转登录页
	    		 window.location = "/zh/login.html";
	        }    

	     	},
			error: function(){
				 easyDialog.open({
         		   container : {
             		     header : '  提示信息',
             		     content : '  上传失败 '
         		   },
					  overlay : false,
					  autoClose : 1000
         		 });   
	     		$('#show_upload_dialog').hide();
			}       	     	
	 	}); 	 		    

 }

//上传材料证明
function upload_material_report(obj){
		var path = $(obj).val();
	    sppath = path.split("\\");
	    var fileName = sppath[sppath.length-1];	  	   
	    if(fileName == null || fileName == '' || fileName == undefined){
	    	return false;
	    }else{
	       $(obj).parents('form').find('input').eq(1).val(fileName);
    	   autTime(); 
		   $('#upload_title').children().text("上传进度");
	    }	 		    	
     		
		  
		  //先上传后获取上传文件路径
		 $("#file_upload_id2").ajaxSubmit({    			
			type: "post",
			url: "/inquiry/insertReport.do",
	     	dataType: "text",
	     	success: function(str){
	     	var result = eval('(' + str + ')');		
	     	var report = result.data.report;
	     	if(result.state == 0){   
	     		var div = '<div class="posirela"><p onclick="download_report('+report.id+')">'+report.fileName+'</p><em>'+(new Date(report.uploadDate.replace(/-/g,"/").split(".")[0])).Format("yy/MM/dd")+'</em><i class="iconfont" onclick="del_report('+report.id+',this,2)">&#xe61d;</i></div>'; 		     		
	     		$('#material_div').prepend(div);
	     		
	     	}else if(result.state == 2){
	    		 //如果还未登录，跳转登录页
	    		 window.location = "/zh/login.html";
	        }    

	     	},
			error: function(){
				 easyDialog.open({
         		   container : {
             		     header : '  提示信息',
             		     content : '  上传失败 '
         		   },
					  overlay : false,
					  autoClose : 1000
         		 });   
	     		$('#show_upload_dialog').hide();
			}       	     	
	 	}); 	 		    

 }

//上传质量报告
function upload_quanlity_report(obj){
		var path = $(obj).val();
	    sppath = path.split("\\");
	    var fileName = sppath[sppath.length-1];	  	   
	    if(fileName == null || fileName == '' || fileName == undefined){
	    	return false;
	    }else{
	       $(obj).parents('form').find('input').eq(1).val(fileName);
    	   autTime(); 
		   $('#upload_title').children().text("上传进度");
	    }	 		    	
     		
		  
		  //先上传后获取上传文件路径
		 $("#file_upload_id3").ajaxSubmit({    			
			type: "post",
			url: "/inquiry/insertReport.do",
	     	dataType: "text",
	     	success: function(str){
	     	var result = eval('(' + str + ')');		
	     	var report = result.data.report;
	     	if(result.state == 0){  
	     		var div = '<div class="posirela"><p onclick="download_report('+report.id+')">'+report.fileName+'</p><em>'+(new Date(report.uploadDate.replace(/-/g,"/").split(".")[0])).Format("yy/MM/dd")+'</em><i class="iconfont" onclick="del_report('+report.id+',this,3)">&#xe61d;</i></div>'; 		     		
	     		$('#quanlity_div').prepend(div);
	     		
	     	}else if(result.state == 2){
	    		 //如果还未登录，跳转登录页
	    		 window.location = "/zh/login.html";
	        }    

	     	},
			error: function(){
				 easyDialog.open({
         		   container : {
             		     header : '  提示信息',
             		     content : '  上传失败 '
         		   },
					  overlay : false,
					  autoClose : 1000
         		 });   
	     		$('#show_upload_dialog').hide();
			}       	     	
	 	}); 	 		    

 }











/**
 * 获取输入字符串长度
 * @returns {Boolean}
 */
function getCharacterLength(){
	var character = $('#remark').val();
	var tl = character.length;
	if(tl > 300){
		easyDialog.open({
   		   container : {
       		     header : ' 提示信息',
       		     content : '最多只能300个字'
   		   },
					  overlay : false,
					  autoClose : 1000
   		   });   		
		$('#remark').val(character.substring(0,300));
		return false;
	}else{
		$('#remark').next().find('i').text(tl);
	}
}
//获取编辑框大小
function get_char(obj){
	var character = $(obj).val();
	var tl = character.length;
	if(tl > 300){
		easyDialog.open({
   		   container : {
       		     header : ' 提示信息',
       		     content : '最多只能300个字'
   		   },
					  overlay : false,
					  autoClose : 1000
   		   });   		
		$(obj).val(character.substring(0,300));
		return false;
	}else{
		$(obj).next().find('i').text(tl);
	}
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


