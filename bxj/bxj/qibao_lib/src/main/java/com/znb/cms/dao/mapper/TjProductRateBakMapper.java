package com.znb.cms.dao.mapper;

import com.znb.cms.model.mapper.TjProductRateBak;

public interface TjProductRateBakMapper {
    int deleteByPrimaryKey(Integer fid);

    int insert(TjProductRateBak record);

    int insertSelective(TjProductRateBak record);

    TjProductRateBak selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(TjProductRateBak record);

    int updateByPrimaryKey(TjProductRateBak record);
}