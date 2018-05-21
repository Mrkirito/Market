package com.hangjia.bxj.service.activity.impl;

import com.hangjia.bxj.dao.activity.ActivityVoteSignMapper;
import com.hangjia.bxj.model.activity.ActivityVoteSign;
import com.hangjia.bxj.query.activity.VoteSignerQuery;
import com.hangjia.bxj.service.activity.IVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Throwable.class)
public class VoteServiceImpl implements IVoteService {

    @Autowired
    private ActivityVoteSignMapper voteSignMapper;


    /**
     * 查询投票报名人列表总数
     *
     * @param query
     * @return
     */
    @Override
    public int querySignerDataCount(VoteSignerQuery query) {
        return voteSignMapper.querySignerDataCount(query);
    }

    /**
     * 查询投票报名人列表
     *
     * @param query
     * @return
     */
    @Override
    public List<ActivityVoteSign> querySignerDataPage(VoteSignerQuery query) {
        return voteSignMapper.querySignerDataPage(query) == null ? new ArrayList<ActivityVoteSign>() : voteSignMapper.querySignerDataPage(query);
    }

    /**
     * 审核通过参赛信息
     *
     * @param voteSign
     * @return
     */
    @Override
    public int approve(ActivityVoteSign voteSign) {
        VoteSignerQuery query = new VoteSignerQuery();
        query.setSignType(voteSign.getSignType());
        int signCode = voteSignMapper.queryMaxSignCode(query) + 1;
        if (signCode <= 1) {
            if (voteSign.getSignType() == 0)
                signCode = 2016001;
            else if (voteSign.getSignType() == 1)
                signCode = 2016501;
            else if (voteSign.getSignType() == 2)
                signCode = 2016401;
            else if (voteSign.getSignType() == 3)
                signCode = 2016901;
        }
        voteSign.setSignCode(String.valueOf(signCode));
        voteSign.setStatus(1);
        voteSign.setAuthTime(new Date());
        return voteSignMapper.updateByPrimaryKeySelective(voteSign);
    }

    /**
     * 修改参赛信息
     *
     * @param voteSign
     * @return
     */
    @Override
    public int update(ActivityVoteSign voteSign) {
        return voteSignMapper.updateByPrimaryKeySelective(voteSign);
    }

    /**
     * 审核拒绝参赛信息
     *
     * @param id
     * @return
     */
    @Override
    public int refuse(Long id) {
        ActivityVoteSign voteSign = new ActivityVoteSign();
        voteSign.setId(id);
        voteSign.setStatus(2);
        voteSign.setAuthTime(new Date());
        return voteSignMapper.updateByPrimaryKeySelective(voteSign);
    }
}
