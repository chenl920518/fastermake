/**
 * Edit 修复上传错误定时器持续执行
 * Polo 2018/4/12
 */

var iCount = '';
function autTime() { 
	  $('.ui-progress').css({"width":"0%"});
	  $('#main_content').hide();
	  $('#show_upload_dialog').show();
	  iCount = setInterval("progressStatus()", 200);//200豪秒查询进度一次
} 

function progressStatus(){
	   $("#progress").html(""); 
	      $.ajax({ 
	        type: "POST", 
	        url: "/progressStatus.do", 
	      data : "1=1", 
	      dataType : "text", 
	      success : function(result) {
	        var json = eval('(' + result + ')'); 
	        var percentage = json.percentage;
	        $('.ui-progress').css({"width":Math.round(percentage)+"%"});
	        $('#ui-progress-upload').find('span').show().children().text(Math.round(percentage)+"%");	        
	        if(percentage == 100){
	        	 clearInterval(iCount);	  
				 $('#main_content').show();				 
				 setTimeout("$('#show_upload_dialog').hide();",1000);
	        }
	        
	      }, error:function(result) {
	    	  clearInterval(iCount);
	      }
	    }); 
}


//取消上传
function cancel_upload(){
	clearInterval(iCount);	
	$('#show_upload_dialog').hide();
}