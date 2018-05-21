/**
 * Created by 蔡杲沛(Bright) on 2016/11/9.
 * 统计管理-新人通
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
                        renderer : function(row, col, data,value) {
                            return row + 1;
                        }
                    },
                    {
                        name: "日期",
                        width: 150,
                        fieldName: "dataTime",
                        renderer : function(row, col, data,value) {
                            return util.dateFormat2(value, 'YYYY-MM-DD');
                        }
                    },
                    {
                        name: "学习PV",
                        width: 150,
                        fieldName: "studyCount"
                    },
                    {
                        name: "学习UV",
                        width: 150,
                        fieldName: "studyCountUv"
                    },
                    {
                        name: "测试PV",
                        width: 150,
                        fieldName: "testCount"
                    },
                    {
                        name: "测试UV",
                        width: 150,
                        fieldName: "testCountUv"
                    },
                    {
                        name: "通关PV",
                        width: 150,
                        fieldName: "clearanceCount"
                    },
                    {
                        name: "通关UV",
                        width: 150,
                        fieldName: "clearanceCountUv"
                    }
                ];
                var thiz = this, list = listTool.create({
                    title: "新人通数据统计列表(负责人：张闯)",
                    selector: ".data-list",
                    remote: true,
                    url: serverHost + "/tjgl/queryXrtStatisticsDataList.json",
                    width: 600,
                    height: 900,
                    columns: listJson,
                    orderBy: "data_Time desc",
                    paramFn: function () {
                        return thiz.getParams();
                    },

                    initSearch: function (query) {
                    },
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
            /**报表*/
            dataCharts: function () {
                var thiz = this;
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
            },
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
                        text: '学习数据'
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
                        text: '测试数据'
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
                        text: '通关数据'
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