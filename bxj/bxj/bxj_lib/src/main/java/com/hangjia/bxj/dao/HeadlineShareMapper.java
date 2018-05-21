package com.hangjia.bxj.dao;

import com.hangjia.bxj.model.HeadlineShare;

public interface HeadlineShareMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HeadlineShare record);

    int insertSelective(HeadlineShare record);

    HeadlineShare selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HeadlineShare record);

    int updateByPrimaryKey(HeadlineShare record);
}