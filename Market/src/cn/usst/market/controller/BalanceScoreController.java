package cn.usst.market.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.usst.market.annotation.MethodLog;
import cn.usst.market.po.BalanceScore;
import cn.usst.market.po.BalanceScoreAvgData;
import cn.usst.market.po.BalanceScoreMaxData;
import cn.usst.market.po.BalanceScoreMinData;
import cn.usst.market.po.BalanceScoreVo;
import cn.usst.market.po.BalanceSheet;
import cn.usst.market.po.CashFlow;
import cn.usst.market.po.Company;
import cn.usst.market.po.CompanyInvestment;
import cn.usst.market.po.CompanyMarketShare;
import cn.usst.market.po.CompanyMedia;
import cn.usst.market.po.CompanyMediaVo;
import cn.usst.market.po.Competition;
import cn.usst.market.po.HirePeople;
import cn.usst.market.po.HirePeopleOnline;
import cn.usst.market.po.IdQuarter;
import cn.usst.market.po.IncomeStatement;
import cn.usst.market.po.LearnTime;
import cn.usst.market.po.LearnTimeVo;
import cn.usst.market.po.SalesSalary;
import cn.usst.market.service.BalanceScoreService;
import cn.usst.market.service.CompanyService;
import cn.usst.market.service.CompetitionResultService;
import cn.usst.market.service.CompetitionService;
import cn.usst.market.service.PerformanceReportService;

@Controller
public class BalanceScoreController {
	@Autowired
	private BalanceScoreService balanceScoreService;
	
	@Autowired
	private CompetitionService competitionService;
	
	@Autowired
	private CompetitionResultService competitionResultService;
	
	@Autowired
	private PerformanceReportService performanceReportService;
	
	@Autowired
	private CompanyService companyService;
	
	@RequestMapping("/loadBalanceScore.do")
	public String loadBalanceScore(HttpServletRequest request, String companyId, String competitionId){
		BalanceScore balanceScore = new BalanceScore();
		int quarter = (int) request.getSession().getAttribute("currentQuarter") - 1;
		balanceScore.setCompanyId(Integer.parseInt(companyId));
		balanceScore.setQuarter(quarter);
		BalanceScoreMinData balanceScoreMinData = new BalanceScoreMinData();
		balanceScoreMinData = balanceScoreService.getMinData(Integer.parseInt(competitionId), quarter);
		BalanceScoreMaxData balanceScoreMaxData = new BalanceScoreMaxData();
		balanceScoreMaxData = balanceScoreService.getMaxData(Integer.parseInt(competitionId), quarter);
		BalanceScoreAvgData balanceScoreAvgData = new BalanceScoreAvgData();
		balanceScoreAvgData = balanceScoreService.getAvgData(Integer.parseInt(competitionId), quarter);
		BalanceScoreVo balanceScoreVo = balanceScoreService.loadBalanceScore(balanceScore); 
		balanceScoreVo.setBalanceScoreMinData(balanceScoreMinData);
		balanceScoreVo.setBalanceScoreMaxData(balanceScoreMaxData);
		balanceScoreVo.setBalanceScoreAvgData(balanceScoreAvgData);
		request.setAttribute("balanceScoreVo", balanceScoreVo);
		return "balance_score";
	}
	
	@RequestMapping("/loadBalanceScoreReport.do")
	public String loadBalanceScoreReport(HttpServletRequest request){
		int companyId = (int) request.getSession().getAttribute("companyId");
		int quarter = Integer.parseInt(request.getParameter("quarter")) - 1;
		Competition competition = competitionService.findCompetitionByCompanyId(companyId);
		BalanceScore balanceScore = new BalanceScore();
		balanceScore.setCompanyId(companyId);
//		balanceScore.setQuarter(competition.getCurrentQuarter() - 1);
		balanceScore.setQuarter(quarter);
		BalanceScoreMinData balanceScoreMinData = new BalanceScoreMinData();
		balanceScoreMinData = balanceScoreService.getMinData(competition.getId(), quarter);
		BalanceScoreMaxData balanceScoreMaxData = new BalanceScoreMaxData();
		balanceScoreMaxData = balanceScoreService.getMaxData(competition.getId(), quarter);
		BalanceScoreAvgData balanceScoreAvgData = new BalanceScoreAvgData();
		balanceScoreAvgData = balanceScoreService.getAvgData(competition.getId(), quarter);
		BalanceScoreVo balanceScoreVo = balanceScoreService.loadBalanceScore(balanceScore); 
		balanceScoreVo.setBalanceScoreMinData(balanceScoreMinData);
		balanceScoreVo.setBalanceScoreMaxData(balanceScoreMaxData);
		balanceScoreVo.setBalanceScoreAvgData(balanceScoreAvgData);
		request.setAttribute("balanceScoreVo", balanceScoreVo);
		return "balance_score_report";
	}
	
	@RequestMapping("/loadGradeEvaluation.do")
	public @ResponseBody List<BalanceScoreVo> loadGradeEvaluation(HttpServletRequest request, Integer competitionId){
		BalanceScoreVo balanceScoreVo = new BalanceScoreVo();
		Competition competition = competitionService.findCompetitionById(competitionId);
		//int quarter = (int) request.getSession().getAttribute("currentQuarter") - 1;
		int quarter = competition.getCurrentQuarter()-1;
		//System.out.println(quarter);
		Company company = new Company();
		company.setCompetitionId(competitionId);
		BalanceScore balanceScore = new BalanceScore();
		balanceScore.setQuarter(quarter);
		balanceScoreVo.setCompany(company);
		balanceScoreVo.setBalanceScore(balanceScore);
		BalanceScoreMinData balanceScoreMinData = new BalanceScoreMinData();
		balanceScoreMinData = balanceScoreService.getMinData(competitionId, quarter);
		BalanceScoreMaxData balanceScoreMaxData = new BalanceScoreMaxData();
		balanceScoreMaxData = balanceScoreService.getMaxData(competitionId, quarter);
		BalanceScoreAvgData balanceScoreAvgData = new BalanceScoreAvgData();
		balanceScoreAvgData = balanceScoreService.getAvgData(competitionId, quarter);
		List<BalanceScoreVo> balanceScoreVos = balanceScoreService.loadAllBalanceScore(balanceScoreVo);
		for (BalanceScoreVo vo : balanceScoreVos) {
			vo.setBalanceScoreAvgData(balanceScoreAvgData);
			vo.setBalanceScoreMaxData(balanceScoreMaxData);
			vo.setBalanceScoreMinData(balanceScoreMinData);
		}
		return balanceScoreVos;
	}
	
	

	@MethodLog(description="课程介绍")
	@RequestMapping("/classIntroduction.do")
	public void getIntroductionRequest(){
		
	}
}
