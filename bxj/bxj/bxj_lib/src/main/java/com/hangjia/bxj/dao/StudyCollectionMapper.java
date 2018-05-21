package com.hangjia.bxj.dao;

import java.util.List;
import java.util.Map;

import com.hangjia.bxj.model.StudyCollection;

public interface StudyCollectionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StudyCollection record);

    int insertSelective(StudyCollection record);

    StudyCollection selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StudyCollection record);

    int updateByPrimaryKey(StudyCollection record);
    
    List<StudyCollection> selectCollectList(Map<String,Object> map);
    
    int selectTotal(Map<String,Object> map);
}