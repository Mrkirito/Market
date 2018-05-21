package com.hangjia.bxj.mvc.controller.banner;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hangjia.bxj.mvc.AjaxResult;
import com.hangjia.bxj.query.banner.BannerManagerQuery;
import com.hangjia.bxj.service.banner.BannerManagerService;
import com.hangjia.bxj.vo.banner.BannerManagerParameter;

/**
 * 
 * 【Banner】管理控制器
 * @author K9999
 *
 */
@Controller
@RequestMapping("banner")
public class BannerManagerController {
	
	@Autowired
	private BannerManagerService bannerManagerService;

	@RequestMapping("index.jhtml")
	private Object showIndexPage() {
		return "banner/index";
	}
	
	@RequestMapping("list.json")
	@ResponseBody
	private Object list(BannerManagerQuery params) {
		Object result = bannerManagerService.paginationQuery(params);
		return result;
	}
	
	@RequestMapping("updateDisplayStatus.json")
	@ResponseBody
	private Object updateDisplayStatus(String showType, Integer id, Boolean status) {
		bannerManagerService.updateDisplayStatus(showType, id, status);
		return new AjaxResult.success();
	}
	
	@RequestMapping("create.jhtml")
	private Object showCreatePage() {
		return "banner/form";
	}
	
	@RequestMapping("update.jhtml")
	private Object showUpdatePage(HttpServletRequest request, String showType, Integer bannerId) {
		Object banner = bannerManagerService.findBannerToUpdate(showType, bannerId);
		request.setAttribute("model", banner);
		return "banner/form";
	}
	
	@RequestMapping("saveOrUpdate.jhtml")
	private Object saveOrUpdate(BannerManagerParameter params, MultipartFile image) {
		bannerManagerService.saveOrUpdate(params, image);
		return "redirect:index.jhtml#" + params.getShowType();
	}
	
}
