package com.hangjia.bxj.dao;

import java.util.List;
import java.util.Map;

import com.hangjia.bxj.model.StudyDetail;
import com.hangjia.bxj.query.app.StudyDetailQuery;

public interface StudyDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StudyDetail record);

    int insertSelective(StudyDetail record);

    StudyDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StudyDetail record);

    int updateByPrimaryKeyWithBLOBs(StudyDetail record);

    int updateByPrimaryKey(StudyDetail record);
    
    List<StudyDetail> queryList(Map<String, Object> map);
    
    int updateCount(Long id);
    
    List<StudyDetail> queryPageData(StudyDetailQuery query);
    
    int queryCount(Map<String, Object> map);
    
}