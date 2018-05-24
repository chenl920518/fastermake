
var iCount = '';
function autTime() { 
	  $('.ui-progress').css({"width":"0%"});
	  $('#show_upload_dialog').show();
	  iCount = setInterval("progressStatus()", 200);//200豪秒查询进度一次
} 

function progressStatus(){
	   $("#progress").html(""); 
	     $.ajax({ 
	      type: "post", 
	      url: "/crm/progressStatus.do", 
	      dataType : "text", 
	      success : function(result) {
	        var json = eval('(' + result + ')'); 
	        var percentage = json.percentage;
	        $('.ui-progress').css({"width":Math.round(percentage)+"%"});
	        $('#ui-progress-upload').find('span').show().children().text(Math.round(percentage)+"%");	        
	        if(percentage == 100){
        	  clearInterval(iCount);	  
			  $('#main_content').show();
			  var t=setTimeout("show_upload_dialog()",1000)
	        } 
	      } 
	    }); 
}

function show_upload_dialog(){
	 $('#show_upload_dialog').hide();
}
