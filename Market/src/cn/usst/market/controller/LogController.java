package cn.usst.market.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.usst.market.po.Company;
import cn.usst.market.po.Log;
import cn.usst.market.po.LogVo;
import cn.usst.market.po.Page;
import cn.usst.market.po.Pager;
import cn.usst.market.service.LogService;

@Controller
public class LogController {
	@Autowired
	private LogService logService;
	
	@RequestMapping("/findMemberOperate.do")
	public @ResponseBody Pager findMemberOperate(HttpServletRequest request){
		String pageNow=request.getParameter("pageNowOperate");
		String companyId = request.getParameter("companyId");
		int currentQuarter = (int) request.getSession().getAttribute("currentQuarter");
		Page page = null;
		List<LogVo> logVoList = new ArrayList<LogVo>();
		Company company = new Company();
		company.setId(Integer.parseInt(companyId));
		Log log = new Log();
		log.setQuarter(currentQuarter);
		LogVo logVo = new LogVo();
		logVo.setCompany(company);
		logVo.setLog(log);
		int count = logService.getCount(logVo);
		
		if(pageNow != null&&!pageNow.equals("")){
			page = new Page(count, Integer.parseInt(pageNow));
			logVo.setStartPos(page.getStartPos());
			logVo.setPageSize(6);
			logVoList = logService.selectMemberOperateByPage(logVo);
	    } else {  
	        page = new Page(count, 1); 
	        logVo.setStartPos(page.getStartPos());
	        logVo.setPageSize(6);
	        logVoList = logService.selectMemberOperateByPage(logVo);
	    }  
		Pager pager = new Pager();
		pager.setCurrentPage(page.getPageNow());
		pager.setTotalRecord(count);
		pager.setDataList(logVoList);
		return pager;
	}
}
