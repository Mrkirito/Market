define(['jquery',
    //'app/nifty',
    'plugins/switchery/switchery',
    'plugins/bootstrap-select/bootstrap-select',
    'plugins/jquery.cookie',

    'app/common/selectTool',
    'app/common/messageTool',
    'app/common/util'
], function ($, Switchery, _, _,
             selectTool, messageTool, util
) {

    var feedbackList = $(".feedbackNotify").data("feed")||[];
    //console.info(feedbackList)

    // SETTINGS WINDOW
    // =================================================================

    var demoSetBody = $('#demo-set');
    var demoSetIcon = $('#demo-set-icon');
    var demoSetBtnGo = $('#demo-set-btngo');

    if (demoSetBody.length) {
        $('html').on('click', function (e) {
            if (demoSetBody.hasClass('open')) {
                if (!$(e.target).closest('#demo-set').length) {
                    demoSetBody.removeClass('open');
                }
            }
        });
        $('#demo-set-btn').on('click', function (e) {
            e.stopPropagation();
            demoSetBody.toggleClass('open');
        });

        function are_cookies_enabled() {
            var cookieEnabled = (navigator.cookieEnabled) ? true : false;
            if (typeof navigator.cookieEnabled == "undefined" && !cookieEnabled) {
                document.cookie = "testcookie";
                cookieEnabled = (document.cookie.indexOf("testcookie") != -1) ? true : false;
            }
            return (cookieEnabled);
        }

        if (are_cookies_enabled() == false) {
            $.niftyNoty({
                type: 'danger',
                message: "Your browser's <strong>cookie</strong> functionality is turned off. Some settings won\'t work as expected....",
                container: '#demo-set-alert',
                closeBtn: false
            });

            $('#demo-set').addClass('no-cookie');
        }

    };






    // TRANSITION EFFECTS
    // =================================================================
    // =================================================================
    var effectList = 'easeInQuart easeOutQuart easeInBack easeOutBack easeInOutBack steps jumping rubber',
        animCheckbox = $('#demo-anim'),
        transitionVal = $('#demo-ease');

    // Animations checkbox
    animCheckbox.on('change', function () {
        if (animCheckbox.niftyCheck('isChecked')) {
            nifty.container.addClass('effect');
            transitionVal.prop('disabled', false).selectpicker('refresh');
            setCookie('settings-animation', 'effect');
        } else {
            nifty.container.removeClass('effect ' + effectList);
            transitionVal.prop('disabled', true).selectpicker('refresh');
            setCookie('settings-animation', 'none');
        }
    });


    // Transition selectbox
    transitionVal.selectpicker().on('change', function (e) {
        var optionSelected = $("option:selected", this);
        var valueSelected = this.value;

        if (valueSelected) {
            nifty.container.removeClass(effectList).addClass(valueSelected);
            setCookie('settings-animation', valueSelected);
        }
    });









    // NAVIGATION
    // =================================================================
    // =================================================================
    var collapsedCheckbox = $('#demo-nav-coll');
    var navFixedCheckbox = $('#demo-nav-fixed');
    var navOffcanvasSB = $('#demo-nav-offcanvas');


    // Collapsing/Expanding Navigation
    // =================================================================
    collapsedCheckbox.on('change', function () {
        if ($.cookie('settings-nav-offcanvas')) {
            setCookie('settings-nav-offcanvas', false);
            setCookie('settings-nav-collapsed', true);
            demoSetBody.removeClass('open');
            location.reload(true);
            return false;
        }


        //$.niftyNav('colExpToggle');
        // $.niftyNav('colExpToggle', function(){...Callback..});



        if (collapsedCheckbox.niftyCheck('isChecked')) {
            $.niftyNav('collapse');
            setCookie('settings-nav-collapsed', true);
        } else {
            $.niftyNav('expand');
            setCookie('settings-nav-collapsed', false);
        }
    });





    // Fixed Position
    // =================================================================
    navFixedCheckbox.on('change', function () {
        if (navFixedCheckbox.niftyCheck('isChecked')) {
            $.niftyNav('fixedPosition');
            setCookie('settings-nav-fixed', true);
        } else {
            $.niftyNav('staticPosition');
            setCookie('settings-nav-fixed', false);
        }
    });





    // Offcanvas Navigation
    // =================================================================
    navOffcanvasSB.selectpicker().on('change', function () {
        setCookie('settings-nav-collapsed', false)
        setCookie('settings-nav-offcanvas', this.value);
        demoSetBody.removeClass('open');
        location.reload(true);
    }).selectpicker('val', $.cookie('settings-nav-offcanvas'));











    // ASIDE
    // =================================================================
    // =================================================================
    var asdVisCheckbox = $('#demo-asd-vis');
    var asdFixedCheckbox = $('#demo-asd-fixed');
    var asdPosCheckbox = $('#demo-asd-align');
    var asdThemeCheckbox = $('#demo-asd-themes');


    // Visible
    // =================================================================
    asdVisCheckbox.on('change', function () {
        if (asdVisCheckbox.niftyCheck('isChecked')) {
            $.niftyAside('show');
            setCookie('settings-asd-visibility', true);
        } else {
            $.niftyAside('hide');
            setCookie('settings-asd-visibility', false);
        }
    });







    // Fixed Position
    // =================================================================
    asdFixedCheckbox.on('change', function () {
        if (asdFixedCheckbox.niftyCheck('isChecked')) {
            $.niftyAside('fixedPosition');
            setCookie('settings-asd-fixed', true);
        } else {
            $.niftyAside('staticPosition');
            setCookie('settings-asd-fixed', false);
        };
    });






    // Position
    // =================================================================
    asdPosCheckbox.on('change', function () {
        if (asdPosCheckbox.niftyCheck('isChecked')) {
            $.niftyAside('alignLeft');
            setCookie('settings-asd-align', 'left');
        } else {
            $.niftyAside('alignRight');
            setCookie('settings-asd-align', 'right');
        };
    });







    // Color Themes
    // =================================================================
    asdThemeCheckbox.on('change', function () {
        if (asdThemeCheckbox.niftyCheck('isChecked')) {
            $.niftyAside('brightTheme');
            setCookie('settings-asd-theme', 'bright');
        } else {
            $.niftyAside('darkTheme');
            setCookie('settings-asd-theme', 'dark');
        };
    });









    // NAVBAR
    // =================================================================
    // =================================================================
    var navbarFixedCheckbox = $('#demo-navbar-fixed');

    // Fixed Position
    // =================================================================
    navbarFixedCheckbox.on('change', function () {
        if (navbarFixedCheckbox.niftyCheck('isChecked')) {
            nifty.container.addClass('navbar-fixed');
            setCookie('settings-navbar-fixed', true);
        } else {
            nifty.container.removeClass('navbar-fixed');
            setCookie('settings-navbar-fixed', false);
        }

        // Refresh the aside, to enable or disable the "Bootstrap Affix" when the navbar is in a "static position".
        nifty.mainNav.niftyAffix('update');
        nifty.aside.niftyAffix('update');
    });









    // FOOTER
    // =================================================================
    // =================================================================
    var footerFixedCheckbox = $('#demo-footer-fixed');

    // Fixed Position
    // =================================================================
    footerFixedCheckbox.on('change', function () {
        if (footerFixedCheckbox.niftyCheck('isChecked')) {
            nifty.container.addClass('footer-fixed');
            setCookie('settings-footer-fixed', true);
        } else {
            nifty.container.removeClass('footer-fixed');
            setCookie('settings-footer-fixed', false);
        }
    });









    // COLOR THEMES
    // =================================================================
    var themeBtn = $('.demo-theme'),
        changeTheme = function (themeName, type) {
            var themeCSS = $('#theme'),
            
            	filename = serverHost + '/res/media/css/themes/type-' + type + '/' + themeName + '.min.css';
//                filename = cdnURI + 'css/themes/type-' + type + '/' + themeName + '.min.css';

            if (themeCSS.length) {
                themeCSS.prop('href', filename);
            } else {
                themeCSS = '<link id="theme" href="' + filename + '" rel="stylesheet">';
                $('head').append(themeCSS);
            }
            setCookie('settings-theme-name', themeName);
            setCookie('settings-theme-type', type);
        };


    $('#demo-theme').on('click', '.demo-theme', function (e) {
        e.preventDefault();
        var el = $(this);
        if (el.hasClass('disabled')) {
            return false;
        }
        changeTheme(el.attr('data-theme'), el.attr('data-type'));
        themeBtn.removeClass('disabled');
        el.addClass('disabled');
        return false;
    });









    // LANGUAGE SWITCHER
    // =================================================================
    // Require Admin Core Javascript
    // http://www.themeOn.net
    // =================================================================
    /*$('#demo-lang-switch').niftyLanguage({
        onChange: function (e) {
            $.niftyNoty({
                type: 'info',
                icon: 'fa fa-info fa-lg',
                title: 'Language changed',
                message: 'The language apparently changed, the selected language is : <strong> ' + e.id + ' ' + e.name + '</strong> '
            });
        }
    });*/









    var elems = Array.prototype.slice.call(document.querySelectorAll('.demo-switch'));
    elems.forEach(function (html) {
        var switchery = new Switchery(html);
    });









    // GENERATE RANDOM ALERT
    // =================================================================
    // Require Admin Core Javascript
    // http://themeon.net
    // =================================================================

    var dataAlert = [{
        icon: 'fa fa-info fa-lg',
        title: "Info",
        type: "info"
    }, {
        icon: 'fa fa-star fa-lg',
        title: "Primary",
        type: "primary"
    }, {
        icon: 'fa fa-thumbs-up fa-lg',
        title: "Success",
        type: "success"
    }, {
        icon: 'fa fa-bolt fa-lg',
        title: "Warning",
        type: "warning"
    }, {
        icon: 'fa fa-times fa-lg',
        title: "Danger",
        type: "danger"
    }, {
        icon: 'fa fa-leaf fa-lg',
        title: "Mint",
        type: "mint"
    }, {
        icon: 'fa fa-shopping-cart fa-lg',
        title: "Purple",
        type: "purple"
    }, {
        icon: 'fa fa-heart fa-lg',
        title: "Pink",
        type: "pink"
    }, {
        icon: 'fa fa-sun-o fa-lg',
        title: "Dark",
        type: "dark"
    }
    ];



    // GROWL LIKE NOTIFICATIONS
    // =================================================================
    // Require Admin Core Javascript
    // =================================================================
    $('#demo-alert').on('click', function (ev) {
        ev.preventDefault();
        /*var dataNum = nifty.randomInt(0, 8);


        $.niftyNoty({
            type: dataAlert[dataNum].type,
            icon: dataAlert[dataNum].icon,
            title: dataAlert[dataNum].title,
            message: "Lorem ipsum dolor sit amet.",
            container: 'floating',
            timer: 3500
        });*/
    });






    // ALERT ON TOP PAGE
    // =================================================================
    // Require Admin Core Javascript
    // =================================================================

    // Show random page alerts.
    var idx = 0,
        max = feedbackList.length;

    $('#demo-page-alert').on('click', function (ev) {
        ev.preventDefault();

        var feed = feedbackList[idx],
            title = '意见反馈',
            msg =  '<a href="' + serverHost + 'feedback/feedbackDetail.htm?id=' + feed.id + '">' +
                feed.cpName + ' ' +  feed.createTime + '<br>' + feed.content
                + '</a>';
        $.niftyNoty({
            type: 'info',
            icon: 'fa fa-comment fa-lg',
            //icon: 'fa fa-info fa-lg',
            title: title,
            message: msg,
            timer: 5000
        });

        if(idx == max-1) {
            idx = 0;
        } else {
            idx++;
        }

        /*var dataNum = nifty.randomInt(0, 8),
            timer = function () {
                if (nifty.randomInt(0, 5) < 4) return 3000
                return 0;
            }();



        // Show random page alerts.
        $.niftyNoty({
            type: dataAlert[dataNum].type,
            icon: dataAlert[dataNum].icon,
            title: function () {
                if (timer > 0) {
                    return 'Autoclose Alert'
                }
                return 'Sticky Alert Box'
            }(),
            message: 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.',
            timer: timer
        });*/


    });






    // ASIDE
    // =================================================================
    // Toggle Visibe
    // =================================================================
    $('#demo-toggle-aside').on('click', function (ev) {
        /*ev.preventDefault();
        if (!nifty.container.hasClass('aside-in')) {
            $.niftyAside('show');
            asdVisCheckbox.niftyCheck('toggleOn')
        } else {
            $.niftyAside('hide');
            asdVisCheckbox.niftyCheck('toggleOff')
        }*/
    });









    // INIT - Check and apply all settings are available.
    // =================================================================

    if (typeof window.demoLayout !== 'undefined') return;


    var cookieList = [
            'settings-animation',
            'settings-nav-fixed',
            'settings-nav-collapsed',
            'settings-nav-offcanvas',
            'settings-asd-visibility',
            'settings-asd-fixed',
            'settings-asd-align',
            'settings-asd-theme',
            'settings-navbar-fixed',
            'settings-footer-fixed',
            'settings-theme-type',
            'settings-theme-name'
        ],
        setCookie = function (name, value) {
            if (value == false) {
                $.removeCookie(name, {
                    path: '/'
                });
            } else {
                $.cookie(name, ((value === true) ? '1' : value), {
                    expires: 7,
                    path: '/'
                });
            }
        },
        removeAllCookie = function () {
            for (var i = 0; i < cookieList.length; i++) {
                $.removeCookie(cookieList[i], {
                    path: '/'
                });
            }
        };


    // Reser all settings
    $('#demo-reset-settings').on('click', function () {
        animCheckbox.niftyCheck('toggleOn');
        nifty.container.removeClass(effectList).addClass('effect');
        transitionVal.selectpicker('val', 'effect');


        navFixedCheckbox.niftyCheck('toggleOff');
        $.niftyNav('staticPosition');

        collapsedCheckbox.niftyCheck('toggleOff');
        $.niftyNav('expand');

        nifty.container.removeClass('mainnav-in mainnav-out mainnav-sm');
        navOffcanvasSB.selectpicker('val', 'none');


        asdVisCheckbox.niftyCheck('toggleOff');
        $.niftyAside('hide');


        asdFixedCheckbox.niftyCheck('toggleOff');
        $.niftyAside('staticPosition');

        asdPosCheckbox.niftyCheck('toggleOff');
        $.niftyAside('alignRight');


        asdThemeCheckbox.niftyCheck('toggleOff');
        $.niftyAside('darkTheme');


        navbarFixedCheckbox.niftyCheck('toggleOff');
        nifty.container.removeClass('navbar-fixed');
        nifty.mainNav.niftyAffix('update');
        nifty.aside.niftyAffix('update');

        footerFixedCheckbox.niftyCheck('toggleOff');
        nifty.container.removeClass('footer-fixed');


        //changeTheme('theme-navy', 'mainnav');
        $('#theme').remove();

        $('.demo-theme').removeClass('disabled').filter('[data-type="mainnav"]').filter('[data-theme="theme-navy"]').addClass('disabled');

        removeAllCookie();

        $.niftyNoty({
            icon: 'fa fa-check fa-lg',
            type: 'success',
            message: "All settings has been restored to the factory default values.",
            container: '#demo-set-alert',
            timer: 4000
        });


    });



    // Animation cookie
    if ($.cookie('settings-animation')) {
        if ($.cookie('settings-animation') == 'none') {
            nifty.container.removeClass('effect ' + effectList);
            animCheckbox.niftyCheck('toggleOff');
            transitionVal.prop('disabled', true).selectpicker('refresh');
        } else {
            animCheckbox.niftyCheck('toggleOn');
            nifty.container.addClass('effect ' + $.cookie('settings-animation'));
            transitionVal.selectpicker('val', $.cookie('settings-animation'))
        }
    }




    // Fixed navigation
    if ($.cookie('settings-nav-fixed') == 1 || nifty.container.hasClass('mainnav-fixed')) {
        navFixedCheckbox.niftyCheck('toggleOn');
        $.niftyNav('fixedPosition');
    } else {
        navFixedCheckbox.niftyCheck('toggleOff');
        $.niftyNav('staticPosition');
    };





    // Collapsed navigation
    if ($.cookie('settings-nav-collapsed') == 1) {
        collapsedCheckbox.niftyCheck('toggleOn');
        $.niftyNav('collapse');
        $('.mainnav-toggle').removeClass('push slide reveal')
    } else {
        collapsedCheckbox.niftyCheck('toggleOff');
        if ($.cookie('settings-nav-offcanvas')) {
            nifty.container.removeClass('mainnav-in mainnav-sm mainnav-lg');
            $.niftyNav($.cookie('settings-nav-offcanvas') + 'Out');
            $('.mainnav-toggle').removeClass('push slide reveal').addClass($.cookie('settings-nav-offcanvas'));
        }
    };



    if (nifty.container.hasClass('aside-in')) {
        asdVisCheckbox.niftyCheck('toggleOn');
    } else {
        asdVisCheckbox.niftyCheck('toggleOff');
    }



    if (nifty.container.hasClass('aside-fixed')) {
        asdFixedCheckbox.niftyCheck('toggleOn');
    } else {
        asdFixedCheckbox.niftyCheck('toggleOff');
    }


    if (nifty.container.hasClass('aside-left')) {
        asdPosCheckbox.niftyCheck('toggleOn');
    } else {
        asdPosCheckbox.niftyCheck('toggleOff');
    }


    if (nifty.container.hasClass('aside-left')) {
        asdThemeCheckbox.niftyCheck('toggleOn');
    } else {
        asdThemeCheckbox.niftyCheck('toggleOff');
    }





    // Fixed navbar
    if ($.cookie('settings-navbar-fixed') == 1 || nifty.container.hasClass('navbar-fixed')) {
        navbarFixedCheckbox.niftyCheck('toggleOn');
        nifty.container.addClass('navbar-fixed');

        // Refresh the aside, to enable or disable the "Bootstrap Affix" when the navbar is in a "static position".
        nifty.mainNav.niftyAffix('update');
        nifty.aside.niftyAffix('update');
    } else {
        navbarFixedCheckbox.niftyCheck('toggleOff');
        nifty.container.removeClass('navbar-fixed');

        // Refresh the aside, to enable or disable the "Bootstrap Affix" when the navbar is in a "static position".
        nifty.mainNav.niftyAffix('update');
        nifty.aside.niftyAffix('update');
    };





    // Fixed footer
    if ($.cookie('settings-footer-fixed') == 1 || nifty.container.hasClass('footer-fixed')) {
        footerFixedCheckbox.niftyCheck('toggleOn');
        nifty.container.addClass('footer-fixed');
    } else {
        footerFixedCheckbox.niftyCheck('toggleOff');
        nifty.container.removeClass('footer-fixed');
    }




    // Themes
    if ($.cookie('settings-theme-name') && $.cookie('settings-theme-type')) {
        changeTheme($.cookie('settings-theme-name'), $.cookie('settings-theme-type'));

        $('.demo-theme').filter('[data-type=' + $.cookie('settings-theme-type') + ']').filter('[data-theme=' + $.cookie('settings-theme-name') + ']').addClass('disabled');
    } else {
        $('.demo-theme.demo-c-navy').addClass('disabled');
    }

    // 设置用户当前商场
    /*var mall = $(".head-account-mall").data("mall")||{},
        mallData = null;

    if(mall.mallId) {
        mallData = {
            id: mall.mallId,
            text: mall.mallName
        }
    }

    selectTool.renderSelect({
        selector: ".head-account-mall",
        msg: "所在商场",
        textName: "cpName",
        url: serverHost + "mall/mall_ajax/query_mall_list.json",
        width: '150',
        allowClear: false,
        val: mallData,
        paramFn: function () {
            return {
                permission: true
            }
        },
        listeners: {
            change: function (val, data, old) {
                // 切换商场
                messageTool.confirm("确定要切换到" + data.text + "?", function (flag) {
                    if(flag) {
                        util.request({

                        })
                    } else {
                        $(".head-account-mall").select2("data", old);
                    }
                });
            }
        }
    });*/

    // 修改密码

    var changeBorder = function (selector, clear) {
        if(!!clear) {
            $(selector).removeClass("validate-error");
        } else {
            $(selector).addClass("validate-error");
        }
    }
    var changePassword = function () {
        var userId = $(".head-account-id").val(),
            oldPassword = $("#old-password").val(),
            newPassword = $("#new-password").val(),
            rePassword = $("#re-password").val();

        if(!oldPassword) {
            changeBorder("#old-password");
        } else if(!newPassword) {
            changeBorder("#new-password");
        } else if(!rePassword) {
            changeBorder("#re-password");
        } else if(newPassword != rePassword ){
            changeBorder("#new-password");
            changeBorder("#re-password");
        } else {
            changeBorder("#old-password", true);
            changeBorder("#new-password", true);
            changeBorder("#re-password", true);

            util.request({
                confirm: false,
                msg: "确定要修改密码?",
                url: serverHost + "/account/changePasswd.json",
                params: {
                    id: userId,
                    password: oldPassword,
                    newPassword: newPassword,
                    rePassword: rePassword
                },
                success: function (resp) {
                    if(resp.success) {
                        $("#old-password").val("");
                        $("#new-password").val("");
                        $("#re-password").val("");
                        messageTool.success("密码修改成功");
                    } else {
                        messageTool.error(resp.msg||"密码修改失败");
                    }
                }
            });

        }
    }
    $(".change-password-btn").on("click", function () {
        changePassword();
    })
});

