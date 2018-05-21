package cn.usst.market.po;

public class WorkersSalary {
    private Integer id;

    private Integer companyId;

    private Integer quarter;

    private Integer salary;

    private Integer welfare;

    private Integer holiday;

    private Integer publicFund;

    private Integer companyPension;

    private Integer retiredPay;

    private Integer salaryTotal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public  void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getQuarter() {
        return quarter;
    }

    public void setQuarter(Integer quarter) {
        this.quarter = quarter;
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

    public Integer getSalaryTotal() {
        return salaryTotal;
    }

    public void setSalaryTotal(Integer salaryTotal) {
        this.salaryTotal = salaryTotal;
    }
}