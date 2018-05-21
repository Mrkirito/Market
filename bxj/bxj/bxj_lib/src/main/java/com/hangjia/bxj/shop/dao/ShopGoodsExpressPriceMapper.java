package com.hangjia.bxj.shop.dao;

import com.hangjia.bxj.common.BaseCommonQuery;
import com.hangjia.bxj.shop.model.ShopGoodsExpressPrice;
import com.hangjia.bxj.shop.query.ShopGoodsExpressPriceQuery;

import java.util.List;

public interface ShopGoodsExpressPriceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShopGoodsExpressPrice record);

    int insertSelective(ShopGoodsExpressPrice record);

    ShopGoodsExpressPrice selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShopGoodsExpressPrice record);

    int updateByPrimaryKey(ShopGoodsExpressPrice record);

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
    List<ShopGoodsExpressPrice> selectByPage(BaseCommonQuery query);

    /**
     * 总个数
     * @param query
     * @return
     */
    int selectCountByGoodsId(BaseCommonQuery query);

    /**
     * 分页查询
     * @param query
     * @return
     */
    List<ShopGoodsExpressPrice> selectPageByQuery(ShopGoodsExpressPriceQuery query);
}