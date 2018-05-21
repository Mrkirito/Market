define(['jquery',
    'app/common/util',
    'app/common/advanceListTool',
    'app/common/winTool',
    'app/common/dateTool',
    'app/common/messageTool',
    'app/common/imageUploadTool',
    'app/common/editorTool',
    'app/common/imageView',
    'app/shop/goods_price',
    'app/shop/express_price',
    'app/shop/orders_price'
    ],

    function ($, util, listTool, winTool, dateTool, messageTool, imageUploadTool, editorTool, _, goods_price, express, ordersPrice) {
        return {
            /** 列表 **/
            list: function () {
                var thiz = this,
                    list = listTool.create({
                        title: "商品列表",
                        selector: ".data-list",
                        remote: true,
                        //hideColumnBorder: true,
                        //multiSelect: true,
                        /** checkbox **/
                        //showCheckbox: true,
                        width: 640,
                        height: 600,
                        url: serverHost + "/shopGoods/list.json",
                        orderBy: "id desc",
                        columns: [
                            {
                                name: "id",
                                width: "20",
                                sort: true,
                                sortName: "id",
                                fieldName: "id"
                            },
                            {
                                name: "标题",
                                fieldName: "title"
                            },
                            {
                                name: "名称",
                                fieldName: "name"
                            },
                            {
                                name: "简称",
                                fieldName: "shortName"
                            },
                            {
                                name: "状态",
                                fieldName: "status",
                                renderer: function (row, col, data, value) {
                                    if (value == 1) {
                                        return '<span class="label label-sm label-success">正常</span>';
                                    } else if (value == 2) {
                                        return '<span class="label label-sm label-danger">补货中</span>';
                                    } else if (value == 3) {
                                        return '<span class="label label-sm label-info">售罄</span>';
                                    } else if (value == 4) {
                                        return '<span class="label label-sm label-warning">热销中</span>';
                                    }
                                }
                            },
                            {
                                name: "上下线",
                                fieldName: "isOnline",
                                renderer: function (row, col, data, value) {
                                    if (value == true) {
                                        return '<span class="label label-sm label-success">已上线</span>';
                                    } else {
                                        return '<span class="label label-sm label-danger">已下线</span>';
                                    }
                                }
                            },
                            {
                                name: "限购",
                                fieldName: "isLimitPurchase",
                                renderer: function (row, col, data, value) {
                                    if (value == true) {
                                        return '<span class="label label-sm label-success">是</span>';
                                    } else {
                                        return '<span class="label label-sm label-danger">否</span>';
                                    }
                                }
                            },
                            {
                                name: "原价",
                                fieldName: "price1"
                            },
                            {
                                name: "现价",
                                fieldName: "price2"
                            },
                            {
                                name: "折扣价",
                                fieldName: "price3"
                            },
                            {
                                name: "销量",
                                fieldName: "saleCount",
                            },
							{
                                name: "最高销量",
                                fieldName: "maxSaleCount",
                            },
                            {
                                name: "排序",
                                fieldName: "sort",
                            },
                            {
                                name: "是否置顶",
                                fieldName: "head",
                                renderer: function (row, col, data, value) {
                                    if (value == 1) {
                                        return '<span class="label label-sm label-success">是</span>';
                                    } else {
                                        return '<span class="label label-sm label-danger">否</span>';
                                    }
                                }
                            },
                            {
                                name: "操作",
                                rightFixed: true,
                                width: "150",
                                renderer: function (rindx, cindex, data) {
                                    var st = '';
                                    var isOnline = data.isOnline;
                                    if(isOnline) st += '<a class="red offline" href="javascript:void(0);" title="下线"> <i class="fa fa-arrow-down bigger-140 "></i> </a>&nbsp;';
                                    else st += '<a class="green online" href="javascript:void(0);" title="上线"> <i class="fa fa-arrow-up bigger-140 "></i> </a>&nbsp;';
                                    st += '<a class="green update" href="javascript:void(0);" title="编辑"> <i class="fa fa-pencil bigger-140 "></i> </a>&nbsp;';
                                    st += '<a class="blue prices" href="javascript:void(0);" title="商品数量价格" > <i class="fa fa-money bigger-140"></i> </a>&nbsp;';
                                    st += '<a class="blue express_prices" href="javascript:void(0);" title="商品快递价格" > <i class="fa fa-cc-mastercard bigger-140"></i> </a>&nbsp;';;
                                    st += '<a class="blue orders_prices" href="javascript:void(0);" title="订单费用计算" > <i class="fa fa-calculator bigger-140"></i> </a>&nbsp;';;
                                    return st;
                                }
                            }],

                        /** 按钮 **/
                        tbars: [{
                            icon: 'fa fa-plus',
                            name: "添加新商品",
                            rightCode: "add",
                            handler: function (idx, data) {
                                thiz.add();
                            }
                        }],

                        /** 操作 **/
                        classEvents: [
                            {
                                className: "offline",
                                rightCode: "offline",
                                handler: function (idx, data) {
                                    thiz.online(data, 0);
                                }
                            },
                            {
                                className: "online",
                                rightCode: "online",
                                handler: function (idx, data) {
                                    thiz.online(data, 1);
                                }
                            },
                            {
                                className: "update",
                                rightCode: "update",
                                handler: function (idx, data) {
                                    thiz.update(data);
                                }
                            },
                            {
                                className: "prices",
                                rightCode: "prices",
                                handler: function (idx, data) {
                                    goods_price.priceListWin(data);
                                }
                            },
                            {
                                className: "express_prices",
                                rightCode: "express_prices",
                                handler: function (idx, data) {
                                    express.exprssPriceListWin(data);
                                }
                            },
                            {
                                className: "orders_prices",
                                rightCode: "orders_prices",
                                handler: function (idx, data) {
                                    ordersPrice.ordersPriceListWin(data);
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
                    $('#name').val('');
                    $("#status").val("");
                });
            },

            /** 搜索条件 **/
            getParams: function () {
                return {
                    name: $('#name').val(),
                    status: $("#status").val()
                }
            },

            /** 添加或更新参数 **/
            checkForm: function (win) {
                var id = win.find('#id').val(),
                    title = win.find('.title').val(),
                    name = win.find('.name').val(),
                    shortName = win.find('.shortName').val(),
                    unit = win.find('.unit').val(),
                    weight = win.find('.weight').val(),
                    status = win.find('.status').val(),
                    isLimitPurchase = win.find('.isLimitPurchase').val(),
                    price1 = win.find('.price1').val(),
                    price2 = win.find('.price2').val(),
                    price3 = win.find('.price3').val(),
                    maxBuyCount = win.find('.maxBuyCount').val(),
                    falseSaleCount = win.find('.falseSaleCount').val(),
                    maxSaleCount = win.find('.maxSaleCount').val(),
                    //picture = win.find('.picture').val(),
                    picture = win.find(".picUrl").data("url"),
                    guideSharingWord = win.find('.guideSharingWord').val(),
                    shareTitle = win.find('.shareTitle').val(),
                    shareDesc = win.find('.shareDesc').val(),
                    shareUrl = win.find('.shareUrl').val(),
                    //sharePic = win.find('.sharePic').val(),
                    sharePic = win.find(".picUrl2").data("url"),
                    //description = win.find(".description").val();
                    description = win.edit.getValue(),
                    head=win.find('select[name=head]').val(),
                	hasMark=win.find('select[name=hasMark]').val(),
                	markText=win.find('.markText').val(),
                	sort=win.find('.sort').val(),
                	minBuyCount=win.find('.minBuyCount').val(),
               	 	picture2 = win.find(".picUrl3").data("url");

                var flag = true;
                if (!title) {
                    messageTool.error("必须输入标题!");
                    flag = false;
                }
				if (title.length > 8) {
                    messageTool.error("标题不能超过8个字!");
                    flag = false;
                }
                if (!name) {
                    messageTool.error("必须输入名称!");
                    flag = false;
                }
				if (name.length > 20) {
                    messageTool.error("名称不能超过20个字!");
                    flag = false;
                }
                if (!shortName) {
                    messageTool.error("必须输入简称!");
                    flag = false;
                }
                if (!unit) {
                    messageTool.error("必须输入单位!");
                    flag = false;
                }
                if (!weight) {
                    messageTool.error("必须输入重量!");
                    flag = false;
                }
                if (!price1) {
                    messageTool.error("必须输入商品价格!");
                    flag = false;
                }
                if (!price2) {
                    messageTool.error("必须输入商品现价!");
                    flag = false;
                }
                if (!maxBuyCount) {
                    messageTool.error("必须输入单次最大购买量!");
                    flag = false;
                }
                if (!picture) {
                    messageTool.error("必须输入商品图片!");
                    flag = false;
                }
                if (!guideSharingWord) {
                    messageTool.error("必须输入引导分享文案!");
                    flag = false;
                }
                if (!shareTitle) {
                    messageTool.error("必须输入分享标题!");
                    flag = false;
                }
                if (!shareDesc) {
                    messageTool.error("必须输入分享描述!");
                    flag = false;
                }
                if (!shareUrl) {
                    messageTool.error("必须输入分享链接!");
                    flag = false;
                }
                if (!sharePic) {
                    messageTool.error("必须输入商品图片");
                    flag = false;
                }
                if (!description) {
                    messageTool.error("必须输入商品描述!");
                    flag = false;
                }

                if (flag) return {
                    id: id,
                    title: title,
                    name: name,
                    shortName: shortName,
                    unit: unit,
                    weight: weight,
                    status: status,
                    isLimitPurchase: isLimitPurchase,
                    price1: price1,
                    price2: price2,
                    price3: price3,
                    maxBuyCount: maxBuyCount,
                    falseSaleCount: falseSaleCount,
                    maxSaleCount: maxSaleCount,
                    picture: picture,
                    guideSharingWord: guideSharingWord,
                    shareTitle: shareTitle,
                    shareDesc: shareDesc,
                    shareUrl: shareUrl,
                    sharePic: sharePic,
                    description: description,
                    head:head,
                    hasMark:hasMark,
                    markText:markText,
                    sort:sort,
                    minBuyCount:minBuyCount,
                    picture2:picture2
                };
                else return flag;
            },

            /** 新增 **/
            add: function () {
                var thiz = this;
                var win = winTool.create({
                    title: "添加新商品",
                    height: 600,
                    width: 1200,
                    showCancel: true,
                    okName: "保存",
                    cancelName: "关闭",
                    type: "selector",
                    selector: "#dialog",
                    okFn: function (win) {
                        var params = thiz.checkForm(win);
                        if (typeof params != 'boolean') {
                            util.request({
                                confirm: true,
                                msg: "确定要添加?",
                                url: serverHost + "/shopGoods/addOrUpdate.json",
                                params: params,
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

            /** 上线、下线 **/
            online: function (data, online) {
                var thiz = this;
                var id = data.id;
                util.request({
                    confirm: true,
                    msg: online==1?"确定要上线?":"确定要下线?",
                    url: serverHost + "/shopGoods/online.json",
                    params: {
                        id: id,
                        online : online
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

            /** 更新 **/
            update: function (data) {
                var thiz = this,
                    id = data.id;
                util.ajax({
                    url: serverHost + "/shopGoods/detail.json",
                    params: {
                        id: id
                    },
                    success: function (resp) {
                        if (resp.success) {
                            var detail = resp.model;
                            var win = winTool.create({
                                title: "更新商品信息",
                                height: 600,
                                width: 1200,
                                showCancel: true,
                                okName: "保存",
                                cancelName: "关闭",
                                type: "selector",
                                selector: "#dialog",
                                okFn: function (win) {
                                    var params = thiz.checkForm(win);
                                    if (typeof params != 'boolean') {
                                        util.request({
                                            confirm: true,
                                            msg: "确定要更新?",
                                            url: serverHost + "/shopGoods/addOrUpdate.json",
                                            params: params,
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

                            thiz.rendenWin(win);
                            /** 赋值 **/
                            win.find("#id").val(detail.id);
                            win.find(".title").val(detail.title);
                            win.find(".name").val(detail.name);
                            win.find(".shortName").val(detail.shortName);
                            win.find(".unit").val(detail.unit);
                            win.find(".weight").val(detail.weight);
                            win.find(".status").val(detail.status);
                            win.find(".isLimitPurchase").val(true==detail.isLimitPurchase?1:0);
                            win.find(".price1").val(detail.price1);
                            win.find(".price2").val(detail.price2);
                            win.find(".price3").val(detail.price3);
                            win.find(".maxBuyCount").val(detail.maxBuyCount);
                            win.find(".falseSaleCount").val(detail.falseSaleCount);
                            win.find(".maxSaleCount").val(detail.maxSaleCount);
                            //win.find(".picture").val(detail.picture);
                            win.find(".picUrl").attr("src", detail.picturePath);
                            win.find(".picUrl").attr("data-url", detail.picture);
                            win.find(".guideSharingWord").val(detail.guideSharingWord);
                            win.find(".shareTitle").val(detail.shareTitle);
                            win.find(".shareDesc").val(detail.shareDesc);
                            win.find(".shareUrl").val(detail.shareUrl);
                            //win.find(".sharePic").val(detail.sharePic);
                            win.find(".picUrl2").attr("src", detail.sharePicPath);
                            win.find(".picUrl2").attr("data-url", detail.sharePic);
                            //win.find(".description").val(detail.description);
                            win.edit.setValue(detail.description);
                            /**20161115商城改版begin**/
                            var hasMark=detail.hasMark;
                            if(hasMark==1){
                            	win.find('.markTextDiv').show();
                            }else{
                            	win.find('.markTextDiv').hide();
                            }
                            win.find('select[name=head]').val(detail.head);
                            win.find('select[name=hasMark]').val(hasMark);
                            win.find('.markText').val(detail.markText);
                            win.find('.sort').val(detail.sort);
                            win.find('.minBuyCount').val(detail.minBuyCount);
                            win.find('select[name=hasMark]').click(function(){
                            	if($(this).val()==1){
                                	win.find('.markTextDiv').show();
                                }else{
                                	win.find('.markTextDiv').hide();
                                }
                            });
                            win.find(".picUrl3").attr("src", detail.picture2Path);
                            win.find(".picUrl3").attr("data-url", detail.picture2);
                            /**20161115商城改版end**/
                        } else {
                            messageTool.error("更新失败!");
                        }
                    }
                });
            },

            /** 上传图片 **/
            rendenWin: function (win) {
                win.edit = editorTool.init({
                    selector: win.find("#description"),
                    width: 800,
                    heigth: 300,
                    minHeight: 700,
                    lang: 'en-US'
                });
                win.find('.note-editor').css('height', '300px');

                /** 商品详情图片 **/
                win.find(".infos-images").on("click", function () {
                    imageUploadTool.init({
                        title: "上传图片",
                        attach: 10,
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
                /**商品列表图片**/
                win.find(".infos-images3").on("click", function () {
                    imageUploadTool.init({
                        title: "上传图片",
                        attach: 10,
                        acceptedFiles: ".png,.jpg,.jpeg",
                        maxFiles: 1, //图片个数
                        extData: {
                            //size: "100x100"
                        },
                        callback: function (images) {
                            var img = images.length > 0 ? images[0] : {};
                            $(".picUrl3").attr("src", img.fileUrl);
                            $(".picUrl3").data("url", img.fileName);
                        }
                    });
                });
                // 删除 图片
                win.find(".uploadImg3").on("click", ".remove-img", function () {
                    $(".picUrl3").attr("src", "");
                    $(".picUrl3").data("url", "");
                });

                
                /** 分享图片 **/
                win.find(".infos-images2").on("click", function () {
                    imageUploadTool.init({
                        title: "上传图片",
                        attach: 10,
                        acceptedFiles: ".png,.jpg,.jpeg",
                        maxFiles: 1, //图片个数
                        extData: {
                            //size: "100x100"
                        },
                        callback: function (images) {
                            var img = images.length > 0 ? images[0] : {};
                            $(".picUrl2").attr("src", img.fileUrl);
                            $(".picUrl2").data("url", img.fileName);
                        }
                    });
                });
                // 删除 图片
                win.find(".uploadImg2").on("click", ".remove-img", function () {
                    $(".picUrl2").attr("src", "");
                    $(".picUrl2").data("url", "");
                });
            },
        };
    });