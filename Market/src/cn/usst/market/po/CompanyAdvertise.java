package cn.usst.market.po;

public class CompanyAdvertise {
    private Integer id;
    
    private Integer companyId;
    
    private Integer quarter;
    
    private Integer productId;
    
    private String productName;
    
    private String advertiseName;
    
    private String advertiseId;

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

	public String getAdvertiseName() {
		return advertiseName;
	}

	public void setAdvertiseName(String advertiseName) {
		this.advertiseName = advertiseName;
	}

	public String getAdvertiseId() {
		return advertiseId;
	}

	public void setAdvertiseId(String advertiseId) {
		this.advertiseId = advertiseId;
	}


}