package com.hangjia.bxj.mvc.controller.study_core;

import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.model.study_core.ArticleCreateArgs;
import com.hangjia.bxj.mvc.AjaxResult;
import com.hangjia.bxj.mvc.util.UEditorUtils;
import com.hangjia.bxj.query.study_core.ArticleManagerQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("study_core")
public class StudyCoreManagerController {
	
	@Autowired
	private StudyCoreManagerService studyCoreManagerService;
	
	/**
	 * 
	 * 显示学习板块首页。
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("index.jhtml")
	private Object showIndexPage(HttpServletRequest request) {
		
		// 查询分类，供选择（查询条件，可选择按分类查询）
		Object typies = studyCoreManagerService.listSimpleArticleTypies();
		request.setAttribute("typies", typies);
		return "study_core/index";
	}
	
	/**
	 * 首页加载完成后，加载数据（学习文章）列表。
	 * @param query
	 * @return
	 */
	@ResponseBody
	@RequestMapping("list.json")
	private Object list(ArticleManagerQuery query) {
		if("".equalsIgnoreCase(query.getClassify())) {
			query.setClassify(null);
		}
		if("".equalsIgnoreCase(query.getClassify1())) {
			query.setClassify1(null);
		}
		if("".equalsIgnoreCase(query.getClassify2())) {
			query.setClassify2(null);
		}
		Object result = studyCoreManagerService.paginationQuery(query);
		return result;
	}

	@RequestMapping("create.jhtml")
	private Object showCreatorPage(HttpServletRequest request) {
		
		// 查询分类，供选择（选择创建文章的分类）
		Object typies = studyCoreManagerService.listSimpleArticleTypies();
		request.setAttribute("typies", typies);
		
		return "study_core/create";
	}
	
	@ResponseBody
	@RequestMapping("create.json")
	private Object createArticle(ArticleCreateArgs args) {
		studyCoreManagerService.create(args);
		return new AjaxResult.success(args.getId());
	}

	@ResponseBody
	@RequestMapping("classify.json")
	private Object classify(Integer classifyId) {
		Object object = studyCoreManagerService.classify(classifyId);
		return new AjaxResult.success(object);
	}
	
	@RequestMapping("update.jhtml")
	private Object showUpdatePage(HttpServletRequest request, Integer articleId) {
		
		// 查询分类，供选择（选择创建文章的分类）
		Object typies = studyCoreManagerService.listSimpleArticleTypies();
		request.setAttribute("typies", typies);
		
		// 查询文章数据，补全表单
		Object model = studyCoreManagerService.findArticleToUpdate(articleId);
		request.setAttribute("model", model);
		
		return "study_core/update";
	}
	
	@ResponseBody
	@RequestMapping("update.json")
	private Object updateArticle(ArticleCreateArgs args) {
		studyCoreManagerService.update(args);
		return new AjaxResult.success(args.getId());
	}
	@ResponseBody
	@RequestMapping("upActicleData.json")
	private Object upActicleData(ArticleCreateArgs args) {
		Result result = new Result();
		studyCoreManagerService.updateScore(args);
		return result;
	}
	
	@RequestMapping("details.jhtml")
	private Object showDetailsPage(HttpServletRequest request) {
		
		// 查询分类，供选择（选择创建文章的分类）
		Object typies = studyCoreManagerService.listSimpleArticleTypies();
		request.setAttribute("typies", typies);
		
		return "";
	}
	
	@RequestMapping("updateDisplayStatus.json")
	@ResponseBody
	private Object updateDisplayStatus(Integer articleId, Boolean display) {
		studyCoreManagerService.updateDisplayStatus(articleId, display);
		return new AjaxResult.success();
	}
	
	/*
	 * ----------------------------
	 * UE
	 */
	
	
	private String articleImageRequestBasePath;
	
	private String articleImageStoreBasePath;
	
	@Value("${study_core.articleImg.requestBasePath}")
	public void setArticleImageRequestBasePath(String articleImageRequestBasePath) {
		this.articleImageRequestBasePath = articleImageRequestBasePath + "/image/";
	}
	
	@Value("${study_core.articleImg.storeBasePath}")
	public void setArticleImageStoreBasePath(String articleImageStoreBasePath) {
		this.articleImageStoreBasePath = articleImageStoreBasePath + "/image/";
	}
	
	/**
	 * 抓去远程图片，当远程图片被复制到编辑器时，由 ueditor 发起请求调用。
	 * @return
	 */
	@ResponseBody
	@RequestMapping("catchImage.json")
	private Object catchImage(HttpServletRequest request) {
		String[] param = request.getParameterValues("source[]");
		return UEditorUtils.saveRemoteFile(param, articleImageStoreBasePath, articleImageRequestBasePath);
	}
	
	@ResponseBody
	@RequestMapping("uploadImage.json")
	private Object uploadImage(MultipartFile upfile) {
		return UEditorUtils.uploadFile(upfile, articleImageStoreBasePath, articleImageRequestBasePath);
	}
	
	@ResponseBody
	@RequestMapping("listImage.json")
	private Object listImage(int start, int size) {
		return UEditorUtils.listFile(start, size, articleImageStoreBasePath, articleImageRequestBasePath);
	}
	
	//{"state": "SUCCESS", list: [{"state": "SUCCESS","title": "1467275561432060056.jpg","source": "http://img1.gtimg.com/news/pics/hv1/92/126/2091/135999497.jpg","url": "/ueditor/jsp/upload/image/20160630/1467275561432060056.jpg","size": "65748"} ]}
}
