define(['jquery', 'app/common/winTool',
    'app/common/toastrTool'],
    function ($, winTool, toastrTool) {

    var defaults = {
        html: "空白",
        fn: null,
        showTitle: false,
        showCancel: false,
        type: 'html'
    };

    return {
        alert: function (msg, fn) {
            var config = {
                html: msg,
                okFn: function (win) {
                    win.close();
                    fn && fn();
                }
            };
            config = $.extend({}, defaults, config);
            var win = winTool.create(config);
            $(win.$modalElement).addClass("message-box");
        },

        success: function (msg, fn) {
            toastrTool.show({
                title: "提示",
                msg: msg
            });
            fn && fn();
        },

        info: function (msg, fn) {
            toastrTool.show({
                title: "提示",
                msg: msg,
                type: 'info'
            });
            fn && fn();
        },

        error: function (msg, fn) {
            toastrTool.show({
                title: "错误",
                msg: msg,
                type: 'error'
            });
            fn && fn();
        },

        warning: function (msg, fn) {
            toastrTool.show({
                title: "警告",
                msg: msg,
                type: 'warning'
            });
            fn && fn();
        },

        confirm: function (msg, fn) {
            var config = {
                html: msg,
                showOk: false,
                buttons: [{
                    name: 'Cancel',
                    cls: 'btn-default',
                    handler: function (win) {
                        win.close();
                        fn && fn(false);
                    }
                }, {
                    name: 'OK',
                    handler: function (win) {
                        win.close();
                        fn && fn(true);
                    }
                }]
            };
            config = $.extend({}, defaults, config);
            var win = winTool.create(config);
            $(win.$modalElement).addClass("message-box");
        },
        confirm2: function (msg, fn) {
            var config = {
                html: msg,
                showOk: false,
                buttons: [{
                    name: 'Cancel',
                    cls: 'btn-default',
                    handler: function (win) {
                        win.close();
                    }
                }, {
                    name: 'OK',
                    handler: function (win) {
                        win.close();
                        fn && fn(true);
                    }
                }]
            };
            config = $.extend({}, defaults, config);
            var win = winTool.create(config);
            $(win.$modalElement).addClass("message-box");
        },

        prompt: function (msg, fn) {
            var config = {
                showTitle: true,
                title: msg,
                showOk: false,
                html: '<input class="message-input  form-control" autocomplete="off" type="text">',
                buttons: [{
                    name: 'Cancel',
                    cls: 'btn-default',
                    handler: function (win) {
                        var text = win.find(".message-input").val();
                        win.close();
                        fn && fn(false, text);
                    }
                }, {
                    name: 'OK',
                    handler: function (win) {
                        var text = win.find(".message-input").val();
                        win.close();
                        fn && fn(true, text);
                    }
                }]
            };
            config = $.extend({}, defaults, config);
            var win = winTool.create(config);
            $(win.$modalElement).addClass("message-box");
        }
    };

});