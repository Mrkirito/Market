package com.hangjia.bxj.newperson.query;

import com.hangjia.bxj.common.BaseCommonQuery;

/**
 * 新人通学习类别 后台查询
 *
 * @ClassName:
 * @Description:
 * @author: bei.zhang
 * @date: 2016年11月25日 上午11:40:38
 */

public class NewPersonCourseTypeQuery extends BaseCommonQuery {

    private static final long serialVersionUID = -7468621596343710116L;

    private Long courseStageId;

    private String name;

    private Integer enableStatus;

    public Long getCourseStageId() {
        return courseStageId;
    }

    public void setCourseStageId(Long courseStageId) {
        this.courseStageId = courseStageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(Integer enableStatus) {
        this.enableStatus = enableStatus;
    }
}
