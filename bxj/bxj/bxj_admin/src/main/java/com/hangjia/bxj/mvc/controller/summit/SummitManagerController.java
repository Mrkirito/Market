package com.hangjia.bxj.mvc.controller.summit;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.model.summit.ChampionSummitEntity;
import com.hangjia.bxj.model.summit.ChampionSummitListEntry;
import com.hangjia.bxj.model.summit.ClassroomEntity;
import com.hangjia.bxj.query.summit.SummitManagerQuery;

/**
 * 【峰会】即【冠军论坛】管理控制器。
 *
 */
@Controller
@RequestMapping("/summit")
public class SummitManagerController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SummitManagerService summitManagerService;
	
	@Autowired
	private ClassroomManagerService classroomManagerService;

	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat, true));
    }
	
	/**
	 * 峰会管理首页，列表页。
	 * @return
	 */
	@RequestMapping("index.jhtml")
	private Object showIndexPage() {
		return "summit/index";
	}
	
	/**
	 * 查询列表
	 * @param query
	 * @return
	 */
	@ResponseBody
	@RequestMapping("list.json")
	private Object listSummit(SummitManagerQuery query) {
		return summitManagerService.paginationQuery(query);
	}
	
	/**
	 * 查询单个峰会
	 * @param query
	 * @return
	 */
	@ResponseBody
	@RequestMapping("listOne.json")
	private Result lookOne(SummitManagerQuery query) {
	     Result result= summitManagerService.paginationQuery(query);
	     if(result.getModel()!=null){
	    	 List<ChampionSummitListEntry> list =(List<ChampionSummitListEntry>) result.getModel();
	    	 result.setModel(list.get(0));
	    	 result.setSuccess(true);
	     } else {
	    		result.setSuccess(false);
	    		logger.error("查询冠军论坛峰会失败");
	    }
		 return result;
	}
	
	/**
	 * 保存修改峰会
	 * @param summit
	 * @return
	 */
	@RequestMapping("saveOrUpdate.jhtml")  
	private @ResponseBody Result doSummitSaveOrUpdate(ChampionSummitEntity summit) { // MultipartFile image
		Result result = new Result();
		Integer id=summitManagerService.saveOrUpdate(summit);
		if(id!=null){
			result.setSuccess(true);
			logger.error("新增冠军论坛峰会成功");
		}else{
			result.setSuccess(false);
			logger.error("新增冠军论坛峰会失败");
		}
		return result;
	}
	
	/*
	 * 【峰会】功能结束
	 * =============================================
	 * 
	 * 【峰会讲师】功能开始
	 */
	
	/**
	 * 峰会管理首页，列表页。
	 * @return
	 */
	@RequestMapping("indexTeacher.jhtml")
	private Object showIndexTeacher() {
		return "summit/indexTeacher";
	}
	
	/**
	 * 查询所有讲师
	 * @param query
	 * @return
	 */
	@ResponseBody
	@RequestMapping("listTeachers.json")
	private Object listTeachers(SummitManagerQuery query) {
		return classroomManagerService.paginationQueryTeachers(query);
	}
	
	/**
	 * 保存 、修改讲师
	 * @param classRoom
	 * @return  
	 */
	@ResponseBody
	@RequestMapping(value="saveOrUpdateTeacher.jhtml")
	private Result doClassroomSaveOrUpdate(ClassroomEntity classRoom , String imgsInput) {
			JSONArray jsonArr=JSON.parseArray(imgsInput); 
			if(jsonArr !=null &&!jsonArr.isEmpty()){
				String str[]=new String[jsonArr.size()];
				for (int i = 0; i < jsonArr.size(); i++) {
						str[i]=(String) jsonArr.get(i); 
				}
			   	classRoom.setImages(str);
			}
		   	
			Result result = new Result();
			Integer id=classroomManagerService.saveOrUpdateTeacher(classRoom);
			if(id!=null){
				result.setSuccess(true);
				logger.error("新增峰会讲师成功");
			}else{
				result.setSuccess(false);
				logger.error("新增冠军论坛峰会讲师失败");
			}
			return result;
		}
		
	/**
	 * 查询单个讲师
	 * @param query
	 * @return
	 */
	@ResponseBody
	@RequestMapping("listOneTeacher.json")
	private Object lookOneTeacher(SummitManagerQuery query) {
		 Result<ClassroomEntity> result=new Result<ClassroomEntity>();
		 ClassroomEntity entity= classroomManagerService.queryOneTeachers(query);
	     if(entity!=null){
	    	 result.setModel(entity);
	    	 result.setSuccess(true);
	     } else {
	    		result.setSuccess(false);
	    		logger.error("查询峰会讲师失败");
	    }
		 return result;
	}
	
}
