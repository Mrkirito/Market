package com.hangjia.bxj.query.activity;

import com.hangjia.bxj.common.BaseCommonQuery;

import java.util.Date;

/**
 * 教师节活动 后台查询
 *
 * @ClassName:
 * @Description:
 * @author: bei.zhang
 * @date: 2016年8月29日 上午11:40:38
 */

public class TeachersDayStatQuery extends BaseCommonQuery {

    private static final long serialVersionUID = -7468621596343710116L;
    private String phone;

    private String oweName;

    private String signedName;

    private Integer oweType;

    private Integer type;

    private Date statDate;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOweName() {
        return oweName;
    }

    public void setOweName(String oweName) {
        this.oweName = oweName;
    }

    public String getSignedName() {
        return signedName;
    }

    public void setSignedName(String signedName) {
        this.signedName = signedName;
    }

    public Integer getOweType() {
        return oweType;
    }

    public void setOweType(Integer oweType) {
        this.oweType = oweType;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getStatDate() {
        return statDate;
    }

    public void setStatDate(Date statDate) {
        this.statDate = statDate;
    }
}
