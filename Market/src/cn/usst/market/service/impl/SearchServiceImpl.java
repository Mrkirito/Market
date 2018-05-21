package cn.usst.market.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.usst.market.mapper.CompanyMapper;
import cn.usst.market.mapper.SallSituationMapper;
import cn.usst.market.po.Company;
import cn.usst.market.po.CompanyDecision;
import cn.usst.market.po.CompanyDecitionZLYPo;
import cn.usst.market.po.CompanyMarket;
import cn.usst.market.po.GlobalPathNeedsVo;
import cn.usst.market.po.HirePeople;
import cn.usst.market.po.MarketOpenedPo;
import cn.usst.market.po.ProductMarketShare;
import cn.usst.market.po.PysicalEmploeePo;
import cn.usst.market.po.SaleIncomVo;
import cn.usst.market.po.SallSituationVo;
import cn.usst.market.po.StoreInforVo;
import cn.usst.market.service.SearchService;

@Service("SearchServiceImpl")
public class SearchServiceImpl implements SearchService {
	
	@Autowired
	public SallSituationMapper SSM;
	
	@Autowired
	public CompanyMapper CM;

	@Override
	public Integer SelectMoneySum(Integer company_id){
		return SSM.selectMoneySum(company_id);
	}

	@Override
	public Integer SelectMoneySum(Integer company_id,Integer decitonid){
		
		CompanyDecision cd = new CompanyDecision();
		cd.setCompanyId(company_id);
		cd.setDecisionId(decitonid);
		
		Integer sourceMoney = SSM.selectMoneySum(company_id);
		if(sourceMoney==null){
			sourceMoney=0;
		}
		Integer adjusted = SSM.selectMoneybyDecitionIDandCompanyID(cd);
		if(adjusted==null){
			adjusted=0;
		}
		System.out.println("companyid: "+company_id);
		System.out.println("decitonid: "+decitonid);
		System.out.println("adjusted: "+adjusted);
		
		return sourceMoney-adjusted;
	}
	
	@Override
	public Integer CompanyDecitionLog(int companyid,Integer money,int decitionid){
		/**
		 * 这里的策略是：
		 * 1，直接删除dicision_id下的所有记录
		 * 2，重写dicision_id下的所有记录
		 * 3，传入的值为正数，为这个季度花掉的钱，在函数内要做翻转，翻转成负数
		 * 		这一代码风格是因为历史遗留问题。当初设计的时候本来是保存正数
		 * 		在代码已经写好了之后又说要改成负数，所以只好在这里改了
		 * 4，这里要检查是否需要提示紧急贷款。这个代码设计在这里也是同3的历史遗留问题
		 * 		如果需要紧急贷款，则返回贷款数字。如果不需要，则返回0；
		 */
		money = -money;
		CompanyDecitionZLYPo po = new CompanyDecitionZLYPo();
		po.setCompany_id(companyid);
		po.setMoney(money);
		po.setDecision_id(decitionid);
		System.out.println("companyid: "+companyid);
		System.out.println("decitionid: "+decitionid);
		System.out.println("money: "+money);

		Integer lastDecition;
		/*NullException Error*/
		Integer monysum= SSM.selectMoneySum(companyid);
		if(monysum==null){
			monysum=0;
		}
		if(SSM.countMoneybyDecitionID(po.getDecision_id()) != 0)
			lastDecition = SSM.selectMoneybyDecitionID(po.getDecision_id());
		else
			lastDecition = 0;
		/*
		 * 当数据库返回空表的时候上面的查询有可能出现空指针错误
		 * 因此需要一个Integer i做中间变量
		 * 这样可以避免空指针错误
		 */
		System.out.println("故障语句通过");
		System.out.println("monysum:"+monysum);
		System.out.println("lastDecition:"+lastDecition);
		System.out.println("money:"+money);
		
		Integer moneys = monysum-lastDecition+money;
		System.out.println("计算完毕");
		//将本次决策写入日志
		SSM.DelCompanyDecitionLog(po);
		SSM.CompanyDecitionLog(po);
		if(moneys < 0)
			return moneys;
		else 
			return 0;
	}

	@Override
	public List<SallSituationVo> selectSaleSituationbyCompanyID(PysicalEmploeePo po) {
		// TODO Auto-generated method stub
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("cn.usst.market.service.impl.SearchServiceImpl.selectSaleSituationbyCompanyID DebugInfor Begin");
		System.out.println("company_id ："+po.getCompanyid());
		System.out.println("quarter ："+po.getQuater());
		System.out.println("Mapper : SSM.selectSaleSituationbyCompanyID");
		List<SallSituationVo> SSV = SSM.selectSaleSituationbyCompanyID(po);
		System.out.println("DB function well");
		List<SallSituationVo> SSV2 = new ArrayList<SallSituationVo>();
		for(SallSituationVo v:SSV){
			int income = v.getPP().getPrice()*(v.getPMS().getHongkongSale()+v.getPMS().getMoscowSale()+v.getPMS().getNewdelhiSale()+v.getPMS().getSingaporeSale());
			int lost = v.getPP().getPrice()*v.getPMS().getStockoun();
			v.setSaleIncom(income);
			v.setSaleLost(lost);
			v.setMeedssum(v.getPMS().getMoscowNeed()+v.getPMS().getNewdelhiNeed()+v.getPMS().getSingaporeNeed()+v.getPMS().getHongkongNeed()+v.getPMS().getOnlineNeed());
			v.setSalesum(v.getPMS().getMoscowSale()+v.getPMS().getNewdelhiSale()+v.getPMS().getSingaporeSale()+v.getPMS().getHongkongSale()+v.getPMS().getOnlineSale());
			SSV2.add(v);
		}
		System.out.println("cn.usst.market.service.impl.SearchServiceImpl.selectSaleSituationbyCompanyID DebugInfor End");
		System.out.println("-----------------------------------------------------------------------");
		return SSV2;
	}
	
	public List<SallSituationVo> selectSaleSituationbyCompanyID(int companyid,int quarter){
		System.out.println("SerchServiceImpl: selectSaleSituationbyCompanyID called");
		System.out.println("companyid ： "+companyid);
		System.out.println("quarter ： "+quarter);
		PysicalEmploeePo po = new PysicalEmploeePo();
		po.setQuater(quarter);
		po.setCompanyid(companyid);
		return selectSaleSituationbyCompanyID(po);
	}

	@Override
	public List<CompanyMarket> selectStoreStratge(int companyid, int quarter) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("SerchServiceImpl: selectStoreStratge called");
		
		Company infor = new Company();
		infor.setId(companyid);
		
		Company me = CM.findCompanyById(infor);
		List<Company> ListC = CM.findCompanyListByCompetitionId(me.getCompetitionId());
		List<CompanyMarket> ListCM = new ArrayList<CompanyMarket>();
		
		for(Company c:ListC){
			CompanyMarket CM2 = new CompanyMarket();
			CM2.setIsPhy(1);
			CM2.setCompanyId(c.getId());
			CM2.setQuarter(quarter);
			List<CompanyMarket> ListCM2 = CM.showCompanymarket(CM2);
			ListCM.add(ListCM2.get(0));
		}
		
		return ListCM;
	}

	@Override
	public List<SaleIncomVo> selectPathAbilitybyCompanyID(PysicalEmploeePo po) {
		// TODO Auto-generated method stub
		System.out.println("SerchServiceImpl: selectPathAbilitybyCompanyID called");
		List<SaleIncomVo> SSV = SSM.selectPathAbilitybyCompanyID(po);
		List<SaleIncomVo> SSV2 = new ArrayList<SaleIncomVo>();
		for(SaleIncomVo v:SSV){
			v.setMeedssum(v.getPMS().getMoscowNeed()+v.getPMS().getNewdelhiNeed()+v.getPMS().getSingaporeNeed()+v.getPMS().getHongkongNeed()+v.getPMS().getOnlineNeed());
			v.setSalesum(v.getPMS().getMoscowSale()+v.getPMS().getNewdelhiSale()+v.getPMS().getSingaporeSale()+v.getPMS().getHongkongSale()+v.getPMS().getOnlineSale());
			SSV2.add(v);
		}
		return SSV2;
	}

	@Override
	public List<SaleIncomVo> selectPathAbilitybyCompanyID(int companyid, int quarter) {
		// TODO Auto-generated method stub
		System.out.println("SerchServiceImpl: selectPathAbilitybyCompanyID called");
		
		PysicalEmploeePo po = new PysicalEmploeePo();
		po.setQuater(quarter);
		po.setCompanyid(companyid);
		
		//查询
		List<SaleIncomVo> ListSIV = selectPathAbilitybyCompanyID(po);

		//整理数据
		List<SaleIncomVo> ListSIV2 = new ArrayList<SaleIncomVo>();
		for(SaleIncomVo vo:ListSIV){
			int saleIncomSum = vo.getPP().getPrice()*(vo.getPMS().getHongkongSale()+vo.getPMS().getMoscowSale()+vo.getPMS().getNewdelhiSale()+vo.getPMS().getSingaporeSale()+vo.getPMS().getOnlineSale());
			vo.setSaleIncomSum(saleIncomSum);
			int saleCostSum = vo.getCP().getCost()*(vo.getPMS().getHongkongSale()+vo.getPMS().getMoscowSale()+vo.getPMS().getNewdelhiSale()+vo.getPMS().getSingaporeSale()+vo.getPMS().getOnlineSale());
			vo.setSaleCostSum(saleCostSum);
			int youjiSum = vo.getPP().getYouji()*(vo.getPMS().getHongkongSale()+vo.getPMS().getMoscowSale()+vo.getPMS().getNewdelhiSale()+vo.getPMS().getSingaporeSale()+vo.getPMS().getOnlineSale());
			vo.setYoujiSum(youjiSum);
			ListSIV2.add(vo);
		}
		return ListSIV2;
	}

	
	@Override
	public List<GlobalPathNeedsVo> selectGlobalPathSharebycompanyid(int companyid, int quarter) {
		// TODO Auto-generated method stub
		System.out.println("SerchServiceImpl:selectGlobalPathSharebycompanyid Called");
		System.out.println("companyid : "+companyid);
		System.out.println("quarter : "+quarter);
		PysicalEmploeePo po = new PysicalEmploeePo();
		po.setQuater(quarter);
		po.setCompanyid(companyid);
		List<GlobalPathNeedsVo> SSV = new ArrayList<GlobalPathNeedsVo>();
		
		boolean insertFlag = true;
		
		//这个循环的作用是从数据库中读取当前季度，以及之前所有季度的数据
		//如果公司名称和产品名称字段出现重复，则认为是重复字段
		//由于读取的顺序是从最后一个季度向前读取
		//因此后出现的重复字段必然是多余字段，因此后出现的字段应当被丢弃
		System.out.println("开始循环查询之前每个季度的公司和产品信息");
		System.out.println("初始公司ID : "+po.getCompanyid());
		System.out.println("初始季度 : "+po.getQuater()+"\n");
		while(po.getQuater()==quarter){
			System.out.println("当前公司ID : "+po.getCompanyid());
			System.out.println("当前季度 : "+po.getQuater());
			List<GlobalPathNeedsVo> SSVmiddle = SSM.selectGlobalPathSharebycompanyid(po);
			for(GlobalPathNeedsVo m : SSVmiddle){
				List<GlobalPathNeedsVo> SSVmiddle2 = new ArrayList<GlobalPathNeedsVo>();
				for(GlobalPathNeedsVo m2 : SSV){
					System.out.println("SSV读取：公司名:"+m2.getC().getName()+" 产品名:"+m2.getCp().getName());
					if(m2!=null && m2.getC().getName()==m.getC().getName() &&m2.getCp().getName() == m.getCp().getName() )
						insertFlag = false;//如果flag判断为false，则说明遇到了应当丢弃的多余字段
					SSVmiddle2.add(m2);
				}
				for(GlobalPathNeedsVo m2 : SSVmiddle2){
					System.out.println("SSV输入：公司名:"+m2.getC().getName()+" 产品名:"+m2.getCp().getName());
					//SSV.add(m2);
				}
				if(insertFlag)//如果flag判断为false，则说明遇到了应当丢弃的多余字段
					SSV.add(m);
				insertFlag = true;
			}
			po.setQuater(po.getQuater()-1);
			System.out.println("开始下一轮循环\n");
		}

		System.out.println("循环结束");
		List<GlobalPathNeedsVo> SSV2 = new ArrayList<GlobalPathNeedsVo>();
		for(GlobalPathNeedsVo v:SSV){
			v.setMeedssum(v.getPms().getMoscowNeed()+v.getPms().getNewdelhiNeed()+v.getPms().getSingaporeNeed()+v.getPms().getHongkongNeed()+v.getPms().getOnlineNeed());
			v.setSalesum(v.getPms().getMoscowSale()+v.getPms().getNewdelhiSale()+v.getPms().getSingaporeSale()+v.getPms().getHongkongSale()+v.getPms().getOnlineSale());
			SSV2.add(v);
		}
		return SSV2;
	}

	@Override
	public List<StoreInforVo> selectStoreInforbycompanyid(int companyid, int quarter) {
		// TODO Auto-generated method stub
		System.out.println("SerchServiceImpl:selectStoreInforbycompanyid Called");
		PysicalEmploeePo po = new PysicalEmploeePo();
		po.setQuater(quarter);
		po.setCompanyid(companyid);
		
		System.out.println("InforGet");
		System.out.println("quarter : "+quarter);
		System.out.println("companyid : "+companyid);
		List<StoreInforVo> SIVmid = SSM.selectStoreInforbycompanyid(po);

		System.out.println("DBVisited");
		List<StoreInforVo> SIV = new ArrayList<StoreInforVo>();
		System.out.println("-----For Started-----");
		for(StoreInforVo s:SIVmid){
			System.out.println("CompanyId : "+s.getCM().getCompanyId());
			System.out.println("Isphy : "+s.getCM().getIsPhy());
			//获取偏移量
			Integer d = CM.showMarketInfo(s.getC().getCompetitionId()).get(0).getId();
			String[] str = s.getCM().getMarketId().split(",");
			for(String ss:str)//新版本中，需要加入21的偏移量
				System.out.println("ss : "+ss);
			int[] i = new int[4];
			for(Integer count = 0;count<4;count++){
				Integer ccc = count + d;
				for(int y = 0;y<str.length;y++){
					if(str[y].equals(ccc.toString()))
						i[count] = 1;
				}
			}
			String[] mStr = new String[4];
			for(int j = 0;j<4;j++){
				System.out.println("i["+j+"] = "+i[j]);
				if(i[j]==1)
					mStr[j]="●";
				/*else
					mStr[j]="否";*/
			}
			s.setStoreList(i);
			s.setmStr(mStr);
			if(s.getCM().getIsPhy() != 1){
				List<StoreInforVo> SIV2 = new ArrayList<StoreInforVo>();
				for(StoreInforVo p:SIV){
					if(p.getC().getName().equals(s.getC().getName())){
						System.out.println("p.getC().getName()"+p.getC().getName());
						System.out.println("p.getCM().getIsPhy()"+p.getCM().getIsPhy());
						p.setIsphy("●");
						s=p;
						continue;
					}
					SIV2.add(p);
				}
				SIV.removeAll(SIV);
				for(StoreInforVo p:SIV2){
					SIV.add(p);
				}
			}
			SIV.add(s);
		}
		System.out.println("-----For Ended-----");

		System.out.println("ServiceReturned");
		return SIV;
	}
	
	@Override
	public void HirePeopleAdjust(String[] ss,int quarter,int company_id){
		PysicalEmploeePo po = new PysicalEmploeePo();;
		po.setCompanyid(company_id);
		po.setQuater(quarter);
		System.out.println("HirePeopleAdjust company_id : " + company_id);
		System.out.println("HirePeopleAdjust quarter : "+quarter);
		System.out.println("HirePeopleAdjust select");
		List<HirePeople> L = SSM.selectHirePeoplebyCompanyID(po);
		System.out.println("HirePeopleAdjust del");
		//SSM.deletHirePeoplebyCompanyID(po);
		int MarketidTempLog = -1;
		for(String s:ss){
			boolean found = false;
			System.out.println("Before for --- s: "+s);
			for(HirePeople l:L){
				System.out.println("HirePeopleAdjust if");
				System.out.println("l.getMarketId(): "+l.getMarketId()+" s: "+s);
				if(MarketidTempLog == l.getMarketId())
					break;
				else
					MarketidTempLog = l.getMarketId();
				System.out.println("l.getMarketId().toString().equals(s): "+(l.getMarketId().toString().equals(s)));
				if(l.getMarketId().toString().equals(s)){
					SSM.updateHirePeoplebyCompanyID(l);
					found = true;
					System.out.println("found and true");
				}
			}
			if(!found){//如果判断为真，则应当手动生成记录并放入内存
				HirePeople l = new HirePeople();
				l.setCompanyId(company_id);
				Integer i = Integer.parseInt(s);
				l.setMarketId(i);
				l.setQuarter(quarter);
				l.setSaleman(0);
				l.setAfterSale(0);
				SSM.insertHirePeoplebyCompanyID(l);
			}
		}
	}
	
	@Override
	public boolean marketIsOpened(Integer company_id,Integer market_id){
		MarketOpenedPo po = new MarketOpenedPo();
		po.setCompany_id(company_id);
		po.setMarket_opened(market_id.toString());
		System.out.println("marketIsOpened/company_id：" + company_id);
		System.out.println("marketIsOpened/market_id：" + market_id);
		if(SSM.countWhetherMarketOpened(po) == 0){
			//如果此处判断为真，则说明这家公司还没有开店记录
			//SSM.insertOpenedMarket(po);
			//在早期的设计中，这里要更新数据库。
			//现在这个设计已被放弃，只返回查找结果
			System.out.println("company_id:" + company_id+" market_id:"+market_id+" Store was not opened last season");
			return false;//报告上个季度没有开店
		}
		
		System.out.println("company_id：" + company_id);
		
		MarketOpenedPo result = SSM.selectOpenedMarket(po);
		String marketId="";
		if(result!=null&&result.getMarket_opened()!=null){
			marketId=result.getMarket_opened();
		}

		String[] marketList = marketId.split(",");
		for(String OpenedMarket : marketList){
			System.out.println("marketIsOpened/for/OpenedMarket：|" + OpenedMarket+"|");
			System.out.println("marketIsOpened/market_id：|" + market_id+"|");
			System.out.println("market_id.toString().equals(OpenedMarket)：" + market_id.toString().equals(OpenedMarket));
			if(market_id.toString().equals(OpenedMarket))
				return true;//数据库中找到对应的marketID，说明这个位置已经开过店了
		}
		return false;
	}
	
	@Override
	public void resetmarketIsOpened(Integer company_id,String marketOpened){
		MarketOpenedPo po = new MarketOpenedPo();
		po.setCompany_id(company_id);
		po.setMarket_opened(marketOpened);
		
		MarketOpenedPo po2 = SSM.selectOpenedMarket(po);
		String oldMarketOpenedId="";
		if(po2!=null&&po2.getMarket_opened()!=null){
			oldMarketOpenedId=po2.getMarket_opened();
		}
		String[] Oldmarkets = oldMarketOpenedId.split(",");
		
		String[] Newmarkets = marketOpened.split(",");
		
		boolean has;
		String result = ",";
		for(String N : Newmarkets){
			has = false;
			for(String O : Oldmarkets)
				if(N.equals(O))
					has = true;

			if(!has)
				result += N+",";
		}
		
		for(String O : Oldmarkets)
			result += O+",";
		
		po.setMarket_opened(result);
		
		SSM.deletOpenedMarket(po);
		SSM.insertOpenedMarket(po);
	}
}
