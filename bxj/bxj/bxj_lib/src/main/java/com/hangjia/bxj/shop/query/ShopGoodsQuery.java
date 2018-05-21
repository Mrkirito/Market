package com.hangjia.bxj.shop.query;

import com.hangjia.bxj.common.BaseCommonQuery;

/**
 * Created by xiongfangyong on 2016/10/8.
 */
public class ShopGoodsQuery extends BaseCommonQuery {
    private String name;
    private Integer status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Integer getStatus() {
        return status;
    }

    @Override
    public void setStatus(Integer status) {
        this.status = status;
    }
}
