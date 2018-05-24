/* 选择国家对应国旗 */
	var national_flag = {
		"USA":"/m-zh/image/flag/1us_flag[1].gif",
		"Canada":"/m-zh/image/flag/2ca_flag[1].gif",
		"France":"/m-zh/image/flag/3fr_flag[1].gif",
		"Germany":"/m-zh/image/flag/4de_flag[1].gif",
		"Netherlands":"/m-zh/image/flag/5nl_flag[1].gif",
		"Israel":"/m-zh/image/flag/6il_flag[1].gif",
		"Mexico":"/m-zh/image/flag/7mx_flag[1].gif",
		"Australia":"/m-zh/image/flag/8au_flag[1].gif",
		"Italy":"/m-zh/image/flag/9it_flag[1].gif",
		"Switzerland":"/m-zh/image/flag/10ch_flag[1].gif",
		"Finland":"/m-zh/image/flag/11fi_flag[1].gif",
		"Sweden":"/m-zh/image/flag/12se_flag[1].gif",
		"UK":"/m-zh/image/flag/13uk_flag[1].gif",
		"Argentina":"/m-zh/image/flag/14ar_flag[1].gif",
		"Other":"/m-zh/image/flag/1us_flag[1].gif"
	}
	function getFlag(country){
		var flag_src = national_flag[country];
		if(flag_src == null || flag_src == ''){
			flag_src = national_flag["Other"];
		}
		return flag_src;
	}