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
                        url: serverHost + "/activity/queryTeachersDayStatList.json",
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
                                name: "原始感恩卡类型",
                                width: 120,
                                fieldName: "oweType",
                                renderer: function (row, col, data, value) {
                                    if (value == 1)return "普通感恩卡";
                                    if (value == 2)return "内定感恩卡";
                                }
                            },
                            {
                                name: "感恩卡创建时间",
                                width: 120,
                                fieldName: "signCreateTime",
                                renderer: function (row, col, data, value) {
                                    return util.dateFormat2(value, 'YYYY-MM-DD HH:mm:ss');
                                }
                            },
                            {
                                name: "感恩卡修改时间",
                                width: 120,
                                fieldName: "signUpdateTime",
                                renderer: function (row, col, data, value) {
                                    return util.dateFormat2(value, 'YYYY-MM-DD HH:mm:ss');
                                }
                            },
                            {
                                name: "金额",
                                width: 80,
                                fieldName: "amount"
                            },
                            {
                                name: "类型",
                                width: 80,
                                fieldName: "type",
                                renderer: function (row, col, data, value) {
                                    if (value == 1)return "普通感恩卡";
                                    if (value == 2)return "幸运礼金卡";
                                    if (value == 3)return "感恩礼金卡";
                                }
                            },
                            {
                                name: "统计日期",
                                width: 120,
                                fieldName: "statDate",
                                renderer: function (row, col, data, value) {
                                    return util.dateFormat2(value, 'YYYY-MM-DD');
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
                            }
                        ],
                        paramFn: function () {
                            return {
                                phone: $(".phone").val(),
                                signedName: $(".signedName").val(),
                                oweType: $(".oweType").val(),
                                type: $(".type").val(),
                                statDate: $(".statDate").val()
                            }
                        },
                        initSearch: function (query) {

                        },
                        tbars: [],
                        classEvents: []
                    });
                this.list = list;
                //时间渲染
                dateTool.renderDate(".statDate");
                //搜索
                $(".search-action").on("click", function () {
                    thiz.list.reloadPageData(1);
                });
            }
        };
        return profitList;
    });