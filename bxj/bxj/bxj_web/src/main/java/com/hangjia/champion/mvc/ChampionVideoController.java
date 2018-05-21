package com.hangjia.champion.mvc;

import javax.servlet.http.HttpServletRequest;

import com.baobao.framework.support.utility.Configuration;
import com.baobao.sso.service.UserService;
import com.hangjia.champion.service.ChampionInviteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baobao.sso.client.WebUtils;
import com.baobao.sso.filter.AuthenType;
import com.baobao.sso.filter.Login;
import com.hangjia.bxj.model.ChampionVideo;
import com.hangjia.bxj.mvc.AjaxResult;
import com.hangjia.bxj.util.Constants;
import com.hangjia.champion.service.ChampionVideoService;

import java.util.Map;

/**
 * @ClassName: ChampionVideoController
 * @Description: 冠军说视频管理
 * @author: bei.zhang
 * @date: 2016年4月12日 下午3:46:13
 */
@Controller
@RequestMapping("champion/video")
public class ChampionVideoController {

	private static Logger log = LoggerFactory.getLogger(ChampionVideoController.class);
	@Autowired
	private ChampionVideoService championVideoService;
	@Autowired
	private UserService userService;
	@Autowired
	private ChampionInviteService championInviteService;

	/**
	 * @Title: getChampionVideoByFid
	 * @Description: 获取视频明细
	 * @param request
	 * @param fid
	 * @return
	 */
	@RequestMapping(value = "videoDetail.page")
	public String videoDetail(HttpServletRequest request, Long fid) {
		log.debug("获取视频明细，视频主键={}", fid);
		Integer userId = null;
		try {
			userId = WebUtils.getMemberId(request);
		} catch (Exception e) {
			log.info("用户没有登录");
		}
		videoDetail(request, fid, userId);
		return "championsay/video_details";
	}

	public void videoDetail(HttpServletRequest request, Long fid, Integer userId) {
		ChampionVideo video = championVideoService.queryChampionVideoByFid(fid);
		request.setAttribute("authority",championVideoService.getVideoAuthorityVo(userId, video));
		request.setAttribute("video", video);
		if(null != userId) request.setAttribute("isCollection", championVideoService.isStore(userId, fid));
	}

	/**
	 * @Title: shareVideo
	 * @Description: 分享视频
	 * @param fid
	 */
	@RequestMapping(value = "shareVideo.do")
	@ResponseBody
	public void shareVideo(HttpServletRequest request, Long fid) {
		Integer userId = null;
		try {
			userId = WebUtils.getMemberId(request);
		} catch (Exception e) {
			log.info("用户没有登录");
		}
		championVideoService.shareChampionVideo(userId, fid);
	}

	/**
	 * 观看视频、登录、添加或取消收藏、使用券处理
	 * @param request
	 * @param videoId
	 * @param key
	 * @param type
	 * @param flag
	 * @return
	 */
	@RequestMapping(value="do_{key}.do")
	@Login(AuthenType.json)
	public Object common(HttpServletRequest request, Long videoId, @PathVariable String key, Integer type, String flag) {
		Integer userId = WebUtils.getMemberId(request);
		boolean isAutoLogin = null!=flag&& flag.contains("videoId");
		if(null==flag || isAutoLogin) {
			if(isAutoLogin) {
				videoId = Long.parseLong(flag.split("videoId_")[1].split("_type")[0]);
				type = Integer.parseInt(flag.split("type_")[1]);
			}
			/** 观看视频 **/
			if(key.equals(Constants.PLAY_VIDEO))
				championVideoService.playChampionVideo(videoId, userId.longValue());
			/** 登录 **/
			else if(key.equals(Constants.DO_LOGIN))
				;
			/** 添加或取消收藏 **/
			else if(key.equals(Constants.STORE_OR_CANCEL))
				championVideoService.storeOrCancle(userId.longValue(), videoId, type);
			/** 使用券 **/
			else if(key.equals(Constants.USE_VIDEO_VOUCHER))
				championVideoService.useVideoVoucher(userId.longValue(), videoId);
			if(isAutoLogin) return "redirect:videoDetail.page?fid="+videoId;
			else return "redirect:success.do";
		}
		else return "redirect:videoDetail.page?fid="+flag;
	}

	@RequestMapping(value = "success.do")
	@ResponseBody
	@Login(AuthenType.json)
	public Object success() {
		return new AjaxResult.success();
	}

	/**
	 * 跳转到注册页面
	 * @param request
	 * @param inviteUserId
     * @return
     */
	@RequestMapping(value = "toRegist.page")
	public Object toRegist(HttpServletRequest request, Integer inviteUserId) {
		request.setAttribute("inviteUserId", inviteUserId);
		return "championsay/regist";
	}

	/**
	 * 跳转到666活动注册页面
	 * @param request
	 * @param inviteUserId
	 * @return
	 */
	@RequestMapping(value = "toCashInviteRegist.page")
	public Object toCashInviteRegist(HttpServletRequest request, Integer inviteUserId) {
		request.setAttribute("inviteUserId", inviteUserId);
		return "championsay/cash_invite_regist";
	}

	/**
	 * 跳转到红包活动注册页面
	 * @param request
	 * @param inviteUserId
	 * @return
	 */
	@RequestMapping(value = "toRedInviteRegist.page")
	public Object toRedInviteRegist(HttpServletRequest request, Integer inviteUserId) {
		request.setAttribute("inviteUserId", inviteUserId);
		return "championsay/red_invite_regist";
	}

	/**
	 * 获取验证码
	 * @param phone
	 * @return
     */
    @RequestMapping(value = "getValidateCode.do")
    @ResponseBody
    public Object getValidateCode(String phone) {
		String sysid = Configuration.getProperty("system", "sysid");
		Map<String ,Object> map = userService.sendNewUserValidateCode(phone, sysid);
        return new AjaxResult.success(map);
    }

	/**
	 * 跳转到投票活动注册页面
	 * @param request
	 * @param inviteUserId
	 * @return
	 */
	@RequestMapping(value = "toVoteInviteRegist.page")
	public Object toVoteInviteRegist(HttpServletRequest request, Integer inviteUserId) {
		request.setAttribute("inviteUserId", inviteUserId);
		return "championsay/vote_invite_regist";
	}
	
	/**
	 * 注册
	 * @param phone
	 * @param code
     * @return
     */
	@RequestMapping(value = "regist.do")
	@ResponseBody
	public Object regist(String phone, String code, Integer inviteUserId) {
		Map<String, Object> map = userService.newUserRegist(phone, code);
		if(!Constants.REGIST_SUCCESS.equals(String.valueOf(map.get("code")))) {
			return new AjaxResult.success(map);
		} else {
			return new AjaxResult.success(championInviteService.inviteUserSuccess(map, inviteUserId.longValue(), Constants.CHAMPION_VOUCHER_ID_VIDEO));
		}
	}

	/**
	 * 分享跳转
	 *
	 * @param request
	 * @param title
	 * @param lecturerName
	 * @param coverImageUrl
     * @return
     */
	@RequestMapping(value = "toShare.page")
	public Object toShare(HttpServletRequest request, String title, String lecturerName, String coverImageUrl) {
		request.setAttribute("title", title);
		request.setAttribute("lecturerName", lecturerName);
		request.setAttribute("coverImageUrl", coverImageUrl);
		return "championsay/share_page";
	}
}
