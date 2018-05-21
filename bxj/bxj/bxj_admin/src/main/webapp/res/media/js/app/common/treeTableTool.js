define(['jquery', 'plugins/treetable/jquery.treetable'], function ($) {


    return {
        init: function (selector, config) {
            config = config||{};
            return $(selector).treetable(config);
        },

        destroy: function (selector) {
            $(selector).treetable("destroy");
        },

        expandAll: function (selector) {
            $(selector).treetable("expandAll");
        }
    }
})