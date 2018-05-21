define(['jquery','app/common/util','app/common/advanceListTool', 'app/common/dateTool', 'app/common/messageTool' 
        ,'app/common/chartsTool', 'app/common/exportTool', 'app/common/winTool'],
		function($, util, listTool, dateTool, messageTool, echarts, exportTool, winTool) {
    var reportList = {
    		list:function(){
	            var thiz = this,
            list = listTool.create({
            	title: "用户数据统计列表",
                selector: ".data-list",
                remote: true,
                url: serverHost + "/report/queryUserDataReportList.json",
                width: 600,
                height: 600,
                columns: [
                    {
                    	name: "时间",
                    	width:80,
                    	fieldName: "dataTime",
                    	renderer : function(row, col, data,value) {
                        	return util.dateFormat2(value, 'YYYY-MM-DD');
                        },
					    sort: true,
					    sortName:"dataTime",
					    defaultSort: "desc"
                	},
					{
						name: "行家app新增用户",
						width:80,
						fieldName: "hjappNew"
					},
					{
						name: "行家app启动用户",
						width:80,
						fieldName: "hjappStart"
					},
					{
						name: "行家网PV",
						width:80,
						fieldName: "hjwPv"
					},
					{
						name: "行家网UV",
						width:80,
						fieldName: "hjwUv"
					},
					{
                        name: "操作",
                        width:100,
                        renderer : function(rindx, cindex, data) {
                        	var st = '<div class="action-buttons">' +
			               			 '<div class="btn-group">' +
			               			'<button class="btn btn-info update">修改</button>' +
			               			 '</div>' +
			               			 '</div>';
                        	return st;
                        }
                    }
                	],
                paramFn: function () {
            		thiz.getParams();
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
    	                        icon: 'fa fa-file-excel-o',
    	                        name: "导出excel",
    							rightCode: "export",
    	                        handler: function (e, btn, tool) {
    	                            var action = serverHost + "/report/exportExcel.json";
    	                            exportTool.export(action, tool.getTotalCount(), thiz.getParams(), tool);
    	                        }
    	                    }
                        ]
                    }
                ],
                classEvents : [
                   {
	   	              className : "update",
	   	              rightCode: "update",
	   	              handler: function (idx, data) {
	   	                  thiz.updateData(data);
	   	              }
                   }
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
            
       	 	//搜索
            $(".search-action").on("click", function () {
            	thiz.list.reloadPageData(1);
            });
            
            thiz.newUserLine();
            thiz.activeUserLine();
            thiz.salesUserLine();
				thiz.bbnewUserLine();
				thiz.bbwactiveUserLine();
				thiz.bbappnewUserLine();
				thiz.bbappactiveUserLine();
				thiz.hjappnewUserLine();
				thiz.hjappactiveUserLine();
        },
       
        getParams: function () {
            return {
            	startTime: $(".startTime").val(),
        		endTime: $(".endTime").val()
            };
        },
        
        updateData: function (data) {
        	var thiz = this;
        	var win = winTool.create({
                title: "修改",
                height: 500,
                width: 650,
                showCancel: true,
                okName: "修改",
                cancelName: "关闭",
                type: "selector",
                selector: ".add-data-tpl",
                okFn: function (win) {
                	var flag = true;
                	var dataTime = win.find(".dataTime").val(),
                		newIosNum = win.find(".newIosNum").val(),
                		newAndroidNum = win.find(".newAndroidNum").val(),
                		activeIosNum = win.find(".activeIosNum").val(),
                		activeAndroidNum = win.find(".activeAndroidNum").val(),
						newAddTarget =  win.find(".newAddTarget").val(),
						newStartTarget =  win.find(".newStartTarget").val(),
						salesTotalTarget =  win.find(".salesTotalTarget").val(),
                		activeSum = win.find(".activeSum").val(),
						timesIosNum = win.find(".timesIosNum").val(),
						timesAndroidNum = win.find(".timesAndroidNum").val(),
						salesTotal = win.find(".salesTotal").val();
						salesProfits = win.find(".salesProfits").val();

						bbwNew = win.find(".bbwNew").val();
						bbwStart = win.find(".bbwStart").val();
						bbappNew = win.find(".bbappNew").val();
						bbappStart = win.find(".bbappStart").val();
						hjappNew = win.find(".hjappNew").val();
						hjappStart = win.find(".hjappStart").val();
						hjwPv= win.find(".hjwPv").val();
						hjwUv= win.find(".hjwUv").val();

                	if(!dataTime){
                		messageTool.error("必须选择统计时间!");
                		flag = false;
                	} 
                	if(!newIosNum){
                		messageTool.error("必须输入ios新增用户!");
                		flag = false;
                	} 
                	if(!newAndroidNum){
                		messageTool.error("必须输入安卓新增用户!");
                		flag = false;
                	} 
                	if(!activeIosNum){
                		messageTool.error("必须输入ios启动用户!");
                		flag = false;
                	}
                	if(!activeAndroidNum){
                		messageTool.error("必须输入安卓启动用户!");
                		flag = false;
                	}
					if(!timesIosNum){
						messageTool.error("必须输入ios启动次数!");
						flag = false;
					}
					if(!timesAndroidNum){
						messageTool.error("必须输入安卓启动次数!");
						flag = false;
					}
					if(!salesTotal){
						messageTool.error("必须输入总销售额!");
						flag = false;
					}
					if(!salesProfits){
						messageTool.error("必须输入毛利!");
						flag = false;
					}
                	if(flag) {
                		util.request({
                    		confirm: true,
                            msg: "确定要修改?",
                            url: serverHost + "/report/updateUserData.json",
                            params: {
                            	id: data.id,
                            	dataTime: dataTime,
                            	newIosNum: newIosNum,
                            	newAndroidNum: newAndroidNum,
                            	activeIosNum: activeIosNum,
                            	activeAndroidNum: activeAndroidNum,
								newAddTarget: newAddTarget,
								newStartTarget : newStartTarget,
								salesTotalTarget: salesTotalTarget,
								timesIosNum: timesIosNum,
								timesAndroidNum: timesAndroidNum,
								salesTotal:salesTotal,
								salesProfits:salesProfits,
								bbwNew:bbwNew,
								bbwStart:bbwStart,
								bbappNew:bbappNew,
								bbappStart:bbappStart,
								hjappNew:hjappNew,
								hjappStart:hjappStart,
								hjwPv:hjwPv,
								hjwUv:hjwUv
                            },
                            success: function (resp) {
                                if(resp.success) {
                                	win.close();
                                	messageTool.success("修改成功");
                                	thiz.list.reloadPageData();
                                } else {
                                	win.close();
                                	messageTool.error(resp.msg || "修改失败");
                                }
                            }
                        });
                	}
                }
            });
        	// 时间渲染
        	dateTool.renderDate(win.find(".dataTime"));
			win.find(".newIosNum, .newAndroidNum, .activeIosNum, .activeAndroidNum, .timesIosNum, .timesAndroidNum").on("keyup", function(event){
        		var $amountInput = $(this);
        		//响应鼠标事件，允许左右方向键移动 
        		event = window.event || event;
        		if (event.keyCode == 37 | event.keyCode == 39) {
        			return;
        		}
        		//先把非数字的都替换掉，除了数字和. 
        		$amountInput.val($amountInput.val().replace(/[^\d]/g, ""));
			})
        	win.find(".dataTime").val(util.dateFormat2(data.dataTime, 'YYYY-MM-DD'));
			win.find(".newIosNum").val(data.newIosNum);
			win.find(".newAndroidNum").val(data.newAndroidNum);
			win.find(".activeIosNum").val(data.activeIosNum);
			win.find(".activeAndroidNum").val(data.activeAndroidNum);
			
			win.find(".newAddTarget").val(data.newAddTarget);
			win.find(".newStartTarget").val(data.newStartTarget);
			win.find(".salesTotalTarget").val(data.salesTotalTarget);
			
			win.find(".timesIosNum").val(data.timesIosNum);
			win.find(".timesAndroidNum").val(data.timesAndroidNum);
			win.find(".salesTotal").val(data.salesTotal);
			win.find(".salesProfits").val(data.salesProfits);

			win.find(".bbwNew").val(data.bbwNew);
			win.find(".bbwStart").val(data.bbwStart);
			win.find(".bbappNew").val(data.bbappNew);
			win.find(".bbappStart").val(data.bbappStart);
			win.find(".hjappNew").val(data.hjappNew);
			win.find(".hjappStart").val(data.hjappStart);
			win.find(".hjwPv").val(data.hjwPv);
			win.find(".hjwUv").val(data.hjwUv);
        },

        newUserLine: function(){
        	var thiz = this;
    		util.ajax({
                url: serverHost + "/report/queryEchartsData.json",
                params: {
                	echartsType: 1
                },
                success: function (resp) {
                    if(resp.success) {
                    	var data = resp.model;
                    	thiz.renderNewEcharts(data);
                    }
                }
            });

        },
        
        activeUserLine: function(){
        	var thiz = this;
    		util.ajax({
                url: serverHost + "/report/queryEchartsData.json",
                params: {
                	echartsType: 2
                },
                success: function (resp) {
                    if(resp.success) {
                    	var data = resp.model;
                    	thiz.renderActiveEcharts(data);
                    }
                }
            });

        },
        salesUserLine:function(){
        	var thiz = this;
    		util.ajax({
                url: serverHost + "/report/queryEchartsData.json",
                params: {
                	echartsType: 3
                },
                success: function (resp) {
                    if(resp.success) {
                    	var data = resp.model;
                    	thiz.renderSalesEcharts(data);
                    }
                }
            });


        },
		bbnewUserLine: function(){
			var thiz = this;
			util.ajax({
				url: serverHost + "/report/queryEchartsData.json",
				params: {
					echartsType: 4
				},
				success: function (resp) {
					if(resp.success) {
						var data = resp.model;
						thiz.renderbbNewEcharts(data);
					}
				}
			});
		},
		bbwactiveUserLine: function(){
			var thiz = this;
			util.ajax({
				url: serverHost + "/report/queryEchartsData.json",
				params: {
					echartsType: 10
				},
				success: function (resp) {
					if(resp.success) {
						var data = resp.model;
						thiz.renderbbactiveEcharts(data);
					}
				}
			});
		},
		bbappnewUserLine: function(){
			var thiz = this;
			util.ajax({
				url: serverHost + "/report/queryEchartsData.json",
				params: {
					echartsType: 6
				},
				success: function (resp) {
					if(resp.success) {
						var data = resp.model;
						thiz.renderbbappnewEcharts(data);
					}
				}
			});
		},
		bbappactiveUserLine: function(){
			var thiz = this;
			util.ajax({
				url: serverHost + "/report/queryEchartsData.json",
				params: {
					echartsType: 7
				},
				success: function (resp) {
					if(resp.success) {
						var data = resp.model;
						thiz.renderappactiveEcharts(data);
					}
				}
			});
		},
		hjappnewUserLine: function(){
			var thiz = this;
			util.ajax({
				url: serverHost + "/report/queryEchartsData.json",
				params: {
					echartsType: 8
				},
				success: function (resp) {
					if(resp.success) {
						var data = resp.model;
						thiz.renderhjNewEcharts(data);
					}
				}
			});
		},
		hjappactiveUserLine: function(){
			var thiz = this;
			util.ajax({
				url: serverHost + "/report/queryEchartsData.json",
				params: {
					echartsType: 9
				},
				success: function (resp) {
					if(resp.success) {
						var data = resp.model;
						thiz.renderhjappactiveEcharts(data);
					}
				}
			});
		},
        // 新增用户charts
        renderNewEcharts: function(data){
        	// 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('newUser'));
            var d = [],
            	d1 = [],
            	d2 = [];
            for (var int = 0; int < data.length; int++) {
            	var object = data[int],
            		date = new Date(object.dataTime);
				d.push([
				     [date.getFullYear(), date.getMonth() + 1, date.getDate()].join('-'),
				     object.newSumDay
				]);
				d1.push([
					     [date.getFullYear(), date.getMonth() + 1, date.getDate()].join('-'),object.newAddTarget
				])
			}
        	var start = new Date();
            start.setTime(start.getTime() - 31*24*60*60*1000);
            var startValue = start.getFullYear()+"-" + (start.getMonth()+1) + "-" + start.getDate();
            var end = new Date();
            end.setTime(end.getTime() - 24*60*60*1000);
            var endValue = end.getFullYear()+"-" + (end.getMonth()+1) + "-" + end.getDate();
            
            // 指定图表的配置项和数据
            var option = {
            	    title : {
            	        text : '保险家新增用户'
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
            	        filterMode: 'filter',
            	        startValue : startValue,
            	        endValue : endValue
            	    }],
            	    legend : {
            	        data : ['新增用户总数','目标用户总数']
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
            	            name: '新增用户总数',
            	            type: 'line',
            	            showAllSymbol: true,
            	            symbolSize: function (value){
            	                return 3;
            	            },
            	            data: d
            	        },{
            	            name: '目标用户总数',
            	            type: 'line',
            	            showAllSymbol: true,
            	            symbolSize: function (value){
            	                return 3;
            	            },
            	            data: d1
            	        }
            	    ],
            	    color : [
                 	        '#dacd21','#fb0303'
             	    ]
            	};
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        },
		renderbbNewEcharts: function(data){
			// 基于准备好的dom，初始化echarts实例
			var myChart = echarts.init(document.getElementById('bbwNew'));
			var d = [],
				d1 = [],
				d2 = [];
			for (var int = 0; int < data.length; int++) {
				var object = data[int],
					date = new Date(object.dataTime);
				d.push([
					[date.getFullYear(), date.getMonth() + 1, date.getDate()].join('-'),
					object.bbwNew
				]);
				d1.push([
					[date.getFullYear(), date.getMonth() + 1, date.getDate()].join('-'),
					object.bbwStart
				]);

			}
			var start = new Date();
			start.setTime(start.getTime() - 31*24*60*60*1000);
			var startValue = start.getFullYear()+"-" + (start.getMonth()+1) + "-" + start.getDate();
			var end = new Date();
			end.setTime(end.getTime() - 24*60*60*1000);
			var endValue = end.getFullYear()+"-" + (end.getMonth()+1) + "-" + end.getDate();

			// 指定图表的配置项和数据
			var option = {
				title : {
					text : '保保网数据'
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
					filterMode: 'filter',
					startValue : startValue,
					endValue : endValue
				}],
				legend : {
					data : ['保保网PV','保保网UV']
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
						name: '保保网PV',
						type: 'line',
						showAllSymbol: true,
						symbolSize: function (value){
							return 3;
						},
						data: d
					},{
						name: '保保网UV',
						type: 'line',
						showAllSymbol: true,
						symbolSize: function (value){
							return 3;
						},
						data: d1
					}
				],
				color : [
					'#dacd21','#fb0303'
				]
			};
			// 使用刚指定的配置项和数据显示图表。
			myChart.setOption(option);
		},
		renderbbappnewEcharts: function(data){
			// 基于准备好的dom，初始化echarts实例
			var myChart = echarts.init(document.getElementById('bbappNew'));
			var d = [],
				d1 = [],
				d2 = [];
			for (var int = 0; int < data.length; int++) {
				var object = data[int],
					date = new Date(object.dataTime);
				d.push([
					[date.getFullYear(), date.getMonth() + 1, date.getDate()].join('-'),
					object.bbappNew
				]);

			}
			var start = new Date();
			start.setTime(start.getTime() - 31*24*60*60*1000);
			var startValue = start.getFullYear()+"-" + (start.getMonth()+1) + "-" + start.getDate();
			var end = new Date();
			end.setTime(end.getTime() - 24*60*60*1000);
			var endValue = end.getFullYear()+"-" + (end.getMonth()+1) + "-" + end.getDate();

			// 指定图表的配置项和数据
			var option = {
				title : {
					text : '保保app新增用户'
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
					filterMode: 'filter',
					startValue : startValue,
					endValue : endValue
				}],
				legend : {
					data : ['保保app新增用户']
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
						name: '保保app新增用户',
						type: 'line',
						showAllSymbol: true,
						symbolSize: function (value){
							return 3;
						},
						data: d
					}
				],
				color : [
					'#dacd21','#fb0303'
				]
			};
			// 使用刚指定的配置项和数据显示图表。
			myChart.setOption(option);
		},
		renderappactiveEcharts: function(data){
			// 基于准备好的dom，初始化echarts实例
			var myChart = echarts.init(document.getElementById('bbappStart'));
			var d = [],
				d1 = [],
				d2 = [];
			for (var int = 0; int < data.length; int++) {
				var object = data[int],
					date = new Date(object.dataTime);
				d.push([
					[date.getFullYear(), date.getMonth() + 1, date.getDate()].join('-'),
					object.bbappStart
				]);

			}
			var start = new Date();
			start.setTime(start.getTime() - 31*24*60*60*1000);
			var startValue = start.getFullYear()+"-" + (start.getMonth()+1) + "-" + start.getDate();
			var end = new Date();
			end.setTime(end.getTime() - 24*60*60*1000);
			var endValue = end.getFullYear()+"-" + (end.getMonth()+1) + "-" + end.getDate();

			// 指定图表的配置项和数据
			var option = {
				title : {
					text : '保保app启动用户'
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
					filterMode: 'filter',
					startValue : startValue,
					endValue : endValue
				}],
				legend : {
					data : ['保保app启动用户']
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
						name: '保保app启动用户',
						type: 'line',
						showAllSymbol: true,
						symbolSize: function (value){
							return 3;
						},
						data: d
					}
				],
				color : [
					'#dacd21','#fb0303'
				]
			};
			// 使用刚指定的配置项和数据显示图表。
			myChart.setOption(option);
		},
		renderhjNewEcharts: function(data){
			// 基于准备好的dom，初始化echarts实例
			var myChart = echarts.init(document.getElementById('hjappNew'));
			var d = [],
				d1 = [],
				d2 = [];
			for (var int = 0; int < data.length; int++) {
				var object = data[int],
					date = new Date(object.dataTime);
				d.push([
					[date.getFullYear(), date.getMonth() + 1, date.getDate()].join('-'),
					object.hjappNew
				]);

			}
			var start = new Date();
			start.setTime(start.getTime() - 31*24*60*60*1000);
			var startValue = start.getFullYear()+"-" + (start.getMonth()+1) + "-" + start.getDate();
			var end = new Date();
			end.setTime(end.getTime() - 24*60*60*1000);
			var endValue = end.getFullYear()+"-" + (end.getMonth()+1) + "-" + end.getDate();

			// 指定图表的配置项和数据
			var option = {
				title : {
					text : '行家app新增用户'
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
					filterMode: 'filter',
					startValue : startValue,
					endValue : endValue
				}],
				legend : {
					data : ['行家app新增用户']
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
						name: '行家app新增用户',
						type: 'line',
						showAllSymbol: true,
						symbolSize: function (value){
							return 3;
						},
						data: d
					}
				],
				color : [
					'#dacd21'
				]
			};
			// 使用刚指定的配置项和数据显示图表。
			myChart.setOption(option);
		},
		renderhjappactiveEcharts: function(data){
			// 基于准备好的dom，初始化echarts实例
			var myChart = echarts.init(document.getElementById('hjappStart'));
			var d = [],
				d1 = [],
				d2 = [];
			for (var int = 0; int < data.length; int++) {
				var object = data[int],
					date = new Date(object.dataTime);
				d.push([
					[date.getFullYear(), date.getMonth() + 1, date.getDate()].join('-'),
					object.hjappStart
				]);

			}
			var start = new Date();
			start.setTime(start.getTime() - 31*24*60*60*1000);
			var startValue = start.getFullYear()+"-" + (start.getMonth()+1) + "-" + start.getDate();
			var end = new Date();
			end.setTime(end.getTime() - 24*60*60*1000);
			var endValue = end.getFullYear()+"-" + (end.getMonth()+1) + "-" + end.getDate();

			// 指定图表的配置项和数据
			var option = {
				title : {
					text : '行家app启动用户'
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
					filterMode: 'filter',
					startValue : startValue,
					endValue : endValue
				}],
				legend : {
					data : ['行家app启动用户']
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
						name: '行家app启动用户',
						type: 'line',
						showAllSymbol: true,
						symbolSize: function (value){
							return 3;
						},
						data: d
					}
				],
				color : [
					'#dacd21','#fb0303'
				]
			};
			// 使用刚指定的配置项和数据显示图表。
			myChart.setOption(option);
		},
		renderbbactiveEcharts: function(data){
			// 基于准备好的dom，初始化echarts实例
			var myChart = echarts.init(document.getElementById('hjw'));
			var d = [],
				d1 = [],
				d2 = [];
			for (var int = 0; int < data.length; int++) {
				var object = data[int],
					date = new Date(object.dataTime);
				d.push([
					[date.getFullYear(), date.getMonth() + 1, date.getDate()].join('-'),
					object.hjwPv
				]);
				d1.push([
					[date.getFullYear(), date.getMonth() + 1, date.getDate()].join('-'),
					object.hjwUv
				]);

			}
			var start = new Date();
			start.setTime(start.getTime() - 31*24*60*60*1000);
			var startValue = start.getFullYear()+"-" + (start.getMonth()+1) + "-" + start.getDate();
			var end = new Date();
			end.setTime(end.getTime() - 24*60*60*1000);
			var endValue = end.getFullYear()+"-" + (end.getMonth()+1) + "-" + end.getDate();

			// 指定图表的配置项和数据
			var option = {
				title : {
					text : '行家网数据'
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
					filterMode: 'filter',
					startValue : startValue,
					endValue : endValue
				}],
				legend : {
					data : ['行家网PV','行家网UV',]
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
						name: '行家网PV',
						type: 'line',
						showAllSymbol: true,
						symbolSize: function (value){
							return 3;
						},
						data: d
					},
					{
						name: '行家网UV',
						type: 'line',
						showAllSymbol: true,
						symbolSize: function (value){
							return 3;
						},
						data: d1
					}
				],
				color : [
					'#dacd21','#fb0303'
				]
			};
			// 使用刚指定的配置项和数据显示图表。
			myChart.setOption(option);
		},
        
        // 启动用户charts
        renderActiveEcharts: function(data){
        	// 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('activeUser'));
            var d = [],
            	d1 = [],
            	d2 = [];
            for (var int = 0; int < data.length; int++) {
            	var object = data[int],
            		date = new Date(object.dataTime);
				d.push([
				     [date.getFullYear(), date.getMonth() + 1, date.getDate()].join('-'),
				     object.activeSumDay
				]);
				d1.push([
					     [date.getFullYear(), date.getMonth() + 1, date.getDate()].join('-'),
					     //10242
					object.newStartTarget
					//12500
					]);
			}
            
        	var start = new Date();
            start.setTime(start.getTime() - 31*24*60*60*1000);
            var startValue = start.getFullYear()+"-" + (start.getMonth()+1) + "-" + start.getDate();
            var end = new Date();
            end.setTime(end.getTime() - 24*60*60*1000);
            var endValue = end.getFullYear()+"-" + (end.getMonth()+1) + "-" + end.getDate();
            
            // 指定图表的配置项和数据
            var option = {
            	    title : {
            	        text : '保险家启动用户'
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
            	    dataZoom: {
            	        show: true,
            	        startValue : startValue,
            	        endValue : endValue
            	    },
            	    legend : {
            	        data : ['启动用户总数','启动用户目标数'/*,'ios启动用户','安卓启动用户'*/]
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
            	            name: '启动用户总数',
            	            type: 'line',
            	            showAllSymbol: true,
            	            symbolSize: function (value){
            	                return 3;
            	            },
            	            data: d
            	        },
            	        {
            	            name: '启动用户目标数',
            	            type: 'line',
            	            showAllSymbol: true,
            	            symbolSize: function (value){
            	                return 3;
            	            },
            	            data: d1
            	        }
            	    ],
            	    color : [
            	          '#dacd21','#fb0303'/*,'#dacd21','#1881de'*/
            	    ]
            	};
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        },
        renderSalesEcharts:function(data){
        	// 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('salesUser'));
            var d = [],
            	d1 = [],
            	d2 = [];
            for (var int = 0; int < data.length; int++) {
            	var object = data[int],
            		date = new Date(object.dataTime);
				d.push([
					[date.getFullYear(), date.getMonth() + 1, date.getDate()].join('-'),
					object.salesTotal
				]);
				d2.push([
					[date.getFullYear(), date.getMonth() + 1, date.getDate()].join('-'),
					object.salesProfits
				]);
				d1.push([
					     [date.getFullYear(), date.getMonth() + 1, date.getDate()].join('-'),
					    // 3000
						object.salesTotalTarget
					]);
			}
            
        	var start = new Date();
            start.setTime(start.getTime() - 31*24*60*60*1000);
            var startValue = start.getFullYear()+"-" + (start.getMonth()+1) + "-" + start.getDate();
            var end = new Date();
            end.setTime(end.getTime() - 24*60*60*1000);
            var endValue = end.getFullYear()+"-" + (end.getMonth()+1) + "-" + end.getDate();
            
            // 指定图表的配置项和数据
            var option = {
            	    title : {
            	        text : '保险家销售额'
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
            	    dataZoom: {
            	        show: true,
            	        startValue : startValue,
            	        endValue : endValue
            	    },
            	    legend : {
            	        data : ['销售额','日均目标额','毛利'/*,'ios启动用户','安卓启动用户'*/]
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
            	            name: '销售额',
            	            type: 'line',
            	            showAllSymbol: true,
            	            symbolSize: function (value){
            	                return 3;
            	            },
            	            data: d
            	        },

            	        {
            	            name: '日均目标额',
            	            type: 'line',
            	            showAllSymbol: true,
            	            symbolSize: function (value){
            	                return 3;
            	            },
            	            data: d1
            	        },{
							name: '毛利',
							type: 'line',
							showAllSymbol: true,
							symbolSize: function (value){
								return 3;
							},
							data: d2
						}
            	    ],
            	    color : [
            	          '#dacd21','#fb0303'
            	    ]
            	};
            myChart.setOption(option);
        }
    };
    return reportList;
});