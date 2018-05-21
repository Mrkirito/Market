define(['jquery', 'app/common/util', 'app/common/advanceListTool', 'app/common/winTool',
        'app/common/dateTool', 'app/common/messageTool', 'app/common/imageUploadTool', 'app/common/editorTool', 'app/common/commonTool',
        'app/common/imageView'],
    function ($, util, listTool, winTool, dateTool, messageTool, imageUploadTool, editorTool, commonTool) {
        var userList = {
            list: function () {
                var thiz = this,
                    list = listTool.create({
                        title: "商品",
                        selector: ".data-list",
                        remote: true,
                        url: serverHost + "/orders/list.json",
                        width: 600,
                        height: 600,
                        columns: [
                            {
                                name: "ID",
                                width: 60,
                                fieldName: "id"
                            },
                            {
                                name: "订单id",
                                width: 120,
                                fieldName: "orderId"
                            },
                            {
                                name: "支付状态",
                                width: 40,
                                fieldName: "status",
                                renderer: function (row, col, data, value) {
                                    // 图文
                                    if (value == 5) {
                                        return "<span class='bg-success'>支付成功</span>";
                                    } else {
                                        return "<span class='bg-warning'>待支付</span>";
                                    }
                                }
                            },
                            {
                                name: "下单时间",
                                width: 120,
                                fieldName: "createTime",
                                sort: true,
                                sortName: "t1.create_time",
                                renderer: function (row, col, data, value) {
                                    return util.dateFormat2(value, 'YYYY-MM-DD HH:mm:ss');
                                }
                            },
                            {
                                name: "用户id",
                                width: 80,
                                fieldName: "userId"

                            },
                            {
                                name: "注册手机号",
                                width: 80,
                                fieldName: "registPhone"

                            },
                            {
                                name: "商品名称",
                                width: 80,
                                fieldName: "goodsName"

                            },
                            {
                                name: "购买数量",
                                width: 80,
                                fieldName: "quantity"

                            },
                            {
                                name: "支付方式",
                                width: 80,
                                fieldName: "payType"

                            },
                            {
                                name: "支付方式",
                                width:80,
                                fieldName: "payType",
                                renderer : function(row, col, data,value) {
                                    // 图文
                                    if(value == 'WX'){
                                        return "<span class='bg-success'>微信</span>";
                                    } else if(value == 'ALIPAY'){
                                        return "<span class='bg-warning'>支付宝</span>";
                                    }
                                }
                            },
                            {
                                name: "付款金额",
                                width: 80,
                                fieldName: "money"

                            },
                            {
                                name: "收货人",
                                width: 80,
                                fieldName: "name"

                            },
                            {
                                name: "收货人手机号",
                                width: 80,
                                fieldName: "phone"

                            },
                            {
                                name: "填写时间",
                                width: 120,
                                fieldName: "addressTime",
                                sort: true,
                                sortName: "t2.createTime",
                                renderer: function (row, col, data, value) {
                                    return util.dateFormat2(value, 'YYYY-MM-DD HH:mm:ss');
                                }
                            },
                            {
                                name: "地区",
                                width: 80,
                                fieldName: "area"

                            },
                            {
                                name: "详细地址",
                                width: 80,
                                fieldName: "address"

                            },
                            {
                                name: "快递号",
                                width: 80,
                                fieldName: "expressNo"

                            },
                            {
                                name: "操作",
                                width: 100,
                                rightFixed: true,
                                renderer: function (rindx, cindex, data) {
                                    var st = '<button class="btn btn-warning update">更新快递号</button>';
                                    return st;
                                }
                            },
                        ],
                        paramFn: function () {
                            return thiz.getParams();
                        },
                        classEvents: [
                            {
                                className: "update",
                                handler: function (idx, data) {
                                    thiz.update(data);
                                }
                            },
                        ]
                    });
                this.list = list;
                /** 时间渲染 搜索 **/
                dateTool.renderDateTime(".sOnlineTime");
                /** 搜索 **/
                $(".search-action").on("click", function () {
                    thiz.list.reloadPageData(1);
                });
                /** 商品下拉框 **/
                commonTool.renderGoods({
                    msg : "商品",
                    selector : $(".goodsSelect"),
                    width : "100%",
                    paramFn : function() {
                        return {
							permission: true
                        };
                    },
                    listeners : {
                        change : function() {

                        }
                    }
                });
            },

            /** 更新快递号 **/
            update: function (data) {
                var thiz = this,
                    expressNo = data.expressNo,
                    orderId = data.orderId;
                $('#expressNo').val(expressNo);
                var win = winTool.create({
                    title: "更新快递号",
                    height: 100,
                    width: 500,
                    showCancel: true,
                    okName: "保存",
                    cancelName: "关闭",
                    type: "selector",
                    selector: "#dialog",
                    okFn: function (win) {
                        expressNo = win.find("#expressNoAdd").val(),
                        util.request({
                            confirm: true,
                            msg: "确定要更新?",
                            url: serverHost + "/orders/updateExpNo.json",
                            params: {
                                orderId : orderId,
                                expressNo : expressNo
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
                });
                /** 赋值 **/
                win.find('#expressNoAdd').val(expressNo);
            },

            getParams: function () {
                return {
                    goodsId : $('#goodsId').val(),
                    payType : $(".payType").val(),
                    status : $(".status").val(),
                    name : $(".name").val(),
                    phone : $(".phone").val(),
                    expressNo : $(".expressNo").val(),
                    createTime1 : $(".createTime1").val(),
                    createTime2 : $(".createTime2").val(),
                    addressTime1 : $(".addressTime1").val(),
                    addressTime2 : $(".addressTime2").val(),
                };
            }
        };
        return userList;
    });
 