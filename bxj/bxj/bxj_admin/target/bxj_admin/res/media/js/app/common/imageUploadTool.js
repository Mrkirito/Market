define(["jquery", 'util',
    'app/common/winTool',
    'plugins/dropzone/dropzone',
    'css!global/js/lib/plugins/dropzone/css/dropzone.css'
], function ($, util, winTool) {

    var imageUpload = {
        init: function (config) {
            config = config || {};
            this.callback = config.callback|| function () {};
            this.dataList = [];
            this.maxFiles = config.maxFiles == undefined?null:config.maxFiles;
            this.acceptedFiles = config.acceptedFiles == undefined?null:config.acceptedFiles;
            this.attach = config.attach == undefined?0:config.attach;
            this.compress = config.compress == undefined?1:config.compress;
            this.gaussian = config.gaussian == undefined?0:config.gaussian;
            this.maxFilesize = config.maxFilesize == undefined?50:config.maxFilesize;

            this.extData = config.extData||{};

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
            var thiz = this,
                extString = "";

            $.each(this.extData, function (name, value) {
                extString += '<input type="hidden" name="' + name + '" value="' + value + '">';
            });

            var imageHtml =
                "<form method='post' class='x-image-upload-form dropzone' enctype='multipart/form-data' style='width: 100%;height: 100%; overflow-y: auto; text-align: left;'>" +
                '<input type="hidden" name="attach" value="' + thiz.attach + '">' +
                '<input type="hidden" name="compress" value="' + thiz.compress + '">' +
                '<input type="hidden" name="gaussian" value="' + thiz.gaussian + '">' +
                extString +
                "</form>";
            this.uploadForm = $(imageHtml).appendTo(this.win.$content);

            this.uploadForm.dropzone({
                url: serverHost + "/common/uploadImage.json",
                paramName: "imageFile",
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
                    //console.info(file)
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
                            if(resp.msg) {
                                $(file.previewElement).find(".dz-error-message").find("span").html(resp.msg);
                            }
                        }
                    }
                },
                removedfile: function (file) {
                    var dataList = file.data||{};
                    $(dataList).each(function (idx, data) {
                        if(thiz.containsImageData(data)) {
                            thiz.removeImageData(data);
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
            if(data) {
                if(!this.containsImageData(data)) {
                    this.dataList.push(data);
                }
            }
        },

        containsImageData: function (imageData) {
            imageData = imageData ||{};
            var contains = false;
            $(this.dataList).each(function(idx, data){
                if(imageData.fileName === data.fileName) {
                    contains = true;
                    return false;
                }
            });
            return contains;
        },

        removeImageData: function (imageData) {
            imageData = imageData || {};
            var thiz = this,
                newDataList = [];
            $(thiz.dataList).each(function(idx, data){
                if(imageData.id != data.id) {
                    newDataList.push(data);
                }
            });
            thiz.dataList = newDataList;
        }
    };

    return imageUpload;
});