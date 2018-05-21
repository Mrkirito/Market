package com.hangjia.bxj.dao.firendcircle;

import com.hangjia.bxj.model.firendcircle.FriendCircleStatistics;

import java.util.List;

public interface FriendCircleStatisticsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FriendCircleStatistics record);

    int insertSelective(FriendCircleStatistics record);

    FriendCircleStatistics selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FriendCircleStatistics record);

    int updateByPrimaryKey(FriendCircleStatistics record);

    int selectBySelectiveCount(FriendCircleStatistics record);

    /**************************** extend begin here *******************************/
    /**
     * 分页查询
     * @param record
     * @return
     */
    List<FriendCircleStatistics> selectByPage(FriendCircleStatistics record);

    /**
     * 查询总个数
     * @param record
     * @return
     */
    int selectCount(FriendCircleStatistics record);

    /**
     * 删除喜欢
     * @param record
     * @return
     */
    int deleteByUserId(FriendCircleStatistics record);
}