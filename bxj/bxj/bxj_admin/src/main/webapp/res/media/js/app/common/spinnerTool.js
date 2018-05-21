define(['jquery', 'plugins/fuelux/spinner'], function ($, _) {

    return {
        spinner: function (selector, config) {
            config = config || {};
            $(selector).spinner(config);
        }
    };
})