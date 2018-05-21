package com.hangjia.newpeople.mvc.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hangjia.bxj.common.Pagination;
import com.hangjia.bxj.mvc.AjaxResult;
import com.hangjia.bxj.vo.NewPeoplePassVo;
import com.hangjia.newpeople.mvc.NewPeopleController;
import com.hangjia.newpeople.service.InewPeopleService;

@Controller
//@RequestMapping("newPeopleClient")
public class NewPeopleClientController {

	private static Logger log = LoggerFactory.getLogger(NewPeopleController.class);
	@Autowired
	InewPeopleService newPeopleService;
	
//	@RequestMapping(value = "qryNewPeopleInit.json" , method = RequestMethod.GET)
	@ResponseBody
	public Object queryInit(HttpServletRequest request, @RequestParam(defaultValue = "1") Integer index,
            @RequestParam(defaultValue = "5") Integer pageSize) {
		   Map<String, Object> respMap = new HashMap<String, Object>();
		 /*  Integer userId = null;
	        //先判断是否登陆
	        try {
	            userId = WebUtils.getMemberId(request);
	        } catch (Exception e) {
	        	log.info("用户未登陆!");
	        }*/
		//vo
		NewPeoplePassVo newPeopleVO=new NewPeoplePassVo();
		//初始化信息
	    List<NewPeoplePassVo> listinit=newPeopleService.selList(newPeopleVO);
		respMap.put("listinit",new Pagination<NewPeoplePassVo>(5, index, pageSize, listinit)); //初始化的  1-5天中 各天课程信息

		//		log.info("查询新人通初始化信息个数:"+listinit.size());
//		if(!listinit.isEmpty()) respMap.put("newPerOneInfo",listinit.get(0)); //保存名称：  视频列表 课件列表  
		
		//课件列表 信息
//		respMap.put("courseList",newPeopleService.selListNewPeoCourse(newPeopleVO, index, pageSize)); 
		
		 return new AjaxResult.success(respMap);
	}
	
//	@RequestMapping(value = "qryNewPerList.json" , method = RequestMethod.GET)
	@ResponseBody
	public Object queryNewPeopleList(HttpServletRequest request,@RequestParam(defaultValue = "1") Integer day, @RequestParam(defaultValue = "1") Integer index,
            @RequestParam(defaultValue = "36") Integer pageSize) {
		   Map<String, Object> respMap = new HashMap<String, Object>();
		//vo
		NewPeoplePassVo newPeopleVO=new NewPeoplePassVo();
		//newPeopleVO.setUserId(userId.longValue()); //用户id 初始化不需要
		//newPeopleVO.setDay(day); //第几天 
		//视频列表 
		/* List<NewPeoplePassVo> listvds=newPeopleService.selListNewPeopleVD(newPeopleVO, index, pageSize);
		respMap.put("vdList",listvds);*/
		List<NewPeoplePassVo> listcourse= newPeopleService.selListNewPeoCourse(newPeopleVO, index, pageSize);
		
		Pagination<NewPeoplePassVo> pageInfo=new Pagination<NewPeoplePassVo>(66, index, pageSize, listcourse);
		//课件列表
		respMap.put("courseList",pageInfo); 
		
		return new AjaxResult.success(respMap);
	}
}
