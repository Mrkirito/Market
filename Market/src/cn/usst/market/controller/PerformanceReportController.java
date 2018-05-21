package cn.usst.market.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.usst.market.po.BalanceSheet;
import cn.usst.market.po.CompanyCapacity;
import cn.usst.market.po.CompanyProduct;
import cn.usst.market.po.FinancialRatio;
import cn.usst.market.po.FinancialRatioAvg;
import cn.usst.market.po.FinancialRatioMax;
import cn.usst.market.po.FinancialRatioMin;
import cn.usst.market.po.FinancialRatioVo;
import cn.usst.market.po.IdQuarter;
import cn.usst.market.po.IncomeStatement;
import cn.usst.market.po.PerformanceReport;
import cn.usst.market.service.BalanceScoreService;
import cn.usst.market.service.CompanyService;
import cn.usst.market.service.CompetitionResultService;
import cn.usst.market.service.PerformanceReportService;

@Controller
public class PerformanceReportController {
	@Autowired
	private PerformanceReportService performanceReportService;

	@Autowired
	private CompanyService companyService;
	
	/*@Autowired
	private BalanceScoreService balanceScoreService;*/
	
	@RequestMapping("/showCompanyMarketShare.do")
	public String showCompanyMarketShare(HttpServletRequest request){
		int companyId = (int) request.getSession().getAttribute("companyId");
		int competitionId = companyService.selectCompanyById(companyId).getCompetitionId();
		int quarter = Integer.parseInt(request.getParameter("quarter"));
		
		List<PerformanceReport> list = performanceReportService.findCompanyMarketShareByCompetetionId(competitionId, quarter-1);
		request.setAttribute("companyMarketShare", list);
		return "companyMarketShare";
	}
	
	@RequestMapping("/showTotalSale.do")
	public String showTotalSale(HttpServletRequest request){
		int companyId = (int) request.getSession().getAttribute("companyId");
		int competitionId = companyService.selectCompanyById(companyId).getCompetitionId();
		int quarter = Integer.parseInt(request.getParameter("quarter"));
		List<PerformanceReport> list = performanceReportService.findCompanyMarketShareByCompetetionId(competitionId, quarter-1);
		request.setAttribute("companyMarketShare", list);
		return "totalSalesReport";
	}
	
	@RequestMapping("/showProductMarketShare.do")
	public String showProductMarketShare(HttpServletRequest request){
		int companyId = (int) request.getSession().getAttribute("companyId");
		int competitionId = companyService.selectCompanyById(companyId).getCompetitionId();
		int quarter = Integer.parseInt(request.getParameter("quarter"));
		List<PerformanceReport> list = performanceReportService.findCompanyProductMarketShareByCompetionId(competitionId, quarter-1);
		request.setAttribute("productMarketShare", list);
		return "brandDemandReport";
	}
	
	@RequestMapping("/showFinancialRatio.do")
	public String showFinancialRatio(HttpServletRequest request){
		int companyId = (int) request.getSession().getAttribute("companyId");
		int quarter = Integer.parseInt(request.getParameter("quarter"));
		int competitionId = companyService.selectCompanyById(companyId).getCompetitionId();
		FinancialRatioVo financialRatioVo = performanceReportService.findFinancialRatioByCompanyId(companyId, quarter-1);
		FinancialRatioMax financialRatioMax = performanceReportService.getMax(competitionId, quarter-1);
		FinancialRatioMin financialRatioMin = performanceReportService.getMin(competitionId, quarter-1);
		FinancialRatioAvg financialRatioAvg = performanceReportService.getAvg(competitionId, quarter-1);
		financialRatioVo.setFinancialRatioAvg(financialRatioAvg);
		financialRatioVo.setFinancialRatioMax(financialRatioMax);
		financialRatioVo.setFinancialRatioMin(financialRatioMin);
		request.setAttribute("financialRatioVo", financialRatioVo);
		return "financialRatio";
	}
}
