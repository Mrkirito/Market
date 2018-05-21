package com.hangjia.bxj.shop.query;

import com.hangjia.bxj.common.BaseCommonQuery;

/**
 * Created by xiongfangyong on 2016/10/8.
 */
public class ShopGoodsExpressPriceQuery extends BaseCommonQuery {
    private Long goodsId;
    private String area;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
