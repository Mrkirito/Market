package com.hangjia.bxj.shop.service;

import com.hangjia.bxj.common.BaseCommonQuery;
import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.shop.model.ShopGoodsExpressPrice;
import com.hangjia.bxj.shop.query.ShopGoodsExpressPriceQuery;

/**
 * Created by xiongfangyong on 2016/9/29.
 */
public interface ShopGoodsExpressPriceService {

    /**
     * 分页查询
     * @return
     */
    Result getPageList(BaseCommonQuery query);

    /**
     * 分页查询
     * @return
     */
    Result getPageListByGoodsId(ShopGoodsExpressPriceQuery query);

    /**
     * 详细
     * @return
     */
    ShopGoodsExpressPrice detail(Long id);

    /**
     * 添加
     * @param expressPrice
     * @return
     */
    int add(ShopGoodsExpressPrice expressPrice);

    /**
     * 更新
     * @param expressPrice
     * @return
     */
    int update(ShopGoodsExpressPrice expressPrice);

    /**
     * 删除
     * @return
     */
    int delete(Long id);

    /**
     * 删除
     * @return
     */
    int deleteBat(String ids);
}
