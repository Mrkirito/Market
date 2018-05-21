package com.hangjia.bxj.shop.service;

import com.hangjia.bxj.common.BaseCommonQuery;
import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.shop.model.ShopGoodsPrice;
import com.hangjia.bxj.shop.query.ShopGoodsPriceQuery;

/**
 * Created by xiongfangyong on 2016/9/29.
 */
public interface ShopGoodsPriceService {
    /**
     * 分页查询
     * @return
     */
    Result getPageList(BaseCommonQuery query);

    /**
     * 分页查询
     * @return
     */
    Result getPageListByGoodsId(ShopGoodsPriceQuery query);

    /**
     * 详细
     * @return
     */
    ShopGoodsPrice detail(Long id);

    /**
     * 添加
     * @param price
     * @return
     */
    int add(ShopGoodsPrice price);

    /**
     * 更新
     * @param price
     * @return
     */
    int update(ShopGoodsPrice price);

    /**
     * 删除
     * @return
     */
    int delete(Long id);
}
