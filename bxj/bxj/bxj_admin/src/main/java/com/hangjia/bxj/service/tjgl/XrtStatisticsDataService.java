/*
* Copyright (c) 2016 www.baobao18.com. All Rights Reserved.
*/
package com.hangjia.bxj.service.tjgl;

import com.hangjia.bxj.common.BaseCommonQuery;
import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.model.tjgl.XrtStatisticsData;
import com.hangjia.bxj.query.tjgl.XrtStatisticsDataQuery;

import java.util.List;

/**
 * Created by Caigp
 * Date: 2016/11/10 11:54
 */
public interface XrtStatisticsDataService {

    int deleteByPrimaryKey(Long id);

    int insert(XrtStatisticsData record);

    int insertSelective(XrtStatisticsData record);

    XrtStatisticsData selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(XrtStatisticsData record);

    int updateByPrimaryKey(XrtStatisticsData record);


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
    List<XrtStatisticsData> selectByPage(BaseCommonQuery query);

    /**
     * 总个数
     * @param query
     * @return
     */
    int selectCountByQuery(XrtStatisticsDataQuery query);

    /**
     * 分页查询
     * @param query
     * @return
     */
    List<XrtStatisticsData> selectPageByQuery(XrtStatisticsDataQuery query);

    /**
     * echarts 图标数据
     * @return
     */
    List<XrtStatisticsData> getAllBxjAppXrtDatas();


    Result getPageListByQuery(XrtStatisticsDataQuery query);
}
