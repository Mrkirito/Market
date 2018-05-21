define(['jquery', 'util',
        'app/common/winTool',
        'app/common/listTool',
        'app/common/messageTool',
        'app/common/formTool',
        'app/right/funcTool'],
    function ($, util, winTool, listTool, messageTool, formTool, funcTool) {

        var funcFields = [{
                cls: 'id'
            }, {
                cls: 'resourceCode'
            }, {
                cls: 'funcCode',
                required: true
            }, {
                cls: 'funcName',
                required: true
            }, {
                cls: 'sort',
                dataType: 'int',
                required: true
            }],
            funcForm = formTool.create({
                fields: funcFields
            });

    return {
        showFuncListWin: function (resourceData) {
            var thiz = this,
                win = winTool.create({
                    title: '按钮权限(' + resourceData.resourceName + ')',
                    showOk: false,
                    cancelName: "关闭",
                    selector: ".func-list-tmpl",
                    type: 'selector',
                    width: 900,
                    height: 400
                });

            thiz.createListTool(win, resourceData);

            $(win.find(".search-function")).on("click", function () {
                thiz.funcList.loadPageData(1);
            });
        },

        createListTool: function (win, resourceData) {
            var thiz = this,
                funcListSelector = win.find(".function-list"),
                funcToolbar = win.find(".function-toolbar"),
                url = serverHost + "/func/queryFunctionPageData.json?resourceCode=" + resourceData.resourceCode,
                getParams = function () {
                    return {
                        funcCode: win.find(".funcCode").val(),
                        funcName: win.find(".funcName").val(),
                        status: win.find(".status").val()
                    }
                },
                itemListTool = listTool.create({
                    selector: funcListSelector,
                    toolbarSelector: funcToolbar,
                    remote: true,
                    url: url,
                    extData: resourceData,
                    columns: [{
                            name: "编码",
                            fieldName: "funcCode"
                        },
                        {
                            name: "名称",
                            fieldName: "funcName"
                        },
                        {
                            name: "排序",
                            fieldName: "sort",
                            width: 60
                        },
                        {
                            name: "状态",
                            fieldName: "status",
                            width: 70,
                            renderer: function (row, col, data, value) {
                                if (value == 1) {
                                    return '<span class="label label-sm label-success">有效 </span>';
                                } else {
                                    return '<span class="label label-sm label-danger">无效 </span>';
                                }
                            }
                        },
                        {
                            name: "管理",
                            width: 80,
                            renderer: function (rindx, cindex, data) {
                                return '<div class="    action-buttons"> ' +
                                    '<a class="green update-info" href="javacript:void(0);" title="编辑"> <i class="fa fa-pencil bigger-130 "></i> </a> ' +
                                    '<a class="red delete-info" href="javacript:void(0);" title="删除" data-rel="25"> <i class="fa fa-trash-o bigger-130"></i> </a> </div>';
                            }
                        }],
                    tbars: [{
                        icon: 'fa fa-plus',
                        name: "新增",
                        //rightCode: "addFunc",
                        handler: function (e, btn, tool) {
                            thiz.showAddFuncWin();
                        }
                    }, {
                        icon: 'fa fa-plus',
                        name: "增加常用功能",
                        //rightCode: "addNormalFunc",
                        handler: function (tool) {
                            funcTool.show(function (datas, win) {
                                thiz.saveFuncList(datas, win);
                            });
                        }
                    }, {
                        icon: 'fa fa-trash-o',
                        name: "删除",
                        //rightCode: "deleteFunc",
                        handler: function (e, btn, tool) {
                            var selected = tool.getSelected()||[],
                                idList = [];
                            if(selected.length == 0) {
                                messageTool.error("请选择要删除数据");
                                return ;
                            }
                            $(selected).each(function (idx, data) {
                                idList.push(data.id);
                            });
                            thiz.deleteFuncton(idList);
                        }
                    }],
                    classEvents: [{
                        className: 'update-info',
                        //rightCode: "updateFunc",
                        handler: function (rowIndex, data) {
                            thiz.showUpdateFuncWin(data);
                        }
                    }, {
                        className: 'delete-info',
                        //rightCode: "deleteFunc",
                        handler: function (idx, data) {
                            thiz.deleteFuncton([data.id]);
                        }
                    }],
                    paramFn: getParams
                });

            thiz.funcList = itemListTool;
        },

        createWin: function (title) {
            var thiz = this,
                win = winTool.create({
                    title: title,
                    okName: "保存",
                    cancelName: "关闭",
                    type: 'selector',
                    selector: ".edit-func-tmpl",
                    okFn: function (win) {
                        thiz.saveFuncData(win);
                    }
                });
            return win;
        },

        showAddFuncWin: function () {
            var thiz = this,
                win = thiz.createWin("新增"),
                resourceData = thiz.funcList.getExtData();
            win.find(".resourceCode").val(resourceData.resourceCode);
        },

        showUpdateFuncWin: function (data) {
            var thiz = this,
                win = thiz.createWin("修改");

            funcForm.serializable(win.$content, data);
        },

        saveFuncData: function (win) {
            var thiz = this;
            funcForm.submit({
                selector: win.$content,
                url: serverHost + "/func/saveFunction.json",
                paramName: "function",
                success: function () {
                    win.close();
                    messageTool.success("保存成功", function () {
                        thiz.funcList.loadPageData();
                    });
                }
            });
        },

        saveFuncList: function (dataList, win) {
            var thiz = this;
            if(dataList.length ==0) {
                messageTool.error("请选择常用功能");
            } else {
                var resourceCode = thiz.funcList.getExtData().resourceCode;
                $(dataList).each(function (idx, data) {
                    data.resourceCode = resourceCode;
                });
                util.ajax({
                    url: serverHost + "/func/saveFunctionList.json",
                    params: {
                        functionList: JSON.stringify(dataList) + ""
                    },
                    success: function (resp) {
                        if(resp.success == true) {
                            messageTool.success("保存成功", function () {
                                win.close();
                                thiz.funcList.loadPageData();
                            });
                        } else {
                            messageTool.error(resp.msg||"保存失败");
                        }
                    }
                });
            }
        },

        deleteFuncton: function (idList) {
            var thiz = this;
            messageTool.confirm("确定要删除？", function (flag) {
                if(flag) {
                    util.ajax({
                        url: serverHost + "/func/deleteFunction.json",
                        params: {
                            idList: idList
                        },
                        success: function (resp) {
                            if(resp.success == true) {
                                messageTool.success("删除成功", function () {
                                    thiz.funcList.loadPageData();
                                });
                            } else {
                                messageTool.error(resp.msg||"删除失败");
                            }
                        }
                    });
                }
            });
        }

    };
});