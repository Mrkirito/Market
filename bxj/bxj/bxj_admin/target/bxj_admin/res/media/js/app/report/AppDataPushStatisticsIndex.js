/**
 * Created by 蔡杲沛(Bright) on 2016/11/22.
 * 推送数据统计
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
                    listJson = [
                        {
                            name: "日期",
                            width: 400,
                            fieldName: "dataTime",
                            renderer: function (row, col, data, value) {
                                return util.dateFormat2(value, 'YYYY-MM-DD HH:mm:ss');
                            }
                        },
                        {
                            name: "内容",
                            width: 1000,
                            fieldName: "content",
                            renderer: function (row, col, data, value) {
                                if (value) {
                                    return value;
                                }else{
                                    return "三高/双高参照基准（平均值)"
                                }
                            }
                        },
                        {
                            name: "分类",
                            width: 150,
                            fieldName: "category"
                        },
                        {
                            name: "子分类",
                            width: 150,
                            fieldName: "subCategory"
                        }
                        , {
                            name: "IOS推送成功量",
                            width: 150,
                            fieldName: "iosPushSuccessCount"
                        }
                        , {
                            name: "IOS点击量",
                            width: 150,
                            fieldName: "iosClickCount"
                        }
                        , {
                            name: "IOS点击率",
                            width: 150,
                            fieldName: "iosClickRate",
                            renderer: function (row, col, data, value) {
                                return value + "%";
                            }
                        }
                        , {
                            name: "安卓推送成功量",
                            width: 150,
                            fieldName: "androidPushSuccessCount"
                        }
                        , {
                            name: "安卓点击量",
                            width: 150,
                            fieldName: "androidClickCount"
                        }
                        , {
                            name: "安卓点击率",
                            width: 150,
                            fieldName: "androidClickRate",
                            renderer: function (row, col, data, value) {
                                return value + "%";
                            }
                        },
                        {
                            name: "总送成功量",
                            width: 150,
                            fieldName: "pushSuccessCount"
                        }
                        , {
                            name: "总点击量",
                            width: 150,
                            fieldName: "clickCount"
                        }
                        , {
                            name: "总点击率",
                            width: 150,
                            fieldName: "clickRate",
                            renderer: function (row, col, data, value) {
                                return value + "%";
                            }
                        }
                    ];
                var thiz = this, list = listTool.create({
                    title: "推送数据统计",
                    selector: ".data-list",
                    remote: true,
                    url: serverHost + "/tjgl/queryAppDataPushStatisticsDataList.json",
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
                    applyClick: function (startDate, endDate) {
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

                reportList.dataAMCharts();
                reportList.dataPMCharts();
            },
            getParams: function () {
                return {
                    startTime: $(".startTime").val(),
                    endTime: $(".endTime").val()
                };
            },

            add: function(data){
                var thiz = this;
                var win = winTool.create({
                    title: "推送数据录入",
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
                                msg: "确定新增推送数据吗?",
                                url: serverHost + "/tjgl/addAppDataPushStatisticsData.json",
                                params: para,
                                success: function (resp) {
                                    if (resp.success) {
                                        messageTool.success("新增成功");
                                        win.close();
                                        thiz.list.reloadPageData();
                                        reportList.dataAMCharts();
                                        reportList.dataPMCharts();
                                    } else {
                                        messageTool.error(resp.msg);
                                    }
                                }
                            });
                        }
                    }
                });

                var objCurrentA = { classNameA: '.androidPushSuccessCount',
                            classNameB: '.iosPushSuccessCount',
                            sumCount: '.pushSuccessCount'
                };
                var objCurrentB = { classNameA: '.iosPushSuccessCount',
                    classNameB: '.androidPushSuccessCount',
                    sumCount: '.pushSuccessCount'
                };
                var objCurrentC = { classNameA: '.iosClickCount',
                    classNameB: '.androidClickCount',
                    sumCount: '.clickCount'
                };
                var objCurrentD = { classNameA: '.androidClickCount',
                    classNameB: '.iosClickCount',
                    sumCount: '.clickCount'
                };

                reportList.calculateCount(win,objCurrentA);
                reportList.calculateCount(win,objCurrentB);
                reportList.calculateCount(win,objCurrentC);
                reportList.calculateCount(win,objCurrentD);

                dateTool.renderDateTime(".dataDate");
            },
            rtnVal:function(data){
                return data == "" ? "" : parseFloat( data);
            },
            calculateCount:function(win,data){
                win.find(data.classNameA).keyup(function(o){
                    var v1 = reportList.rtnVal($(this).val());
                    var v2 = reportList.rtnVal(win.find(data.classNameB).val());
                    win.find(data.sumCount).val(v1+v2);
                    var className = o.currentTarget.className;

                    if (className.indexOf('iosPushSuccessCount') != -1 || className.indexOf('iosClickCount') != -1) {
                        var v3 = reportList.getValueByClass(win,".iosPushSuccessCount");
                        var v4 = reportList.getValueByClass(win,".iosClickCount");
                        win.find('.iosClickRate').val(((v4/v3)*100).toFixed(2));
                    }
                    if (className.indexOf('androidPushSuccessCount') != -1 || className.indexOf('androidClickCount') != -1) {
                        var v3 = reportList.getValueByClass(win,".androidPushSuccessCount");
                        var v4 = reportList.getValueByClass(win,".androidClickCount");
                        win.find('.androidClickRate').val(((v4/v3)*100).toFixed(2));
                    }
                    var v3 = reportList.getValueByClass(win,".pushSuccessCount");
                    var v4 = reportList.getValueByClass(win,".clickCount");
                    win.find('.clickRate').val(((v4/v3)*100).toFixed(2));

                });
            },
            getValueByClass:function(win,className){
                return reportList.rtnVal(win.find(className).val());
            },
            /**报表*/
            dataAMCharts: function () {
                var thiz = this;
                util.ajax({
                    url: serverHost + "/tjgl/getAppDataPushStatisticsEchartsData.json",
                    params: {
                        t: 'AM'
                    },
                    success: function (resp) {
                        if (resp.success) {
                            var data = resp.model;
                            thiz.AMDataCharts(data);
                        }
                    }
                });
            },
            dataPMCharts: function () {
                var thiz = this;
                util.ajax({
                    url: serverHost + "/tjgl/getAppDataPushStatisticsEchartsData.json",
                    params: {
                        t: 'PM'
                    },
                    success: function (resp) {
                        if (resp.success) {
                            var data = resp.model;
                            thiz.PMDataCharts(data);
                        }
                    }
                });
            },
            AMDataCharts: function (data) {
                var myChart = echarts.init(document.getElementById('AmRate'));
                var d = [],d1=[],d2=[];
                var startValue, endValue;
                for (var int = 0; int < data.length; int++) {
                    var object = data[int], date = new Date(object.dataTime);
                    var dateStr = [date.getFullYear(), date.getMonth() + 1, date.getDate()].join('-');
                    d.push([
                        dateStr, object.androidClickRate
                    ]);
                    d1.push([
                        dateStr, object.iosClickRate
                    ]);
                    d2.push([
                        dateStr, object.clickRate
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
                        text: '上午点击率'
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
                        data: ['安卓点击率(%)','IOS点击率(%)','总点击率(%)']
                    },
                    xAxis: {
                        type: 'time',
                        splitLine: {
                            show: false
                        }
                    },
                    yAxis: {
                        type: 'value',
                        axisLabel: {
                            show: true,
                            interval: 'auto',
                            formatter: '{value} %'
                        }
                    },

                    series: [
                        {
                            name: '安卓点击率(%)',
                            type: 'line',
                            showAllSymbol: true,
                            symbolSize: function (value) {
                                return 2;
                            },
                            data: d
                        },
                        {
                            name: 'IOS点击率(%)',
                            type: 'line',
                            showAllSymbol: true,
                            symbolSize: function (value) {
                                return 2;
                            },
                            data: d1
                        },
                        {
                            name: '总点击率(%)',
                            type: 'line',
                            showAllSymbol: true,
                            symbolSize: function (value) {
                                return 2;
                            },
                            data: d2
                        }
                    ],
                    color: ['#CD3333', '#EE6363', '#3A5FCD']
                };
                myChart.setOption(option);
            },
            PMDataCharts: function (data) {
                var myChart = echarts.init(document.getElementById('PmRate'));
                var d = [],d1=[],d2=[];
                var startValue, endValue;
                for (var int = 0; int < data.length; int++) {
                    var object = data[int], date = new Date(object.dataTime);
                    var dateStr = [date.getFullYear(), date.getMonth() + 1, date.getDate()].join('-');
                    d.push([
                        dateStr, object.androidClickRate
                    ]);
                    d1.push([
                        dateStr, object.iosClickRate
                    ]);
                    d2.push([
                        dateStr, object.clickRate
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
                        text: '下午点击率'
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
                        data: ['安卓点击率(%)','IOS点击率(%)','总点击率(%)']
                    },
                    xAxis: {
                        type: 'time',
                        splitLine: {
                            show: false
                        }
                    },
                    yAxis: {
                        type: 'value',
                        axisLabel: {
                            show: true,
                            interval: 'auto',
                            formatter: '{value} %'
                        }
                    },

                    series: [
                        {
                            name: '安卓点击率(%)',
                            type: 'line',
                            showAllSymbol: true,
                            symbolSize: function (value) {
                                return 2;
                            },
                            data: d
                        },
                        {
                            name: 'IOS点击率(%)',
                            type: 'line',
                            showAllSymbol: true,
                            symbolSize: function (value) {
                                return 2;
                            },
                            data: d1
                        },
                        {
                            name: '总点击率(%)',
                            type: 'line',
                            showAllSymbol: true,
                            symbolSize: function (value) {
                                return 2;
                            },
                            data: d2
                        }
                    ],
                    color: ['#CD3333', '#EE6363', '#3A5FCD']
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

