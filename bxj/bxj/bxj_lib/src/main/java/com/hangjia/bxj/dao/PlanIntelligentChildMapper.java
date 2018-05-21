package com.hangjia.bxj.dao;

import com.hangjia.bxj.model.PlanIntelligentChild;

public interface PlanIntelligentChildMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlanIntelligentChild record);

    int insertSelective(PlanIntelligentChild record);

    PlanIntelligentChild selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlanIntelligentChild record);

    int updateByPrimaryKey(PlanIntelligentChild record);
}