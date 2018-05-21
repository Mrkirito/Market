package cn.usst.market.mapper;

import java.util.List;

import cn.usst.market.po.PysicalEmploeePo;
import cn.usst.market.po.PysicalEmploeeVo;

public interface PysicalPathMapper {
	
	/**
	 * 通过公司id查询员工配置信息
	 * 公司id需要放在PysicalEmploeeVo类型的对象中
	 * 返回值为各个实体销售中心的人员配置情况
	 * 返回的列表最多有四项
	 * @param PysicalEmploeeVo
	 * @return List<PysicalEmploeePo>
	 */
	public List<PysicalEmploeePo> findPhysicalStoreEmploeeListByCompanyid(PysicalEmploeeVo vo);

	public Integer findhiredpeople(PysicalEmploeeVo vo);
	
	public Integer findhiredpeopleonline(PysicalEmploeeVo vo);
	/**
	 * 根据传入的Vo值改写对应公司在对应市场的人员配置情况
	 * @param PysicalEmploeeVo
	 */
	public void updateCompanyEmploeeInfo(PysicalEmploeeVo vo);
	
	public void insertCompanyEmploeeInfo(PysicalEmploeeVo vo);
	
	public void deleteCompanyEmploeeInfo(PysicalEmploeeVo vo);
}
