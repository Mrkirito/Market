define(['jquery',
    'app/common/util',
    'app/common/advanceListTool',
    'app/common/messageTool',
    'app/account/accountWindow'
], function ($, util, listTool, messageTool, accountWindow) {


    var account = {
        list: function () {
            account.accountWin = accountWindow.create();

            account.list = listTool.create({
                title: "角色帐号列表",
                selector: ".data-list",
                remote: true,
                hideColumnBorder: true,
                multiSelect: true,
                showCheckbox: true,
                minWidth: 900,
                url: serverHost + "/account/queryRoleUserPageData.json",
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
                        width: 80,
                        rightFixed: true,
                        renderer: function (rindx, cindex, data) {
                            var html = '<div class="action-buttons"> ' +
                                '<div class="btn-group">' +
                                '<button class="btn btn-danger deleteRoleAccount"> 删除</button>' +
                                '</div></div>';
                            return html;
                        }
                    }],
                paramFn: function () {
                    return account.getParams();
                },
                tbars: [
                    {
                        icon: 'fa fa-plus',
                        name: "添加帐号",
                        rightCode: "add",
                        handler: function (e, btn, tool) {
                            account.showAddAccountWindow();
                        }
                    },
                    {
                        icon: 'fa fa-remove',
                        name: "批量删除帐号",
                        rightCode: "batchDelete",
                        handler: function (e, btn, tool) {
                            var selected = tool.getSelected()||[],
                                accountIdList = [];
                            $(selected).each(function () {
                                accountIdList.push(this.id);
                            })
                            account.deleteRoleAccount(accountIdList);
                        }
                    }],
                classEvents: [{
                    className: "deleteRoleAccount",
                    rightCode: "delete",
                    handler: function (rowIndex, rowData) {
                        account.deleteRoleAccount([rowData.id]);
                    }
                }]
            });

        },

        getParams: function () {
            return {
                roleId: $(".id").val() || 0,
                status: 1
            }
        },

        showAddAccountWindow: function () {
            account.accountWin.open({
                title: "选择角色用户",
                callback: function (win, accountList) {
                    account.addRoleAccount(win, accountList);
                },
                paramsFn: function () {
                    return {
                        notRoleId: $(".id").val()
                    }
                },
                autoClose: false
            });
        },
        addRoleAccount: function (win, accountList) {
            accountList = accountList ||[];
            var userIdList = [];
            $(accountList).each(function () {
                userIdList.push(this.id);
            })
            if(userIdList.length > 0) {

                util.request({
                    url: serverHost + "/role/saveRoleUser.json",
                    confirm: true,
                    msg: "确定要增加帐号?",
                    params: {
                        roleId: $(".id").val(),
                        userIdList: userIdList
                    },
                    success: function (resp) {
                        if(resp.success) {
                            win.close();
                            account.list.reloadPageData();
                        } else {
                            messageTool.error(resp.msg||"增加角色帐号失败");
                        }
                    }
                })
            } else {
                messageTool.error("尚未选择任何帐号");
            }
        },

        deleteRoleAccount: function (accountIdList) {
            if(!accountIdList || accountIdList.length == 0) {
                messageTool.error("尚未选择数据");
            } else {
                util.request({
                    url: serverHost + "/role/deleteRoleUser.json",
                    confirm: true,
                    msg: "确定要删除帐号?",
                    params: {
                        roleId: $(".id").val(),
                        userIdList: accountIdList
                    },
                    success: function (resp) {
                        if(resp.success) {
                            account.list.reloadPageData();
                        } else {
                            messageTool.error(resp.msg||"删除角色帐号失败");
                        }
                    }
                })
            }
        }

    };

    return account;
});