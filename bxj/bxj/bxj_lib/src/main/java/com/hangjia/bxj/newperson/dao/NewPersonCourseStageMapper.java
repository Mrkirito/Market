package com.hangjia.bxj.newperson.dao;

import com.hangjia.bxj.newperson.model.NewPersonCourseStage;
import com.hangjia.bxj.newperson.query.NewPersonCourseStageQuery;

import java.util.List;

public interface NewPersonCourseStageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NewPersonCourseStage record);

    int insertSelective(NewPersonCourseStage record);

    NewPersonCourseStage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NewPersonCourseStage record);

    int updateByPrimaryKey(NewPersonCourseStage record);

    int queryStageDataCount(NewPersonCourseStageQuery query);

    List<NewPersonCourseStage> queryStageDataPage(NewPersonCourseStageQuery query);
    
}