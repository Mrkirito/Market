/*
* Copyright (c) 2CompetingProductsStatisticsDataMapper.16 www.baobao18.com. All Rights Reserved.
*/
package com.hangjia.bxj.service.competingproductsstatistics.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hangjia.bxj.common.BaseCommonQuery;
import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.dao.competingproductsstatistics.CompetingProductsStatisticsDataMapper;
import com.hangjia.bxj.model.competingproductsstatistics.CompetingProductsStatisticsData;
import com.hangjia.bxj.query.competingproductsstatistics.CompetingProductsStatisticsDataQuery;
import com.hangjia.bxj.service.competingproductsstatistics.CompetingProductsStatisticsDataService;

/**
 * 
* @author yuanxin
* @date 2017年6月6日上午11:56:21
* @version <b>1.0.0</b>
 */
@Service
public class CompetingProductsStatisticsDataServiceImpl implements CompetingProductsStatisticsDataService {

    @Autowired
    private CompetingProductsStatisticsDataMapper competingProductsStatisticsDataMapper;
    

    @Override
    public int insertSelective(CompetingProductsStatisticsData record) {
        return competingProductsStatisticsDataMapper.insertSelective(record);
    }

    @Override
    public CompetingProductsStatisticsData selectByPrimaryKey(Long id) {
        return competingProductsStatisticsDataMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CompetingProductsStatisticsData record) {
        return competingProductsStatisticsDataMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int selectByCount(BaseCommonQuery query) {
        return competingProductsStatisticsDataMapper.selectByCount(query);
    }

    @Override
    public List<CompetingProductsStatisticsData> selectByPage(BaseCommonQuery query) {
        return competingProductsStatisticsDataMapper.selectByPage(query);
    }

    @Override
    public int selectCountByQuery(CompetingProductsStatisticsDataQuery query) {
        return competingProductsStatisticsDataMapper.selectCountByQuery(query);
    }

    @Override
    public List<CompetingProductsStatisticsData> selectPageByQuery(CompetingProductsStatisticsDataQuery query) {
        return competingProductsStatisticsDataMapper.selectPageByQuery(query);
    }

    @Override
    public List<CompetingProductsStatisticsData> getAllBxjAppKmhDatas() {
        return competingProductsStatisticsDataMapper.getAllBxjAppKmhDatas();
    }

    @Override
    public Result getPageListByQuery(CompetingProductsStatisticsDataQuery CompetingProductsStatisticsDataQuery) {
        Result<List<CompetingProductsStatisticsData>> result = new Result<List<CompetingProductsStatisticsData>>();
        int count = competingProductsStatisticsDataMapper.selectCountByQuery(CompetingProductsStatisticsDataQuery);
        List<CompetingProductsStatisticsData> list = competingProductsStatisticsDataMapper.selectPageByQuery(CompetingProductsStatisticsDataQuery);
        result.setModel(list);
        CompetingProductsStatisticsDataQuery.setTotalItem(count);
        result.setQuery(CompetingProductsStatisticsDataQuery);
        return result;
    }

	@Override
	public CompetingProductsStatisticsData getCpsDatasByDate(String date) {
		return competingProductsStatisticsDataMapper.getCpsDatasByDate(date);
		
	}
}