$(function(){
	
	var permission;
	var factoryInfo = getCookie("factoryInfo");
	if (factoryInfo != null && factoryInfo != '' && factoryInfo != undefined) {    		
		var base = new Base64();
		factoryInfo = base.decode(factoryInfo);
		var strs = [];
		strs = factoryInfo.split("&");
		permission = strs[3];
		if(permission !=1){
			window.location = "/zh/enterprise_archives.html";
		}
	}
	selectFactoryDetailsInfo();
});
function selectFactoryDetailsInfo(){
	$.ajax({
		url : "/factoryInfo/selectFactoryDetailsInfo.do",
		type : "post",
		traditional : true,
		data : {
			
		},
		datatype : "json",
		success : function(result) {
			if (result.state == 0) {

				var data=result.data.factoryInfo;
				$("#factoryName").val(data.factoryName);
				$("#aliWebsite").val(data.aliWebsite);
				$("#tel").val(data.tel);
				$("#mobile").val(data.mobile);
				//区分采购商,供应商(采购商只显示基本信息)
				if(data.factoryType==2){
					$("#aliWebsiteHide").hide();
					$("#factoryLicenseHide").hide();
					$("#factoryGateHide").hide();
					$("#factoryAcreageHide").hide();
					$("#factoryValueHide").hide();
					$("#typeHide").hide();
					$("#qualificationHtmlHide").hide();
					
					$("#technologicalAdvantageHide").hide();
					$("#cooperativeEnterpriseSHide").hide();
					$("#acceptConditionHide").hide();
					$("#equipmentHide").hide();
				}
				$("#factoryNameHtml").text(data.factoryName);
				//工厂logo显示
				if(data.factoryLogo!=null && data.factoryLogo!=''){
					$("#factoryLogoHtml").attr('src',"/static_img/factory_logo/"+data.factoryId+'/'+data.factoryLogo); 
				}
				//处理营业执照
				var factoryLicense=data.factoryLicense;
				var factoryLicenseS="";
				if(factoryLicense!=null && factoryLicense!=""){
					factoryLicenseS = factoryLicense.split(',');
				}
				for(var i = 0;i<factoryLicenseS.length;i++){
				    if(factoryLicenseS[i]==''||factoryLicenseS[i]==null||typeof(factoryLicenseS[i])==undefined){
				    	factoryLicenseS.splice(i,1);
				        i=i-1;
				    }
				}
			    if(factoryLicenseS.length>0){//有营业执照
			    	for(var i=0;i<factoryLicenseS.length;i++){	
						var factoryLicenseHtml='';
				    	if(factoryLicenseS.length<=1){//有营业执照一张
				    		factoryLicenseHtml='<div class="col-xs-6 padlr w200 m20"><a href="javascript:void(0);" class="aimg">'
				    		factoryLicenseHtml+='<img src="/static_img/factory_license/'+data.factoryId+'/'+factoryLicenseS[i]+'" alt="" class="img-responsive">'
				    		factoryLicenseHtml+='<div class="sy" factoryLicense="'+factoryLicenseS[i]+'" onclick="delFactoryLicense(this)"><span class="iconfont">&#xe616;</span></div></a></div>'
				    		factoryLicenseHtml+='<div class="col-xs-6 padlr w200"><a href="javascript:void(0);" class="aimg aimg2 text-center">'
				   
						    factoryLicenseHtml+='<img src="../images/products/add.png" alt="" class="img-responsive" onclick="uploadFactoryLicenseFile()"><br/><span>上传图片</span></a><input id="uploadFactoryLicenseFile" name="files" type="file" class="imgfile" onchange="updateFactoryLicense(this)"/><div class="gs posiabso">（<span>1</span>/2）</div></div>'
//						    factoryLicenseHtml+='<div class="gs posiabso">（<span>1</span>/2）</div>'
				    		$("#factoryLicense").append(factoryLicenseHtml);
				    	}else{//有营业执照两张
				    		factoryLicenseHtml+='<div class="col-xs-6 padlr w200 m20"><a href="###" class="aimg">'
				    		factoryLicenseHtml+='<img src="/static_img/factory_license/'+data.factoryId+'/'+factoryLicenseS[i]+'" alt="" class="img-responsive">'				    		
 	                     	if(i==0){
 	                     		factoryLicenseHtml+='<div class="sy" factoryLicense="'+factoryLicenseS[i]+'" onclick="delFactoryLicense(this)"><span class="iconfont">&#xe616;</span></div></a></div>'
				    		}else if(i==1){
				    			factoryLicenseHtml+='<div class="sy" factoryLicense="'+factoryLicenseS[i]+'" onclick="delFactoryLicense(this)"><span class="iconfont">&#xe616;</span></div></a><div class="gs posiabso">（<span>2</span>/2）</div></div>'
				    		}
				    		$("#factoryLicense").append(factoryLicenseHtml);
				    	}
			    	}
			    }else{//无营业执照
			    	var factoryLicenseHtml='';
			    	factoryLicenseHtml+='<div class="col-xs-6 padlr w200"><a href="###" class="aimg aimg2 text-center">'
			        factoryLicenseHtml+='<img src="../images/products/add.png" alt="" class="img-responsive" onclick="uploadFactoryLicenseFile()"><br/><span>上传图片</span></a><input id="uploadFactoryLicenseFile" name="files" type="file" class="imgfile" onchange="updateFactoryLicense(this)"/><div class="gs posiabso">（<span>0</span>/2）</div></div>'
//			        factoryLicenseHtml+='<div class="gs posiabso">（<span>0</span>/2）</div>';
			    	$("#factoryLicense").append(factoryLicenseHtml);
			    }
			    if(data.state!=null && data.state!=''){
			    	//选择省份
				    $("#province").find("option").each(function(){
				    	if($(this).text()==data.state){
				    		$(this).attr("selected","selected");
				    		loadProvince($(this).val());
				    	}
				    })
			    }
			    if(data.city!=null && data.city!=''){
			    	//选择市
				    $("#city").find("option").each(function(){
			    	  if($(this).text()==data.city){
						 $(this).attr("selected","selected");
						 loadCity($(this).val());
					  }
					})
			    }
			    if(data.district!=null && data.district!=''){
			    	//选择市区
	                $("#district").find("option").each(function(){
					    if($(this).text()==data.district){
					    	$(this).attr("selected","selected");
					    }
					})	
			    }
				
			    		
			    $("#detailsAddress").val(data.detailsAddress);
			   //处理工厂厂门照片
				var factoryGate=data.factoryGate;
				var factoryGateS="";
				if(factoryGate!=null && factoryGate!=""){
					factoryGateS = factoryGate.split(',');
				}
				for(var i = 0;i<factoryGateS.length;i++){
				    if(factoryGateS[i]==''||factoryGateS[i]==null||typeof(factoryGateS[i])==undefined){
				    	factoryGateS.splice(i,1);
				        i=i-1;
				    }
				}
			    if(factoryGateS.length>0){//有工厂厂门照片
			    	for(var i=0;i<factoryGateS.length;i++){
			    		var factoryGateHtml='';
				    	if(factoryGateS.length<=1){//有工厂厂门照片一张
				    	  factoryGateHtml+='<div class="col-xs-6 padlr w200 m20"><a href="###" class="aimg">'
				    	  factoryGateHtml+='<img src="/static_img/factory_gate/'+data.factoryId+'/'+factoryGateS[i]+'" alt="" class="img-responsive">'
				    	  factoryGateHtml+='<div class="sy" factoryGate="'+factoryGateS[i]+'" onclick="delFactoryGate(this)"><span class="iconfont">&#xe616;</span></div></a></div>';
				    	  factoryGateHtml+='<div class="col-xs-6 padlr w200 m20"><a href="###" class="aimg aimg2 text-center">'
				    	  factoryGateHtml+='<img src="../images/products/add.png" alt="" class="img-responsive" onclick="uploadFactoryGateFile()"><br/><span>上传图片</span></a><input id="uploadFactoryGateFile" type="file" name="file" class="imgfile" onchange="uploadFactoryGate(this)"/><div class="gs posiabso">（<span>1</span>/2）</div></div>'
//				    	  factoryGateHtml+='<div class="gs posiabso">（<span>1</span>/2）</div>';
				    	  $("#factoryGate").append(factoryGateHtml);
				    	}else{//有工厂厂门照片两张
				    	  factoryGateHtml+='<div class="col-xs-6 padlr w200 m20"><a href="###" class="aimg">'
						  factoryGateHtml+='<img src="/static_img/factory_gate/'+data.factoryId+'/'+factoryGateS[i]+'" alt="" class="img-responsive">'
						  
	                      if(i==0){
	                     		factoryGateHtml+='<div class="sy" factoryGate="'+factoryGateS[i]+'" onclick="delFactoryGate(this)"><span class="iconfont">&#xe616;</span></div></a></div>';
				    	  }else if(i==1){
				    			factoryGateHtml+='<div class="sy" factoryGate="'+factoryGateS[i]+'" onclick="delFactoryGate(this)"><span class="iconfont">&#xe616;</span></div></a><div class="gs posiabso">（<span>2</span>/2）</div></div>';
				    	  }
				    	  $("#factoryGate").append(factoryGateHtml);
				    	}
			    	}
			    }else{//无工厂厂门照片
			    	var factoryGateHtml='';
			    	factoryGateHtml+='<div class="col-xs-6 padlr w200"><a href="###" class="aimg aimg2 text-center">'
			    	factoryGateHtml+='<img src="../images/products/add.png" alt="" class="img-responsive" onclick="uploadFactoryGateFile()"><br/><span>上传图片</span></a><input id="uploadFactoryGateFile" type="file" name="file" class="imgfile" onchange="uploadFactoryGate(this)" /><div class="gs posiabso">（<span>0</span>/2）</div></div>'
//			    	factoryGateHtml+='<div class="gs posiabso">（<span>0</span>/2）</div>';
			    	$("#factoryGate").append(factoryGateHtml);
			    }
			    
				$("#establishmentYear").val(data.establishmentYear);
				$("#staffNumber").find("option[value= '"+data.staffNumber+"']").attr("selected","selected");
				$("#factoryAcreage").find("option[value= '"+data.factoryAcreage+"']").attr("selected","selected");
			    $("#factoryValue").find("option[value= '"+data.factoryValue+"']").attr("selected","selected");
				$("#type").find("option[value= '"+data.type+"']").attr("selected","selected");
				$("#website").val(data.website);
				$("#factoryInfoHtml").val(data.factoryInfo);
				
				//处理资质认证
				var qualificationList=data.qualificationList;
				for (var j = 0; j < qualificationList.length; j++) {
					var qualificationHtml='';
					if(qualificationList[j].fileUrl!=null && qualificationList[j].fileUrl!=""){//已经上传过附件
						qualificationHtml+='<tr><td class="td1">'+qualificationList[j].qualificationName+'</td><td class="td2">'+(new Date(qualificationList[j].validityTime.replace(/-/g,"/").split(".")[0])).Format("yyyy-MM-dd")+'</td>'
						qualificationHtml+='<td class="td3 posirela"><em>'+qualificationList[j].fileUrl+'</em><span class="posiabso"></span></td>'
						qualificationHtml+='<td class="tdlast1"><span class="pull-left posirela"><em class="iconfont">&#xe72c;</em>修改<input type="file" dataid="'+qualificationList[j].id+'" id="modifyQualificationPic" onchange="modifyQualification(this)" name="file" class="picfile"/></span>'
						qualificationHtml+='<span class="pull-right" onclick="delqualificationFile('+qualificationList[j].id+')"><em class="iconfont">&#xe616;</em>删除</span></td></tr>'
						$("#qualificationList").append(qualificationHtml);
					}else{//没上传附件
						qualificationHtml+='<tr><td class="td1">'+qualificationList[j].qualificationName+'</td><td class="td2">'+(new Date(qualificationList[j].validityTime.replace(/-/g,"/").split(".")[0])).Format("yyyy-MM-dd")+'</td>'
						qualificationHtml+='<td class="td3 posirela"><span class="posiabso"></span></td>'
						qualificationHtml+='<td class="tdlast1 "><span class="pull-left posirela"><em class="iconfont">&#xe66e;</em><input id="uploadQualificationFile" dataid="'+qualificationList[j].id+'" onchange="uploadQualification(this)" type="file" name="file" class="imgfile1"/>上传附件</span></td>'
						qualificationHtml+='<span class="pull-right" onclick="delqualificationFile('+qualificationList[j].id+')"><em class="iconfont">&#xe616;</em>删除</span></td></tr>';
						$("#qualificationList").append(qualificationHtml);
					}
					
				}
				//处理公司的优势
				$("#technologicalAdvantage").val(data.technologicalAdvantage);
				$("#dominantMaterialModel").val(data.dominantMaterialModel);
				$("input[type=radio][name='inlineRadioOptions'][value="+data.dominantMaterialSize+"]").attr("checked",'checked');
				$("#mainProcess").val(data.mainProcess);

	            
				//工艺选择事件
	    		var strs = [];
	    		if(data.mainProcess != null && data.mainProcess != '' && data.mainProcess != undefined){
	    			strs = data.mainProcess.split(",");
	    		}	
	    		if(strs.length != 0){
	    			for(var n=0;n<strs.length;n++){
	    				$('#mainProcessForeach').find('input[type=checkbox][name="mainProcess"]').each(function(){
	    					if($(this).val() == strs[n]){
	    						$(this).attr("checked",true);
	    					}
	    				})    
	    			}
	    		}
				
				
				
				$("input[type=radio][name='siteSize'][value="+data.siteSize+"]").attr("checked",'checked');
				//处理合作过得企业
				var cooperativeEnterpriseS=data.cooperativeEnterpriseS;
				if(cooperativeEnterpriseS !=null && cooperativeEnterpriseS!="" && cooperativeEnterpriseS!=undefined){	
					for (var k = 0; k < cooperativeEnterpriseS.length; k++) {
					  var cooperativeEnterpriseHtml='';
					  cooperativeEnterpriseHtml+='<div class="form-group posirela"><label for="name" class="col-sm-2 control-label">第'+doChangeChinese(k+1)+'主要用户</label>'
					  if(k == 0){
						  cooperativeEnterpriseHtml+='<div class="col-sm-10"><input type="text" class="form-control w450" id="name" placeholder="" value="'+cooperativeEnterpriseS[k]+'"></div></div>';
					  }else{
						  cooperativeEnterpriseHtml+='<div class="col-sm-10"><input type="text" class="form-control w450" id="name" placeholder="" value="'+cooperativeEnterpriseS[k]+'"><div class="add add3 add4" onclick="delCooperativeEnterprise(this)"><button class="btn btn-default pull-right">删除 -</button></div></div></div>'; 
					  }					 
					  $("#cooperativeEnterpriseS").append(cooperativeEnterpriseHtml);
					}
				}
				var equipmentList=data.equipmentList;
//				//关键设备清单
				var equipmentHtml=''
				for(var m = 0; m < equipmentList.length; m++){
//					equipmentHtml+='<tr><td>'+equipmentList[m].equipmentName+'</td><td>'+equipmentList[m].equipmentModel+'</td><td>'+equipmentList[m].number+'</td><td>'+equipmentList[m].type+'</td>';
//					equipmentHtml+='<td class="tdlast"><span class="iconfont">&#xe72c;</span><em onclick="updateEquipmentShow('+equipmentList[m].id+',this)">修改</em><sapn class="iconfont span2">&#xe616;</sapn><em onclick="delEquipment('+equipmentList[m].id+')">删除</em></td></tr>'
			
				var tr = '<tr>'+
           			  '<td>'+
           				'<input type="text" style="display:none;" class="form-control w180" value="'+equipmentList[m].equipmentName+'"/>'+
           				'<span class="w180">'+equipmentList[m].equipmentName+'</span>'+
           			'</td>'+
                    '<td>'+
                    	'<input type="text" style="display:none;" class="form-control w180" value="'+equipmentList[m].equipmentModel+'"/>'+
                    	'<span class="w180">'+equipmentList[m].equipmentModel+'</span>'+	
                    '</td>'+
                    '<td>'+
                    	'<input type="number" style="display:none;" onKeyPress="return inputNum(event);"  min="1" placeholder="1" class="form-control w104" id="number" value="'+equipmentList[m].number+'"/>'+
                    	'<span class="w104">'+equipmentList[m].number+'</span>'+
                    '</td>'+
                    '<td>'+
                    	'<input type="text" style="display:none;" class="form-control w270" value="'+equipmentList[m].type+'"/>'+
                    	'<span class="w270">'+equipmentList[m].type+'</span>'+	
                    '</td>'+
                    '<td class="tdlast">'+
                        '<span class="save" onclick="addEquipment(this,'+equipmentList[m].id+')" style="display:none;"><i class="iconfont">&#xe609;</i><em>保存</em></span>'+
                        '<span class="modi" onclick="editEquipment(this)"><i class="iconfont span2">&#xe62d;</i><em>修改</em></span>'+
                        '<span class="del" onclick="delEquipment('+equipmentList[m].id+',this)"><i class="iconfont span2">&#xe616;</i><em>删除</em></span>'+	                                        
                    '</td>'+
           		     '</tr>';
				  $("#equipmentList").append(tr);
				}
				
				if(data.factoryEquipmentDocument != null && data.factoryEquipmentDocument != '' && data.factoryEquipmentDocument != undefined){
					var path = data.factoryEquipmentDocument;
				    var sppath = path.split("\/");
				    var fileName = sppath[sppath.length-1];	  	  
				    $('.fjmc').find('span').attr('onclick',"download_equipment("+data.id+")").text(fileName);
				    $('.fjmc').find('span').next().attr("onclick","del_equipment(this,("+data.id+"))");
				}
				
//				equipmentHtml+='<tr style="display:none"><td><input type="text" class="form-control w180" placeholder="油式立压机"/></td>'
//				equipmentHtml+='<td><input type="text" class="form-control w180"/></td><td><input type="text" class="form-control w104" placeholder="1"/></td>'
//				equipmentHtml+='<td><input type="text" class="form-control w270"/></td><td class="tdlast"><span class="iconfont">&#xe609;</span>保存'
//				equipmentHtml+='<sapn class="iconfont span2">&#xe616;</sapn>删除/td></tr>'
//				$("#equipmentList").append(equipmentHtml);
			       
                var productionEnvironment=data.productionEnvironment;
                var productionEnvironmentS="";
                if(productionEnvironment!=null &&productionEnvironment!=''){
                	productionEnvironmentS=productionEnvironment.split(',');
                }
                for(var i = 0;i<productionEnvironmentS.length;i++){
				    if(productionEnvironmentS[i]==''||productionEnvironmentS[i]==null||typeof(productionEnvironmentS[i])==undefined){
				    	productionEnvironmentS.splice(i,1);
				        i=i-1;
				    }
				}
                $("#productionEnvironment").empty();
                if(productionEnvironmentS.length>0){//有生产环境照片
			    	for(var i=0;i<productionEnvironmentS.length;i++){	
						var productionEnvironmentHtml='';
				    	if(productionEnvironmentS.length<6){//生产环境照片小于6张
				    	  productionEnvironmentHtml='<div class="col-xs-4 padlr w200 m20 t20"><a href="###" class="aimg">'
				    	  productionEnvironmentHtml+='<img src="/static_img/production_environment/'+data.factoryId+'/'+productionEnvironmentS[i]+'" alt="" class="img-responsive">'
				    	  productionEnvironmentHtml+='<div class="sy" productionEnvironment="'+productionEnvironmentS[i]+'" onclick="delProductionEnvironment(this)"><span class="iconfont">&#xe616;</span></div> </a></div>'
				    	  /*$("#productionEnvironment").append(productionEnvironmentHtml); */
				    	  if(i==productionEnvironmentS.length-1){//当遍历出最后一个时
				    		  productionEnvironmentHtml+='<div class="col-xs-4 padlr w200 m20 t20"><a href="###" class="aimg aimg2 text-center">'
							  productionEnvironmentHtml+='<img src="../images/products/add.png" alt="" class="img-responsive" onclick="uploadProductionEnvironmentFile()"><br/><span>上传图片</span></a><input id="uploadProductionEnvironmentFile" onchange="uploadProductionEnvironment(this)"type="file" name="file" class="imgfile" /><div class="gs posiabso gs2">（<span>'+productionEnvironmentS.length+'</span>/6）</div></div>'
//							  productionEnvironmentHtml+='<div class="gs posiabso gs2">（<span>'+productionEnvironmentS.length+'</span>/6）</div>'
				    	  }
				    	  $("#productionEnvironment").append(productionEnvironmentHtml);  
				    	}else{//生产环境照片为6张


					    	productionEnvironmentHtml='<div class="col-xs-4 padlr w200 m20 t20"><a href="###" class="aimg"><img src="/static_img/production_environment/'+data.factoryId+'/'+productionEnvironmentS[i]+'" alt="" class="img-responsive">'
					    	productionEnvironmentHtml+='<div class="sy" productionEnvironment="'+productionEnvironmentS[i]+'" onclick="delProductionEnvironment(this)"><span class="iconfont">&#xe616;</span></div></a></div>'
				    		$("#productionEnvironment").append(productionEnvironmentHtml);
				    	}
			    	}
			    }else{//无生产环境照片
			    	var productionEnvironmentHtml='';
			    	productionEnvironmentHtml+='<div class="col-xs-4 padlr w200 m20 t20"><a href="###" class="aimg aimg2 text-center">'
			    	productionEnvironmentHtml+='<img src="../images/products/add.png" alt="" class="img-responsive" onclick="uploadProductionEnvironmentFile()"><br/><span>上传图片</span></a><input id="uploadProductionEnvironmentFile" onchange="uploadProductionEnvironment(this)"type="file" name="file" class="imgfile" /><div class="gs posiabso gs2">（<span>0</span>/6）</div></div>'
//			    	productionEnvironmentHtml+='<div class="gs posiabso gs2">（<span>0</span>/6）</div>'
			    	$("#productionEnvironment").append(productionEnvironmentHtml);
			    }
                if(data.productionVideo!=null && data.productionVideo!=''){
                	 $("#productionVideoUrl").text(data.productionVideo);
                     $("#videoBoxUrl").val("/static_img/production_video/"+data.factoryId+"/"+data.productionVideo);	
//                     $("#video_box").attr("src","/static_img/production_video/"+data.factoryId+"/"+data.productionVideo);
                }else{
                	$("#delProductionVideoHtml").hide();
                	$("#productionVideoDiv").hide();
                }
            
				//可接受询盘条件
				$("input[type=radio][name='optionsRadios'][value="+data.acceptCondition+"]").attr("checked",'checked');
				$("#acceptMoney").find("option[value= '"+data.acceptMoney+"']").attr("selected","selected");
				
				
				
				
				 // 关键设备清单js
//			    $('.modify_files .tdlast .modi').hide();      
//			    $('.modify_files .tdlast .save').click(function(){
//			    	$(this).parents('tr').find('td:not(last)').find('span').show();
//			    	$(this).parents('tr').find('input').hide();
//			    });
			    $('.modify_files .tdlast .modi').click(function(){
			    	$(this).parents('tr').find('td:not(:last-child)').find('span').hide();
			    	$(this).hide();
			    	$(this).prev().show();
			    	$(this).parents('tr').find('input').show();
			    });
				
			}else if(result.state == 2){
	    		 //如果还未登录，跳转登录页
	    		 window.location = "/zh/login.html";
	        }    
		}
	})
}


function addcss(){
	
	 /* body 高度控制底部位置 */
   var h1 = $(document.body).height();
   var h2 = window.screen.availHeight  ;
   if(h1 < h2){
       $('#footer').addClass('footer1')
   }else{
       $('#footer').removeClass('footer1');
   }; 
	
}




/**
 * 编辑企业基本档案信息
 */
function editFactoryInfo(){
	var factoryName=$("#factoryName").val();
	var aliWebsite=$("#aliWebsite").val();
	var tel=$("#tel").val();
	var mobile=$("#mobile").val();
	var province=$("#province").find("option:selected").text();
	if(province == "请选择省份"){
	   province = "";
	}
	var city=$("#city").find("option:selected").text();
	if(city == "请选择城市"){
		city = "";
	}
	var district=$("#district").find("option:selected").text();
	if(district == "请选择区域"){
		district = "";
	}
	var detailsAddress=$("#detailsAddress").val();
	var establishmentYear=$("#establishmentYear").val();
	var staffNumber=$("#staffNumber").find('option:selected').val();
	var factoryAcreage=$("#factoryAcreage").find('option:selected').val();
	var factoryValue=$("#factoryValue").find('option:selected').val();
	var type=$("#type").find("option:selected").val();
	var website=$("#website").val();
	var factoryInfo=$("#factoryInfoHtml").val();
//	var factoryLicenseFileS=$("#factoryLicenseFileS").val();
//	var factoryGateFileS=$("#factoryGateFileS").val();
	var factoryLogoFileS=$("#factoryLogoFileS").val();

	$.ajax({
		url : "/factoryInfo/updateFactoryInfo.do",
		type : "post",
		traditional : true,
		data : {
			factoryName:factoryName,aliWebsite:aliWebsite,tel:tel,mobile:mobile,province:province,factoryInfo:factoryInfo,
			city:city,district:district,detailsAddress:detailsAddress,establishmentYear:establishmentYear,
			staffNumber:staffNumber,factoryAcreage:factoryAcreage,factoryValue:factoryValue,type:type,
			website:website,factoryLogoFileS:factoryLogoFileS 
		},
		datatype : "json",
		success : function(result) {
			if (result.state == 0) {
				selectEditFactoryInfo();
				easyDialog.open({
					container : {
						header : '提示信息',
						content : '操作成功'
					},
					overlay : false,
					autoClose : 1000
				});
			}else if(result.state == 2){
	    		 //如果还未登录，跳转登录页
	    		 window.location = "/zh/login.html";
	        }else{
				easyDialog.open({
					container : {
						header : '提示信息',
						content : '操作失败'
					},
					overlay : false,
					autoClose : 1000
				});
			}
		}
	})
}
/***
 * 查询企业档案基本信息(编辑成功之后的)
 */
function selectEditFactoryInfo(){
	$.ajax({
		url : "/factoryInfo/selectFactoryInfo.do",
		type : "post",
		traditional : true,
		data : {
			
		},
		datatype : "json",
		success : function(result) {
			if (result.state == 0) {
				//清空工厂,营业执照
				$("#factoryLicense").empty();
				$("#factoryGate").empty();
				var data=result.data.factoryInfo;
				$("#factoryName").val(data.factoryName);
				$("#aliWebsite").val(data.aliWebsite);
				$("#tel").val(data.tel);
				//工厂logo显示
				if(data.factoryLogo!=null && data.factoryLogo!=''){
					$("#factoryLogoHtml").attr('src',"/static_img/factory_logo/"+data.factoryId+'/'+data.factoryLogo); 
				}
				//处理营业执照
				var factoryLicense=data.factoryLicense;
				var factoryLicenseS="";
				if(factoryLicense!=null && factoryLicense!=""){
					factoryLicenseS = factoryLicense.split(',');
				}
				for(var i = 0;i<factoryLicenseS.length;i++){
				    if(factoryLicenseS[i]==''||factoryLicenseS[i]==null||typeof(factoryLicenseS[i])==undefined){
				    	factoryLicenseS.splice(i,1);
				        i=i-1;
				    }
				}
			    if(factoryLicenseS.length>0){//有营业执照
			    	for(var i=0;i<factoryLicenseS.length;i++){	
						var factoryLicenseHtml='';
				    	if(factoryLicenseS.length<=1){//有营业执照一张
				    		factoryLicenseHtml='<div class="col-xs-6 padlr w200 m20"><a href="###" class="aimg">'
				    		factoryLicenseHtml+='<img src="/static_img/factory_license/'+data.factoryId+'/'+factoryLicenseS[i]+'" alt="" class="img-responsive">'
				    		factoryLicenseHtml+='<div class="sy" factoryLicense="'+factoryLicenseS[i]+'" onclick="delFactoryLicense(this)"> <span class="iconfont")">&#xe616;</span></div></a></div>'
				    		factoryLicenseHtml+='<div class="col-xs-6 padlr w200"><a href="###" class="aimg aimg2 text-center">'
						    factoryLicenseHtml+='<img src="../images/products/add.png" alt="" class="img-responsive" onclick="uploadFactoryLicenseFile()"><br/><span>上传图片</span></a><input id="uploadFactoryLicenseFile" name="files" type="file" class="imgfile" onchange="updateFactoryLicense(this)" /><div class="gs posiabso">（<span>1</span>/2）</div></div>'
//						    factoryLicenseHtml+='<div class="gs posiabso">（<span>1</span>/2）</div>'
				    		$("#factoryLicense").append(factoryLicenseHtml);
				    	}else{//有营业执照两张
				    		factoryLicenseHtml+='<div class="col-xs-6 padlr w200 m20"><a href="###" class="aimg">'
				    		factoryLicenseHtml+='<img src="/static_img/factory_license/'+data.factoryId+'/'+factoryLicenseS[i]+'" alt="" class="img-responsive">'
//				    		factoryLicenseHtml+='<div class="sy" factoryLicense="'+factoryLicenseS[i]+'" onclick="delFactoryLicense(this)"> <span class="iconfont">&#xe616;</span></div></a></div>'
				    		if(i==0){
 	                     		factoryLicenseHtml+='<div class="sy" factoryLicense="'+factoryLicenseS[i]+'" onclick="delFactoryLicense(this)"><span class="iconfont">&#xe616;</span></div></a></div>'
				    		}else if(i==1){
				    			factoryLicenseHtml+='<div class="sy" factoryLicense="'+factoryLicenseS[i]+'" onclick="delFactoryLicense(this)"><span class="iconfont">&#xe616;</span></div></a><div class="gs posiabso">（<span>2</span>/2）</div></div>'
				    		}
				    		$("#factoryLicense").append(factoryLicenseHtml);
				    	}
			    	}
			    }else{//无营业执照
			    	var factoryLicenseHtml='';
			    	factoryLicenseHtml+='<div class="col-xs-6 padlr w200"><a href="###" class="aimg aimg2 text-center">'
			        factoryLicenseHtml+='<img src="../images/products/add.png" alt="" class="img-responsive" onclick="uploadFactoryLicenseFile()"><br/><span>上传图片</span></a><input id="uploadFactoryLicenseFile" name="files" type="file" class="imgfile" onchange="updateFactoryLicense(this)"/><div class="gs posiabso">（<span>0</span>/2）</div></div>'
//			        factoryLicenseHtml+='<div class="gs posiabso">（<span>0</span>/2）</div>';
			    	$("#factoryLicense").append(factoryLicenseHtml);
			    }
			    if(data.state!=null && data.state!=''){
			    	//选择省份
				    $("#province").find("option").each(function(){
				    	if($(this).text()==data.state){
				    		$(this).attr("selected","selected");
				    		loadProvince($(this).val());
				    	}
				    })
			    }
			    if(data.city!=null && data.city!=''){
			    	//选择市
				    $("#city").find("option").each(function(){
			    	  if($(this).text()==data.city){
						 $(this).attr("selected","selected");
						 loadCity($(this).val());
					  }
					})
			    }
			    if(data.district!=null && data.district!=''){
			    	//选择市区
	                $("#district").find("option").each(function(){
					    if($(this).text()==data.district){
					    	$(this).attr("selected","selected");
					    }
					})	
			    }
			    $("#detailsAddress").val(data.detailsAddress);
			   //处理工厂厂门照片
				var factoryGate=data.factoryGate;
				var factoryGateS="";
				if(factoryGate!=null && factoryGate!=""){
					factoryGateS = factoryGate.split(',');
				}
				for(var i = 0;i<factoryGateS.length;i++){
				    if(factoryGateS[i]==''||factoryGateS[i]==null||typeof(factoryGateS[i])==undefined){
				    	factoryGateS.splice(i,1);
				        i=i-1;
				    }
				}
			    if(factoryGateS.length>0){//有工厂厂门照片
			    	for(var i=0;i<factoryGateS.length;i++){
			    		var factoryGateHtml='';
				    	if(factoryGateS.length<=1){//有工厂厂门照片一张
				    	  factoryGateHtml+='<div class="col-xs-6 padlr w200 m20"><a href="###" class="aimg">'
				    	  factoryGateHtml+='<img src="/static_img/factory_gate/'+data.factoryId+'/'+factoryGateS[i]+'" alt="" class="img-responsive">'
				    	  factoryGateHtml+='<div class="sy" factoryGate="'+factoryGateS[i]+'" onclick="delFactoryGate(this)"><span class="iconfont">&#xe616;</span></div></a></div>';
				    	  factoryGateHtml+='<div class="col-xs-6 padlr w200 m20"><a href="###" class="aimg aimg2 text-center">'
				    	  factoryGateHtml+='<img src="../images/products/add.png" alt="" class="img-responsive" onclick="uploadFactoryGateFile()"><br/><span>上传图片</span></a><input id="uploadFactoryGateFile" type="file" name="files" class="imgfile" onchange="uploadFactoryGate(this)"  /><div class="gs posiabso">（<span>1</span>/2）</div></div>'
//				    	  factoryGateHtml+='<div class="gs posiabso">（<span>1</span>/2）</div>';
				    	  $("#factoryGate").append(factoryGateHtml);
				    	}else{//有工厂厂门照片两张
				    	  factoryGateHtml+='<div class="col-xs-6 padlr w200 m20"><a href="###" class="aimg">'
						  factoryGateHtml+='<img src="/static_img/factory_gate/'+data.factoryId+'/'+factoryGateS[i]+'" alt="" class="img-responsive">'					  
				    	  if(i == 0){
				    		  factoryGateHtml+='<div class="sy" factoryGate="'+factoryGateS[i]+'" onclick="delFactoryGate(this)"><span class="iconfont">&#xe616;</span></div></a></div>'; 
				    	  }else if(i ==1){
				    		  factoryGateHtml+='<div class="sy" factoryGate="'+factoryGateS[i]+'" onclick="delFactoryGate(this)"><span class="iconfont">&#xe616;</span></div></a><div class="gs posiabso">（<span>2</span>/2）</div></div>';
				    	  }
				    	  $("#factoryGate").append(factoryGateHtml);
				    	}
			    	}
			    }else{//无工厂厂门照片
			    	var factoryGateHtml='';
			    	factoryGateHtml+='<div class="col-xs-6 padlr w200"><a href="###" class="aimg aimg2 text-center">'
			    	factoryGateHtml+='<img src="../images/products/add.png" alt="" class="img-responsive" onclick="uploadFactoryGateFile()"><br/><span>上传图片</span></a><input id="uploadFactoryGateFile" type="file" name="files" class="imgfile" onchange="uploadFactoryGate(this)" /><div class="gs posiabso">（<span>0</span>/2）</div></div>'
//			    	factoryGateHtml+='<div class="gs posiabso">（<span>0</span>/2）</div>';
			    	$("#factoryGate").append(factoryGateHtml);
			    }
			    
				$("#establishmentYear").val(data.establishmentYear);
				$("#staffNumber").find("option[value= '"+data.staffNumber+"']").attr("selected","selected");
				$("#factoryAcreage").find("option[value= '"+data.factoryAcreage+"']").attr("selected","selected");
			    $("#factoryValue").find("option[value= '"+data.factoryValue+"']").attr("selected","selected");
				$("#type").find("option[value= '"+data.type+"']").attr("selected","selected");
				$("#website").val(data.website);
				$("#factoryInfoHtml").val(data.factoryInfo);
			}else if(result.state == 2){
	    		 //如果还未登录，跳转登录页
	    		 window.location = "/zh/login.html";
	        }    
		}
	})
}

/***
 * 添加公司的优势
 */
function addFactoryAdvantage(){
	var technologicalAdvantage=$("#technologicalAdvantage").val();
	var dominantMaterialModel=$("#dominantMaterialModel").val();
	var dominantMaterialSize=$("input[name='inlineRadioOptions']:checked").val();
	var siteSize=$("input[name='siteSize']:checked").val();
	var mainProcess =$("#mainProcess").val();
	$.ajax({
		url : "/factoryInfo/updateFactoryAdvantage.do",
		type : "post",
		traditional : true,
		data : {
			technologicalAdvantage:technologicalAdvantage,dominantMaterialModel:dominantMaterialModel,
			dominantMaterialSize:dominantMaterialSize,mainProcess:mainProcess,siteSize:siteSize
		},
		datatype : "json",
		success : function(result) {
			if (result.state == 0) {
				easyDialog.open({
		    		  container : {
		    		    header : '提示信息',
		    		    content : "操作成功"
		    		  },
		    		  overlay : false,
		    		  autoClose : 1000   			    		  
		    	});	
				selectFactoryAdvantage();
			}else if(result.state == 2){
	    		 //如果还未登录，跳转登录页
	    		 window.location = "/zh/login.html";
	        }else{
				easyDialog.open({
		    		  container : {
		    		    header : '提示信息',
		    		    content : "操作失败"
		    		  },
		    		  overlay : false,
		    		  autoClose : 1000   			    		  
		    	});	
			}
		}
	})
}
/**
 * 查询公司的优势
 */
function selectFactoryAdvantage(){
	$.ajax({
		url : "/factoryInfo/selectFactoryDetailsInfo.do",
		type : "post",
		traditional : true,
		data : {
			
		},
		datatype : "json",
		success : function(result) {
			if (result.state == 0) {
				//处理公司的优势
				var data=result.data.factoryInfo;
				$("#technologicalAdvantage").val(data.technologicalAdvantage);
				$("#dominantMaterialModel").val(data.dominantMaterialModel);
				$("input[type=radio][name='inlineRadioOptions'][value="+data.dominantMaterialSize+"]").attr("checked",'checked');
				$("input[type=radio][name='siteSize'][value="+data.siteSize+"]").attr("checked",'checked');
				$("#mainProcess").val(data.mainProcess);
			}else if(result.state == 2){
	    		 //如果还未登录，跳转登录页
	    		 window.location = "/zh/login.html";
	        }       
		}
	})
}

/**
 * 添加询盘条件
 * @param num
 * @returns {String}
 */
function updateAcceptCondition(){
	var acceptCondition=$("input[type=radio][name='optionsRadios']:checked").val();
	var acceptMoney=$("#acceptMoney").find('option:selected').val();
	$.ajax({
		url : "/factoryInfo/updateAcceptCondition.do",
		type : "post",
		data : {
			acceptCondition:acceptCondition,acceptMoney:acceptMoney
		},
		datatype : "json",
		success : function(result) {
			if (result.state == 0) {
				easyDialog.open({
		    		  container : {
		    		    header : '提示信息',
		    		    content : "操作成功"
		    		  },
		    		  overlay : false,
		    		  autoClose : 1000   			    		  
		    		});	
				selectAcceptCondition();
			}else if(result.state == 2){
	    		 //如果还未登录，跳转登录页
	    		 window.location = "/zh/login.html";
	        }else{
				easyDialog.open({
		    		  container : {
		    		    header : '提示信息',
		    		    content : "操作失败"
		    		  },
		    		  overlay : false,
		    		  autoClose : 1000   			    		  
		    		});	
			}
		}
	})
}
/**
 * 查询接受询盘条件
 */
function selectAcceptCondition(){
	$.ajax({
		url : "/factoryInfo/selectFactoryDetailsInfo.do",
		type : "post",
		traditional : true,
		data : {
		},
		datatype : "json",
		success : function(result) {
			if (result.state == 0) {
				//可接受询盘条件
				var data=result.data.factoryInfo;
				$("input[type=radio][name='optionsRadios'][value="+data.acceptCondition+"]").attr("checked",'checked');
				$("#acceptMoney").find("option[value= '"+data.acceptMoney+"']").attr("selected","selected");
			}else if(result.state == 2){
	    		 //如果还未登录，跳转登录页
	    		 window.location = "/zh/login.html";
	        }       
		}
	})
}

/**
 * 添加合作企业
 */
function addCooperativeEnterprise(){
	var data='';
	var cooperativeS='';
	$("#cooperativeEnterpriseS").find("div.form-group.posirela").each(function(i){
		var cooperative=$("#cooperativeEnterpriseS").find("div.form-group.posirela").eq(i).find('input').val().trim();
		cooperativeS=cooperative+',';
		data+=cooperativeS;
	})
	data = data.substring(0, data.length - 1);  
	$.ajax({
		url : "/factoryInfo/addCooperativeEnterprise.do",
		type : "post",
		data : {
			data:data
		},
		datatype : "json",
		success : function(result) {
			if (result.state == 0) {
				easyDialog.open({
					container : {
						header : '提示信息',
						content : '操作成功'
					},
					overlay : false,
					autoClose : 1000
				});
				selectCooperativeEnterprise();
			}else if(result.state == 2){
	    		 //如果还未登录，跳转登录页
	    		 window.location = "/zh/login.html";
	        }else{
				easyDialog.open({
					container : {
						header : '提示信息',
						content : '操作失败'
					},
					overlay : false,
					autoClose : 1000
				});
			}
		}
	})
}
/***
 * 查询合作企业
 */
function selectCooperativeEnterprise(){
	$.ajax({
		url : "/factoryInfo/selectFactoryDetailsInfo.do",
		type : "post",
		traditional : true,
		data : {		
		},
		datatype : "json",
		success : function(result) {
			if (result.state == 0) {
				$("#cooperativeEnterpriseS").empty();
				var data=result.data.factoryInfo;
				//处理合作过得企业
				var cooperativeEnterpriseS=data.cooperativeEnterpriseS;
				for (var k = 0; k < cooperativeEnterpriseS.length; k++) {
				  var cooperativeEnterpriseHtml='';
				  cooperativeEnterpriseHtml+='<div class="form-group posirela"><label for="name" class="col-sm-2 control-label">第'+doChangeChinese(k+1)+'主要用户</label>'
				  if(k == 0){
					  cooperativeEnterpriseHtml+='<div class="col-sm-10"><input type="text" class="form-control w450" id="name" placeholder="" value="'+cooperativeEnterpriseS[k]+'"></div></div>';
				  }else{
					  cooperativeEnterpriseHtml+='<div class="col-sm-10"><input type="text" class="form-control w450" id="name" placeholder="" value="'+cooperativeEnterpriseS[k]+'"><div class="add add3 add4" onclick="delCooperativeEnterprise(this)"><button class="btn btn-default pull-right">删除 -</button></div></div></div>'; 
				  }	
				  $("#cooperativeEnterpriseS").append(cooperativeEnterpriseHtml);
				}
			}else if(result.state == 2){
	    		 //如果还未登录，跳转登录页
	    		 window.location = "/zh/login.html";
	        }    
		}
	})
}


/**
 * 点击添加弹出隐藏层
 */
function addEquipmentShow(){
	
	var tr = '<tr>'+
				  '<td>'+
					'<input type="text" class="form-control w180"/>'+
					'<span class="w180" style="display:none;"></span>'+
				 '</td>'+
				  '<td>'+
				  	'<input type="text"  class="form-control w180"/>'+
				  	'<span class="w180" style="display:none;"></span>'+	
				  '</td>'+
				  '<td>'+
				  	'<input type="number" min="1" onKeyPress="return inputNum(event);" placeholder="1" class="form-control w104" id="number" value="1"/>'+
				  	'<span class="w104" style="display:none;">1</span>'+
				  '</td>'+
				  '<td>'+
				  	'<input type="text" class="form-control w270"/>'+
				  	'<span class="w270" style="display:none;"></span>'+	
				  '</td>'+
				  '<td class="tdlast">'+
				      '<span class="save" onclick="addEquipment(this,null);"><i class="iconfont">&#xe609;</i><em>保存</em></span>'+
				      '<span class="modi" onclick="editEquipment(this)" style="display:none;"><i class="iconfont span2">&#xe62d;</i><em>修改</em></span>'+
				      '<span class="del" onclick="delEquipment(null,this)"><i class="iconfont span2">&#xe616;</i><em>删除</em></span>'+	                                        
				  '</td>'+
			'</tr>';
	
	$('#equipmentList').append(tr);
	
	$('#equipmentList').find('tr:last').find('input:first').focus();
}

/**
 * 删除合作企业
 */
function delCooperativeEnterprise(obj){
	$(obj).parent().parent().prev().remove();
	
//	$('.panel3 form').find('.posirela').each(function(i){
	$('.panel3 form').find('.posirela:eq(0)').find('.add4').remove();
//	})

}


/**
 * 点击删除关闭弹出层
 */
function delEquipmentHtml(){
	 $("#addeEuipmentShow").css("display","none");
}
/**
 * 设备清单添加功能
 */
function addEquipment(obj,id){
	var equipmentName=$(obj).parents('tr').find('td:eq(0)').find('input').val();
	var equipmentModel=$(obj).parents('tr').find('td:eq(1)').find('input').val();
	var number=$(obj).parents('tr').find('td:eq(2)').find('input').val();
	var type=$(obj).parents('tr').find('td:eq(3)').find('input').val();
	if(id == undefined){
		id = "";
	}
	if(equipmentName==null || equipmentName=="" || equipmentName==undefined){
		easyDialog.open({
			container : {
				header : '提示信息',
				content : '设备名称为空'
			},
			overlay : false,
			autoClose : 1000
		});
		return false;
	}
	if(equipmentModel==null || equipmentModel=="" || equipmentModel==undefined){
		easyDialog.open({
			container : {
				header : '提示信息',
				content : '设备型号为空'
			},
			overlay : false,
			autoClose : 1000
		});
		return false;
	}
	if(equipmentName==null || equipmentName=="" || equipmentName==undefined){
		easyDialog.open({
			container : {
				header : '提示信息',
				content : '设备名称为空'
			},
			overlay : false,
			autoClose : 1000
		});
		return false;
	}
	$.ajax({
		url : "/equipment/addEquipment.do",
		type : "post",
		data : {
			equipmentName:equipmentName,equipmentModel:equipmentModel,
			number:number,type:type,id:id
		},
		datatype : "json",
		success : function(result) {
			if (result.state == 0) {
				$("#addeEuipmentShow").css("display","none");
				easyDialog.open({
					container : {
						header : '提示信息',
						content : '操作成功'
					},
					overlay : false,
					autoClose : 1000
				});

                $(obj).parents('tr').find('input').each(function(){
                	$(this).hide();
                	$(this).next().text($(this).val()).show();
                })
                $(obj).hide();
                $(obj).next().show();
				$(obj).next().next().attr("onclick","delEquipment("+result.data+",this)");
			}else if(result.state == 2){
	    		 //如果还未登录，跳转登录页
	    		 window.location = "/zh/login.html";
	        }else{
				easyDialog.open({
					container : {
						header : '提示信息',
						content : '操作失败'
					},
					overlay : false,
					autoClose : 1000
				});
			}
		}
	})
}

function editEquipment(obj){	
	$(obj).parents('tr').find('td:not(:last-child)').find('span').hide();
	$(obj).hide();
	$(obj).prev().show();
	$(obj).parents('tr').find('input').show();
}

/**
 * 查询设备清单
 */
function selectEquipment(){
	$.ajax({
		url : "/equipment/selectFactoryEquipment.do",
		type : "post",
		traditional : true,
		data : {
		},
		datatype : "json",
		success : function(result) {
			if (result.state == 0) {
				//关键设备清单
				$("#equipmentList").empty();
				var equipmentList=result.data.equipmentList;
				for(var m = 0; m < equipmentList.length; m++){
					var tr = '<tr>'+
			         			  '<td>'+
			         				'<input type="text" style="display:none;" class="form-control w180" value="'+equipmentList[m].equipmentName+'"/>'+
			         				'<span class="w180">'+equipmentList[m].equipmentName+'</span>'+
			         			'</td>'+
			                  '<td>'+
			                  	'<input type="text" style="display:none;" class="form-control w180" value="'+equipmentList[m].equipmentModel+'"/>'+
			                  	'<span class="w180">'+equipmentList[m].equipmentModel+'</span>'+	
			                  '</td>'+
			                  '<td>'+
			                  	'<input type="number" style="display:none;" min="1" onKeyPress="return inputNum(event);" placeholder="1" class="form-control w104" id="number" value="'+equipmentList[m].number+'"/>'+
			                  	'<span class="w104">'+equipmentList[m].number+'</span>'+
			                  '</td>'+
			                  '<td>'+
			                  	'<input type="text" style="display:none;" class="form-control w270" value="'+equipmentList[m].type+'"/>'+
			                  	'<span class="w270">'+equipmentList[m].type+'</span>'+	
			                  '</td>'+
			                  '<td class="tdlast">'+
			                      '<span class="save" onclick="addEquipment(this,'+equipmentList[m].id+')" style="display:none;"><i class="iconfont">&#xe609;</i><em>保存</em></span>'+
			                      '<span class="modi" onclick="editEquipment(this)"><i class="iconfont span2">&#xe62d;</i><em>修改</em></span>'+
			                      '<span class="del" onclick="delEquipment('+equipmentList[m].id+',this)"><i class="iconfont span2">&#xe616;</i><em>删除</em></span>'+	                                        
			                  '</td>'+
			         		     '</tr>';
				  $("#equipmentList").append(tr);
				}
			}else if(result.state == 2){
	    		 //如果还未登录，跳转登录页
	    		 window.location = "/zh/login.html";
	        }       
		}
	})
}
/**
 * 删除设备清单
 */
function delEquipment(id,obj){	
	if(id == null || id == undefined){
		$(obj).parents('tr').remove();
		return false;
	}
	var id=id;
	$.ajax({
		url : "/equipment/delEquipment.do",
		type : "post",
		data : {
			id:id
		},
		datatype : "json",
		success : function(result) {
			if (result.state == 0) {
				easyDialog.open({
					container : {
						header : '提示信息',
						content : '操作成功'
					},
					overlay : false,
					autoClose : 1000
				});
				$(obj).parents('tr').remove();
			}else if(result.state == 2){
	    		 //如果还未登录，跳转登录页
	    		 window.location = "/zh/login.html";
	        }else{
				easyDialog.open({
					container : {
						header : '提示信息',
						content : '操作失败'
					},
					overlay : false,
					autoClose : 1000
				});
			}
		}
	})
}



/**
 * 上传设备清单（excel、word、pdf）
 */
function upload_equipment(obj){
		
	var trs =$('#equipmentList').find('tr').length;
	if(trs < 3){
		easyDialog.open({
			container : {
				header : '提示信息',
				content : '请填写至少3件设备'
			},
			overlay : false,
			autoClose : 1000
	     });	
		return false;
	}	

	var path = $(obj).val();
	
	if (!getFilesize(obj)) {

		easyDialog.open({
			container : {
				header : '提示信息',
				content : '请上传小于10M的文件'
			},
			overlay : false,
			autoClose : 1000
	     });	
	}	
	var path = $(obj).val();
	var gen = path.lastIndexOf("."); 
    var type = path.substr(gen); 
    if(!(type.toLowerCase()  == ".doc" || type.toLowerCase()==".docx"  
    	|| type.toLowerCase()  == ".xls" || type.toLowerCase()==".xlsx" || type.toLowerCase()==".pdf")){
    	easyDialog.open({
			container : {
				header : '提示信息',
				content : '请选择excel/word/pdf文件'
			},
			overlay : false,
			autoClose : 1000  	
        });   
    	return false;
    }
	
    sppath = path.split("\\");
    var fileName = sppath[sppath.length-1];	  	  
	if (fileName == null || fileName == '' || fileName == undefined) {
		return false;
	}else{
		$("#upload_file_form").find('input:first').val(fileName);
		autTime();
		$('#show_upload_dialog').show();
	}
	// 先上传后获取上传文件路径
	$("#upload_file_form").ajaxSubmit({
		type : "post",
		url : "/equipment/updateFactoryEquipment.do",
		dataType : "text",
		success : function(str) {
			var result = eval('(' + str + ')');
			if (result.state == 0) {     
				var id = result.data;
				$(obj).parent().next().find('span').text(fileName);
				$(obj).parent().next().find('span').attr("onclick","download_equipment("+id+")");
				$(obj).parent().next().find('span').next().attr("onclick","del_equipment(this,"+id+")");
				$(obj).val('');
			}else if(result.state == 2){
	    		 //如果还未登录，跳转登录页
	    		 window.location = "/zh/login.html";
	        }else {
				easyDialog.open({
					container : {
						header : '  提示信息',
						content : '  上传失败 '
					},
					overlay : false,
					autoClose : 1000
				});
				$(obj).val('');
				$('#show_upload_dialog').hide();
			}
		},
		error : function() {
			easyDialog.open({
				container : {
					header : '  提示信息',
					content : '  上传失败 '
				},
				overlay : false,
				autoClose : 1000
			});
			$(obj).val('');
			$('#show_upload_dialog').hide();
		}
	});
}

//下载设备清单
function download_equipment(id){
   window.location = "/download/equipment-download.do?id="+id;	
}

/**
 * 删除设备清单
 * @param obj
 */
function del_equipment(obj,id){
	 $.post("/equipment/delFactoryEquipment.do",	
			  {
		       "id":id
			  },
			 function(result){
				if(result.state == 0){
					$(obj).prev().text("");
				}else if(result.state == 2){
		    		 //如果还未登录，跳转登录页
		    		 window.location = "/zh/login.html";
		        }     
		   })		 
}
/**
 * 编辑企业编辑
 * @param id
 * @param obj
 */
function updateEquipmentShow(id,obj){
	$("#addeEuipmentShow").css("display","block");
	$(obj).parents('tr').find('td').eq(0).text()
	$("#equipmentName").val($(obj).parents('tr').find('td').eq(0).text());
	$("#equipmentModel").val($(obj).parents('tr').find('td').eq(1).text());
	$("#number").val($(obj).parents('tr').find('td').eq(2).text());
    $("#equipmentType").val($(obj).parents('tr').find('td').eq(3).text());
    $("#equipmentId").val(id);
}
/**
 * 添加资质认证
 */
function addQualification(){
	var qualificationName=$("#selectQualificationName").find("option:selected").val();
	var validityTime=$("#validityTime").val();
	if(validityTime==null || validityTime=="" || validityTime==undefined){
		easyDialog.open({
			container : {
				header : '提示信息',
				content : '有效期不能为空'
			},
			overlay : false,
			autoClose : 1000
		});
		return false;
	}
	          	  
	  var d=new Date(Date.parse(validityTime.replace(/-/g,"/").split(".")[0]));
	  var curDate = new Date();
	//判断日期有效期时间是否大于当前时间
	  if(d < curDate){
		  easyDialog.open({
      		   container : {
          		     header : '提示信息',
          		     content : '有效期必须大于当前时间'
      		   },
				  overlay : false,
				  autoClose : 1000
      		 });   
		  return false;  
	   }
	
	$.ajax({
		url : "/qualificate/addQualification.do",
		type : "post",
		data : {
			qualificationName:qualificationName,
			validityTime:validityTime 
		},
		datatype : "json",
		success : function(result) {
			if (result.state == 0) {
				selectQualification();
				easyDialog.open({
					container : {
						header : '提示信息',
						content : '操作成功'
					},
					overlay : false,
					autoClose : 1000
				});
			}else if(result.state == 2){
	    		 //如果还未登录，跳转登录页
	    		 window.location = "/zh/login.html";
	        }else{
				easyDialog.open({
					container : {
						header : '提示信息',
						content : '操作失败'
					},
					overlay : false,
					autoClose : 1000
				});
			}
		}
	})
	
}

/**
 * 编辑资质认证附件
 */
function modifyQualification(obj){
	
	var dataid = $(obj).attr('dataid');
	var uploadFiles = $(obj)[0].files;
	
	var fileName = '';
	for (var i = 0; i < uploadFiles.length; i++) {
		if (i == uploadFiles.length - 1) {
			fileName += uploadFiles[i].name;
		} else {
			fileName += uploadFiles[i].name + ',';
		}
	}
	if (fileName == null || fileName == '' || fileName == undefined) {
		return false;
	}else{
		$("#modifyQualificationFile").val(fileName);
		autTime();
		$('#show_upload_dialog').show();
	}
	// 先上传后获取上传文件路径
	$("#upload_qualification_file").ajaxSubmit({
		type : "post",
		url : "/upload/uploadQualificationFile.do",
		dataType : "text",
		success : function(str) {
			var result = eval('(' + str + ')');
			if (result.state == 0) {     
				var qualificationFile=result.data.fileName;
				//上传成功更新数据库,在进行查询操作
				$.ajax({
					url : "/qualificate/updateQualification.do",
					type : "post",
					data : {
						qualificationFile:qualificationFile,
						id:dataid 
					},
					datatype : "json",
					success : function(result) {
						if (result.state == 0) {
							selectQualification();
						}else if(result.state == 2){
				    		 //如果还未登录，跳转登录页
				    		 window.location = "/zh/login.html";
				        }else{
							alert("附件更新失败");
						}
					}
				})
			}else if(result.state == 2){
	    		 //如果还未登录，跳转登录页
	    		 window.location = "/zh/login.html";
	        }else {
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
		},
		error : function() {
			easyDialog.open({
				container : {
					header : '  提示信息',
					content : ' 上传失败 '
				},
				overlay : false,
				autoClose : 1000
			});
			$('#show_upload_dialog').hide();
		}
	});
}


/**
 * 添加资质认证附件
 */
function uploadQualification(obj){
	var dataid = $(obj).attr('dataid');
	var uploadFiles = $(obj)[0].files;
	var fileName = '';
	for (var i = 0; i < uploadFiles.length; i++) {
		if (i == uploadFiles.length - 1) {
			fileName += uploadFiles[i].name;
		} else {
			fileName += uploadFiles[i].name + ',';
		}
	}
	if (fileName == null || fileName == '' || fileName == undefined) {
		return false;
	}else{
		$("#qualificationFile").val(fileName);
		autTime();
		$('#show_upload_dialog').show();
	}
	// 先上传后获取上传文件路径
	$("#upload_qualification_file").ajaxSubmit({
		type : "post",
		url : "/upload/uploadQualificationFile.do",
		dataType : "text",
		success : function(str) {
			var result = eval('(' + str + ')');
			var data=result.data;
			if (result.state == 0) {     
				var qualificationFile=data.fileName;
				//上传成功更新数据库,在进行查询操作
				$.ajax({
					url : "/qualificate/updateQualification.do",
					type : "post",
					data : {
						qualificationFile:qualificationFile,
						id:dataid 
					},
					datatype : "json",
					success : function(result) {
						if (result.state == 0) {
							easyDialog.open({
								container : {
									header : '提示信息',
									content : '操作成功'
								},
								overlay : false,
								autoClose : 1000
							});
							selectQualification();
						}else{
							easyDialog.open({
								container : {
									header : '提示信息',
									content : '操作失败'
								},
								overlay : false,
								autoClose : 1000
							});
						}
					}
				})
			}else if(result.state == 2){
	    		 //如果还未登录，跳转登录页
	    		 window.location = "/zh/login.html";
	        }else{
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
		},
		error : function() {
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
 * 删除资质认证信息
 */
function delqualificationFile(obj){
	$.ajax({
		url : "/qualificate/delQualification.do",
		type : "post",
		data : {
			id:obj 
		},
		datatype : "json",
		success : function(result) {
			if (result.state == 0) {
				easyDialog.open({
					container : {
						header : '提示信息',
						content : '操作成功'
					},
					overlay : false,
					autoClose : 1000
				});
				selectQualification();
			}else if(result.state == 2){
	    		 //如果还未登录，跳转登录页
	    		 window.location = "/zh/login.html";
	        }else{
				easyDialog.open({
					container : {
						header : '提示信息',
						content : '操作失败'
					},
					overlay : false,
					autoClose : 1000
				});
			}
		}
	})
}

/**
 * 查询资质认证
 */
function selectQualification(){
	$.ajax({
		url : "/qualificate/selectQualification.do",
		type : "post",
		data : {	
		},
		datatype : "json",
		success : function(result) {
			if (result.state == 0) {
				//处理资质认证
				$("#qualificationList").empty();
				var qualificationList=result.data.qualificationList;
			
				for (var j = 0; j < qualificationList.length; j++) {
					var qualificationHtml='';
					if(qualificationList[j].fileUrl!=null && qualificationList[j].fileUrl!=""){//已经上传过附件
						qualificationHtml+='<tr><td class="td1">'+qualificationList[j].qualificationName+'</td><td class="td2">'+(new Date(qualificationList[j].validityTime.replace(/-/g,"/").split(".")[0])).Format("yyyy-MM-dd")+'</td>'
						qualificationHtml+='<td class="td3 posirela"><em>'+qualificationList[j].fileUrl+'</em><span class="posiabso"></span></td>'
						qualificationHtml+='<td class="tdlast1"><span class="pull-left posirela"><em class="iconfont">&#xe72c;</em>修改<input dataid="'+qualificationList[j].id+'" type="file" id="modifyQualificationPic" onchange="modifyQualification(this)" name="file" class="picfile"/></span>'
						qualificationHtml+='<span class="pull-right" onclick="delqualificationFile('+qualificationList[j].id+')"><em class="iconfont">&#xe616;</em>删除</span></td></tr>'		
						$("#qualificationList").append(qualificationHtml);
					}else{//没上传附件
						qualificationHtml+='<tr><td class="td1">'+qualificationList[j].qualificationName+'</td><td class="td2">'+(new Date(qualificationList[j].validityTime.replace(/-/g,"/").split(".")[0])).Format("yyyy-MM-dd")+'</td>'
						qualificationHtml+='<td class="td3 posirela"><span class="posiabso"></span></td>'
						qualificationHtml+='<td class="tdlast1 "><span class="pull-left posirela"><em class="iconfont">&#xe66e;</em><input dataid="'+qualificationList[j].id+'" id="uploadQualificationFile" onchange="uploadQualification(this)" type="file" name="file" class="imgfile1"/>上传附件</span></td>'
						qualificationHtml+='<span class="pull-right" onclick="delqualificationFile('+qualificationList[j].id+')"><em class="iconfont">&#xe616;</em>删除</span></td></tr>';
						$("#qualificationList").append(qualificationHtml);
					}
				}
			}else if(result.state == 2){
	    		 //如果还未登录，跳转登录页
	    		 window.location = "/zh/login.html";
	        }    
		}
	})
	
}

function doChangeChinese(num){
	if(num==1){
		return "一";
	}else if(num==2){
		return "二";
	}else if(num==3){
		return "三";
	}else if(num==4){
		return "四";
	}else if(num==5){
		return "五";
	}
}
/**
 * 删除营业执照
 * @param obj
 */
function delFactoryLicense(obj){
	var factoryLicense=$(obj).attr('factoryLicense');
	$.ajax({
		url : "/factoryInfo/delFactoryLicense.do",
		type : "post",
		data : {
			factoryLicense:factoryLicense 
		},
		datatype : "json",
		success : function(result) {
			if (result.state == 0) {
				easyDialog.open({
					container : {
						header : '提示信息',
						content : '操作成功'
					},
					overlay : false,
					autoClose : 1000
				});
				selectEditFactoryInfo();
			}else if(result.state == 2){
	    		 //如果还未登录，跳转登录页
	    		 window.location = "/zh/login.html";
	        }else{
				easyDialog.open({
					container : {
						header : '提示信息',
						content : '操作失败'
					},
					overlay : false,
					autoClose : 1000
				});
			}
		}
	})
}
/**
 * 删除工厂厂门照片
 */
function delFactoryGate(obj){
	var factoryGate=$(obj).attr('factoryGate');
	$.ajax({
		url : "/factoryInfo/delFactoryLicense.do",
		type : "post",
		data : {
			factoryGate:factoryGate 
		},
		datatype : "json",
		success : function(result) {
			if (result.state == 0) {
				easyDialog.open({
					container : {
						header : '提示信息',
						content : '操作成功'
					},
					overlay : false,
					autoClose : 1000
				});
				selectEditFactoryInfo();
			}else if(result.state == 2){
	    		 //如果还未登录，跳转登录页
	    		 window.location = "/zh/login.html";
	        }else{
				easyDialog.open({
					container : {
						header : '提示信息',
						content : '操作失败'
					},
					overlay : false,
					autoClose : 1000
				});
			}
		}
	})
}


/**
 * 删除生产设备照片
 */
function delProductionEnvironment(obj){
	var productionEnvironment=$(obj).attr('productionEnvironment');
	$.ajax({
		url : "/factoryInfo/delFactoryLicense.do",
		type : "post",
		data : {
			productionEnvironment:productionEnvironment 
		},
		datatype : "json",
		success : function(result) {
			if (result.state == 0) {
				easyDialog.open({
					container : {
						header : '提示信息',
						content : '操作成功'
					},
					overlay : false,
					autoClose : 1000
				});
				selectProductionEnvironmentAndVideo()
			}else if(result.state == 2){
	    		 //如果还未登录，跳转登录页
	    		 window.location = "/zh/login.html";
	        }else{
				easyDialog.open({
					container : {
						header : '提示信息',
						content : '操作失败'
					},
					overlay : false,
					autoClose : 1000
				});
			}
		}
	})
}
/**
 * 上传企业Logo
 */
function updateFactoryLogo(obj){
	var upload_factory_file = $(obj).parents('form');
	upload_factory_file.find('input[name="factoryLogoFileName"]').val('')
	upload_factory_file.find('input[name="factoryLicenseFileName"]').val('')
	upload_factory_file.find('input[name="factoryGateFileName"]').val('')
	var uploadFiles = $(obj)[0].files;
	var fileName = '';
	for (var i = 0; i < uploadFiles.length; i++) {
		if (i == uploadFiles.length - 1) {
			fileName += uploadFiles[i].name;
		} else {
			fileName += uploadFiles[i].name + ',';
		}
	}
	if (fileName == null || fileName == '' || fileName == undefined) {
		return false;
	}else{
		autTime();
		$('#show_upload_dialog').show();
	}
    // 先上传后获取上传文件路径
	upload_factory_file.find('input[name="factoryLogoFileName"]').val(fileName);
	$("#upload_factory_file").ajaxSubmit({
		type : "post",
		url : "/upload/updateFactoryLogo.do",
		dataType : "text",
		success : function(str) {
			var result = eval('(' + str + ')');
			var data=result.data;
			if (result.state == 0) {
                $("#factoryLogoFileS").val(data);
                $("#factoryLicenseFileS").val('');
                $("#factoryGateFileS").val('');
                editFactoryInfo();
			}else if(result.state == 2){
	    		 //如果还未登录，跳转登录页
	    		 window.location = "/zh/login.html";
	        }else {
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
		},
		error : function() {
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

//上传多个文件时后返回产品路径，以逗号隔开(上传营业执照)
function updateFactoryLicense(obj) {
	var upload_factory_file = $(obj).parents('form');
	upload_factory_file.find('input[name="factoryLogoFileName"]').val('')
	upload_factory_file.find('input[name="factoryLicenseFileName"]').val('')
	upload_factory_file.find('input[name="factoryGateFileName"]').val('')
	
	var uploadFiles = $(obj)[0].files;
	var fileName = '';
	for (var i = 0; i < uploadFiles.length; i++) {
		if (i == uploadFiles.length - 1) {
			fileName += uploadFiles[i].name;
		} else {
			fileName += uploadFiles[i].name + ',';
		}
	}
	if (fileName == null || fileName == '' || fileName == undefined) {
		return false;
	}else{
		autTime();
		$('#show_upload_dialog').show();
	}
	upload_factory_file.find('input[name="factoryLicenseFileName"]').val(fileName);
	// 先上传后获取上传文件路径
	$("#upload_factory_file").ajaxSubmit({
		type : "post",
		url : "/upload/uploadFactoryLicense.do",
		dataType : "text",
		success : function(str) {
			var result = eval('(' + str + ')');
			var data=result.data;
			if (result.state == 0) {
                $("#factoryLicenseFileS").val(data);
                $("#factoryLogoFileS").val('');
                $("#factoryGateFileS").val('');
                editFactoryInfo();
			}else if(result.state == 2){
	    		 //如果还未登录，跳转登录页
	    		 window.location = "/zh/login.html";
	        }else {
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
		},
		error : function() {
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


//上传多个文件时后返回产品路径，以逗号隔开(上传工厂厂门照片)
function uploadFactoryGate(obj) {
	var upload_factory_file = $(obj).parents('form');
	upload_factory_file.find('input[name="factoryLogoFileName"]').val('')
	upload_factory_file.find('input[name="factoryLicenseFileName"]').val('')
	upload_factory_file.find('input[name="factoryGateFileName"]').val('')
	
	var uploadFiles = $(obj)[0].files;
	var fileName = '';
	for (var i = 0; i < uploadFiles.length; i++) {
		if (i == uploadFiles.length - 1) {
			fileName += uploadFiles[i].name;
		} else {
			fileName += uploadFiles[i].name + ',';
		}
	}
	if (fileName == null || fileName == '' || fileName == undefined) {
		return false;
	}else{
		autTime();
		$('#show_upload_dialog').show();
	}
	upload_factory_file.find('input[name="factoryGateFileName"]').val(fileName);
	$("#upload_factory_file").ajaxSubmit({
		type : "post",
		url : "/upload/uploadFactoryGateFile.do",
		dataType : "text",
		success : function(str) {
			var result = eval('(' + str + ')');
			var data=result.data;
			if (result.state == 0) {     
				$("#factoryGateFileS").val(data);
				$("#factoryLogoFileS").val('');
	            $("#factoryLicenseFileS").val('');
				editFactoryInfo();
			}else if(result.state == 2){
	    		 //如果还未登录，跳转登录页
	    		 window.location = "/zh/login.html";
	        }else {
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
		},
		error : function() {
			easyDialog.open({
				container : {
					header : '  提示信息',
					content : ' 上传失败 '
				},
				overlay : false,
				autoClose : 1000
			});
			$('#show_upload_dialog').hide();
		}
	});
}



/*
 * 检测文件大小
 */
function getFilesize(file) {

	fileSize = file.files[0].size / 1024;
	if (fileSize > 10240) {
		return false;
	} else {
		return true;
	}

}



/**
 * 上传工厂生产环境照片
 * @param obj
 */
function uploadProductionEnvironment(obj){
	var uploadFiles = $("#uploadProductionEnvironmentFile")[0].files;
	var fileName = '';
	for (var i = 0; i < uploadFiles.length; i++) {
		if (i == uploadFiles.length - 1) {
			fileName += uploadFiles[i].name;
		} else {
			fileName += uploadFiles[i].name + ',';
		}
	}
	if (fileName == null || fileName == '' || fileName == undefined) {
		return false;
	}else{
		autTime();
		$('#show_upload_dialog').show();
	}
	// 先上传后获取上传文件路径
	$("#upload_file_form").ajaxSubmit({
		type : "post",
		url : "/upload/uploadProductionEnvironmentFile.do",
		dataType : "text",
		success : function(str) {
			var result = eval('(' + str + ')');
			var data=result.data;
			if (result.state == 0) {     
				    var productionEnvironment=data.fileNames;
	                var productionEnvironmentS="";
	            	$("#productionEnvironmentS").val(productionEnvironment);
	            	$(obj).val('');
	                updateProductionEnvironment();
			}else if(result.state == 2){
	    		 //如果还未登录，跳转登录页
	    		 window.location = "/zh/login.html";
	        }else {
				easyDialog.open({
					container : {
						header : '  提示信息',
						content : ' 上传失败'
					},
					overlay : false,
					autoClose : 1000
				});
				$(obj).val('');
				$('#show_upload_dialog').hide();
			}
		},
		error : function() {
			easyDialog.open({
				container : {
					header : '  提示信息',
					content : ' 上传失败 '
				},
				overlay : false,
				autoClose : 1000
			});
			$(obj).val('');
			$('#show_upload_dialog').hide();
		}
	});
}
/**
 * 更新生产视频
 */
function uploadProductionVideo(obj){
	var uploadFiles = $("#uploadProduction")[0].files;
	var fileName = '';
	for (var i = 0; i < uploadFiles.length; i++) {
		if (i == uploadFiles.length - 1) {
			fileName += uploadFiles[i].name;
		} else {
			fileName += uploadFiles[i].name + ',';
		}
	}
	
	var gen = fileName.lastIndexOf("."); 
    var type = fileName.substr(gen); 
    if(!(type.toLowerCase()  == ".mp4" || type.toLowerCase()==".ogg")){
    	easyDialog.open({
			container : {
				header : '提示信息',
				content : '请选择.mp4格式文件'
			},
			overlay : false,
			autoClose : 1000  	
        });   
    	return false;
    }
    
    fileSize = uploadFiles.size / 1024;
	if (fileSize > 10240) {
		easyDialog.open({
			container : {
				header : '提示信息',
				content : '请上传小于10M视频'
			},
			overlay : false,
			autoClose : 1000  	
        });   
    	return false;
	}
    
	
	if (fileName == null || fileName == '' || fileName == undefined) {
		return false;
	}else{
		$("#productionVideo").val(fileName);
		autTime();
		$('#show_upload_dialog').show();
	}
	// 先上传后获取上传文件路径
	$("#upload_file_form").ajaxSubmit({
		type : "post",
		url : "/upload/uploadProductionVideo.do",
		dataType : "text",
		success : function(str) {
			var result = eval('(' + str + ')');
			var data=result.data;
			if (result.state == 0) {     
				var productionVideo=data.fileName;
				$("#productionVideoUrl").text(productionVideo);	
				$("#productionVideoS").val(productionVideo);
				$(obj).val('');
				updateProductionEnvironment();
			}else if(result.state == 2){
	    		 //如果还未登录，跳转登录页
	    		 window.location = "/zh/login.html";
	        }else {
				easyDialog.open({
					container : {
						header : '  提示信息',
						content : ' 上传失败 '
					},
					overlay : false,
					autoClose : 1000
				});
				$(obj).val('');
				$('#show_upload_dialog').hide();
			}
		},
		error : function() {
			easyDialog.open({
				container : {
					header : '  提示信息',
					content : ' 上传失败 '
				},
				overlay : false,
				autoClose : 1000
			});
			$(obj).val('');
			$('#show_upload_dialog').hide();
		}
	});	
}
/**
 * 删除生产视频
 */
function delProductionVideo(){
	$.ajax({
		url : "/factoryInfo/delProductionVideo.do",
		type : "post",
		data : {
			
		},
		datatype : "json",
		success : function(result) {
			if (result.state == 0) {
				easyDialog.open({
					container : {
						header : '提示信息',
						content : '操作成功'
					},
					overlay : false,
					autoClose : 1000
				});
				$('#productionVideoUrl').text('');
				$('#videoBoxUrl').val('');
				$('#uploadProduction').val('');
				$('#productionVideoDiv').hide();
			}else if(result.state == 2){
	    		 //如果还未登录，跳转登录页
	    		 window.location = "/zh/login.html";
	        }else{
				easyDialog.open({
					container : {
						header : '提示信息',
						content : '操作失败'
					},
					overlay : false,
					autoClose : 1000
				});
			}
		}
	})
}

/***
 * 更新生产环境照片和视频
 */
function updateProductionEnvironment(){
//	var productionEnvironmentS=$("#productionEnvironmentS").val();
	var productionVideoS=$("#productionVideoS").val();
	$.ajax({
		url : "/factoryInfo/updateProductionEnvironmentAndVideo.do",
		type : "post",
		data : {
//			productionEnvironmentS:productionEnvironmentS,
			productionVideoS:productionVideoS 
		},
		datatype : "json",
		success : function(result) {
			if (result.state == 0) {
				easyDialog.open({
					container : {
						header : '提示信息',
						content : '操作成功'
					},
					overlay : false,
					autoClose : 1000
				});
				selectProductionEnvironmentAndVideo();
			}else if(result.state == 2){
	    		 //如果还未登录，跳转登录页
	    		 window.location = "/zh/login.html";
	        }else{
				easyDialog.open({
					container : {
						header : '提示信息',
						content : '操作失败'
					},
					overlay : false,
					autoClose : 1000
				});
			}
		}
	})
}
/**
 * 查询更新之后的视频和图片
 */
function selectProductionEnvironmentAndVideo(){
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
				var productionEnvironment=data.productionEnvironment;
				var productionVideo=data.productionVideo;
                var productionEnvironmentS="";
                $("#productionEnvironment").empty();
                if(productionEnvironment!=null &&productionEnvironment!=''){
                	productionEnvironmentS=productionEnvironment.split(',');
                }
                for(var i = 0;i<productionEnvironmentS.length;i++){
				    if(productionEnvironmentS[i]==''||productionEnvironmentS[i]==null||typeof(productionEnvironmentS[i])==undefined){
				    	productionEnvironmentS.splice(i,1);
				        i=i-1;
				    }
				}
                if(productionEnvironmentS.length>0){//有生产环境照片
			    	for(var i=0;i<productionEnvironmentS.length;i++){	
						var productionEnvironmentHtml='';
				    	if(productionEnvironmentS.length<6){//生产环境照片小于6张
				    	  productionEnvironmentHtml='<div class="col-xs-4 padlr w200 m20 t20"><a href="###" class="aimg">'
				    	  productionEnvironmentHtml+='<img src="/static_img/production_environment/'+data.factoryId+'/'+productionEnvironmentS[i]+'" alt="" class="img-responsive">'
				    	  productionEnvironmentHtml+='<div class="sy" productionEnvironment="'+productionEnvironmentS[i]+'" onclick="delProductionEnvironment(this)"><span class="iconfont">&#xe616;</span></div> </a></div>'
				    	  /*$("#productionEnvironment").append(productionEnvironmentHtml); */
				    	  if(i==productionEnvironmentS.length-1){//当遍历出最后一个时
				    		  productionEnvironmentHtml+='<div class="col-xs-4 padlr w200 m20 t20"><a href="###" class="aimg aimg2 text-center">'
							  productionEnvironmentHtml+='<img src="../images/products/add.png" alt="" class="img-responsive" onclick="uploadProductionEnvironmentFile()"><br/><span>上传图片</span></a><input id="uploadProductionEnvironmentFile" onchange="uploadProductionEnvironment(this)"type="file" name="file" class="imgfile" multiple="multiple"/><div class="gs posiabso gs2">（<span>'+productionEnvironmentS.length+'</span>/6）</div></div>'
//							  productionEnvironmentHtml+='<div class="gs posiabso gs2">（<span>'+productionEnvironmentS.length+'</span>/6）</div>'
				    	  }
				    	  $("#productionEnvironment").append(productionEnvironmentHtml);  
				    	}else{//生产环境照片为6张
				    		productionEnvironmentHtml='<div class="col-xs-4 padlr w200 m20 t20"><a href="###" class="aimg"><img src="/static_img/production_environment/'+data.factoryId+'/'+productionEnvironmentS[i]+'" alt="" class="img-responsive">'
				    		productionEnvironmentHtml+='<div class="sy" productionEnvironment="'+productionEnvironmentS[i]+'" onclick="delProductionEnvironment(this)"><span class="iconfont">&#xe616;</span></div></a></div>'
				    		$("#productionEnvironment").append(productionEnvironmentHtml);
				    	}
			    	}
			    }else{//无生产环境照片
			    	var productionEnvironmentHtml='';
			    	productionEnvironmentHtml+='<div class="col-xs-4 padlr w200 m20 t20"><a href="###" class="aimg aimg2 text-center">'
			    	productionEnvironmentHtml+='<img src="../images/products/add.png" alt="" class="img-responsive" onclick="uploadProductionEnvironmentFile()"><br/><span>上传图片</span></a><input id="uploadProductionEnvironmentFile" onchange="uploadProductionEnvironment(this)"type="file" name="file" class="imgfile" multiple="multiple"/><div class="gs posiabso gs2">（<span>0</span>/6）</div></div>'
//			    	productionEnvironmentHtml+='<div class="gs posiabso gs2">（<span>0</span>/6）</div>'
			    	$("#productionEnvironment").append(productionEnvironmentHtml);
			    }
                if(data.productionVideo!=null && data.productionVideo!=''){
               	    $("#productionVideoUrl").text(data.productionVideo);
                    $("#videoBoxUrl").val("/static_img/production_video/"+data.factoryId+"/"+data.productionVideo);
//                    $("#video_box").attr("src","/static_img/production_video/"+data.factoryId+"/"+data.productionVideo);
                    $('#productionVideoDiv').show();
                    $('#delProductionVideoHtml').show();
                }else{
                 	$("#delProductionVideoHtml").hide();
               	    $("#productionVideoDiv").hide();
                }
     /*           $("#productionVideoUrl").text(data.productionVideo);
                $("#videoBoxUrl").val("/static_img/production_video/"+data.factoryId+"/"+data.productionVideo);*/
			}else if(result.state == 2){
	    		 //如果还未登录，跳转登录页
	    		 window.location = "/zh/login.html";
	        }     
		}
	})
}



//注意：火狐使用event时， 
function inputNum(evt){ 
  //火狐使用evt.which获取键盘按键值，IE使用window.event.keyCode获取键盘按键值
  var ev = evt.which?evt.which:window.event.keyCode;
  if(ev>=48&&ev<=57){
    return true;  
  }else{
    return false;
  } 
}  


