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
        id: 'username',//用户名
        required: true
    }, {
        id: 'passwd',//密码
        required: true
    }, {
        id: 'nickname',//昵称
        required: true
    }, {
        id: 'realname',//真实姓名
        required: true
    }, {
        id: 'mobile',//手机号
        required: true
    }, {
        id: 'tgc'//用户登录tiket和会话
    }, {
        id: 'validate_code'//验证码
    }, {
        id: 'is_del',//'0.有效，1.冻结，2.异常.3.删除'
        required: true
    }, {
        id: 'wx_user_id'//微信用户ID
    }, {
        id: 'last_login_date',//上次登录日期
        required: true
    }, {
        id: 'last_login_ip'//上次登录IP
    }, {
        id: 'sysid'
    }, {
        id: 'sourceid'//注册来源ID，（0默认注册登录，1.邀请注册）
    }, {
        id: 'create_time',//创建时间
        required: true
    }, {
        id: 'update_time'//更新时间
    }];
    userForm = formTool.create({
        fields: fields
    });

    var user = {
        list: function () {
            var thiz, list;
            thiz = this;
            list = listTool.create({
                title: "用户信息列表",
                selector: ".data-list",
                remote: true,
                hideColumnBorder: true,
                showCheckbox: true,
                url: serverHost + "/userCenter/queryUserDataList.json",
                columns: [
                    {
                        name: "用户名称",
                        fieldName: "username",
                    },
                    {
                        name: "用户密码",
                        fieldName: "passwd"
                    },
                    {
                        name: "用户昵称",
                        fieldName: "nickname"
                    },
                    {
                        name: "真实姓名",
                        fieldName: "realname",
                    },
                    {
                        name: "用户手机号",
                        fieldName: "mobile",
                    },
                    {
                        name: "用户登录信息",
                        fieldName: "tgc",
                    },
                    {
                        name: "验证码",
                        fieldName: "validateCode",
                    },
                    {
                        name: "状态",
                        fieldName: "isDel",
                        width: 60,
                        renderer: function (row, col, data, value) {
                            if (value == 0) {
                                return '<span class="label label-sm label-success">有效 </span>';
                            } else if (value == 1) {
                                return '<span class="label label-sm label-danger">冻结 </span>';
                            } else if (value == 2) {
                                return '<span class="label label-sm label-danger">异常 </span>';
                            } else {
                                return '<span class="label label-sm label-danger">删除 </span>';
                            }
                        }
                    },
                    {
                        name: "微信用户ID",
                        fieldName: "wxUserId",
                    },
                    {
                        name: "最后登录时间",
                        fieldName: "lastLoginDate",
                        type: "date"
                    },
                    {
                        name: "最后登录IP",
                        fieldName: "lastLoginIp",
                    },
                    {
                        name: "系统编码",
                        fieldName: "sysid",
                    },
                    {
                        name: "注册来源ID",
                        fieldName: "sourceid",
                    },
                    {
                        name: "创建时间",
                        fieldName: "createTime",
                        type: "date"
                    },
                    {
                        name: "更新时间",
                        fieldName: "updateTime",
                        type: "date"
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
                            if (data.isDel == 0) {
                                html += '<button class="btn btn-danger delete-info"><i class="fa fa-trash-o"></i>' +
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
                    $("#userName").val(query.username || "");
                    $("#mobile").val(query.mobile || "");
                    $("#realName").val(query.realname || "");
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
                                url: serverHost + "/ucUserCenter/ucUserAdd.jhtml"
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
                        window.location = serverHost + "/ucUserCenter/ucUserEdit.jhtml?id=" + rowData.id;
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
                $("#userName").val("");
                $("#mobile").val("");
                $("#realName").val("");
            });
        },

        getParams: function () {
            return {
                username: $("#userName").val(),
                mobile: $("#mobile").val(),
                realname: $("#realName").val()
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

            $(".submit-btn").click(function () {
                $(fields).each(function () {
                    if (this.id == "passwd") {
                        this.required = false;
                    } else if (this.id == "rePasswd") {
                        this.required = false;
                    }
                })
                user.saveData();
            })
        },

        deleteUser: function (idList) {
            var thiz = this;
            messageTool.confirm("确定要禁用？", function (flag) {
                if (flag) {
                    util.ajax({
                        url: serverHost + "/ucUserCenter/deleteUcUser.json",
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
                        url: serverHost + "/ucUserCenter/resetPasswd.json",
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
                        url: serverHost + "/ucUserCenter/enableUcUser.json",
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
                if (roleIds != "") {
                    roleIds = roleIds + ",";
                }
                roleIds = roleIds + this.id;
            });
            if (userForm.validate("#account-form")) {
                userForm.submit({
                    selector: $("#account-form"),
                    url: serverHost + "/ucUserCenter/ucUserEdit.json",
                    extValues: {
                        roleIds: roleIds
                    },
                    success: function (resp) {
                        if (resp.success == true) {
                            messageTool.success("保存成功", function () {
                                window.location.href = serverHost + "/ucUserCenter/queryUserDataList.jhtml";
                            });
                        } else {
                            messageTool.error(resp.msg || "处理失败");
                        }

                    }
                });
            }
        }
    };

    return user;
});
