package com.znb.cms.model.mapper;

import java.util.Date;

import com.znb.cms.common.BaseCommonQuery;


/**
 * 
 * @author yx
 *	申请理赔信息
 */
public class Compensation extends BaseCommonQuery {
    private Integer id;
	private String injuredName;		//出险人姓名
	private String injuredCard;		//出险人身份证
	private String entryTime;		//入职时间
	private Integer salary;			//薪水
	private String accidentTime;	//出险时间
	private String accidentAddress;	//出险地点
	private String injuredPosition;	//受伤部位
	private String injuredDegree;	//爱伤程度
	private String accidentDetail;	//受伤/治疗情况
	private String linkName;		//联系人
	private String linkPhone;		//联系人电话
	private String linkEmail;		//联系人邮箱
	private Date createTime;		//申请时间

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInjuredName() {
        return injuredName;
    }

    public void setInjuredName(String injuredName) {
        this.injuredName = injuredName == "" ? null : injuredName.trim();
    }

    public String getInjuredCard() {
        return injuredCard;
    }

    public void setInjuredCard(String injuredCard) {
        this.injuredCard = injuredCard  == "" ? null : injuredCard.trim();
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime == null ? null : entryTime.trim();
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getAccidentTime() {
        return accidentTime;
    }

    public void setAccidentTime(String accidentTime) {
        this.accidentTime = accidentTime == "" ? null : accidentTime.trim();
    }

    public String getAccidentAddress() {
        return accidentAddress;
    }

    public void setAccidentAddress(String accidentAddress) {
        this.accidentAddress = accidentAddress == "" ? null : accidentAddress.trim();
    }

    public String getInjuredPosition() {
        return injuredPosition;
    }

    public void setInjuredPosition(String injuredPosition) {
        this.injuredPosition = injuredPosition == null ? null : injuredPosition.trim();
    }

    public String getInjuredDegree() {
        return injuredDegree;
    }

    public void setInjuredDegree(String injuredDegree) {
        this.injuredDegree = injuredDegree == null ? null : injuredDegree.trim();
    }

    public String getAccidentDetail() {
        return accidentDetail;
    }

    public void setAccidentDetail(String accidentDetail) {
        this.accidentDetail = accidentDetail == null ? null : accidentDetail.trim();
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName == "" ? null : linkName.trim();
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone == "" ? null : linkPhone.trim();
    }

    public String getLinkEmail() {
        return linkEmail;
    }

    public void setLinkEmail(String linkEmail) {
        this.linkEmail = linkEmail == null ? null : linkEmail.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}