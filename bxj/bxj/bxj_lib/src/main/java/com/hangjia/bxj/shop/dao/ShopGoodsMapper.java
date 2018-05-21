package com.hangjia.bxj.shop.dao;

import com.hangjia.bxj.common.BaseCommonQuery;
import com.hangjia.bxj.shop.model.ShopGoods;
import com.hangjia.bxj.shop.query.ShopGoodsQuery;

import java.util.List;

public interface ShopGoodsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShopGoods record);

    int insertSelective(ShopGoods record);

    ShopGoods selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShopGoods record);

    int updateByPrimaryKeyWithBLOBs(ShopGoods record);

    int updateByPrimaryKey(ShopGoods record);

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
    List<ShopGoods> selectByPage(BaseCommonQuery query);

    /**
     * 总个数
     * @param query
     * @return
     */
    int selectCountByQuery(ShopGoodsQuery query);

    /**
     * 分页查询
     * @param query
     * @return
     */
    List<ShopGoods> selectPageByQuery(ShopGoodsQuery query);
}