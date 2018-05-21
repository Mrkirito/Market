define(
		[ 'jquery', 'app/common/util', 'app/common/advanceListTool',
				'app/common/dateTool', 'app/common/messageTool',
				'app/common/chartsTool', 'app/common/exportTool',
				'app/common/winTool' ], function($, util, listTool, dateTool,
				messageTool, echarts, exportTool, winTool) {
			return {
				list : function() {
					var thiz = this; list = listTool.create({
						title : "邀请函每日数据统计",
						selector : ".data-list",
						remote : true,
						url : serverHost+ "/report/inviteJson.json",
						width : 600,
						height : 700,
						showPagebar: false,
						columns : [ {
							name : "日期",
							width : 150,
							fieldName : "dateStr"
						}, {
							name : "邀请函生成数",
							width : 150,
							fieldName : "count1"
						}, {
							name : "邀请函用户数",
							width : 150,
							fieldName : "count2"
						}, {
							name : "邀请函分享数",
							width : 150,
							fieldName : "count3"
						}],
						paramFn : function() {
							return thiz.getParams();
						},
						initSearch : function(query) {
						},
						classEvents : []
					});
					this.list = list;
					dateTool.renderDateRange(".reportTime", {
						applyClick : function(startDate, endDate) {
							$(".startTime").val(startDate);
							$(".endTime").val(endDate);
						}
					});
					$(".search-action").on("click", function() {
						thiz.list.reloadPageData(1);
					});
				},
				getParams : function() {
					return {
						startDate : $(".startTime").val(),
						endDate : $(".endTime").val()
					};
				}
			};
		});