package com.hangjia.bxj.dao;

import com.hangjia.bxj.model.PlanProductQy;

public interface PlanProductQyMapper {
    int deleteByPrimaryKey(Integer fid);

    int deleteByPid(Integer pid);
    
    int insert(PlanProductQy record);

    int insertSelective(PlanProductQy record);

    PlanProductQy selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(PlanProductQy record);

    int updateByPrimaryKey(PlanProductQy record);
}