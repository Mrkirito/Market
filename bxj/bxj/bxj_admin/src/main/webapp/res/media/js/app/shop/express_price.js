define(['jquery',
    'app/common/util',
    'app/common/winTool',
    'app/common/listTool',
    'app/common/messageTool',
    'app/right/funcTool'
    ],
    function ($, util, winTool, listTool, messageTool) {
        return {
            exprssPriceListWin: function (data) {
                var thiz = this,
                    goodsId = data.id,
                    name = data.name,
                    win = winTool.create({
                        title: '商品快递价格(' + name + ')',
                        showOk: false,
                        cancelName: "关闭",
                        selector: ".express_price_list",
                        type: 'selector',
                        width: 900,
                        height: 600
                    });

                thiz.createListTool(win, goodsId);
            },

            createListTool: function (win, goodsId) {
                var thiz = this,
                    priceListSelector = win.find(".express_price_list"),
                    priceToolbar = win.find(".express_price_toolbar"),
                    url = serverHost + "/goodsExpressPrice/list.json?goodsId=" + goodsId,
                    itemListTool = listTool.create({
                        selector: priceListSelector,
                        toolbarSelector: priceToolbar,
                        remote: true,
                        showCheckbox: true,
                        url: url,
                        columns: [
                            {
                                name: "地区",
                                fieldName: "area"
                            },
                            {
                                name: "首重（kg）",
                                fieldName: "firstHeavy"
                            },
                            {
                                name: "首重价格（元）",
                                fieldName: "firstHeavyPrice",
                            },
                            {
                                name: "超重后价格（元/kg）",
                                fieldName: "overHeavyPrice",
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
                        },{
                            icon: 'fa fa-minus',
                            name: "批量删除",
                            //rightCode: 'add_goods_price',
                            handler: function (e, btn, tool) {
                                thiz.deleteBatPirce(goodsId);
                            }
                        }
                        ],
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

                thiz.expressPriceList = itemListTool;
            },

            /** 添加或更新商品价格参数 **/
            checkPriceForm: function (win, goodsId) {
                var id = win.find('#express_price_id').val(),
                    //area = win.find('.area').val(),
                    firstHeavy = win.find('.firstHeavy').val(),
                    firstHeavyPrice = win.find('.firstHeavyPrice').val(),
                    overHeavyPrice = win.find('.overHeavyPrice').val();
                var area = "";
                $('input[name=area]').each(function(index, item) {
                    if(item.checked && false==item.disabled) area += item.value + ',';
                });
                if(null!=id && id.length>0) area = $('#areaEdit').val();
                var flag = true;
                if (!area) {
                    messageTool.error("必须输入地区!");
                    flag = false;
                }
                if (!firstHeavy) {
                    messageTool.error("必须输入首重!");
                    flag = false;
                }
                if (!firstHeavyPrice) {
                    messageTool.error("必须输入首重价格!");
                    flag = false;
                }
                if (!overHeavyPrice) {
                    messageTool.error("必须输入超重价格!");
                    flag = false;
                }

                if (flag) return {
                    id: id,
                    area: area,
                    firstHeavy: firstHeavy,
                    firstHeavyPrice: firstHeavyPrice,
                    overHeavyPrice: overHeavyPrice,
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
                        showCancel: false,
                        cancelName: "关闭",
                        type: 'selector',
                        selector: ".express_price_add_dialog",
                        width: 800,
                        height: 350,
                        okFn: function (win) {
                            thiz.saveFuncData(win, goodsId);
                        }
                    });

                /*var s = "<option value=''>请选择</option>";
                for(var i=0; i<datas_city.length; i++) {
                    var ac = datas_city[i].p;
                    s += "<option value='"+ac+"'>"+ac+"</option>";
                }*/

                $('.area').html('');
                if(title == '新增') thiz.checkBox(goodsId);
                win.find('.area').on('change',"input[name=select_all]",function(){
                	var isAllSelect=$(this).val()==1;
                	$(this).parent().find('input[type=checkbox]').each(function(i,o){
                		o=$(o);
                		var disabledCheck=o.prop('disabled');
                		if(!disabledCheck&&disabledCheck!='disabled'){
                			if(isAllSelect){
                				if(!o.is(':checked')){
                					o.prop('checked','checked');
                				}                				
                			}else{
                				if(o.is(':checked')){
                					o.prop('checked',false);
                				}else{
                					o.prop('checked','checked');
                				}                				
                			}
                		}
                	});
                });
                return win;
            },

            /** 省份复选框 **/
            checkBox: function(goodsId) {
                util.ajax({
                    url: serverHost + "/goodsExpressPrice/list.json?goodsId=" + goodsId +"&currentPage=1&pageSize=100",
                    success: function (resp) {
                        if (resp.success) {
                            var list = resp.model;
                            var datas_city = city_data.citylist;
                            var s = "";
                            for(var i=0; i<datas_city.length; i++) {
                                var ac = datas_city[i].p;
                                var flag = true;
                                $(list).each(function(index, item) {
                                    if(item.area == ac) {
                                        s += "<input type='checkbox' value='"+ac+"' name='area' style='width:19px;' checked disabled>" + ac;
                                        flag = false;
                                    }
                                });
                                if(flag) s += "<input type='checkbox' value='"+ac+"' name='area' style='width:19px;'>" + ac;
                                if(i==datas_city.length-1){
                                	s+="<input type='radio' value='1' name='select_all' style='width:19px;'>全选<input type='radio' value='0' name='select_all' style='width:19px;'>反选";
                                }
                                if(i!=0 && i%9==0) s+="<br>";
                            }
                            $('.area').html(s);
                        } else {
                            messageTool.error("更新失败!");
                        }
                    }
                });
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
                    url: serverHost + "/goodsExpressPrice/detail.json",
                    params: {
                        id: id
                    },
                    success: function (resp) {
                        if (resp.success) {
                            var detail = resp.model;
                            /** 赋值 **/
                            win.find("#express_price_id").val(detail.id);
                            win.find(".area").html("<input type='input' disabled value='"+detail.area+"' id='areaEdit'>");
                            win.find(".firstHeavy").val(detail.firstHeavy);
                            win.find(".firstHeavyPrice").val(detail.firstHeavyPrice);
                            win.find(".overHeavyPrice").val(detail.overHeavyPrice);
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
                        url: serverHost + "/goodsExpressPrice/addOrUpdate.json",
                        params: params,
                        success: function (resp) {
                            if (resp.success) {
                                messageTool.success("添加成功");
                                win.close();
                                thiz.expressPriceList.loadPageData();
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
                    url: serverHost + "/goodsExpressPrice/delete.json?id=" + id,
                    params: {
                        id: id,
                        status: status
                    },
                    success: function (resp) {
                        if (resp.success) {
                            messageTool.success("操作成功!");
                            thiz.expressPriceList.loadPageData();
                        } else {
                            messageTool.error("操作失败！");
                            thiz.expressPriceList.loadPageData();
                        }
                    }
                });
            },

            // 批量删除
            deleteBatPirce: function(data) {
                var thiz = this;
                var selected = thiz.expressPriceList.getSelected() || [],
                    idList = [];
                if (selected.length == 0) {
                    messageTool.error("没有选中任何数据！");
                    return;
                }
                $(selected).each(function (idx, data) {
                    idList.push(data.id);
                });
                util.request({
                    confirm: true,
                    msg: "确定要删除?",
                    url: serverHost + "/goodsExpressPrice/delete_bat.json",
                    params: {
                        ids: idList.toString()
                    },
                    success: function (resp) {
                        if(resp.success) {
                            messageTool.success("删除成功");
                            thiz.expressPriceList.loadPageData();
                        } else {
                            messageTool.error(resp.msg || "此记录已不存在");
                            thiz.expressPriceList.loadPageData();
                        }
                    }
                });
            }
        };
    });