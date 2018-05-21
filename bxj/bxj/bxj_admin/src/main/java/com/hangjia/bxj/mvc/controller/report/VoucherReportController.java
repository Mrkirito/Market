package com.hangjia.bxj.mvc.controller.report;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.dao.AdminReportMapper;
import com.hangjia.bxj.model.report.VoucherReport;
import com.hangjia.bxj.query.report.VoucherReportQuery;

/** 
* @author  作者 : yaoy
* @date 2016年5月9日 下午2:30:15 
* @version 1.0 
*/
@Controller
@RequestMapping("/report")
public class VoucherReportController {

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
    @RequestMapping("voucherReport.jhtml")
    public String voucherReport() {
        return "report/voucherReport";
    }
    
	/**
	 * 查询视频券统计列表
	 * @param query
	 * @return Result
	 */
    @RequestMapping("queryVoucherReportList.json")
    public @ResponseBody Result queryVoucherReportList(@ModelAttribute VoucherReportQuery query) {
    	Result result = new Result();
    	int count = adminReportMapper.queryVoucherReportCount(query);
    	if(count > 0){
    		List<VoucherReport> reportList = adminReportMapper.queryVoucherReportList(query);
    		result.setModel(reportList);
    	}
    	query.setTotalItem(count);
    	result.setQuery(query);
        return result;
    }
    
}
