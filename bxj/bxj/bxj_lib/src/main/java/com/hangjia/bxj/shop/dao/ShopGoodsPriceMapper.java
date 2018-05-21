package com.hangjia.bxj.shop.dao;

import com.hangjia.bxj.common.BaseCommonQuery;
import com.hangjia.bxj.shop.model.ShopGoodsPrice;
import com.hangjia.bxj.shop.query.ShopGoodsPriceQuery;

import java.util.List;

public interface ShopGoodsPriceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShopGoodsPrice record);

    int insertSelective(ShopGoodsPrice record);

    ShopGoodsPrice selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShopGoodsPrice record);

    int updateByPrimaryKey(ShopGoodsPrice record);

    /**************************** extend begin here *******************************/
    /**
     * 总个数
     * @param query
     * @return
     */
    int selectByCount(BaseCommonQuery query);

    /**
     * 分页查询
     * @param query
     * @return
     */
    List<ShopGoodsPrice> selectByPage(BaseCommonQuery query);

    /**
     * 总个数
     * @param query
     * @return
     */
    int selectCountByGoodsId(ShopGoodsPriceQuery query);

    /**
     * 分页查询
     * @param query
     * @return
     */
    List<ShopGoodsPrice> selectPageByGoodsId(ShopGoodsPriceQuery query);
}