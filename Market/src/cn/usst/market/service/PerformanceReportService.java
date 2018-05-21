package cn.usst.market.service;

import java.util.List;

import cn.usst.market.po.CompanyInvestment;
import cn.usst.market.po.CompanyLoan;
import cn.usst.market.po.CompanyMarketShare;
import cn.usst.market.po.FinancialRatio;
import cn.usst.market.po.FinancialRatioAvg;
import cn.usst.market.po.FinancialRatioMax;
import cn.usst.market.po.FinancialRatioMin;
import cn.usst.market.po.FinancialRatioVo;
import cn.usst.market.po.PerformanceReport;

public interface PerformanceReportService {
	public List<PerformanceReport> findCompanyMarketShareByCompetetionId(int competetionId, int quarter);
	public List<PerformanceReport> findCompanyProductMarketShareByCompetionId(int competitionId, int quarter);
	public FinancialRatioVo findFinancialRatioByCompanyId(int companyId, int quarter);
	public void insertFinancialRatio(FinancialRatio financialRatio);
	public FinancialRatioMax getMax(int id, int quarter);
	public FinancialRatioMin getMin(int id, int quarter);
	public FinancialRatioAvg getAvg(int id, int quarter);
	public CompanyMarketShare findCompanyMarketShareByCompanyId(int companyId, int quarter);
	public CompanyInvestment findCompanyInvestmentByCompanyId(int companyId, int quarter);
	public CompanyLoan findCompanyLoanByCompanyIdQuarter(int companyId, int quarter);
	public void processFinancialRatio(int companyId, int quarter);
}
