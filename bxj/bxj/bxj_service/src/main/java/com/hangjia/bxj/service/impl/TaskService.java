package com.hangjia.bxj.service.impl;

import com.hangjia.bxj.dao.StatisticsDataMapper;
import com.hangjia.bxj.dao.firendcircle.FriendCircleMapper;
import com.hangjia.bxj.vo.StatisticsDataVo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


public class TaskService {
	private static Logger logger = LoggerFactory.getLogger(TaskService.class);
	@Value("${task_run_status}")
	private String taskRunStatus;

	@Autowired
	private FriendCircleMapper friendCircleMapper;
	
	@Autowired
	private StatisticsDataMapper statisticsDataMapper;
	
	private boolean isTaskRun() {
		if("Y".equalsIgnoreCase(taskRunStatus)) {
			return true;
		}
		return false;
	}

	/**
	 * 每周一重置周分享数据
	 */
	public void weekFriendCircle() {
		if(!isTaskRun()) return;
		logger.info("exectue weekFriendCircle begin ");
		try {
			friendCircleMapper.clearWeekShareCount(null);
			logger.info("exectue weekFriendCircle success ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("exectue weekFriendCircle end ");
	}

	
	/**
	 * 新人通 
	 */
	public void newPeopleStatistics() {
		if(!isTaskRun()) 
			return;
		logger.info("New People Statistics begin ");
		try {
			Date yesterday = getBeforeByToday(-1);
			String yesterdayStr=getDateByFormat("yyyy-MM-dd", yesterday);
			String begin=yesterdayStr+" 00:00:00";
			String end=yesterdayStr+" 23:59:59";
			int i=statisticsDataMapper.newPeopleStatisticsDataCount(begin, end);
			if(i==0){				
				StatisticsDataVo vo = new StatisticsDataVo();
				vo.setDataTime(yesterday);
				List<StatisticsDataVo> list = statisticsDataMapper.newPeopleStatisticsData(begin,end);
				for (StatisticsDataVo single : list) {
					Integer count=single.getNumber();
					Integer type=single.getType();
					Integer people=single.getPeople();
					if (type == 1) {
						vo.setStudyCount(count);
						vo.setStudyCountUv(people);
					} else if (type == 2) {
						vo.setTestCount(count);
						vo.setTestCountUv(people);
					} else if (type == 3) {
						vo.setClearanceCount(count);
						vo.setClearanceCountUv(people);
					}
				}
				statisticsDataMapper.insertNewPeopleStatisticsData(vo);
			}
			logger.info("New People Statistics success ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("New People Statistics end ");
	}
	
	/**
	 * 开门红 
	 */
	public void goodStartStatistics() {
		if(!isTaskRun()) return;
		logger.info("Good Start Statistics begin ");
		try {			
			Date yesterday = getBeforeByToday(-1);
			String yesterdayStr=getDateByFormat("yyyy-MM-dd", yesterday);
			String begin=yesterdayStr+" 00:00:00";
			String end=yesterdayStr+" 23:59:59";
			int i=statisticsDataMapper.goodStartStatisticsDataCount(begin, end);
			if(i==0){
				StatisticsDataVo vo = new StatisticsDataVo();
				vo.setDataTime(yesterday);
				List<StatisticsDataVo> list = statisticsDataMapper.goodStartStatisticsData(begin,end);
				for (StatisticsDataVo single : list) {
					Integer count=single.getNumber();
					Integer type=single.getType();
					Integer people=single.getPeople();
					if (type == 1) {
						vo.setBrowseCount(count);
						vo.setBrowseCountUv(people);
					} else if (type == 2) {
						vo.setShareCount(count);
						vo.setShareCountUv(people);
					}
				}
				statisticsDataMapper.insertGoodStart(vo);
			}
			logger.info("Good Start Statistics success ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Good Start Statistics end ");
	}
	
	private static Date getBeforeByToday(int i){
		Calendar calendar=Calendar.getInstance();
		calendar.add(Calendar.DATE, i);
		Date yesterday = calendar.getTime();
		return yesterday;
	}
	
	private static String getDateByFormat(String format,Date date){
		return new SimpleDateFormat(format).format(date);
	}
}
