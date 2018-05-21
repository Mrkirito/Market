/*
* Copyright (c) 2kmhStatisticsDataMapper.16 www.baobao18.com. All Rights Reserved.
*/
package com.hangjia.bxj.service.tjgl.impl;

import com.hangjia.bxj.common.BaseCommonQuery;
import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.dao.tjgl.KmhStatisticsDataMapper;
import com.hangjia.bxj.model.tjgl.KmhStatisticsData;
import com.hangjia.bxj.query.tjgl.KmhStatisticsDataQuery;
import com.hangjia.bxj.service.tjgl.KmhStatisticsDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Caigp
 * Date: 2016/11/10
 */
@Service
public class KmhStatisticsDataServiceImpl implements KmhStatisticsDataService {

    @Autowired
    private KmhStatisticsDataMapper kmhStatisticsDataMapper;
    
    @Override
    public int deleteByPrimaryKey(Long id) {
        return kmhStatisticsDataMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(KmhStatisticsData record) {
        return kmhStatisticsDataMapper.insert(record);
    }

    @Override
    public int insertSelective(KmhStatisticsData record) {
        return kmhStatisticsDataMapper.insertSelective(record);
    }

    @Override
    public KmhStatisticsData selectByPrimaryKey(Long id) {
        return kmhStatisticsDataMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(KmhStatisticsData record) {
        return kmhStatisticsDataMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(KmhStatisticsData record) {
        return kmhStatisticsDataMapper.updateByPrimaryKey(record);
    }

    @Override
    public int selectByCount(BaseCommonQuery query) {
        return kmhStatisticsDataMapper.selectByCount(query);
    }

    @Override
    public List<KmhStatisticsData> selectByPage(BaseCommonQuery query) {
        return kmhStatisticsDataMapper.selectByPage(query);
    }

    @Override
    public int selectCountByQuery(KmhStatisticsDataQuery query) {
        return kmhStatisticsDataMapper.selectCountByQuery(query);
    }

    @Override
    public List<KmhStatisticsData> selectPageByQuery(KmhStatisticsDataQuery query) {
        return kmhStatisticsDataMapper.selectPageByQuery(query);
    }

    @Override
    public List<KmhStatisticsData> getAllBxjAppKmhDatas() {
        return kmhStatisticsDataMapper.getAllBxjAppKmhDatas();
    }

    @Override
    public Result getPageListByQuery(KmhStatisticsDataQuery kmhStatisticsDataQuery) {
        Result<List<KmhStatisticsData>> result = new Result<List<KmhStatisticsData>>();
        int count = kmhStatisticsDataMapper.selectCountByQuery(kmhStatisticsDataQuery);
        List<KmhStatisticsData> list = kmhStatisticsDataMapper.selectPageByQuery(kmhStatisticsDataQuery);
        result.setModel(list);
        kmhStatisticsDataQuery.setTotalItem(count);
        result.setQuery(kmhStatisticsDataQuery);
        return result;
    }
}