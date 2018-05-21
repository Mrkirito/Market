package com.hangjia.bxj.dao;

import java.util.List;

import com.hangjia.bxj.model.AppExtendData;

public interface AppExtendDataMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AppExtendData record);

    int insertSelective(AppExtendData record);

    AppExtendData selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AppExtendData record);

    int updateByPrimaryKey(AppExtendData record);
    
    int queryCount(AppExtendData query);
    
    List<AppExtendData> queryData(AppExtendData query);
    
    List<AppExtendData> queryEchartsData(AppExtendData query);
    
    int existDataByDay(AppExtendData query);
    
    
    List<AppExtendData> queryAllData(String date);
}