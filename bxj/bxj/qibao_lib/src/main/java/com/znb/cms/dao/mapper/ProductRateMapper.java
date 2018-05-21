package com.znb.cms.dao.mapper;

import com.znb.cms.model.mapper.ProductRate;

public interface ProductRateMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductRate record);

    int insertSelective(ProductRate record);

    ProductRate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductRate record);

    int updateByPrimaryKey(ProductRate record);
    
    int deleteByProuctId(Integer id);
}