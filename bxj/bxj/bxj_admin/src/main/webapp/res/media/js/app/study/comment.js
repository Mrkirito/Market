define(['jquery',
    'app/common/util',
    'app/common/advanceListTool',
    'app/common/winTool',
    'app/common/dateTool',
    'app/common/messageTool',
    'app/common/imageUploadTool',
    'app/common/editorTool',
    'app/common/imageView'
    ],

    function ($, util, listTool, winTool, dateTool, messageTool) {
        return {
            /** 列表 **/
            list: function () {
                var thiz = this,
                    list = listTool.create({
                        title: "评论列表",
                        selector: ".data-list",
                        remote: true,
                        //hideColumnBorder: true,
                        //multiSelect: true,
                        /** checkbox **/
                        showCheckbox: true,
                        width: 640,
                        height: 600,
                        url: serverHost + "/study3Comment/list.json",
                        orderBy: "id desc",
                        columns: [
                            {
                                name: "id",
                                sortName: "id",
                                fieldName: "id"
                            },
                            {
                                name: "文章标题",
                                fieldName: "title"
                            },
                            {
                                name: "评论内容",
                                fieldName: "content"
                            },
                            {
                                name: "操作",
                                rightFixed: true,
                                width: "150",
                                renderer: function (rindx, cindex, data) {
                                    var st = '';
                                    st += '<a class="red delete" href="javascript:void(0);" title="删除"> <i class="fa fa-trash bigger-140 "></i> </a>&nbsp;';
                                    return st;
                                }
                            }],

                        /** 按钮 **/
                        tbars: [{
                            icon: 'fa fa-plus',
                            name: "批量删除",
                            rightCode: "delete_bat",
                            handler: function (idx, data) {
                                thiz.delete_bat();
                            }
                        }],

                        /** 操作 **/
                        classEvents: [
                            {
                                className: "delete",
                                rightCode: "delete",
                                handler: function (idx, data) {
                                    thiz.delete(data);
                                }
                            }
                        ],

                        /** 搜索条件 **/
                        paramFn: function () {
                            return thiz.getParams();
                        }
                    });
                thiz.list = list;

                /** 搜索按钮事件 **/
                $(".search-data").on("click", function () {
                    thiz.list.reloadPageData(1);
                });

                /** 重置按钮 **/
                $(".reset-data").on("click", function () {
                    $('#articleTitle').val('');
                    $("#content").val("");
                });
            },

            /** 搜索条件 **/
            getParams: function () {
                return {
                    articleTitle: $('#articleTitle').val(),
                    content: $("#content").val()
                }
            },

            /** 删除 **/
            delete: function (data) {
                var thiz = this;
                var id = data.id;
                util.request({
                    confirm: true,
                    msg: "确定要删除?",
                    url: serverHost + "/study3Comment/delete.json",
                    params: {
                        id: id
                    },
                    success: function (resp) {
                        if (resp.success) {
                            messageTool.success("操作成功!");
                            thiz.list.reloadPageData();
                        } else {
                            messageTool.error("操作失败！");
                            thiz.list.reloadPageData();
                        }
                    }
                });
            },

            // 批量删除
            delete_bat: function(data) {
                var thiz = this;
                var selected = thiz.list.getSelected() || [],
                    idList = [];
                if (selected.length == 0) {
                    messageTool.error("没有选中任何评论！");
                    return;
                }
                $(selected).each(function (idx, data) {
                    idList.push(data.id);
                });
                util.request({
                    confirm: true,
                    msg: "确定要删除?",
                    url: serverHost + "/study3Comment/delete_bat.json",
                    params: {
                        ids: idList.toString()
                    },
                    success: function (resp) {
                        if(resp.success) {
                            messageTool.success("删除成功");
                            thiz.list.reloadPageData();
                        } else {
                            messageTool.error(resp.msg || "此记录已不存在");
                            thiz.list.reloadPageData();
                        }
                    }
                });
            }
        };
    });