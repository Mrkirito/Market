define(['jquery',
        'app/common/util',
        'app/common/commonTool',
        'app/common/advanceListTool',
        'app/common/winTool',
        'app/common/dateTool',
        'app/common/messageTool',
        'app/common/imageUploadTool',
        'app/common/imageView'
    ],

    function ($, util, commonTool, listTool, winTool, dateTool, messageTool, imageUploadTool) {
        return {
            /** 列表 **/
            list: function () {
                var thiz = this,
                    list = listTool.create({
                        title: "课程阶段列表",
                        selector: ".data-list",
                        remote: true,
                        //hideColumnBorder: true,
                        //multiSelect: true,
                        /** checkbox **/
                        //showCheckbox: true,
                        width: 640,
                        height: 600,
                        url: serverHost + "/courseStage/queryCourseStageList.json",
                        orderBy: "id desc",
                        columns: [
                            {
                                name: "主键ID",
                                width: "20",
                                sort: true,
                                sortName: "id",
                                fieldName: "id"
                            },
                            {
                                name: "课程阶段名称",
                                fieldName: "name"
                            },
                            {
                                name: "课程阶段描述",
                                fieldName: "description"
                            },
                            {
                                name: "课程阶段icon",
                                width: 50,
                                fieldName: "iconUrl",
                                renderer: function (row, col, data, value) {
                                    if (value != null && value != '') {
                                        return "<a href='" + public_path + "/bxj_web" + value + "' rel='lightbox'><img src='" + public_path + "/bxj_web" + value + "' style='width: 40px;height: 40px' /></a>"
                                    }
                                }
                            },
                            {
                                name: "状态",
                                fieldName: "enableStatus",
                                renderer: function (row, col, data, value) {
                                    if (value == 1) {
                                        return '<span class="label label-sm label-success">启用</span>';
                                    } else if (value == 0) {
                                        return '<span class="label label-sm label-danger">禁用</span>';
                                    }
                                }
                            },
                            {
                                name: "排序",
                                fieldName: "sort",
                                sort: true,
                                sortName: "sort",
                                defaultSort: "asc"
                            },
                            {
                                name: "banner类型",
                                fieldName: "bannerType",
                            },
                            {
                                name: "操作",
                                rightFixed: true,
                                width: "150",
                                renderer: function (rindx, cindex, data) {
                                    var st = '';
                                    st += '<button class="btn btn-danger update">修改</button>';
                                    return st;
                                }
                            }],
                        /** 按钮 **/
                        tbars: [{
                            icon: 'fa fa-plus',
                            name: "新增",
                            rightCode: "add",
                            handler: function (idx, data) {
                                thiz.add();
                            }
                        }],
                        /** 操作 **/
                        classEvents: [
                            {
                                className: "unforbid",
                                rightCode: "unforbid",
                                handler: function (idx, data) {
                                    thiz.forbid(data, 1);
                                }
                            },
                            {
                                className: "forbid",
                                rightCode: "forbid",
                                handler: function (idx, data) {
                                    thiz.forbid(data, 0);
                                }
                            },
                            {
                                className: "update",
                                rightCode: "update",
                                handler: function (idx, data) {
                                    thiz.update(data);
                                }
                            },
                        ],
                        /** 搜索条件 **/
                        paramFn: function () {
                            return thiz.getParams();
                        }
                    });
                thiz.list = list;
                /** 搜索按钮事件 **/
                $(".search-action").on("click", function () {
                    thiz.list.reloadPageData(1);
                });
            },
            /** 搜索条件 **/
            getParams: function () {
                return {
                    name: $('.stageName').val(),
                    enableStatus: $(".enableStatus").val()
                }
            },
            /** 添加或更新参数 **/
            checkForm: function (win) {
                var id = win.find('#id').val(),
                    name = win.find('.stageName').val(),
                    description = win.find('.description').val(),
                    iconUrl = win.find(".iconUrl").data("url"),
                    sort = win.find('.sort').val(),
                    bannerType = win.find('.bannerType').val(),
                    enableStatus = win.find('.enableStatus').val();
                var flag = true;
                if (!name) {
                    messageTool.error("必须输入课程阶段名称!");
                    flag = false;
                }
                if (!description) {
                    messageTool.error("必须输入课程阶段描述!");
                    flag = false;
                }
                if (!iconUrl) {
                    messageTool.error("必须上传课程阶段Icon!");
                    flag = false;
                }
                if (!sort) {
                    messageTool.error("必须输入课程阶段排序!");
                    flag = false;
                }
                if (!bannerType) {
                    messageTool.error("必须输入课程banner类型!");
                    flag = false;
                }
                if (!enableStatus) {
                    messageTool.error("必须选择启用状态");
                    flag = false;
                }
                if (flag) return {
                    id: id,
                    name: name,
                    description: description,
                    iconUrl: iconUrl,
                    sort: sort,
                    bannerType:bannerType,
                    enableStatus: enableStatus
                };
                else return flag;
            },
            /** 新增 **/
            add: function () {
                var thiz = this;
                var win = winTool.create({
                    title: "新增课程阶段",
                    height: 400,
                    width: 800,
                    showCancel: true,
                    okName: "保存",
                    cancelName: "关闭",
                    type: "selector",
                    selector: ".stage_info",
                    okFn: function (win) {
                        var params = thiz.checkForm(win);
                        if (typeof params != 'boolean') {
                            util.request({
                                confirm: true,
                                msg: "确定要添加?",
                                url: serverHost + "/courseStage/saveCourseStage.json",
                                params: params,
                                success: function (resp) {
                                    if (resp.success) {
                                        messageTool.success(resp.msg);
                                        win.close();
                                        thiz.list.reloadPageData();
                                    } else {
                                        messageTool.error(resp.msg);
                                    }
                                }
                            });
                        }
                    }
                });
                this.rendenWin(win);
            },
            /** 更新 **/
            update: function (data) {
                var thiz = this;
                var win = winTool.create({
                    title: "修改课程阶段",
                    height: 400,
                    width: 800,
                    showCancel: true,
                    okName: "保存",
                    cancelName: "关闭",
                    type: "selector",
                    selector: ".stage_info",
                    okFn: function (win) {
                        var params = thiz.checkForm(win);
                        if (typeof params != 'boolean') {
                            util.request({
                                confirm: true,
                                msg: "确定要修改该课程阶段?",
                                url: serverHost + "/courseStage/saveCourseStage.json",
                                params: params,
                                success: function (resp) {
                                    if (resp.success) {
                                        messageTool.success(resp.msg);
                                        win.close();
                                        thiz.list.reloadPageData();
                                    } else {
                                        messageTool.error(resp.msg);
                                    }
                                }
                            });
                        }
                    }
                });
                this.rendenWin(win)
                win.find('#id').val(data.id);
                win.find('.stageName').val(data.name);
                win.find('.description').val(data.description);
                win.find(".iconUrl").attr("src", public_path + "/bxj_web" + data.iconUrl);
                win.find(".iconUrl").data("url", data.iconUrl);
                win.find('.sort').val(data.sort);
                win.find('.bannerType').val(data.bannerType);
                win.find('.enableStatus').val(data.enableStatus);
            },
            /** 上传图片 **/
            rendenWin: function (win) {
                win.find(".infos-images").on("click", function () {
                    var thiz = this;
                    imageUploadTool.init({
                        title: "上传图片",
                        attach: 14,
                        acceptedFiles: ".png,.jpg,.jpeg",
                        maxFiles: 1,
                        callback: function (images) {
                            var img = images.length > 0 ? images[0] : {};
                            $(thiz).parent().find(".picUrl").attr("src", public_path + "/bxj_web" + img.fileName);
                            $(thiz).parent().find(".picUrl").data("url", img.fileName);
                        }
                    });
                });
                win.find(".uploadImg").on("click", ".remove-img", function () {
                    var thiz = this;
                    $(thiz).parent().find(".picUrl").attr("src", "");
                    $(thiz).parent().find(".picUrl").data("url", "");
                });
            },
        };
    });