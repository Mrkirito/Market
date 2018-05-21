package cn.usst.market.po;

/**
 * Created by zasx-chenliyang on 2018/3/29.
 */
public class FinalCheckDO {
    private int id;
    private int companyId;
    private int productId;
    private int sale;
    private int quarter;
    private float price;
    private float salesSalary;
    private float worksSalary;
    private int isPhy;
    private int capacityNow;
    private String checkResult;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public int getQuarter() {
        return quarter;
    }

    public void setQuarter(int quarter) {
        this.quarter = quarter;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getSalesSalary() {
        return salesSalary;
    }

    public void setSalesSalary(float salesSalary) {
        this.salesSalary = salesSalary;
    }

    public float getWorksSalary() {
        return worksSalary;
    }

    public void setWorksSalary(float worksSalary) {
        this.worksSalary = worksSalary;
    }

    public int getIsPhy() {
        return isPhy;
    }

    public void setIsPhy(int isPhy) {
        this.isPhy = isPhy;
    }

    public int getCapacityNow() {
        return capacityNow;
    }

    public void setCapacityNow(int capacityNow) {
        this.capacityNow = capacityNow;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }
}
