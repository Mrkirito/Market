package com.hangjia.bxj.dao;


import com.hangjia.bxj.model.PlanBookUserRel;
import com.hangjia.bxj.model.PlanBookUserVo;

import java.util.List;

public interface PlanBookUserRelMapper {
    int deleteByPrimaryKey(Integer fid);
    
    int deleteByPrimaryKeyTag(Integer fid);

    int deletePlanbookByPrimaryKeyTag(Integer fid);

    int insert(PlanBookUserRel record);

    int insertSelective(PlanBookUserRel record);

    PlanBookUserRel selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(PlanBookUserRel record);

    int updateByPrimaryKey(PlanBookUserRel record);
    //    <!-- 获取用户创建的所以计划书 -->
    List<PlanBookUserRel> getPlanBookRelsByUserId(Integer userId);
    
    //<!-- 获取用户创建的所有计划书链接查询 -->
    List<PlanBookUserVo> getPlanBooksByUserId(Integer userId);
}