package cn.usst.market.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.usst.market.mapper.CompanyMapper;
import cn.usst.market.mapper.PysicalPathMapper;
import cn.usst.market.po.PysicalEmploeePo;
import cn.usst.market.po.PysicalEmploeeVo;
import cn.usst.market.po.SalesSalary;
import cn.usst.market.service.PysicalPathService;

@Service("PysicalPathServiceImpl")
public class PysicalPathServiceImpl implements PysicalPathService{
	
	@Autowired
	private PysicalPathMapper ppm;
	
	@Autowired
	private CompanyMapper CM;

	@Override
	public List<PysicalEmploeePo> findPhysicalStoreEmploeeListByCompanyid(PysicalEmploeeVo vo) {
		// TODO Auto-generated method stub
		return ppm.findPhysicalStoreEmploeeListByCompanyid(vo);
	}

	@Override
	public void updateCompanyEmploeeInfo(PysicalEmploeeVo vo) {
		// TODO Auto-generated method stub
		ppm.updateCompanyEmploeeInfo(vo);
	}

	@Override
	public void insertCompanyEmploeeInfo(PysicalEmploeeVo vo) {
		// TODO Auto-generated method stub
		ppm.insertCompanyEmploeeInfo(vo);
	}

	@Override
	public void deleteCompanyEmploeeInfo(PysicalEmploeeVo vo) {
		// TODO Auto-generated method stub
		ppm.deleteCompanyEmploeeInfo(vo);
	}

	@Override
	public Integer getPopulationbyCompanyid(PysicalEmploeeVo vo) {
		// TODO Auto-generated method stub
		List<PysicalEmploeePo> PEP = new ArrayList<PysicalEmploeePo>();
		for(int i=0;i<4;i++){
			PysicalEmploeePo p = new PysicalEmploeePo();
			p.setCompanyid(vo.getPysicalEmploee().getCompanyid());
			p.setLocation(i+1);
			PEP.add(p);
		}
		Integer PEPbuff = ppm.findhiredpeople(vo)+ppm.findhiredpeopleonline(vo);
		System.out.println("实体店和网店员工总人数 : "+PEPbuff);
		return PEPbuff;
	}

	@Override
	public Integer getPopulationbyCompanyid(int companyid,int quater) {
		// TODO Auto-generated method stub
		PysicalEmploeeVo vo = new PysicalEmploeeVo();
		PysicalEmploeePo po = new PysicalEmploeePo();
		po.setCompanyid(companyid);
		po.setQuater(quater);
		vo.setPysicalEmploee(po);
		return getPopulationbyCompanyid(vo);
	}

	@Override
	public List<PysicalEmploeePo> getPopuChangesbyCompanyid(PysicalEmploeeVo vo) {
		// TODO Auto-generated method stub
		//初始化空白队列
		List<PysicalEmploeePo> PEP = new ArrayList<PysicalEmploeePo>();
		List<PysicalEmploeePo> PEP2 = new ArrayList<PysicalEmploeePo>();
		for(int i=0;i<4;i++){
			PysicalEmploeePo p = new PysicalEmploeePo();
			p.setCompanyid(vo.getPysicalEmploee().getCompanyid());
			p.setLocation(i+1);
			PEP.add(p);
		}
		for(int i=0;i<4;i++){
			PysicalEmploeePo p = new PysicalEmploeePo();
			p.setCompanyid(vo.getPysicalEmploee().getCompanyid());
			p.setLocation(i+1);
			PEP2.add(p);
		}
		List<PysicalEmploeePo> PEPbuff = new ArrayList<PysicalEmploeePo>();
		PEPbuff = ppm.findPhysicalStoreEmploeeListByCompanyid(vo);
		Iterator<PysicalEmploeePo> it= PEPbuff.iterator();
		while(it.hasNext()){
			System.out.println("--------------------------");
			PysicalEmploeePo p = it.next();
			if(p.getQuater()>vo.getPysicalEmploee().getQuater())
				break;
			System.out.println("p.getLocation()-1 : "+(p.getLocation()-1));
			System.out.println("PEP.get(p.getLocation()-1).getLocation()"+PEP.get(p.getLocation()-1).getLocation());
			System.out.println("PEP2.get(p.getLocation()-1).getLocation()"+PEP2.get(p.getLocation()-1).getLocation());
			PEP2.set(p.getLocation()-1, PEP.get(p.getLocation()-1));
			PEP.set(p.getLocation()-1, p);
			System.out.println("while end");
			System.out.println("--------------------------");
		}
		System.out.println("-------------while terminal-------------");
		System.out.println("*********************************");
		System.out.println("PEP"+PEP);
		System.out.println("PEP2"+PEP2);
		System.out.println("*********************************");
		List<PysicalEmploeePo> PEP3 = new ArrayList<PysicalEmploeePo>();
		Iterator<PysicalEmploeePo> it1= PEP.iterator();
		Iterator<PysicalEmploeePo> it2= PEP2.iterator();
		for(int i=0;i<4;i++){
			System.out.println("--------------------------");
			System.out.println("round "+i);
			PysicalEmploeePo p = new PysicalEmploeePo();
			PysicalEmploeePo M1 = it1.next();
			if(M1.getQuater()<vo.getPysicalEmploee().getQuater()){
				//如果这里的代码被执行，则说明当前季度其实根本就没有记录
				//因此，当前季度的数据将完全由最近的一个季度复制而来，因此应当返回一个全零的列表
				for(int t=0;t<4;t++){
					PysicalEmploeePo qq = new PysicalEmploeePo();
					qq.setCompanyid(vo.getPysicalEmploee().getCompanyid());
					qq.setLocation(t+1);
					qq.setQuater(vo.getPysicalEmploee().getQuater());
					PEP3.add(qq);
				}
				return PEP3;
			}
			PysicalEmploeePo M2 = it2.next();
			p.setCompanyid(vo.getPysicalEmploee().getCompanyid());
			p.setLocation(i+1);
			System.out.println("Location : "+p.getLocation());
			System.out.println("it1.next().getSaller() : "+M1.getSaller());
			System.out.println("it2.next().getSaller() : "+M2.getSaller());
			p.setSaller(M1.getSaller()-M2.getSaller());
			p.setAftersale(M1.getAftersale()-M2.getAftersale());
			PEP3.add(p);
			System.out.println("round end");
			System.out.println("--------------------------");
			
		}
		return PEP3;
	}

	@Override
	public List<PysicalEmploeePo> getPopuChangesbyCompanyid(int companyid,int quater) {
		// TODO Auto-generated method stub

		PysicalEmploeeVo vo = new PysicalEmploeeVo();
		PysicalEmploeePo po = new PysicalEmploeePo();
		po.setCompanyid(companyid);
		po.setQuater(quater);
		vo.setPysicalEmploee(po);
		
		return getPopuChangesbyCompanyid(vo);
	}

	@Override
	public int[] HRcost(int companyid, int quater) {
		// TODO Auto-generated method stub
		List<PysicalEmploeePo> PEP = getPopuChangesbyCompanyid(companyid,quater);
		int HRcosts[] = new int[4];
		for(PysicalEmploeePo p:PEP){
			if(p.getSaller()+p.getAftersale()>0)
			    HRcosts[p.getLocation()-1] = 100*(p.getSaller()+p.getAftersale());
			else
			    HRcosts[p.getLocation()-1] = -150*(p.getSaller()+p.getAftersale());
		}
		return HRcosts;
	}

	@Override
	public Integer salary(int companyid, int quater) {
		// TODO Auto-generated method stub
		/**
		 * 注意：
		 * 这里下面这行代码返回了一个List，但实际上这个查询应该恒只有一个结果才对
		 * 因此，这个返回List的设计可能是项目初期设计失误的结果
		 * 在这里无论这个List里有多少个个体，我只读取第一个
		 */
		List<SalesSalary> SSList = CM.selectCompanySalesSalary(companyid, quater);
		System.out.println("SSList : "+SSList);
		SalesSalary SS = new SalesSalary();
		if(!SSList.isEmpty())
			SS = SSList.get(0);
		
		//获取员工总人数
		Integer PEP = getPopulationbyCompanyid(companyid,quater);
		
		Integer sal = SS.getSalary()*PEP;
		System.out.println("SS.getSalary() : "+SS.getSalary());
		System.out.println("PEPm.getSaller() : "+PEP);
		
		return sal;
	}

}
