package cn.usst.market.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.usst.market.po.CompanyQuarterTime;
import cn.usst.market.po.Competition;
import cn.usst.market.po.DemandInfo;
import cn.usst.market.po.MarketInfoWeight;
import cn.usst.market.po.TeacherQueryVo;
import cn.usst.market.po.TeacherReference;

public interface CompetitionService {
	
	int insert(Competition record);
	
	List<Competition> findAllCompetition()throws Exception;
	
	List<Competition> findCompetitionByTeacherId(Integer id)throws Exception;
	
	Competition findCompetitionById(Integer id);
	
	Competition findCompetitionByLicense(Competition record);
	
	Competition checkCompetitionExist(String name);
	
	public List<DemandInfo> showDemandInfo();
	
	public List<Competition> findCompetitionList(TeacherQueryVo teacherQueryVo)throws Exception;
	
	public List<TeacherReference> findTeacherReference() throws Exception;
	
	
	public List<Competition> selectCompetitionByPage(TeacherQueryVo teacherQueryVo);
    
    public long getCompetitionCount(TeacherQueryVo teacherQueryVo);
    
    
    public void updateCompetitionInfo(Competition record);
    
    public Competition findCompetitionByCompanyId(Integer companyId);

	int getCurrentQuarterByCompanyId(int companyId);

	List<MarketInfoWeight> selectMarketInfoWeight();

	void insertMarketInfo(Integer competitionId, String name, int perfect, int business, int practical,
			int web_perfect,int web_business,int web_practical,int rent,int open,int web_rent,int web_open, String img);


	public void insertMarketOpened(Integer companyId);

	void updateCompanySubmit(CompanyQuarterTime companyQuarterTime);

	CompanyQuarterTime findCompanyQuarterTime(int companyId, int quarter);

	CompanyQuarterTime findOneByCompetitionIdQuarter(int companyId, int currentQuarter);
    
}
