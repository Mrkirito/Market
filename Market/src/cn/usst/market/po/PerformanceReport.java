package cn.usst.market.po;

public class PerformanceReport {
	private CompanyMarketShare companyMarketShare;
	private Company company;
	private ProductMarketShare productMarketShare;
	private CompanyProduct companyProduct;
	

	public CompanyProduct getCompanyProduct() {
		return companyProduct;
	}

	public void setCompanyProduct(CompanyProduct companyProduct) {
		this.companyProduct = companyProduct;
	}

	public ProductMarketShare getProductMarketShare() {
		return productMarketShare;
	}

	public void setProductMarketShare(ProductMarketShare productMarketShare) {
		this.productMarketShare = productMarketShare;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public CompanyMarketShare getCompanyMarketShare() {
		return companyMarketShare;
	}

	public void setCompanyMarketShare(CompanyMarketShare companyMarketShare) {
		this.companyMarketShare = companyMarketShare;
	}
	
}
