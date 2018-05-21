package com.znb.cms.dao.mapper;

import java.util.List;

import com.znb.cms.model.mapper.ProductRateFactor;

public interface ProductRateFactorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductRateFactor record);

    int insertSelective(ProductRateFactor record);

    ProductRateFactor selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductRateFactor record);

    int updateByPrimaryKey(ProductRateFactor record);
    
    List<ProductRateFactor> selectAll();
}