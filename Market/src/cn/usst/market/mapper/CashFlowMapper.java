package cn.usst.market.mapper;

import java.util.List;

import cn.usst.market.po.BalanceSheet;
import cn.usst.market.po.CashFlow;
import cn.usst.market.po.CompanyCapacity;
import cn.usst.market.po.CompanyMarket;
import cn.usst.market.po.CompanyMedia;
import cn.usst.market.po.CompanyProduct;
import cn.usst.market.po.CompanyProductDemand;
import cn.usst.market.po.CompanyProductInventory;
import cn.usst.market.po.CompanyReport;
import cn.usst.market.po.IncomeStatement;
import cn.usst.market.po.MarketOpenedPo;
import cn.usst.market.po.MarketWebOpened;
import cn.usst.market.po.ProductPrice;

public interface CashFlowMapper {
	//第一季度现金流表
	//研发
	List<CompanyProduct> selectProductByCompanyIdAndQuarter(int company_id,int quarter);
	
	void updateCashFlowYanfa(float yanfa,int company_id,int quarter);
	
	//销售中心
	CompanyMarket selectPhyCompanyMarket(int company_id,int quarter);
	
	int selectOpenByMarketId(int id);
	
	void updateCashFlowPhyMarket(float PhyMarket,int company_id,int quarter);
	
	CompanyMarket selectWebCompanyMarket(int company_id,int quarter);
	
	int selectWebOpenByMarketId(int id);
	
	void updateCashFlowWebMarket(float WebMarket,int company_id,int quarter);
	
	//工厂
	int selectCapacity(int company_id,int quarter);
	
	int selectInvestByCapacity(int capacity);
	
	void updateCashFlowCapacity(float invest,int company_id,int quarter);
	
	//存款利息
	int selectCunru(int company_id,int quarter);
	
	void updateCashFlowCunkuanPay(float cunru,int company_id,int quarter);
	
	void updateCashFlowLixi(float lixi,int company_id,int quarter);
	
	//第一季度利润表
	void updateIncomeStatementYanfa(float yanfa,int company_id,int quarter);
	
	void updateIncomeStatementPhyMarket(float PhyMarket,int company_id,int quarter);

	void updateIncomeStatementWebMarket(float WebMarket,int company_id,int quarter);

	void updateIncomStatementLixi(float lixi,int company_id,int quarter);
	
	//第一季度资产负债表
	int selectStockPrice(int company_id,int quarter);
	
	void updateBalanceSheetCunKuan(float cunkuan,int company_id,int quarter);
	
	void updateBalanceSheetZiChan(float zichan,int company_id,int quarter);
	
	void updateBalanceSheetGuBen(float guben,int company_id,int quarter);
	
	void updateBalanceSheetLiuCun(float liucun,int company_id,int quarter);
	
	void updateBalanceSheetHuoBi(float huobi,int company_id,int quarter);
	
	//第二季度现金刘表
	int selectPrice(int productId,int quarter);
	
	int selectDemandNum2(int productId);
	
	int selectDemandNum3(int productId);
	
	void updateCashFlowShouRu(int income,int company_id,int quarter);
	
	int selectTiQu(int company_id,int quarter);
	
	void updateCashFlowTiqu(int tiqu,int company_id,int quarter);
	
	int selectCunKuanLast(int company_id,int quarter);
	
	int selectYouJi(int productId);
	
	void updateCashFlowYouJi(int youji,int company_id,int quarter);
	
	int selectReport(int company_id,int quarter);
	
	void updateCashFlowDiaoYan(int diaoyan,int company_id,int quarter);
	
	void updateCashFlowHuoYun(int huoyun,int company_id,int quarter);
	
	int selectSaleSalary(int company_id,int quarter);
	
	void updateCashFlowSalerPay(int saleSalary,int company_id,int quarter);
	
	int selectRent(int marketId);
	
	int selectWebRent(int marketId);
	
	List<CompanyMedia> selectProductMedia(int productId,int quarter);
	
	int selectMediaCost(int mediaId);
	
	void updateCashFlowMediaCost(int mediaCost,int company_id,int quarter);
	
	int selectKucunNum2(int productId);
	
	int selectKucunNum3(int productId);
	
	void updateCashFlowKuCun(int kucun,int company_id,int quarter);
	
	//第二季度利润
	void updateIncomeShouRu(int income,int company_id,int quarter);
	
	void updateIncomeHuoYun(int huoyun,int company_id,int quarter);
	
	void updateIncomeDiaoYan(int diaoyan,int company_id,int quarter);
	
	void updateIncomeYouJi(int youji,int company_id,int quarter);
	
	void updateIncomeGuangGao(int guanggao,int company_id,int quarter);
	
	//第二季度负债
	CashFlow selectCashFlow(int company_id,int quarter);
	
	BalanceSheet selectHuoBiLast(int company_id,int quarter);
	
	//查看上季度
	void updateCashFlowResult(float lixi,float yanfa,float guanggao,float saler,float salesCenter,float salesCenterWeb,float diaoyan,float gongchang,float tiqu,float cunkuan,int company_id,int quarter);
	
	List<CashFlow> selectCashFlowResult(int company_id,int quarter);
	
	void updateIncomeResult(float lixi,float yanfa,float guanggao,float saler,float salesCenter,float salesCenterWeb,float diaoyan,int company_id,int quarter);
	
	List<IncomeStatement> selectIncomeStatementResult(int company_id,int quarter);
	
	List<BalanceSheet> selectBalanceSheetResult(int company_id,int quarter);
	
	BalanceSheet selectBalanceSheet(int company_id,int quarter);
	
	void updateBalanceSheetResult(float cunkuan,float zichan,float guben,int company_id,int quarter);
	
	void updateBalanceSheetResult2(float huobi,float liucun,float cunhuo,int company_id,int quarter );
	
	//内部持股
	void insertStock(int company_id,int quarter,String stock_type,String owner,int stockNumber,int stockPrice,int totalPrice);
	
	ProductPrice showPrice(int productId,int quarter);
	
	CompanyReport showReport(int company_id,int quarter);
	
	void updateIncomeYingYe(int yingyeCost,int company_id,int quarter);
	
	void updateIncomeSaler(int saler,int company_id,int quarter);
	
	void updateIncomeKuCun(int kucun,int company_id,int quarter);
	
	CompanyCapacity showCapacity(int company_id,int quarter);
	
	int selectProductCost(int productId);
	
	void updateCashFlowShengChan(float shengchan,int company_id,int quarter);
	
	int selectSaleResult(int productId,int quarter);
	
	int selectKuCunResult(int productId,int quarter);
	
	void updateCashFlowResult2(float incomeSum,float youjiSum,float shengchanSum,float huoyunSum,float kucunSum,int company_id,int quarter);
	
	void insertCashFlowResult(int company_id,int quarter);
	
	void insertIncomeResult(int company_id,int quarter);
	
	void insertBalanceResult(int company_id,int quarter);
	
	void updateBalanceSheetCunHuo(int cunhuo,int company_id,int quarter);
	
	void updateIncomeResult2(float incomeSum,float yingyeSum,float youjiSum,float huoyunSum,float kucunSum,int company_id,int quarter);
	
	ProductPrice selectProductPrice2(int productId,int quarter);

	void insertProductPrice2(int productId,int companyId,int quarter);
	
	CompanyProduct selectCompanyProduct(int productId);
	
	int selectProductDemand(int productId,int quarter);
	
	int selectProductKuCun(int productId,int quarter);
	
	List<CashFlow> showCashFlowResult(int companyId,int quarter);
	
	List<BalanceSheet> showBalanceSheetResult(int companyId,int quarter);
	
	List<IncomeStatement> showIncomeStatementResult(int companyId,int quarter);
	
	void updateIncomeStatementTax(float tax,int company_id,int quarter);
	
	void updateIncomeStatementResultTax(float tax,int company_id,int quarter);
	
	void updateBalanceSheetResult3(float ziben,int company_id,int quarter);
	
	void updateBalanceSheet3(float ziben,int company_id,int quarter);
	
	void updateCashFlowJiChu(float jichuXianjin,int company_id,int quarter);
	
	void updateCashFlowResultJiChu(float jichuXianjin,int company_id,int quarter);
	
	CompanyProductDemand selectDemand(int productId,int quarter);
	
	CompanyProductInventory selectInventory(int productId,int quarter);
	
	void updateCashFlowLoan(int company_id,int quarter,float getLoan,float returnLoan);
	
	void updateBalanceSheetLoan(int company_id,int quarter,float totalLoan,float lixiLoan);
	
	void updateCashResultLoan(int company_id,int quarter,float getLoan,float payLoan);
	
	void updateBalanceSheetResultLoan(int company_id,int quarter,float totalLoan,float lixiLoan);
	
	int selectDecision(int company_id);

//	0213
	void deleteAllCompanyDecision(int company_id);
//	0408
	MarketOpenedPo selectMarketOpen(int company_id);
	
	MarketWebOpened selectMarketWebOpen(int company_id,int quarter);
	
}
