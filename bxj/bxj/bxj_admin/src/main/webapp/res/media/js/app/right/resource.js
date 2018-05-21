define(['jquery',
        'app/common/listTool',
        'app/common/winTool',
        'app/common/formTool',
        'app/common/util',
        'app/common/messageTool',
        'app/common/treeTool',
        'app/common/treeWinTool',
        'app/right/func',
        'app/common/IconTool',
        'app/common/selectTool'],
    function ($, listTool, winTool, formTool, util,
              messageTool, treeTool, treeWin, func,
              iconTool, selectTool) {

        var resourceFields = [{
                cls: 'id'
            }, {
                cls: 'parentId'
            }, {
                cls: 'resourceCode',
                required: true
            }, {
                cls: 'resourceName',
                required: true
            }, {
                cls: 'parentName'
            }, {
                cls: 'resourceUrl'
            }, {
                cls: 'resourceIcon'
            }, {
                cls: 'menuShow',
                type: 'checkbox'
            }, {
                cls: 'permissionControl',
                type: 'checkbox'
            }, {
                cls: 'sort',
                dataType: 'int',
                required: true
            }, {
                cls: 'navCode',
                type: 'select2',
                idKey: 'navCode',
                textKey: 'navName',
                required: true
            }],
            resourceForm = formTool.create({
                fields: resourceFields
            });

        return {
            list: function () {
                var thiz = this;

                thiz.loadTree();

                var list = listTool.create({
                    selector: ".data-list",
                    remote: true,
                    toolbarSelector: ".resource-toolbar",
                    url: serverHost + "/right/queryResourceList.json",
                    showCheckbox: true,
                    columns: [
                        {
                            name: "所属系统",
                            fieldName: "navName",
                            width: 80
                        }, {
                            name: "权限编码",
                            fieldName: "resourceCode"
                        },
                        {
                            name: "权限名称",
                            fieldName: "resourceName",
                            renderer: function (rowIndex, colIndex, data) {
                                return '<i class="' + (data.resourceIcon||"") + '"></i>&nbsp;' + data.resourceName;
                            }
                        },
                        {
                            name: "权限url",
                            fieldName: "resourceUrl",
                            renderer: function (row, col, data, value) {
                                if(value) {
                                    var url = '<a target="_blank" href="' + serverHost + value + '">' + value + '</a>';
                                    return url;
                                }
                            }
                        },
                        {
                            name: "权限控制",
                            fieldName: "permissionControl",
                            width: 80,
                            renderer: function (rowIndex, colIndex, data) {
                                return data.permissionControl == 1?"是":"否";
                            }
                        },
                        {
                            name: "是否菜单",
                            fieldName: "menuShow",
                            width: 80,
                            renderer: function (rowIndex, colIndex, data) {
                                return data.menuShow==1?"是":"否";
                            }
                        },
                        {
                            name: "排序",
                            fieldName: "sort",
                            width: 60
                        },
                        {
                            name: "状态",
                            fieldName: "status",
                            width: 60,
                            renderer: function (row, col, data, value) {
                                if(value == 1) {
                                    return '<span class="label label-sm label-success">有效 </span>';
                                } else {
                                    return '<span class="label label-sm label-danger">无效 </span>';
                                }
                            }
                        },

                        {
                            name: "管理",
                            width: 100,
                            renderer: function (rindx, cindex, data) {
                                return '<div class="    action-buttons"> ' +
                                    '<a class="green update-info" href="javascript:void(0);" title="编辑"> <i class="fa fa-pencil bigger-140 "></i> </a> ' +
                                    '<a class="red delete-info" href="javascript:void(0);" title="删除" > <i class="fa fa-trash-o bigger-140"></i> </a> ' +
                                    '<a class="blue manager-function" href="javascript:void(0);" title="按钮权限" > <i class="fa fa-shield bigger-140"></i> </a> ' +
                                    '</div>';
                            }
                        }],
                    tbars: [{
                        icon: 'fa fa-plus',
                        name: "新增",
                        rightCode: "add",
                        handler: function (tool) {
                            thiz.showAddWin();
                        }
                    }, {
                        icon: 'fa fa-trash-o',
                        name: '删除',
                        rightCode: "delete",
                        handler: function () {
                            var selected = thiz.list.getSelected()||[],
                                idList = [];
                            if(selected.length == 0) {
                                messageTool.error("请选择要删除数据");
                                return ;
                            }
                            $(selected).each(function (idx, data) {
                                idList.push(data.id);
                            });
                            thiz.deleteResource(idList);
                        }
                    }],
                    paramFn: function () {
                        return {
                            navCode: $(".navCode").val(),
                            resourceCode: $(".resourceCode").val(),
                            resourceName: $(".resourceName").val(),
                            status: $(".status").val(),
                            parentId: $(".parentId").val()
                        }
                    },
                    initSearch: function (query) {
                        $(".resourceCode").val(query.resourceCode||"");
                        $(".resourceName").val(query.resourceName||"");
                        $(".status").val(query.status||"1");
                        $(".parentId").val(query.parentId||"0");
                    },
                    classEvents: [{
                        className: 'update-info',
                        rightCode: "update",
                        handler: function (rowIndex, data) {
                            thiz.showUpdateWin(data);
                        }
                    }, {
                        className: 'delete-info',
                        rightCode: "delete",
                        handler: function (idx, data) {
                            thiz.deleteResource([data.id]);
                        }
                    }, {
                        className: 'manager-function',
                        handler: function (idx, data) {
                            func.showFuncListWin(data);
                        }
                    }]
                });

                thiz.list = list;

                $(".search-data").on("click", function () {
                    list.loadPageData(1);
                });
            },

            createWin: function (title, disableNavCode) {
                var thiz = this,
                    win = winTool.create({
                    title: title,
                    okName: "保存",
                    cancelName: "关闭",
                    type: 'selector',
                    selector: ".edit-tmpl",
                    okFn: function (win) {
                        thiz.saveResourceData(win);
                    }
                });

                win.find(".resourceIcon").on("click", function () {
                    iconTool.show({
                        callback: function (iconName) {
                            var icon = win.find(".resourceIcon-i").find("i"),
                                clazzName = icon.attr("class");
                            icon.removeClass(clazzName);
                            icon.addClass(iconName);
                            win.find(".resourceIcon").val(iconName);
                        }
                    });
                });

                var resourceType = $(".resourceType").val(),
                    selectDatas = thiz.tree.getSelected(),
                    data = selectDatas.length>0?selectDatas[0]:{},
                    pdata = null;

                if('resource' == resourceType) {
                    win.find(".parentId").val(data.id||0);
                    win.find(".parentName").val(data.resourceName||"");

                    pdata = {id: data.navCode, text: data.navName};
                } else if ('nav' == resourceType && data.id){
                    pdata = {id: data.id, text: data.resourceName};
                }

                selectTool.renderSelect({
                    selector: win.find(".navCode"),
                    url: serverHost + "/right/queryNavigationList.json",
                    width: '100%',
                    showSearch: true,
                    idName: "navCode",
                    textName: "navName",
                    val: pdata,
                    listeners: {
                        select: function (id, data) {
                            thiz.selectParent(win, id||"");
                        },
                        change: function (id, data) {
                            win.find(".parentId").val(0);
                            win.find(".parentName").val("");
                        }
                    }
                });

                if(!!disableNavCode) {
                    win.find(".navCode").select2("disable");
                }

                return win;
            },

            showAddWin: function () {
                var thiz = this,
                    win = thiz.createWin('新增');
                thiz.selectParent(win);
            },

            showUpdateWin: function (data) {
                var thiz = this,
                    win = thiz.createWin('修改', true);
                resourceForm.serializable(win.$content, data);
                win.find(".resourceIcon-i").find("i").addClass(data.resourceIcon||"");
                thiz.selectParent(win);
            },

            selectParent: function (win, navCode) {
                var navCode = navCode||win.find(".navCode").select2("val")||"";
                treeWin.render(win.find(".parentName"), {
                    title: "选择父菜单",
                    url: serverHost + "/right/queryResourceTree.json?showNav=0&navCode=" + navCode,
                    multi: false,
                    showSearchBar: true,
                    onOk: function (data) {
                        data = data || {};
                        win.find(".parentId").val(data.id||0);
                        win.find(".parentName").val(data.resourceName||"");

                        if(data.id) {
                            win.find(".navCode").select2("data", {
                                id: data.navCode,
                                text: data.navName
                            });
                        } else {
                            win.find(".navCode").select2("data", null);
                            win.find(".navCode").select2("enable");
                        }
                    },
                    valFn: function () {
                        return win.find(".parentId");
                    },
                    mappings: [{
                        name: 'resourceName', mapping: 'name'
                    }, {
                        name: 'parentId', mapping: 'pId'
                    }]
                });
            },

            loadTree: function () {
                var thiz = this;
                thiz.tree = treeTool.create({
                    selector: '.resource-tree',
                    rootName: "访问权限",
                    remote: true,
                    url: serverHost + "/right/queryResourceTree.json",
                    keyMappings: [{
                        name: 'resourceName', mapping: 'name'
                    }/*, {
                        name: 'parentId', mapping: 'pId'
                    }*/],
                    treeClick: function (treeId, treeNode) {
                        var parentId = $(".parentId").val();
                        if(treeNode && parentId != treeNode.id) {
                            $(".resourceType").val(treeNode.type||"");

                            if(treeNode.type == 'resource') {
                                $(".parentId").val(treeNode.id||"0");
                                $(".navCode").val("");
                            } else {
                                $(".parentId").val("0");
                                $(".navCode").val(treeNode.id||"");
                            }
                            thiz.list.loadPageData(1);
                        }
                    }
                })
            },
            saveResourceData: function (win) {
                var thiz = this;
                resourceForm.submit({
                    selector: win.$content,
                    url: serverHost + "/right/saveResource.json",
                    paramName: "resourceVo",
                    success: function () {
                        win.close();
                        messageTool.success("保存成功", function () {
                            thiz.list.loadPageData();
                            thiz.tree.loadTreeData();
                        });
                    }
                });
            },

            deleteResource: function (idList) {
                var thiz = this;
                messageTool.confirm("确定要删除？", function (flag) {
                    if(flag) {
                        util.ajax({
                            url: serverHost + "/right/deleteResource.json",
                            params: {
                                idList: idList
                            },
                            success: function (resp) {
                                if(resp.success == true) {
                                    messageTool.success("删除成功", function () {
                                        thiz.list.loadPageData();
                                        thiz.tree.loadTreeData();
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