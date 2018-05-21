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
import com.hangjia.bxj.model.firendcircle.FriendCircle;
import com.hangjia.bxj.query.app.FriendCircleQuery;
import com.hangjia.bxj.service.friendcircle.IfriendCircleService;


@Controller
@RequestMapping("/friendCircle")
public class FriendCircleController {

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
	 * 跳转页面
	 * @return url
	 */
    @RequestMapping("friendCircle.jhtml")
    public String showFriendList() {
        return "friendCircle/friendCircle";
    }
    
	/**
	 * 查询列表
	 * @param query
	 * @return Result
	 */
    @RequestMapping("queryFriendsList.json")
    public @ResponseBody Result friendList(@ModelAttribute FriendCircleQuery query) {
    	Result result = new Result();
    	int count = circleService.queryCount(query);
    	if(count > 0){
    		List<FriendCircle> friendList = circleService.queryPageData(query);
    		result.setModel(friendList); 
    	}
    	query.setTotalItem(count);
    	result.setQuery(query);
        return result;
    }
    
	/**
	 * 更新 状态
	 * @param record
	 * @return Result
	 */
    @RequestMapping("updateFriend.json")
    public @ResponseBody Result updateFriend(@ModelAttribute FriendCircle record) {
    	Result result = new Result();
    	record.setUpdateTime(new Date());
    	int update = circleService.updateByPKey(record);
    	if(update != 1){
    		result.setSuccess(false);
    		logger.error("更新朋友圈状态失败");
    	}
        return result;
    }
        
	/**
	 * 删除 
	 * @param id
	 * @return Result
	 */
    @RequestMapping("deleteFriend.json")
    public @ResponseBody Result deleteFriend(@RequestParam Long id) {
    	Result result = new Result();
    	FriendCircle record=new FriendCircle();
    	record.setStatus(4);
    	record.setFid(id);
    	int delete = circleService.updateByPKey(record);
    	if(delete != 1){
    		result.setSuccess(false);
    		logger.error("删除Friend失败");
    	}
        return result;
    }
    
	/**
	 * 新增 
	 * @param record
	 * @return Result
	 */
    @RequestMapping("addFriend.json")
    public @ResponseBody Result addFriend(@ModelAttribute FriendCircle record) {
    	Result result = new Result();
    	record.setCreateTime(new Date());
    	record.setStatus(1); //未上线 
    	int insert = circleService.insertFriend(record);
    	if(insert != 1){
    		result.setSuccess(false);
    		logger.error("新增Friend失败");
    	}
        return result;
    }
    
	/**
	 * 查询单一 
	 * @param query
	 * @return Result
	 */
    @RequestMapping("queryFriend.json")
    public @ResponseBody Result queryFriend(@ModelAttribute FriendCircleQuery query) {
    	Result result = new Result();
    	FriendCircle friendCircle = circleService.selectByPK(query.getId());
    	if(null != friendCircle){
    		result.setModel(friendCircle);
    	} else {
    		result.setSuccess(false);
    		logger.error("查询Friend失败");
    	}
        return result;
    }
    
    /**
     * 更新 信息
     * @param record
     * @return
     */
    @RequestMapping("updateImgsInfo.json")
    public @ResponseBody Result updateImgs(@ModelAttribute FriendCircle record) {
    	Result result = new Result();
    	record.setUpdateTime(new Date());
    	int update = circleService.updateImgsByPK(record);
    	if(update != 1){
    		result.setSuccess(false);
    		logger.error("更新朋友圈失败");
    	}
        return result;
    }
    
   
}
