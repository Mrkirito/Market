package com.hangjia.bxj.dao.firendcircle;

import com.hangjia.bxj.model.firendcircle.FriendCircleWeekRank;

import java.util.List;

public interface FriendCircleWeekRankMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FriendCircleWeekRank record);

    int insertSelective(FriendCircleWeekRank record);

    FriendCircleWeekRank selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FriendCircleWeekRank record);

    int updateByPrimaryKey(FriendCircleWeekRank record);

    /**************************** extend begin here *******************************/
    /**
     * 查询总条数
     * @return
     */
    int selectCount(FriendCircleWeekRank record);

    /**
     * 分页查询
     * @param record
     * @return
     * */
    List<FriendCircleWeekRank> selectByPage(FriendCircleWeekRank record);

    /**
     * 按日期表插入
     * @param record
     * @return
     */
    int insertSelectiveByTable(FriendCircleWeekRank record);

    /**
     * 按日期表更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelectiveByTable(FriendCircleWeekRank record);

    /**
     * 表是不存在
     * @return
     */
    int selectTableCount(FriendCircleWeekRank record);

    /**
     * 创建表
     * @return
     */
    int createNewTable(FriendCircleWeekRank record);
}