define(['jquery'], function ($) {
    return {
        init: function () {
            var thiz = this;
            $(".icons-list a").on("click", function () {
                var icon = $(this).find("i");
                if(icon && icon.length > 0) {
                    thiz.handler(icon);
                }
            });
/*
            $(".bs-glyphicons-list").on("click", "li", function () {
                var icon = $(this).find(".glyphicon");
                if(icon && icon.length > 0) {
                    thiz.handler(icon);
                }
            });

            $(".simplelineicons-demo").on("click", ".item-box .item", function () {
                var icon = $(this).find("span");
                if(icon && icon.length > 0) {
                    thiz.handler(icon);
                }
            })*/
        },

        handler: function (icon) {
            var iconName = icon.attr("class");
            if(window.callback) {
                window.callback(iconName);
            }
        }
    };
});