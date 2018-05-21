package cn.usst.market.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.usst.market.annotation.MethodLog;
import cn.usst.market.mapper.FinalCheckMapper;
import cn.usst.market.po.AdvertiseInfo;
import cn.usst.market.po.CapacityPojo;
import cn.usst.market.po.Company;
import cn.usst.market.po.CompanyAdvertise;
import cn.usst.market.po.CompanyCapacity;
import cn.usst.market.po.CompanyDuty;
import cn.usst.market.po.CompanyMarket;
import cn.usst.market.po.CompanyMedia;
import cn.usst.market.po.CompanyProduct;
import cn.usst.market.po.CompanyReport;
import cn.usst.market.po.CompanyRule;
import cn.usst.market.po.CompanyRuleInfo;
import cn.usst.market.po.CompanyStock;
import cn.usst.market.po.CompanyStrategy;
import cn.usst.market.po.Competition;
import cn.usst.market.po.DepositPojo;
import cn.usst.market.po.Duty;
import cn.usst.market.po.FinalCheck;
import cn.usst.market.po.FixedDeposit;
import cn.usst.market.po.HirePeople;
import cn.usst.market.po.HirePeopleOnline;
import cn.usst.market.po.IdQuarter;
import cn.usst.market.po.KeyDecisionVo;
import cn.usst.market.po.MarketInfo;
import cn.usst.market.po.MarketInfo2;
import cn.usst.market.po.MarketPojo;
import cn.usst.market.po.MediaInfo;
import cn.usst.market.po.Member;
import cn.usst.market.po.MemberDutyCustom;
import cn.usst.market.po.OperationCapacity;
import cn.usst.market.po.PersonalGoal;
import cn.usst.market.po.PersonalGoalCustom;
import cn.usst.market.po.PersonalGoalInfo;
import cn.usst.market.po.PositionPojo;
import cn.usst.market.po.ProductInfo;
import cn.usst.market.po.ProductPojo;
import cn.usst.market.po.ProductPrice;
import cn.usst.market.po.RulePojo;
import cn.usst.market.po.SalesSalary;
import cn.usst.market.po.StockPojo;
import cn.usst.market.po.StrategyInfo;
import cn.usst.market.po.StrategyPojo;
import cn.usst.market.po.WorkersSalary;
import cn.usst.market.service.CompanyService;
import cn.usst.market.service.CompetitionResultService;
import cn.usst.market.service.CompetitionService;
import cn.usst.market.service.MemberService;
import cn.usst.market.service.PolicyDecisionService;
import cn.usst.market.service.TeacherService;

@Controller
public class PolicyDecisionController {
	@Autowired
	private CompetitionService competitionService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private PolicyDecisionService policyDecisionService;
	
	@Autowired
	private FinalCheckMapper finalCheckMapper;
	
	@Autowired
	private CompetitionResultService competitionResultService;
	
	//================================
	//接下来是当前季度决策
	@RequestMapping("/jumpPolicyDecision.do")
	public String jumpPolicyDecision(Model model,Integer id) throws Exception{
		
		Competition competition= competitionService.findCompetitionById(id);
		//List<Company> companyList= competitionService
		//通过形参中的model将model数据传到页面,相当于上面addObjeect
		model.addAttribute("competition", competition);
		
		//返回jsp页面，相当于modelAndView.setViewName
		return "jsp/policyDecision/policyDecision";	
	}

	//==============关键决策
	@RequestMapping("/policyDecision/keyDecision.do")
	public String keyDecision(Model model,Integer competitionId,Integer currentQuarter)throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		
		List<KeyDecisionVo> keyDecisionList= new ArrayList<KeyDecisionVo>();
		for(int i=0;i<companyList.size();i++){
			KeyDecisionVo keyDecision=new KeyDecisionVo();
			IdQuarter idQuarter=new IdQuarter();
			idQuarter.setId(companyList.get(i).getId());
			idQuarter.setQuarter(currentQuarter);
			//公司
			keyDecision.setCompany(companyList.get(i));
			
			//找目标细分市场，实用型--商旅型
			CompanyStrategy companyStrategy=teacherService.findCompanyGoalAndPolicy(idQuarter);
			keyDecision.setCompanyStrategy(companyStrategy);
			
			//找公司新品牌
			List<CompanyProduct> companyProductList=policyDecisionService.findProductsByCompanyIdQuarter(companyList.get(i).getId(), currentQuarter);
			keyDecision.setCompanyProductList(companyProductList);
			
			//销售品牌的数量,二季度之后
			int productNumber=0;
			/*for(int j=currentQuarter;j>=1;j--){
				//IdQuarter productNumIdQuarter=new IdQuarter();
				//productNumIdQuarter.setId(companyList.get(i).getId());
				//productNumIdQuarter.setQuarter(j);
				List<CompanyProduct> productList=policyDecisionService.findProductsByCompanyIdQuarter(companyList.get(i).getId(), j);
				productNumber=productList.size()+productNumber;
			}*/
			List<CompanyProduct> productList=policyDecisionService.findProductsByCompanyIdQuarter(companyList.get(i).getId(), currentQuarter);
			productNumber=productList.size();
			keyDecision.setProductNumber(productNumber);
			
			//最低价、最高价、平均价
			int minPrice=0,maxPrice=0,avgPrice=0;
			minPrice=policyDecisionService.findMinPriceProduct(idQuarter);
			maxPrice=policyDecisionService.findMaxPriceProduct(idQuarter);
			avgPrice=policyDecisionService.findAvgPriceProduct(idQuarter);
			keyDecision.setMinPrice(minPrice);
			keyDecision.setMaxPrice(maxPrice);
			keyDecision.setAvgPrice(avgPrice);
			
			//广告
			List<CompanyAdvertise> companyAdvertiseList=new ArrayList<>();
			companyAdvertiseList=policyDecisionService.findAdvertiseByCompanyIdQuarter(companyList.get(i).getId(),currentQuarter);
			keyDecision.setCompanyAdvertiseList(companyAdvertiseList);
			
			//广告投放
			int mediaNum=policyDecisionService.findMediaNumByIdQuarter(idQuarter);
			keyDecision.setMediaNumber(mediaNum);
			
			//销售渠道
			//实体市场
			CompanyMarket companyMarket=new CompanyMarket();
			companyMarket=competitionResultService.findCompanyMarket(companyList.get(i).getId(), currentQuarter, 1);
			List<MarketInfo2> phyMarketList=new ArrayList<MarketInfo2>();
			if(companyMarket!=null){
				String phyMarketId=companyMarket.getMarketId();
				if(phyMarketId!=null){
					String[] phyMarketIdArr=phyMarketId.split(",");
					if(phyMarketIdArr!=null&&phyMarketIdArr.length>0){
						for(int j=0;j<phyMarketIdArr.length;j++){
							MarketInfo2 marketInfo=competitionResultService.findMarketInfoById(Integer.parseInt(phyMarketIdArr[j]));
							phyMarketList.add(marketInfo);
						}
					}
				}
				
			}
			keyDecision.setPhyMarketList(phyMarketList);
			
			//网络市场
			CompanyMarket netCompanyMarket=new CompanyMarket();
			netCompanyMarket=competitionResultService.findCompanyMarket(companyList.get(i).getId(), currentQuarter, 0);
			List<MarketInfo2> netMarketList=new ArrayList<MarketInfo2>();
			if(netCompanyMarket!=null){
				String marketNetId=netCompanyMarket.getMarketId();
				if(marketNetId!=null){
					String[] netMarketIdArr=marketNetId.split(",");
					if(netMarketIdArr!=null&&netMarketIdArr.length>0){
						/*for(int j=0;j<netMarketIdArr.length;j++){
							MarketInfo2 marketInfo=competitionResultService.findMarketInfoById(Integer.parseInt(netMarketIdArr[j]));
							netMarketList.add(marketInfo);
						}*/
						netCompanyMarket.setMarketId("全球市场");
						keyDecision.setNetMarket(netCompanyMarket);
					}
				}
			}
			
			//公司人数
			List<Member> memberList=memberService.showAllMemberByComapnyId(companyList.get(i).getId());
			int memberCount=memberList.size();
			keyDecision.setMemberCount(memberCount);
			
			//销售人员人数
			List<HirePeople> phySaler=policyDecisionService.findHirePeopleByCompanyIdQuarter(companyList.get(i).getId(), currentQuarter);
			int phySalerNum=0;
			if(phySaler!=null&&phySaler.size()>0){
				for(int a=0;a<phySaler.size();a++){
					phySalerNum+=phySaler.get(a).getSaleman()+phySaler.get(a).getAfterSale();
				}
			}
			keyDecision.setPhySalerNum(phySalerNum);
			
			List<HirePeopleOnline> netSaler=policyDecisionService.findHirePeopleOnlineByCompanyIdQuarter(companyList.get(i).getId(), currentQuarter);
			int netSalerNum=0;
			if(netSaler!=null&&netSaler.size()>0){
				for(int a=0;a<netSaler.size();a++){
					phySalerNum+=netSaler.get(a).getSaleman()+netSaler.get(a).getAfterSale();
				}
			}
			keyDecision.setNetSalerNum(netSalerNum);
			
			//销售人员薪酬
			SalesSalary salesSalary=policyDecisionService.findSalesSalaryByIdQuarter(idQuarter);
			keyDecision.setSalesSalary(salesSalary);
			
			//工厂工人薪酬
			WorkersSalary workersSalary=policyDecisionService.findWorkersSalaryByIdQuarter(idQuarter);
			keyDecision.setWorkersSalary(workersSalary);
			
			//生产制造,下季度将增加的固定产能
			CompanyCapacity companyCapacity=teacherService.findCompanyCapacityByIdQuarter(idQuarter);
			keyDecision.setCompanyCapacity(companyCapacity);
			
			//公司持股
			List<CompanyStock> companyStockList=teacherService.findCompanyStockByIdQuarter(idQuarter);
			float companyStockSum=0;
			if(companyStockList!=null &&companyStockList.size()>0){
				for(CompanyStock companyStock:companyStockList){
					companyStockSum+=companyStock.getTotalPrice();
				}
			}
			keyDecision.setCompanyStockSum(companyStockSum);
			//定期存款
			FixedDeposit fixedDeposit=teacherService.findCompanyFixedDepositByIdQuarter(idQuarter);
			if(fixedDeposit!=null){
				keyDecision.setFixedDeposit(fixedDeposit.getCunru());
			}else{
				keyDecision.setFixedDeposit(0);
			}
			
			keyDecisionList.add(keyDecision);
		}
		
		model.addAttribute("competition", competition);
		model.addAttribute("keyDecisionList", keyDecisionList);
		model.addAttribute("currentQuarter", currentQuarter);
		
		if(currentQuarter==1){
			return "jsp/policyDecision/keyDecision";
		}else if(currentQuarter==2){
			return "jsp/policyDecision/keyDecisionQ2";
		}else if(currentQuarter>=3){
			return "jsp/policyDecision/keyDecisionQ3";
		}
		return "null";
		/*return "jsp/policyDecision/keyDecision";*/
		
	}
	
	
	
	//==============决策汇总
	@RequestMapping("/policyDecision/decisionSummary.do")
	public String decisionSummary(Model model,Integer competitionId,Integer currentQuarter)throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		
		model.addAttribute("competition", competition);
		model.addAttribute("companyList", companyList);
		model.addAttribute("currentQuarter", currentQuarter);
		
		return "jsp/policyDecision/decisionSummary";
		
	}
	
	//每个公司的决策汇总
	@MethodLog(description="查看决策汇总操作")
	@RequestMapping("/policyDecision/companyDecisionSummary.do")
	public String companyDecisionSummary(Model model,Integer companyId,Integer currentQuarter)throws Exception{
		Company company= companyService.selectCompanyById(companyId);
		
		List<MemberDutyCustom> memberDutyList=teacherService.findMemberDutyList(companyId);
		
		IdQuarter idQuarter=new IdQuarter();
			idQuarter.setId(companyId);
			idQuarter.setQuarter(currentQuarter);
		CompanyStrategy companyStrategy=teacherService.findCompanyGoalAndPolicy(idQuarter);
		//查找已选则的策略
		List<StrategyInfo> strategyList=new ArrayList<StrategyInfo>();
		if(companyStrategy!=null){
			String strategyId=companyStrategy.getStrategyId();
			if(strategyId!=null&&strategyId.length()>0){
				String[] strategyArr= strategyId.split(",");
				for(int i=0;i<strategyArr.length;i++){
					StrategyInfo strategyInfo=teacherService.findStrategyById(Integer.parseInt(strategyArr[i]));
					strategyList.add(strategyInfo);
				}
			}
		}
		
		//查找团队规则
		CompanyRule companyRule=teacherService.findCompanyRule(idQuarter);
		List<CompanyRuleInfo> ruleInfoList=new ArrayList<CompanyRuleInfo>();
		if(companyRule!=null){
			String ruleInfoId=companyRule.getRuleId();
			if(ruleInfoId!=null&& ruleInfoId.length()>0){
				String[] ruleArr=ruleInfoId.split(",");
				for(int j=0;j<ruleArr.length;j++){
					CompanyRuleInfo companyRuleInfo=teacherService.findRuleInfoById(Integer.parseInt(ruleArr[j]));
					ruleInfoList.add(companyRuleInfo);
				}
			}
		}
		
		//查找个人目标
		List<Member> memberList=memberService.showAllMemberByComapnyId(companyId);
		List<PersonalGoalCustom> allPersonalGoalList=new ArrayList<PersonalGoalCustom>();
		for(int k=0;k<memberList.size();k++){
			int memberId=memberList.get(k).getId();
			PersonalGoal personalGoal=teacherService.findPersonalGoalByMemberId(memberId);
			if(personalGoal!=null){
				String goalId=personalGoal.getGoalId();
				List<PersonalGoalInfo> personalGoalList=new ArrayList<PersonalGoalInfo>();
				if(goalId!=null&&goalId.length()>0){
					String[] goalArr=goalId.split(",");
					for(int m=0;m<goalArr.length;m++){
						PersonalGoalInfo personalGoalInfo=teacherService.findPersonalGoalInfoById(Integer.parseInt(goalArr[m]));
						personalGoalList.add(personalGoalInfo);
					}
				}
				PersonalGoalCustom personalGoalCustom=new PersonalGoalCustom();
				personalGoalCustom.setMember(memberList.get(k));
				personalGoalCustom.setPersonalGoal(personalGoalList);
				allPersonalGoalList.add(personalGoalCustom);
			}
			
		}
		
		//查找公司生产的新产品
		List<CompanyProduct> companyProductList=policyDecisionService.findProductsByCompanyIdQuarter(companyId, currentQuarter);
		model.addAttribute("companyProductList", companyProductList);
		//List<ProductInfo> bibeiList=new ArrayList<ProductInfo>();
		//List<List<ProductInfo>> productsList=new ArrayList<>();
		HashMap<String, List<ProductInfo>> productsList=new HashMap<String, List<ProductInfo>>();
		for(int n=0;n<companyProductList.size();n++){
			String productDetailId=companyProductList.get(n).getDetail();
			List<ProductInfo> productInfoList=new ArrayList<>();
			if(productDetailId!=null&&productDetailId.length()>0){
				String[] detailIdArr=productDetailId.split(",");
				for(int m=0;m<detailIdArr.length;m++){
					ProductInfo productInfo=teacherService.findProductInfoById(Integer.parseInt(detailIdArr[m]));
					productInfoList.add(productInfo);
				}
			}
			productsList.put(companyProductList.get(n).getName(),productInfoList);
			//productsList.add(companyProductList.get(n).getName(),productInfoList);
		}
		model.addAttribute("productsList", productsList);
		//model.addAttribute("bibeiList", bibeiList);
		
		//查找设计的广告
		List<CompanyAdvertise> companyAdvertiseList=policyDecisionService.findAdvertiseByCompanyIdQuarter(companyId,currentQuarter);
		HashMap<CompanyAdvertise, List<AdvertiseInfo>> advertisesList=new HashMap<CompanyAdvertise, List<AdvertiseInfo>>();
		for(int n=0;n<companyAdvertiseList.size();n++){
			String advertiseInfoId=companyAdvertiseList.get(n).getAdvertiseId();
			List<AdvertiseInfo> advertiseInfoList=new ArrayList<>();
			if(advertiseInfoId!=null&&advertiseInfoId.length()>0){
				String[] InfoIdArr=advertiseInfoId.split(",");
				for(int m=0;m<InfoIdArr.length;m++){
					AdvertiseInfo advertiseInfo=policyDecisionService.findAdvertiseInfoById(Integer.parseInt(InfoIdArr[m]));
					advertiseInfoList.add(advertiseInfo);
				}
			}
			advertisesList.put(companyAdvertiseList.get(n),advertiseInfoList);
			//productsList.add(companyProductList.get(n).getName(),productInfoList);
		}
		model.addAttribute("advertisesList", advertisesList);
		
		//找所有媒体
		List<MediaInfo> mediaInfoList=new ArrayList<>();
		mediaInfoList=policyDecisionService.findAllMediaInfo();
		model.addAttribute("mediaInfoList", mediaInfoList);
		
		//找产品对应的媒体数量
		HashMap<String,List<CompanyMedia>> productMediaNumList=new HashMap<String,List<CompanyMedia>>();
		int mediaCostSum=0;
		List<Integer> proId=policyDecisionService.findProductIdFromMedia(idQuarter);
		if(proId!=null&&proId.size()>0){
			for(int n=0;n<proId.size();n++){
				CompanyProduct product=policyDecisionService.findProductById(proId.get(n));
				if(product!=null){
					List<CompanyMedia> mediaNum=policyDecisionService.findCompanyMediaByIdQuarterPid(companyId, currentQuarter, proId.get(n));
					productMediaNumList.put(product.getName(), mediaNum);
					//计算媒体投资总和
					if(mediaNum!=null&mediaNum.size()>0){
						for(int m=0;m<mediaNum.size();m++){
							mediaCostSum+=mediaNum.get(m).getNum()*mediaInfoList.get(m).getCost();
						}
					}
				}
			}
		}
		model.addAttribute("productMediaNumList", productMediaNumList);
		model.addAttribute("mediaCostSum", mediaCostSum);
		
		//找产品定价
		HashMap<String,ProductPrice> productPriceList=new HashMap<String,ProductPrice>();
		List<CompanyProduct> productList=policyDecisionService.findProductsByCompanyIdQuarter(companyId,currentQuarter);
		if(productList!=null&&productList.size()>0){
			for(int n=0;n<productList.size();n++){
				ProductPrice salePrice=policyDecisionService.findProductSalePriceByIdQPid(companyId, currentQuarter, productList.get(n).getId());
				if(salePrice!=null){
					productPriceList.put(productList.get(n).getName(), salePrice);
				}
			}
		}
		model.addAttribute("productPriceList", productPriceList);
		
		//查找是否购买市场调研报告
		String isBuyReport;
		CompanyReport comReport=policyDecisionService.findComReportByIdQuarter(idQuarter);
		if(comReport!=null){
			isBuyReport="您购买了市场调研报告！总费用15000元";
		}else{
			isBuyReport="您没有购买市场调研报告！";
		}
		model.addAttribute("isBuyReport", isBuyReport);
		
		//人力资源
		//销售市场人数
		List<HirePeople> phySaler=policyDecisionService.findHirePeopleByCompanyIdQuarter(companyId, currentQuarter);
		HashMap<String,HirePeople> hirePeopleList=new HashMap<String,HirePeople>();
		if(phySaler!=null&&phySaler.size()>0){
			for(int a=0;a<phySaler.size();a++){
				MarketInfo marketInfo=policyDecisionService.findMarketInfoById(phySaler.get(a).getMarketId());
				if(marketInfo!=null){
					if(phySaler.get(a).getAfterSale()!=0 || phySaler.get(a).getSaleman()!=0){
						
						hirePeopleList.put(marketInfo.getCity(), phySaler.get(a));
					}
				}
			}
		}
		model.addAttribute("hirePeopleList", hirePeopleList);

		
		List<HirePeopleOnline> netSaler=policyDecisionService.findHirePeopleOnlineByCompanyIdQuarter(companyId, currentQuarter);
		//现在这里每个公司没季度只有一条数据
		HashMap<String,HirePeopleOnline> hirePeopleOnlineList=new HashMap<String,HirePeopleOnline>();
		if(netSaler!=null&&netSaler.size()>0){
			/*for(int a=0;a<netSaler.size();a++){
				MarketInfo marketInfo=policyDecisionService.findMarketInfoById(netSaler.get(a).getMarketId());
				if(marketInfo!=null){
					hirePeopleOnlineList.put(marketInfo.getCity(), netSaler.get(a));
				}
			}*/
			if(netSaler.get(0).getMarketId()!=null){
				hirePeopleOnlineList.put("网络市场", netSaler.get(0));
			}
		}
		model.addAttribute("hirePeopleOnlineList", hirePeopleOnlineList);
		
		//薪酬
		SalesSalary salesSalary=policyDecisionService.findSalesSalaryByIdQuarter(idQuarter);
		model.addAttribute("salesSalary", salesSalary);
		WorkersSalary workersSalary=policyDecisionService.findWorkersSalaryByIdQuarter(idQuarter);
		model.addAttribute("workersSalary", workersSalary);
		
		
		//销售渠道，实体销售中心
		CompanyMarket companyPhyMarket=teacherService.findCompanyPhyMarketByIdQuarter(idQuarter);
		List<MarketInfo2> phyMarketList=new ArrayList<MarketInfo2>();
		int phyTotalPrice=0;
		if(companyPhyMarket!=null){
			String marketPhyId=companyPhyMarket.getMarketId();
			if(marketPhyId!=null&& marketPhyId.length()>0){
				String[] marketPhyIdArr=marketPhyId.split(",");
				for(int j=0;j<marketPhyIdArr.length;j++){
					MarketInfo2 marketInfo=competitionResultService.findMarketInfoById(Integer.parseInt(marketPhyIdArr[j]));
					//MarketInfo2 marketInfo=teacherService.findMarketInfoById(Integer.parseInt(marketPhyIdArr[j]));
					phyTotalPrice+=marketInfo.getOpen();
					phyMarketList.add(marketInfo);
				}
			}
		}
		model.addAttribute("phyMarketList", phyMarketList);
		model.addAttribute("phyTotalPrice", phyTotalPrice);
		//网络销售中心
		CompanyMarket companyNetMarket=teacherService.findCompanyNetMarketByIdQuarter(idQuarter);
		//List<MarketInfo2> netMarketList=new ArrayList<MarketInfo2>();
		String netMarket="";
		int netTotalPrice=0;
		if(companyNetMarket!=null){
			String marketNetId=companyNetMarket.getMarketId();
			if(marketNetId!=null&& marketNetId.length()>0){
				//String[] marketNetIdArr=marketNetId.split(",");
				//找本次竞赛的所有市场，将webOpen加起来
				List<MarketInfo2> marketInfo2List=competitionResultService.findMarketInfoByCompetitionId(company.getCompetitionId());
				if(marketInfo2List!=null&&marketInfo2List.size()>0){
					for(int j=0;j<marketInfo2List.size();j++){
						netTotalPrice+=marketInfo2List.get(j).getWebOpen();
					}
				}
				/*for(int j=0;j<marketNetIdArr.length;j++){
					MarketInfo2 marketInfo=competitionResultService.findMarketInfoById(Integer.parseInt(marketNetIdArr[j]));
					//MarketInfo marketInfo=teacherService.findMarketInfoById(Integer.parseInt(marketNetIdArr[j]));
					netTotalPrice+=marketInfo.getWebOpen();
					//netMarketList.add(marketInfo);
				}*/
				netMarket="网络市场";
			}else{
				netMarket="未开放网络市场";
			}
		}
		model.addAttribute("netMarket", netMarket);
		model.addAttribute("netTotalPrice", netTotalPrice);
		
		//固定产能-增加产能
		CompanyCapacity companyCapacity=teacherService.findCompanyCapacityByIdQuarter(idQuarter);
		int nextTotalCapacity=0;
		if(companyCapacity!=null){
			nextTotalCapacity=companyCapacity.getCapacityNow()+companyCapacity.getCapacityAdd();
		}
		model.addAttribute("companyCapacity", companyCapacity);
		model.addAttribute("nextTotalCapacity", nextTotalCapacity);
		//运行产能
		OperationCapacity operationCapacity=policyDecisionService.findOpeartionCapacityByCompanyIdQuarter(companyId, currentQuarter);
		model.addAttribute("operationCapacity", operationCapacity);

		
		//产品预测需求量
		HashMap<String,Integer> forecastDemandList=new HashMap<String,Integer>();
		if(productList!=null&&productList.size()>0){
			for(int m=0;m<productList.size();m++){
				int demand=0;
				demand=policyDecisionService.findForecastDemandByProductIdQuarter(productList.get(m).getId(), currentQuarter);
				forecastDemandList.put(productList.get(m).getName(), demand);
			}
		}
		model.addAttribute("forecastDemandList", forecastDemandList);
		
		//产品库存控制
		HashMap<String,Integer> inventoryList=new HashMap<String,Integer>();
		if(productList!=null&&productList.size()>0){
			for(int m=0;m<productList.size();m++){
				int inventory=0;
				inventory=policyDecisionService.findProductInventoryByProductIdQuarter(productList.get(m).getId(), currentQuarter);
				inventoryList.put(productList.get(m).getName(), inventory);
			}
		}
		model.addAttribute("inventoryList", inventoryList);
		
		//公司持股
		List<CompanyStock> companyStockList=teacherService.findCompanyStockByIdQuarter(idQuarter);
		model.addAttribute("companyStockList", companyStockList);
		//定期存款
		FixedDeposit fixedDeposit=teacherService.findCompanyFixedDepositByIdQuarter(idQuarter);
		model.addAttribute("fixedDeposit", fixedDeposit);
		
		//================
		model.addAttribute("company", company);
		model.addAttribute("memberDutyList", memberDutyList);
		model.addAttribute("companyStrategy", companyStrategy);
		model.addAttribute("strategyList", strategyList);
		model.addAttribute("companyRule", companyRule);
		model.addAttribute("ruleInfoList", ruleInfoList);
		//model.addAttribute("memberList", memberList);
		model.addAttribute("allPersonalGoalList", allPersonalGoalList);
		
		if(currentQuarter==1){
			return "jsp/policyDecision/companyDecisionSummary";
		}else if(currentQuarter==2){
			return "jsp/policyDecision/companyDecisionSummaryQ2";
		}else if(currentQuarter>=3){
			return "jsp/policyDecision/companyDecisionSummaryQ3";
		}
		return "null";
		
		//return "jsp/policyDecision/companyDecisionSummary";
	}
	
	
	
	@RequestMapping("/policyDecision/errorAndWarn.do")
	public String errorAndWarn(Model model,Integer competitionId,Integer currentQuarter)throws Exception{
		Competition competition= competitionService.findCompetitionById(competitionId);
		List<Company> companyList=companyService.showCompanyByCompetitionId(competitionId);
		model.addAttribute("companyList", companyList);
		model.addAttribute("currentQuarter", currentQuarter);
		
		return "jsp/policyDecision/errorAndWarn";
	}
	
	//检查学生端做的决策时否有错误
    @RequestMapping("/policyDecision/companyErrorAndWarn.do")
    public String companyErrorAndWarn(Model model,HttpServletRequest request, Integer companyId,Integer currentQuarter) {
        //存储检查信息的实体类
        FinalCheck finalCheck = new FinalCheck();
        //定义一个标志变量,表示决策是否存在异常
        boolean flag = true;
        System.out.println("------------------检查开始----------------------");
        int quarter = currentQuarter;
        Company company = finalCheckMapper.selectCompanyById(companyId);
        if (company != null) {
            String name = company.getName();
            if (name == null || "".equals(name.trim().toString())) {
                finalCheck.setCompanyName("抱歉，未能找到您公司的名称，快去给您的公司起个响亮的名字吧");
                flag = false;
            }
        } else {
            flag = false;
        }

        //检查目标策略
        StrategyPojo strategyPojo = finalCheckMapper.selectStrategyByCompanyId(companyId, quarter);
        if (strategyPojo != null) {
            if (strategyPojo.getMainPro() == null || strategyPojo.getMinorPro() == null) {
                finalCheck.setStrategyInfo("您还没有设计您公司的产品类型");
                flag = false;
            } else if (strategyPojo.getService() == null || "".equals(strategyPojo.getService())) {
                finalCheck.setStrategyInfo("您的公司还没有服务宗旨，这是一个很重要的决策，请慎重决定");
                flag = false;
            } else if (strategyPojo.getStrategyId() == null) {
                finalCheck.setStrategyInfo("您的公司还没有制定任何策略方向，这有可能影响您公司的进一步发展");
                flag = false;
            }
        } else {
            flag = false;
        }
        //检查人力资源
        //检查人力资源下的职位分配
        List<PositionPojo> positionPojos = finalCheckMapper.selectPositionByCompanyId(companyId);
        if (positionPojos == null) {
            finalCheck.setPositionInfo("您还没有为您的公司分配职位");
            flag = false;
        }
        //检查人力资源下的团队规则
        RulePojo rulePojo = finalCheckMapper.selectRulesByCompanyId(companyId, quarter);
        if (rulePojo == null || rulePojo.getRuleId() == null) {
            finalCheck.setRuleInfo("您还没有为您的公司建立一个有效的团队规则");
            flag = false;
        }
        //检查销售渠道
        List<MarketPojo> marketPojos = finalCheckMapper.selectMarketInfoByCompanyId(companyId, quarter);
        if (marketPojos != null) {
            for (MarketPojo marketPojo : marketPojos) {
                if (marketPojo.getMarketId() == null) {
                    finalCheck.setMarketInfo("您还没有创建实体或网络销售中心");
                    flag = false;
                }
            }
        } else {
            flag = false;
        }
        //检查生产制造
        //检查产品信息
        List<ProductPojo> productPojos = finalCheckMapper.selectProductByCompanyId(companyId, quarter);
        if (productPojos != null) {
            for (ProductPojo productPojo : productPojos) {
                if (productPojo.getName() == null || productPojo.getDetail() == null) {
                    finalCheck.setProductInfo("您的公司还没有设计任何产品");
                }
            }
        } else {
            flag = false;
        }
        //检查固定产能
        CapacityPojo capacityPojo = finalCheckMapper.selectCapacityByCompanyId(companyId,quarter);
        if (capacityPojo == null) {
            finalCheck.setCapacityInfo("您的公司还没有设置产能");
            flag = false;
        }
        //检查财务
        //检查公司持股信息
        StockPojo stockPojo = finalCheckMapper.selectStockByCompanyId(companyId,quarter);
        if (stockPojo == null) {
            finalCheck.setCapacityInfo("您的公司还没有持股信息");
            flag = false;
        }
        //检查定期存款
        DepositPojo depositPojo = finalCheckMapper.selectDepositByCompanyId(companyId,quarter);
        if (depositPojo == null) {
            finalCheck.setCapacityInfo("您的公司还没有存取款信息");
            flag = false;
        }
        model.addAttribute("flag", flag);
        if (flag) {
            model.addAttribute("finalCheck", "没有重大决策失误");
            //检查已通过，在提交再检查
            request.getSession().setAttribute("pass", true);
        } else {
            model.addAttribute("finalCheck", finalCheck);
            request.getSession().setAttribute("pass", false);
        }
        System.out.println("------------------检查结束----------------------");
        return "jsp/policyDecision/companyErrorAndWarn";
    }
	
	
	
	
	//测试返回学生端界面
	@RequestMapping(value="/policyDecision/strategyInfo.do")
	public ModelAndView strategyInfo(HttpServletRequest request,Integer currentQuarter,Integer companyId){
		
		//查询title
		List<String> titleList=companyService.countStrategyTitle();
		//根据title遍历存入map
		Map<String,List<StrategyInfo>> result=new HashMap<String,List<StrategyInfo>>();
		for(int i = 0;i < titleList.size(); i ++){
            System.out.println(titleList.get(i));
            result.put(titleList.get(i), companyService.showStrategyInfoBytitle(titleList.get(i)));
        }
		
		//查询公司id和季度
		int company_id=companyId;
		int quarter=currentQuarter;
		
		CompanyStrategy companyStrategy=companyService.selectCompanyStrategy(company_id,quarter);	
		ModelAndView modelAndView =  new ModelAndView();
		modelAndView.addObject("result",result);
		
		//若有公司该季度记录，则添加该对象
		if(companyStrategy!=null){
			modelAndView.addObject("companyStrategy",companyStrategy);
		}
		
		modelAndView.setViewName("strategy");	
		return modelAndView;
	}
	
	//返回学生端新产品
	@RequestMapping("/policyDecision/newProduct.do")
	public String newProduct(Model model,Integer currentQuarter,Integer companyId)throws Exception{
		IdQuarter idQuarter=new IdQuarter();
		idQuarter.setId(companyId);
		idQuarter.setQuarter(currentQuarter);
		
		List<CompanyProduct> companyProductList=policyDecisionService.findProductsByCompanyIdQuarter(companyId, currentQuarter);
		model.addAttribute("companyProductList", companyProductList);
		
		List<List<ProductInfo>> productsList=new ArrayList<>();
		for(int n=0;n<companyProductList.size();n++){
			String productDetailId=companyProductList.get(n).getDetail();
			List<ProductInfo> productInfoList=new ArrayList<>();
			if(productDetailId!=null&&productDetailId.length()>0){
				String[] detailIdArr=productDetailId.split(",");
				for(int m=0;m<detailIdArr.length;m++){
					ProductInfo productInfo=teacherService.findProductInfoById(Integer.parseInt(detailIdArr[m]));
					productInfoList.add(productInfo);
				}
			}
			productsList.add(productInfoList);
		}
		model.addAttribute("productsList", productsList);
		
		return "jsp/policyDecision/newProducts";
	}
	
	//实体销售中心
	@RequestMapping(value="/policyDecision/entitySailCenter.do")
	public ModelAndView entitySailCenter(HttpServletRequest request,Integer currentQuarter,Integer companyId){
		//System.out.println("实体销售中心。。。");
        int competitionId = (int) request.getSession().getAttribute("competitionId");

		List<MarketInfo> marketInfoList=companyService.showMarketInfo(competitionId);//市场信息列表
		HashMap<MarketInfo, Integer> hm=new LinkedHashMap<MarketInfo,Integer>();//用于判断公司市场
		//System.out.println("市场个数："+marketInfoList.size());
		for(int i=0;i<marketInfoList.size(); i++){
			System.out.println("第"+(i+1)+"个市场");
			MarketInfo ms=marketInfoList.get(i);
			CompanyMarket companyMarket=new CompanyMarket();
			companyMarket.setCompanyId(companyId);
			//companyMarket.setCompanyId((int)request.getSession().getAttribute("companyId"));
			companyMarket.setIsPhy(0);
			companyMarket.setQuarter(currentQuarter);
			List<CompanyMarket> companyMarkets=companyService.showCompanymarket(companyMarket);//该季度公司市场记录
			//System.out.println(companyMarkets.size()+"00000000000");//1000000000
			for(CompanyMarket cm:companyMarkets)
			{
				String string=cm.getMarketId();
				String []ss=string.split(",");
				//判断是否选择了该市场，若选择了，则放入ms中，置值为1，反之为0
				for(String sss:ss){
					
					//System.out.println(sss);
					if(sss.equals(ms.getId().toString()))
					{
						hm.put(ms, 1);
						System.out.println("1111111111111");
					}
				}
			}
			if(!hm.containsKey(ms))  
			hm.put(ms, 0);
		}
		for(Integer i:hm.values()){System.out.println(i);}//打印市场的选择值（0或1）
		ModelAndView modelAndView =  new ModelAndView();
			
		modelAndView.addObject("marketInfoList",hm);
		
		modelAndView.setViewName("physicalStore");	
		return modelAndView;
		
		
	}
	
	//网络销售中心
	@RequestMapping(value="/policyDecision/webSailCenter.do")
	public ModelAndView webSailCenter(HttpServletRequest request,Integer currentQuarter,Integer companyId){
        int competitionId = (int) request.getSession().getAttribute("competitionId");

		List<MarketInfo> marketInfoList=companyService.showMarketInfo(competitionId);
		
		
		HashMap<MarketInfo, Integer> hm=new LinkedHashMap<MarketInfo,Integer>();
		for(int i=0;i<marketInfoList.size(); i++){
			MarketInfo ms=marketInfoList.get(i);
			CompanyMarket companyMarket=new CompanyMarket();
			companyMarket.setCompanyId(companyId);
			companyMarket.setIsPhy(0);
			companyMarket.setQuarter(currentQuarter);
			List<CompanyMarket> companyMarkets=companyService.showCompanymarket(companyMarket);//该季度公司市场记录

			//System.out.println(companyMarkets.size()+"00000000000");
			for(CompanyMarket cm:companyMarkets)
			{
				String string=cm.getMarketId();
				String []ss=string.split(",");
				//System.out.println("ssssssss"+ss.length);
				
				for(String sss:ss){
					
					//System.out.println(sss);
					if(sss.equals(ms.getId().toString()))
					{
						hm.put(ms, 1);
						System.out.println("1111111111111");
					}
				}
				
			}
			if(!hm.containsKey(ms))  
			hm.put(ms, 0);
		}
		
		ModelAndView modelAndView =  new ModelAndView();
		modelAndView.addObject("marketInfoList",hm);
		modelAndView.setViewName("onlineStore");	
		return modelAndView;
	}
	
	//团队信息
	@RequestMapping(value="/policyDecision/companyNumberInfo.do")
	public ModelAndView companyNumberInfo(HttpServletRequest request,Integer currentQuarter,Integer companyId){
		//System.out.println("职位分配显示控制台。。");
		
		ModelAndView modelAndView =  new ModelAndView();
		int id=companyId;
		//int id=(int) request.getSession().getAttribute("companyId");
		//根据公司ID查出所有成员
		List<Member> memberList=memberService.showAllMemberByComapnyId(id);
		List<Duty> dutyList=companyService.showAllPosition();
		
		//查询该公司下所有成员ID
		List<Integer> memberId=companyService.selectMemberIdByCompanyId(id);
		
		List<CompanyDuty> companyDuty=companyService.selectMemberDutyByCompanyId(id);
		
		modelAndView.addObject("companyDuty",companyDuty);
		modelAndView.addObject("memberList",memberList);
		modelAndView.addObject("dutyList",dutyList);
		modelAndView.setViewName("duty");	
		return modelAndView;
	}
	
	
}
