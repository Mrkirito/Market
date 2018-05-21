package cn.usst.market.mapper;

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

public interface CompetitionResultMapper {

    int insert(BalanceSheet record);

    int insertSelective(BalanceSheet record);
    
    //通过公司ID和季度找资产负债表
    public BalanceSheet findBalanceSheetByIdAndQuarter(IdQuarter idQuarter);
    
    //找现金流表
    public CashFlow findCashFlowByIdAndQuarter(IdQuarter idQuarter);
    
    //损益表
    public IncomeStatement findIncomeStatementByIdAndQuarter(IdQuarter idQuarter);
    
    //根据竞赛id修改当前时间
    public void updateCompetitionCurrentQuarter(int id,int quarter);
    
    //找没有提交的公司
    public List<CompanyQuarterTime> findNoSubmitCompanyByIdQuarter(IdQuarter idQuarter);
    
    //添加公司季度开始时间
    public void insertCompanyQuarterTime(CompanyQuarterTime companyQT);
    //查找公司季度时间
    public CompanyQuarterTime findCompanyQuarterTime(int companyId,int quarter);
    
    //找每个产品
    public List<CompanyProduct> findProductsByCompetIdAndQuarter(int competitionId,int quarter);
    
    //找每个产品的媒体投放
    public List<CompanyMedia> findMediaByProductIdAndQuarter(int productId,int quarter);
    //根据ID找媒体权值
    public MediaInfo findMediaById(int id);
    
    //根据ID找产品设计的详细信息
    public ProductInfo findProductInfoById(int id);
    //根据公司ID，季度，是否是实体市场找市场
    public CompanyMarket findCompanyMarket(int companyId,int quarter,int isPhy);
    
    //找本次竞赛的销售人员平均工资
    public SalesSalary findAvgSalesSalaryByCompetIdQuarter(int competitionId,int quarter);
    public WorkersSalary findAvgWorkersSalaryByCompetIdQuarter(int competitionId,int quarter);
    
    //找每个公司的公司人数
    public List<HirePeople> findCompanyPhySalesNum(int companyId,int quarter);
    public List<HirePeopleOnline> findCompanyNetSalesNum(int companyId,int quarter);
    public MarketInfo2 findMarketInfoById(int id);
    
    //找公司销售工人薪酬
    public SalesSalary findSalesSalaryByCompanyIdQuar(int companyId,int quarter);
    
    //找公司工厂工人薪酬
    public WorkersSalary findWorkerSalaryByCompanyIdQuar(int companyId,int quarter);
    
    //添加产品各种效用
    public void insertProductEfficiency(ProductEfficiency productEfficiency);
    
    //找产品定价
    public ProductPrice findProductPriceByIdQuarter(int productId,int quarter);
    
    //找该公司所有产品的定价
    public List<ProductPrice> findProductPriceByCompanyIdQuarter(int companyId,int quarter);
    
    //查找本次竞赛本季度产品各种效用
    public List<ProductEfficiency> findProductEfficiency(int competitionId,int quarter,String type,int marketId);
    
    //添加各种产品的市场份额
    public void insertOrUpdateProductMarketShare(ProductMarketShare productMarketShare);
    
    //查找每个产品的市场份额
    public List<ProductMarketShare> findProductMSByCompanyIdQuarterType(int companyId,int quarter,String type);
    public List<ProductMarketShare> findProductMSByCompanyIdQuarter(int companyId,int quarter);
    public List<ProductMarketShare> findProductMSByCompetIdQuarterType(int competitionId,int quarter,String type);
    //找本次竞赛该季度的所有产品市场份额
    public List<ProductMarketShare> findProductMSByCompetitionIdQuarter(int competitionId,int quarter);
    
    //更新产品市场份额数据
    //public void updateProductMSByProductIdQuarter(int productId,int quarter,double marketshare);
    public void updateProductMSByProductIdQuarter(ProductMarketShare productMarketShare);
    
    
    //插入公司市场份额
    public void insertCompanyMarketShare(CompanyMarketShare companyMarketShare);
    
    //找公司市场份额
    public CompanyMarketShare findCompanyMarketShare(int companyId,int quarter);
    
    //找市场份额的权值矩阵
    public MarketShareWeight findMaketShareWeightByType(String type);
    
    
    //插入公司的各种投资
    public void insertCompanyInvestment(CompanyInvestment companyInvestment);
    
    public CompanyInvestment findCompanyInvestByCompanyIdQuarter(int companyId,int quarter);
    
    
    //找公司的平衡记分卡
    public BalanceScore findBalanceScoreByCompanyIdQuarter(int companyId,int quarter);
    
    //这个应该没有用到，可以删掉
    public IncomeStatement findIncomeStatementResultByCompanyIdQuarter(int companyId,int quarter);
    
    //找市场需求量
    public List<MarketInfo> findAllMarketInfo();
    
    //找市场需求量
    public List<MarketInfo2> findMarketInfoByCompetitionId(int competitionId);
    
    //找公司开放的市场
    public CompanyMarket findCompanyMarketByCompanyIdQuarter(int companyId,int quarter,int isPhy);
    
    //根据产品id和季度找产品市场份额
    public ProductMarketShare findProductMSByProductIdQuarter(int productId,int quarter);
}