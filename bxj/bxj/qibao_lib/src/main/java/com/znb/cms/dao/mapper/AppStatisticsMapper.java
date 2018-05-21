package com.znb.cms.dao.mapper;

import java.util.List;

import com.znb.cms.model.mapper.AppStatistics;

public interface AppStatisticsMapper {
    int deleteByPrimaryKey(String date);

    int insert(AppStatistics record);

    int insertSelective(AppStatistics record);

    AppStatistics selectByPrimaryKey(String date);

    int updateByPrimaryKeySelective(AppStatistics record);

    int updateByPrimaryKey(AppStatistics record);
    
    /***ext**/
	int getCount(AppStatistics query);

	List<AppStatistics> getStatistics(AppStatistics query);
	
	List<AppStatistics> getAll();
}