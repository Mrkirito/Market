define(['jquery',
    'util',
    'app/common/winTool',
    'app/common/messageTool',
    'plugins/ztree/ztree'
], function($, util, winTool, messageTool){
    var redioSetting = {
            enable: true,
            chkStyle: "radio",
            radioType : "all"
        },
        checkSetting = {
            enable: true,
            chkDisabledInherit: true,
            chkboxType : { "Y" : "", "N" : "" }
        },
        defaults = {
            title: "选择树窗口",
            height: 400,
            width: 430,
            blankText: "尚未选择数据",
            multi:false,
            showSearchBar: false,
            queryName: "q",
            allowEmpty: true,
            onClick: function (node, checked) {

            },
            onOk: function (nodes) {

            },
            valFn:null,
            mappings: [{}]// [{name:'parentId', mapping:'pId'}]
        };


    return {
        init: function (config) {
            var treeWin = {
                init: function (option) {
                    option = option || {};
                    this.opts = $.extend({}, defaults, option);
                    this.initWindow();
                    this.loadTree();
                },

                initWindow: function () {
                    var thiz = this,
                        treeHtml = '<div style="width: 100%; height: 100%; ">' +
                            '<div class="x-window-query" >' +
                            (!!thiz.opts.showSearchBar?('<input type="text" class="x-window-query-param target" style="padding: 3px; margin-right: 5px;">'):"") +
                            '<div class="buttons">' +
                            (!!thiz.opts.showSearchBar?('<a href="javascript:;" class="search-tree">查询</a>' +
                            '<span class="xtb-sep"></span>'):"") +
                            '<a href="javascript:;" class="refresh">刷新</a>' +
                            '<span class="xtb-sep"></span>' +
                            (!!thiz.opts.multi?('<a href="javascript:;" class="selectall">全选</a>' +
                            '<span class="xtb-sep"></span>' +
                            '<a href="javascript:;" class="unselectall">全不选</a>' +
                            '<span class="xtb-sep"></span>'):"") +
                            '<a href="javascript:;" class="ok">确定</a>' +
                            '<span class="xtb-sep"></span>' +
                            '</div>' +
                            '</div>' +
                            '<div class="x-window-tree ztree" style="overflow-y: auto; height: 100%;"></div>' +
                            '</div>';
                    thiz.win = winTool.create({
                        title: thiz.opts.title,
                        showOk: false,
                        showCancel: false,
                        cancelName: "关闭",
                        html: "",
                        type: 'html',
                        width: thiz.opts.width,
                        height: thiz.opts.height
                    });
                    $(treeHtml).appendTo(thiz.win.$content);
                    thiz.win.find(".x-window-tree").css("height", (thiz.opts.height-40));
                    thiz.win.$content.css({
                        background: '#eff3f8',
                        padding: 0
                    });
                    thiz.bindingEvents();
                },

                bindingEvents: function () {
                    var thiz = this;
                    thiz.win.find(".x-window-query-param").on("keypress", function (e) {
                        if(e.keyCode == 13 ) {
                            thiz.loadTree();
                        }
                    });

                    thiz.win.find(".search-tree").on("click", function () {
                        thiz.loadTree();
                    });

                    thiz.win.find(".refresh").on("click", function () {
                        thiz.loadTree();
                    });

                    thiz.win.find(".selectall").on("click", function () {
                        if(thiz.treeTools) {
                            thiz.treeTools.checkAllNodes(true);
                        }
                    });
                    thiz.win.find(".unselectall").on("click", function () {
                        if(thiz.treeTools) {
                            thiz.treeTools.checkAllNodes(false);
                        }
                    });
                    thiz.win.find(".ok").on("click", function () {
                        if(thiz.treeTools) {
                            var selectedNodes = thiz.treeTools.getCheckedNodes();
                            if(selectedNodes.length <=0) {
                                if(!!thiz.opts.allowEmpty) {
                                    if(thiz.opts.onOk) {
                                        !!thiz.opts.multi?thiz.opts.onOk(selectedNodes):config.onOk(selectedNodes[0]);
                                    }
                                    thiz.win.close();
                                } else {
                                    messageTool.error(thiz.opts.blankText);
                                }
                            } else {
                                if(thiz.opts.onOk) {
                                    !!thiz.opts.multi?thiz.opts.onOk(selectedNodes):config.onOk(selectedNodes[0]);
                                }
                                thiz.win.close();
                            }
                        }
                    });
                },

                loadTree: function() {
                    var thiz = this,
                        treeSelector = thiz.win.find(".x-window-tree");
                    if(thiz.treeTools) {
                        thiz.treeTools.destroy();
                    }
                    var option = {
                        data: {
                            simpleData: {
                                enable: true
                            }
                        },
                        callback: {
                            "onClick": function (event, treeId, treeNode) {
                                var checked = !treeNode.checked;
                                thiz.treeTools.checkNode(treeNode, checked);
                                thiz.opts.onClick && thiz.opts.onClick(treeNode, checked);
                            },
                            onCheck: function (event, treeId, treeNode) {
                                thiz.opts.onClick && thiz.opts.onClick(treeNode, treeNode.checked);
                            }
                        },
                        check: !!thiz.opts.multi?checkSetting:redioSetting
                    };

                    var params = {};
                    params[thiz.opts.queryName] = !!thiz.opts.showSearchBar?thiz.win.find(".x-window-query-param").val():"";

                    util.ajax({
                        url: thiz.opts.url,
                        params: params,
                        success: function (resp) {
                            var data = resp.model||[];
                            data = thiz.generateData(data);
                            thiz.selectNode(data);
                            thiz.treeTools = $.fn.zTree.init(treeSelector, option, data);
                            thiz.treeTools.expandAll(true);
                        }
                    });
                },

                selectNode: function (dataList) {
                    var thiz = this;
                    if(dataList && dataList.length > 0) {
                        $(dataList).each(function (idx, data) {
                            if(thiz.opts.valFn) {
                                var value = thiz.opts.valFn();
                                if(value && $.isArray(value)) {
                                    thiz._checkNode(data, value);
                                } else if(value && typeof value == 'string') {
                                    var values = value.split(",");
                                    thiz._checkNode(data, values);
                                }
                            }
                        });
                    }
                },

                _checkNode: function (data, values) {
                    $(values).each(function (idx2, value) {
                        if(data.id == value) {
                            data.checked = true;
                        }
                    })
                },

                generateData: function (dataList) {
                    dataList = dataList || [];
                    var mappings = this.opts.mappings || [];
                    $(dataList).each(function (idx, data) {
                        $(mappings).each(function (idx, map) {
                            data[map.mapping] = data[map.name];
                        });
                    });
                    return dataList;
                }
            };

            treeWin.init(config);
        },

        show: function(config) {
            this.init(config);
        },
        render: function (selector, config) {
            var thiz = this;
            $(selector).attr("readonly", true);
            $(selector).unbind("click");
            $(selector).on("click", function () {
                thiz.show(config);
            });
        }
    };
});