package cn.usst.market.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/*import cn.usst.market.controller.Test.EnumTest;*/
import cn.usst.market.po.BalanceScore;
import cn.usst.market.po.BalanceSheet;
import cn.usst.market.po.CashFlow;
import cn.usst.market.po.Company;
import cn.usst.market.po.CompanyAdvertise;
import cn.usst.market.po.CompanyCapacity;
import cn.usst.market.po.CompanyFinanceVo;
import cn.usst.market.po.CompanyInvestment;
import cn.usst.market.po.CompanyMarket;
import cn.usst.market.po.CompanyMarketShare;
import cn.usst.market.po.CompanyMedia;
import cn.usst.market.po.CompanyProduct;
import cn.usst.market.po.CompanyQuarterTime;
import cn.usst.market.po.Competition;
import cn.usst.market.po.DemandInfo;
import cn.usst.market.po.EveryProductMarketShareVo;
import cn.usst.market.po.HirePeople;
import cn.usst.market.po.HirePeopleOnline;
import cn.usst.market.po.HirePeopleOnlineVo;
import cn.usst.market.po.HirePeopleVo;
import cn.usst.market.po.IdQuarter;
import cn.usst.market.po.IncomeStatement;
import cn.usst.market.po.MarketInfo;
import cn.usst.market.po.MarketInfo2;
import cn.usst.market.po.MarketSaleNumVo;
import cn.usst.market.po.MarketShareWeight;
import cn.usst.market.po.MediaInfo;
import cn.usst.market.po.OperationCapacity;
import cn.usst.market.po.PriceInfo;
import cn.usst.market.po.ProductEfficiency;
import cn.usst.market.po.ProductInfo;
import cn.usst.market.po.ProductMarketShare;
import cn.usst.market.po.ProductMediaNumVo;
import cn.usst.market.po.ProductPrice;
import cn.usst.market.po.SalesSalary;
import cn.usst.market.po.UsageInfo;
import cn.usst.market.po.WorkersSalary;
import cn.usst.market.service.BalanceScoreService;
import cn.usst.market.service.CompanyService;
import cn.usst.market.service.CompetitionResultService;
import cn.usst.market.service.CompetitionService;
import cn.usst.market.service.DeleteReleaseResultService;
import cn.usst.market.service.PerformanceReportService;
import cn.usst.market.service.PolicyDecisionService;
import cn.usst.market.service.SearchService;
import cn.usst.market.service.StaticInfoService;

@Controller
public class CompetitionResultController {
	public enum TypeEnum {  
	    实用型, 极致型, 商务型;
	}
	@Autowired
	private SearchService searchService;
	
	@Autowired
	private CompetitionService competitionService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private CompetitionResultService competitionResultService;
	
	@Autowired
	private PolicyDecisionService policyDecisionService;
	
	@Autowired
	private BalanceScoreService balanceScoreService;
	
	@Autowired
	private PerformanceReportService performanceReportService;
	
	@Autowired
	private DeleteReleaseResultService deleteReleaseResultService;
	
	@Autowired
	private StaticInfoService staticInfoService;
	
	DecimalFormat df = new DecimalFormat("#.00");
	
	@RequestMapping("/jumpCompetitionResult.do")
	public String jumpCompetitionResult(HttpServletRequest request,Model model,Integer id) throws Exception{
		
		Competition competition= competitionService.findCompetitionById(id);
		//List<Company> companyList= competitionService
		//通过形参中的model将model数据传到页面,相当于上面addObjeect
		/*String msg=new String(message.getBytes(),"UTF-8");*/
		String message=(String)request.getAttribute("message");
		if(message==null){
			message=request.getParameter("message");
			if(message==null){
				message="";
			}else{
				message=new String(message.getBytes("ISO-8859-1"),"UTF-8");
			}
		}
		model.addAttribute("competition", competition);
		model.addAttribute("message", message);
		//返回jsp页面，相当于modelAndView.setViewName
		return "jsp/competitionResult/competitionResult";	
	}
	//结果汇总
	@RequestMapping("/competitionResult/resultSummary.do")
	public String resultSummary(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		
		//净收入
		List<Float> netIncomeList = new ArrayList<Float>();
		//余额
		List<Float> yuEList = new ArrayList<Float>();
		//留存
		List<Float> liucunList = new ArrayList<Float>();
		//市场份额
		List<Double> marketShareList = new ArrayList<Double>();
		//市场需求量
		List<Integer> needNumList= new ArrayList<Integer>();
		//市场销售量
		List<Integer> saleNumList= new ArrayList<Integer>();
		//脱销量
		List<Integer> stockounNumList= new ArrayList<Integer>();
		
		//求上面的和
		if(companyList!=null){
			for(int i=0;i<companyList.size();i++){
			//==============求净收入=============
				/*//毛利
				float grossProfit=0;
				//支出
				float sumPay=0;
				//营业利润
				float sumProfit=0;*/
				//定义净收入
				float netIncome=0;
				
				IdQuarter idQuarter=new IdQuarter();
				idQuarter.setId(companyList.get(i).getId());
				idQuarter.setQuarter(currentQuarter);
				
				IncomeStatement incomeStatement = competitionResultService.findIncomeStatementByIdAndQuarter(idQuarter);
				if(incomeStatement!=null){
					//求和
					/*grossProfit = incomeStatement.getYingyeIncome()-incomeStatement.getYingyeCost()-incomeStatement.getFankuan();
					sumPay = incomeStatement.getYanfa()+incomeStatement.getGuanggao()+incomeStatement.getSalerCost()+incomeStatement.getSalescenterCost()+
								incomeStatement.getBaogao()+incomeStatement.getHuoyun()+incomeStatement.getKucun()+incomeStatement.getZhejiu()+
								incomeStatement.getNetmarketCost();
					sumProfit = grossProfit - sumPay;
					netIncome = sumProfit+incomeStatement.getTechIncome()-incomeStatement.getTechCost()+incomeStatement.getQitaIncome()-incomeStatement.getQitaCost()+
								incomeStatement.getLixiIncome()-incomeStatement.getLixiCost()-incomeStatement.getTaxCost();
					*/
					netIncome=incomeStatement.getYingyeIncome()+incomeStatement.getLixiIncome()-incomeStatement.getYingyeCost()-
							incomeStatement.getFankuan()-incomeStatement.getYanfa()-incomeStatement.getGuanggao()-
							incomeStatement.getSalerCost()-incomeStatement.getSalescenterCost()-incomeStatement.getSalescenterWebCost()-
							incomeStatement.getBaogao()-incomeStatement.getHuoyun()-incomeStatement.getKucun()-incomeStatement.getExcessCapacity()-
							incomeStatement.getZhejiu()-incomeStatement.getNetmarketCost()-incomeStatement.getLixiCost()+
							incomeStatement.getQitaIncome()-incomeStatement.getQitaCost()+incomeStatement.getTechIncome()-
							incomeStatement.getTechCost()-incomeStatement.getTaxCost();
							
					/*netIncome=incomeStatement.getJingLiRun();*/
				}
				
				netIncomeList.add(netIncome);
				
			//================求季末现金流=============
				//定义现金流总和
				float operatingCashSum=0;
				//定义财务活动总和
				float financeActiveSum=0;
				//定义季末现金余额
				float yuE=0;
				
				//现金流表
				CashFlow cashFlow = competitionResultService.findCashFlowByIdAndQuarter(idQuarter);
				if(cashFlow!=null){
					//现金流总和
					/*operatingCashSum=cashFlow.getXiaoshouGet()+cashFlow.getLixiGet()+cashFlow.getJishuGet()+cashFlow.getQitaGet()-cashFlow.getFankuanPay()-
							cashFlow.getShengchanPay()-cashFlow.getYanfaPay()-cashFlow.getGuanggaoPay()-cashFlow.getSalerPay()-cashFlow.getSalescenterPay()-
							cashFlow.getSalescenterWebPay()-
							cashFlow.getDiaoyanPay()-cashFlow.getHuoyunPay()-cashFlow.getKucunPay()-cashFlow.getNetmarketPay()-cashFlow.getTaxPay()-
							cashFlow.getLixiPay()-cashFlow.getJishuPay()-cashFlow.getQitaPay();
					//财务活动总和
					financeActiveSum=cashFlow.getDaikuanNormalGet()+cashFlow.getDaikuanEmergyGet()+cashFlow.getCunkuanRegularGet()-
								cashFlow.getDaikuanNormalPay()-cashFlow.getDaikuanEmergyPay()-cashFlow.getCunkuanRegularPay();
					//季末现金余额应该计算出来并存入当前季度的余额
					IdQuarter idQuarterPre=new IdQuarter();
					idQuarterPre.setId(companyList.get(i).getId());
					idQuarterPre.setQuarter(currentQuarter-1);
					CashFlow cashFlowPre = competitionResultService.findCashFlowByIdAndQuarter(idQuarterPre);
					//定义上季度余额
					float yuEPre=0;
					if(cashFlowPre!=null){
						yuEPre= cashFlowPre.getYuE();
					}
					yuE= financeActiveSum+yuEPre+operatingCashSum-cashFlow.getGongchangPay();
					//这里应该把余额存入数据库中，暂时没有实现。
*/					yuE=cashFlow.getYuE();
				}
				yuEList.add(yuE);
				//================求留存收益=============
				//留存收益
				float liucun = 0;
				BalanceSheet balanceSheet = competitionResultService.findBalanceSheetByIdAndQuarter(idQuarter);
				if(balanceSheet!=null){
					liucun = balanceSheet.getLiucun();
				}
				liucunList.add(liucun);
				
				//===========求市场份额、需求量、销售量等==========
				//??????????????????????????????????????????
				//注意：这里市场份额不能只是简单的相加，而是把公司的需求量/所有公司总需求量
				//如果每种类型的需求量一样的话，则可以相加/3
				double marketShare=0;
				int needNum=0;
				int saleNum=0;
				int stockoun=0;
				CompanyMarketShare companyMS= competitionResultService.findCompanyMarketShare(companyList.get(i).getId(), currentQuarter);
				if(companyMS!=null){
					needNum=companyMS.getBusinessNeed()+companyMS.getPerfectNeed()+companyMS.getPracticalNeed();
					saleNum=companyMS.getBusinessSale()+companyMS.getPerfectSale()+companyMS.getPracticalSale();
					stockoun=companyMS.getBusinessStockoun()+companyMS.getPerfectStockoun()+companyMS.getPracticalStockoun();
				}
				//市场份额
				needNumList.add(needNum);
				saleNumList.add(saleNum);
				stockounNumList.add(stockoun);
				
				//下面计算市场份额
				int needNumSum=0;
				if(currentQuarter>=2){
					for(int a=0;a<companyList.size();a++){
						CompanyMarketShare companyMS2=competitionResultService.findCompanyMarketShare(companyList.get(a).getId(), currentQuarter);
						if(companyMS2!=null){
							needNumSum+=companyMS2.getBusinessNeed()+companyMS2.getPerfectNeed()+companyMS2.getPracticalNeed();
						}
					}
				}
				if(currentQuarter==1){
					marketShareList.add(0.0);
				}else if(currentQuarter>=2){
					double share=0;
					CompanyMarketShare companyMS3=competitionResultService.findCompanyMarketShare(companyList.get(i).getId(), currentQuarter);
					if(companyMS3!=null){
						share=(double)(companyMS3.getBusinessNeed()+companyMS3.getPerfectNeed()+companyMS3.getPracticalNeed())/needNumSum*100;
						share=(double)(Math.round(share*100))/100;
						//String shareDF=df.format(share);
						//share=Double.valueOf(shareDF);
					}
					marketShareList.add(share);
				}
				
			}
		
		}
		
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		model.addAttribute("quarter", currentQuarter);
		model.addAttribute("netIncomeList", netIncomeList);
		model.addAttribute("yuEList", yuEList);
		model.addAttribute("liucunList", liucunList);
		model.addAttribute("marketShareList", marketShareList);
		model.addAttribute("needNumList", needNumList);
		model.addAttribute("saleNumList", saleNumList);
		model.addAttribute("stockounNumList", stockounNumList);
		
		if(currentQuarter==1){
			return "jsp/competitionResult/resultSummary";
		}else if(currentQuarter==2){
			return "jsp/competitionResult/resultSummaryQ2";
		}else if(currentQuarter>=3){
			return "jsp/competitionResult/resultSummaryQ3";
		}
		return "null";
		
	}
	
	//跳转详细竞赛结果
	@RequestMapping("/competitionResult/detailedCompetitionResult.do")
	public String detailedCompetitionResult(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		model.addAttribute("quarter", currentQuarter);
		
		if(currentQuarter==1){
			return "jsp/competitionResult/detailedCompetitionResult";
		}else if(currentQuarter==2){
			return "jsp/competitionResult/detailedCompetitionResultQ2";
		}else if(currentQuarter>=3){
			return "jsp/competitionResult/detailedCompetitionResultQ3";
		}
		return "null";	
	}
	
	//公司决策时间
	@RequestMapping("competitionResult/everyCompanyPolicyDecisionTime.do")
	public String companyPolicyDecisionTime(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		HashMap<String, CompanyQuarterTime> companyQTList=new HashMap<String, CompanyQuarterTime>();
		
		if(companyList!=null&&companyList.size()>0){
			for(int n=0;n<companyList.size();n++){
				CompanyQuarterTime companyQT=competitionResultService.findCompanyQuarterTime(companyList.get(n).getId(), currentQuarter);
				companyQTList.put(companyList.get(n).getName(), companyQT);
			}
			model.addAttribute("competition", competition);
			model.addAttribute("companyQTList", companyQTList);
		}
		return "jsp/competitionResult/detailedCompetitionResult/everyCompanyPolicyDecisionTime";	
	}
	
	//公司平衡记分卡
	@RequestMapping("competitionResult/everyCompanyBalanceScore.do")
	public String everyCompanyBalanceScore(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		HashMap<String, BalanceScore> companyBSList=new HashMap<String, BalanceScore>();
		
		if(companyList!=null&&companyList.size()>0){
			for(int n=0;n<companyList.size();n++){
				BalanceScore balanceScore=competitionResultService.findBalanceScoreByCompanyIdQuarter(companyList.get(n).getId(), currentQuarter);
				companyBSList.put(companyList.get(n).getName(), balanceScore);
			}
			model.addAttribute("competition", competition);
			model.addAttribute("companyQTList", companyBSList);
		}
		return "jsp/competitionResult/detailedCompetitionResult/everyCompanyBalanceScore";	
	}
	
	//利润表
	@RequestMapping("/competitionResult/incomeStatement.do")
	public String incomeStatement(Model model,Integer competitionId,Integer currentQuarter) throws Exception{

		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		List<IncomeStatement> incomeStatementList=new ArrayList<>();
		if(companyList!=null&&companyList.size()>0){
			for(int i=0;i<companyList.size();i++){
				IncomeStatement incomeStatement=new IncomeStatement();
				//incomeStatement=competitionResultService.findIncomeStatementResultByCompanyIdQuarter(companyList.get(i).getId(), currentQuarter);
				List<IncomeStatement> isList=companyService.selectIncomeStatementResult(companyList.get(i).getId(), currentQuarter);
				if(isList!=null&&isList.size()>0){
					incomeStatement=isList.get(0);
				}
				incomeStatementList.add(incomeStatement);
			}
		}
		
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		model.addAttribute("incomeStatementList", incomeStatementList);
		model.addAttribute("quarter", currentQuarter);
		
		return "jsp/competitionResult/detailedCompetitionResult/incomeStatement";	
	}
	
	//现金流表
	@RequestMapping("/competitionResult/cashFlow.do")
	public String cashFlow(Model model,Integer competitionId,Integer currentQuarter) throws Exception{

		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		List<CashFlow> cashFlowList=new ArrayList<>();
		if(companyList!=null&&companyList.size()>0){
			for(int i=0;i<companyList.size();i++){
				CashFlow cashFlow= new CashFlow();
				List<CashFlow> cfList=companyService.selectCashFlowResult(companyList.get(i).getId(), currentQuarter);
				if(cfList!=null&&cfList.size()>0){
					cashFlow = cfList.get(0);
				}
				cashFlowList.add(cashFlow);
			}
		}
		
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		model.addAttribute("cashFlowList", cashFlowList);
		
		model.addAttribute("quarter", currentQuarter);
		
		return "jsp/competitionResult/detailedCompetitionResult/cashFlow";	
	}
	
	//资产负债表
	@RequestMapping("/competitionResult/balanceSheet.do")
	public String balanceSheet(Model model,Integer competitionId,Integer currentQuarter) throws Exception{

		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		List<BalanceSheet> balanceSheetList=new ArrayList<>();

		if(companyList!=null){
			for(int i=0;i<companyList.size();i++){
				BalanceSheet balanceSheet = new BalanceSheet();
				List<BalanceSheet> bsList=companyService.selectBalanceSheetResult(companyList.get(i).getId(), currentQuarter);
				if(bsList!=null&&bsList.size()>0){
					balanceSheet = bsList.get(0);
				}
				balanceSheetList.add(balanceSheet);
			}
		}
		
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		model.addAttribute("balanceSheetList", balanceSheetList);
		model.addAttribute("quarter", currentQuarter);
		
		return "jsp/competitionResult/detailedCompetitionResult/balanceSheet";	
	}
	
	//市场调研报告（终端用户）
	//客户需求
	@RequestMapping(value="competitionResult/showDemandInfo.do")
	public String showDemandInfo(Model model) throws Exception{
		List<DemandInfo> list=competitionService.showDemandInfo();
		model.addAttribute("demandInfoList", list);
		return "jsp/competitionResult/detailedCompetitionResult/customRequired";
	}
	
	//产品用途
	@RequestMapping(value="competitionResult/showUsageInfo.do")
	public String showUsageInfo(Model model) throws Exception{
		List<UsageInfo> list=staticInfoService.showUsageInfo();
		model.addAttribute("productUseList", list);
		return "jsp/competitionResult/detailedCompetitionResult/productUse";
	}
	
	//市场信息
	@RequestMapping(value="competitionResult/showMarketInfo2.do")
	public String showMarketInfo2(Model model,Integer competitionId) throws Exception{
		List<MarketInfo> list=staticInfoService.showMarketInfo(competitionId);
		model.addAttribute("MarketInfoList", list);
		return "jsp/competitionResult/detailedCompetitionResult/marketScale";
	}
	
	//愿意支付的价格
	@RequestMapping(value="competitionResult/showPriceInfo.do")
	public String showPriceInfo(Model model) throws Exception{
		List<PriceInfo> list=staticInfoService.showPriceInfo();
		model.addAttribute("PriceInfoList", list);
		return "jsp/competitionResult/detailedCompetitionResult/marketPrice";
	}
	
	//媒体偏好
	@RequestMapping(value="competitionResult/showMediaInfo.do")
	public String showMediaInfo(Model model) throws Exception{
		List<MediaInfo> list=staticInfoService.showMediaInfo();
		model.addAttribute("MediaInfoList", list);
		return "jsp/competitionResult/detailedCompetitionResult/mediaInfo";
	}
	
	
	//公司市场份额，需求等表
	@RequestMapping("competitionResult/everyCompanyMarketNeedNum.do")
	public String everyCompanyMarketNeedNum(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		HashMap<String, CompanyMarketShare> companyMSList=new HashMap<String, CompanyMarketShare>();
		
		if(companyList!=null&&companyList.size()>0){
			for(int n=0;n<companyList.size();n++){
				CompanyMarketShare companyMS=competitionResultService.findCompanyMarketShare(companyList.get(n).getId(), currentQuarter);
				companyMSList.put(companyList.get(n).getName(), companyMS);
			}
			model.addAttribute("competition", competition);
			model.addAttribute("companyMSList", companyMSList);
		}
		return "jsp/competitionResult/detailedCompetitionResult/everyCompanyMarketNeedNum";	
	}
	
	@RequestMapping("competitionResult/everyCompanyMarketSaleNum.do")
	public String everyCompanyMarketShare(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		HashMap<String, CompanyMarketShare> companyMSList=new HashMap<String, CompanyMarketShare>();
		
		if(companyList!=null&&companyList.size()>0){
			for(int n=0;n<companyList.size();n++){
				CompanyMarketShare companyMS=competitionResultService.findCompanyMarketShare(companyList.get(n).getId(), currentQuarter);
				companyMSList.put(companyList.get(n).getName(), companyMS);
			}
			model.addAttribute("competition", competition);
			model.addAttribute("companyMSList", companyMSList);
		}
//		if(companyList!=null&&companyList.size()>0){
//			for(int n=0;n<companyList.size();n++){
//				CompanyMarketShare companyMS=competitionResultService.findCompanyMarketShare(companyList.get(n).getId(), currentQuarter);
//				companyMSList.put(companyList.get(n).getName(), companyMS);
//			}
//			model.addAttribute("competition", competition);
//			model.addAttribute("companyMSList", companyMSList);
//		}
		return "jsp/competitionResult/detailedCompetitionResult/everyCompanyMarketSaleNum";	
	}
	//所有产品的需求量
	@RequestMapping("competitionResult/everyProductNeedNum.do")
	public String everyProductNeedNum(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		//List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		List<ProductMarketShare> productMSList=competitionResultService.findProductMSByCompetitionIdQuarter(competitionId, currentQuarter);
		List<EveryProductMarketShareVo> everyProductMSList=new ArrayList<>();
		if(productMSList!=null&&productMSList.size()>0){
			for(int n=0;n<productMSList.size();n++){
				EveryProductMarketShareVo everyProMS=new EveryProductMarketShareVo();
				CompanyProduct product=companyService.selectProductByProductId(productMSList.get(n).getProductId());
				Company company=companyService.selectCompanyById(productMSList.get(n).getCompanyId());
				if(product!=null&&company!=null){
					everyProMS.setProductName(product.getName());
					everyProMS.setCompanyName(company.getName());
					everyProMS.setType(productMSList.get(n).getProductType());
					everyProMS.setMarketShare(productMSList.get(n).getMarketShare());
					everyProMS.setSingaporeNeed(productMSList.get(n).getSingaporeNeed());
					everyProMS.setHongkongNeed(productMSList.get(n).getHongkongNeed());
					everyProMS.setMoscowNeed(productMSList.get(n).getMoscowNeed());
					everyProMS.setNewdelhiNeed(productMSList.get(n).getNewdelhiNeed());
					everyProMS.setOnlineNeed(productMSList.get(n).getOnlineNeed());
//					everyProMS.setNeed(productMSList.get(n).getHongkongNeed()+productMSList.get(n).getMoscowNeed()+productMSList.get(n).getNewdelhiNeed()+productMSList.get(n).getSingaporeNeed()+productMSList.get(n).getOnlineNeed());
				}
				everyProductMSList.add(everyProMS);
			}
		}
		model.addAttribute("competition", competition);
		model.addAttribute("everyProductMSList", everyProductMSList);
		
		return "jsp/competitionResult/detailedCompetitionResult/everyProductNeedNum";	
	}
	
	//销量及脱销量
	@RequestMapping("competitionResult/everyCompanySaleAndStockoun.do")
	public String everyCompanySaleAndStockoun(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		HashMap<String, CompanyMarketShare> companyMSList=new HashMap<String, CompanyMarketShare>();
		
		if(companyList!=null&&companyList.size()>0){
			for(int n=0;n<companyList.size();n++){
				CompanyMarketShare companyMS=competitionResultService.findCompanyMarketShare(companyList.get(n).getId(), currentQuarter);
				companyMSList.put(companyList.get(n).getName(), companyMS);
			}
			model.addAttribute("competition", competition);
			model.addAttribute("companyMSList", companyMSList);
		}
		return "jsp/competitionResult/detailedCompetitionResult/everyCompanySaleAndStockoun";	
	}
	
	//产品定价
	@RequestMapping("competitionResult/everyProductPrice.do")
	public String everyProductPrice(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		List<EveryProductMarketShareVo> everyProductMSList=new ArrayList<>();
		
		if(companyList!=null&&companyList.size()>0){
			for(int n=0;n<companyList.size();n++){
				List<ProductPrice> productPriceList=competitionResultService.findProductPriceByCompanyIdQuarter(companyList.get(n).getId(), currentQuarter);
				if(productPriceList!=null){
					for(int m=0;m<productPriceList.size();m++){
						CompanyProduct product=new CompanyProduct();
						product=companyService.selectProductByProductId(productPriceList.get(m).getProductId());
						EveryProductMarketShareVo everyProductMS=new EveryProductMarketShareVo();
						everyProductMS.setCompanyName(companyList.get(n).getName());
						everyProductMS.setProductName(product.getName());
						everyProductMS.setPrice(productPriceList.get(m).getPrice());
						everyProductMSList.add(everyProductMS);
					}
				}
			}
		}
		model.addAttribute("everyProductMSList", everyProductMSList);
		model.addAttribute("competition", competition);
		return "jsp/competitionResult/detailedCompetitionResult/everyProductPrice";	
	}
	
	//公司销售人数
	@RequestMapping("competitionResult/everyCompanyPhySaleNum.do")
	public String everyCompanyPhySaleNum(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		List<MarketSaleNumVo> saleNumList=new ArrayList<MarketSaleNumVo>();
		
		if(companyList!=null&&companyList.size()>0){
			for(int n=0;n<companyList.size();n++){
				List<HirePeople> phySaleNumList=competitionResultService.findCompanyPhySalesNum(companyList.get(n).getId(), currentQuarter);
				if(phySaleNumList!=null&&phySaleNumList.size()>0){
					for(int m=0;m<phySaleNumList.size();m++){
						MarketInfo2 marketInfo=competitionResultService.findMarketInfoById(phySaleNumList.get(m).getMarketId());
						MarketSaleNumVo saleNum=new MarketSaleNumVo();
						saleNum.setCompanyName(companyList.get(n).getName());
						saleNum.setMarketName(marketInfo.getCity());
						saleNum.setMarketType("实体销售中心");
						saleNum.setSaleman(phySaleNumList.get(m).getSaleman());
						saleNum.setAfterSale(phySaleNumList.get(m).getAfterSale());
						saleNumList.add(saleNum);
					}
				}
			}
			model.addAttribute("competition", competition);
			model.addAttribute("saleNumList", saleNumList);
		}
		return "jsp/competitionResult/detailedCompetitionResult/everyCompanyPhySaleNum";	
	}
	@RequestMapping("competitionResult/everyCompanyNetSaleNum.do")
	public String everyCompanyNetSaleNum(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		List<MarketSaleNumVo> saleNumList=new ArrayList<MarketSaleNumVo>();
		
		if(companyList!=null&&companyList.size()>0){
			for(int n=0;n<companyList.size();n++){
				List<HirePeopleOnline> netSaleNumList=competitionResultService.findCompanyNetSalesNum(companyList.get(n).getId(), currentQuarter);
				if(netSaleNumList!=null&&netSaleNumList.size()>0){
					for(int m=0;m<netSaleNumList.size();m++){
						MarketInfo2 marketInfo=competitionResultService.findMarketInfoById(netSaleNumList.get(m).getMarketId());
						MarketSaleNumVo saleNum=new MarketSaleNumVo();
						saleNum.setCompanyName(companyList.get(n).getName());
						saleNum.setMarketName(marketInfo.getCity());
						saleNum.setMarketType("网络销售中心");
						saleNum.setSaleman(netSaleNumList.get(m).getSaleman());
						saleNum.setAfterSale(netSaleNumList.get(m).getAfterSale());
						saleNumList.add(saleNum);
					}
				}
			}
			model.addAttribute("competition", competition);
			model.addAttribute("saleNumList", saleNumList);
		}
		return "jsp/competitionResult/detailedCompetitionResult/everyCompanyNetSaleNum";	
	}
	
	//每个公司开放的市场
	@RequestMapping("competitionResult/everyCompanyOpenMarket.do")
	public String everyCompanyOpenMarket(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		//新建List，保存市场和公司名称,实体和网络
		HashMap<MarketInfo2,String> companyPhyMarketList=new HashMap<MarketInfo2,String>();
		HashMap<MarketInfo2,String> companyNetMarketList=new HashMap<MarketInfo2,String>();
		if(companyList!=null&&companyList.size()>0){
			for(int n=0;n<companyList.size();n++){
				CompanyMarket companyPhyMarket=competitionResultService.findCompanyMarket(companyList.get(n).getId(), currentQuarter, 1);
				CompanyMarket companyNetMarket=competitionResultService.findCompanyMarket(companyList.get(n).getId(), currentQuarter, 0);
				
				if(companyPhyMarket!=null){
					String marketPhyId=companyPhyMarket.getMarketId();
					if(marketPhyId!=null&& marketPhyId.length()>0){
						String[] marketPhyIdArr=marketPhyId.split(",");
						for(int j=0;j<marketPhyIdArr.length;j++){
							MarketInfo2 marketInfo=competitionResultService.findMarketInfoById(Integer.parseInt(marketPhyIdArr[j]));
							companyPhyMarketList.put(marketInfo, companyList.get(n).getName());
						}
					}
				}
				
				if(companyNetMarket!=null){
					String marketNetId=companyNetMarket.getMarketId();
					if(marketNetId!=null&& marketNetId.length()>0){
						String[] marketNetIdArr=marketNetId.split(",");
						for(int j=0;j<marketNetIdArr.length;j++){
							MarketInfo2 marketInfo=competitionResultService.findMarketInfoById(Integer.parseInt(marketNetIdArr[j]));
							companyNetMarketList.put(marketInfo, companyList.get(n).getName());
						}
					}
				}
			}
			model.addAttribute("competition", competition);
			model.addAttribute("companyPhyMarketList", companyPhyMarketList);
			model.addAttribute("companyNetMarketList", companyNetMarketList);
		}
		return "jsp/competitionResult/detailedCompetitionResult/everyCompanyOpenMarket";	
	}
	
	//广告计划
	@RequestMapping("competitionResult/everyCompanyAdPlan.do")
	public String everyCompanyAdPlan(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		//Competition competition= competitionService.findCompetitionById(competitionId);
		//List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		List<ProductMediaNumVo> mediaNumList=new ArrayList<>();
		List<CompanyProduct> productList=competitionResultService.findProductsByCompetIdAndQuarter(competitionId, currentQuarter);
		if(productList!=null&&productList.size()>0){
			for(int n=0;n<productList.size();n++){
				ProductMediaNumVo mediaNumVo= new ProductMediaNumVo();
				mediaNumVo.setProductName(productList.get(n).getName());
				
				Company company1=new Company();
				company1.setId(productList.get(n).getCompanyId());
				Company company2=companyService.findCompanyById(company1);
				if(company2!=null){
					mediaNumVo.setCompanyName(company2.getName());
				}
				
				CompanyAdvertise advertise=policyDecisionService.findAdvertiseByProductIdQuarter(productList.get(n).getId(), currentQuarter);
				if(advertise!=null){
					mediaNumVo.setAdName(advertise.getAdvertiseName());
				}
				
				List<CompanyMedia> mediaList=competitionResultService.findMediaByProductIdAndQuarter(productList.get(n).getId(), currentQuarter);
				int mediaNum=0;
				if(mediaList!=null&&mediaList.size()>0){
					for(int m=0;m<mediaList.size();m++){
						mediaNum+=mediaList.get(m).getNum();
					}
				}
				mediaNumVo.setMediaNum(mediaNum);
				mediaNumList.add(mediaNumVo);
			}
		}
		model.addAttribute("mediaNumList", mediaNumList);

		return "jsp/competitionResult/detailedCompetitionResult/everyCompanyAdPlan";	
	}
	
	
	
	//工人薪酬
	@RequestMapping("competitionResult/everyCompanySalesSalary.do")
	public String everyCompanySalesSalary(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		HashMap<String,SalesSalary> salesSalaryList=new HashMap<String, SalesSalary>();
		if(companyList!=null&&companyList.size()>0){
			for(int n=0;n<companyList.size();n++){
				SalesSalary ss=competitionResultService.findSalesSalaryByCompanyIdQuar(companyList.get(n).getId(), currentQuarter);
				salesSalaryList.put(companyList.get(n).getName(), ss);
			}
		}
		
		model.addAttribute("competition", competition);
		model.addAttribute("salesSalaryList", salesSalaryList);
		return "jsp/competitionResult/detailedCompetitionResult/everyCompanySalesSalary";	
	}
	
	@RequestMapping("competitionResult/everyCompanyWorkerSalary.do")
	public String everyCompanyWorkerSalary(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		HashMap<String,WorkersSalary> workerSalaryList=new HashMap<String, WorkersSalary>();
		if(companyList!=null&&companyList.size()>0){
			for(int n=0;n<companyList.size();n++){
				WorkersSalary ws=competitionResultService.findWorkerSalaryByCompanyIdQuar(companyList.get(n).getId(), currentQuarter);
				workerSalaryList.put(companyList.get(n).getName(), ws);
			}
		}
		
		model.addAttribute("competition", competition);
		model.addAttribute("workerSalaryList", workerSalaryList);
		return "jsp/competitionResult/detailedCompetitionResult/everyCompanyWorkerSalary";	
	}
	
	
	/*=========下面是有用图表的部分==========*/
	//跳转有用图表
	@RequestMapping("/competitionResult/usefulChart.do")
	public String usefulChart(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		model.addAttribute("quarter", currentQuarter);
		
		if(currentQuarter==1){
			return "jsp/competitionResult/usefulChart";
		}else if(currentQuarter==2){
			return "jsp/competitionResult/usefulChartQ2";
		}else if(currentQuarter>=3){
			return "jsp/competitionResult/usefulChartQ3";
		}
		return "null";
		
	}
	
	//资产负债表
	@RequestMapping("/competitionResult/balanceSheetChart.do")
	public String balanceSheetChart(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		
		List<List<Float>> resultChartList = new ArrayList<List<Float>>();
		
		if(companyList!=null && currentQuarter>=1){
			for(int j=1;j<=currentQuarter;j++){
				List<Float> balanceSheetChartList = new ArrayList<Float>();
				for(int i=0;i<companyList.size();i++){
					float assetSum=0;
					List<BalanceSheet> bsResult=companyService.selectBalanceSheetResult(companyList.get(i).getId(), j);
					
					if(bsResult!=null&&bsResult.size()>0){
						//资产
						assetSum=bsResult.get(0).getHuobi()+bsResult.get(0).getCunkuan()+bsResult.get(0).getLixiCollection()+bsResult.get(0).getCunhuo()+bsResult.get(0).getZichan();
					}
					balanceSheetChartList.add(assetSum);
				}
				resultChartList.add(balanceSheetChartList);
			}
			
		}
		
		model.addAttribute("resultChartList", resultChartList);
		model.addAttribute("quarter", currentQuarter);
		model.addAttribute("chartName", "各个公司资产负债表");
		
		return "jsp/competitionResult/everyIndicatorChart";	
	}
	
	//现金表
	@RequestMapping("/competitionResult/cashChart.do")
	public String cashChart(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		
		List<List<Float>> resultChartList = new ArrayList<List<Float>>();
		
		if(companyList!=null && currentQuarter>=1){
			for(int j=1;j<=currentQuarter;j++){
				
				List<Float> moneyList = new ArrayList<Float>();
				
				for(int i=0;i<companyList.size();i++){
					//定义现金流总和
					float operatingCashSum=0;
					//定义财务活动总和
					float financeActiveSum=0;
					//定义季末现金余额
					float yuE=0;
					
					CompanyFinanceVo companyFinanceVo=new CompanyFinanceVo();
					//IdQuarter idQuarter=new IdQuarter();
					//idQuarter.setId(companyList.get(i).getId());
					//idQuarter.setQuarter(j);
					
					companyFinanceVo.setCompany(companyList.get(i));
					//改这个查询结果
					//CashFlow cashFlow = competitionResultService.findCashFlowByIdAndQuarter(idQuarter);
					CashFlow cashFlow = companyService.selectCashFlowResult(companyList.get(i).getId(), j).get(0);
					if(cashFlow!=null){
						companyFinanceVo.setCashFlow(cashFlow);
						//现金流总和
						operatingCashSum=cashFlow.getXiaoshouGet()+cashFlow.getLixiGet()+cashFlow.getJishuGet()+cashFlow.getQitaGet()-cashFlow.getFankuanPay()-
									cashFlow.getShengchanPay()-cashFlow.getYanfaPay()-cashFlow.getGuanggaoPay()-cashFlow.getSalerPay()-cashFlow.getSalescenterPay()-
									cashFlow.getDiaoyanPay()-cashFlow.getHuoyunPay()-cashFlow.getKucunPay()-cashFlow.getNetmarketPay()-cashFlow.getTaxPay()-
									cashFlow.getLixiPay()-cashFlow.getJishuPay()-cashFlow.getQitaPay();
						//财务活动总和
						financeActiveSum=cashFlow.getDaikuanNormalGet()+cashFlow.getDaikuanEmergyGet()+cashFlow.getCunkuanRegularGet()-
									cashFlow.getDaikuanNormalPay()-cashFlow.getDaikuanEmergyPay()-cashFlow.getCunkuanRegularPay();
						//季末现金余额应该计算出来并存入当前季度的余额
						//IdQuarter idQuarterPre=new IdQuarter();
						//idQuarterPre.setId(companyList.get(i).getId());
						//idQuarterPre.setQuarter(j-1);
						//CashFlow cashFlowPre = competitionResultService.findCashFlowByIdAndQuarter(idQuarterPre);
						List<CashFlow> cashFlowPre = companyService.selectCashFlowResult(companyList.get(i).getId(), j-1);
						//定义上季度余额
						float yuEPre=0;
						if(cashFlowPre!=null&&cashFlowPre.size()>0){
							yuEPre= cashFlowPre.get(0).getYuE();
						}
						yuE= financeActiveSum+yuEPre+operatingCashSum-cashFlow.getGongchangPay();
						//这里应该把余额存入数据库中，暂时没有实现。
					}
					//一直到这里
					moneyList.add(yuE);
				}
				resultChartList.add(moneyList);
			}
			
		}
		
		model.addAttribute("resultChartList", resultChartList);
		model.addAttribute("quarter", currentQuarter);
		model.addAttribute("chartName", "各个公司现金表");
		
		return "jsp/competitionResult/everyIndicatorChart";	
	}
	
	//营运现金流operatingCashChart
	@RequestMapping("/competitionResult/operatingCashChart.do")
	public String operatingCashChart(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		
		List<List<Float>> resultChartList = new ArrayList<List<Float>>();
		
		if(companyList!=null && currentQuarter>=1){
			for(int j=1;j<=currentQuarter;j++){
				
				List<Float> moneyList = new ArrayList<Float>();
				
				for(int i=0;i<companyList.size();i++){
					//定义现金流总和
					float operatingCashSum=0;
					List<CashFlow> cashFlow=companyService.selectCashFlowResult(companyList.get(i).getId(), j);
					if(cashFlow!=null&&cashFlow.size()>0){
						operatingCashSum=cashFlow.get(0).getXiaoshouGet()+cashFlow.get(0).getLixiGet()+
								cashFlow.get(0).getJishuGet()+cashFlow.get(0).getQitaGet()-cashFlow.get(0).getFankuanPay()-
								cashFlow.get(0).getShengchanPay()-cashFlow.get(0).getYanfaPay()-cashFlow.get(0).getGuanggaoPay()-
								cashFlow.get(0).getSalerPay()-cashFlow.get(0).getSalescenterPay()-
								cashFlow.get(0).getSalescenterWebPay()-cashFlow.get(0).getDiaoyanPay()-
								cashFlow.get(0).getHuoyunPay()-cashFlow.get(0).getKucunPay()-cashFlow.get(0).getNetmarketPay()-
								cashFlow.get(0).getTaxPay()-cashFlow.get(0).getLixiPay()-cashFlow.get(0).getJishuPay()-cashFlow.get(0).getQitaPay();
						
					}
					
					//一直到这里
					moneyList.add(operatingCashSum);
				}
				resultChartList.add(moneyList);
			}
			
		}
		
		model.addAttribute("resultChartList", resultChartList);
		model.addAttribute("quarter", currentQuarter);
		model.addAttribute("chartName", "各个公司营业现金表");
		
		return "jsp/competitionResult/everyIndicatorChart";	
	}
	//净股本
	@RequestMapping("/competitionResult/companyNetStockChart.do")
	public String companyNetStockChart(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		
		List<List<Float>> resultChartList = new ArrayList<List<Float>>();
		if(companyList!=null && currentQuarter>=1){
			for(int j=1;j<=currentQuarter;j++){
				List<Float> moneyList = new ArrayList<Float>();
				for(int i=0;i<companyList.size();i++){
					//定义现金流总和
					float moneySum=0;
					List<BalanceSheet> balanceSheet=companyService.selectBalanceSheetResult(companyList.get(i).getId(), j);
					if(balanceSheet!=null&&balanceSheet.size()>0){
						moneySum=balanceSheet.get(0).getGuben();
					}
					moneyList.add(moneySum);
				}
				resultChartList.add(moneyList);
			}
		}
		
		model.addAttribute("resultChartList", resultChartList);
		model.addAttribute("quarter", currentQuarter);
		model.addAttribute("chartName", "各个公司净股本表");
		
		return "jsp/competitionResult/everyIndicatorChart";	
	}
	
	//总负债表
	@RequestMapping("/competitionResult/companySumDebtChart.do")
	public String companySumDebtChart(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		
		List<List<Float>> resultChartList = new ArrayList<List<Float>>();
		if(companyList!=null && currentQuarter>=1){
			for(int j=1;j<=currentQuarter;j++){
				List<Float> moneyList = new ArrayList<Float>();
				for(int i=0;i<companyList.size();i++){
					//定义资金数量
					float moneySum=0;
					List<BalanceSheet> balanceSheet=companyService.selectBalanceSheetResult(companyList.get(i).getId(), j);
					if(balanceSheet!=null&&balanceSheet.size()>0){
						moneySum=balanceSheet.get(0).getLiucun();
					}
					moneyList.add(moneySum);
				}
				resultChartList.add(moneyList);
			}
		}
		
		model.addAttribute("resultChartList", resultChartList);
		model.addAttribute("quarter", currentQuarter);
		model.addAttribute("chartName", "各个公司总负债表");
		
		return "jsp/competitionResult/everyIndicatorChart";	
	}
	
	//留存收益表
	@RequestMapping("/competitionResult/companyLiucunChart.do")
	public String companyLiucunChart(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		
		List<List<Float>> resultChartList = new ArrayList<List<Float>>();
		if(companyList!=null && currentQuarter>=1){
			for(int j=1;j<=currentQuarter;j++){
				List<Float> moneyList = new ArrayList<Float>();
				for(int i=0;i<companyList.size();i++){
					//定义资金数量
					float moneySum=0;
					List<BalanceSheet> balanceSheet=companyService.selectBalanceSheetResult(companyList.get(i).getId(), j);
					if(balanceSheet!=null&&balanceSheet.size()>0){
						moneySum=balanceSheet.get(0).getLiucun();
					}
					moneyList.add(moneySum);
				}
				resultChartList.add(moneyList);
			}
		}
		
		model.addAttribute("resultChartList", resultChartList);
		model.addAttribute("quarter", currentQuarter);
		model.addAttribute("chartName", "各个公司总负债表");
		
		return "jsp/competitionResult/everyIndicatorChart";	
	}
	
	//收入表
	@RequestMapping("/competitionResult/companyIncomeChart.do")
	public String companyIncomeChart(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		
		List<List<Float>> resultChartList = new ArrayList<List<Float>>();
		if(companyList!=null && currentQuarter>=1){
			for(int j=1;j<=currentQuarter;j++){
				List<Float> moneyList = new ArrayList<Float>();
				for(int i=0;i<companyList.size();i++){
					//定义资金数量
					float moneySum=0;
					List<CashFlow> cashFlow=companyService.selectCashFlowResult(companyList.get(i).getId(), j);
					if(cashFlow!=null&&cashFlow.size()>0){
						moneySum=cashFlow.get(0).getXiaoshouGet()+cashFlow.get(0).getLixiGet()+cashFlow.get(0).getJishuGet()+
								cashFlow.get(0).getQitaGet()+cashFlow.get(0).getChouZiGet()+cashFlow.get(0).getCunkuanRegularGet()+
								cashFlow.get(0).getDaikuanEmergyGet()+cashFlow.get(0).getDaikuanNormalGet()+cashFlow.get(0).getXianJinGet();
					}
					moneyList.add(moneySum);
				}
				resultChartList.add(moneyList);
			}
		}
		
		model.addAttribute("resultChartList", resultChartList);
		model.addAttribute("quarter", currentQuarter);
		model.addAttribute("chartName", "各个公司收入表");
		
		return "jsp/competitionResult/everyIndicatorChart";	
	}	
	
	//销货成本表
	@RequestMapping("/competitionResult/companySellingCostChart.do")
	public String companySellingCostChart(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		
		List<List<Float>> resultChartList = new ArrayList<List<Float>>();
		if(companyList!=null && currentQuarter>=1){
			for(int j=1;j<=currentQuarter;j++){
				List<Float> moneyList = new ArrayList<Float>();
				for(int i=0;i<companyList.size();i++){
					//定义资金数量
					float moneySum=0;
					List<CashFlow> cashFlow=companyService.selectCashFlowResult(companyList.get(i).getId(), j);
					if(cashFlow!=null&&cashFlow.size()>0){
						moneySum=cashFlow.get(0).getShengchanPay();
					}
					moneyList.add(moneySum);
				}
				resultChartList.add(moneyList);
			}
		}
		
		model.addAttribute("resultChartList", resultChartList);
		model.addAttribute("quarter", currentQuarter);
		model.addAttribute("chartName", "各个公司销货成本表");
		
		return "jsp/competitionResult/everyIndicatorChart";	
	}
	//网络营销费用表
	@RequestMapping("/competitionResult/companyNetMarketCostChart.do")
	public String companyNetMarketCostChart(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		
		List<List<Float>> resultChartList = new ArrayList<List<Float>>();
		if(companyList!=null && currentQuarter>=1){
			for(int j=1;j<=currentQuarter;j++){
				List<Float> moneyList = new ArrayList<Float>();
				for(int i=0;i<companyList.size();i++){
					//定义资金数量
					float moneySum=0;
					List<CashFlow> cashFlow=companyService.selectCashFlowResult(companyList.get(i).getId(), j);
					if(cashFlow!=null&&cashFlow.size()>0){
						moneySum=cashFlow.get(0).getSalescenterPay()+cashFlow.get(0).getNetmarketPay();
					}
					moneyList.add(moneySum);
				}
				resultChartList.add(moneyList);
			}
		}
		
		model.addAttribute("resultChartList", resultChartList);
		model.addAttribute("quarter", currentQuarter);
		model.addAttribute("chartName", "各个公司网络营销费用表");
		
		return "jsp/competitionResult/everyIndicatorChart";	
	}
	
	
	//净收入表
	@RequestMapping("/competitionResult/netIncomeChart.do")
	public String netIncomeChart(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		
		List<List<Float>> resultChartList = new ArrayList<List<Float>>();
		
		if(companyList!=null && currentQuarter>=1){
			for(int j=1;j<=currentQuarter;j++){
				
				List<Float> moneyList = new ArrayList<Float>();
				
				for(int i=0;i<companyList.size();i++){
					/*//毛利
					float grossProfit=0;
					//支出
					float sumPay=0;
					//营业利润
					float sumProfit=0;*/
					//定义净收入
					float netIncome=0;
					
					//CompanyFinanceVo companyFinanceVo=new CompanyFinanceVo();
					//IdQuarter idQuarter=new IdQuarter();
					//idQuarter.setId(companyList.get(i).getId());
					//idQuarter.setQuarter(j);
					
					//companyFinanceVo.setCompany(companyList.get(i));
					//IncomeStatement incomeStatement = competitionResultService.findIncomeStatementByIdAndQuarter(idQuarter);
					IncomeStatement incomeStatement = companyService.selectIncomeStatementResult(companyList.get(i).getId(), j).get(0);
					
					if(incomeStatement!=null){
						//companyFinanceVo.setIncomeStatement(incomeStatement);
						//求和
						/*grossProfit = incomeStatement.getYingyeIncome()-incomeStatement.getYingyeCost()-incomeStatement.getFankuan();
						sumPay = incomeStatement.getYanfa()+incomeStatement.getGuanggao()+incomeStatement.getSalerCost()+incomeStatement.getSalescenterCost()+
									incomeStatement.getBaogao()+incomeStatement.getHuoyun()+incomeStatement.getKucun()+incomeStatement.getZhejiu()+
									incomeStatement.getNetmarketCost();
						sumProfit = grossProfit - sumPay;
						netIncome = sumProfit+incomeStatement.getTechIncome()-incomeStatement.getTechCost()+incomeStatement.getQitaIncome()-incomeStatement.getQitaCost()+
									incomeStatement.getLixiIncome()-incomeStatement.getLixiCost()-incomeStatement.getTaxCost();*/
						netIncome=incomeStatement.getYingyeIncome()+incomeStatement.getLixiIncome()-incomeStatement.getYingyeCost()-
								incomeStatement.getFankuan()-incomeStatement.getYanfa()-incomeStatement.getGuanggao()-
								incomeStatement.getSalerCost()-incomeStatement.getSalescenterCost()-incomeStatement.getSalescenterWebCost()-
								incomeStatement.getBaogao()-incomeStatement.getHuoyun()-incomeStatement.getKucun()-incomeStatement.getExcessCapacity()-
								incomeStatement.getZhejiu()-incomeStatement.getNetmarketCost()-incomeStatement.getLixiCost()+
								incomeStatement.getQitaIncome()-incomeStatement.getQitaCost()+incomeStatement.getTechIncome()-
								incomeStatement.getTechCost()-incomeStatement.getTaxCost();
								
					}
					
					//一直到这里
					moneyList.add(netIncome);
				}
				resultChartList.add(moneyList);
			}
			
		}
		
		model.addAttribute("resultChartList", resultChartList);
		model.addAttribute("quarter", currentQuarter);
		model.addAttribute("chartName", "净收入");
		
		return "jsp/competitionResult/everyIndicatorChart";	
	}
	
	//========================================
	//市场规模表
	@RequestMapping("/competitionResult/marketNeedChart.do")
	public String marketNeedChart(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		
		List<List<Integer>> resultChartList = new ArrayList<List<Integer>>();
		if(companyList!=null && currentQuarter>=1&&currentQuarter<=competition.getQuarter()){
			List<Integer> marketneedList = new ArrayList<Integer>();
			for(int j=1;j<=currentQuarter;j++){
				if(j==1){
					marketneedList.add(0);
				}else if(j>=2){
					int need=0;
					for(int i=0;i<companyList.size();i++){
						CompanyMarketShare companyMS=competitionResultService.findCompanyMarketShare(companyList.get(i).getId(), j);
						if(companyMS!=null){
							need=companyMS.getBusinessNeed()+companyMS.getPerfectNeed()+companyMS.getPracticalNeed();
						}
					}
					marketneedList.add(need);
				}
				resultChartList.add(marketneedList);
			}
		}
		
		model.addAttribute("resultChartList", resultChartList);
		model.addAttribute("quarter", currentQuarter);
		model.addAttribute("chartName", "本页面暂时有问题，需要修改，要新建jsp页面，然后修改表格列数！！！");
		return "jsp/competitionResult/everyIndicatorChart";	
	}
	
	//各个公司市场份额表
	@RequestMapping("/competitionResult/companyMarketShareChart.do")
	public String companyMarketShareChart(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		
		List<List<Double>> resultChartList = new ArrayList<List<Double>>();
		if(companyList!=null && currentQuarter>=1&&currentQuarter<=competition.getQuarter()){
			for(int j=1;j<=currentQuarter;j++){
				List<Double> marketShareList = new ArrayList<Double>();
				int needNumSum=0;
				if(j>=2){
					for(int a=0;a<companyList.size();a++){
						CompanyMarketShare companyMS=competitionResultService.findCompanyMarketShare(companyList.get(a).getId(), j);
						if(companyMS!=null){
							needNumSum+=companyMS.getBusinessNeed()+companyMS.getPerfectNeed()+companyMS.getPracticalNeed();
						}
					}
				}
				
				for(int i=0;i<companyList.size();i++){
					if(j==1){
						marketShareList.add(0.0);
					}else if(j>=2){
						double share=0;
						CompanyMarketShare companyMS=competitionResultService.findCompanyMarketShare(companyList.get(i).getId(), j);
						if(companyMS!=null){
							share=(double)(companyMS.getBusinessNeed()+companyMS.getPerfectNeed()+companyMS.getPracticalNeed())/needNumSum*100;
							share=(double)(Math.round(share*1000))/1000;
							
							/*String shareDF=df.format(share);
							share=Double.valueOf(shareDF);*/
						}
						marketShareList.add(share);
					}
				}
				resultChartList.add(marketShareList);
			}
		}
		
		model.addAttribute("resultChartList", resultChartList);
		model.addAttribute("quarter", currentQuarter);
		model.addAttribute("chartName", "各个公司市场份额（%）");
		return "jsp/competitionResult/everyIndicatorChart";	
	}
	
	//各个类型市场份额表
	@RequestMapping("/competitionResult/practicalMarketShareChart.do")
	public String practicalMarketShareChart(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		
		List<List<Double>> resultChartList = new ArrayList<List<Double>>();
		if(companyList!=null && currentQuarter>=1&&currentQuarter<=competition.getQuarter()){
			for(int j=1;j<=currentQuarter;j++){
				List<Double> marketShareList = new ArrayList<Double>();
				for(int i=0;i<companyList.size();i++){
					if(j==1){
						marketShareList.add(0.0);
					}else if(j>=2){
						double share=0;
						CompanyMarketShare companyMS=competitionResultService.findCompanyMarketShare(companyList.get(i).getId(), j);
						if(companyMS!=null){
							share=(double)companyMS.getPracticalShare()*100;
							share=(double)(Math.round(share*100))/100;
							/*String shareDF=df.format(share);
							share=Double.valueOf(shareDF);*/
						}
						marketShareList.add(share);
					}
				}
				resultChartList.add(marketShareList);
			}
		}
		
		model.addAttribute("resultChartList", resultChartList);
		model.addAttribute("quarter", currentQuarter);
		model.addAttribute("chartName", "实用型市场份额（%）");
		return "jsp/competitionResult/everyIndicatorChart";	
	}
	
	@RequestMapping("/competitionResult/perfectMarketShareChart.do")
	public String perfectMarketShareChart(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		
		List<List<Double>> resultChartList = new ArrayList<List<Double>>();
		if(companyList!=null && currentQuarter>=1&&currentQuarter<=competition.getQuarter()){
			for(int j=1;j<=currentQuarter;j++){
				List<Double> marketShareList = new ArrayList<Double>();
				for(int i=0;i<companyList.size();i++){
					if(j==1){
						marketShareList.add(0.0);
					}else if(j>=2){
						double share=0;
						CompanyMarketShare companyMS=competitionResultService.findCompanyMarketShare(companyList.get(i).getId(), j);
						if(companyMS!=null){
							share=(double)companyMS.getPerfectShare()*100;
							share=(double)(Math.round(share*100))/100;
							/*String shareDF=df.format(share);
							share=Double.valueOf(shareDF);*/
						}
						marketShareList.add(share);
					}
				}
				resultChartList.add(marketShareList);
			}
		}
		
		model.addAttribute("resultChartList", resultChartList);
		model.addAttribute("quarter", currentQuarter);
		model.addAttribute("chartName", "极致型市场份额（%）");
		return "jsp/competitionResult/everyIndicatorChart";	
	}
	
	@RequestMapping("/competitionResult/businessMarketShareChart.do")
	public String businessMarketShareChart(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		
		List<List<Double>> resultChartList = new ArrayList<List<Double>>();
		if(companyList!=null && currentQuarter>=1&&currentQuarter<=competition.getQuarter()){
			for(int j=1;j<=currentQuarter;j++){
				List<Double> marketShareList = new ArrayList<Double>();
				for(int i=0;i<companyList.size();i++){
					if(j==1){
						marketShareList.add(0.0);
					}else if(j>=2){
						double share=0;
						CompanyMarketShare companyMS=competitionResultService.findCompanyMarketShare(companyList.get(i).getId(), j);
						if(companyMS!=null){
							share=(double)companyMS.getBusinessShare()*100;
							share=(double)(Math.round(share*1000))/1000;
							/*String shareDF=df.format(share);
							share=Double.valueOf(shareDF);*/
						}
						marketShareList.add(share);
					}
				}
				resultChartList.add(marketShareList);
			}
		}
		
		model.addAttribute("resultChartList", resultChartList);
		model.addAttribute("quarter", currentQuarter);
		model.addAttribute("chartName", "商务市场份额（%）");
		return "jsp/competitionResult/everyIndicatorChart";	
	}
	
	//各个公司需求量
	@RequestMapping("/competitionResult/companyNeedNumChart.do")
	public String companyNeedNumChart(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		
		List<List<Integer>> resultChartList = new ArrayList<List<Integer>>();
		if(companyList!=null && currentQuarter>=1&&currentQuarter<=competition.getQuarter()){
			for(int j=1;j<=currentQuarter;j++){
				List<Integer> companyNeedList = new ArrayList<Integer>();
				for(int i=0;i<companyList.size();i++){
					if(j==1){
						companyNeedList.add(0);
					}else if(j>=2){
						int need=0;
						CompanyMarketShare companyMS=competitionResultService.findCompanyMarketShare(companyList.get(i).getId(), j);
						if(companyMS!=null){
							need=companyMS.getBusinessNeed()+companyMS.getPerfectNeed()+companyMS.getPracticalNeed();
						}
						companyNeedList.add(need);
					}
				}
				resultChartList.add(companyNeedList);
			}
		}
		
		model.addAttribute("resultChartList", resultChartList);
		model.addAttribute("quarter", currentQuarter);
		model.addAttribute("chartName", "各个公司市场需求量");
		return "jsp/competitionResult/everyIndicatorChart";	
	}
	
	//各个公司需求量图表
	@RequestMapping("/competitionResult/companySaleNumChart.do")
	public String companySaleNumChart(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		
		List<List<Integer>> resultChartList = new ArrayList<List<Integer>>();
		if(companyList!=null && currentQuarter>=1&&currentQuarter<=competition.getQuarter()){
			for(int j=1;j<=currentQuarter;j++){
				List<Integer> companySaleList = new ArrayList<Integer>();
				for(int i=0;i<companyList.size();i++){
					if(j==1){
						companySaleList.add(0);
					}else if(j>=2){
						int sale=0;
						CompanyMarketShare companyMS=competitionResultService.findCompanyMarketShare(companyList.get(i).getId(), j);
						if(companyMS!=null){
							sale=companyMS.getBusinessSale()+companyMS.getPerfectSale()+companyMS.getPracticalSale();
						}
						companySaleList.add(sale);
					}
				}
				resultChartList.add(companySaleList);
			}
		}
		
		model.addAttribute("resultChartList", resultChartList);
		model.addAttribute("quarter", currentQuarter);
		model.addAttribute("chartName", "各个公司市场销售量");
		return "jsp/competitionResult/everyIndicatorChart";	
	}
	
	//各个公司的品牌数量表
	@RequestMapping("/competitionResult/companyProductNumChart.do")
	public String companyProductNumChart(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		
		List<List<Integer>> resultChartList = new ArrayList<List<Integer>>();
		if(companyList!=null && currentQuarter>=1&&currentQuarter<=competition.getQuarter()){
			for(int j=1;j<=currentQuarter;j++){
				List<Integer> productNumList = new ArrayList<Integer>();
				for(int i=0;i<companyList.size();i++){
					if(j==1){
						productNumList.add(0);
					}else if(j>=2){
						int productNum=0;
						CompanyInvestment companyInvestment=competitionResultService.findCompanyInvestByCompanyIdQuarter(companyList.get(i).getId(), j);
						if(companyInvestment!=null){
							productNum=companyInvestment.getProductNum();
						}
						productNumList.add(productNum);
					}
				}
				resultChartList.add(productNumList);
			}
		}
		
		model.addAttribute("resultChartList", resultChartList);
		model.addAttribute("quarter", currentQuarter);
		model.addAttribute("chartName", "各个公司品牌数量");
		return "jsp/competitionResult/everyIndicatorChart";	
	}
	
	//平均价格图表
	@RequestMapping("/competitionResult/companyAvgPriceChart.do")
	public String companyAvgPriceChart(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		
		List<List<Integer>> resultChartList = new ArrayList<List<Integer>>();
		if(companyList!=null && currentQuarter>=1&&currentQuarter<=competition.getQuarter()){
			for(int j=1;j<=currentQuarter;j++){
				List<Integer> avgPriceList = new ArrayList<Integer>();
				for(int i=0;i<companyList.size();i++){
					if(j==1){
						avgPriceList.add(0);
					}else if(j>=2){
						int price=0;
						CompanyInvestment companyInvestment=competitionResultService.findCompanyInvestByCompanyIdQuarter(companyList.get(i).getId(), j);
						if(companyInvestment!=null){
							price=companyInvestment.getProductAvgPrice();
						}
						avgPriceList.add(price);
					}
				}
				resultChartList.add(avgPriceList);
			}
		}
		
		model.addAttribute("resultChartList", resultChartList);
		model.addAttribute("quarter", currentQuarter);
		model.addAttribute("chartName", "各个公司平均价格表");
		return "jsp/competitionResult/everyIndicatorChart";	
	}
	//媒体总投资
	@RequestMapping("/competitionResult/companyAdCostChart.do")
	public String companyAdCostChart(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		
		List<List<Integer>> resultChartList = new ArrayList<List<Integer>>();
		if(companyList!=null && currentQuarter>=1&&currentQuarter<=competition.getQuarter()){
			for(int j=1;j<=currentQuarter;j++){
				List<Integer> adCostList = new ArrayList<Integer>();
				for(int i=0;i<companyList.size();i++){
					if(j==1){
						adCostList.add(0);
					}else if(j>=2){
						int adCost=0;
						CompanyInvestment companyInvestment=competitionResultService.findCompanyInvestByCompanyIdQuarter(companyList.get(i).getId(), j);
						if(companyInvestment!=null){
							adCost=companyInvestment.getAdCost();
						}
						adCostList.add(adCost);
					}
				}
				resultChartList.add(adCostList);
			}
		}
		
		model.addAttribute("resultChartList", resultChartList);
		model.addAttribute("quarter", currentQuarter);
		model.addAttribute("chartName", "各个公司广告总投资");
		return "jsp/competitionResult/everyIndicatorChart";	
	}
	
	//媒体投放数量
	@RequestMapping("/competitionResult/companyAdNumChart.do")
	public String companyAdNumChart(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		
		List<List<Integer>> resultChartList = new ArrayList<List<Integer>>();
		if(companyList!=null && currentQuarter>=1&&currentQuarter<=competition.getQuarter()){
			for(int j=1;j<=currentQuarter;j++){
				List<Integer> adNumList = new ArrayList<Integer>();
				for(int i=0;i<companyList.size();i++){
					if(j==1){
						adNumList.add(0);
					}else if(j>=2){
						int adNum=0;
						CompanyInvestment companyInvestment=competitionResultService.findCompanyInvestByCompanyIdQuarter(companyList.get(i).getId(), j);
						if(companyInvestment!=null){
							adNum=companyInvestment.getAdNum();
						}
						adNumList.add(adNum);
					}
				}
				resultChartList.add(adNumList);
			}
		}
		
		model.addAttribute("resultChartList", resultChartList);
		model.addAttribute("quarter", currentQuarter);
		model.addAttribute("chartName", "各个公司媒体投放数量表");
		return "jsp/competitionResult/everyIndicatorChart";	
	}
	
	//实体及网络销售中心费用
	@RequestMapping("/competitionResult/companySaleCenterCostChart.do")
	public String companySaleCenterCostChart(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		
		List<List<Integer>> resultChartList = new ArrayList<List<Integer>>();
		if(companyList!=null && currentQuarter>=1&&currentQuarter<=competition.getQuarter()){
			for(int j=1;j<=currentQuarter;j++){
				List<Integer> saleCenterCostList = new ArrayList<Integer>();
				for(int i=0;i<companyList.size();i++){
					if(j==1){
						saleCenterCostList.add(0);
					}else if(j>=2){
						int saleCenterCost=0;
						CompanyInvestment companyInvestment=competitionResultService.findCompanyInvestByCompanyIdQuarter(companyList.get(i).getId(), j);
						if(companyInvestment!=null){
							saleCenterCost=companyInvestment.getNetMarketCost()+companyInvestment.getPhyMarketCost();
						}
						saleCenterCostList.add(saleCenterCost);
					}
				}
				resultChartList.add(saleCenterCostList);
			}
		}
		
		model.addAttribute("resultChartList", resultChartList);
		model.addAttribute("quarter", currentQuarter);
		model.addAttribute("chartName", "各个公司市场投资表");
		return "jsp/competitionResult/everyIndicatorChart";	
	}
	
	//实体及网络市场数量
	@RequestMapping("/competitionResult/companyMarketNumChart.do")
	public String companyMarketNumChart(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		
		List<List<Integer>> resultChartList = new ArrayList<List<Integer>>();
		if(companyList!=null && currentQuarter>=1&&currentQuarter<=competition.getQuarter()){
			for(int j=1;j<=currentQuarter;j++){
				List<Integer> marketNumList = new ArrayList<Integer>();
				for(int i=0;i<companyList.size();i++){
					if(j==1){
						marketNumList.add(0);
					}else if(j>=2){
						int marketNum=0;
						CompanyInvestment companyInvestment=competitionResultService.findCompanyInvestByCompanyIdQuarter(companyList.get(i).getId(), j);
						if(companyInvestment!=null){
							marketNum=companyInvestment.getPhyMarketNum()+companyInvestment.getNetMarketNum();
						}
						marketNumList.add(marketNum);
					}
				}
				resultChartList.add(marketNumList);
			}
		}
		
		model.addAttribute("resultChartList", resultChartList);
		model.addAttribute("quarter", currentQuarter);
		model.addAttribute("chartName", "各个公司开放市场数量表");
		return "jsp/competitionResult/everyIndicatorChart";	
	}
	
	//销售人员总数
	@RequestMapping("/competitionResult/companySalesNumChart.do")
	public String companySalesNumChart(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		
		List<List<Integer>> resultChartList = new ArrayList<List<Integer>>();
		if(companyList!=null && currentQuarter>=1&&currentQuarter<=competition.getQuarter()){
			for(int j=1;j<=currentQuarter;j++){
				List<Integer> SalesNumList = new ArrayList<Integer>();
				for(int i=0;i<companyList.size();i++){
					if(j==1){
						SalesNumList.add(0);
					}else if(j>=2){
						int salesNum=0;
						CompanyInvestment companyInvestment=competitionResultService.findCompanyInvestByCompanyIdQuarter(companyList.get(i).getId(), j);
						if(companyInvestment!=null){
							salesNum=companyInvestment.getPhySalesNum()+companyInvestment.getNetSalesNum();
						}
						SalesNumList.add(salesNum);
					}
				}
				resultChartList.add(SalesNumList);
			}
		}
		
		model.addAttribute("resultChartList", resultChartList);
		model.addAttribute("quarter", currentQuarter);
		model.addAttribute("chartName", "各个公司销售人员数量表");
		return "jsp/competitionResult/everyIndicatorChart";	
	}
	
	//销售人员费用
	@RequestMapping("/competitionResult/companySalesCostChart.do")
	public String companySalesCostChart(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		
		List<List<Integer>> resultChartList = new ArrayList<List<Integer>>();
		if(companyList!=null && currentQuarter>=1&&currentQuarter<=competition.getQuarter()){
			for(int j=1;j<=currentQuarter;j++){
				List<Integer> salesCostList = new ArrayList<Integer>();
				for(int i=0;i<companyList.size();i++){
					if(j==1){
						salesCostList.add(0);
					}else if(j>=2){
						int salesCost=0;
						CompanyInvestment companyInvestment=competitionResultService.findCompanyInvestByCompanyIdQuarter(companyList.get(i).getId(), j);
						if(companyInvestment!=null){
							salesCost=companyInvestment.getPhySalesCost()+companyInvestment.getNetSalesCost();
						}
						salesCostList.add(salesCost);
					}
				}
				resultChartList.add(salesCostList);
			}
		}
		
		model.addAttribute("resultChartList", resultChartList);
		model.addAttribute("quarter", currentQuarter);
		model.addAttribute("chartName", "各个公司销售人员费用表");
		return "jsp/competitionResult/everyIndicatorChart";	
	}
	
	//工厂工人效率
	@RequestMapping("/competitionResult/companyWorkerEffiChart.do")
	public String companyWorkerEffiChart(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		
		List<List<Double>> resultChartList = new ArrayList<List<Double>>();
		if(companyList!=null && currentQuarter>=1&&currentQuarter<=competition.getQuarter()){
			for(int j=1;j<=currentQuarter;j++){
				List<Double> workerEffiList = new ArrayList<Double>();
				for(int i=0;i<companyList.size();i++){
					if(j==1){
						workerEffiList.add(0.00);
					}else if(j>=2){
						double workerEffi=0;
						CompanyInvestment companyInvestment=competitionResultService.findCompanyInvestByCompanyIdQuarter(companyList.get(i).getId(), j);
						if(companyInvestment!=null){
							workerEffi=companyInvestment.getWorkerEfficiency();
						}
						workerEffiList.add(workerEffi);
					}
				}
				resultChartList.add(workerEffiList);
			}
		}
		
		model.addAttribute("resultChartList", resultChartList);
		model.addAttribute("quarter", currentQuarter);
		model.addAttribute("chartName", "各个公司工厂工人效率表");
		return "jsp/competitionResult/everyIndicatorChart";	
	}
	
	//销售工人效率
	@RequestMapping("/competitionResult/companySalesEffiChart.do")
	public String companySalesEffiChart(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		
		List<List<Double>> resultChartList = new ArrayList<List<Double>>();
		if(companyList!=null && currentQuarter>=1&&currentQuarter<=competition.getQuarter()){
			for(int j=1;j<=currentQuarter;j++){
				List<Double> salesEffiList = new ArrayList<Double>();
				for(int i=0;i<companyList.size();i++){
					if(j==1){
						salesEffiList.add(0.00);
					}else if(j>=2){
						double salesEffi=0;
						CompanyInvestment companyInvestment=competitionResultService.findCompanyInvestByCompanyIdQuarter(companyList.get(i).getId(), j);
						if(companyInvestment!=null){
							salesEffi=companyInvestment.getSalesEfficiency();
						}
						salesEffiList.add(salesEffi);
					}
				}
				resultChartList.add(salesEffiList);
			}
		}
		
		model.addAttribute("resultChartList", resultChartList);
		model.addAttribute("quarter", currentQuarter);
		model.addAttribute("chartName", "各个公司销售工人效率表");
		return "jsp/competitionResult/everyIndicatorChart";	
	}
	
	//固定产能表
	@RequestMapping("/competitionResult/companyFixedCapacityChart.do")
	public String companyFixedCapacityChart(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		
		List<List<Integer>> resultChartList = new ArrayList<List<Integer>>();
		if(companyList!=null && currentQuarter>=1&&currentQuarter<=competition.getQuarter()){
			for(int j=1;j<=currentQuarter;j++){
				List<Integer> fixedCapacityList = new ArrayList<Integer>();
				for(int i=0;i<companyList.size();i++){
					IdQuarter idQ=new IdQuarter();
					idQ.setId(companyList.get(i).getId());
					idQ.setQuarter(j);
					if(j==1){
						fixedCapacityList.add(0);
					}else if(j>=2){
						int fixedCapacity=0;
						CompanyCapacity companyCapacity=policyDecisionService.findCompanyCapacityByIdQuarter(idQ);
						if(companyCapacity!=null){
							fixedCapacity=companyCapacity.getCapacityNow();
						}
						fixedCapacityList.add(fixedCapacity);
					}
				}
				resultChartList.add(fixedCapacityList);
			}
		}
		
		model.addAttribute("resultChartList", resultChartList);
		model.addAttribute("quarter", currentQuarter);
		model.addAttribute("chartName", "各个公司固定产能表");
		return "jsp/competitionResult/everyIndicatorChart";	
	}
	
	//运行产能表
	@RequestMapping("/competitionResult/companyOperationCapacityChart.do")
	public String companyOperationCapacityChart(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		
		List<List<Integer>> resultChartList = new ArrayList<List<Integer>>();
		if(companyList!=null && currentQuarter>=1&&currentQuarter<=competition.getQuarter()){
			for(int j=1;j<=currentQuarter;j++){
				List<Integer> operationCapacityList = new ArrayList<Integer>();
				for(int i=0;i<companyList.size();i++){
					IdQuarter idQ=new IdQuarter();
					idQ.setId(companyList.get(i).getId());
					idQ.setQuarter(currentQuarter);
					if(j==1){
						operationCapacityList.add(0);
					}else if(j>=2){
						int operationCapacity=0;
						OperationCapacity oc=policyDecisionService.findOpeartionCapacityByCompanyIdQuarter(companyList.get(i).getId(), j);
						if(oc!=null){
							operationCapacity=oc.getOperateCapacity();
						}
						operationCapacityList.add(operationCapacity);
					}
				}
				resultChartList.add(operationCapacityList);
			}
		}
		
		model.addAttribute("resultChartList", resultChartList);
		model.addAttribute("quarter", currentQuarter);
		model.addAttribute("chartName", "各个公司运行产能表");
		return "jsp/competitionResult/everyIndicatorChart";	
	}
	//产能利用率
	@RequestMapping("/competitionResult/companyCapacityUtilizationChart.do")
	public String companyCapacityUtilizationChart(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		
		List<List<Integer>> resultChartList = new ArrayList<List<Integer>>();
		if(companyList!=null && currentQuarter>=1&&currentQuarter<=competition.getQuarter()){
			for(int j=1;j<=currentQuarter;j++){
				List<Integer> capacityUtilizationList = new ArrayList<Integer>();
				for(int i=0;i<companyList.size();i++){
					if(j==1){
						capacityUtilizationList.add(0);
					}else if(j>=2){
						int capacityUtilization=0;
						OperationCapacity oc=policyDecisionService.findOpeartionCapacityByCompanyIdQuarter(companyList.get(i).getId(), j);
						if(oc!=null&&oc.getOperateCapacity()!=0){
							CompanyMarketShare companyMS=competitionResultService.findCompanyMarketShare(companyList.get(i).getId(), j);
							if(companyMS!=null){
								int productSaleNum=companyMS.getBusinessNeed()+companyMS.getPerfectNeed()+companyMS.getPracticalNeed();
								capacityUtilization=productSaleNum*100/oc.getOperateCapacity();
							}
						}
						
						capacityUtilizationList.add(capacityUtilization);
					}
				}
				resultChartList.add(capacityUtilizationList);
			}
		}
		
		model.addAttribute("resultChartList", resultChartList);
		model.addAttribute("quarter", currentQuarter);
		model.addAttribute("chartName", "各个公司产能利用率表");
		return "jsp/competitionResult/everyIndicatorChart";	
	}
	
	//库存持有成本
	@RequestMapping("/competitionResult/companyStockCostChart.do")
	public String companyStockCostChart(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		
		List<List<Integer>> resultChartList = new ArrayList<List<Integer>>();
		if(companyList!=null && currentQuarter>=1&&currentQuarter<=competition.getQuarter()){
			for(int j=1;j<=currentQuarter;j++){
				List<Integer> stockCostList = new ArrayList<Integer>();
				for(int i=0;i<companyList.size();i++){
					IdQuarter idQ=new IdQuarter();
					idQ.setId(companyList.get(i).getId());
					idQ.setQuarter(currentQuarter);
					if(j==1){
						stockCostList.add(0);
					}else if(j>=2){
						int stockCost=0;
						List<ProductMarketShare> productMSList=competitionResultService.findProductMSByCompanyIdQuarter(companyList.get(i).getId(), j);
						if(productMSList!=null&&productMSList.size()>0){
							for(int m=0;m<productMSList.size();m++){
								CompanyProduct product=companyService.selectProductByProductId(productMSList.get(m).getProductId());
								stockCost+=productMSList.get(m).getStock()*product.getCost();
							}
						}
						stockCostList.add(stockCost);
					}
				}
				resultChartList.add(stockCostList);
			}
		}
		
		model.addAttribute("resultChartList", resultChartList);
		model.addAttribute("quarter", currentQuarter);
		model.addAttribute("chartName", "各个公司库存持有成本表");
		return "jsp/competitionResult/everyIndicatorChart";	
	}
	
	//库存量
	@RequestMapping("/competitionResult/companyStockNumChart.do")
	public String companyStockNumChart(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		
		List<List<Integer>> resultChartList = new ArrayList<List<Integer>>();
		if(companyList!=null && currentQuarter>=1&&currentQuarter<=competition.getQuarter()){
			for(int j=1;j<=currentQuarter;j++){
				List<Integer> stockNumList = new ArrayList<Integer>();
				for(int i=0;i<companyList.size();i++){
					IdQuarter idQ=new IdQuarter();
					idQ.setId(companyList.get(i).getId());
					idQ.setQuarter(currentQuarter);
					if(j==1){
						stockNumList.add(0);
					}else if(j>=2){
						int stockNum=0;
						List<ProductMarketShare> productMSList=competitionResultService.findProductMSByCompanyIdQuarter(companyList.get(i).getId(), j);
						if(productMSList!=null&&productMSList.size()>0){
							for(int m=0;m<productMSList.size();m++){
								stockNum+=productMSList.get(m).getStock();
							}
						}
						stockNumList.add(stockNum);
					}
				}
				resultChartList.add(stockNumList);
			}
		}
		
		model.addAttribute("resultChartList", resultChartList);
		model.addAttribute("quarter", currentQuarter);
		model.addAttribute("chartName", "各个公司库存持有成本表");
		return "jsp/competitionResult/everyIndicatorChart";	
	}
	//脱销量
	@RequestMapping("/competitionResult/companyStockounNumChart.do")
	public String companyStockounNumChart(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		
		List<List<Integer>> resultChartList = new ArrayList<List<Integer>>();
		if(companyList!=null && currentQuarter>=1&&currentQuarter<=competition.getQuarter()){
			for(int j=1;j<=currentQuarter;j++){
				List<Integer> stockounNumList = new ArrayList<Integer>();
				for(int i=0;i<companyList.size();i++){
					IdQuarter idQ=new IdQuarter();
					idQ.setId(companyList.get(i).getId());
					idQ.setQuarter(currentQuarter);
					if(j==1){
						stockounNumList.add(0);
					}else if(j>=2){
						int stockounNum=0;
						List<ProductMarketShare> productMSList=competitionResultService.findProductMSByCompanyIdQuarter(companyList.get(i).getId(), j);
						if(productMSList!=null&&productMSList.size()>0){
							for(int m=0;m<productMSList.size();m++){
								stockounNum+=productMSList.get(m).getStockoun();
							}
						}
						stockounNumList.add(stockounNum);
					}
				}
				resultChartList.add(stockounNumList);
			}
		}
		
		model.addAttribute("resultChartList", resultChartList);
		model.addAttribute("quarter", currentQuarter);
		model.addAttribute("chartName", "各个公司库存持有成本表");
		return "jsp/competitionResult/everyIndicatorChart";	
	}	
	
	
	//======================================
	//各个公司平衡计分卡内容
	//营业总收入表
	@RequestMapping("/competitionResult/grossRevenueChart.do")
	public String grossRevenueChart(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		
		List<List<Double>> resultChartList = new ArrayList<List<Double>>();
		if(companyList!=null && currentQuarter>=1&&currentQuarter<=competition.getQuarter()){
			for(int j=1;j<=currentQuarter;j++){
				List<Double> grossRevenueList = new ArrayList<Double>();
				for(int i=0;i<companyList.size();i++){
					if(j==1){
						grossRevenueList.add(0.00);
					}else if(j>=2){
						double grossRevenue=0;
						BalanceScore balanceScore = competitionResultService.findBalanceScoreByCompanyIdQuarter(companyList.get(i).getId(), j);
						if(balanceScore!=null){
							grossRevenue=balanceScore.getGrossRevenue();
						}
						grossRevenueList.add(grossRevenue);
					}
				}
				resultChartList.add(grossRevenueList);
			}
		}
		
		model.addAttribute("resultChartList", resultChartList);
		model.addAttribute("quarter", currentQuarter);
		model.addAttribute("chartName", "各个公司营业总收入表");
		return "jsp/competitionResult/everyIndicatorChart";	
	}
	//营业总成本
	@RequestMapping("/competitionResult/grossCostChart.do")
	public String grossCostChart(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		
		List<List<Double>> resultChartList = new ArrayList<List<Double>>();
		if(companyList!=null && currentQuarter>=1&&currentQuarter<=competition.getQuarter()){
			for(int j=1;j<=currentQuarter;j++){
				List<Double> grossCostList = new ArrayList<Double>();
				for(int i=0;i<companyList.size();i++){
					if(j==1){
						grossCostList.add(0.00);
					}else if(j>=2){
						double grossCost=0;
						BalanceScore balanceScore = competitionResultService.findBalanceScoreByCompanyIdQuarter(companyList.get(i).getId(), j);
						if(balanceScore!=null){
							grossCost=balanceScore.getGrossCost();
						}
						grossCostList.add(grossCost);
					}
				}
				resultChartList.add(grossCostList);
			}
		}
		
		model.addAttribute("resultChartList", resultChartList);
		model.addAttribute("quarter", currentQuarter);
		model.addAttribute("chartName", "各个公司营业总成本表");
		return "jsp/competitionResult/everyIndicatorChart";	
	}
	//营业总利润
	@RequestMapping("/competitionResult/operatingProfitChart.do")
	public String operatingProfitChart(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		
		List<List<Double>> resultChartList = new ArrayList<List<Double>>();
		if(companyList!=null && currentQuarter>=1&&currentQuarter<=competition.getQuarter()){
			for(int j=1;j<=currentQuarter;j++){
				List<Double> operatingProfitList = new ArrayList<Double>();
				for(int i=0;i<companyList.size();i++){
					if(j==1){
						operatingProfitList.add(0.00);
					}else if(j>=2){
						double operatingProfit=0;
						BalanceScore balanceScore = competitionResultService.findBalanceScoreByCompanyIdQuarter(companyList.get(i).getId(), j);
						if(balanceScore!=null){
							operatingProfit=balanceScore.getOperatingProfit();
						}
						operatingProfitList.add(operatingProfit);
					}
				}
				resultChartList.add(operatingProfitList);
			}
		}
		
		model.addAttribute("resultChartList", resultChartList);
		model.addAttribute("quarter", currentQuarter);
		model.addAttribute("chartName", "各个公司营业总利润表");
		return "jsp/competitionResult/everyIndicatorChart";	
	}	
	
	
	//现金等价物余额表
	@RequestMapping("/competitionResult/cashEquivalentChart.do")
	public String cashEquivalentChart(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		
		List<List<Double>> resultChartList = new ArrayList<List<Double>>();
		if(companyList!=null && currentQuarter>=1&&currentQuarter<=competition.getQuarter()){
			for(int j=1;j<=currentQuarter;j++){
				List<Double> cashEquivalentList = new ArrayList<Double>();
				for(int i=0;i<companyList.size();i++){
					if(j==1){
						cashEquivalentList.add(0.00);
					}else if(j>=2){
						double cashEquivalent=0;
						BalanceScore balanceScore = competitionResultService.findBalanceScoreByCompanyIdQuarter(companyList.get(i).getId(), j);
						if(balanceScore!=null){
							cashEquivalent=balanceScore.getCashEquivalent();
						}
						cashEquivalentList.add(cashEquivalent);
					}
				}
				resultChartList.add(cashEquivalentList);
			}
		}
		
		model.addAttribute("resultChartList", resultChartList);
		model.addAttribute("quarter", currentQuarter);
		model.addAttribute("chartName", "各个公司现金等价物余额表");
		return "jsp/competitionResult/everyIndicatorChart";	
	}
	//单位营销收益表
	@RequestMapping("/competitionResult/unitMarketingRevenueChart.do")
	public String unitMarketingRevenueChart(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		
		List<List<Double>> resultChartList = new ArrayList<List<Double>>();
		if(companyList!=null && currentQuarter>=1&&currentQuarter<=competition.getQuarter()){
			for(int j=1;j<=currentQuarter;j++){
				List<Double> unitMarketingRevenueList = new ArrayList<Double>();
				for(int i=0;i<companyList.size();i++){
					if(j==1){
						unitMarketingRevenueList.add(0.00);
					}else if(j>=2){
						double unitMarketingRevenue=0;
						BalanceScore balanceScore = competitionResultService.findBalanceScoreByCompanyIdQuarter(companyList.get(i).getId(), j);
						if(balanceScore!=null){
							unitMarketingRevenue=balanceScore.getUnitMarketingRevenue();
						}
						unitMarketingRevenueList.add(unitMarketingRevenue);
					}
				}
				resultChartList.add(unitMarketingRevenueList);
			}
		}
		
		model.addAttribute("resultChartList", resultChartList);
		model.addAttribute("quarter", currentQuarter);
		model.addAttribute("chartName", "各个公司单位营销收益表");
		return "jsp/competitionResult/everyIndicatorChart";	
	}
	//销售人员薪酬表
	@RequestMapping("/competitionResult/salesRemunerationChart.do")
	public String salesRemunerationChart(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		
		List<List<Double>> resultChartList = new ArrayList<List<Double>>();
		if(companyList!=null && currentQuarter>=1&&currentQuarter<=competition.getQuarter()){
			for(int j=1;j<=currentQuarter;j++){
				List<Double> salesRemunerationList = new ArrayList<Double>();
				for(int i=0;i<companyList.size();i++){
					if(j==1){
						salesRemunerationList.add(0.00);
					}else if(j>=2){
						double salesRemuneration=0;
						BalanceScore balanceScore = competitionResultService.findBalanceScoreByCompanyIdQuarter(companyList.get(i).getId(), j);
						if(balanceScore!=null){
							salesRemuneration=balanceScore.getSalesStaffRemuneration();
						}
						salesRemunerationList.add(salesRemuneration);
					}
				}
				resultChartList.add(salesRemunerationList);
			}
		}
		
		model.addAttribute("resultChartList", resultChartList);
		model.addAttribute("quarter", currentQuarter);
		model.addAttribute("chartName", "各个公司销售人员薪酬表");
		return "jsp/competitionResult/everyIndicatorChart";	
	}
	
	//学习时间表
	@RequestMapping("/competitionResult/trainingTimeChart.do")
	public String trainingTimeChart(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		
		List<List<Double>> resultChartList = new ArrayList<List<Double>>();
		if(companyList!=null && currentQuarter>=1&&currentQuarter<=competition.getQuarter()){
			for(int j=1;j<=currentQuarter;j++){
				List<Double> trainingTimeList = new ArrayList<Double>();
				for(int i=0;i<companyList.size();i++){
					if(j==1){
						trainingTimeList.add(0.00);
					}else if(j>=2){
						double trainingTime=0;
						BalanceScore balanceScore = competitionResultService.findBalanceScoreByCompanyIdQuarter(companyList.get(i).getId(), j);
						if(balanceScore!=null){
							trainingTime=balanceScore.getTrainingTime();
						}
						trainingTimeList.add(trainingTime);
					}
				}
				resultChartList.add(trainingTimeList);
			}
		}
		
		model.addAttribute("resultChartList", resultChartList);
		model.addAttribute("quarter", currentQuarter);
		model.addAttribute("chartName", "各个公司学习时间表");
		return "jsp/competitionResult/everyIndicatorChart";	
	}
	//资产管理表
	@RequestMapping("/competitionResult/assetManagementChart.do")
	public String assetManagementChart(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		
		List<List<Double>> resultChartList = new ArrayList<List<Double>>();
		if(companyList!=null && currentQuarter>=1&&currentQuarter<=competition.getQuarter()){
			for(int j=1;j<=currentQuarter;j++){
				List<Double> assetManagementList = new ArrayList<Double>();
				for(int i=0;i<companyList.size();i++){
					if(j==1){
						assetManagementList.add(0.00);
					}else if(j>=2){
						double assetManagement=0;
						BalanceScore balanceScore = competitionResultService.findBalanceScoreByCompanyIdQuarter(companyList.get(i).getId(), j);
						if(balanceScore!=null){
							assetManagement=balanceScore.getAssetManagement();
						}
						assetManagementList.add(assetManagement);
					}
				}
				resultChartList.add(assetManagementList);
			}
		}
		
		model.addAttribute("resultChartList", resultChartList);
		model.addAttribute("quarter", currentQuarter);
		model.addAttribute("chartName", "各个公司资产管理表");
		return "jsp/competitionResult/everyIndicatorChart";	
	}
	//生产效率表
	@RequestMapping("/competitionResult/productionEfficiencyChart.do")
	public String productionEfficiencyChart(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		
		List<List<Double>> resultChartList = new ArrayList<List<Double>>();
		if(companyList!=null && currentQuarter>=1&&currentQuarter<=competition.getQuarter()){
			for(int j=1;j<=currentQuarter;j++){
				List<Double> productionEfficiencyList = new ArrayList<Double>();
				for(int i=0;i<companyList.size();i++){
					if(j==1){
						productionEfficiencyList.add(0.00);
					}else if(j>=2){
						double productionEfficiency=0;
						BalanceScore balanceScore = competitionResultService.findBalanceScoreByCompanyIdQuarter(companyList.get(i).getId(), j);
						if(balanceScore!=null){
							productionEfficiency=balanceScore.getProductionEfficiency();
						}
						productionEfficiencyList.add(productionEfficiency);
					}
				}
				resultChartList.add(productionEfficiencyList);
			}
		}
		
		model.addAttribute("resultChartList", resultChartList);
		model.addAttribute("quarter", currentQuarter);
		model.addAttribute("chartName", "各个公司生产效率表");
		return "jsp/competitionResult/everyIndicatorChart";	
	}
	//===================================
	//跳转到发布结果页面
	@RequestMapping("/competitionResult/goReleaseResult.do")
	public String goReleaseResult(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		
		//设置是否所有公司都提交的标志字段
		IdQuarter idQuarter=new IdQuarter();
		idQuarter.setId(competitionId);
		idQuarter.setQuarter(currentQuarter);
		String isAllCompanySubmit="";
		List<CompanyQuarterTime> companyQT=new ArrayList<>();
		companyQT=competitionResultService.findNoSubmitCompanyByIdQuarter(idQuarter);
		if(companyQT!=null&&companyQT.size()>0){
			isAllCompanySubmit="no";
		}else{
			isAllCompanySubmit="yes";//全部公司已经提交
		}
		
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		model.addAttribute("quarter", currentQuarter);
		model.addAttribute("isAllCompanySubmit", isAllCompanySubmit);
		
		return "jsp/competitionResult/releaseResult";	
	}
	
	//发布竞赛结果
	@Transactional(propagation = Propagation.REQUIRED)
	@RequestMapping("/competitionResult/releaseResult.do")
	public String releaseResult(HttpServletRequest request,Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		IdQuarter idQuarter=new IdQuarter();
		idQuarter.setId(competitionId);
		idQuarter.setQuarter(currentQuarter);
		String message="";
		List<CompanyQuarterTime> companyQT=new ArrayList<>();
		try{
			//计算网络市场总需求量,实体的需求量每个市场单独算
			int netPracticalDemandSum=0;
			int netPerfectDemandSum=0;
			int netBusinessDemandSum=0;
			List<MarketInfo2> marketInfo2List=competitionResultService.findMarketInfoByCompetitionId(competitionId);
//			List<MarketInfo> marketInfoList=competitionResultService.findAllMarketInfo();
			if(marketInfo2List!=null&&marketInfo2List.size()>0){
				for(int n=0;n<marketInfo2List.size();n++){
					netPracticalDemandSum+=marketInfo2List.get(n).getWebPractical();
					netPerfectDemandSum+=marketInfo2List.get(n).getWebPerfect();
					netBusinessDemandSum+=marketInfo2List.get(n).getWebBusiness();
				}
			}
			if(competition.getQuarter()!=0){
				netPracticalDemandSum=netPracticalDemandSum/competition.getQuarter();
				netPerfectDemandSum=netPerfectDemandSum/competition.getQuarter();
				netBusinessDemandSum=netBusinessDemandSum/competition.getQuarter();
			}
			
			//这里先进行判断每个公司是否都已经提交，如果已经提交则执行下面操作
			if(currentQuarter<=competition.getQuarter()){
				companyQT=competitionResultService.findNoSubmitCompanyByIdQuarter(idQuarter);
				if(companyQT!=null&&companyQT.size()>0){
					message="当前季度有团队没有提交！请通知团队进行提交！";
				}else{//如果全部公司都提交，则进行一下操作
					
					//如果是第一季度，只算现有的直接财务数据
					if(currentQuarter==1){
						//第一季度只插入财务等数据
						for(int c=0;c<companyList.size();c++){
							int company_id=companyList.get(c).getId();
							int quarter=currentQuarter;
							
							companyService.calCashFlowResult(company_id, quarter);
							companyService.calIncomeResult(company_id, quarter);
							companyService.calBalanceSheetResult(company_id, quarter);
							
							companyService.insertGuben(company_id, quarter+1);
						}
						
					}else if(currentQuarter>=2&&currentQuarter<=competition.getQuarter()){
						/*1.统计每个公司做出的关键性投资*/
						for(int n=0;n<companyList.size();n++){
							IdQuarter idQ=new IdQuarter();
							idQ.setId(companyList.get(n).getId());
							idQ.setQuarter(currentQuarter);
							int productNum=0;
							int productAvgPrice=0;
							int adNum=0;
							int adCost=0;
							int phyMarketNum=0;
							int phyMarketCost=0;
							int netMarketNum=0;
							int netMarketCost=0;
							int phySalesNum=0;
							int phySalesCost=0;
							int netSalesNum=0;
							int netSalesCost=0;
							double workerEffi=0;
							double salesEffi=0;
							
							//品牌数量
							List<CompanyProduct> prods=policyDecisionService.findProductsByCompanyIdQuarter(companyList.get(n).getId(),currentQuarter);
							productNum=prods.size();
							//产品平均价格
							productAvgPrice=policyDecisionService.findAvgPriceProduct(idQ);
							//广告数量
							List<CompanyAdvertise> ads=policyDecisionService.findAdvertiseByCompanyIdQuarter(companyList.get(n).getId(),currentQuarter);
							adNum=ads.size();
							//广告的总投入，这里计算的是媒体的总投入资金
							List<CompanyMedia> mediaList=new ArrayList<>();
							mediaList=policyDecisionService.findMediabyCompanyIdQuarter(idQ);
							if(mediaList!=null&&mediaList.size()>0){
								for(int m=0;m<mediaList.size();m++){
									MediaInfo mediaInfo=policyDecisionService.findMediaInfoById(mediaList.get(m).getMediaId());
									adCost+=mediaList.get(m).getNum()*mediaInfo.getCost();
								}
							}
							//开放实体市场数量
							CompanyMarket companyMarket=new CompanyMarket();
							companyMarket=competitionResultService.findCompanyMarket(companyList.get(n).getId(), currentQuarter, 1);
							if(companyMarket!=null){
								String phyMarketId=companyMarket.getMarketId();
								if(phyMarketId!=null){
									String[] phyMarketIdArr=phyMarketId.split(",");
									phyMarketNum=phyMarketIdArr.length;
									
									//实体市场的总投入
									for(int m=0;m<phyMarketIdArr.length;m++){
										MarketInfo2 marketInfo=competitionResultService.findMarketInfoById(Integer.parseInt(phyMarketIdArr[m]));
										phyMarketCost+=marketInfo.getOpen();//这里3季度以后要改成租赁
									}
								}
								
							}
							
							//开放网络市场的数量
							CompanyMarket netCompanyMarket=new CompanyMarket();
							netCompanyMarket=competitionResultService.findCompanyMarket(companyList.get(n).getId(), currentQuarter, 0);
							if(netCompanyMarket!=null){
								String netMarketId=netCompanyMarket.getMarketId();
								if(netMarketId!=null){
									String[] netMarketIdArr=netMarketId.split(",");
									//netMarketNum=netMarketIdArr.length;
									netMarketNum=1;
									//实体市场的总投入
									for(int m=0;m<netMarketIdArr.length;m++){
										MarketInfo2 marketInfo=competitionResultService.findMarketInfoById(Integer.parseInt(netMarketIdArr[m]));
										netMarketCost+=marketInfo.getOpen();//这里3季度以后要改成租赁
									}
								}
							}
							
							//实体销售人数
							List<HirePeople> hirePeopleList=new ArrayList<>();
							hirePeopleList=competitionResultService.findCompanyPhySalesNum(companyList.get(n).getId(), currentQuarter);
							if(hirePeopleList!=null&&hirePeopleList.size()>0){
								for(int m=0;m<hirePeopleList.size();m++){
									phySalesNum+=hirePeopleList.get(m).getSaleman()+hirePeopleList.get(m).getAfterSale();
								}
							}
							//网络销售人数
							List<HirePeopleOnline> hirePeopleOnlineList=new ArrayList<>();
							hirePeopleOnlineList=policyDecisionService.findHirePeopleOnlineByCompanyIdQuarter(companyList.get(n).getId(), currentQuarter);
							if(hirePeopleOnlineList!=null&&hirePeopleOnlineList.size()>0){
								for(int m=0;m<hirePeopleOnlineList.size();m++){
									netSalesNum+=hirePeopleOnlineList.get(m).getSaleman()+hirePeopleOnlineList.get(m).getAfterSale();
								}
							}
							
							
							//销售工人薪酬
							SalesSalary salesSalary=competitionResultService.findSalesSalaryByCompanyIdQuar(companyList.get(n).getId(), currentQuarter);
							WorkersSalary workersSalary=competitionResultService.findWorkerSalaryByCompanyIdQuar(companyList.get(n).getId(), currentQuarter);
							//销售人数*薪酬就是网络和实体的销售人员投资
							if(salesSalary!=null){
								phySalesCost=phySalesNum*salesSalary.getSalaryTotal();
								netSalesCost=netSalesNum*salesSalary.getSalaryTotal();
								//销售人员效率
								SalesSalary avgSSalary=competitionResultService.findAvgSalesSalaryByCompetIdQuarter(competitionId, currentQuarter);
								if(avgSSalary!=null){
									salesEffi=1-Math.pow(Math.E,-salesSalary.getSalaryTotal()/((double)avgSSalary.getSalaryTotal()));
									salesEffi = (double)(Math.round(salesEffi*100))/100;
								}
							}
							
							
							if(workersSalary!=null){
								//工厂工人效率
								WorkersSalary avgWSalary=competitionResultService.findAvgWorkersSalaryByCompetIdQuarter(competitionId, currentQuarter);
								if(avgWSalary!=null){
									workerEffi=1-Math.pow(Math.E,-workersSalary.getSalaryTotal()/((double)avgWSalary.getSalaryTotal()));
									workerEffi = (double)(Math.round(workerEffi*100))/100;
								}
							}
							
							
							CompanyInvestment companyInvestment=new CompanyInvestment();
							companyInvestment.setCompetitionId(competitionId);
							companyInvestment.setCompanyId(companyList.get(n).getId());
							companyInvestment.setQuarter(currentQuarter);
							companyInvestment.setProductNum(productNum);
							companyInvestment.setProductAvgPrice(productAvgPrice);
							companyInvestment.setAdNum(adNum);
							companyInvestment.setAdCost(adCost);
							companyInvestment.setPhyMarketNum(phyMarketNum);
							companyInvestment.setPhyMarketCost(phyMarketCost);
							companyInvestment.setNetMarketNum(netMarketNum);
							companyInvestment.setNetMarketCost(netMarketCost);
							companyInvestment.setPhySalesNum(phySalesNum);
							companyInvestment.setPhySalesCost(phySalesCost);
							companyInvestment.setNetSalesNum(netSalesNum);
							companyInvestment.setNetSalesCost(netSalesCost);
							companyInvestment.setWorkerEfficiency(workerEffi);
							companyInvestment.setSalesEfficiency(salesEffi);
							
							//插入公司投资
							competitionResultService.insertCompanyInvestment(companyInvestment);
							//第一季度没有显示,所以不用插入这些值
						}
						
						//2:先算各种效率，再算市场份额等数据，然后存入财务表中，然后再计算财务
						/* 下面都是按照产品在不同市场上的投资 */	
						List<CompanyProduct> productList=competitionResultService.findProductsByCompetIdAndQuarter(competitionId, currentQuarter);
						if(productList!=null&&productList.size()>0){
							for(int z=0;z<productList.size();z++){
								//如果设置的产品需求量为0，则该产品不进行生产和市场占有利率比较
								//int forecastDemand = policyDecisionService.findForecastDemandByProductIdQuarter(productList.get(z).getId(), currentQuarter);
								//if(forecastDemand!=0){//现在这里跟预测需求量无关，直接注释掉
									
								int marketId=0;
								int adWeight=0;
								int designWeight=0;
								int marketWeight=0;
								//int salemanNum=0;
								int phySalemanNum=0;
								int netSalemanNum=0;
								int salemanSalary=0;
								int price=0;
								ProductEfficiency productEffi=new ProductEfficiency();
								
								//广告带来的各种类型效益
								List<CompanyMedia> medias=competitionResultService.findMediaByProductIdAndQuarter(productList.get(z).getId(), currentQuarter);
								if(medias!=null&&medias.size()>0){
									if(productList.get(z).getType().equals("实用型")){
										for(int m=0;m<medias.size();m++){
											MediaInfo mediaInfo=competitionResultService.findMediaById(medias.get(m).getMediaId());
											adWeight+=medias.get(m).getNum()*mediaInfo.getPractical();
										}
									}else if(productList.get(z).getType().equals("极致型")){
										for(int m=0;m<medias.size();m++){
											MediaInfo mediaInfo=competitionResultService.findMediaById(medias.get(m).getMediaId());
											adWeight+=medias.get(m).getNum()*mediaInfo.getPerfect();
										}
									}else if(productList.get(z).getType().equals("商务型")){
										for(int m=0;m<medias.size();m++){
											MediaInfo mediaInfo=competitionResultService.findMediaById(medias.get(m).getMediaId());
											adWeight+=medias.get(m).getNum()*mediaInfo.getBusiness();
										}
									}
									
								}
								
								//产品设计带来的各种类型中的效益
								String productDetailId=productList.get(z).getDetail();
								if(productDetailId!=null&&productDetailId.length()>0){
									String[] detailIdArr=productDetailId.split(",");
									if(productList.get(z).getType().equals("实用型")){
										for(int y=0;y<detailIdArr.length;y++){
											ProductInfo productInfo=competitionResultService.findProductInfoById(Integer.parseInt(detailIdArr[y]));
											designWeight+=productInfo.getPractical();
										}
									}else if(productList.get(z).getType().equals("极致型")){
										for(int y=0;y<detailIdArr.length;y++){
											ProductInfo productInfo=competitionResultService.findProductInfoById(Integer.parseInt(detailIdArr[y]));
											designWeight+=productInfo.getPerfect();
										}
									}else if(productList.get(z).getType().equals("商务型")){
										for(int y=0;y<detailIdArr.length;y++){
											ProductInfo productInfo=competitionResultService.findProductInfoById(Integer.parseInt(detailIdArr[y]));
											designWeight+=productInfo.getBusiness();
										}
									}
									
								}
								
								//市场数量
								//marketWeight=phyMarketNum+netMarketNum;
								marketWeight=1;
								
								
								//销售工人的工资
								//SalesSalary avgSalary=competitionResultService.findAvgSalesSalaryByCompetIdQuarter(competitionId, currentQuarter);
								SalesSalary companySalary=competitionResultService.findSalesSalaryByCompanyIdQuar(productList.get(z).getCompanyId(), currentQuarter);
								if(companySalary!=null){
									salemanSalary=companySalary.getSalaryTotal();
								}
								
								//产品定价
								ProductPrice productPrice=competitionResultService.findProductPriceByIdQuarter(productList.get(z).getId(), currentQuarter);
								if(productPrice!=null){
									price=productPrice.getPrice();
								}
								
								productEffi.setCompetitionId(competitionId);
								productEffi.setCompanyId(productList.get(z).getCompanyId());
								productEffi.setProductId(productList.get(z).getId());
								productEffi.setProductType(productList.get(z).getType());
								productEffi.setQuarter(currentQuarter);
								productEffi.setAdWeight(adWeight);
								productEffi.setDesignWeight(designWeight);
								productEffi.setMarketWeight(marketWeight);
								productEffi.setSalemanSalary(salemanSalary);
								productEffi.setPrice(price);
								CompanyMarket phyMarket=competitionResultService.findCompanyMarket(productList.get(z).getCompanyId(), currentQuarter, 1);
								if(phyMarket!=null){
									String marketPhyId=phyMarket.getMarketId();
									if(marketPhyId!=null&& marketPhyId.length()>0){
										String[] marketPhyIdArr=marketPhyId.split(",");
										//每个市场插入一条记录
										for(int j=0;j<marketPhyIdArr.length;j++){
											marketId=Integer.parseInt(marketPhyIdArr[j]);
											productEffi.setMarketId(marketId);
											//每个市场的销售人数
											HirePeopleVo phyPeople=companyService.selectHirePeople(productList.get(z).getCompanyId(), marketId, currentQuarter);
											phySalemanNum=phyPeople.getHirePeople().getAfterSale()+phyPeople.getHirePeople().getSaleman();
											productEffi.setSalemanNum(phySalemanNum);
											
											competitionResultService.insertProductEfficiency(productEffi);
										}
									}
								}
								//插入网络人员
								CompanyMarket netMarket=competitionResultService.findCompanyMarket(productList.get(z).getCompanyId(), currentQuarter, 0);
								if(netMarket!=null&&netMarket.getMarketId()!=""){
									String marketNetId=netMarket.getMarketId();
									if(marketNetId!=null&& marketNetId.length()>0){
										productEffi.setMarketId(0);//设置网络市场的标志id为0
										HirePeopleOnline netPeople=companyService.selectHirePeopleOnline(productList.get(z).getCompanyId(), currentQuarter);
										netSalemanNum=netPeople.getAfterSale()+netPeople.getSaleman();
										productEffi.setSalemanNum(netSalemanNum);
										
										competitionResultService.insertProductEfficiency(productEffi);
										
									}
									
								}
									
								//}//需求量预测是否为0的if判断
								
							}//productList的for循环
						}//产品是否为空的if
						
						//2:统计产品的各种参数、市场份额存入数据库
						//按类型统计，总共三种类型，这里用枚举
						for (TypeEnum e : TypeEnum.values()) {  
						    //System.out.println(e.toString());
							//这里再加一层市场的for循环,用marketInfo2List的市场
							
							//List<MarketInfo> marketList=competitionResultService.findAllMarketInfo();
						    if(marketInfo2List!=null&&marketInfo2List.size()>0){
						    	for(int ml=0;ml<marketInfo2List.size();ml++){
						    		//本次竞赛，本季度，该类型，第ml个实体市场进行比较
						    		List<ProductEfficiency> prodEfficiencyList=competitionResultService.findProductEfficiency(competitionId, currentQuarter,e.toString(),marketInfo2List.get(ml).getId());
								    int adWeightSum=0;
								    int designWeightSum=0;
								    int marketWeightSum=0;
									int salemanWeightSum=0;
									int priceSum=0;
									
									if(prodEfficiencyList!=null&&prodEfficiencyList.size()>0){
										//计算总的效益和，为了用公式求平均值
										for(int i=0;i<prodEfficiencyList.size();i++){
											adWeightSum+=prodEfficiencyList.get(i).getAdWeight();
											designWeightSum+=prodEfficiencyList.get(i).getDesignWeight();
											marketWeightSum+=prodEfficiencyList.get(i).getMarketWeight();
											salemanWeightSum+=prodEfficiencyList.get(i).getSalemanNum()*prodEfficiencyList.get(i).getSalemanSalary();
											//netSalemanWeightSum+=prodEfficiencyList.get(i).getNetSalemanNum()*prodEfficiencyList.get(i).getSalemanSalary();
											priceSum+=prodEfficiencyList.get(i).getPrice();
										}
										
										//接下来计算每个产品的市场占有率
										for(int j=0;j<prodEfficiencyList.size();j++){
											//例如：double x=Math.pow(Math.E, 2);
											double adEffi=0;
											double designEffi=0;
											double marketEffi=0.63;
											double salemanEffi=0;
											//double netSalemanEffi=0;
											double priceEffi=0;
											
											//计算practical广告效用
											if(prodEfficiencyList.get(j).getAdWeight()!=0){
												adEffi=1-Math.pow(Math.E,-prodEfficiencyList.get(j).getAdWeight()/((double)adWeightSum/prodEfficiencyList.size()));
												adEffi = (double)(Math.round(adEffi*100))/100;								
											}else{
												adEffi=0;
											}
											//计算设计效用
											if(prodEfficiencyList.get(j).getDesignWeight()!=0){
												designEffi=1-Math.pow(Math.E,-prodEfficiencyList.get(j).getDesignWeight()/((double)designWeightSum/prodEfficiencyList.size()));
												designEffi = (double)(Math.round(designEffi*100))/100;
											}else{
												designEffi=0;
											}
											
											//计算市场数量带来的效率
											/*if(prodEfficiencyList.get(j).getMarketWeight()!=0){
												marketEffi=1-Math.pow(Math.E,-prodEfficiencyList.get(j).getMarketWeight()/((double)marketWeightSum/prodEfficiencyList.size()));
												marketEffi = (double)(Math.round(marketEffi*100))/100;
											}else{
												marketEffi=0;
											}*/
											
											
											//计算销售人员数量带来的效率
											if(prodEfficiencyList.get(j).getSalemanNum()!=0&&prodEfficiencyList.get(j).getSalemanSalary()!=0){
												salemanEffi=1-Math.pow(Math.E,-prodEfficiencyList.get(j).getSalemanNum()*prodEfficiencyList.get(j).getSalemanSalary()/((double)salemanWeightSum/prodEfficiencyList.size()));
												salemanEffi = (double)(Math.round(salemanEffi*100))/100;
											}else{
												salemanEffi=0;
											}
											
											/*if(prodEfficiencyList.get(j).getNetSalemanNum()!=0&&prodEfficiencyList.get(j).getSalemanSalary()!=0){
												netSalemanEffi=1-Math.pow(Math.E,-prodEfficiencyList.get(j).getNetSalemanNum()*prodEfficiencyList.get(j).getSalemanSalary()/((double)netSalemanWeightSum/prodEfficiencyList.size()));
												netSalemanEffi = (double)(Math.round(netSalemanEffi*100))/100;
											}else{
												netSalemanEffi=0;
											}*/
											
											
											//计算价格带来的效率,之前使用自身/平均，计算价格是用平均/自身，因为价格越高，得到的效率越低
											if(prodEfficiencyList.get(j).getPrice()!=0){
												priceEffi=1-Math.pow(Math.E,-((double)priceSum/prodEfficiencyList.size())/prodEfficiencyList.get(j).getPrice());
												priceEffi = (double)(Math.round(priceEffi*100))/100;
											}else{
												priceEffi=0;
											}
											//===========================================
											/*2.2：上面效率已经计算出来，现在可以求市场份额*/
											MarketShareWeight marketSW=competitionResultService.findMaketShareWeightByType(e.toString());
											double marketShare=0;
											//double netMarketShare=0;
											int need=0;
											int sale=0;
											//int onlineNeed=0;
											//int onlineSale=0;
											int stockoun=0;				//脱销
											int stockNum=0;				//剩余的产品
											//实体市场占有率
											marketShare=(double)(marketSW.getAdWeight()*adEffi+
													marketSW.getDesignWeight()*designEffi+
													marketSW.getMarketWeight()*marketEffi+
													marketSW.getSalemanWeight()*salemanEffi+
													marketSW.getPriceWeight()*priceEffi);
											//市场占有率总是在0.5-0.6左右，多少个产品都是这个值，所以，产品越多，超出总需求越多
											//所以为了减少同类型下该产品的市场份额，除以同类型产品数量，除以0.65
											//0.65就是那个公式的平均数
											marketShare=(double)marketShare/prodEfficiencyList.size()/0.65;
											
											//产品市场总需求量，demandSum是市场总需求量，这里要用开放市场的需求总量，不能用全球市场的需求量
											MarketInfo2 marketInfo=competitionResultService.findMarketInfoById(marketInfo2List.get(ml).getId());
											if(e.toString().equals("实用型")){
												need=(int)Math.round(marketShare*marketInfo.getPractical()/competition.getQuarter());
											}else if(e.toString().equals("极致型")){
												need=(int)Math.round(marketShare*marketInfo.getPerfect()/competition.getQuarter());
											}else if(e.toString().equals("商务型")){
												need=(int)Math.round(marketShare*marketInfo.getBusiness()/competition.getQuarter());
											}
											
											//网络市场占有率
											/*netMarketShare=(double)(marketSW.getAdWeight()*adEffi+
													marketSW.getDesignWeight()*designEffi+
													marketSW.getMarketWeight()*marketEffi+
													marketSW.getSalemanWeight()*netSalemanEffi+
													marketSW.getPriceWeight()*priceEffi);
											if(e.toString().equals("实用型")){
												onlineNeed=(int)(marketShare*marketInfo.getPractical());
											}else if(e.toString().equals("极致型")){
												onlineNeed=(int)(marketShare*marketInfo.getPerfect());
											}else if(e.toString().equals("商务型")){
												onlineNeed=(int)(marketShare*marketInfo.getBusiness());
											}*/
											
											/*
											//计算产品需求量比例，然后按比例进行生产
											List<CompanyProduct> prodList = policyDecisionService.findProductsByCompanyIdQuarter(prodEfficiencyList.get(j).getCompanyId(), currentQuarter);
											int forecastDemandSum=0;
											if(prodList!=null&&prodList.size()>0){
												for(int p=0;p<prodList.size();p++){
													forecastDemandSum+=policyDecisionService.findForecastDemandByProductIdQuarter(prodList.get(p).getId(), currentQuarter);
												}
											}
											
											//用运行产能*65*生产效率=生产量，有产品预测需求量，按比例分配到每个产品
											
											OperationCapacity operationCapa=policyDecisionService.findOpeartionCapacityByCompanyIdQuarter(prodEfficiencyList.get(j).getCompanyId(), currentQuarter);
											CompanyInvestment companyInvestment=competitionResultService.findCompanyInvestByCompanyIdQuarter(prodEfficiencyList.get(j).getCompanyId(), currentQuarter);
											//该产品预计需求量
											int forecastDemand = policyDecisionService.findForecastDemandByProductIdQuarter(prodEfficiencyList.get(j).getProductId(), currentQuarter);
											//除以总需求量，按比例分配运行产能
											int produceNum=0;		//生产数量
											int productNum=0;	//分配给该市场的数量
											double produceProportion=0;
											if(operationCapa!=null&&companyInvestment!=null){
												if(forecastDemandSum!=0){
													produceProportion=(double)forecastDemand/forecastDemandSum;
													produceNum=(int)(produceProportion*operationCapa.getOperateCapacity()*65*companyInvestment.getWorkerEfficiency());
													
													//改产品的产能按市场人数投放比例，分配到各个市场
													if(prodEfficiencyList.get(j).getSalemanNum()!=0){
														//该市场人数/全球总人数*总生产量，得到该市场的生产量,
														double numRate=0;
														numRate=(double)prodEfficiencyList.get(j).getSalemanNum()/(companyInvestment.getPhySalesNum()+companyInvestment.getNetSalesNum());
														productNum=(int)(produceNum*numRate);
													}
												}
											}
											
											//找公司库存设置，如果产能多了，就存放在库存中，少了则脱销
											int productInventory=policyDecisionService.findProductInventoryByProductIdQuarter(prodEfficiencyList.get(j).getProductId(), currentQuarter);
											if(need<=productNum){
												sale=need;						//需求量<产能，则需求多少卖多少
												stockNum=productNum-need;		//剩余部分存入库存,这部分还需要和公司产品设置的库存比较
												
												if(stockNum>productInventory){
													stockNum=productInventory;
												}
												ProductMarketShare productMS=competitionResultService.findProductMSByProductIdQuarter(prodEfficiencyList.get(j).getProductId(), currentQuarter);
												if(productMS!=null){
													stockNum+=productMS.getStock();
												}
												
											}else{
												sale=productNum;				//否则只能卖出产能这么多件
												stockoun=need-productNum;		//产生脱销
												ProductMarketShare productMS=competitionResultService.findProductMSByProductIdQuarter(prodEfficiencyList.get(j).getProductId(), currentQuarter);
												if(productMS!=null){
													stockoun+=productMS.getStockoun();
												}
											}*/
											
											ProductMarketShare productMS=new ProductMarketShare();
											productMS.setCompetitionId(competitionId);
											productMS.setCompanyId(prodEfficiencyList.get(j).getCompanyId());
											productMS.setProductId(prodEfficiencyList.get(j).getProductId());
											productMS.setQuarter(currentQuarter);
											productMS.setProductType(e.toString());
											productMS.setMarketShare(0);
											if(marketInfo2List.get(ml).getCity().equals("新加坡")){
												productMS.setSingaporeNeed(need);
//												productMS.setSingaporeSale(sale);
											}else if(marketInfo2List.get(ml).getCity().equals("香港")){
												productMS.setHongkongNeed(need);
//												productMS.setHongkongSale(sale);
											}else if(marketInfo2List.get(ml).getCity().equals("莫斯科")){
												productMS.setMoscowNeed(need);
//												productMS.setMoscowSale(sale);
											}else if(marketInfo2List.get(ml).getCity().equals("新德里")){
												productMS.setNewdelhiNeed(need);
//												productMS.setNewdelhiSale(sale);
											}
											
											/*productMS.setStockoun(stockoun);
											productMS.setStock(stockNum);*/
											competitionResultService.insertOrUpdateProductMarketShare(productMS);
											/*
											 * //到此，市场份额计算完成
											 */
											
										}//每个产品在该类型市场份额的for循环
										
										
									}else{
										//这个else没什么用，可以删掉
									}//if判断prodEfficiencyList结束
						    		
						    	}//计算市场的for循环
						    	
						    }//判断实体市场的if
						    
						    //=========================================
						    //2.3比较并插入网络市场的销售，如果前端界面重新调整网络市场，那么这部分可能要跟上面整合
						    //现在可能找不到Id为100的网络市场，以后改为只要找到不为空的网络市场，就表示开了网络市场
				    		//本次竞赛，本季度，该类型，第ml个实体市场进行比较
				    		List<ProductEfficiency> prodEfficiencyList=competitionResultService.findProductEfficiency(competitionId, currentQuarter,e.toString(),0);
						    
						    int adWeightSum=0;
						    int designWeightSum=0;
						    int marketWeightSum=0;
							int salemanWeightSum=0;
							int priceSum=0;
							
							if(prodEfficiencyList!=null&&prodEfficiencyList.size()>0){
								//计算总的效益和，为了用公式求平均值
								for(int i=0;i<prodEfficiencyList.size();i++){
									adWeightSum+=prodEfficiencyList.get(i).getAdWeight();
									designWeightSum+=prodEfficiencyList.get(i).getDesignWeight();
									marketWeightSum+=prodEfficiencyList.get(i).getMarketWeight();
									salemanWeightSum+=prodEfficiencyList.get(i).getSalemanNum()*prodEfficiencyList.get(i).getSalemanSalary();
									//netSalemanWeightSum+=prodEfficiencyList.get(i).getNetSalemanNum()*prodEfficiencyList.get(i).getSalemanSalary();
									priceSum+=prodEfficiencyList.get(i).getPrice();
								}
								
								//接下来计算每个产品的市场份额
								for(int j=0;j<prodEfficiencyList.size();j++){
									//例如：double x=Math.pow(Math.E, 2);
									double adEffi=0;
									double designEffi=0;
									double marketEffi=0.63;
									double salemanEffi=0;
									//double netSalemanEffi=0;
									double priceEffi=0;
									
									//计算practical广告效用
									if(prodEfficiencyList.get(j).getAdWeight()!=0){
										adEffi=1-Math.pow(Math.E,-prodEfficiencyList.get(j).getAdWeight()/((double)adWeightSum/prodEfficiencyList.size()));
										adEffi = (double)(Math.round(adEffi*100))/100;								
									}else{
										adEffi=0;
									}
									//计算设计效用
									if(prodEfficiencyList.get(j).getDesignWeight()!=0){
										designEffi=1-Math.pow(Math.E,-prodEfficiencyList.get(j).getDesignWeight()/((double)designWeightSum/prodEfficiencyList.size()));
										designEffi = (double)(Math.round(designEffi*100))/100;
									}else{
										designEffi=0;
									}
									
									//计算销售人员数量带来的效率
									if(prodEfficiencyList.get(j).getSalemanNum()!=0&&prodEfficiencyList.get(j).getSalemanSalary()!=0){
										salemanEffi=1-Math.pow(Math.E,-prodEfficiencyList.get(j).getSalemanNum()*prodEfficiencyList.get(j).getSalemanSalary()/((double)salemanWeightSum/prodEfficiencyList.size()));
										salemanEffi = (double)(Math.round(salemanEffi*100))/100;
									}else{
										salemanEffi=0;
									}
									
									//计算价格带来的效率,之前使用自身/平均，计算价格是用平均/自身，因为价格越高，得到的效率越低
									if(prodEfficiencyList.get(j).getPrice()!=0){
										priceEffi=1-Math.pow(Math.E,-((double)priceSum/prodEfficiencyList.size())/prodEfficiencyList.get(j).getPrice());
										priceEffi = (double)(Math.round(priceEffi*100))/100;
									}else{
										priceEffi=0;
									}
									//===========================================
									/*2.2：上面效率已经计算出来，现在可以求市场份额*/
									MarketShareWeight marketSW=competitionResultService.findMaketShareWeightByType(e.toString());
									double netMarketShare=0;
									int need=0;
									int sale=0;
									int stockoun=0;				//脱销
									int stockNum=0;				//剩余的产品
									
									//网络市场占有率
									netMarketShare=(double)(marketSW.getAdWeight()*adEffi+
											marketSW.getDesignWeight()*designEffi+
											marketSW.getMarketWeight()*marketEffi+
											marketSW.getSalemanWeight()*salemanEffi+
											marketSW.getPriceWeight()*priceEffi);
									//同样，多个产品的市场份额都是0.5-0.6之间
									netMarketShare=(double)netMarketShare/prodEfficiencyList.size()/0.65;
									
									
									if(e.toString().equals("实用型")){
										need=(int)Math.round(netMarketShare*netPracticalDemandSum);
									}else if(e.toString().equals("极致型")){
										need=(int)Math.round(netMarketShare*netPerfectDemandSum);
									}else if(e.toString().equals("商务型")){
										need=(int)Math.round(netMarketShare*netBusinessDemandSum);
									}
									
									/*//计算产品需求量比例，然后按比例进行生产
									List<CompanyProduct> prodList = policyDecisionService.findProductsByCompanyIdQuarter(prodEfficiencyList.get(j).getCompanyId(), currentQuarter);
									int forecastDemandSum=0;
									if(prodList!=null&&prodList.size()>0){
										for(int p=0;p<prodList.size();p++){
											forecastDemandSum+=policyDecisionService.findForecastDemandByProductIdQuarter(prodList.get(p).getId(), currentQuarter);
										}
									}
									
									//用运行产能*65*生产效率=生产量，有产品预测需求量，按比例分配到每个产品
									OperationCapacity operationCapa=policyDecisionService.findOpeartionCapacityByCompanyIdQuarter(prodEfficiencyList.get(j).getCompanyId(), currentQuarter);
									CompanyInvestment companyInvestment=competitionResultService.findCompanyInvestByCompanyIdQuarter(prodEfficiencyList.get(j).getCompanyId(), currentQuarter);
									//该产品预计需求量
									int forecastDemand = policyDecisionService.findForecastDemandByProductIdQuarter(prodEfficiencyList.get(j).getProductId(), currentQuarter);
									//除以总需求量，按比例分配运行产能
									int produceNum=0;		//生产数量
									int productNum=0;	//分配市场的数量
									double produceProportion=0;
									if(operationCapa!=null&&companyInvestment!=null){
										if(forecastDemandSum!=0){
											produceProportion=(double)forecastDemand/forecastDemandSum;
											produceNum=(int)(produceProportion*operationCapa.getOperateCapacity()*65*companyInvestment.getWorkerEfficiency());
											
											//改产品的产能按市场人数投放比例，分配到各个市场
											//该市场人数/全球总人数*总生产量，得到该市场的生产量
											if(prodEfficiencyList.get(j).getSalemanNum()!=0){
												double numRate=0;
												numRate=(double)prodEfficiencyList.get(j).getSalemanNum()/(companyInvestment.getPhySalesNum()+companyInvestment.getNetSalesNum());
												productNum=(int)(produceNum*numRate);
											}
										}
									}
									
									//找实体市场中的销售量，有库存或者脱销计入网络中
									ProductMarketShare productms=competitionResultService.findProductMSByProductIdQuarter(prodEfficiencyList.get(j).getProductId(), currentQuarter);
									if(productms!=null){
										if(productms.getStock()>productms.getStockoun()){
											productNum+=productms.getStock()-productms.getStockoun();
										}else{
											need+=productms.getStockoun()-productms.getStock();
										}
										
									}
									
									//找公司库存设置，如果产能多了，就存放在库存中，少了则脱销
									int productInventory=policyDecisionService.findProductInventoryByProductIdQuarter(prodEfficiencyList.get(j).getProductId(), currentQuarter);
									if(need<=productNum){
										sale=need;						//需求量<产能，则需求多少卖多少
										stockNum+=productNum-need;		//剩余部分存入库存,这部分还需要和公司产品设置的库存比较
										if(stockNum>productInventory){
											stockNum=productInventory;
										}
									}else{
										sale=productNum;				//否则只能卖出产能这么多件
										stockoun+=need-productNum;		//产生脱销
									}*/
									
									ProductMarketShare productMS=new ProductMarketShare();
									productMS.setCompetitionId(competitionId);
									productMS.setCompanyId(prodEfficiencyList.get(j).getCompanyId());
									productMS.setProductId(prodEfficiencyList.get(j).getProductId());
									productMS.setQuarter(currentQuarter);
									productMS.setProductType(e.toString());
									productMS.setMarketShare(0);
									productMS.setOnlineNeed(need);
//									productMS.setOnlineSale(sale);
									
//									productMS.setStockoun(stockoun);
//									productMS.setStock(stockNum);
									competitionResultService.insertOrUpdateProductMarketShare(productMS);
									
									/*
									 * //到此，市场份额计算完成
									 */
									
								}//每个产品在该类型市场份额的for循环
								
							}else{
								//这个else没什么用，可以删掉
							}//if判断prodEfficiencyList结束
								
						}//枚举类型结束，到此将三种类型的产品都存入数据库中
						
						
						//3:上面计算各个产品的市场需求量，现在计算各个产品的销售量
						List<ProductMarketShare> productMSList_need=competitionResultService.findProductMSByCompetitionIdQuarter(competitionId, currentQuarter);
						if(productMSList_need!=null&&productMSList_need.size()>0){
							for(int p=0;p<productMSList_need.size();p++){
								//找该产品所属公司所有产品需求量总和
								List<ProductMarketShare> everyCompanyProductMSList=competitionResultService.findProductMSByCompanyIdQuarter(productMSList_need.get(p).getCompanyId(), currentQuarter);
								int productNeedSum=0;
								if(everyCompanyProductMSList!=null&&everyCompanyProductMSList.size()>0){
									for(int ecpms=0;ecpms<everyCompanyProductMSList.size();ecpms++){
										productNeedSum+=everyCompanyProductMSList.get(ecpms).getHongkongNeed()+everyCompanyProductMSList.get(ecpms).getMoscowNeed()+
												everyCompanyProductMSList.get(ecpms).getNewdelhiNeed()+everyCompanyProductMSList.get(ecpms).getSingaporeNeed()+everyCompanyProductMSList.get(ecpms).getOnlineNeed();
									}
									
								}
								
								
								//定义销售量变量
								int singaporeSale=0;
								int hongkongSale=0;
								int moscowSale=0;
								int newdelhiSale=0;
								int onlineSale=0;
								
								int needNum=productMSList_need.get(p).getSingaporeNeed()+productMSList_need.get(p).getHongkongNeed()+
										productMSList_need.get(p).getMoscowNeed()+productMSList_need.get(p).getNewdelhiNeed()+productMSList_need.get(p).getOnlineNeed();
								//计算产品需求量比例，然后按比例进行生产
								/*List<CompanyProduct> prodList = policyDecisionService.findProductsByCompanyIdQuarter(productMSList_need.get(p).getCompanyId(), currentQuarter);
								int forecastDemandSum=0;
								if(prodList!=null&&prodList.size()>0){
									for(int a=0;a<prodList.size();a++){
										forecastDemandSum+=policyDecisionService.findForecastDemandByProductIdQuarter(prodList.get(a).getId(), currentQuarter);
									}
								}*/
								
								//用运行产能*65*生产效率=生产量，有产品预测需求量，按比例分配到每个产品，现在不用运行产能，而用固定产能
								//OperationCapacity operationCapa=policyDecisionService.findOpeartionCapacityByCompanyIdQuarter(productMSList_need.get(p).getCompanyId(), currentQuarter);
								CompanyCapacity companyCapacity = policyDecisionService.findCompanyCapacityByCompanyIdQuarter(productMSList_need.get(p).getCompanyId(), currentQuarter);
								CompanyInvestment companyInvestment=competitionResultService.findCompanyInvestByCompanyIdQuarter(productMSList_need.get(p).getCompanyId(), currentQuarter);
								//该产品预计需求量
								//int forecastDemand = policyDecisionService.findForecastDemandByProductIdQuarter(productMSList_need.get(p).getProductId(), currentQuarter);
								//除以总需求量，按比例分配运行产能
								int produceNum=0;		//生产数量
								int productNum=0;	//分配市场的数量
								
								int stockounNum=0;
								int stockNum=0;
								
								double produceProportion=0;
								if(companyCapacity!=null&&companyInvestment!=null){
									//if(forecastDemandSum!=0){
										//produceProportion=(double)forecastDemand/forecastDemandSum;
									
									//该产品的生产比例=该产品的需求量，除以公司所有产品的需求量。
									produceProportion=(double)needNum/productNeedSum;
									
									//生产量=产能*65*工人效率
									produceNum=(int)Math.round(produceProportion*companyCapacity.getCapacityNow()*65*companyInvestment.getWorkerEfficiency());
									
									//找该产品上季度的库存，和本季度生产量相加
									ProductMarketShare lastProductMS=competitionResultService.findProductMSByProductIdQuarter(productMSList_need.get(p).getProductId(), currentQuarter-1);
									if(lastProductMS!=null){
										productNum+=lastProductMS.getStock();
									}
									
									//比较生产数量和市场需求量总和，生产量大，则所有市场均能满足，且产生库存
									//生产量小，则按每个市场需求比例将生产量分配到每个市场，产生脱销
									if(needNum<=produceNum){
										stockNum=produceNum-needNum;//产生库存
										
										singaporeSale=productMSList_need.get(p).getSingaporeNeed();
										hongkongSale=productMSList_need.get(p).getHongkongNeed();
										moscowSale=productMSList_need.get(p).getMoscowNeed();
										newdelhiSale=productMSList_need.get(p).getNewdelhiNeed();
										onlineSale=productMSList_need.get(p).getOnlineNeed();
										
										//库存和公司设置的产品最大库存进行比较
										int productInventory=policyDecisionService.findProductInventoryByProductIdQuarter(productMSList_need.get(p).getProductId(), currentQuarter);
										if(stockNum>productInventory){
											stockNum=productInventory;
										}
										
									}else{
										//暂时不用下面这个算，因为误差的原因，四舍五入条件下总会出现1-2个产品的误差
										//stockounNum=needNum-produceNum;//产生脱销
										
										singaporeSale=Math.round(productMSList_need.get(p).getSingaporeNeed()*produceNum/needNum);
										hongkongSale=Math.round(productMSList_need.get(p).getHongkongNeed()*produceNum/needNum);
										moscowSale=Math.round(productMSList_need.get(p).getMoscowNeed()*produceNum/needNum);
										newdelhiSale=Math.round(productMSList_need.get(p).getNewdelhiNeed()*produceNum/needNum);
										onlineSale=Math.round(productMSList_need.get(p).getOnlineNeed()*produceNum/needNum);
										
										//用下面这个需求量-销售量=脱销量，不会产生误差
										stockounNum=needNum-singaporeSale-hongkongSale-moscowSale-newdelhiSale-onlineSale;
										
									}
										
									//}
								}
								
								ProductMarketShare productMarketShare=new ProductMarketShare();
								productMarketShare.setCompetitionId(competitionId);
								productMarketShare.setCompanyId(productMSList_need.get(p).getCompanyId());
								productMarketShare.setProductId(productMSList_need.get(p).getProductId());
								productMarketShare.setQuarter(currentQuarter);
								productMarketShare.setSingaporeSale(singaporeSale);
								productMarketShare.setHongkongSale(hongkongSale);
								productMarketShare.setMoscowSale(moscowSale);
								productMarketShare.setNewdelhiSale(newdelhiSale);
								productMarketShare.setOnlineSale(onlineSale);
								productMarketShare.setMarketShare(0);
								productMarketShare.setStockoun(stockounNum);
								productMarketShare.setStock(stockNum);
								productMarketShare.setProductType(productMSList_need.get(p).getProductType());
								competitionResultService.insertOrUpdateProductMarketShare(productMarketShare);
								
							}
						}
						
						
						//上面产品中的marketshare只是市场效用，下面计算的是市场份额
						//4.计算每个产品在该类型的市场份额
						List<ProductMarketShare> practicalList=competitionResultService.findProductMSByCompetIdQuarterType(competitionId, currentQuarter, "实用型");
						int practicalSumNum=0;
						if(practicalList!=null&&practicalList.size()>0){
							for(int n=0;n<practicalList.size();n++){
								practicalSumNum+=practicalList.get(n).getHongkongNeed()+practicalList.get(n).getMoscowNeed()+
										practicalList.get(n).getNewdelhiNeed()+practicalList.get(n).getSingaporeNeed()+practicalList.get(n).getOnlineNeed();
							}
						}
						
						List<ProductMarketShare> perfectList=competitionResultService.findProductMSByCompetIdQuarterType(competitionId, currentQuarter, "极致型");
						int perfectSumNum=0;
						if(perfectList!=null&&perfectList.size()>0){
							for(int n=0;n<perfectList.size();n++){
								perfectSumNum+=perfectList.get(n).getHongkongNeed()+perfectList.get(n).getMoscowNeed()+
										perfectList.get(n).getNewdelhiNeed()+perfectList.get(n).getSingaporeNeed()+perfectList.get(n).getOnlineNeed();
							}
						}
						
						List<ProductMarketShare> businessList=competitionResultService.findProductMSByCompetIdQuarterType(competitionId, currentQuarter, "商务型");
						int businessSumNum=0;
						if(businessList!=null&&businessList.size()>0){
							for(int n=0;n<businessList.size();n++){
								businessSumNum+=businessList.get(n).getHongkongNeed()+businessList.get(n).getMoscowNeed()+
										businessList.get(n).getNewdelhiNeed()+businessList.get(n).getSingaporeNeed()+businessList.get(n).getOnlineNeed();
							}
						}
						//int practicalSumNum=competitionResultService.findProductMSNeedNumByCompetIdQuarterType(competitionId, currentQuarter, "实用型");
						//int perfectSumNum=competitionResultService.findProductMSNeedNumByCompetIdQuarterType(competitionId, currentQuarter, "极致型");
						//int businessSumNum=competitionResultService.findProductMSNeedNumByCompetIdQuarterType(competitionId, currentQuarter, "商务型");
						
						List<ProductMarketShare> productMSList=competitionResultService.findProductMSByCompetitionIdQuarter(competitionId, currentQuarter);
						if(productMSList!=null&&productMSList.size()>0){
							for(int a=0;a<productMSList.size();a++){
								ProductMarketShare productMarketShare=new ProductMarketShare();
								//int needNum=productMSList.get(a).getNeed();
								int needNum=productMSList.get(a).getSingaporeNeed()+productMSList.get(a).getHongkongNeed()+
										productMSList.get(a).getMoscowNeed()+productMSList.get(a).getNewdelhiNeed()+productMSList.get(a).getOnlineNeed();
								double itsMS=0;
								if(productMSList.get(a).getProductType().equals("实用型")&&needNum!=0){
									itsMS=(double)needNum/practicalSumNum;
									itsMS=(double)(Math.round(itsMS*1000))/1000;
								}else if(productMSList.get(a).getProductType().equals("极致型")&&needNum!=0){
									itsMS=(double)needNum/perfectSumNum;
									itsMS=(double)(Math.round(itsMS*1000))/1000;
								}else if(productMSList.get(a).getProductType().equals("商务型")&&needNum!=0){
									itsMS=(double)needNum/businessSumNum;
									itsMS=(double)(Math.round(itsMS*1000))/1000;
								}
								productMarketShare.setProductId(productMSList.get(a).getProductId());
								productMarketShare.setQuarter(productMSList.get(a).getQuarter());
								productMarketShare.setMarketShare(itsMS);
//								int stockoun=productMSList.get(a).getStockoun()-productMSList.get(a).getStock();
//								int stock=0;
//								if(stockoun<=0){
//									stock=-stockoun;
//									stockoun=0;
//								}
//								productMarketShare.setStockoun(stockoun);
//								productMarketShare.setStock(stock);
								
								competitionResultService.insertOrUpdateProductMarketShare(productMarketShare);
//								competitionResultService.updateProductMSByProductIdQuarter(productMarketShare);
							}
						}
						
						/*5.将产品市场份额统计到公司市场份额中*/
						//每个公司一条记录，所以根据公司循环
						for(int n=0;n<companyList.size();n++){
							double practicalShare=0;
							int practicalNeed=0;
							int practicalSale=0;
							int practicalStockoun=0;
							int practicalStock=0;
							double perfectShare=0;
							int perfectNeed=0;
							int perfectSale=0;
							int perfectStockoun=0;
							int perfectStock=0;
							double businessShare=0;
							int businessNeed=0;
							int businessSale=0;
							int businessStockoun=0;
							int businessStock=0;
							
							//找各个公司中实用型产品占的份额
							List<ProductMarketShare> productMSPracticalList=competitionResultService.findProductMSByCompanyIdQuarterType(companyList.get(n).getId(), currentQuarter, "实用型");
							if(productMSPracticalList!=null&&productMSPracticalList.size()>0){
								for(int m=0;m<productMSPracticalList.size();m++){
									practicalShare+=productMSPracticalList.get(m).getMarketShare();
									practicalNeed+=productMSPracticalList.get(m).getHongkongNeed()+productMSPracticalList.get(m).getMoscowNeed()+
											productMSPracticalList.get(m).getNewdelhiNeed()+productMSPracticalList.get(m).getSingaporeNeed()+productMSPracticalList.get(m).getOnlineNeed();
									practicalSale+=productMSPracticalList.get(m).getHongkongSale()+productMSPracticalList.get(m).getMoscowSale()+
											productMSPracticalList.get(m).getNewdelhiSale()+productMSPracticalList.get(m).getSingaporeSale()+productMSPracticalList.get(m).getOnlineSale();
									practicalStockoun+=productMSPracticalList.get(m).getStockoun();
									practicalStock+=productMSPracticalList.get(m).getStock();
								}
							}
							List<ProductMarketShare> productMSPerfectList=competitionResultService.findProductMSByCompanyIdQuarterType(companyList.get(n).getId(), currentQuarter, "极致型");
							if(productMSPerfectList!=null&&productMSPerfectList.size()>0){
								for(int m=0;m<productMSPerfectList.size();m++){
									perfectShare+=productMSPerfectList.get(m).getMarketShare();
									perfectNeed+=productMSPerfectList.get(m).getHongkongNeed()+productMSPerfectList.get(m).getMoscowNeed()+
											productMSPerfectList.get(m).getNewdelhiNeed()+productMSPerfectList.get(m).getSingaporeNeed()+productMSPerfectList.get(m).getOnlineNeed();
									perfectSale+=productMSPerfectList.get(m).getHongkongSale()+productMSPerfectList.get(m).getMoscowSale()+
											productMSPerfectList.get(m).getNewdelhiSale()+productMSPerfectList.get(m).getSingaporeSale()+productMSPerfectList.get(m).getOnlineSale();
									perfectStockoun+=productMSPerfectList.get(m).getStockoun();
									perfectStock+=productMSPerfectList.get(m).getStock();
								}
							}
							List<ProductMarketShare> productMSBusinessList=competitionResultService.findProductMSByCompanyIdQuarterType(companyList.get(n).getId(), currentQuarter, "商务型");
							if(productMSBusinessList!=null&&productMSBusinessList.size()>0){
								for(int m=0;m<productMSBusinessList.size();m++){
									businessShare+=productMSBusinessList.get(m).getMarketShare();
									businessNeed+=productMSBusinessList.get(m).getHongkongNeed()+productMSBusinessList.get(m).getMoscowNeed()+
											productMSBusinessList.get(m).getNewdelhiNeed()+productMSBusinessList.get(m).getSingaporeNeed()+productMSBusinessList.get(m).getOnlineNeed();
									businessSale+=productMSBusinessList.get(m).getHongkongSale()+productMSBusinessList.get(m).getMoscowSale()+
											productMSBusinessList.get(m).getNewdelhiSale()+productMSBusinessList.get(m).getSingaporeSale()+productMSBusinessList.get(m).getOnlineSale();
									businessStockoun+=productMSBusinessList.get(m).getStockoun();
									businessStock+=productMSBusinessList.get(m).getStock();
								}
							}
							CompanyMarketShare companyMS=new CompanyMarketShare();
							companyMS.setPracticalShare(practicalShare);
							companyMS.setPracticalNeed(practicalNeed);
							companyMS.setPracticalSale(practicalSale);
							companyMS.setPracticalStockoun(practicalStockoun);
							companyMS.setPracticalStock(practicalStock);
							companyMS.setPerfectShare(perfectShare);
							companyMS.setPerfectNeed(perfectNeed);
							companyMS.setPerfectSale(perfectSale);
							companyMS.setPerfectStockOun(perfectStockoun);
							companyMS.setPerfectStock(perfectStock);
							companyMS.setBusinessShare(businessShare);
							companyMS.setBusinessNeed(businessNeed);
							companyMS.setBusinessSale(businessSale);
							companyMS.setBusinessStockoun(businessStockoun);
							companyMS.setBusinessStock(businessStock);
							companyMS.setCompanyId(companyList.get(n).getId());
							companyMS.setQuarter(currentQuarter);
							competitionResultService.insertCompanyMarketShare(companyMS);
							
							
						}//到这里公司市场份额插入结束
						
						//6.插入财务等数据
						for(int c=0;c<companyList.size();c++){
							int company_id=companyList.get(c).getId();
							int quarter=currentQuarter;
							
							companyService.calCashFlowResult(company_id, quarter);
							companyService.calIncomeResult(company_id, quarter);
							companyService.calBalanceSheetResult(company_id, quarter);
							companyService.insertGuben(company_id, quarter+1);
							companyService.updateAllCompanyDecision(company_id, quarter);
							
//							张丽阳
							if(companyService.selectCompanyMarket(company_id, 1, quarter)!=null){
								searchService.resetmarketIsOpened(company_id, companyService.selectCompanyMarket(company_id, 1, quarter).getMarketId());
							}else{
								searchService.resetmarketIsOpened(company_id,"");
							}
						}
						
						
						//7.插入平衡计分卡等数据
						for(int b=0;b<companyList.size();b++){
							balanceScoreService.insertTotleBalanceScore(companyList.get(b).getId(), currentQuarter);
						}
						for(int b=0;b<companyList.size();b++){
							performanceReportService.processFinancialRatio(companyList.get(b).getId(), currentQuarter);
						}
						
					}//第二季度结果计算结束
					
					//任何季度都要做的，就放在这里
					message="当前季度竞赛结果已经发布";
					competitionResultService.updateCompetitionCurrentQuarter(competitionId, currentQuarter+1);
	
					//将起止时间存入表中
					for(int n=0;n<companyList.size();n++){
						CompanyQuarterTime comQT=new CompanyQuarterTime();
						comQT.setCompanyId(companyList.get(n).getId());
						comQT.setQuarter(currentQuarter+1);
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
						//System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
						String startTime=df.format(new Date());
						comQT.setStartTime(startTime);
						comQT.setIsSubmit(0);
						comQT.setCompetitionId(competitionId);
						competitionResultService.insertCompanyQuarterTime(comQT);
					}//companyQuarterTime表的for循环
					
				}
				
			}else if(currentQuarter>competition.getQuarter()){
				message="本次竞赛已经结束！";
			}
		
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		/*message=new String(message.getBytes(),"UTF-8");*/
		model.addAttribute("message", message);
		
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		model.addAttribute("quarter", currentQuarter);
		
		request.setAttribute("message", message);
		/*return "jsp/competitionResult/releaseResult";*/
		//return "forward:/jumpCompetitionResult.do?id="+competitionId;
		return "redirect:/jumpCompetitionResult.do?id="+competitionId;
	}
	
	//删除当前季度发布结果
	@Transactional(propagation = Propagation.REQUIRED)
	@RequestMapping("/competitionResult/deleteReleaseResult.do")
	public String deleteReleaseResult(Model model,Integer competitionId,Integer currentQuarter) throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		int quarter=currentQuarter-1;
		try{
			List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
			deleteReleaseResultService.deleteCompanyInvestByCompetitionIdQuarter(competitionId, quarter);
			deleteReleaseResultService.deleteProductEfficiencyByCompetitionIdQuarter(competitionId, quarter);
			deleteReleaseResultService.deleteProductMarketShareByCompetitionIdQuarter(competitionId, quarter);
			deleteReleaseResultService.deleteCompanyQuarterTimeByCompetitionIdQuarter(competitionId, quarter+1);
			if(companyList!=null&& companyList.size()>0){
				for(int n=0;n<companyList.size();n++){
					deleteReleaseResultService.deleteCompanyMarketShareByCompanyIdQuarter(companyList.get(n).getId(), quarter);
					deleteReleaseResultService.deleteBalanceScoreByCompanyIdQuarter(companyList.get(n).getId(), quarter);
					deleteReleaseResultService.deleteFinancialRatioByCompanyIdQuarter(companyList.get(n).getId(), quarter);
				}
			}
			competitionResultService.updateCompetitionCurrentQuarter(competitionId, quarter);
		
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		
		model.addAttribute("competition", competition);
		model.addAttribute("currentQuarter", quarter);
		return "jsp/competitionResult/releaseResult";
	}
	
}
