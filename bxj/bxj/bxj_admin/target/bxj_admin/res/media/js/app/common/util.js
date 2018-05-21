define(['jquery', 'util', 'app/common/messageTool'], function ($, util, messageTool) {

    var commonUtil = {
        request: function (config) {
            config = config || {};
            if(!!config.confirm) {
                messageTool.confirm(config.msg||"确定要处理？", function (flag) {
                    if(flag) {
                        if(config.confirmFn) {
                            config.confirmFn();
                        }
                        util.ajax(config);
                    }
                });
            } else {
                util.ajax(config);
            }
        }
    };

    return $.extend({}, util, commonUtil);
});