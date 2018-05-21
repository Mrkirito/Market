package cn.usst.market.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.usst.market.mapper.CompetitionResultMapper;
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
import cn.usst.market.service.CompetitionResultService;

@Service("competitionResultService")
public class CompetitionResultServiceImpl implements CompetitionResultService {
	@Autowired
	private CompetitionResultMapper competitionResultMapper;
	
	//通过公司ID和季度找资产负债表
	@Override
    public BalanceSheet findBalanceSheetByIdAndQuarter(IdQuarter idQuarter){
    	return competitionResultMapper.findBalanceSheetByIdAndQuarter(idQuarter);
    }
	
	//找现金流表
	@Override
    public CashFlow findCashFlowByIdAndQuarter(IdQuarter idQuarter){
    	return competitionResultMapper.findCashFlowByIdAndQuarter(idQuarter);
    }
	
	//损益表
	@Override
	public IncomeStatement findIncomeStatementByIdAndQuarter(IdQuarter idQuarter){
		return competitionResultMapper.findIncomeStatementByIdAndQuarter(idQuarter);
	}
	
	//修改竞赛当前季度
	@Override
	public void updateCompetitionCurrentQuarter(int id,int quarter){
		competitionResultMapper.updateCompetitionCurrentQuarter(id, quarter);
	}
	
	//查询没有提交的公司
	@Override
	public List<CompanyQuarterTime> findNoSubmitCompanyByIdQuarter(IdQuarter idQuarter){
		return competitionResultMapper.findNoSubmitCompanyByIdQuarter(idQuarter);
	}
	
	//添加公司季度开始时间
	@Override
	public void insertCompanyQuarterTime(CompanyQuarterTime companyQT){
		competitionResultMapper.insertCompanyQuarterTime(companyQT);
	}
	
	//查找公司季度时间
	@Override
    public CompanyQuarterTime findCompanyQuarterTime(int companyId,int quarter){
    	return competitionResultMapper.findCompanyQuarterTime(companyId, quarter);
    }
	
	//找每个产品
	@Override
    public List<CompanyProduct> findProductsByCompetIdAndQuarter(int competitionId,int quarter){
    	return competitionResultMapper.findProductsByCompetIdAndQuarter(competitionId, quarter);
    }
	
	//找每个产品的媒体投放
	@Override
    public List<CompanyMedia> findMediaByProductIdAndQuarter(int productId,int quarter){
    	return competitionResultMapper.findMediaByProductIdAndQuarter(productId, quarter);
    }
	//根据ID找媒体权值
	@Override
    public MediaInfo findMediaById(int id){
    	return competitionResultMapper.findMediaById(id);
    }
	
	//根据ID找产品设计的详细信息
	@Override
    public ProductInfo findProductInfoById(int id){
    	return competitionResultMapper.findProductInfoById(id);
    }
	//根据公司ID，季度，是否是实体市场找市场
	@Override
    public CompanyMarket findCompanyMarket(int companyId,int quarter,int isPhy){
    	return competitionResultMapper.findCompanyMarket(companyId, quarter, isPhy);
    }
	
	//找本次竞赛的平均工资
	@Override
    public SalesSalary findAvgSalesSalaryByCompetIdQuarter(int competitionId,int quarter){
    	return competitionResultMapper.findAvgSalesSalaryByCompetIdQuarter(competitionId, quarter);
    }
	
	//找公司的工资
	@Override
    public SalesSalary findSalesSalaryByCompanyIdQuar(int companyId,int quarter){
    	return competitionResultMapper.findSalesSalaryByCompanyIdQuar(companyId, quarter);
    }
	@Override
	public WorkersSalary findAvgWorkersSalaryByCompetIdQuarter(int competitionId,int quarter){
		return competitionResultMapper.findAvgWorkersSalaryByCompetIdQuarter(competitionId, quarter);
	}
	
	//找每个公司的公司人数
	@Override
    public List<HirePeople> findCompanyPhySalesNum(int companyId,int quarter){
    	return competitionResultMapper.findCompanyPhySalesNum(companyId, quarter);
    }
    @Override
    public List<HirePeopleOnline> findCompanyNetSalesNum(int companyId,int quarter){
    	return competitionResultMapper.findCompanyNetSalesNum(companyId, quarter);
    }
    @Override
    public MarketInfo2 findMarketInfoById(int id){
    	return competitionResultMapper.findMarketInfoById(id);
    }
	//找公司工厂工人薪酬
	@Override
    public WorkersSalary findWorkerSalaryByCompanyIdQuar(int companyId,int quarter){
    	return competitionResultMapper.findWorkerSalaryByCompanyIdQuar(companyId, quarter);
    }
	
	//找产品定价
	@Override
    public ProductPrice findProductPriceByIdQuarter(int productId,int quarter){
    	return competitionResultMapper.findProductPriceByIdQuarter(productId, quarter);
    }
	
	//找该公司所有产品的定价
	@Override
    public List<ProductPrice> findProductPriceByCompanyIdQuarter(int companyId,int quarter){
    	return competitionResultMapper.findProductPriceByCompanyIdQuarter(companyId, quarter);
    }
    
	
	//添加产品各种效用
	@Override
    public void insertProductEfficiency(ProductEfficiency productEfficiency){
    	competitionResultMapper.insertProductEfficiency(productEfficiency);
    }
    
    //查找本次竞赛本季度产品各种效用
	@Override
    public List<ProductEfficiency> findProductEfficiency(int competitionId,int quarter,String type,int marketId){
    	return competitionResultMapper.findProductEfficiency(competitionId, quarter,type,marketId);
    }
    
    //添加各种产品的市场份额
	@Override
    public void insertOrUpdateProductMarketShare(ProductMarketShare productMarketShare){
    	competitionResultMapper.insertOrUpdateProductMarketShare(productMarketShare);
    }
    
    //查找每个产品的市场份额
	@Override
    public List<ProductMarketShare> findProductMSByCompanyIdQuarterType(int companyId,int quarter,String type){
    	return competitionResultMapper.findProductMSByCompanyIdQuarterType(companyId, quarter,type);
    }
	@Override
    public List<ProductMarketShare> findProductMSByCompanyIdQuarter(int companyId,int quarter){
    	return competitionResultMapper.findProductMSByCompanyIdQuarter(companyId, quarter);
    }
	
	@Override
    public List<ProductMarketShare> findProductMSByCompetIdQuarterType(int competitionId,int quarter,String type){
    	return competitionResultMapper.findProductMSByCompetIdQuarterType(competitionId, quarter, type);
    }
	
	//修改每个产品的市场份额
	@Override
    public void updateProductMSByProductIdQuarter(ProductMarketShare productMarketShare){
    	competitionResultMapper.updateProductMSByProductIdQuarter(productMarketShare);
    }
	
	//插入公司市场份额
	@Override
    public void insertCompanyMarketShare(CompanyMarketShare companyMarketShare){
    	competitionResultMapper.insertCompanyMarketShare(companyMarketShare);
    }
	
	//找公司市场份额
	@Override
    public CompanyMarketShare findCompanyMarketShare(int companyId,int quarter){
    	return competitionResultMapper.findCompanyMarketShare(companyId, quarter);
    }
	
	//找市场份额的权值矩阵
	@Override
    public MarketShareWeight findMaketShareWeightByType(String type){
    	return competitionResultMapper.findMaketShareWeightByType(type);
    }
	
	//找本次竞赛该季度的所有产品市场份额
	@Override
    public List<ProductMarketShare> findProductMSByCompetitionIdQuarter(int competitionId,int quarter){
    	return competitionResultMapper.findProductMSByCompetitionIdQuarter(competitionId, quarter);
    }
	
	//插入公司的各种投资
	@Override
    public void insertCompanyInvestment(CompanyInvestment companyInvestment){
    	competitionResultMapper.insertCompanyInvestment(companyInvestment);
    }
    
	@Override
    public CompanyInvestment findCompanyInvestByCompanyIdQuarter(int companyId,int quarter){
    	return competitionResultMapper.findCompanyInvestByCompanyIdQuarter(companyId, quarter);
    }
	
	
	//找公司的平衡记分卡
	@Override
    public BalanceScore findBalanceScoreByCompanyIdQuarter(int companyId,int quarter){
    	return competitionResultMapper.findBalanceScoreByCompanyIdQuarter(companyId, quarter);
    }
	
	@Override
	public IncomeStatement findIncomeStatementResultByCompanyIdQuarter(int companyId,int quarter){
		return competitionResultMapper.findIncomeStatementResultByCompanyIdQuarter(companyId, quarter);
	}
	
	//找所有市场的信息，包含市场需求量
	@Override
    public List<MarketInfo> findAllMarketInfo(){
    	return competitionResultMapper.findAllMarketInfo();
    }
	
	//找市场需求量2
	@Override
    public List<MarketInfo2> findMarketInfoByCompetitionId(int competitionId){
    	return competitionResultMapper.findMarketInfoByCompetitionId(competitionId);
    }
	
	
	//找公司开放的市场
	@Override
    public CompanyMarket findCompanyMarketByCompanyIdQuarter(int companyId,int quarter,int isPhy){
    	return competitionResultMapper.findCompanyMarket(companyId, quarter, isPhy);
    }
	
	//根据产品id和季度找产品市场份额
	@Override
    public ProductMarketShare findProductMSByProductIdQuarter(int productId,int quarter){
    	return competitionResultMapper.findProductMSByProductIdQuarter(productId, quarter);
    }
}
