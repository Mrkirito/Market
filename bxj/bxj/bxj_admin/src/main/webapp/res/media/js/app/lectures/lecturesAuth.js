define(['jquery','app/common/util','app/common/advanceListTool',
        'app/common/dateTool','app/common/messageTool','app/common/commonTool','app/common/winTool','app/common/imageView'],
		function($, util, listTool, dateTool, messageTool, commonTool, winTool) {
    var lecturesAuth = {
    		list:function(){
	            var thiz = this,
            list = listTool.create({
            	title: "申请认证讲师列表",
                selector: ".data-list",
                remote: true,
                url: serverHost + "/lectures/queryLecturesAuthList.json",
                width: 600,
                height: 600,
                columns: [
                    {
                    	name: "会员id",
                    	width:80,
                    	fieldName: "fid"
                	},
                    {
                        name: "会员账号",
                        width:120,
                        fieldName: "phone"
                    },
                    {
                    	name: "申请日期",
                    	width:80,
                    	fieldName: "applyTime",
                    	renderer : function(row, col, data,value) {
                        	return util.dateFormat2(value, 'YYYY-MM-DD HH:mm:ss');
                        }
                    },
                    {
                    	name: "审核日期",
                    	width:80,
                    	fieldName: "auditTime",
                    	renderer : function(row, col, data,value) {
                        	return util.dateFormat2(value, 'YYYY-MM-DD HH:mm:ss');
                        }
                    },
                    {
                        name: "真实姓名",
                        width:120,
                        fieldName: "realName"
                    },
                    {
                        name: "身份证号码",
                        width:120,
                        fieldName: "idCard"
                    },
                    {
                        name: "手持身份证正面照片",
                        width:120,
                        fieldName: "idCardFrontUrl",
                        renderer : function(row, col, data,value) {
                        	if(value){
                        		return '<a href="' + value + '" rel="lightbox[' + row + ']"><img src="' + value + '" width="100px" height="50px"></img></a>';
                        	}
                        }
                    },
                    {
                        name: "手持身份证反面照片",
                        width:120,
                        fieldName: "idCardReverseUrl",
                        renderer : function(row, col, data,value) {
                        	if(value){
                        		return '<a href="' + value + '" rel="lightbox[' + row + ']"><img src="' + value + '" width="100px" height="50px"></img></a>';
                        	}
                        }
                    },
                    {
                        name: "审核状态",
                        width:120,
                        fieldName: "realAuditStatus",
                        renderer : function(row, col, data,value) {
                        	if(value == 1){
                        		return '<span class="label label-sm label-info">提交审核 </span>';
                        	} else if(value == 2){
                        		return '<span class="label label-sm label-success">审核通过 </span>';
                        	} else if(value == 3){
                        		return '<span class="label label-sm label-danger">审核失败 </span>';
                        	}
                        }
                    },
                    {
                        name: "拒绝原因",
                        width:120,
                        fieldName: "auditReason"
                    },
                    {
                        name: "操作",
                        width:100,
						rightFixed: true,
                        renderer : function(rindx, cindex, data) {
                        	var auditButton = '';
                        	if (data.realAuditStatus == 1){
                        		auditButton = '<button class="btn btn-success pass">审核通过</button>' + 
                        					  '<button class="btn btn-danger fail">审核不通过</button>'
                        	}
                        	var st = '<div class="action-buttons">' +
			               			 '<div class="btn-group">' +
			               			 auditButton +
			               			 '</div>' +
			               			 '</div>';
                        	return st;
                        }
                    }
                	],
                paramFn: function () {
                	return {
                		phone: $(".phone").val(),
                		realName: $(".realName").val(),
                		idCard: $(".idCard").val(),
                		startTime: $(".startTime").val(),
                		endTime: $(".endTime").val(),
                		realAuditStatus: $(".realAuditStatus").val()
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
        	var fid = data.fid;
            var idCard = data.idCard;
        	var realName = data.realName;
        	util.request({
        		confirm: true,
                msg: "确定要审核通过?",
                url: serverHost + "/lectures/passAuth.json",
                params: {
                	fid: fid,
                    idCard: idCard,
                	realAuditStatus: 2,
                	realName: realName
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
        	var fid = data.fid;
        	
        	var win = winTool.create({
                title: "审核",
                height: 350,
                width: 600,
                showCancel: true,
                okName: "审核不通过",
                cancelName: "关闭",
                type: "selector",
                selector: ".auth-tpl",
                okFn: function (win) {
                	if(!win.find(".auditReason").val().trim()){
                		messageTool.error("拒绝原因不能为空!");
                	} else if(win.find(".auditReason").val().length > 128)	{
                		messageTool.error("拒绝原因不能超过128个字!");
                	} else {
                		util.request({
                    		confirm: true,
                            msg: "确定要审核不通过?",
                            url: serverHost + "/lectures/failAuth.json",
                            params: {
                            	fid: fid,
                            	realAuditStatus: 3,
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
    return lecturesAuth;
});