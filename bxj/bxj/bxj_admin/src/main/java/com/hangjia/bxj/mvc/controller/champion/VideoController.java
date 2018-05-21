package com.hangjia.bxj.mvc.controller.champion;

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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.dao.ChampionVideoMapper;
import com.hangjia.bxj.excel.view.ViewExcel;
import com.hangjia.bxj.model.ChampionVideo;
import com.hangjia.bxj.model.champion.VideoCommentDO;
import com.hangjia.bxj.model.champion.VideoReportDO;
import com.hangjia.bxj.query.champion.ChampionVideoQuery;
import com.hangjia.bxj.query.champion.VideoCommentQuery;
import com.hangjia.bxj.query.champion.VideoReportQuery;
import com.hangjia.bxj.service.champion.VideoService;

/**
* @author  作者 : yaoy
* @date 2016年5月9日 下午2:30:15
* @version 1.0
*/
@Controller
@RequestMapping("/champion")
public class VideoController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ChampionVideoMapper championVideoMapper;

	@Autowired
	private VideoService videoService;

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
    @RequestMapping("video.jhtml")
    public String video() {
        return "champion/video";
    }

	/**
	 * 跳转页面
	 * @return url
	 */
    @RequestMapping("toCommentDetail.jhtml")
    public String toCommentDetail() {
        return "champion/videoComment";
    }

	/**
	 * 跳转页面
	 * @return url
	 */
    @RequestMapping("videoReport.jhtml")
    public String videoReport() {
        return "champion/videoReport";
    }

	/**
	 * 查询音/视频列表
	 * @param query
	 * @return Result
	 */
    @RequestMapping("queryVideoList.json")
    public @ResponseBody Result videoList(@ModelAttribute ChampionVideoQuery query) {
    	Result result = new Result();
    	int count = championVideoMapper.queryPageDataCount(query);
    	if(count > 0){
    		List<ChampionVideo> videoList = videoService.queryPageData(query);
    		result.setModel(videoList);
    	}
    	query.setTotalItem(count);
    	result.setQuery(query);
        return result;
    }

	/**
	 * 更新视频状态
	 * @param record
	 * @return Result
	 */
    @RequestMapping("updateVideoStatus.json")
    public @ResponseBody Result updateVideoStatus(@ModelAttribute ChampionVideo record) {
    	Result result = new Result();
    	record.setAuditAt(new Date());
    	int update = videoService.updateVideoStatus(record);
    	if(update != 1){
    		result.setSuccess(false);
    		logger.error("更新Video失败");
    	}
        return result;
    }

	/**
	 * 更新视频
	 * @param record
	 * @param tagIds
	 * @return Result
	 */
    @RequestMapping("updateVideo.json")
    public @ResponseBody Result updateVideo(@ModelAttribute ChampionVideo record, String tagIds) {
    	Result result = new Result();
    	record.setAuditAt(new Date());
    	int update = videoService.updateVideo(record, tagIds);
    	if(update != 1){
    		result.setSuccess(false);
    		logger.error("更新Video失败");
    	}
        return result;
    }

	/**
	 * 新增Video
	 * @param record
	 * @param tagIds
	 * @return Result
	 */
    @RequestMapping("addNews.json")
    public @ResponseBody Result addVideo(@ModelAttribute ChampionVideo record, String tagIds) {
    	Result result = new Result();
    	record.setCreateAt(new Date());
    	int insert = videoService.addVideo(record, tagIds);
    	if(insert != 1){
    		result.setSuccess(false);
    		logger.error("新增Video失败");
    	}
        return result;
    }

	/**
	 * 查询单一Video
	 * @param query
	 * @return Result
	 */
    @RequestMapping("queryVideo.json")
    public @ResponseBody Result queryVideo(@ModelAttribute ChampionVideoQuery query) {
    	Result result = new Result();
    	ChampionVideo video = videoService.queryVideo(query);
    	if(null != video){
    		result.setModel(video);
    	} else {
    		result.setSuccess(false);
    		logger.error("查询Video失败");
    	}
        return result;
    }

	/**
	 * 查询音/视频评论列表
	 * @param query
	 * @return Result
	*/
	@RequestMapping("commentDetailList.json")
	public @ResponseBody Result commentDetailList(@ModelAttribute VideoCommentQuery query) {
		Result result = new Result();
		int count = championVideoMapper.queryCommentPageDataCount(query);
		if(count > 0) {
			query.setAuditStatus(2);
			List<VideoCommentDO> videoCommentList = championVideoMapper.queryCommentPageData(query);
    		result.setModel(videoCommentList);
    	}
    	query.setTotalItem(count);
    	result.setQuery(query);
        return result;
    }

	/**
	 * 删除音/视频评论
	 * @param videoCommentDO
	 * @return Result
	 */
    @RequestMapping("deleteVideoComment.json")
    public @ResponseBody Result deleteVideoComment(@ModelAttribute VideoCommentDO videoCommentDO) {
    	Result result = new Result();
    	if(null != videoCommentDO && null != videoCommentDO.getFid()){
    		int delete = videoService.deleteVideoComment(videoCommentDO);
        	if(delete <= 0){
        		result.setSuccess(false);
        		logger.error("删除音/视频评论失败");
        	}
    	} else {
    		result.setSuccess(false);
    		logger.error("删除音/视频评论失败");
    	}
        return result;
    }

	/**
	 * 查询音/视频举报列表
	 * @param query
	 * @return Result
	 */
    @RequestMapping("queryVideoReportList.json")
    public @ResponseBody Result queryVideoReportList(@ModelAttribute VideoReportQuery query) {
    	Result result = new Result();

    	int count = 0;

    	if(StringUtils.isNotBlank(query.getPhone()) || StringUtils.isNotBlank(query.getName())){
    		List<Long> userIds = videoService.queryUserIds(query);
    		if(null != userIds && userIds.size() > 0){
    			query.setUserIds(userIds);
    		} else {
    			query.setTotalItem(count);
    	    	result.setQuery(query);
    	        return result;
    		}
    	}

    	count = videoService.queryReportDataCount(query);
    	if(count > 0){
    		List<VideoReportDO> reportList = videoService.queryReportPageData(query);
    		result.setModel(reportList);
    	}
    	query.setTotalItem(count);
    	result.setQuery(query);
        return result;
    }

	/**
	 * 删除音/视频评论
	 * @param ids
	 * @return Result
	 */
	@RequestMapping("delete_bat.json")
	public @ResponseBody Result deleteBat(String ids) {
		Result result = new Result();
		if(StringUtils.isNotBlank(ids)){
			int delete = videoService.deleteBat(ids);
			if(delete <= 0){
				result.setSuccess(false);
				logger.error("删除音/视频评论失败");
			}
		} else {
			result.setSuccess(false);
			logger.error("删除音/视频评论失败");
		}
		return result;
	}
}
