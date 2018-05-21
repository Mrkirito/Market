package com.hangjia.bxj.dao;

import java.util.List;

import com.hangjia.bxj.model.AppExtendDetailData;

public interface AppExtendDetailDataMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AppExtendDetailData record);

    int insertSelective(AppExtendDetailData record);

    AppExtendDetailData selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AppExtendDetailData record);

    int updateByPrimaryKey(AppExtendDetailData record);
    
    
    int count();
    
    
    List<AppExtendDetailData> getDataList(String date);
}