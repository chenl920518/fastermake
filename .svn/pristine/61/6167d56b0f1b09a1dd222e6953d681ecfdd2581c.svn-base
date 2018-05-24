/* 选择国家对应国旗 */
	var national_flag = {
		"USA":"/images/flag/1us_flag[1].gif",
		"Canada":"/images/flag/2ca_flag[1].gif",
		"France":"/images/flag/3fr_flag[1].gif",
		"Germany":"/images/flag/4de_flag[1].gif",
		"Netherlands":"/images/flag/5nl_flag[1].gif",
		"Israel":"/images/flag/6il_flag[1].gif",
		"Mexico":"/images/flag/7mx_flag[1].gif",
		"Australia":"/images/flag/8au_flag[1].gif",
		"Italy":"/images/flag/9it_flag[1].gif",
		"Switzerland":"/images/flag/10ch_flag[1].gif",
		"Finland":"/images/flag/11fi_flag[1].gif",
		"Sweden":"/images/flag/12se_flag[1].gif",
		"UK":"/images/flag/13uk_flag[1].gif",
		"Argentina":"/images/flag/14ar_flag[1].gif",
		"Other":"/images/flag/1us_flag[1].gif"
	}
	
	//如果国家显示为Other或者空值，则统一USA显示
	function getFlag(country){
		var flag_src = national_flag[country];
		if(flag_src == null || flag_src == ''){
			flag_src = national_flag["Other"];
		}
		return flag_src;
	}