/*
* Copyright (c) 2016 www.baobao18.com. All Rights Reserved.
*/
package com.hangjia.bxj.service.tjgl;

import com.hangjia.bxj.common.BaseCommonQuery;
import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.model.tjgl.KmhStatisticsData;
import com.hangjia.bxj.query.tjgl.KmhStatisticsDataQuery;

import java.util.List;

/**
 * Created by Caigp
 * Date: 2016/11/10 11:53
 */
public interface KmhStatisticsDataService {

    int deleteByPrimaryKey(Long id);

    int insert(KmhStatisticsData record);

    int insertSelective(KmhStatisticsData record);

    KmhStatisticsData selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(KmhStatisticsData record);

    int updateByPrimaryKey(KmhStatisticsData record);



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
    List<KmhStatisticsData> selectByPage(BaseCommonQuery query);

    /**
     * 总个数
     * @param query
     * @return
     */
    int selectCountByQuery(KmhStatisticsDataQuery query);

    /**
     * 分页查询
     * @param query
     * @return
     */
    List<KmhStatisticsData> selectPageByQuery(KmhStatisticsDataQuery query);

    /**
     * echarts 图标数据
     * @return
     */
    List<KmhStatisticsData> getAllBxjAppKmhDatas();

    /**
     * 列表展现数据
     * @param kmhStatisticsDataQuery
     * @return
     */
    Result getPageListByQuery(KmhStatisticsDataQuery kmhStatisticsDataQuery);
}
