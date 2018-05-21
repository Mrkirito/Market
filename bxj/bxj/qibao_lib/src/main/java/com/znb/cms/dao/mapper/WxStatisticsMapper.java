package com.znb.cms.dao.mapper;

import java.util.List;

import com.znb.cms.model.mapper.WxStatistics;

public interface WxStatisticsMapper {
    int deleteByPrimaryKey(String date);

    int insert(WxStatistics record);

    int insertSelective(WxStatistics record);

    WxStatistics selectByPrimaryKey(String date);

    int updateByPrimaryKeySelective(WxStatistics record);

    int updateByPrimaryKey(WxStatistics record);
    
    /***ext**/
	int getCount(WxStatistics query);

	List<WxStatistics> getStatistics(WxStatistics query);
	
	List<WxStatistics> getAll();
	
	WxStatistics getStatisticsOne();
	
}