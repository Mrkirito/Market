package com.hangjia.bxj.newperson.query;

import com.hangjia.bxj.common.BaseCommonQuery;

/**
 * 新人通学习日志 后台查询
 *
 * @ClassName:
 * @Description:
 * @author: bei.zhang
 * @date: 2016年11月25日 上午11:40:38
 */

public class NewPersonCourseAccessQuery extends BaseCommonQuery {

    private static final long serialVersionUID = -7468621596343710116L;

    private String phone;
    private String userName;
    private Integer type;
    private Long typeId;
    private Long stageId;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getStageId() {
        return stageId;
    }

    public void setStageId(Long stageId) {
        this.stageId = stageId;
    }
}
