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


//function dateFormat(dateString,format) {
//    if(!dateString)return "";
//    var time = new Date(dateString.replace(/-/g,'/').replace(/T|Z/g,' ').trim());
//    var o = {
//        "M+": time.getMonth() + 1, //月份
//        "d+": time.getDate(), //日
//        "h+": time.getHours(), //小时
//        "m+": time.getMinutes(), //分
//        "s+": time.getSeconds(), //秒
//        "q+": Math.floor((time.getMonth() + 3) / 3), //季度
//        "S": time.getMilliseconds() //毫秒
//    };
//    if (/(y+)/.test(format)) format = format.replace(RegExp.$1, (time.getFullYear() + "").substr(4 - RegExp.$1.length));
//    for (var k in o)
//        if (new RegExp("(" + k + ")").test(format)) format = format.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
//    return format;
//}





function DateDiff(date1,date2){
	var d1 = new Date(date1);
	var d2 = new Date(date2);	
	var hour = Number(parseInt(d2 - d1) / 1000 / 60 / 60).toFixed(0);
	return hour;
}