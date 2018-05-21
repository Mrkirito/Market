package com.hangjia.bxj.dao;

import java.util.List;
import java.util.Map;

import com.hangjia.bxj.model.StudyComment;

public interface StudyCommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StudyComment record);

    int insertSelective(StudyComment record);

    StudyComment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StudyComment record);

    int updateByPrimaryKey(StudyComment record);
    
    int updateSupportNum(Long id);
    
    List<StudyComment> selectList(Map<String,Object> map);
    
    int selectCount();
}