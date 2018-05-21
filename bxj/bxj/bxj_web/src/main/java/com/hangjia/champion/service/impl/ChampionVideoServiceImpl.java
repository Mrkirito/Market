package com.hangjia.champion.service.impl;

import com.hangjia.bxj.dao.*;
import com.hangjia.bxj.model.*;
import com.hangjia.bxj.service.RegistService;
import com.hangjia.bxj.util.OrderConstants;
import com.hangjia.bxj.util.Constants;
import com.hangjia.bxj.vo.ChampionModuleVideo;
import com.hangjia.bxj.vo.*;
import com.hangjia.champion.service.ChampionVideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @ClassName: ChampionVideoServiceImpl
 * @Description: 冠军说视频业务
 * @author: bei.zhang
 * @date: 2016年4月12日 上午11:49:22
 */
@Service
public class ChampionVideoServiceImpl implements ChampionVideoService {
	private Logger logger = LoggerFactory.getLogger(ChampionVideoServiceImpl.class);

	@Autowired
	private ChampionVideoMapper championVideoMapper;
	@Autowired
	private ChampionPlayVideoLogMapper championPlayVideoLogMapper;
	@Autowired
	private ChampionUserVoucherMapper championUserVoucherMapper;
	@Autowired
	private ChampionUserVocherLogMapper championUserVocherLogMapper;
	@Autowired
	private ChampionModuleMapper championModuleMapper;
	@Autowired
	private ChampionCollectionMapper championCollectionMapper;
	@Autowired
	private ChampionVoucherMapper championVoucherMapper;
	@Autowired
	private RegistService registService;

	@Value("${bxj_path}")
	private String bxjPath;
	@Value("${qiniu_path}")
	private String qiniuPath;
	@Value("${static_path}")
	private String staticPath;

	@Override
	public Pagination<ChampionVideo> queryAllChampionVideo(Long channelId, Integer currPage, Integer pageSize) {
		ChampionVideo championVideo = new ChampionVideo();
		if (!Constants.CHAMPION_CHANNEL_ID_ALL.equals(channelId)) {
			championVideo.setChannelId(channelId);
		}
		List<OrderData> orderDatas = new ArrayList<OrderData>();
		//orderDatas.add(new OrderData(OrderConstants.CHAMPION_VIDEO_IS_RECOMMEND, OrderMethod.DESC));
		orderDatas.add(new OrderData(OrderConstants.ONLINE_TIME, OrderMethod.DESC));
		championVideo.setOrderDatas(orderDatas);
		championVideo.setCurrPage(currPage);
		championVideo.setPageSize(pageSize);
		championVideo.setAuditStatus(Constants.VIDEO_AUDIT_STATUS_PASS);
		int total = championVideoMapper.selectBySelectiveCount(championVideo);
		List<ChampionVideo> championVideos = championVideoMapper.selectBySelective(championVideo);
		List<ChampionVideo> list = new ArrayList<ChampionVideo>();
		for(ChampionVideo obj : championVideos) {
			list.add(obj.getChampionVideo(bxjPath, qiniuPath, staticPath));
		}
		return new Pagination<ChampionVideo>(total, list);
	}

	@Override
	public Pagination<ChampionVideo> queryOtherChampionVideo(Long channelId, Integer currPage, Integer pageSize) {
		ChampionVideo championVideo = new ChampionVideo();
		championVideo.setChannelId(channelId);
		List<OrderData> orderDatas = new ArrayList<OrderData>();
		//orderDatas.add(new OrderData(OrderConstants.CHAMPION_VIDEO_PLAY_COUNT, OrderMethod.DESC));
		orderDatas.add(new OrderData(OrderConstants.ONLINE_TIME, OrderMethod.DESC));
		championVideo.setOrderDatas(orderDatas);
		championVideo.setCurrPage(currPage);
		championVideo.setPageSize(pageSize);
		championVideo.setAuditStatus(Constants.VIDEO_AUDIT_STATUS_PASS);
		int total = championVideoMapper.selectBySelectiveCount(championVideo);
		List<ChampionVideo> championVideos = championVideoMapper.selectBySelective(championVideo);
		List<ChampionVideo> list = new ArrayList<ChampionVideo>();
		for(ChampionVideo obj : championVideos) {
			list.add(obj.getChampionVideo(bxjPath, qiniuPath, staticPath));
		}
		return new Pagination<ChampionVideo>(total, list);
	}

	@Override
	public ChampionVideo queryChampionVideoByFid(Long fid) {
		ChampionVideo championVideo = new ChampionVideo();
		championVideo.setFid(fid);
		championVideo.setAuditStatus(Constants.VIDEO_AUDIT_STATUS_PASS);
		List<ChampionVideo> championVideos = championVideoMapper.selectBySelective(championVideo);
		if (championVideos != null && !championVideos.isEmpty()) {
			ChampionVideo video = championVideos.get(0);
			video = video.getChampionVideo(bxjPath, qiniuPath, staticPath);
			if(video.getIsFalseCount()) video.setPlayCount(video.getFalseCount());
			return video;
		}
		return null;
	}

	@Override
	@Transactional
	public void playChampionVideo(Long fid, Long userId) {
		ChampionVideo championVideo = championVideoMapper.selectByPrimaryKey(fid);
		championVideo.setPlayCount(championVideo.getPlayCount() + 1);
		championVideo.setFalseCount(championVideo.getFalseCount() + 1);
		championVideoMapper.updateByPrimaryKeySelective(championVideo);
		ChampionPlayVideoLog championPlayVideoLog = new ChampionPlayVideoLog();
		championPlayVideoLog.setUserId(userId);
		championPlayVideoLog.setVideoId(fid);
		championPlayVideoLog.setPlayTime(new Date());
		championPlayVideoLogMapper.insert(championPlayVideoLog);
	}
	
	 
	@Override
	@Transactional
	public void updateVideoBrowserNum(Long fid) {
		ChampionVideo championVideo = championVideoMapper.selectByPrimaryKey(fid);
		championVideo.setBrowseNum(championVideo.getBrowseNum() + 1); //更新浏览次数 
		championVideo.setFalseCount(championVideo.getFalseCount() + 1);
		championVideoMapper.updateByPrimaryKeySelective(championVideo);
	}

	/**
	 * 视频分享：1.更新分享次数；2.送券
	 * @param fid
     */
	@Override
	public void shareChampionVideo(Integer userId, Long fid) {
		ChampionVideo championVideo = championVideoMapper.selectByPrimaryKey(fid);
		championVideo.setShareCount(championVideo.getShareCount() + 1);
		championVideoMapper.updateByPrimaryKeySelective(championVideo);
		if(null != userId)
			registService.getRegistVoucher(Long.parseLong(String.valueOf(userId)), 5);
	}

	/**
	 * 精选页面
	 * 
	 * @return
	 */
	public Pagination<ChampionModuleVideo> getVideosJx(Integer currPage, Integer pageSize) {
		Map<String, Object> query = new HashMap<String, Object>();
		int start = (currPage-1)*pageSize;
		query.put("start", start);
		query.put("end", pageSize);
		List<ChampionModule> moduleList = championModuleMapper.getListPage(query);
		int count = championModuleMapper.getAllModuleCount();
		List<ChampionModuleVideo> list = new ArrayList<ChampionModuleVideo>();
		for (ChampionModule championModule : moduleList) {
			Map<String, Object> map = new HashMap<String, Object>();
			ChampionModule championModule1 = championModuleMapper.selectByPrimaryKey(championModule.getFid());
			map.put("moduleId", championModule.getFid());
			map.put("start", Constants.BEGIN);
			map.put("end", Constants.SUBPAGESIZE);
			List<ChampionVideo> listC = championVideoMapper.getVideosByModuleId(map);
			List<ChampionVideo> l = new ArrayList<ChampionVideo>();
			for(ChampionVideo obj : listC) {
				l.add(obj.getChampionVideo(bxjPath, qiniuPath, staticPath));
			}
			ChampionModuleVideo c = new ChampionModuleVideo(championModule1, l);
			list.add(c);
		}
		return new Pagination<ChampionModuleVideo>(count, list);
	}

	/**
	 * 根据模块id查询视频
	 * 
	 * @param moduleId
	 * @param index
	 * @param size
	 * @return
	 */
	public List<ChampionVideo> getVideosByModuleId(Long moduleId, Integer index, Integer size) {
		Map<String, Object> map = new HashMap<String, Object>();
		long start = (index - 1) * size;
		map.put("moduleId", moduleId);
		map.put("start", start);
		map.put("end", size);
		List<ChampionVideo> list = championVideoMapper.getVideosByModuleId(map);
		List<ChampionVideo> l = new ArrayList<ChampionVideo>();
		for(ChampionVideo obj : list) {
			l.add(obj.getChampionVideo(bxjPath, qiniuPath, staticPath));
		}
		return l;
	}

	@Override
	public ChampionUserVoucher queryUserVoucherTotal(Long userId, Long voucherId) {
		ChampionUserVoucher userVoucher = new ChampionUserVoucher();
		userVoucher.setUserId(userId);
		userVoucher.setVoucherId(voucherId);
		List<ChampionUserVoucher> userVouchers = championUserVoucherMapper.selectBySelective(userVoucher);
		if (userVouchers != null && !userVouchers.isEmpty()) {
			return userVouchers.get(0);
		}
		return null;
	}

	/**
	 * 使用券观看视频：券数-1；添加用券Log
	 * @param userId
	 * @param videoId
	 * @return
	 */
	@Override
	@Transactional
	public boolean useVideoVoucher(Long userId, Long videoId) {
		try {
			/** 视频详细 **/
			ChampionVideo video = championVideoMapper.selectByPrimaryKey(videoId);
			/** 用户券数 **/
			ChampionUserVoucher userVoucher = this.queryUserVoucherTotal(userId, video.getVoucherId());
			if (userVoucher == null) {
				logger.info("用户没有可用券!");
				return false;
			}
			if (video.getVoucherCount().compareTo(userVoucher.getTotal()) == 1) {
				logger.info("用户的视频券不足，无法消费!");
				return false;
			}
			/** 视频是否已用券观看过 **/
			ChampionUserVocherLog userVoucherLog = new ChampionUserVocherLog();
			userVoucherLog.setUserId(userId);
			userVoucherLog.setVocherId(video.getVoucherId());
			userVoucherLog.setVideoId(videoId);
			List<ChampionUserVocherLog> userVoucherLogs = championUserVocherLogMapper.selectBySelective(userVoucherLog);
			if (userVoucherLogs != null && !userVoucherLogs.isEmpty()) {
				logger.info("已使用过视频券,可直接观看!");
				return false;
			}
			/** 消费使用券 **/
			userVoucher.setUseAllcounts(userVoucher.getUseAllcounts() + video.getVoucherCount());
			userVoucher.setTotal(userVoucher.getTotal() - video.getVoucherCount());
			userVoucher.setUpdateTime(new Date());
			if(championUserVoucherMapper.updateByPrimaryKeySelective(userVoucher) < 1) return false;
			/** 插入消费券日志 **/
			userVoucherLog.setType(Constants.VOUCHER_CONSUME_TYPE_USE);
			userVoucherLog.setCount(video.getVoucherCount());
			userVoucherLog.setCreateAt(new Date());
			if(championUserVocherLogMapper.insert(userVoucherLog) < 1) return false;
			return true;
		}catch (Exception e) {
			logger.info("useVideoVoucher处理错误！");
			e.printStackTrace();
		}
		return false;
	}

	@Transactional(rollbackFor = Exception.class)
	public int storeOrCancle(Long userId, Long videoId, Integer type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("videoId", videoId);
		int result = 0;
		if (championCollectionMapper.selectCountBy(map) > 0) {
			map.put("isCollection", type);
			map.put("cancelAt", new Date());
			//更新收藏次数
			ChampionVideo championVideo = championVideoMapper.selectByPrimaryKey(videoId);
			int count = type==1?1:-1;
			championVideo.setCollectionCount(championVideo.getCollectionCount() + count);
			championVideoMapper.updateByPrimaryKey(championVideo);
			result = championCollectionMapper.updateCollectionBy(map);
		} else {
			// 添加收藏
			ChampionCollection championCollection = new ChampionCollection();
			championCollection.setUserId(userId);
			championCollection.setVideoId(videoId);
			championCollection.setCreateAt(new Date());
			championCollection.setIsCollection(true);
			result = championCollectionMapper.insert(championCollection);
		}
		return result;
	}

	/**
	 * 用户权限信息
	 * 
	 * @param userId
	 * @return
	 */
	public VideoAuthorityVo getVideoAuthorityVo(Integer userId, ChampionVideo video) {
		VideoAuthorityVo vo = new VideoAuthorityVo();
		vo.setUserId(userId);
		int status = Constants.VIDEO_AUTHORITY_NOLOGIN;
		if (null != userId) {
			ChampionUserVocherLog record = new ChampionUserVocherLog(userId.longValue(), video.getVoucherId(),
					video.getFid());
			// 视频是否已观看过
			if (championUserVocherLogMapper.getWatchedCoumt(record) > 0
					|| video.getVoucherCount().compareTo(Integer.valueOf(0)) == 0)
				status = Constants.VIDEO_AUTHORITY_WATCHED;
			else {
				ChampionUserVoucher userVoucher = queryUserVoucherTotal(userId.longValue(),
						Constants.CHAMPION_VOUCHER_ID_VIDEO);
				int total = null == userVoucher ? 0 : userVoucher.getTotal();
				status = total >= video.getVoucherCount() ? Constants.VIDEO_AUTHORITY_ENOUGH
						: Constants.VIDEO_AUTHORITY_NOENOUGH;
				vo.setHasVouchers(total);
			}
		}
		if (Constants.VIDEO_AUTHORITY_NOENOUGH == status) {
			ChampionVoucher championVoucher = championVoucherMapper
					.selectByPrimaryKey(Constants.CHAMPION_VOUCHER_ID_VIDEO);
			vo.setGiveVouchers(championVoucher.getInviteCount());
		}
		vo.setStatus(status);
		return vo;
	}

	@Override
	public boolean isStore(Integer userId, Long videoId) {
		if (userId == null) {
			return false;
		}
		ChampionCollection collection = new ChampionCollection();
		collection.setUserId(userId.longValue());
		collection.setVideoId(videoId);
		collection.setIsCollection(true);
		List<ChampionCollection> collections = championCollectionMapper.selectBySelective(collection);
		if (collections == null || collections.isEmpty()) {
			return false;
		}
		return true;
	}
}
