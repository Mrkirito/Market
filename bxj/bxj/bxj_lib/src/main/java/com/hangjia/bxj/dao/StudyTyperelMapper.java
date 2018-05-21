package com.hangjia.bxj.dao;

import com.hangjia.bxj.model.StudyTyperel;

public interface StudyTyperelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StudyTyperel record);

    int insertSelective(StudyTyperel record);

    StudyTyperel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StudyTyperel record);

    int updateByPrimaryKey(StudyTyperel record);
}