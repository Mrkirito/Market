package com.hangjia.bxj.mvc.controller.log;

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
import com.hangjia.bxj.dao.log.SysLogMapper;
import com.hangjia.bxj.model.log.SysLogDO;
import com.hangjia.bxj.query.log.SysLogQuery;

/** 
* @author  作者 : yaoy
* @date 2016年5月9日 下午2:30:15 
* @version 1.0 
*/
@Controller
@RequestMapping("/log")
public class SysLogController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SysLogMapper sysLogMapper;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat, true));
    }
	
	/**
	 * 跳转页面
	 * @return url
	 */
    @RequestMapping("sysLog.jhtml")
    public String sysLog() {
        return "log/sysLog";
    }
    
	/**
	 * 查询操作日志
	 * @param query
	 * @return Result
	 */
    @RequestMapping("querySysLogList.json")
    public @ResponseBody Result querySysLogList(@ModelAttribute SysLogQuery query) {
    	Result result = new Result();
    	int count = sysLogMapper.queryPageDataCount(query);
    	if(count > 0){
    		List<SysLogDO> sysLogList = sysLogMapper.queryPageData(query);
    		result.setModel(sysLogList);
    	}
    	query.setTotalItem(count);
    	result.setQuery(query);
        return result;
    }
    
}
