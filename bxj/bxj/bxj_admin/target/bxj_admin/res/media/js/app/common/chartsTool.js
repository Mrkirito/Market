define(['jquery', 'plugins/echarts/echarts', 'plugins/echarts/macarons'], function ($, echarts, macarons) {
    return {
        init: function (selector) {
            var echart = {
                init: function (selector) {
                    this.echarts = echarts.init(selector, 'macarons');
                    return this;
                },
                setOption: function(option){
                	this.echarts.setOption(option);
                }
            };

            return echart.init(selector);
        }
    };
});