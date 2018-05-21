package cn.usst.market.po;

public class CompanyCapacity {
    private Integer id;

    private Integer capacityNow;

    private Integer capacityAdd;

    private Integer companyId;

    private Integer quarter;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCapacityNow() {
        return capacityNow;
    }

    public void setCapacityNow(Integer capacityNow) {
        this.capacityNow = capacityNow;
    }

    public Integer getCapacityAdd() {
        return capacityAdd;
    }

    public void setCapacityAdd(Integer capacityAdd) {
        this.capacityAdd = capacityAdd;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getQuarter() {
        return quarter;
    }

    public void setQuarter(Integer quarter) {
        this.quarter = quarter;
    }
}