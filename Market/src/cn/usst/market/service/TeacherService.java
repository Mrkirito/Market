package cn.usst.market.service;

import java.util.List;

import cn.usst.market.po.BalanceScore;
import cn.usst.market.po.BalanceScoreAvgData;
import cn.usst.market.po.BalanceScoreMaxData;
import cn.usst.market.po.BalanceScoreMinData;
import cn.usst.market.po.BalanceScoreVo;
import cn.usst.market.po.Company;
import cn.usst.market.po.CompanyCapacity;
import cn.usst.market.po.CompanyMarket;
import cn.usst.market.po.CompanyProduct;
import cn.usst.market.po.CompanyRule;
import cn.usst.market.po.CompanyRuleInfo;
import cn.usst.market.po.CompanyStock;
import cn.usst.market.po.CompanyStrategy;
import cn.usst.market.po.Competition;
import cn.usst.market.po.CompetitionQuarterTime;
import cn.usst.market.po.FixedDeposit;
import cn.usst.market.po.HelpDocument;
import cn.usst.market.po.IdQuarter;
import cn.usst.market.po.MarketInfo;
import cn.usst.market.po.Member;
import cn.usst.market.po.MemberDutyCustom;
import cn.usst.market.po.PersonalGoal;
import cn.usst.market.po.PersonalGoalInfo;
import cn.usst.market.po.ProductInfo;
import cn.usst.market.po.StrategyInfo;
import cn.usst.market.po.StudentTextbook;
import cn.usst.market.po.Teacher;
import cn.usst.market.po.TeacherQueryVo;
import cn.usst.market.po.TeacherTextbook;

public interface TeacherService {

	public Teacher doTeacherLogin(Teacher record)throws Exception;
	
	void updateByPrimaryKeySelective(Teacher record);
	
	void insert(Teacher record);
	
	
	//资料上传下载
	
	public void insertTeacherTextbook(TeacherTextbook record);

	public void deleteTextbookByPrimaryKey(Integer id);

	public List<TeacherTextbook> selectTextbookByCompetitionId(Integer id);

	public List<TeacherTextbook> selectTextbookListByTeacherId(Integer id);
	
	public void insertStudentTextbook(StudentTextbook record);
	
	public TeacherTextbook selectTeacherTextbookById(Integer id);

	public void deleteStudentTextbookByTeacherTextbookId(Integer id);
	
	
	//教员工具箱2017.7.1
	public List<TeacherQueryVo> findCompanyListByCompetitionId(Integer id) throws Exception;

	public void updateCompanyPeopleNumberByCompetitionId(Company company);

	public void updateCompetitionMemberByPrimaryKey(Competition competition);

	public void updateCompetitionNameByPrimaryKey(Competition competition);

	public List<TeacherQueryVo> findCompanyAndMemberListByCompetitionId(Integer id) throws Exception;

	public void deleteMemberById(Integer id) throws Exception;

	public void moveMemberToCompanyById(Member member) throws Exception;

	public void updateCompetitionIsLockByPrimaryKey(Competition competition);

	public List<CompetitionQuarterTime> findQuarterTimeByCompetitionId(Integer id);

	public void updateAllowSubmitByCompetitionId(CompetitionQuarterTime competitionQuarterTime);
	
	//接下来是决策分析
	public List<MemberDutyCustom> findMemberDutyList(Integer id);

	public CompanyStrategy findCompanyGoalAndPolicy(IdQuarter idQuarter);

	public StrategyInfo findStrategyById(Integer id);

	public CompanyRule findCompanyRule(IdQuarter idQuarter);

	public CompanyRuleInfo findRuleInfoById(Integer id);

	public PersonalGoal findPersonalGoalByMemberId(Integer id);

	public PersonalGoalInfo findPersonalGoalInfoById(Integer id);

	public ProductInfo findProductInfoById(Integer id);


	public CompanyMarket findCompanyPhyMarketByIdQuarter(IdQuarter idQuarter);

	public CompanyMarket findCompanyNetMarketByIdQuarter(IdQuarter idQuarter);

	public MarketInfo findMarketInfoById(Integer id);

	//public int findAddCapacityByIdQuarter(IdQuarter idQuarter);

	//public int findTotalCapacityByIdQuarter(IdQuarter idQuarter);

	public CompanyCapacity findCompanyCapacityByIdQuarter(IdQuarter idQuarter);

	public List<CompanyStock> findCompanyStockByIdQuarter(IdQuarter idQuarter);

	public FixedDeposit findCompanyFixedDepositByIdQuarter(IdQuarter idQuarter);
	
	
	//20170711--帮助文档
	public int getTitleLevelOneCount();
	public List<HelpDocument> selectAllHelpDocument();
	public HelpDocument selectHelpDocument(HelpDocument helpDocument);
	
	


}
