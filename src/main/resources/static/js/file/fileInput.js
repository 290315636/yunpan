
//初始化fileinput
var FileInput = function () {
	
	return {
		init: function(target, uploadUrl){
			var target = $('#' + target);
	        //初始化上传控件的样式
			target.fileinput({
	            language: 'zh',                                         //设置语言
	            uploadUrl: uploadUrl,                                   //上传的地址
	            allowedFileExtensions: ['jpg', 'gif', 'png'],    		//接收的文件后缀
	            showUpload: true,                                       //是否显示上传按钮
	            showCaption: false,                                     //是否显示标题
	            browseClass: "btn btn-primary",                         //按钮样式     
//	            dropZoneEnabled: false,                                 //是否显示拖拽区域
	            //minImageWidth: 50,                                    //图片的最小宽度
	            //minImageHeight: 50,                                   //图片的最小高度
	            //maxImageWidth: 1000,                                  //图片的最大宽度
	            //maxImageHeight: 1000,                                 //图片的最大高度
	            maxFileSize: 0,                                     //单位为kb，如果为0表示不限制文件大小
	            minFileCount: 0,
	            maxFileCount: 20,                                        //表示允许同时上传的最大文件个数
	            enctype: 'multipart/form-data',
	            validateInitialCount:true,
	            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
	            msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
	            uploadExtraData:function (previewId, index) {
	            	//传参
	        	    var data = {
	                    "md5": 'warwreasd',      //此处自定义传参
	                };
	                return data;
	            	// return {};
	            }
	        });

	        //导入文件上传完成之后的事件
	        $("#fileUpload").on("fileuploaded", function (event, data, previewId, index) {
	            //报告table刷新
//	            showDataGrid_report($('#lbl_groupId').html());
	        	console.log(data);
	        	console.log(previewId);
	        	console.log(index);
	        });
		}
	}; 
}();
