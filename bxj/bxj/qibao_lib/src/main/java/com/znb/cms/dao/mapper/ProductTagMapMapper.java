package com.znb.cms.dao.mapper;

import com.znb.cms.model.mapper.ProductTagMap;

public interface ProductTagMapMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(ProductTagMap record);
	
	int deleteByProductId(Integer productId);
}