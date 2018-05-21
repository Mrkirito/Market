define(['jquery', 'app/common/util', 'app/common/advanceListTool',
        'app/common/dateTool', 'app/common/messageTool', 'app/common/commonTool', 'app/common/winTool', 'app/common/imageView'],
    function ($, util, listTool, dateTool, messageTool, commonTool, winTool) {
        var profitList = {
            list: function () {
                var thiz = this,
                    list = listTool.create({
                        title: "开门红文章列表",
                        selector: ".data-list",
                        remote: true,
                        url: serverHost + "/activity/queryOpenerArticleList.json",
                        width: 600,
                        height: 600,
                        columns: [
                            {
                                name: "主键",
                                width: 40,
                                fieldName: "id"
                            },
                            {
                                name: "文章附图",
                                width: 50,
                                fieldName: "iconUrl",
                                renderer: function (row, col, data, value) {
                                    return "<a href='" + public_path + value + "' rel='lightbox'><img src='" + public_path + value + "' style='width: 40px;height: 40px' /></a>"
                                }
                            },
                            {
                                name: "文章标题",
                                width: 150,
                                fieldName: "title"
                            },
                            {
                                name: "文章内容",
                                width: 80,
                                fieldName: "articleContent",
                                renderer: function (row, col, data, value) {
                                    return '<a class="label btn-rounded label-info contentDetail">查看明细</a>';
                                }
                            }, {
                                name: "文章声明",
                                width: 150,
                                fieldName: "articleSource"
                            },
                            {
                                name: "文章类型",
                                width: 80,
                                fieldName: "typeId",
                                renderer: function (row, col, data, value) {
                                    if (value == 1) return "<span class='label label-table label-success'>成功秘籍<i></i></span>";
                                    if (value == 2) return "<span class='label label-table label-danger'>开单宝典<i></i></span>";
                                    if (value == 3) return "<span class='label label-table label-warning'>热门产品<i></i></span>";
                                }
                            },
                            {
                                name: "发布日期",
                                width: 100,
                                fieldName: "publishDate",
                                sort: true,
                                sortName: "publish_date",
                                renderer: function (row, col, data, value) {
                                    return util.dateFormat2(value, 'YYYY-MM-DD HH:mm:ss');
                                }
                            },
                            {
                                name: "真实浏览数",
                                width: 80,
                                fieldName: "browseCountReal"
                            },
                            {
                                name: "虚拟浏览数",
                                width: 80,
                                fieldName: "browseCountVirtual"
                            },
                            {
                                name: "真实分享数",
                                width: 80,
                                fieldName: "shareCountReal"
                            },
                            {
                                name: "虚拟分享数",
                                width: 80,
                                fieldName: "shareCountVirtual"
                            },
                            {
                                name: "排序",
                                width: 50,
                                fieldName: "sort",
                                sort: true,
                                sortName: "sort",
                            },
                            {
                                name: "显示状态",
                                width: 80,
                                fieldName: "showStatus",
                                renderer: function (row, col, data, value) {
                                    if (value == 1) return "<span class='label label-table label-success'>显示<i></i></span>";
                                    if (value == 0) return "<span class='label label-table label-danger'>隐藏<i></i></span>";
                                }
                            },
                            {
                                name: "创建日期",
                                width: 100,
                                fieldName: "createTime",
                                renderer: function (row, col, data, value) {
                                    return util.dateFormat2(value, 'YYYY-MM-DD HH:mm:ss');
                                }
                            },
                            {
                                name: "操作",
                                width: 100,
                                rightFixed: true,
                                renderer: function (rindx, cindex, data) {
                                    var st = '<button class="btn btn-danger update">修改</button>';
                                    return st;
                                }
                            }
                        ],
                        paramFn: function () {
                            return {
                                title: $(".title").val(),
                                publishDate: $(".publishDate").val(),
                                typeId: $(".typeId").val(),
                                showStatus: $(".showStatus").val()
                            }
                        },
                        initSearch: function (query) {

                        },
                        tbars: [{
                            icon: 'fa fa-plus',
                            name: "新增",
                            // rightCode: "add",
                            handler: function (idx, data) {
                                thiz.add();
                            }
                        }],
                        classEvents: [{
                            className: "update",
                            handler: function (idx, data) {
                                thiz.update(data);
                            }
                        }, {
                            className: "contentDetail",
                            handler: function (idx, data) {
                                thiz.contentDetail(data);
                            }
                        },]
                    });
                this.list = list;
                //时间渲染
                //时间渲染
                dateTool.renderDate(".publishDate");
                //搜索
                $(".search-action").on("click", function () {
                    thiz.list.reloadPageData(1);
                });
            },// 新增开门红文章
            add: function (data) {
                window.location.href = serverHost + "/activity/createOpener.jhtml"
            },
            update: function (data) {
                window.location.href = serverHost + "/activity/updateOpener.jhtml?id=" + data.id;
            },
            contentDetail: function (data) {
                var win = winTool.create({
                    title: data.title,
                    height: 800,
                    width: 800,
                    showCancel: false,
                    okName: "关闭",
                    type: "selector",
                    selector: ".article_detail",
                    okFn: function (win) {
                        win.close();
                    }
                });
                win.find(".contentDetail").html(data.articleContent);
            },
            // 新增用户charts
            renderArticleUeditor: function (data) {
                var ue = UE.getEditor('articleContent');

            },
        };
        return profitList;
    });