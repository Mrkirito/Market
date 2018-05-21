define(['jquery',
    'app/common/util',
    'app/common/winTool',
    'app/common/listTool',
    'app/common/messageTool',
    'app/right/funcTool'
    ],
    function ($, util, winTool, listTool, messageTool) {
        return {
            ordersPriceListWin: function (data) {
                var thiz = this,
                    goodsId = data.id,
                    name = data.name,
                    win = winTool.create({
                        title: '订单费用计算(' + name + ')',
                        showOk: false,
                        cancelName: "关闭",
                        selector: ".orders_price_list",
                        type: 'selector',
                        width: 900,
                        height: 200
                    });

                var datas_city = city_data.citylist;
                $('.orders_area').html('');
                var s = "<option value=''>请选择</option>";
                for(var i=0; i<datas_city.length; i++) {
                var ac = datas_city[i].p;
                    s += "<option value='"+ac+"'>"+ac+"</option>";
                }

                $('.orders_area').html(s);
                $('.goodsQuantityPrice').html("暂无数据。");
                $('.goodsExcpressPrice').html("暂无数据。");
                $('.goodsDiscountPrice').html("暂无数据。");

                $(win.find(".search-money")).on("click", function () {
                    thiz.search_money(goodsId);
                });
            },

            /** 订单费用计算 **/
            search_money: function(goodsId) {
                $('.goodsQuantityPrice').html("暂无数据。");
                $('.goodsExcpressPrice').html("暂无数据。");
                $('.goodsDiscountPrice').html("暂无数据。");
                var arrc = $('.orders_count');
                var arra = $('.orders_area');
                var arru = $('.orders_userId');
                var count = arrc.get(arrc.length-1).value;
                var area = arra.get(arra.length-1).value;
                var userId = arru.get(arru.length-1).value;
                if(area!=null && ""!=area && (count==null || ""==count)) {
                    messageTool.error("请输入购买数量！!");
                    return false;
                }
                util.ajax({
                    url: serverHost + "/shopGoods/calculation.json",
                    params: {
                        goodsId: goodsId,
                        count: count,
                        area: area,
                        userId: userId
                    },
                    success: function (resp) {
                        if(resp) resp = JSON.parse(resp);
                        if(resp && null!=resp.goodsQuantityPrice) $('.goodsQuantityPrice').html(resp.goodsQuantityPrice);
                        if(resp && null!=resp.goodsExcpressPrice) $('.goodsExcpressPrice').html(resp.goodsExcpressPrice);
                        if(resp && null!=resp.goodsDiscountPrice) $('.goodsDiscountPrice').html(resp.goodsDiscountPrice);
                    }
                });
            }
        };
    });