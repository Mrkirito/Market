define(["jquery", 'common/math', "util",
        'app/common/selectTool'],
    function ($, math, util, selectTool) {

        var defaults = {
                selector: 'body',
                listName: "标题",
                listHeadName: "表头信息",
                tbars: [],
                columns: [],
                data: [],
                remote: false,
                url: "",
                multiSelect: true,// 是否多选
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
                headClassEvents: [],
                /**
                 * 渲染查询条件
                 * @param queryData 后台返回的查询条件数据
                 */
                initSearch: function (queryData, module) {
                },
                module: null, // 引用listTool的模块对象, 回传给initSearch的参数
                hiddenToolbar: false,
                toolbarSelector: '.page-toolbar-buttons',
                extData: {},// 扩展数据
                hideColumnBorder: false,
                showPagebar: true, // 是否显示分页工具条 默认true
                showCheckbox: true // 是否显示复选框
            },
            listHtmls = ['<div class="row"><div class="col-xs-12">',
                '<div class="table-responsive">',
                '<table  class="table table-striped table-bordered table-hover dataTable " style="margin-top: 0px; margin-bottom: 0px; table-layout: fixed;">',
                '<thead></thead><tbody class="shop-data-list"></tbody></table>',
                '<div class="row page-data-info x-list-page-data-info" style="margin-left: 0px; margin-right: 0px;"></div>',
                '</div>',
                '</div>',
                '</div>'],
            abtnHtmls = ['<a href="$btnUrl$">',
                '<i class="$icon$"></i>',
                '$btnName$',
                '</a>'],
            btnHtmls = [
                '<a >',
                '<i class="$icon$"></i>',
                '$btnName$',
                '</a>'],
            headCheckHtml = '<th class="center" style="width: 50px;"><label><div class="checker"><span><input type="checkbox" class=" header-checkbox" /></span></div></label></th>',
            lineCheckHtml = '<td class="center"><label><div class="checker"><span><input type="checkbox" class=" line-checkbox" /></span></div></label></td>',

            _defaultColumn = {
                class: "",
                name: "未命名",
                fieldName: "blankName",
                content: null,  // html内容
                renderer: null, // function(){}）
                style: "", // 样式内容
                sort: false, // 是否排序 默认为否
                sortName: "", // 排序段名
                fieldType: "", // input, textarea, select, radio, checkbox, select2, date, datetime, daterange, year
                editor: function (cell, td, tr) {

                }
            },
            fieldClass = "td-field-editor";


        return {
            create: function (config) {
                var listTool = {
                    init: function (config) {
                        config = config || {};
                        this.opts = $.extend({}, defaults, config);
                        this.dataList = [];
                        this.deleteDataList = [];
                        this.currentPage = 1;
                        this.pageSize = 10;
                        this.orderBy = null;
                        this.firstLoad = true;
                        this.multiSelect = this.opts.multiSelect == undefined ? true : !!this.opts.multiSelect;
                        this.buttons = [];

                        this.listenerKey = 0;

                        if (!this.opts.showCheckbox) {
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
                        this.toolbar = $(this.opts.toolbarSelector);
                        this.pageinfo = $(list).find(".x-list-page-data-info");

                        if (!!this.opts.hiddenToolbar) {
                            this.toolbar.css("display", "none");
                            $(list).find(".header-h3").css("display", "none");
                        }

                        if (!!this.opts.hideColumnBorder) {
                            this.table.addClass("table-bordered2");
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
                            if (thiz.hasRight(button)) {
                                if (button.url) {
                                    var abtnHtml = abtnHtmls.join("").replace("$icon$", button.icon || "").replace("$btnName$", button.name || "").replace("$btnUrl$", button.url);
                                    var btn = $(abtnHtml).appendTo(thiz.toolbar);
                                    thiz.buttons.push(btn);
                                } else {
                                    var btnHtml = btnHtmls.join("").replace("$icon$", button.icon || "").replace("$btnName$", button.name || "");
                                    var btn = $(btnHtml).appendTo(thiz.toolbar);
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
                            headCheck = $(headCheckHtml).appendTo(headtr);
                        // 隐藏复选框
                        $(headCheck).css("display", !thiz.opts.showCheckbox ? "none" : "");

                        $(thiz.opts.columns).each(function (idx, column) {
                            column = $.extend({}, _defaultColumn, column);
                            var defaultStyle = "border-color: #DDD; font-weight: bold; vertical-align: middle; " +
                                    "white-space: nowrap;  overflow:hidden; ",
                                style = defaultStyle + (column.width ? "width: " + column.width + "px;" : "") + (column.style || "");
                            var thHtml = '<th style="' + style + '" class="' + column.class + '"' +
                                ' data-sortname="' + (column.sortName || "") + '">' + column.name + '</th>';
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

                        thiz.stopListener();

                        thiz.tbody.html("");
                        thiz.dataList = [];
                        thiz.deleteDataList = [];

                        thiz.addLoadingText();

                        if (data && data.length > 0) {
                            thiz.addDataLines(data);
                            thiz.renderPageInfo({
                                currentPage: pageNo,
                                pageSize: pageSize,
                                totalCount: data.length
                            });
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
                                    }
                                }
                            });
                        }
                    },

                    addLoadingText: function () {
                        var colCount = this.opts.columns.length,
                            colCount = this.opts.showCheckbox? colCount + 1 : colCount,
                            linetr = $("<tr></tr>").appendTo(this.tbody),
                            rowHtml = '<td colspan="' + colCount + '" style="text-align: center;">数据加载中。。。</td>';
                        $(rowHtml).appendTo(linetr);
                    },

                    addNoDataText: function () {
                        var colCount = this.opts.columns.length,
                            colCount = this.opts.showCheckbox? colCount + 1 : colCount,
                            linetr = $("<tr class='editor-list-empty-text'></tr>").appendTo(this.tbody),
                            rowHtml = '<td colspan="' + colCount + '" style="text-align: center;">暂无数据。</td>';
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
                        thiz.deleteDataList = [];

                        if(dataList.length == 0) {
                            thiz.addNoDataText();
                        }

                        $(dataList).each(function (idx, item) {
                            thiz.addRow(idx, item);
                        });

                        thiz.startListener();
                    },

                    addRow: function (idx, item) {
                        var thiz = this;

                        if($(".editor-list-empty-text", thiz.body).length > 0) {
                            $(".editor-list-empty-text", thiz.body).remove();
                        }


                        item = item || {isnew: true},
                            idx = undefined == idx ? thiz.dataList.length : idx;

                        thiz.dataList.push(item);

                        var linetr = $("<tr></tr>").appendTo(thiz.tbody),
                            lineCheckEle = $(lineCheckHtml).appendTo(linetr),
                            checkedEle = lineCheckEle.find(".line-checkbox"),
                            rowSelected = thiz.opts.rowSelected(item);

                        // 隐藏复选框
                        $(lineCheckEle).css("display", !thiz.opts.showCheckbox ? "none" : "");

                        if (!!rowSelected) {
                            $(checkedEle).attr("checked", rowSelected);
                            $(linetr).addClass('highlight');
                        }

                        $(thiz.opts.columns).each(function (idx2, column) {
                            var rowHtml = '',
                                style = ' style="white-space: nowrap;  overflow:hidden;  ' + (column.width ? "width: " + column.width + "px;" : "") + '" ',
                                value = item[column.fieldName],
                                value = undefined == value ? "" : value,
                                title = value;

                            if (column.fieldType) {
                                rowHtml = rowHtml + '<td ' + style + ' title="' + title + '"></td>';

                                var td = $(rowHtml).appendTo(linetr);

                                var input = '<input type="text" class=" ' + column.fieldName + '">',
                                    disabled = column.disableFn?!!column.disableFn(idx, idx2, item):false,
                                    cell = $(input).appendTo(td);

                                $(cell).attr("disabled", disabled);

                                switch (column.fieldType) {
                                    case "input":
                                        cell.addClass("form-control");
                                        cell.val(value);
                                        break;
                                    case "select2":
                                        var editor = column.editor,
                                            data = {
                                                id: item[column.idName],
                                                text: item[column.textName]
                                            };
                                        if (editor) {
                                            var selectConfig = {
                                                    selector: cell,
                                                    showSearch: true,
                                                    width: '100%',
                                                    val: data
                                                },
                                                editorConfig = editor(data, cell, td, linetr);

                                            selectConfig = $.extend({}, selectConfig, editorConfig);
                                            selectTool.renderSelect(selectConfig);
                                        }

                                        break;
                                }
                            } else {
                                if (column.content) {
                                    value = column.content;
                                    title = "";
                                } else if (column.renderer) {
                                    value = column.renderer(idx, idx2, item, value) || "";
                                    title = "";
                                } else if ('date' == column.type && undefined != value) {
                                    var fmt = column.dateFormat || 'yyyy-MM-dd hh:mm:ss';
                                    value = util.dateFormat2(value, fmt);
                                    title = value;
                                }
                                rowHtml = rowHtml + '<td ' + style + ' title="' + title + '">' + value + '</td>';
                                $(rowHtml).appendTo(linetr);
                            }
                        });
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
                                    var tr = $(lineCheckbox).parent().parent().parent().parent().parent();
                                    $(lineCheckbox).get(0).checked = checked;
                                    if (checked) {
                                        $(tr).addClass("highlight");
                                        $(lineCheckbox).parents(".checker").find("span").addClass("checked");
                                    } else {
                                        $(tr).removeClass("highlight");
                                        $(lineCheckbox).parents(".checker").find("span").removeClass("checked");
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

                            if (checked) {
                                if (!thiz.multiSelect) {
                                    $(tr).addClass('highlight').siblings().removeClass('highlight');

                                    $("tr .line-checkbox", thiz.tbody).each(function (idx, ele) {
                                        ele.checked = false;
                                        $(ele).parents(".checker").find("span").removeClass("checked");
                                    });
                                } else {
                                    $(tr).addClass("highlight");
                                }
                                if (isChecker) {
                                    $("span", checker).addClass("checked");
                                }
                            } else {
                                $(tr).removeClass("highlight");
                                if (isChecker) {
                                    $("span", checker).removeClass("checked");
                                }
                            }

                            lineCheckbox.get(0).checked = checked;

                            if (thiz.opts.rowClick) {
                                var rowIndex = $(tr).get(0).rowIndex - 1;
                                thiz.opts.rowClick(rowIndex, thiz.dataList[rowIndex], checked);
                            }
                        });
                        $(thiz.tbody).on("dblclick", "tr", function (e) {
                            var tr = $(this);
                            if (thiz.opts.rowDblclick) {
                                var rowIndex = $(tr).get(0).rowIndex - 1;
                                thiz.opts.rowDblclick(rowIndex, thiz.dataList[rowIndex]);
                            }
                        });

                        if (thiz.opts.classEvents && $.isArray(thiz.opts.classEvents)) {
                            $(thiz.opts.classEvents).each(function (idx, event) {
                                if (event.className && event.handler && $.isFunction(event.handler)) {
                                    $(thiz.tbody).on("click", "." + event.className, function (e) {
                                        var tr = $(this).parents("TR"),
                                            rowIndex = tr[0].rowIndex;
                                        event.handler(rowIndex, thiz.dataList[rowIndex - 1], $(this), tr, e);
                                    });
                                }
                            });
                        }

                        if (thiz.opts.headClassEvents && $.isArray(thiz.opts.headClassEvents)) {
                            $(thiz.opts.headClassEvents).each(function (idx, event) {
                                if (event.className && event.handler && $.isFunction(event.handler)) {
                                    $(thiz.thead).on("click", "." + event.className, function (e) {
                                        event.handler($(this), thiz.thead, e);
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
                                if (!thiz.hasRight(event)) {
                                    $("." + event.className, thiz.tbody).addClass("hidden");
                                }
                            });
                        }
                        if (thiz.opts.headClassEvents && $.isArray(thiz.opts.headClassEvents)) {
                            $(thiz.opts.headClassEvents).each(function (idx, event) {
                                if (!thiz.hasRight(event)) {
                                    $("." + event.className, thiz.thead).addClass("hidden");
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
                            totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : Math.floor(totalCount / pageSize) + 1,
                            prePage = currentPage <= 1 ? 0 : (currentPage - 1),
                            nextPage = currentPage >= totalPage ? totalPage : (currentPage + 1),
                            prevDisabled = currentPage == 1 ? "disabled" : "",
                            nextDisabled = currentPage >= totalPage ? "disabled" : "";

                        thiz.currentPage = currentPage;
                        thiz.pageSize = pageSize;
                        thiz.orderBy = orderBy;

                        var pageInfo = '<div class="col-sm-9"> ' +
                            '<div class="dataTables_info" style="padding-top: 4px; white-space: nowrap;">' +
                            '显示 ' + start + ' - ' + end + ' 条记录 共 ' + totalCount + ' 条 ' +
                            '每页<input type="text" class="page-size" value="' + pageSize + '" style="">条 ' +
                            '共 ' + totalPage + " 页 " +
                            '跳转至第<input type="text" class="to-pageNo" style="">页 ' +
                            '<input type="button" class="btn btn-info btn-to-pageNo" value="确定" style="padding: 4px; ">' +
                            '<input type="hidden" class="data-totalCount" value="' + totalCount + '">' +
                            '</div> </div> ' +
                            '<div class="col-sm-3"> <div class="pull-right"> ' +
                            '<ul class="pagination"> ' +
                            '<li class="prev ' + prevDisabled + '"> <a href="#" class="prev-page" data-no="' + prePage + '"><i class="fa fa-angle-left"></i></a> </li> ' +
                            '<li class="active"> <a href="#">' + currentPage + '</a> </li> ' +
                            '<li class="next ' + nextDisabled + '"> <a href="#" class="next-page" data-no="' + nextPage + '"><i class="fa fa-angle-right"></i></a> </li> ' +
                            '</ul> </div> </div>';

                        thiz.pageinfo.html("");

                        $(thiz.pageinfo).css("display", !thiz.opts.showPagebar ? "none" : "");

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
                        return this.opts.extData || {};
                    },

                    getAllData: function () {
                        return this.dataList;
                    },

                    getDeleteData: function () {
                        return this.deleteDataList;
                    },
                    /**
                     * 删除行
                     * 从 1 开始
                     * @param idx
                     */
                    removeByIndex: function (idx) {
                        var thiz = this,
                            child = thiz.tbody.children()[idx - 1],
                            data = thiz.dataList[idx - 1];
                        if (child) {
                            child.remove();
                            if (!data.isnew) {
                                thiz.deleteDataList.push(data);
                            }
                            thiz.freshDataList(idx - 1);
                        }
                        return data;
                    },

                    disableColumn: function (fieldName, disabled) {
                        $("." + fieldName, this.tbody).attr("disabled", disabled);
                    },

                    clearColumnVal: function (fieldName) {
                        $("." + fieldName, this.tbody).val("");
                    },

                    /**
                     * 刷新数据
                     * @param idx
                     */
                    freshDataList: function (idx) {
                        var thiz = this,
                            newDataList = [];
                        thiz.stopListener();
                        $(thiz.dataList).each(function (index, data) {
                            if (idx != index) {
                                newDataList.push(data);
                            }
                        });
                        thiz.dataList = newDataList;
                        thiz.startListener();
                    },

                    getColumn: function (tr, fieldName) {
                        return $(tr).find("." + fieldName);
                    },

                    startListener: function () {
                        var thiz = this;
                        thiz.listenerKey = setInterval(function () {
                            thiz.listenerDataChange();
                        }, 500);
                    },

                    stopListener: function () {
                        clearInterval(this.listenerKey);
                    },

                    /**
                     * 监听数据的变化
                     */
                    listenerDataChange: function () {
                        var thiz = this;
                        $(thiz.dataList).each(function (idx, item) {
                            item.update = !!item.update;

                            $(thiz.opts.columns).each(function (idx2, column) {
                                var value = item[column.fieldName],
                                    value = undefined == value?"":value,
                                    row = thiz.tbody.children()[idx];

                                if (column.fieldType) {
                                    switch (column.fieldType) {
                                        case "input":
                                            var val = $(row).find("." + column.fieldName).val(),
                                                val = undefined == val?"":val;
                                            if(value != val) {
                                                item[column.fieldName] = val;
                                                item.update = !item.update;
                                            }
                                            break;
                                        case "select2":
                                            var data = $(row).find("." + column.fieldName).select2("data")||{},
                                                oldData = {
                                                    id: item[column.idName],
                                                    text: item[column.textName]
                                                };
                                            if (oldData.id != data.id || oldData.text != data.text) {
                                                item[column.idName] = data.id;
                                                item[column.textName] = data.text;
                                                item.update = !item.update;
                                            }
                                            break;
                                    }
                                }
                            });
                        });
                    }
                };
                return listTool.init(config);
            }
        };
    });