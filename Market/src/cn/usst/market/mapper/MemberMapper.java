package cn.usst.market.mapper;

import java.util.List;

import cn.usst.market.po.Competition;
import cn.usst.market.po.Member;
import cn.usst.market.po.TeacherQueryVo;


public interface MemberMapper {

	public Member doMemberLogin(Member record)throws Exception;
	
    void deleteByPrimaryKey(Integer id);

    void insert(Member record);

    Member selectByPrimaryKey(Integer id);
    
    public Member findMeberByEmail(Member record);
    
    List<Member> showAllMemberByComapnyId(Integer companyId);
    
    public List<TeacherQueryVo> findMemberList(TeacherQueryVo teacherQueryVo)throws Exception;
    
    public List<TeacherQueryVo> selectMemberByPage(TeacherQueryVo teacherQueryVo);
    
    public long getMemberCount(TeacherQueryVo teacherQueryVo);

}