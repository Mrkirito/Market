define(['jquery','app/common/util','app/common/advanceListTool',
        'app/common/dateTool','app/common/messageTool','app/common/commonTool'],
		function($, util, listTool, dateTool, messageTool, commonTool) {
    var profitList = {
    		list:function(){
	            var thiz = this,
            list = listTool.create({
            	title: "收益列表",
                selector: ".data-list",
                remote: true,
                url: serverHost + "/order/queryAccountProfitList.json",
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
                    	name: "收益说明",
                    	width:80,
                    	fieldName: "videoName",
                    	lineHiddenFn: function() {
					    	if($(".dimension").val() == 4) return false;
					    	return true;
					    }
                    },
                    {
                    	name: "收益金额(元)",
                    	width:80,
                    	fieldName: "amount"
                    },
                    {
                        name: "当前收益余额(元)不跟维度查询条件变化",
                        width:120,
                        fieldName: "usableAmount",
                        lineHiddenFn: function() {
					    	if($(".dimension").val() == 1 || $(".dimension").val() == 3 || $(".dimension").val() == 4) return false;
					    	return true;
					    }
                    },
                    {
                        name: "交易单号",
                        width:120,
                        fieldName: "orderNo",
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
                		orderNo: $(".orderNo").val(),
                		startTime: $(".startTime").val(),
                		endTime: $(".endTime").val(),
                		dimension: $(".dimension").val(),
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
    return profitList;
});