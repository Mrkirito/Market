define(['jquery','app/common/util','app/common/listTool','app/common/winTool','app/common/dateTool','app/common/messageTool','app/common/distpicker'],
    function($, util, listTool, winTool, dateTool,messageTool,distpicker) {
        return {
            list:function(){
                var thiz=this,
                    list=listTool.create({
                        title: "在线预约信息",
                        toolbarSelector:"div.product_info_tbars",
                        selector: ".data-list",
                        remote: true,
                        url: serverHost+"/insureBook/list.json",
                        width: 700,
                        height: 500,
                        showPagebar:true,
                        showCheckbox:false,
                        pageSize:15,
                        columns : [
                            {
                                name : "产品名称",
                                width : 150,
                                fieldName : "productName"
                            },
                            {
                                name : "产品ID",
                                width : 80,
                                fieldName : "productId"
                            },
                            {
                                name : "企业名称",
                                width : 150,
                                fieldName : "firmName"
                            },
                            {
                                name : "客户姓名",
                                width : 100,
                                fieldName : "customerName"
                            },
                            {
                                name : "客户手机号",
                                width : 100,
                                fieldName : "customerPhone"
                            },
                            {
                                name : "客户ip",
                                width : 120,
                                fieldName : "customerIp"
                            },
                            {
                                name : "客户备注",
                                width : 120,
                                fieldName : "customerMark"

                            },
                            {
                                name : "会员id",
                                width : 120,
                                fieldName : "userId"

                            },
                            {
                                name : "订单状态",
                                width : 80,
                                fieldName : "orderState",
                                renderer : function(row, col, data,
                                                    value) {
                                    if (value == 0) {
                                        return "未处理";
                                    }
                                    if (value == 1) {
                                        return "已处理";
                                    }
                                    return null;
                                }
                            },
                            {
                                name : "处理意见",
                                width : 120,
                                fieldName : "dealMsg"

                            },
                            {
                                name : "创建时间",
                                width : 130,
                                fieldName : "createTime",
                                renderer : function(row, col, data,
                                                    value) {
                                    return util.dateFormat2(value,
                                        "");
                                }
                            },
                            {
                                name : "修改时间",
                                width : 130,
                                fieldName : "updateTime",
                                renderer : function(row, col, data,
                                                    value) {
                                    return util.dateFormat2(value,
                                        "");
                                }
                            },
                            {
                                name : "操作",
                                width : 120,
                                rightFixed : true,
                                renderer : function(rindx, cindex,
                                                    data) {
                                    var st = '<button class="btn btn-danger delete">删除</button>' +
                                        '<button class="btn btn-warning update">修改</button>'
                                    return st;
                                }
                            } ],
                        paramFn: function () {
                            return thiz.getParams();
                        },
                        initSearch: function (query) {},
                        /*tbars: [{
                            icon: 'fa fa-plus',
                            name: "新增",
                            handler: function (idx, data) {
                                thiz.addTexts();
                            }
                        }

                        ],*/
                        classEvents : [
                            {
                                className : "delete",
                                handler: function (idx, data) {
                                    thiz.delete1(data);
                                }
                            },
                            {
                                className : "update",
                                handler: function (idx, data) {
                                    thiz.update(data);
                                }
                            },

                        ]
                    });
                this.list = list;
                $("button.search-action").on("click", function() {
                    thiz.list.loadPageData(1);
                });
                $("button.clear-action").on("click", function() {
                    document.getElementById("queryForm").reset();
                });
                dateTool.renderDate(".form-date","");
            },
            // 删除
            delete1: function(data){
                var thiz = this;
                var id = data.id;
                util.request({
                    confirm: true,
                    msg: "确定要删除?",
                    url: serverHost + "/insureBook/delete.json",
                    params: {
                        id: id
                    },
                    success: function (resp) {
                        if(resp.success) {
                            messageTool.success("删除成功!");
                            thiz.list.loadPageData();
                        } else {
                            messageTool.error("此记录已不存在");
                            thiz.list.loadPageData();
                        }
                    }
                });
            },
            // 修改
            update : function(data){
                var thiz = this,
                    id = data.id;
                util.ajax({
                    url: serverHost + "/insureBook/query.json",
                    params: {
                        id : id
                    },
                    success: function (resp) {
                        if(resp.success) {
                            var insureBook = resp.model;
                            var win = winTool.create({
                                title: "修改预约信息",
                                height: 700,
                                width: 800,
                                showCancel: true,
                                okName: "保存",
                                cancelName: "关闭",
                                type: "selector",
                                selector: ".video-tpl",
                                okFn: function (win) {
                                    var productName =null,
                                        productId=null,
                                        firmName=null,
                                        customerName=null,
                                        customerPhone=null,
                                        customerMark=null,
                                        userId=null,
                                        dealState=null,
                                        dealMsg=null,
                                        customerIp=null,
                                        createTime=null,
                                        updateTime=null,
                                        id = null;
                                    var flag = true;
                                    productName = win.find("input[name=productName]").val();
                                    firmName = win.find("input[name=firmName]").val();
                                    customerName = win.find("input[name=customerName]").val();
                                    customerPhone = win.find("input[name=customerPhone]").val();
                                    userId = win.find("input[name=userId]").val();
                                    customerMark = win.find("input[name=customerMark]").val();
                                    dealState = win.find("input[name=dealState]").val();
                                    dealMsg = win.find("input[name=dealMsg]").val();
                                    customerIp = win.find("input[name=customerIp]").val();
                                    productId = win.find("input[name=productId]").val();
                                    id = win.find("input[name=id]").val();
                                    if(!productName){
                                        messageTool.error("必须输入产品名称!");
                                        flag = false;
                                    }
                                    if(!productId){
                                        messageTool.error("必须输入产品id!");
                                        flag = false;
                                    }
                                    if(flag){
                                        util.request({
                                            confirm: true,
                                            msg: "确定要保存?",
                                            url: serverHost + "/insureBook/update.json",
                                            params: {
                                                id: id,
                                                productName: productName,
                                                productId: productId,
                                                firmName:firmName,
                                                customerName:customerName,
                                                customerPhone:customerPhone,
                                                customerMark:customerMark,
                                                userId:userId,
                                                dealState:dealState,
                                                dealMsg:dealMsg,
                                                customerIp:customerIp
                                            },
                                            success: function (resp) {
                                                if(resp.success) {
                                                    messageTool.success("更新成功");
                                                    win.close();
                                                    thiz.list.loadPageData();
                                                } else {
                                                    messageTool.error("更新失败");
                                                }
                                            }
                                        });
                                    }
                                }
                            });
                            win.find("input[name=productName]").val(insureBook.productName),
                            win.find("input[name=productId]").val(insureBook.productId);
                            win.find("input[name=firmName]").val(insureBook.firmName);
                            win.find("input[name=customerName]").val(insureBook.customerName);
                            win.find("input[name=customerPhone]").val(insureBook.customerPhone);
                            win.find("input[name=customerMark]").val(insureBook.customerMark);
                            win.find("input[name=userId]").val(insureBook.userId);
                            win.find("input[name=dealState]").val(insureBook.dealState);
                            win.find("input[name=dealMsg]").val(insureBook.dealMsg);
                            win.find("input[name=customerIp]").val(insureBook.customerIp);
                           /* win.find("input[name=updateTime]").val(insureBook.updateTime);*/
                            win.find("input[name=updateTime]").each(function (i, o) {
                                if(o.name =="updateTime"){
                                    $(o).val(util.dateFormat2(data[o.name], 'YYYY-MM-DD hh:mm:ss'))
                                    return true;
                                }
                            })
                            win.find("input[name=createTime]").each(function (i, o) {
                                if(o.name =="createTime"){
                                    $(o).val(util.dateFormat2(data[o.name], 'YYYY-MM-DD hh:mm:ss'))
                                    return true;
                                }
                            })
                           /* win.find("input[name=createTime]").val(insureBook.createTime);*/
                            win.find("input[name=id]").val(insureBook.id);

                        } else {
                            messageTool.error("此信息已不存在!");
                        }
                    }
                });
            },
            // 新增
            addTexts: function(){
                var thiz = this;
                var win = winTool.create({
                    title: "新增白名单商户",
                    height: 700,
                    width: 800,
                    showCancel: true,
                    okName: "保存",
                    cancelName: "关闭",
                    type: "selector",
                    selector: ".video-tpl",
                    okFn: function (win) {
                        var productName =null,
                            productId=null,
                            firmName=null,
                            customerName=null,
                            customerPhone=null,
                            customerMark=null,
                            userId=null,
                            dealState=null,
                            dealMsg=null,
                            customerIp=null,
                            createTime=null,
                            updateTime=null,
                            id = null;
                        var flag = true;
                        productName = win.find("input[name=productName]").val();
                        firmName = win.find("input[name=firmName]").val();
                        customerName = win.find("input[name=customerName]").val();
                        customerPhone = win.find("input[name=customerPhone]").val();
                        userId = win.find("input[name=userId]").val();
                        customerMark = win.find("input[name=customerMark]").val();
                        dealState = win.find("input[name=dealState]").val();
                        dealMsg = win.find("input[name=dealMsg]").val();
                        customerIp = win.find("input[name=customerIp]").val();
                        productId = win.find("input[name=productId]").val();
                        id = win.find("input[name=id]").val();
                        var flag = true;
                        if(!productName){
                            messageTool.error("必须输入产品名称!");
                            flag = false;
                        }
                        if(!productId){
                            messageTool.error("必须输入产品id!");
                            flag = false;
                        }
                        if(flag){
                            util.request({
                                confirm: true,
                                msg: "确定要新增?",
                                url: serverHost + "/insureBook/insert.json",
                                params: {
                                    id: id,
                                    productName: productName,
                                    productId: productId,
                                    firmName:firmName,
                                    customerName:customerName,
                                    customerPhone:customerPhone,
                                    customerMark:customerMark,
                                    userId:userId,
                                    dealState:dealState,
                                    dealMsg:dealMsg,
                                    customerIp:customerIp
                                },
                                success: function (resp) {
                                    if(resp.success) {
                                        messageTool.success("新增成功");
                                        win.close();
                                        thiz.list.loadPageData();
                                    } else {
                                        messageTool.error("新增失败");
                                    }
                                }
                            });
                        }
                    }
                });

                //时间渲染
//        	dateTool.renderDate(".onlineTime");
//                 thiz.rendenWin(win);
            },
            getParams: function () {
                var d = {};
                var t = $('#queryForm').serializeArray();
                $.each(t, function() {
                    if(this.name&&this.value){
                        d[this.name] = this.value;
                    }
                });
                return d;
                /*  return {
                 comId: $("#comId").val(),
                 createTime: $("#createTime").val() != "" ?  $("#createTime").val() + " 00:00:00" : "",
                 };*/
            },
        };

    });