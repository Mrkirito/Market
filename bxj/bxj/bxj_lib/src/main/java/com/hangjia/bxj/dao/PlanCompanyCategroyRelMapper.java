package com.hangjia.bxj.dao;

import com.hangjia.bxj.model.PlanCompanyCategroyRel;

public interface PlanCompanyCategroyRelMapper {
    int deleteByPrimaryKey(Integer fid);

    int insert(PlanCompanyCategroyRel record);

    int insertSelective(PlanCompanyCategroyRel record);

    PlanCompanyCategroyRel selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(PlanCompanyCategroyRel record);

    int updateByPrimaryKey(PlanCompanyCategroyRel record);
}