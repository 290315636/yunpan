// 云盘展示页面--js
var FileList = function(){
	// 公共变量
	
	// 私有函数
	
	// 监听事件
	// 文件展示方式
    $("#file-style button").bind("click", function(){
        $(this).addClass("active").siblings().removeClass("active");
    });

    // 左侧导航菜单折叠和展开
    $("#file-left ul li:first").bind("click", function(){
        if($(this).find('span:last').hasClass('glyphicon glyphicon-indent-right')){
            $(this).find('span:first').addClass('hidden');
            $(this).find('span:last').removeClass('pull-right').removeClass('glyphicon glyphicon-indent-right').addClass('glyphicon glyphicon-indent-left');

            // 只显示图标
            $("#file-left ul li:not(:first)").find("span:not(:first)").addClass('hidden');

            $('#file-left').removeClass('col-md-2').addClass('col-md-1').css('width', '6.333333%');
            $('#file-yunpan').removeClass('col-md-10').addClass('col-md-11').css('width', '93.66666667%');;

            $('#file-expand').addClass('hidden');
        }else if(!$(this).find('span:last').is('glyphicon glyphicon-indent-right')){
            $(this).find('span:first').removeClass('hidden');
            $(this).find('span:last').addClass('pull-right').removeClass('glyphicon glyphicon-indent-left').addClass('glyphicon glyphicon-indent-right');

            // 显示图标和菜单
            $("#file-left ul li:not(:first)").find("span:not(:first)").removeClass('hidden');

            $('#file-left').removeClass('col-md-1').addClass('col-md-2').css('width', '16.66666667%');
            $('#file-yunpan').removeClass('col-md-11').addClass('col-md-10').css('width', '83.33333333%');

            $('#file-expand').removeClass('hidden');
        }
    });
    $("#file-left ul li:not(:first)").bind("click", function(){
        $(this).addClass("active").siblings().removeClass("active");
    });

	// 点击文件列表后选中和取消
    $("#show-file tr").bind("click", function(){
        $('#file-menu-more').show();	// 显示对应可操作菜单
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
    });

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
    // 上传文件
    $('#file-upload').click(function(){
    	FileInput.resetFileMd5();
    });
    
	// 对外暴漏的函数
	return {
		init: function(){
//			IndexInit.showMessage("您好，欢迎使用本系统!");
//			IndexInit.showMessage("您好，我是您的小密，有事请找我!");
//			console.log(jQuery('#show-file').html());
//			jQuery('#show-file').bind('click', function(){
//				console.log('here');
//			});
//			Message.init();
			// 参数1:控件id、参数2:上传地址
	        FileInput.init("fileUpload", "/file/upload"); 
		},
		getLeftFolder: function(url) { // 查询顶层文件夹
            $.post(url, {}, function (data) {
            	console.log(data);
            });
        },
        uploadFile: function(){
        	// <input type="file" name="fileUpload" id="fileUpload" multiple />
        	
        }
	}
}();