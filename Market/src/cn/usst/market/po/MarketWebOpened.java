package cn.usst.market.po;

public class MarketWebOpened {
    private Integer companyId;
    private Integer quarter;
    private Integer cost;

    public MarketWebOpened() {
    }

    public Integer getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getQuarter() {
        return this.quarter;
    }

    public void setQuarter(Integer quarter) {
        this.quarter = quarter;
    }

    public Integer getCost() {
        return this.cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}
