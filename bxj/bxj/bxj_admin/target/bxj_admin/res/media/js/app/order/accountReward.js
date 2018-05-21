define(['jquery','app/common/util','app/common/advanceListTool',
        'app/common/dateTool','app/common/messageTool','app/common/commonTool'],
		function($, util, listTool, dateTool, messageTool, commonTool) {
    var rewardList = {
    		list:function(){
	            var thiz = this,
            list = listTool.create({
            	title: "打赏列表",
                selector: ".data-list",
                remote: true,
                url: serverHost + "/order/queryAccountRewardList.json",
                width: 600,
                height: 600,
                columns: [
                    {
                    	name: "会员id",
                    	width:80,
                    	fieldName: "userId",
                    	lineHiddenFn: function() {
					    	if($(".dimension").val() == 1 || $(".dimension").val() == 3 || $(".dimension").val() == 4) return false;
					    	return true;
					    }
                	},
                	{
                    	name: "会员账号",
                    	width:100,
                    	fieldName: "userName",
                    	lineHiddenFn: function() {
					    	if($(".dimension").val() == 1 || $(".dimension").val() == 3 || $(".dimension").val() == 4) return false;
					    	return true;
					    }
                	},
                	{
                    	name: "会员名称",
                    	width:100,
                    	fieldName: "nickName",
                    	lineHiddenFn: function() {
					    	if($(".dimension").val() == 1 || $(".dimension").val() == 3 || $(".dimension").val() == 4) return false;
					    	return true;
					    }
                	},
                    {
                    	name: "交易说明",
                    	width:80,
                    	fieldName: "videoName",
                    	lineHiddenFn: function() {
					    	if($(".dimension").val() == 4) return false;
					    	return true;
					    }
                    },
                    {
                    	name: "打赏金额(元)",
                    	width:80,
                    	fieldName: "money"
                    },
                    {
                    	name: "打赏人数",
                    	width:80,
                    	fieldName: "userSum",
                    	lineHiddenFn: function() {
					    	if($(".dimension").val() == 2 || $(".dimension").val() == 0) return false;
					    	return true;
					    }
                    },
                    {
                        name: "交易单号",
                        width:120,
                        fieldName: "orderId",
                        lineHiddenFn: function() {
					    	if($(".dimension").val() == 4) return false;
					    	return true;
					    }
                    },
                    {
                        name: "交易日期",
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
                    }
                	],
                paramFn: function () {
                	return {
                		userName: $(".userName").val(),
                		nickName: $(".nickName").val(),
                		orderId: $(".orderId").val(),
                		startTime: $(".startTime").val(),
                		endTime: $(".endTime").val(),
                		dimension: $(".dimension").val(),
                		videoName: $(".videoName").val()
                	}
                },
               
                initSearch: function (query) {
                	
                },
                tbars: [],
                classEvents : [
                	
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
       
    };
    return rewardList;
});