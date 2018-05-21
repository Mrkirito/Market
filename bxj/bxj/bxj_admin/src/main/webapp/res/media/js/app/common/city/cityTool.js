define(['jquery', 'css!global/scripts/app/common/city/city.css'], function ($, _) {

    var tpl = '<div class="city-toggle"> <div class="head"> <ul> <li class="province active">省份</li> <li class="city">城市</li> </ul> </div> ' +
        '<div class="provinces active"> <ul class="" > </ul> </div> ' +
        '<div class="citys"> </div> </div>';

    return {
        init: function (selector, data, callback) {
            var cityTool =  {
                init: function (selector, data, callback) {
                    this.elements = [];
                    this.initContainer(selector, data);
                    this.initEvents();
                    this.callback = callback;
                    return this;
                },

                initContainer: function (selector, data) {
                    var thiz = this;
                    selector = (typeof(selector) == 'string')?$(selector):selector;

                    $(selector).each(function () {
                        var ele = thiz.findCityToggle($(this));
                        if(!ele) {
                            ele = $(tpl);

                            var provinces = ele.find(".provinces ul"),
                                citys = ele.find(".citys");
                            data = data || [];
                            $(data).each(function () {
                                $('<li><a class="hd" data-id="' + this.id + '">' + this.text + '</a></li>').appendTo(provinces);

                                var cityData = this.citys||[],
                                    citysele = $('<ul class="city" data-id="' + this.id + '"></ul>').appendTo(citys);
                                $(cityData).each(function () {
                                    $('<li><a class="bd" data-id="' + this.id + '">' + this.text + '</a></li>').appendTo(citysele);
                                })
                            });

                            $(this).after($(ele));
                            ele.selector = $(this);
                            thiz.elements.push(ele);
                        }
                    });

                    this.selector = selector;
                },

                initEvents: function () {
                    var thiz = this;
                    $(thiz.selector).click(function () {
                        var cityToggle = thiz.findCityToggle($(this));
                        if(cityToggle) {
                            cityToggle.addClass("open");
                        }
                    });

                    $(document).on("click", function (e) {
                        var target = e.target;
                        if(!$(target).hasClass("city-toggle")
                            && $(target).parents(".city-toggle").length == 0) {
                            $('.city-toggle').removeClass("open");
                        }

                        $(thiz.selector).each(function () {
                            if($(e.target).is($(this))) {
                                var cityToggle = thiz.findCityToggle($(this));
                                if(cityToggle) {
                                    cityToggle.addClass("open");
                                }
                            }
                        });

                    });
                    $(thiz.elements).each(function () {
                        var element = $(this);

                        $(element).on("click", ".hd", function () {
                            var id = $(this).data("id");
                            $(element).find(".citys .city").each(function () {
                                if($(this).data("id") == id) {
                                    $(this).addClass("active").siblings().removeClass("active");
                                    $(element).find(".provinces").removeClass("active")
                                    $(element).find(".head .province").removeClass("active")
                                    $(element).find(".head .city").addClass("active")
                                    $(element).find(".citys").addClass("open")
                                }
                            });
                        });

                        $(element).on("click", ".head .province", function () {
                            $(element).find(".head .province").addClass("active");
                            $(element).find(".head .city").removeClass("active")
                            $(element).find(".provinces").addClass("active")
                            $(element).find(".citys .city").removeClass("active")
                            $(element).find(".citys").removeClass("open")
                        });

                        $(element).on("click", ".head .city", function () {
                            $(element).find(".head .city").addClass("active");
                            $(element).find(".head .province").removeClass("active")
                            $(element).find(".provinces").removeClass("active")
                            $($(element).find(".citys .city")[0]).addClass("active");
                            $(element).find(".citys").addClass("open")
                        });

                        $(element).on("click", ".bd", function () {
                            if(thiz.callback) {
                                var data = {
                                    id: $(this).data("id"),
                                    text: $(this).text()
                                };
                                thiz.callback(data, element.selector);
                            }
                        });
                    })
                },

                findCityToggle: function (selector) {
                    var cityToggle = $(selector).next();
                    if(cityToggle && cityToggle.hasClass("city-toggle")) {
                        return cityToggle;
                    }
                    return null;
                }
            }

            return cityTool.init(selector, data, callback);
        }
    }


})