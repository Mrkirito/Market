package com.hangjia.bxj.dao.tjgl;

import com.hangjia.bxj.common.BaseCommonQuery;
import com.hangjia.bxj.model.tjgl.KmhStatisticsData;
import com.hangjia.bxj.model.tjgl.XrtStatisticsData;
import com.hangjia.bxj.query.report.ProductDataReportQuery;
import com.hangjia.bxj.query.tjgl.KmhStatisticsDataQuery;

import java.util.List;

public interface KmhStatisticsDataMapper {
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


    List<KmhStatisticsData> getAllBxjAppKmhDatas();


    List<KmhStatisticsData> queryKmhDataPage(ProductDataReportQuery query);
}