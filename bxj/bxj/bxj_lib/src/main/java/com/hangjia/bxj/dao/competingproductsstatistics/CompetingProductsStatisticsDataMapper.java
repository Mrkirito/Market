package com.hangjia.bxj.dao.competingproductsstatistics;

import java.util.List;

import com.hangjia.bxj.common.BaseCommonQuery;
import com.hangjia.bxj.model.competingproductsstatistics.CompetingProductsStatisticsData;
import com.hangjia.bxj.query.competingproductsstatistics.CompetingProductsStatisticsDataQuery;
import com.hangjia.bxj.query.report.ProductDataReportQuery;

public interface CompetingProductsStatisticsDataMapper {
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


    List<CompetingProductsStatisticsData> getAllBxjAppKmhDatas();


    List<CompetingProductsStatisticsData> queryKmhDataPage(ProductDataReportQuery query);
}