package com.hangjia.bxj.service.my;

import com.baobao.framework.support.utility.WebUtility;
import com.baobao.framework.support.utility.timer.ExecuteTask;
import com.baobao.sso.client.Constants;
import com.baobao.sso.client.UserCardReqDto;
import com.baobao.sso.client.UserCardRespDto;
import com.baobao.sso.service.UserCardSupportService;
import com.baobao.sso.service.UserService;
import com.hangjia.bxj.dao.*;
import com.hangjia.bxj.model.*;
import com.hangjia.bxj.service.ControlAppStoreService;
import com.hangjia.bxj.util.RedisKeyConstants;
import com.hangjia.bxj.vo.Pagination;
import com.hangjia.bxj.vo.PlanUserCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

;

/**
 * @ClassName: MyVideoVoucherServiceImpl
 * @Description: 我的收藏及视频操作实现
 * @author: he-Yi
 * @date: 2016年4月14日 下午12:12:01
 */
@Service
public class MyVideoVoucherServiceImpl implements MyVideoVoucherService {
	
	private static Logger log = LoggerFactory.getLogger(MyVideoVoucherServiceImpl.class);
	/**
	 * 我的收藏列表
	 */
	@Autowired
	private ChampionVideoMapper championVideoMapper;

	/**
	 * 收藏
	 */
	@Autowired
	private ChampionCollectionMapper championCollectionMapper;

	/**
	 * 视频券
	 */
	@Autowired
	private ChampionUserVoucherMapper championUserVoucherMapper;

	/**
	 * 邀请
	 */
	@Autowired
	private ChampionInviteMapper championInviteMapper;
	
	@Autowired
	private UserCardSupportService userCardSupportService;

	@Autowired
	private ChampionUserVocherLogMapper championUserVocherLogMapper;

    @Autowired
    private ControlAppStoreService controlAppStoreService;
	@Autowired
	private UserService userService;
	@Value("${bxj_path}")
	private String bxjPath;
	@Value("${qiniu_path}")
	private String qiniuPath;
	@Value("${static_path}")
	private String staticPath;
	@Value("${championsay_path}")
	private String championsayPath;



	/**
	 * 3.0版本判断，使用新版地址还是老版地址
	 * fid=1&page=1&pageSize=10&OS=iOS&versionCode=101&versionName=2.3.1
	 * versionName=2.4&versionCode=10&os=android
	 * @return
	 */
	private String getVideoPath(HttpServletRequest request) {
		String os = request.getParameter("os");
		if(os == null) {
			os = request.getParameter("OS");
		}
		if(os == null) os = "";

		String versionCode = request.getParameter("versionCode");
		if(versionCode == null) versionCode = "";



		String path = bxjPath;
		if("android".equals(os) && (Integer.valueOf(versionCode))>10) {
			path = championsayPath;
		} else if("iOS".equals(os) && (Integer.valueOf(versionCode))>102) {
			path = championsayPath;
		}
		return path;
	}
	@Override
	public Pagination<ChampionVideo> myCollectList(HttpServletRequest request, Long userId,Integer index, Integer pageSize) {
		String tempPath = getVideoPath(request);
		Map<String, Object> map = new HashMap<String, Object>();
		long start = (index - 1) * pageSize;
		map.put("userId", userId);
		map.put("start", start);
		map.put("end", pageSize);
//		map.put("orderby", "t1.create_at");
		//我的收藏列表
		List<ChampionVideo> listChampVideos=championVideoMapper.myCollectList(map);
		List<ChampionVideo> list = new ArrayList<ChampionVideo>();
		for(ChampionVideo obj : listChampVideos) {
			list.add(obj.getChampionVideo(tempPath, qiniuPath, staticPath));
		}
		//收藏总条数
		Integer total=championVideoMapper.myCollectCount(map);
		return new Pagination<ChampionVideo>(total, list);
	}

	@Transactional(rollbackFor = Exception.class)
	public int storeOrCancel(Long userId, Long videoId,Long type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("videoId", videoId);
		int result = 0;
		if (championCollectionMapper.selectCountBy(map) > 0) {
			// 取消收藏
			map.put("cancelAt", new Date());
			map.put("isCollection", type);
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

	@Transactional(rollbackFor = Exception.class)
	public int cancelVideo(String ids,Long userId) {
		Map<String,Object> map=new HashMap<String, Object>(); 
		if (!StringUtils.isEmpty(ids)) {
			List<Long> list = new ArrayList<Long>();
			for (String id : ids.split(",")) {
				list.add(Long.parseLong(id));
			}
			 map.put("userId", userId);
			 map.put("list", list);
			 return championCollectionMapper.cancelVedioList(map);
		}
		return 0;
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
		userVoucher.setTotal(0);
		userVoucher.setGetAllcounts(0);
		userVoucher.setUseAllcounts(0);
		return userVoucher;
	}

	@Override
	public Map<String, Integer> selectCountInvite(Long userId) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		ChampionInvite championInv = new ChampionInvite();
		championInv.setInviteUser(userId);
		/** 邀请次数 **/
		int inviteCount = championInviteMapper.selectCountInvite(championInv);
		ChampionUserVocherLog championUserVocherLog = new ChampionUserVocherLog();
		championUserVocherLog.setUserId(userId);
		championUserVocherLog.setType(com.hangjia.bxj.util.Constants.VOUCHER_CONSUME_TYPE_INVITE_ADD);
		/** 邀请获得券数 **/
		int inviteVourcherCount = championUserVocherLogMapper.geInviteVourcherCoumt(championUserVocherLog);
		if(inviteCount > inviteVourcherCount) inviteVourcherCount = inviteCount;
		map.put("inviteCount", inviteCount);
		map.put("inviteVourcherCount", inviteVourcherCount);
		return map;
	}

	@Override
	public Pagination<ChampionVideo> myStoreList(HttpServletRequest request, Long userId, Integer index, Integer pageSize) {
		String tempPath = getVideoPath(request);
		Map<String, Object> map = new HashMap<String, Object>();
		long start = (index - 1) * pageSize;
		map.put("userId", userId);
		map.put("start", start);
		map.put("end", pageSize);	
		map.put("orderby", "vd.create_at");
		//我的视频
		List<ChampionVideo> listChampVideos=championVideoMapper.myStoreList(map);
		//我的视频总条数 
		Integer total=championVideoMapper.myStoreCount(map);
		List<ChampionVideo> list = new ArrayList<ChampionVideo>();
		for(ChampionVideo obj : listChampVideos) {
			list.add(obj.getChampionVideo(tempPath, qiniuPath, staticPath));
		}
		return new Pagination<ChampionVideo>(total, list);
	}
	
	@Override
	public PlanUserCard queryUserCardById(Integer userId) {
//		PlanUserCard userCard = userCardMapper.selectByPKey(userId);
		UserCardRespDto userCardResp = userCardSupportService.getUserCardByFid(userId);
		try {
			PlanUserCard userCard = new PlanUserCard();
			BeanUtils.copyProperties(userCardResp, userCard);
			userCard.setShareUrl(bxjPath + com.hangjia.bxj.util.Constants.USER_CARD_SHARE_PATH_PREFIX +
					userCard.getFid() + com.hangjia.bxj.util.Constants.USER_CARD_SHARE_PATH_SUFFIX);
			return userCard;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public UserCardRespDto getUserBaseInfoByUserId(Integer userId) {
		return this.userCardSupportService.getUserBaseInfoByUserId(userId);
	}
	
	//修改用户昵称 
	@Override
	public int updateNickName(Long userId,String nickName,String qrcode, String phone) {
		UserCardReqDto userCardReq = new UserCardReqDto();
		userCardReq.setFid(userId.intValue());
		if(nickName!=null)userCardReq.setName(nickName); //昵称 
		if(qrcode!=null)userCardReq.setQrcode(qrcode); //二维码
		try {
			if (null == userCardSupportService.getUserCardByFid(userId.intValue())) {
				userCardReq.setPhone(phone);
				userCardReq.setSex(Integer.valueOf(controlAppStoreService.getResponseParaByKey(RedisKeyConstants.KEY_DEFAULT_SEX)));
				userCardReq.setModel(Integer.valueOf(controlAppStoreService.getResponseParaByKey(RedisKeyConstants.KEY_DEFAULT_MODEL)));
				userCardReq.setCompanyCode(controlAppStoreService.getResponseParaByKey(RedisKeyConstants.KEY_DEFAULT_COMPANY));
				userCardReq.setJobCode(controlAppStoreService.getResponseParaByKey(RedisKeyConstants.KEY_DEFAULT_POSITION));
				userCardReq.setJob(controlAppStoreService.getResponseParaByKey(RedisKeyConstants.KEY_DEFAULT_POSITION_NAME));
				userCardReq.setCompany(controlAppStoreService.getResponseParaByKey(RedisKeyConstants.KEY_DEFAULT_COMPANY_NAME));
				userCardReq.setCities(controlAppStoreService.getResponseParaByKey(RedisKeyConstants.KEY_DEFAULT_CITIES_NAME));
			}
			UserCardRespDto UserCardRespDto = userCardSupportService.saveUserCardBySelective(userCardReq);
			if(UserCardRespDto==null){
				log.error("修改昵称 ,调用修改返回UserCardRespDto对象为空！二维码："+qrcode+"{}昵称："+nickName);
				return 0;
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.error("修改昵称异常",e.getMessage());
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
	
	//修改用户头像 
	@Override
 	public int updatePhoto(Long userId,byte[] imgBytes, String phone) {
		UserCardReqDto userCardReq = new UserCardReqDto();
		userCardReq.setFid(userId.intValue());
		userCardReq.setUserPhotoByte(imgBytes);
		try {
			if (null == userCardSupportService.getUserCardByFid(userId.intValue())){
				userCardReq.setPhone(phone);
				userCardReq.setSex(Integer.valueOf(controlAppStoreService.getResponseParaByKey(RedisKeyConstants.KEY_DEFAULT_SEX)));
				userCardReq.setModel(Integer.valueOf(controlAppStoreService.getResponseParaByKey(RedisKeyConstants.KEY_DEFAULT_MODEL)));
				userCardReq.setCompanyCode(controlAppStoreService.getResponseParaByKey(RedisKeyConstants.KEY_DEFAULT_COMPANY));
				userCardReq.setJobCode(controlAppStoreService.getResponseParaByKey(RedisKeyConstants.KEY_DEFAULT_POSITION));
				userCardReq.setJob(controlAppStoreService.getResponseParaByKey(RedisKeyConstants.KEY_DEFAULT_POSITION_NAME));
				userCardReq.setCompany(controlAppStoreService.getResponseParaByKey(RedisKeyConstants.KEY_DEFAULT_COMPANY_NAME));
				userCardReq.setCities(controlAppStoreService.getResponseParaByKey(RedisKeyConstants.KEY_DEFAULT_CITIES_NAME));
			}
			UserCardRespDto UserCardRespDto = userCardSupportService.saveUserCardBySelective(userCardReq);
			if(UserCardRespDto==null){
				log.error("修改头像 ,调用修改返回UserCardRespDto对象为空！");
				return 0;
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.error("修改头像异常",e.getMessage());
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	@Override
	public int logout(HttpServletRequest request, HttpServletResponse response) {
		try {
			Map cookie = WebUtility.getCookies(request);
			Object tgc = cookie.get("tgc");
			if (tgc != null && org.apache.commons.lang3.StringUtils.isNotEmpty(tgc.toString())) {
				userService.logout(tgc.toString());
				Map cookieMap = new HashMap<String, Object>() {
					{
						put("bbssoSessionId", "");
						put("usys", "");
						put("uid", "");
						put("tgc", "");
						put("uname", "");
						put(Constants.CAS_AUTOCODE_KEY, "");
						put("timeout", 0);
					}
				};
				WebUtility.setCookies(response, cookieMap);
			}
		} catch (Exception ex) {
			log.error("退出异常：{}", ex.getMessage());
			ex.printStackTrace();
			return 0;
		}
		return 1;
	}


}
