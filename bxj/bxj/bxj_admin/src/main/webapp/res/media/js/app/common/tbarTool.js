define(['jquery'], function ($) {

    var defaults = {
            toolbarSelector: '.top-toolbar',
            tbars: []
        },
        abtnHtmls = ['<a href="$btnUrl$">',
            '<i class="$icon$"></i>',
            '$btnName$',
            '</a>'],
        btnHtmls = [
            '<a >',
            '<i class="$icon$"></i>',
            '$btnName$',
            '</a>'];

    return {
        /**
         * [{
         *  name: "",
         *  icon: "",
         *  url: "",
         *  hanlder: function(){}
         * }]
         * @param config
         * @returns {*}
         */
        create: function (config) {
            var toolbar = {
                init: function (option) {
                    option = option || {};
                    this.opts = $.extend({}, defaults, option);
                    this.toolbar = $(this.opts.toolbarSelector);
                    this.createTbar();
                    return this;
                },

                createTbar: function () {
                    var thiz = this;
                    thiz.buttons = [];
                    $(thiz.opts.tbars).each(function (idx, button) {
                        if(thiz.hasRight(button)) {
                            if (button.url) {
                                var abtnHtml = abtnHtmls.join("").replace("$icon$", button.icon || "").replace("$btnName$", button.name || "").replace("$btnUrl$", button.url);
                                var btn = $(abtnHtml).appendTo(thiz.toolbar);
                                if (button.cls) {
                                    $(btn).addClass(button.cls);
                                }
                                thiz.buttons.push(btn);
                            } else {
                                var btnHtml = btnHtmls.join("").replace("$icon$", button.icon || "").replace("$btnName$", button.name || "");
                                var btn = $(btnHtml).appendTo(thiz.toolbar);
                                if (button.cls) {
                                    $(btn).addClass(button.cls);
                                }
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
                 * 判断是否有按钮权限
                 * @param button
                 * @returns {boolean}
                 */
                hasRight: function (button) {
                    var userRights = eval($("._u_f_").val())||[],
                        hasRight = false;
                    if(button.rightCode) {
                        $(userRights).each(function () {
                            if(this.rightCode == button.rightCode && !this.disabled) {
                                hasRight = true;
                                return false;
                            }
                        });
                    } else {
                        hasRight = true;
                    }
                    return hasRight;
                }
            };

            return toolbar.init(config);
        }
    };
});