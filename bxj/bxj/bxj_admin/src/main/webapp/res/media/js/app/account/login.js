define(['jquery'], function ($) {

    var login = {
        init: function () {
            var $imgHolder 	= $('#demo-bg-list');
            var $bgBtn 		= $imgHolder.find('.demo-chg-bg');
            var $target 	= $('#bg-overlay');

            $bgBtn.on('click', function(e){
                e.preventDefault();
                e.stopPropagation();


                var $el = $(this);
                if ($el.hasClass('active') || $imgHolder.hasClass('disabled'))return;
                if ($el.hasClass('bg-trans')) {
                    $target.css('background-image','none');
                    $imgHolder.removeClass('disabled');
                    $bgBtn.removeClass('active');
                    $el.addClass('active');

                    return;
                }

                $imgHolder.addClass('disabled');
                var url = $("#path").attr('href') + $el.attr('src').replace('/thumbs','');

                $('<img/>').attr('src' , url).load(function(){
                    $target.css('background-image', 'url("' + url + '")');
                    $imgHolder.removeClass('disabled');
                    $bgBtn.removeClass('active');
                    $el.addClass('active');

                    $(this).remove();
                })

            });

            $(".use-weixin-login").on("click", function () {
                $(".weixin-login").removeClass("hidden");
                $(".account-login").addClass("hidden");
            })

            $(".use-account-login").on("click", function () {
                $(".weixin-login").addClass("hidden");
                $(".account-login").removeClass("hidden");
            })
        }
    };

    return login;
});