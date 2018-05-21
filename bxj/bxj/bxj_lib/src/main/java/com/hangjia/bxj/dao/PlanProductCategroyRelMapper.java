package com.hangjia.bxj.dao;

import com.hangjia.bxj.model.PlanProductCategroyRel;

public interface PlanProductCategroyRelMapper {
    int deleteByPrimaryKey(Integer fid);

    int insert(PlanProductCategroyRel record);

    int insertSelective(PlanProductCategroyRel record);

    PlanProductCategroyRel selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(PlanProductCategroyRel record);

    int updateByPrimaryKey(PlanProductCategroyRel record);
    
    int deleteByPid(Integer pid);
}