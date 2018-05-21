package cn.usst.market.service;

import java.util.List;

import cn.usst.market.po.Member;
import cn.usst.market.po.TeacherQueryVo;

public interface MemberService {
	public Member doMemberLogin(Member record)throws Exception;
	
	void insert(Member record);
	
	public Member findMeberByEmail(Member record);
	
	List<Member> showAllMemberByComapnyId(Integer companyId);
	
	public List<TeacherQueryVo> findMemberList(TeacherQueryVo teacherQueryVo)throws Exception;
	
	public List<TeacherQueryVo> selectMemberByPage(TeacherQueryVo teacherQueryVo);
    
    public long getMemberCount(TeacherQueryVo teacherQueryVo);
}
