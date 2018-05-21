/**
 * Created by 蔡杲沛(Bright) on 2016/11/9.
 * 统计管理-开门红
 */
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
                        name: "序号",
                        width: 150,
                        renderer: function (row, col, data, value) {
                            return row + 1;
                        }
                    },
                        {
                            name: "日期",
                            width: 150,
                            fieldName: "dataTime",
                            renderer: function (row, col, data, value) {
                                return util.dateFormat2(value, 'YYYY-MM-DD');
                            }
                        },
                        {
                            name: "保险家",
                            width: 150,
                            fieldName: "bxjCount"
                        },
                        {
                            name: "保险师",
                            width: 150,
                            fieldName: "bxsCount"
                        },
                        {
                            name: "保险助手",
                            width: 150,
                            fieldName: "bxzsCount"
                        },
                        {
                            name: "保险人",
                            width: 150,
                            fieldName: "bxrCount"
                        }, {
                            name: "随身保典",
                            width: 150,
                            fieldName: "ssbdCount"
                        },
                        {
                            name: "聚米",
                            width: 150,
                            fieldName: "jmCount"
                        },
                        {
                            name: "纪家保险",
                            width: 150,
                            fieldName: "jjbxCount"
                        },
                        {
                            name: "保险大咖",
                            width: 150,
                            fieldName: "bxdkCount"
                        },
                        {
                            name: "i云保",
                            width: 150,
                            fieldName: "iybCount"
                        },
                        {
                            name: "保险问问",
                            width: 150,
                            fieldName: "bxwwCount"
                        }
                    ];
                var thiz = this, list = listTool.create({
                    title: "竞品APP数据统计列表",
                    selector: ".data-list",
                    remote: true,
                    url: serverHost + "/cps/queryCpsStatisticsDataList.json",
                    width: 600,
                    height: 900,
                    columns: listJson,
                    orderBy: "data_Time desc",
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
                //时间渲染
                dateTool.renderDateRange(".reportTime", {
                    applyClick : function(startDate, endDate) {
                        $(".startTime").val(startDate);
                        $(".endTime").val(endDate);
                    }
                });
                $(".search-data").on("click", function () {
                    //if ($(".reportTime").val()) {
                    thiz.list.reloadPageData(1);
                    //} else {
                    //    messageTool.error("请选择统计时间");
                    //}
                });

                reportList.dataCharts();
            },
            getParams: function () {
                return {
                    startTime: $(".startTime").val(),
                    endTime: $(".endTime").val()
                };
            },
            add: function (data) {
                var thiz = this;
                var win = winTool.create({
                    title: "竞品app数据",
                    height: 600,
                    width: 700,
                    showCancel: true,
                    okName: "确定",
                    cancelName: "关闭",
                    type: "selector",
                    selector: ".data_info",
                    okFn: function (win) {
                        var dataTime = win.find(".dataTime").val();
                        if (isNull(dataTime)) {
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
                        		msg: "确定新增竞品app数据吗?",
                        		url: serverHost + "/cps/addWchartData.json",
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
                $('.dataTime').datepicker({
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
						url: serverHost + "/cps/ajaxDisplayInfo.json",
						params: {
							date: year+""+month+""+day
						},
						success: function (resp) {
							if(resp.msg !='nodata') {
								var obj = resp.model;
								win.find("input[name='bxjCount']").val(obj.bxjCount);
								win.find("input[name='bxsCount']").val(obj.bxsCount);
								win.find("input[name='bxzsCount']").val(obj.bxzsCount);
								win.find("input[name='bxrCount']").val(obj.bxrCount);
								win.find("input[name='ssbdCount']").val(obj.ssbdCount);
								win.find("input[name='jmCount']").val(obj.jmCount);
								win.find("input[name='jjbxCount']").val(obj.jjbxCount);
								win.find("input[name='bxdkCount']").val(obj.bxdkCount);
								win.find("input[name='iybCount']").val(obj.iybCount);
								win.find("input[name='bxwwCount']").val(obj.bxwwCount);
								win.find("input[name='id']").val(obj.id);
							}else{
								win.find("input[name='bxjCount']").val('');
								win.find("input[name='bxsCount']").val('');
								win.find("input[name='bxzsCount']").val('');
								win.find("input[name='bxrCount']").val('');
								win.find("input[name='ssbdCount']").val('');
								win.find("input[name='jmCount']").val('');
								win.find("input[name='jjbxCount']").val('');
								win.find("input[name='bxdkCount']").val('');
								win.find("input[name='iybCount']").val('');
								win.find("input[name='bxwwCount']").val('');
								win.find("input[name='id']").val('');
							}
						}
					});
				}
			},

            /**报表*/
            dataCharts: function () {
                var thiz = this;
                util.ajax({
                    url: serverHost + "/cps/getAllBxjAppCpsDatas.json",
                    success: function (resp) {
                        if (resp.success) {
                            var data = resp.model;
                            thiz.renderDataCharts(data);
                            thiz.shareRenderDataCharts(data);
                        }
                    }
                });
            },
            renderDataCharts: function (data) {
                var myChart = echarts.init(document.getElementById('cpsA'));
                var d = [], d1 = [],d2 = [],d3= [],d4=[];
                var startValue, endValue;
                for (var int = 0; int < data.length; int++) {
                    var object = data[int], date = new Date(object.dataTime);
                    var dateStr = [date.getFullYear(), date.getMonth() + 1, date.getDate()].join('-');
                    d.push([
                        dateStr, object.bxsCount
                    ]);
                    d1.push([
                        dateStr, object.bxzsCount
                    ]);
                    d2.push([
                             dateStr, object.bxjCount
                         ]);
                    d3.push([
                             dateStr, object.bxrCount
                         ]);
                    d4.push([
                             dateStr, object.ssbdCount
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
                        text: 'A类竞品'
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
                        data: ['保险师', '保险助手','保险家','保险人','随身保典']
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
                            name: '保险师',
                            type: 'line',
                            showAllSymbol: true,
                            symbolSize: function (value) {
                                return 2;
                            },
                            data: d
                        },
                        {
                            name: '保险助手',
                            type: 'line',
                            showAllSymbol: true,
                            symbolSize: function (value) {
                                return 2;
                            },
                            data: d1
                        },
                        {
                            name: '保险家',
                            type: 'line',
                            showAllSymbol: true,
                            symbolSize: function (value) {
                                return 2;
                            },
                            data: d2
                        },
                        {
                            name: '保险人',
                            type: 'line',
                            showAllSymbol: true,
                            symbolSize: function (value) {
                                return 2;
                            },
                            data: d3
                        },
                        {
                            name: '随身保典',
                            type: 'line',
                            showAllSymbol: true,
                            symbolSize: function (value) {
                                return 2;
                            },
                            data: d4
                        }
                    ],
                    color: ['#1881de','#dacd21','#fb0303','#C815D7','#F19207']
                };
                myChart.setOption(option);
            },
            shareRenderDataCharts: function (data) {
                var myChart = echarts.init(document.getElementById('cpsB'));
                var d = [], d1 = [],d2 = [],d3= [],d4=[];
                var startValue, endValue;
                for (var int = 0; int < data.length; int++) {
                    var object = data[int], date = new Date(object.dataTime);
                    var dateStr = [date.getFullYear(), date.getMonth() + 1, date.getDate()].join('-');
                    d.push([
                        dateStr, object.jmCount
                    ]);
                    d1.push([
                        dateStr, object.jjbxCount
                    ]);
                    d2.push([
                             dateStr, object.bxdkCount
                         ]);
                    d3.push([
                             dateStr, object.iybCount
                         ]);
                    d4.push([
                             dateStr, object.bxwwCount
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
                        text: 'B类竞品'
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
                        data: ['聚米', '纪家保险','保险大咖','i云保','保险问问']
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
                            name: '聚米',
                            type: 'line',
                            showAllSymbol: true,
                            symbolSize: function (value) {
                                return 2;
                            },
                            data: d
                        },
                        {
                            name: '纪家保险',
                            type: 'line',
                            showAllSymbol: true,
                            symbolSize: function (value) {
                                return 2;
                            },
                            data: d1
                        },
                        {
                            name: '保险大咖',
                            type: 'line',
                            showAllSymbol: true,
                            symbolSize: function (value) {
                                return 2;
                            },
                            data: d2
                        },
                        {
                            name: 'i云保',
                            type: 'line',
                            showAllSymbol: true,
                            symbolSize: function (value) {
                                return 2;
                            },
                            data: d3
                        },
                        {
                            name: '保险问问',
                            type: 'line',
                            showAllSymbol: true,
                            symbolSize: function (value) {
                                return 2;
                            },
                            data: d4
                        }
                    ],
                    color: ['#fb0303','#dacd21','#1881de','#C815D7','#F19207']
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