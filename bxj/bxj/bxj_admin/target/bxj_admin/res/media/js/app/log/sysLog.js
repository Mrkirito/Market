define(['jquery','app/common/util','app/common/advanceListTool','app/common/winTool',
        'app/common/dateTool','app/common/messageTool','app/common/imageUploadTool','app/common/editorTool','app/common/commonTool',
        'app/common/imageView'],
		function($, util, listTool, winTool, dateTool, messageTool, imageUploadTool, editorTool, commonTool) {
    var logList = {
    		list:function(){
	            var thiz = this,
            list = listTool.create({
            	title: "操作日志列表",
                selector: ".data-list",
                remote: true,
                url: serverHost + "/log/querySysLogList.json",
                width: 600,
                height: 1500,
                columns: [
                    {
                    	name: "id",
                    	width:80,
                    	fieldName: "id"		
                	},
                	{
                    	name: "操作人",
                    	width:100,
                    	fieldName: "operationName"
                	},
                	{
                    	name: "操作地址",
                    	width:100,
                    	fieldName: "operationMethod"
                	},
                    {
                    	name: "操作描述",
                    	width:80,
                    	fieldName: "operationRemark"
                    },
                    {
                    	name: "操作ip",
                    	width:80,
                    	fieldName: "operationIp"
                    },
                    {
                        name: "操作时间",
                        width:120,
                        fieldName: "createTime",
					    sort: true,
					    sortName:"a.createTime",
                        renderer : function(row, col, data,value) {
                        	return util.dateFormat2(value, 'YYYY-MM-DD HH:mm:ss');
                        }
                    }
                	],
                paramFn: function () {
                	return thiz.getParams();
                },
               
                initSearch: function (query) {
                	
                },
            });
            this.list = list;
        	//时间渲染
        	dateTool.renderDate(".createTime");
       	 	//搜索
            $(".search-action").on("click", function () {
            	thiz.list.reloadPageData(1);
            });
        },
        getParams: function () {
            return {
            	createTime: $(".createTime").val(),
            	operationName: $(".operationName").val(),
            	operationIp: $(".operationIp").val(),
            	operationMethod: $(".operationMethod").val(),
            	operationRemark: $(".operationRemark").val()
            };
        },
    };
    return logList;
});