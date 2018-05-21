define(['jquery', 'app/common/util',
        'app/common/winTool',
        'app/common/listTool',
        'app/common/messageTool',
        'app/common/imageUploadTool'
],
    function ($, util, winTool, listTool, messageTool, imageUploadTool) {
        return {
            pptListWin: function (data) {
                var thiz = this,
                    courseId = data.id,
                    courseTitle = data.courseTitle,
                    win = winTool.create({
                        title: '课程(' + courseTitle + ')',
                        showOk: false,
                        cancelName: "关闭",
                        selector: ".course_ppt_list",
                        type: 'selector',
                        width: 900,
                        height: 500
                    });

                thiz.createListTool(win, courseId);
            },

            createListTool: function (win, courseId) {
                var thiz = this,
                    priceListSelector = win.find(".ppt_list"),
                    priceToolbar = win.find(".ppt_toolbar"),
                    url = serverHost + "/coursePpt/list.json?courseId=" + courseId,
                    itemListTool = listTool.create({
                        selector: priceListSelector,
                        toolbarSelector: priceToolbar,
                        remote: true,
                        showCheckbox: false,
                        url: url,
                        columns: [
                            /*{
                                name: "图片",
                                fieldName: "pptUrl",
                                renderer: function(row, col, data, value) {
                                    return '<a href="' + $('#show_path').val() + value + '" rel="lightbox"><img src="' + $('#show_path').val() + value + '"/></a>';
                                }
                            },*/
                            {
                                name: "状态",
                                fieldName: "enableStatus",
                                renderer: function (row, col, data, value) {
                                    if (value == 1) {
                                        return '<span class="label label-sm label-success">启用</span>';
                                    } else if (value == 0) {
                                        return '<span class="label label-sm label-danger">禁用</span>';
                                    }
                                }
                            },
                            {
                                name: "段落标题",
                                fieldName: "paragraphTitle"
                            },
                            {
                                name: "段落内容",
                                fieldName: "paragraphContext",
                            },
                            {
                                name: "排序",
                                fieldName: "sort",
                                sort: true,
                                sortName: "sort",
                                fieldName: "sort"
                            },
                            {
                                name: "操作",
                                renderer: function (rindx, cindex, data) {
                                    var st = '<div class="action-buttons">';
                                    var enableStatus = data.enableStatus;
                                    if(enableStatus) st += '<a class="red forbid" href="javascript:void(0);" title="禁用"> <i class="fa fa-arrow-down bigger-140 "></i> </a>&nbsp;';
                                    else st += '<a class="green unforbid" href="javascript:void(0);" title="启用"> <i class="fa fa-arrow-up bigger-140 "></i> </a>&nbsp;';
                                    st += '<a class="green update-info" href="javacript:void(0);" title="编辑"> <i class="fa fa-pencil bigger-130 "></i> </a> ' +
                                        '<a class="red delete-info" href="javacript:void(0);" title="删除" data-rel="25"> <i class="fa fa-trash-o bigger-130"></i> </a> </div>';
                                    return st;
                                }
                            }],
                        tbars: [{
                            icon: 'fa fa-plus',
                            name: "新增",
                            //rightCode: 'add_goods_price',
                            handler: function (e, btn, tool) {
                                thiz.addPirce(courseId);
                            }
                        }],
                        classEvents: [
                            {
                                className: "unforbid",
                                rightCode: "unforbid",
                                handler: function (idx, data) {
                                    thiz.forbid(data, 1);
                                }
                            },
                            {
                                className: "forbid",
                                rightCode: "forbid",
                                handler: function (idx, data) {
                                    thiz.forbid(data, 0);
                                }
                            },
                            {
                                className: 'update-info',
                                //rightCode: "update_goods_price",
                                handler: function (rowIndex, data) {
                                    thiz.updatePirce(data);
                                }
                            },
                            {
                            className: 'delete-info',
                            //rightCode: "delete_goods_price",
                            handler: function (idx, data) {
                                thiz.deletePirce(data);
                            }
                        }],
                    });

                thiz.pptList = itemListTool;
            },

            /** 添加或更新ppt **/
            checkPriceForm: function (win, courseId) {
                var id = win.find('#ppt_id').val(),
                    paragraphTitle = win.find('.paragraphTitle').val(),
                    paragraphContext = win.find('.paragraphContext').val(),
                    sort = win.find('.sort').val(),
                    pptUrl = win.find(".picUrl2").attr("data-url");

                var flag = true;
                /*if (!paragraphContext) {
                    messageTool.error("必须输入内容!");
                    flag = false;
                }*/
                if (!pptUrl) {
                    messageTool.error("必须输入图片!");
                    flag = false;
                }
                if (!sort) {
                    messageTool.error("必须输入排序!");
                    flag = false;
                }

                if (flag) return {
                    id: id,
                    paragraphTitle: paragraphTitle,
                    paragraphContext: paragraphContext,
                    sort: sort,
                    pptUrl: pptUrl,
                    courseId: courseId
                };
                else return flag;
            },

            /** 添加更新窗口 **/
            createWin: function (title, courseId) {
                var thiz = this,
                    win = winTool.create({
                        title: title,
                        okName: "保存",
                        cancelName: "关闭",
                        type: 'selector',
                        selector: ".course_ppt_add_dialog",
                        width: 600,
                        height: 400,
                        okFn: function (win) {
                            thiz.saveFuncData(win, courseId);
                        }
                    });
                thiz.rendenWin(win);
                return win;
            },

            /** 添加 **/
            addPirce: function (courseId) {
                var thiz = this,
                    win = thiz.createWin("新增", courseId);
            },

            /** 更新 **/
            updatePirce: function (data) {
                var thiz = this,
                    id = data.id,
                    courseId = data.courseId;
                    win = thiz.createWin("修改", courseId);
                util.ajax({
                    url: serverHost + "/coursePpt/detail.json",
                    params: {
                        id: id
                    },
                    success: function (resp) {
                        if (resp.success) {
                            var detail = resp.model;
                            /** 赋值 **/
                            win.find("#ppt_id").val(detail.id);
                            win.find(".paragraphTitle").val(detail.paragraphTitle);
                            win.find(".paragraphContext").val(detail.paragraphContext);
                            win.find(".sort").val(detail.sort);
                            win.find(".picUrl2").attr("src", $('#show_path').val() + detail.pptUrl);
                            win.find(".picUrl2").attr("data-url", detail.pptUrl);
                        } else {
                            messageTool.error("更新失败!");
                        }
                    }
                });

            },

            /** 添加或更新保存数据 **/
            saveFuncData: function (win, goodsId) {
                var thiz = this;
                var params = thiz.checkPriceForm(win, goodsId);
                if (typeof params != 'boolean') {
                    util.request({
                        confirm: true,
                        msg: "确定要执行操作?",
                        url: serverHost + "/coursePpt/addOrUpdate.json",
                        params: params,
                        success: function (resp) {
                            if (resp.success) {
                                messageTool.success("操作成功");
                                win.close();
                                thiz.pptList.loadPageData();
                            } else {
                                messageTool.error("添加失败");
                            }
                        }
                    });
                }
            },

            /** 禁用启用 **/
            forbid: function (data, status) {
                var thiz = this;
                var id = data.id;
                var msg = status==1?'确定要启用？':'确定要禁用';
                util.request({
                    confirm: true,
                    msg: msg,
                    url: serverHost + "/coursePpt/forbid.json",
                    params: {
                        id: id,
                        status : status
                    },
                    success: function (resp) {
                        if (resp.success) {
                            messageTool.success("操作成功!");
                            thiz.pptList.loadPageData();
                        } else {
                            messageTool.error("操作失败！");
                            thiz.pptList.loadPageData();
                        }
                    }
                });
            },

            /** 删除 **/
            deletePirce: function (data) {
                var thiz = this;
                var id = data.id;
                util.request({
                    confirm: true,
                    msg: "确定要删除?",
                    url: serverHost + "/coursePpt/delete.json?id=" + id,
                    params: {
                        id: id,
                        status: status
                    },
                    success: function (resp) {
                        if (resp.success) {
                            messageTool.success("操作成功!");
                            thiz.pptList.loadPageData();
                        } else {
                            messageTool.error("操作失败！");
                            thiz.pptList.loadPageData();
                        }
                    }
                });
            },

            /** 上传图片 **/
            rendenWin: function (win) {
                win.find(".infos-images2").on("click", function () {
                    imageUploadTool.init({
                        title: "上传图片",
                        attach: 11,
                        acceptedFiles: ".png,.jpg,.jpeg",
                        maxFiles: 1, //图片个数
                        extData: {
                            //size: "100x100"
                        },
                        callback: function (images) {
                            var img = images.length > 0 ? images[0] : {};
                            $(".picUrl2").attr("src", img.fileUrl);
                            $(".picUrl2").attr("data-url", img.fileName);
                        }
                    });
                });
                // 删除 图片
                win.find(".uploadImg2").on("click", ".remove-img", function () {
                    $(".picUrl2").attr("src", "");
                    $(".picUrl2").attr("data-url", "");
                });
            }
        };
    });