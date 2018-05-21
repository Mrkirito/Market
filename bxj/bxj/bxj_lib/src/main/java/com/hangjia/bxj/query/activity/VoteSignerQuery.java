package com.hangjia.bxj.query.activity;

import com.hangjia.bxj.common.BaseCommonQuery;

import java.util.Date;

/**
 * 投票评选报名用户
 *
 * @ClassName:
 * @Description:
 * @author: bei.zhang
 * @date: 2016年9月9日 上午11:40:38
 */

public class VoteSignerQuery extends BaseCommonQuery {

    private static final long serialVersionUID = -7468621596343710116L;

    private String realname;
    private String mobile;
    private Date signDate;
    private Date authDate;
    private Integer step;
    private Integer signType;
    private Integer status;

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public Date getAuthDate() {
        return authDate;
    }

    public void setAuthDate(Date authDate) {
        this.authDate = authDate;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public Integer getSignType() {
        return signType;
    }

    public void setSignType(Integer signType) {
        this.signType = signType;
    }

    @Override
    public Integer getStatus() {
        return status;
    }

    @Override
    public void setStatus(Integer status) {
        this.status = status;
    }
}
