package com.hangjia.bxj.dao.shop;

import com.hangjia.bxj.model.shop.ShopOrdersDetail;

public interface ShopOrdersDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShopOrdersDetail record);

    int insertSelective(ShopOrdersDetail record);

    ShopOrdersDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShopOrdersDetail record);

    int updateByPrimaryKey(ShopOrdersDetail record);
}