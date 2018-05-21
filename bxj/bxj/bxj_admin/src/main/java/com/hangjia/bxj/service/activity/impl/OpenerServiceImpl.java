package com.hangjia.bxj.service.activity.impl;

import com.hangjia.bxj.dao.activity.ActivityOpenerArticleMapper;
import com.hangjia.bxj.model.activity.ActivityOpenerArticle;
import com.hangjia.bxj.query.activity.OpenerArticleQuery;
import com.hangjia.bxj.service.activity.IOpenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Throwable.class)
public class OpenerServiceImpl implements IOpenerService {

    @Autowired
    private ActivityOpenerArticleMapper openerArticleMapper;

    /**
     * 查询开门红文章列表总数
     *
     * @param query
     * @return
     */
    @Override
    public int queryOpenerDataCount(OpenerArticleQuery query) {
        return openerArticleMapper.queryOpenerDataCount(query);
    }

    /**
     * 查询开门红文章列表
     *
     * @param query
     * @return
     */
    @Override
    public List<ActivityOpenerArticle> queryOpenerDataPage(OpenerArticleQuery query) {
        List<ActivityOpenerArticle> list = list = openerArticleMapper.queryOpenerDataPage(query);
        return list == null ? new ArrayList<ActivityOpenerArticle>() : list;
    }

    /**
     * 根据ID查询开门红文章
     *
     * @param id
     * @return
     */
    @Override
    public ActivityOpenerArticle queryOpenerDataById(Long id) {
        return openerArticleMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增开门红文章
     *
     * @param openerArticle
     * @return
     */
    @Override
    public int addOpenerArticle(ActivityOpenerArticle openerArticle) {

        return openerArticleMapper.insertSelective(openerArticle);
    }

    /**
     * 修改开门红文章
     *
     * @param openerArticle
     * @return
     */
    @Override
    public int updateOpenerArticle(ActivityOpenerArticle openerArticle) {
        return openerArticleMapper.updateByPrimaryKeySelective(openerArticle);
    }
}
