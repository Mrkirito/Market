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
                    name: "渠道",
                    width: 150,
                    fieldName: "productName",
                    renderer: function (row, col, data, value) {
                        return value;
                    }
                },
                    {
                        name: "负责人",
                        width: 150,
                        fieldName: "worker"
                    },
                    {
                        name: "数据类型",
                        width: 150,
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
                    title: "保保微信端数据统计列表",
                    selector: ".data-list",
                    remote: true,
                    url: serverHost + "/bbreport/queryWchartDataReportList.json",
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
                    title: "微信数据",
                    height: 600,
                    width: 700,
                    showCancel: true,
                    okName: "确定",
                    cancelName: "关闭",
                    type: "selector",
                    selector: ".data_info",
                    okFn: function (win) {
                        var dataDate = win.find(".dataDate").val();
                        if (isNull(dataDate)) {
                        	messageTool.error("数据日期不能为空");
                        	return false;
                        }
                        var b=false;
                        win.find('.is_number').each(function(i,o){
                        	o=$(o);
                        	var val=o.val();
                        	if(val&&!isNaN(val)){
                            	b=true;
                        	}else{
                        		b=false;
                        		messageTool.error(o.prop('placeholder')+"（数字）");
                        		return false;
                        	}
                        });       
                        if(b){                        	
                        	var para=win.find('#form').serialize();
                        	util.request({
                        		confirm: true,
                        		msg: "确定新增推广运营数据吗?",
                        		url: serverHost + "/bbreport/addWchartData.json",
                        		params: para,
                        		success: function (resp) {
                        			if (resp.success) {
                        				messageTool.success("新增成功");
                        				win.close();
                        				thiz.list.reloadPageData();
                        				reportList.dataCharts();					
                        			} else {
                        				messageTool.error(resp.msg);
                        			}
                        		}
                        	});
                        }
                    }
                });
                //dateTool.renderDate(".dataDate");
                $('.dataDate').datepicker({
					format: 'yyyy-mm-dd',
					language: "zh-CN",
					onSelect: displayInfo,
					autoclose: true,
					clearBtn:true,
					todayBtn: false
                }).on('changeDate',displayInfo);

                function displayInfo(ev){
					var year = ev.date.getFullYear();
					var month = ev.date.getMonth()+1;
					var day = ev.date.getDate();
					if(month<10) month ='0'+month;
					if(day<10) day ='0'+day;

					util.request({
						url: serverHost + "/bbreport/ajaxDisplayInfo.json",
						params: {
							date: year+""+month+""+day
						},
						success: function (resp) {
							if(resp.msg !='nodata') {
								var obj = resp.model;
								win.find("input[name='data1']").val(obj.data1);
								win.find("input[name='data2']").val(obj.data2);
								win.find("input[name='data3']").val(obj.data3);
								win.find("input[name='data4']").val(obj.data4);
								win.find("input[name='data5']").val(obj.data5);
								win.find("input[name='data6']").val(obj.data6);
								win.find("input[name='data7']").val(obj.data7);
								win.find("input[name='data8']").val(obj.data8);
								win.find("input[name='data9']").val(obj.data9);
								win.find("input[name='data10']").val(obj.data10);
								win.find("input[name='data11']").val(obj.data11);
								win.find("input[name='id']").val(obj.id);
							}else{
								win.find("input[name='id']").val('');
								win.find("input[name='data1']").val('');
								win.find("input[name='data2']").val('');
								win.find("input[name='data3']").val('');
								win.find("input[name='data4']").val('');
								win.find("input[name='data5']").val('');
								win.find("input[name='data6']").val('');
								win.find("input[name='data7']").val('237');
								win.find("input[name='data8']").val('');
								win.find("input[name='data9']").val('');
								win.find("input[name='data10']").val('');
								win.find("input[name='data11']").val('');
							}
						}
					});
				}
			},
            /**报表*/
            dataCharts: function(){
            	var thiz = this;
        		util.ajax({
                    url: serverHost + "/bbreport/queryWchartMarkDataCharts.json",
                    params: {
                    	date: $('.reportTime').val()
                    },
                    success: function (resp) {
                        if(resp.success) {
                        	var data = resp.model;
                        	thiz.renderDataCharts(data);
                        	thiz.renderDataCharts2(data);
                        	thiz.renderDataCharts3(data);
                        }
                    }
                });
            },
            renderDataCharts:function(data){
                var myChart = echarts.init(document.getElementById('newUser'));
                var d = [],d1=[];
                var startValue,endValue;
                for (var int = 0; int < data.length; int++) {
                	var object = data[int],date = new Date(object.dataDate);
                	var dateStr=[date.getFullYear(), date.getMonth() + 1, date.getDate()].join('-');
    				d.push([
    				     dateStr,object.data2
    				]);    				
    				d1.push([
    				     dateStr,object.data3
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
                	        text : '保保微博号'
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
                	        data : ['增加数','净增数']
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
                	            name: '增加数',
                	            type: 'line',
                	            showAllSymbol: true,
                	            symbolSize: function (value){
                	                return 3;
                	            },
                	            data: d
                	        },
                	        {
                	            name: '净增数',
                	            type: 'line',
                	            showAllSymbol: true,
                	            symbolSize: function (value){
                	                return 3;
                	            },
                	            data: d1
                	        }
                	    ],
                	    color : [ '#fb0303','#dacd21']
                	};
                myChart.setOption(option);
            },
            renderDataCharts2:function(data){
                var myChart = echarts.init(document.getElementById('newUser2'));
                var d1 = [],d2=[];
                var startValue,endValue;
                for (var int = 0; int < data.length; int++) {
                	var object = data[int],date = new Date(object.dataDate);
                	var dateStr=[date.getFullYear(), date.getMonth() + 1, date.getDate()].join('-');
    				d1.push([
    				     dateStr,object.data5
    				]);
    				d2.push([
    				     dateStr,object.data6
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
                	        text : '行家微博号'
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
                	        data : ['增加数','净增数']
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
                	            name: '增加数',
                	            type: 'line',
                	            showAllSymbol: true,
                	            symbolSize: function (value){
                	                return 3;
                	            },
                	            data: d1
                	        },
                	        {
                	            name: '净增数',
                	            type: 'line',
                	            showAllSymbol: true,
                	            symbolSize: function (value){
                	                return 3;
                	            },
                	            data: d2
                	        }
                	    ],
                	    color : [ '#fb0303','#dacd21']
                	};
                myChart.setOption(option);
            },
            renderDataCharts3:function(data){
                var myChart = echarts.init(document.getElementById('newUser3'));
                var d7 = [],d8=[];
                var startValue,endValue;
                for (var int = 0; int < data.length; int++) {
                	var object = data[int],date = new Date(object.dataDate);
                	var dateStr=[date.getFullYear(), date.getMonth() + 1, date.getDate()].join('-');
    				d7.push([
        				     dateStr,object.data10
        				]);
    				d8.push([
        				     dateStr,object.data11
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
                	        text : '行家微信群'
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
                	        data : ['增加数','净增数']
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
                	            name: '增加数',
                	            type: 'line',
                	            showAllSymbol: true,
                	            symbolSize: function (value){
                	                return 3;
                	            },
                	            data: d7
                	        },
							{
                	            name: '净增数',
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