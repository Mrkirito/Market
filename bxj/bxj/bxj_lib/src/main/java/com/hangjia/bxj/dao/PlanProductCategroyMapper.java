package com.hangjia.bxj.dao;

import java.util.List;

import com.hangjia.bxj.model.PlanProductCategroy;

public interface PlanProductCategroyMapper {
    int deleteByPrimaryKey(Integer fid);

    int insert(PlanProductCategroy record);

    int insertSelective(PlanProductCategroy record);

    PlanProductCategroy selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(PlanProductCategroy record);

    int updateByPrimaryKey(PlanProductCategroy record);
    
    List<PlanProductCategroy> getProductCategroys();
    
    List<PlanProductCategroy> getAllProductCategroys();
    
    List<PlanProductCategroy> getProductCategroyByPid(Integer pid);
}