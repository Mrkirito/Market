package com.hangjia.bxj.dao;

import com.hangjia.bxj.model.PlanBookFeedbacks;

public interface PlanBookFeedbacksMapper {
    int deleteByPrimaryKey(Long fid);

    int insert(PlanBookFeedbacks record);

    int insertSelective(PlanBookFeedbacks record);

    PlanBookFeedbacks selectByPrimaryKey(Long fid);

    int updateByPrimaryKeySelective(PlanBookFeedbacks record);

    int updateByPrimaryKey(PlanBookFeedbacks record);
    
    int getCountByPlanBookFeedbacks(PlanBookFeedbacks feedback);
}