package com.hangjia.bxj.dao;

import com.hangjia.bxj.model.StudyType;

public interface StudyTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StudyType record);

    int insertSelective(StudyType record);

    StudyType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StudyType record);

    int updateByPrimaryKey(StudyType record);
}