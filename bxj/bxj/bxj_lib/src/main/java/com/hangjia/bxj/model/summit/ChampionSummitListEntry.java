package com.hangjia.bxj.model.summit;

public class ChampionSummitListEntry extends ChampionSummitEntity {

	/**
	 * 讲师名称。多个之间使用（、）分开
	 */
	private String teacherNames;
	
	public String getTeacherNames() {
		return teacherNames;
	}

	public void setTeacherNames(String teacherNames) {
		this.teacherNames = teacherNames;
	}

	private static final long serialVersionUID = 5109041448802026466L;

}
