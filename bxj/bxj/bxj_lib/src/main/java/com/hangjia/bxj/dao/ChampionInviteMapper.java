package com.hangjia.bxj.dao;

import java.util.List;
import java.util.Map;

import com.baobao.framework.page.Paginator;
import com.hangjia.bxj.model.ChampionInvite;
import com.hangjia.bxj.vo.VideoInviteCountVo;

public interface ChampionInviteMapper {
	int deleteByPrimaryKey(Long fid);

	int insert(ChampionInvite record);

	int insertSelective(ChampionInvite record);

	ChampionInvite selectByPrimaryKey(Long fid);

	int updateByPrimaryKeySelective(ChampionInvite record);

	int updateByPrimaryKey(ChampionInvite record);

	List<ChampionInvite> selectBySelective(ChampionInvite record);
	
	/**
	 * 用户的邀请人总数
	 */
	int selectCountInvite(ChampionInvite championInvite);

	/**
	 * 用户的邀请人,并下载app成功总数
	 */
	Long queryInviteUser(ChampionInvite championInvite);

	Long selectCountDownInvite(Map map);

	Integer selectByPaginatorCount(Paginator paginator);

	List selectPaginateInvite(Paginator paginator);
	
	/**
	 * 统计 邀请总 人数
	 * @param invite
	 * @return
	 */
	int selTotal(VideoInviteCountVo invite);
	
	/**
	 * 查询邀请人ID分组总数
	 * @param invite
	 * @return
	 */
	int selGroupUserTotal(VideoInviteCountVo invite);
	
	/**
	 * 统计-- 查询邀请信息
	 * @param invite
	 * @return
	 */
    List<VideoInviteCountVo> selInviteInfo(VideoInviteCountVo invite);
    
    /**
     * 获取用户信息
     * @return
     */
    VideoInviteCountVo selUserInfo(VideoInviteCountVo invite);

}