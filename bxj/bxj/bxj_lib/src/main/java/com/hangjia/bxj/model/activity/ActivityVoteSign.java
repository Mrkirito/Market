package com.hangjia.bxj.model.activity;

import com.hangjia.bxj.common.BaseCommonDO;

import java.io.Serializable;
import java.util.Date;

public class ActivityVoteSign extends BaseCommonDO implements Serializable {
    private Long id;

    private Long userId;

    private String realname;

    private String photo;

    private String mobile;

    private String signCode;

    private Integer step;

    private String homePage;

    private String company;

    private String job;

    private String idCard;

    private String yearSalary;

    private String myDes;

    private Integer signType;

    private String pic1;

    private String pic2;

    private String pic3;

    private String incomePic;

    private String weixinId;

    private String joinTime;

    private Integer num;

    private String userCardPic;

    private String positionPic;

    private String jobYear;

    private String province;

    private Integer status;

    private Date authTime;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getSignCode() {
        return signCode;
    }

    public void setSignCode(String signCode) {
        this.signCode = signCode == null ? null : signCode.trim();
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage == null ? null : homePage.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getYearSalary() {
        return yearSalary;
    }

    public void setYearSalary(String yearSalary) {
        this.yearSalary = yearSalary == null ? null : yearSalary.trim();
    }

    public String getMyDes() {
        return myDes;
    }

    public void setMyDes(String myDes) {
        this.myDes = myDes == null ? null : myDes.trim();
    }

    public Integer getSignType() {
        return signType;
    }

    public void setSignType(Integer signType) {
        this.signType = signType;
    }

    public String getPic1() {
        return pic1;
    }

    public void setPic1(String pic1) {
        this.pic1 = pic1 == null ? null : pic1.trim();
    }

    public String getPic2() {
        return pic2;
    }

    public void setPic2(String pic2) {
        this.pic2 = pic2 == null ? null : pic2.trim();
    }

    public String getPic3() {
        return pic3;
    }

    public void setPic3(String pic3) {
        this.pic3 = pic3 == null ? null : pic3.trim();
    }

    public String getIncomePic() {
        return incomePic;
    }

    public void setIncomePic(String incomePic) {
        this.incomePic = incomePic == null ? null : incomePic.trim();
    }

    public String getWeixinId() {
        return weixinId;
    }

    public void setWeixinId(String weixinId) {
        this.weixinId = weixinId == null ? null : weixinId.trim();
    }

    public String getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(String joinTime) {
        this.joinTime = joinTime == null ? null : joinTime.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getUserCardPic() {
        return userCardPic;
    }

    public void setUserCardPic(String userCardPic) {
        this.userCardPic = userCardPic == null ? null : userCardPic.trim();
    }

    public String getPositionPic() {
        return positionPic;
    }

    public void setPositionPic(String positionPic) {
        this.positionPic = positionPic == null ? null : positionPic.trim();
    }

    public String getJobYear() {
        return jobYear;
    }

    public void setJobYear(String jobYear) {
        this.jobYear = jobYear == null ? null : jobYear.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getAuthTime() {
        return authTime;
    }

    public void setAuthTime(Date authTime) {
        this.authTime = authTime;
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
        sb.append(", userId=").append(userId);
        sb.append(", realname=").append(realname);
        sb.append(", photo=").append(photo);
        sb.append(", mobile=").append(mobile);
        sb.append(", signCode=").append(signCode);
        sb.append(", step=").append(step);
        sb.append(", homePage=").append(homePage);
        sb.append(", company=").append(company);
        sb.append(", job=").append(job);
        sb.append(", idCard=").append(idCard);
        sb.append(", yearSalary=").append(yearSalary);
        sb.append(", myDes=").append(myDes);
        sb.append(", signType=").append(signType);
        sb.append(", pic1=").append(pic1);
        sb.append(", pic2=").append(pic2);
        sb.append(", pic3=").append(pic3);
        sb.append(", incomePic=").append(incomePic);
        sb.append(", weixinId=").append(weixinId);
        sb.append(", joinTime=").append(joinTime);
        sb.append(", num=").append(num);
        sb.append(", userCardPic=").append(userCardPic);
        sb.append(", positionPic=").append(positionPic);
        sb.append(", jobYear=").append(jobYear);
        sb.append(", province=").append(province);
        sb.append(", status=").append(status);
        sb.append(", authTime=").append(authTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}