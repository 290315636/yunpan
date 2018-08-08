// 闭包使用方法
var IndexInit = function (){
	function hasScrollbar() {
	    return document.body.scrollHeight > (window.innerHeight || document.documentElement.clientHeight);
	}
	// 内部变量或方法
	// 初始化监听事件
	jQuery(function($) {
		$('body').bind('mousewheel', function(event, delta) {
			var dir = delta > 0 ? 'Up' : 'Down';
			var needHandle = false;
			// 有滚动条才用处理
			if(hasScrollbar()){
				needHandle = true;
			}
			if(needHandle){
				if (dir == 'Up') {
					// 显示页头，隐藏页尾
					$('#menu-nav').fadeIn();
					$('#footer-nav').fadeOut();
				} else {
					// 隐藏页头，显示页尾
					$('#menu-nav').fadeOut();
					$('#footer-nav').fadeIn();
					
				}
			}
			return true;
		});
		// 初始化
		IndexInit.init();
	});
	
	//问候语 
	var welcomeStrMap = {};
	
	//解析时间判断早上、中午、晚上
	function parseDayTime(){
		var now = new Date(),
			curHour = now.getHours(),
			timeStart, timeEnd, strRow;

		for(var i = 0, l = welcomeStrMap.length; i < l; i++){
			strRow = welcomeStrMap[i];
			timeStart = parseInt(strRow.key.substring(0, 2));
			timeEnd = parseInt(strRow.key.substring(2));

			//时间段如23：00 - 02：00
			if(timeStart > timeEnd){
				if(curHour >= timeStart || curHour < timeEnd){
					return strRow;
				}
			}else{
				if(timeStart <= curHour && curHour < timeEnd){
					return strRow;
				}
			}
		}
	}

	return {
		init: function(){ // 对外暴漏的方法
			IndexInit.initWelcome();
			IndexInit.datePrototype();
			IndexInit.juicerInit();
		},
		//顶部问候语设置
		initWelcome: function(){
			function doCheckTime(){
				var timeObj = parseDayTime(),  // 取得对应时间段的，问候语数组
					randomNum;
				if($.data($('#g_welcome')[0], 'time-period') == timeObj.key) return;
				
				if(timeObj){
					randomNum = Math.floor(Math.random()*(timeObj.value.length));

					$('#g_welcome').text(timeObj.value[randomNum]);
					$.data($('#g_welcome')[0], 'time-period', timeObj.key);
				}
			}
			welcomeStrMap = [{"key":"0206","value":[" 夜深了，天上的星星都已经睡了。"," 夜都睡了，你还不睡吗？","晚睡伤身体，客官早点歇息吧: )"]},{"key":"0911","value":["上午好，生活是一面镜子，你笑它也笑。","记得每天要喝八杯水哦！"," 勤劳的小蜜蜂要开始忙碌的一天啦。"]},{"key":"1113","value":["工作再忙，午餐必不可少哦。","午后的小憩，换来百倍的抖擞","午饭要好好吃哇，幸福是吃到饱。"]},{"key":"1317","value":["抬眼硬拗三分困，不及午后一盏茶。","忙碌的你，有着最迷人的剪影，下午好！","抖擞精神，开始下半场的工作。"]},{"key":"1719","value":["华灯初上，谁在等你，你又在等谁？","万家灯火中，总有一盏为你点亮。"]},{"key":"1923","value":["晚上好，要对自己的胃好一些哦。","忙碌了一天，别忘了陪陪家人。","还在忙吗，别忘了给自己一个微笑。"]},{"key":"0609","value":[" 一日之计在于晨，早安！","请在清早给自己一个微笑^__^","每天都是新的自己，早上好！"]},{"key":"2302","value":["深夜了，不知道有多少人和你一样还没有睡呢？","不要活得太累，不要忙得太疲惫。","愿与您分享这夜色，早点休息:)"]}];
			doCheckTime();
			//一分钟检测一次, 改变问候语
			setInterval(function(){
				doCheckTime();
			}, 60*1000);
		},
		datePrototype: function(){
			Date.prototype.Format = function (fmt) {  
			    var o = {  
			        "M+": this.getMonth() + 1, //月份   
			        "d+": this.getDate(), //日   
			        "H+": this.getHours(), //小时   
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
		},
		change2Mb: function(size) {
			if(!size || size == '0' || size == 0){
				return '-';
			}else if(size.indexOf('Mb')!=-1){
				return size;
			}else{
				return (parseInt(size) / 1024 /1024).toFixed(2) + 'Mb';
			}
		},
		substringBefore: function(str, delimiter){
			if(!delimiter)delimiter = '.';
			if(str.indexOf(delimiter) < 0){
				return str;
			}
			return str.substring(0, str.indexOf(delimiter));
		},
		juicerInit: function(){
			// 重置边界--避免和其他模板冲突
			juicer.set({
			    'tag::operationOpen': '{@',			// 默认{@
			    'tag::operationClose': '}',			// 默认}
			    'tag::interpolateOpen': '${{',		// 默认${
			    'tag::interpolateClose': '}',		// 默认}
			    'tag::noneencodeOpen': '$${',		// 默认$${
			    'tag::noneencodeClose': '}',		// 默认}
			    'tag::commentOpen': '{{#',			// 默认{#
			    'tag::commentClose': '}'			// 默认}
			});
			
			var links = function(data) {
				return '<a href="' + data.href + '" alt="' + data.alt + '" />';
			};
			
			juicer.register('change2Mb', IndexInit.change2Mb); // 注册自定义函数--单位换算（字节转化为Mb）
			juicer.register('substringBefore', IndexInit.substringBefore); // 注册自定义函数--字符串截取
		}
	};
}();