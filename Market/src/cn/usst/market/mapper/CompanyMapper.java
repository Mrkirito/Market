package cn.usst.market.mapper;

import java.util.List;

import cn.usst.market.po.*;
import org.apache.ibatis.annotations.Param;


public interface CompanyMapper {

    int insert(Company record);

    Company selectByPrimaryKey(Integer id);

    Company findCompanyByCondition(Company record);

    Company findCompanyById(Company record);

    void updateCompanyName(Company record);

    List<Company> showCompanyByCompetitionId(Integer id);

    List<String> countStrategyTitle();

    List<StrategyInfo> showStrategyInfoByTitle(String title);

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

	/*public Company findCompanyById(int id)throws Exception;*/

    public List<Company> findCompanyListByCompetitionId(int id) throws Exception;

    public void updateByPrimaryKey(Company company) throws Exception;

    public void deleteByPrimaryKey(Integer id) throws Exception;


    public List<TeacherQueryVo> selectCompanyByPage(TeacherQueryVo teacherQueryVo);

    public long getCompanyCount(TeacherQueryVo teacherQueryVo);

    //zhao
    Company checkCompanyNameExist(String name);

    Company selectCompanyById(Integer id);

    void updateCompanyNameById(Integer id, String name);

    public CompanyStrategy selectCompanyStrategy(Integer company_id, Integer quarter);

    int insertCompanyStrategy(CompanyStrategy companyStrategy) throws Exception;

    void updateCompanyStrategy(CompanyStrategy companyStrategy) throws Exception;


    public void deleteProductById(Integer id);


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

    void updateCashFlowWebMarket(float salescenter_web_pay, int company_id, int quarter);


    IncomeStatement selectIncomeStatementByCompanyId(int companyId, int quarter);

    void updateIncomeStatementYanFa(float yanfa_pay, int company_id, int quarter);

    void updateIncomeStatementMarket(float salescenter_pay, int company_id, int quarter);

    void updateIncomeStatementWebMarket(float salescenter_web_pay, int company_id, int quarter);


    void insertCompanyProduct(CompanyProduct companyProduct);

    public List<CompanyProduct> selectProductByCompanyId(Integer companyId);

    public CompanyProduct selectProductByProductId(Integer id);

    List<ProductInfo> showAllDetail();

    void updateCompanyProduct(CompanyProduct companyProduct);

    public void deleteCompanyProduct(Integer product_id);


    public int selectTotalCount(int companyId, int i);

    public CapacityInfo selectCapacityInfo(int number);

    void insertCompanyCapacity(@Param("capacityNow") int capacityNow, @Param("number") int number, @Param("companyId") int companyId, @Param("quarter") int quarter);


    public List<CapacityInfo> showCapacityInfo();

    public List<CompanyCapacity> showCapacityInfo1(int companyId, int quarter);

    void updateCompanyCapacity(int capacity_now, int capacity_add, int companyId, int quarter);


    void updateBalanceSheetZiChan(float invest, int company_id, int quarter);

    void updateBalanceSheetHuoBi(float huobi, int company_id, int quarter);

    int selectGuBen(int compant_id, int quarter);

    public List<BalanceSheet> showBalanceSheet(int company_id, int quarter);

    public List<CashFlow> showCashFlow(int company_id, int quarter);

    public List<IncomeStatement> showIncomeStatement(int company_id, int quarter);

    public List<CompanyStock> showCompanyStock(int company_id, int quarter);


    List<FixedDeposit> showFixedDeposit();

    void insertFixedDeposite(int companyIdInt, float tiQuFloat, float cunRuFloat, int quarter, float cunkunLast);

    List<FixedDeposit> selectFixedDeposite(int companyIdInt, int quarter);

    void updateFixedDeposite(int companyIdInt, float tiQuFloat, float cunRuFloat, int quarter);


    //创建财务表中的信息

    void insertCashFlowFirst(CashFlow cashFlow);

    void insertIncomeStatementFirst(IncomeStatement incomeStatement);

    void insertBalanceSheetFirst(BalanceSheet balanceSheet);

    AverageSalary showAverageSalaryOfSale();

    AverageSalary showAverageSalaryOfWork();

    void insertCompanyStock(CompanyStock cs);

    void insertCompanyDuty(CompanyDuty cd);

	
	/*第二季度*/

    // 工厂工人薪酬

    public List<AllWorkersSalaryVo> findWSalaryofAllCompanysbyCompanyID(int companyId, int quarter);

    List<WorkersSalary> selectCompanyWorkersSalary(int company_id, int quarter);

    void insertCompanyWorkersSalary(WorkersSalary workersSalary);

    void updateCompanyWorkersSalary(WorkersSalary workersSalary);

    // 销售人员薪酬

    public List<AllSalesSalaryVo> findSalaryofAllCompanysbyCompanyID(int companyId, int quarter);

    List<SalesSalary> selectCompanySalesSalary(int company_id, int quarter);

    SalesSalary findCompanySalesSalary(int company_id, int quarter);

    void insertCompanySalesSalary(SalesSalary salesSalary);

    void updateCompanySalesSalary(SalesSalary salesSalary);


    public void insertHirePeople(HirePeople hirePeople);


    // 需求量预测

    List<DemandForecast> showDemandForecast();

    void insertDemandForecast(int companyIdInt, int quarter, int demandAveragePhyt, int demandAverageWebt);

    List<DemandForecast> selectDemandForecast(int companyIdInt, int quarter);

    void updateDemandForecast(int companyIdInt, int quarter, int demandAveragePhyt, int demandAverageWebt);


    void insertDemandForecast2(CompanyProduct companyProduct);

    List<CompanyProduct> selectDemandForecast2(int companyIdInt, int quarter);

    void updateDemandForecast2(CompanyProduct companyProduct);

    //固定成本
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


    FixedDeposit selectCunkuanLast(int company_id, int quarter);

    void insertCunkuanLast(float cunkuanLast, int company_id, int quarter);

    // 库存控制
    void insertInventoryControl(CompanyProduct companyProduct);

    List<CompanyProductVo2> selectInventoryControl(int companyIdInt, int quarter);

    List<CompanyProductVo2> selectInvertoryCountFinancialRatio(int companyIdInt, int quarter);

    void updateInventoryControl(CompanyProduct companyProduct);

    //雇佣销售人员

    public HirePeopleVo selectHirePeople(int companyId, int marketInt, int quarter);

    public HirePeopleOnline selectHirePeopleOnline(int companyId, int quarter);

    //更新销售人员
    public void updateHirePeopleById(HirePeople hirePeople);

    public void updateHirePeopleOnlineById(HirePeopleOnline hirePeopleOnline);


    public CompanyCapacity selectCompanyCapacity(int companyId, int quarter);

    //雇佣网络销售人员  第一季度勾选网络销售中心时  插入到 hire_people_online

    public void insertHirePeopleOnline(HirePeopleOnline hirePeopleOnline);

    public HirePeople checkHirePeople(HirePeople hirePeople);

    public HirePeopleOnline checkHirePeopleOnline(HirePeopleOnline hirePeopleOnline);

    public void deleteHirePeopleOnline(HirePeopleOnline hirePeopleOnline);

    public void deleteHirePeople(HirePeople hirePeople);

    //更新库存控制
    public void updateCompanyProductInventory(int productId, int inventory, int quarter);

    public void deleteCompanyProductInventory(int productId);

    public void insertCompanyProductInventoryById(int id, int i);


    //2017-11-5  第三季度

    //上季度结果
    public List<ProductMarketShare> selectProductMarketShare(Integer id, Integer quarter);

    List<CompanyInvestment> selectCompanyInvestment(@Param("companyId") int companyId, @Param("quarter") int quarter);

    public List<cn.usst.market.po.CompanyProduct> selectCompanyProductByCompanyIdQuarter(int companyId, int quarter);

    //紧急贷款
    CompanyLoan selectCompanyLoan(int company_id, int quarter);

    void insertCompanyLoan(int company_id, int quarter, float get, float payBack);

    void updateCompanyLoan(int company_id, int quarter, float get, float payBack);

    void insertLoanLast(float loanLast, int company_id, int quarter);


    //需求量预测   2017-11-12
    public List<HirePeople> selectHirePeopleList(int companyId, int quarter);

    public List<HirePeopleOnline> selectHirePeopleOnlineList(int companyId, int quarter);

    public DemandForecast getDemandForecastByCompanyIdAndQuarter(int companyId, int quarter);

    public List<CompanyProductVo> selectCompanyProduct(int companyId, int quarter, int quarter2);

    public void insertCompanyProductDemandById(int id, int quarter);

    public void updateProductDemand(int productId, int quarter, int demand);


    //删除产品时 附带要删除的信息
    public void deleteCompanyProductDemand(int productId);

    public void deleteProductPrice(int productId);

    public void deleteCompanyMedia(int productId);

    public void deleteCompanyAdvertise(int productId);

    CompanyMarket selectCompanyMarket(Integer companyId, Integer isPhy, Integer quarter);

    void deleteCompanyMarket(CompanyMarket companyMarket1);


    public void updateCompanyQuarterTime(Integer companyId, int quarter, String endTime);

    int selectCompanyMarket1(int companyId, int isPhy);

    void updateCompanyMarket1(CompanyMarket companyMarket);

    void updateCompanyMarket2(CompanyMarket companyMarket);

    CompanyDecision selectCompanyDecision(int company_id, int i);

    void updateCompanyDecision(int company_id, int money, int desicion_id);

    void insertCompanyDecision(int company_id, int money, int decision_id);

    List<CompanyMedia> selectCompanyMedia1(int companyId, int quarter, int productId);

    void updateProductPrice1(int productId, int i);


    void updateProductPrice2(ProductPrice productPrice);

    CompanyDecision selectCompanyDecision1(int company_id, int i);

    /**
     * 插入建立网络销售中心的费用
     */
    void insertCostIntoMarketWebOpened(Integer companyId, Integer quarter, Integer open);

    /**
     * 更新费用
     */

    void updateMarketWebOpened(@Param("companyId") Integer companyId, @Param("quarter") Integer quarter, @Param("cost") int cost);

    MarketWebOpened selectMarketWebOpened(@Param("companyId") Integer companyId, @Param("quarter") Integer quarter);

    CompanyDecision selectWebCostInCompanyDecision(@Param("companyId") Integer companyId);

    void insertCostIntoCompanyDecision(@Param("companyId") Integer companyId, int var2, @Param("cost") int cost);

    void updateCostInCompanyDecision(@Param("companyId") Integer companyId, int i, @Param("cost") int cost);

    int selectRestMoney(@Param("companyId") Integer companyId);

    List<CompanyMarket> selectCompanyMarketAll(Integer companyId, Integer quarter);

    void updateCompanyMarketWeb(CompanyMarket companyMarket);

    int selectCapacityAdd(int companyId, int quarter);

    int selectIssubmit(int companyId, int quarter);

    StateOfSign selectIssubmitState(int companyId, int quarter);

    void updateIsSubmitState(int timesOfSign, int companyId, int quarter);

    void insertIssubmitState(int timesOfSign, Integer companyId, Integer quarter);

}