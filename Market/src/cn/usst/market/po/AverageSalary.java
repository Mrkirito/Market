package cn.usst.market.po;

public class AverageSalary {
    private Integer id;

    private String region;

    private Integer salary;

    private Integer welfare;

    private Integer holiday;

    private Integer publicFund;

    private Integer companyPension;

    private Integer retiredPay;

    private Integer cost;

    private Integer isSale;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getWelfare() {
        return welfare;
    }

    public void setWelfare(Integer welfare) {
        this.welfare = welfare;
    }

    public Integer getHoliday() {
        return holiday;
    }

    public void setHoliday(Integer holiday) {
        this.holiday = holiday;
    }

    public Integer getPublicFund() {
        return publicFund;
    }

    public void setPublicFund(Integer publicFund) {
        this.publicFund = publicFund;
    }

    public Integer getCompanyPension() {
        return companyPension;
    }

    public void setCompanyPension(Integer companyPension) {
        this.companyPension = companyPension;
    }

    public Integer getRetiredPay() {
        return retiredPay;
    }

    public void setRetiredPay(Integer retiredPay) {
        this.retiredPay = retiredPay;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getIsSale() {
        return isSale;
    }

    public void setIsSale(Integer isSale) {
        this.isSale = isSale;
    }
}