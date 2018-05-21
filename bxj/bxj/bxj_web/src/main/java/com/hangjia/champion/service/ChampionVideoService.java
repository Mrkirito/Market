package com.hangjia.champion.service;

import com.hangjia.bxj.model.ChampionUserVoucher;
import com.hangjia.bxj.model.ChampionVideo;
import com.hangjia.bxj.mvc.AjaxResult;
import com.hangjia.bxj.vo.ChampionModuleVideo;
import com.hangjia.bxj.vo.Pagination;
import com.hangjia.bxj.vo.VideoAuthorityVo;

import java.util.List;

public interface ChampionVideoService {

	/**
	 * @Title: queryAllChampionVideo
	 * @Description: 根据分类分页获取所有视频, 排序规则 1.是否推荐 2.按上架时间
	 * @param channelId
	 * @param currPage
	 * @param pageSize
	 * @return
	 */
	Pagination<ChampionVideo> queryAllChampionVideo(Long channelId, Integer currPage, Integer pageSize);

	/**
	 * @Title: queryOtherChampionVideo
	 * @Description: 根据分类分页获取所有视频, 排序规则 1.按播放次数
	 * @param channelId
	 * @param currPage
	 * @param pageSize
	 * @return
	 */
	Pagination<ChampionVideo> queryOtherChampionVideo(Long channelId, Integer currPage, Integer pageSize);

	/**
	 * @Title: queryChampionVideoByFid
	 * @Description: 根据视频主键获取视频详情
	 * @param fid
	 * @return
	 */
	ChampionVideo queryChampionVideoByFid(Long fid);

	/**
	 * @Title: playChampionVideo
	 * @Description: 播放视频
	 * @param fid
	 * @param userId
	 */
	void playChampionVideo(Long fid, Long userId);

	/**
	 * @Title: shareChampionVideo
	 * @Description: 分享视频
	 * @param fid
	 */
	void shareChampionVideo(Integer userId, Long fid);

	/**
	 * @Title: queryUserVoucherTotal
	 * @Description: 查询用户拥有的券总数
	 * @param userId
	 * @param voucherId
	 * @return
	 */
	ChampionUserVoucher queryUserVoucherTotal(Long userId, Long voucherId);

	/**
	 * @Title: useVideoVoucher
	 * @Description: 观看视频使用券消费
	 * @param userId
	 * @param videoId
	 * @return
	 */
	boolean useVideoVoucher(Long userId, Long videoId);

	/**
	 * 本周热门
	 * @param channelId
	 * @param index
	 * @param size
	 * @return
	 */
	List<ChampionVideo> getVideosByModuleId(Long channelId, Integer index, Integer size);

	/**
	 * 精选
	 * @return
	 */
	Pagination<ChampionModuleVideo> getVideosJx(Integer currPage, Integer pageSize);

	/**
	 * 视频收藏/取消收藏
	 * @param userId
	 * @param videoId
	 * @param type
	 * @return
	 */
	int storeOrCancle(Long userId, Long videoId, Integer type);

	/**
	 * 用户权限信息
	 * @param userId
	 * @return
	 */
	VideoAuthorityVo getVideoAuthorityVo(Integer userId, ChampionVideo video);

	/** 
	 * @Title: isStore 
	 * @Description: 是否收藏
	 * @param userId
	 * @param videoId
	 * @return
	 */
	boolean isStore(Integer userId, Long videoId);
	
	/**
	 * 更新视频浏览次数
	 * @param fid
	 */
	void updateVideoBrowserNum(Long fid);
}