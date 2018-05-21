package com.znb.cms.dao.mapper;

import com.znb.cms.model.mapper.ProductTagInfo;

public interface ProductTagInfoMapper {
    int deleteByPrimaryKey(Integer id);
    int insert(ProductTagInfo record);
    ProductTagInfo selectByName(String name);
    
}