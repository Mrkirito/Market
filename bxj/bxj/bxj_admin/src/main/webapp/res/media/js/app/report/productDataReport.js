define(
    ['jquery', 'app/common/util', 'app/common/advanceListTool',
        'app/common/dateTool', 'app/common/messageTool',
        'app/common/chartsTool', 'app/common/exportTool',
        'app/common/winTool'],
    function ($, util, listTool, dateTool, messageTool, echarts, exportTool, winTool) {
        var reportList = {
            list: function () {
            	var thiz = this,
               listJson = [{
                    name: "产品",
                    width: 150,
                    fieldName: "productName",
                    renderer: function (row, col, data, value) {
                        return value;
                    }
                },
                    {
                        name: "工作",
                        width: 180,
                        fieldName: "workDetail",
                        renderer: function (row, col, data, value) {

                            return value;
                        }
                    },
                    {
                        name: "负责人",
                        width: 80,
                        fieldName: "worker"
                    },
                    {
                        name: "数据类型",
                        width: 120,
                        fieldName: "dataInfo"
                    },
                    {name: "1", width: 50, fieldName: "column1"},
                    {name: "2", width: 50, fieldName: "column2"},
                    {name: "3", width: 50, fieldName: "column3"},
                    {name: "4", width: 50, fieldName: "column4"},
                    {name: "5", width: 50, fieldName: "column5"},
                    {name: "6", width: 50, fieldName: "column6"},
                    {name: "7", width: 50, fieldName: "column7"},
                    {name: "8", width: 50, fieldName: "column8"},
                    {name: "9", width: 50, fieldName: "column9"},
                    {name: "10", width: 50, fieldName: "column10"},
                    {name: "11", width: 50, fieldName: "column11"},
                    {name: "12", width: 50, fieldName: "column12"},
                    {name: "13", width: 50, fieldName: "column13"},
                    {name: "14", width: 50, fieldName: "column14"},
                    {name: "15", width: 50, fieldName: "column15"},
                    {name: "16", width: 50, fieldName: "column16"},
                    {name: "17", width: 50, fieldName: "column17"},
                    {name: "18", width: 50, fieldName: "column18"},
                    {name: "19", width: 50, fieldName: "column19"},
                    {name: "20", width: 50, fieldName: "column20"},
                    {name: "21", width: 50, fieldName: "column21"},
                    {name: "22", width: 50, fieldName: "column22"},
                    {name: "23", width: 50, fieldName: "column23"},
                    {name: "24", width: 50, fieldName: "column24"},
                    {name: "25", width: 50, fieldName: "column25"},
                    {name: "26", width: 50, fieldName: "column26"},
                    {name: "27", width: 50, fieldName: "column27"},
                    {name: "28", width: 50, fieldName: "column28"},
                    {name: "29", width: 50, fieldName: "column29"},
                    {name: "30", width: 50, fieldName: "column30"},
                    {name: "31", width: 50, fieldName: "column31"}
                ];
                var thiz = this, list = listTool.create({
                    title: "产品每日考核统计列表",
                    selector: ".data-list",
                    remote: true,
						url: serverHost + "/report/queryProductDataReportList.json",
                    width: 600,
                    height: 900,
                    columns: listJson,
                    idName: "productTable",
                    showPagebar: false,
                    showCheckbox: false,
                    paramFn: function () {
                        return thiz.getParams();
                    },

                    initSearch: function (query) {
                    },
                    tbars: [{
                        icon: 'fa fa-plus',
                        name: "新增",
                        // rightCode: "add",
                        handler: function (idx, data) {
                            thiz.add();
                        }
                    }],
                    classEvents: []
                });
                this.list = list;
                dateTool.renderYYYYMM2(".reportTime");
                $(".search-action").on("click", function () {
                    if ($(".reportTime").val()) {
                        thiz.list.reloadPageData();
//                        reportList.dataCharts();						
                    } else {
                        messageTool.error("请选择统计时间");
                    }
                });
                reportList.dataCharts();
            },
            getParams: function () {
                return {
                    date: $(".reportTime").val()
                };
            },
            add: function (data) {
                var thiz = this;
                var win = winTool.create({
                    title: "产品每日考核",
                    height: 600,
                    width: 800,
                    showCancel: true,
                    okName: "确定",
                    cancelName: "关闭",
                    type: "selector",
                    selector: ".data_info",
                    okFn: function (win) {
                        var dataDate = win.find(".dataDate").val();
                        var planPv = win.find(".planPv").val();
                        var planUv = win.find(".planUv").val();
                        var planMakePopulation = win.find(".planMakePopulation").val();
                        var planMakeCount = win.find(".planMakeCount").val();
                        var studyPv = win.find(".studyPv").val();
                        var studyUv = win.find(".studyUv").val();
                        var studyNewUv = win.find(".studyNewUv").val();
                        var friendPv = win.find(".friendPv").val();
                        var friendUv = win.find(".friendUv").val();
                        var friendShareCount = win.find(".friendShareCount").val();
                        if (isNull(dataDate)) {
                            messageTool.error("数据日期不能为空");
                            return;
                        }
                        util.request({
                            confirm: true,
                            msg: "确定新增产品每日考核数据吗?",
                            url: serverHost + "/report/addProductData.json",
                            params: {
                                dataDate: dataDate,
                                planPv: planPv,
                                planUv: planUv,
                                planMakePopulation: planMakePopulation,
                                planMakeCount: planMakeCount,
                                studyPv: studyPv,
                                studyUv: studyUv,
                                studyNewPv: studyNewUv,
                                friendPv: friendPv,
                                friendUv: friendUv,
                                friendShareCount: friendShareCount
                            },
                            success: function (resp) {
                                if (resp.success) {
                                    messageTool.success("新增成功");
                                    win.close();
                                    thiz.list.reloadPageData();
                                } else {
                                    messageTool.error(resp.msg);
                                }
                            }
                        });
                    }
                });
                dateTool.renderDate(".dataDate");
            },        
            /**报表*/
            dataCharts: function(){
            	var thiz = this;
        		util.ajax({
                    url: serverHost + "/report/queryMarkDataCharts.json",
                    params: {
                    	date: $('.reportTime').val()
                    },
                    success: function (resp) {
                        if(resp.success) {
                        	var data = resp.model;
                        	thiz.renderDataCharts1(data);
                        	thiz.renderDataCharts2(data);
                        	thiz.renderDataCharts3(data);
                        }
                    }
                });
				//新人通，开门红数据
				util.ajax({
					url: serverHost + "/tjgl/getAllBxjAppXrtDatas.json",
					success: function (resp) {
						if (resp.success) {
							var data = resp.model;
							thiz.renderDataCharts(data);
							thiz.testRenderDataCharts(data);
							thiz.clearanceRenderDataCharts(data);
						}
					}
				});
				util.ajax({
					url: serverHost + "/tjgl/getAllBxjAppKmhDatas.json",
					success: function (resp) {
						if (resp.success) {
							var data = resp.model;
							thiz.shareRenderDataCharts(data);
						}
					}
				});
            },
			shareRenderDataCharts: function (data) {
				var myChart = echarts.init(document.getElementById('share'));
				var d = [], d1 = [], d2 = [], d3 = [];
				var startValue, endValue;
				for (var int = 0; int < data.length; int++) {
					var object = data[int], date = new Date(object.dataTime);
					var dateStr = [date.getFullYear(), date.getMonth() + 1, date.getDate()].join('-');
					d.push([
						dateStr, object.browseCount
					]);
					d1.push([
						dateStr, object.browseCountUv
					]);
					d2.push([
						dateStr, object.shareCount
					]);
					d3.push([
						dateStr, object.shareCountUv
					]);

					if (int == 0) {
						startValue = dateStr;
					} else if (int == data.length - 1) {
						endValue = dateStr;
					}
				}
				var start = new Date();
				start.setTime(start.getTime() - 30 * 24 * 60 * 60 * 1000);
				var startValue = start.getFullYear() + "-" + (start.getMonth() + 1) + "-" + start.getDate();
				var end = new Date();
				end.setTime(end.getTime() - 24 * 60 * 60 * 1000);
				var endValue = end.getFullYear() + "-" + (end.getMonth() + 1) + "-" + end.getDate();
				var option = {
					title: {
						text: '开门红'
					},
					tooltip: {
						trigger: 'axis'
					},
					toolbox: {
						show: true,
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
						startValue: startValue,
						endValue: endValue
					}],
					legend: {
						data: ['浏览PV', '浏览UV','分享PV', '分享UV']
					},
					xAxis: {
						type: 'time',
						splitLine: {
							show: false
						}
					},
					yAxis: {
						type: 'value'
					},

					series: [
						{
							name: '浏览PV',
							type: 'line',
							showAllSymbol: true,
							symbolSize: function (value) {
								return 2;
							},
							data: d
						},
						{
							name: '浏览UV',
							type: 'line',
							showAllSymbol: true,
							symbolSize: function (value) {
								return 2;
							},
							data: d1
						},
						{
							name: '分享PV',
							type: 'line',
							showAllSymbol: true,
							symbolSize: function (value) {
								return 2;
							},
							data: d2
						},
						{
							name: '分享UV',
							type: 'line',
							showAllSymbol: true,
							symbolSize: function (value) {
								return 2;
							},
							data: d3
						}
					],
					color: ['#CD3333', '#EE6363', '#3A5FCD', '#1E90FF',]
				};
				myChart.setOption(option);
			},
			/**报表*/
		
			renderDataCharts: function (data) {
				var myChart = echarts.init(document.getElementById('xrt'));
				var d = [], d1 = [], d2 = [];
				var startValue, endValue;
				for (var int = 0; int < data.length; int++) {
					var object = data[int], date = new Date(object.dataTime);
					var dateStr = [date.getFullYear(), date.getMonth() + 1, date.getDate()].join('-');
					d.push([
						dateStr, object.studyCount
					]);
					d1.push([
						dateStr, object.studyCountUv
					]);

					if (int == 0) {
						startValue = dateStr;
					} else if (int == data.length - 1) {
						endValue = dateStr;
					}
				}
				var start = new Date();
				start.setTime(start.getTime() - 30 * 24 * 60 * 60 * 1000);
				var startValue = start.getFullYear() + "-" + (start.getMonth() + 1) + "-" + start.getDate();
				var end = new Date();
				end.setTime(end.getTime() - 24 * 60 * 60 * 1000);
				var endValue = end.getFullYear() + "-" + (end.getMonth() + 1) + "-" + end.getDate();
				var option = {
					title: {
						text: '新人通(学习数据)'
					},
					tooltip: {
						trigger: 'axis'
					},
					toolbox: {
						show: true,
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
						startValue: startValue,
						endValue: endValue
					}],
					legend: {
						data: ['学习数据PV', '学习数据UV']
					},
					xAxis: {
						type: 'time',
						splitLine: {
							show: false
						}
					},
					yAxis: {
						type: 'value'
					},

					series: [
						{
							name: '学习数据PV',
							type: 'line',
							showAllSymbol: true,
							symbolSize: function (value) {
								return 3;
							},
							data: d
						},
						{
							name: '学习数据UV',
							type: 'line',
							showAllSymbol: true,
							symbolSize: function (value) {
								return 3;
							},
							data: d1
						}
					],
					color: ['#fb0303', '#dacd21']
				};
				myChart.setOption(option);
			},


			testRenderDataCharts: function (data) {
				var myChart = echarts.init(document.getElementById('test'));
				var d = [], d1 = [], d2 = [];
				var startValue, endValue;
				for (var int = 0; int < data.length; int++) {
					var object = data[int], date = new Date(object.dataTime);
					var dateStr = [date.getFullYear(), date.getMonth() + 1, date.getDate()].join('-');
					d.push([
						dateStr, object.testCount
					]);
					d1.push([
						dateStr, object.testCountUv
					]);

					if (int == 0) {
						startValue = dateStr;
					} else if (int == data.length - 1) {
						endValue = dateStr;
					}
				}
				var start = new Date();
				start.setTime(start.getTime() - 30 * 24 * 60 * 60 * 1000);
				var startValue = start.getFullYear() + "-" + (start.getMonth() + 1) + "-" + start.getDate();
				var end = new Date();
				end.setTime(end.getTime() - 24 * 60 * 60 * 1000);
				var endValue = end.getFullYear() + "-" + (end.getMonth() + 1) + "-" + end.getDate();
				var option = {
					title: {
						text: '新人通(测试数据)'
					},
					tooltip: {
						trigger: 'axis'
					},
					toolbox: {
						show: true,
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
						startValue: startValue,
						endValue: endValue
					}],
					legend: {
						data: ['测试数据PV', '测试数据UV']
					},
					xAxis: {
						type: 'time',
						splitLine: {
							show: false
						}
					},
					yAxis: {
						type: 'value'
					},

					series: [
						{
							name: '测试数据PV',
							type: 'line',
							showAllSymbol: true,
							symbolSize: function (value) {
								return 3;
							},
							data: d
						},
						{
							name: '测试数据UV',
							type: 'line',
							showAllSymbol: true,
							symbolSize: function (value) {
								return 3;
							},
							data: d1
						}
					],
					color: ['#fb0303', '#dacd21']
				};
				myChart.setOption(option);
			},
			clearanceRenderDataCharts: function (data) {
				var myChart = echarts.init(document.getElementById('clearance'));
				var d = [], d1 = [], d2 = [];
				var startValue, endValue;
				for (var int = 0; int < data.length; int++) {
					var object = data[int], date = new Date(object.dataTime);
					var dateStr = [date.getFullYear(), date.getMonth() + 1, date.getDate()].join('-');
					d.push([
						dateStr, object.clearanceCount
					]);
					d1.push([
						dateStr, object.clearanceCountUv
					]);

					if (int == 0) {
						startValue = dateStr;
					} else if (int == data.length - 1) {
						endValue = dateStr;
					}
				}
				var start = new Date();
				start.setTime(start.getTime() - 30 * 24 * 60 * 60 * 1000);
				var startValue = start.getFullYear() + "-" + (start.getMonth() + 1) + "-" + start.getDate();
				var end = new Date();
				end.setTime(end.getTime() - 24 * 60 * 60 * 1000);
				var endValue = end.getFullYear() + "-" + (end.getMonth() + 1) + "-" + end.getDate();
				var option = {
					title: {
						text: '新人通(通关数据)'
					},
					tooltip: {
						trigger: 'axis'
					},
					toolbox: {
						show: true,
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
						startValue: startValue,
						endValue: endValue
					}],
					legend: {
						data: ['通关数据PV', '通关数据UV']
					},
					xAxis: {
						type: 'time',
						splitLine: {
							show: false
						}
					},
					yAxis: {
						type: 'value'
					},

					series: [
						{
							name: '通关数据PV',
							type: 'line',
							showAllSymbol: true,
							symbolSize: function (value) {
								return 3;
							},
							data: d
						},
						{
							name: '通关数据UV',
							type: 'line',
							showAllSymbol: true,
							symbolSize: function (value) {
								return 3;
							},
							data: d1
						}
					],
					color: ['#fb0303', '#dacd21']
				};
				myChart.setOption(option);
			},

			renderDataCharts1:function(data){
                var myChart = echarts.init(document.getElementById('newUser1'));
                var d = [],d1 = [],d2 = [],d3=[];
                var startValue,endValue;
                for (var int = 0; int < data.length; int++) {
                	var object = data[int],date = new Date(object.dataDate);
                	var dateStr=[date.getFullYear(), date.getMonth() + 1, date.getDate()].join('-');
    				d.push([
    				     dateStr,object.planPv
    				]);    				
    				d1.push([
    				     dateStr,object.planUv
    				]);
    				d2.push([
    				     dateStr,object.planMakePopulation
    				]);
    				d3.push([
        				     dateStr,object.planMakeCount
        			]);
    				if(int==0){
    					startValue=dateStr;
    				}else if(int==data.length-1){
    					endValue=dateStr;
    				}
    			}
                var start = new Date();
	            start.setTime(start.getTime() - 30*24*60*60*1000);
	            var startValue = start.getFullYear()+"-" + (start.getMonth()+1) + "-" + start.getDate();
	            var end = new Date();
	            end.setTime(end.getTime() - 24*60*60*1000);
	            var endValue = end.getFullYear()+"-" + (end.getMonth()+1) + "-" + end.getDate();
                var option = {
                	    title : {
                	        text : '计划书'
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
                	        data : ['PV','UV','制作人数','制作总数']
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
                	            name: 'PV',
                	            type: 'line',
                	            showAllSymbol: true,
                	            symbolSize: function (value){
                	                return 3;
                	            },
                	            data: d
                	        },
                	        {
                	            name: 'UV',
                	            type: 'line',
                	            showAllSymbol: true,
                	            symbolSize: function (value){
                	                return 3;
                	            },
                	            data: d1
                	        },
                	        {
                	            name: '制作人数',
                	            type: 'line',
                	            showAllSymbol: true,
                	            symbolSize: function (value){
                	                return 3;
                	            },
                	            data: d2
                	        },
                	        {
                	            name: '制作总数',
                	            type: 'line',
                	            showAllSymbol: true,
                	            symbolSize: function (value){
                	                return 3;
                	            },
                	            data: d3
                	        }
                	    ],
                	    color : [ '#fb0303','#dacd21','#5fab33','#1881de']
                	};
                myChart.setOption(option);
            },
            renderDataCharts2:function(data){
                var myChart = echarts.init(document.getElementById('newUser2'));
                var d = [],d1 = [],d2 = [];
                var startValue,endValue;
                for (var int = 0; int < data.length; int++) {
                	var object = data[int],date = new Date(object.dataDate);
                	var dateStr=[date.getFullYear(), date.getMonth() + 1, date.getDate()].join('-');
    				d.push([
    				     dateStr,object.studyNewPv
    				]);    				
    				d1.push([
    				     dateStr,object.studyPv
    				]);
    				d2.push([
    				     dateStr,object.studyUv
    				]);
    				if(int==0){
    					startValue=dateStr;
    				}else if(int==data.length-1){
    					endValue=dateStr;
    				}
    			}
                var start = new Date();
	            start.setTime(start.getTime() - 30*24*60*60*1000);
	            var startValue = start.getFullYear()+"-" + (start.getMonth()+1) + "-" + start.getDate();
	            var end = new Date();
	            end.setTime(end.getTime() - 24*60*60*1000);
	            var endValue = end.getFullYear()+"-" + (end.getMonth()+1) + "-" + end.getDate();
                var option = {
                	    title : {
                	        text : '学习'
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
                	        data : ['新内容PV','PV','UV']
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
                	            name: '新内容PV',
                	            type: 'line',
                	            showAllSymbol: true,
                	            symbolSize: function (value){
                	                return 3;
                	            },
                	            data: d
                	        },
                	        {
                	            name: 'PV',
                	            type: 'line',
                	            showAllSymbol: true,
                	            symbolSize: function (value){
                	                return 3;
                	            },
                	            data: d1
                	        },
                	        {
                	            name: 'UV',
                	            type: 'line',
                	            showAllSymbol: true,
                	            symbolSize: function (value){
                	                return 3;
                	            },
                	            data: d2
                	        }
                	    ],
                	    color : [ '#fb0303','#dacd21','#1881de']
                	};
                myChart.setOption(option);
            },
            renderDataCharts3:function(data){
                var myChart = echarts.init(document.getElementById('newUser3'));
                var d = [],d1 = [],d2 = [];
                var startValue,endValue;
                for (var int = 0; int < data.length; int++) {
                	var object = data[int],date = new Date(object.dataDate);
                	var dateStr=[date.getFullYear(), date.getMonth() + 1, date.getDate()].join('-');
    				d.push([
    				     dateStr,object.friendPv
    				]);    				
    				d1.push([
    				     dateStr,object.friendUv
    				]);
    				d2.push([
    				     dateStr,object.friendShareCount
    				]);
    				if(int==0){
    					startValue=dateStr;
    				}else if(int==data.length-1){
    					endValue=dateStr;
    				}
    			}
                var start = new Date();
	            start.setTime(start.getTime() - 30*24*60*60*1000);
	            var startValue = start.getFullYear()+"-" + (start.getMonth()+1) + "-" + start.getDate();
	            var end = new Date();
	            end.setTime(end.getTime() - 24*60*60*1000);
	            var endValue = end.getFullYear()+"-" + (end.getMonth()+1) + "-" + end.getDate();
	            
	                       
	            
                var option = {
                	    title : {
                	        text : '朋友圈助手'
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
                	        data : ['PV','UV','UV分享数']
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
                	            name: 'PV',
                	            type: 'line',
                	            showAllSymbol: true,
                	            symbolSize: function (value){
                	                return 3;
                	            },
                	            data: d
                	        },
                	        {
                	            name: 'UV',
                	            type: 'line',
                	            showAllSymbol: true,
                	            symbolSize: function (value){
                	                return 3;
                	            },
                	            data: d1
                	        },
                	        {
                	            name: 'UV分享数',
                	            type: 'line',
                	            showAllSymbol: true,
                	            symbolSize: function (value){
                	                return 3;
                	            },
                	            data: d2
                	        }
                	    ],
                	    color : [ '#fb0303','#dacd21','#1881de']
                	};
                myChart.setOption(option);
            }
        };
        return reportList;
    });

function isNull(obj) {
    if (obj == null || obj == "" || obj == undefined) {
        return true;
    }
    return false;
}