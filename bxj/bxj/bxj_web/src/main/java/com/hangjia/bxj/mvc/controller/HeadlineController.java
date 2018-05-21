package com.hangjia.bxj.mvc.controller;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baobao.sso.client.UserCardRespDto;
import com.baobao.sso.client.WebUtils;
import com.baobao.sso.filter.AuthenType;
import com.baobao.sso.filter.Login;
import com.baobao.sso.service.UserCardSupportService;
import com.hangjia.bxj.dao.HeadlineNewsMapper;
import com.hangjia.bxj.dao.HeadlineShareMapper;
import com.hangjia.bxj.model.Headline;
import com.hangjia.bxj.model.HeadlineBanner;
import com.hangjia.bxj.model.HeadlineNews;
import com.hangjia.bxj.model.HeadlineShare;
import com.hangjia.bxj.service.headline.HeadlineService;

/**
 * 头条
 * @author yaoy
 *
 */
@Controller
@RequestMapping(value="/headline")
public class HeadlineController {	

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private HeadlineService headlineService;
	
	@Autowired
	private HeadlineNewsMapper headlineNewsMapper;
	
	@Autowired
	private HeadlineShareMapper headlineShareMapper;
	
	@Autowired
    private UserCardSupportService userCardSupportService;
	
	@Value("${static_path}")
	private String staticPath;
	
	/**
     * 去登录
     *
     * @return
     */
    @RequestMapping(value = "login.do", method = RequestMethod.GET)
    @Login(AuthenType.json)
    @ResponseBody
    public String login(HttpServletRequest request) {
        return "success";
    }
    
	/**
	 * 头条list
	 * @return list
	 */
	@RequestMapping(value = "list.page")
	@Login(AuthenType.page)
	public ModelAndView list(Headline record) {
		ModelAndView view = new ModelAndView("/headline/list");
		List<HeadlineBanner> banners = headlineService.queryBannerList(record);
		List<HeadlineNews> upNews = headlineService.queryUpNewsList(record);
		record.setCurrPage(1);
		record.setPageSize(10);
		List<HeadlineNews> news = headlineService.queryNewsList(record);
		view.addObject("banners", banners);
		view.addObject("upNews", upNews);
		view.addObject("news", news);
		return view;
	}

	/**
	 * 头条list滚动分页数据
	 * @return list
	 */
	@RequestMapping(value = "list.do")
	public @ResponseBody Object pageList(Headline record) {
		Map<String, List<HeadlineNews>> map = new HashMap<String, List<HeadlineNews>>();
		List<HeadlineNews> news = headlineService.queryNewsList(record);
		map.put("list", news);
		return map;
	}
	
	/**
	 * 新闻详情
	 * @return HeadlineNews
	 */
	@RequestMapping(value = "detail.page")
	@Login(AuthenType.page)
	public ModelAndView detail(Long id) {
		ModelAndView view = new ModelAndView("/headline/detail");
		HeadlineNews news = headlineService.selectByPrimaryKey(id);
		view.addObject("news", news);
		return view;
	}
	
	/**
	 * 分享存数据
	 * @return id
	 */
	@RequestMapping(value = "share.do")
	public @ResponseBody Object share(HeadlineShare record, HttpServletRequest request) {
		
		Integer userId = null;
		try {
			userId = WebUtils.getMemberId(request);
			if(userId != null) {
				record.setUserId(userId);
			}
		} catch (Exception e) {
			logger.error("获取userId失败");
		}
		record.setCreateTime(new Date());
		int id = headlineShareMapper.insertSelective(record);
		if(id != 1){
			return "fail";
		}
		return record.getId();
	}
	
	/**
	 * 分享后的新闻详情带观点
	 * @return HeadlineNews
	 */
	@RequestMapping(value = "share.page")
	public ModelAndView toShare(Long id) {
		ModelAndView view = new ModelAndView("/headline/share");
		HeadlineShare share = headlineShareMapper.selectByPrimaryKey(id);
		if(null != share){
			HeadlineNews news = headlineNewsMapper.selectByPrimaryKey(share.getNewsId());
			if(null != news && null != news.getImgUrl()){
				news.setFileUrl(staticPath + File.separator + news.getImgUrl()); 
			}
			UserCardRespDto user = userCardSupportService.getUserBaseInfoByUserId(share.getUserId());
			view.addObject("news", news);
			view.addObject("user", user);
		}
		view.addObject("share", share);
		return view;
	}
	
	/**
	 * 更新实际点击数
	 * @return string
	 */
	@RequestMapping(value = "updateHit.do")
	public @ResponseBody String updateHit(Long id) {
		int update = headlineNewsMapper.updateHit(id);
		if(update != 1){
			return "fail";
		}
		return "success";
	}
}
