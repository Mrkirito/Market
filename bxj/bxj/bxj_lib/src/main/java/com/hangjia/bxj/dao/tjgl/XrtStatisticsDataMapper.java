package com.hangjia.bxj.dao.tjgl;

import com.hangjia.bxj.common.BaseCommonQuery;
import com.hangjia.bxj.model.tjgl.XrtStatisticsData;
import com.hangjia.bxj.query.report.ProductDataReportQuery;
import com.hangjia.bxj.query.tjgl.XrtStatisticsDataQuery;
import com.hangjia.bxj.vo.StatisticsDataVo;

import java.util.List;

public interface XrtStatisticsDataMapper {
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

    List<XrtStatisticsData> queryXrtDataPage(ProductDataReportQuery query);

    List<XrtStatisticsData> getAllBxjAppXrtDatas();
}