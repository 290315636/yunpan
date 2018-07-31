
//初始化fileinput
var FileInput = function () {
	var file_md5 = [], md5;
	function get_filemd5sum(ofile, i, le, fn) {
        var file = ofile;
        var tmp_md5;
        var blobSlice = File.prototype.slice || File.prototype.mozSlice || File.prototype.webkitSlice,
            // file = this.files[0],
            chunkSize = 8097152, // Read in chunks of 2MB
            chunks = Math.ceil(file.size / chunkSize),
            currentChunk = 0,
            spark = new SparkMD5.ArrayBuffer(),
            fileReader = new FileReader();

        fileReader.onload = function(e) {
            // console.log('read chunk nr', currentChunk + 1, 'of', chunks);
            spark.append(e.target.result); // Append array buffer
            currentChunk++;
            var md5_progress = Math.floor((currentChunk / chunks) * 100);

            console.log(file.name + "  正在处理，请稍等," + "已完成" + md5_progress + "%");
//            var handler_info = document.getElementById("handler_info");
//            var progressbar = document.getElementsByClassName("progressbar")[0];
            
//            handler_info.innerHTML=file.name + "  正在处理，请稍等," + "已完成" + md5_progress + "%"
//            progressbar.value =md5_progress;
            if (currentChunk < chunks) {
                loadNext();
            } else {
                tmp_md5 = spark.end();
                md5 = tmp_md5;
                console.log(tmp_md5);
                file_md5[file_md5.length] = tmp_md5;
                
                if(i == le-1){
					fn();
				}
//                handler_info.innerHTML = file.name + "的MD5值是：" + tmp_md5;
            }
        };

        fileReader.onerror = function() {
            console.warn('oops, something went wrong.');
        };

        function loadNext() {
            var start = currentChunk * chunkSize,
                end = ((start + chunkSize) >= file.size) ? file.size : start + chunkSize;
            fileReader.readAsArrayBuffer(blobSlice.call(file, start, end));
        }
        loadNext();
    }

    
//	$('.kv-file-zoom').bind('click', function(){
//		$('#kvFileinputModal').find('.modal-backdrop').removeClass('in');
//	});
	
	return {
		init: function(target, uploadUrl){
			var target = $('#' + target);
	        //初始化上传控件的样式
			target.fileinput({
	            language: 'zh',                                         //设置语言
	            uploadUrl: uploadUrl,                                   //上传的地址
//	            allowedFileExtensions: ['jpg', 'gif', 'png'],    		//接收的文件后缀
	            showClose : true,										//显示右上角X关闭
	            showRemove : false,										//显示移除按钮,跟随文本框的那个
	            showUpload : false,										//是否显示上传后的按钮
	            showBrowse : true,										//是否显示上传前的上传按钮
	            showCaption: true,                                     //是否显示标题
	            browseClass: "btn btn-primary",                         //按钮样式     
//	            dropZoneEnabled: false,                                 //是否显示拖拽区域
	            showAjaxErrorDetails: true,								//显示出错信息
//	            minImageWidth: 50,                                    //图片的最小宽度
//	            minImageHeight: 50,                                   //图片的最小高度
//	            maxImageWidth: 1000,                                  //图片的最大宽度
//	            maxImageHeight: 1000,                                 //图片的最大高度
	            maxFileSize: 0,                                     //单位为kb，如果为0表示不限制文件大小
	            minFileCount: 0,
	            maxFileCount: 20,                                        //表示允许同时上传的最大文件个数
	            enctype: 'multipart/form-data',
	            validateInitialCount:true,
	            previewClass: "",
	            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
	            msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
	            uploadExtraData:function (previewId, index) {
	            	//传参
	        	    var data = {
	        	    	"pid": "1",
	                    "md5": file_md5[index]      //此处自定义传参
	                };
	                return data;
	            },
				slugCallback: function (filename) {
		            return filename.replace('(', '_').replace(']', '_');
		        }
		    });
			
			function uploadfile2back(){
				$("#fileUpload").fileinput("upload");
			}
			
			function getMd5From(files, up){
				for(let i=0;i<files.length;i++){
					get_filemd5sum(files[i], i, files.length, up);// 循环上传
				}
			}
			
			// 选择文件时计算文件md5值
			$("#fileUpload").on("filebatchselected", function(event, files) {
				getMd5From(files, uploadfile2back);
				
			});
						
//			$("#fileUpload").on("filepreremove",function(event, data, msg){
//				console.log("filepreremove:"+data);
//			});
//			// 上传成功后删除
//			$("#fileUpload").on("filesuccessremove",function(event, data, msg){
//				console.log(data);
//			});

	        //导入文件上传完成之后的事件
	        $("#fileUpload").on("fileuploaded", function (event, file, previewId, index) {
//	        	console.log(file);
//	        	console.log(file.name);
	        	
	        });
		},
		resetFileMd5: function(){
			file_md5 = [];
		},
		getFileMd5: function(){
			return file_md5;
		},
		addFileMd5: function(md5){
			if(file_md5.length==0){
				file_md5[0] = md5;
			}else {
				var has_md5 = false;
				for(let i=0;i<file_md5.length;i++){
					if(file_md5[i] == md5){
						has_md5 = true;
					}
				}
				if(!has_md5){
					file_md5[file_md5.length] = md5;
				}
			}
		},
		checkFileMd5: function(md5){
			if(file_md5.length==0)return false;
			for(let i=0;i<file_md5.length;i++){
				if(file_md5[i] == md5){
					return true;
				}
			}
			return false;
		}
	}; 
}();
