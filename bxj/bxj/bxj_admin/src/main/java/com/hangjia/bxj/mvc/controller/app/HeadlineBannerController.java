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
import com.hangjia.bxj.dao.HeadlineBannerMapper;
import com.hangjia.bxj.model.HeadlineBanner;
import com.hangjia.bxj.query.app.HeadlineBannerQuery;
import com.hangjia.bxj.service.HeadlineBannerService;

/** 
* @author  作者 : yaoy
* @date 2016年5月9日 下午2:30:15 
* @version 1.0 
*/
@Controller
@RequestMapping("/app")
public class HeadlineBannerController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private HeadlineBannerMapper headlineBannerMapper;
	
	@Autowired
	private HeadlineBannerService headlineBannerService;
	
	@Value("${show_path}")
	private String uploadPath;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat, true));
    }
	/**
	 * 跳转页面
	 * @return url
	 */
    @RequestMapping("headlineBanner.jhtml")
    public String headlineBanner() {
        return "app/headlineBanner";
    }
    
	/**
	 * 查询头条headlineBanner列表
	 * @param query
	 * @return Result
	 */
    @RequestMapping("queryHeadlineBannerList.json")
    public @ResponseBody Result bannerList(@ModelAttribute HeadlineBannerQuery query) {
    	Result result = new Result();
    	int count = headlineBannerMapper.queryPageDataCount(query);
    	if(count > 0){
    		List<HeadlineBanner> bannerList = headlineBannerService.queryPageData(query);
    		result.setModel(bannerList);
    	}
    	query.setTotalItem(count);
    	result.setQuery(query);
        return result;
    }
    
	/**
	 * 更新banner
	 * @param query
	 * @return Result
	 */
    @RequestMapping("updateBanner.json")
    public @ResponseBody Result updateBanner(@ModelAttribute HeadlineBanner record) {
    	Result result = new Result();
    	record.setModifyAt(new Date());
    	int update = headlineBannerService.updateBanner(record);
    	if(update != 1){
    		result.setSuccess(false);
    		logger.error("更新banner失败");
    	}
        return result;
    }
    
	/**
	 * 删除banner
	 * @param query
	 * @return Result
	 */
    @RequestMapping("deleteBanner.json")
    public @ResponseBody Result deleteBanner(@RequestParam Long id) {
    	Result result = new Result();
    	int delete = headlineBannerMapper.deleteByPrimaryKey(id);
    	if(delete != 1){
    		result.setSuccess(false);
    		logger.error("删除banner失败");
    	}
        return result;
    }
    
	/**
	 * 新增banner
	 * @param query
	 * @return Result
	 */
    @RequestMapping("addBanner.json")
    public @ResponseBody Result addBanner(@ModelAttribute HeadlineBanner record) {
    	Result result = new Result();
    	record.setCreateAt(new Date());
    	int insert = headlineBannerService.addBanner(record);
    	if(insert != 1){
    		result.setSuccess(false);
    		logger.error("新增banner失败");
    	}
        return result;
    }
    
	/**
	 * upOrDownbanner
	 * @param query
	 * @return Result
	 */
    @RequestMapping("upOrDownBanner.json")
    public @ResponseBody Result upOrDownBanner(@ModelAttribute HeadlineBannerQuery query) {
    	Result result = new Result();
    	int update = headlineBannerService.upOrDownHeadlineBanner(query);
    	if(update != 1){
    		result.setSuccess(false);
    		logger.error("更新banner失败");
    	}
        return result;
    }
    
	/**
	 * 查询单一banner
	 * @param query
	 * @return Result
	 */
    @RequestMapping("queryBanner.json")
    public @ResponseBody Result queryBanner(@ModelAttribute HeadlineBannerQuery query) {
    	Result result = new Result();
    	HeadlineBanner headlineBanner = headlineBannerMapper.selectByPrimaryKey(query.getId());
    	if(null != headlineBanner){
    		if(StringUtils.isNotBlank(headlineBanner.getImageUrl())){
    			headlineBanner.setFileUrl(uploadPath + File.separator + headlineBanner.getImageUrl()); 
    		}
    		result.setModel(headlineBanner);
    	} else {
    		result.setSuccess(false);
    		logger.error("查询banner失败");
    	}
        return result;
    }
    
}
