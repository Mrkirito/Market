define(['jquery',
    'app/common/util',
    'app/common/advanceListTool',
    'app/common/messageTool',
    'app/common/winTool',
    'app/common/treeWinTool',
    'app/common/formTool',
    'app/common/selectTool',
    'app/common/exportTool'
], function ($, util, listTool, messageTool,
             winTool, treeWinTool, formTool, selectTool, exportTool) {

    var fields = [{
            id: 'id'
        }, {
            id: 'userCode',
            required: true
        }, {
            id: 'userName',
            required: true
        }, {
            id: 'password',
            required: true
        }, {
            id: 'rePassword',
            required: true
        }, {
            id: 'mobile'
        }, {
            id: 'email', dataType: "email"
        }, {
            id: 'realName'
        }, {
            id: 'department'
        }, {
            id: 'roleId', type: "select2",
            multiple: true
        }
        ],
        userForm = formTool.create({
            fields: fields
        });

    var user = {
        list: function () {
            var thiz = this,
                list = listTool.create({
                    title: "帐号列表",
                    selector: ".data-list",
                    remote: true,
                    hideColumnBorder: true,
                    multiSelect: true,
                    showCheckbox: true,
                    minWidth: 900,
                    url: serverHost + "/account/queryAccountList.json",
                    columns: [
                        {
                            name: "帐号",
                            fieldName: "userCode",
                            sort: true,
                            sortName: "a.user_code"
                        },
                        {
                            name: "昵称",
                            fieldName: "userName"
                        },
                        {
                            name: "角色",
                            fieldName: "roleNames"
                        },
                        {
                            name: "真实姓名",
                            fieldName: "realName",
                            firstHide: true
                        },
                        {
                            name: "邮箱",
                            fieldName: "email",
                            firstHide: true
                        },
                        {
                            name: "电话",
                            fieldName: "mobile",
                            firstHide: true
                        },
                        {
                            name: "最后登录时间",
                            fieldName: "lastLoginTime",
                            secondHide: true,
                            type: "date"
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
                            width: 220,
                            rightFixed: true,
                            renderer: function (rindx, cindex, data) {
                                var html = '<div class="action-buttons"> ' +
                                    '<div class="btn-group">' +
                                    '<button class="btn btn-info update-info"><i class="fa fa-pencil"></i> 编辑</button>' +
                                    '<button class="btn btn-info reset-passwd"><i class="fa fa-lock"></i>' +
                                    ' 重置密码</button>';
                                if (data.status == 1) {
                                    html += '<button class="btn btn-danger delete-info"><i class="fa fa-trash-o"></i>'+
                                    ' 禁用</button>';
                                } else {
                                    html += '<button class="btn btn-success enable-info"><i class="fa fa-hand-o-up' +
                                        ' bigger-140"></i> 启用</button>';
                                }
                                html += '</div></div>';
                                return html;
                            }
                        }],
                    paramFn: function () {
                        return thiz.getParams();
                    },
                    initSearch: function (query) {
                        $("#userCode").val(query.userCode || "");
                        $("#userName").val(query.userNameLike || "");
                        $("#email").val(query.email || "");
                        $("#mobile").val(query.mobile || "");
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
                                    name: "新增帐号",
                                    rightCode: "add",
                                    url: serverHost + "/account/accountAdd.jhtml"
                                }, {
                                    icon: 'fa fa-trash-o',
                                    name: '批量禁用',
                                    rightCode: "batchDiable",
                                    handler: function () {
                                        var selected = thiz.list.getSelected() || [],
                                            idList = [];
                                        if (selected.length == 0) {
                                            messageTool.error("请选择要禁用的帐号");
                                            return;
                                        }
                                        $(selected).each(function (idx, data) {
                                            idList.push(data.id);
                                        });
                                        thiz.deleteUser(idList);
                                    }
                                }
                            ]
                        }],
                    classEvents: [{
                        className: "update-info",
                        rightCode: "edit",
                        handler: function (rowIndex, rowData) {
                            window.location = serverHost + "/account/accountEdit.jhtml?id=" + rowData.id;
                        }
                    }, {
                        className: 'reset-passwd',
                        rightCode: "resetpwd",
                        handler: function (idx, data) {
                            thiz.resetPasswd(data.id);
                        }
                    }, {
                        className: 'delete-info',
                        rightCode: "disable",
                        handler: function (idx, data) {
                            thiz.deleteUser([data.id]);
                        }
                    }, {
                        className: 'enable-info',
                        rightCode: "enable",
                        handler: function (idx, data) {
                            thiz.enableUser(data.id);
                        }
                    }]
                });
            thiz.list = list;

            $(".search-data").on("click", function () {
                thiz.list.reloadPageData(1);
            });

            $(".reset-data").on("click", function () {
                $("#userCode").val("");
                $("#userName").val("");
                $("#email").val("");
                $("#mobile").val("");
                $("#status").val("1");
            });

        },

        getParams: function () {
            return {
                userCode: $("#userCode").val(),
                userNameLike: $("#userName").val(),
                email: $("#email").val(),
                mobile: $("#mobile").val(),
                status: $("#status").val()
            }
        },

        /**
         * 新增帐号
         */
        initAdd: function () {
            this.renderSelect();
            this.renderSelectData();

            $(".submit-btn").click(function () {
                user.saveData();
            })
        },

        /**
         * 编辑帐号信息
         */
        initEdit: function () {
            this.renderSelect();
            this.renderSelectData();

            $(".submit-btn").click(function () {
                $(fields).each(function () {
                    if (this.id == "password") {
                        this.required = false;
                    } else if (this.id == "rePassword") {
                        this.required = false;
                    }
                })
                user.saveData();
            })
        },

        renderSelect: function () {
            selectTool.renderSelect({
                selector: "#roleId",
                msg: "--选择权限--",
                textName: "roleName",
                url: serverHost + "/role/queryRoleTree.json",
                width: '100%',
                multiple: true
            });
        },

        renderSelectData: function () {
            var roleJson = $("#roleId").data("json") || [],
                roleData = [];
            $(roleJson).each(function () {
                roleData.push({
                    id: this.id,
                    text: this.roleName
                });
            })
            $("#roleId").select2("data", roleData);
        },

        deleteUser: function (idList) {
            var thiz = this;
            messageTool.confirm("确定要禁用？", function (flag) {
                if (flag) {
                    util.ajax({
                        url: serverHost + "/account/deleteUser.json",
                        params: {
                            idList: idList
                        },
                        success: function (resp) {
                            if (resp.success == true) {
                                messageTool.success("禁用成功", function () {
                                    thiz.list.reloadPageData();
                                });
                            } else {
                                messageTool.error(resp.msg || "禁用失败");
                            }
                        }
                    });
                }
            });
        },

        resetPasswd: function (id) {
            var thiz = this;
            messageTool.confirm("确定要重置密码？", function (flag) {
                if (flag) {
                    util.ajax({
                        url: serverHost + "/account/resetPasswd.json",
                        params: {
                            id: id
                        },
                        success: function (resp) {
                            if (resp.success == true) {
                                messageTool.success("重置密码成功");
                            } else {
                                messageTool.error(resp.msg || "重置密码失败");
                            }
                        }
                    });
                }
            });
        },

        enableUser: function (id) {
            var thiz = this;
            messageTool.confirm("确定要启用？", function (flag) {
                if (flag) {
                    util.ajax({
                        url: serverHost + "/account/enableUser.json",
                        params: {
                            id: id
                        },
                        success: function (resp) {
                            if (resp.success == true) {
                                thiz.list.reloadPageData();
                            } else {
                                messageTool.error(resp.msg || "启用失败");
                            }
                        }
                    });
                }
            });
        },

        saveData: function () {
            var thiz = this;
        	var roleIdList = $("#roleId").select2("data") || [];
        	var roleIds = "";
            $(roleIdList).each(function () {
            	if(roleIds != ""){
            		roleIds = roleIds + ",";
            	}
            	roleIds = roleIds + this.id;
            });
            if (userForm.validate("#account-form")) {
            	userForm.submit({
            		selector: $("#account-form"),
                    url: serverHost + "/account/accountEdit.json",
                    extValues: {
                    	roleIds: roleIds
                    },
                    success: function (resp) {
                    	if(resp.success == true) {
                    		messageTool.success("保存成功", function () {
                            	window.location.href = serverHost + "/account/accountList.jhtml";
                            });
                        } else {
                            messageTool.error(resp.msg||"处理失败");
                        }
                        
                    }
                });
            }
        }
    };

    return user;
});