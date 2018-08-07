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
    $("#show-file").on("click", "tr", function(){
    	if($(this).index()==0 && $(this).find('input').is('input')){
    		// 新建文件夹生成的一条记录-->监听enter事件
    		$(this).find('input').keyup(function(event){
			  if(event.keyCode ==13){
				  $(this).next().children('button:last').click();
			  }
			});
    	}else{
    		$('#file-menu-more').show();	// 显示对应可操作菜单
            if($(this).find(' td:first span').hasClass('glyphicon-check')){
                $(this).find(' td:first span').replaceWith('<span class="glyphicon glyphicon-unchecked" style="cursor:pointer"></span>');
            }else if(!$(this).find(' td:first span').is('glyphicon-check')){
                $(this).find(' td:first span').replaceWith('<span class="glyphicon glyphicon-check" style="cursor:pointer"></span>');
            }
    	}
    });

	// 右键菜单
    // 动态修改菜单
    $('#show-file tr').contextmenu({
        target:'#context-menu',
        before: function(e,context) {
            // execute code before context menu if shown
            $('#file-menu-more').show();	// 显示对应可操作菜单
            $(context).find('td:first span').replaceWith('<span class="glyphicon glyphicon-check" style="cursor:pointer"></span>');
            // 每个选项加上隐藏属性
            this.getMenu().find('ul > li').data('id', $(context).find('td:first').data('id'));
            this.getMenu().find('ul > li').data('record-index', $(context).index());
            
//	       	console.log($(context).find('td:first').data('id'));
//	       	console.log(this.getMenu().find('ul > li').eq(1).find('a').attr('href'));
	       	this.getMenu().find('ul > li').eq(0).find('a').prop('href', '/file/download?id='+$(context).find('td:first').data('id'));
//	       	this.getMenu().find('ul > li').eq(0).find('a').prop('href', '../file-tree/downloads?fileId='+$(context).find('td:first').data('id'));
        },
        onItem: function(context,e) {
            // execute on menu item selection
        }
    });

    // 点击路径导航事件
    $('.breadcrumb > li').bind("click", function(){
    	$(this).addClass("active").siblings().removeClass("active");
    	$(this).html($(this).find('a').html());
    	$(this).nextAll().remove();
    });
    
    // 点击新建文件夹
    $('.glyphicon-plus').parent().bind("click", function(){
    	var nowTime=new Date().Format("yyyy-MM-dd HH:mm:ss");
    	var str = '<tr>                                                                         '+
    	'  <td class="col-md-1" data-id="">                                           '+
    	'	<span class="glyphicon glyphicon-unchecked" style="cursor:pointer"></span>'+
    	'	<div class="fileicon-position fileicon-small-foler"></div>                '+
    	'  </td>                                                                      '+
    	'  <td>                                                                       '+
    	'	<div class="input-group input-group-sm">                                              '+
    	'	    <input type="text" class="form-control" placeholder="新建文件夹" aria-label="...">             '+
    	'		<div class="input-group-btn">                                         '+
    	'		  <button onclick="FileList.folderCancel()" class="btn btn-default glyphicon glyphicon-remove" type="button"></button>          '+
    	'		  <button onclick="FileList.folderConfirm(this)" class="btn btn-default glyphicon glyphicon-ok" type="button"></button>          '+
    	'		</div>                                                                '+
    	'	</div>                                                                    '+
    	'  </td>                                                                      '+
    	'  <td>0</td>                                                                 '+
    	'  <td>'+nowTime+'</td>                                                                  '+
    	'</tr>                                                                        ';
//    	console.log(str);
    	
    	$('#show-file tbody').prepend(str);
    });
    
    
	// 显示菜单时
    // $('#show-file tr').on('show.bs.context',function () {
     //    // do something...
    // });

    // 上传文件
//    $('#file-upload').click(function(){
//    	FileInput.resetFileMd5();
//    });
    
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
        	
        },
        folderCancel: function(){
        	$('#show-file tr:first').remove();
        },
        folderConfirm: function(_this, url){// 添加文件夹到数据库
        	if(!url)url = "/file-tree/addFolder";
        	var name = $(_this).parent().parent().find('input').val();
        	var pid = $('#breadcrumb').find('li:last').data('pid');
        	if(!name){
        		Message.showMsg('文件夹名称不能为空！', 'warn');
        		FileList.folderCancel();
        		return;
        	}else if(!pid){
        		Message.showMsg('非法操作！', 'error');
        		FileList.folderCancel();
        		return;
        	}
        	
        	$.post(url, {name:name,pid:pid}, function (data) {
            	if(data.isSuccess){
            		Message.showMsg('新建文件夹成功！', 'success');
            		$(_this).parent().parent().html(name);
            	}else{
            		Message.showMsg('新建文件夹失败,请稍后重试！', 'error');
            		FileList.folderCancel();
            	}
            });
        },
        fileDelete: function(_this, url){
        	if(!url)url = "/file-tree/delete?id=";
        	var id = $(_this).data('id');
        	if(!id){
        		Message.showMsg('非法操作！', 'warn');
        		return;
        	}
        	url += id; 
        	$.ajax({
                url:url,
                type:"delete",
                contentType:"application/json",
                dataType:"json",
                data:{},
                success:function(msg){
                	if(msg.isSuccess){
                		Message.showMsg('删除成功！', 'success');
//                		window.location.href = '/file/yunpan';
                		$('#show-file tr').eq($(_this).data('record-index')).remove();
                	}else{
                		Message.showMsg('删除失败,请稍后重试！', 'error');
                	}
                },
                error:function(xhr,textstatus,thrown){
                	Message.showMsg('删除失败,请稍后重试！', 'error');
                }
            });
        }
	}
}();