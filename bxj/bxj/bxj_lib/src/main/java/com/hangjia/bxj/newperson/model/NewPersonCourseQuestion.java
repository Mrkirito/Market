package com.hangjia.bxj.newperson.model;

import com.hangjia.bxj.model.BaseModel;

import java.io.Serializable;
import java.util.Date;

public class NewPersonCourseQuestion extends BaseModel {
    private Long id;

    private Long courseId;

    private String questionTitle;

    private Integer questionType;

    private String questionAnswer1;

    private String questionAnswer2;

    private String questionAnswer3;

    private String questionAnswer4;

    private String questionAnswer5;

    private String questionAnswer6;

    private String correctAnswer;

    private Integer sort;

    private Integer enableStatus;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle == null ? null : questionTitle.trim();
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    public String getQuestionAnswer1() {
        return questionAnswer1;
    }

    public void setQuestionAnswer1(String questionAnswer1) {
        this.questionAnswer1 = questionAnswer1 == null ? null : questionAnswer1.trim();
    }

    public String getQuestionAnswer2() {
        return questionAnswer2;
    }

    public void setQuestionAnswer2(String questionAnswer2) {
        this.questionAnswer2 = questionAnswer2 == null ? null : questionAnswer2.trim();
    }

    public String getQuestionAnswer3() {
        return questionAnswer3;
    }

    public void setQuestionAnswer3(String questionAnswer3) {
        this.questionAnswer3 = questionAnswer3 == null ? null : questionAnswer3.trim();
    }

    public String getQuestionAnswer4() {
        return questionAnswer4;
    }

    public void setQuestionAnswer4(String questionAnswer4) {
        this.questionAnswer4 = questionAnswer4 == null ? null : questionAnswer4.trim();
    }

    public String getQuestionAnswer5() {
        return questionAnswer5;
    }

    public void setQuestionAnswer5(String questionAnswer5) {
        this.questionAnswer5 = questionAnswer5 == null ? null : questionAnswer5.trim();
    }

    public String getQuestionAnswer6() {
        return questionAnswer6;
    }

    public void setQuestionAnswer6(String questionAnswer6) {
        this.questionAnswer6 = questionAnswer6 == null ? null : questionAnswer6.trim();
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer == null ? null : correctAnswer.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(Integer enableStatus) {
        this.enableStatus = enableStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", courseId=").append(courseId);
        sb.append(", questionTitle=").append(questionTitle);
        sb.append(", questionType=").append(questionType);
        sb.append(", questionAnswer1=").append(questionAnswer1);
        sb.append(", questionAnswer2=").append(questionAnswer2);
        sb.append(", questionAnswer3=").append(questionAnswer3);
        sb.append(", questionAnswer4=").append(questionAnswer4);
        sb.append(", questionAnswer5=").append(questionAnswer5);
        sb.append(", questionAnswer6=").append(questionAnswer6);
        sb.append(", correctAnswer=").append(correctAnswer);
        sb.append(", sort=").append(sort);
        sb.append(", enableStatus=").append(enableStatus);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**************************** extend begin here *******************************/
    private String courseTitle;

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }
}