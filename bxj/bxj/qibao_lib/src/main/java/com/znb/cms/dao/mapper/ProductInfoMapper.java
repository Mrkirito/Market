package com.znb.cms.dao.mapper;

import java.util.List;

import com.znb.cms.model.mapper.ProductInfo;
import com.znb.cms.query.ProductQuery;

public interface ProductInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductInfo record);

    int insertSelective(ProductInfo record);

    ProductInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductInfo record);

    int updateByPrimaryKey(ProductInfo record);
    
	List<ProductInfo> queryProductInfoList(ProductQuery query);
	
	int queryProductInfoCount(ProductQuery query);
	
	List<ProductInfo> getAllProductInfoList();
}