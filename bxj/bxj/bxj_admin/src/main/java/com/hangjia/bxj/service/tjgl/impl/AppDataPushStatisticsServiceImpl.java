/*
* Copyright (c) 2016 www.baobao18.com. All Rights Reserved.
*/
package com.hangjia.bxj.service.tjgl.impl;

import com.hangjia.bxj.common.BaseCommonQuery;
import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.dao.tjgl.AppDataPushStatisticsMapper;
import com.hangjia.bxj.model.tjgl.AppDataPushStatistics;
import com.hangjia.bxj.query.tjgl.AppDataPushStatisticsQuery;
import com.hangjia.bxj.service.tjgl.AppDataPushStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Caigp
 * Date: 2016/11/22 12:18
 */
@Service
public class AppDataPushStatisticsServiceImpl implements AppDataPushStatisticsService {

    @Autowired
    private AppDataPushStatisticsMapper dao;

    @Override
    public int getAppDataPushStatisticsByDate(String date) {
        return dao.getAppDataPushStatisticsByDate(date);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return dao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(AppDataPushStatistics record) {
        return dao.insert(record);
    }

    @Override
    public int insertSelective(AppDataPushStatistics record) {
        return dao.insertSelective(record);
    }

    @Override
    public AppDataPushStatistics selectByPrimaryKey(Long id) {
        return dao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(AppDataPushStatistics record) {
        return dao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(AppDataPushStatistics record) {
        return dao.updateByPrimaryKey(record);
    }

    @Override
    public int selectByCount(BaseCommonQuery query) {
        return dao.selectByCount(query);
    }

    @Override
    public List<AppDataPushStatistics> selectByPage(BaseCommonQuery query) {
        return dao.selectByPage(query);
    }

    @Override
    public int selectCountByQuery(AppDataPushStatisticsQuery query) {
        return dao.selectCountByQuery(query);
    }

    @Override
    public List<AppDataPushStatistics> selectPageByQuery(AppDataPushStatisticsQuery query) {
        return dao.selectPageByQuery(query);
    }

    @Override
    public Result getPageListByQuery(AppDataPushStatisticsQuery query) {
        Result<List<AppDataPushStatistics>> result = new Result<List<AppDataPushStatistics>>();
        int count = dao.selectCountByQuery(query);
        List<AppDataPushStatistics> list = dao.selectPageByQuery(query);
        if(list.size()>0)list.add(this.selectAvgByAllData());
        result.setModel(list);
        query.setTotalItem(count);
        result.setQuery(query);
        return result;
    }

    @Override
    public AppDataPushStatistics selectByCategory(String avgVal) {
        return dao.selectByCategory(avgVal);
    }

    @Override
    public AppDataPushStatistics selectAvgByAllData() {
        return dao.selectAvgByAllData();
    }

    @Override
    public List<AppDataPushStatistics> getEveryDaySumRate() {
        return dao.getEveryDaySumRate();
    }

    /**
     * 上午的统计数据
     * @return
     */
    @Override
    public List<AppDataPushStatistics> getAmPushData() {
        return dao.getAmPushData();
    }

    /**
     * 下午的是统计数据
     * @return
     */
    @Override
    public List<AppDataPushStatistics> getPmPushData() {
        return dao.getPmPushData();
    }
}