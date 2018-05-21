package cn.usst.market.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.usst.market.mapper.BalanceScoreMapper;
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
import cn.usst.market.po.HirePeople;
import cn.usst.market.po.HirePeopleOnline;
import cn.usst.market.po.IdQuarter;
import cn.usst.market.po.IncomeStatement;
import cn.usst.market.po.LearnTime;
import cn.usst.market.po.LearnTimeVo;
import cn.usst.market.po.PerformanceReport;
import cn.usst.market.po.SalesSalary;
import cn.usst.market.service.BalanceScoreService;
import cn.usst.market.service.CompanyService;
import cn.usst.market.service.PerformanceReportService;

@Service("balanceScoreService")
public class BalanceScoreServiceImpl implements BalanceScoreService {
	@Autowired
	private BalanceScoreMapper balanceScoreMapper;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private PerformanceReportService performanceReportService;
	
	@Override
	public BalanceScoreVo loadBalanceScore(BalanceScore balanceScore) {
		// TODO Auto-generated method stub
		return balanceScoreMapper.loadBalanceScore(balanceScore);
	}

	@Override
	public BalanceScoreMinData getMinData(int id, int quarter) {
		// TODO Auto-generated method stub
		return balanceScoreMapper.getMinData(id, quarter);
	}

	@Override
	public BalanceScoreAvgData getAvgData(int id, int quarter) {
		// TODO Auto-generated method stub
		return balanceScoreMapper.getAvgData(id, quarter);
	}

	@Override
	public BalanceScoreMaxData getMaxData(int id, int quarter) {
		// TODO Auto-generated method stub
		return balanceScoreMapper.getMaxData(id, quarter);
	}

	@Override
	public List<BalanceScoreVo> loadAllBalanceScore(BalanceScoreVo balanceScoreVo) {
		// TODO Auto-generated method stub
		return balanceScoreMapper.loadAllBalanceScore(balanceScoreVo);
	}

	@Override
	public void insertBalanceScore(BalanceScore balanceScore) {
		// TODO Auto-generated method stub
		balanceScoreMapper.insertBalanceScore(balanceScore);
	}

	@Override
	public void insertLearnTime(LearnTime learnTime) {
		// TODO Auto-generated method stub
		balanceScoreMapper.insertLearnTime(learnTime);
	}

	@Override
	public LearnTime getLearnTimeByMemberId(LearnTime learnTime) {
		// TODO Auto-generated method stub
		return balanceScoreMapper.getLearnTimeByMemberId(learnTime);
	}

	@Override
	public void updateLearnTime(LearnTime learnTime) {
		// TODO Auto-generated method stub
		balanceScoreMapper.updateLearnTime(learnTime);
	}

	@Override
	public List<LearnTime> getLearnTimeByCompanyId(LearnTimeVo learnTimeVo) {
		// TODO Auto-generated method stub
		return balanceScoreMapper.getLearnTimeByCompanyId(learnTimeVo);
	}

	@Override
	public List<CompanyMediaVo> getCompanyMediaCostByCompanyId(CompanyMediaVo companyMediaVo) {
		// TODO Auto-generated method stub
		return balanceScoreMapper.getCompanyMediaCostByCompanyId(companyMediaVo);
	}

	@Override
	public SalesSalary getSalesSalaryByCompanyIdQuarter(int companyId, int quarter) {
		// TODO Auto-generated method stub
		return balanceScoreMapper.getSalesSalaryByCompanyIdQuarter(companyId, quarter);
	}

	@Override
	public List<HirePeople> getHirePeopleByCompanyIdQuarter(int companyId) {
		// TODO Auto-generated method stub
		return balanceScoreMapper.getHirePeopleByCompanyIdQuarter(companyId);
	}
	
	@Override
	public List<HirePeopleOnline> getHirePeopleOnlineByCompanyIdQuarter(int companyId) {
		// TODO Auto-generated method stub
		return balanceScoreMapper.getHirePeopleOnlineByCompanyIdQuarter(companyId);
	}
	
	//平衡记分卡的计算部分
	@Override
	public void insertTotleBalanceScore(Integer companyId,int quarter){
		IdQuarter idQuarter=new IdQuarter();
		idQuarter.setId(companyId);
		idQuarter.setQuarter(quarter);
/*		IncomeStatement ll=competitionResultService.findIncomeStatementResultByCompanyIdQuarter(companyId, quarter);
*/		
		IncomeStatement incomeStatement = companyService.selectIncomeStatementResult(companyId, quarter).get(0);
		 
		//营业总收入
		double grossRevenue = incomeStatement.getYingyeIncome() + incomeStatement.getLixiIncome();//营业总收入
		//营业总成本
		double grossCost = incomeStatement.getYingyeCost() + incomeStatement.getFankuan() + incomeStatement.getYanfa() + incomeStatement.getGuanggao() 
				+ incomeStatement.getSalerCost() + incomeStatement.getSalescenterCost() + incomeStatement.getSalescenterWebCost() + incomeStatement.getBaogao()
				+ incomeStatement.getHuoyun() + incomeStatement.getKucun() + incomeStatement.getExcessCapacity() + incomeStatement.getZhejiu() + incomeStatement.getNetmarketCost()
				+ incomeStatement.getLixiCost();
		//营业利润
		double operatingProfit = grossRevenue - grossCost;
		CashFlow cashFlow = companyService.selectCashFlowResult(companyId, quarter).get(0);
		//现金等价物余额
		double cashEquivalent = cashFlow.getYuE();
		BalanceSheet balanceSheet = companyService.selectBalanceSheetResult(companyId, quarter).get(0);
		
		double grossAsset = balanceSheet.getHuobi() + balanceSheet.getCunkuan() + balanceSheet.getLixiCollection()
				 + balanceSheet.getCunhuo() + balanceSheet.getZichan();
		double grossDebt = balanceSheet.getDaikuanEmergency() + balanceSheet.getLixiPay() + balanceSheet.getDaikuanNormal();
		//资产管理
		double assetManagement = grossAsset - grossDebt;
		//人员培训时间
		double trainingTime = processTrainingTime(companyId, quarter);
		//净利润
		double netProfit = incomeStatement.getYingyeIncome() + incomeStatement.getLixiIncome() + incomeStatement.getTechIncome()
				+ incomeStatement.getQitaIncome() - incomeStatement.getYingyeCost() - incomeStatement.getSalerCost() - incomeStatement.getSalescenterCost()
				- incomeStatement.getSalescenterWebCost() - incomeStatement.getNetmarketCost() - incomeStatement.getLixiCost();
		
		CompanyMediaVo companyMediaVo = new CompanyMediaVo();
		companyMediaVo = initCompanyMediaVo(companyId, quarter);
		List<CompanyMediaVo> companyMediaList = getCompanyMediaCostByCompanyId(companyMediaVo);
		//广告成本
		double advertiseCost = countAdCost(companyMediaList);
		CompanyMarketShare companyMarketShare = performanceReportService.findCompanyMarketShareByCompanyId(companyId, quarter);
		//市场份额
		Company company = new Company();
		company.setId(companyId);
		Company currCompany=companyService.findCompanyById(company);
		int needSum=0;//设置需求量总和
		double marketShare=0;
		if(currCompany!=null){
			List<PerformanceReport> companyMSList=performanceReportService.findCompanyMarketShareByCompetetionId(currCompany.getCompetitionId(), quarter);
			if(companyMSList!=null&&companyMSList.size()>0){
				for(int i=0;i<companyMSList.size();i++){
					needSum+=companyMSList.get(i).getCompanyMarketShare().getBusinessNeed()+companyMSList.get(i).getCompanyMarketShare().getPerfectNeed()+
							companyMSList.get(i).getCompanyMarketShare().getPracticalNeed();
				}
			}
		}
		if(needSum!=0){
			marketShare=(double)(companyMarketShare.getBusinessNeed()+companyMarketShare.getPerfectNeed()+companyMarketShare.getPracticalNeed())/needSum;
		}
		marketShare=(double)(Math.round(marketShare*1000))/1000;
		//double marketShare = companyMarketShare.getBusinessShare() + companyMarketShare.getPerfectShare() + companyMarketShare.getPracticalShare();
		
		//生产效率
		CompanyInvestment companyInvestment = performanceReportService.findCompanyInvestmentByCompanyId(companyId, quarter);
		double productionEfficiency = companyInvestment.getWorkerEfficiency();
		
		//销售人员薪酬
		SalesSalary salesSalary = getSalesSalaryByCompanyIdQuarter(companyId, quarter);
		List<HirePeople> hirePeopleList = getHirePeopleByCompanyIdQuarter(companyId);
		List<HirePeopleOnline> hirePeopleOnlineList = getHirePeopleOnlineByCompanyIdQuarter(companyId);
		int salesNum = countSalesManNum(hirePeopleList, hirePeopleOnlineList);
		double salesStaffRemuneration = 0;
		if(salesSalary!=null){
			salesStaffRemuneration=salesNum * salesSalary.getSalaryTotal();
		}
		//单位营销收益
		double unitMarketingRevenue;
		if(advertiseCost == 0 && advertiseCost == 0){
			unitMarketingRevenue = 0;
		}else{
			unitMarketingRevenue = netProfit/(advertiseCost + advertiseCost);
		}
		BalanceScore balanceScore = new BalanceScore();
		balanceScore.setAssetManagement(assetManagement);
		balanceScore.setCashEquivalent(cashEquivalent);
		balanceScore.setCompanyId(companyId);
		balanceScore.setGrossCost(grossCost);
		balanceScore.setGrossRevenue(grossRevenue);
		balanceScore.setMarketShare(marketShare);
		balanceScore.setOperatingProfit(operatingProfit);
		balanceScore.setProductionEfficiency(productionEfficiency);
		balanceScore.setQuarter(quarter);
		balanceScore.setSalesStaffRemuneration(salesStaffRemuneration);
		balanceScore.setTrainingTime(trainingTime);
		balanceScore.setUnitMarketingRevenue(unitMarketingRevenue);
		insertBalanceScore(balanceScore);
	}

	private int countSalesManNum(List<HirePeople> hirePeopleList, List<HirePeopleOnline> hirePeopleOnlineList) {
		// TODO Auto-generated method stub
		int num = 0;
		for (HirePeople hirePeople : hirePeopleList) {
			num = num + hirePeople.getSaleman() + hirePeople.getAfterSale();
		}
		
		for (HirePeopleOnline hirePeopleOnline : hirePeopleOnlineList) {
			num = num + hirePeopleOnline.getSaleman() + hirePeopleOnline.getAfterSale();
		}
		return num;
	}

	private double countAdCost(List<CompanyMediaVo> companyMediaList) {
		// TODO Auto-generated method stub
		double advertiseCost = 0;
		for (CompanyMediaVo companyMediaVo : companyMediaList) {
			advertiseCost += companyMediaVo.getCompanyMedia().getNum() * companyMediaVo.getMediaInfo().getCost();
		}
		return advertiseCost;
	}

	private CompanyMediaVo initCompanyMediaVo(Integer companyId, Integer currentQuarter) {
		// TODO Auto-generated method stub
		CompanyMediaVo companyMediaVo = new CompanyMediaVo();
		CompanyMedia companyMedia = new CompanyMedia();
		companyMedia.setCompanyId(companyId);
		companyMedia.setQuarter(currentQuarter);
		companyMediaVo.setCompanyMedia(companyMedia);
		return companyMediaVo;
	}

	private double processTrainingTime(Integer companyId, Integer currentQuarter) {
		// TODO Auto-generated method stub
		LearnTimeVo learnTimeVo = new LearnTimeVo();
		LearnTime learnTime = new LearnTime();
		learnTime.setQuarter(currentQuarter);
		learnTimeVo.setCompanyId(companyId);
		learnTimeVo.setLearnTime(learnTime);
		List<LearnTime> list = getLearnTimeByCompanyId(learnTimeVo);
		double totalTime = 0;
		for (LearnTime lt : list) {
			totalTime += lt.getTime();
		}
		return totalTime;
	}
	
	
}
