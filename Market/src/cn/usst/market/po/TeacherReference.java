package cn.usst.market.po;

import java.util.List;

public class TeacherReference {
    private Integer id;

    private String referenceName;

    private String introduction;
    
    private List<TeacherReferenceBook> TeacherReferenceBookList;
   
    public List<TeacherReferenceBook> getTeacherReferenceBookList() {
		return TeacherReferenceBookList;
	}

	public void setTeacherReferenceBookList(List<TeacherReferenceBook> teacherReferenceBookList) {
		TeacherReferenceBookList = teacherReferenceBookList;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReferenceName() {
        return referenceName;
    }

    public void setReferenceName(String referenceName) {
        this.referenceName = referenceName == null ? null : referenceName.trim();
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }
}