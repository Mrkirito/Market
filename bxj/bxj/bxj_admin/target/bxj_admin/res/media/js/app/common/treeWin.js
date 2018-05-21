define(['jquery',
    'util',
    'app/common/winTool',
    'app/common/messageTool',
    'plugins/ztree/ztree'
], function($, util, winTool, messageTool){
    var treeTools, treeSelector, multi = false, url, showSearchBar = false, win, valFn;

    var redioSetting = {
            enable: true,
            chkStyle: "radio",
            radioType : "all"
        };
    var checkSetting = {
            enable: true,
            chkDisabledInherit: true,
            chkboxType : { "Y" : "", "N" : "" }
        };
    var __defaults = {
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                "onClick": function (event, treeId, treeNode) {
                    $.fn.zTree.getZTreeObj(treeId).checkNode(treeNode, !treeNode.checked);
                }
            }
        };

    var refreshTree = function() {
        if(treeTools) {
            treeTools.destroy();
        }
        var option = $.extend({}, __defaults, {
            check: !!multi?checkSetting:redioSetting
        });
        util.ajax({
            url: url,
            params: {
                q: !!showSearchBar?win.find(".x-window-query-param").val():""
            },
            success: function (resp) {
                var data = resp.model||[];
                selectNode(data);
                treeTools = $.fn.zTree.init(treeSelector, option, data);
                treeTools.expandAll(true);
            }
        });
    }

    var createWin = function (config) {
        var __defaultWin = {
            title: "选择树窗口",
            height: 300,
            width: 300,
            blankText: "尚未选择数据",
            showToolbar: true,
            showButton: true,
            showSearchBar: false
        };
        config = config || {};
        config = $.extend({}, __defaultWin, config);

        var tbars = [];
        if(!!config.multi) {
        }

        win = winTool.create({
            title: config.title,
            showOk: false,
            showCancel: false,
            cancelName: "关闭",
            html: "",
            type: 'html',
            width: 450,
            height: 400
        });

        var treeHtml = '<div style="width: 100%; height: 100%; ">' +
            '   <div class="x-window-query" >' +
            '       <input type="text" class="x-window-query-param target" style="padding: 3px; margin-right: 5px;">' +
            '<div class="buttons">' +
                '<a href="javascript:;" class="search-tree">查询</a>' +
                    '<span class="xtb-sep"></span>' +
                '<a href="javascript:;" class="refresh">刷新</a>' +
                    '<span class="xtb-sep"></span>' +
                '<a href="javascript:;" class="selectall">全选</a>' +
                '<span class="xtb-sep"></span>' +
                '<a href="javascript:;" class="unselectall">全不选</a>' +
                '<span class="xtb-sep"></span>' +
                '<a href="javascript:;" class="ok">确定</a>' +
                '<span class="xtb-sep"></span>' +
                '</div>' +
            '   </div>' +
            '   <div class="x-window-tree ztree" style="overflow-y: auto; height: 100%;"></div>' +
            '</div>',
            conHeight = 400;

        win.treeModule = $(treeHtml).appendTo(win.$content);
        $(win.treeModule).find(".x-window-tree").css("height", (conHeight-40) + "px")

        win.$content.css("padding", "0");

        var queryTarget = win.treeModule.find(".x-window-query-param");

        win.$content.css("padding-left", "0");
        win.$content.css("padding-right", "0");
        win.$content.css({
            background: '#eff3f8'
        });

        queryTarget.on("keypress", function (e) {
            if(e.keyCode == 13 ) {
                refreshTree();
            }
        });

        win.find(".search-tree").on("click", function () {
            refreshTree();
        });

        win.find(".refresh").on("click", function () {
            refreshTree();
        });

        win.find(".selectall").on("click", function () {
            if(treeTools) {
                treeTools.checkAllNodes(true);
            }
        });
        win.find(".unselectall").on("click", function () {
            if(treeTools) {
                treeTools.checkAllNodes(false);
            }
        });
        win.find(".ok").on("click", function () {
            if(treeTools) {
                var selectedNodes = treeTools.getCheckedNodes();
                if(selectedNodes.length <=0) {
                    messageTool.warning(config.blankText);
                } else {
                    if(config.callback) {
                        !!config.multi?config.callback(selectedNodes):config.callback(selectedNodes[0]);
                    }
                    win.close();
                }
            }
        });

        if(!!config.showSearchBar) {
            win.treeModule.css("display", "");
        } else {
            win.treeModule.css("display", "none");
        }

        showSearchBar = !!config.showSearchBar;

        var treeSelector = win.treeModule.find(".x-window-tree");

        return treeSelector;
    }

    var createTree = function (config) {
        treeSelector = createWin(config);
        url = config.url||"";
        multi = !!config.multi;
        valFn = config.valFn;

        var option = $.extend({}, __defaults, {
            check: !!config.multi?checkSetting:redioSetting
        });
        util.ajax({
            url: config.url,
            success: function (resp) {
                var data = resp.model||[];
                selectNode(data);
                treeTools = $.fn.zTree.init(treeSelector, option, data);
                treeTools.expandAll(true);
            }
        });
    }

    var selectNode  = function (dataList) {
        if(dataList && dataList.length > 0) {
            $(dataList).each(function (idx, data) {
                if(valFn) {
                    var value = valFn();
                    if(value && $.isArray(value)) {
                        $(value).each(function (idx2, value) {
                            if(data.id == value) {
                                data.checked = true;
                            }
                        })
                    } else if(value && data.id == value) {
                        data.checked = true;
                    }
                }

            });
        }
    }

    return {
        show: function(config) {
            config = config || {};
            config = $.extend({}, {
                multi:false,
                callback: function(){},
                valFn:null
            }, config);
            createTree(config);
        },
        render: function (selector, config) {
            var thiz = this;
            $(selector).attr("readonly", true);
            $(selector).addClass("target");
            $(selector).on("click", function () {
                thiz.show(config);
            });
        }
    };
});