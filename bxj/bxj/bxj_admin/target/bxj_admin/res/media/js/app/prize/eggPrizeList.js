define(['jquery','app/common/util','app/common/advanceListTool',
        'app/common/dateTool','app/common/messageTool','app/common/commonTool','app/common/winTool','app/common/imageView'],
		function($, util, listTool, dateTool, messageTool, commonTool, winTool) {
    var eggPrizeList = {
    		list:function(){
	            var thiz = this,
            list = listTool.create({
            	title: "砸蛋奖品列表",
                selector: ".data-list",
                remote: true,
                url: serverHost + "/eggPrize/queryEggPrizeList.json",
                width: 600,
                height: 600,
                columns: [
                    {
                    	name: "奖品id",
                    	width:50,
                    	fieldName: "id"
                	},
                    {
                        name: "奖品名称",
                        width:80,
                        fieldName: "name"
                    },
                    {
                    	name: "奖品价值",
                    	width:60,
                    	fieldName: "amount"
                    },
                    {
                    	name: "奖品总数",
                    	width:60,
                    	fieldName: "totalNum"
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
                    },
                    {
                    	name: "奖品概率",
                    	width:60,
                    	fieldName: "probability",
                    	renderer : function(row, col, data,value) {
                        	return value + "%";
                        }
                    },
                    {
                        name: "限制金额",
                        width:60,
                        fieldName: "amountLimit"
                    },
                    {
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
                    },
                    {
                        name: "奖品等级",
                        width:50,
                        fieldName: "level"
                    },
                    {
                        name: "是否启用",
                        width:80,
                        fieldName: "isEnable",
                        renderer : function(row, col, data,value) {
					    	// 启用
                        	if(value == 1){
                        		return "<span class='label label-table label-success'>启用<i></i></span>";
                        	// 不启用
                        	} else if(value == 0){
                        		return "<span class='label label-table label-danger'>不启用<i></i></span>";
                        	}
                        }
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
                        name: "文案图片",
                        width:80,
                        fieldName: "contextImg",
                        renderer : function(row, col, data,value) {
                        	if(value){
                        		return '<a href="' + value + '" rel="lightbox[' + row + ']"><img src="' + value + '" width="50px" height="50px"></img></a>';
                        	}
                        }
                    },
                    {
                        name: "创建时间",
                        width:80,
                        fieldName: "createTime",
					    renderer : function(row, col, data,value) {
					    	return util.dateFormat2(value, 'YYYY-MM-DD');
					    }
                    },
                    {
                        name: "操作",
                        width:100,
                        renderer : function(rindx, cindex, data) {
                        	var upDownButton = '';
                        	if (data.isEnable == 1){
                        		upDownButton = '<button class="btn btn-danger down">禁用</button>';
                        	} else if(data.isEnable == 0){
                        		upDownButton = '<button class="btn btn-success up">启用</button>';
                        	}
                        	var st = '<div class="action-buttons">' +
			               			 '<div class="btn-group">' +
			               			upDownButton +
			               			'<button class="btn btn-info addTotalNum">修改</button>' +
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
                		isEnable: $(".isEnable").val()
                	}
                },
               
                initSearch: function (query) {
                	
                },
                tbars: [
                        {
                            type: "group",
                            icon: "fa fa-gear fa-lg",
                            name: "操作",
                            btns: [
                                {
                                    icon: 'fa fa-plus',
                                    name: "新增奖品",
//                                    rightCode: "add",
                                    url: serverHost + "/eggPrize/eggPrizeAdd.jhtml"
                                }
                            ]
                        }
                ],
                classEvents : [{
	            	className : "up",
//	            	rightCode: "upDown",
	            	handler: function (idx, data) {
	                    thiz.up(data);
	                }
                },
                {
	            	className : "down",
//	            	rightCode: "upDown",
	            	handler: function (idx, data) {
	                    thiz.down(data);
	                }
                },
                {
	            	className : "addTotalNum",
//	            	rightCode: "upDown",
	            	handler: function (idx, data) {
	                    thiz.addTotalNum(data);
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
       	 	//搜索
            $(".search-action").on("click", function () {
            	thiz.list.reloadPageData(1);
            });
        },
        
        // 启用
        up : function(data){
        	var id = data.id,
        		probability = data.probability,
        		thiz = this;
        	util.request({
        		confirm: true,
                msg: "确定要启用?",
                url: serverHost + "/eggPrize/upPrize.json",
                params: {
                	id: id,
                	isEnable: 1,
                	probability: probability
                },
                success: function (resp) {
                    if(resp.success) {
                    	messageTool.success("启用成功");
                    	thiz.list.reloadPageData();
                    } else {
                    	messageTool.error(resp.msg || "启用失败");
                    }
                }
            });
        },
        
        // 禁用
        down : function(data){
        	var id = data.id,
    			probability = data.probability,
    			thiz = this;
	    	util.request({
	    		confirm: true,
	            msg: "确定要禁用?",
	            url: serverHost + "/eggPrize/downPrize.json",
	            params: {
	            	id: id,
	            	isEnable: 0,
	            	probability: probability
	            },
	            success: function (resp) {
	                if(resp.success) {
	                	messageTool.success("禁用成功");
	                	thiz.list.reloadPageData();
	                } else {
	                	messageTool.error(resp.msg || "禁用失败");
	                }
	            }
	        });
        },
        
        // 修改奖品数量
        addTotalNum : function(data){
        	var id = data.id,
        		version = data.version,
        		oldProbability = data.probability,
        		thiz = this;
        	var win = winTool.create({
                title: "修改",
                height: 350,
                width: 600,
                showCancel: true,
                okName: "修改",
                cancelName: "关闭",
                type: "selector",
                selector: ".add-total-tpl",
                okFn: function (win) {
                	var flag = true;
                	var addTotalNum = win.find(".addTotalNum").val(),
                		probability = win.find(".probability").val(),
                		amountLimit = win.find(".amountLimit").val(),
                		level = win.find(".level").val()
                	if(!addTotalNum){
                		messageTool.error("必须输入增量!");
                		flag = false;
                	} 
                	if(!probability){
                		messageTool.error("必须输入奖品概率!");
                		flag = false;
                	} 
                	if(!amountLimit){
                		messageTool.error("必须输入限制金额!");
                		flag = false;
                	} 
                	if(!level){
                		messageTool.error("必须选择奖品等级!");
                		flag = false;
                	} 
                	if(flag) {
                		util.request({
                    		confirm: true,
                            msg: "确定要修改?",
                            url: serverHost + "/eggPrize/updatePrize.json",
                            params: {
                            	id: id,
                            	version: version,
                            	addTotalNum: addTotalNum,
                            	probability: probability,
                            	totalNum: Number(data.totalNum) + Number(addTotalNum),
                            	usableNum: Number(data.usableNum) + Number(addTotalNum),
                            	amountLimit: amountLimit,
                            	level: level,
                            	oldProbability: oldProbability
                            },
                            success: function (resp) {
                                if(resp.success) {
                                	win.close();
                                	messageTool.success("保存成功");
                                	thiz.list.reloadPageData();
                                } else {
                                	win.close();
                                	messageTool.error(resp.msg || "保存失败");
                                }
                            }
                        });
                	}
                }
            });
        	win.find(".totalNum").val(data.totalNum);
        	win.find(".usableNum").val(data.usableNum);
        	win.find(".name").val(data.name);
        	win.find(".amount").val(data.amount);
        	win.find(".type").val(data.type);
        	win.find(".probability").val(data.probability);
        	win.find(".amountLimit").val(data.amountLimit);
        	win.find(".level").val(data.level);
        	
        	win.find(".addTotalNum, .amountLimit").on("keyup", function(event){
        		var $amountInput = $(this);
        		//响应鼠标事件，允许左右方向键移动 
        		event = window.event || event;
        		if (event.keyCode == 37 | event.keyCode == 39) {
        			return;
        		}
        		//先把非数字的都替换掉，除了数字和. 
        		$amountInput.val($amountInput.val().replace(/[^\d]/g, ""));
			})
			win.find(".probability").on("keyup", function(event){
        		var $amountInput = $(this);
        		//响应鼠标事件，允许左右方向键移动 
        		event = window.event || event;
        		if (event.keyCode == 37 | event.keyCode == 39) {
        			return;
        		}
        		//先把非数字的都替换掉，除了数字和. 
        		$amountInput.val($amountInput.val().replace(/[^\d]/g, ""));
        		if($amountInput.val() > 100){
        			$amountInput.val(100);
        		}
			})
        },
    };
    return eggPrizeList;
});