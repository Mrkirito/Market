define(["jquery", 'util',
    'app/common/winTool',
    'plugins/dropzone/dropzone',
    'css!global/scripts/lib/plugins/dropzone/css/dropzone.css'
], function ($, util, winTool) {

    var fileUpload = {
        init: function (config) {
            config = config || {};
            this.callback = config.callback|| function () {};
            this.dataList = [];
            this.maxFiles = config.maxFiles == undefined?null:config.maxFiles;
            this.acceptedFiles = config.acceptedFiles == undefined?null:config.acceptedFiles;
            this.attach = config.attach == undefined?0:config.attach;
            this.maxFilesize = config.maxFilesize == undefined?10:config.maxFilesize;
            this.url = config.url == undefined?null:config.url;
            this.params = config.params||{};

            if(this.params.uploadFileName) {
                this.uploadFileName = this.params.uploadFileName;
            }

            this.createWin({title: config.title});
            this.initUploadContent();
        },

        createWin: function (config) {
            var thiz = this,

            win = winTool.create({
                title: config.title||"文件上传",
                okName: "确定",
                cancelName: "关闭",
                type: 'html',
                html: "",
                width: 700,
                height: 500,
                okFn: function (win) {
                    if(thiz.callback) {
                        thiz.callback(thiz.dataList);
                    }
                    win.close();
                }
            });
            win.$content.css("padding", "0px");
            this.win = win;

        },

        initUploadContent: function () {
            var thiz = this;
            var fileHtml =
                "<form method='post' class='x-file-upload-form dropzone' enctype='multipart/form-data' style='height: 100%; overflow-y: auto;'>" +
                "</form>";
            thiz.uploadForm = $(fileHtml).appendTo(this.win.$content);

            $.each(thiz.params, function (key, value) {
                var param = '<input type="hidden" name="' + key + '" value="' + value +'">';
                $(param).appendTo(thiz.uploadForm);
            })

            thiz.uploadForm.dropzone({
                url: thiz.url || (serverHost + "common/upload/uploadImage.json"),
                paramName: thiz.uploadFileName || "file",
                uploadMultiple: false,
                dictDefaultMessage: "拖放文件到这里或点击鼠标上传" + (thiz.acceptedFiles?"<br>(支持文件后缀:<br>" + thiz.acceptedFiles + ")" : ""),
                dictFallbackMessage: "您的浏览器不支持此上传控件.",
                dictFallbackText: "请使用下面的备用形式上传您的文件.",
                dictFileTooBig: "文件太大 ({{filesize}}MiB). 最大上传文件大小: {{maxFilesize}}MiB.",
                dictInvalidFileType: "不能上传此类型的文件.",
                dictResponseError: "Server responded with {{statusCode}} code.",
                dictCancelUpload: "取消上传",
                dictCancelUploadConfirmation: "确定要取消上传?",
                dictRemoveFile: "删除文件",
                dictRemoveFileConfirmation: null,
                dictMaxFilesExceeded: "最多只能上传{{maxFiles}}个文件.",
                addRemoveLinks: true,
                maxFilesize: thiz.maxFilesize,
                acceptedFiles: thiz.acceptedFiles,
                maxFiles: thiz.maxFiles,
                success: function (file, resp, event) {
                    if(resp.success == true) {
                        var data = resp.model||{};
                        thiz.addDataList(data);
                        file.data = data;
                        if (file.previewElement) {
                            file.previewElement.classList.add("dz-success");
                        }
                    } else {
                        if (file.previewElement) {
                            file.previewElement.classList.add("dz-error");
                            $(file.previewElement).find(".dz-error-message span").html(resp.msg||"上传失败");
                        }
                    }
                },
                removedfile: function (file) {
                    var dataList = file.data||{};
                    $(dataList).each(function (idx, data) {
                        if(thiz.containsFileData(data)) {
                            thiz.removeFileData(data);
                        }
                    });

                    var _ref;
                    if (file.previewElement) {
                        if ((_ref = file.previewElement) != null) {
                            _ref.parentNode.removeChild(file.previewElement);
                        }
                    }
                }
            });
        },

        addDataList: function (data) {
            var thiz = this;
            data = data || {};
            thiz.addData(data);
        },

        addData: function (data) {
            if(data && data.fileName) {
                if(!this.containsFileData(data)) {
                    this.dataList.push(data);
                }
            }
        },

        containsFileData: function (fileData) {
            fileData = fileData ||{};
            var contains = false;
            $(this.dataList).each(function(idx, data){
                if(fileData.fileName === data.fileName) {
                    contains = true;
                    return false;
                }
            });
            return contains;
        },

        removeFileData: function (fileData) {
            fileData = fileData || {};
            var thiz = this,
                newDataList = [];
            $(thiz.dataList).each(function(idx, data){
                if(fileData.id != data.id) {
                    newDataList.push(data);
                }
            });
            thiz.dataList = newDataList;
        }

    };

    return fileUpload;
});