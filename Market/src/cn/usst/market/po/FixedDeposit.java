package cn.usst.market.po;

public class FixedDeposit {
    private Integer id;

    private Integer companyId;

    private Integer quarter;

    private Float cunkuanLast;

    private Float tiqu;

    private Float cunru;

    private Float lilv;

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

    public Integer getQuarter() {
        return quarter;
    }

    public void setQuarter(Integer quarter) {
        this.quarter = quarter;
    }

    public Float getCunkuanLast() {
        return cunkuanLast;
    }

    public void setCunkuanLast(Float cunkuanLast) {
        this.cunkuanLast = cunkuanLast;
    }

    public Float getTiqu() {
        return tiqu;
    }

    public void setTiqu(Float tiqu) {
        this.tiqu = tiqu;
    }

    public Float getCunru() {
        return cunru;
    }

    public void setCunru(Float cunru) {
        this.cunru = cunru;
    }

    public Float getLilv() {
        return lilv;
    }

    public void setLilv(Float lilv) {
        this.lilv = lilv;
    }
}