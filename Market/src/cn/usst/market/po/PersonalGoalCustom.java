package cn.usst.market.po;

import java.util.List;

public class PersonalGoalCustom {
	private Member member;
	
	private List<PersonalGoalInfo> personalGoal;

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public List<PersonalGoalInfo> getPersonalGoal() {
		return personalGoal;
	}

	public void setPersonalGoal(List<PersonalGoalInfo> personalGoal) {
		this.personalGoal = personalGoal;
	}
}
