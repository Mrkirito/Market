package cn.usst.market.po;

public class ProductInfo {
    private Integer id;

    private String detail;

    private String title;

    private Integer price;

    private Byte practical;

    private Byte perfect;

    private Byte business;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Byte getPractical() {
        return practical;
    }

    public void setPractical(Byte practical) {
        this.practical = practical;
    }

    public Byte getPerfect() {
        return perfect;
    }

    public void setPerfect(Byte perfect) {
        this.perfect = perfect;
    }

    public Byte getBusiness() {
        return business;
    }

    public void setBusiness(Byte business) {
        this.business = business;
    }
}