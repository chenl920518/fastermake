$(function(){
	showEnterpriseInfo();
});

function showEnterpriseInfo(){
	$.ajax({
		url : "/factoryInfo/selectFactoryInfo.do",
		type : "post",
		traditional : true,
		data : {
			
		},
		datatype : "json",
		success : function(result) {
			if (result.state == 0) {
				var data=result.data.factoryInfo;
				var factoryUser = result.data.factoryUser;
				var type=0;
				//区分采购商,供应商
				if(data.factoryType==2){
					$("#equipmentHide").hide();
					$("#mainProcessAndenterpriseHide").hide();
				}
				//如果用户权限不为管理员，无法修改企业信息
				if(factoryUser.permission != 1){
					$('.li1').next().hide();
				}				
				
				if(data.state!=null && data.state!=""){
					$(".state").text(data.state);
				}
				if(data.detailsAddress!=null && data.detailsAddress!=""){
				    $(".detailsAddress").text(data.detailsAddress);
				}
				if(data.establishmentYear!=null && data.establishmentYear!=""){
					$(".establishmentYear").text(data.establishmentYear);
				}
				if(data.staffNumber!=null && data.staffNumber){
					$(".staffNumber").text(data.staffNumber);
				}
                if(data.factoryAcreage!=null && data.factoryAcreage){
                	$(".factoryAcreage").text(data.factoryAcreage);
                }
                if(data.factoryValue!=null && data.factoryValue){
                	$(".factoryValue").text(data.factoryValue);
                }
                
				if(type==0){
					type="否";	
				}else{
					type="是";
				}
				$(".type").text(type);
				$("#factoryNameHtml").text(data.factoryName);
				$("#factoryInfoHtml").text(data.factoryInfo);
				var mainHtml="";
				var mainProcess=result.data.factoryInfo.mainProcessS;
				for (var i = 0; i < mainProcess.length; i++) {
					mainHtml='<li><a href="###"><span>.</span>'+mainProcess[i]+'</a></li>';
					$("#panel-body-mainProcess").append(mainHtml);
				}
				
				var equipmentList=result.data.factoryInfo.equipmentList;
				var equipHtml="";
				for(var j=0;j<equipmentList.length;j++){
					equipHtml='<tr><td>'+equipmentList[j].equipmentName+'</td><td>'+equipmentList[j].equipmentModel+'</td><td>'+equipmentList[j].number+'</td><td>'+equipmentList[j].type+'</td></tr>';
					$("#equipmentList").append(equipHtml);
				}
				
				var cooperativeEnterprise=result.data.factoryInfo.cooperativeEnterpriseS;
				var enterpriseHtml="";
				enterpriseHtml+='<li><a href="###" class="a1">合作过的企业</a></li>';
				for(var k=0;k<cooperativeEnterprise.length;k++){
					enterpriseHtml='<li><a href="###"><span>.</span>'+cooperativeEnterprise[k]+'</a></li>';
					$(".enterprise").append(enterpriseHtml);
				}
				
//				addcss();
				
			}else if(result.state == 2){
	    		 //如果还未登录，跳转登录页
	    		 window.location = "/zh/login.html";
	        }     
		}
	})
}


function addcss(){
	
	 /* body 高度控制底部位置 */
    var h1 = $(document.body).height()+230;
    var h2 = window.screen.availHeight ;
    if(h1 < h2){
        $('#footer').addClass('footer1')
    }else{
        $('#footer').removeClass('footer1');
    }; 
	
}









