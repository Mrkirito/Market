package cn.usst.market.service;

import java.util.List;

import cn.usst.market.po.BalanceScore;
import cn.usst.market.po.BalanceSheet;
import cn.usst.market.po.CashFlow;
import cn.usst.market.po.CompanyInvestment;
import cn.usst.market.po.CompanyMarket;
import cn.usst.market.po.CompanyMarketShare;
import cn.usst.market.po.CompanyMedia;
import cn.usst.market.po.CompanyProduct;
import cn.usst.market.po.CompanyQuarterTime;
import cn.usst.market.po.HirePeople;
import cn.usst.market.po.IdQuarter;
import cn.usst.market.po.IncomeStatement;
import cn.usst.market.po.MarketInfo;
import cn.usst.market.po.MarketInfo2;
import cn.usst.market.po.MarketShareWeight;
import cn.usst.market.po.MediaInfo;
import cn.usst.market.po.HirePeopleOnline;
import cn.usst.market.po.ProductEfficiency;
import cn.usst.market.po.ProductInfo;
import cn.usst.market.po.ProductMarketShare;
import cn.usst.market.po.ProductPrice;
import cn.usst.market.po.SalesSalary;
import cn.usst.market.po.WorkersSalary;

public interface CompetitionResultService {

	BalanceSheet findBalanceSheetByIdAndQuarter(IdQuarter idQuarter);

	CashFlow findCashFlowByIdAndQuarter(IdQuarter idQuarter);

	IncomeStatement findIncomeStatementByIdAndQuarter(IdQuarter idQuarter);
	//修改竞赛当前季度
	void updateCompetitionCurrentQuarter(int id, int quarter);
	//找没有提交的公司
	List<CompanyQuarterTime> findNoSubmitCompanyByIdQuarter(IdQuarter idQuarter);
	
	//添加季度开始时间
	void insertCompanyQuarterTime(CompanyQuarterTime companyQT);
	CompanyQuarterTime findCompanyQuarterTime(int companyId, int quarter);

	//添加产品各种效用
	void insertProductEfficiency(ProductEfficiency productEfficiency);

	//找产品效用
	List<ProductEfficiency> findProductEfficiency(int competitionId, int quarter,String type,int marketId);

	//添加市场份额
	void insertOrUpdateProductMarketShare(ProductMarketShare productMarketShare);

	//找产品市场份额
	List<ProductMarketShare> findProductMSByCompanyIdQuarterType(int companyId, int quarter,String type);
	List<ProductMarketShare> findProductMSByCompanyIdQuarter(int companyId, int quarter);

	MarketShareWeight findMaketShareWeightByType(String type);

	//找本次竞赛本季度的所有产品
	List<CompanyProduct> findProductsByCompetIdAndQuarter(int competitionId, int quarter);
	
	//找每个产品的媒体投放
	List<CompanyMedia> findMediaByProductIdAndQuarter(int productId, int quarter);

	MediaInfo findMediaById(int id);

	ProductInfo findProductInfoById(int id);

	CompanyMarket findCompanyMarket(int companyId, int quarter, int isPhy);
	
	//找本次竞赛的平均工资
	SalesSalary findAvgSalesSalaryByCompetIdQuarter(int competitionId, int quarter);
	WorkersSalary findAvgWorkersSalaryByCompetIdQuarter(int competitionId, int quarter);

	SalesSalary findSalesSalaryByCompanyIdQuar(int companyId, int quarter);
	WorkersSalary findWorkerSalaryByCompanyIdQuar(int companyId, int quarter);

	ProductPrice findProductPriceByIdQuarter(int productId, int quarter);
	List<ProductPrice> findProductPriceByCompanyIdQuarter(int companyId, int quarter);
	
	//插入公司的市场份额
	void insertCompanyMarketShare(CompanyMarketShare companyMarketShare);

	CompanyMarketShare findCompanyMarketShare(int companyId, int quarter);
	
	//找本次竞赛该季度所有产品的市场份额
	List<ProductMarketShare> findProductMSByCompetitionIdQuarter(int competitionId, int quarter);
	List<ProductMarketShare> findProductMSByCompetIdQuarterType(int competitionId, int quarter, String type);
	//修改产品的市场份额
	void updateProductMSByProductIdQuarter(ProductMarketShare productMarketShare);

	void insertCompanyInvestment(CompanyInvestment companyInvestment);

	CompanyInvestment findCompanyInvestByCompanyIdQuarter(int companyId, int quarter);
	
	//公司人数
	List<HirePeople> findCompanyPhySalesNum(int companyId, int quarter);
	List<HirePeopleOnline> findCompanyNetSalesNum(int companyId, int quarter);
	MarketInfo2 findMarketInfoById(int id);
	
	//公司平衡计分卡
	BalanceScore findBalanceScoreByCompanyIdQuarter(int companyId, int quarter);

	IncomeStatement findIncomeStatementResultByCompanyIdQuarter(int companyId, int quarter);

	//找所有市场的信息，要用到市场需求量
	List<MarketInfo> findAllMarketInfo();
	//需求量用下面这个
	List<MarketInfo2> findMarketInfoByCompetitionId(int competitionId);

	//找公司开放的市场
	CompanyMarket findCompanyMarketByCompanyIdQuarter(int companyId, int quarter, int isPhy);

	//找单个产品市场份额
	ProductMarketShare findProductMSByProductIdQuarter(int productId, int quarter);
	

}
