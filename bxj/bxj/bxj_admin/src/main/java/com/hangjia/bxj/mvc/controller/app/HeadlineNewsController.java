package com.hangjia.bxj.mvc.controller.app;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.dao.HeadlineNewsMapper;
import com.hangjia.bxj.model.HeadlineNews;
import com.hangjia.bxj.query.app.HeadlineNewsQuery;
import com.hangjia.bxj.service.HeadlineNewsService;

/** 
* @author  作者 : yaoy
* @date 2016年5月9日 下午2:30:15 
* @version 1.0 
*/
@Controller
@RequestMapping("/app")
public class HeadlineNewsController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private HeadlineNewsMapper headlineNewsMapper;
	
	@Autowired
	private HeadlineNewsService headlineNewsService;
	
	@Value("${show_path}")
	private String uploadPath;
	
	@InitBinder
    protected void initBinder1(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat, true));
    }
	
	/**
	 * 跳转页面
	 * @return url
	 */
    @RequestMapping("headlineNews.jhtml")
    public String headlineNews() {
        return "app/headlineNews";
    }
    
	/**
	 * 查询头条headlineNews列表
	 * @param query
	 * @return Result
	 */
    @RequestMapping("queryHeadlineNewsList.json")
    public @ResponseBody Result newsList(@ModelAttribute HeadlineNewsQuery query) {
    	Result result = new Result();
    	int count = headlineNewsMapper.queryPageDataCount(query);
    	if(count > 0){
    		List<HeadlineNews> NewsList = headlineNewsService.queryPageData(query);
    		result.setModel(NewsList);
    	}
    	query.setTotalItem(count);
    	result.setQuery(query);
        return result;
    }
    
	/**
	 * 更新News
	 * @param query
	 * @return Result
	 */
    @RequestMapping("updateNews.json")
    public @ResponseBody Result updateNews(@ModelAttribute HeadlineNews record) {
    	Result result = new Result();
    	record.setUpdateTime(new Date());
    	int update = headlineNewsService.updateNews(record);
    	if(update != 1){
    		result.setSuccess(false);
    		logger.error("更新News失败");
    	}
        return result;
    }
    
	/**
	 * 删除News
	 * @param query
	 * @return Result
	 */
    @RequestMapping("deleteNews.json")
    public @ResponseBody Result deleteNews(@RequestParam Long id) {
    	Result result = new Result();
    	int delete = headlineNewsMapper.deleteByPrimaryKey(id);
    	if(delete != 1){
    		result.setSuccess(false);
    		logger.error("删除News失败");
    	}
        return result;
    }
    
	/**
	 * 新增News
	 * @param query
	 * @return Result
	 */
    @RequestMapping("addNews.json")
    public @ResponseBody Result addNews(@ModelAttribute HeadlineNews record) {
    	Result result = new Result();
    	record.setCreateTime(new Date());
    	int insert = headlineNewsService.addNews(record);
    	if(insert != 1){
    		result.setSuccess(false);
    		logger.error("新增News失败");
    	}
        return result;
    }
    
	/**
	 * upOrDownNews
	 * @param query
	 * @return Result
	 */
    @RequestMapping("upOrDownNews.json")
    public @ResponseBody Result upOrDownNews(@ModelAttribute HeadlineNewsQuery query) {
    	Result result = new Result();
    	int update = headlineNewsService.upOrDownHeadlineNews(query);
    	if(update == 20){
    		result.setMsg("该头条已经排在最后位!");
    		result.setSuccess(false);
    		logger.error("该头条已经排在最后位!");
    	} else if(update != 1){
    		result.setSuccess(false);
    		logger.error("更新News失败");
    	}
        return result;
    }
    
	/**
	 * 查询单一News
	 * @param query
	 * @return Result
	 */
    @RequestMapping("queryNews.json")
    public @ResponseBody Result queryNews(@ModelAttribute HeadlineNewsQuery query) {
    	Result result = new Result();
    	HeadlineNews headlineNews = headlineNewsMapper.selectByPrimaryKey(query.getId());
    	if(null != headlineNews){
    		if(StringUtils.isNotBlank(headlineNews.getImgUrl())){
    			headlineNews.setFileUrl(uploadPath + File.separator + headlineNews.getImgUrl()); 
    		}
    		result.setModel(headlineNews);
    	} else {
    		result.setSuccess(false);
    		logger.error("查询News失败");
    	}
        return result;
    }
    
}
