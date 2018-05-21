/*
* Copyright (c) 2016 www.baobao18.com. All Rights Reserved.
*/
package com.hangjia.bxj.service.tjgl.impl;

import com.hangjia.bxj.common.BaseCommonQuery;
import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.dao.tjgl.XrtStatisticsDataMapper;
import com.hangjia.bxj.model.tjgl.KmhStatisticsData;
import com.hangjia.bxj.model.tjgl.XrtStatisticsData;
import com.hangjia.bxj.query.tjgl.XrtStatisticsDataQuery;
import com.hangjia.bxj.service.tjgl.XrtStatisticsDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Caigp
 * Date: 2016/11/10 14:03
 */
@Service
public class XrtStatisticsDataServiceImpl implements XrtStatisticsDataService {

    @Autowired
    private XrtStatisticsDataMapper xrtStatisticsDataMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return xrtStatisticsDataMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(XrtStatisticsData record) {
        return xrtStatisticsDataMapper.insert(record);
    }

    @Override
    public int insertSelective(XrtStatisticsData record) {
        return xrtStatisticsDataMapper.insertSelective(record);
    }

    @Override
    public XrtStatisticsData selectByPrimaryKey(Long id) {
        return xrtStatisticsDataMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(XrtStatisticsData record) {
        return xrtStatisticsDataMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(XrtStatisticsData record) {
        return xrtStatisticsDataMapper.updateByPrimaryKey(record);
    }

    @Override
    public int selectByCount(BaseCommonQuery query) {
        return xrtStatisticsDataMapper.selectByCount(query);
    }

    @Override
    public List<XrtStatisticsData> selectByPage(BaseCommonQuery query) {
        return xrtStatisticsDataMapper.selectByPage(query);
    }

    @Override
    public int selectCountByQuery(XrtStatisticsDataQuery query) {
        return xrtStatisticsDataMapper.selectCountByQuery(query);
    }

    @Override
    public List<XrtStatisticsData> selectPageByQuery(XrtStatisticsDataQuery query) {
        return xrtStatisticsDataMapper.selectPageByQuery(query);
    }

    @Override
    public List<XrtStatisticsData> getAllBxjAppXrtDatas() {
        return xrtStatisticsDataMapper.getAllBxjAppXrtDatas();
    }

    @Override
    public Result getPageListByQuery(XrtStatisticsDataQuery query) {
        Result<List<XrtStatisticsData>> result = new Result<List<XrtStatisticsData>>();
        int count = xrtStatisticsDataMapper.selectCountByQuery(query);
        List<XrtStatisticsData> list = xrtStatisticsDataMapper.selectPageByQuery(query);
        result.setModel(list);
        query.setTotalItem(count);
        result.setQuery(query);
        return result;
    }
}