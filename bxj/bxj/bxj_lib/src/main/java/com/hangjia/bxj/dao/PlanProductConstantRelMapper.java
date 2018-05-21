package com.hangjia.bxj.dao;

import com.hangjia.bxj.model.PlanProductConstantRel;

public interface PlanProductConstantRelMapper {
    int deleteByPrimaryKey(Integer fid);

    int insert(PlanProductConstantRel record);

    int insertSelective(PlanProductConstantRel record);

    PlanProductConstantRel selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(PlanProductConstantRel record);

    int updateByPrimaryKey(PlanProductConstantRel record);
}