package com.hangjia.bxj.dao;

import java.util.List;

import com.hangjia.bxj.model.Headline;
import com.hangjia.bxj.model.HeadlineBanner;
import com.hangjia.bxj.query.app.HeadlineBannerQuery;

public interface HeadlineBannerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HeadlineBanner record);

    int insertSelective(HeadlineBanner record);

    HeadlineBanner selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HeadlineBanner record);

    int updateByPrimaryKey(HeadlineBanner record);
    
    List<HeadlineBanner> queryPageData(HeadlineBannerQuery query);
    
    int queryPageDataCount(HeadlineBannerQuery query);
    
    int querySort(HeadlineBannerQuery query);
    
    List<HeadlineBanner> queryBannerList(Headline record);
}