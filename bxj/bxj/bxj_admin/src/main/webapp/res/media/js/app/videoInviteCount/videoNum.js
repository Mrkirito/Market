define(['jquery','app/common/util','app/common/advanceListTool','app/common/winTool',
        'app/common/dateTool','app/common/messageTool','app/common/imageUploadTool','app/common/editorTool'
        ,'app/common/commonTool','app/common/selectTool','app/common/listTool'],
		function($, util, listTool, winTool, dateTool, messageTool, imageUploadTool, editorTool, commonTool, selectTool,listTool2) {
    var userList = {
    		list:function(){
	            var thiz = this,
            list = listTool.create({
            	title: "观看视频统计列表",
                selector: ".data-list",
                remote: true,
                url: serverHost + "/videoInvite/getVideoListInfo.json",
                width: 600,
                height: 600,
                showTotals:true,
                columns: [
                    {
                    	name: "观看时间",
                    	width:80,
                    	fieldName: "lookTime",
                    	renderer : function(row, col, data,value) {
                          	return util.dateFormat2(value, 'YYYY-MM-DD');
                        }
                	},
                	 {
                      name: "观看用户",
                      width:120,
                      fieldName: "mobile"
                     },
                     {
                     	name: "观看视频数量",
                     	width:80,
                     	fieldName: "lookNum"		
                 	},
                 	 {
                     	name: "使用券数量",
                     	width:80,
                     	fieldName: "vocherNum"		
                 	},
                    {
                        name: "操作",
                        width:100,
						rightFixed: true,
                        renderer : function(rindx, cindex, data) {
                        	var st = '<a class="detail" title="详情" href="javascript:void(0)"><span class="glyphicon glyphicon-file" aria-hidden="true"></span><a>'
                        	//var st = '<i class="fa fa-file-photo-o"></i>'
               			 
               			 	return st;
                        }
                    }
                	],
                paramFn: function () {
                	return thiz.getParams();
                },
               
                initSearch: function (query) {
                	
                },
                
                classEvents : [{
   	            	className : "detail",
	            	handler: function (idx, data) {
	                    thiz.detail(data);
	                }
                },
                ]
            });
            this.list = list;
        	
       	 	//搜索
            $(".search-action").on("click", function () {
            	thiz.list.reloadPageData(1);
            });
            // 时间渲染
            dateTool.renderDateRange(".lookTime", {
				applyClick : function(startDate, endDate) {
					$(".lookStartTime").val(startDate);
					$(".lookEndTime").val(endDate);
				}
			});
        },
        // 观看视频详情
        detail: function(data){
        	var thiz = this;
        	var win = winTool.create({
                title: "详情",
                height: 500,
                width: 900,
                showCancel: false,
                okName: "关闭",
                html: "<div style='padding: 10px; margin-left: 40px;'>" +
                "<div class='row'>" +
                '<div class="col-sm-10">' +
                "标题：<input type='text' class='topic' style='width: 400px;padding: 5px 6px; border: 1px solid #ddd;'>" +
                '</div>' +
                '<div class="col-sm-2">' +
                "<a class='btn btn-info searchLook' href='javascript:void(0);' style='margin-left: 10px;'>查询</a>" +
                '</div>' +
                "</div>" +
                "</div>" +
                "<div class='detail-data-list' style='overflow-y: auto;  height: 430px; overflow-x: hidden;'></div>",
                okFn: function (win) {
                	win.close();
                },
                overflow: "hidden"
             });
        	console.log(win);
        	console.log(win.opts.html);
        	console.log(win.find(".detail-data-list"));
        	thiz.detailList(win, data);
        },
        
        getParams: function () {
            return {
            	lookStartTime: $(".lookStartTime").val(),
            	lookEndTime: $(".lookEndTime").val(),
            	mobile: $(".mobile").val(),
            };
        },
        
        // 观看视频详情列表
        detailList: function(win, data){
        	var thiz = this;
        	thiz.detailsList = listTool2.create({
            	title: "观看视频统计列表",
                selector: win.find(".detail-data-list"),
                remote: true,
                url: serverHost + "/videoInvite/queryLookVideoDetail.json",
                showTotals:true,
                columns: [
                    {
                      	name: "视频id",
                      	fieldName: "fid"		
                   	},
                    {
                    	name: "观看时间",
                    	fieldName: "playTime",
                    	renderer : function(row, col, data,value) {
                          	return util.dateFormat2(value, 'YYYY-MM-DD');
                        }
                	},
                	{
                        name: "视频标题",
                        fieldName: "title",
                        rightFixed: true
                    },
                	],
                paramFn: function () {
                	return {
                		userId : data.userId,
                		playTime : util.dateFormat2(data.lookTime, 'YYYY-MM-DD'),
                		title : win.find('.topic').val()
                	};
                },
               
                initSearch: function (query) {
                },
                
                classEvents : [{
                	
                },
                ]
            });
        	thiz.detailsList.loadPageData(1);
        	//搜索
            win.find(".searchLook").on("click", function () {
            	thiz.detailsList.loadPageData(1);
            });
        }

    };
    return userList;
});