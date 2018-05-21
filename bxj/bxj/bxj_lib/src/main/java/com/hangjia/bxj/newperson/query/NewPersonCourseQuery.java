package com.hangjia.bxj.newperson.query;

import com.hangjia.bxj.common.BaseCommonQuery;

/**
 * Created by Administrator on 2016/10/27.
 */
public class NewPersonCourseQuery extends BaseCommonQuery {
    private Long courseTypeId;
    private String courseTitle;

    public Long getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(Long courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }
}
