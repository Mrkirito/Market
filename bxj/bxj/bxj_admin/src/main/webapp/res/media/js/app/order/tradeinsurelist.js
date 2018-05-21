define(['jquery','app/common/util','app/common/listTool','app/common/winTool','app/common/dateTool','app/common/messageTool','app/common/distpicker'],
    function($, util, listTool, winTool, dateTool,messageTool,distpicker) {
        var AjaxStatus = {
            0 : function(json, opts) {
                messageTool.info('导入订单保单数据成功');
                if (opts.onSuccess) {
                    opts.onSuccess(json);
                }
            },
            500 : function(json, opts) {
                messageTool.error(json.error||'导入订单保单数据失败');
            },
            302 : function(json) {
            }
        }
        return {
            list:function(){
                var thiz=this,list=listTool.create({
                    title: "订单信息",
                    toolbarSelector:"div.product_info_tbars",
                    selector: "div.data-list",
                    remote: true,
                    url: serverHost+"/tradeinsure/list.json",
                    width: 700,
                    height: 500,
                    showPagebar:true,
                    showCheckbox:false,
                    pageSize:15,
                    columns : [
                        {
                            name : "订单编号",
                            width : 200,
                            fieldName : "tradeId"
                        },
                        {
                            name : "订单状态",
                            width : 100,
                            fieldName : "statusId",
                            renderer : function(row, col, data,
                                                value) {
                                if (value == 1) {
                                    return "待支付";
                                }
                                if (value == 2) {
                                    return "处理中";
                                }
                                if (value == 4) {
                                    return "已承保";
                                }
                                if (value == 5) {
                                    return "已取消";
                                }
                                if (value == 9) {
                                    return "已生效";
                                }
                                if (value == 6) {
                                    return "已失效";
                                }
                                return null;
                            }
                        },
                        {
                            name : "保单编号",
                            width : 200,
                            fieldName : "policyNumber"
                        },
                        {
                            name : "订单创建日期",
                            width : 130,
                            fieldName : "createTime",
                            renderer : function(row, col, data,
                                                value) {
                                return util.dateFormat2(value,"");
                            }
                        },
                        {
                            name : "订单修改日期",
                            width : 130,
                            fieldName : "modifiedTime",
                            renderer : function(row, col, data,
                                                value) {
                                return util.dateFormat2(value,
                                    "");
                            }
                        },
                        {
                            name : "会员账号",
                            width : 100,
                            fieldName : "memberId"
                        },
                        {
                            name : "产品名称",
                            width : 100,
                            fieldName : "productName"
                        }
                        ,
                        /*	{
                         name : "险种",
                         width : 100,
                         fieldName : ""
                         },*/
                        {
                            name : "保障期限",
                            width : 100,
                            renderer : function(row, col, data,value) {
                                if(data.insureType == 4){
                                    return "终身";
                                }
                                if(data.insureType == 0){
                                    return data.insureYears+"年";
                                }
                                if(data.insureType == 1){
                                    return data.insureYears+"月";
                                }
                                if(data.insureType == 2){
                                    return data.insureYears+"日";
                                }
                                if(data.insureType == 3){
                                    return "保障至"+data.insureYears+"岁";
                                }
                            }
                        },
                        {
                            name : "保额",
                            width : 100,
                            fieldName : "marketAmount"
                        },
                        {
                            name : "购买份数",
                            width : 100,
                            fieldName : "buyNumber"
                        },
                        {
                            name : "交费期限",
                            width : 100,
                            renderer : function(row, col, data,value) {
                                if(data.payYearsType == 4){
                                    return "终身";
                                }
                                if(data.payYearsType == 0){
                                    return data.payYears+"年";
                                }
                                if(data.payYearsType == 1){
                                    return data.payYears+"月";
                                }
                                if(data.payYearsType == 2){
                                    return data.payYears+"日";
                                }
                                if(data.payYearsType == 3){
                                    return "保障至"+data.payYears+"岁";
                                }
                            }
                        },
                        {
                            name : "保障开始日期",
                            width : 150,
                            fieldName : "insureBeginDate",
                            renderer : function(row, col, data,
                                                value) {
                                return util.dateFormat2(value,"");

                            }
                        },
                        {
                            name : "保障结束日期",
                            width : 150,
                            fieldName : "insureEndDate",
                            renderer : function(row, col, data,
                                                value) {
                                return util.dateFormat2(value,
                                    "");
                            }
                        },
                        {
                            name : "订单总价",
                            width : 100,
                            fieldName : "totalAmount",
                            renderer : function(row, col, data,value) {
                                return value+"元";
                            }
                        },
                        {
                            name : "实际支付金额",
                            width : 100,
                            fieldName : "totalAmount",
                            renderer : function(row, col, data,value) {
                                return value+"元";
                            }
                        },
                        {
                            name : "返利金额",
                            width : 80,
                            renderer : function(row, col, data,value) {
                                return "0元";
                            }
                        },
                        {
                            name : "订单来源",
                            width : 100,
                            fieldName : "sourceId",
                            renderer : function(row, col, data,
                                                value) {
                                if (value == 1) {
                                    return "新概念服务号";
                                }
                                if (value == 2) {
                                    return "保险家";
                                }
                                if (value == 3) {
                                    return "行家";
                                }
                                return null;
                            }
                        },
                        {
                            name : "上传保单",
                            width : 100,
                            fieldName : "insureFileUrl",
                            renderer : function(row, col, data,
                                                value) {
                                if (value != ""
                                    && value != null) {
                                    return "是"
                                } else {
                                    return "否"
                                }
                            }
                        },
                        {
                            name : "是否发送保单",
                            width : 100,
                            fieldName : "insureFileUrl",
                            renderer : function(row, col, data,
                                                value) {
                                if (value != ""
                                    && value != null) {
                                    return "是"
                                } else {
                                    return "否"
                                }
                            }
                        },
                        {
                            name : "被保人",
                            width : 100,
                            fieldName : "recognizeeName"
                        },
                        {
                            name : "投保人",
                            width : 100,
                            fieldName : "realName"
                        },
                        {
                            name : "操作",
                            width : 230,
                            rightFixed : true,
                            renderer : function(rindx, cindex,
                                                data) {
                                var dom = [
                                    '<button class="basic" title="编辑信息"><i class="fa fa-pencil-square-o bigger-150"></i></button>',
                                    /*'<button class="downOrder" title="订单下载"><i class="fa bigger-150 fa-delicious"></i></button>',*/
                                    '<button class="delOrder" title="作废订单"><i class="fa fa-pencil-square-o bigger-150"></i></button>',
                                    '<button class="importSinglePolicy" title="保单导入"><i class="fa bigger-150 fa-codepen"></i></button>',
                                    '<button class="downSinglePolicy" title="保单下载"><i class="fa bigger-150 fa-codepen"></i></button>'];
                                return dom.join('&nbsp;');
                            }
                        } ],
                    paramFn: function () {
                        return thiz.getParams();
                    },
                    initSearch: function (query) {},
                    tbars: [],
                    classEvents : [{	className : "basic",
                        handler: function (idx, data) {
                            thiz.basic(data);
                        }
                    },{	className : "importSinglePolicy",
                        handler: function (idx, data) {
                            thiz.importSinglePolicy(data);
                        }
                    },{	className : "updateInsureMessage",
                        handler: function (idx, data) {
                            console.log(data);
                            thiz.updateInsureMessage(data);
                        }
                    },{	className : "updateIsurantMessage",
                        handler: function (idx, data) {
                            thiz.updateIsurantMessage(data);
                        }
                    },{	className : "delOrder",
                        handler: function (idx, data) {
                            thiz.delOrder(data);
                        }
                    },{	className : "downSinglePolicy",
                        handler: function (idx, data) {
                            thiz.downSinglePolicy(data);
                        }
                    },{	className : "downOrder",
                        handler: function (idx, data) {
                            thiz.downOrder(data);
                        }
                    }]
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
            getParams: function () {
                var d = {};
                var t = $('#queryForm').serializeArray();
                $.each(t, function() {
                    if(this.name){
                        d[this.name] = this.value;
                    }
                });
                return d;
            },
            basic: function(data){
                var thiz = this,id = data.tradeId;
                util.ajax({
                    url: serverHost + "/tradeinsure/detail.json",
                    params: {tradeId : id},
                    success: function (resp) {
                        if(resp.success) {
                            var resData = resp.model;
                            var win = winTool.create({
                                title: "修改订单信息信息",
                                height: 500,
                                width: 700,
                                showCancel: true,
                                okName: "保存",
                                cancelName: "关闭",
                                type: "selector",
                                selector: ".video-tpl",
                                okFn: function (win) {
                                    var isValidateTrue=true;
                                    var orderState;
                                    var receiptType;
                                    win.find('select,input').each(function(i,o){
                                        var name = o.name;
                                        if(name=="statusId"){
                                            orderState =$(o).val();
                                        }
                                        o=$(o);
                                        var isDisabled = $(o).prop("disabled");
                                        if(!isDisabled){
                                            if(name == "refuseReason" && orderState != 5){
                                                return true;
                                            }
                                            if(name=="policyNumber"){
                                                return true;
                                            }
                                            if(name==""){
                                                return true;
                                            }
                                            var v=o.val();
                                            if(!v){
                                                isValidateTrue=false;
                                                messageTool.error(o.prev().text()+'必须填写');
                                                return false;
                                            }
                                        }
                                    });
                                    if(isValidateTrue){
                                        var paras=win.find('#editForm').serialize();
                                        util.request({
                                            confirm: true,
                                            msg: "确定要修改订单信息信息?",
                                            url: serverHost + "/tradeinsure/updateOrder.json",
                                            dataType:"json",
                                            params:paras ,
                                            success: function (resp) {
                                                if(resp) {
                                                    win.close();
                                                    messageTool.success("更改订单信息成功");
                                                    thiz.list.loadPageData();
                                                } else {
                                                    messageTool.error("更改订单信息失败");
                                                }
                                            }
                                        });
                                    }
                                }
                            });
                            win.find('select,input').each(function(i,o){
                                if(o.name == "insureFileUrls"){
                                    if(resData.insureFileUrl != "" && null != resData[o.name] ){
                                        $(o).val("是");
                                    }else{
                                        $(o).val("否");
                                    }
                                    return true;
                                }
                                if(o.name == "modifiedTime"){
                                    $(o).val(util.dateFormat2(resData[o.name], 'YYYY-MM-DD hh:mm:ss'))
                                    return true;
                                }
                                if(o.name == "createTime"){
                                    $(o).val(util.dateFormat2(resData[o.name], 'YYYY-MM-DD hh:mm:ss'))
                                    return true;
                                }
                                if(o.name == "insureBeginDateTemp"){
                                    $(o).val(util.dateFormat2(resData.insureBeginDate, 'YYYY-MM-DD'))
                                    return true;
                                }
                                if(o.name == "insureEndDateTemp"){
                                    $(o).val(util.dateFormat2(resData.insureEndDate, 'YYYY-MM-DD'))
                                    return true;
                                }
                                if(o.name == "duration"){
                                    if(data.insureType == 4){
                                        $(o).val("终身");
                                        return true;
                                    }
                                    if(data.insureType == 0){
                                        $(o).val(data.insureYears+"年");
                                        return true;
                                    }
                                    if(data.insureType == 1){
                                        $(o).val(data.insureYears+"月");
                                        return true;
                                    }
                                    if(data.insureType == 2){
                                        $(o).val(data.insureYears+"日");
                                        return true;
                                    }
                                    if(data.insureType == 3){
                                        $(o).val("保障至"+data.insureYears+"岁");
                                        return true;
                                    }
                                    return true;
                                }
                                if(o.name == "tradeduration"){
                                    if(data.payYearsType == 4){
                                        $(o).val("终身");
                                        return true;
                                    }
                                    if(data.payYearsType == 0){
                                        $(o).val(data.payYears+"年");
                                        return true;
                                    }
                                    if(data.payYearsType == 1){
                                        $(o).val(data.payYears+"月");
                                        return true;
                                    }
                                    if(data.payYearsType == 2){
                                        $(o).val(data.payYears+"日");
                                        return true;
                                    }
                                    if(data.payYearsType == 3){
                                        $(o).val("保障至"+data.payYears+"岁");
                                        return true;
                                    }
                                    return true;
                                }
                                if(o.name == "sourceId"){
                                    if (resData[o.name] == 1) {
                                        $(o).val("新概念服务号");
                                        return true;
                                    }
                                    if (resData[o.name] == 2) {
                                        $(o).val("保险家");
                                        return true;
                                    }
                                    if (resData[o.name] == 3) {
                                        $(o).val("行家");
                                        return true;
                                    }
                                    $(o).val("未知来源");
                                    return true;
                                }
                                if(o.name == "fanliAmount"){
                                    $(o).val("0元");
                                    return true;
                                }

                                var indexInsure = o.name.indexOf("tradeAssuredInsure.");
                                var indexIsurant = o.name.indexOf("tradeAssuredIsurent.");
                                if(indexInsure == 0 || indexIsurant == 0 ){
                                    var obj;
                                    if(indexInsure == 0){
                                        obj = resData.tradeAssuredInsure;
                                    }else{
                                        obj = resData.tradeAssuredIsurent;
                                    }
                                    var index = o.name.indexOf(".");
                                    var names = o.name.substring(index+1);
                                    if(names == "sex"){
                                        if(obj[names] == 0){
                                            $(o).val("男");
                                            return true;
                                        }
                                        if(obj[names] == 1){
                                            $(o).val("女");
                                            return true;
                                        }
                                        return true;

                                    }
                                    $(o).val(obj[names]);
                                    return true;
                                }
                                $(o).val(resData[o.name]);
                            });
                            win.find('div[data-toggle="distpicker"]').each(function(i,o){
                                win.find('div[data-toggle="distpicker"]').distpicker();
                            });
                            dateTool.renderDate(win.find(".form-begindate"));
                            dateTool.renderDate(win.find(".form-enddate"));
                        }
                    }
                });
            },
            importSinglePolicy:function(data){
                var thiz=this;
                $("#id").val(data.fid);
                win = winTool.create({
                    title: "导入单个订单信息保单",
                    height: 100,
                    width:450,
                    showCancel: false,
                    showOk: true,
                    okName:'导入',
                    type: "selector",
                    selector: "div.order_policy_import",
                    okFn: function (win) {
                        var isValidateTrue = true;
                        var v = win.find('input[name=file]').val();
                        if(!v){
                            isValidateTrue=false;
                            messageTool.success("必须选择一个pdf文件");
                        }
                        if(isValidateTrue){
                            messageTool.confirm2('<font color="red">注：导入前请先执行导出备份原有数据。</font><br/>确定要导入该文件？',function(){
                                var form=win.find('#order_policy_import_form')[0];
                                thiz.ajaxUpload(form,{
                                    onSuccess:function(data){
                                        if(data.status==0){
                                            win.close();
                                            thiz.list.loadPageData();
                                        }
                                    }
                                });
                            });
                        }
                    }
                });
            },
            downSinglePolicy:function(data){
                var thiz=this,insureFileUrl=data.insureFileUrl;
                if(insureFileUrl == "" || insureFileUrl == null){
                    messageTool.success("该订单没有上传保单");
                    return;
                }
                //window.open(STATIC_PATH+policyUrl, "下载保单", "", "");
                location.href=serverHost + "/tradeinsure/downSinglePolicy.json?insureFileUrl="+insureFileUrl;
            },
            downOrder:function(data){
                var thiz=this,id=data.id;
                location.href=serverHost + "/order/downOrder.json?id="+id;
            },
            delOrder:function(data){
                var thiz=this,id=data.fid;
                util.request({
                    confirm: true,
                    msg: "确定要作废该订单信息?",
                    url: serverHost + "/tradeinsure/del.json",
                    dataType:"json",
                    params: {id : id},
                    success: function (resp) {
                        if(resp) {
                            messageTool.success("作废订单信息成功");
                            thiz.list.loadPageData();
                        } else {
                            messageTool.error("作废订单信息失败");
                        }
                    }
                });
            },
            updateIsurantMessage:function(data){
                var thiz=this,id=data.isurantId;
                if(null == id){
                    messageTool.success("被保人公司id为空，不可更改!");
                    return;
                }
                util.request({
                    confirm: true,
                    msg: "确定要修改被保人企查查信息?",
                    url: serverHost + "/order/updateIsurantMessage.json",
                    dataType:"json",
                    params: {id : id},
                    success: function (resp) {
                        if(resp) {
                            messageTool.success("更改信息成功");
                            thiz.list.loadPageData();
                        } else {
                            messageTool.error("更改信息失败");
                        }
                    }
                });
            },
            ajaxUpload:function(form, opts) {
                var thiz=this;
                opts = opts || {};
                var formData = new FormData(form);
                if (opts.onData) {
                    opts.onData(formData);
                }
                var xhr = new XMLHttpRequest();
                xhr.open("POST", form.action);
                xhr.onload=function(e) {
                    var json = eval('(' + this.response + ')');
                    thiz.validateSsouser(json);
                    var cb = AjaxStatus[json.status];
                    if (!cb) {
                        messageTool.error('程序错误，错误代码：' + json.status);
                    }
                    cb(json, opts);
                };
                xhr.send(formData);
            },
            validateSsouser:function(data) {
                if(data.ssotype) {
                    window.location.href = decodeURIComponent(data.redirectUrl);
                    return false;
                }
                return true;
            },
            validateSsouser:function(data) {
                if(data.ssotype) {
                    window.location.href = decodeURIComponent(data.redirectUrl);
                    return false;
                }
                return true;
            }
        };
    });