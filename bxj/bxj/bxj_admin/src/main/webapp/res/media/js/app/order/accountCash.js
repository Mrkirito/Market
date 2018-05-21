define(['jquery','app/common/util','app/common/advanceListTool',
        'app/common/dateTool','app/common/messageTool','app/common/commonTool','app/common/winTool'],
		function($, util, listTool, dateTool, messageTool, commonTool, winTool) {
    var cashList = {
    		list:function(){
	            var thiz = this,
            list = listTool.create({
            	title: "提现列表",
                selector: ".data-list",
                remote: true,
                url: serverHost + "/order/queryAccountCashList.json",
                width: 600,
                height: 600,
                columns: [
                    {
                    	name: "用户ID",
                    	width:80,
                    	fieldName: "userId",
                    	lineHiddenFn: function() {
					    	if($(".dimension").val() == 1 || $(".dimension").val() == 3 || $(".dimension").val() == 4) return false;
					    	return true;
					    }
                	},
                    {
                        name: "申请日期",
                        width:120,
                        fieldName: "createTime",
                        renderer : function(row, col, data,value) {
                        	if($(".dimension").val() == 2 || $(".dimension").val() == 3)return util.dateFormat2(value, 'YYYY-MM-DD');
                        	if($(".dimension").val() == 4)return util.dateFormat2(value, 'YYYY-MM-DD HH:mm:ss');
                        },
                        lineHiddenFn: function() {
					    	if($(".dimension").val() == 2 || $(".dimension").val() == 3 || $(".dimension").val() == 4) return false;
					    	return true;
					    }
                    },
                    {
                    	name: "提现总金额(元)",
                    	width:80,
                    	fieldName: "sumAmount"
                    },
                    {
                    	name: "到账金额(元)",
                    	width:80,
                    	fieldName: "expectedAmount"
                    },
//                    {
//                    	name: "提现手续费(元)",
//                    	width:80,
//                    	fieldName: "fee"
//                    },
                    {
                        name: "提现订单号",
                        width:120,
                        fieldName: "orderNo",
                        lineHiddenFn: function() {
					    	if($(".dimension").val() == 4) return false;
					    	return true;
					    }
                    },
                    {
                        name: "审核状态",
                        width:120,
                        fieldName: "auditStatus",
                        lineHiddenFn: function() {
					    	if($(".dimension").val() == 4) return false;
					    	return true;
					    },
					    renderer : function(row, col, data,value) {
					    	// 提交审核
                        	if(value == 1){
                        		return "<span class='label label-table label-info'>提交审核<i></i></span>";
                        	// 审核成功
                        	} else if(value == 2){
                        		return "<span class='label label-table label-success'>审核成功<i></i></span>";
                        	// 审核失败
                        	} else if(value == 3){
                        		return "<span class='label label-table label-danger'>审核失败<i></i></span>";
                        	}
                        }
                    },
                    {
                        name: "审核时间",
                        width:120,
                        fieldName: "auditTime",
                        lineHiddenFn: function() {
					    	if($(".dimension").val() == 4) return false;
					    	return true;
					    },
					    renderer : function(row, col, data,value) {
                        	return util.dateFormat2(value, 'YYYY-MM-DD');
                        }
                    },
                    {
                        name: "提现银行卡ID",
                        width:120,
                        fieldName: "bankNo",
                        lineHiddenFn: function() {
					    	if($(".dimension").val() == 4) return false;
					    	return true;
					    }
                    },
                    {
                        name: "真实姓名",
                        width:120,
                        fieldName: "bankUserName",
                        lineHiddenFn: function() {
					    	if($(".dimension").val() == 4) return false;
					    	return true;
					    }
                    },
                    {
                        name: "银行名称",
                        width:120,
                        fieldName: "bankName",
                        lineHiddenFn: function() {
					    	if($(".dimension").val() == 4) return false;
					    	return true;
					    }
                    },
                    {
                        name: "开户城市",
                        width:120,
                        fieldName: "bankProvince",
                        lineHiddenFn: function() {
					    	if($(".dimension").val() == 4) return false;
					    	return true;
					    },
					    renderer : function(row, col, data,value) {
					    	var bankProvince = data.bankProvince ? data.bankProvince : "",
			    				bankCity = data.bankCity ? data.bankCity : "",
	    						bankCounty = data.bankCounty ? data.bankCounty : "";
                        	return bankProvince + " " + bankCity + " " + bankCounty;
                        }
                    },
                    {
                        name: "支行全称",
                        width:120,
                        fieldName: "bankBranchName",
                        lineHiddenFn: function() {
					    	if($(".dimension").val() == 4) return false;
					    	return true;
					    }
                    },
                    {
                        name: "拒绝原因",
                        width:120,
                        fieldName: "auditReason",
                        lineHiddenFn: function() {
					    	if($(".dimension").val() == 4) return false;
					    	return true;
					    }
                    },
                    {
                        name: "操作",
                        width:100,
						rightFixed: true,
                        renderer : function(rindx, cindex, data) {
                        	var auditButton = '';
                        	if (data.auditStatus == 1){
                        		auditButton = '<button class="btn btn-success pass">审核通过</button>' + 
                        					  '<button class="btn btn-danger fail">审核不通过</button>'
                        	}
                        	var st = '<div class="action-buttons">' +
			               			 '<div class="btn-group">' +
			               			 auditButton +
			               			 '</div>' +
			               			 '</div>';
                        	return st;
                        },
                        lineHiddenFn: function() {
					    	if($(".dimension").val() == 4) return false;
					    	return true;
					    }
                    }
                	],
                paramFn: function () {
                	return {
                		userId: $(".userId").val(),
                		bankName: $(".bankName").val(),
                		bankUserName: $(".bankUserName").val(),
                		startTime: $(".startTime").val(),
                		endTime: $(".endTime").val(),
                		dimension: $(".dimension").val(),
                		bankCityName: $(".bankCityName").val(),
                		auditStatus: $(".auditStatus").val()
                	}
                },
               
                initSearch: function (query) {
                	
                },
                tbars: [],
                classEvents : [{
	            	className : "pass",
	            	rightCode: "audit",
	            	handler: function (idx, data) {
	                    thiz.pass(data);
	                }
                },
                {
	            	className : "fail",
	            	rightCode: "audit",
	            	handler: function (idx, data) {
	                    thiz.fail(data);
	                }
                }
                ]
            });
            this.list = list;
        	//时间渲染
            dateTool.renderDateRange(".createTime", {
				applyClick : function(startDate, endDate) {
					$(".startTime").val(startDate);
					$(".endTime").val(endDate);
				}
			});
        	$(".dimension").on("change", function(){
        		thiz.list.reloadPageData(1);
        	})
       	 	//搜索
            $(".search-action").on("click", function () {
            	thiz.list.reloadPageData(1);
            });
        },
        // 审核通过
        pass: function(data){
        	var thiz = this;
        	var id = data.id;
        	util.request({
        		confirm: true,
                msg: "确定要审核通过?",
                url: serverHost + "/order/passCash.json",
                params: {
                	id: id,
                	auditStatus: 2
                },
                success: function (resp) {
                    if(resp.success) {
                    	messageTool.success("更新成功");
                    	thiz.list.reloadPageData();
                    } else {
                    	messageTool.error(resp.msg || "更新失败");
                    	thiz.list.reloadPageData();
                    }
                }
            });
        },
        
        // 审核不通过
        fail: function(data){
        	var thiz = this;
        	var id = data.id;
        	
        	var win = winTool.create({
                title: "审核",
                height: 350,
                width: 600,
                showCancel: true,
                okName: "审核不通过",
                cancelName: "关闭",
                type: "selector",
                selector: ".cash-tpl",
                okFn: function (win) {
                	if(!win.find(".auditReason").val().trim()){
                		messageTool.error("拒绝原因不能为空!");
                	} else if(win.find(".auditReason").val().length > 128)	{
                		messageTool.error("拒绝原因不能超过128个字!");
                	} else {
                		util.request({
                    		confirm: true,
                            msg: "确定要审核不通过?",
                            url: serverHost + "/order/failCash.json",
                            params: {
                            	id: id,
                            	auditStatus: 3,
                            	auditReason: win.find(".auditReason").val()
                            },
                            success: function (resp) {
                                if(resp.success) {
                                	messageTool.success("更新成功");
                                	win.close();
                                	thiz.list.reloadPageData();
                                } else {
                                	messageTool.error(resp.msg || "更新失败");
                                	win.close();
                                	thiz.list.reloadPageData();
                                }
                            }
                        });
                	}
                }
            });
        },
    };
    return cashList;
});