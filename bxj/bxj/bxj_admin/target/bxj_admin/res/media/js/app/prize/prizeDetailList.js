define(['jquery','app/common/util','app/common/advanceListTool',
        'app/common/dateTool','app/common/messageTool','app/common/commonTool','app/common/winTool','app/common/imageView'],
		function($, util, listTool, dateTool, messageTool, commonTool, winTool) {
    var eggPrizeList = {
    		list:function(){
	            var thiz = this,
            list = listTool.create({
            	title: "中奖-发货详细列表",
                selector: ".data-list",
                remote: true,
                url: serverHost + "/prizeDetail/queryPrizeList.json",
                width: 600,
                height: 600,
                columns: [
                    {
                    	name: "会员id",
                    	width:50,
                    	fieldName: "userID"
                	},
                	{
                        name: "会员用户名",
                        width:80,
                        fieldName: "userName"
                    },
                    {
                        name: "奖品图片",
                        width:80,
                        fieldName: "prizeImg",
                        renderer : function(row, col, data,value) {
                        	if(value){
                        		return '<a href="' + value + '" rel="lightbox[' + row + ']"><img src="' + value + '" width="50px" height="50px"></img></a>';
                        	}
                        }
                    },
                    {
                        name: "奖品名称",
                        width:120,
                        fieldName: "name"
                    },
                    /*{
                    	name: "奖品总数",
                    	width:60,
                    	fieldName: "totalNum"
                    },
                    {
                    	name: "奖品价值",
                    	width:60,
                    	fieldName: "amount"
                    },
                   
                    {
                    	name: "可用奖品数",
                    	width:60,
                    	fieldName: "usableNum"
                    },
                    {
                    	name: "已用奖品数",
                    	width:60,
                    	fieldName: "usedNum"
                    },*/
                    /*{
                        name: "奖品类型",
                        width:80,
                        fieldName: "type",
					    renderer : function(row, col, data,value) {
					    	// 自动发放
                        	if(value == 1){
                        		return "<span class='label label-table label-info'>自动发放<i></i></span>";
                        	// 手动激活-充值
                        	} else if(value == 2){
                        		return "<span class='label label-table label-info'>手动激活-充值<i></i></span>";
                        	// 手动激活-收货
                        	} else if(value == 3){
                         		return "<span class='label label-table label-info'>手动激活-收货<i></i></span>";
                         	} 
                        }
                    },*/
                    
                    {
                        name: "获奖时间",
                        width:80,
                        fieldName: "logCreateTime",
					    renderer : function(row, col, data,value) {
					    	return util.dateFormat2(value, 'YYYY-MM-DD');
					    }
                    },
                    {
                        name: "领取状态",
                        width:70,
                        fieldName: "logStatus",
                        renderer : function(row, col, data,value) {
                        	//未激活
                        	if(value == 1){
                        		return "<span class='label label-table label-warning'>未激活<i></i></span>";
                        	} 
                        	else if(value == 2){ // 已激活
                        		return "<span class='label label-table label-success'>已激活<i></i></span>";
                        	//  发货成功
                        	} else if(value == 3){
                        		return "<span class='label label-table label-info'>发货成功<i></i></span>";
                        	}
                        }
                    },
                    
                    {
                    	name: "收货人姓名",
                    	width:60,
                    	fieldName: "takeName"
                    },
                    {
                    	name: "收货人电话",
                    	width:40,
                    	fieldName: "takephone"
                    },
                    {
                    	name: "收货地区",
                    	width:80,
                    	fieldName: "area"
                    },
                    {
                    	name: "收货详细地址",
                    	width:160,
                    	fieldName: "address"
                    },
                    {
                        name: "操作",
                        width:50,
                        renderer : function(rindx, cindex, data) {
                        	var upDownButton = '';
                        	if (data.logStatus == 2){ //已激活
                        		upDownButton = '<button class="btn btn-danger sendInfo">发货</button>';
                        	} else if(data.logStatus == 3){ //已发货 
//                        		upDownButton = '无';
                        	}
                        	var st = '<div class="action-buttons">' +
			               			 '<div class="btn-group">' +
			               			upDownButton +
//			               			'<button class="btn btn-info addTotalNum">修改</button>' +
			               			 '</div>' +
			               			 '</div>';
                        	return st;
                        }
                    }
                	],
                paramFn: function () {
                	return {
                		startTime: $(".startTime").val(),
                		endTime: $(".endTime").val(),
                		name: $(".name").val(),
                		logStatus: $(".logStatus").val(),
                		takeName:$(".takeName").val(),
                		takephone:$(".takephone").val()
                	}
                },
               
                initSearch: function (query) {
                	
                },
                classEvents : [{
	            	className : "sendInfo",
//	            	rightCode: "upDown",
	            	handler: function (idx, data) {
	                    thiz.sendInfo(data);
	                }
                },
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
       	 	//搜索
            $(".search-action").on("click", function () {
            	thiz.list.reloadPageData(1);
            });
        },
        
        // 发货 
        sendInfo : function(data){
        	var bonusLogId = data.bonusLogId,
        	    name= data.name,
        	    phone=data.takephone,
        		thiz = this;
        	util.request({
        		confirm: true,
                msg: "确定要发货?",
                url: serverHost + "/prizeDetail/updateLogStatus.json",
                params: {
                	bonusLogId: bonusLogId,
                	logStatus: 3,
                	name:name,
                	takephone:phone
                },
                success: function (resp) {
                    if(resp.success) {
                    	messageTool.success("发货成功!");
                    	thiz.list.reloadPageData();
                    } else {
                    	messageTool.error(resp.msg || "发货失败!请重试！");
                    }
                }
            });
        },
        
    };
    return eggPrizeList;
});