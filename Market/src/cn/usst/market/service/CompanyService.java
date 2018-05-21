package cn.usst.market.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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

public interface CompanyService {

    public void insert(Company record);

    Company findCompanyByCondition(Company record);

    Company findCompanyById(Company record);


    void updateCompanyName(Company record);

    List<Company> showCompanyByCompetitionId(Integer id);

    List<String> countStrategyTitle();

    List<StrategyInfo> showStrategyInfoBytitle(String title);

    List<String> countProductInfoTitle();

    List<ProductInfo> showProductInfoByTitle(String title);

    List<String> countCompanyRuleTitle();

    List<StrategyInfo> showCompanyRuleInfoBytitle(String title);

    List<String> countPersonalGoalTitle();

    List<StrategyInfo> showPersonalGoalInfoBytitle(String title);

    List<Duty> showAllPosition();

    List<MarketInfo> showMarketInfo(int competitionId);


    //Guan
    public List<TeacherQueryVo> findCompanyList(TeacherQueryVo teacherQueryVo) throws Exception;

    public List<TeacherQueryVo> selectCompanyByPage(TeacherQueryVo teacherQueryVo);

    public long getCompanyCount(TeacherQueryVo teacherQueryVo);


    //zhao

    Company checkCompanyNameExist(String name);

    Company selectCompanyById(Integer id);

    void updateCompanyNameById(Integer id, String name);

    public CompanyStrategy selectCompanyStrategy(Integer company_id, Integer quarter);

    int insertCompanyStrategy(CompanyStrategy companyStrategy) throws Exception;

    void updateCompanyStrategy(CompanyStrategy companyStrategy) throws Exception;

    public List<ProductInfo> showProductDetailByTitle(String title);

    CompanyDuty selectMemberByMemberId(Integer id);

    void insertMemberDuty(CompanyDuty companyDuty);

    void updateMemberDuty(CompanyDuty companyDuty);

    CompanyDuty selectMemberDutyByMemberId(Integer id);

    List<Integer> selectMemberIdByCompanyId(Integer id);

    List<CompanyDuty> selectMemberDutyByCompanyId(Integer id);

    CompanyRule selectCompanyRule(Integer company_id, Integer quarter);

    void insertCompanyRule(CompanyRule companyRule);

    void updateCompanyRule(CompanyRule companyRule);

    CompanyPersonGoal selectCompanyPersonGoal(Integer company_id, Integer quarter);

    void insertCompanyPersonGoal(CompanyPersonGoal companyPersonGoal);

    void updateCompanyPersonGoal(CompanyPersonGoal companyPersonGoal);


    void insertmarketinfo(CompanyMarket mInfo);

    List<CompanyMarket> showCompanymarket(CompanyMarket companyMarket);

    CompanyMarket selectCompanyPhysicalMarket(Integer company_id, Integer quarter);

    void insertCompanyMarket(CompanyMarket companyMarket);

    void updateCompanyMarket(CompanyMarket companyMarket);

    CashFlow selectCashFlowByCompanyId(int companyId, int quarter);

    void insertCashFlow(@Param("companyId") int companyId, @Param("quarter") int quarter, @Param("yanfaPay") float yanfaPay);

    void updateCashFlowYanFa(float yanfa_pay, int company_id, int quarter);

    void updateCashFlowGongChang(float invest, int company_id, int quarter);

    int selectSalesCenterOpen(int market_id);

    int selectSalesCenterWebOpen(int market_id);

    void updateCashFlowMarket(float salescenter_pay, int company_id, int quarter);


    IncomeStatement selectIncomeStatementByCompanyId(int companyId, int quarter);

    void updateIncomeStatementYanFa(float yanfa_pay, int company_id, int quarter);

    void updateIncomeStatementMarket(float salescenter_pay, int company_id, int quarter);


    void insertCompanyProduct(CompanyProduct companyProduct);

    public List<CompanyProduct> selectProductByCompanyId(Integer companyId);

    public CompanyProduct selectProductByProductId(Integer id);

    List<ProductInfo> showAllDetail();

    void updateCompanyProduct(CompanyProduct companyProduct);

    public void deleteProductById(Integer id);

    public void deleteCompanyProduct(Integer product_id);


    public int selectTotalCount(int companyId, int i);

    public CapacityInfo selectCapacityInfo(int number);

    public void insertCompanyCapacity(int capacityNow, int number, int companyId, int quarter);

    public List<CapacityInfo> showCapacityInfo();

    public List<CompanyCapacity> showCapacityInfo1(int companyId, int quarter);

    void updateCompanyCapacity(int capacity_now, int capacity_add, int companyId, int quarter);


    int selectGuBen(int compant_id, int quarter);

    public List<BalanceSheet> showBalanceSheet(int company_id, int quarter);

    public List<CashFlow> showCashFlow(int company_id, int quarter);

    public List<IncomeStatement> showIncomeStatement(int company_id, int quarter);

    public List<CompanyStock> showCompanyStock(int company_id, int quarter);


    List<FixedDeposit> showFixedDeposit();

    void insertFixedDeposite(int companyIdInt, float tiQuFloat, float cunRuFloat, int quarter, float cunkunLast);

    List<FixedDeposit> selectFixedDeposite(int companyIdInt, int quarter);

    void updateFixedDeposite(int companyIdInt, float tiQuFloat, float cunRuFloat, int quarter);


    void insertCashFlowFirst(CashFlow cashFlow);

    void insertIncomeStatementFirst(IncomeStatement incomeStatement);

    void insertBalanceSheetFirst(BalanceSheet balanceSheet);


    AverageSalary showAverageSalaryOfSale();

    AverageSalary showAverageSalaryOfWork();

    public void insertCompanyStock(CompanyStock cs);

    public void insertCompanyDuty(CompanyDuty cd);


    //工厂工人薪酬

    public List<AllWorkersSalaryVo> findWSalaryofAllCompanysbyCompanyID(int companyId, int quarter);

    List<WorkersSalary> selectCompanyWorkersSalary(int company_id, int quarter);

    void insertCompanyWorkersSalary(WorkersSalary workersSalary);

    void updateCompanyWorkersSalary(WorkersSalary workersSalary);

    //销售人员薪酬

    public List<AllSalesSalaryVo> findSalaryofAllCompanysbyCompanyID(int companyId, int quarter);

    List<SalesSalary> selectCompanySalesSalary(int company_id, int quarter);

    SalesSalary findCompanySalesSalary(int company_id, int quarter);

    void insertCompanySalesSalary(SalesSalary salesSalary);

    void updateCompanySalesSalary(SalesSalary salesSalary);


    //需求量预测
    List<DemandForecast> showDemandForecast();

    void insertDemandForecast(int companyIdInt, int quarter, int demandAveragePhyt, int demandAverageWebt);

    List<DemandForecast> selectDemandForecast(int companyIdInt, int quarter);

    void updateDemandForecast(int companyIdInt, int quarter, int demandAveragePhyt, int demandAverageWebt);


    void insertDemandForecast2(CompanyProduct companyProduct);

    List<CompanyProduct> selectDemandForecast2(int companyIdInt, int quarter);

    void updateDemandForecast2(CompanyProduct companyProduct);


    public List<CompanyProduct> selectProductByCompanyIdAndQuarter(Integer companyId, Integer quarter);


    void insertProductPrice(ProductPrice productPrice);

    void updateProductPrice(int productId, int quarter, int price, int youji);

    List<ProductPrice> selectProductPrice(int company_id, int quarter);

    List<ProductPrice> showTotalProductPrice(int company_id, int quarter);

    List<CompanyProduct> showTotalCompanyProduct(int company_id, int quarter);


    //主流媒体投放
    List<CompanyMedia> selectTotalCompanyMedia(int company_id, int quarter);

    List<CompanyMedia> selectCompanyMedia(int company_id, int quarter, int mediaId, int productId);

    void insertCompanyMedia(CompanyMedia companyMedia);

    void updateCompanyMedia(CompanyMedia companyMedia);

    //设计广告语
    List<CompanyAdvertise> selectCompanyAdvertiseByProductId(int company_id, int quarter, int productId);

    void insertCompanyAdvertise(CompanyAdvertise companyAdvertise);

    void updateCompanyAdvertise(CompanyAdvertise companyAdvertise);

    //	检查广告语

    List<CompanyAdvertise> selectCompanyAdvertise(int company_id, int quarter);

    List<AdvertiseInfo> selectAdvertsieById(int advertiseId);

    //市场调研报告
    String selectCompanyReport(int company_id, int quarter);

    void insertCompanyReport(int company_id, int quarter, int buyReport);

    void updateCompanyReport(int company_id, int quarter, int buyReport);

    //运行产能

    List<OperationCapacity> showOperationCapacity();

    void insertOperationCapacity(int companyIdInt, int quarter, int operateCapacityInt, int workerProductivityInt);

    List<OperationCapacity> selectOperationCapacity(int companyIdInt, int quarter);

    void updateOperationCapacity(int companyIdInt, int quarter, int operateCapacityInt, int workerProductivityInt);

    //库存控制

    void insertInventoryControl(CompanyProduct companyProduct);

    List<CompanyProductVo2> selectInventoryControl(int companyIdInt, int quarter);

    void updateInventoryControl(CompanyProduct companyProduct);

    //雇佣销售人员

    public HirePeopleVo selectHirePeople(int companyId, int marketInt, int quarter);

    public HirePeopleOnline selectHirePeopleOnline(int companyId, int quarter);


    //更新销售人员
    public void updateHirePeopleById(HirePeople hirePeople);

    public void updateHirePeopleOnlineById(HirePeopleOnline hirePeopleOnline);

    //根据第一季度 勾选的 区域市场在 hire_people 表中插入对应的值
    public void insertHirePeople(HirePeople hirePeople);


    //2017-11-1财务

    List<CompanyProduct> selectProductByCompanyIdAndQuarter(int company_id, int quarter);

    void updateCashFlowYanfa(float yanfa, int company_id, int quarter);

    CompanyMarket selectPhyCompanyMarket(int company_id, int quarter);

    int selectOpenByMarketId(int id);

    void updateCashFlowPhyMarket(float PhyMarket, int company_id, int quarter);

    CompanyMarket selectWebCompanyMarket(int company_id, int quarter);

    int selectWebOpenByMarketId(int id);

    void updateCashFlowWebMarket(float WebMarket, int company_id, int quarter);

    int selectCapacity(int company_id, int quarter);

    int selectInvestByCapacity(int capacity);

    void updateCashFlowCapacity(float invest, int company_id, int quarter);

    int selectCunru(int company_id, int quarter);

    void updateCashFlowCunkuanPay(float cunru, int company_id, int quarter);

    void updateIncomeStatementYanfa(float yanfa, int company_id, int quarter);

    void updateIncomeStatementPhyMarket(float PhyMarket, int company_id, int quarter);

    void updateIncomeStatementWebMarket(float WebMarket, int company_id, int quarter);

    void updateIncomStatementLixi(float lixi, int company_id, int quarter);

    int selectStockPrice(int company_id, int quarter);

    void updateCashFlowLixi(float lixi, int company_id, int quarter);

    void updateBalanceSheetCunKuan(float cunkuan, int company_id, int quarter);

    void updateBalanceSheetZiChan(float zichan, int company_id, int quarter);

    void updateBalanceSheetGuBen(float guben, int company_id, int quarter);

    void updateBalanceSheetLiuCun(float liucun, int company_id, int quarter);

    void updateBalanceSheetHuoBi(float huobi, int company_id, int quarter);

    //存款 新
    FixedDeposit selectCunkuanLast(int company_id, int quarter);

    void insertCunkuanLast(float cunkuanLast, int company_id, int quarter);

    //第二季度
    void updateCashFlowKuCun(int kucun, int company_id, int quarter);

    int selectPrice(int productId, int quarter);

    int selectDemandNum2(int productId);

    int selectDemandNum3(int productId);

    void updateCashFlowShouRu(int income, int company_id, int quarter);

    int selectTiQu(int company_id, int quarter);

    void updateCashFlowTiqu(int tiqu, int company_id, int quarter);

    int selectCunKuanLast(int company_id, int quarter);

    int selectYouJi(int productId);

    void updateCashFlowYouJi(int youji, int company_id, int quarter);

    int selectReport(int company_id, int quarter);

    void updateCashFlowDiaoYan(int diaoyan, int company_id, int quarter);

    void updateCashFlowHuoYun(int huoyun, int company_id, int quarter);

    int selectSaleSalary(int company_id, int quarter);

    void updateCashFlowSalerPay(int saleSalary, int company_id, int quarter);

    int selectRent(int marketId);

    int selectWebRent(int marketId);

    List<CompanyMedia> selectProductMedia(int productId, int quarter);

    int selectMediaCost(int mediaId);

    void updateCashFlowMediaCost(int mediaCost, int company_id, int quarter);

    void updateIncomeShouRu(int income, int company_id, int quarter);

    void updateIncomeHuoYun(int huoyun, int company_id, int quarter);

    void updateIncomeDiaoYan(int diaoyan, int company_id, int quarter);

    void updateIncomeYouJi(int youji, int company_id, int quarter);

    void updateIncomeGuangGao(int guanggao, int company_id, int quarter);

    CashFlow selectCashFlow(int company_id, int quarter);

    BalanceSheet selectHuoBiLast(int company_id, int quarter);

    void updateCashFlowResult(float lixi, float yanfa, float guanggao, float saler, float salesCenter, float salesCenterWeb, float diaoyan, float gongchang, float tiqu, float cunkuan, int company_id, int quarter);

    List<CashFlow> selectCashFlowResult(int company_id, int quarter);

    void updateIncomeResult(float lixi, float yanfa, float guanggao, float saler, float salesCenter, float salesCenterWeb, float diaoyan, int company_id, int quarter);

    List<IncomeStatement> selectIncomeStatementResult(int company_id, int quarter);

    List<BalanceSheet> selectBalanceSheetResult(int company_id, int quarter);

    BalanceSheet selectBalanceSheet(int company_id, int quarter);

    void updateBalanceSheetResult(float cunkuan, float zichan, float guben, int company_id, int quarter);

    void updateBalanceSheetResult2(float huobi, float liucun, float cunhuo, int company_id, int quarter);

    void insertStock(int company_id, int quarter, String stock_type, String owner, int stockNumber, int stockPrice, int totalPrice);

    int selectKucunNum2(int productId);

    ProductPrice showPrice(int productId, int quarter);

    CompanyReport showReport(int company_id, int quarter);

    void updateIncomeYingYe(int yingyeCost, int company_id, int quarter);

    void updateIncomeSaler(int saler, int company_id, int quarter);

    void updateIncomeKuCun(int kucun, int company_id, int quarter);

    CompanyCapacity showCapacity(int company_id, int quarter);

    int selectProductCost(int productId);

    int selectKucunNum3(int productId);

    int selectSaleResult(int productId, int quarter);

    int selectKuCunResult(int productId, int quarter);

    void updateCashFlowResult2(float incomeSum, float youjiSum, float shengchanSum, float huoyunSum, float kucunSum, int company_id, int quarter);

    void insertCashFlowResult(int company_id, int quarter);

    void insertIncomeResult(int company_id, int quarter);

    void insertBalanceResult(int company_id, int quarter);

    void updateBalanceSheetCunHuo(int cunhuo, int company_id, int quarter);

    void updateIncomeResult2(float incomeSum, float yingyeSum, float youjiSum, float huoyunSum, float kucunSum, int company_id, int quarter);

    void updateCashFlowShengChan(float shengchan, int company_id, int quarter);


    public CompanyCapacity selectCompanyCapacity(int companyId, int quarter);


    ProductPrice selectProductPrice2(int productId, int quarter);

    void insertProductPrice2(int productId, int companyId, int quarter);

    CompanyProduct selectCompanyProduct(int productId);

    //雇佣网络销售人员

    public void insertHirePeopleOnline(HirePeopleOnline hirePeopleOnline);

    public HirePeople checkHirePeople(HirePeople hirePeople);

    public HirePeopleOnline checkHirePeopleOnline(HirePeopleOnline hirePeopleOnline);

    public void deleteHirePeopleOnline(HirePeopleOnline hirePeopleOnline);

    public void deleteHirePeople(HirePeople hirePeople);


    //第三季度

    public List<ProductMarketShare> selectProductMarketShare(Integer id, Integer quarter);

    List<CompanyInvestment> selectCompanyInvestment(@Param("companyId") int companyId, @Param("quarter") int quarter);


    //紧急贷款
    CompanyLoan selectCompanyLoan(int company_id, int quarter);

    void insertCompanyLoan(int company_id, int quarter, float get, float payBack);

    void updateCompanyLoan(int company_id, int quarter, float get, float payBack);

    void insertLoanLast(float loanLast, int company_id, int quarter);

    public List<HirePeople> selectHirePeopleList(int companyId, int quarter);

    public List<HirePeopleOnline> selectHirePeopleOnlineList(int companyId, int quarter);

    public DemandForecast getDemandForecastByCompanyIdAndQuarter(int companyId, int quarter);

    public List<CompanyProductVo> selectCompanyProduct(int companyId, int quarter, int quarter2);

    //  需求量预测

    public void insertCompanyProductDemandById(int id, int quarter);

    public void updateProductDemand(int productId, int quarter, int demand);

    //删除产品时 附带要删除的信息
    public void deleteCompanyProductDemand(int productId);

    public void deleteProductPrice(int productId);

    public void deleteCompanyMedia(int productId);

    public void deleteCompanyAdvertise(int productId);

    //更新库存控制
    public void updateCompanyProductInventory(int productId, int inventory, int quarter);

    public void deleteCompanyProductInventory(int productId);

    List<CompanyProductVo2> selectInvertoryCountFinancialRatio(int companyIdInt, int quarter);

    public void insertCompanyProductInventoryById(int id, int i);

    public List<cn.usst.market.po.CompanyProduct> selectCompanyProductByCompanyIdQuarter(int companyId, int quarter);

		
		
		
		/*2017-11-15*/

    int selectProductDemand(int productId, int quarter);

    int selectProductKuCun(int productId, int quarter);

    List<CashFlow> showCashFlowResult(int companyId, int quarter);

    List<BalanceSheet> showBalanceSheetResult(int companyId, int quarter);

    List<IncomeStatement> showIncomeStatementResult(int companyId, int quarter);

    void updateIncomeStatementTax(float tax, int company_id, int quarter);

    void updateIncomeStatementResultTax(float tax, int company_id, int quarter);

    void updateBalanceSheetResult3(float ziben, int company_id, int quarter);

    void updateBalanceSheet3(float ziben, int company_id, int quarter);

    void updateCashFlowJiChu(float jichuXianjin, int company_id, int quarter);

    void updateCashFlowResultJiChu(float jichuXianjin, int company_id, int quarter);


    List<OnlineStore> selectAndCountTotalNeed(List<MarketInfo> marketInfoList);

    CompanyMarket selectCompanyMarket(Integer companyId, Integer isPhy, Integer quarter);


    void calCashFlowResult(int company_id, int quarter);

    void calIncomeResult(int company_id, int quarter);

    void calBalanceSheetResult(int company_id, int quarter);

    void deleteCompanyMarket(CompanyMarket companyMarket1);

    public void updateCompanyQuarterTime(Integer companyId, int quarter, String endTime);

    int selectCompanyMarket1(int companyId, int isPhy);

    void updateCompanyMarket1(CompanyMarket companyMarket);

    void updateCompanyMarket2(CompanyMarket companyMarket);


    CompanyProductDemand selectDemand(int productId, int quarter);

    CompanyProductInventory selectInventory(int productId, int quarter);

    void updateCashFlowLoan(int company_id, int quarter, float getLoan, float returnLoan);

    void updateBalanceSheetLoan(int company_id, int quarter, float totalLoan, float lixiLoan);

    void updateCashResultLoan(int company_id, int quarter, float getLoan, float payLoan);

    void updateBalanceSheetResultLoan(int company_id, int quarter, float totalLoan, float lixiLoan);


    // 股本接口   companySERvice
    void insertGuben(int company_id, int quarter);

    int selectDecision(int company_id);

    CompanyDecision selectCompanyDecision(int company_id, int decision_id);

    void updateCompanyDecision(int company_id, int money, int decision_id);

    void insertCompanyDecision(int company_id, int money, int decision_id);

    void updateProductPrice2(ProductPrice productPrice);
    
//    0215
    void deleteAllCompanyDecision(int company_id);
    
    void updateAllCompanyDecision(int company_id,int quarter);
    
//	0408
	MarketOpenedPo selectMarketOpen(int company_id);
	
	MarketWebOpened selectMarketWebOpen(int company_id,int quarter);
}
