define(['jquery','app/common/util','app/common/advanceListTool','app/common/winTool',
        'app/common/dateTool','app/common/messageTool','app/common/imageUploadTool','app/common/editorTool'
        ,'app/common/commonTool','app/common/selectTool'],
		function($, util, listTool, winTool, dateTool, messageTool, imageUploadTool, editorTool,commonTool) {
    var userList = {
    		list:function(){
	            var thiz = this,
            list = listTool.create({
            	title: "邀请统计列表",
                selector: ".data-list",
                remote: true,
                url: serverHost + "/videoInvite/lookInviteInfo.json",
                width: 600,
                height: 600,
                showTotals : true,
                columns: [
                    {
                    	name: "邀请人ID",
                    	width:80,
                    	fieldName: "inviteUserId"		
                	},
                	 {
                    	name: "手机号",
                    	width:80,
                    	fieldName: "mobile"		
                	},
                	 {
                      name: "日期",
                      width:120,
                      fieldName: "inviteAt",
                      renderer : function(row, col, data,value) {
                      	return util.dateFormat2(value, 'YYYY-MM-DD');
                      }
                     },
                     {
                     	name: "每日成功邀请人数",
                     	width:80,
                     	fieldName: "inviteNum"		
                 	},
                    
                	],
                paramFn: function () {
                	return thiz.getParams();
                },
               
                initSearch: function (query) {
                	
                },
                
                classEvents : [ 
                ]
            });
            this.list = list;
        	//时间渲染
        	dateTool.renderDate(".sinviteAt");
        	
       	 	//搜索
            $(".search-action").on("click", function () {
            	thiz.list.reloadPageData(1);
            });
        },
        
        getParams: function () {
            return {
            	/*title: $(".stitle").val(),
            	auditStatus: $(".sAuditStatus").val(),*/
            	inviteAt: $(".sinviteAt").val(),
//            	offlineTime: $(".sOfflineTime").val(),
            };
        }
    };
    return userList;
});