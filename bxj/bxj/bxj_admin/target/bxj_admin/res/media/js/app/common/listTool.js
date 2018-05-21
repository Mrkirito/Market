/*listTool.create({
 selector: ".x-data-list",
 listName: "标题",
 listHeadName: "表头信息",
 tbars: [{
 name: "添加",
 icon: "fa fa-plus",
 url: "shop-add.html"
 }, {
 name: "删除",
 icon: "icon-trash",
 handler: function (event, btn) {
 alert(1);
 }
 }],
 columns: [{
 name: "一级分类",
 fieldName: "firstItemName"
 }, {
 name: "二级分类",
 fieldName: "secondItemName",
 renderer: function(rowIndex, colIndex,
 }],
 data: [{
 firstItemName: "美食",
 secondItemName: "早点"
 }, {
 firstItemName: "美食1",
 secondItemName: "早点2"
 }],
 remote: false,
 paramFn: function(){},
 queryData: function (callback) {},
 rowClick: function (rowIndex, rowData, checked) {},
 rowDblclick: function (rowIndex, rowData) {},
 classEvents:[{
 className: "add-shop",
 handler: function(rowIndex, rowData){}
 }]
 });*/
define(["jquery", 'common/math', "util",
    'app/common/treeTableTool',
    'plugins/jquery.blockUI'
], function ($, math, util, treeTableTool, _) {

    var defaults = {
            selector: 'body',
            listName: "标题",
            listHeadName: "表头信息",
            tbars: [],
            columns: [],
            data: [],
            remote: false,
            url: "",
            multiSelect: true,
            headChbWidth: 50,
            paramFn: function () {
            },
            queryData: function (callback) {
            },
            rowClick: function (rowIndex, rowData, checked) {
            },
            rowDblclick: function (rowIndex, rowData) {
            },
            rowSelected: function (rowData) {
                return false;
            },
            selectAllFn: function (dataList, checked) {

            },
            classEvents: [],
            /**
             * 渲染查询条件
             * @param queryData 后台返回的查询条件数据
             */
            initSearch: function (queryData, module) {
            },
            module: null, // 引用listTool的模块对象, 回传给initSearch的参数
            hiddenToolbar: false,
            toolbarSelector: '.top-toolbar',
            extData: {},// 扩展数据
            hideColumnBorder: false,
            showPagebar: true, // 是否显示分页工具条 默认true
            showCheckbox: true, // 是否显示复选框
            lineCheckboxShowFn: function () {
                return true;
            },
            lineCheckboxDisabledFn: function () {
                return false;
            },
            lineHiddenFn: function () {
                return false;
            },
            enableTree: false,
            treeConfig: {},
            idName: null,
            parentName: null,
            tableId: null
        },
        listHtmls = ['<div class="row"><div class="col-xs-12">',
//            '<h3 class="header smaller lighter blue header-h3">$listName$</h3>',
//            '<div class="x-list-toolbar" style="margin-bottom: 0px;">',
//            '</div>',
            '<div class="table-responsive" style="">',
            '<table  class="table table-striped table-bordered table-hover dataTable " style="margin-top: 0px; margin-bottom: 0px; table-layout: fixed;">',
            '<thead></thead><tbody class="shop-data-list"></tbody></table>',
            '<div class="row page-data-info x-list-page-data-info" style="margin-left: 0px; margin-right: 0px;"></div>',
            '</div>',
            '</div>',
            '</div>'],
        abtnHtmls = ['<a href="$btnUrl$" >',
            '<i class="$icon$"></i>',
            '$btnName$',
            '</a>'],
        btnHtmls = [
            '<a >',
            '<i class="$icon$"></i>',
            '$btnName$',
            '</a>'],
        headCheckHtml = '<th class="center" style="width: 50px;"><label><div class="checker"><span><input type="checkbox" class=" header-checkbox" /></span></div></label></th>',
        lineCheckHtml = '<td class="center"><span><label><div class="checker"><span><input type="checkbox" class=" line-checkbox" /></span></div></label></span></td>',

        _defaultColumn = {
            class: "",
            name: "未命名",
            fieldName: "blankName",
            content: null,
            renderer: null,
            style: "",
            sortName: "",
            firstHide: false,
            secondHide: false,
            thirdHide: false
        };


    return {
        create: function (config) {
            var listTool = {
                init: function (config) {
                    config = config || {};
                    this.opts = $.extend({}, defaults, config);
                    this.dataList = [];
                    this.currentPage = 1;
                    this.pageSize = this.opts.pageSize||10;
                    this.orderBy = null;
                    this.firstLoad = true;
                    this.multiSelect = this.opts.multiSelect == undefined ? true : !!this.opts.multiSelect;
                    this.buttons = [];
                    this.totalCount = 0;

                    if(!this.opts.showCheckbox) {
                        this.multiSelect = false;
                    }

                    var listName = this.opts.listName,
                        listHeadName = this.opts.listHeadName,
                        selector = this.opts.selector,
                        listHtml = listHtmls.join(""),
                        listHtml = listHtml.replace("$listName$", listName).replace("$listHeadName$", listHeadName),
                        list = $(listHtml).appendTo(selector);

                    this.table = $(list).find('table');
                    this.thead = $(this.table).find('thead');
                    this.tbody = $(this.table).find('tbody');
//                    this.toolbar = $(list).find(".x-list-toolbar");
                    this.toolbar = $(this.opts.toolbarSelector);
                    this.pageinfo = $(list).find(".x-list-page-data-info");

                    if (!!this.opts.hiddenToolbar) {
                        this.toolbar.css("display", "none");
                        $(list).find(".header-h3").css("display", "none");
                    }

                    if(!!this.opts.hideColumnBorder) {
                        this.table.addClass("table-bordered2");
                    }

                    if(this.opts.tableId) {
                        this.table.attr("id", this.opts.tableId);
                    }

                    this.createToolbar();
                    this.createHeader();
                    this.loadPageData();
                    this.eventBinding();
                    return this;
                },

                /**
                 * 创建工具栏，按钮区域
                 */
                createToolbar: function () {
                    var thiz = this;
                    thiz.buttons = [];
                    $(thiz.opts.tbars).each(function (idx, button) {
                        if(thiz.hasRight(button)) {
                            if (button.url) {
                                var abtnHtml = abtnHtmls.join("").replace("$icon$", button.icon || "").replace("$btnName$", button.name || "").replace("$btnUrl$", button.url);
                                var btn = $(abtnHtml).appendTo(thiz.toolbar);
                                if (button.cls) {
                                    $(btn).addClass(button.cls);
                                }
                                thiz.buttons.push(btn);
                            } else {
                                var btnHtml = btnHtmls.join("").replace("$icon$", button.icon || "").replace("$btnName$", button.name || "");
                                var btn = $(btnHtml).appendTo(thiz.toolbar);

                                if (button.cls) {
                                    $(btn).addClass(button.cls);
                                }
                                $(btn).on("click", function (e) {
                                    if (button.handler) {
                                        button.handler(e, this, thiz);
                                    }
                                });
                                thiz.buttons.push(btn);
                            }
                        }
                    });
                },
                /**
                 * 创建表头
                 */
                createHeader: function () {
                    var thiz = this,
                        headtr = $("<tr></tr>").appendTo(thiz.thead),
                        headCheck = $(headCheckHtml).appendTo(headtr),
                        enableTree = !!(thiz.opts.enableTree && thiz.opts.idName && thiz.opts.parentName),
                        treePaddingHtml = '<span class="indenter" style="padding-left: 0px;"></span>';

                    if(enableTree) {
                        headCheck.prepend($(treePaddingHtml));
                    }

                    if(thiz.opts.headChbWidth) {
                        headCheck.css("width", thiz.opts.headChbWidth+"px");
                    }

                    // 隐藏复选框
                    $(headCheck).css("display", !thiz.opts.showCheckbox?"none":"");

                    $(thiz.opts.columns).each(function (idx, column) {
                        column = $.extend({}, _defaultColumn, column);
                        var defaultStyle = "border-color: #DDD; font-weight: bold; vertical-align: middle; " +
                                "white-space: nowrap; overflow:hidden; ",
                            style = defaultStyle + (column.width ? "width: " + column.width + "px;" : "") + (column.style || ""),
                            className = column.class||"";
                        if(!!column.firstHide) {
                            className += " hide-td-first";
                        }
                        if(!!column.secondHide) {
                            className += " hide-td-second";
                        }
                        if(!!column.thirdHide) {
                            className += " hide-td-third";
                        }
                        if(!!column.lineHiddenFn && column.lineHiddenFn()) {
                            className += " hide-td-third";
                        }
                        var thHtml = '<th style="' + style + '" class="' + className + '"' +
                            ' title="' + column.name + '" data-sortname="' + (column.sortName || "") + '">' + column.name + '</th>';
                        var th = $(thHtml).appendTo(headtr);
                        if (!!column.sort) {
                            $(th).addClass("sorting");
                        }
                    });
                    $(thiz.thead).on("click", "th", function (e) {
                        var sortName = $(this).data("sortname"),
                            sort = "";
                        $(this).siblings().filter(function () {
                            return $(this).hasClass("sorting_desc") || $(this).hasClass("sorting_asc");
                        }).addClass("sorting").removeClass("sorting_desc").removeClass("sorting_asc");

                        if ($(this).hasClass("sorting")) {
                            $(this).removeClass("sorting");
                            $(this).addClass("sorting_asc");
                            sort = " asc";
                        } else if ($(this).hasClass("sorting_asc")) {
                            $(this).removeClass("sorting_asc");
                            $(this).addClass("sorting_desc");
                            sort = " desc";
                        } else if ($(this).hasClass("sorting_desc")) {
                            $(this).removeClass("sorting_desc");
                            $(this).addClass("sorting_asc");
                            sort = " asc";
                        }
                        if (sortName) {
                            thiz.loadPageData(1, null, sortName + sort);
                        }
                    });
                },

                /**
                 * 生成表数据
                 */
                loadPageData: function (pageNo, pageSize, orderBy) {
                    var thiz = this,
                        data = thiz.opts.data || [];

                    pageNo = pageNo || thiz.currentPage;
                    pageSize = pageSize || thiz.pageSize;

                    orderBy = orderBy || thiz.orderBy;

                    thiz.tbody.html("");
                    thiz.dataList = [];

                    thiz.addLoadingText();

                    if (data && data.length > 0) {
                        thiz.addDataLines(data);
                        thiz.renderPageInfo({
                            currentPage: pageNo,
                            pageSize: pageSize,
                            totalCount: data.length
                        });
                        thiz.totalCount = data.length;
                    } else if (!!thiz.opts.remote && thiz.opts.url) {
                        /** 动态加载数据使用 */
                        var params = {};
                        if (thiz.opts.paramFn) {
                            params = thiz.opts.paramFn() || {};
                        }
                        params['currentPage'] = pageNo;
                        params['pageSize'] = pageSize;
                        params['orderBy'] = orderBy;
                        params['firstLoad'] = thiz.firstLoad;

                        $(thiz.table).block({
                            message: "数据加载中...",
                            fadeIn: 1000,
                            fadeOut: 1000,
                            css: {
                                border: '0px',
                                padding: '10px',
                                'background-color': 'transparent',
                                'color': '#fff',
                                'font-size': '15px',
                                'font-weight': 'bolder',
                            }
                        });

                        util.ajax({
                            url: thiz.opts.url,
                            params: params,
                            success: function (resp) {
                                if (resp.success == true) {
                                    var dataList = resp.model || [],
                                        pageData = resp.query || {};
                                    thiz.renderSearchCondition(pageData);
                                    thiz.addDataLines(dataList);
                                    thiz.renderPageInfo(pageData);
                                    thiz.hasClassRight();
                                } else {
                                    thiz.tbody.html("");
                                    thiz.addTableMessage("系统超时，请稍后重试");
                                }
                                $(thiz.table).unblock();
                            },
                            failure: function () {
                                $(thiz.table).unblock();
                            }
                        });
                    }
                },

                addLoadingText: function () {
                    this.addTableMessage("数据加载中。。。");
                },

                addNoDataText: function () {
                    this.addTableMessage("暂无数据。");
                },

                addTableMessage: function (message) {
                    var colCount = this.opts.columns.length,
                        colCount = this.opts.showCheckbox? colCount + 1 : colCount,
                        linetr = $("<tr></tr>").appendTo(this.tbody),
                        rowHtml = '<td colspan="' + colCount + '" style="text-align: center;">' + message + '</td>';
                    $(rowHtml).appendTo(linetr);
                },

                /**
                 * 插入数据行
                 * @param dataList
                 */
                addDataLines: function (dataList) {
                    var thiz = this;
                    thiz.tbody.html("");
                    thiz.dataList = [];

                    if(dataList.length == 0) {
                        thiz.addNoDataText();
                    }

                    $(dataList).each(function (idx, item) {
                        thiz.dataList.push(item);

                        var lineHtml = "<tr></tr>";

                        if(thiz.opts.enableTree && thiz.opts.idName && thiz.opts.parentName) {
                            var parent_id = item[thiz.opts.parentName],
                                parent_id = undefined == parent_id?"":"data-tt-parent-id='" + item[thiz.opts.parentName] + "'",
                                ttid = item[thiz.opts.idName],
                                ttid = undefined == ttid?0:ttid;
                            lineHtml = "<tr data-tt-id='" + ttid + "' " + parent_id + "></tr>"
                        }

                        var linetr = $(lineHtml).appendTo(thiz.tbody),
                            lineCheckEle = $(lineCheckHtml).appendTo(linetr),
                            checkedEle = lineCheckEle.find(".line-checkbox"),
                            rowSelected = thiz.opts.rowSelected(item);

                        // 隐藏复选框
                        $(lineCheckEle).css("display", !thiz.opts.showCheckbox?"none":"");

                        var showchb = true,
                            diabledchb = false;
                        if(thiz.opts.lineCheckboxShowFn) {
                            showchb = thiz.opts.lineCheckboxShowFn(item);
                            showchb = undefined == showchb?true:showchb;
                        }
                        if(thiz.opts.lineCheckboxDisabledFn) {
                            diabledchb = thiz.opts.lineCheckboxDisabledFn(item);
                            diabledchb = undefined == diabledchb?false:diabledchb;
                        }
                        $(checkedEle).css("display", !showchb?"none":"");
                        $(checkedEle).attr("disabled", diabledchb);


                        if (!!rowSelected) {
                            $(checkedEle).attr("checked", rowSelected);
                            $(linetr).addClass('highlight');
                        }

                        $(thiz.opts.columns).each(function (idx2, column) {
                            var rowHtml = '',
                                style = ' style="' + (!!column.wordWrap?"":'white-space: nowrap;  overflow:hidden;') + (column.width ? "width: " + column.width + "px;" : "") + '" ',
                                value = item[column.fieldName],
                                value = undefined == value ? "" : value,
                                title = value,
                                className = column.class||"";
                            if(!!column.firstHide) {
                                className += " hide-td-first";
                            }
                            if(!!column.secondHide) {
                                className += " hide-td-second";
                            }
                            if(!!column.thirdHide) {
                                className += " hide-td-third";
                            }

                            if(!!column.lineHiddenFn && column.lineHiddenFn()) {
                                className += " hide-td-third";
                            }

                            if (column.content) {
                                value = column.content;
                                title = "";
                            } else if (column.renderer) {
                                value = column.renderer(idx, idx2, item, value) || "";
                                title = "";
                            } else if ('date' == column.type && undefined != value) {
                                var fmt = column.dateFormat || 'YYYY-MM-DD HH:mm:ss';
                                value = util.dateFormat2(value, fmt);
                                title = value;
                            }
                            rowHtml = rowHtml + '<td ' + style + ' title="' + title + '" class="' + className + '" ><span data-toggle="popover" data-content="' + title + '">' + value + '</span></td>';

                            $(rowHtml).appendTo(linetr);
                        });
                    });

                    $('[data-toggle="popover"]').popover({
                        trigger: "hover",
                        placement: "top"
                    });

                    if(thiz.opts.enableTree) {
                        treeTableTool.destroy(thiz.table);
                        treeTableTool.init(thiz.table, thiz.opts.treeConfig);
                        //treeTableTool.expandAll(thiz.table);
                    }
                },
                /**
                 * 事件绑定
                 */
                eventBinding: function () {
                    var thiz = this;

                    if (!thiz.multiSelect) {// 单选
                        $(".header-checkbox", thiz.table).attr("disabled", true);
                    } else {// 多选
                        // 全选/取消全选
                        $(".header-checkbox", thiz.table).on("click", function (e) {
                            var checked = $(this).get(0).checked;
                            $(".line-checkbox", thiz.tbody).each(function (idx, lineCheckbox) {
                                var disabled = $(lineCheckbox).attr("disabled");
                                if(!disabled) {
                                    var tr = $(lineCheckbox).parent().parent().parent().parent().parent();
                                    $(lineCheckbox).get(0).checked = checked;
                                    if (checked) {
                                        $(tr).addClass("highlight");
                                        $(lineCheckbox).parents(".checker").find("span").addClass("checked");
                                    } else {
                                        $(tr).removeClass("highlight");
                                        $(lineCheckbox).parents(".checker").find("span").removeClass("checked");
                                    }
                                }
                            });
                            if (thiz.opts.selectAllFn) {
                                thiz.opts.selectAllFn(thiz.dataList, checked);
                            }
                        });
                    }

                    $(thiz.tbody).on("click", "tr", function (e) {
                        var tr = $(this),
                            lineCheckbox = $(tr).find(".line-checkbox"),
                            target = e.target,
                            checked = !lineCheckbox.get(0).checked;
                        if ($(target).hasClass("line-checkbox")) {
                            checked = $(target).get(0).checked;
                        }
                        var checker = $(lineCheckbox).parents(".checker"),
                            isChecker = checker.length > 0;

                        if (!$(tr).hasClass('highlight')) {
                            if (!thiz.multiSelect) {
                                $(tr).addClass('highlight').siblings().removeClass('highlight');

                                $("tr .line-checkbox", thiz.tbody).each(function (idx, ele) {
                                    ele.checked = false;
                                    $(ele).parents(".checker").find("span").removeClass("checked");
                                });
                            } else {
                                $(tr).addClass("highlight");
                            }
                            if(isChecker) {
                                $("span", checker).addClass("checked");
                            }
                        } else {
                            $(tr).removeClass("highlight");
                            if(isChecker) {
                                $("span", checker).removeClass("checked");
                            }
                        }
                        var disabled = lineCheckbox.attr("disabled");
                        if(!disabled) {
                            lineCheckbox.get(0).checked = checked;
                        }
                        if (thiz.opts.rowClick) {
                            var rowIndex = $(tr).get(0).rowIndex - 1;
                            thiz.opts.rowClick(rowIndex, thiz.dataList[rowIndex], checked, $(tr), thiz);
                        }
                    });
                    $(thiz.tbody).on("dblclick", "tr", function (e) {
                        var tr = $(this);
                        if (thiz.opts.rowDblclick) {
                            var rowIndex = $(tr).get(0).rowIndex - 1;
                            thiz.opts.rowDblclick(rowIndex, thiz.dataList[rowIndex], tr, thiz);
                        }
                    });

                    if (thiz.opts.classEvents && $.isArray(thiz.opts.classEvents)) {
                        $(thiz.opts.classEvents).each(function (idx, event) {
                            if (event.className && event.handler && $.isFunction(event.handler)) {
                                $(thiz.tbody).on("click", "." + event.className, function (e) {
                                    var tr = $(this).parents("TR"),
                                        rowIndex = tr[0].rowIndex;
                                    event.handler(rowIndex, thiz.dataList[rowIndex - 1], $(this), tr, thiz, e);
                                    e.stopPropagation();
                                });
                            }
                        });
                    }
                },
                /**
                 * 判断是否有按钮权限
                 * @param button
                 * @returns {boolean}
                 */
                hasRight: function (button) {
                    var userRights = eval($("._u_f_").val())||[],
                        hasRight = false;
                    if(button.rightCode) {
                        $(userRights).each(function () {
                            if(this.rightCode == button.rightCode && !this.disabled) {
                                hasRight = true;
                                return false;
                            }
                        });
                    } else {
                        hasRight = true;
                    }
                    return hasRight;
                },
                /**
                 * 判断是否有按钮权限
                 * @param button
                 * @returns {boolean}
                 */
                hasClassRight: function () {
                    var thiz = this;
                    if (thiz.opts.classEvents && $.isArray(thiz.opts.classEvents)) {
                        $(thiz.opts.classEvents).each(function (idx, event) {
                            if(!thiz.hasRight(event)) {
                                $("." + event.className, thiz.tbody).addClass("hidden");
                            }
                        });
                    }
                },
                /**
                 * 渲染分页信息
                 * @param pageData
                 */
                renderPageInfo: function (pageData) {
                    var currentPage = pageData.currentPage,
                        pageSize = pageData.pageSize,
                        totalCount = pageData.totalItem,
                        orderBy = pageData.orderBy;

                    var thiz = this,
                        start = (currentPage - 1) * pageSize + 1,
                        end = currentPage * pageSize,
                        end = end>totalCount?totalCount:end,
                        totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : Math.floor(totalCount / pageSize) + 1,
                        prePage = currentPage <= 1 ? 0 : (currentPage - 1),
                        nextPage = currentPage >= totalPage ? totalPage : (currentPage + 1),
                        prevDisabled = currentPage == 1 ? "disabled" : "",
                        nextDisabled = currentPage >= totalPage ? "disabled" : "";

                    thiz.currentPage = currentPage;
                    thiz.totalCount = totalCount;
                    thiz.pageSize = pageSize;
                    thiz.orderBy = orderBy;

                    var pageInfo = '<div class="col-sm-9" style="padding-left: 0px;"> ' +
                        '<div class="dataTables_info" style="padding-top: 4px; white-space: nowrap;">' +
                        '显示 ' + start + ' - ' + end + ' 条记录 共 ' + totalCount + ' 条 ' +
                        '每页<input type="text" class="page-size" value="' + pageSize + '" style="">条 ' +
                        '共 ' + totalPage + " 页 " +
                        '跳转至第<input type="text" class="to-pageNo" style="">页 ' +
                        '<input type="button" class="btn btn-info btn-to-pageNo" value="确定" style="padding: 4px; ">' +
                        '<input type="hidden" class="data-totalCount" value="' + totalCount + '">' +
                        '</div> </div> ' +
                        '<div class="col-sm-3" style="padding-right: 0px;"> <div class="pull-right"> ' +
                        '<ul class="pagination"> ' +
                        '<li class="prev ' + prevDisabled + '"> <a href="javascript:void(0);" class="prev-page" data-no="' + prePage + '"><i class="fa fa-angle-left"></i></a> </li> ' +
                        '<li class="active"> <a href="#">' + currentPage + '</a> </li> ' +
                        '<li class="next ' + nextDisabled + '"> <a href="javascript:void(0);" class="next-page" data-no="' + nextPage + '"><i class="fa fa-angle-right"></i></a> </li> ' +
                        '</ul> </div> </div>';

                    thiz.pageinfo.html("");

                    $(thiz.pageinfo).css("display", !thiz.opts.showPagebar?"none":"");

                    var pageDataInfo = $(pageInfo).appendTo(thiz.pageinfo);

                    $(pageDataInfo).on("click", ".prev-page", function () {
                        if ($(this).parent().hasClass("disabled")) return;
                        var pageNo = $(this).data("no"),
                            pageSize = $(pageDataInfo).find(".page-size").val();
                        thiz.loadPageData(pageNo, pageSize);
                    });
                    $(pageDataInfo).on("click", ".next-page", function () {
                        if ($(this).parent().hasClass("disabled")) return;
                        var pageNo = $(this).data("no"),
                            pageSize = $(pageDataInfo).find(".page-size").val();
                        thiz.loadPageData(pageNo, pageSize);
                    });

                    $(pageDataInfo).on("change", ".page-size", function () {
                        var val = $(this).val();
                        if (!val || !math.isInteger(val) || math.floatSub(val, 0) <= 0 || math.floatSub(val, 10000) > 0) {
                            $(this).val("");
                        }
                    });
                    $(pageDataInfo).on("blur", ".page-size", function () {
                        thiz.loadPageData(1, $(this).val());
                    });
                    $(pageDataInfo).on("change", ".to-pageNo", function () {
                        var val = $(this).val();
                        if (!val || !math.isInteger(val) || math.floatSub(val, 0) <= 0 || math.floatSub(val, totalPage) > 0) {
                            $(this).val("");
                        }
                    });

                    $(pageDataInfo).on("click", ".btn-to-pageNo", function () {
                        var pageNo = $(pageDataInfo).find(".to-pageNo").val(),
                            pageSize = $(pageDataInfo).find(".page-size").val();
                        thiz.loadPageData(pageNo, pageSize);
                    });
                },

                /**
                 * 取得选择的数据行
                 * @returns {*}
                 */
                getSelected: function () {
                    var result = [],
                        thiz = this;
                    $(".line-checkbox", thiz.tbody).each(function (idx, checkBox) {
                        if ($(checkBox)[0].checked) {
                            var tr = $(this).parents("TR"),
                                rowIndex = tr[0].rowIndex;
                            result.push(thiz.dataList[rowIndex - 1]);
                        }
                    });
                    if (!!thiz.multiSelect) {
                        return result;
                    } else if (result.length > 0) {
                        return result[0];
                    }
                    return null;
                },
                /**
                 * 设置查询条件
                 * @param pageData
                 */
                renderSearchCondition: function (pageData) {
                    if (!!this.firstLoad) {
                        this.firstLoad = false;
                        if (this.opts.initSearch) {
                            this.opts.initSearch(pageData, this.opts.module);
                        }
                    }
                },
                /**
                 * 查询的总数
                 * @returns {*}
                 */
                getTotalCount: function () {
                    return this.totalCount;
                },

                enableButton: function (idx, enable) {
                    enable = !!enable;
                    if (enable) {
                        this.buttons[idx].removeClass("disabled");
                    } else {
                        this.buttons[idx].addClass("disabled");
                    }
                },
                /**
                 * 扩展数据
                 * @returns {*|itemListTool.extData|list.extData|{}}
                 */
                getExtData: function () {
                    return this.opts.extData||{};
                },

                getDataCount: function () {
                    return this.dataList.length||0;
                }
            };
            return listTool.init(config);
        }
    };
});