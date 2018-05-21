define(['jquery',
    'plugins/bootstrap-modal/js/bootstrap-modal',
], function ($) {

    var defaults = {
            title: '标题',
            type: 'html', // type: html, selector, iframe
            selector: null, // type = selector时生效
            html: null, // type = html时生效
            url: null, // type = iframe时生效
            backdrop: true,
            showCancel: true,
            cancelName: 'Close',
            showOk: true,
            okName: "OK",
            width: null,
            height: null,
            showTitle: true,
            showButtons: true,
            overflow:null,
            okFn: function (win) {},
            closeFn: function (win) {},
            buttons: []// {name: '测试', cls: '', handler: function(win){}}
        },

        winTmpl =
            '<div class="modal fade" id="" tabindex="-1" >'+
                //'<div class="modal-dialog">'+
                //    '<div class="modal-content">'+
                        '<div class="modal-header">'+
                            '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'+
                            '<h4 class="modal-title" ></h4>'+
                        '</div>'+
                        '<div class="modal-body">'+
                            '<button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="display: none;">&times;</button>' +
                        '</div>'+
                        '<div class="modal-footer">'+
                            '<button type="button" class="btn btn-default modal-cancel" data-dismiss="modal"></button>'+
                            '<button type="button" class="btn btn-primary modal-ok"></button>'+
                        '</div>'+
                    //'</div>'+
                //'</div>'+
            '</div>',
        btnTmpl = '<button type="button" class="btn "></button>',
        iframeTmpl = '<iframe src="" style="border: 0px; width: 100%; height: 100%;"></iframe>';

    return {
        create: function (config) {
            config = config || {};
            config = $.extend({}, defaults, config);

            var winTool = {
                init: function (opts) {
                    var thiz = this;
                    thiz.id = thiz.generateId();
                    thiz.opts = opts;
                    thiz.$modalElement = $(winTmpl).appendTo("body");

                    thiz.$modalElement.attr("id", thiz.id);

                    thiz.$header = thiz.$modalElement.find(".modal-header");
                    thiz.$title = thiz.$modalElement.find(".modal-title");
                    thiz.$content = thiz.$modalElement.find(".modal-body");
                    thiz.$footer = thiz.$modalElement.find(".modal-footer");
                    thiz.$cancel = thiz.$modalElement.find(".modal-cancel");
                    thiz.$ok = thiz.$modalElement.find(".modal-ok");
                    thiz.$close = thiz.$modalElement.find(".close");

                    thiz.initButtons();

                    thiz.initWin();
                    return thiz;
                },

                initWin: function () {

                    var thiz = this;

                    thiz.$title.html(thiz.opts.title);

                    thiz.$header.css("display", !thiz.opts.showTitle?"none":"");

                    thiz.$content.find(".close").css("display", !thiz.opts.showTitle?"":"none")

                    var winConfig = {};
                    if(thiz.opts.width) {
                        winConfig.width = thiz.opts.width;
                    }

                    if(thiz.opts.height) {
                        winConfig.height = thiz.opts.height;
                    }

                    switch (thiz.opts.type) {
                        case 'html':
                            $('<div style="text-align: left;">' + thiz.opts.html + '</div>').appendTo(thiz.$content);
                            break;
                        case 'selector':
                            var selector = $($(thiz.opts.selector).html()).appendTo(thiz.$content);
                            if($(selector).css("display") == "none") {
                                $(selector).css("display", 'block');
                            }
                            break;
                        case 'iframe':
                            if(thiz.opts.url) {
                                thiz.iframe = $(iframeTmpl).appendTo(thiz.$content);
                                $(thiz.iframe).attr("src", thiz.opts.url);
                                $(thiz.$content).css("padding", "0");
                                winConfig.overflow = 'hidden';
                            }
                    }

                    if(thiz.opts.overflow) {
                        winConfig.overflow = thiz.opts.overflow;
                    }

                    $(thiz.$modalElement).modal(winConfig);

                },

                initButtons: function () {
                    var thiz = this;

                    thiz.$footer.css("display", !thiz.opts.showButtons?"none":"");

                    thiz.$ok.html(thiz.opts.okName);
                    thiz.$cancel.html(thiz.opts.cancelName);

                    if(!thiz.opts.showOk) {
                        thiz.$ok.css("display", "none");
                    }
                    if(!thiz.opts.showCancel) {
                        thiz.$cancel.css("display", "none");
                    }

                    if(thiz.opts.backdrop) {
                        thiz.$modalElement.data("backdrop", 'static');
                    }

                    $(thiz.$ok).on("click", function () {
                        if(thiz.opts.okFn) {
                            thiz.opts.okFn(thiz);
                        }
                    });

                    $(thiz.$close).on("click", function () {
                        if(thiz.opts.closeFn) {
                            thiz.opts.closeFn(thiz);
                        }
                    });

                    $(thiz.$cancel).on("click", function () {
                        if(thiz.opts.closeFn) {
                            thiz.opts.closeFn(thiz);
                        }
                        // $('.modal-scrollable').remove();
                        // $('.modal-backdrop').remove();
                    });

                    if(thiz.opts.buttons) {
                        $.each(thiz.opts.buttons, function (idx, button) {
                            var btn = $(btnTmpl).appendTo(thiz.$footer),
                                cls = button.cls||'btn-primary';
                            btn.addClass(cls);

                            btn.html(button.name||"未命名");
                            $(btn).on("click", function () {
                                if(button.handler) {
                                    button.handler(thiz);
                                }
                            })
                        });
                    }
                },

                generateId: function () {
                    return "bs-modal-" + new Date().getTime();
                },

                findModal: function () {
                    return $(this.$modalElement).data("modal");
                },

                getChildWindow: function () {
                    if(this.iframe && this.iframe.length > 0) {
                        return this.iframe[0].contentWindow;
                    }

                    return null;
                },

                close: function () {
                    var $modal = this.findModal();
                    if($modal) {
                        $modal.destroy();
                    }

                },

                /*show: function () {
                    var $modal = this.findModal();
                    if($modal) {
                        $modal.show();
                    }
                },

                hide: function () {
                    var $modal = this.findModal();
                    if($modal) {
                        $modal.hide();
                    }
                },*/

                find: function (selector) {
                    return this.$content.find(selector);
                },

                hideOk: function () {
                    $(this.$ok).css("display", "none");
                }
            };

            return winTool.init(config);
        }
    };
});