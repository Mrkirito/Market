package cn.usst.market.po;

public class StudentTextbook {
    private Integer id;

    private Integer studentId;

    private Integer teacherTextbookId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getTeacherTextbookId() {
        return teacherTextbookId;
    }

    public void setTeacherTextbookId(Integer teacherTextbookId) {
        this.teacherTextbookId = teacherTextbookId;
    }
}