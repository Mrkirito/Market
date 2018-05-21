package com.hangjia.bxj.mvc.controller.online;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.model.online.SalesOnlineCourse;
import com.hangjia.bxj.model.online.SalesOnlineCourseQuery;
import com.hangjia.bxj.service.online.IOnlineService;

@Controller
@RequestMapping("/online")
public class OnlineController {
	@Autowired
	private IOnlineService service;
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat, true));
    }
    @RequestMapping("list.jhtml")
    public String list(){
        return "online/listOnline";
    }
    
	@RequestMapping("getOnlineCourseData.json")
	public @ResponseBody Result getOnlineCourseData(SalesOnlineCourseQuery query) {
		return service.getResultByPage(query);
	}
	
	@RequestMapping("getOnlineCourse.json")
	public @ResponseBody Result getSalesOnlineCourseById(Long id) {
		Result result=new Result();
		result.setModel(service.getSalesOnlineCourseById(id));
		return result;
	}
	
    @RequestMapping("updateOnlineCourse.json")
    public @ResponseBody Result updateOnlineCou(SalesOnlineCourse course){
    	return service.updateSalesOnlineCourse(course);
    }
    
    @RequestMapping("insertOnlineCourse.json")
    public @ResponseBody Result insertOnlineCou(SalesOnlineCourse course){
    	return service.insertSalesOnlineCourse(course);
    }    
    @RequestMapping("queryLectures")
    public @ResponseBody Result queryLectures(){
    	return service.queryLectures();
    }
}
