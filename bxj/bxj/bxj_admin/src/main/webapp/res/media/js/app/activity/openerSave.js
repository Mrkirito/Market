define(['jquery', 'app/common/util', 'app/common/advanceListTool',
        'app/common/dateTool', 'app/common/messageTool', 'app/common/commonTool', 'app/common/winTool', 'app/common/imageUploadTool', 'app/common/imageView'],
    function ($, util, listTool, dateTool, messageTool, commonTool, winTool, imageUploadTool) {
        var profitList = {
            list: function () {
                var thiz = this;
                //时间渲染
                dateTool.renderDateTime(".publishDate");
                thiz.renderArticleUeditor(thiz);
                $(".infos-images").on("click", function () {
                    var thiz = this;
                    imageUploadTool.init({
                        title: "上传图片",
                        attach: 12,
                        acceptedFiles: ".png,.jpg,.jpeg",
                        maxFiles: 1,
                        callback: function (images) {
                            var img = images.length > 0 ? images[0] : {};
                            $(thiz).parent().find(".picUrl").attr("src", public_path + img.fileName);
                            $(thiz).parent().find(".picUrl").data("url", img.fileName);
                        }
                    });
                });
                $(".uploadImg").on("click", ".remove-img", function () {
                    var thiz = this;
                    $(thiz).parent().find(".picUrl").attr("src", "");
                    $(thiz).parent().find(".picUrl").data("url", "");
                });

                var articleId = $("#articleId").val();
                if (articleId != null && articleId != "") {
                    util.request({
                        confirm: false,
                        url: serverHost + "/activity/queryOpenerArticleById.json",
                        params: {id: articleId},
                        success: function (resp) {
                            if (resp.success) {
                                $(".title").val(resp.model.title);
                                $(".typeId").val(resp.model.typeId);
                                $(".publishDate").val(util.dateFormat2(resp.model.publishDate, 'YYYY-MM-DD HH:mm:ss'));
                                $(".iconUrl").attr("src", public_path + resp.model.iconUrl);
                                $(".iconUrl").data("url", resp.model.iconUrl);
                                var ue = UE.getEditor('articleContent');
                                ue.addListener("ready", function () {
                                    // editor准备好之后才可以使用
                                    ue.setContent(resp.model.articleContent);
                                });
                                $(".browseCountVirtual").val(resp.model.browseCountVirtual);
                                $(".shareCountVirtual").val(resp.model.shareCountVirtual);
                                $(".showNewStatus").val(resp.model.showNewStatus);
                                $(".showHotStatus").val(resp.model.showHotStatus);
                                $(".sort").val(resp.model.sort);
                                $(".showStatus").val(resp.model.showStatus);
                            }
                        }
                    });
                }
                $(".btn-submit").on("click", function () {
                    var title = $(".title").val();
                    var typeId = $(".typeId").val();
                    var publishDate = $(".publishDate").val();
                    var iconUrl = $(".iconUrl").data("url");
                    var articleSource = $(".articleSource").val();
                    var articleContent = UE.getEditor('articleContent').getContent();
                    var browseCountVirtual = $(".browseCountVirtual").val();
                    var shareCountVirtual = $(".shareCountVirtual").val();
                    var showNewStatus = $(".showNewStatus").val();
                    var showHotStatus = $(".showHotStatus").val();
                    var sort = $(".sort").val();
                    var showStatus = $(".showStatus").val();
                    var flag = true;
                    if (!title) {
                        messageTool.error("必须输入标题!");
                        flag = false;
                    }
                    if (!typeId) {
                        messageTool.error("必须选择文章分类!");
                        flag = false;
                    }
                    if (!publishDate) {
                        publishDate = new Date();
                    }
                    if (!iconUrl) {
                        messageTool.error("必须上传文章附图!");
                        flag = false;
                    }
                    if (!articleSource) {
                        messageTool.error("必须输入文章来源!");
                        flag = false;
                    }
                    if (!browseCountVirtual) {
                        browseCountVirtual = 0;
                    }
                    if (!shareCountVirtual) {
                        shareCountVirtual = 0;
                    }
                    if (!showNewStatus) {
                        messageTool.error("必须选择最新icon显示状态!");
                        flag = false;
                    }
                    if (!showHotStatus) {
                        messageTool.error("必须选择热门icon显示状态!");
                        flag = false;
                    }
                    if (!sort) {
                        sort = 0;
                    }
                    if (!showStatus) {
                        messageTool.error("必须选择文章显示状态!");
                        flag = false;
                    }
                    if (!flag)return;
                    var params = {
                        id: articleId,
                        title: title, typeId: typeId, iconUrl: iconUrl,
                        publishDate: publishDate, articleSource: articleSource,
                        articleContent: articleContent, browseCountVirtual: browseCountVirtual,
                        shareCountVirtual: shareCountVirtual, showNewStatus: showNewStatus,
                        showHotStatus: showHotStatus, sort: sort,
                        showStatus: showStatus
                    };
                    util.request({
                        confirm: true,
                        msg: "确定保存开门红文章吗",
                        url: serverHost + "/activity/saveOpener.json",
                        params: params,
                        success: function (resp) {
                            if (resp.success) {
                                messageTool.success(resp.msg);
                                window.location.href = serverHost + "/activity/opener.jhtml";
                            } else {
                                messageTool.error(resp.msg);
                            }
                        }
                    });
                });
                $(".btn-preview").on("click", function () {
                    var title = $(".title").val();
                    var articleContent = UE.getEditor('articleContent').getContent();
                    thiz.contentDetail({title: title, articleContent: articleContent});
                });
                $(".btn-back").on("click", function () {
                    window.location.href = serverHost + "/activity/opener.jhtml";
                });
            },// 编辑器渲染
            renderArticleUeditor: function (data) {
                UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
                UE.Editor.prototype.getActionUrl = function (action) {
                    if (action == 'catchimage') {
                        return serverHost + '/activity/catchImage.json';
                    } else if (action == 'uploadimage') {
                        return serverHost + '/activity/uploadImage.json';
                    } else if (action == 'listimage') {
                        return serverHost + '/activity/listImage.json';
                    } else {
                        return this._bkGetActionUrl.call(this, action);
                    }
                }
                UE.getEditor('articleContent');
            },//预览明细
            contentDetail: function (data) {
                var win = winTool.create({
                    title: data.title,
                    height: 800,
                    width: 800,
                    showCancel: false,
                    okName: "关闭",
                    type: "selector",
                    selector: ".article_detail",
                    okFn: function (win) {
                        win.close();
                    }
                });
                win.find(".contentDetail").html(data.articleContent);
            },
        };
        return profitList;
    });