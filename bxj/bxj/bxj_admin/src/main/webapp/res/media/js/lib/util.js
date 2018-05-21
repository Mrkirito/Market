define(['jquery', 'moment'], function ($, moment) {

    /**
     *
     * @param config {
     *  url:'',
     *  params: {key: value},
     *  async: true|false, default: true,
     *  success: function(data){}
     * }
     */
    function ajax(config) {
        config = config || {};
        params = config.params || {};
        config.async = config.async == undefined ? true : config.async;
        $.ajax(config.url, {
            dataType: config.dataType||'json',
            data: config.params,
            async: config.async,
            type: config.method || "post",
        }).done(config.success).fail(function (resp) {
            console.log('fail', resp.status);
            if(config.failure) {
                config.failure(resp);
            }
        });
    }

    /**
     *
     * @param config {
     *  url:'',
     *  method: post|get, default:post
     *  dataType: script|json|jsonp|html|, default:json
     *  params: {},
     *  async: true|false, default: true,
     *  callback: function(data){}
     * }
     */
    function ajaxRequestNoConfirm(config) {
        config = config || {};
        config.async = config.async == undefined ? true : config.async;
        $.ajax({
            url: config.url || '',
            type: config.method || "post",
            dataType: config.dataType || "json",
            data: config.params || {},
            async: config.async,
            success: function (data) {
                if (config.callback) {
                    config.callback(data);
                }
            }
        });
    };

    var $id = function (id) {
        return document.getElementById(id);
    };

    /**
     * 回车时间绑定
     * @param selector
     * @param fn
     */
    var setEnterKey = function (selector, fn) {
        $(selector).on("keypress", function (e) {
            if (e.keyCode != 13)
                return;

            $(selector).attr("disabled", true);

            if (fn) {
                try {
                    fn();
                } catch (e) {
                }
            }

            $(selector).attr("disabled", false);
        });
    }

    /*var isIE = function () {
     $.browser.msie10 = $.browser.msie && /msie 10\.0/i.test(userAgent);
     $.browser.msie9 = $.browser.msie && /msie 9\.0/i.test(userAgent);
     $.browser.msie8 = $.browser.msie && /msie 8\.0/i.test(userAgent);
     $.browser.msie7 = $.browser.msie && /msie 7\.0/i.test(userAgent);
     $.browser.msie6 = !$.browser.msie8 && !$.browser.msie7 && $.browser.msie && /msie 6\.0/i.test(userAgent);
     }*/

//    isIE();

    function setPath(fnode, furl, snode) {
        if (snode && typeof(snode) != "undefined" && snode != 0) {
            var tmp = $("<li> <a href='" + furl + "'>" + fnode + "</a> </li> <li class='active'>" + snode + "</li>");
            $("#pathBar").append(tmp);
        } else {
            var tmp = $("<li class='active'>" + fnode + "</li>");
            $("#pathBar").append(tmp);
        }

    }

    /**
     * 格式化日期, 针对的事java.util.Date转化后的json对象
     * @param date
     * @param fmt yyyy-MM-dd hh:mm:ss.S ==> 2006-07-02 08:09:04.423 | yyyy-M-d h:m:s.S ==> 2006-7-2 8:9:4.18
     * @returns {*}
     */
    var dateFormat = function (dateJson, fmt) {
        if(!dateJson || !dateJson.time) {
            return "";
        }
        return dateFormat2(dateJson.time, fmt);
    };

    var dateFormat2 = function (date, fmt) {
        //fmt = fmt|| 'yyyy-MM-dd hh:mm:ss';
        fmt = fmt|| 'YYYY-MM-DD HH:mm:ss';
        if(!date ) {
            return "";
        }
        return moment(date).format(fmt);
        /*var date = new Date(date);
         var o = {
         "M+": date.getMonth() + 1, //月份
         "d+": date.getDate(), //日
         "h+": date.getHours(), //小时
         "m+": date.getMinutes(), //分
         "s+": date.getSeconds(), //秒
         "q+": Math.floor((date.getMonth() + 3) / 3), //季度
         "S": date.getMilliseconds() //毫秒
         };
         if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
         for (var k in o)
         if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
         return fmt;*/
    }


    return {
        ajax: ajax,
        ajaxRequestNoConfirm: ajaxRequestNoConfirm,
        setEnterKey: setEnterKey,
        setPath: setPath,
        dateFormat: dateFormat,
        dateFormat2: dateFormat2,

        contains: function (array, val) {
            var res = false;
            array = array || [];
            $(array).each(function () {
                if(this == val) {
                    res = true;
                    return false;
                }
            });
            return res;
        }
    };
});