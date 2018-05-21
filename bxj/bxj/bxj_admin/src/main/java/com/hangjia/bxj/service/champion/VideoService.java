package com.hangjia.bxj.service.champion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hangjia.bxj.dao.ChampionUserDataMapper;
import com.hangjia.bxj.dao.ChampionVideoMapper;
import com.hangjia.bxj.dao.ChampionVideoTagMapper;
import com.hangjia.bxj.model.ChampionTag;
import com.hangjia.bxj.model.ChampionUserData;
import com.hangjia.bxj.model.ChampionVideo;
import com.hangjia.bxj.model.ChampionVideoTag;
import com.hangjia.bxj.model.champion.VideoCommentDO;
import com.hangjia.bxj.model.champion.VideoReportDO;
import com.hangjia.bxj.model.order.Reward;
import com.hangjia.bxj.mvc.aop.annotation.MethodLog;
import com.hangjia.bxj.mvc.common.AdminConstants;
import com.hangjia.bxj.mvc.util.RandomUtil;
import com.hangjia.bxj.query.champion.ChampionVideoQuery;
import com.hangjia.bxj.query.champion.VideoReportQuery;
import com.hangjia.bxj.query.order.RewardQuery;
import com.hangjia.bxj.ucenter.dao.AccountOrderMapper;
import com.hangjia.bxj.ucenter.model.UcUser;

@Service
@Transactional(rollbackFor=Throwable.class)
public class VideoService{
	
	@Autowired
	private ChampionVideoMapper championVideoMapper;
	
	@Autowired
	private ChampionVideoTagMapper championVideoTagMapper;
	
	@Autowired
	private ChampionUserDataMapper championUserDataMapper;
	
	@Autowired
	private AccountOrderMapper accountOrderMapper;
	
	@Value("${show_path}")
	private String uploadPath;
	
	/**
	 * 查询音/视频列表分页
	 * @param query
	 * @return
	 */
	public List<ChampionVideo> queryPageData(ChampionVideoQuery query){
		List<ChampionVideo> videos = new ArrayList<ChampionVideo>();
		videos = championVideoMapper.queryPageData(query);
		if(null != videos && videos.size() > 0){
			for (ChampionVideo video : videos) {
				if(StringUtils.isNotBlank(video.getCoverImageUrl())){
					if(StringUtils.equals("/", StringUtils.substring(video.getCoverImageUrl(), 0, 1))){
						video.setCoverFileUrl(uploadPath + video.getCoverImageUrl()); 
					} else {
						video.setCoverFileUrl(uploadPath + "/" + video.getCoverImageUrl()); 
					}
				}
				if(StringUtils.isNotBlank(video.getVideoUrl()) && null != video.getVideoType() && video.getVideoType().intValue() == 2){
					if(StringUtils.equals("/", StringUtils.substring(video.getVideoUrl(), 0, 1))){
						video.setVideoFileUrl(uploadPath + video.getVideoUrl()); 
					} else {
						video.setVideoFileUrl(uploadPath + "/" + video.getVideoUrl()); 
					}
				}
				video.setSize();
			}
		}
		return videos;
	}
	
	/**
	 * 更新音/视频状态
	 * @param video
	 * @return
	 */
	@MethodLog(remark = "更新音/视频状态")
	public int updateVideoStatus(ChampionVideo video){
		
		if(null != video && null != video.getOnlineStatus()){
			// 上线视频
			if(video.getOnlineStatus().intValue() == 1){
				video.setOnlineTime(new Date());
			// 下线视频
			} else if(video.getOnlineStatus().intValue() == 0){
				video.setOfflineTime(new Date());
			}
		}
		int update = championVideoMapper.updateByPrimaryKeySelective(video);
		if(update == 1){
			
			ChampionUserData insertData = new ChampionUserData();
			ChampionVideo oldVideo = championVideoMapper.selectByPrimaryKey(video.getFid());
			ChampionUserData updateData = championUserDataMapper.selectByUserId(oldVideo.getLecturerId());
			
			// 如果不存在则新增
			if(updateData == null){
				insertData.setCreatAt(new Date());
				insertData.setUserId(oldVideo.getLecturerId());
				insertData.setPublishCount(1);
				insertData.setAttentionCount(0);
				insertData.setFansCount(0);
				insertData.setCollectionCount(0);
				championUserDataMapper.insertSelective(insertData);
			// 则更新
			} else {
				// 审核通过
				updateData.setUpdateAt(new Date());
				if(null != video.getAuditStatus() && video.getAuditStatus().intValue() == 2){
					updateData.setPublishCount(updateData.getPublishCount() + 1);
				}
				// 审核不通过
				if(null != video.getAuditStatus() && video.getAuditStatus().intValue() == 1){
					updateData.setPublishCount((updateData.getPublishCount() - 1) < 0 ? 0 : (updateData.getPublishCount() - 1));
				}
				// 删除审核通过的音/视频
				if(null != video.getStatus() && video.getStatus().intValue() == 0){
					if(null != oldVideo && null != oldVideo.getAuditStatus() && oldVideo.getAuditStatus().intValue() == 2){
						updateData.setPublishCount((updateData.getPublishCount() - 1) < 0 ? 0 : (updateData.getPublishCount() - 1));
					}
				}
				championUserDataMapper.updateByPrimaryKeySelective(updateData);
			}
			
		}
		return update;
	}
	
	/**
	 * 更新音/视频状态
	 * @param video
	 * @return
	 */
	@MethodLog(remark = "更新音/视频")
	public int updateVideo(ChampionVideo video, String tagIds){
		
		int update = championVideoMapper.updateByPrimaryKeySelective(video);
		String[] tagarr = StringUtils.split(tagIds, ",");
		if(update == 1 && null != tagarr){
			championVideoTagMapper.deleteByVideoId(video.getFid());
			if(tagarr.length > 0){
				for (String tagId : tagarr) {
					ChampionVideoTag championVideoTag = new ChampionVideoTag();
					championVideoTag.setTagId(Long.parseLong(tagId));
					championVideoTag.setVideoId(video.getFid());
					championVideoTag.setCreateAt(new Date());
					championVideoTagMapper.insertSelective(championVideoTag);
				}
			}
		}
		return update;
	}
	
	/**
	 * 新增音/视频
	 * @param video
	 * @return
	 */
	@MethodLog(remark = "新增音/视频")
	public int addVideo(ChampionVideo video, String tagIds){
		if(null != video){
			// 是否显示假播放次数
			if(null != video.getIsFalseCount() && video.getIsFalseCount()){
				video.setFalseCount(RandomUtil.genThreeRandomNum());
			}
			// 是否用券
			if(null != video.getVoucherCount() && video.getVoucherCount().intValue() > 0){
				video.setVoucherId(AdminConstants.VOUCHER_ID);
			}
		}
		int insert = championVideoMapper.insertSelective(video);
		String[] tagarr = StringUtils.split(tagIds, ",");
		// 如果有标签 则添加
		if(insert == 1 && null != tagarr && tagarr.length > 0){
			for (String tagId : tagarr) {
				ChampionVideoTag championVideoTag = new ChampionVideoTag();
				championVideoTag.setTagId(Long.parseLong(tagId));
				championVideoTag.setVideoId(video.getFid());
				championVideoTag.setCreateAt(new Date());
				championVideoTagMapper.insertSelective(championVideoTag);
			}
		}
		if(insert == 1 && StringUtils.isBlank(video.getPageUrl())){
			ChampionVideo updateVideo = new ChampionVideo();
			updateVideo.setFid(video.getFid());
			updateVideo.setPageUrl("champion/video/videoDetail.page?fid=" + video.getFid());
			int update = championVideoMapper.updateByPrimaryKeySelective(updateVideo);
			return update;
		}
		return insert;
	}
	
	/**
	 * 单一Video
	 * @param query
	 * @return
	 */
	public ChampionVideo queryVideo(ChampionVideoQuery query){
		ChampionVideo video = championVideoMapper.queryVideo(query);
		if(null != video){
			// 图片音频路径补充
			if(StringUtils.isNotBlank(video.getCoverImageUrl())){
				if(StringUtils.equals("/", StringUtils.substring(video.getCoverImageUrl(), 0, 1))){
					video.setCoverFileUrl(uploadPath + video.getCoverImageUrl()); 
				} else {
					video.setCoverFileUrl(uploadPath + "/" + video.getCoverImageUrl()); 
				}
			}
			if(StringUtils.isNotBlank(video.getVideoUrl()) && null != video.getVideoType() && video.getVideoType().intValue() == 2){
				if(StringUtils.equals("/", StringUtils.substring(video.getVideoUrl(), 0, 1))){
					video.setVideoFileUrl(uploadPath + video.getVideoUrl()); 
				} else {
					video.setVideoFileUrl(uploadPath + "/" + video.getVideoUrl()); 
				}
			}
			// 标签查询
			ChampionVideoTag record = new ChampionVideoTag();
			record.setVideoId(query.getFid());
			List<ChampionTag> tagList = championVideoTagMapper.queryTagByVideoId(record);
			video.setTagList(tagList);
		}
		return video;
	}
	
	/**
	 * 删除音/视频评论
	 * @param videoCommentDO
	 * @return
	 */
	@MethodLog(remark = "删除音/视频评论")
	public int deleteVideoComment(VideoCommentDO videoCommentDO){
		return championVideoMapper.deleteVideoComment(videoCommentDO);
	}
	
	/**
	 * 查询音/视频举报列表分页
	 * @param query
	 * @return
	 */
	public List<VideoReportDO> queryReportPageData(VideoReportQuery query){
		List<VideoReportDO> list = new ArrayList<VideoReportDO>();
		List<Long> userIds = new ArrayList<Long>();
		list = championVideoMapper.queryReportPageData(query);
		if(null != list && list.size() > 0 && null != query.getDimension() && query.getDimension().intValue() == 2){
			for (VideoReportDO videoReportDO : list) {
				if(null != videoReportDO.getUserId()){
					userIds.add(videoReportDO.getUserId());
				}
			}
		}
		if(null != userIds && userIds.size() > 0){
			List<UcUser> users = accountOrderMapper.queryUserByUserId(userIds);
			if(null != users && users.size() > 0){
				for (VideoReportDO videoReportDO : list) {
					for (UcUser ucUser : users) {
						if(ucUser.getId().longValue() == videoReportDO.getUserId().longValue()){
							videoReportDO.setPhone(ucUser.getUsername());
							videoReportDO.setName(ucUser.getNickname());
							break;
						}
					}
				}
			}
		}
		return list;
	}
	
	/**
	 * 查询音/视频举报列表数量
	 * @param query
	 * @return
	 */
	public int queryReportDataCount(VideoReportQuery query){
		return championVideoMapper.queryReportDataCount(query);
	}
	
	/**
	 * 查询所有符合信息的userId
	 * @param query
	 * @return
	 */
	public List<Long> queryUserIds(VideoReportQuery query) {
		RewardQuery rewQuery = new RewardQuery();
		rewQuery.setUserName(query.getPhone());
		rewQuery.setNickName(query.getName());
		return accountOrderMapper.queryUserIds(rewQuery);
	}

	/**
	 * 批量审核视频评论-审核不通过
	 * @param ids
	 * @return
     */
	public int deleteBat(String ids) {
		int count = 0;
		String [] idArr = ids.split(",");
		for(String id: idArr) {
			count += championVideoMapper.unAuthorVideoComment(Long.valueOf(id));
		}
		return count;
	}
}
