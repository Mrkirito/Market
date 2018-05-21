package com.hangjia.bxj.mvc.controller.app;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.mvc.util.DecimalFormatUtil;
import com.hangjia.bxj.query.app.LookVideoQuery;
import com.hangjia.bxj.service.study.IstudyService;
import com.hangjia.bxj.service.vdinvitecount.IVideoInviteService;
import com.hangjia.bxj.vo.LookVideoDetailVo;
import com.hangjia.bxj.vo.LookVideoVo;
import com.hangjia.bxj.vo.VideoInviteCountVo;

/**
 * 视频用券与邀请统计
 * @ClassName: 
 * @Description: 
 * @author: he-Yi
 * @date: 2016年5月31日 上午9:53:07
 */
@Controller
@RequestMapping(value="/videoInvite")
public class VideoInviteController {	

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IstudyService studyService;
	
	/**
	 * 查询视频与邀请统计信息
	 */
	@Autowired
	private IVideoInviteService videoInvite;
	
	@Value("${show_path}")
	private String showPath;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat, true));
    }
	
	/**
	 * 跳转邀请 统计页面 
	 * @return url
	 */
    @RequestMapping("inviteCount.jhtml")
    public String showInvitePage(HttpServletRequest request,VideoInviteCountVo query) {
    	if(query.getInviteAt()==null)query.setInviteAt(new Date());
    	//页面 头显示 邀请 总条数 --------------
    	int numTotal=videoInvite.qryInviteInfoTotal(query);
    	request.setAttribute("numTotal", numTotal);
        return "videoInviteCount/inviteCount";
    }
    
    /**
	 * 跳转视频用券 统计页面 
	 * @return url
	 */
    @RequestMapping("videoCount.jhtml")
    public String showVideoPage(HttpServletRequest request,VideoInviteCountVo query) {
    	/*if(query.getCreateAt()==null)query.setCreateAt(new Date());
       int videoTotal=videoInvite.qryVideoInfoTotal(query);
    	request.setAttribute("numTotal", videoTotal);*/
        return "videoInviteCount/videoVoucherCount";
    }
    /**
     *  
     * @param query
     * @return
     */
    @RequestMapping("lookInviteInfo.json")
    public @ResponseBody Result listKnowAll(HttpServletRequest request,@ModelAttribute VideoInviteCountVo query) {
    	Result result = new Result();
    	if(query.getInviteAt()==null)query.setInviteAt(new Date());
	    //查询 konw list 
		List<VideoInviteCountVo> resultlist = videoInvite.qryInviteInfo(query);
		result.setModel(resultlist);
    	//分组  邀请数据总条数 
    	query.setTotalItem(videoInvite.qryGroupUserNum(query)); 
    	result.setQuery(query);
    	result.setShowTextInfo("邀请总数:");
    	// 显示 邀请总数
    	Integer inviteTotal=videoInvite.qryInviteInfoTotal(query);
    	result.setShowTotal(DecimalFormatUtil.formatNum(inviteTotal)); //显示总条数
        return result;
    }
    
    /**
     * 查询观看视频信息 
     * @param query
     * @return
     */
    @RequestMapping("getVideoListInfo.json")
    public @ResponseBody Result selKnowText(@ModelAttribute LookVideoQuery query) {
//    	Result result = new Result();
//        if(query.getCreateAt()==null)query.setCreateAt(new Date());
//	    //查询 konw list  query.getPageSize()
//		List<VideoInviteCountVo> resultlist = videoInvite.qryVideoInfo(query);
//		result.setModel(resultlist);
//    	//每个视频  数据总条数 
//    	query.setTotalItem(videoInvite.qryVDVourcherNum(query));
//    	result.setQuery(query);
//    	//显示 视频用券 总数 
//    	Integer videoTotal=videoInvite.qryVideoInfoTotal(query);
//    	result.setShowTextInfo("视频用券总数:");
//    	result.setShowTotal(DecimalFormatUtil.formatNum(videoTotal)); //显示总条数
    	
    	Result result = new Result();
    	int count = videoInvite.queryPageDataCount(query);
    	if(count > 0){
    		List<LookVideoVo> list = videoInvite.queryPageData(query);
    		result.setModel(list);
    	}
    	query.setTotalItem(count);
    	result.setQuery(query);
        return result;
    }

    /**
     * 查询观看视频详情
     * @param query
     * @return
     */
    @RequestMapping("queryLookVideoDetail.json")
    public @ResponseBody Result queryLookVideoDetail(@ModelAttribute LookVideoQuery query) {
    	
    	Result result = new Result();
    	int count = videoInvite.queryPageDetailDataCount(query);
    	if(count > 0){
    		List<LookVideoDetailVo> list = videoInvite.queryPageDetailData(query);
    		result.setModel(list);
    	}
    	query.setTotalItem(count);
    	result.setQuery(query);
        return result;
    }
}
