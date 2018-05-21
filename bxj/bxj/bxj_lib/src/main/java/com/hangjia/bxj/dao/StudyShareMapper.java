package com.hangjia.bxj.dao;

import java.util.Map;

import com.hangjia.bxj.model.StudyShare;

public interface StudyShareMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StudyShare record);

    int insertSelective(StudyShare record);

    StudyShare selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StudyShare record);

    int updateByPrimaryKey(StudyShare record);
    
    StudyShare selectBystudyID(Map<String,Object> map);
    
}