define(['jquery', 'app/common/util',
        'app/common/winTool',
        'app/common/listTool',
        'app/common/messageTool',
        'app/common/formTool',
        'app/right/funcTool'
],
    function ($, util, winTool, listTool, messageTool) {
        return {
            priceListWin: function (data) {
                var thiz = this,
                    goodsId = data.id,
                    name = data.name,
                    win = winTool.create({
                        title: '商品数量价格(' + name + ')',
                        showOk: false,
                        cancelName: "关闭",
                        selector: ".goods_price_list",
                        type: 'selector',
                        width: 900,
                        height: 500
                    });

                thiz.createListTool(win, goodsId);
            },

            createListTool: function (win, goodsId) {
                var thiz = this,
                    priceListSelector = win.find(".price_list"),
                    priceToolbar = win.find(".price_toolbar"),
                    url = serverHost + "/goodsPrice/list.json?goodsId=" + goodsId,
                    itemListTool = listTool.create({
                        selector: priceListSelector,
                        toolbarSelector: priceToolbar,
                        remote: true,
                        showCheckbox: false,
                        url: url,
                        columns: [
                            {
                                name: "最小值",
                                fieldName: "minCount"
                            },
                            {
                                name: "最大值",
                                fieldName: "maxCount"
                            },
                            {
                                name: "价格",
                                fieldName: "price",
                            },
                            {
                                name: "说明",
                                fieldName: "description",
                            },
                            {
                                name: "操作",
                                renderer: function (rindx, cindex, data) {
                                    return '<div class="    action-buttons"> ' +
                                        '<a class="green update-info" href="javacript:void(0);" title="编辑"> <i class="fa fa-pencil bigger-130 "></i> </a> ' +
                                        '<a class="red delete-info" href="javacript:void(0);" title="删除" data-rel="25"> <i class="fa fa-trash-o bigger-130"></i> </a> </div>';
                                }
                            }],
                        tbars: [{
                            icon: 'fa fa-plus',
                            name: "新增",
                            //rightCode: 'add_goods_price',
                            handler: function (e, btn, tool) {
                                thiz.addPirce(goodsId);
                            }
                        }],
                        classEvents: [{
                            className: 'update-info',
                            //rightCode: "update_goods_price",
                            handler: function (rowIndex, data) {
                                thiz.updatePirce(data);
                            }
                        }, {
                            className: 'delete-info',
                            //rightCode: "delete_goods_price",
                            handler: function (idx, data) {
                                thiz.deletePirce(data);
                            }
                        }],
                    });

                thiz.priceList = itemListTool;
            },

            /** 添加或更新商品价格参数 **/
            checkPriceForm: function (win, goodsId) {
                var id = win.find('#price_id').val(),
                    minCount = win.find('.minCount').val(),
                    maxCount = win.find('.maxCount').val(),
                    price = win.find('.price').val(),
                    description = win.find('.price_description').val();

                var flag = true;
                if (!minCount) {
                    messageTool.error("必须输入最小值!");
                    flag = false;
                }
                if (!maxCount) {
                    messageTool.error("必须输入最大值!");
                    flag = false;
                }
                if (parseInt(minCount) > parseInt(maxCount)) {
                    messageTool.error("最小值必须小于最大值!");
                    flag = false;
                }
                if (!price) {
                    messageTool.error("必须输入价格!");
                    flag = false;
                }

                if (flag) return {
                    id: id,
                    minCount: minCount,
                    maxCount: maxCount,
                    price: price,
                    description: description,
                    goodsId: goodsId
                };
                else return flag;
            },

            /** 添加更新窗口 **/
            createWin: function (title, goodsId) {
                var thiz = this,
                    win = winTool.create({
                        title: title,
                        okName: "保存",
                        cancelName: "关闭",
                        type: 'selector',
                        selector: ".goods_price_add_dialog",
                        width: 800,
                        height: 250,
                        okFn: function (win) {
                            thiz.saveFuncData(win, goodsId);
                        }
                    });
                return win;
            },

            /** 添加 **/
            addPirce: function (goodsId) {
                var thiz = this,
                    win = thiz.createWin("新增", goodsId);
            },

            /** 更新 **/
            updatePirce: function (data) {
                var thiz = this,
                    id = data.id,
                    goodsId = data.goodsId;
                    win = thiz.createWin("修改", goodsId);
                util.ajax({
                    url: serverHost + "/goodsPrice/detail.json",
                    params: {
                        id: id
                    },
                    success: function (resp) {
                        if (resp.success) {
                            var detail = resp.model;
                            /** 赋值 **/
                            win.find("#price_id").val(detail.id);
                            win.find(".minCount").val(detail.minCount);
                            win.find(".maxCount").val(detail.maxCount);
                            win.find(".price").val(detail.price);
                            win.find(".price_description").val(detail.description);
                        } else {
                            messageTool.error("更新失败!");
                        }
                    }
                });

            },

            /** 添加或更新保存数据 **/
            saveFuncData: function (win, goodsId) {
                var thiz = this;
                var params = thiz.checkPriceForm(win, goodsId);
                if (typeof params != 'boolean') {
                    util.request({
                        confirm: true,
                        msg: "确定要添加?",
                        url: serverHost + "/goodsPrice/addOrUpdate.json",
                        params: params,
                        success: function (resp) {
                            if (resp.success) {
                                messageTool.success("添加成功");
                                win.close();
                                thiz.priceList.loadPageData();
                            } else {
                                messageTool.error("添加失败");
                            }
                        }
                    });
                }
            },

            /** 删除 **/
            deletePirce: function (data) {
                var thiz = this;
                var id = data.id;
                util.request({
                    confirm: true,
                    msg: "确定要删除?",
                    url: serverHost + "/goodsPrice/delete.json?id=" + id,
                    params: {
                        id: id,
                        status: status
                    },
                    success: function (resp) {
                        if (resp.success) {
                            messageTool.success("操作成功!");
                            thiz.priceList.loadPageData();
                        } else {
                            messageTool.error("操作失败！");
                            thiz.priceList.loadPageData();
                        }
                    }
                });
            }
        };
    });