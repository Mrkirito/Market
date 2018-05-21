package com.hangjia.bxj.mvc.controller.report;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.dao.AppExtendDataMapper;
import com.hangjia.bxj.dao.AppExtendDetailDataMapper;
import com.hangjia.bxj.model.AppExtendData;
import com.hangjia.bxj.model.AppExtendDetailData;
import com.hangjia.bxj.mvc.aop.annotation.MethodLog;
import com.hangjia.bxj.mvc.common.BaseModule;
import com.hangjia.bxj.query.report.UserDataReportQuery;

@Controller
@RequestMapping("/extend")
public class ExtendDataReportController extends BaseModule {
	
	@Autowired
	private AppExtendDataMapper appExtendDataMapper;
	
	@Autowired
	private AppExtendDetailDataMapper appExtendDetailDataMapper;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat, true));
    }

	@MethodLog(remark = "推广统计")
	@RequestMapping("saveData.json")
	public @ResponseBody Result saveData(AppExtendData data) {
		Result result = new Result();    	
    	int exist = appExtendDataMapper.existDataByDay(data);
    	if(exist >= 1){
    		result.setSuccess(false);
    		result.setMsg("当前的统计时间已经有数据，请校验。");
    		return result;
    	}
		data.setCreateTime(new Date());
		data.setCreateName(getLoginUserName());
		int i=appExtendDataMapper.insertSelective(data);
		if(i != 1){
			result.setSuccess(false);
			result.setMsg("新增统计数据失败");
		}    	
		return result;
	}
	
	
	@RequestMapping("extendDataReport2.jhtml")
    public ModelAndView extendDataReportAdd2() {
		ModelAndView modelAndView = new ModelAndView("report/extendDataReportAdd2");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
		String date=dateFormat.format(new Date());
		modelAndView.addObject("osDate", date);
        return modelAndView;
    }
	
	
    @RequestMapping("extendDataReportAdd.jhtml")
    public ModelAndView extendDataReportAdd() {
		ModelAndView modelAndView = new ModelAndView("report/extendDataReportAdd");
        return modelAndView;
    }	
	
	@RequestMapping("getDataById.json")
	public @ResponseBody Result getDataById(Long id) {
		Result result = new Result();
		AppExtendData data = appExtendDataMapper.selectByPrimaryKey(id);
		result.setModel(data);
		return result;
	}
	@MethodLog(remark = "推广统计更改")
	@RequestMapping("updateData.json")
	public @ResponseBody Result updateData(AppExtendData data) {
		Result result = new Result();
    	int exist = appExtendDataMapper.existDataByDay(data);
    	if(exist >= 1){
    		result.setSuccess(false);
    		result.setMsg("当前的统计时间已经有数据，请校验。");
    		return result;
    	}
		data.setUpdateName(getLoginUserName());
		data.setUpdateTime(new Date());
		int i=appExtendDataMapper.updateByPrimaryKey(data);
		if(i != 1){
			result.setSuccess(false);
			result.setMsg("新增统计数据失败");
		}  
		return result;
	}
	
    @RequestMapping("extendDataReport.jhtml")
    public ModelAndView voucherReport() {
		ModelAndView modelAndView = new ModelAndView("report/extendDataReport");
        return modelAndView;
    }
    
    @RequestMapping("queryExtendDataReportList.json")
    public @ResponseBody Result queryExtendDataReportList(AppExtendData query) {
		Result result = new Result();
		int count = appExtendDataMapper.queryCount(query);
		if (count > 0) {
			List<AppExtendData> list = appExtendDataMapper.queryData(query);
			result.setModel(list);
		}
    	query.setTotalItem(count);
    	result.setQuery(query);
        return result;
    }
    
    
    @RequestMapping("queryExtendDataReportList2.json")
    public @ResponseBody Result queryExtendDataReportList2(String date) {
		Result result = new Result();
		try {
			List<AppExtendDetailData> list =appExtendDetailDataMapper.getDataList(date);
			List<AppExtendData> dataList = appExtendDataMapper.queryAllData(date);
			Class clazz = AppExtendDetailData.class;
			Class clazz2 = AppExtendData.class;
			for (AppExtendDetailData appExtendDetailData : list) {
				String field = appExtendDetailData.getFiled();
				for (int i = 0; i < dataList.size(); i++) {
					AppExtendData appExtendData = dataList.get(i);
					SimpleDateFormat dateFormat=new SimpleDateFormat("d");
					Method method=clazz.getMethod("setColumn"+dateFormat.format(appExtendData.getDataTime()), Integer.class);
					method.setAccessible(true);
					Method method2=clazz2.getMethod("get" + field);
					Object value=method2.invoke(appExtendData);
					method.invoke(appExtendDetailData, value); 
				}
			}
			result.setModel(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return result;
    }
    
    
    
    @RequestMapping("queryEchartsData.json")
    public @ResponseBody Result queryEchartsData(@ModelAttribute AppExtendData query) {
    	Result result = new Result();
    	List<AppExtendData> data = appExtendDataMapper.queryEchartsData(query);
    	result.setModel(data);
        return result;
    }

    @RequestMapping("queryEchartsDataByMonth.json")
    public @ResponseBody Result queryEchartsDataByMonth(String date) {
    	Result result = new Result();
    	List<AppExtendData> data = appExtendDataMapper.queryAllData(date);
    	result.setModel(data);
        return result;
    }
    
}
