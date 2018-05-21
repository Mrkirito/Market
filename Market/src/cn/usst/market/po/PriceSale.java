package cn.usst.market.po;

/**
 * Created by ³ÂÁ¢Ñô on 2017/11/4.
 */
public class PriceSale {
    private Integer companyId;
    private Integer productId;
    private String productName;
    private Integer price;
    private Integer youji;
    private Integer isSale;

    public Integer getIsSale() {
        return isSale;
    }

    public void setIsSale(Integer isSale) {
        this.isSale = isSale;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getYouji() {
        return youji;
    }

    public void setYouji(Integer youji) {
        this.youji = youji;
    }
}
