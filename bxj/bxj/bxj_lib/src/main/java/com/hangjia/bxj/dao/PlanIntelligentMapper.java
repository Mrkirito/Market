package com.hangjia.bxj.dao;

import com.hangjia.bxj.model.PlanIntelligent;

public interface PlanIntelligentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlanIntelligent record);

    int insertSelective(PlanIntelligent record);

    PlanIntelligent selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlanIntelligent record);

    int updateByPrimaryKey(PlanIntelligent record);
}