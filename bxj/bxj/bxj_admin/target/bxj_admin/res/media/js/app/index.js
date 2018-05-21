define(['jquery',
        'plugins/skycons/skycons'],
    function ($, Skycons, echarts, macarons) {
        'use strict';
        return {
            init: function () {
                this.initWeather();
            },

            initWeather: function () {

                var skyconsOptions = {
                    "color": "#fff",
                    "resizeClear": true
                }
                /* Main Icon */
                var skycons = new Skycons(skyconsOptions);
                skycons.add("demo-weather-icon-1", Skycons.PARTLY_CLOUDY_DAY);
                skycons.play();



                /* Small Icons*/
                var skycons2 = new Skycons(skyconsOptions);
                skycons2.add("demo-weather-icon-2", Skycons.CLOUDY);
                skycons2.play();



                var skycons3 = new Skycons(skyconsOptions);
                skycons3.add("demo-weather-icon-3", Skycons.WIND);
                skycons3.play();



                var skycons4 = new Skycons(skyconsOptions);
                skycons4.add("demo-weather-icon-4", Skycons.RAIN);
                skycons4.play();



                var skycons5 = new Skycons(skyconsOptions);
                skycons5.add("demo-weather-icon-5", Skycons.PARTLY_CLOUDY_DAY);
                skycons5.play();
            }
        }

    })