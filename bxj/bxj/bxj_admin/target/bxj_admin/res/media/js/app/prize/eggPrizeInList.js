define(['jquery','app/common/util','app/common/advanceListTool',
        'app/common/dateTool','app/common/messageTool','app/common/commonTool','app/common/winTool','app/common/imageView'],
		function($, util, listTool, dateTool, messageTool, commonTool, winTool) {
    var eggPrizeInList = {
    		list:function(){
	            var thiz = this,
            list = listTool.create({
            	title: "砸蛋奖品增量列表",
                selector: ".data-list",
                remote: true,
                url: serverHost + "/eggPrize/queryEggPrizeInList.json",
                width: 600,
                height: 600,
                columns: [
                    {
                    	name: "增量id",
                    	width:50,
                    	fieldName: "id"
                	},
                    {
                        name: "奖品名称",
                        width:80,
                        fieldName: "name"
                    },
                    {
                    	name: "增量",
                    	width:60,
                    	fieldName: "incrementNum"
                    },
                    {
                    	name: "限制金额",
                    	width:60,
                    	fieldName: "amountLimit"
                    },
                    {
                        name: "增量状态",
                        width:80,
                        fieldName: "status",
					    renderer : function(row, col, data,value) {
					    	// 已完成增量
                        	if(value == 0){
                        		return "<span class='label label-table label-success'>已完成<i></i></span>";
                        	// 还没增量
                        	} else if(value == 1){
                        		return "<span class='label label-table label-info'>未完成<i></i></span>";
                        	}
                        }
                    },
                    {
                        name: "创建时间",
                        width:80,
                        fieldName: "createTime",
					    renderer : function(row, col, data,value) {
					    	return util.dateFormat2(value, 'YYYY-MM-DD HH:mm:ss');
					    }
                    },
                    {
                        name: "操作",
                        width:100,
                        renderer : function(rindx, cindex, data) {
                        	var upDownButton = '';
                        	if (data.status == 1){
                        		upDownButton = '<button class="btn btn-danger delete">删除</button>';
                        	}
                        	var st = '<div class="action-buttons">' +
			               			 '<div class="btn-group">' +
			               			upDownButton +
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
                		status: $(".status").val()
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
                                    name: "新增奖品增量",
//                                    rightCode: "add",
                                    url: serverHost + "/eggPrize/eggPrizeInAdd.jhtml"
                                }
                            ]
                        }
                ],
                classEvents : [{
	            	className : "delete",
//	            	rightCode: "upDown",
	            	handler: function (idx, data) {
	                    thiz.delete1(data);
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
        
        // 删除
        delete1 : function(data){
        	var thiz = this;
    		util.request({
        		confirm: true,
                msg: "确定要删除?",
                url: serverHost + "/eggPrize/deletePrizeIn.json",
                params: {
                	id: data.id,
                	updateTime: util.dateFormat2(data.updateTime, 'YYYY-MM-DD HH:mm:ss')
                },
                success: function (resp) {
                    if(resp.success) {
                    	messageTool.success("删除成功");
                    	thiz.list.reloadPageData();
                    } else {
                    	messageTool.error(resp.msg || "删除失败");
                    }
                }
            });
        }
    };
    return eggPrizeInList;
});