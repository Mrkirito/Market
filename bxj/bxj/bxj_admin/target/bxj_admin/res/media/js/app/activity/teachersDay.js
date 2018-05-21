define(['jquery', 'app/common/util', 'app/common/advanceListTool',
        'app/common/dateTool', 'app/common/messageTool', 'app/common/commonTool', 'app/common/winTool'],
    function ($, util, listTool, dateTool, messageTool, commonTool, winTool) {
        var profitList = {
            list: function () {
                var thiz = this,
                    list = listTool.create({
                        title: "感恩卡列表",
                        selector: ".data-list",
                        remote: true,
                        url: serverHost + "/activity/queryTeachersDayOweList.json",
                        width: 600,
                        height: 600,
                        columns: [
                            {
                                name: "会员id",
                                width: 80,
                                fieldName: "userId"
                            },
                            {
                                name: "手机号码",
                                width: 80,
                                fieldName: "phone"
                            },
                            {
                                name: "恩师姓名",
                                width: 100,
                                fieldName: "oweName"
                            },
                            {
                                name: "感恩内容",
                                width: 100,
                                fieldName: "oweContent"
                            },
                            {
                                name: "落款人",
                                width: 80,
                                fieldName: "signedName"
                            },
                            {
                                name: "头像",
                                width: 80,
                                fieldName: "signedPhoto",
                                renderer: function (row, col, data, value) {
                                    return "<img src='" + value + "' style='width: 40px;height: 40px' />"
                                }
                            },
                            {
                                name: "获赞数",
                                width: 80,
                                fieldName: "likeNum",
                                sort: true,
                                sortName: "like_num",
                                defaultSort: "desc"
                            },
                            {
                                name: "感恩卡类型",
                                width: 120,
                                fieldName: "oweType",
                                renderer: function (row, col, data, value) {
                                    if (value == 1)return "普通感恩卡";
                                    if (value == 2)return "内定感恩卡";
                                }
                            },
                            {
                                name: "创建日期",
                                width: 120,
                                fieldName: "createTime",
                                renderer: function (row, col, data, value) {
                                    return util.dateFormat2(value, 'YYYY-MM-DD HH:mm:ss');
                                }
                            },
                            {
                                name: "修改日期",
                                width: 120,
                                fieldName: "updateTime",
                                renderer: function (row, col, data, value) {
                                    return util.dateFormat2(value, 'YYYY-MM-DD HH:mm:ss');
                                }
                            },
                            {
                                name: "操作",
                                width: 100,
                                rightFixed: true,
                                renderer: function (rindx, cindex, data) {
                                    if (data.oweType == 1) {
                                        var st = '<button class="btn btn-info oweTypeProcess">设为内定感恩卡</button>';
                                    } else if (data.oweType == 2) {
                                        var st = '<button class="btn btn-danger oweTypeProcess">设为普通感恩卡</button>';
                                    }
                                    st += '<button class="btn btn-info luckOwe">设为幸运礼金</button>';
                                    return st;
                                }
                            }
                        ],
                        paramFn: function () {
                            return {
                                phone: $(".phone").val(),
                                signedName: $(".signedName").val(),
                                oweType: $(".oweType").val()
                            }
                        },
                        initSearch: function (query) {

                        },
                        tbars: [],
                        classEvents: [{
                            className: "oweTypeProcess",
                            handler: function (idx, data) {
                                thiz.oweTypeProcess(data);
                            }
                        }, {
                            className: "luckOwe",
                            handler: function (idx, data) {
                                thiz.luckOwe(data);
                            }
                        },]
                    });
                this.list = list;
                //时间渲染
                dateTool.renderDateRange(".createTime", {
                    applyClick: function (startDate, endDate) {
                        $(".startTime").val(startDate);
                        $(".endTime").val(endDate);
                    }
                });
                //搜索
                $(".search-action").on("click", function () {
                    thiz.list.reloadPageData(1);
                });
            },// 感恩卡类型设置
            oweTypeProcess: function (data) {
                var thiz = this;
                var id = data.id;
                if (data.oweType == 1) var msgInfo = "确定要设置为内定感恩卡，设置成功后改用户将成为感恩礼金获得者";
                if (data.oweType == 2) var msgInfo = "确定要设置为普通感恩卡";
                util.request({
                    confirm: true,
                    msg: msgInfo,
                    url: serverHost + "/activity/updateOwe.json",
                    params: {
                        id: id
                    },
                    success: function (resp) {
                        if (resp.success) {
                            messageTool.success("更新成功");
                            thiz.list.reloadPageData();
                        } else {
                            messageTool.error("此记录已不存在");
                            thiz.list.reloadPageData();
                        }
                    }
                });
            },// 幸运礼金
            luckOwe: function (data) {
                var thiz = this;
                var id = data.id;
                var win = winTool.create({
                    title: "幸运礼金设置",
                    height: 300,
                    width: 450,
                    showCancel: true,
                    okName: "保存",
                    cancelName: "关闭",
                    type: "selector",
                    selector: ".headline-banner-tpl",
                    okFn: function (win) {
                        var amount = win.find(".amount").val();
                        var flag = true;
                        if (!amount) {
                            messageTool.error("必须输入幸运礼金金额!");
                            flag = false;
                        }
                        if (flag) {
                            util.request({
                                confirm: true,
                                msg: "确定要设置为幸运礼金获取用户?",
                                url: serverHost + "/activity/luckOwe.json",
                                params: {
                                    id: id,
                                    amount: amount
                                },
                                success: function (resp) {
                                    if (resp.success) {
                                        messageTool.success("设置成功，可在【教师节活动获奖管理】中查看");
                                        win.close();
                                        thiz.list.reloadPageData();
                                    } else {
                                        messageTool.error("新增失败");
                                    }
                                }
                            });
                        }
                    }
                })
            }
        };
        return profitList;
    });