define(['jquery',
    'app/common/util',
    'app/common/commonTool',
    'app/common/advanceListTool',
    'app/common/winTool',
    'app/common/dateTool',
    'app/common/messageTool'
    ],

    function ($, util, commonTool, listTool, winTool, dateTool, messageTool) {
        return {
            /** 列表 **/
            list: function () {
                var thiz = this,
                    list = listTool.create({
                        title: "课程试题列表",
                        selector: ".data-list",
                        remote: true,
                        //hideColumnBorder: true,
                        //multiSelect: true,
                        /** checkbox **/
                        //showCheckbox: true,
                        width: 640,
                        height: 600,
                        url: serverHost + "/courseQuestion/list.json",
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
                                name: "课程",
                                fieldName: "courseTitle"
                            },
                            {
                                name: "标题",
                                fieldName: "questionTitle"
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
                                    return st;
                                }
                            }],

                        /** 按钮 **/
                        tbars: [{
                            icon: 'fa fa-plus',
                            name: "添加新试题",
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

                /** 类别下拉框 **/
                commonTool.renderCourse({
                    msg : "课程",
                    selector : $(".courseIdSelect"),
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
                    $("#questionTitle").val("");
                    $("#courseIdSelect").val("");
                    $('#select2-chosen-1').html('');
                });
            },

            /** 搜索条件 **/
            getParams: function () {
                return {
                    courseId: $('#courseIdSelect').val(),
                    questionTitle: $("#questionTitle").val()
                }
            },

            /** 添加或更新参数 **/
            checkForm: function (win) {
                var id = win.find('#id').val(),
                    courseId = win.find('.courseId').select2("val"),
                    questionTitle = win.find('.questionTitle').val(),
                    questionAnswer1 = win.find('.questionAnswer1').val(),
                    questionAnswer2 = win.find('.questionAnswer2').val(),
                    questionAnswer3 = win.find('.questionAnswer3').val(),
                    questionAnswer4 = win.find('.questionAnswer4').val(),
                    questionAnswer5 = win.find('.questionAnswer5').val(),
                    questionAnswer6 = win.find('.questionAnswer6').val(),
                    questionType = win.find('.questionType').val(),
                    sort = win.find('.sort').val(),
                    correctAnswer = '';

                if('1' ==questionType) {
                    questionAnswer5 = '';
                    questionAnswer6 = '';
                    correctAnswer = $('.correctAnswerDiv1 input[name=correctAnswer1]').filter(":checked").val();
                } else if('2' == questionType) {
                    $('.correctAnswerDiv2 input[name=correctAnswer2]').each(function(index, item) {
                        if(item.checked && 'A'==item.value && correctAnswer.indexOf('A')<0) correctAnswer += 'A';
                        if(item.checked && 'B'==item.value && correctAnswer.indexOf('B')<0) correctAnswer += 'B';
                        if(item.checked && 'C'==item.value && correctAnswer.indexOf('C')<0) correctAnswer += 'C';
                        if(item.checked && 'D'==item.value && correctAnswer.indexOf('D')<0) correctAnswer += 'D';
                        if(item.checked && 'E'==item.value && correctAnswer.indexOf('E')<0) correctAnswer += 'E';
                        if(item.checked && 'F'==item.value && correctAnswer.indexOf('F')<0) correctAnswer += 'F';
                    });
                } else if('3' == questionType) {
                    questionAnswer3 = '';
                    questionAnswer4 = '';
                    questionAnswer5 = '';
                    questionAnswer6 = '';
                    correctAnswer = $('.correctAnswerDiv3 input[name=correctAnswer3]').filter(":checked").val();
                }

                var flag = true;
                if (!courseId) {
                    messageTool.error("必须选择课程!");
                    flag = false;
                }
                if (!questionTitle) {
                    messageTool.error("必须输入标题!");
                    flag = false;
                }
                if (!questionType) {
                    messageTool.error("必须选择试题类型!");
                    flag = false;
                }
                if (!correctAnswer) {
                    messageTool.error("必须输入正确答案!");
                    flag = false;
                }

                if (flag) return {
                    id: id,
                    courseId: courseId,
                    questionTitle: questionTitle,
                    questionType: questionType,
                    questionAnswer1: questionAnswer1,
                    questionAnswer2: questionAnswer2,
                    questionAnswer3: questionAnswer3,
                    questionAnswer4: questionAnswer4,
                    questionAnswer5: questionAnswer5,
                    questionAnswer6: questionAnswer6,
                    correctAnswer: correctAnswer,
                    sort: sort
                };
                else return flag;
            },

            /** 新增 **/
            add: function () {
                var thiz = this;
                var win = winTool.create({
                    title: "添加新试题",
                    height: 700,
                    width: 800,
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
                                url: serverHost + "/courseQuestion/addOrUpdate.json",
                                params: params,
                                success: function (resp) {
                                    $('.modal-scrollable').remove();
                                    $('.modal-backdrop').remove();
                                    $('.message-box').remove();
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
                    },
                    closeFn: function (win) {
                        $('.modal-scrollable').remove();
                        $('.modal-backdrop').remove();
                    }
                });
                $("input[name^='correctAnswer']").attr("checked",false);
                $('.questionType').change(function() {
                    var val = $(this).val();
                    if('1' == val) {
                        $('.correctAnswerDiv1').show();
                        $('.correctAnswerDiv2').hide();
                        $('.correctAnswerDiv3').hide();
                        $('.answerdiv3').show();
                        $('.answerdiv4').show();
                        $('.answerdiv5').hide();
                        $('.answerdiv6').hide();
                    } else if('2' == val) {
                        $('.correctAnswerDiv1').hide();
                        $('.correctAnswerDiv2').show();
                        $('.correctAnswerDiv3').hide();
                        $('.answerdiv3').show();
                        $('.answerdiv4').show();
                        $('.answerdiv5').show();
                        $('.answerdiv6').show();
                    } else if('3' == val) {
                        $('.correctAnswerDiv1').hide();
                        $('.correctAnswerDiv2').hide();
                        $('.correctAnswerDiv3').show();
                        $('.answerdiv3').hide();
                        $('.answerdiv4').hide();
                        $('.answerdiv5').hide();
                        $('.answerdiv6').hide();
                    }
                });

                /** 类别下拉框 **/
                commonTool.renderCourse({
                    msg : "选择课程",
                    selector : win.find(".courseId"),
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
                    url: serverHost + "/courseQuestion/detail.json",
                    params: {
                        id: id
                    },
                    success: function (resp) {
                        if (resp.success) {
                            var detail = resp.model;
                            var vals={id:detail.courseId, text:detail.courseTitle};
                            var win = winTool.create({
                                title: "更新试题信息",
                                height: 700,
                                width: 800,
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
                                            url: serverHost + "/courseQuestion/addOrUpdate.json",
                                            params: params,
                                            success: function (resp) {
                                                $('.modal-scrollable').remove();
                                                $('.modal-backdrop').remove();
                                                $('.message-box').remove();
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
                                },
                                closeFn: function (win) {
                                    $('.modal-scrollable').remove();
                                    $('.modal-backdrop').remove();
                                }
                            });

                            $('.questionType').change(function() {
                                var val = $(this).val();
                                if('1' == val) {
                                    $('.correctAnswerDiv1').show();
                                    $('.correctAnswerDiv2').hide();
                                    $('.correctAnswerDiv3').hide();
                                    $('.answerdiv3').show();
                                    $('.answerdiv4').show();
                                    $('.answerdiv5').hide();
                                    $('.answerdiv6').hide();
                                } else if('2' == val) {
                                    $('.correctAnswerDiv1').hide();
                                    $('.correctAnswerDiv2').show();
                                    $('.correctAnswerDiv3').hide();
                                    $('.answerdiv3').show();
                                    $('.answerdiv4').show();
                                    $('.answerdiv5').show();
                                    $('.answerdiv6').show();
                                } else if('3' == val) {
                                    $('.correctAnswerDiv1').hide();
                                    $('.correctAnswerDiv2').hide();
                                    $('.correctAnswerDiv3').show();
                                    $('.answerdiv3').hide();
                                    $('.answerdiv4').hide();
                                    $('.answerdiv5').hide();
                                    $('.answerdiv6').hide();
                                }
                            });

                            /** 类别下拉框 **/
                            commonTool.renderCourse({
                                msg : "选择课程",
                                selector : win.find(".courseId"),
                                val: vals,
                                width : "100%",
                                paramFn : function() {
                                },
                                listeners : {
                                    change : function() {
                                    }
                                }
                            });

                            $("input[name^='correctAnswer']").attr("checked",false);
                            /** 赋值 **/
                            win.find("#id").val(detail.id);
                            win.find(".questionTitle").val(detail.questionTitle);
                            win.find(".questionType").val(detail.questionType);
                            win.find(".questionAnswer1").val(detail.questionAnswer1);
                            win.find(".questionAnswer2").val(detail.questionAnswer2);
                            win.find(".questionAnswer3").val(detail.questionAnswer3);
                            win.find(".questionAnswer4").val(detail.questionAnswer4);
                            win.find(".questionAnswer5").val(detail.questionAnswer5);
                            win.find(".questionAnswer6").val(detail.questionAnswer6);
                            win.find(".sort").val(detail.sort);

                            var questionType = detail.questionType;
                            if('1' == questionType) {
                                $(".modal-scrollable .correctAnswerDiv1 input[name='correctAnswer1'][value='"+detail.correctAnswer+"']").prop("checked",true);
                                $('.correctAnswerDiv1').show();
                                $('.correctAnswerDiv2').hide();
                                $('.correctAnswerDiv3').hide();
                                $('.answerdiv3').show();
                                $('.answerdiv4').show();
                                $('.answerdiv5').hide();
                                $('.answerdiv6').hide();
                            } else if('2' == questionType) {
                                var correctAnswer = detail.correctAnswer;
                                for(var i=0;i<correctAnswer.length; i++) {
                                    $('.modal-scrollable .correctAnswerDiv2 input[name=correctAnswer2]').each(function(index, item) {
                                        if(item.value == correctAnswer.charAt(i)) {
                                            item.checked = true;
                                        }
                                    });
                                }
                                $('.correctAnswerDiv1').hide();
                                $('.correctAnswerDiv2').show();
                                $('.correctAnswerDiv3').hide();
                                $('.answerdiv3').show();
                                $('.answerdiv4').show();
                                $('.answerdiv5').show();
                                $('.answerdiv6').show();
                            } else if('3' == questionType) {
                                $(".modal-scrollable .correctAnswerDiv3 input[name='correctAnswer3'][value='"+detail.correctAnswer+"']").prop("checked",true);
                                $('.correctAnswerDiv1').hide();
                                $('.correctAnswerDiv2').hide();
                                $('.correctAnswerDiv3').show();
                                $('.answerdiv3').hide();
                                $('.answerdiv4').hide();
                                $('.answerdiv5').hide();
                                $('.answerdiv6').hide();
                            }
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
                    url: serverHost + "/courseQuestion/forbid.json",
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
            }
        };
    });