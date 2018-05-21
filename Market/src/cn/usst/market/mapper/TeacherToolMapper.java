package cn.usst.market.mapper;

import java.util.List;

import cn.usst.market.po.Company;
import cn.usst.market.po.Competition;
import cn.usst.market.po.CompetitionQuarterTime;
import cn.usst.market.po.Member;
import cn.usst.market.po.TeacherQueryVo;


public interface TeacherToolMapper {
	public void updateCompetitionNameByPrimaryKey(Competition competition);
	//public List<TeacherReference> findTeacherReference() throws Exception;
	public List<TeacherQueryVo> findCompanyListByCompetitionId(Integer id) throws Exception;

	public void updateCompetitionMemberByPrimaryKey(Competition competition);
	
	public void updateCompetitionIsLockByPrimaryKey(Competition competition);
	//查找季度时间	
	public List<CompetitionQuarterTime> findQuarterTimeByCompetitionId(Integer id);
	//更新是否可以过期提交
	public void updateAllowSubmitByCompetitionId(CompetitionQuarterTime competitionQuarterTime);
	//更新公司人数
	public void updateCompanyPeopleNumberByCompetitionId(Company company);
	//根据竞赛ID查找公司和对应的成员
	public List<TeacherQueryVo> findCompanyAndMemberListByCompetitionId(Integer id)throws Exception;
	//删除成员
	public void deleteMemberById(Integer id)throws Exception;
	//移动成员
	public void moveMemberToCompanyById(Member member)throws Exception;
}
