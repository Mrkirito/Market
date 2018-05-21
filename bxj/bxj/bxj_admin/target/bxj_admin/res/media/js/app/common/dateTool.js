define(['jquery',
    'moment',
    'plugins/bootstrap-datepicker/bootstrap-datepicker',
    'plugins/bootstrap-datetimepicker/bootstrap-datetimepicker2',
    'css!global/js/lib/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css',
    'plugins/bootstrap-daterangepicker/daterangepicker',
    'plugins/scalendar/scalendar',
    'plugins/bootstrap-timepicker/bootstrap-timepicker'
], function($, moment){
    var __defaults = {
        autoclose: true,
        clearBtn:true,
        todayBtn: false
    };

    return {
        /**
         * 日期控件
         * @param selector
         * @param option
         */
        renderDate: function (selector, option) {
            option = option || {};
            option = $.extend({
                language: "zh-CN",
                format: 'yyyy-mm-dd'
            }, __defaults, option);
            $(selector).datepicker(option);
            $(selector).addClass('datepicker-icon');
            $(selector).attr("readonly", true);
        },
        
        /**
         * 日期控件
         * @param node
         * @param option
         */
        renderDateByNode: function (node, option) {
            option = option || {};
            option = $.extend({
                language: "zh-CN",
                format: 'yyyy-mm-dd'
            }, __defaults, option);
            node.datepicker(option);
            node.addClass('datepicker-icon');
            node.attr("readonly", true);
        },
        /**
         * 时间控件
         * @param selector
         * @param option
         */
        renderDateTime: function (selector, option) {
            option = option || {};
            option = $.extend({}, __defaults, option);
            option.minuteStep = 1;
            option.format = 'yyyy-mm-dd hh:ii:ss';
            $(selector).datetimepicker(option);
            $(selector).addClass("datetimepicker-icon");
            $(selector).attr("readonly", true);
        },
        /**
         * 年月控件
         * @param selector
         * @param option
         */
        renderYYYYMM: function (selector, option) {
            option = option || {};
            option = $.extend({}, __defaults, option);
            $(selector).scalender(option);
            $(selector).attr("readonly", true);
        },
        
        renderYYYYMM2: function (selector, option) {
            option = option || {};
            option = $.extend({
            	  format: 'yyyymm',  
                  weekStart: 1,  
                  autoclose: true,  
                  startView: 3,  
                  minViewMode:'months',
                  minView: 3,  
                  forceParse: false,  
                  language: 'zh-CN'             	
            }, __defaults, option);
            $(selector).datepicker(option);
            $(selector).addClass('datepicker-icon');
            $(selector).attr("readonly", true);
        },
        unbindDate: function (selector) {
            selector = typeof(selector)=="string"?$(selector):selector;
            selector.datepicker("remove");
            selector.datetimepicker("remove");
            selector.removeClass('datepicker-icon');
            selector.attr("readonly", false);
        },
        unbindDateTime: function (selector) {
            selector = typeof(selector)=="string"?$(selector):selector;
            selector.datetimepicker("remove");
            selector.removeClass('datetimepicker-icon');
            selector.attr("readonly", false);
        },
        getCurrentDate: function (){
            //var d = new Date();
            //var selector = d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
            //console.info(moment().format("YYYY-MM-DD HH:mm:ss"))
            return moment().format("YYYY-MM-DD HH:mm:ss");
        },
        getCurrentDate: function (formatPattern){
            //var d = new Date();
            //var selector = d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
            //console.info(moment().format("YYYY-MM-DD HH:mm:ss"))
            return moment().format(formatPattern);
        },

        renderDateRange: function (selector, config) {
            selector = typeof(selector)=="string"?$(selector):selector;
            config = config || {};
            config.format = 'YYYY-MM-DD';
            config.separator = ' ~ ';
            selector.daterangepicker(config);
            selector.attr("readonly", true);
        },

        renderTime: function (selector, config) {
            selector = typeof(selector)=="string"?$(selector):selector;
            config = config || {};
            config.minuteStep = 1;
            config.showSeconds = true;
            config.showMeridian = false;
            selector.timepicker(config);
            selector.attr("readonly", true);
        },
        
        renderDateRangeMonth: function (selector, config) {
            selector = typeof(selector)=="string"?$(selector):selector;
            config = config || {};
            config.format = 'YYYY-MM';
            config.separator = ' ~ ';
            selector.daterangepicker(config);
            selector.attr("readonly", true);
        },

    };
});