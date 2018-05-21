define(['jquery',
        'app/common/util',
        'app/common/advanceListTool',
        'app/common/winTool',
        'app/common/dateTool',
        'app/common/messageTool',
        'app/common/imageUploadTool',
        'app/common/commonTool',
        'app/common/imageView'
    ],

    function ($, util, listTool, winTool, dateTool, messageTool, imageUploadTool, commonTool) {
        return {
            /** 列表 **/
            list: function () {
                var thiz = this,
                    list = listTool.create({
                        title: "课程类别列表",
                        selector: ".data-list",
                        remote: true,
                        //hideColumnBorder: true,
                        //multiSelect: true,
                        /** checkbox **/
                        //showCheckbox: true,
                        width: 900,
                        height: 900,
                        url: serverHost + "/courseType/list.json",
                        orderBy: "id desc",
                        columns: [
                            {
                                name: "id",
                                width: "20",
                                sort: true,
                                sortName: "id",
                                fieldName: "id"
                            },
                            {
                                name: "名称",
                                fieldName: "name"
                            },
                            {
                                name: "课程阶段",
                                fieldName: "courseStageName"
                            },
                            {
                                name: "图标",
                                fieldName: "iconUrl",
                                renderer: function (row, col, data, value) {
                                    return "<a href='" + public_path + "/bxj_web" + value + "' rel='lightbox'><img src='" + public_path + "/bxj_web" + value + "' style='width: 40px;height: 40px' /></a>"
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
                            },
                            {
                                name: "操作",
                                rightFixed: true,
                                width: "150",
                                renderer: function (rindx, cindex, data) {
                                    var st = '';
                                    var enableStatus = data.enableStatus;
                                    if (enableStatus) st += '<a class="red forbid" href="javascript:void(0);" title="禁用"> <i class="fa fa-arrow-down bigger-140 "></i> </a>&nbsp;';
                                    else st += '<a class="green unforbid" href="javascript:void(0);" title="启用"> <i class="fa fa-arrow-up bigger-140 "></i> </a>&nbsp;';
                                    st += '<a class="green update" href="javascript:void(0);" title="编辑"> <i class="fa fa-pencil bigger-140 "></i> </a>&nbsp;';
                                    return st;
                                }
                            }],

                        /** 按钮 **/
                        tbars: [{
                            icon: 'fa fa-plus',
                            name: "添加新类别",
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
                            }
                        ],
                        /** 搜索条件 **/
                        paramFn: function () {
                            return thiz.getParams();
                        }
                    });
                thiz.list = list;

                /** 课程阶段下拉框 **/
                commonTool.renderCourseStage({
                    msg : "课程阶段",
                    selector : $("#courseStageId"),
                    width : "100%",
                    paramFn : function() {
                        return {
                            permission: true
                        };
                    },
                    listeners : {
                        change : function() {

                        }
                    }
                });

                /** 搜索按钮事件 **/
                $(".search-action").on("click", function () {
                    thiz.list.reloadPageData(1);
                });
            },

            /** 搜索条件 **/
            getParams: function () {
                return {
                    courseStageId: $('#courseStageId').val(),
                    enableStatus: $(".enableStatus").val()
                }
            },

            /** 添加或更新参数 **/
            checkForm: function (win) {
                var id = win.find('#id').val(),
                    courseStageId = win.find('.courseStageId').select2("val"),
                    sort = win.find('.sort').val(),
                    name = win.find('.name').val(),
                    iconUrl = win.find(".picUrl").data("url");

                var flag = true;
                if (!name) {
                    messageTool.error("必须输入名称!");
                    flag = false;
                }
                if (!courseStageId) {
                    messageTool.error("必须选择课程阶段!");
                    flag = false;
                }

                if (!sort) {
                    messageTool.error("必须输入排序!");
                    flag = false;
                }

                if (!iconUrl) {
                    messageTool.error("请上传图标");
                    flag = false;
                }

                if (flag) return {
                    id: id,
                    courseStageId: courseStageId,
                    sort: sort,
                    name: name,
                    iconUrl: iconUrl

                };
                else return flag;
            },

            /** 新增 **/
            add: function () {
                var thiz = this;
                var win = winTool.create({
                    title: "添加新类别",
                    height: 400,
                    width: 600,
                    showCancel: true,
                    okName: "保存",
                    cancelName: "关闭",
                    type: "selector",
                    selector: "#dialog",
                    okFn: function (win) {
                        var params = thiz.checkForm(win);
                        if (typeof params != 'boolean') {
                            util.request({
                                confirm: true,
                                msg: "确定要添加?",
                                url: serverHost + "/courseType/addOrUpdate.json",
                                params: params,
                                success: function (resp) {
                                    if (resp.success) {
                                        messageTool.success("添加成功");
                                        win.close();
                                        thiz.list.reloadPageData();
                                    } else {
                                        messageTool.error("添加失败");
                                    }
                                }
                            });
                        }
                    }
                });
                thiz.rendenWin(win);
                /** 课程阶段下拉框 **/
                commonTool.renderCourseStage({
                    msg : "课程阶段",
                    selector : win.find(".courseStageId"),
                    width : "100%",
                    paramFn : function() {
                        return {
                            permission: true
                        };
                    },
                    listeners : {
                        change : function() {

                        }
                    }
                });
            },

            /** 更新 **/
            update: function (data) {
                var thiz = this,
                    id = data.id;
                var vals={id:data.courseStageId, text:data.courseStageName};
                util.ajax({
                    url: serverHost + "/courseType/detail.json",
                    params: {
                        id: id
                    },
                    success: function (resp) {
                        if (resp.success) {
                            var detail = resp.model;
                            var win = winTool.create({
                                title: "更新类别信息",
                                height: 400,
                                width: 600,
                                showCancel: true,
                                okName: "保存",
                                cancelName: "关闭",
                                type: "selector",
                                selector: "#dialog",
                                okFn: function (win) {
                                    var params = thiz.checkForm(win);
                                    if (typeof params != 'boolean') {
                                        util.request({
                                            confirm: true,
                                            msg: "确定要更新?",
                                            url: serverHost + "/courseType/addOrUpdate.json",
                                            params: params,
                                            success: function (resp) {
                                                if (resp.success) {
                                                    messageTool.success("更新成功");
                                                    win.close();
                                                    thiz.list.reloadPageData();
                                                } else {
                                                    messageTool.error("更新失败");
                                                }
                                            }
                                        });
                                    }
                                }
                            });

                            thiz.rendenWin(win);
                            /** 课程阶段下拉框 **/
                            commonTool.renderCourseStage({
                                msg : "课程阶段",
                                selector : win.find(".courseStageId"),
                                val: vals,
                                width : "100%",
                                paramFn : function() {
                                },
                                listeners : {
                                    change : function() {
                                    }
                                }
                            });
                            /** 赋值 **/
                            win.find("#id").val(detail.id);
                            win.find(".sort").val(detail.sort);
                            win.find(".name").val(detail.name);
                            win.find(".picUrl").attr("src", $('#show_path').val() + detail.iconUrl);
                            win.find(".picUrl").attr("data-url", detail.iconUrl);
                        } else {
                            messageTool.error("更新失败!");
                        }
                    }
                });
            },

            /** 禁用启用 **/
            forbid: function (data, status) {
                var thiz = this;
                var id = data.id;
                var msg = status == 1 ? '确定要启用？' : '确定要禁用';
                util.request({
                    confirm: true,
                    msg: msg,
                    url: serverHost + "/courseType/forbid.json",
                    params: {
                        id: id,
                        status: status
                    },
                    success: function (resp) {
                        if (resp.success) {
                            messageTool.success("操作成功!");
                            thiz.list.reloadPageData();
                        } else {
                            messageTool.error("操作失败！");
                            thiz.list.reloadPageData();
                        }
                    }
                });
            },

            /** 上传图片 **/
            rendenWin: function (win) {
                /** 商品图片 **/
                win.find(".infos-images").on("click", function () {
                    imageUploadTool.init({
                        title: "上传图片",
                        attach: 11,
                        acceptedFiles: ".png,.jpg,.jpeg",
                        maxFiles: 1, //图片个数
                        extData: {
                            //size: "100x100"
                        },
                        callback: function (images) {
                            var img = images.length > 0 ? images[0] : {};
                            $(".picUrl").attr("src", img.fileUrl);
                            $(".picUrl").data("url", img.fileName);
                        }
                    });
                });
                // 删除 图片
                win.find(".uploadImg").on("click", ".remove-img", function () {
                    $(".picUrl").attr("src", "");
                    $(".picUrl").data("url", "");
                });
            },
        };
    });