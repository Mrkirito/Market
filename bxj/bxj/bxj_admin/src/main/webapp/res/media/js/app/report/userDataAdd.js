define(['jquery', 'app/common/util', 'app/common/dateTool', 'app/common/messageTool'],
    function ($, util, dateTool, messageTool) {
        var userDataAdd = {
            init: function () {
                $(".saveIn").on("click", function () {
                    var flag = true;
                    var dataTime = $(".dataTime").val(),
                        newIosNum = $(".newIosNum").val(),
                        newAndroidNum = $(".newAndroidNum").val(),
                        activeIosNum = $(".activeIosNum").val(),
                        newAddTarget = $(".newAddTarget").val(),
                        newStartTarget = $(".newStartTarget").val(),
                        salesTotalTarget = $(".salesTotalTarget").val(),

                        activeAndroidNum = $(".activeAndroidNum").val(),
                        timesIosNum = $(".timesIosNum").val(),
                        timesAndroidNum = $(".timesAndroidNum").val(),
                        salesTotal = $(".salesTotal").val();
                    salesProfits = $(".salesProfits").val();
                    bbwNew = $(".bbwNew").val();
                    bbwStart = $(".bbwStart").val();
                    bbwTimes = $(".bbwTimes").val();
                    bbwMessage = $(".bbwMessage").val();
                    bbwSalesTotal = $(".bbwSalesTotal").val();
                    bbappNew = $(".bbappNew").val();
                    bbappStart = $(".bbappStart").val();
                    bbappTimes = $(".bbappTimes").val();
                    bbappMessage = $(".bbappMessage").val();
                    bbappSalesTotal = $(".bbappSalesTotal").val();
                    hjappNew = $(".hjappNew").val();
                    hjappStart = $(".hjappStart").val();
                    hjappTimes = $(".hjappTimes").val();
                    hjappMessage = $(".hjappMessage").val();
                    hjappSalesTotal = $(".hjappSalesTotal").val();
                    hjwPv = $(".hjwPv").val();
                    hjwUv = $(".hjwUv").val();


                    if (!dataTime) {
                        messageTool.error("必须选择统计时间!");
                        flag = false;
                    }
                    if (!newIosNum) {
                        messageTool.error("必须输入ios新增用户!");
                        flag = false;
                    }
                    if (!newAndroidNum) {
                        messageTool.error("必须输入安卓新增用户!");
                        flag = false;
                    }
                    if (!activeIosNum) {
                        messageTool.error("必须输入ios启动用户!");
                        flag = false;
                    }
                    if (!activeAndroidNum) {
                        messageTool.error("必须输入安卓启动用户!");
                        flag = false;
                    }
                    if (!timesIosNum) {
                        messageTool.error("必须输入ios启动次数!");
                        flag = false;
                    }
                    if (!timesAndroidNum) {
                        messageTool.error("必须输入安卓启动次数!");
                        flag = false;
                    }
                    if (!salesTotal) {
                        messageTool.error("必须输入总销售额!");
                        flag = false;
                    }
                    if (!salesProfits) {
                        messageTool.error("必须输入毛利!");
                        flag = false;
                    }


                    if (flag) {
                        util.request({
                            confirm: true,
                            msg: "确定要新增?",
                            url: serverHost + "/report/addUserData.json",
                            params: {
                                dataTime: dataTime,
                                newIosNum: newIosNum,
                                newAndroidNum: newAndroidNum,
                                activeIosNum: activeIosNum,
                                newAddTarget: newAddTarget,
                                newStartTarget: newStartTarget,
                                salesTotalTarget: salesTotalTarget,
                                activeAndroidNum: activeAndroidNum,
                                timesIosNum: timesIosNum,
                                timesAndroidNum: timesAndroidNum,
                                salesTotal: salesTotal,
                                salesProfits: salesProfits,
                                bbwNew: bbwNew,
                                bbwStart: bbwStart,
                                bbwTimes: bbwTimes,
                                bbwMessage: bbwMessage,
                                bbwSalesTotal: bbwSalesTotal,
                                bbappNew: bbappNew,
                                bbappStart: bbappStart,
                                bbappTimes: bbappTimes,
                                bbappMessage: bbappMessage,
                                bbappSalesTotal: bbappSalesTotal,
                                hjappNew: hjappNew,
                                hjappStart: hjappStart,
                                hjappTimes: hjappTimes,
                                hjappMessage: hjappMessage,
                                hjappSalesTotal: hjappSalesTotal,
                                hjwPv: hjwPv,
                                hjwUv: hjwUv
                            },
                            success: function (resp) {
                                if (resp.success) {
                                    messageTool.success("保存成功");
                                } else {
                                    messageTool.error(resp.msg || "保存失败");
                                }
                            }
                        });
                    }
                })

                // 时间渲染
                dateTool.renderDate($(".dataTime"));
                $(".newIosNum, .newAndroidNum, .activeIosNum, .activeAndroidNum, .timesIosNum, .timesAndroidNum").on("keyup", function (event) {
                    var $amountInput = $(this);
                    //响应鼠标事件，允许左右方向键移动
                    event = window.event || event;
                    if (event.keyCode == 37 | event.keyCode == 39) {
                        return;
                    }
                    //先把非数字的都替换掉，除了数字和.
                    $amountInput.val($amountInput.val().replace(/[^\d]/g, ""));
                })
            },
        };
        return userDataAdd;
    });