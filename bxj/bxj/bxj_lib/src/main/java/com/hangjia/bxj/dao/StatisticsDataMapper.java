package com.hangjia.bxj.dao;

import java.util.List;

import com.hangjia.bxj.vo.StatisticsDataVo;

public interface StatisticsDataMapper {
	List<StatisticsDataVo> newPeopleStatisticsData(String start,String end);
	
	int insertNewPeopleStatisticsData(StatisticsDataVo vo);
	
	List<StatisticsDataVo> goodStartStatisticsData(String start,String end);
	
	int insertGoodStart(StatisticsDataVo vo);
	
	
	int newPeopleStatisticsDataCount(String start,String end);
	
	int goodStartStatisticsDataCount(String start,String end);
	
/*	StatisticsDataVo goodStartStatisticsDataAll();*/
}