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
                            name: "浏览PV",
                            width: 150,
                            fieldName: "browseCount"
                        },
                        {
                            name: "浏览UV",
                            width: 150,
                            fieldName: "browseCountUv"
                        },
                        {
                            name: "分享PV",
                            width: 150,
                            fieldName: "shareCount"
                        }, {
                            name: "分享UV",
                            width: 150,
                            fieldName: "shareCountUv"
                        }
                    ];
                var thiz = this, list = listTool.create({
                    title: "开门红数据统计列表(负责人：张闯)",
                    selector: ".data-list",
                    remote: true,
                    url: serverHost + "/tjgl/queryKmhStatisticsDataList.json",
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
                    url: serverHost + "/tjgl/getAllBxjAppKmhDatas.json",
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
                var myChart = echarts.init(document.getElementById('kmh'));
                var d = [], d1 = [];
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
                        text: '浏览数据'
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
                        data: ['浏览PV', '浏览UV']
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
                        }
                    ],
                    color: ['#fb0303', '#dacd21']
                };
                myChart.setOption(option);
            },
            shareRenderDataCharts: function (data) {
                var myChart = echarts.init(document.getElementById('share'));
                var d = [], d1 = [];
                var startValue, endValue;
                for (var int = 0; int < data.length; int++) {
                    var object = data[int], date = new Date(object.dataTime);
                    var dateStr = [date.getFullYear(), date.getMonth() + 1, date.getDate()].join('-');
                    d.push([
                        dateStr, object.shareCount
                    ]);
                    d1.push([
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
                        text: '分享数据'
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
                        data: ['分享PV', '分享UV']
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
                            name: '分享PV',
                            type: 'line',
                            showAllSymbol: true,
                            symbolSize: function (value) {
                                return 2;
                            },
                            data: d
                        },
                        {
                            name: '分享UV',
                            type: 'line',
                            showAllSymbol: true,
                            symbolSize: function (value) {
                                return 2;
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