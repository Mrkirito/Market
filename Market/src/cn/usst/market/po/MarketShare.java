package cn.usst.market.po;

/**
 * Created by 陈立阳 on 2017/10/31.
 */
public class MarketShare {
    private Integer companyId;
    private Integer productId;
    private String productType;
    private Integer singaporeSale;
    private Integer singaporeNeed;
    private double eva;//品牌评价
    private double marketShare;
    private String companyName;
    private String productName;
    private Integer hongkongSale;
    private Integer hongkongNeed;
    private Integer moscowSale;
    private Integer moscowNeed;
    private Integer newdelhiSale;
    private Integer newdelhiNeed;
    private Integer onlineSale;
    private Integer onlineNeed;
    private Integer stockoun;
    private Integer stock;
    private Integer totalNeed;

    public Integer getTotalNeed() {
        return totalNeed;
    }

    public void setTotalNeed(Integer totalNeed) {
        this.totalNeed = totalNeed;
    }

    public Integer getSingaporeNeed() {
        return singaporeNeed;
    }

    public void setSingaporeNeed(Integer singaporeNeed) {
        this.singaporeNeed = singaporeNeed;
    }

    public Integer getHongkongSale() {
        return hongkongSale;
    }

    public void setHongkongSale(Integer hongkongSale) {
        this.hongkongSale = hongkongSale;
    }

    public Integer getHongkongNeed() {
        return hongkongNeed;
    }

    public void setHongkongNeed(Integer hongkongNeed) {
        this.hongkongNeed = hongkongNeed;
    }

    public Integer getMoscowSale() {
        return moscowSale;
    }

    public void setMoscowSale(Integer moscowSale) {
        this.moscowSale = moscowSale;
    }

    public Integer getMoscowNeed() {
        return moscowNeed;
    }

    public void setMoscowNeed(Integer moscowNeed) {
        this.moscowNeed = moscowNeed;
    }

    public Integer getNewdelhiSale() {
        return newdelhiSale;
    }

    public void setNewdelhiSale(Integer newdelhiSale) {
        this.newdelhiSale = newdelhiSale;
    }

    public Integer getNewdelhiNeed() {
        return newdelhiNeed;
    }

    public void setNewdelhiNeed(Integer newdelhiNeed) {
        this.newdelhiNeed = newdelhiNeed;
    }

    public Integer getOnlineSale() {
        return onlineSale;
    }

    public void setOnlineSale(Integer onlineSale) {
        this.onlineSale = onlineSale;
    }

    public Integer getOnlineNeed() {
        return onlineNeed;
    }

    public void setOnlineNeed(Integer onlineNeed) {
        this.onlineNeed = onlineNeed;
    }

    public Integer getStockoun() {
        return stockoun;
    }

    public void setStockoun(Integer stockoun) {
        this.stockoun = stockoun;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public double getEva() {
        return eva;
    }

    public void setEva(double eva) {
        this.eva = eva;
    }

    public Integer getSingaporeSale() {
        return singaporeSale;
    }

    public void setSingaporeSale(Integer singaporeSale) {
        this.singaporeSale = singaporeSale;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getMarketShare() {
        return marketShare;
    }

    public void setMarketShare(double marketShare) {
        this.marketShare = marketShare;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

}
