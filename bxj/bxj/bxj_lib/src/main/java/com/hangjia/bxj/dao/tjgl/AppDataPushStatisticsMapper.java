package com.hangjia.bxj.dao.tjgl;

import com.hangjia.bxj.common.BaseCommonQuery;
import com.hangjia.bxj.model.tjgl.AppDataPushStatistics;
import com.hangjia.bxj.query.tjgl.AppDataPushStatisticsQuery;

import java.util.List;

public interface AppDataPushStatisticsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AppDataPushStatistics record);

    int insertSelective(AppDataPushStatistics record);

    AppDataPushStatistics selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AppDataPushStatistics record);

    int updateByPrimaryKey(AppDataPushStatistics record);



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
    List<AppDataPushStatistics> selectByPage(BaseCommonQuery query);

    /**
     * 总个数
     * @param query
     * @return
     */
    int selectCountByQuery(AppDataPushStatisticsQuery query);

    /**
     * 分页查询
     * @param query
     * @return
     */
    List<AppDataPushStatistics> selectPageByQuery(AppDataPushStatisticsQuery query);

    int getAppDataPushStatisticsByDate(String date);


    AppDataPushStatistics selectByCategory(String avgVal);

    AppDataPushStatistics selectAvgByAllData();

    List<AppDataPushStatistics> getAmPushData();
    List<AppDataPushStatistics> getPmPushData();

    List<AppDataPushStatistics> getEveryDaySumRate();

}