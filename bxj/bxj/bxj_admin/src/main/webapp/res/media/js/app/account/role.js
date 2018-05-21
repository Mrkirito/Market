define(['jquery',
    'app/common/util',
    'app/common/advanceListTool',
    'app/common/messageTool',
    'app/common/treeTool',
    'common/arrayUtil',
    'app/common/formTool',
    'app/common/selectTool',
    'app/common/exportTool',
    'app/common/tbarTool',
    'app/common/winTool'
], function ($, util, listTool, messageTool,
             treeTool, arrayUtil, formTool, selectTool, exportTool,
             tbarTool,winTool) {

    var fields = [
        {
            cls: 'id'
        }, {
            cls: 'parentId'
        }, {
            cls: 'roleCode',
            required: true
        }, {
            cls: 'roleName',
            required: true
        }, {
            cls: 'memo'
        }],
        roleForm = formTool.create({
            fields: fields
        });

    var role = {
        list: function () {
            role.list = listTool.create({
                title: "角色列表",
                selector: ".data-list",
                remote: true,
                hideColumnBorder: true,
                multiSelect: true,
                showCheckbox: true,
                minWidth: 900,
                url: serverHost + "/role/queryPageData.json",
                columns: [
                    {
                        name: "编码",
                        fieldName: "roleCode"
                    },
                    {
                        name: "名称",
                        fieldName: "roleName"
                    },
                    {
                        name: "备注",
                        fieldName: "memo"
                    },
                    {
                        name: "创建时间",
                        fieldName: "createTime",
                        renderer : function(row, col, data,value) {
                        	return util.dateFormat2(value, 'YYYY-MM-DD HH:mm:ss');
                        }
                    },
                    {
                        name: "创建人",
                        fieldName: "createName"
                    },
                    {
                        name: "修改时间",
                        fieldName: "updateTime",
                        renderer : function(row, col, data,value) {
                        	return util.dateFormat2(value, 'YYYY-MM-DD HH:mm:ss');
                        }
                    },
                    {
                        name: "修改人",
                        fieldName: "updateName"
                    },
                    {
                        name: "状态",
                        fieldName: "status",
                        width: 60,
                        renderer: function (row, col, data, value) {
                            if (value == 1) {
                                return '<span class="label label-sm label-success">正常 </span>';
                            } else {
                                return '<span class="label label-sm label-danger">已禁用 </span>';
                            }
                        }
                    },
                    {
                        name: "操作",
                        width: 200,
                        rightFixed: true,
                        renderer: function (rindx, cindex, data) {
                            return '<div class="action-buttons"> ' +
                                '<div class="btn-group">' +
                                '<button class="btn btn-info update-info"> 编辑</button>' +
                                (data.status == 1?'<button class="btn btn-danger delete-info"> 禁用</button>':'<button' +
                                ' class="btn btn-success enable-info"> 启用</button>') +
                                '<button class="btn btn-info role-account"> 角色用户</button>' +
                                '</div>' +
                                '</div>';
                        }
                    }],
                paramFn: function () {
                    return role.getParams();
                },
                initSearch: function (query) {
                    $("#roleCode").val(query.roleCode || "");
                    $("#roleName").val(query.roleName || "");
                    $("#status").val(query.status || "1");
                },
                tbars: [
                    {
                        type: "group",
                        icon: "fa fa-gear fa-lg",
                        name: "操作",
                        btns: [
                            {
                                icon: 'fa fa-plus',
                                name: "新增角色",
                                rightCode: "add",
                                url: serverHost + "/role/roleAdd.jhtml"
                            }, {
                                icon: 'fa fa-trash-o',
                                name: '批量禁用',
                                rightCode: "batchDisable",
                                handler: function () {
                                    var selected = role.list.getSelected() || [],
                                        idList = [];
                                    if (selected.length == 0) {
                                        messageTool.error("请选择要禁用的帐号");
                                        return;
                                    }
                                    $(selected).each(function (idx, data) {
                                        idList.push(data.id);
                                    });
                                    role.deleteRole(idList);
                                }
                            }
                        ]
                    }],
                classEvents: [{
                    className: "update-info",
                    rightCode: "edit",
                    handler: function (rowIndex, rowData) {
                        window.location = serverHost + "/role/roleEdit.jhtml?id=" + rowData.id;
                    }
                }, {
                    className: 'delete-info',
                    rightCode: "disable",
                    handler: function (idx, data) {
                        role.deleteRole([data.id], 0);
                    }
                }, {
                    className: 'enable-info',
                    rightCode: "enable",
                    handler: function (idx, data) {
                        role.deleteRole([data.id], 1);
                    }
                }, {
                    className: 'role-account',
                    rightCode: "enable",
                    handler: function (idx, data) {
                        window.location = serverHost + "/role/roleAccount.jhtml?id=" + data.id;
                    }
                },]
            });

            $(".search-data").on("click", function () {
                role.list.reloadPageData(1);
            });

            $(".reset-data").on("click", function () {
                $("#roleCode").val("");
                $("#roleName").val("");
                $("#status").val("1");
            });

        },

        getParams: function () {
            return {
                roleCode: $("#roleCode").val(),
                roleName: $("#roleName").val(),
                status: $("#status").val()
            }
        },

        /**
         * 新增帐号
         */
        initAdd: function () {
            /*tbarTool.create({
                tbars: [{
                    icon: "fa fa-save",
                    name: "保存",
                    handler: function () {
                        role.saveData();
                    }
                }]
            });*/
            $(".submit-btn").click(function () {
                role.saveData();
            })
            role.loadResourceTree();
            role.loadFunctionTree();

            role.resourceList = [];
            role.selectedFuncMap = {};
        },

        /**
         * 编辑帐号信息
         */
        initEdit: function () {

            $(".submit-btn").click(function () {
                role.saveData();
            })
            role.loadResourceTree();
            role.loadFunctionTree();

            role.resourceList = JSON.parse($(".resourceIdList").val())||[];

            role.selectedFuncMap = JSON.parse($(".funcMap").val())||{};
            console.info(role.selectedFuncMap)
        },

        /**
         * 加载资源权限
         */
        loadResourceTree: function () {
            role.tree = treeTool.create({
                treeId: 'resourceTree',
                selector: '.resource-tree',
                rootName: "资源权限",
                enableCheck: true,
                multiple: true,
                selectedMulti: false,
                chkboxType: {"Y": "p", "N": "s"},
                url: serverHost + "/role/queryResourceTree.json",
                keyMappings: [{
                    name: 'resourceName', mapping: 'name'
                }, {
                    name: 'parentId', mapping: 'pId'
                }],
                treeClick: function (treeId, treeNode) {
                    if ($(".selectResourceId").val() != treeNode.id) {
                        $(".selectResourceId").val(treeNode.id)

                        role.functionTree.reloadTree(role.getSelectedFuncIdList(treeNode.id));
                    }
                },
                checkClick: function (checked, treeId, treeNode) {
                    if (!treeNode.checked) {
                        role.resourceList = arrayUtil.removeItem(role.resourceList, treeNode.id);
                        role.clearResourceFunction(treeNode.id);
                        role.functionTree.checkAllNodes(false);
                    } else {
                        role.resourceList = arrayUtil.insertItem(role.resourceList, treeNode.id, 0);
                    }
                    console.info(role.resourceList)
                },
                paramFn: function () {
                    return {
                        roleId: $(".id").val() || 0
                    };
                }
            });
        },

        getSelectedFuncIdList: function (resourceId) {
            var funcIdList = [];
            $(role.selectedFuncMap[resourceId] || []).each(function () {
                funcIdList.push(this.id);
            });
            return funcIdList;
        },

        /**
         * 加载按钮权限
         */
        loadFunctionTree: function () {
            role.functionTree = treeTool.create({
                treeId: 'functionTree',
                selector: '.function-tree',
                rootName: "按钮权限",
                enableCheck: true,
                multiple: true,
                chkboxType: {"Y": "p", "N": "s"},
                url: serverHost + "/role/queryResourceFunctionTree2.json",
                keyMappings: [{
                    name: 'funcName', mapping: 'name'
                }],
                paramFn: function () {
                    return {
                        roleId: $(".id").val() || 0,
                        resourceId: $(".selectResourceId").val() || 0
                    };
                },
                treeClick: function (treeId, treeNode) {
                    var resourceId = $(".selectResourceId").val();
                    if (treeNode.checked) {
                        role.addResourceFunction(resourceId, treeNode);
                    } else {
                        role.clearResourceFunction(resourceId, treeNode.id);
                    }
                }
            })
        },

        addResourceFunction: function (resourceId, func) {
            if (resourceId && func) {
                var funcList = role.selectedFuncMap[resourceId] || [];
                funcList.push(func);
                role.selectedFuncMap[resourceId] = funcList;
            }
        },

        /**
         * 取消选择时,清除按钮权限数据
         * @param resourceId
         */
        clearResourceFunction: function (resourceId, funcId) {
            if (resourceId) {
                var funcList = role.selectedFuncMap[resourceId] || [];
                if (!funcId) {
                    funcList = [];
                } else {
                    var newFuncList = [];
                    $(funcList).each(function () {
                        if (this.id != funcId) {
                            newFuncList.push(this);
                        }
                    })
                    funcList = newFuncList;
                }
                role.selectedFuncMap[resourceId] = funcList;
            }
        },

        deleteRole: function (idList, status) {
            messageTool.confirm(status == 0 ? "确定要禁用？" : "确定要启用？", function (flag) {
                if (flag) {
                    util.ajax({
                        url: serverHost + "/role/deleteRole.json",
                        params: {
                            idList: idList,
                            status: status
                        },
                        success: function (resp) {
                            if (resp.success == true) {
                                messageTool.success(status == 0 ? "禁用成功" : "启用成功", function () {
                                    role.list.reloadPageData();
                                });
                            } else {
                                messageTool.error(resp.msg || status == 0 ? "禁用失败" : "启用失败");
                            }
                        }
                    });
                }
            });
        },

        saveData: function () {
            if (roleForm.validate("#role-form")) {
                var resourceIdList = "",
                    funcIdList = "";

                $(role.tree.getChecked()||[]).each(function () {
                	if(resourceIdList != ""){
                		resourceIdList = resourceIdList + ",";
                	}
                	resourceIdList = resourceIdList + this.id;
                })

                $.each(role.selectedFuncMap, function (id, funcList) {
                    console.info(funcList)
                    $(funcList).each(function () {
                    	if(funcIdList != ""){
                    		funcIdList = funcIdList + ",";
                    	}
                    	funcIdList = funcIdList + this.id;
                    })
                })
                console.info(resourceIdList);
                console.info(funcIdList);
                roleForm.submit({
                    selector: "#role-form",
                    confirm: true,
                    msg: "确定要保存?",
                    url: serverHost + "/role/saveRole.json",
                    extValues: {
                        resourceIdList : resourceIdList,
                        funcIdList: funcIdList
                    },
                    success: function (resp) {
                        if(resp.success) {
                            window.location = serverHost + "/role/roleList.jhtml";
                        }
                    }
                })
            }
        }
    };

    return role;
});