package com.hangjia.bxj.mvc.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baobao.sso.client.UserCardRespDto;
import com.baobao.sso.client.WebUtils;
import com.baobao.sso.filter.AuthenType;
import com.baobao.sso.filter.Login;
import com.baobao.sso.service.UserCardSupportService;
import com.hangjia.bxj.dao.StudyDetailMapper;
import com.hangjia.bxj.model.StudyDetail;
import com.hangjia.bxj.model.StudyShare;
import com.hangjia.bxj.service.study.impl.StudyService;

/**
 * 学习 模块
 * 
 * @ClassName:
 * @Description:
 * @author: he-Yi
 * @date: 2016年5月14日 下午2:08:52
 */
@Controller
@RequestMapping(value = "/study")
public class StudyController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private StudyService studyService;

	@Autowired
	private StudyDetailMapper studydao;

	@Autowired
	private UserCardSupportService userCardSupportService;

	/**
	 * 去登录
	 *
	 * @return
	 */
	@RequestMapping(value = "login.do", method = RequestMethod.GET)
	@ResponseBody
	public String login(HttpServletRequest request) {
		Integer userId = null;
		// 先判断是否登陆
		try {
			userId = WebUtils.getMemberId(request);
		} catch (Exception e) {
			return "0";
		}
		if (userId == null) {
			return "0";
		}
		return "1";
	}

	/**
	 * 学习list
	 * 
	 * @return list
	 */
	@RequestMapping(value = "list.page")
	@Login(AuthenType.page)
	public ModelAndView list(StudyDetail record, @RequestParam(defaultValue = "1") int index,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		ModelAndView view = new ModelAndView("/study/studylist");
		// List<HeadlineBanner> banners = studyService.queryBannerList(record);
		List<StudyDetail> studyListup = studyService.queryUpList(record, index, pageSize);
		List<StudyDetail> studylist = studyService.queryList(record, index, pageSize);
		// view.addObject("banners", banners);
		view.addObject("upStudys", studyListup);
		view.addObject("news", studylist);
		return view;
	}

	/**
	 * list滚动分页数据
	 * 
	 * @return list
	 */
	@RequestMapping(value = "list.do")
	public @ResponseBody Object pageList(StudyDetail record, @RequestParam(defaultValue = "1") int currPage,
			@RequestParam(defaultValue = "10") int pageSize) {
		Map<String, List<StudyDetail>> map = new HashMap<String, List<StudyDetail>>();
		List<StudyDetail> news = studyService.queryList(record, currPage, pageSize);
		map.put("list", news);
		return map;
	}

	/**
	 * 学习详情
	 * 
	 * @return HeadlineNews
	 */
	@RequestMapping(value = "detail.page")
	@Login(AuthenType.page)
	public ModelAndView detail(HttpServletRequest request, Long id, String showName) {
		ModelAndView view = new ModelAndView("/study/study_detail");
		if (id == null)
			logger.info("传入学习为空id!");
		view.addObject("study", studyService.selectByPK(id));
		// 判断是app外查看
		// if(showName==null){
		// //分享后获取的
		// request.setAttribute("shareVal", "shareVal"); //标记是分享的
		//
		// StudyDetail share = studyService.selectByPK(id);
		// if(null != share){
		// StudyShare studyshare = studyService.selectBystudyID(share.getId());
		// UserCardRespDto user =
		// userCardSupportService.getUserCardByFid(studyshare.getUserId().intValue());
		// view.addObject("studyshare", studyshare);
		// view.addObject("user", user);
		// }
		// }
		return view;
	}

	@RequestMapping(value = "detailShare.page")
	public ModelAndView detailShare(HttpServletRequest request, Long id, String showName) {
		ModelAndView view = new ModelAndView("/study/study_detail");
		// 判断是app外查看
		if (showName == null) {
			// 分享后获取的
			request.setAttribute("shareVal", "shareVal"); // 标记是分享的
			StudyShare share = studyService.selectShareByPK(id);
			if (null != share) {
				view.addObject("study", studyService.selectByPK(share.getStudyId()));
				if (null != share.getUserId()) {
					UserCardRespDto user = userCardSupportService.getUserCardByFid(share.getUserId().intValue());
					view.addObject("user", user);
				}
				view.addObject("studyshare", share);
			}
		}
		return view;
	}

	/**
	 * 存储分享数据
	 * 
	 * @return id
	 */
	@RequestMapping(value = "share.do")
	public @ResponseBody Object share(StudyShare record, HttpServletRequest request) {

		Integer userId = null;
		try {
			userId = WebUtils.getMemberId(request);
			if (userId != null) {
				record.setUserId(userId.longValue());
			}
		} catch (Exception e) {
			logger.error("获取userId失败");
		}
		record.setCreateTime(new Date());
		int id = studyService.insertShare(record);
		if (id != 1) {
			return "fail";
		}
		return record.getId();
	}

	/**
	 * 更新实际点击数
	 * 
	 * @return string
	 */
	@RequestMapping(value = "updateCount.do")
	public @ResponseBody String updateHit(Long id) {
		int update = studydao.updateCount(id);
		if (update != 1) {
			return "fail";
		}
		return "success";
	}
}
