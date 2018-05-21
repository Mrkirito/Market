define(['jquery', 'app/common/util', 'app/common/advanceListTool', 'app/common/winTool',
        'app/common/dateTool', 'app/common/messageTool', 'app/common/imageUploadTool', 'app/common/editorTool', 'app/common/commonTool',
        'app/common/imageView'],
    function ($, util, listTool, winTool, dateTool, messageTool, imageUploadTool, editorTool) {
        var userList = {
            list: function () {
                var thiz = this,
                    list = listTool.create({
                        title: "商品",
                        selector: ".data-list",
                        remote: true,
                        url: serverHost + "/goods/list.json",
                        width: 600,
                        height: 600,
                        columns: [
                            {
                                name: "ID",
                                width: 60,
                                fieldName: "id"
                            },
                            {
                                name: "名称",
                                width: 120,
                                fieldName: "name"
                            },
                            /*{
                                name: "图片",
                                width: 80,
                                fieldName: "picture",
                                renderer: function (row, col, data, value) {
                                    return '<a href="' + value + '" rel="lightbox"><img src="' + value + '"/></a>';
                                }
                            },*/
                            {
                                name: "原价",
                                width: 80,
                                fieldName: "price1"

                            },
                            {
                                name: "会员价",
                                width: 80,
                                fieldName: "price2"

                            },
                            {
                                name: "新会员价",
                                width: 80,
                                fieldName: "price3"

                            },
                            {
                                name: "销量",
                                width: 80,
                                fieldName: "saleCount"

                            },
                            {
                                name: "假销量",
                                width: 80,
                                fieldName: "falseSaleCount"

                            },
                            {
                                name: "操作",
                                width: 100,
                                rightFixed: true,
                                renderer: function (rindx, cindex, data) {
                                    var st = '<button class="btn btn-danger delete">删除</button>' +
                                        '<button class="btn btn-warning update">修改</button>'
                                    return st;
                                }
                            }
                        ],
                        paramFn: function () {
                            return thiz.getParams();
                        },

                        initSearch: function (query) {

                        },
                        tbars: [{
                            icon: 'fa fa-plus',
                            name: "新增分类",
                            handler: function (idx, data) {
                                thiz.addTexts();
                            }
                        }
                        ],
                        classEvents: [
                            {
                                className: "delete",
                                handler: function (idx, data) {
                                    thiz.delete(data);
                                }
                            },
                            {
                                className: "update",
                                handler: function (idx, data) {
                                    thiz.update(data);
                                }
                            },
                        ]
                    });
                this.list = list;
            },

            /** 删除 **/
            delete: function (data) {
                var thiz = this;
                var id = data.id;
                util.request({
                    confirm: true,
                    msg: "确定要删除?",
                    url: serverHost + "/goods/delete.json",
                    params: {
                        id: id
                    },
                    success: function (resp) {
                        if (resp.success) {
                            messageTool.success("删除成功!");
                            thiz.list.reloadPageData();
                        } else {
                            messageTool.error("此记录已不存在");
                            thiz.list.reloadPageData();
                        }
                    }
                });
            },
            /** 新增 **/
            addTexts: function () {
                var thiz = this;
                var win = winTool.create({
                    title: "添加新商品",
                    height: 700,
                    width: 800,
                    showCancel: true,
                    okName: "保存",
                    cancelName: "关闭",
                    type: "selector",
                    selector: "#dialog",
                    okFn: function (win) {
                        var name = win.find("#name").val(),
                            price1 = win.find("#price1").val(),
                            price2 = win.find("#price2").val(),
                            price3 = win.find("#price3").val(),
                            falseSaleCount = win.find("#falseSaleCount").val(),
                            picture = win.find("#picture").data("url"),
                            description = win.find("#description").val();

                        var flag = true;
                        if (!name) {
                            messageTool.error("必须输入名称!");
                            flag = false;
                        } else {
                            if (name.length > 50) {
                                messageTool.error("名称不能超过50个字!");
                                flag = false;
                            }
                        }
                        if (!picture) {
                            messageTool.error("请上传图片!");
                            flag = false;
                        }

                        if (flag) {
                            util.request({
                                confirm: true,
                                msg: "确定要添加?",
                                url: serverHost + "/goods/addOrUpdate.json",
                                params: {
                                    name: name,
                                    price1: price1,
                                    price2: price2,
                                    price3: price3,
                                    falseSaleCount: falseSaleCount,
                                    picture: picture,
                                    description: description
                                },
                                success: function (resp) {
                                    if (resp.success) {
                                        messageTool.success("添加成功");
                                        win.close();
                                        thiz.list.reloadPageData();
                                    } else {
                                        messageTool.error("添加失败");
                                    }
                                }
                            });
                        }
                    }
                });
                thiz.rendenWin(win);
            },

            /** 更新 **/
            update: function (data) {
                var thiz = this,
                    id = data.id;
                util.ajax({
                    url: serverHost + "/goods/detail.json",
                    params: {
                        id: id
                    },
                    success: function (resp) {
                        if (resp.success) {
                            var news = resp.model;
                            var win = winTool.create({
                                title: "更新商品信息",
                                height: 700,
                                width: 1500,
                                showCancel: true,
                                okName: "保存",
                                cancelName: "关闭",
                                type: "selector",
                                selector: "#dialog",
                                okFn: function (win) {
                                    var id = win.find("#id").val(),
                                        name = win.find("#name").val(),
                                        price1 = win.find("#price1").val(),
                                        price2 = win.find("#price2").val(),
                                        price3 = win.find("#price3").val(),
                                        falseSaleCount = win.find("#falseSaleCount").val(),
                                        picture = win.find("#picture").data("url"),
                                        description = win.find("#description").val();

                                    var flag = true;
                                    if (!name) {
                                        messageTool.error("必须输入名称!");
                                        flag = false;
                                    } else {
                                        if (name.length > 50) {
                                            messageTool.error("名称不能超过50个字!");
                                            flag = false;
                                        }
                                    }
                                    if (!picture) {
                                        messageTool.error("请上传图片!");
                                        flag = false;
                                    }

                                    if (flag) {
                                        util.request({
                                            confirm: true,
                                            msg: "确定要更新?",
                                            url: serverHost + "/goods/addOrUpdate.json",
                                            params: {
                                                id: id,
                                                name: name,
                                                price1: price1,
                                                price2: price2,
                                                price3: price3,
                                                falseSaleCount: falseSaleCount,
                                                picture: picture,
                                                description: description
                                            },
                                            success: function (resp) {
                                                if (resp.success) {
                                                    messageTool.success("更新成功");
                                                    win.close();
                                                    thiz.list.reloadPageData();
                                                } else {
                                                    messageTool.error("更新失败");
                                                }
                                            }
                                        });
                                    }
                                }
                            });
                            /** 图片 **/
                            thiz.rendenWin(win);
                            /** 赋值 **/
                            win.find("#id").val(news.id);
                            win.find("#name").val(news.name);
                            win.find("#price1").val(news.price1);
                            win.find("#price2").val(news.price2);
                            win.find("#price3").val(news.price3);
                            win.find("#falseSaleCount").val(news.falseSaleCount);
                            win.find("#description").val(news.description);
                            win.find("#picture").attr("src", news.filePath);
                            win.find("#picture").data("url", news.picture);
                        } else {
                            messageTool.error("此信息已不存在!");
                        }
                    }
                });
            },
            /** 图片处理 **/
            rendenWin: function (win) {
                /** 上传图片 **/
                win.find(".infos-images").on("click", function () {
                    imageUploadTool.init({
                        title: "上传图片",
                        attach: 5,
                        acceptedFiles: ".png,.jpg,.jpeg",
                        maxFiles: 1, //图片个数
                        extData: {
                            //size: "100x100"
                        },
                        callback: function (images) {
                            var img = images.length > 0 ? images[0] : {};
                            $(".picUrl").attr("src", img.fileUrl);
                            $(".picUrl").data("url", img.fileName);
                        }
                    });
                });
                // 删除 图片
                win.find(".uploadImg").on("click", ".remove-img", function () {
                    $(".picUrl").attr("src", "");
                    $(".picUrl").data("url", "");
                });

            },

            getParams: function () {
                return {
                    id: $(".sid").val(), //ID
                    title: $(".stitle").val(), //标题
                };
            }
        };
        return userList;
    });
 