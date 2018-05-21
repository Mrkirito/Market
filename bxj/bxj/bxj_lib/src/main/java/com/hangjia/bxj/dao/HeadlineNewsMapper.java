package com.hangjia.bxj.dao;

import java.util.List;

import com.hangjia.bxj.model.Headline;
import com.hangjia.bxj.model.HeadlineNews;
import com.hangjia.bxj.query.app.HeadlineNewsQuery;

public interface HeadlineNewsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HeadlineNews record);

    int insertSelective(HeadlineNews record);

    HeadlineNews selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HeadlineNews record);

    int updateByPrimaryKeyWithBLOBs(HeadlineNews record);

    int updateByPrimaryKey(HeadlineNews record);
    
    List<HeadlineNews> queryPageData(HeadlineNewsQuery query);
    
    int queryPageDataCount(HeadlineNewsQuery query);
    
    int querySort(HeadlineNewsQuery query);
    
    List<HeadlineNews> queryNewsList(Headline record);
    
    int updateHit(Long id);
}