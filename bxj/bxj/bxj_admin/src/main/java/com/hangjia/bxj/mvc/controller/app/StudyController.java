package com.hangjia.bxj.mvc.controller.app;

import java.io.File;
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
import org.springframework.web.servlet.ModelAndView;

import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.model.Knowledge;
import com.hangjia.bxj.model.StudyDetail;
import com.hangjia.bxj.query.app.StudyDetailQuery;
import com.hangjia.bxj.service.study.IstudyService;

/**
 * 学习 模块
 * @ClassName: 
 * @Description: 
 * @author: he-Yi
 * @date: 2016年5月14日 下午2:08:52
 */
@Controller
@RequestMapping(value="/studyapp")
public class StudyController {	

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IstudyService studyService;
	
	
	@Value("${show_path}")
	private String showPath;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat, true));
    }
	
	/**
	 * 跳转学习知识页面 
	 * @return url
	 */
    @RequestMapping("konwList.jhtml")
    public String listKnow() {
    	
        return "app/konwList";
    }
    
    /**
     * 知识列表
     * @param query
     * @return
     */
    @RequestMapping("konwlist.json")
    public @ResponseBody Result listKnowAll(@ModelAttribute StudyDetailQuery query) {
    	Result result = new Result();
	    //查询 konw list 
		List<Knowledge> resultlist = studyService.queryKnowList(query,query.getCurrentPage(), query.getPageSize());
		result.setModel(resultlist);
    	//总条数 
    	query.setTotalItem(studyService.queryKnowtotal());
    	result.setQuery(query);
        return result;
    }
    
    /**
     * 下拉列表 知识 
     * @param query
     * @return
     */
    @RequestMapping("getAllkonwInfo.json")
    public @ResponseBody Result selKnowText(@ModelAttribute StudyDetailQuery query) {
    	Result result = new Result();
	    //查询 konw list  query.getPageSize()
		List<Knowledge> resultlist = studyService.queryKnowList(query,query.getCurrentPage(), 30);
		result.setModel(resultlist);
    	//总条数 
    	query.setTotalItem(studyService.queryKnowtotal());
    	result.setQuery(query);
        return result;
    }
    
    /**
     * 查询单个 知识
     * @param record
     * @return
     */
    @RequestMapping("selOneknow.json")
    public @ResponseBody Result selOneKnow(@ModelAttribute Knowledge record) {
    	Result result = new Result();
    	Knowledge know = studyService.selknowByPK(record.getId());
    	result.setModel(know);
        return result;
    }
    
     /**
      * 修改知识列表
      */
    @RequestMapping("updateKnow.json")
    public @ResponseBody Result updateknow(@ModelAttribute Knowledge record) {
    	Result result = new Result();
    	int num = studyService.updateKnowledge(record);
    	if(num != 1){
    		result.setSuccess(false);
    		logger.error("更新知识失败");
    	}
        return result;
    }
    
    /**
	 * 新增 知识
	 * @param query
	 * @return Result
	 */
    @RequestMapping("addKnow.json")
    public @ResponseBody Result addKnow(@ModelAttribute Knowledge record) {
    	Result result = new Result();
    	//更新 Knowledge 中 location 值  ，状态值
    	int insert = studyService.insertKnowledge(record);
    	if(insert != 1){
    		result.setSuccess(false);
    		logger.error("新增知识失败");
    	}
        return result;
    }
    
    /**
	 * 跳转学习详情页面
	 * @return url
	 */
    @RequestMapping("studyDetail.jhtml")
    public String listDetail() {
    	
        return "app/studyDetail";
    }
    
    /**
     * 查询单个 学习详情
     * @param record
     * @return
     */
    @RequestMapping("selOneDetail.json")
    public @ResponseBody Result selOneDetail(@ModelAttribute StudyDetailQuery record) {
    	Result result = new Result();
    	StudyDetail detail = studyService.selectByPK(record.getId());
    	result.setModel(detail);
        return result;
    }
    
    /**
     * 查询学习详情 
     * @param record
     * @param index
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "studyList.json")
    @ResponseBody
	public Result list( @RequestParam(defaultValue = "1") int index,
			@RequestParam(defaultValue = "10") Integer pageSize ,StudyDetailQuery query) {
    	Result result = new Result();
    	//查询  (query, query.getCurrentPage(), query.getPageSize());
		List<StudyDetail> studylist = studyService.queryPageData(query);
		result.setModel(studylist);
		query.setTotalItem(studyService.queryCount(query));
    	result.setQuery(query);
    	 // 
        return result;
	 
	}
    
    /**
	 * 新增 学习详情
	 * @param query
	 * @return Result
	 */
    @RequestMapping("addStudyDetail.json")
    public @ResponseBody Result addDetail(@ModelAttribute StudyDetail record,Integer knowID) {
    	Result result = new Result();
    	// 新增值
    	int num = studyService.insertStudyDetail(record,knowID);
    	if(num == 0){
    		result.setSuccess(false);
    		logger.error("新增学习详情失败");
    	}
        return result;
    }
    
    /**
     * 修改学习详情列表
     */
   @RequestMapping("updateStudyDetail.json")
   public @ResponseBody Result updateDetail(@ModelAttribute StudyDetail record,Integer knowID) {
   	Result result = new Result();
   	int num = studyService.updateByPKeyDetail(record,knowID);
   	if(num == 0){
   		result.setSuccess(false);
   		logger.error("更新学习详情失败");
   	}
       return result;
   }
   
   /**
    * 查询单个学习详情
    * @param query
    * @return
    */
   @RequestMapping("queryDetail.json")
   public @ResponseBody Result queryDetail(@ModelAttribute StudyDetailQuery query) {
   	Result result = new Result();
   	StudyDetail detail = studyService.selectByPK(query.getId());
   	if(null != detail){
   		detail.setFileUrl(showPath + File.separator + detail.getImgUrl()); 
		result.setModel(detail);
	} else {
		result.setSuccess(false);
		logger.error("查询 学习详情失败");
	}
       return result;
   }
   
}
