package cn.usst.market.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.usst.market.mapper.MemberMapper;
import cn.usst.market.po.Member;
import cn.usst.market.po.TeacherQueryVo;
import cn.usst.market.service.MemberService;
@Service("memberService")
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberMapper memberMapper;
	@Override
	public Member doMemberLogin(Member record) throws Exception {
		
		return memberMapper.doMemberLogin(record);
	}
	@Override
	public void insert(Member record) {
		memberMapper.insert(record);
	}
	@Override
	public Member findMeberByEmail(Member record) {
		
		return memberMapper.findMeberByEmail(record);
	}
	@Override
	public List<Member> showAllMemberByComapnyId(Integer companyId) {
		// TODO Auto-generated method stub
		return memberMapper.showAllMemberByComapnyId(companyId);
	}
	@Override
	public List<TeacherQueryVo> findMemberList(TeacherQueryVo teacherQueryVo) throws Exception {
		// TODO Auto-generated method stub
		return memberMapper.findMemberList(teacherQueryVo);
	}
	@Override
	public List<TeacherQueryVo> selectMemberByPage(TeacherQueryVo teacherQueryVo) {
		// TODO Auto-generated method stub
		return memberMapper.selectMemberByPage(teacherQueryVo);
	}
	@Override
	public long getMemberCount(TeacherQueryVo teacherQueryVo) {
		// TODO Auto-generated method stub
		return memberMapper.getMemberCount(teacherQueryVo);
	}

}
