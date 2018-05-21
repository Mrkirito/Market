package com.hangjia.bxj.service.vdinvitecount.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hangjia.bxj.dao.ChampionInviteMapper;
import com.hangjia.bxj.dao.ChampionUserVocherLogMapper;
import com.hangjia.bxj.query.app.LookVideoQuery;
import com.hangjia.bxj.service.vdinvitecount.IVideoInviteService;
import com.hangjia.bxj.vo.LookVideoDetailVo;
import com.hangjia.bxj.vo.LookVideoVo;
import com.hangjia.bxj.vo.VideoInviteCountVo;

@Service
@Transactional(rollbackFor = Throwable.class)
public class VideoInviteServiceImpl implements IVideoInviteService {

	@Autowired
	private ChampionInviteMapper championInviteMapper;
	
	@Autowired 
	private ChampionUserVocherLogMapper championUserVocherLogMapper;
	
	@Override
	public List<VideoInviteCountVo> qryInviteInfo(VideoInviteCountVo invite) {
		//获取邀请信息
		List<VideoInviteCountVo> list =championInviteMapper.selInviteInfo(invite);
		for (VideoInviteCountVo invitevo : list) { 
			if(invitevo.getInviteUserId()!=null){
				//获取 用户手机号
				VideoInviteCountVo inviteget=championInviteMapper.selUserInfo(invitevo);
				if(inviteget!=null){
					invitevo.setMobile(inviteget.getMobile());
				}
			}	
			
		}
		return	list;
	}

	@Override
	public int qryGroupUserNum(VideoInviteCountVo invite) {
		return championInviteMapper.selGroupUserTotal(invite);
	}

	@Override
	public int qryInviteInfoTotal(VideoInviteCountVo invite) {
		return championInviteMapper.selTotal(invite);
	}

	@Override
	public List<VideoInviteCountVo> qryVideoInfo(VideoInviteCountVo video) {
		return championUserVocherLogMapper.selVourcherList(video); 
	}

	@Override
	public int qryVideoInfoTotal(VideoInviteCountVo video) {
		return championUserVocherLogMapper.selVourcherTotal(video);
	}

	@Override
	public int qryVDVourcherNum(VideoInviteCountVo video) {
		return championUserVocherLogMapper.selVDVourcherNum(video); 
	}

	/**
	 * 查询每个视频用券 总数量
	 * @param video
	 * @return
	 */
	@Override
	public List<LookVideoVo> queryPageData(LookVideoQuery query) {
		return championUserVocherLogMapper.queryPageData(query);
	}

	/**
	 * 查询 视频用券信息
	 * @param video
	 * @return
	 */
	@Override
	public int queryPageDataCount(LookVideoQuery query) {
		return championUserVocherLogMapper.queryPageDataCount(query);
	}

	/**
	 * 个人在指定天观看视频详情
	 * @param query
	 * @return
	 */
	@Override
	public List<LookVideoDetailVo> queryPageDetailData(LookVideoQuery query) {
		return championUserVocherLogMapper.queryPageDetailData(query);
	}

	/**
	 * 个人在指定天观看视频数量
	 * @param query
	 * @return
	 */
	@Override
	public int queryPageDetailDataCount(LookVideoQuery query) {
		return championUserVocherLogMapper.queryPageDetailDataCount(query);
	}
	
}
