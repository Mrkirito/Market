package com.hangjia.bxj.newperson.query;

import com.hangjia.bxj.common.BaseCommonQuery;

/**
 * Created by Administrator on 2016/10/27.
 */
public class NewPersonCourseQuestionQuery extends BaseCommonQuery {
    private Long courseId;
    private String questionTitle;

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
        this.questionTitle = questionTitle;
    }
}
