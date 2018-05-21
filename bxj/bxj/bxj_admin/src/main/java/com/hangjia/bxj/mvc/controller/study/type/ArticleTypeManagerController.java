package com.hangjia.bxj.mvc.controller.study.type;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hangjia.bxj.model.study.type.ArticleTypeEntity;
import com.hangjia.bxj.mvc.AjaxResult;

/**
 * 
 * 学习管理模块 文章【分类】管理 控制器
 * @author K9999
 *
 */
@Controller
@RequestMapping("studyType")
public class ArticleTypeManagerController {
	
	@Autowired
	private ArticleTypeManagerService articleTypeManagerService;

	@RequestMapping("index.jhtml")
	private Object showIndexPage() {
		return "study/type/index";
	}
	
	@ResponseBody
	@RequestMapping("list.json")
	private Object list() {
		Object result = articleTypeManagerService.listAllType();
		return result;
	}
	
	@ResponseBody
	@RequestMapping("delete.json")
	private Object delete(Integer typeId) {
		articleTypeManagerService.deleteType(typeId);
		return new AjaxResult.success();
	}
	
	@ResponseBody
	@RequestMapping("updateDisplayStatus.json")
	private Object updateDisplayStatus(Integer typeId, Boolean display) {
		articleTypeManagerService.updateDisplayStatus(typeId, display);
		return new AjaxResult.success();
	}
	
	@RequestMapping("create.jhtml")
	private Object showCreatePage() {
		return "study/type/form";
	}
	
	@RequestMapping("update.jhtml")
	private Object showUpdatePage(HttpServletRequest request, Integer typeId) {
		Object model = articleTypeManagerService.findArticleTypeToUpdate(typeId);
		request.setAttribute("model", model);
		return "study/type/form";
	}
	
	@RequestMapping("saveOrUpdate.jhtml")
	private Object saveOrUpdate(ArticleTypeEntity entity) {
		articleTypeManagerService.saveOrUpdate(entity);
		return "redirect:index.jhtml";
	}
	
}
