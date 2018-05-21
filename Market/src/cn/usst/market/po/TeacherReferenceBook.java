package cn.usst.market.po;

public class TeacherReferenceBook {
    private Integer id;

    private String bookName;

    private Integer teacherReferenceId;

    private String path;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName == null ? null : bookName.trim();
    }

    public Integer getTeacherReferenceId() {
        return teacherReferenceId;
    }

    public void setTeacherReferenceId(Integer teacherReferenceId) {
        this.teacherReferenceId = teacherReferenceId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }
}