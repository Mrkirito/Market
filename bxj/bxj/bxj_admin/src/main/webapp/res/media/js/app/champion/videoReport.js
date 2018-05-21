define(['jquery','app/common/util','app/common/advanceListTool',
        'app/common/dateTool','app/common/messageTool','app/common/commonTool',
        'app/common/imageView'],
		function($, util, listTool, dateTool, messageTool, commonTool) {
    var videoReportList = {
    		list:function(){
	            var thiz = this,
	            	id = commonTool.getUrlParam("id");
	            console.info(id);
            list = listTool.create({
            	title: "音/视频举报列表",
                selector: ".data-list",
                remote: true,
                url: serverHost + "/champion/queryVideoReportList.json",
                width: 600,
                height: 1500,
                columns: [
                          {
                          	name: "视频id",
                          	width:80,
                          	fieldName: "videoId"
                      	},
                      	{
                          	name: "视频名称",
                          	width:100,
                          	fieldName: "title"
                      	},
                      	{
                          	name: "被举报次数",
                          	width:100,
                          	fieldName: "reportCount",
                          	lineHiddenFn: function() {
      					    	if($(".dimension").val() == 1) return false;
      					    	return true;
      					    }
                      	},
                          {
                          	name: "被举报人数",
                          	width:80,
                          	fieldName: "reportUserCount",
                          	lineHiddenFn: function() {
      					    	if($(".dimension").val() == 1) return false;
      					    	return true;
      					    }
                          },
                          {
                          	name: "举报人id",
                          	width:80,
                          	fieldName: "userId",
                          	lineHiddenFn: function() {
      					    	if($(".dimension").val() == 2) return false;
      					    	return true;
      					    }
                          },
                          {
                              name: "举报人账号",
                              width:120,
                              fieldName: "phone",
                              lineHiddenFn: function() {
      					    	if($(".dimension").val() == 2) return false;
      					    	return true;
      					    }
                          },
                          {
                              name: "举报人用户名",
                              width:120,
                              fieldName: "name",
                              lineHiddenFn: function() {
      					    	if($(".dimension").val() == 2) return false;
      					    	return true;
      					    }
                          },
                          {
                              name: "举报类型",
                              width:120,
                              fieldName: "content",
                              lineHiddenFn: function() {
      					    	if($(".dimension").val() == 2) return false;
      					    	return true;
      					    }
                          },
                          {
                              name: "举报时间",
                              width:120,
                              fieldName: "createTime",
                              renderer : function(row, col, data,value) {
                              	return util.dateFormat2(value, 'YYYY-MM-DD HH:mm:ss');
                              },
                              lineHiddenFn: function() {
      					    	if($(".dimension").val() == 2) return false;
      					    	return true;
      					    }
                          }
                      	],
                paramFn: function () {
                	return thiz.getParams();
                },
               
                initSearch: function (query) {
                	
                },
                classEvents : []
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
        
        getParams: function () {
            return {
            	dimension: $(".dimension").val(),
            	startTime: $(".startTime").val(),
            	endTime: $(".endTime").val(),
            	title: $(".title").val(),
            	phone: $(".phone").val(),
            	name: $(".name").val(),
            	reportId: $(".reportId").val(),
            };
        },
    };
    return videoReportList;
});