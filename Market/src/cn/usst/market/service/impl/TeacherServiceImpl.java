package cn.usst.market.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.usst.market.mapper.BalanceScoreMapper;
import cn.usst.market.mapper.HelpDocumentMapper;
import cn.usst.market.mapper.StudentTextbookMapper;
import cn.usst.market.mapper.TeacherMapper;
import cn.usst.market.mapper.TeacherPolicyDecisionMapper;
import cn.usst.market.mapper.TeacherTextbookMapper;
import cn.usst.market.mapper.TeacherToolMapper;
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
import cn.usst.market.service.TeacherService;

@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {
	@Autowired
	private TeacherMapper teacherMapper;
	
	@Autowired
	private TeacherTextbookMapper teacherTextbookMapper;

	@Autowired
	private StudentTextbookMapper studentTextbookMapper;
	
	@Autowired
	private TeacherToolMapper teacherToolMapper;
	
	@Autowired
	private TeacherPolicyDecisionMapper teacherPolicyDecisionMapper;
	
	@Autowired
	private HelpDocumentMapper helpDocumentMapper;

	@Autowired
	private BalanceScoreMapper balanceScoreMapper;

	@Override
	public Teacher doTeacherLogin(Teacher record) throws Exception {
		
		return teacherMapper.doTeacherLogin(record);
	}

	@Override
	public void updateByPrimaryKeySelective(Teacher record) {
		teacherMapper.updateByPrimaryKeySelective(record);
		
	}

	@Override
	public void insert(Teacher record) {
		teacherMapper.insert(record);
	}

	//教师资料上传下载分享
	@Override
	public void insertTeacherTextbook(TeacherTextbook record){
		 teacherTextbookMapper.insertTeacherTextbook(record);
	}
	
	@Override
	public void deleteTextbookByPrimaryKey(Integer id){
		teacherTextbookMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public List<TeacherTextbook> selectTextbookByCompetitionId(Integer id){
		return teacherTextbookMapper.selectByCompetitionId(id);
	}
	
	@Override
	public List<TeacherTextbook> selectTextbookListByTeacherId(Integer id){
		return teacherTextbookMapper.selectByTeacherId(id);
	}
	
	@Override
	public TeacherTextbook selectTeacherTextbookById(Integer id){
		return teacherTextbookMapper.selectById(id);
	}
	
	@Override
	public void insertStudentTextbook(StudentTextbook record){
		studentTextbookMapper.insert(record);
	}
	
	@Override
	public void deleteStudentTextbookByTeacherTextbookId(Integer id){
		studentTextbookMapper.deleteByTeacherTextbookId(id);
	}
	
	//教员工具箱中
	//更新竞赛名称
	@Override
	public void updateCompetitionNameByPrimaryKey(Competition competition){
		teacherToolMapper.updateCompetitionNameByPrimaryKey(competition);
	}
	
	//更新竞赛isLock属性
	@Override
	public void updateCompetitionIsLockByPrimaryKey(Competition competition){
		teacherToolMapper.updateCompetitionIsLockByPrimaryKey(competition);
	}
	
	//竞赛季度时间
	@Override
	public List<CompetitionQuarterTime> findQuarterTimeByCompetitionId(Integer id){
		return teacherToolMapper.findQuarterTimeByCompetitionId(id);
	}
	//设置竞赛是否可以过期提交
	@Override
	public void updateAllowSubmitByCompetitionId(CompetitionQuarterTime competitionQuarterTime){
		teacherToolMapper.updateAllowSubmitByCompetitionId(competitionQuarterTime);
	}
	
	@Override
	public List<TeacherQueryVo> findCompanyListByCompetitionId(Integer id) throws Exception{
		return teacherToolMapper.findCompanyListByCompetitionId(id);
	}
	
	@Override
	public void updateCompanyPeopleNumberByCompetitionId(Company company){
		teacherToolMapper.updateCompanyPeopleNumberByCompetitionId(company);
	}
	
	@Override
	public void updateCompetitionMemberByPrimaryKey(Competition competition){
		teacherToolMapper.updateCompetitionMemberByPrimaryKey(competition);
	}
	@Override
	public List<TeacherQueryVo> findCompanyAndMemberListByCompetitionId(Integer id)throws Exception{
		return teacherToolMapper.findCompanyAndMemberListByCompetitionId(id);
	}
	
	@Override
	public void deleteMemberById(Integer id)throws Exception{
		teacherToolMapper.deleteMemberById(id);
	}
	
	@Override
	public void moveMemberToCompanyById(Member member)throws Exception{
		teacherToolMapper.moveMemberToCompanyById(member);
	}
	
	//====================================
	//接下来是决策分析
	@Override
	public List<MemberDutyCustom> findMemberDutyList(Integer id){
		return teacherPolicyDecisionMapper.findMemberDutyList(id);
	}
	@Override
	public CompanyStrategy findCompanyGoalAndPolicy(IdQuarter idQuarter){
		return teacherPolicyDecisionMapper.findCompanyGoalAndPolicy(idQuarter);
	}
	@Override
	public StrategyInfo findStrategyById(Integer id){
		return teacherPolicyDecisionMapper.findStrategyById(id);
	}
	//查找团队规则
	@Override
	public CompanyRule findCompanyRule(IdQuarter idQuarter){
		return teacherPolicyDecisionMapper.findCompanyRule(idQuarter);
	}
	//查找根据ID查找规则信息
	@Override
	public CompanyRuleInfo findRuleInfoById(Integer id){
		return teacherPolicyDecisionMapper.findRuleInfoById(id);
	}
	//查找个人目标
	@Override
	public PersonalGoal findPersonalGoalByMemberId(Integer id){
		return teacherPolicyDecisionMapper.findPersonalGoalByMemberId(id);
	}
	@Override
	public PersonalGoalInfo findPersonalGoalInfoById(Integer id){
		return teacherPolicyDecisionMapper.findPersonalGoalInfoById(id);
	}
	
	//查找产品信息
	@Override
	public ProductInfo findProductInfoById(Integer id){
		return teacherPolicyDecisionMapper.findProductInfoById(id);
	}
	//找开始实体市场
	@Override
	public CompanyMarket findCompanyPhyMarketByIdQuarter(IdQuarter idQuarter){
		return teacherPolicyDecisionMapper.findCompanyPhyMarketByIdQuarter(idQuarter);
	}
	//找开设网络市场
	@Override
	public CompanyMarket findCompanyNetMarketByIdQuarter(IdQuarter idQuarter){
		return teacherPolicyDecisionMapper.findCompanyNetMarketByIdQuarter(idQuarter);
	}
	
	//找市场具体信息
	@Override
	public MarketInfo findMarketInfoById(Integer id){
		return teacherPolicyDecisionMapper.findMarketInfoById(id);
	}
	
	//找季度公司的产能
	//查找公司产能
	@Override
	public CompanyCapacity findCompanyCapacityByIdQuarter(IdQuarter idQuarter){
		return teacherPolicyDecisionMapper.findCompanyCapacityByIdQuarter(idQuarter);
	}
	
	//公司持股
	@Override
	public List<CompanyStock> findCompanyStockByIdQuarter(IdQuarter idQuarter){
		return teacherPolicyDecisionMapper.findCompanyStockByIdQuarter(idQuarter);
	}
	
	//公司定期存款
	public FixedDeposit findCompanyFixedDepositByIdQuarter(IdQuarter idQuarter){
		return teacherPolicyDecisionMapper.findCompanyFixedDepositByIdQuarter(idQuarter);
	}
	//帮助文档
	@Override
	public int getTitleLevelOneCount() {
		// TODO Auto-generated method stub
		return helpDocumentMapper.getTitleLevelOneCount();
	}

	@Override
	public HelpDocument selectHelpDocument(HelpDocument helpDocument) {
		// TODO Auto-generated method stub
		return helpDocumentMapper.selectHelpDocument(helpDocument);
	}
	
	@Override
	public List<HelpDocument> selectAllHelpDocument() {
		// TODO Auto-generated method stub
		return helpDocumentMapper.selectAllHelpDocument();
	}
	
	
	
	
}
