package com.hangjia.bxj.mvc.controller.report;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hangjia.bxj.mvc.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.hangjia.bxj.dao.AdminReportMapper;
import com.hangjia.bxj.excel.view.ViewExcel;
import com.hangjia.bxj.model.report.UserDataReport;
import com.hangjia.bxj.mvc.common.BaseModule;
import com.hangjia.bxj.query.champion.ChampionVideoQuery;
import com.hangjia.bxj.query.report.UserDataReportQuery;

/** 
* @author  作者 : yaoy
* @date 2016年5月9日 下午2:30:15 
* @version 1.0 
*/
@Controller
@RequestMapping("/report")
public class UserDataReportController extends BaseModule {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private AdminReportMapper adminReportMapper;
	
	@InitBinder
    protected void initBinder1(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat, true));
    }
	
	/**
	 * 跳转页面
	 * @return url
	 */
    @RequestMapping("userDataReport.jhtml")
    @ResponseBody
    public ModelAndView voucherReport(ModelAndView modelAndView) {
    	UserDataReport report = new UserDataReport();
    	UserDataReport summary = adminReportMapper.queryUserDataSummary();
    	UserDataReport yesterday = adminReportMapper.queryUserDataYesterday();
		UserDataReport avg = new UserDataReport();
		if(DateUtils.isMonthFirst()){
			avg = adminReportMapper.queryUserDataAvg2();
		} else {
			avg = adminReportMapper.queryUserDataAvg();
		}
    	// 总数
    	if(null != summary){
    		report.setNewSum(summary.getNewSum());
    	}
    	// 昨日
    	if(null != yesterday){
    		report.setNewSumYesterday(yesterday.getNewSumYesterday());
        	report.setActiveSumYesterday(yesterday.getActiveSumYesterday());
        	report.setTimesAvgYesterday(yesterday.getTimesAvgYesterday());
    	}
    	// 月平均
    	if(null != avg){
    		report.setNewAvgMonth(avg.getNewAvgMonth());
    		report.setActiveAvgMonth(avg.getActiveAvgMonth());
    	}
    	modelAndView.addObject("userData", report);
    	modelAndView.setViewName("report/userDataReport");
        return modelAndView;
    }
	/**
	 * 跳转页面
	 * @return url
	 */
	@RequestMapping("userDataBbReport.jhtml")
	@ResponseBody
	public ModelAndView userDataBbReport(ModelAndView modelAndView) {
		UserDataReport report = new UserDataReport();
		UserDataReport summary = adminReportMapper.queryUserDataSummary();
		UserDataReport yesterday = adminReportMapper.queryUserDataYesterday();
		UserDataReport avg = new UserDataReport();
		if(DateUtils.isMonthFirst()){
			avg = adminReportMapper.queryUserDataAvg2();
		} else {
			avg = adminReportMapper.queryUserDataAvg();
		}
		// 总数
		if(null != summary){
			report.setNewSum(summary.getNewSum());
		}
		// 昨日
		if(null != yesterday){
			report.setNewSumYesterday(yesterday.getNewSumYesterday());
			report.setActiveSumYesterday(yesterday.getActiveSumYesterday());
			report.setTimesAvgYesterday(yesterday.getTimesAvgYesterday());
		}
		// 月平均
		if(null != avg){
			report.setNewAvgMonth(avg.getNewAvgMonth());
			report.setActiveAvgMonth(avg.getActiveAvgMonth());
		}
		modelAndView.addObject("userData", report);
		modelAndView.setViewName("report/userDataBbReport");
		return modelAndView;
	}
	/**
	 * 跳转页面
	 * @return url
	 */
	@RequestMapping("userDataHjReport.jhtml")
	@ResponseBody
	public ModelAndView userDataHjReport(ModelAndView modelAndView) {
		UserDataReport report = new UserDataReport();
		UserDataReport summary = adminReportMapper.queryUserDataSummary();
		UserDataReport yesterday = adminReportMapper.queryUserDataYesterday();
		UserDataReport avg = new UserDataReport();
		if(DateUtils.isMonthFirst()){
			avg = adminReportMapper.queryUserDataAvg2();
		} else {
			avg = adminReportMapper.queryUserDataAvg();
		}
		// 总数
		if(null != summary){
			report.setNewSum(summary.getNewSum());
		}
		// 昨日
		if(null != yesterday){
			report.setNewSumYesterday(yesterday.getNewSumYesterday());
			report.setActiveSumYesterday(yesterday.getActiveSumYesterday());
			report.setTimesAvgYesterday(yesterday.getTimesAvgYesterday());
		}
		// 月平均
		if(null != avg){
			report.setNewAvgMonth(avg.getNewAvgMonth());
			report.setActiveAvgMonth(avg.getActiveAvgMonth());
		}
		modelAndView.addObject("userData", report);
		modelAndView.setViewName("report/userDataHjReport");
		return modelAndView;
	}

    
	/**
	 * 跳转页面
	 * @return url
	 */
    @RequestMapping("userDataAdd.jhtml")
    public String userDataAdd() {
        return "report/userDataAdd";
    }
    
	/**
	 * 用户数据统计列表
	 * @param query
	 * @return Result
	 */
    @RequestMapping("queryUserDataReportList.json")
    public @ResponseBody Result queryUserDataReportList(@ModelAttribute UserDataReportQuery query, HttpServletRequest request) {
    	Result result = new Result();
    	int count = adminReportMapper.queryUserDataReportCount(query);
    	if(count > 0){
    		List<UserDataReport> reportList = adminReportMapper.queryUserDataReportList(query);
    		result.setModel(reportList);
    	}
    	query.setTotalItem(count);
    	result.setQuery(query);
        return result;
    }
    
    /**
	 * 新增用户统计数据
	 * @param userDataReport
	 * @return Result
	 */
    @RequestMapping("addUserData.json")
    public @ResponseBody Result addUserData(UserDataReport userDataReport) {
    	Result result = new Result();
    	
    	UserDataReportQuery query = new UserDataReportQuery();
    	query.setDataTime(userDataReport.getDataTime());
    	
    	int exist = adminReportMapper.existUserDataByDay(query);
    	if(exist >= 1){
    		result.setSuccess(false);
    		result.setMsg("当前的统计时间已经有数据，请校验。");
    		return result;
    	}
    	
    	userDataReport.setCreateName(getLoginUserName());
    	int insert = adminReportMapper.addUserData(userDataReport);
    	if(insert != 1){
    		result.setSuccess(false);
    		result.setMsg("新增用户统计数据失败");
    	}
        return result;
    }
    
    /**
	 * 折线图数据
	 * @param query
	 * @return Result
	 */
    @RequestMapping("queryEchartsData.json")
    public @ResponseBody Result queryEchartsData(UserDataReportQuery query) {
    	Result result = new Result();
    	List<UserDataReport> data = adminReportMapper.queryEchartsData(query);
    	result.setModel(data);
        return result;
    }

	/**
	 * 折线图数据
	 * @param query
	 * @return Result
	 */
	@RequestMapping("queryVideoEchartsData.json")
	public @ResponseBody Result queryVideoEchartsData(UserDataReportQuery query) {
		Result result = new Result();
		List<UserDataReport> data = adminReportMapper.queryVideoEchartsData(query);
		result.setModel(data);
		return result;
	}
    
    /** 
     * 导出Excel 
     * @param model 
     * @param query
     * @return
     */  
    @RequestMapping("exportExcel.json")  
     public ModelAndView exportExcel(ModelMap model, @ModelAttribute UserDataReportQuery query){  
    	int count = adminReportMapper.queryUserDataReportCount(query);
    	query.setTotalItem(count);
    	ViewExcel viewExcel = new ViewExcel("用户数据统计", getLoginUserId().longValue(), query, adminReportMapper, "queryUserDataReportList", "adminReportMapper");
        return new ModelAndView(viewExcel, model);   
    }
    
    /**
	 * 修改用户统计数据
	 * @param userDataReport
	 * @return Result
	 */
    @RequestMapping("updateUserData.json")
    public @ResponseBody Result updateUserData(UserDataReport userDataReport) {
    	Result result = new Result();
    	
    	UserDataReportQuery query = new UserDataReportQuery();
    	query.setId(userDataReport.getId());
    	query.setDataTime(userDataReport.getDataTime());
    	
    	int exist = adminReportMapper.existUserDataByDay(query);
    	if(exist >= 1){
    		result.setSuccess(false);
    		result.setMsg("当前的统计时间已经有数据，请校验。");
    		return result;
    	}
    	
    	userDataReport.setUpdateName(getLoginUserName());
    	int update = adminReportMapper.updateUserData(userDataReport);
    	if(update != 1){
    		result.setSuccess(false);
    		result.setMsg("修改用户统计数据失败");
    	}
        return result;
    }
}
