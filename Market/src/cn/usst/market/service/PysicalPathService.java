package cn.usst.market.service;

import java.util.List;

import cn.usst.market.po.PysicalEmploeePo;
import cn.usst.market.po.PysicalEmploeeVo;

public interface PysicalPathService {
	
	public List<PysicalEmploeePo> findPhysicalStoreEmploeeListByCompanyid(PysicalEmploeeVo vo);

	public void updateCompanyEmploeeInfo(PysicalEmploeeVo vo);
	
	public void insertCompanyEmploeeInfo(PysicalEmploeeVo vo);
	
	public void deleteCompanyEmploeeInfo(PysicalEmploeeVo vo);
	
	/**
	 * getPopulationbyCompanyid
	 * 传入公司ID，获取该公司当前季度的销售人员人数和售后人数
	 * 公司ID要封装在PysicalEmploeeVo对象中
	 * 返回数据将被封装在PysicalEmploeePo对象中
	 * @param PysicalEmploeeVo vo
	 * @return PysicalEmploeePo
	 */
	public Integer getPopulationbyCompanyid(PysicalEmploeeVo vo);

	/**
	 * getPopulationbyCompanyid
	 * 传入公司ID，获取该公司当前季度的销售人员人数和售后人数
	 * 公司ID可以直接以int型数据传入
	 * 返回数据将被封装在PysicalEmploeePo对象中
	 * @param int companyid
	 * @return PysicalEmploeePo
	 */
	public Integer getPopulationbyCompanyid(int companyid,int quater);
	
	/**
	 * getPopuChangesbyCompanyid
	 * 根据公司ID获取公司当前季度人员变动情况
	 * 公司ID要封装在PysicalEmploeeVo对象中
	 * 返回数据将被封装在PysicalEmploeePo对象中
	 * @param PysicalEmploeeVo vo
	 * @return PysicalEmploeePo
	 */
	public List<PysicalEmploeePo> getPopuChangesbyCompanyid(PysicalEmploeeVo vo);
	
	/**
	 * getPopuChangesbyCompanyid
	 * 根据公司ID获取公司当前季度人员变动情况
	 * 公司ID可以直接以int型数据传入
	 * 返回数据将被封装在PysicalEmploeePo对象中
	 * @param int companyid
	 * @return PysicalEmploeePo
	 */
	public List<PysicalEmploeePo> getPopuChangesbyCompanyid(int companyid,int quater);
	
	public int[] HRcost(int companyid,int quater);
	
	public Integer salary(int companyid,int quater);
}
