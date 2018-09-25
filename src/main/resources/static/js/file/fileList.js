// 云盘展示页面--js
var FileList = function(){
	// 公共变量
	var TimeFn = null; // 单击事件
	var MemoryPool = new Object(); // 内存池-保存复制或剪切的内容
	// 私有函数
	
	// 监听事件
	// 文件展示方式
    $("#file-style button").bind("click", function(){
        $(this).addClass("active").siblings().removeClass("active");
    });

    // 左侧导航菜单折叠和展开
    $("#file-left ul").on("click", "li:first", function(){
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
    $("#file-left ul").on("click", "li:not(:first)", function(){
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
    
    // 点击路径导航事件
//    $('.breadcrumb > li').bind("click", function(){
//    	$(this).addClass("active").siblings().removeClass("active");
//    	$(this).html($(this).find('a').html());
//    	$(this).nextAll().remove();
//    });
    
    // 点击新建文件夹
    $('.glyphicon-plus').parent().bind("click", function(){
    	var nowTime=new Date().Format("yyyy-MM-dd HH:mm:ss");
    	var str = '<tr>                                                                         '+
    	'  <td class="col-md-1" data-id="">                                           '+
    	'	<span class="glyphicon glyphicon-unchecked" style="cursor:pointer"></span>'+
    	'	<div class="fileicon-position fileicon-small-folder"></div>                '+
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
	        FileList.initLeftFileCount();
	        // 初始化第一层文件夹
	        FileList.findFolder(1, '云盘');
	        // 初始化文件夹列表的右键事件
	        FileList.initRightClick();
		},
		initLeftFileCount: function(){
			var url = "/file-tree/left-count";
			FileList.sendRequest(url, function(msg){
        		if(msg.isSuccess){
//            		console.log(msg.data);
            		var tpl = $('#left-file-count-tpl').html();
            		var html = juicer(tpl, msg.data);
            		$('#left-file-count').html(html);
            	}else{
            		Message.showMsg('获取文件数量失败:' + msg.resultMsg, 'error');
            	}
        	});
		},
		initRightClick: function(){ // 右键菜单事件监听
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
		    			
//		    	       	console.log($(context).find('td:first').data('id'));
//		    	       	console.log(this.getMenu().find('ul > li').eq(1).find('a').attr('href'));
		    	       	this.getMenu().find('ul > li').eq(0).find('a').prop('href', '/file/download?id='+$(context).find('td:first').data('id'));
//		    	       	this.getMenu().find('ul > li').eq(0).find('a').prop('href', '../file-tree/downloads?fileId='+$(context).find('td:first').data('id'));
		    		}
		        },
		        onItem: function(context,e) {
		            // execute on menu item selection
		        }
		    });
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
            		$(_this).parent().parent().parent().html(name);
            		$('#show-file').find('tr:first td:first').attr('data-id', data.resultMsg);
//            		window.location.href = '/file/yunpan';
            		// 重新监听右键事件
            		FileList.initRightClick();
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
            		Message.showMsg('删除到回收站成功！', 'success');
//            		window.location.href = '/file/yunpan'; // 不推荐重新刷新页面
//            		// 更新左侧文件夹统计数
//            		var name = $('#show-file tr').eq($(_this).data('record-index')).find('td:eq(1)').html();
//            		console.log($('#show-file tr').eq($(_this).data('record-index')).find('td:eq(1)').html());
            		
            		// 此目标删除
            		$('#show-file tr').eq($(_this).data('record-index')).remove();
            	}else{
            		Message.showMsg('删除到回收站失败:' + msg.resultMsg, 'error');
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
            		Message.showMsg('彻底删除成功！', 'success');
//            		window.location.href = '/file/yunpan';
            		$('#show-file tr').eq($(_this).data('record-index')).remove();
            	}else{
            		Message.showMsg('彻底删除失败:' + msg.resultMsg, 'error');
            	}
        	}, {}, 'delete');
        },
        file2Rename: function(_this){
        	var targetRecord = $('#show-file tr').eq($(_this).data('record-index')).find('td:eq(1)');
//        	var id = $('#show-file tr').eq($(_this).data('record-index')).find('td:eq(0)').data('id').trim();
        	var id = $(_this).data('id');
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
        file2Copy: function(_this){
        	var id = $(_this).data('id');
        	var pid = $('#breadcrumb').find('li:last').data('pid');
        	if(!id || !pid){
        		Message.showMsg('非法操作！', 'error');
        		return;
        	}
        	
        	MemoryPool.id = id;
        	MemoryPool.pid = pid;
        	
        	
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
        	if(typeof pid =='string'){
        		pid = pid.trim();
        	}
        	$.get(url, {pid:pid}, function (msg) {
        		FileList.renderFolderData(msg, pid);
        	});
        },
        findFolderByType: function(pid, name){
        	pid = 1;
        	var url = '/file-tree/findFolder'
        	if(!pid && !name){
        		Message.showMsg('非法操作！', 'warn');
        		return;
        	}
        	if(typeof pid =='string'){
        		pid = pid.trim();
        	}
        	$.get(url, {pid:pid}, function (msg) {
        		FileList.renderFolderData(msg, pid);
        	});
        },
        renderFolderData: function(msg, pid){
        	if(msg.isSuccess){
    			// 进入文件夹
    			var targetIndex = -1;
    			$('#breadcrumb li').each(function(index, element){
    				if($(element).data('pid') == pid){
    					targetIndex = index;
    					return true;
    				}
			    });
    			var str = '';
    			if(targetIndex ==0){
    				$('#breadcrumb').empty();
    				str = '<li class="active" onclick="FileList.findFolder(\''+pid+'\', \''+name+'\');" data-pid="'+pid+'"><a href="javascript:void(0);"><span class="glyphicon glyphicon-home"></span> '+name+'</a></li>';
    			}else{
    				if(targetIndex !=-1 && targetIndex != $('#breadcrumb li:last').index()){
    					$('#breadcrumb').find('li').eq(targetIndex - 1).nextAll().remove();
    				}
    				str = '<li class="active" data-pid="'+pid+'"><span class="glyphicon glyphicon-folder-open"></span> '+name+'</li>';
    			}
    			
    			// 更新导航
    			if($('#breadcrumb li:last').index() > 0){ // 更新倒数第二个导航
    				var prev_id = $('#breadcrumb li:last').data('pid');
    				var prev_name = $('#breadcrumb li:last').text().trim();
    				var prev_html = '<li data-pid="'+prev_id+'" onclick="FileList.findFolder(\''+prev_id+'\', \''+prev_name+'\');"><a href="javascript:void(0);"><span class="glyphicon glyphicon-folder-close"></span> ' + prev_name + '</a></li>';
    				$('#breadcrumb li:last').remove();
    				$('#breadcrumb li:last').after(prev_html);
    			}
    			$('#breadcrumb li:last').removeClass('active');
    			$('#breadcrumb').append(str);
    			
    			// 更新子文件夹里的内容
    			$('#show-file tr').remove();
        		var tpl = $('#file-list-tpl').html();
        		var html = juicer(tpl, msg);
        		$('#show-file tbody').append(html);
        		// 重新监听右键事件
        		FileList.initRightClick();
        	}else{
        		Message.showMsg('查询失败:' + msg.resultMsg, 'error');
        	}
        }
	}
}();