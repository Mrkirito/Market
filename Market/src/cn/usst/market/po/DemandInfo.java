package cn.usst.market.po;

public class DemandInfo {
    private Integer id;

    private String detail;

    private Integer practical;

    private Integer perfect;

    private Integer business;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public Integer getPractical() {
        return practical;
    }

    public void setPractical(Integer practical) {
        this.practical = practical;
    }

    public Integer getPerfect() {
        return perfect;
    }

    public void setPerfect(Integer perfect) {
        this.perfect = perfect;
    }

    public Integer getBusiness() {
        return business;
    }

    public void setBusiness(Integer business) {
        this.business = business;
    }
}