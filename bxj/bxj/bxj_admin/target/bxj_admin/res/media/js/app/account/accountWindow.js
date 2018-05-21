define(['jquery',
    'app/common/listTool',
    'app/common/messageTool',
    'app/common/selectTool',
    'app/common/winTool'
], function ($, listTool, messageTool, selectTool, winTool) {

    return {
        create: function (config) {
            var account = {
                init: function () {
                    return this;
                },

                open: function (config) {
                    config = config ||{};

                    var title = config.title||"标题",
                        callback = config.callback,
                        paramsFn = config.paramsFn,
                        autoClose = config.autoClose == undefined?true:config.autoClose;

                    account.paramsFn = paramsFn;

                    account.win = winTool.create({
                        title: title,
                        height: 500,
                        width: 900,
                        showCancel: true,
                        cancelBtnName: "关闭",
                        okBtnName: "选择",
                        html: "<div style='padding: 10px; '>" +
                        "<div class='row'>" +
                        '<div class="col-sm-10">' +
                        "帐号：<input type='text' class='userCode' " +
                        "style='width: 200px;padding: 5px 6px; border: 1px solid #ddd; margin-right: 10px;'>" +
                        "用户昵称：<input type='text' class='userName' " +
                        "style='width: 200px;padding: 5px 6px; border: 1px solid #ddd;'>" +
                        '</div>' +
                        '<div class="col-sm-2">' +
                        "<a class='btn btn-info searchNote' href='javascript:void(0);' style='margin-left: 10px;'>查询</a>" +
                        '</div>' +
                        "</div>" +
                        "</div>" +
                        "<div class='note-item-list' style='overflow-y: auto;  height: 430px; overflow-x: hidden;'></div>",
                        okFn: function (win) {
                            var data = account.list.getSelected();
                            if(data) {
                                if(callback)callback(win, data);
                                if(autoClose) {
                                    win.close();
                                }
                            } else {
                                messageTool.error("尚未选择任何数据");
                                return false;
                            }
                        },
                        overflow: "hidden"
                    });

                    var url = serverHost + "/account/queryRoleUserPageData.json";

                    account.list = listTool.create({
                        selector: account.win.find(".note-item-list"),
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
                            }
                        ],
                        remote: true,
                        url: url,
                        paramFn: function () {
                            return account.queryParams();
                        },
                        rowDblclick: function (rowIndex, rowData) {
                            if(callback)callback(account.win, rowData);

                            if(autoClose) {
                                account.win.close();
                            }
                        },
                        pageSize: 8
                    });

                    account.win.find(".searchNote").on("click", function () {
                        account.list.loadPageData(1);
                    });

                },
                queryParams: function () {
                    var params =  {
                        userCode: account.win.find(".userCode").val(),
                        userName: account.win.find(".userName").val(),
                        status: 1
                    };
                    return $.extend({}, params, (account.paramsFn?account.paramsFn()||{}:{}));
                }
            };

            return account.init(config);
        }
    }
});