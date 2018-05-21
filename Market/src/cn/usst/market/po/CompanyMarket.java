package cn.usst.market.po;

public class CompanyMarket {
    private Integer id;

    private Integer companyId;

    private String marketId;

    private Integer isPhy;

    private Integer quarter;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId == null ? null : marketId.trim();
    }

    public Integer getIsPhy() {
        return isPhy;
    }

    public void setIsPhy(Integer isPhy) {
        this.isPhy = isPhy;
    }

    public Integer getQuarter() {
        return quarter;
    }

    public void setQuarter(Integer quarter) {
        this.quarter = quarter;
    }
}