package com.hangjia.bxj.shop.service;

import com.hangjia.bxj.common.BaseCommonQuery;
import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.shop.model.ShopGoods;
import com.hangjia.bxj.shop.query.ShopGoodsQuery;

/**
 * Created by xiongfangyong on 2016/9/29.
 */
public interface ShopGoodsService {

    /**
     * 分页查询
     * @return
     */
    Result getPageList(BaseCommonQuery query);

    /**
     * 分页查询
     * @return
     */
    Result getPageListByQuery(ShopGoodsQuery query);

    /**
     * 详细
     * @return
     */
    ShopGoods detail(Long id);

    /**
     * 添加
     * @param goods
     * @return
     */
    int add(ShopGoods goods);

    /**
     * 更新
     * @param goods
     * @return
     */
    int update(ShopGoods goods);

    /**
     * 上、下线
     *
     * @param id
     * @return
     */
    int online(Long id, Integer online);
}
