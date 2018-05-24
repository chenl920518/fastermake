/* 选择的不同域名 */
//	var server = {
//		"en":"https://www.quickpart.cn",
//		"zh":"https://www.kuaizhizao.cn"
//	}
	var server = {
			"en":"http://192.168.1.88:8080",
			"zh":"http://192.168.1.88:8080"
		}
	//根据国家语言选择域名
	function getServerName(lan){
		var server_name = server[lan];
		if(!lan){
			server_name = "https://www.kuaizhizao.cn";
		}
		return server_name;
	}