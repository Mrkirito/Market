package com.hangjia.bxj.dao.firendcircle;

import com.hangjia.bxj.model.firendcircle.FriendCircle;
import com.hangjia.bxj.query.app.FriendCircleQuery;

import java.util.List;

public interface FriendCircleMapper {
    int deleteByPrimaryKey(Long fid);

    int insert(FriendCircle record);

    int insertSelective(FriendCircle record);

    FriendCircle selectByPrimaryKey(Long fid);

    int updateByPrimaryKeySelective(FriendCircle record);

    int updateByPrimaryKeyWithBLOBs(FriendCircle record);

    int updateByPrimaryKey(FriendCircle record);

    /**************************** extend begin here *******************************/
    /**
     * 分页查询
     * @param record
     * @return
     */
    List<FriendCircle> selectByPage(FriendCircle record);

    /**
     * 查询总个数
     * @param record
     * @return
     */
    int selectCount(FriendCircle record);

    /**
     * 分页查询:朋友圈首页
     * @param record
     * @return
     */
    List<FriendCircle> selectIndexByPage(FriendCircle record);

    /**
     * 查询总个数：朋友圈首页
     * @param record
     * @return
     */
    int selectIndexCount(FriendCircle record);

      /**
     * 分页查询:朋友圈首页
     * @param record
     * @return
     */
    List<FriendCircle> selectIndexByPage_v32(FriendCircle record);

    /**
     * 分页查询:朋友圈首页匹配保险公司
     * @param record
     * @return
     */
    List<FriendCircle> selectIndexByPage_company(FriendCircle record);


    /**
     * 分页查询:朋友圈人气排行
     * @param record
     * @return
     */
    List<FriendCircle> selectRankCategory(FriendCircle record);


    /**
     * 分页查询:首页分类朋友圈
     * @param record
     * @return
     */
    List<FriendCircle> selectRankCategoryOrderByTime(FriendCircle record);

    /**
     * 查询总个数
     * @param record
     * @return
     */
    int selectRankCategoryCount(FriendCircle record);

    /**
     * 重置周分享数量
     * @param record
     * @return
     */
    int clearWeekShareCount(FriendCircle record);

    /**
     * 后台
     * 查询所有朋友圈 信息
     * @param query
     * @return
     */
    List<FriendCircle> queryPageData(FriendCircleQuery query);

    /**
     * 后台
     * 查询所有朋友圈 总条数
     * @param query
     * @return
     */
    int queryPageDataCount(FriendCircleQuery query);
    /**
     * 后台
     * 修改朋友圈
     * @param record
     * @return
     */
    int updateByPK(FriendCircle record);

    /**
     * 后台
     * 修改朋友圈 图文上传
     * @param record
     * @return
     */
    int updateImgsByPK(FriendCircle record);
}