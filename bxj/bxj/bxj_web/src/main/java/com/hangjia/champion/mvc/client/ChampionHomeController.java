package com.hangjia.champion.mvc.client;

import javax.servlet.http.HttpServletRequest;

import com.baobao.framework.page.Paginator;
import com.hangjia.champion.constant.SearchConstant;
import com.hangjia.champion.service.ChampionSupportService;
import com.hangjia.champion.vo.ChampionVideoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hangjia.bxj.mvc.AjaxResult;
import com.hangjia.champion.service.ChampionBannerService;
import com.hangjia.champion.service.ChampionChannelService;
import com.hangjia.champion.service.ChampionVideoService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/12.
 */
@Controller
@RequestMapping("champion/home")
public class ChampionHomeController {
	private Logger log = LoggerFactory.getLogger(ChampionHomeController.class);
	@Autowired
	private ChampionBannerService bannerService;
	@Autowired
	private ChampionChannelService championChannelService;
	@Autowired
	private ChampionVideoService championVideoService;
	@Autowired
	private ChampionSupportService championSupportService;
	@Value("${bxj_path}")
	private String bxjPath;

	/**
	 * @Title: getHeadChampionChannel
	 * @Description: 获取频道首页标题
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "headChannel.json", method = RequestMethod.GET)
	@ResponseBody
	public Object headChannel(HttpServletRequest request) {
		log.debug("获取频道首页标题");
		return new AjaxResult.success(championChannelService.queryHeadChampionChannel());
	}

	/**
	 * @Title: getAllChampionVideoForPage
	 * @Description: 根据全部频道分类分页获取所有视频,排序规则 1.是否推荐 2.按上架时间
	 * @param channelId
	 * @param currPage
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "allVideo.json", method = RequestMethod.GET)
	@ResponseBody
	public Object allVideoForPage(Long channelId,
			@RequestParam(defaultValue = "1") Integer currPage, @RequestParam(defaultValue = "10") Integer pageSize) {
		log.debug("根据全部频道分类分页获取所有视频,排序规则 1.是否推荐 2.按上架时间,频道ID={}", channelId);
		if (channelId == null) {
			return new AjaxResult.error("频道ID不能为空！");
		}
		return new AjaxResult.success(championVideoService.queryAllChampionVideo(channelId, currPage, pageSize));
	}

	/**
	 * @Title: otherVideoForPage
	 * @Description: 根据子频道分类分页获取所有视频，排序规则 1.播放次数
	 * @param request
	 * @param channelId
	 * @param currPage
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "otherVideo.json", method = RequestMethod.GET)
	@ResponseBody
	public Object otherVideoForPage(HttpServletRequest request, Long channelId,
			@RequestParam(defaultValue = "1") Integer currPage, @RequestParam(defaultValue = "20") Integer pageSize) {
		log.debug("根据子频道分类分页获取所有视频,排序规则 1.播放次数,频道ID={}", channelId);
		if (channelId == null) {
			return new AjaxResult.error("频道ID不能为空！");
		}
		return new AjaxResult.success(championVideoService.queryOtherChampionVideo(channelId, currPage, pageSize));
	}

	/**
	 * 精选频道
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/jx.json", method = RequestMethod.GET)
	public Object jx(@RequestParam(defaultValue = "1") Integer currPage, @RequestParam(defaultValue = "3") Integer pageSize) {
		return new AjaxResult.success(championVideoService.getVideosJx(currPage, pageSize));
	}

	/**
	 * 模块-更多
	 * 
	 * @param moduleId
	 * @param index
	 * @param size
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/moreVideo.json", method = RequestMethod.GET)
	public Object moreVideo(Long moduleId, @RequestParam(defaultValue = "1") Integer index,
			@RequestParam(defaultValue = "10") Integer size) {
		return new AjaxResult.success(championVideoService.getVideosByModuleId(moduleId, index, size));
	}

	/**
	 * 频道banner列表
	 * 
	 * @param channelId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/banner.json", method = RequestMethod.GET)
	public Object banner(Long channelId, @RequestParam(defaultValue = "iOS") String os, HttpServletRequest request) {
		try {
			String versionCode = request.getParameter("versionCode");
			if ("android".equalsIgnoreCase(os) && versionCode != null && versionCode.equalsIgnoreCase("10")) {
				channelId = 0L;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new AjaxResult.success(bannerService.getBannsersByChannel(channelId));
	}

	@ResponseBody
	@RequestMapping(value = "/search.json", method = RequestMethod.GET)
	public Object search(String key, @RequestParam(defaultValue = "1") Integer currPage, @RequestParam(defaultValue = "10") Integer pageSize) {
		/*try {
			key = new String(key.getBytes("ISO-8859-1"), "UTF-8");
		} catch (Exception e){
			log.info("转码出错");
			return new AjaxResult.error("转码出错");
		}*/
		log.info("search key:" + key);
		Paginator paginator = new Paginator();
		paginator.setItemsPerPage(pageSize);
		paginator.setPage(currPage);
		/*Map<String ,String> param = new HashMap<String, String>();
		param.put("championUrl", bxjPath);
		paginator.setParams(param);*/
		ChampionVideoVo championVideoVo = new ChampionVideoVo();
		/*paginator.setSortField(SearchConstant.PLAY_COUNT);
		paginator.setSortType(SearchConstant.DESC);*/
		Map<String, Object> result = championSupportService.searchVideoList(paginator, championVideoVo, key);
		return new AjaxResult.success(result);
	}
}
