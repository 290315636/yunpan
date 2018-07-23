// 云盘展示页面--js
var FileList = function(){
	// 公共变量
	
	// 私有函数
	
	// 监听事件
	$("#show-file tr td:first").bind("click", function(){
	    console.log("ss");
	});
	// 阻止浏览器默认右键点击事件 
	$("#show-file").bind("contextmenu", function(){
	    return false;
	});
	$("#show-file tr").mousedown(function(e) {
        //右键为3,左键为1
		$('#file-menu-more').show();	// 显示对应可操作菜单
        if (3 == e.which) { 			
        	console.log($(this).html());
        	$(this).find('td:first').html('<span class="glyphicon glyphicon-check"></span>');
        	// 显示下拉菜单
        } else if (1 == e.which) {		// 显示预览（如果支持）
            //左键为1
        	if($(this).find('td:first > span').hasClass('glyphicon-check')){
        		console.log('左键1');
        		$(this).find('td:first').html('<span class="glyphicon glyphicon-unchecked"></span>');
        	}else if(!$(this).find('td:first > span').is('glyphicon-check')){
        		console.log('左键2');
        		$(this).find('td:first').html('<span class="glyphicon glyphicon-check"></span>');
        	}
        }
    });
//	jQuery('#show-file').bind('click', function(){
//		console.log('here');
//	});
	// 对外暴漏的函数
	return {
		init: function(){
			IndexInit.showMessage("您好，欢迎使用本系统!");
			IndexInit.showMessage("您好，我是您的小密，有事请找我!");
//			console.log(jQuery('#show-file').html());
//			jQuery('#show-file').bind('click', function(){
//				console.log('here');
//			});
		}
	}
}();