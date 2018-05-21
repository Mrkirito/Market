package com.hangjia.summit.mvc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.model.summit.ClassroomEntity;
import com.hangjia.bxj.query.summit.SummitManagerQuery;
import com.hangjia.summit.service.ISummitService;

/**
 * 【冠军论坛峰会】 
 *
 */
@Controller
@RequestMapping("/summit")
public class SummitController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ISummitService summitService; 
	
	/**
	 * 查询 峰会 列表
	 * @param query
	 * @return
	 */
	@RequestMapping("summitlist.page")
	private Object listSummit(HttpServletRequest request) {
		return "summit/champion_index";
	}
	
	@ResponseBody
	@RequestMapping("getSummitlist.json")
	private Object listSummitJson(HttpServletRequest request, @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize) {
		SummitManagerQuery query=new SummitManagerQuery();
//		query.setPageSize(pageSize);
		query.setCurrentPage(page);
		Result result=summitService.querySummitList(query);
		return result;
	}
	
	/**
	 * 查询所有讲师
	 * @param query
	 * @return
	 */
	@RequestMapping("listTeachers.page")
	private Object listTeachers(HttpServletRequest request,Integer summitId, @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "20") Integer pageSize) {
		SummitManagerQuery query=new SummitManagerQuery();
		query.setId(summitId);
		query.setCurrentPage(page);
		query.setPageSize(pageSize);
		List<ClassroomEntity> list=summitService.paginationQueryTeachers(query);
		request.setAttribute("summitClassList",list);
		//获取峰会名
		if(!list.isEmpty())request.setAttribute("summitName",list.get(0).getSummitName());
		return "summit/champion_teacher";
	}
	
	/**
	 * 查询单个讲师 ppt信息
	 * @param query
	 * @return
	 */
	@RequestMapping("oneTeacher.page")
	private Object lookOneTeacher(Integer classId,HttpServletRequest request) {
		  SummitManagerQuery query=new SummitManagerQuery();
		  query.setId(classId);
		  ClassroomEntity entity= summitService.queryOneTeachers(query);
		  //更新次数
		  Integer num=summitService.updateTeacher(entity);
			if(num!=null){
				logger.error("冠军论坛峰会更新次数成功");
			}else{
				logger.error("冠军论坛峰会更新次数失败");
			}
		 request.setAttribute("summitClass",entity);
		return "summit/championppt";
	}
	
	/**
	 * 获取ppt图片集合
	 * @param classId
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getImgs.json")
	private Object getImgsInfo(Integer classId,HttpServletRequest request) {
		  SummitManagerQuery query=new SummitManagerQuery();
		  query.setId(classId);
		  Result<ClassroomEntity> result =new Result<ClassroomEntity>();
		  ClassroomEntity entity= summitService.getImgArray(query);
		  result.setModel(entity); 
		return result;
	}
	
	/**
	 * 分享 
	 * 查询单个讲师 ppt信息
	 * @param query
	 * @return
	 */
	@RequestMapping("shareTeacher.page")
	private Object shareOneTeacher(Integer classId,HttpServletRequest request) {
		  SummitManagerQuery query=new SummitManagerQuery();
		  query.setId(classId);
		  ClassroomEntity entity= summitService.queryOneTeachers(query);
		  //更新次数
		  Integer num=summitService.updateTeacher(entity);
			if(num!=null){
				logger.error("冠军论坛峰会更新次数成功");
			}else{
				logger.error("冠军论坛峰会更新次数失败");
			}
		 request.setAttribute("summitClass",entity);
		return "summit/share_championppt";
	}
	
}
