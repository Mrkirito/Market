package cn.usst.market.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.usst.market.po.CompanyQuarterTime;
import cn.usst.market.po.Competition;
import cn.usst.market.po.MarketInfoWeight;
import cn.usst.market.po.TeacherQueryVo;
import cn.usst.market.po.UsageInfo;


public interface CompetitionMapper {

    int insert(Competition record);
    
    List<Competition> findAllCompetition()throws Exception;
    
    List<Competition> findCompetitionByTeacherId(Integer id)throws Exception;
    
    Competition findCompetitionById(Integer id);
    
    Competition findCompetitonByLicense(Competition record);
    
    public List<Competition> findCompetitionList(TeacherQueryVo teacherQueryVo)throws Exception;
    
    
    Competition checkCompetitionExist(String name);
    
    
    
    public List<Competition> selectCompetitionByPage(TeacherQueryVo teacherQueryVo);
    
    public long getCompetitionCount(TeacherQueryVo teacherQueryVo);

	void updateCompetitionInfo(Competition record);
	
	public Competition findCompetitionByCompanyId(Integer companyId);
	
	int getCurrentQuarterByCompanyId(Integer companyId);

	List<MarketInfoWeight> selectMarketInfoWeight();

	void insertMarketInfo(Integer competitionId, String name, int perfect, int business, int pratical, int web_perfect, int web_business, int web_practical, int rent, int open, int web_rent, int web_open, String img);
    
    void insertMarketOpened(Integer companyId);

    void updateCompanySubmit(CompanyQuarterTime companyQuarterTime);
    
    public CompanyQuarterTime findCompanyQuarterTime(int companyId,int quarter);

    CompanyQuarterTime findOneByCompetitionIdQuarter(int companyId, int currentQuarter);
}