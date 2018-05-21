package com.hangjia.bxj.service.vdinvitecount;

import java.util.List;

import com.hangjia.bxj.query.app.LookVideoQuery;
import com.hangjia.bxj.vo.LookVideoDetailVo;
import com.hangjia.bxj.vo.LookVideoVo;
import com.hangjia.bxj.vo.VideoInviteCountVo;

/**
 * 统计  查询视频和邀请总人数
 * @ClassName: 
 * @Description: 
 * @author: he-Yi
 * @date: 2016年5月30日 下午1:59:29
 */
public interface IVideoInviteService {
	
	/**
	 * 查询邀请信息 
	 * @param invite
	 * @return
	 */
	List<VideoInviteCountVo> qryInviteInfo(VideoInviteCountVo invite);
	
	/**
	 * 查询 邀请信息的总条数
	 * @return
	 */
	int qryInviteInfoTotal(VideoInviteCountVo invite);
	
	/**
	 * 邀请人分组  总条数 
	 * @param invite
	 * @return
	 */
	int qryGroupUserNum(VideoInviteCountVo invite);
	
	/**
	 * 查询每个 视频信息
	 * @param video
	 * @return
	 */
	List<VideoInviteCountVo> qryVideoInfo(VideoInviteCountVo video);
	
	/**
	 * 查询 视频信息的总条数
	 * @return
	 */
	int qryVideoInfoTotal(VideoInviteCountVo video);
	
	/**
	 * 每个视频用券数
	 * @param video
	 * @return
	 */
	int qryVDVourcherNum(VideoInviteCountVo video);
	
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
