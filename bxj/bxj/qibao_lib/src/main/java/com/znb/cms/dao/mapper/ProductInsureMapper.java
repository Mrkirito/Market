package com.znb.cms.dao.mapper;

import com.znb.cms.model.mapper.ProductInsure;

public interface ProductInsureMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductInsure record);

    int insertSelective(ProductInsure record);

    ProductInsure selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductInsure record);

    int updateByPrimaryKey(ProductInsure record);
    
    int deleteByProuctId(Integer id);
    
    int insertInsures(ProductInsure r);
    
    int updateProductInfoProtectAge(Integer id);
    
    ProductInsure getLimitProductInsure(Integer productId,Integer sex);
}