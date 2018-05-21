package com.znb.cms.dao.mapper;

import java.util.List;

import com.znb.cms.model.mapper.ProductError;

public interface ProductErrorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductError record);

    int insertSelective(ProductError record);

    ProductError selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductError record);

    int updateByPrimaryKey(ProductError record);
    
    List<ProductError> getProductErrors(ProductError error);
    
    int getCount(ProductError error);
}