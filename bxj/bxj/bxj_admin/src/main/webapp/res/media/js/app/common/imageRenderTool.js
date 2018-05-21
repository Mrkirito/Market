define(["jquery",
    'css!global/css/upload.css'
], function ($) {
    var clsName = "upload-file-list",
        tpl = '<div class="file" data=""> <div class="file-details"> ' +
            '<img src="" ></div> ' +
            '<span class="name"></span> ' +
            '<a class="file-remove">删除</a></div>';

    return {
        render: function (selector, dataList, clear) {
            clear = !!clear;
            dataList = dataList || [];
            $(selector).addClass(clsName);
            this.registerEvent(selector);
            if(clear) {
                $(selector).html("");
            }

            $(dataList).each(function () {
                var node = $(tpl);
                node.data(this);
                node.find("img").attr('src', this.fileUrl||"");
                node.find(".name").html(this.oldFileName||"");
                node.appendTo(selector);
            });
        },

        getData: function (selector) {
            var dataList = [];
            $(selector).find(".file").each(function () {
                var data = $(this).data();
                dataList.push(data);
            });
            return dataList;
        },

        registerEvent: function (selector) {
            $(selector).on("click", ".file-remove", function () {
                $(this).parent().remove();
            });
        }
    }

});