package com.hangjia.newpeople.mvc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.baobao.sso.client.WebUtils;
import com.baobao.sso.filter.AuthenType;
import com.baobao.sso.filter.Login;
import com.hangjia.bxj.model.ChampionVideo;
import com.hangjia.bxj.vo.NewPeoplePassVo;
import com.hangjia.champion.service.ChampionVideoService;
import com.hangjia.newpeople.service.InewPeopleService;

/**
 * 新人通 
 * @ClassName: 
 * @Description: 
 * @author: he-Yi
 * @date: 2016年5月9日 下午2:41:14
 */
@Controller
//@RequestMapping("newPeoplePass")
public class NewPeopleController {

	private static Logger log = LoggerFactory.getLogger(NewPeopleController.class);
	@Autowired
	InewPeopleService newPeopleService;
	
	@Autowired
	private ChampionVideoService championVideoService;
	
//	@RequestMapping(value = "showNewPeople.page")
	public String queryNewPeople(HttpServletRequest request,@RequestParam(defaultValue = "1") Integer day, @RequestParam(defaultValue = "1") Integer index,
            @RequestParam(defaultValue = "50") Integer pageSize) {
		   Integer userId = null;
	        //先判断是否登陆
	        try {
	            userId = WebUtils.getMemberId(request);
	        } catch (Exception e) {
	        	log.info("用户未登陆!");
	        }
		//vo
		NewPeoplePassVo newPeopleVO=new NewPeoplePassVo();
		//newPeopleVO.setUserId(userId.longValue()); //用户id 初始化不需要
		//视频列表 
		/* List<NewPeoplePassVo> listvds=newPeopleService.selListNewPeopleVD(newPeopleVO, index, pageSize);
		request.setAttribute("vdList",listvds);*/
		
		//初始化信息
	    List<NewPeoplePassVo> listinit=newPeopleService.selList(newPeopleVO);
		request.setAttribute("listinit",listinit); 
		if(!listinit.isEmpty()) request.setAttribute("vdOneInfo",listinit.get(0)); //单个信息
		
		//课件列表
		request.setAttribute("courseList",newPeopleService.selListNewPeoCourse(newPeopleVO, index, pageSize)); 
		
		 //第几天
		newPeopleVO.setDay(day);
		newPeopleVO.setNewText("查询某天");
		List<NewPeoplePassVo> listone=newPeopleService.selList(newPeopleVO);
		if(!listone.isEmpty()){
			//更新浏览次数  跳转 1...5.天 
			newPeopleService.updateNum(listone.get(0).getId()); 
		}
		
		return "newpeople/list";
	}
	
	/**
	 * 课件 详情 
	 * @param request
	 * @param courseUrl
	 * @param title
	 * @param playCount
	 * @param lecturerName
	 * @param courseID
	 * @param courseImageUrl
	 * @return
	 */
//	@RequestMapping(value = "showpptDetail.page")
	public String queryCourseDetail(HttpServletRequest request,String courseUrl,String title,Integer playCount,String lecturerName,Integer courseID,String courseImageUrl) {
		   Integer userId = null;
	        //先判断是否登陆
	        try {
	            userId = WebUtils.getMemberId(request);
	        } catch (Exception e) {
	        	log.info("用户未登陆!");
	        }
	        
	    //课件vo    
	    NewPeoplePassVo newPeopleVO=new NewPeoplePassVo();
	    newPeopleVO.setId(courseID.longValue()); 
	    //获取课件 对应播放次数
	    NewPeoplePassVo newCourseVoUpdate=newPeopleService.selByPKgetCourse(newPeopleVO);
	    //更新课件点击次数
	    newPeopleService.updateNewPeople(newCourseVoUpdate);
	    
	  //获取课件 对应播放次数
	    NewPeoplePassVo newCourseVo=newPeopleService.selByPKgetCourse(newPeopleVO);
	     //判断是app外查看   
	    if(lecturerName==null&&playCount==null){
	    	//分享后获取的
	    	request.setAttribute("shareVal", "shareVal"); //标记是分享的 
	    }
		//课件详情
		request.setAttribute("newCourseVo", newCourseVo); 
		
		//详情页
		return "newpeople/pptdemo";
	}
	
	/**
	 * 冠军说 ppt页
	 * @param request
	 * @param showName
	 * @param courseID
	 * @return
	 */
//	@RequestMapping(value = "showpptChampion.page")
	public String queryCourseChampion(HttpServletRequest request,String showName,Integer courseID) {
	        
	    //课件vo    
	    NewPeoplePassVo newPeopleVO=new NewPeoplePassVo();
	    newPeopleVO.setId(courseID.longValue()); 
	    //获取课件 对应播放次数
	    NewPeoplePassVo newCourseVo=newPeopleService.selByPKgetCourse(newPeopleVO);
	    //更新点击次数
	    newPeopleService.updateNewPeople(newCourseVo);
	     //判断是app外查看   
	    if(showName==null){ 
	    	//分享后获取的
	    	request.setAttribute("shareVal", "shareVal"); //标记是分享的 
	    }
		//课件详情
		request.setAttribute("newCourseVo", newCourseVo); 
		
		//详情页
		return "newpeople/championppt";
	}
	
	/**
	 * @Title: 获取视频详情
	 * @Description: 获取视频明细
	 * @param request
	 * @param fid
	 * @return
	 */
//	@RequestMapping(value = "videoDetail.page")
	public String videoDetail(HttpServletRequest request, Long fid,Integer playCount,Long id) {
		log.debug("获取视频明细，视频主键={}", fid);
		Integer userId = null;
		try {
			userId = WebUtils.getMemberId(request);
		} catch (Exception e) {
			log.info("用户没有登录");
		}
		lookVideoDetail(request, fid, userId);
		championVideoService.updateVideoBrowserNum(fid); //更新视频浏览次数
		NewPeoplePassVo newPeopleVO=newPeopleService.selOneNewpeopleVD(id);
		if(newPeopleVO!=null)request.setAttribute("viewPlayCount", newPeopleVO.getPlayCount()); //显示浏览次数  
		return "newpeople/video_details";
	}
	
	public void lookVideoDetail(HttpServletRequest request, Long fid, Integer userId) {
		ChampionVideo video = championVideoService.queryChampionVideoByFid(fid);
		//request.setAttribute("authority",championVideoService.getVideoAuthorityVo(userId, video));
		request.setAttribute("video", video);
	}
	
	/**
	 * 新人通 展示1-5天列表页
	 * @param request
	 * @param day
	 * @param index
	 * @param pageSize
	 * @return
	 */
//	@RequestMapping(value = "showNewPeoIndex.page")
	public String queryNewPeopleIndex(HttpServletRequest request,@RequestParam(defaultValue = "1") Integer day, @RequestParam(defaultValue = "1") Integer index,
            @RequestParam(defaultValue = "50") Integer pageSize) {
		   Integer userId = null;
	        //先判断是否登陆
	        try {
	            userId = WebUtils.getMemberId(request);
	        } catch (Exception e) {
	        	log.info("用户未登陆!");
	        }
		//vo
		NewPeoplePassVo newPeopleVO=new NewPeoplePassVo();
		//初始化信息
	    List<NewPeoplePassVo> listinit=newPeopleService.selList(newPeopleVO);
		request.setAttribute("listinit",listinit); 
		if(!listinit.isEmpty()) request.setAttribute("vdOneInfo",listinit.get(0)); //单个信息
		
		return "newpeople/listIndex";
	}
}
