// 云盘展示页面--js
var FileList = function(){
	// 公共变量
	
	// 私有函数
	
	// 监听事件
	// 文件展示方式
    $("#file-style button").bind("click", function(){
        $(this).addClass("active").siblings().removeClass("active");
    });

	// 点击文件列表后选中和取消
    $("#show-file tr").bind("click", function(){
        if($(this).find(' td:first span').hasClass('glyphicon-check')){
            $(this).find(' td:first').html('<span class="glyphicon glyphicon-unchecked"  style="cursor:pointer"></span>');
        }else if(!$(this).find(' td:first span').is('glyphicon-check')){
            $(this).find(' td:first').html('<span class="glyphicon glyphicon-check"  style="cursor:pointer"></span>');
        }
    });

	// 右键菜单
    $('#show-file tr').contextmenu({
        target:'#context-menu',
        before: function(e,context) {
            // execute code before context menu if shown
            $('#file-menu-more').show();	// 显示对应可操作菜单
            $(context).find('td:first').html('<span class="glyphicon glyphicon-check"  style="cursor:pointer"></span>');
        },
        onItem: function(context,e) {
            // execute on menu item selection
        }
    })

	// 显示菜单时
    // $('#show-file tr').on('show.bs.context',function () {
     //    // do something...
    // });

	// 动态修改菜单
    // $('#main').contextmenu({
     //    target: "#myMenu",
     //    before: function(e) {
     //        this.getMenu().find("li").eq(2).find('a').html("This was dynamically changed");
     //    }
    // });
	// 对外暴漏的函数
	return {
		init: function(){
//			IndexInit.showMessage("您好，欢迎使用本系统!");
//			IndexInit.showMessage("您好，我是您的小密，有事请找我!");
//			console.log(jQuery('#show-file').html());
//			jQuery('#show-file').bind('click', function(){
//				console.log('here');
//			});
			Message.init();
		},
		getFile: function(url, p_id) {
            $.post(url, {}, function () {

            });
        }
	}
}();