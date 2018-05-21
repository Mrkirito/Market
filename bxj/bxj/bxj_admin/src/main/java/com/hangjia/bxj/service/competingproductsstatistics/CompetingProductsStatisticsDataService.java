/*
* Copyright (c) 2016 www.baobao18.com. All Rights Reserved.
*/
package com.hangjia.bxj.service.competingproductsstatistics;

import java.util.List;

import com.hangjia.bxj.common.BaseCommonQuery;
import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.model.competingproductsstatistics.CompetingProductsStatisticsData;
import com.hangjia.bxj.query.competingproductsstatistics.CompetingProductsStatisticsDataQuery;

public interface CompetingProductsStatisticsDataService {



    int insertSelective(CompetingProductsStatisticsData record);

    CompetingProductsStatisticsData selectByPrimaryKey(Long id);
    
    CompetingProductsStatisticsData getCpsDatasByDate(String date);

    int updateByPrimaryKeySelective(CompetingProductsStatisticsData record);




    /**************************** extend begin here *******************************/
    /**
     * 总个数
     * @param query
     * @return
     */
    int selectByCount(BaseCommonQuery query);

    /**
     * 分页查询
     * @param query
     * @return
     */
    List<CompetingProductsStatisticsData> selectByPage(BaseCommonQuery query);

    /**
     * 总个数
     * @param query
     * @return
     */
    int selectCountByQuery(CompetingProductsStatisticsDataQuery query);

    /**
     * 分页查询
     * @param query
     * @return
     */
    List<CompetingProductsStatisticsData> selectPageByQuery(CompetingProductsStatisticsDataQuery query);

    /**
     * echarts 图标数据
     * @return
     */
    List<CompetingProductsStatisticsData> getAllBxjAppKmhDatas();

    /**
     * 列表展现数据
     * @param CompetingProductsStatisticsDataQuery
     * @return
     */
    Result getPageListByQuery(CompetingProductsStatisticsDataQuery CompetingProductsStatisticsDataQuery);
}
