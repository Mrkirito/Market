package cn.usst.market.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.usst.market.mapper.CompetitionMapper;
import cn.usst.market.mapper.DemandInfoMapper;
import cn.usst.market.mapper.TeacherReferenceMapper;
import cn.usst.market.po.CompanyQuarterTime;
import cn.usst.market.po.Competition;
import cn.usst.market.po.DemandInfo;
import cn.usst.market.po.MarketInfoWeight;
import cn.usst.market.po.TeacherQueryVo;
import cn.usst.market.po.TeacherReference;
import cn.usst.market.service.CompetitionService;
import marketUtil.StringRandom;
@Service("competitionService")
public class CompetitionServiceImpl implements CompetitionService {
	@Autowired
	private CompetitionMapper competitionMapper;
	
	@Autowired
	private DemandInfoMapper demandInfoMapper;
	
	@Autowired
	private TeacherReferenceMapper teacherReferenceMapper;

	@Override
	public int insert(Competition record) {
		/*StringRandom sr=new StringRandom();
		String license=sr.getStringRandom(12);
		record.setLicense(license);*/
		return competitionMapper.insert(record);
	}

	@Override
	public List<Competition> findAllCompetition() throws Exception {
		return competitionMapper.findAllCompetition();
	}

	@Override
	public Competition findCompetitionById(Integer id) {
		return competitionMapper.findCompetitionById(id);
	}
	
	@Override
	public List<Competition> findCompetitionByTeacherId(Integer id) throws Exception {
		return competitionMapper.findCompetitionByTeacherId(id);
	}

	@Override
	public Competition findCompetitionByLicense(Competition record) {
		
		return competitionMapper.findCompetitonByLicense(record);
	}

	@Override
	public List<DemandInfo> showDemandInfo() {
		return demandInfoMapper.showDemandInfo();
	}

	@Override
	public List<Competition> findCompetitionList(TeacherQueryVo teacherQueryVo) throws Exception {
		
		return competitionMapper.findCompetitionList(teacherQueryVo);
	}

	@Override
	public List<TeacherReference> findTeacherReference() throws Exception {
		// TODO Auto-generated method stub
		return teacherReferenceMapper.findTeacherReference();
	}


	@Override
	public long getCompetitionCount(TeacherQueryVo teacherQueryVo) {
		
		return competitionMapper.getCompetitionCount(teacherQueryVo);
	}

	@Override
	public List<Competition> selectCompetitionByPage(TeacherQueryVo teacherQueryVo) {
		// TODO Auto-generated method stub
		return competitionMapper.selectCompetitionByPage(teacherQueryVo);
	}

	@Override
	public Competition checkCompetitionExist(String name) {
		// TODO Auto-generated method stub
		return competitionMapper.checkCompetitionExist(name);
	}

	@Override
	public void updateCompetitionInfo(Competition record) {
		competitionMapper.updateCompetitionInfo(record);
	}
	
	@Override
	public Competition findCompetitionByCompanyId(Integer companyId){
		return competitionMapper.findCompetitionByCompanyId(companyId);
	}

	@Override
	public int getCurrentQuarterByCompanyId(int companyId) {
		return competitionMapper.getCurrentQuarterByCompanyId(companyId);
	}

	@Override
	public List<MarketInfoWeight> selectMarketInfoWeight() {
		// TODO Auto-generated method stub
		return competitionMapper.selectMarketInfoWeight();
	}

	@Override
	public void insertMarketInfo(Integer competitionId, String name, int perfect, int business, int practical,
			int web_perfect, int web_business, int web_practical, int rent, int open, int web_rent, int web_open,
			String img) {
		competitionMapper.insertMarketInfo(competitionId,name,perfect,business,practical,
				 web_perfect,web_business,web_practical,rent,open,web_rent,web_open,img);
		
	}

	@Override
	public void insertMarketOpened(Integer companyId){
		competitionMapper.insertMarketOpened(companyId);
	}
	
	@Override
	public void updateCompanySubmit(CompanyQuarterTime companyQuarterTime){
		competitionMapper.updateCompanySubmit(companyQuarterTime);
		
	}
	
	@Override
	public CompanyQuarterTime findCompanyQuarterTime(int companyId,int quarter){
		return competitionMapper.findCompanyQuarterTime(companyId, quarter);
	}

	@Override
	public CompanyQuarterTime findOneByCompetitionIdQuarter(int companyId, int currentQuarter) {
		// TODO Auto-generated method stub
		
		return competitionMapper.findOneByCompetitionIdQuarter(companyId,currentQuarter);
	}
	
}
