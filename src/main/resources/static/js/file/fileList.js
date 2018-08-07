// 云盘展示页面--js
var FileList = function(){
	// 公共变量
	var TimeFn = null; // 单击事件
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
    	var _this = $(this);
    	// 取消上次延时未执行的方法
        clearTimeout(TimeFn);
        //执行延时
        TimeFn = setTimeout(function(){
            //do function在此处写单击事件要执行的代码
        	if($(_this).index()==0 && $(_this).find('input').is('input')){
        		// 新建文件夹生成的一条记录-->监听enter事件
        		$(_this).find('input').keyup(function(event){
    			  if(event.keyCode ==13){
    				  $(this).next().children('button:last').click();
    			  }
    			});
        	}else{
        		if($(_this).find('td:first div').hasClass('fileicon-small-foler')){
        			$('#file-menu-more').hide();	// 隐藏对应可操作菜单
        		}else{
        			$('#file-menu-more').show();	// 显示对应可操作菜单
        		}
        		// 选中/取消
        		if($(_this).find(' td:first span').hasClass('glyphicon-check')){
                    $(_this).find(' td:first span').replaceWith('<span class="glyphicon glyphicon-unchecked" style="cursor:pointer"></span>');
                }else if(!$(_this).find(' td:first span').is('glyphicon-check')){
                    $(_this).find(' td:first span').replaceWith('<span class="glyphicon glyphicon-check" style="cursor:pointer"></span>');
                }
        	}
        },300);
    });
    
    $("#show-file").on("dblclick", "tr", function(ev){
    	ev.stopPropagation();
    	// 取消上次延时未执行的方法
        clearTimeout(TimeFn);
        //双击事件的执行代码
        var id = $(this).find('td:first').data('id');
        var name = $(this).find('td:eq(1)').html();
//        console.log(id);
        FileList.findFolder(id, name);
    });

	// 右键菜单
    // 动态修改菜单
    $('#show-file tr').contextmenu({
        target:'#context-menu',
        before: function(e,context) {
        	// execute code before context menu if shown
        	// 每个选项加上隐藏属性
            this.getMenu().find('ul > li').data('id', $(context).find('td:first').data('id'));
            this.getMenu().find('ul > li').data('record-index', $(context).index());
            $(context).find('td:first span').replaceWith('<span class="glyphicon glyphicon-check" style="cursor:pointer"></span>');
            
        	if($(context).find('td:first div').hasClass('fileicon-small-foler')){// 文件夹
        		this.getMenu().find('ul > li:first').hide();
    			this.getMenu().find('ul > li:last').hide();
    			$('#file-menu-more').hide();	// 隐藏对应可操作菜单
    		}else{
    			this.getMenu().find('ul > li:first').show();
    			this.getMenu().find('ul > li:last').show();
    			$('#file-menu-more').show();	// 显示对应可操作菜单
    			
//    	       	console.log($(context).find('td:first').data('id'));
//    	       	console.log(this.getMenu().find('ul > li').eq(1).find('a').attr('href'));
    	       	this.getMenu().find('ul > li').eq(0).find('a').prop('href', '/file/download?id='+$(context).find('td:first').data('id'));
//    	       	this.getMenu().find('ul > li').eq(0).find('a').prop('href', '../file-tree/downloads?fileId='+$(context).find('td:first').data('id'));
    		}
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
    	'  <td>-</td>                                                                 '+
    	'  <td>'+nowTime+'</td>                                                                  '+
    	'</tr>                                                                        ';
    	
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
			Message.init();
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
//            		$(_this).parent().parent().html(name);// TODO 新增的记录监听不到右键事件
            		window.location.href = '/file/yunpan';
            	}else{
            		Message.showMsg('新建文件夹失败,请稍后重试！', 'error');
            		FileList.folderCancel();
            	}
            });
        },
        sendRequest: function(url, callback, data, method){
        	if(!data)data = {};
        	if(!method)method = 'post';
        	$.ajax({
                url: url,
                type: method,
                contentType: "application/json",
                dataType: "json",
                data: data,
                success: function(msg){
                	callback(msg);
                },
                error: function(xhr, textstatus, thrown){
                	Message.showMsg('操作失败,请稍后重试！', 'error');
                }
            });
        },
        fileReback: function(_this, url){
        	var id = $(_this).data('id');
        	if(!url)url = "/file-tree/inputReback?id=" + id;
        	if(!id){
        		Message.showMsg('非法操作！', 'warn');
        		return;
        	}
        	FileList.sendRequest(url, function(msg){
        		if(msg.isSuccess){
            		Message.showMsg('删除成功！', 'success');
//            		window.location.href = '/file/yunpan';
            		$('#show-file tr').eq($(_this).data('record-index')).remove();
            	}else{
            		Message.showMsg('删除失败:' + msg.resultMsg, 'error');
            	}
        	}, {}, 'put');
        },
        fileDelete: function(_this, url){
        	var id = $(_this).data('id');
        	if(!url)url = "/file-tree/delete?id=" + id;
        	if(!id){
        		Message.showMsg('非法操作！', 'warn');
        		return;
        	}
        	FileList.sendRequest(url, function(msg){
        		if(msg.isSuccess){
            		Message.showMsg('删除成功！', 'success');
//            		window.location.href = '/file/yunpan';
            		$('#show-file tr').eq($(_this).data('record-index')).remove();
            	}else{
            		Message.showMsg('删除失败:' + msg.resultMsg, 'error');
            	}
        	}, {}, 'delete');
        },
        file2Rename: function(_this){
        	var targetRecord = $('#show-file tr').eq($(_this).data('record-index')).find('td:eq(1)');
        	var id = $('#show-file tr').eq($(_this).data('record-index')).find('td:eq(0)').data('id').trim();
        	var old = $(targetRecord).html().trim();
        	var str = '	<div class="input-group input-group-sm">                                              '+
        	'	    <input type="text" class="form-control" placeholder="'+old+'" value="'+old+'" aria-label="...">             '+
        	'		<div class="input-group-btn">                                         '+
        	'		  <button onclick="FileList.fileRenameCancel(this,\''+old+'\');" class="btn btn-default glyphicon glyphicon-remove" type="button"></button>'+
        	'		  <button onclick="FileList.fileRenameConfirm(this,\''+id+'\',\''+old+'\');" class="btn btn-default glyphicon glyphicon-ok" type="button"></button>'+
        	'		</div>                                                                '+
        	'	</div>                                                                    ';
        	$(targetRecord).html(str);
        },
        fileRenameCancel: function(_this, old){
        	$(_this).parent().parent().parent().html(old);
        },
        fileRenameConfirm: function(_this, id, old){
        	var name = $(_this).parent().parent().find('input').val();
        	console.log(name);
        	if(!name){
        		Message.showMsg('名称不能为空！', 'warn');
        		FileList.fileRenameCancel(_this, old);
        		return;
        	}else if(!id){
        		Message.showMsg('非法操作！', 'error');
        		FileList.fileRenameCancel(_this, old);
        		return;
        	}
        	var url = "/file-tree/rename?id=" + id + '&name=' + name;
        	FileList.sendRequest(url, function(msg){
        		if(msg.isSuccess){
            		Message.showMsg('重命名成功！', 'success');
            		FileList.fileRenameCancel(_this, name);
            	}else{
            		Message.showMsg('重命名失败:' + msg.resultMsg, 'error');
            	}
        	}, {}, 'put');
        },
        findFolder: function(pid, name){
        	var url = '/file-tree/findFolder'
        	if(!pid && !name){
        		Message.showMsg('非法操作！', 'warn');
        		return;
        	}
        	$.get(url, {pid:pid}, function (msg) {
        		if(msg.isSuccess){
        			// 进入文件夹
        			var str = '<li class="active" data-pid="'+pid+'"><a href="#">'+name+'</a></li>';
        			$('#breadcrumb li').removeClass('active').append(str);
            		if(msg.data.length == 0){ 
            			$('#show-file tr').remove();
            		}
            	}else{
            		Message.showMsg('查询失败:' + msg.resultMsg, 'error');
            	}
        	});
        }
	}
}();