define(['jquery', 'app/common/util', 'app/common/advanceListTool',
        'app/common/dateTool', 'app/common/messageTool', 'app/common/commonTool', 'app/common/winTool', 'app/common/imageUploadTool', 'app/common/imageView'],
    function ($, util, listTool, dateTool, messageTool, commonTool, winTool, imageUploadTool) {
        var profitList = {
            list: function () {
                var thiz = this,
                    list = listTool.create({
                        title: "评选参选人列表",
                        selector: ".data-list",
                        remote: true,
                        url: serverHost + "/activity/queryVoteSignerList.json",
                        width: 600,
                        height: 600,
                        columns: [
                            {
                                name: "用户id",
                                width: 80,
                                fieldName: "userId"
                            }, {
                                name: "参赛用户名",
                                width: 80,
                                fieldName: "realname"
                            }, {
                                name: "参赛头像",
                                width: 100,
                                fieldName: "photo",
                                renderer: function (row, col, data, value) {
                                    if (!isNull(value))
                                        return "<a href='" + public_path + "/activity" + value + "' rel='lightbox'><img src='" + public_path + "/activity" + value + "' style='width: 40px;height: 40px' /></a>"
                                    return "";
                                }
                            }, {
                                name: "报名手机号码",
                                width: 80,
                                fieldName: "mobile"
                            }, {
                                name: "报名编号",
                                width: 80,
                                fieldName: "signCode"
                            }, {
                                name: "参赛类型",
                                width: 120,
                                fieldName: "signType",
                                renderer: function (row, col, data, value) {
                                    if (value == 0)return "中国保险家候选外勤";
                                    if (value == 1)return "中国青年保险家候选外勤";
                                    if (value == 2)return "中国保险家候选内勤";
                                    if (value == 3)return "中国青年保险家候选内勤";
                                }
                            }, {
                                name: "得票数",
                                width: 80,
                                fieldName: "num",
                                sort: true,
                                sortName: "num"
                            }, {
                                name: "报名状态",
                                width: 80,
                                fieldName: "step",
                                renderer: function (row, col, data, value) {
                                    if (value == 1)return "<span class='label label-table label-info'>报名参赛<i></i></span>";
                                    if (value == 2)return "<span class='label label-table label-success'>完善资料<i></i></span>";
                                }
                            }, {
                                name: "审核状态",
                                width: 80,
                                fieldName: "status",
                                sort: true,
                                sortName: "status",
                                renderer: function (row, col, data, value) {
                                    if (value == 0) return "<span class='label label-table label-info'>待审核<i></i></span>";
                                    if (value == 1) return "<span class='label label-table label-success'>审核通过<i></i></span>";
                                    if (value == 2) return "<span class='label label-table label-danger'>审核拒绝<i></i></span>";
                                }
                            }, {
                                name: "审核时间",
                                width: 100,
                                fieldName: "authTime",
                                sort: true,
                                sortName: "auth_time",
                                renderer: function (row, col, data, value) {
                                    return util.dateFormat2(value, 'YYYY-MM-DD HH:mm:ss');
                                }
                            }, {
                                name: "报名日期",
                                width: 100,
                                fieldName: "createTime",
                                sort: true,
                                sortName: "create_time",
                                renderer: function (row, col, data, value) {
                                    return util.dateFormat2(value, 'YYYY-MM-DD HH:mm:ss');
                                }
                            }, {
                                name: "操作",
                                width: 160,
                                rightFixed: true,
                                renderer: function (rindx, cindex, data) {
                                    var st = '<button class="btn btn-info viewInfo">查看</button>&nbsp;';
                                    if (data.status == 0) {
                                        st += '<button class="btn btn-info approve">审核通过</button>';
                                        st += '<button class="btn btn-info refuse">审核拒绝</button>';
                                    }
                                    st += '<button class="btn btn-danger update">修改</button>';
                                    return st;
                                }
                            }
                        ],
                        paramFn: function () {
                            return {
                                realname: $(".realname").val(),
                                mobile: $(".mobile").val(),
                                signDate: $(".signDate").val(),
                                authDate: $(".authDate").val(),
                                step: $(".step").val(),
                                signType: $(".signType").val(),
                                status: $(".status").val()
                            }
                        },
                        initSearch: function (query) {

                        },
                        tbars: [],
                        classEvents: [{
                            className: "viewInfo",
                            handler: function (idx, data) {
                                thiz.viewInfo(data);
                            }
                        }, {
                            className: "approve",
                            handler: function (idx, data) {
                                thiz.update(data, 1);
                            }
                        }, {
                            className: "update",
                            handler: function (idx, data) {
                                thiz.update(data, 2);
                            }
                        }, {
                            className: "refuse",
                            handler: function (idx, data) {
                                thiz.refuse(data);
                            }
                        },]
                    });
                this.list = list;
                //时间渲染
                dateTool.renderDate(".signDate");
                //时间渲染
                dateTool.renderDate(".authDate");
                //搜索
                $(".search-action").on("click", function () {
                    thiz.list.reloadPageData(1);
                });
            },
            viewInfo: function (data) {
                var thiz = this;
                var win = winTool.create({
                    title: "参选人信息查看",
                    height: 800,
                    width: 800,
                    showCancel: true,
                    showOk: false,
                    okName: "保存",
                    cancelName: "关闭",
                    type: "selector",
                    selector: ".viewWin"
                });
                thiz.rendenWin(win);
                win.find(".userId").text(data.userId);
                win.find(".weixinId").text(data.weixinId);
                win.find(".realname").text(data.realname);
                win.find(".mobile").text(data.mobile);
                win.find(".idCard").text(data.idCard);
                win.find(".createTime").text(util.dateFormat2(data.createTime, 'YYYY-MM-DD HH:mm:ss'));
                win.find(".signCode").text(data.signCode);
                win.find(".province").text(data.province);
                win.find(".company").text(data.company);
                win.find(".job").text(data.job);
                win.find(".yearSalary").text(data.yearSalary + "万元");
                if (data.signType == 0)
                    win.find(".signType").text("中国保险家候选外勤");
                else if (data.signType == 1)
                    win.find(".signType").text("中国青年保险家候选外勤");
                else if (data.signType == 2)
                    win.find(".signType").text("中国保险家候选内勤");
                else if (data.signType == 3)
                    win.find(".signType").text("中国青年保险家候选内勤");

                if (data.signType < 2) {
                    win.find(".jobLbl").text("职级");
                    win.find(".jobYearLbl").text("入行时间");
                    win.find(".jobYear").text(data.joinTime + "年");
                    win.find(".card").css("display", "none");
                    win.find(".position").css("display", "none");
                    win.find(".salary").css("display", "block");
                    win.find(".income").css("display", "block");
                } else {
                    win.find(".jobLbl").text("职务");
                    win.find(".jobYearLbl").text("从业年限");
                    win.find(".jobYear").text(data.jobYear + "年");
                    win.find(".salary").css("display", "none");
                    win.find(".income").css("display", "none");
                    win.find(".card").css("display", "block");
                    win.find(".position").css("display", "block");
                }
                win.find(".myDes").text(data.myDes);
                if (!isNull(data.photo)) {
                    win.find(".photoPic").find(".picHref").attr("href", public_path + "/activity" + data.photo);
                    win.find(".photoPic").find(".picUrl").attr("src", public_path + "/activity" + data.photo);
                }
                if (!isNull(data.pic1)) {
                    win.find(".pic1").find(".picHref").attr("href", public_path + "/activity" + data.pic1);
                    win.find(".pic1").find(".picUrl").attr("src", public_path + "/activity" + data.pic1);
                }
                if (!isNull(data.pic2)) {
                    win.find(".pic2").find(".picHref").attr("href", public_path + "/activity" + data.pic1);
                    win.find(".pic2").find(".picUrl").attr("src", public_path + "/activity" + data.pic2);
                }
                if (!isNull(data.incomePic)) {
                    win.find(".incomePic").find(".picHref").attr("href", public_path + "/activity" + data.incomePic);
                    win.find(".incomePic").find(".picUrl").attr("src", public_path + "/activity" + data.incomePic);
                }
                if (!isNull(data.userCardPic)) {
                    win.find(".userCardPic").find(".picHref").attr("href", public_path + "/activity" + data.userCardPic);
                    win.find(".userCardPic").find(".picUrl").attr("src", public_path + "/activity" + data.userCardPic);
                }
                if (!isNull(data.positionPic)) {
                    win.find(".positionPic").find(".picHref").attr("href", public_path + "/activity" + data.positionPic);
                    win.find(".positionPic").find(".picUrl").attr("src", public_path + "/activity" + data.positionPic);
                }
            },
            update: function (data, operateType) {
                var thiz = this;
                var id = data.id;
                var win = winTool.create({
                    title: "审核",
                    height: 600,
                    width: 800,
                    showCancel: true,
                    okName: "保存",
                    cancelName: "关闭",
                    type: "selector",
                    selector: ".updateWin",
                    okFn: function (win) {
                        var realname = win.find(".realname").val();
                        var province = win.find(".province").val();
                        var company = win.find(".company").val();
                        var job = win.find(".job").val();
                        var jobYear = win.find(".jobYear").val();
                        var joinTime = win.find(".jobYear").val();
                        var signType = win.find(".signType").val();
                        var myDes = win.find(".myDes").val();
                        var photo = win.find(".photoPic").data("url");
                        var pic1 = win.find(".pic1").data("url");
                        var pic2 = win.find(".pic2").data("url");
                        var yearSalary = win.find(".yearSalary").val();
                        var flag = true;
                        if (!realname) {
                            messageTool.error("必须参赛用户名!");
                            flag = false;
                        }
                        if (!province) {
                            messageTool.error("必须输入省份!");
                            flag = false;
                        }
                        if (!company) {
                            messageTool.error("必须输入公司!");
                            flag = false;
                        }
                        if (!signType) {
                            messageTool.error("必选选择参赛类型!");
                            flag = false;
                        }
                        if (!job) {
                            if (signType < 2)
                                messageTool.error("必须输入职级!");
                            else
                                messageTool.error("必须输入职务!");
                            flag = false;
                        }
                        if (!jobYear) {
                            if (signType < 2) {
                                jobYear = null;
                                messageTool.error("必须输入入行时间!");
                            } else {
                                joinTime = null;
                                messageTool.error("必须输入从业年限!");
                            }
                            flag = false;
                        }
                        if (!myDes) {
                            messageTool.error("必须输入个人荣誉!");
                            flag = false;
                        }
                        if (signType < 2) {
                            if (!yearSalary) {
                                messageTool.error("必须输入税前年薪!");
                                flag = false;
                            }
                        }
                        if (!photo) {
                            messageTool.error("必须上传用户头像!");
                            flag = false;
                        }
                        if (flag) {
                            if (operateType == 1) {
                                util.request({
                                    confirm: true,
                                    msg: "确定参赛信息审核通过吗?",
                                    url: serverHost + "/activity/approve.json",
                                    params: {
                                        id: id,
                                        realname: realname,
                                        province: province,
                                        company: company,
                                        job: job,
                                        jobYear: jobYear,
                                        signType: signType,
                                        myDes: myDes,
                                        photo: photo,
                                        pic1: pic1,
                                        pic2: pic2,
                                        yearSalary: yearSalary
                                    },
                                    success: function (resp) {
                                        if (resp.success) {
                                            messageTool.success("审核成功");
                                            win.close();
                                            thiz.list.reloadPageData();
                                        } else {
                                            messageTool.error("审核失败");
                                        }
                                    }
                                });
                            } else {
                                util.request({
                                    confirm: true,
                                    msg: "确定修改参赛信息吗?",
                                    url: serverHost + "/activity/update.json",
                                    params: {
                                        id: id,
                                        realname: realname,
                                        province: province,
                                        company: company,
                                        job: job,
                                        jobYear: jobYear,
                                        signType: signType,
                                        myDes: myDes,
                                        photo: photo,
                                        pic1: pic1,
                                        pic2: pic2,
                                        yearSalary: yearSalary
                                    },
                                    success: function (resp) {
                                        if (resp.success) {
                                            messageTool.success("修改成功");
                                            win.close();
                                            thiz.list.reloadPageData();
                                        } else {
                                            messageTool.error("修改失败");
                                        }
                                    }
                                });
                            }
                        }
                    }
                });
                thiz.rendenWin(win);
                win.find(".userId").text(data.userId);
                win.find(".weixinId").text(data.weixinId);
                win.find(".realname").val(data.realname);
                win.find(".mobile").text(data.mobile);
                win.find(".idCard").text(data.idCard);
                win.find(".createTime").text(util.dateFormat2(data.createTime, 'YYYY-MM-DD HH:mm:ss'));
                // win.find(".signCode").val(data.signCode);
                win.find(".province").val(data.province);
                win.find(".company").val(data.company);
                win.find(".job").val(data.job);
                win.find(".yearSalary").val(data.yearSalary);
                win.find(".signType").val(data.signType);
                if (data.signType < 2) {
                    win.find(".jobLbl").text("职级");
                    win.find(".jobYearLbl").text("入行时间");
                    win.find(".jobYear").val(data.joinTime);
                    win.find(".card").css("display", "none");
                    win.find(".position").css("display", "none");
                    win.find(".salary").css("display", "block");
                    win.find(".income").css("display", "block");
                } else {
                    win.find(".jobLbl").text("职务");
                    win.find(".jobYearLbl").text("从业年限");
                    win.find(".jobYear").val(data.jobYear);
                    win.find(".salary").css("display", "none");
                    win.find(".income").css("display", "none");
                    win.find(".card").css("display", "block");
                    win.find(".position").css("display", "block");
                }
                win.find(".signType").bind("change", function () {
                    if (this.selectedIndex < 2) {
                        win.find(".jobLbl").text("职级");
                        win.find(".jobYearLbl").text("入行时间");
                        win.find(".card").css("display", "none");
                        win.find(".position").css("display", "none");
                        win.find(".salary").css("display", "block");
                        win.find(".income").css("display", "block");
                    } else {
                        win.find(".jobLbl").text("职务");
                        win.find(".jobYearLbl").text("从业年限");
                        win.find(".salary").css("display", "none");
                        win.find(".income").css("display", "none");
                        win.find(".card").css("display", "block");
                        win.find(".position").css("display", "block");
                    }
                });
                win.find(".myDes").val(data.myDes);
                if (!isNull(data.photo)) {
                    win.find(".photoPic").data("url", data.photo);
                    win.find(".photoPic").attr("src", public_path + "/activity" + data.photo);
                }
                if (!isNull(data.pic1)) {
                    win.find(".pic1").data("url", data.pic1);
                    win.find(".pic1").attr("src", public_path + "/activity" + data.pic1);
                }
                if (!isNull(data.pic2)) {
                    win.find(".pic2").data("url", data.pic2);
                    win.find(".pic2").attr("src", public_path + "/activity" + data.pic2);
                }
                if (!isNull(data.incomePic)) {
                    win.find(".incomePic").find(".picHref").attr("href", public_path + "/activity" + data.incomePic);
                    win.find(".incomePic").find(".picUrl").attr("src", public_path + "/activity" + data.incomePic);
                }
                if (!isNull(data.userCardPic)) {
                    win.find(".userCardPic").find(".picHref").attr("href", public_path + "/activity" + data.userCardPic);
                    win.find(".userCardPic").find(".picUrl").attr("src", public_path + "/activity" + data.userCardPic);
                }
                if (!isNull(data.positionPic)) {
                    win.find(".positionPic").find(".picHref").attr("href", public_path + "/activity" + data.positionPic);
                    win.find(".positionPic").find(".picUrl").attr("src", public_path + "/activity" + data.positionPic);
                }
            },
            refuse: function (data) {
                var thiz = this;
                var id = data.id;
                util.request({
                    confirm: true,
                    msg: "确定拒绝参赛信息吗?",
                    url: serverHost + "/activity/refuse.json",
                    params: {
                        id: id
                    },
                    success: function (resp) {
                        if (resp.success) {
                            messageTool.success("拒绝成功");
                            thiz.list.reloadPageData();
                        } else {
                            messageTool.error("拒绝失败");
                        }
                    }
                });
            },
            rendenWin: function (win) {
                win.find(".infos-images").on("click", function () {
                    var thiz = this;
                    imageUploadTool.init({
                        title: "上传图片",
                        attach: 9,
                        acceptedFiles: ".png,.jpg,.jpeg",
                        maxFiles: 1,
                        callback: function (images) {
                            var img = images.length > 0 ? images[0] : {};
                            $(thiz).parent().find(".picUrl").attr("src", public_path + "/activity" + img.fileName);
                            $(thiz).parent().find(".picUrl").data("url", img.fileName);
                        }
                    });
                });
                win.find(".uploadImg").on("click", ".remove-img", function () {
                    var thiz = this;
                    $(thiz).parent().find(".picUrl").attr("src", "");
                    $(thiz).parent().find(".picUrl").data("url", "");
                });
            },
        };
        return profitList;
    });

function isNull(obj) {
    if (obj == null || obj == "" || obj == undefined) {
        return true;
    }
    return false;
}