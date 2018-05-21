package cn.usst.market.po;

public class MarketInfo {
    private String rented;

	private String opened;
	
    private Integer id;

    private String city;

    private Integer practical;

    private Integer perfect;

    private Integer business;

    private Integer webPractical;

    private Integer webPerfect;

    private Integer webBusiness;

    private Integer open;

    private Integer rent;

    private Integer webOpen;

    private Integer webRent;

    private String img;

    public String getRented() {
        return rented;
    }

    public void setRented(String rented) {
        this.rented = rented;
    }

    public String getOpened() {
		return opened;
	}

	public void setOpened(String opened) {
		this.opened = opened;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
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

    public Integer getWebPractical() {
        return webPractical;
    }

    public void setWebPractical(Integer webPractical) {
        this.webPractical = webPractical;
    }

    public Integer getWebPerfect() {
        return webPerfect;
    }

    public void setWebPerfect(Integer webPerfect) {
        this.webPerfect = webPerfect;
    }

    public Integer getWebBusiness() {
        return webBusiness;
    }

    public void setWebBusiness(Integer webBusiness) {
        this.webBusiness = webBusiness;
    }

    public Integer getOpen() {
        return open;
    }

    public void setOpen(Integer open) {
        this.open = open;
    }

    public Integer getRent() {
        return rent;
    }

    public void setRent(Integer rent) {
        this.rent = rent;
    }

    public Integer getWebOpen() {
        return webOpen;
    }

    public void setWebOpen(Integer webOpen) {
        this.webOpen = webOpen;
    }

    public Integer getWebRent() {
        return webRent;
    }

    public void setWebRent(Integer webRent) {
        this.webRent = webRent;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }
}