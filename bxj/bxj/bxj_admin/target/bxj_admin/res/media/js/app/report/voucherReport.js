define(['jquery','app/common/util','app/common/advanceListTool','app/common/winTool',
        'app/common/dateTool','app/common/messageTool','app/common/imageUploadTool','app/common/editorTool','app/common/commonTool',
        'app/common/imageView'],
		function($, util, listTool, winTool, dateTool, messageTool, imageUploadTool, editorTool, commonTool) {
    var reportList = {
    		list:function(){
	            var thiz = this,
            list = listTool.create({
            	title: "视频券统计列表",
                selector: ".data-list",
                remote: true,
                url: serverHost + "/report/queryVoucherReportList.json",
                width: 600,
                height: 600,
                columns: [
                    {
                    	name: "时间",
                    	width:80,
                    	fieldName: "createAt",
                    	renderer : function(row, col, data,value) {
                        	return util.dateFormat2(value, 'YYYY-MM-DD');
                        },
                        lineHiddenFn: function() {
					    	if($(".dimension").val() == 1) return false;
					    	return true;
					    },
					    sort: true,
					    sortName:"createAt",
					    defaultSort: "desc"
                	},
                	{
                    	name: "发券数",
                    	width:100,
                    	fieldName: "sendSum"
                	},
                	{
                    	name: "发券的人数",
                    	width:100,
                    	fieldName: "sendUserCount"
                	},
                    {
                    	name: "用券数",
                    	width:80,
                    	fieldName: "useSum"
                    },
                    {
                    	name: "用券的人数",
                    	width:80,
                    	fieldName: "useUserCount"
                    },
                    {
                        name: "有券的人数",
                        width:120,
                        fieldName: "haveUserCount",
                        lineHiddenFn: function() {
                        	if($(".startTime").val() || $(".endTime").val()){
                        		return true;
                        	}
					    	if($(".dimension").val() == 1) return true;
					    	return false;
					    }
                    },
                	],
                paramFn: function () {
                	return {
                		dimension: $(".dimension").val() || null,
                		startTime: $(".startTime").val(),
                		endTime: $(".endTime").val()
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
            dateTool.renderDateRange(".reportTime", {
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
    return reportList;
});