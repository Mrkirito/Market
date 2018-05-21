package cn.usst.market.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.usst.market.mapper.CashFlowMapper;
import cn.usst.market.mapper.CompanyMapper;
import cn.usst.market.mapper.CompetitionMapper;
import cn.usst.market.po.AdvertiseInfo;
import cn.usst.market.po.AllSalesSalaryVo;
import cn.usst.market.po.AllWorkersSalaryVo;
import cn.usst.market.po.AverageSalary;
import cn.usst.market.po.BalanceSheet;
import cn.usst.market.po.CapacityInfo;
import cn.usst.market.po.CashFlow;
import cn.usst.market.po.Company;
import cn.usst.market.po.CompanyAdvertise;
import cn.usst.market.po.CompanyCapacity;
import cn.usst.market.po.CompanyDecision;
import cn.usst.market.po.CompanyDuty;
import cn.usst.market.po.CompanyInvestment;
import cn.usst.market.po.CompanyLoan;
import cn.usst.market.po.CompanyMarket;
import cn.usst.market.po.CompanyMedia;
import cn.usst.market.po.CompanyPersonGoal;
import cn.usst.market.po.CompanyProduct;
import cn.usst.market.po.CompanyProductDemand;
import cn.usst.market.po.CompanyProductInventory;
import cn.usst.market.po.CompanyProductVo;
import cn.usst.market.po.CompanyProductVo2;
import cn.usst.market.po.CompanyReport;
import cn.usst.market.po.CompanyRule;
import cn.usst.market.po.CompanyStock;
import cn.usst.market.po.CompanyStrategy;
import cn.usst.market.po.Competition;
import cn.usst.market.po.DemandForecast;
import cn.usst.market.po.Duty;
import cn.usst.market.po.FixedDeposit;
import cn.usst.market.po.HirePeople;
import cn.usst.market.po.HirePeopleOnline;
import cn.usst.market.po.HirePeopleVo;
import cn.usst.market.po.IncomeStatement;
import cn.usst.market.po.MarketInfo;
import cn.usst.market.po.MarketOpenedPo;
import cn.usst.market.po.MarketWebOpened;
import cn.usst.market.po.OnlineStore;
import cn.usst.market.po.OperationCapacity;
import cn.usst.market.po.ProductInfo;
import cn.usst.market.po.ProductMarketShare;
import cn.usst.market.po.ProductPrice;
import cn.usst.market.po.SalesSalary;
import cn.usst.market.po.StrategyInfo;
import cn.usst.market.po.TeacherQueryVo;
import cn.usst.market.po.WorkersSalary;
import cn.usst.market.service.CompanyService;

@Service("companyService")
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	private CompanyMapper companyMapper;
	
	@Autowired
	private CashFlowMapper cashFlowMapper;
	
	@Autowired
	private CompetitionMapper CM;
	
	@Override
	public void insert(Company record) {
		companyMapper.insert(record);
	}

	@Override
	public Company findCompanyByCondition(Company record) {
		
		return companyMapper.findCompanyByCondition(record);
	}

	@Override
	public Company findCompanyById(Company record) {

		return companyMapper.findCompanyById(record);
	}

	@Override
	public void updateCompanyName(Company record) {
		companyMapper.updateCompanyName(record);
		
	}

	@Override
	public List<Company> showCompanyByCompetitionId(Integer id) {
		return companyMapper.showCompanyByCompetitionId(id);
	}



	@Override
	public List<String> countStrategyTitle() {
		return companyMapper.countStrategyTitle();
	}

	@Override
	public List<StrategyInfo> showStrategyInfoBytitle(String title) {
		
		return companyMapper.showStrategyInfoByTitle(title);
	}

	@Override
	public List<String> countCompanyRuleTitle() {
		
		return companyMapper.countCompanyRuleTitle();
	}

	@Override
	public List<StrategyInfo> showCompanyRuleInfoBytitle(String title) {
		
		return companyMapper.showCompanyRuleInfoBytitle(title);
	}

	@Override
	public List<String> countPersonalGoalTitle() {
		
		return companyMapper.countPersonalGoalTitle();
	}

	@Override
	public List<StrategyInfo> showPersonalGoalInfoBytitle(String title) {
		
		return companyMapper.showPersonalGoalInfoBytitle(title);
	}

	@Override
	public List<Duty> showAllPosition() {
		
		return companyMapper.showAllPosition();
	}

	@Override
	public List<TeacherQueryVo> findCompanyList(TeacherQueryVo teacherQueryVo) throws Exception {

		return companyMapper.findCompanyList(teacherQueryVo);
	}

	@Override
	public List<TeacherQueryVo> selectCompanyByPage(TeacherQueryVo teacherQueryVo) {
		
		return companyMapper.selectCompanyByPage(teacherQueryVo);
	}

	@Override
	public long getCompanyCount(TeacherQueryVo teacherQueryVo) {
		
		return companyMapper.getCompanyCount(teacherQueryVo);
	}

	@Override
	public List<MarketInfo> showMarketInfo(int competitionId) {
		
		return companyMapper.showMarketInfo(competitionId);
	}

	@Override
	public List<CapacityInfo> showCapacityInfo() {
		
		return companyMapper.showCapacityInfo();
	}

	@Override
	public Company checkCompanyNameExist(String name) {
		
		return companyMapper.checkCompanyNameExist(name);
	}

	@Override
	public Company selectCompanyById(Integer id) {
		
		return companyMapper.selectCompanyById(id);
	}

	@Override
	public void updateCompanyNameById(Integer id, String name) {
		
		companyMapper.updateCompanyNameById(id, name);
	}



	@Override
	public CompanyStrategy selectCompanyStrategy(Integer company_id, Integer quarter) {
		
		return companyMapper.selectCompanyStrategy(company_id, quarter) ;
	}

	@Override
	public int insertCompanyStrategy(CompanyStrategy companyStrategy) throws Exception {
		
		return companyMapper.insertCompanyStrategy(companyStrategy);
	}

	@Override
	public void updateCompanyStrategy(CompanyStrategy companyStrategy) throws Exception {
		companyMapper.updateCompanyStrategy(companyStrategy);
		
	}
	@Override
	public void deleteProductById(Integer id) {
		
		companyMapper.deleteProductById(id);
	}
	
	@Override
	public List<ProductInfo> showProductDetailByTitle(String title) {
		
		return companyMapper.showProductDetailByTitle(title);
	}

	@Override
	public List<String> countProductInfoTitle() {
		
		return companyMapper.countProductInfoTitle();
	}

	@Override
	public List<ProductInfo> showProductInfoByTitle(String title) {
		
		return companyMapper.showProductInfoByTitle(title);
	}
	
	
	@Override
	public CompanyDuty selectMemberByMemberId(Integer id) {
		return companyMapper.selectMemberByMemberId(id);
	}

	@Override
	public void insertMemberDuty(CompanyDuty companyDuty) {
		companyMapper.insertMemberDuty(companyDuty);
		
	}

	@Override
	public void updateMemberDuty(CompanyDuty companyDuty) {
		companyMapper.updateMemberDuty(companyDuty);
	}

	
	@Override
	public CompanyDuty selectMemberDutyByMemberId(Integer id) {
		return companyMapper.selectMemberDutyByMemberId(id);
	}

	@Override
	public List<Integer> selectMemberIdByCompanyId(Integer id) {
		
		return companyMapper.selectMemberIdByCompanyId(id);
	}

	@Override
	public List<CompanyDuty> selectMemberDutyByCompanyId(Integer id) {
		
		return companyMapper.selectMemberDutyByCompanyId(id);

	}
	
	@Override
	public CompanyRule selectCompanyRule(Integer company_id, Integer quarter) {
		return companyMapper.selectCompanyRule(company_id, quarter);
	}

	@Override
	public void insertCompanyRule(CompanyRule companyRule) {
		companyMapper.insertCompanyRule(companyRule);
		
	}

	@Override
	public void updateCompanyRule(CompanyRule companyRule) {
		companyMapper.updateCompanyRule(companyRule);
		
	}
	
	@Override
	public CompanyPersonGoal selectCompanyPersonGoal(Integer company_id, Integer quarter) {
		return companyMapper.selectCompanyPersonGoal(company_id, quarter);
	}

	@Override
	public void insertCompanyPersonGoal(CompanyPersonGoal companyPersonGoal) {
		companyMapper.insertCompanyPersonGoal(companyPersonGoal);
		
	}

	@Override
	public void updateCompanyPersonGoal(CompanyPersonGoal companyPersonGoal) {
		companyMapper.updateCompanyPersonGoal(companyPersonGoal);	
	}
	
	@Override
	public CompanyMarket selectCompanyPhysicalMarket(Integer company_id, Integer quarter) {
		return companyMapper.selectCompanyPhysicalMarket(company_id, quarter);
	}

	@Override
	public void insertCompanyMarket(CompanyMarket companyMarket) {
		companyMapper.insertCompanyMarket(companyMarket);
		
	}

	@Override
	public void updateCompanyMarket(CompanyMarket companyMarket) {
		companyMapper.updateCompanyMarket(companyMarket);
		
	}
	
	@Override
	public List<CompanyMarket> showCompanymarket(CompanyMarket companyMarket)
	{
	return	companyMapper.showCompanymarket(companyMarket);
	}
	
	@Override
	public void insertmarketinfo(CompanyMarket mInfo) {
		companyMapper.insertmarketinfo(mInfo);
		
	}
	
	@Override
	public CashFlow selectCashFlowByCompanyId(int companyId, int quarter) {
		return companyMapper.selectCashFlowByCompanyId(companyId, quarter);
	}

	@Override
	public void updateCashFlowYanFa(float yanfa_pay, int company_id, int quarter) {
		companyMapper.updateCashFlowYanFa(yanfa_pay, company_id, quarter);
		
	}

	@Override
	public void insertCashFlow(int companyId, int quarter, float yanfaPay) {
		companyMapper.insertCashFlow(companyId, quarter, yanfaPay);
		
	}

	@Override
	public void updateCashFlowGongChang(float invest, int company_id, int quarter) {
		companyMapper.updateCashFlowGongChang(invest, company_id, quarter);
		
	}

	@Override
	public void updateCashFlowMarket(float salescenter_pay, int company_id, int quarter) {
		companyMapper.updateCashFlowMarket(salescenter_pay, company_id, quarter);
		
	}


	@Override
	public int selectSalesCenterOpen(int market_id) {
		
		return companyMapper.selectSalesCenterOpen(market_id);
	}

	@Override
	public int selectSalesCenterWebOpen(int market_id) {
		
		return companyMapper.selectSalesCenterWebOpen(market_id);
	}


	
	@Override
	public IncomeStatement selectIncomeStatementByCompanyId(int companyId, int quarter) {
		
		return companyMapper.selectIncomeStatementByCompanyId(companyId, quarter);
	}

	@Override
	public void updateIncomeStatementYanFa(float yanfa_pay, int company_id, int quarter) {
		
		companyMapper.updateIncomeStatementYanFa(yanfa_pay, company_id, quarter);
	}

	@Override
	public void updateIncomeStatementMarket(float salescenter_pay, int company_id, int quarter) {
		
		companyMapper.updateIncomeStatementMarket(salescenter_pay, company_id, quarter);
	}

	
	
	@Override
	public void insertCompanyProduct(CompanyProduct companyProduct) {
		companyMapper.insertCompanyProduct(companyProduct);
		
	}

	@Override
	public List<CompanyProduct> selectProductByCompanyId(Integer companyId) {
		return companyMapper.selectProductByCompanyId(companyId);
	}

	@Override
	public CompanyProduct selectProductByProductId(Integer id) {
		return companyMapper.selectProductByProductId(id);
	}

	@Override
	public List<ProductInfo> showAllDetail() {
		
		return companyMapper.showAllDetail();
	}

	@Override
	public void updateCompanyProduct(CompanyProduct companyProduct) {
		companyMapper.updateCompanyProduct(companyProduct);
		
	}
	@Override
	public void deleteCompanyProduct(Integer product_id) {
		companyMapper.deleteCompanyProduct(product_id);
		
	}
	
	

	@Override
	public List<BalanceSheet> showBalanceSheet(int company_id,int quarter) {
		
		return companyMapper.showBalanceSheet(company_id,quarter);
	}


	
	@Override
	public int selectGuBen(int compant_id, int quarter) {
		
		return companyMapper.selectGuBen(compant_id, quarter);
	}

	@Override
	public int selectTotalCount(int companyId, int i) {
		
		return companyMapper.selectTotalCount(companyId, i);
	}

	@Override
	public CapacityInfo selectCapacityInfo(int number) {
		
		return companyMapper.selectCapacityInfo(number);
	}

	@Override
	public void insertCompanyCapacity(int capacityNow, int number, int companyId, int quarter) {
		
		companyMapper.insertCompanyCapacity(capacityNow, number, companyId, quarter);
		
	}

	@Override
	public List<CompanyCapacity> showCapacityInfo1(int companyId, int quarter) {
		
		return companyMapper.showCapacityInfo1(companyId, quarter);
	}

	@Override
	public void updateCompanyCapacity(int capacity_now,int capacity_add, int companyId, int quarter) {
		
		 companyMapper.updateCompanyCapacity(capacity_now, capacity_add, companyId, quarter);
		
	}

	@Override
	public List<CashFlow> showCashFlow(int company_id, int quarter) {
		
		return companyMapper.showCashFlow(company_id, quarter);
	}

	@Override
	public List<IncomeStatement> showIncomeStatement(int company_id, int quarter) {
		
		return companyMapper.showIncomeStatement(company_id, quarter);
	}
	
	@Override
	public List<CompanyStock> showCompanyStock(int company_id, int quarter) {
		
		return companyMapper.showCompanyStock(company_id, quarter);
	}
	
	@Override
	public List<FixedDeposit> showFixedDeposit() {
		return companyMapper.showFixedDeposit();
	}

	@Override
	public void insertFixedDeposite(int companyIdInt, float tiQuFloat, float cunRuFloat,int quarter,float cunkunLast) {
		
		companyMapper.insertFixedDeposite(companyIdInt, tiQuFloat, cunRuFloat,quarter,cunkunLast);
	}

	@Override
	public List<FixedDeposit> selectFixedDeposite(int companyIdInt, int quarter) {
		
		return companyMapper.selectFixedDeposite(companyIdInt,quarter);
	}
	@Override
	public void updateFixedDeposite(int companyIdInt, float tiQuFloat, float cunRuFloat, int quarter) {
		
		companyMapper.updateFixedDeposite(companyIdInt,tiQuFloat,cunRuFloat,quarter);
	}

	@Override
	public void insertCashFlowFirst(CashFlow cashFlow) {
		companyMapper.insertCashFlowFirst(cashFlow);
	}
	
	@Override
	public void insertIncomeStatementFirst(IncomeStatement incomeStatement) {
		companyMapper.insertIncomeStatementFirst(incomeStatement);
		
	}

	@Override
	public void insertBalanceSheetFirst(BalanceSheet balanceSheet) {
		companyMapper.insertBalanceSheetFirst(balanceSheet);
		
	}

	
	
	@Override
	public AverageSalary showAverageSalaryOfSale() {
		
		return companyMapper.showAverageSalaryOfSale();
	}

	@Override
	public AverageSalary showAverageSalaryOfWork() {
		
		return companyMapper.showAverageSalaryOfWork();
	}

	@Override
	public void insertCompanyStock(CompanyStock cs) {
		companyMapper.insertCompanyStock(cs);
		
	}

	@Override
	public void insertCompanyDuty(CompanyDuty cd) {
		companyMapper.insertCompanyDuty(cd);
		
	}

	@Override
	public List<WorkersSalary> selectCompanyWorkersSalary(int company_id, int quarter) {
		
		return companyMapper.selectCompanyWorkersSalary(company_id, quarter);
	}

	@Override
	public void insertCompanyWorkersSalary(WorkersSalary workersSalary) {
		
		companyMapper.insertCompanyWorkersSalary(workersSalary);
	}

	@Override
	public void updateCompanyWorkersSalary(WorkersSalary workersSalary) {
		
		companyMapper.updateCompanyWorkersSalary(workersSalary);
	}
	@Override
	public List<SalesSalary> selectCompanySalesSalary(int company_id, int quarter) {
		
		return companyMapper.selectCompanySalesSalary(company_id, quarter);
	}
	
	@Override
	public SalesSalary findCompanySalesSalary(int company_id, int quarter) {
		
		return companyMapper.findCompanySalesSalary(company_id, quarter);
	}

	@Override
	public void insertCompanySalesSalary(SalesSalary salesSalary) {
		
		companyMapper.insertCompanySalesSalary(salesSalary);
	}

	@Override
	public void updateCompanySalesSalary(SalesSalary salesSalary) {
		
		companyMapper.updateCompanySalesSalary(salesSalary);
	}
	
	
	@Override
	public List<DemandForecast> showDemandForecast() {
		return companyMapper.showDemandForecast();
	}

	@Override
	public void insertDemandForecast(int companyIdInt, int quarter, int demandAveragePhyt, int demandAverageWebt) {
		companyMapper.insertDemandForecast(companyIdInt, quarter, demandAveragePhyt, demandAverageWebt);
	}

	@Override
	public List<DemandForecast> selectDemandForecast(int companyIdInt, int quarter) {
		return companyMapper.selectDemandForecast(companyIdInt, quarter);
	}

	@Override
	public void updateDemandForecast(int companyIdInt, int quarter, int demandAveragePhyt, int demandAverageWebt) {
		companyMapper.updateDemandForecast(companyIdInt, quarter, demandAveragePhyt, demandAverageWebt);
	}

	@Override
	public void insertDemandForecast2(CompanyProduct companyProduct) {
		companyMapper.insertDemandForecast2(companyProduct);
	}

	@Override
	public List<CompanyProduct> selectDemandForecast2(int companyIdInt, int quarter) {
		return companyMapper.selectDemandForecast2(companyIdInt, quarter);
	}

	@Override
	public void updateDemandForecast2(CompanyProduct companyProduct) {
		companyMapper.updateDemandForecast2(companyProduct);
	}

	

	

	@Override
	public List<CompanyProduct> selectProductByCompanyIdAndQuarter(Integer companyId, Integer quarter) {
		
		return companyMapper.selectProductByCompanyIdAndQuarter(companyId, quarter);
	}

	@Override
	public void insertProductPrice(ProductPrice productPrice) {
		
		companyMapper.insertProductPrice(productPrice);
		
	}

	@Override
	public void updateProductPrice(int productId, int quarter, int price, int youji) {
		
		companyMapper.updateProductPrice(productId, quarter, price, youji);
		
	}

	@Override
	public List<ProductPrice> selectProductPrice(int company_id, int quarter) {
		
		return companyMapper.selectProductPrice(company_id, quarter);
	}

	@Override
	public List<ProductPrice> showTotalProductPrice(int company_id, int quarter) {
		
		
		return companyMapper.showTotalProductPrice(company_id, quarter);
	}

	@Override
	public List<CompanyProduct> showTotalCompanyProduct(int company_id, int quarter) {
		
		return companyMapper.showTotalCompanyProduct(company_id, quarter);
	}

	@Override
	public List<CompanyMedia> selectTotalCompanyMedia(int company_id, int quarter) {
		return companyMapper.selectTotalCompanyMedia(company_id, quarter);
	}

	@Override
	public List<CompanyMedia> selectCompanyMedia(int company_id, int quarter, int mediaId, int productId) {
		return companyMapper.selectCompanyMedia(company_id, quarter, mediaId, productId);
	}

	@Override
	public void insertCompanyMedia(CompanyMedia companyMedia) {
		companyMapper.insertCompanyMedia(companyMedia);
		
	}

	@Override
	public void updateCompanyMedia(CompanyMedia companyMedia) {
		companyMapper.updateCompanyMedia(companyMedia);
		
	}

	@Override
	public List<CompanyAdvertise> selectCompanyAdvertiseByProductId(int company_id, int quarter, int productId) {
		return companyMapper.selectCompanyAdvertiseByProductId(company_id, quarter, productId);
	}

	@Override
	public void insertCompanyAdvertise(CompanyAdvertise companyAdvertise) {
		companyMapper.insertCompanyAdvertise(companyAdvertise);
	}

	@Override
	public void updateCompanyAdvertise(CompanyAdvertise companyAdvertise) {
		companyMapper.updateCompanyAdvertise(companyAdvertise);
		
	}

	@Override
	public String selectCompanyReport(int company_id, int quarter) {
		return companyMapper.selectCompanyReport(company_id, quarter);
	}

	@Override
	public void insertCompanyReport(int company_id, int quarter, int buyReport) {
		companyMapper.insertCompanyReport(company_id, quarter, buyReport);
	}

	@Override
	public void updateCompanyReport(int company_id, int quarter, int buyReport) {
		companyMapper.updateCompanyReport(company_id, quarter, buyReport);
	}

	@Override
	public List<CompanyAdvertise> selectCompanyAdvertise(int company_id, int quarter) {
		
		return companyMapper.selectCompanyAdvertise(company_id, quarter);
	}

	@Override
	public List<AdvertiseInfo> selectAdvertsieById(int advertiseId) {
		return companyMapper.selectAdvertsieById(advertiseId);
	}
	
	@Override
	public List<OperationCapacity> showOperationCapacity() {
		return companyMapper.showOperationCapacity();
	}

	@Override
	public void insertOperationCapacity(int companyIdInt, int quarter, int operationCapacityInt,
			int workerProductivityInt) {
		companyMapper.insertOperationCapacity(companyIdInt,quarter,operationCapacityInt,workerProductivityInt);
		
	}

	@Override
	public List<OperationCapacity> selectOperationCapacity(int companyIdInt, int quarter) {
		return companyMapper.selectOperationCapacity(companyIdInt,quarter);
	}

	@Override
	public void updateOperationCapacity(int companyIdInt, int quarter, int operationCapacityInt,
			int workerProductivityInt) {
		companyMapper.updateOperationCapacity(companyIdInt,quarter,operationCapacityInt,workerProductivityInt);
		
	}
	
	//2017-10-16
	
	@Override
	public List<CompanyProduct> selectProductByCompanyIdAndQuarter(int company_id, int quarter) {
		return cashFlowMapper.selectProductByCompanyIdAndQuarter(company_id, quarter);
	}



	
	
		
		//库存控制
		
		@Override
		public void insertInventoryControl(CompanyProduct companyProduct) {
			companyMapper.insertInventoryControl(companyProduct);
		}

		@Override
		public List<CompanyProductVo2> selectInventoryControl(int companyIdInt, int quarter) {
			return companyMapper.selectInventoryControl(companyIdInt, quarter);
		}

		@Override
		public void updateInventoryControl(CompanyProduct companyProduct) {
			companyMapper.updateInventoryControl(companyProduct);
		}

		@Override
		public HirePeopleVo selectHirePeople(int companyId, int marketInt, int quarter) {
			return companyMapper.selectHirePeople(companyId, marketInt, quarter);
		}

		@Override
		public void updateHirePeopleById(HirePeople hirePeople) {
			companyMapper.updateHirePeopleById(hirePeople);
			
		}

		@Override
		public void insertHirePeople(HirePeople hirePeople) {
			companyMapper.insertHirePeople(hirePeople);
			
		}
		
		@Override
		public void updateCashFlowYanfa(float yanfa, int company_id, int quarter) {
			cashFlowMapper.updateCashFlowYanfa(yanfa, company_id, quarter);
		}

		@Override
		public CompanyMarket selectPhyCompanyMarket(int company_id, int quarter) {
			return cashFlowMapper.selectPhyCompanyMarket(company_id, quarter);
		}

		@Override
		public int selectOpenByMarketId(int id) {
			return cashFlowMapper.selectOpenByMarketId(id);
		}

		@Override
		public void updateCashFlowPhyMarket(float PhyMarket, int company_id, int quarter) {
			cashFlowMapper.updateCashFlowPhyMarket(PhyMarket, company_id, quarter);
		}
		
		@Override
		public CompanyMarket selectWebCompanyMarket(int company_id, int quarter) {
			return cashFlowMapper.selectWebCompanyMarket(company_id, quarter);
		}

		@Override
		public int selectWebOpenByMarketId(int id) {
			return cashFlowMapper.selectWebOpenByMarketId(id);
		}

		@Override
		public void updateCashFlowWebMarket(float WebMarket, int company_id, int quarter) {
			cashFlowMapper.updateCashFlowWebMarket(WebMarket, company_id, quarter);
		}

		@Override
		public int selectCapacity(int company_id, int quarter) {
			return cashFlowMapper.selectCapacity(company_id, quarter);
		}

		@Override
		public int selectInvestByCapacity(int capacity) {
			return cashFlowMapper.selectInvestByCapacity(capacity);
		}

		@Override
		public void updateCashFlowCapacity(float invest, int company_id, int quarter) {
			cashFlowMapper.updateCashFlowCapacity(invest, company_id, quarter);
		}

		@Override
		public FixedDeposit selectCunkuanLast(int company_id, int quarter) {
			return companyMapper.selectCunkuanLast(company_id, quarter);
		}

		@Override
		public void insertCunkuanLast(float cunkuanLast, int company_id, int quarter) {
			companyMapper.insertCunkuanLast(cunkuanLast, company_id, quarter);
		}

		@Override
		public int selectCunru(int company_id, int quarter) {
			return cashFlowMapper.selectCunru(company_id, quarter);
		}

		@Override
		public void updateCashFlowCunkuanPay(float cunru, int company_id, int quarter) {
			cashFlowMapper.updateCashFlowCunkuanPay(cunru, company_id, quarter);
		}

		@Override
		public void updateIncomeStatementYanfa(float yanfa, int company_id, int quarter) {
			cashFlowMapper.updateIncomeStatementYanfa(yanfa, company_id, quarter);
		}

		@Override
		public void updateIncomeStatementPhyMarket(float PhyMarket, int company_id, int quarter) {
			cashFlowMapper.updateIncomeStatementPhyMarket(PhyMarket, company_id, quarter);
		}

		@Override
		public void updateIncomeStatementWebMarket(float WebMarket, int company_id, int quarter) {
			cashFlowMapper.updateIncomeStatementWebMarket(WebMarket, company_id, quarter);
		}

		@Override
		public void updateIncomStatementLixi(float lixi, int company_id, int quarter) {
			cashFlowMapper.updateIncomStatementLixi(lixi, company_id, quarter);
		}

		@Override
		public int selectStockPrice(int company_id, int quarter) {
			return cashFlowMapper.selectStockPrice(company_id, quarter);
		}

		@Override
		public void updateCashFlowLixi(float lixi, int company_id, int quarter) {
			cashFlowMapper.updateCashFlowLixi(lixi, company_id, quarter);
		}

		@Override
		public void updateBalanceSheetCunKuan(float cunkuan, int company_id, int quarter) {
			cashFlowMapper.updateBalanceSheetCunKuan(cunkuan, company_id, quarter);
			
		}

		@Override
		public void updateBalanceSheetZiChan(float zichan, int company_id, int quarter) {
			cashFlowMapper.updateBalanceSheetZiChan(zichan, company_id, quarter);
		}

		@Override
		public void updateBalanceSheetGuBen(float guben, int company_id, int quarter) {
			cashFlowMapper.updateBalanceSheetGuBen(guben, company_id, quarter);
		}

		@Override
		public void updateBalanceSheetLiuCun(float liucun, int company_id, int quarter) {
			cashFlowMapper.updateBalanceSheetLiuCun(liucun, company_id, quarter);
		}

		@Override
		public void updateBalanceSheetHuoBi(float huobi, int company_id, int quarter) {
			
			cashFlowMapper.updateBalanceSheetHuoBi(huobi, company_id, quarter);
		}

		@Override
		public int selectPrice(int productId,int quarter) {
			// TODO Auto-generated method stub
			return cashFlowMapper.selectPrice(productId,quarter);
		}

		@Override
		public int selectDemandNum2(int productId) {
			// TODO Auto-generated method stub
			return cashFlowMapper.selectDemandNum2(productId);
		}
		
		@Override
		public int selectDemandNum3(int productId) {
			// TODO Auto-generated method stub
			return cashFlowMapper.selectDemandNum3(productId);
		}

		@Override
		public void updateCashFlowShouRu(int income, int company_id, int quarter) {
			// TODO Auto-generated method stub
			cashFlowMapper.updateCashFlowShouRu(income, company_id, quarter);
		}

		@Override
		public int selectTiQu(int company_id, int quarter) {
			// TODO Auto-generated method stub
			return cashFlowMapper.selectTiQu(company_id, quarter);
		}

		@Override
		public int selectCunKuanLast(int company_id, int quarter) {
			// TODO Auto-generated method stub
			return cashFlowMapper.selectCunKuanLast(company_id, quarter);
		}

		@Override
		public void updateCashFlowTiqu(int tiqu, int company_id, int quarter) {
			// TODO Auto-generated method stub
			cashFlowMapper.updateCashFlowTiqu(tiqu, company_id, quarter);
		}

		@Override
		public int selectYouJi(int productId) {
			// TODO Auto-generated method stub
			return cashFlowMapper.selectYouJi(productId);
		}

		@Override
		public void updateCashFlowYouJi(int youji, int company_id, int quarter) {
			// TODO Auto-generated method stub
			cashFlowMapper.updateCashFlowYouJi(youji, company_id, quarter);
		}

		@Override
		public int selectReport(int company_id, int quarter) {
			// TODO Auto-generated method stub
			return cashFlowMapper.selectReport(company_id, quarter);
		}

		@Override
		public void updateCashFlowDiaoYan(int diaoyan, int company_id, int quarter) {
			// TODO Auto-generated method stub
			cashFlowMapper.updateCashFlowDiaoYan(diaoyan, company_id, quarter);
		}

		@Override
		public void updateCashFlowHuoYun(int huoyun, int company_id, int quarter) {
			// TODO Auto-generated method stub
			cashFlowMapper.updateCashFlowHuoYun(huoyun, company_id, quarter);
		}

		@Override
		public int selectSaleSalary(int company_id, int quarter) {
			// TODO Auto-generated method stub
			return cashFlowMapper.selectSaleSalary(company_id, quarter);
		}

		@Override
		public void updateCashFlowSalerPay(int saleSalary, int company_id, int quarter) {
			// TODO Auto-generated method stub
			cashFlowMapper.updateCashFlowSalerPay(saleSalary, company_id, quarter);
		}

		@Override
		public int selectRent(int marketId) {
			// TODO Auto-generated method stub
			return cashFlowMapper.selectRent(marketId);
		}

		@Override
		public int selectWebRent(int marketId) {
			// TODO Auto-generated method stub
			return cashFlowMapper.selectWebRent(marketId);
		}

		@Override
		public List<CompanyMedia> selectProductMedia(int productId, int quarter) {
			// TODO Auto-generated method stub
			return cashFlowMapper.selectProductMedia(productId, quarter);
		}

		@Override
		public int selectMediaCost(int mediaId) {
			// TODO Auto-generated method stub
			return cashFlowMapper.selectMediaCost(mediaId);
		}

		@Override
		public void updateCashFlowMediaCost(int mediaCost, int company_id, int quarter) {
			// TODO Auto-generated method stub
			cashFlowMapper.updateCashFlowMediaCost(mediaCost, company_id, quarter);
		}

		@Override
		public void updateIncomeShouRu(int income, int company_id, int quarter) {
			// TODO Auto-generated method stub
			cashFlowMapper.updateIncomeShouRu(income, company_id, quarter);
		}

		@Override
		public void updateIncomeHuoYun(int huoyun, int company_id, int quarter) {
			// TODO Auto-generated method stub
			cashFlowMapper.updateIncomeHuoYun(huoyun, company_id, quarter);
		}

		@Override
		public void updateIncomeDiaoYan(int diaoyan, int company_id, int quarter) {
			// TODO Auto-generated method stub
			cashFlowMapper.updateIncomeDiaoYan(diaoyan, company_id, quarter);
		}

		@Override
		public void updateIncomeYouJi(int youji, int company_id, int quarter) {
			// TODO Auto-generated method stub
			cashFlowMapper.updateIncomeYouJi(youji, company_id, quarter);
		}

		@Override
		public void updateIncomeGuangGao(int guanggao, int company_id, int quarter) {
			// TODO Auto-generated method stub
			cashFlowMapper.updateIncomeGuangGao(guanggao, company_id, quarter);
		}

		@Override
		public CashFlow selectCashFlow(int company_id, int quarter) {
			// TODO Auto-generated method stub
			return cashFlowMapper.selectCashFlow(company_id, quarter);
		}

		@Override
		public BalanceSheet selectHuoBiLast(int company_id, int quarter) {
			// TODO Auto-generated method stub
			return cashFlowMapper.selectHuoBiLast(company_id, quarter);
		}

		@Override
		public void updateCashFlowResult(float lixi, float yanfa, float guanggao, float saler, float salesCenter,
				float salesCenterWeb, float diaoyan, float gongchang, float tiqu, float cunkuan, int company_id,
				int quarter) {
			// TODO Auto-generated method stub
			cashFlowMapper.updateCashFlowResult(lixi, yanfa, guanggao, saler, salesCenter, salesCenterWeb, diaoyan, gongchang, tiqu, cunkuan, company_id, quarter);
		}

		@Override
		public List<CashFlow> selectCashFlowResult(int company_id, int quarter) {
			// TODO Auto-generated method stub
			return cashFlowMapper.selectCashFlowResult(company_id, quarter);
		}

		@Override
		public void updateIncomeResult(float lixi, float yanfa, float guanggao, float saler, float salesCenter,
				float salesCenterWeb, float diaoyan, int company_id, int quarter) {
			// TODO Auto-generated method stub
			cashFlowMapper.updateIncomeResult(lixi, yanfa, guanggao, saler, salesCenter, salesCenterWeb, diaoyan, company_id, quarter);
		}

		@Override
		public List<IncomeStatement> selectIncomeStatementResult(int company_id, int quarter) {
			// TODO Auto-generated method stub
			return cashFlowMapper.selectIncomeStatementResult(company_id, quarter);
		}

		@Override
		public List<BalanceSheet> selectBalanceSheetResult(int company_id, int quarter) {
			// TODO Auto-generated method stub
			return cashFlowMapper.selectBalanceSheetResult(company_id, quarter);
		}

		@Override
		public BalanceSheet selectBalanceSheet(int company_id, int quarter) {
			// TODO Auto-generated method stub
			return cashFlowMapper.selectBalanceSheet(company_id, quarter);
		}

		@Override
		public void updateBalanceSheetResult(float cunkuan, float zichan, float guben, int company_id, int quarter) {
			// TODO Auto-generated method stub
			cashFlowMapper.updateBalanceSheetResult(cunkuan, zichan, guben, company_id, quarter);
		}

		@Override
		public void updateBalanceSheetResult2(float huobi, float liucun,float cunhuo, int company_id, int quarter) {
			// TODO Auto-generated method stub
			cashFlowMapper.updateBalanceSheetResult2(huobi, liucun,cunhuo, company_id, quarter);
		}

		@Override
		public void insertStock(int company_id, int quarter, String stock_type, String owner, int stockNumber,
				int stockPrice, int totalPrice) {
			// TODO Auto-generated method stub
			cashFlowMapper.insertStock(company_id, quarter, stock_type, owner, stockNumber, stockPrice, totalPrice);
		}

		@Override
		public int selectKucunNum2(int productId) {
			// TODO Auto-generated method stub
			return cashFlowMapper.selectKucunNum2(productId);
		}

		@Override
		public void updateCashFlowKuCun(int kucun, int company_id, int quarter) {
			// TODO Auto-generated method stub
			cashFlowMapper.updateCashFlowKuCun(kucun, company_id, quarter);
		}

		@Override
		public ProductPrice showPrice(int productId, int quarter) {
			// TODO Auto-generated method stub
			return cashFlowMapper.showPrice(productId, quarter);
		}

		@Override
		public CompanyReport showReport(int company_id, int quarter) {
			// TODO Auto-generated method stub
			return cashFlowMapper.showReport(company_id, quarter);
		}

		@Override
		public void updateIncomeYingYe(int yingyeCost, int company_id, int quarter) {
			// TODO Auto-generated method stub
			cashFlowMapper.updateIncomeYingYe(yingyeCost, company_id, quarter);
		}

		@Override
		public void updateIncomeSaler(int saler, int company_id, int quarter) {
			// TODO Auto-generated method stub
			cashFlowMapper.updateIncomeSaler(saler, company_id, quarter);
		}

		@Override
		public void updateIncomeKuCun(int kucun, int company_id, int quarter) {
			// TODO Auto-generated method stub
			cashFlowMapper.updateIncomeKuCun(kucun, company_id, quarter);
		}

		@Override
		public CompanyCapacity showCapacity(int company_id, int quarter) {
			// TODO Auto-generated method stub
			return cashFlowMapper.showCapacity(company_id, quarter);
		}

		@Override
		public int selectProductCost(int productId) {
			// TODO Auto-generated method stub
			return cashFlowMapper.selectProductCost(productId);
		}

		@Override
		public void updateCashFlowShengChan(float shengchan, int company_id, int quarter) {
			// TODO Auto-generated method stub
			cashFlowMapper.updateCashFlowShengChan(shengchan, company_id, quarter);
		}

		@Override
		public int selectKucunNum3(int productId) {
			// TODO Auto-generated method stub
			return cashFlowMapper.selectKucunNum3(productId);
		}

		@Override
		public int selectSaleResult(int productId, int quarter) {
			// TODO Auto-generated method stub
			return cashFlowMapper.selectSaleResult(productId, quarter);
		}

		@Override
		public int selectKuCunResult(int productId, int quarter) {
			// TODO Auto-generated method stub
			return cashFlowMapper.selectKuCunResult(productId, quarter);
		}

		@Override
		public void updateCashFlowResult2(float incomeSum, float youjiSum, float shengchanSum, float huoyunSum,
				float kucunSum, int company_id, int quarter) {
			// TODO Auto-generated method stub
			cashFlowMapper.updateCashFlowResult2(incomeSum, youjiSum, shengchanSum, huoyunSum, kucunSum, company_id, quarter);
		}

		@Override
		public void insertCashFlowResult(int company_id, int quarter) {
			// TODO Auto-generated method stub
			cashFlowMapper.insertCashFlowResult(company_id, quarter);
		}

		@Override
		public void insertIncomeResult(int company_id, int quarter) {
			// TODO Auto-generated method stub
			cashFlowMapper.insertIncomeResult(company_id, quarter);
		}

		@Override
		public void updateIncomeResult2(float incomeSum, float yingyeSum, float youjiSum, float huoyunSum, float kucunSum,
				int company_id, int quarter) {
			// TODO Auto-generated method stub
			cashFlowMapper.updateIncomeResult2(incomeSum, yingyeSum, youjiSum, huoyunSum, kucunSum, company_id, quarter);
		}

		@Override
		public void insertBalanceResult(int company_id, int quarter) {
			// TODO Auto-generated method stub
			cashFlowMapper.insertBalanceResult(company_id, quarter);
		}

		@Override
		public void updateBalanceSheetCunHuo(int cunhuo, int company_id, int quarter) {
			// TODO Auto-generated method stub
			cashFlowMapper.updateBalanceSheetCunHuo(cunhuo, company_id, quarter);
		}

		@Override
		public CompanyCapacity selectCompanyCapacity(int companyId, int quarter) {
			// TODO Auto-generated method stub
			return companyMapper.selectCompanyCapacity(companyId, quarter);
		}
		
		@Override
		public ProductPrice selectProductPrice2(int productId, int quarter) {
			// TODO Auto-generated method stub
			return cashFlowMapper.selectProductPrice2(productId, quarter);
		}

		@Override
		public void insertProductPrice2(int productId, int companyId, int quarter) {
			// TODO Auto-generated method stub
			cashFlowMapper.insertProductPrice2(productId, companyId, quarter);
		}
		
		@Override
		public CompanyProduct selectCompanyProduct(int productId) {
			// TODO Auto-generated method stub
			return cashFlowMapper.selectCompanyProduct(productId);
		}

		@Override
		public void insertHirePeopleOnline(HirePeopleOnline hirePeopleOnline) {
			companyMapper.insertHirePeopleOnline(hirePeopleOnline);
		}

		@Override
		public HirePeopleOnline checkHirePeopleOnline(HirePeopleOnline hirePeopleOnline) {
			
			return companyMapper.checkHirePeopleOnline(hirePeopleOnline);
		}

		@Override
		public void deleteHirePeopleOnline(HirePeopleOnline hirePeopleOnline) {
			companyMapper.deleteHirePeopleOnline(hirePeopleOnline);
			
		}

		@Override
		public void deleteHirePeople(HirePeople hirePeople) {
			companyMapper.deleteHirePeople(hirePeople);
		}

		@Override
		public HirePeople checkHirePeople(HirePeople hirePeople) {
			// TODO Auto-generated method stub
			return companyMapper.checkHirePeople(hirePeople);
		}

		@Override
		public HirePeopleOnline selectHirePeopleOnline(int companyId, int quarter) {
			return companyMapper.selectHirePeopleOnline(companyId, quarter);
		}

		@Override
		public void updateHirePeopleOnlineById(HirePeopleOnline hirePeopleOnline) {
			companyMapper.updateHirePeopleOnlineById(hirePeopleOnline);
			
		}
		
		@Override
		public List<ProductMarketShare> selectProductMarketShare(Integer id,Integer quarter) {
			
			return companyMapper.selectProductMarketShare(id,quarter);
		}

		@Override
		public List<CompanyInvestment> selectCompanyInvestment(int companyId, int quarter) {
			return companyMapper.selectCompanyInvestment(companyId, quarter);
		}

		@Override
		public CompanyLoan selectCompanyLoan(int company_id, int quarter) {
			return companyMapper.selectCompanyLoan(company_id, quarter);
		}

		@Override
		public void insertCompanyLoan(int company_id, int quarter, float get, float payBack) {
			// TODO Auto-generated method stub
			companyMapper.insertCompanyLoan(company_id, quarter, get, payBack);
		}

		@Override
		public void updateCompanyLoan(int company_id, int quarter, float get, float payBack) {
			// TODO Auto-generated method stub
			companyMapper.updateCompanyLoan(company_id, quarter, get, payBack);
		}

		@Override
		public void insertLoanLast(float loanLast, int company_id, int quarter) {
			// TODO Auto-generated method stub
			companyMapper.insertLoanLast(loanLast, company_id, quarter);
		}

		@Override
		public List<HirePeople> selectHirePeopleList(int companyId, int quarter) {
			// TODO Auto-generated method stub
			return companyMapper.selectHirePeopleList(companyId, quarter);
		}
		
		@Override
		public List<HirePeopleOnline> selectHirePeopleOnlineList(int companyId, int quarter) {
			// TODO Auto-generated method stub
			return companyMapper.selectHirePeopleOnlineList(companyId, quarter);
		}

		@Override
		public DemandForecast getDemandForecastByCompanyIdAndQuarter(int companyId, int quarter) {
			// TODO Auto-generated method stub
			return companyMapper.getDemandForecastByCompanyIdAndQuarter(companyId, quarter);
		}

		@Override
		public List<CompanyProductVo> selectCompanyProduct(int companyId, int quarter,int quarter2) {
			// TODO Auto-generated method stub
			return companyMapper.selectCompanyProduct(companyId, quarter,quarter2);
		}

		@Override
		public void insertCompanyProductDemandById(int id, int quarter) {
			// TODO Auto-generated method stub
			companyMapper.insertCompanyProductDemandById(id, quarter);
		}

		@Override
		public void deleteCompanyProductDemand(int productId) {
			// TODO Auto-generated method stub
			companyMapper.deleteCompanyProductDemand(productId);
		}

		@Override
		public void deleteProductPrice(int productId) {
			// TODO Auto-generated method stub
			companyMapper.deleteProductPrice(productId);
		}

		@Override
		public void updateProductDemand(int productId, int quarter, int demand) {
			companyMapper.updateProductDemand(productId, quarter, demand);
		}

		@Override
		public void updateCompanyProductInventory(int productId, int inventory, int quarter) {
			// TODO Auto-generated method stub
			companyMapper.updateCompanyProductInventory(productId, inventory, quarter);
		}

		@Override
		public void deleteCompanyProductInventory(int productId) {
			// TODO Auto-generated method stub
			companyMapper.deleteCompanyProductInventory(productId);
		}

		@Override
		public void insertCompanyProductInventoryById(int id, int i) {
			// TODO Auto-generated method stub
			companyMapper.insertCompanyProductInventoryById(id, i);
		}

		@Override
		public void deleteCompanyMedia(int productId) {
			companyMapper.deleteCompanyMedia(productId);
			
		}

		@Override
		public void deleteCompanyAdvertise(int productId) {
			// TODO Auto-generated method stub
			companyMapper.deleteCompanyAdvertise(productId);
		}
		
		@Override
		public List<CompanyProductVo2> selectInvertoryCountFinancialRatio(int companyIdInt, int quarter) {
			// TODO Auto-generated method stub
			return companyMapper.selectInvertoryCountFinancialRatio(companyIdInt, quarter);
		}

		@Override
		public List<CompanyProduct> selectCompanyProductByCompanyIdQuarter(int companyId, int quarter) {
			// TODO Auto-generated method stub
			return companyMapper.selectCompanyProductByCompanyIdQuarter(companyId, quarter);
		}

		@Override
		public int selectProductDemand(int productId, int quarter) {
			// TODO Auto-generated method stub
			return cashFlowMapper.selectProductDemand(productId, quarter);
		}

		@Override
		public int selectProductKuCun(int productId, int quarter) {
			// TODO Auto-generated method stub
			return cashFlowMapper.selectProductKuCun(productId, quarter);
		}

		@Override
		public List<CashFlow> showCashFlowResult(int companyId, int quarter) {
			// TODO Auto-generated method stub
			return cashFlowMapper.showCashFlowResult(companyId, quarter);
		}

		@Override
		public List<BalanceSheet> showBalanceSheetResult(int companyId, int quarter) {
			// TODO Auto-generated method stub
			return cashFlowMapper.showBalanceSheetResult(companyId, quarter);
		}

		@Override
		public List<IncomeStatement> showIncomeStatementResult(int companyId, int quarter) {
			// TODO Auto-generated method stub
			return cashFlowMapper.showIncomeStatementResult(companyId, quarter);
		}
		// 查看业内工厂工人薪酬
		@Autowired
		private CompetitionMapper CW;
		
		@Override
		public List<AllWorkersSalaryVo> findWSalaryofAllCompanysbyCompanyID(int companyId, int quarter) {

			Competition comp = CW.findCompetitionByCompanyId(companyId);
			System.out.println("competition:" + comp);

			List<Company> LC = new ArrayList<>();

			LC = companyMapper.showCompanyByCompetitionId(comp.getId());

			List<AllWorkersSalaryVo> allVoList = new ArrayList<>();

			for (Company cc : LC) {
				List<WorkersSalary> LSS = new ArrayList<>();
				LSS = companyMapper.selectCompanyWorkersSalary(cc.getId(), quarter);
				AllWorkersSalaryVo ASSV = new AllWorkersSalaryVo();
				List<CompanyInvestment> CI = new ArrayList<>();
				CI =companyMapper.selectCompanyInvestment(cc.getId(), quarter);
				ASSV.setCompany(cc);
				ASSV.setWorkersSalary(LSS.get(0));
				ASSV.setWorkerEfficiency(CI.get(0).getWorkerEfficiency());;
				allVoList.add(ASSV);
			}

			return allVoList;
		}
		
		@Override
		public List<AllSalesSalaryVo> findSalaryofAllCompanysbyCompanyID(int companyId, int quarter) {
			Competition comp = new Competition();

			comp = CM.findCompetitionByCompanyId(companyId);

			List<Company> LC = new ArrayList<>();

			LC = companyMapper.showCompanyByCompetitionId(comp.getId());

			List<AllSalesSalaryVo> allVoList = new ArrayList<>();

			for (Company cc : LC) {
				List<SalesSalary> LSS = new ArrayList<>();
				LSS = companyMapper.selectCompanySalesSalary(cc.getId(), quarter);
				 List<CompanyInvestment> CI = new ArrayList<>();
				 CI =companyMapper.selectCompanyInvestment(cc.getId(), quarter);
				
				
				AllSalesSalaryVo ASSV = new AllSalesSalaryVo();
				System.out.println("Length of LC = " + LC.size());
				System.out.println("Length of LSS = " + LSS.size());
				System.out.println("cc = " + cc);
				System.out.println(cc.getName());

				System.out.println("cc.getId() = " + cc.getId());
				ASSV.setCompany(cc);
				ASSV.setSalesSalary(LSS.get(0));
				ASSV.setSalesEfficiency(CI.get(0).getSalesEfficiency());;
				
				allVoList.add(ASSV);
			}

			return allVoList;
		}

		@Override
		public void updateIncomeStatementTax(float tax, int company_id, int quarter) {
			// TODO Auto-generated method stub
			cashFlowMapper.updateIncomeStatementTax(tax, company_id, quarter);
		}

		@Override
		public void updateIncomeStatementResultTax(float tax, int company_id, int quarter) {
			// TODO Auto-generated method stub
			cashFlowMapper.updateIncomeStatementResultTax(tax, company_id, quarter);
		}

		@Override
		public void updateBalanceSheetResult3(float ziben, int company_id, int quarter) {
			// TODO Auto-generated method stub
			cashFlowMapper.updateBalanceSheetResult3(ziben, company_id, quarter);
		}

		@Override
		public void updateBalanceSheet3(float ziben, int company_id, int quarter) {
			// TODO Auto-generated method stub
			cashFlowMapper.updateBalanceSheet3(ziben, company_id, quarter);
		}

		@Override
		public void updateCashFlowJiChu(float jichuXianjin, int company_id, int quarter) {
			// TODO Auto-generated method stub
			cashFlowMapper.updateCashFlowJiChu(jichuXianjin, company_id, quarter);
		}

		@Override
		public void updateCashFlowResultJiChu(float jichuXianjin, int company_id, int quarter) {
			// TODO Auto-generated method stub
			cashFlowMapper.updateCashFlowResultJiChu(jichuXianjin, company_id, quarter);
		}
		
		@Override
	    public List<OnlineStore> selectAndCountTotalNeed(List<MarketInfo> marketInfoList) {
	        List<OnlineStore> list = new LinkedList<>();
	        OnlineStore onlineStore = new OnlineStore();
	        int perfect = 0, business = 0, practice = 0, open = 0, rent = 0;
	        if (marketInfoList.size() != 0) {
	            for (int i = 0; i < marketInfoList.size(); i++) {
	                perfect += marketInfoList.get(i).getWebPerfect();
	                business += marketInfoList.get(i).getWebBusiness();
	                practice += marketInfoList.get(i).getWebPractical();
	                open += marketInfoList.get(i).getWebOpen();
	                rent += marketInfoList.get(i).getWebRent();
	            }
	            onlineStore.setPractice(practice);
	            onlineStore.setPerfect(perfect);
	            onlineStore.setBusiness(business);
	            onlineStore.setOpen(open);
	            onlineStore.setRent(rent);
	            list.add(onlineStore);
	            return list;
	        } else {
	            return null;
	        }

	    }
		
		@Override
	    public CompanyMarket selectCompanyMarket(Integer companyId, Integer isPhy, Integer quarter) {
	        return companyMapper.selectCompanyMarket(companyId, isPhy, quarter);
	    }
		@Override
		public void calCashFlowResult(int company_id, int quarter) {
			// TODO Auto-generated method stub
			List<CashFlow> selectCashFlowResult = selectCashFlowResult(company_id, quarter);
			if(selectCashFlowResult.size()==0){
				insertCashFlowResult(company_id, quarter);
			}
			//更新固定费用到结果表
			CashFlow cashFlow=selectCashFlow(company_id, quarter);
			float lixi=cashFlow.getLixiGet();
			float yanfa=cashFlow.getYanfaPay();
			float guanggao=cashFlow.getGuanggaoPay();
			float saler=cashFlow.getSalerPay();
			float salesCenter=cashFlow.getSalescenterPay();
			float salesCenterWeb=cashFlow.getSalescenterWebPay();
			float diaoyan=cashFlow.getDiaoyanPay();
			float gongchang=cashFlow.getGongchangPay();
			float tiqu=cashFlow.getCunkuanRegularGet();
			float cunkuan=cashFlow.getCunkuanRegularPay();
			updateCashFlowResult(lixi, yanfa, guanggao, saler, salesCenter, salesCenterWeb, diaoyan, gongchang, tiqu, cunkuan, company_id, quarter);
			if(quarter!=1){
				List<CompanyProduct> companyProducts=selectProductByCompanyIdAndQuarter(company_id, 1);
				for(int i=2;i<=quarter;i++){
					companyProducts.addAll(selectProductByCompanyIdAndQuarter(company_id, i));
				}
				//计算收入,邮寄，生产，货运，库存
				float incomeSum=0;
				float youjiSum=0;
				float shengchanSum=0;
				float kucunSum=0;
				float huoyunSum=0;
				for(int i=0;i<companyProducts.size();i++){
					int productId=companyProducts.get(i).getId();
					int productCost=selectProductCost(productId);
					int productPrice=0;
					int youji=0;
					ProductPrice price=showPrice(productId, quarter);
					if(price!=null){
						youji=price.getYouji();
						productPrice=price.getPrice();
					}
					int saleNum=selectSaleResult(productId, quarter);
					int kucunNum=selectKuCunResult(productId, quarter);
					int shengchanCost=companyProducts.get(i).getShengChanCost((saleNum+kucunNum), productCost);//生产成本
					
					shengchanSum+=shengchanCost*(saleNum+kucunNum);//总生产
					kucunSum+=shengchanCost*kucunNum/10;//库存费用
					incomeSum+=productPrice*saleNum;//总收入
					youjiSum+=youji*saleNum;//总邮寄费用
					huoyunSum+=saleNum*100;//货运
				}
				updateCashFlowResult2(incomeSum, youjiSum, shengchanSum, huoyunSum, kucunSum, company_id, quarter);
			}
			if(quarter!=1){
				float jichuXianjin=selectHuoBiLast(company_id, quarter-1).getHuobi();
				updateCashFlowResultJiChu(jichuXianjin, company_id, quarter);
			}
		}

		@Override
		public void calIncomeResult(int company_id, int quarter) {
			// TODO Auto-generated method stub
			calCashFlowResult(company_id,quarter);
			List<IncomeStatement> selectIncomeStatementResult = selectIncomeStatementResult(company_id, quarter);
			if(selectIncomeStatementResult.size()==0){
				insertIncomeResult(company_id, quarter);
			}
			List<CashFlow> cashFlow = selectCashFlowResult(company_id, quarter);
			float lixi=cashFlow.get(0).getLixiGet();
			float yanfa=cashFlow.get(0).getYanfaPay();
			float guanggao=cashFlow.get(0).getGuanggaoPay();
			float diaoyan=cashFlow.get(0).getDiaoyanPay();
			float saler=cashFlow.get(0).getSalerPay();
			float salesCenter=cashFlow.get(0).getSalescenterPay();
			float salesCenterWeb=cashFlow.get(0).getSalescenterWebPay();
			updateIncomeResult(lixi, yanfa, guanggao, saler, salesCenter, salesCenterWeb, diaoyan, company_id, quarter);
			
			float incomeSum=cashFlow.get(0).getXiaoshouGet();
			float yingyeSum=cashFlow.get(0).getShengchanPay();
			float youjiSum=cashFlow.get(0).getFankuanPay();
			float huoyunSum=cashFlow.get(0).getHuoyunPay();
			float kucunSum=cashFlow.get(0).getKucunPay();
			updateIncomeResult2(incomeSum, yingyeSum, youjiSum, huoyunSum, kucunSum, company_id, quarter);
			//所得税费用(净利润*25%)
			float netProfit=incomeSum+lixi-yingyeSum-youjiSum-yanfa-guanggao-saler-salesCenter-salesCenterWeb-
					diaoyan-huoyunSum-kucunSum;
			float tax=0;
			if(netProfit>0){
				tax=(float) (netProfit*0.25);
			}
			updateIncomeStatementResultTax(tax, company_id, quarter);
		}

		@Override
		public void calBalanceSheetResult(int company_id, int quarter) {
			calCashFlowResult(company_id,quarter);
			List<BalanceSheet> balanceResult=selectBalanceSheetResult(company_id, quarter);
			if(balanceResult.size()==0){
				insertBalanceResult(company_id, quarter);
			}
			BalanceSheet balanceSheet=selectBalanceSheet(company_id, quarter);
			float cunkuan=balanceSheet.getCunkuan();
			float zichan=balanceSheet.getZichan();
			float guben=balanceSheet.getGuben();
			//货币，留存，存货
			int cunru=selectCunru(company_id, quarter);
			int tiqu=selectTiQu(company_id, quarter);
			int cunkuanLast=selectCunKuanLast(company_id, quarter);
			List<CashFlow> cashFlow = selectCashFlowResult(company_id, quarter);
			int cunhuo=(int)(cashFlow.get(0).getKucunPay()*10);
			float xianjinGet=cashFlow.get(0).getXianJinGet();
			float xianjinPay=cashFlow.get(0).getXianJinPay();
			float lirun=xianjinGet-xianjinPay;
			float gongchang=cashFlow.get(0).getGongchangPay();
			float huobi=0;
			float liucun=0; 
			if(quarter==1){
				huobi=2000000+lirun-gongchang-cunru+tiqu-cunhuo;
				liucun=lirun;
			}else{
				BalanceSheet balanceSheet2=selectHuoBiLast(company_id, quarter-1);
				float huobiLast=balanceSheet2.getHuobi();
				huobi=huobiLast+1000000+lirun-gongchang-cunru+tiqu-cunhuo-(float)(cunkuanLast*1.5/100);
				float liucunLast=balanceSheet2.getLiucun();
				liucun=liucunLast+lirun;
			}
			updateBalanceSheetResult(cunkuan, zichan, guben, company_id, quarter);
			updateBalanceSheetResult2(huobi, liucun, cunhuo, company_id, quarter);
			updateBalanceSheetResult3(0, company_id, quarter);
			
//		 	将货币字段添加到decision表
			CompanyDecision companyDecision=null;
			companyDecision=selectCompanyDecision(company_id, 100);
			if(companyDecision==null){
				insertCompanyDecision(company_id, (int)huobi, 100);
			}else{
				updateCompanyDecision(company_id,(int)huobi , 100);
			}
			
		}
		
		@Override
	    public void deleteCompanyMarket(CompanyMarket companyMarket1) {
	        companyMapper.deleteCompanyMarket(companyMarket1);
	    }

		@Override
		public void updateCompanyQuarterTime(Integer companyId, int quarter, String endTime) {
			// TODO Auto-generated method stub
			companyMapper.updateCompanyQuarterTime(companyId, quarter, endTime);
		}

		@Override
		public int selectCompanyMarket1(int companyId, int isPhy) {
			return companyMapper.selectCompanyMarket1(companyId,isPhy);
		}
		
		@Override
	    public void updateCompanyMarket1(CompanyMarket companyMarket) {
	         companyMapper.updateCompanyMarket1(companyMarket);
	    }

	    @Override
	    public void updateCompanyMarket2(CompanyMarket companyMarket) {
	        companyMapper.updateCompanyMarket2(companyMarket);
	    }
	    public CompanyProductDemand selectDemand(int productId, int quarter) {
			// TODO Auto-generated method stub
			return cashFlowMapper.selectDemand(productId, quarter);
		}

		@Override
		public CompanyProductInventory selectInventory(int productId, int quarter) {
			// TODO Auto-generated method stub
			return cashFlowMapper.selectInventory(productId, quarter);
		}
		@Override
		public void updateCashFlowLoan(int company_id, int quarter, float getLoan, float returnLoan) {
			// TODO Auto-generated method stub
			cashFlowMapper.updateCashFlowLoan(company_id, quarter, getLoan, returnLoan);
		}

		@Override
		public void updateBalanceSheetLoan(int company_id, int quarter, float totalLoan, float lixiLoan) {
			// TODO Auto-generated method stub
			cashFlowMapper.updateBalanceSheetLoan(company_id, quarter, totalLoan, lixiLoan);
		}

		@Override
		public void updateCashResultLoan(int company_id, int quarter, float getLoan, float payLoan) {
			// TODO Auto-generated method stub
			cashFlowMapper.updateCashResultLoan(company_id, quarter, getLoan, payLoan);
		}

		@Override
		public void updateBalanceSheetResultLoan(int company_id, int quarter, float totalLoan, float lixiLoan) {
			// TODO Auto-generated method stub
			cashFlowMapper.updateBalanceSheetResultLoan(company_id, quarter, totalLoan, lixiLoan);
		}
		
		@Override
		public void insertGuben(int company_id, int quarter) {
			// TODO Auto-generated method stub
			List<CompanyStock> list = companyMapper.showCompanyStock(company_id, quarter);
			if (list.size() == 0) {
				cashFlowMapper.insertStock(company_id, quarter, "普通股", "经营团队", 5000, 200, 1000000);
			}
		}
	    
	    @Override
		public int selectDecision(int company_id) {
			// TODO Auto-generated method stub
			return cashFlowMapper.selectDecision(company_id);
		}
	    
	    @Override
	    public CompanyDecision selectCompanyDecision(int company_id, int decision_id) {
	        return companyMapper.selectCompanyDecision(company_id,decision_id);
	    }

	    @Override
	    public void updateCompanyDecision(int company_id, int money, int decision_id) {
	        companyMapper.updateCompanyDecision(company_id,money,decision_id);
	    }

	    @Override
	    public void insertCompanyDecision(int company_id, int money, int decision_id) {
	        companyMapper.insertCompanyDecision(company_id,money,decision_id);
	    }

	@Override
	public void updateProductPrice2(ProductPrice productPrice) {
		companyMapper.updateProductPrice2(productPrice);
	}

	@Override
	public void deleteAllCompanyDecision(int company_id) {

		cashFlowMapper.deleteAllCompanyDecision(company_id);
		
	}

	@Override
	public void updateAllCompanyDecision(int company_id,int quarter) {
//		进入下一季度，清空company_decision表，并添加编号为100的季初可用资金，quarter当季度的值
		deleteAllCompanyDecision(company_id);
		List<BalanceSheet> balanceSheet=selectBalanceSheetResult(company_id, quarter);
		if(balanceSheet.size()!=0){
			float huobi=balanceSheet.get(0).getHuobi();
			insertCompanyDecision(company_id, (int)huobi, 100);
		}
		
	}

	@Override
	public MarketOpenedPo selectMarketOpen(int company_id) {
		// TODO Auto-generated method stub
		return cashFlowMapper.selectMarketOpen(company_id);
	}

	@Override
	public MarketWebOpened selectMarketWebOpen(int company_id, int quarter) {
		// TODO Auto-generated method stub
		return cashFlowMapper.selectMarketWebOpen(company_id, quarter);
	}
	


}
