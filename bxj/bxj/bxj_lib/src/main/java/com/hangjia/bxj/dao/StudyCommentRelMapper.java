package com.hangjia.bxj.dao;

import java.util.List;

import com.hangjia.bxj.model.StudyCommentRel;

public interface StudyCommentRelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StudyCommentRel record);

    int insertSelective(StudyCommentRel record);

    StudyCommentRel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StudyCommentRel record);

    int updateByPrimaryKey(StudyCommentRel record);
    
    List<StudyCommentRel> selectCommentRelList(StudyCommentRel commentRel);
}