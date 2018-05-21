package cn.usst.market.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.usst.market.mapper.CashFlowMapper;
import cn.usst.market.mapper.CompanyMapper;
import cn.usst.market.mapper.FinancialRatioMapper;
import cn.usst.market.mapper.PerformanceReportMapper;
import cn.usst.market.po.BalanceSheet;
import cn.usst.market.po.CompanyCapacity;
import cn.usst.market.po.CompanyInvestment;
import cn.usst.market.po.CompanyLoan;
import cn.usst.market.po.CompanyMarketShare;
import cn.usst.market.po.CompanyProduct;
import cn.usst.market.po.CompanyProductVo2;
import cn.usst.market.po.FinancialRatio;
import cn.usst.market.po.FinancialRatioAvg;
import cn.usst.market.po.FinancialRatioMax;
import cn.usst.market.po.FinancialRatioMin;
import cn.usst.market.po.FinancialRatioVo;
import cn.usst.market.po.IdQuarter;
import cn.usst.market.po.IncomeStatement;
import cn.usst.market.po.PerformanceReport;
import cn.usst.market.service.PerformanceReportService;
@Service("performanceReportService")
public class PerformanceReportServiceImpl implements PerformanceReportService {

	@Autowired
	PerformanceReportMapper performanceMapper;
	
	@Autowired
	FinancialRatioMapper financialRatioMapper;
	
	@Autowired
	private CompanyMapper companyMapper;
	
	@Autowired 
	private CashFlowMapper cashFlowMapper;
	
	@Override
	public List<PerformanceReport> findCompanyMarketShareByCompetetionId(int competetionId, int quarter) {
		// TODO Auto-generated method stub
		return performanceMapper.findCompanyMarketShareByCompetetionId(competetionId, quarter);
	}

	@Override
	public List<PerformanceReport> findCompanyProductMarketShareByCompetionId(int competetionId, int quarter) {
		// TODO Auto-generated method stub
		return performanceMapper.findCompanyProductMarketShareByCompetionId(competetionId, quarter);
	}

	@Override
	public FinancialRatioVo findFinancialRatioByCompanyId(int companyId, int quarter) {
		// TODO Auto-generated method stub
		return financialRatioMapper.findFinancialRatioByCompanyId(companyId, quarter);
	}

	@Override
	public FinancialRatioMax getMax(int id, int quarter) {
		// TODO Auto-generated method stub
		return financialRatioMapper.getMax(id, quarter);
	}

	@Override
	public FinancialRatioMin getMin(int id, int quarter) {
		// TODO Auto-generated method stub
		return financialRatioMapper.getMin(id, quarter);
	}

	@Override
	public FinancialRatioAvg getAvg(int id, int quarter) {
		// TODO Auto-generated method stub
		return financialRatioMapper.getAvg(id, quarter);
	}

	@Override
	public CompanyMarketShare findCompanyMarketShareByCompanyId(int companyId, int quarter) {
		// TODO Auto-generated method stub
		return performanceMapper.findCompanyMarketShareByCompanyId(companyId, quarter);
	}

	@Override
	public CompanyInvestment findCompanyInvestmentByCompanyId(int companyId, int quarter) {
		// TODO Auto-generated method stub
		return performanceMapper.findCompanyInvestmentByCompanyId(companyId, quarter);
	}

	@Override
	public void insertFinancialRatio(FinancialRatio financialRatio) {
		// TODO Auto-generated method stub
		financialRatioMapper.insertFinancialRatio(financialRatio);
	}
	
	@Override
	public CompanyLoan findCompanyLoanByCompanyIdQuarter(int companyId, int quarter) {
		// TODO Auto-generated method stub
		return performanceMapper.findCompanyLoanByCompanyIdQuarter(companyId, quarter);
	}
	
	@Override
	public void processFinancialRatio(int companyId, int quarter){
		IdQuarter idQuarter=new IdQuarter();
		idQuarter.setId(companyId);
		idQuarter.setQuarter(quarter);
		IncomeStatement incomeStatement = cashFlowMapper.selectIncomeStatementResult(companyId, quarter).get(0);
		double grossRevenue = incomeStatement.getYingyeIncome() + incomeStatement.getLixiIncome();//销售收入
		double grossCost = incomeStatement.getYingyeCost() + incomeStatement.getFankuan() + incomeStatement.getYanfa() + incomeStatement.getGuanggao() 
				+ incomeStatement.getSalerCost() + incomeStatement.getSalescenterCost() + incomeStatement.getSalescenterWebCost() + incomeStatement.getBaogao()
				+ incomeStatement.getHuoyun() + incomeStatement.getKucun() + incomeStatement.getExcessCapacity() + incomeStatement.getZhejiu() + incomeStatement.getNetmarketCost()
				+ incomeStatement.getLixiCost();//销售成本
		double operatingProfit = grossRevenue - grossCost;//销售毛利
		//净收入
		double netProfit = incomeStatement.getYingyeIncome() + incomeStatement.getLixiIncome() + incomeStatement.getTechIncome()
				+ incomeStatement.getQitaIncome() - incomeStatement.getYingyeCost() - incomeStatement.getSalerCost() - incomeStatement.getSalescenterCost()
				- incomeStatement.getSalescenterWebCost() - incomeStatement.getNetmarketCost() - incomeStatement.getLixiCost();
		List<CompanyProductVo2> productList = companyMapper.selectInvertoryCountFinancialRatio(companyId, quarter);
		double productInventory =0;
		if(productList!=null){
			//成品库存
			for (CompanyProductVo2 companyProductVo2 : productList) {
				productInventory += companyProductVo2.getInventory();
			}
		}
		
		List<CompanyCapacity> capacityList = companyMapper.showCapacityInfo1(companyId, quarter);
		double fixedAsset = 0;
		if(capacityList!=null&&capacityList.size()>0){
			fixedAsset = capacityList.get(0).getCapacityNow();//固定资产
		}
		List<BalanceSheet> listBSR = cashFlowMapper.selectBalanceSheetResult(companyId, quarter);
		BalanceSheet balanceSheet=new BalanceSheet();
		if(listBSR!=null){
			balanceSheet = listBSR.get(0);//balanceSheetResult
		}
		double cash = balanceSheet.getHuobi();//现金
		double deposit = balanceSheet.getCunkuan();//三个月存款
		//总资产
		double totalAsset = balanceSheet.getCunhuo() + balanceSheet.getHuobi() + balanceSheet.getLixiCollection() +
				balanceSheet.getZichan() + deposit;
		//银行贷款
		double loan=0;
		CompanyLoan companyLoan = findCompanyLoanByCompanyIdQuarter(companyId, quarter);
		if(companyLoan!=null){
			if(companyLoan.getLoanLast() != null){
				loan = companyLoan.getLoanLast();
			}
		}
		//销货成本
		double costOfSaleGood = incomeStatement.getYingyeCost();
		//速动比率
		double quickRatio = countRatio(cash + deposit, loan);
		//库存周转率
		double inventoryoverRatio = countRatio(costOfSaleGood, productInventory);
		//固定资产周转率
		double fixedassetoverRatio = countRatio(grossRevenue, fixedAsset);
		//总资产周转率
		double totalassetRatio = countRatio(grossRevenue, totalAsset);
		//负债比率
		double debtRatio = countRatio(loan, totalAsset);
		//毛利率
		double grossprofitRatio = countRatio(operatingProfit, grossRevenue);
		//净利率
		double netprofitRatio = countRatio(netProfit, grossRevenue);
		//资产回报率
		double returnRatio = countRatio(netProfit, totalAsset);
		
		FinancialRatio financialRatio = new FinancialRatio();
		financialRatio.setCompanyId(companyId);
		financialRatio.setDebtRatio(debtRatio);
		financialRatio.setFixedassetoverRatio(fixedassetoverRatio);
		financialRatio.setGrossprofitRatio(grossprofitRatio);
		financialRatio.setInventoryoverRatio(inventoryoverRatio);
		financialRatio.setNetprofitRatio(netprofitRatio);
		financialRatio.setQuarter(quarter);
		financialRatio.setQuickRatio(quickRatio);
		financialRatio.setReturnRatio(returnRatio);
		financialRatio.setTotalassetRatio(totalassetRatio);
		insertFinancialRatio(financialRatio);;
	}
	public double countRatio(double a, double b){
		if(b == 0){
			return 0;
		}else{
			return a/b;
		}
	}
	
	
	public int isNullInventory(Integer inventory){
		if(inventory == null){
			return 0;
		}else{
			return inventory;
		}
	}
}
