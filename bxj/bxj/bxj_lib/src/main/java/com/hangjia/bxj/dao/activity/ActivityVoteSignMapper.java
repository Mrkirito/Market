package com.hangjia.bxj.dao.activity;

import com.hangjia.bxj.model.activity.ActivityVoteSign;
import com.hangjia.bxj.query.activity.VoteSignerQuery;

import java.util.List;

public interface ActivityVoteSignMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ActivityVoteSign record);

    int insertSelective(ActivityVoteSign record);

    ActivityVoteSign selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ActivityVoteSign record);

    int updateByPrimaryKey(ActivityVoteSign record);

    int querySignerDataCount(VoteSignerQuery record);

    List<ActivityVoteSign> querySignerDataPage(VoteSignerQuery record);

    int queryMaxSignCode(VoteSignerQuery record);
}