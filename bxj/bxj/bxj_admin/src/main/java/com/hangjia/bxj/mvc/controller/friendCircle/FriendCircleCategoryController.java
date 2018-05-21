package com.hangjia.bxj.mvc.controller.friendCircle;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


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
import com.hangjia.bxj.model.firendcircle.FriendCircleCategory;
import com.hangjia.bxj.query.app.FriendCircleQuery;
import com.hangjia.bxj.service.friendcircle.IfriendCircleService;

/**
 * 朋友圈分类
 * @ClassName: 
 * @Description: 
 * @author: he-Yi
 * @date: 2016年8月25日 下午3:37:14
 */
@Controller
@RequestMapping("/friendCateGory")
public class FriendCircleCategoryController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IfriendCircleService  circleService;
	
	@Value("${show_path}")
	private String uploadPath;
	
	@InitBinder
    protected void initBinder1(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat, true));
    }
	
    /**
	 * 跳转页面 朋友圈分类vm
	 * @return url
	 */
    @RequestMapping("friendCategory.jhtml")
    public String showCircleCategoryList() {
        return "friendCircle/friendCircleCategory";
    }
    
    /**
	 * 查询分类列表
	 * @param query
	 * @return Result
	 */
    @RequestMapping("queryCategoryList.json")
    public @ResponseBody Result categoryList(@ModelAttribute FriendCircleQuery query) {
    	Result result = new Result();
    	int count = circleService.queryCountCateGory(query);
    	if(count > 0){
    		List<FriendCircleCategory> friendList = circleService.selectTypeList(query);
    		result.setModel(friendList); 
    	}
    	query.setTotalItem(count);
    	result.setQuery(query);
        return result;
    }
    
    /**
	 * 查询分类
	 * @param query
	 * @return Result
	 */
    @RequestMapping("queryCateGory.json")
    public @ResponseBody Result queryCateGory(@ModelAttribute FriendCircleQuery query) {
    	Result result = new Result(); 
    	FriendCircleCategory fircleCategory = circleService.getCategory(query);
    	if(null != fircleCategory){
    		result.setModel(fircleCategory);
    	} else {
    		result.setSuccess(false);
    		logger.error("查询 朋友圈分类失败");
    	}
        return result;
    }
    
    /**
	 * 删除 分类
	 * @param id
	 * @return Result
	 */
    @RequestMapping("deleteCategory.json")
    public @ResponseBody Result deleteCategory(@RequestParam Long id) {
    	Result result = new Result();
    	FriendCircleCategory record=new FriendCircleCategory();
    	record.setIsDisplay(false);
    	record.setFid(id);
    	int delete = circleService.updateCategory(record);
    	if(delete != 1){
    		result.setSuccess(false);
    		logger.error("删除分类Category失败");
    	}
        return result;
    }
    /**
     * 修改 分类
     * @param id
     * @return
     */
    @RequestMapping("updateCategory.json")
    public @ResponseBody Result updateCategory(@ModelAttribute  FriendCircleCategory record) {
    	Result result = new Result();
    	int update = circleService.updateCategory(record);
    	if(update != 1){
    		result.setSuccess(false);
    		logger.error("删除分类Category失败");
    	}
        return result;
    }
    
	/**
	 * 新增 分类 
	 * @param record
	 * @return Result
	 */
    @RequestMapping("addCategory.json")
    public @ResponseBody Result addCategory(@ModelAttribute FriendCircleCategory record) {
    	Result result = new Result();
    	record.setCreateAt(new Date());
    	int insert = circleService.insertCategory(record);
    	if(insert != 1){
    		result.setSuccess(false);
    		logger.error("新增分类Category失败");
    	}
        return result;
    }
}
