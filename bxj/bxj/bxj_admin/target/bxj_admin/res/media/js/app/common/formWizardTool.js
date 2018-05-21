define(['jquery', 'app/omc',
    'plugins/bootstrap-wizard/jquery.bootstrap.wizard',
    'css!global/css/amc/form.wizard.css'
], function ($, omc, _, _) {

    var defaults = {
        formWizardSelector: null,
        formSelector: null,
        nextSelector: null,
        previousSelector: null,
        progressBarSelector: null,
        submitSelector: null,
        confirmSelector: null,
        titleSelector: null,
        showIndex: 1,
        onNext: function (tab, navigation, index){return false},
        onPrevious: function (tab, navigation, index){return false},
        onTabShow: function (tab, navigation, index){return false},
        changeTitle: function (index, total) {

        },
        displayConfirm: function () {
            
        }
    };

    var wizard = {
        init: function (config) {
            config = config || {};

            this.opts = $.extend({}, defaults, config);

            if($().bootstrapWizard) {
                this.initWizard();
            }
        },

        initWizard: function () {
            var thiz = this,
                opts = thiz.opts;

            $(opts.formWizardSelector).bootstrapWizard({
                'nextSelector': opts.nextSelector,
                'previousSelector': opts.previousSelector,
                onTabClick: function (tab, navigation, index, clickedIndex) {
                    if(opts.onTabClick) {
                        return !!opts.onTabClick(tab, navigation, index, clickedIndex);
                    }
                    return false;
                },
                onNext: function (tab, navigation, index) {
                    if(opts.onNext) {
                        var res = opts.onNext(tab, navigation, index+1),
                            res = undefined == res?true:!!res;
                        if(!res) {
                            return false;
                        }
                    }

                    thiz.handleTitle(tab, navigation, index);
                },
                onPrevious: function (tab, navigation, index) {
                    if(opts.onPrevious) {
                        opts.onPrevious(tab, navigation, index+1);
                    }

                    thiz.handleTitle(tab, navigation, index);
                },
                onTabShow: function (tab, navigation, index) {
                    var total = navigation.find('li').length,
                        current = index + 1,
                        $percent = (current / total) * 100;

                    if(opts.onTabShow) {
                        opts.onTabShow(tab, navigation, current, $percent, total);
                    }

                    if(opts.progressBarSelector) {
                        $(opts.formWizardSelector).find(opts.progressBarSelector).css({
                            width: $percent + '%'
                        });
                    }
                }
            });
            var bootstrapWizard = $(opts.formWizardSelector).data("bootstrapWizard");
            if(bootstrapWizard) {
                var showIndex = opts.showIndex;
                if(showIndex <=0) {
                    showIndex = 1;
                }
                bootstrapWizard.show(showIndex-1);
            }
        },

        handleTitle: function(tab, navigation, index) {
            var thiz = this,
                opts = thiz.opts,
                total = navigation.find('li').length,
                current = index + 1;
            // set wizard title
            if(thiz.changeTitle) {
                thiz.changeTitle(current, total);
            }

            // set done steps
            $('li', opts.formWizardSelector).removeClass("done");
            var li_list = navigation.find('li');
            for (var i = 0; i < index; i++) {
                $(li_list[i]).addClass("done");
            }

            if (current == 1) {
                $(opts.formWizardSelector).find(opts.previousSelector).hide();
            } else {
                $(opts.formWizardSelector).find(opts.previousSelector).show();
            }

            if (current >= total) {
                $(opts.formWizardSelector).find(opts.nextSelector).hide();
                $(opts.formWizardSelector).find(opts.submitSelector).show();
                if(opts.displayConfirm) {
                    opts.displayConfirm();
                }
            } else {
                $(opts.formWizardSelector).find(opts.nextSelector).show();
                $(opts.formWizardSelector).find(opts.submitSelector).hide();
            }
            if(opts.titleSelector) {
                omc.scrollTo($(opts.titleSelector));
            }
        },

        displayConfirm: function() {
            if(this.opts.formSelector) {
                var form = $(this.opts.formSelector);
                $(".form-control-static", form).each(function(){
                    var input = $('[name="'+$(this).attr("data-display")+'"]', form);
                    if (input.is(":radio")) {
                        input = $('[name="'+$(this).attr("data-display")+'"]:checked', form);
                    }
                    if (input.is(":text") || input.is("textarea")) {
                        $(this).html(input.val());
                    } else if (input.is("select")) {
                        $(this).html(input.find('option:selected').text());
                    } else if (input.is(":radio") && input.is(":checked")) {
                        $(this).html(input.attr("data-title"));
                    } else if ($(this).attr("data-display") == 'payment[]') {
                        var payment = [];
                        $('[name="payment[]"]:checked', form).each(function(){
                            payment.push($(this).attr('data-title'));
                        });
                        $(this).html(payment.join("<br>"));
                    }
                });
            }

        }
    };

    return wizard;
});