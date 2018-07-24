var Message = function(){
	// 提示对象单例
	var tip;
	
	return {
		init: function(){
			Message.initMessager('air');
//			Message.showMsg('欢迎使用本系统！', 'success');
//			Message.showMsg('欢迎使用本系统！', 'error');
//			Message.showMsg('欢迎使用本系统！', 'info');
//			Message.showMsg('欢迎使用本系统！', 'warn');
			Message.showTip('欢迎使用本系统！', 'success');
//			Message.showTip('欢迎使用本系统！', 'error');
//			Message.showTip('欢迎使用本系统！', 'info');
//			Message.showTip('欢迎使用本系统！', 'warn');
		},
		initMessager: function(theme){
			// 初始化主题及位置
//			Messenger.options = {
//			    extraClasses: 'messenger-fixed messenger-on-bottom messenger-on-right',
//			    theme: theme // flat、future、block、air、ice
//			}
			tip = Messenger().post({
              message: '操作成功',
              type: 'success',
              id: '1',
              singleton: true,
              hideAfter: 3,
	          hideOnNavigate: true
            });
			tip.hide();
		},
		showMsg: function(msg, type){
			if(!type || type == 'success'){
				Message.showSuccessMsg(msg);
			}else if(type == 'error' || type == 'danger'){
				Message.showErrorMsg(msg);
			}else if(type == 'info'){
				Message.showInfoMsg(msg);
			}else if(type == 'warn' || type == 'warning'){
				Message.showWarnMsg(msg);
			}
		},
		showSuccessMsg: function(msg){
			var tmp = msg;
			if(!msg || typeof(msg) == 'undefined'){
				tmp = '操作成功';
			}
			Messenger().post({
              message: tmp,
              type: 'success',
              hideAfter: 1,
              hideOnNavigate: true
            });
		},
		showErrorMsg: function(msg){
			var tmp = msg; 
			if(!msg || typeof(msg) == 'undefined'){
				tmp = '操作失败';
			}
			Messenger().post({
              message: tmp,
              type: 'danger',
              hideAfter: 5,
              hideOnNavigate: true
            });
		},
		showInfoMsg: function(msg){
			if(!msg || typeof(msg) == 'undefined'){
				msg = '此功能更新，请稍后访问！';
			}
			Messenger().post({
              message: msg,
              type: 'info',
              hideAfter: 3,
              hideOnNavigate: true
            });
		},
		showWarnMsg: function(msg){
			if(!msg || typeof(msg) == 'undefined'){
				msg = '系统更新，请稍后访问！';
			}
			Messenger().post({
              message: msg,
              type: 'warning',
              hideAfter: 3,
              hideOnNavigate: true
            });
		},
		showTip: function(msg, type){
			if(!type || type == 'success'){
				Message.showSuccessTip(msg);
			}else if(type == 'error' || type == 'fail' || type == 'danger'){
				Message.showErrorTip(msg);
			}else if(type == 'info'){
				Message.showInfoTip(msg);
			}else if(type == 'warn' || type == 'warning'){
				Message.showWarnTip(msg);
			}
		},
		showSuccessTip: function(msg){
			tip.update({
				message: msg,
	            type: 'success'
			});
		},
		showErrorTip: function(){
			tip.update({
				message: msg,
	            type: 'danger'
			});
		},
		showInfoTip: function(){
			tip.update({
				message: msg,
	            type: 'info'
			});
		},
		showWarnTip: function(){
			tip.update({
				message: msg,
	            type: 'warning'
			});
		},
		showMessage: function(message, type){
		    var msg = Messenger().post({
              message: message,
              type: type,// info、error、
              actions: {
                cancel: {
                  label: '取消',
                  action: function() {
                    msg.update({
                      message: 'Thermonuclear war averted.',
                      type: 'success',
                      actions: false
                    });
                  }
                }
              }
            });
		}
		
	}
}();