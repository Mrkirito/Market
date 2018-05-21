package com.hangjia.bxj.dao;

import com.hangjia.bxj.model.BxjKeywordRecord;

public interface BxjKeywordRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BxjKeywordRecord record);

    int insertSelective(BxjKeywordRecord record);

    BxjKeywordRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BxjKeywordRecord record);

    int updateByPrimaryKey(BxjKeywordRecord record);
}