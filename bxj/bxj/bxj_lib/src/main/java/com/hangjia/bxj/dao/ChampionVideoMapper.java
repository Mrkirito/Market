package com.hangjia.bxj.dao;

import java.util.List;
import java.util.Map;

import com.baobao.framework.page.Paginator;
import com.hangjia.bxj.model.ChampionVideo;
import com.hangjia.bxj.model.champion.VideoCommentDO;
import com.hangjia.bxj.model.champion.VideoReportDO;
import com.hangjia.bxj.model.order.AccountOrderExt;
import com.hangjia.bxj.model.order.Reward;
import com.hangjia.bxj.query.champion.ChampionVideoQuery;
import com.hangjia.bxj.query.champion.VideoCommentQuery;
import com.hangjia.bxj.query.champion.VideoReportQuery;
import com.hangjia.bxj.query.order.RewardQuery;

public interface ChampionVideoMapper {
    int deleteByPrimaryKey(Long fid);

    int insert(ChampionVideo record);

    int insertSelective(ChampionVideo record);

    ChampionVideo selectByPrimaryKey(Long fid);

    int updateByPrimaryKeySelective(ChampionVideo record);

    int updateByPrimaryKey(ChampionVideo record);

    List<ChampionVideo> selectBySelective(ChampionVideo record);

    int selectBySelectiveCount(ChampionVideo championVideo);

    /**
     * 根据频道查询视频数据
     * @param map
     * @return
     */
    List<ChampionVideo> getVideosByModuleId(Map<String, Object> map);

    List<ChampionVideo> selectPaginateVideo(Paginator paginator);

    int selectByPaginatorCount(Paginator paginator);

    /*List<ChampionVideo> storeList(Map<String, Object> map);*/

    /**
     * 我的收藏总条数
     * @param map
     * @return
     */
    int myCollectCount(Map<String, Object> map);
    /**
     * 我的收藏
     * @param map  参数map包含用户id及页码和页面数量
     * @return
     */
    List<ChampionVideo> myCollectList(Map<String, Object> map);

    /**
     * 我的视频总条数
     * @param map
     * @return
     */
    int myStoreCount(Map<String, Object> map);
    /**
     * 我的视频
     * @param map 参数map包含用户id及页码和页面数量
     * @return
     */
    List<ChampionVideo> myStoreList(Map<String, Object> map);
    
    /**
     * 视频详情分页
     * @param query
     * @return
     */
    List<ChampionVideo> queryPageData(ChampionVideoQuery query);
    /**
     * 视频总数
     * @param query
     * @return
     */
    int queryPageDataCount(ChampionVideoQuery query);
    
    /**
     * 查询单一Video
     * @param query
     * @return
     */
    ChampionVideo queryVideo(ChampionVideoQuery query);
    
    /**
     * 音/视频评论列表分页
     * @param query
     * @return
     */
    List<VideoCommentDO> queryCommentPageData(VideoCommentQuery query);
    /**
     * 音/视频评论列表总数
     * @param query
     * @return
     */
    int queryCommentPageDataCount(VideoCommentQuery query);
    
    /**
     * 删除音/视频评论
     * @param query
     * @return
     */
    int deleteVideoComment(VideoCommentDO videoCommentDO);
    
    /**
     * 根据订单查询音/视频标题
     * @param query
     * @return
     */
    List<AccountOrderExt> queryVideoNameByOrderNo(List<String> orderNos);
    
    /**
     * 音/视频举报列表总数
     * @param query
     * @return
     */
    int queryReportDataCount(VideoReportQuery query);
    
    /**
     * 音/视频举报列表
     * @param query
     * @return
     */
    List<VideoReportDO> queryReportPageData(VideoReportQuery query);

    /**
     * 审核视频评论-审核不通过
     * @param videoId
     * @return
     */
    int unAuthorVideoComment(Long videoId);
}