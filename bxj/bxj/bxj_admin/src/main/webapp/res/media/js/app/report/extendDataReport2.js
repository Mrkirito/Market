define(
		[ 'jquery', 'app/common/util', 'app/common/advanceListTool',
				'app/common/dateTool', 'app/common/messageTool',
				'app/common/chartsTool', 'app/common/exportTool',
				'app/common/winTool' ],
		function($, util, listTool, dateTool, messageTool, echarts, exportTool,winTool) {
			var reportList = {
				list : function() {
					var listJson = [{	name : "分类",
										width : 150,
										fieldName : "categroyName"
									},
									{	name : "细分",
										width : 120,
										fieldName : "categroyDetail"
									},
									/*{	name : "日均",
										width : 80,
										fieldName : "perDayNum"
									},
									{ 	name : "月总",
										width : 80,
										fieldName : "perMonthNum"
									},*/
									{	name : "负责人",
										width : 80,
										fieldName : "learder"
									},
									{name : "1",width : 50,fieldName : "column1"},
									{name : "2",width : 50,fieldName : "column2"},
									{name : "3",width : 50,fieldName : "column3"},
									{name : "4",width : 50,fieldName : "column4"},
									{name : "5",width : 50,fieldName : "column5"},
									{name : "6",width : 50,fieldName : "column6"},
									{name : "7",width : 50,fieldName : "column7"},
									{name : "8",width : 50,fieldName : "column8"},
									{name : "9",width : 50,fieldName : "column9"},
									{name : "10",width : 50,fieldName : "column10"},
									{name : "11",width : 50,fieldName : "column11"},
									{name : "12",width : 50,fieldName : "column12"},
									{name : "13",width : 50,fieldName : "column13"},
									{name : "14",width : 50,fieldName : "column14"},
									{name : "15",width : 50,fieldName : "column15"},
									{name : "16",width : 50,fieldName : "column16"},
									{name : "17",width : 50,fieldName : "column17"},
									{name : "18",width : 50,fieldName : "column18"},
									{name : "19",width : 50,fieldName : "column19"},
									{name : "20",width : 50,fieldName : "column20"},
									{name : "21",width : 50,fieldName : "column21"},
									{name : "22",width : 50,fieldName : "column22"},
									{name : "23",width : 50,fieldName : "column23"},
									{name : "24",width : 50,fieldName : "column24"},
									{name : "25",width : 50,fieldName : "column25"},
									{name : "26",width : 50,fieldName : "column26"},
									{name : "27",width : 50,fieldName : "column27"},
									{name : "28",width : 50,fieldName : "column28"},
									{name : "29",width : 50,fieldName : "column29"},
									{name : "30",width : 50,fieldName : "column30"},
									{name : "31",width : 50,fieldName : "column31"}
									];
					var thiz = this, list = listTool.create({
						title : "推广运营数据统计列表",
						selector : ".data-list",
						remote : true,
						url : serverHost+ "/extend/queryExtendDataReportList2.json",
						width : 600,
						height : 900,
						columns : listJson,
						showPagebar:false,
						showCheckbox:false,
						paramFn : function() {
							return thiz.getParams(); 
						},

						initSearch : function(query) {
						},
						tbars : [],
						classEvents : []
					});
					this.list = list;
					dateTool.renderYYYYMM2(".reportTime");
					$(".search-action").on("click", function() {
						if($(".reportTime").val()){
							thiz.list.reloadPageData(1);
//							thiz.list.loadPageData(1);							
//							reportList.dataCharts();
						}else{
							messageTool.error("请选择统计时间");
						}
					});
					reportList.dataCharts();
				},
				getParams : function() {
					return {
						date : $(".reportTime").val()
					};
				},
				 dataCharts: function(){
		            	var thiz = this;
		        		util.ajax({
		                    url: serverHost + "/extend/queryEchartsData.json",
		                    params: {
		                    	date: $('.reportTime').val()
		                    },
		                    success: function (resp) {
		                        if(resp.success) {
		                        	var data = resp.model;
		                        	thiz.renderDataCharts(data);
		                        	thiz.renderDataCharts2(data);
		                        }
		                    }
		                });
		            },
		            renderDataCharts:function(data){
		                var myChart = echarts.init(document.getElementById('newUser'));
		                var d = [],d1 = [],d7=[],d2 = [],d8=[];
		                var startValue,endValue;
		                for (var int = 0; int < data.length; int++) {
		                	var object = data[int],date = new Date(object.dataTime);
		                	var dateStr=[date.getFullYear(), date.getMonth() + 1, date.getDate()].join('-');
		    				d.push([
		    				     dateStr,object.channelNum
		    				]);    				
		    				d1.push([
		    				     dateStr,object.everyNum
		    				]);
		    				d7.push([
		        				     dateStr,object.lineNum
		        				]);
		    				d2.push([
		    				     dateStr,object.activityNum
		    				]);
		    				d8.push([
			    				     dateStr,object.totalNum
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
		                	        text : 'APP运营数据总计'
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
		                	        data : ['日常','合计']
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
		                	            name: '日常',
		                	            type: 'line',
		                	            showAllSymbol: true,
		                	            symbolSize: function (value){
		                	                return 3;
		                	            },
		                	            data: d1
		                	        },
		                	        {
		                	            name: '合计',
		                	            type: 'line',
		                	            showAllSymbol: true,
		                	            symbolSize: function (value){
		                	                return 3;
		                	            },
		                	            data: d8
		                	        }
		                	    ],
		                	    color : [ '#fb0303','#dacd21']
		                	};
		                myChart.setOption(option);
		            },
		            renderDataCharts2:function(data){
		                var myChart = echarts.init(document.getElementById('newUser2'));
		                var d = [],d1 = [],d7=[],d2 = [],d8=[];
		                var startValue,endValue;
		                for (var int = 0; int < data.length; int++) {
		                	var object = data[int],date = new Date(object.dataTime);
		                	var dateStr=[date.getFullYear(), date.getMonth() + 1, date.getDate()].join('-');
		    				d.push([
		    				     dateStr,object.channelNum
		    				]);    				
		    				d1.push([
		    				     dateStr,object.everyNum
		    				]);
		    				d7.push([
		        				     dateStr,object.lineNum
		        				]);
		    				d2.push([
		    				     dateStr,object.activityNum
		    				]);
		    				d8.push([
			    				     dateStr,object.totalNum
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
		                	        text : 'APP推广运营数据明细'
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
		                	        data : ['渠道及优化','线下','活动']
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
		                	                return 3;
		                	            },
		                	            data: d
		                	        },
		                	        {
		                	            name: '线下',
		                	            type: 'line',
		                	            showAllSymbol: true,
		                	            symbolSize: function (value){
		                	                return 3;
		                	            },
		                	            data: d7
		                	        },
		                	        {
		                	            name: '活动',
		                	            type: 'line',
		                	            showAllSymbol: true,
		                	            symbolSize: function (value){
		                	                return 3;
		                	            },
		                	            data: d2
		                	        }
		                	    ],
		                	    color : [ '#fb0303','#dacd21','#5fab33']
		                	};
		                myChart.setOption(option);
		            }
			};
			return reportList;
		});