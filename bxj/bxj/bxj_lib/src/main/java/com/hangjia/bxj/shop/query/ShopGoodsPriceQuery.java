package com.hangjia.bxj.shop.query;

import com.hangjia.bxj.common.BaseCommonQuery;

/**
 * Created by xiongfangyong on 2016/10/8.
 */
public class ShopGoodsPriceQuery extends BaseCommonQuery {
    private Long goodsId;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
}
