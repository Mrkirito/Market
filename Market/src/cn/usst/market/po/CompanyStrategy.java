package cn.usst.market.po;

public class CompanyStrategy {
    private Integer id;

    private Integer companyId;

    private String mainPro;

    private String minorPro;

    private String strategyId;

    private String service;

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

    public String getMainPro() {
        return mainPro;
    }

    public void setMainPro(String mainPro) {
        this.mainPro = mainPro == null ? null : mainPro.trim();
    }

    public String getMinorPro() {
        return minorPro;
    }

    public void setMinorPro(String minorPro) {
        this.minorPro = minorPro == null ? null : minorPro.trim();
    }

    public String getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(String strategyId) {
        this.strategyId = strategyId == null ? null : strategyId.trim();
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service == null ? null : service.trim();
    }

    public Integer getQuarter() {
        return quarter;
    }

    public void setQuarter(Integer quarter) {
        this.quarter = quarter;
    }
}