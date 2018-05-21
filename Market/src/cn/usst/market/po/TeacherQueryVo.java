package cn.usst.market.po;

import java.util.List;

public class TeacherQueryVo {
	
	private Competition competition;
	private Company company;
	private Member member;
	private Teacher teacher;
	private CompanyQuarterTime companyQuarterTime;
	
	public CompanyQuarterTime getCompanyQuarterTime() {
		return companyQuarterTime;
	}
	public void setCompanyQuarterTime(CompanyQuarterTime companyQuarterTime) {
		this.companyQuarterTime = companyQuarterTime;
	}

	private int startPos;
	private int pageSize;
	
	public int getStartPos() {
		return startPos;
	}
	public void setStartPos(int startPos) {
		this.startPos = startPos;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	private int memberCount;
	
	//接受批量公司信息
	private List<Company> companyList;
	//接受批量成员信息
	private List<Member> memberList;
	
	public List<Member> getMemberList() {
		return memberList;
	}
	public void setMemberList(List<Member> memberList) {
		this.memberList = memberList;
	}
	public List<Company> getCompanyList() {
		return companyList;
	}
	public void setCompanyList(List<Company> companyList) {
		this.companyList = companyList;
	}
	public int getMemberCount() {
		return memberCount;
	}
	public void setMemberCount(int memberCount) {
		this.memberCount = memberCount;
	}
	public Competition getCompetition() {
		return competition;
	}
	public void setCompetition(Competition competition) {
		this.competition = competition;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
}
