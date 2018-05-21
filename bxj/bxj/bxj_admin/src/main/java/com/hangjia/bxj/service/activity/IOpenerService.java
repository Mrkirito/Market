package com.hangjia.bxj.service.activity;

import com.hangjia.bxj.model.activity.ActivityOpenerArticle;
import com.hangjia.bxj.query.activity.OpenerArticleQuery;

import java.util.List;

/**
 * 开门红文章相关
 *
 * @author: bei.zhang
 * @date: 2016-11-23
 */
public interface IOpenerService {

    /**
     * 查询开门红文章列表总数
     *
     * @param query
     * @return
     */
    int queryOpenerDataCount(OpenerArticleQuery query);

    /**
     * 查询开门红文章列表
     *
     * @param query
     * @return
     */
    List<ActivityOpenerArticle> queryOpenerDataPage(OpenerArticleQuery query);

    /**
     * 根据ID查询开门红文章
     *
     * @param id
     * @return
     */
    ActivityOpenerArticle queryOpenerDataById(Long id);

    /**
     * 新增开门红文章
     *
     * @param openerArticle
     * @return
     */
    int addOpenerArticle(ActivityOpenerArticle openerArticle);


    /**
     * 修改开门红文章
     *
     * @param openerArticle
     * @return
     */
    int updateOpenerArticle(ActivityOpenerArticle openerArticle);
}
