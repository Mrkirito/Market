package com.hangjia.bxj.service.activity;

import com.hangjia.bxj.model.activity.ActivityVoteSign;
import com.hangjia.bxj.query.activity.VoteSignerQuery;

import java.util.List;

/**
 * 投票相关
 *
 * @author: bei.zhang
 * @date: 2016-09-09
 */
public interface IVoteService {

    /**
     * 查询投票报名人列表总数
     *
     * @param query
     * @return
     */
    int querySignerDataCount(VoteSignerQuery query);

    /**
     * 查询投票报名人列表
     *
     * @param query
     * @return
     */
    List<ActivityVoteSign> querySignerDataPage(VoteSignerQuery query);

    /**
     * 审核通过参赛信息
     *
     * @param voteSign
     * @return
     */
    int approve(ActivityVoteSign voteSign);

    /**
     * 修改参赛信息
     *
     * @param voteSign
     * @return
     */
    int update(ActivityVoteSign voteSign);

    /**
     * 审核拒绝参赛信息
     *
     * @param id
     * @return
     */
    int refuse(Long id);
}
