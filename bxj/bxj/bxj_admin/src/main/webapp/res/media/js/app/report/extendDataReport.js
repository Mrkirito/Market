define(
		[ 'jquery', 'app/common/util', 'app/common/advanceListTool',
				'app/common/dateTool', 'app/common/messageTool',
				'app/common/chartsTool', 'app/common/exportTool',
				'app/common/winTool' ],
		function($, util, listTool, dateTool, messageTool, echarts, exportTool,winTool) {
			var reportList = {
				list : function() {
					var listJson = [
							{
								name : "时间",
								width : 80,
								fieldName : "dataTime",
								renderer : function(row, col, data, value) {
									return util
											.dateFormat2(value, 'YYYY-MM-DD');
								},
								sort : true,
								sortName : "dataTime",
								defaultSort : "desc"
							},
							{
								name : "渠道及优化",
								width : 80,
								fieldName : "channelNum"
							},
							{
								name : "日常",
								width : 80,
								fieldName : "everyNum"
							},
							{
								name : "微课堂",
								width : 80,
								fieldName : "classroomNum"
							},
							{
								name : "线下",
								width : 80,
								fieldName : "lineNum"
							},
							{
								name : "活动",
								width : 80,
								fieldName : "activityNum"
							},
							{
								name : "合计",
								width : 80,
								fieldName : "totalNum"
							},
							{
								name : "操作",
								width : 100,
								renderer : function(rindx, cindex, data) {
									var st = '<div class="action-buttons">'
											+ '<div class="btn-group">'
											+ '<button class="btn btn-info updateData">修改</button>'
											+ '</div></div>';
									return st;
								}
							} ];
					var thiz = this, list = listTool.create({
						title : "推广运营数据统计列表",
						selector : ".data-list",
						remote : true,
						url : serverHost+ "/extend/queryExtendDataReportList.json",
						width : 600,
						height : 600,
						columns : listJson,
						paramFn : function() {
							return thiz.getParams();
						},

						initSearch : function(query) {
						},
						tbars : [ {
							icon : 'fa fa-plus',
							name : "新增",
							handler : function(idx, data) {
								thiz.addData();
							}
						}],
						classEvents : [ {
							className : "updateData",
							handler : function(idx, data) {
								thiz.updateData(data);
							}
						} ]
					});
					this.list = list;
					// 时间渲染
					dateTool.renderDateRange(".reportTime", {
						applyClick : function(startDate, endDate) {
							$(".startTime").val(startDate);
							$(".endTime").val(endDate);
						}
					});
					//搜索
					$(".search-action").on("click", function() {
						thiz.list.reloadPageData(1);
					});
			        thiz.newUserLine();
				},

				getParams : function() {
					return {
						startTime : $(".startTime").val(),
						endTime : $(".endTime").val()
					};
				},
				addData :function(){
					var thiz=this;
					var win = winTool.create({
	                    title: "新增",
	                    height: 400,
	                    width: 600,
	                    showCancel: true,
	                    okName: "保存",
	                    cancelName: "关闭",
	                    type: "selector",
	                    selector: ".add-data-tpl",
	                    okFn: function (win) {
	                    		var isValidateTrue=true;
	                    		var date=win.find('input[name=dataTime]').val();
	            				if(!date){
	                				isValidateTrue=false;
	                				messageTool.error('数据统计时间不可为空');
	                				return false;
	            				}
	                    		win.find('input.is_number').each(function(i,o){
	                    			o=$(o);
	                    			var v=o.val();
	                    			if(isNaN(v)){
	                    				isValidateTrue=false;
	                    				messageTool.error(o.parent().siblings().text()+'必须为数字');
	                    				return false;
	                    			}
	                    		});
	                    		if(isValidateTrue){                    			
	                    			util.request({
	                    				confirm : true,
	                    				msg : "确定要新增?",
	                    				url : serverHost+ "/extend/saveData.json",
	                    				params : win.find('#form').serialize()+'&dataTime='+date,
	                    				success : function(resp) {
	                    					messageTool.info("新增成功!", function() {
	                    						win.close();
	                    						thiz.list.reloadPageData();
	                    					});
	                    				}
	                    			});
	                    		}
	                    }
	                 });
					dateTool.renderDate("input[name=dataTime]");
				},
				updateData : function(data) {
	               	var thiz = this,
	        		fid = data.id;
	               	util.ajax({
	                url: serverHost + "/extend/getDataById.json",
	                params: {id : fid},
	                success: function (resp) {
	                    if(resp.success) {
	                    	var video = resp.model;  
	                    	var win = winTool.create({
	                            title: "修改",
	                            height: 400,
	                            width: 600,
	                            showCancel: true,
	                            okName: "保存",
	                            cancelName: "关闭",
	                            type: "selector",
	                            selector: ".add-data-tpl",
	                            okFn: function (win) {
	                          		var isValidateTrue=true;
	                          		var date=win.find('input[name=dataTime]').val();
		            				if(!date){
		                				isValidateTrue=false;
		                				messageTool.error('数据统计时间不可为空');
		                				return false;
		            				}
	                        		win.find('input.is_number').each(function(i,o){
	                        			o=$(o);
	                        			var v=o.val();
	                        			if(isNaN(v)){
	                        				isValidateTrue=false;
	                        				messageTool.error(o.parent().siblings().text()+'必须为数字');
	                        				return false;
	                        			}
	                        		});
	                        		if(isValidateTrue){                             	
	                        			var paras=win.find('#form').serialize();
		                            	util.request({
		                            		confirm: true,
		                                    msg: "确定要更改?",
		                                    url: serverHost + "/extend/updateData.json",
		                                    dataType:"json",
		                                    params:paras ,
		                                    success: function (resp) {
		                                        if(resp.success) {
		                                        	messageTool.success("更新成功");
		                                        	win.close();
		                                        	thiz.list.reloadPageData();
		                                        } else {
		                                        	messageTool.error(resp.msg || "更新失败");
		                                        }
		                                    }
		                                });
	                        	  }
	                        	}
	                     });
	                    	win.find('input,select').each(function(i,o){
		                    		o=$(o);
		                    		var name=o.attr('name');
		                    		var val=video[name];
		                    		if(name=='dataTime'){
		                    			o.val(util.dateFormat2(val, 'YYYY-MM-DD'));
		                    		}else{
		                    			o.val(val);
		                    		}
	                    	});
	            			dateTool.renderDate("input[name=dataTime]");
	                   }  	
	                	}
	               	});	
				},
		        newUserLine: function(){
		        	var thiz = this;
		    		util.ajax({
		                url: serverHost + "/extend/queryEchartsData.json",
		                success: function (resp) {
		                    if(resp.success) {
		                    	var data = resp.model;
		                    	thiz.renderNewEcharts(data);
		                    }
		                }
		            });

		        },
		        renderNewEcharts: function(data){
		            var myChart = echarts.init(document.getElementById('newUser'));
		            var d = [],
		            	d1 = [],
		            	d2 = [],d3 = [],d4 = [],d5 = [];
		            for (var int = 0; int < data.length; int++) {
		            	var object = data[int],
		            		date = new Date(object.dataTime);
						d.push([
						     [date.getFullYear(), date.getMonth() + 1, date.getDate()].join('-'),
						     object.channelNum
						]);
						
						d1.push([
						     [date.getFullYear(), date.getMonth() + 1, date.getDate()].join('-'),
						     object.everyNum
						]);
						d2.push([
						     [date.getFullYear(), date.getMonth() + 1, date.getDate()].join('-'),
						     object.classroomNum
						]);
						d3.push([
							     [date.getFullYear(), date.getMonth() + 1, date.getDate()].join('-'),
							     object.lineNum
						]);
						d4.push([
							     [date.getFullYear(), date.getMonth() + 1, date.getDate()].join('-'),
							     object.activityNum
						]);
						d5.push([
							     [date.getFullYear(), date.getMonth() + 1, date.getDate()].join('-'),
							     object.totalNum
						]);
					}
		        	var start = new Date();
		            start.setTime(start.getTime() - 7*24*60*60*1000);
		            var startValue = start.getFullYear()+"-" + (start.getMonth()+1) + "-" + start.getDate();
		            var end = new Date();
		            end.setTime(end.getTime() - 24*60*60*1000);
		            var endValue = end.getFullYear()+"-" + (end.getMonth()+1) + "-" + end.getDate();
		            
		            // 指定图表的配置项和数据
		            var option = {
		            	    title : {
		            	        text : '运营推广统计'
		            	    },
		            	    tooltip : {
		            	        trigger: 'axis'
		            	    },
		            	    toolbox: {
		            	        show : true,
		            	        feature: {
		            	            dataZoom: {
		            	                yAxisIndex: 'none'
		            	            },
		            	            restore: {},
		            	            saveAsImage: {}
		            	        }
		            	    },
		            	    dataZoom: [{
		            	        show: true,
		            	        startValue : startValue,
		            	        endValue : endValue
		            	    }],
		            	    legend : {
		            	        data : ['渠道及优化','日常','微课堂','线下','活动','合计']
		            	    },
		            	    xAxis: {
		            	        type: 'time',
		            	        splitLine: {
		            	            show: false
		            	        }
		            	    },
		            	    yAxis : {
		        	            type : 'value'
		            	    },
		            	   
		            	    series : [
		            	        {
		            	            name: '渠道及优化',
		            	            type: 'line',
		            	            showAllSymbol: true,
		            	            symbolSize: function (value){
		            	                return 10;
		            	            },
		            	            data: d
		            	        },
		            	        {
		            	            name: '日常',
		            	            type: 'line',
		            	            showAllSymbol: true,
		            	            symbolSize: function (value){
		            	                return 10;
		            	            },
		            	            data: d1
		            	        },
		            	        {
		            	            name: '微课堂',
		            	            type: 'line',
		            	            showAllSymbol: true,
		            	            symbolSize: function (value){
		            	                return 10;
		            	            },
		            	            data: d2
		            	        },
		            	        {
		            	            name: '线下',
		            	            type: 'line',
		            	            showAllSymbol: true,
		            	            symbolSize: function (value){
		            	                return 10;
		            	            },
		            	            data: d3
		            	        },
		            	        {
		            	        	name: '活动',
		            	        	type: 'line',
		            	        	showAllSymbol: true,
		            	        	symbolSize: function (value){
		            	        		return 10;
		            	        	},
		            	        	data: d4
		            	        },
		            	        {
		            	            name: '合计',
		            	            type: 'line',
		            	            showAllSymbol: true,
		            	            symbolSize: function (value){
		            	                return 10;
		            	            },
		            	            data: d5
		            	        }
		            	    ],
		            	    color : [
		                 	        '#fb0303','#dacd21','#1881de','#C815D7','#F19207','#0E58EC'
		             	    ]
		            	};
		            // 使用刚指定的配置项和数据显示图表。
		            myChart.setOption(option);
		        }
			};
			return reportList;
		});