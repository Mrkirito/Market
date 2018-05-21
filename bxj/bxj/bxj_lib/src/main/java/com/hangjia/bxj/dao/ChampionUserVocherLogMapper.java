package com.hangjia.bxj.dao;

import java.util.List;

import com.hangjia.bxj.model.ChampionUserVocherLog;
import com.hangjia.bxj.query.app.LookVideoQuery;
import com.hangjia.bxj.vo.LookVideoDetailVo;
import com.hangjia.bxj.vo.LookVideoVo;
import com.hangjia.bxj.vo.VideoInviteCountVo;

public interface ChampionUserVocherLogMapper {
	int deleteByPrimaryKey(Long fid);

	int insert(ChampionUserVocherLog record);

	int insertSelective(ChampionUserVocherLog record);

	ChampionUserVocherLog selectByPrimaryKey(Long fid);

	int updateByPrimaryKeySelective(ChampionUserVocherLog record);

	int updateByPrimaryKey(ChampionUserVocherLog record);

	List<ChampionUserVocherLog> selectBySelective(ChampionUserVocherLog record);

	/**
	 * 用户是否已观看此视频
	 * @param record
	 * @return
     */
	int getWatchedCoumt(ChampionUserVocherLog record);

	/**
	 * 按type查询个数
	 * @param record
	 * @return
     */
	int geInviteVourcherCoumt(ChampionUserVocherLog record);
	
	/**
	 * 查询视频用券 总数量
	 * @param video
	 * @return
	 */
	int selVourcherTotal(VideoInviteCountVo video);
	
	/**
	 * 查询每个视频用券 总数量
	 * @param video
	 * @return
	 */
	int selVDVourcherNum(VideoInviteCountVo video);
	
	/**
	 * 查询 视频用券信息
	 * @param video
	 * @return
	 */
	List<VideoInviteCountVo> selVourcherList(VideoInviteCountVo video);
	
	/**
	 * 统计每个视频的观看次数和用券数列表
	 * @param query
	 * @return
	 */
	List<LookVideoVo> queryPageData(LookVideoQuery query);

	/**
	 * 统计每个视频的观看次数和用券数总量
	 * @param query
	 * @return
	 */
	int queryPageDataCount(LookVideoQuery query);
	
	/**
	 * 个人在指定天观看视频详情
	 * @param query
	 * @return
	 */
	List<LookVideoDetailVo> queryPageDetailData(LookVideoQuery query);

	/**
	 * 个人在指定天观看视频数量
	 * @param query
	 * @return
	 */
	int queryPageDetailDataCount(LookVideoQuery query);
}