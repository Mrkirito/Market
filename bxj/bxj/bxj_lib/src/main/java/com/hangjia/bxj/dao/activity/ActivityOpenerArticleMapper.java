package com.hangjia.bxj.dao.activity;

import com.hangjia.bxj.model.activity.ActivityOpenerArticle;
import com.hangjia.bxj.query.activity.OpenerArticleQuery;

import java.util.List;

public interface ActivityOpenerArticleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ActivityOpenerArticle record);

    int insertSelective(ActivityOpenerArticle record);

    ActivityOpenerArticle selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ActivityOpenerArticle record);

    int updateByPrimaryKeyWithBLOBs(ActivityOpenerArticle record);

    int updateByPrimaryKey(ActivityOpenerArticle record);

    int queryOpenerDataCount(OpenerArticleQuery record);

    List<ActivityOpenerArticle> queryOpenerDataPage(OpenerArticleQuery record);

}