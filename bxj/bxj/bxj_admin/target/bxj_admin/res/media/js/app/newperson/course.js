define(['jquery',
    'app/common/util',
    'app/common/commonTool',
    'app/common/advanceListTool',
    'app/common/winTool',
    'app/common/dateTool',
    'app/common/messageTool',
    'app/common/imageUploadTool',
    'app/newperson/course_ppt'
    ],

    function ($, util, commonTool, listTool, winTool, dateTool, messageTool, imageUploadTool, ppt) {
        return {
            /** 列表 **/
            list: function () {
                var thiz = this,
                    list = listTool.create({
                        title: "课程列表",
                        selector: ".data-list",
                        remote: true,
                        //hideColumnBorder: true,
                        //multiSelect: true,
                        /** checkbox **/
                        //showCheckbox: true,
                        width: 640,
                        height: 600,
                        url: serverHost + "/course/list.json",
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
                                name: "类别",
                                fieldName: "courseTypeName"
                            },
                            {
                                name: "标题",
                                fieldName: "courseTitle"
                            },
                            {
                                name: "副标题",
                                fieldName: "courseSubtitle"
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
                                name: "学习时长",
                                fieldName: "suggestStudyTime"
                            },
                            {
                                name: "学习人数",
                                fieldName: "studyPerson"
                            },
                            {
                                name: "测试人数",
                                fieldName: "testPerson"
                            },
                            {
                                name: "通关人数",
                                fieldName: "passPerson"
                            },
                            {
                                name: "难度星级",
                                fieldName: "difficultyStar"
                            },
                            /*{
                                name: "图标",
                                fieldName: "iconUrl",
                                renderer: function(row, col, data, value) {
                                    return '<a href="' + value + '" rel="lightbox"><img src="' + $('#show_path').val() + value + '"/></a>';
                                }
                            },*/
							{
                                name: "排序",
                                fieldName: "sort",
                                sort: true,
                                sortName: "sort",
                                fieldName: "sort"
                            },
                            {
                                name: "操作",
                                rightFixed: true,
                                width: "150",
                                renderer: function (rindx, cindex, data) {
                                    var st = '';
                                    var enableStatus = data.enableStatus;
                                    if(enableStatus) st += '<a class="red forbid" href="javascript:void(0);" title="禁用"> <i class="fa fa-arrow-down bigger-140 "></i> </a>&nbsp;';
                                    else st += '<a class="green unforbid" href="javascript:void(0);" title="启用"> <i class="fa fa-arrow-up bigger-140 "></i> </a>&nbsp;';
                                    st += '<a class="green update" href="javascript:void(0);" title="编辑"> <i class="fa fa-pencil bigger-140 "></i> </a>&nbsp;';
                                    st += '<a class="green ppt" href="javascript:void(0);" title="ppt"> <i class="fa fa-bars bigger-140 "></i> </a>&nbsp;';
                                    return st;
                                }
                            }],

                        /** 按钮 **/
                        tbars: [{
                            icon: 'fa fa-plus',
                            name: "添加新课程",
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
                            {
                                className: "ppt",
                                rightCode: "ppt",
                                handler: function (idx, data) {
                                    ppt.pptListWin(data);
                                }
                            }
                        ],

                        /** 搜索条件 **/
                        paramFn: function () {
                            return thiz.getParams();
                        }
                    });
                thiz.list = list;

                /** 类别下拉框 **/
                commonTool.renderCourseType({
                    msg : "类别",
                    selector : $(".courseTypeSelect"),
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
                $(".search-data").on("click", function () {
                    thiz.list.reloadPageData(1);
                });

                /** 重置按钮 **/
                $(".reset-data").on("click", function () {
                    $('.courseTypeSelect').val('');
                    $("#courseTitle").val("");
                    $('#select2-chosen-1').html('');
                });
            },

            /** 搜索条件 **/
            getParams: function () {
                return {
                    courseTypeId: $('#courseTypeSelect').val(),
                    courseTitle: $("#courseTitle").val()
                }
            },

            /** 添加或更新参数 **/
            checkForm: function (win) {
                var id = win.find('#id').val(),
                    courseTypeId = win.find('.courseTypeId').select2("val"),
                    courseTitle = win.find('.courseTitle').val(),
                    courseSubtitle = win.find('.courseSubtitle').val(),
                    suggestStudyTime = win.find('.suggestStudyTime').val(),
                    virtualStudyPerson = win.find('.virtualStudyPerson').val(),
                    virtualTestPerson = win.find('.virtualTestPerson').val(),
                    virtualPassPerson = win.find('.virtualPassPerson').val(),
                    difficultyStar = win.find('.difficultyStar').val(),
                    sort = win.find('.sort').val(),
                    iconUrl = win.find(".picUrl").data("url");

                var flag = true;
                if (!courseTypeId) {
                    messageTool.error("必须分类!");
                    flag = false;
                }
                if (!courseTitle) {
                    messageTool.error("必须输入标题!");
                    flag = false;
                }
                if (!courseSubtitle) {
                    messageTool.error("必须输入副标题!");
                    flag = false;
                }
                if (!suggestStudyTime) {
                    messageTool.error("必须输入建议学习时长!");
                    flag = false;
                }
                if (!iconUrl) {
                    messageTool.error("请上传图标");
                    flag = false;
                }

                if (flag) return {
                    id: id,
                    courseTypeId: courseTypeId,
                    courseTitle: courseTitle,
                    courseSubtitle: courseSubtitle,
                    suggestStudyTime: suggestStudyTime,
                    virtualStudyPerson: virtualStudyPerson,
                    virtualTestPerson: virtualTestPerson,
                    virtualPassPerson: virtualPassPerson,
                    difficultyStar: difficultyStar,
                    sort: sort,
                    iconUrl: iconUrl

                };
                else return flag;
            },

            /** 新增 **/
            add: function () {
                var thiz = this;
                var win = winTool.create({
                    title: "添加新课程",
                    height: 750,
                    width: 700,
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
                                url: serverHost + "/course/addOrUpdate.json",
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

                /** 类别下拉框 **/
                commonTool.renderCourseType({
                    msg : "选择分类的标签",
                    selector : win.find(".courseTypeId"),
                    width : "100%",
                    paramFn : function() {
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
                util.ajax({
                    url: serverHost + "/course/detail.json",
                    params: {
                        id: id
                    },
                    success: function (resp) {
                        if (resp.success) {
                            var detail = resp.model;
                            var vals={id:detail.courseTypeId, text:detail.courseTypeName};
                            var win = winTool.create({
                                title: "更新课程信息",
                                height: 750,
                                width: 700,
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
                                            url: serverHost + "/course/addOrUpdate.json",
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
                            /** 类别下拉框 **/
                            commonTool.renderCourseType({
                                msg : "选择分类的标签",
                                selector : win.find(".courseTypeId"),
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
                            $(".courseType").val(detail.courseTypeId);
                            win.find(".courseTitle").val(detail.courseTitle);
                            win.find(".courseSubtitle").val(detail.courseSubtitle);
                            win.find(".suggestStudyTime").val(detail.suggestStudyTime);
                            win.find(".virtualStudyPerson").val(detail.virtualStudyPerson);
                            win.find(".virtualTestPerson").val(detail.virtualTestPerson);
                            win.find(".virtualPassPerson").val(detail.virtualPassPerson);
                            win.find(".difficultyStar").val(detail.difficultyStar);
                            win.find(".sort").val(detail.sort);
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
                var msg = status==1?'确定要启用？':'确定要禁用';
                util.request({
                    confirm: true,
                    msg: msg,
                    url: serverHost + "/course/forbid.json",
                    params: {
                        id: id,
                        status : status
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
                            $(".picUrl").attr("data-url", img.fileName);
                        }
                    });
                });
                // 删除 图片
                win.find(".uploadImg").on("click", ".remove-img", function () {
                    $(".picUrl").attr("src", "");
                    $(".picUrl").attr("data-url", "");
                });
            }
        };
    });