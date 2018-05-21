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
                        title: "课程日志列表",
                        selector: ".data-list",
                        remote: true,
                        //hideColumnBorder: true,
                        //multiSelect: true,
                        /** checkbox **/
                        //showCheckbox: true,
                        width: 640,
                        height: 600,
                        url: serverHost + "/courseAccess/queryCourseAccessList.json",
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
                                fieldName: "stageName"
                            },
                            {
                                name: "课程分类名称",
                                fieldName: "typeName"
                            },
                            {
                                name: "课程名称",
                                fieldName: "courseTitle"
                            },
                            {
                                name: "用户名",
                                fieldName: "userName"
                            },
                            {
                                name: "手机号码",
                                fieldName: "phone"
                            },
                            {
                                name: "类型",
                                fieldName: "type",
                                renderer: function (row, col, data, value) {
                                    if (value == 1) {
                                        return '<span class="label label-sm label-info">学习</span>';
                                    } else if (value == 2) {
                                        return '<span class="label label-sm label-pink">测试</span>';
                                    } else if (value == 3) {
                                        return '<span class="label label-sm label-success">通关</span>';
                                    }
                                }
                            },
                            {
                                name: "创建日期",
                                fieldName: "createTime",
                                renderer: function (row, col, data, value) {
                                    return util.dateFormat2(value, 'YYYY-MM-DD HH:mm:ss');
                                }
                            }],
                        /** 按钮 **/
                        tbars: [],
                        /** 操作 **/
                        classEvents: [],
                        /** 搜索条件 **/
                        paramFn: function () {
                            return thiz.getParams();
                        }
                    });
                thiz.list = list;

                /** 课程阶段下拉框 **/
                commonTool.renderCourseStage({
                    msg : "课程阶段",
                    selector : $("#stageId"),
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

                /** 课程阶段下拉框 **/
                commonTool.renderCourseType({
                    msg : "课程阶段",
                    selector : $("#typeId"),
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
                    userName: $('.userName').val(),
                    phone: $(".phone").val(),
                    type: $(".type").val(),
                    stageId: $('#stageId').val(),
                    typeId: $('#typeId').val()
                }
            }
        };
    });