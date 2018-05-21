package com.hangjia.bxj.dao;

import com.hangjia.bxj.model.StudyBanner;

public interface StudyBannerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StudyBanner record);

    int insertSelective(StudyBanner record);

    StudyBanner selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StudyBanner record);

    int updateByPrimaryKey(StudyBanner record);
}