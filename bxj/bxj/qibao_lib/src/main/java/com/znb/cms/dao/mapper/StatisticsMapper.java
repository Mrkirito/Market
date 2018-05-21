package com.znb.cms.dao.mapper;

import java.util.List;

import com.znb.cms.model.mapper.Statistics;

public interface StatisticsMapper {
    int deleteByPrimaryKey(String date);

    int insert(Statistics record);

    int insertSelective(Statistics record);

    Statistics selectByPrimaryKey(String date);

    int updateByPrimaryKeySelective(Statistics record);

    int updateByPrimaryKey(Statistics record);
    
	int getCount(Statistics query);

	List<Statistics> getStatistics(Statistics query);
	
	List<Statistics> getAll();
}