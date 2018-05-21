define(['jquery',
        'plugins/toastr/toastr',
        'css!global/js/lib/plugins/toastr/toastr.css'],
    function ($, toastr) {

    var position = {
            TopRight: 'toast-top-right',
            TopLeft: 'toast-top-left',
            TopCenter: 'toast-top-center',
            TopFullWidth: 'toast-top-full-width',
            BootomRight: 'toast-bottom-right',
            BootomLeft: 'toast-bottom-left',
            BootomCenter: 'toast-bottom-center',
            BootomFullWidth: 'toast-bottom-full-width'
        },

        defaults = {
            title: null,
            msg: "空白",
            closeButton: true,
            debug: false,
            progressBar: true,
            //position: 'TopRight',
            position: 'TopCenter',
            onclick: null,
            showDuration: 1000,
            hideDuration: 1000,
            timeOut: 5000,
            extendedTimeOut: 1000,
            //showEasing: 'swing',
            //hideEasing: 'swing',
            //showMethod: 'slideDown',
            //showMethod: 'fadeIn',
            //hideMethod: 'fadeOut',
            type: 'success' // success, warning, error, info
        };

    return {
        show: function (config) {
            config = config || {};
            config = $.extend({}, defaults, config);
            //config.positionClass = position[config.position]||'toast-top-right';

            var $toast = toastr[config.type](config.msg, config.title, config);
            return $toast;
        }
    };
});