define(['jquery', 'util',
    'app/common/messageTool', 'moment'],
    function ($, util, messageTool, moment) {

    var defaults = {
            selector: "",
            /**
             * {
             *  id: '',
             *  name: ''
             *  type: hidden|text|textarea|select|select2|date|datetime|daterange|checkbox,
             *  multiple: false, type为select2时生效
             *  idKey: '', type为select2时生效
             *  textKey: '', type为select2时生效
             *  valueKey: '',
             *  cls: '' 样式
             *  dataType: ''// 使用regexMap定义的key
             *  greaterThan: null,
             *  greaterThanOrEqualTo: null,
             *  format: 'YYYY-MM-DD'  dataType为date或者datetime时生效
             * }
             * */
            fields: []
        },
        regexMap = {
            number: /^[-]?\d+$/,
            int: /^\d+$/,
            //double: /^[-\+]?\d+(\.\d+)?$/,
            double: /^[-\+]?\d+(\.\d{0,4})?$/,
            email: /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/,
            phone: /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/,
            mobile: /^((\(\d{2,3}\))|(\d{3}\-))?1[3,5,7,8]\d{9}$/,
            url: /^http:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/,
            money: /^\d+(\.\d+)?$/,
            bank: /^\d{19}$/
        },
        msgMap = {
            required: '不能为空',
            number: '必须为数字',
            int: '必须为整数',
            double: '必须为数字,小数点后保留2位小数',
            email: '邮箱格式不正确',
            phone: '无效的电话',
            mobile: '无效的手机号',
            url: '无效的链接地址',
            money: '无效的金额',
            bank: '无效的银行帐号，必须是19位数字'
        };


    return {
        create: function (config) {
            var formTool = {
                init: function (config) {
                    config = config || {};
                    var thiz = this;
                    thiz.opts = $.extend({}, defaults, config);

                    return thiz;
                },

                /**
                 * 取得form数据
                 * @param selector
                 * @param fields
                 * @returns {{}}
                 */
                getValues: function (selector, fields) {
                    var thiz = this,
                        selector = selector||thiz.opts.selector,
                        result = {};

                    if(selector) {
                        if(typeof selector == 'string') selector = $(selector);
                        var fields = fields || thiz.opts.fields || [];
                        $(fields).each(function (idx, field) {
                            if(field.id || field.cls || field.name) {
                                var type = field.type||'text',
                                    data = thiz.getFieldValue(selector, field);
                                if(data.name) {
                                    switch (type) {
                                        case 'select2':
                                            var idKey = field.idKey,
                                                textKey = field.textKey;
                                            if(!!field.multiple) {
                                                var dataList = data.val||[],
                                                    ids = [],
                                                    texts = [];
                                                $(dataList).each(function () {
                                                    ids.push(this.id);
                                                    texts.push(this.text);
                                                });
                                                if(idKey && ids.length > 0) {
                                                    result[idKey] = ids.join(",");
                                                }
                                                if(textKey && texts.length > 0) {
                                                    result[textKey] = texts.join(",");
                                                }
                                            } else {
                                                var data2 = data.val||{};
                                                if(idKey && undefined != data2.id) {
                                                    result[idKey] = data2.id;
                                                }
                                                if(textKey && undefined != data2.text) {
                                                    result[textKey] = data2.text;
                                                }
                                            }
                                            break;
                                        default:
                                            if(undefined != data.val) {
                                                result[data.name] = data.val;
                                            }
                                    }
                                }
                            }
                        });
                    }

                    return result;
                },

                /**
                 * 取得单个field的值
                 * @param selector
                 * @param field
                 * @returns {*}
                 */
                getFieldValue: function (selector, field) {
                    var result = {},
                        type = field.type||'text',
                        valueKey = field.valueKey || field.cls || field.id || field.name,
                        fieldSelector = this.getFieldSelector(field, selector);
                    if(valueKey) {
                        result.name = valueKey;
                        switch (type) {
                            case 'hidden':
                            case 'text':
                            case 'textarea':
                            case 'select':
                            case 'date':
                            case 'datetime':
                            case 'daterange':
                                var value = fieldSelector.val();
                                if(valueKey && undefined != value) {
                                    result.val = (value=="")?(undefined != field.defaultVal?field.defaultVal:""):value;
                                }
                                break;
                            case 'select2':
                                var data = fieldSelector.select2("data");
                                if(data) {
                                    result.val = data;
                                }
                                break;
                            case 'checkbox':
                                var value = $(fieldSelector)[0].checked?1:0;
                                if(valueKey && undefined != value) {
                                    result.val = value;
                                }
                        }
                    }
                    return result;
                },

                getFieldSelector: function (field, selector) {
                    var _fieldSelector = field.id?("#" + field.id):(field.cls?("." + field.cls): ("[name=" + field.name + "]"));
                    return $(_fieldSelector, selector);
                },

                getField: function (fields, name) {
                    fields = fields ||[];
                    var field = null;
                    $(fields).each(function () {
                        if(name == (this.id||this.cls||this.name)) {
                            field = this;
                            return false;
                        }
                    });
                    return field;
                },

                /**
                 * 给form填充数据
                 * @param selector
                 * @param fields
                 * @param values
                 */
                serializable: function (selector, values, fields, readonly) {
                    var thiz = this,
                        selector = selector||thiz.opts.selector,
                        values = values || {},
                        readonly = !!readonly;

                    if(selector) {
                        if(typeof selector == 'string') selector = $(selector);
                        var fields = fields || thiz.opts.fields || [];
                        $(fields).each(function (idx, field) {
                            if(field && (field.id || field.cls || field.name)) {
                                var type = field.type||'text',
                                    valueKey = field.valueKey || field.cls || field.id || field.name,
                                    fieldSelector = thiz.getFieldSelector(field, selector);
                                switch (type) {
                                    case 'hidden':
                                    case 'text':
                                    case 'textarea':
                                    case 'select':
                                    case 'date':
                                    case 'datetime':
                                    case 'daterange':
                                        var value = values[valueKey];
                                        fieldSelector.val(value);
                                        $(fieldSelector).attr("readonly", readonly);
                                        break;
                                    case 'select2':
                                        var idKey = field.idKey,
                                            textKey = field.textKey,
                                            data = null;
                                        if(!!field.multiple) {
                                            data = [];
                                            var ids = values[idKey],
                                                texts = values[textKey];
                                            if(ids && texts) {
                                                ids = ids.split(",");
                                                texts = texts.split(",");
                                                $(ids).each(function (idx, id) {
                                                    var data2 = {
                                                        id: id,
                                                        text: texts[idx]
                                                    }
                                                    data.push(data2);
                                                });
                                                fieldSelector.select2("data", data);
                                            }
                                        } else {
                                            if(undefined != values[idKey]) {
                                                data = {
                                                    id: values[idKey],
                                                    text: values[textKey]
                                                };
                                                fieldSelector.select2("data", data);
                                            }
                                        }
                                        break;
                                    case 'checkbox':
                                        if(1 == values[valueKey]) {
                                            fieldSelector[0].checked = true;
                                            if(fieldSelector.parent()[0].tagName == "SPAN"
                                                && fieldSelector.parent().parent().hasClass("checker")) {
                                                fieldSelector.parent().addClass("checked");
                                            }
                                        }
                                }

                                if(true == field.disabled) {
                                    fieldSelector.attr("disabled", true);
                                }
                            }
                        });
                    }
                },

                /**
                 * 给form填充数据
                 * @param selector
                 * @param fields
                 * @param values
                 */
                serializableDisplay: function (selector, values) {
                    var thiz = this,
                        selector = selector||thiz.opts.selector,
                        values = values || {};

                    if(selector) {
                        if(typeof selector == 'string') selector = $(selector);

                        $(".form-control-static", selector).each(function () {
                            var key = $(this).data("display"),
                                type = $(this).data("type"),
                                value = values[key],
                                value = undefined == value?"":value;
                            if("checkbox" == type) {
                                $(this).html("1"==value?"是":"否");
                            } else {
                                $(this).html(value);
                            }
                        });
                    }
                },

                /**
                 * 验证表单数据
                 * @param selector
                 * @param fields
                 * @returns {boolean}
                 */
                validate: function(selector, fields) {
                    var thiz = this,
                        selector = selector||thiz.opts.selector,
                        result = true;

                    if(selector) {
                        if(typeof selector == 'string') selector = $(selector);
                        var fields = fields || thiz.opts.fields || [];
                        $(fields).each(function (idx, field) {
                            if(field && (field.id || field.cls || field.name)) {
                                var dataType = field.dataType,
                                    required = !!field.required,
                                    fieldSelector = thiz.getFieldSelector(field, selector),
                                    data = thiz.getFieldValue(selector, field);

                                thiz.clearError(fieldSelector);

                                if(required && thiz.isNull(data.val)) {
                                    thiz.addError(fieldSelector, field, true);
                                    result = false;
                                    return true;
                                }

                                switch (dataType) {
                                    case 'int':
                                    case 'number':
                                    case 'double':
                                        if(!thiz.isNull(data.val)) {
                                            if(!regexMap[dataType].test(data.val)) {
                                                thiz.addError(fieldSelector, field);
                                                result = false;
                                            } else {
                                                var maxValue = field.maxValue,
                                                    minValue = field.minValue;
                                                if(regexMap['double'].test(maxValue) && parseFloat(data.val) > maxValue) {
                                                    thiz.addError(fieldSelector, field);
                                                    result = false;
                                                } else if(regexMap['double'].test(minValue) && parseFloat(data.val) < minValue) {
                                                    thiz.addError(fieldSelector, field);
                                                    result = false;
                                                } else {
                                                    thiz.clearError(fieldSelector);
                                                }

                                            }
                                        }
                                        break;
                                    case 'email':
                                    case 'phone':
                                    case 'mobile':
                                    case 'url':
                                    case 'bank':
                                        if(!thiz.isNull(data.val) && !regexMap[dataType].test(data.val)) {
                                            thiz.addError(fieldSelector, field);
                                            result = false;
                                        }
                                        break;
                                    case 'money':
                                        if(!thiz.isNull(data.val) && !regexMap[dataType].test(data.val) || parseFloat(data.val) < 0) {
                                            thiz.addError(fieldSelector, field);
                                            result = false;
                                        }
                                        break;
                                    default :
                                        if(required) {
                                            var minLength = field.minLength||0,
                                                maxLength = field.maxLength||0,
                                                len = data.val.length||0;
                                            if(minLength > 0 && len< minLength) {
                                                thiz.addError(fieldSelector, field);
                                                result = false;
                                            } else if(maxLength > 0 && len > maxLength) {
                                                thiz.addError(fieldSelector, field);
                                                result = false;
                                            } else {
                                                thiz.clearError(fieldSelector);
                                            }
                                        }
                                        if(result && (dataType == 'date' || dataType=='datetime') && (field.greaterThan || field.greaterThanOrEqualTo) && data.val) {
                                            var lessFieldName = field.greaterThan || field.greaterThanOrEqualTo,
                                                lessField = thiz.getField(fields, lessFieldName),
                                                lessData = lessField?thiz.getFieldValue(selector, lessField):null;
                                            if(lessData && lessData.val) {
                                                var lessMoment = moment(lessData.val, field.format),
                                                    greaterMoment = moment(data.val, field.format),
                                                    compare = true;
                                                if(field.greaterThan) {
                                                    if(lessMoment >= greaterMoment) {
                                                        compare = false;
                                                    }
                                                } else {
                                                    if(lessMoment > greaterMoment) {
                                                        compare = false;
                                                    }
                                                }
                                                if(!compare) {
                                                    thiz.addError(fieldSelector, field);
                                                    result = false;
                                                }  else {
                                                    thiz.clearError(fieldSelector);
                                                }
                                            }
                                        }

                                }
                            }
                        });
                    }

                    return result;
                },

                isNull: function (val) {
                    return undefined == val || "" == val;
                },

                findPopover: function (selector, msg) {
                    var thiz = this,
                        popover = $(selector).data("bs.popover");
                    if(!popover) {
                        $(selector).popover({
                            trigger: "hover",
                            placement: thiz.position||"top",
                            content: "<span style='color: red;'>" + msg + "</span>",
                            html: "html"
                        });
                        popover = $(selector).data("bs.popover");
                    } else {
                        popover.$element.attr("data-content", "<span style='color: red;'>" + msg + "</span>");
                        popover.setContent();
                    }
                    return popover;
                },

                /**
                 * 处理错误显示
                 * @param selector
                 */
                addError: function (selector, field, required) {
                    var msg = field.msg||(!!required?msgMap['required']:msgMap[field.dataType||'required']),
                        msg = msg + (field.minLength>0?(", 最小长度为" + field.minLength):""),
                        msg = msg + (field.maxLength>0?(", 最大长度为" + field.maxLength):""),
                        msg = msg + (field.minValue>0?(", 最小值为" + field.minValue):""),
                        msg = msg + (field.maxValue>0?(", 最大值为" + field.maxValue):""),
                        errorHtml = '<i class="fa fa-warning validate-error" title="' + msg + '"></i>';

                    $(selector).addClass("validate-error");

                    var popover = this.findPopover(selector, msg);
                    if(popover) {
                        if(!popover.enabled) {
                            popover.enable();
                        }
                        popover.show();
                    }
                },

                /**
                 * 处理错误显示
                 * @param selector
                 */
                addCustomError: function (selector, msg, type) {
                    $(selector).addClass("validate-error");
                    var popover = this.findPopover(selector, msg);
                    if(popover) {
                        if(!popover.enabled) {
                            popover.enable();
                        }
                        popover.show();
                    }
                },

                /**
                 * 清除错误信息
                 * @param selector
                 */
                clearError: function (selector) {
                    $(selector).removeClass("validate-error");
                    var popover = $(selector).data("bs.popover");
                    if(popover) {
                        if(popover.enabled) {
                            popover.disable();
                        }
                        popover.hide();
                    }
                },

                /**
                 * 提交表单数据
                 * @param config {
                 *  selector:'',
                 *  fields: [],
                 *  paramName: '',
                 *  params: {},
                 *  url:'',
                 *  confirm: true,
                 *  msg:'',
                 *  extCheckFn: function(){},
                 *  success: function(){}
                 * }
                 */
                submit: function (config) {
                    var thiz = this;
                    config = config || {};
                    config = $.extend({}, {
                        confirm: true,
                        msg: "确定要处理？"
                    }, config);
//console.info(config.fields||thiz.opts.fields)
                    if(!thiz.validate(config.selector, config.fields||thiz.opts.fields)) {
                        return;
                    }

                    var values = thiz.getValues(config.selector, config.fields||thiz.opts.fields),
                        paramName = config.paramName||"data",
                        params = config.params||{},
                        extValues = config.extValues||{};
//console.info(values);
                    values = $.extend({}, values, extValues);
                    params = values;
//                    params[paramName] = values;
//console.info(values);
                    if(config.extCheckFn && !config.extCheckFn(values)) {
                        return ;
                    }

                    if(config.url) {
                        if(!!config.confirm) {
                            messageTool.confirm(config.msg, function (flag) {
                                if(flag) {
                                    thiz.doSubmit(config, params);
                                }
                            });
                        } else {
                            thiz.doSubmit(config, params);
                        }
                    }
                },

                doSubmit: function (config, params) {
                	console.info(params);
                    util.ajax({
                        url: config.url,
                        params: params,
                        success: function (resp) {
                            if(resp.success == true) {
                                config.success && config.success(resp);
                            } else {
                                messageTool.error(resp.msg||"处理失败");
                            }
                        }
                    });
                }
            };
            return formTool.init(config);
        }
    };
});