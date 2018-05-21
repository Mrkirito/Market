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

    'css!global/js/lib/plugins/datatables/media/css/dataTables.bootstrap.css',
    'css!global/js/lib/plugins/datatables/extensions/FixedColumns/css/fixedColumns.bootstrap.css',
    'css!global/js/lib/plugins/datatables/extensions/Select/css/select.bootstrap.css',

    'plugins/datatables/media/js/jquery.dataTables',
    'plugins/datatables/media/js/dataTables.bootstrap',
    'plugins/datatables/extensions/FixedColumns/js/dataTables.fixedColumns',
    'plugins/datatables/extensions/Select/js/dataTables.select',
    'plugins/jquery.blockUI'
], function ($, math, util) {

    var defaults = {
            selector: 'body',
            title: "表头信息",
            tbars: [],
            columns: [],
            data: [],
            remote: false,
            url: "",
            multiSelect: true,
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
            toolbarSelector: '.top-toolbar',
            extData: {},// 扩展数据
            showCheckbox: false, // 是否显示复选框 优先级更高
            lineCheckboxShowFn: function () {
                return true;
            },
            lineCheckboxDisabledFn: function () {
                return false;
            },
            treeConfig: {},
            idName: null,
            parentName: null,
            tableId: null,

            height: 300,
            minWidth: 600,
            panelClass: null,
            showPagebar: true, // 是否显示分页工具条 默认true
            hideColumnBorder: false,
            showPanel: true,
            showTotals: false
        },
        tableHtml = [
            '<div class="table-responsive" style="overflow-x: hidden">',
            '<table   class=" table table-striped table-bordered" cellspacing="0" width="100%" >',
            '<thead></thead><tbody></tbody></table>',
            '<div class="row page-data-info x-list-page-data-info" style="margin-left: 0px; margin-right: 0px;"></div>',
            '</div>'
        ],
    //显示总条数 float:right;
        listTotalsHtmls = [
            '<div class="row"><div class="col-sm-12"><div class="panel">',
            '<div class="panel-heading">',
            '<div class="panel-control"></div>',
            '<h3 class="panel-title">{title}</h3>',
            '<div class="row" style="width:96%;text-align:right;position:absolute; left:20%; top:0;line-height:50px;"><span id="textInfoId" style="font-weight:900;float:left;"></span>',
            '<span id="totalId"style="float:left;"></span></div>',
            '</div>',
            '<div class="panel-body" style="padding: 0">',
            '</div>',
            '</div></div></div>'
        ],
        listHtmls = [
            '<div class="row">',
            '</div>'],
        listWidthPanelHtmls = [
            '<div class="row"><div class="col-sm-12"><div class="panel">',
            '<div class="panel-heading">',
            '<div class="panel-control"></div>',
            '<h3 class="panel-title">{title}</h3>',
            '</div>',
            '<div class="panel-body" style="padding: 0">',
            '</div>',
            '</div></div></div>'
        ],
        abtnHtmls = [
            '<a href="$btnUrl$" class="btn btn-default tool-btn">',
            '<i class="$icon$"></i> ',
            '$btnName$',
            '</a>'],
        btnHtmls = [
            '<a  class="btn btn-default tool-btn">',
            '<i class="$icon$"></i> ',
            '$btnName$',
            '</a>'],
        liabtnHtmls = [
            '<a href="$btnUrl$" >',
            '<i class="$icon$"></i> ',
            '$btnName$',
            '</a>'],
        libtnHtmls = [
            '<a >',
            '<i class="$icon$"></i> ',
            '$btnName$',
            '</a>'],
        groupsHtml = [
            '<div class="btn-group">',
            '<button data-toggle="dropdown" class="dropdown-toggle btn btn-info"><i class="{icon}"></i> ' +
            '{name}</button>',
            '<ul class="dropdown-menu dropdown-menu-right"></ul>',
            '</div>'],
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
                    this.pageSize = this.opts.pageSize == undefined ? 10 : parseInt(this.opts.pageSize);
                    this.orderBy = this.opts.orderBy == undefined ? null : this.opts.orderBy;
                    this.firstLoad = true;
                    this.multiSelect = this.opts.multiSelect == undefined ? true : !!this.opts.multiSelect;
                    this.buttons = [];
                    this.totalCount = 0;

                    this.advanceTable = null;
                    this.orders = [];

                    this.enableSort = false;

                    this.searching = false;

                    if (!this.opts.showCheckbox) {
                        this.multiSelect = false;
                    }
                    if (!this.opts.tableId) {
                        this.opts.tableId = this._generateTableId();
                    }

                    this.createPanel();

                    this.createToolbar();

                    this.createTable();
                    // 加载数据
                    this._loadPageData();

                    return this;
                },

                createPanel: function () {
                    var thiz = this,
                        selector = typeof thiz.opts.selector == "string" ? $(thiz.opts.selector) : thiz.opts.selector,
//                        listHtml = thiz.opts.showPanel ? listWidthPanelHtmls.join("") : listHtmls.join(""),
                        listHtml = thiz.opts.showTotals ? listTotalsHtmls.join("") : thiz.opts.showPanel ? listWidthPanelHtmls.join("") : listHtmls.join(""),
                        listHtml = listHtml.replace("{title}", thiz.opts.title),
                        list = $(listHtml).appendTo(selector);
                    thiz.panel = $(list).find('.panel');
                    thiz.panelControl = $(list).find('.panel-control');
                    thiz.panelBody = thiz.opts.showPanel ? $(list).find('.panel-body') : list;
                    if (!thiz.panelControl) {
                        this.toolbar = $(this.opts.toolbarSelector);
                    } else {
                        this.toolbar = thiz.panelControl;
                    }
                },

                /**
                 * 创建工具栏，按钮区域
                 */
                createToolbar: function () {
                    var thiz = this;
                    thiz.buttons = [];
                    $(thiz.opts.tbars).each(function (idx, button) {
                        if (!button.hide) {
                            if (button.type == 'group') {
                                var btns = [],
                                    html = groupsHtml.join(""),
                                    html = html.replace("{icon}", button.icon).replace("{name}", button.name),
                                    groups = $(html),
                                    parent = groups.find("ul");
                                $(button.btns || []).each(function () {
                                    var btn = thiz.createButton(this, parent, true);
                                    if (btn) {
                                        btns.push(btn);
                                    }
                                });

                                if (btns.length > 0) {
                                    $(groups).appendTo(thiz.toolbar);
                                }
                            } else {
                                thiz.createButton(button, thiz.toolbar);
                            }
                        }
                    });

                    // 刷新按钮
                    var freshBtn = {
                        icon: "fa fa-rotate-right fa-fw", name: "刷新", handler: function () {
                            thiz.reloadPageData();
                        }
                    };
                    thiz.createButton(freshBtn, thiz.toolbar);
                },

                createButton: function (button, parent, group) {
                    var thiz = this,
                        group = !!group,
                        btn = null;
                    if (thiz.hasRight(button)) {
                        if (button.url) {
                            var abtnHtml = group ? liabtnHtmls.join("") : abtnHtmls.join(""),
                                abtnHtml = abtnHtml.replace("$icon$", button.icon || "").replace("$btnName$", button.name || "").replace("$btnUrl$", button.url);
                            if (group) {
                                var li = $("<li></li>").appendTo(parent);
                                btn = $(abtnHtml).appendTo(li);
                            } else {
                                btn = $(abtnHtml).appendTo(parent);
                            }
                            thiz.buttons.push(btn);
                        } else {
                            var btnHtml = group ? libtnHtmls.join("") : btnHtmls.join(""),
                                btnHtml = btnHtml.replace("$icon$", button.icon || "").replace("$btnName$", button.name || "");
                            if (group) {
                                var li = $("<li></li>").appendTo(parent);
                                btn = $(btnHtml).appendTo(li);
                            } else {
                                btn = $(btnHtml).appendTo(parent);
                            }
                            $(btn).on("click", function (e) {
                                if (button.handler) {
                                    button.handler(e, this, thiz);
                                }
                            });
                            thiz.buttons.push(btn);
                        }
                    }
                    return btn;
                },

                /**
                 * 创建表
                 */
                createTable: function () {
                    var thiz = this;

                    // 清空表数据
                    $(thiz.panelBody).html("");
                    $(tableHtml.join("")).appendTo(thiz.panelBody);

                    // 创建空表
                    thiz.table = $(thiz.panelBody).find('table');
                    thiz.thead = $(thiz.table).find('thead');
                    thiz.tbody = $(thiz.table).find('tbody');
                    thiz.toolbar = $(thiz.opts.toolbarSelector);
                    thiz.pageinfo = $(thiz.panelBody).find(".x-list-page-data-info");

                    // 隐藏border
                    if (!!thiz.opts.hideColumnBorder) {
//                        thiz.table.removeClass("table-bordered");
                        thiz.tbody.css({"border-bottom": "1px solid #ddd"});
                    }

                    if (thiz.opts.tableId) {
                        thiz.table.attr("id", thiz.opts.tableId);
                    }

                    // 表格样式
                    if (thiz.opts.panelClass) {
                        thiz.panel.addClass(thiz.opts.panelClass)
                    } else {
                        thiz.panel.addClass("panel-primary")
                    }


                    // 创建表头
                    thiz.createHeader();
                    // 绑定时间
                    thiz.eventBinding();
                },

                /**
                 * 创建表头
                 */
                createHeader: function () {
                    var thiz = this,
                        headtr = $("<tr></tr>").appendTo(thiz.thead),
                        headCheck = $(headCheckHtml).appendTo(headtr);

                    if (!thiz.opts.multiSelect) headCheck.find(".header-checkbox").attr("disabled", !thiz.multiSelect);


                    // 隐藏复选框
                    $(headCheck).css("display", !thiz.opts.showCheckbox ? "none" : "");

                    $(thiz.opts.columns).each(function (idx, column) {
                        column = $.extend({}, _defaultColumn, column);
                        var defaultStyle = "border-color: #DDD; font-weight: bold; vertical-align: middle; " +
                                "white-space: nowrap; overflow:hidden; ",
                            style = defaultStyle + (column.width ? "width: " + column.width + "px!important;" : "") + (column.style || ""),
                            className = column.class || "";

                        if (!!column.lineHiddenFn && column.lineHiddenFn()) {
                            className += " hide-td-third";
                        }
                        var thHtml = '<th class="' + className + '" style="' + style + '"' + '>' + column.name + '</th>',
                            th = $(thHtml).appendTo(headtr);
                    });
                },

                reloadPageData: function (pageNo, pageSize) {
                    this.createTable();
                    this._loadPageData(pageNo, pageSize);
                },

                /**
                 * 生成表数据
                 */
                _loadPageData: function (pageNo, pageSize) {
                    var thiz = this,
                        data = thiz.opts.data || [];

                    if (thiz.searching) {
                        return;
                    }
                    thiz.searching = true;

                    pageNo = pageNo || thiz.currentPage;
                    pageSize = pageSize || thiz.pageSize;

                    thiz.tbody.html("");
                    thiz.dataList = [];

                    thiz.enableSort = false;

                    this._blockUI();

                    thiz.addLoadingText();


                    if (thiz.firstLoad) {
                        $(thiz.opts.columns).each(function (idx, column) {
                            if (column.defaultSort) {
                                thiz.setColumnOrder({
                                    col: idx + 1,
                                    dir: column.defaultSort
                                });
                                return false;
                            }
                        });
                    }

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
                        params['orderBy'] = thiz.getOrderBy();
                        params['firstLoad'] = thiz.firstLoad;


                        util.ajax({
                            url: thiz.opts.url,
                            params: params,
                            success: function (resp) {
                                if (resp.success == true) {
                                    var dataList = resp.model || [],
                                        pageData = resp.query || {};
                                    thiz.renderSearchCondition(pageData);
                                    //console.info(pageData)
                                    thiz.addDataLines(dataList, pageData.startRow || 1);
                                    thiz.renderPageInfo(pageData);
                                    thiz.hasClassRight();
                                } else {
                                    thiz.tbody.html("");
                                    thiz.addTableMessage("系统超时，请稍后重试");
                                }
                                thiz._unblockUI();
                                thiz.searching = false;
                                //显示总条数
                                if (thiz.opts.showTotals) {
                                    $("#textInfoId").html(resp.showTextInfo);
                                    $("#totalId").html(resp.showTotal);
                                }
                            },
                            failure: function () {
                                thiz._unblockUI();
                                thiz.searching = false;
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
                        colCount = this.opts.showCheckbox ? colCount + 1 : colCount,
                        linetr = $("<tr></tr>").appendTo(this.tbody),
                        rowHtml = '<td colspan="' + colCount + '" style="text-align: center;">' + message + '</td>';
                    $(rowHtml).appendTo(linetr);
                },

                /**
                 * 插入数据行
                 * @param dataList
                 */
                addDataLines: function (dataList, startRow) {
                    var thiz = this;
                    thiz.tbody.html("");
                    thiz.dataList = [];

                    startRow = startRow || 1;

                    if (dataList.length == 0) {
                        //thiz.addNoDataText();
                    }

                    if (thiz.advanceTable) {
                        thiz.advanceTable.destroy();
                    }


                    $(dataList).each(function (idx, item) {
                        if (!item) return true;
                        thiz.dataList.push(item);

                        var lineHtml = "<tr data-row='" + idx + "'></tr>";

                        var linetr = $(lineHtml).appendTo(thiz.tbody),
                            lineCheckEle = $(lineCheckHtml).appendTo(linetr),
                            checkedEle = lineCheckEle.find(".line-checkbox"),
                            rowSelected = thiz.opts.rowSelected(item);

                        // 隐藏复选框
                        $(lineCheckEle).css("display", !thiz.opts.showCheckbox ? "none" : "");

                        var showchb = true;
                        if (thiz.opts.lineCheckboxShowFn) {
                            showchb = thiz.opts.lineCheckboxShowFn(item);
                            showchb = undefined == showchb ? true : showchb;
                        }
                        if (thiz.opts.lineCheckboxDisabledFn) {
                            diabledchb = thiz.opts.lineCheckboxDisabledFn(item);
                            diabledchb = undefined == diabledchb ? false : diabledchb;
                        }
                        $(checkedEle).css("display", !showchb ? "none" : "");
                        $(checkedEle).attr("disabled", diabledchb);

                        if (!!rowSelected) {
                            $(linetr).addClass('highlight');
                        }

                        $(thiz.opts.columns).each(function (idx2, column) {
                            var rowHtml = '',
                                style = ' style="' + (column.width ? "width: " + column.width + "px;" : "") + '" ',
                                //value = item[column.fieldName],
                                value = eval("item."+column.fieldName),
                                value = undefined == value ? "" : value,
                                title = value,
                                className = column.class || "";

                            if (!!column.lineHiddenFn && column.lineHiddenFn()) {
                                className += " hide-td-third";
                            }

                            if (column.content) {
                                value = column.content;
                                title = "";
                            } else if (column.renderer) {
                                value = column.renderer(idx, idx2, item, value, startRow + idx) || "";
                                title = "";
                            } else if ('date' == column.type && undefined != value) {
                                var fmt = column.dateFormat || 'YYYY-MM-DD HH:mm:ss';
                                value = util.dateFormat2(value, fmt);
                                title = value;
                            }
                            rowHtml = rowHtml + '<td class="' + className + '" ' + style + ' >' + value + '</td>';

                            $(rowHtml).appendTo(linetr);
                        });

                    });

                    thiz.renderAdvance();

                    thiz.enableSort = true;
                },

                renderAdvance: function () {
                    var thiz = this,
                        ordering = false,
                        columnDefs = [{
                            bSortable: false,
                            orderable: false,
                            targets: 0
                        }],
                        leftColumns = 0,
                        rightColumns = 0;// 排序

                    // 排序处理
                    $(thiz.opts.columns).each(function (idx, column) {
                        column = $.extend({}, _defaultColumn, column);
                        var cdef = {targets: idx + 1};
                        if (column.width) {
                            cdef.width = column.width + "px";
                        }
                        if (column.sort && column.sortName) {
                            cdef.orderable = true;
                            ordering = true;
                        } else {
                            cdef.orderable = false;
                        }
                        if (column.orderDataType) {
                            cdef.orderDataType = column.orderDataType;
                        }
                        columnDefs.push(cdef);

                        if (!!column.leftFixed) {
                            //leftColumns += 1;
                        }
                        if (!!column.rightFixed) {
                            rightColumns += 1;
                        }
                    });

                    /*$(columnDefs).each(function () {

                     console.info("columnDefs", this)
                     })*/

                    thiz.advanceTable = $("#" + thiz.opts.tableId)
                        .on('order.dt', function (e, data, orders, columns) {
                            //console.info("columns", columns)
                            thiz.setColumnOrder(orders);

                            if (!!thiz.enableSort) {
                                thiz.reloadPageData();
                            }
                        })
                        .DataTable({
                            scrollY: thiz.opts.height || 500,
                            scrollX: true,
                            scrollCollapse: true,
                            paging: false,
                            searching: false,
                            paginate: false,
                            processing: true,
                            ordering: ordering,
                            fixedColumns: {
                                leftColumns: leftColumns,
                                rightColumns: rightColumns
                            },
                            /*columnDefs: [
                             {width: '200px', targets: 8},
                             ],*/
                            columnDefs: columnDefs,
                            language: {
                                info: "", //"显示从 _START_ - _END_ 条共 _TOTAL_ 条",
                                sInfoEmpty: "",
                                sEmptyTable: "暂无数据"
                            },
                            select: {
                                style: 'multi'
                            },
                            order: thiz.orders
                        });

                    var adv_id = "#" + thiz.opts.tableId + "_wrapper";
                    if (thiz.dataList.length == 0 && thiz.opts.hideColumnBorder) {
                        $(adv_id).find(".DTFC_RightHeadWrapper").addClass("null");
                    } else {
                        $(adv_id).find(".DTFC_RightHeadWrapper").removeClass("null");
                    }
                },

                /**
                 * 设置排序
                 * @param orders
                 */
                setColumnOrder: function (orders) {
                    var thiz = this;
                    orders = orders || [];
                    thiz.orders = [];
                    $(orders).each(function () {
                        thiz.orders.push([this.col, this.dir]);
                    });
                },
                /**
                 * 获取排序
                 * @returns {string}
                 */
                getOrderBy: function () {
                    var orderBy = "",
                        thiz = this;
                    $(thiz.orders).each(function (idx) {
                        var column = thiz.opts.columns[this[0] - 1];
                        //console.info("column", column)
                        if (column) {
                            if (idx > 0) {
                                orderBy += ",";
                            }
                            orderBy += column.sortName + " " + this[1];
                        }
                    });
                    if('' == orderBy) orderBy = thiz.opts.orderBy;
                    return orderBy;
                },
                /**
                 * 事件绑定
                 */
                eventBinding: function () {
                    var thiz = this;

                    $(thiz.tbody).on("click", ".line-checkbox", function (e) {
                        var checked = this.checked,
                            disabled = $(this).attr("disabled"),
                            tr = $(this).parents("tr");

                        if (!disabled) {
                            if (checked) {
                                if (!thiz.multiSelect) {
                                    $("tr .line-checkbox", thiz.tbody).each(function (idx, ele) {
                                        ele.checked = false;
                                        $(ele).parents("tr").removeClass("selected");
                                    });
                                }
                                this.checked = checked;
                                tr.addClass("selected");
                            } else {
                                $(tr).removeClass("selected");
                            }

                        } else {
                            $(tr).removeClass("selected");
                        }

                        e.stopPropagation();
                    });


                    $(thiz.tbody).on("click", "tr", function (e) {
                        //if(!this.dataList || this.dataList.length == 0) return ;

                        var tr = $(this),
                            lineCheckbox = $(tr).find(".line-checkbox"),
                            checked = !lineCheckbox[0].checked,
                            rowIdx = tr.data("row");

                        var disabled = lineCheckbox.attr("disabled");
                        if (!disabled) {
                            if (checked) {
                                if (!thiz.multiSelect) {
                                    $("tr .line-checkbox", thiz.tbody).each(function (idx, ele) {
                                        ele.checked = false;
                                        $(ele).parents("tr").removeClass("selected");
                                    });
                                }

                                tr.addClass("selected");
                            } else {
                                $(tr).removeClass("selected");
                            }

                            lineCheckbox[0].checked = checked;
                        } else {
                            $(tr).removeClass("selected");
                        }

                        if (thiz.opts.rowClick) {
                            thiz.opts.rowClick(rowIdx, thiz.dataList[rowIdx], checked, $(tr), thiz);
                        }
                    });


                    $(thiz.tbody).on("dblclick", "tr", function (e) {
                        //if(!this.dataList || this.dataList.length == 0) return ;

                        var tr = $(this);
                        if (thiz.opts.rowDblclick) {
                            var rowIndex = $(tr).data("row");
                            thiz.opts.rowDblclick(rowIndex, thiz.dataList[rowIndex], tr, thiz);
                        }
                    });

                    if (thiz.opts.classEvents && $.isArray(thiz.opts.classEvents)) {
                        $(thiz.opts.classEvents).each(function (idx, event) {
                            if (event.className && event.handler && $.isFunction(event.handler)) {
                                $(thiz.tbody).on("click", "." + event.className, function (e) {
                                    var tr = $(this).parents("TR"),
                                        rowIndex = tr.data("row");
                                    event.handler(rowIndex, thiz.dataList[rowIndex], $(this), tr, e);
                                    e.stopPropagation();
                                });
                            }
                        });
                    }


                    $(".header-checkbox", thiz.thead).on("click", function (e) {
                        var checked = $(this)[0].checked;
                        $("tr .line-checkbox", thiz.tbody).each(function (idx, ele) {
                            var disabled = $(ele).attr("disabled");
                            if (!disabled) {
                                ele.checked = checked;
                                if (checked) {
                                    $(ele).parents("tr").addClass("selected");
                                } else {
                                    $(ele).parents("tr").removeClass("selected");
                                }
                            }
                        });
                    });
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
                 * 渲染分页信息
                 * @param pageData
                 */
                renderPageInfo: function (pageData) {
                    var currentPage = pageData.currentPage,
                        pageSize = pageData.pageSize,
                        totalCount = pageData.totalItem;

                    var thiz = this,
                        start = (currentPage - 1) * pageSize + 1,
                        end = currentPage * pageSize,
                        end = end > totalCount ? totalCount : end,
                        totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : Math.floor(totalCount / pageSize) + 1,
                        prePage = currentPage <= 1 ? 0 : (currentPage - 1),
                        nextPage = currentPage >= totalPage ? totalPage : (currentPage + 1),
                        prevDisabled = currentPage == 1 ? "disabled" : "",
                        nextDisabled = currentPage >= totalPage ? "disabled" : "";

                    thiz.currentPage = currentPage;
                    thiz.totalCount = totalCount;
                    thiz.pageSize = pageSize;


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
                        '<ul class="pagination" style="margin: 0;"> ' +
                        '<li class="prev ' + prevDisabled + '"> <a href="javascript:void(0);" class="prev-page" data-no="' + prePage + '"><i class="fa fa-angle-left"></i></a> </li> ' +
                        '<li class="active"> <a href="#">' + currentPage + '</a> </li> ' +
                        '<li class="next ' + nextDisabled + '"> <a href="javascript:void(0);" class="next-page" data-no="' + nextPage + '"><i class="fa fa-angle-right"></i></a> </li> ' +
                        '</ul> </div> </div>';

                    thiz.pageinfo.html("");

                    $(thiz.pageinfo).css("display", !thiz.opts.showPagebar ? "none" : "");

                    var pageDataInfo = $(pageInfo).appendTo(thiz.pageinfo);

                    $(pageDataInfo).on("click", ".prev-page", function () {
                        if ($(this).parent().hasClass("disabled")) return;
                        var pageNo = $(this).data("no"),
                            pageSize = $(pageDataInfo).find(".page-size").val();
                        thiz.reloadPageData(pageNo, pageSize);
                    });
                    $(pageDataInfo).on("click", ".next-page", function () {
                        if ($(this).parent().hasClass("disabled")) return;
                        var pageNo = $(this).data("no"),
                            pageSize = $(pageDataInfo).find(".page-size").val();
                        thiz.reloadPageData(pageNo, pageSize);
                    });

                    $(pageDataInfo).on("change", ".page-size", function () {
                        var val = $(this).val();
                        if (!val || !math.isInteger(val) || math.floatSub(val, 0) <= 0 || math.floatSub(val, 10000) > 0) {
                            $(this).val("");
                        }
                    });
                    $(pageDataInfo).on("blur", ".page-size", function () {
                        thiz.reloadPageData(1, $(this).val());
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
                        thiz.reloadPageData(pageNo, pageSize);
                    });
                },

                getSelected: function () {
                    var thiz = this,
                        dataList = [];
                    if (thiz.advanceTable) {
                        var indexes = thiz.advanceTable.rows('.selected').indexes();
                        $(indexes).each(function () {
                            dataList.push(thiz.dataList[this]);
                        })
                    }
                    return dataList;
                },

                /**
                 * 查询的总数
                 * @returns {*}
                 */
                getTotalCount: function () {
                    return this.totalCount;
                },

                /**
                 * 判断是否有按钮权限
                 * @param button
                 * @returns {boolean}
                 */
                hasRight: function (button) {
                    var userRights = eval($("._u_f_").val()) || [],
                        hasRight = false;
                    if (button.rightCode) {
                        $(userRights).each(function () {
                            if (this.rightCode == button.rightCode && !this.disabled) {
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
                            var parant = '#' + thiz.opts.tableId + "_wrapper" + " tbody";
                            if (!thiz.hasRight(event)) {
                                $("." + event.className, parant).addClass("hidden");
                            }
                        });
                    }
                },

                /**
                 * 生成tableId
                 * @returns {string}
                 * @private
                 */
                _generateTableId: function () {
                    return "adv_table_" + new Date().getTime();
                },

                _blockUI: function () {
                    $(this.panel).block({
                        message: "数据加载中...",
                        //fadeIn: 1000,
                        //fadeOut: 1000,
                        css: {
                            border: '0px',
                            padding: '10px',
                            'background-color': 'transparent',
                            'color': '#fff',
                            'font-size': '15px',
                            'font-weight': 'bolder',
                        }
                    });
                },
                _unblockUI: function () {
                    $(this.panel).unblock();
                }
            };
            return listTool.init(config);
        }
    };
});