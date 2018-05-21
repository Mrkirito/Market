define(['jquery',
    'app/common/combox', 'util'
], function ($, combox, util) {

    var renderSelect = function (config) {
        var selector = config.selector,
            url = config.url || "",
            listeners = config.listeners||{},
            selectFn = listeners.select,
            changeFn = listeners.change,
            paramFn = config.paramFn,
            val = config.val,
            width = config.width,
            idName = config.idName||"id",
            textName = config.textName||"text",
            queryParamName = config.queryParamName || "queryParam",
            showSearch = config.showSearch == undefined ? false : config.showSearch,
            allowClear = config.allowClear == undefined ? true : config.allowClear,
            queryLength = config.queryLength == undefined ? 0 : config.queryLength;


        $(selector).select2("destroy");

        //val = val||{id:"", text:""};
        var value = '';
        if (val) {
            if (typeof val == "string") {
                value = val;
            }
            if (!!config.multiple) {
                if (typeof val == "object" && $.isArray(val)) {
                    $(val).each(function (idx, obj) {
                        if (typeof obj == "string") {
                            value += (value.length > 0 ? "," + value : value);
                        } else if (typeof obj == "object") {
                            if(idName && undefined == obj.id && undefined != obj[idName]) {
                                obj.id = obj[idName];
                            }
                            if(textName && undefined == obj.text && undefined != obj[textName]) {
                                obj.text = obj[textName];
                            }
                            if(obj.id) {
                                value += (value.length > 0 ? "," + obj.id  : obj.id);
                            }
                        }
                    });
                }
            } else {
                if (typeof val == "object" && val.id) {
                    value = val.id;
                }
            }
            $(selector).val(value);
        }

        combox.render(selector, {
            queryLength: queryLength,
            showSearch: showSearch,
            fn: function (queryParam, callback) {
                var params = {};
                params[queryParamName] = queryParam;
                params = $.extend(params, paramFn ? paramFn() : {});
                util.ajax({
                    url: url,
                    success: function (resp) {
                        if(!!resp.success) {
                            var data = resp.model || [];
                            callback(data);
                        }
                    },
                    params: params
                });
            },
            select: selectFn,
            change: changeFn,
            val: val,
            width: width,
            allowClear: allowClear,
            msg: config.msg,
            valueField: idName,
            displayField: textName,
            multiple: config.multiple,
            formatResult: config.formatResult,
            formatSelection: config.formatSelection,
            extAttrs: config.extAttrs //
        });
    }
    /**
     * 数据字典默认配置
     * @type {{
     * msg: string, 未选择时的提示信息
     * url: string, 后台请求数据的url
     * selector: null, 选择器 .className, #id, jquery对象
     * width: number, 宽度
     * showSearch: boolean, 是否显示搜索框
     * queryParamName: string, 搜索时传入后台的参数名称
     * val: null 选择的值
     * }}
     */
    var defaults_dict = {
        msg: '空白',
        url: null,
        selector: null,
        width: 120,
        showSearch: true,
        queryParamName: "name",
        val: null
    };

    return {
        renderSelect: renderSelect,
        renderDict: function (config) {
            config = config || {};
            config = $.extend({}, defaults_dict, config);
            config.queryParamName = "name";
            config.url = serverHost + "common/dictAjax/queryDictList.json?type=" + (config.type||"");
            renderSelect(config);
        },
        renderDictMINI: function (config) {
            config = config || {};
            config = $.extend({}, defaults_dict, config);
            config.queryParamName = "name",
                config.url = serverHost + "common/dictAjax/queryDictList.json?mini=1&type=" + (config.type||"");
            renderSelect(config);
        }
    };


});