package cn.usst.market.po;

public class MarketInfoWeight {
    private Integer id;

    private String cityName;

    private Float perfect;

    private Float business;

    private Float practical;

    private Integer rent;

    private Integer open;

    private Integer webRent;

    private Integer webOpen;

    private String img;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public Float getPerfect() {
        return perfect;
    }

    public void setPerfect(Float perfect) {
        this.perfect = perfect;
    }

    public Float getBusiness() {
        return business;
    }

    public void setBusiness(Float business) {
        this.business = business;
    }

    public Float getPractical() {
        return practical;
    }

    public void setPractical(Float practical) {
        this.practical = practical;
    }

    public Integer getRent() {
        return rent;
    }

    public void setRent(Integer rent) {
        this.rent = rent;
    }

    public Integer getOpen() {
        return open;
    }

    public void setOpen(Integer open) {
        this.open = open;
    }

    public Integer getWebRent() {
        return webRent;
    }

    public void setWebRent(Integer webRent) {
        this.webRent = webRent;
    }

    public Integer getWebOpen() {
        return webOpen;
    }

    public void setWebOpen(Integer webOpen) {
        this.webOpen = webOpen;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }
}