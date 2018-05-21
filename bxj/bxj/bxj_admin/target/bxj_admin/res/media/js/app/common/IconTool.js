define(['jquery', 'app/common/winTool'], function ($, winTool) {

    return {
        show: function (config) {
            config = config || {};

            var win = winTool.create({
                    title: "选择图标",
                    okName: "保存",
                    cancelName: "关闭",
                    type: 'iframe',
                    url: serverHost + "/common/icons.jhtml",
                    width: 800,
                    height: 500,
                    showOk: false
                });
            if(config.callback) {
                win.find("iframe")[0].contentWindow.callback = function(iconName) {
                    config.callback(iconName);
                    win.close();
                };
            }
            //console.info(win.find("iframe")[0].contentWindow)
        }
    }
})