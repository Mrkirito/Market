define(['jquery', 'plugins/select2/select2',
    'css!global/js/lib/plugins/select2/select2-marx.css'
], function ($) {
    /**
     * selector: #id | .class,
     *
     * {
     *  fn: function(queryParam, callback(data)),
     *  allowClear: 显示关闭按钮 默认true
     *  queryLength： 触发查询的输入字符长度 默认1
     * }
     * @param cfg
     */
    function dynimacSelect(selector, cfg) {
        if (!selector) return;
        cfg = cfg ||{};

        var _defaults = {
            msg: "--请选择--",
            allowClear: true,
            multiple: false,
            queryLength: 1,
            valueField: "id",
            displayField: "text",
            fn: null,
            select: function (id, data) {
            },
            change: function (id, newData, oldData) {
            },
            tags: null,
            data: null,
            showArrow: true,
            extAttrs: [],
            width: 150,
            showSearch: true,
            formatResult: function (object) {
                return object?object.text||"":"";
            },
            formatSelection: function (object) {
                return object?object.text||"":"";
            }
        };
        cfg = $.extend({}, _defaults, cfg);

        var parseData = function (dataList) {
            dataList = dataList || [];
            var result = [];
            $.each(dataList, function (idx, data) {
                if (data[cfg.valueField] && data[cfg.displayField]) {
                    var resData = data;
                    resData.id = data[cfg.valueField];
                    resData.text = data[cfg.displayField];

                    /*$.each(cfg.extAttrs, function (idx, attr) {
                        resData[attr] = data[attr];
                    });*/

                    if (data.children && data.children.length > 0) {
                        resData.children = parseData(data.children);
                    }
                    result.push(resData);
                }
            });
            return result;
        };

        var selectConfig = {
            placeholder: cfg.msg,
            showArrow: cfg.showArrow,
            allowClear: cfg.allowClear,
            //afterSelection: cfg.afterSelection,
            //afterUnselection: cfg.afterUnselection,
            formatResult: cfg.formatResult,
            formatSelection: cfg.formatSelection,
            initSelection: function (ele, callback) {
                if (cfg.value) {
                    callback(cfg.value);
                }
            },
            formatNoMatches: function () {
                return "没有匹配的结果";
            },
            formatSearching: function () {
                return "加载中。。。"
            }
        };

        if (cfg.tags) {
            selectConfig = $.extend(selectConfig, {
                tags: cfg.tags
            });
        }
        if (cfg.data) {
            selectConfig = $.extend(selectConfig, {
                data: cfg.data
            });
        }

        if (cfg.fn) {
            selectConfig = $.extend(selectConfig, {
                multiple: cfg.multiple,
                minimumInputLength: cfg.queryLength,
                width: cfg.width,
                minimumResultsForSearch: !!cfg.showSearch?8:-1,
                query: function (query) {
                    /**
                     * results：[
                     * {id:'', text:''}
                     * ]
                     * @param results
                     */
                    var callback = function (results) {
                        results = results || [];
                        var data = parseData(results);
                        query.callback({results: data});
                    };
                    if (cfg.fn) {
                        cfg.fn(query.term, callback);
                    }
                },
                initSelection: function (element, callback) {
                    callback(cfg.val);
                }
            });
        }
        $(selector).select2(selectConfig).on("change", function(e) {
            if(cfg.change) {
                cfg.change(e.val, e.added, e.removed);
            }
        }).on("select2-selecting", function (e) {
            if(cfg.select) {
                cfg.select(e.val, e.object);
            }
        });
    };

    return {
        render:dynimacSelect
    };
})